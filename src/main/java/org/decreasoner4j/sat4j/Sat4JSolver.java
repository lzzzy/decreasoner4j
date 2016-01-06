package org.decreasoner4j.sat4j;

import java.io.IOException;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

import org.decreasoner4j.Model;
import org.sat4j.minisat.SolverFactory;
import org.sat4j.reader.InstanceReader;
import org.sat4j.reader.ParseFormatException;
import org.sat4j.reader.Reader;
import org.sat4j.specs.ContradictionException;
import org.sat4j.specs.IProblem;
import org.sat4j.specs.ISolver;
import org.sat4j.specs.TimeoutException;
import org.sat4j.tools.ModelIterator;

public class Sat4JSolver {

	public static Model[] satModels(String fileName) {

		Set<Model> result = new TreeSet<Model>(new ModelComparator());

		ISolver solver = SolverFactory.newDefault();
		solver.setTimeout(3600); // 1 hour timeout
		Reader reader = new InstanceReader(new ModelIterator(solver));

		try {
			IProblem problem = reader.parseInstance(fileName);
			while (problem.isSatisfiable()) {
				result.add(Model.fromSat4j(problem.model()));
			}
		} catch (ParseFormatException | IOException e) {
			e.printStackTrace(System.err);
		} catch (ContradictionException e) {
			System.err.println("c Unsatisfiable (trivial)!");
		} catch (TimeoutException e) {
			System.err.println("c Timeout, sorry!");
		}

		return result.toArray(new Model[result.size()]);

	}

	public static void main(String[] args) throws IOException {
		for (Model each : satModels(args[0])) {
			System.out.println(each);
		}
	}

	private static class ModelComparator implements Comparator<Model> {
		@Override
		public int compare(Model m1, Model m2) {
			return m1.toString().compareTo(m2.toString());
		}
	}

}
