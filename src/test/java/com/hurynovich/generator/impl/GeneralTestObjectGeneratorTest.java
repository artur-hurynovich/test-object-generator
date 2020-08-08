package com.hurynovich.generator.impl;

import com.hurynovich.mock_object.MockUserObject;
import com.hurynovich.generator.TestObjectGenerator;
import org.junit.jupiter.api.Test;

public class GeneralTestObjectGeneratorTest {

	private final TestObjectGenerator generator = new GeneralTestObjectGenerator();

	@Test
	public void test() {
		final MockUserObject mockUserObject = generator.generate(MockUserObject.class);

		System.out.println(mockUserObject.isActive());
		System.out.println(mockUserObject.getInner().getText());

		final String generate = generator.generate(String.class);
		System.out.println(generate);
	}

}
