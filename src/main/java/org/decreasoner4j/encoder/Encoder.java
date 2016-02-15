package org.decreasoner4j.encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.decreasoner4j.antlr.encoder.EncoderLexer;
import org.decreasoner4j.antlr.encoder.EncoderParser;

public class Encoder {

	public static Encoder parseEncodingInput(String fileName) throws IOException {
		return parseEncodingInput(new FileInputStream(new File(fileName)));
	}

	public static Encoder parseEncodingInput(InputStream input) throws IOException {
		EncoderLexer lexer = new EncoderLexer(new ANTLRInputStream(input));
		EncoderParser parser = new EncoderParser(new CommonTokenStream(lexer));
		ParseTree tree = parser.encoderInput();
		EncoderVisitor visitor = new EncoderVisitor();
		visitor.visit(tree);
		return visitor.encoder();
	}

}
