package com.hurynovich.generator.impl;

import com.hurynovich.factory.PrimitiveTypeGeneratorFactory;
import com.hurynovich.generator.PrimitiveTypeGenerator;
import com.hurynovich.generator.TestObjectGenerator;
import com.hurynovich.validator.TestObjectClassValidator;
import com.hurynovich.factory.TestObjectClassValidatorFactory;
import com.hurynovich.exception.TestObjectGeneratorException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

public class GeneralTestObjectGenerator implements TestObjectGenerator {

	private final TestObjectClassValidator validator = TestObjectClassValidatorFactory.build();

	public <T> T generate(final Class<T> testObjectClass) {
		validator.validate(testObjectClass);

		final T testObject = instantiate(testObjectClass);

		final List<Field> fields = Arrays.asList(testObjectClass.getDeclaredFields());
		fields.forEach(field -> {
			field.setAccessible(true);

			final Class<?> fieldType = field.getType();

			try {
				if (fieldType.isPrimitive()) {
					final PrimitiveTypeGenerator<? extends Serializable> primitiveTypeGenerator =
							PrimitiveTypeGeneratorFactory.build(fieldType);

					if (primitiveTypeGenerator != null) {
						field.set(testObject, primitiveTypeGenerator.generate());
					}
				} else {
					field.set(testObject, generate(fieldType));
				}
			} catch (final IllegalAccessException e) {
				throw new TestObjectGeneratorException("Failed to set value for field of class '" + fieldType +
						"' with name '" + field.getName() + "'", e);
			}
		});

		return testObject;
	}

	private <T> T instantiate(final Class<T> testObjectClass) {
		try {
			return testObjectClass.getDeclaredConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new TestObjectGeneratorException("Failed to instantiate test object of class '" +
					testObjectClass + "'", e);
		}
	}

}
