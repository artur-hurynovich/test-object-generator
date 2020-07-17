package com.hurynovich.validator;

public interface TestObjectClassValidator {

	<T> void validate(Class<T> testObjectClass);

}
