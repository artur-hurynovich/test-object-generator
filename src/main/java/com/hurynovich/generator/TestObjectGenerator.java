package com.hurynovich.generator;

import com.hurynovich.model.field_descriptor.FieldDescriptor;

import java.util.Collection;

public interface TestObjectGenerator {

	<T> T generate(Class<T> objectClass);

	<T> T generate(Class<T> objectClass, Collection<FieldDescriptor> ignoreFields);

}
