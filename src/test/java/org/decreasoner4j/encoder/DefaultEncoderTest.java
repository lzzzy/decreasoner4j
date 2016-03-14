package org.decreasoner4j.encoder;

import java.io.InputStream;

import org.decreasoner4j.Problem;
import org.junit.Test;

public class DefaultEncoderTest {

	@Test
	public void testEncoder() throws Exception {
		for (Problem problem : Problem.getProblems()) {

			System.out.println("testEncoder: " + problem.path() + problem.name());

			String encoderInput = "/" + problem.path() + problem.name() + "/encoder.input";
			String encoderOutput = "/" + problem.path() + problem.name() + "/encoder.output";

			DefaultEncoder encoder;
			try (InputStream input = this.getClass().getResourceAsStream(encoderInput)) {
				encoder = (DefaultEncoder) DefaultEncoder.parseEncodingInput(input, new DefaultEncoderVisitor());
			}

		}
	}
}
