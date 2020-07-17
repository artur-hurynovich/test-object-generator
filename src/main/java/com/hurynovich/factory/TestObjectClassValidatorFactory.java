package com.hurynovich.factory;

import com.hurynovich.validator.TestObjectClassValidator;
import com.hurynovich.validator.impl.TestObjectClassValidatorImpl;

public class TestObjectClassValidatorFactory {

	private static final TestObjectClassValidator VALIDATOR = new TestObjectClassValidatorImpl();

	public static TestObjectClassValidator build() {
		return VALIDATOR;
	}

}
