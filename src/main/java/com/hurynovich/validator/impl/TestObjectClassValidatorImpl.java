package com.hurynovich.validator.impl;

import com.hurynovich.exception.TestObjectClassValidationException;
import com.hurynovich.factory.DefaultTypeGeneratorFactory;
import com.hurynovich.validator.TestObjectClassValidator;

public class TestObjectClassValidatorImpl implements TestObjectClassValidator {

	public <T> void validate(final Class<T> testObjectClass) {
		try {
			if (!DefaultTypeGeneratorFactory.defaultTypeGeneratorExists(testObjectClass)) {
				testObjectClass.getDeclaredConstructor();
			}
		} catch (final NoSuchMethodException e) {
			throw new TestObjectClassValidationException("Class '" + testObjectClass +
					"' should have default constructor");
		}
	}

}
