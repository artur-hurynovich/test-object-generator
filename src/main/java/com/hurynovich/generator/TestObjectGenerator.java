package com.hurynovich.generator;

public interface TestObjectGenerator {

	<T> T generate(Class<T> objectClass);

}
