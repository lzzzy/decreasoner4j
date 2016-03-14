package org.decreasoner4j.encoder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultEncoder extends Encoder {

	Logger logger = LoggerFactory.getLogger(DefaultEncoder.class);

	int debug;
	int renaming;
	int encoding;
	int weighted;
	String inputFilename;
	String outputFilename;
	String propositionFilename;
	String propositionStream;
	Map<String, Sort> sortD = new HashMap<>();
	Map<String, Constant> constantD = new HashMap<>();
	// Dict *formulaD;
	// List *formulas;
	List<Atom> predicatesToComplete = new ArrayList<>();
	int nextproposition;
	// Clause *clauses;
	double minobjective;
	Sort sorts;
	int mintime;
	int maxtime;
	// List *initiates;
	// List *terminates;
	// List *releases;
	// List *trajectory;
	// List *antitrajectory;
	// List *clipped;
	// List *declipped;

	Map<String, Atom> atomDict = new HashMap<>();

	Sort sortGet(String name) {
		return sortD.get(name);
	}

	Sort sortGetWErr(String name) {
		Sort sort = sortGet(name);
		if (sort == null) {
			logger.error("ERROR: unable to get sort <{}>", name);
			return null;
		}

		return sort;
	}

	// Constant *EConstantCreate(Encoder *e,char *name,Sort *sort,
	// List *argsorts,char *resultsortname,
	// Bool redefineOK)
	Constant constantCreate(String name, Sort sort, Atom argsorts, String resultsortname, boolean redefineOK) {
		Constant c;
		if ((c = constantD.get(name)) != null) {
			if (!redefineOK) {
				logger.warn("ignored redefinition of constant <{}>", name);
				return c;
			}
		} else {
			c = new Constant(name);
			constantD.put(name, c);
		}
		c.sortAdd(sort, atomDict);
		if (argsorts != null) {
			c.argsorts = argsorts;
		}
		if (resultsortname != null) {
			c.resultsort = sortGetWErr(resultsortname);
		}
		return c;
	}

	Constant constantGetWErr(String name) {
		Constant constant = constantGet(name);
		if (constant == null) {
			logger.error("ERROR: unable to get constant <{}>", name);
			return null;
		}

		return constant;
	}
	
	//Bool ELoadConstantReified(Encoder *e,char *s)
	void loadConstantReified(String sortName, String constantName, String resultSortName, List<String> argSortNames) {
		Sort sort;
		if ((sort = sortGetWErr(sortName)) == null) {
			return;
		}
		if (!sort.reified) {
			logger.error("ERROR: defining reified constant for nonreified sort <{}>", sortName);
			return;
		}
		constantCreate(constantName, sort, new Atom(argSortNames), resultSortName, true);
	}

	// Bool ELoadConstantNonreified(Encoder *e,char *s)
	void loadConstantNonreified(String sortName, List<String> constantsNames) {
		Sort sort;
		if ((sort = sortGetWErr(sortName)) == null) {
			return;
		}
		if (sort.reified) {
			logger.error("ERROR: defining nonreified constant for reified sort <{}>", sortName);
			return;
		}
		for (String constantName : constantsNames) {
			constantCreate(constantName, sort, null, null, true);
		}
	}

	// Bool ELoadCompletion(Encoder *e,char *s)
	void loadCompletion(String label, String pred) {
		predicatesToComplete.add(new Atom(Atom.atomCreate(label, atomDict), Atom.atomCreate(pred, atomDict)));
	}

	// Constant *EConstantGet(Encoder *e,char *name)
	Constant constantGet(String name) {
		return constantD.get(name);
	}

	// Bool ELoadNoninertial(Encoder *e,char *s)
	void loadNoninertial(List<String> constantsNames) {
		Constant c;
		for (String constName : constantsNames) {
			c = constantGetWErr(Atom.atomCreate(constName, atomDict).atom);
			if (c == null) {
				continue;
			}
			if (!constantSubsort(sortGetWErr("event"), c) && !constantSubsort(sortGetWErr("fluent"), c)) {
				logger.error("ERROR: <{}> not event/fluent", c.name);
				continue;
			}
			c.noninertial = true;
		}
	}

	// Bool ConstantSubsort(Encoder *e,char *s,Constant *c)
	boolean constantSubsort(Sort parentSort, Constant c) {
		Sort sort;
		for (Atom atom : c.sorts.list) {
			sort = sortGetWErr(atom.atom);
			if (sort == null) {
				continue;
			}
			if (sortSubsort(parentSort, sort)) {
				return true;
			}
		}
		return false;
	}

	// Bool SortSubsort1(Sort *anc,Sort *des)
	// Bool SortSubsort(Encoder *e,char *ancname,Sort *des)
	// Bool SortSubsortString(Encoder *e,char *ancname,char *desname)
	boolean sortSubsort(Sort parentSort, Sort subSort) {
		if (parentSort.equals(subSort)) {
			return true;
		}
		for (Sort sort : subSort.parents) {
			if (sortSubsort(parentSort, sort)) {
				return true;
			}
		}
		return false;
	}

	// Sort *ESortCreate(Encoder *e,char *name,Bool reified,char *parentname)
	Sort sortCreate(String name, boolean reified, String parentname) {

		Sort sort, parent;
		if ((sort = sortD.get(name)) != null) {
			logger.info("ignored redefinition of sort <{}>", name);
			return sort;
		}
		sort = new Sort(name, reified, sorts);
		if (!parentname.equals("na")) {
			if (null != (parent = sortGetWErr(parentname))) {
				sort.addParent(parent);
			}
		}
		sortD.put(name, sort);
		sorts = sort;
		return sort;
	}

	// Bool ELoadRange(Encoder *e,char *s)
	void loadRange(String sortName, int min, int max) {
		Sort sort = sortGetWErr(sortName);

		if (sort != null) {
			sort.minint = min;
			sort.maxint = max;
		}

		if ("time".equals(sort.name)) {
			mintime = min;
			maxtime = max;
		}

		for (int i = min; i <= max; i++) {
			String name = String.format("%d", i);
			Constant c;
			if ((c = constantGet(name)) == null) {
				constantCreate(name, sort, null, null, false);
			} else {
				c.sortAdd(sort, atomDict);
			}
		}
	}
}
