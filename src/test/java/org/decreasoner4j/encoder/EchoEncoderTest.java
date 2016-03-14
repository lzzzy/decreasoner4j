package org.decreasoner4j.encoder;

import static org.junit.Assert.assertEquals;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.decreasoner4j.Problem;
import org.junit.Test;

public class EchoEncoderTest {

	@Test
	public void testEncoder() throws Exception {
		for (Problem problem : Problem.getProblems()) {

			System.out.println("testEncoder: " + problem.path() + problem.name());

			String encoderInput = "/" + problem.path() + problem.name() + "/encoder.input";

			Set<String> inLines;
			try (Stream<String> s = Files.lines(Paths.get(this.getClass().getResource(encoderInput).toURI()))) {
				inLines = s.map(String::trim).collect(Collectors.toSet());
			}

			EchoEncoder encoder;
			try (InputStream input = this.getClass().getResourceAsStream(encoderInput)) {
				encoder = (EchoEncoder) EchoEncoder.parseEncodingInput(input, new EchoEncoderVisitor());
			}

			inLines.removeAll(encoder.getLines());
			
			if (inLines.size() != 0) {
				System.out.println(encoder.getLines());
				System.out.println(inLines);
			}
			assertEquals(0, inLines.size());
			
		}
	}
}
