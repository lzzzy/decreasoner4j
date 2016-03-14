package org.decreasoner4j;

public class Problem {

	private String path, name;

	public Problem(String path, String name) {
		super();
		this.path = path;
		this.name = name;
	}

	
	
	private static Problem[] problems = new Problem[] { new Problem("decreasoner/examples/AkmanEtAl2004/", "ZooTest1"),

			new Problem("decreasoner/examples/AkmanEtAl2004/", "ZooTest2"),
			new Problem("decreasoner/examples/AkmanEtAl2004/", "ZooTest3"),
			new Problem("decreasoner/examples/AkmanEtAl2004/", "ZooTest4.1"),
			new Problem("decreasoner/examples/AkmanEtAl2004/", "ZooTest4.2"),
			new Problem("decreasoner/examples/AkmanEtAl2004/", "ZooTest5.1"),
			new Problem("decreasoner/examples/AkmanEtAl2004/", "ZooTest5.2"),
			new Problem("decreasoner/examples/AkmanEtAl2004/", "ZooTest6"),
			new Problem("decreasoner/examples/AkmanEtAl2004/", "ZooWorld"),

			new Problem("decreasoner/examples/Antoniou1997/", "Dropout"),
			new Problem("decreasoner/examples/Antoniou1997/", "Student"),

			new Problem("decreasoner/examples/BrewkaDixKonolige1997/", "Wine"),

			new Problem("decreasoner/examples/Cassimatis2002/", "OneScreen"),
			new Problem("decreasoner/examples/Cassimatis2002/", "PolySpace"),
			new Problem("decreasoner/examples/Cassimatis2002/", "TwoScreens"),

			// new Problem( "decreasoner/examples/FrankEtAl2003/", "FrankEtAl" ),
			new Problem("decreasoner/examples/FrankEtAl2003/", "Story1"),

			new Problem("decreasoner/examples/Manual/", "Example1"),
			new Problem("decreasoner/examples/Manual/", "Example1a"),
			new Problem("decreasoner/examples/Manual/", "Example2"),
			new Problem("decreasoner/examples/Manual/", "Example3"),
			new Problem("decreasoner/examples/Manual/", "Example4"),

			new Problem("decreasoner/examples/MillerShanahan2002/", "Bowl"),

			new Problem("decreasoner/examples/Mueller2004a/", "Holding"),
			new Problem("decreasoner/examples/Mueller2004a/", "Leaf"),

			new Problem("decreasoner/examples/Mueller2004b/", "Approve"),
			new Problem("decreasoner/examples/Mueller2004b/", "Leaf"),
			new Problem("decreasoner/examples/Mueller2004b/", "OffOn"),
			new Problem("decreasoner/examples/Mueller2004b/", "PickUp"),
			new Problem("decreasoner/examples/Mueller2004b/", "RouletteWheel"),
			new Problem("decreasoner/examples/Mueller2004b/", "RunningAndDriving1"),
			new Problem("decreasoner/examples/Mueller2004b/", "RunningAndDriving2"),
			new Problem("decreasoner/examples/Mueller2004b/", "TV1"),
			new Problem("decreasoner/examples/Mueller2004b/", "TV2"),

			new Problem("decreasoner/examples/Mueller2006/Chapter10/", "MovingNewspaperAndBox"),
			new Problem("decreasoner/examples/Mueller2006/Chapter10/", "OneScreen"),
			new Problem("decreasoner/examples/Mueller2006/Chapter10/", "TwoScreens"),

			new Problem("decreasoner/examples/Mueller2006/Chapter11/", "HungryCat"),
			new Problem("decreasoner/examples/Mueller2006/Chapter11/", "Lottery"),

			new Problem("decreasoner/examples/Mueller2006/Chapter12/", "BrokenDevice"),
			new Problem("decreasoner/examples/Mueller2006/Chapter12/", "DefaultEvent"),
			new Problem("decreasoner/examples/Mueller2006/Chapter12/", "DefaultLocation"),
			new Problem("decreasoner/examples/Mueller2006/Chapter12/", "Device"),
			new Problem("decreasoner/examples/Mueller2006/Chapter12/", "ErraticDevice"),
			new Problem("decreasoner/examples/Mueller2006/Chapter12/", "MethodB"),
			new Problem("decreasoner/examples/Mueller2006/Chapter12/", "MethodD"),

			new Problem("decreasoner/examples/Mueller2006/Chapter13/", "Abduction"),
			new Problem("decreasoner/examples/Mueller2006/Chapter13/", "Deduction1"),
			new Problem("decreasoner/examples/Mueller2006/Chapter13/", "Deduction2"),
			new Problem("decreasoner/examples/Mueller2006/Chapter13/", "ModelFinding"),
			new Problem("decreasoner/examples/Mueller2006/Chapter13/", "Postdiction"),

			new Problem("decreasoner/examples/Mueller2006/Chapter14/", "NetBill1"),
			new Problem("decreasoner/examples/Mueller2006/Chapter14/", "NetBill2"),
			new Problem("decreasoner/examples/Mueller2006/Chapter14/", "NetBill3"),
			new Problem("decreasoner/examples/Mueller2006/Chapter14/", "Vision"),
			new Problem("decreasoner/examples/Mueller2006/Chapter14/", "Workflow"),

			new Problem("decreasoner/examples/Mueller2006/Chapter2/", "Inconsistency1"),
			new Problem("decreasoner/examples/Mueller2006/Chapter2/", "Inconsistency2"),
			new Problem("decreasoner/examples/Mueller2006/Chapter2/", "Inconsistency3"),
			new Problem("decreasoner/examples/Mueller2006/Chapter2/", "Inconsistency4"),
			new Problem("decreasoner/examples/Mueller2006/Chapter2/", "Sleep1"),
			new Problem("decreasoner/examples/Mueller2006/Chapter2/", "Sleep12"),
			new Problem("decreasoner/examples/Mueller2006/Chapter2/", "Sleep2"),
			new Problem("decreasoner/examples/Mueller2006/Chapter2/", "Sleep3"),
			new Problem("decreasoner/examples/Mueller2006/Chapter2/", "Sleep4"),

			new Problem("decreasoner/examples/Mueller2006/Chapter3/", "Telephone1"),
			new Problem("decreasoner/examples/Mueller2006/Chapter3/", "Telephone2"),

			new Problem("decreasoner/examples/Mueller2006/Chapter4/", "AlarmClock"),
			new Problem("decreasoner/examples/Mueller2006/Chapter4/", "BankAccountServiceFee"),

			new Problem("decreasoner/examples/Mueller2006/Chapter6/", "CarryingABook1"),
			new Problem("decreasoner/examples/Mueller2006/Chapter6/", "CarryingABook2"),
			new Problem("decreasoner/examples/Mueller2006/Chapter6/", "ShanahanCircuit"),
			new Problem("decreasoner/examples/Mueller2006/Chapter6/", "ThielscherCircuit1"),
			new Problem("decreasoner/examples/Mueller2006/Chapter6/", "ThielscherCircuit2"),

			new Problem("decreasoner/examples/Mueller2006/Chapter7/", "FallingObjectWithAntiTrajectory"),
			new Problem("decreasoner/examples/Mueller2006/Chapter7/", "FallingObjectWithEvents"),
			new Problem("decreasoner/examples/Mueller2006/Chapter7/", "HotAirBalloon"),

			new Problem("decreasoner/examples/Mueller2006/Chapter8/", "CameraWithFlash"),
			new Problem("decreasoner/examples/Mueller2006/Chapter8/", "MovingRobot"),
			new Problem("decreasoner/examples/Mueller2006/Chapter8/", "PatHeadRubStomach"),

			new Problem("decreasoner/examples/Mueller2006/Chapter9/", "RouletteWheel"),
			new Problem("decreasoner/examples/Mueller2006/Chapter9/", "RunningAndDriving"),

			new Problem("decreasoner/examples/Mueller2006/Exercises/", "Counter"),
			new Problem("decreasoner/examples/Mueller2006/Exercises/", "MixingPaints"),
			new Problem("decreasoner/examples/Mueller2006/Exercises/", "SnoozeAlarm"),
			new Problem("decreasoner/examples/Mueller2006/Exercises/", "TeacherTells"),
			new Problem("decreasoner/examples/Mueller2006/Exercises/", "TelephoneBugs"),

			//new Problem("decreasoner/examples/ReiterCriscuolo1981/", "NixonDiamond1"),
			new Problem("decreasoner/examples/ReiterCriscuolo1981/", "NixonDiamond2"),

			new Problem("decreasoner/examples/Shanahan1997/", "BusRide"),
			new Problem("decreasoner/examples/Shanahan1997/", "DeadOrAlive"),
			new Problem("decreasoner/examples/Shanahan1997/", "StolenCar"),
			new Problem("decreasoner/examples/Shanahan1997/", "StuffyRoom"),
			new Problem("decreasoner/examples/Shanahan1997/", "Supermarket"),
			new Problem("decreasoner/examples/Shanahan1997/", "Yale"),

			new Problem("decreasoner/examples/Shanahan1999/", "ChessBoard"),
			new Problem("decreasoner/examples/Shanahan1999/", "CoinToss"),
			new Problem("decreasoner/examples/Shanahan1999/", "Happy"),
			new Problem("decreasoner/examples/Shanahan1999/", "RussianTurkey"),
			new Problem("decreasoner/examples/Shanahan1999/", "ThielscherCircuit"),

	};

	public String name() {
		return name;
	}

	public String path() {
		return path;
	}
	
	public static Problem[] getProblems() {
		return problems;
	}

}
