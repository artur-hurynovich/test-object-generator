package com.hurynovich.generator.impl;

import com.hurynovich.mock_object.MockInnerObject;
import com.hurynovich.mock_object.MockUserObject;
import com.hurynovich.generator.TestObjectGenerator;
import com.hurynovich.model.field_descriptor.impl.GeneralFieldDescriptor;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class GeneralTestObjectGeneratorTest {

	private final TestObjectGenerator generator = new GeneralTestObjectGenerator();

	@Test
	public void test() {
		final GeneralFieldDescriptor fieldDescriptor = GeneralFieldDescriptor.builder().
				withContainerObjectClass(MockInnerObject.class).
				withFieldClass(String.class).
				withFieldName("ignoredText").
				build();

		final MockUserObject mockUserObject = generator.
				generate(MockUserObject.class, Collections.singletonList(fieldDescriptor));

		System.out.println(mockUserObject.isActive());
		System.out.println(mockUserObject.getInner().getText());
		System.out.println(mockUserObject.getInner().getIgnoredText());

		final String generate = generator.generate(String.class);
		System.out.println(generate);
	}

}
