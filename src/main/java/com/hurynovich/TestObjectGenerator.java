package com.hurynovich;

public interface TestObjectGenerator {

	<T> T generate(Class<T> objectClass);

}
