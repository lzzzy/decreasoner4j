package org.decreasoner4j.sat4j;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Set;

import org.decreasoner4j.Model;
import org.decreasoner4j.Problem;
import org.junit.Test;

public class Sat4JSolverTest {


	@Test
	public void testSatModels() throws Exception {
		for (Problem problem : Problem.getProblems()) {
			
			System.out.println("testSatModels: " + problem.path() + problem.name());

			String solverInput = "/" + problem.path() + problem.name() + "/solver.input";
			String solverOutput = "/" + problem.path() + problem.name() + "/solver.output";

			final Set<Model> sat4jModels;
			try (InputStream input = this.getClass().getResourceAsStream(solverInput)) {
				sat4jModels = Sat4JSolver.satModels(input);
			}

			final Set<Model> relsatModels;
			try (InputStream input = this.getClass().getResourceAsStream(solverInput);
					InputStream output = this.getClass().getResourceAsStream(solverOutput)) {
				relsatModels = Model.fromRelsat(input, output);
			}
			
			int numberSolutions = relsatModels.size();
			sat4jModels.retainAll(relsatModels);
			
			
			assertEquals(sat4jModels.size(), numberSolutions);
		}

	}

}
