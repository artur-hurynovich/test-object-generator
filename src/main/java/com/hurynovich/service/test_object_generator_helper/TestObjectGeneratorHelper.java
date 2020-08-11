package com.hurynovich.service.test_object_generator_helper;

import com.hurynovich.model.object_container_descriptor.ContainerDescriptor;

import java.util.function.Function;

public interface TestObjectGeneratorHelper {

	<T> T generateRandomEnumValue(Class<T> enumClass);

	Object buildAndFillContainer(ContainerDescriptor containerDescriptor, Function<Class<?>, Object> containerElementsSupplier);

}
