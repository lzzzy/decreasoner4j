package org.decreasoner4j.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Atom {

	enum Type {
		ATOM, INTEGER, LIST;
	}

	Type type;

	String atom;
	int integer;
	List<Atom> list = new ArrayList<>();

	Atom(Atom... atoms) {
		type = Type.LIST;
		list.addAll(Arrays.asList(atoms));
	}
	
	Atom(List<String> atomNames) {
		type = Type.LIST;
		list.addAll(atomNames.stream().map(Atom::new).collect(Collectors.toList()));
	}

	Atom(String s) {
		type = Type.ATOM;
		atom = s;
	}

	public Atom(int i) {
		type = Type.INTEGER;
		integer = i;
	}

	// List *AtomCreate(char *s)
	static Atom atomCreate(String s, Map<String, Atom> atomDict) {

		Atom l;
		if ((l = atomDict.get(s)) != null) {
			return l;
		}
		l = new Atom(s);
		atomDict.put(s, l);
		return l;
	}

	// Bool AtomIn(List *a,List *l)
	static boolean atomIn(Atom a, Atom l) {

		if (l.type == Type.LIST) {
			return l.list.contains(a);
		}

		return false;
	}

	@Override
	public String toString() {
		switch (type) {
		case ATOM:
			return atom;
		case INTEGER:
			return String.valueOf(integer);
		case LIST:
			return list.toString();
		}
		return super.toString();
	}
}
