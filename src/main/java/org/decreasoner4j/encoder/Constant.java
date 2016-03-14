package org.decreasoner4j.encoder;

import java.util.Map;

public class Constant {

	String name;
	boolean noninertial;
	Atom sorts = new Atom();
	Atom argsorts = new Atom();
	Sort resultsort;
	// Dict *valueD;


	// Constant *ConstantCreate()
	Constant(String name) {
		this.name = name;
	}

	void sortAdd(Sort sort, Map<String, Atom> atomDict) {

		Atom atom = Atom.atomCreate(sort.name, atomDict);
		if (!Atom.atomIn(atom, sorts)) {
			sorts.list.add(atom);
			sort.constantAdd(this, atomDict);
		}
	}
	
	@Override
	public String toString() {
		return name;
	}
}
