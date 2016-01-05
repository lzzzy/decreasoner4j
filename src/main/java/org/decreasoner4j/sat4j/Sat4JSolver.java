package org.decreasoner4j.sat4j;

import java.io.IOException;
import java.io.PrintWriter;

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
	
	public static void main(String[] args) {
		ISolver solver = SolverFactory.newDefault();
		ModelIterator mi = new ModelIterator(solver);
		solver.setTimeout(3600); // 1 hour timeout
		Reader reader = new InstanceReader(mi);
		PrintWriter sysout = new PrintWriter(System.out);

		try {
			// CNF filename is given on the command line
			IProblem problem = reader.parseInstance(args[0]);
			int solutions = 0;
			while (problem.isSatisfiable()) {
				if (solutions++ == 0) {
					sysout.println("c Satisfiable!");
				}
				reader.decode(problem.model(), sysout);
				sysout.println();
			}
			if (solutions == 0) {
				sysout.println("c Unsatisfiable!");
			}
		} catch (ParseFormatException | IOException e) {
			e.printStackTrace(System.err);
		} catch (ContradictionException e) {
			sysout.println("c Unsatisfiable (trivial)!");
		} catch (TimeoutException e) {
			sysout.println("c Timeout, sorry!");
		} finally {
			sysout.close();
		}
	}

}
