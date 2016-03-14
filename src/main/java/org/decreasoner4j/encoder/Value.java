package org.decreasoner4j.encoder;

public class Value {
	
	String string;
	int integer;
	
	public static Value asString(String string) {
		return new Value(string);
	}

	public static Value asInteger(String string) {
		return new Value(Integer.parseInt(string));
	}

	private Value(String string) {
		this.string = string;
	}

	private Value(int integer) {
		this.integer = integer;
	}

	
}
