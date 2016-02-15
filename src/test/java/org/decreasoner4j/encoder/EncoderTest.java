package org.decreasoner4j.encoder;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;

import org.junit.Test;

public class EncoderTest {
	private String[][] problems = { 
			{ ".", "" }, 
			{ "decreasoner/examples/Manual/", "Example1" }, 
			};

	@Test
	public void testEncoder() throws Exception {
		for (String[] problem : problems) {
			String path = problem[0];
			String name = problem[1];

			System.out.println("testEncoder: " + path + name);

			String encoderInput = "/" + path + name + "/encoder.input";
			String encoderOutput = "/" + path + name + "/encoder.output";

			Encoder encoder;
			try (InputStream input = this.getClass().getResourceAsStream(encoderInput)) {
				encoder = Encoder.parseEncodingInput(input);
			}

			//assertEquals("", encoder);
		}
	}
}
