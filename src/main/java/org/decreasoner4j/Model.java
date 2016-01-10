package org.decreasoner4j;

import static java.util.stream.Collectors.joining;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Model {

	private int numberVariables;
	private int[] sortedTrueVariables;

	protected Model(int[] model) {
		this(model.length, model);
	}

	public Model(int numberVariables, int[] model) {
		this.numberVariables = numberVariables;
		sortedTrueVariables = Arrays.stream(model).filter(x -> x > 0).sorted().toArray();
	}

	public static Model fromSat4j(int[] model) {
		return new Model(model);
	}

	public static Set<Model> fromRelsat(InputStream solverInput, InputStream solverOutput) throws IOException {
		int numberVariables = parseRelsatInput(solverInput);
		List<int[]> solutions = parseRelsatOutput(solverOutput);

		Set<Model> result = new HashSet<>();

		for (int[] each : solutions) {
			result.add(new Model(numberVariables, each));

		}
		return result;
	}

	private static int parseRelsatInput(InputStream solverInput) throws IOException {
		final List<String> headLine;

		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(solverInput))) {
			headLine = buffer.lines().filter(line -> line.startsWith("p cnf ")).collect(Collectors.toList());
		}

		if (headLine.size() != 1) {
			throw new IllegalArgumentException("relsat input file contains no line starting with 'p cnf '");
		}

		return Integer.parseInt(headLine.get(0).substring("p cnf ".length()).split(" ")[0]);
	}

	private static List<int[]> parseRelsatOutput(InputStream solverOutput) throws IOException {

		List<int[]> solutions = new ArrayList<>();

		final List<String> solutionStrings;

		try (BufferedReader buffer = new BufferedReader(new InputStreamReader(solverOutput))) {
			solutionStrings = buffer.lines().filter(line -> !line.startsWith("c ") && !line.startsWith("SAT"))
					.collect(Collectors.toList());
		}

		StringBuffer solution = new StringBuffer();
		for (String line : solutionStrings) {
			if (line.startsWith("Solution ")) {

				if (solution.length() > 0) {
					String[] intsAsString = solution.toString().trim().split(" ");

					if (intsAsString.length == 1) {
						// only negated vars
						solutions.add(new int[0]);
					} else {
						solutions.add(Arrays.stream(intsAsString).mapToInt(Integer::parseInt).toArray());
					}
					solution.setLength(0);
				}

				int indexOfColon = line.indexOf(":");
				solution.append(line.substring(indexOfColon + 1));

			} else {
				solution.append(line);
			}
		}

		if (solution.length() > 0) {
			solutions.add(Arrays.stream(solution.toString().trim().split(" ")).mapToInt(Integer::parseInt).toArray());
			solution.setLength(0);
		}

		return solutions;
	}

	public int getNumberVariables() {
		return numberVariables;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + numberVariables;
		result = prime * result + Arrays.hashCode(sortedTrueVariables);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Model other = (Model) obj;
		if (numberVariables != other.numberVariables)
			return false;
		if (!Arrays.equals(sortedTrueVariables, other.sortedTrueVariables))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Arrays.stream(sortedTrueVariables).mapToObj(Integer::toString).collect(joining(" "));
	}
}
