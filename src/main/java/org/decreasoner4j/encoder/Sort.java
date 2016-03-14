package org.decreasoner4j.encoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sort {
	
	static Logger logger = LoggerFactory.getLogger(Sort.class);

	String name;
	boolean reified;
	List<Sort> parents = new ArrayList<>();
	List<Sort> children = new ArrayList<>();
	Atom constants = new Atom();
	Atom rconstants = new Atom();
	int minint;
	int maxint;
	Sort next;

	Sort(String name, boolean reified, Sort next) {
		this.name = name;
		this.reified = reified;
		this.next = next;
	}

	void constantAdd(Constant c, Map<String, Atom> atomDict) {
		constants.list.add(Atom.atomCreate(c.name, atomDict));
	}
	
	//void SortParentAdd(Sort *sort,Sort *parent)
	void addParent(Sort parent) {
		parents.add(parent);
		parent.children.add(this);
	}
	
	@Override
	public String toString() {
		return name;
	}
}
