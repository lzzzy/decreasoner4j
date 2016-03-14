package org.decreasoner4j.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ParseTree;
import org.decreasoner4j.antlr.encoder.EncoderParser.CompletionLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.ConstantNonreifiedLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.ConstantReifiedLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.FormulaContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.FormulaLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.FunctionValueLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.LineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.NoninertialLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.RangeLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.SortNonreifiedLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.SortReifiedLineContext;

public class EchoEncoderVisitor extends EncoderVisitor<String> {

	private EchoEncoder encoder = new EchoEncoder();

	@Override
	public Encoder encoder() {
		return encoder;
	}

	@Override
	public String visitLine(LineContext ctx) {
		String result = super.visitLine(ctx);
		encoder.getLines().add(result);
		return result;
	}

	@Override
	public String visitRangeLine(RangeLineContext ctx) {
		return toString(ctx.Range(), ctx.sortId(), ctx.minInt(), ctx.maxInt());
	}

	@Override
	public String visitSortNonreifiedLine(SortNonreifiedLineContext ctx) {
		return toString(ctx.Sort(), ctx.sortId(), ctx.parentSortId());
	}

	@Override
	public String visitSortReifiedLine(SortReifiedLineContext ctx) {
		return toString(ctx.Reified(), ctx.Sort(), ctx.sortId(), ctx.parentSortId());
	}

	@Override
	public String visitConstantNonreifiedLine(ConstantNonreifiedLineContext ctx) {
		List<ParseTree> nodeList = new ArrayList<>();
		nodeList.add(ctx.Constant());
		nodeList.add(ctx.sortId());
		nodeList.addAll(ctx.constantId());
		return toString(toArray(nodeList));
	}

	@Override
	public String visitConstantReifiedLine(ConstantReifiedLineContext ctx) {
		List<ParseTree> nodeList = new ArrayList<>();
		nodeList.add(ctx.Reified());
		nodeList.add(ctx.Constant());
		nodeList.add(ctx.sortId());
		nodeList.add(ctx.constantId());
		nodeList.add(ctx.resultSortId());
		nodeList.addAll(ctx.argSortId());
		return toString(toArray(nodeList));
	}

	@Override
	public String visitNoninertialLine(NoninertialLineContext ctx) {
		List<ParseTree> nodeList = new ArrayList<>();
		nodeList.add(ctx.Noninertial());
		nodeList.addAll(ctx.constantId());
		return toString(toArray(nodeList));
	}

	@Override
	public String visitCompletionLine(CompletionLineContext ctx) {
		return toString(ctx.Completion(), ctx.label(), ctx.pred());
	}

	@Override
	public String visitFunctionValueLine(FunctionValueLineContext ctx) {
		List<ParseTree> nodeList = new ArrayList<>();
		nodeList.add(ctx.FunctionValue());
		nodeList.add(ctx.functionId());
		nodeList.add(ctx.value());
		nodeList.addAll(ctx.argument());
		return toString(toArray(nodeList));
	}

	@Override
	public String visitFormulaLine(FormulaLineContext ctx) {
		// System.out.println(ctx.Formula() + " " + this.visit(ctx.formula()));
		return ctx.Formula() + " " + this.visit(ctx.formula());
	}

	@Override
	public String visitFormula(FormulaContext ctx) {

		List<String> nodeList = new ArrayList<>();
		for (ParseTree p : ctx.children) {
			if (p instanceof FormulaContext) {
				nodeList.add(this.visit(p));
			} else {
				nodeList.add(p.getText());
			}

		}

		return nodeList.stream().collect(Collectors.joining(" ")).replace("[ ", "[").replace(" ]", "]");
	}

	private ParseTree[] toArray(List<? extends ParseTree> nodeList) {
		return nodeList.toArray(new ParseTree[nodeList.size()]);
	}

	private String toString(ParseTree... nodes) {
		return Arrays.stream(nodes).map(ParseTree::getText).collect(Collectors.joining(" ")).toString();
	}

}
