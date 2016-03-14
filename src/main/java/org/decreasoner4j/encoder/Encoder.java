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

public abstract class Encoder {

	public static Encoder parseEncodingInput(String fileName) throws IOException {
		return parseEncodingInput(new FileInputStream(new File(fileName)));
	}

	public static Encoder parseEncodingInput(String fileName, EncoderVisitor<?> visitor) throws IOException {
		return parseEncodingInput(new FileInputStream(new File(fileName)), visitor);
	}

	public static Encoder parseEncodingInput(InputStream input) throws IOException {
		return parseEncodingInput(input, new DefaultEncoderVisitor());
	}

	public static Encoder parseEncodingInput(InputStream input, EncoderVisitor<?> visitor) throws IOException {
		EncoderLexer lexer = new EncoderLexer(new ANTLRInputStream(input));
		EncoderParser parser = new EncoderParser(new CommonTokenStream(lexer));
		ParseTree tree = parser.encoderInput();
		visitor.visit(tree);
		return visitor.encoder();
	}

}
