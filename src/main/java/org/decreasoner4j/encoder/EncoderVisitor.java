package org.decreasoner4j.encoder;

import org.decreasoner4j.antlr.encoder.EncoderBaseVisitor;

public abstract class EncoderVisitor<T> extends EncoderBaseVisitor<T> {
	public abstract Encoder encoder();
}
