package org.decreasoner4j.encoder;

import java.util.HashSet;
import java.util.Set;

public class EchoEncoder extends Encoder {
	
	private Set<String> lines = new HashSet<>();
	
	public Set<String> getLines() {
		return lines;
	}

}
