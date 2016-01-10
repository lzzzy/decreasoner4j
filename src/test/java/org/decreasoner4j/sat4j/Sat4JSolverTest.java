package org.decreasoner4j.sat4j;

import static org.junit.Assert.*;

import java.io.InputStream;
import java.util.Set;

import org.decreasoner4j.Model;
import org.junit.Test;

public class Sat4JSolverTest {

	private String[][] problems = { 
			{ "decreasoner/examples/AkmanEtAl2004/", "ZooTest1" },
			{ "decreasoner/examples/AkmanEtAl2004/", "ZooTest2" },
			{ "decreasoner/examples/AkmanEtAl2004/", "ZooTest3" },
			{ "decreasoner/examples/AkmanEtAl2004/", "ZooTest4.1" },
			{ "decreasoner/examples/AkmanEtAl2004/", "ZooTest4.2" },
			{ "decreasoner/examples/AkmanEtAl2004/", "ZooTest5.1" },
			{ "decreasoner/examples/AkmanEtAl2004/", "ZooTest5.2" },
			{ "decreasoner/examples/AkmanEtAl2004/", "ZooTest6" },
			{ "decreasoner/examples/AkmanEtAl2004/", "ZooWorld" },

			{ "decreasoner/examples/Antoniou1997/", "Dropout" },
			{ "decreasoner/examples/Antoniou1997/", "Student" },
			
			{ "decreasoner/examples/BrewkaDixKonolige1997/", "Wine" },
			
			{ "decreasoner/examples/Cassimatis2002/", "OneScreen" },
			{ "decreasoner/examples/Cassimatis2002/", "PolySpace" },
			{ "decreasoner/examples/Cassimatis2002/", "TwoScreens" },

			//{ "decreasoner/examples/FrankEtAl2003/", "FrankEtAl" },
			{ "decreasoner/examples/FrankEtAl2003/", "Story1" },
						
			{ "decreasoner/examples/Manual/", "Example1" }, 
			{ "decreasoner/examples/Manual/", "Example1a"},
			{ "decreasoner/examples/Manual/", "Example2" },
			{ "decreasoner/examples/Manual/", "Example3" },
			{ "decreasoner/examples/Manual/", "Example4" },

		    { "decreasoner/examples/MillerShanahan2002/", "Bowl" },
		      
			{ "decreasoner/examples/Mueller2004a/", "Holding" },
			{ "decreasoner/examples/Mueller2004a/", "Leaf" },

			{ "decreasoner/examples/Mueller2004b/", "Approve" },
			{ "decreasoner/examples/Mueller2004b/", "Leaf" },
			{ "decreasoner/examples/Mueller2004b/", "OffOn" },
			{ "decreasoner/examples/Mueller2004b/", "PickUp" },
			{ "decreasoner/examples/Mueller2004b/", "RouletteWheel" },
			{ "decreasoner/examples/Mueller2004b/", "RunningAndDriving1" },
			{ "decreasoner/examples/Mueller2004b/", "RunningAndDriving2" },
			{ "decreasoner/examples/Mueller2004b/", "TV1" },
			{ "decreasoner/examples/Mueller2004b/", "TV2" },

  			{ "decreasoner/examples/Mueller2006/Chapter10/", "MovingNewspaperAndBox" },
  			{ "decreasoner/examples/Mueller2006/Chapter10/", "OneScreen" },
  			{ "decreasoner/examples/Mueller2006/Chapter10/", "TwoScreens" },

  			{ "decreasoner/examples/Mueller2006/Chapter11/", "HungryCat" },
  			{ "decreasoner/examples/Mueller2006/Chapter11/", "Lottery" },
  			
  			{ "decreasoner/examples/Mueller2006/Chapter12/", "BrokenDevice" },
  			{ "decreasoner/examples/Mueller2006/Chapter12/", "DefaultEvent" },
  			{ "decreasoner/examples/Mueller2006/Chapter12/", "DefaultLocation" },
  			{ "decreasoner/examples/Mueller2006/Chapter12/", "Device" },
  			{ "decreasoner/examples/Mueller2006/Chapter12/", "ErraticDevice" },
  			{ "decreasoner/examples/Mueller2006/Chapter12/", "MethodB" },
  			{ "decreasoner/examples/Mueller2006/Chapter12/", "MethodD" },

  			{ "decreasoner/examples/Mueller2006/Chapter13/", "Abduction" },
  			{ "decreasoner/examples/Mueller2006/Chapter13/", "Deduction1" },
  			{ "decreasoner/examples/Mueller2006/Chapter13/", "Deduction2" },
  			{ "decreasoner/examples/Mueller2006/Chapter13/", "ModelFinding" },
  			{ "decreasoner/examples/Mueller2006/Chapter13/", "Postdiction" },

  			{ "decreasoner/examples/Mueller2006/Chapter14/", "NetBill1" },
  			{ "decreasoner/examples/Mueller2006/Chapter14/", "NetBill2" },
  			{ "decreasoner/examples/Mueller2006/Chapter14/", "NetBill3" },
  			{ "decreasoner/examples/Mueller2006/Chapter14/", "Vision" },
  			{ "decreasoner/examples/Mueller2006/Chapter14/", "Workflow" },
  			
  			{ "decreasoner/examples/Mueller2006/Chapter2/", "Inconsistency1" },
  			{ "decreasoner/examples/Mueller2006/Chapter2/", "Inconsistency2" },
  			{ "decreasoner/examples/Mueller2006/Chapter2/", "Inconsistency3" },
  			{ "decreasoner/examples/Mueller2006/Chapter2/", "Inconsistency4" },
  			{ "decreasoner/examples/Mueller2006/Chapter2/", "Sleep1" },
  			{ "decreasoner/examples/Mueller2006/Chapter2/", "Sleep12" },
  			{ "decreasoner/examples/Mueller2006/Chapter2/", "Sleep2" },
  			{ "decreasoner/examples/Mueller2006/Chapter2/", "Sleep3" },
  			{ "decreasoner/examples/Mueller2006/Chapter2/", "Sleep4" },

  			{ "decreasoner/examples/Mueller2006/Chapter3/", "Telephone1" },
  			{ "decreasoner/examples/Mueller2006/Chapter3/", "Telephone2" },

  			{ "decreasoner/examples/Mueller2006/Chapter4/", "AlarmClock" },
  			{ "decreasoner/examples/Mueller2006/Chapter4/", "BankAccountServiceFee" },


  			{ "decreasoner/examples/Mueller2006/Chapter6/", "CarryingABook1" },
  			{ "decreasoner/examples/Mueller2006/Chapter6/", "CarryingABook2" },
  			{ "decreasoner/examples/Mueller2006/Chapter6/", "ShanahanCircuit" },
  			{ "decreasoner/examples/Mueller2006/Chapter6/", "ThielscherCircuit1" },
  			{ "decreasoner/examples/Mueller2006/Chapter6/", "ThielscherCircuit2" },

  			{ "decreasoner/examples/Mueller2006/Chapter7/", "FallingObjectWithAntiTrajectory" },
  			{ "decreasoner/examples/Mueller2006/Chapter7/", "FallingObjectWithEvents" },
  			{ "decreasoner/examples/Mueller2006/Chapter7/", "HotAirBalloon" },

  			{ "decreasoner/examples/Mueller2006/Chapter8/", "CameraWithFlash" },
  			{ "decreasoner/examples/Mueller2006/Chapter8/", "MovingRobot" },
  			{ "decreasoner/examples/Mueller2006/Chapter8/", "PatHeadRubStomach" },

  			{ "decreasoner/examples/Mueller2006/Chapter9/", "RouletteWheel" },
  			{ "decreasoner/examples/Mueller2006/Chapter9/", "RunningAndDriving" },

  			{ "decreasoner/examples/Mueller2006/Exercises/", "Counter" },
  			{ "decreasoner/examples/Mueller2006/Exercises/", "MixingPaints" },
  			{ "decreasoner/examples/Mueller2006/Exercises/", "SnoozeAlarm" },
  			{ "decreasoner/examples/Mueller2006/Exercises/", "TeacherTells" },
  			{ "decreasoner/examples/Mueller2006/Exercises/", "TelephoneBugs" },
			
  			{ "decreasoner/examples/ReiterCriscuolo1981/", "NixonDiamond1" },
  			{ "decreasoner/examples/ReiterCriscuolo1981/", "NixonDiamond2" },

  			{ "decreasoner/examples/Shanahan1997/", "BusRide" },
  			{ "decreasoner/examples/Shanahan1997/", "DeadOrAlive" },
  			{ "decreasoner/examples/Shanahan1997/", "StolenCar" },
  			{ "decreasoner/examples/Shanahan1997/", "StuffyRoom" },
  			{ "decreasoner/examples/Shanahan1997/", "Supermarket" },
  			{ "decreasoner/examples/Shanahan1997/", "Yale" },

  			{ "decreasoner/examples/Shanahan1999/", "ChessBoard" },
  			{ "decreasoner/examples/Shanahan1999/", "CoinToss" },
  			{ "decreasoner/examples/Shanahan1999/", "Happy" },
  			{ "decreasoner/examples/Shanahan1999/", "RussianTurkey" },
  			{ "decreasoner/examples/Shanahan1999/", "ThielscherCircuit" },
			
	};

	@Test
	public void testSatModels() throws Exception {
		for (String[] problem : problems) {
			String path = problem[0];
			String name = problem[1];
			
			System.out.println("testSatModels: " + path + name);

			String solverInput = "/" + path + name + "/solver.input";
			String solverOutput = "/" + path + name + "/solver.output";

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
