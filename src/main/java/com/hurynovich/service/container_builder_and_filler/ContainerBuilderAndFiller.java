package com.hurynovich.service.container_builder_and_filler;

import com.hurynovich.model.object_container_descriptor.ContainerDescriptor;

import java.util.function.Function;

public interface ContainerBuilderAndFiller {

	boolean hasContainerBuilder(ContainerDescriptor containerDescriptor);

	Object buildAndFill(ContainerDescriptor containerDescriptor, Function<Class<?>, ?> elementsGenerator);

}
