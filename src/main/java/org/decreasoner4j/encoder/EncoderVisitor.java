package org.decreasoner4j.encoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.ParseTree;
import org.decreasoner4j.antlr.encoder.EncoderBaseVisitor;
import org.decreasoner4j.antlr.encoder.EncoderParser.ConstantNonreifiedLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.ConstantReifiedLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.RangeLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.SortNonreifiedLineContext;
import org.decreasoner4j.antlr.encoder.EncoderParser.SortReifiedLineContext;

public class EncoderVisitor extends EncoderBaseVisitor<String> {
	
	private Encoder encoder;
	
	public EncoderVisitor() {
		encoder = new Encoder();
	}

	@Override
	public String visitRangeLine(RangeLineContext ctx) {
		sysout(ctx.Range(), ctx.sortId(), ctx.maxInt(), ctx.maxInt());
		return super.visitRangeLine(ctx);
	}
	
	@Override
	public String visitSortNonreifiedLine(SortNonreifiedLineContext ctx) {
		sysout(ctx.Sort(), ctx.sortId(), ctx.parentSortId());
		return super.visitSortNonreifiedLine(ctx);
	}
	
	@Override
	public String visitSortReifiedLine(SortReifiedLineContext ctx) {
		sysout(ctx.Reified(),ctx.Sort(), ctx.sortId(), ctx.parentSortId());
		return super.visitSortReifiedLine(ctx);
	}
	
	@Override
	public String visitConstantNonreifiedLine(ConstantNonreifiedLineContext ctx) {
		List<ParseTree> nodeList = new ArrayList<>();
		nodeList.add(ctx.Constant());
		nodeList.add(ctx.sortId());
		nodeList.addAll(ctx.constantId());
		sysout(toArray(nodeList));
		return super.visitConstantNonreifiedLine(ctx);
	}
	
	@Override
	public String visitConstantReifiedLine(ConstantReifiedLineContext ctx) {
		List<ParseTree> nodeList = new ArrayList<>();
		nodeList.add(ctx.Reified());
		nodeList.add(ctx.Constant());
		nodeList.add(ctx.sortId());
		nodeList.addAll(ctx.constantId());
		sysout(toArray(nodeList));
		return super.visitConstantReifiedLine(ctx);
	}

	public Encoder encoder() {
		return encoder;
	}

	private ParseTree[] toArray(List<? extends ParseTree> nodeList) {
		return nodeList.toArray(new ParseTree[nodeList.size()]);
	}

	private void sysout(ParseTree... nodes) {
		System.out.println(Arrays.stream(nodes)
				.map(ParseTree::getText)
				.collect(Collectors.joining(" "))
				.toString());
	}

}
