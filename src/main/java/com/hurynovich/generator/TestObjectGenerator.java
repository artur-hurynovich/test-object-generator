package com.hurynovich.generator;

import java.util.Collection;

public interface TestObjectGenerator {

	<T> T generate(Class<T> objectClass);

	<T> T generate(Class<T> objectClass, Collection<String> ignoreFields);

}
