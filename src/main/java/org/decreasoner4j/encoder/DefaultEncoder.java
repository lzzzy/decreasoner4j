package org.decreasoner4j.encoder;

import java.util.HashMap;
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
	// List *predicatesToComplete;
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
			logger.error("ERROR: unable to get sort <%s>", name);
			return null;
		}

		return sort;
	}

	Constant constantCreate(String name, Sort sort, Atom argsorts, String resultsortname, boolean redefineOK) {
		Constant c;
		if ((c = constantD.get(name)) != null) {
			if (!redefineOK) {
				logger.warn("ignored redefinition of constant <%s>", name);
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

	Constant constantGet(String name) {
		return constantD.get(name);
	}

	// Sort *ESortCreate(Encoder *e,char *name,Bool reified,char *parentname)
	Sort sortCreate(String name, boolean reified, String parentname) {

		Sort sort, parent;
		if ((sort = sortD.get(name)) != null) {
			logger.info("ignored redefinition of sort <%s>", name);
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

}
