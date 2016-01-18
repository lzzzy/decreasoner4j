package org.decreasoner4j.sat4j;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;

import org.decreasoner4j.Model;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.DimacsReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.ModelIterator;

public class Sat4JSolver {

	public static Set<Model> satModels(String fileName) throws FileNotFoundException {
		return satModels(new FileInputStream(new File(fileName)));
	}

	public static Set<Model> satModels(InputStream input) {

		Set<Model> result = new HashSet<Model>();

		ISolver solver = SolverFactory.newDefault();
		solver.setTimeout(3600); // 1 hour timeout
		Reader reader = new DimacsReader(new ModelIterator(solver));

		try {
			IProblem problem = reader.parseInstance(input);
			while (problem.isSatisfiable()) {
				result.add(Model.fromSat4j(problem.model()));
			}
		} catch (ContradictionException e) {
			System.err.println(e);
			System.err.println("c Unsatisfiable (trivial)!");
		} catch (TimeoutException e) {
			System.err.println(e);
			System.err.println("c Timeout, sorry!");
		} catch (AssertionError e) {
			System.err.println(e);
			System.err.println("c AssertionError: " + e.getMessage());
		} catch (ParseFormatException | IOException e) {
			System.err.println(e);
			System.err.println("c Error reading the input: " + e.getMessage());
		}

		return result;

	}

	public static void main(String[] args) throws IOException {
		for (Model each : satModels(args[0])) {
			System.out.println(each);
		}
	}

}
