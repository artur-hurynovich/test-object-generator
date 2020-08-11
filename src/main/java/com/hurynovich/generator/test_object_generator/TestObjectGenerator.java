package com.hurynovich.generator.test_object_generator;

public interface TestObjectGenerator {

	<T> T generate(Class<T> objectClass);

}
