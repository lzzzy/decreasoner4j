package org.decreasoner4j.encoder;

import java.util.stream.Collectors;

import org.decreasoner4j.antlr.encoder.EncoderParser.ArgSortIdContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.CompletionLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.ConstantIdContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.ConstantNonreifiedLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.ConstantReifiedLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.LabelContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.MaxIntContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.MinIntContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.NoninertialLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.ParentSortIdContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.PredContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.RangeLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.ResultSortIdContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.SortIdContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.SortNonreifiedLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.SortReifiedLineContext;

public class DefaultEncoderVisitor extends EncoderVisitor<Value> {

	private DefaultEncoder encoder = new DefaultEncoder();
	
	@Override
	public Value visitConstantReifiedLine(ConstantReifiedLineContext ctx) {
		encoder.loadConstantReified(visit(ctx.sortId()).string,
				visit(ctx.constantId()).string,
				visit(ctx.resultSortId()).string,
				ctx.argSortId().stream().map(c -> visit(c).string).collect(Collectors.toList()));
		return null;
	}
	
	@Override
	public Value visitConstantNonreifiedLine(ConstantNonreifiedLineContext ctx) {
		encoder.loadConstantNonreified(visit(ctx.sortId()).string, ctx.constantId().stream().map(c -> visit(c).string).collect(Collectors.toList()));
		return null;
	}
	
	@Override
	public Value visitCompletionLine(CompletionLineContext ctx) {
		encoder.loadCompletion(visit(ctx.label()).string, visit(ctx.pred()).string);
		return null;
	}	
	
	@Override
	public Value visitNoninertialLine(NoninertialLineContext ctx) {
		encoder.loadNoninertial(ctx.constantId().stream().map(c -> visit(c).string).collect(Collectors.toList()));
		return null;
	}
	
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
		encoder.loadRange(visit(ctx.sortId()).string, visit(ctx.minInt()).integer, visit(ctx.maxInt()).integer);
		return null;
	}
	
	@Override
	public Value visitArgSortId(ArgSortIdContext ctx) {
		return Value.asString(ctx.getText());
	}
	
	@Override
	public Value visitResultSortId(ResultSortIdContext ctx) {
		return Value.asString(ctx.getText());
	}
	
	@Override
	public Value visitLabel(LabelContext ctx) {
		return Value.asString(ctx.getText());
	}
	
	@Override
	public Value visitPred(PredContext ctx) {
		return Value.asString(ctx.getText());
	}
	
	@Override
	public Value visitConstantId(ConstantIdContext ctx) {
		return Value.asString(ctx.getText());
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
