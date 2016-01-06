package org.decreasoner4j;

import java.util.Arrays;
import static java.util.stream.Collectors.joining;

public class Model {

	private int numberVariables;
	private int[] sortedTrueVariables;

	protected Model(int[] model) {
		numberVariables = model.length;
		sortedTrueVariables = Arrays.stream(model).filter(x -> x > 0).sorted().toArray();
	}

	public static Model fromSat4j(int[] model) {
		return new Model(model);
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
