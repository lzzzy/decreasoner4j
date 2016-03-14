package org.decreasoner4j.encoder;

import org.decreasoner4j.antlr.encoder.EncoderParser.MaxIntContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.MinIntContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.ParentSortIdContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.RangeLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.SortIdContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.SortNonreifiedLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.SortReifiedLineContext;

public class DefaultEncoderVisitor extends EncoderVisitor<Value> {

	private DefaultEncoder encoder = new DefaultEncoder();
	
	@Override
	public Value visitSortNonreifiedLine(SortNonreifiedLineContext ctx) {
		encoder.sortCreate(visit(ctx.sortId()).string, false, visit(ctx.parentSortId()).string);
		return null;
	}

	@Override
	public Value visitSortReifiedLine(SortReifiedLineContext ctx) {
		encoder.sortCreate(visit(ctx.sortId()).string, true, visit(ctx.parentSortId()).string);
		return null;
	}

	@Override
	public Value visitRangeLine(RangeLineContext ctx) {

		Sort sort = encoder.sortGetWErr(visit(ctx.sortId()).string);
		int min = visit(ctx.minInt()).integer;
		int max = visit(ctx.maxInt()).integer;

		if (sort != null) {
			sort.minint = min;
			sort.maxint = max;
		}

		if ("time".equals(sort.name)) {
			encoder.mintime = min;
			encoder.maxtime = max;
		}

		for (int i = min; i <= max; i++) {
			String name = String.format("%d", i);
			Constant c;
			if ((c = encoder.constantGet(name)) == null) {
				encoder.constantCreate(name, sort, null, null, false);
			} else {
				c.sortAdd(sort, encoder.atomDict);
			}
		}

		return null;
	}

	@Override
	public Value visitParentSortId(ParentSortIdContext ctx) {
		return Value.asString(ctx.getText());
	}
	
	@Override
	public Value visitSortId(SortIdContext ctx) {
		return Value.asString(ctx.getText());
	}

	@Override
	public Value visitMinInt(MinIntContext ctx) {
		return Value.asInteger(ctx.getText());
	}

	@Override
	public Value visitMaxInt(MaxIntContext ctx) {
		return Value.asInteger(ctx.getText());
	}

	@Override
	public Encoder encoder() {
		return encoder;
	}

}
