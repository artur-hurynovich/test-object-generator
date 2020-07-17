package com.hurynovich;

import org.junit.jupiter.api.Test;

public class GeneralTestObjectGeneratorTest {

	private final TestObjectGenerator generator = new GeneralTestObjectGenerator();

	@Test
	public void test() {
		final MockUserObject mockUserObject = generator.generate(MockUserObject.class);

		System.out.println(mockUserObject.isActive());
	}

}
