package com.hurynovich.generator.impl;

import com.hurynovich.generator.DefaultTypeGenerator;
import com.hurynovich.generator.TestObjectGenerator;
import com.hurynovich.mock_object.MockInnerObject;
import com.hurynovich.mock_object.MockUserObject;
import com.hurynovich.model.field_descriptor.FieldDescriptor;
import com.hurynovich.model.field_descriptor.impl.GeneralFieldDescriptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class GeneralTestObjectGeneratorTest {

	private final Map<FieldDescriptor, DefaultTypeGenerator<?>> customTypeGenerators = new HashMap<>();

	private GeneralFieldDescriptor ignoredFieldDescriptor;

	private GeneralFieldDescriptor customFieldDescriptor;

	private TestObjectGenerator generator;

	private enum MockEnum {
		ONE, TWO, THREE
	}

	@BeforeEach
	public void init() {
		ignoredFieldDescriptor = GeneralFieldDescriptor.builder().
				withContainerObjectClass(MockInnerObject.class).
				withFieldClass(String.class).
				withFieldName("ignoredText").
				build();

		customFieldDescriptor = GeneralFieldDescriptor.builder().
				withContainerObjectClass(MockInnerObject.class).
				withFieldClass(String.class).
				withFieldName("customText").
				build();

		customTypeGenerators.put(customFieldDescriptor, () -> "CUSTOM");

		generator = GeneralTestObjectGenerator.
				builder().
				withIgnoreFields(Collections.singletonList(ignoredFieldDescriptor)).
				withCustomTypeGenerators(customTypeGenerators).
				build();
	}

	@Test
	public void test() {
		final MockUserObject mockUserObject = generator.generate(MockUserObject.class);

		System.out.println(mockUserObject.isActive());
		System.out.println(mockUserObject.getInner().getText());
		System.out.println(mockUserObject.getInner().getIgnoredText());
		System.out.println(mockUserObject.getInner().getCustomText());

		System.out.println(generator.generate(MockEnum.class));
	}

}
