package com.hurynovich;

public class TestObjectClassValidatorFactory {

	private static final TestObjectClassValidator VALIDATOR = new TestObjectClassValidatorImpl();

	public static TestObjectClassValidator build() {
		return VALIDATOR;
	}

}
