package com.hurynovich.factory;

import com.hurynovich.model.object_container_descriptor.ContainerDescriptorType;
import com.hurynovich.service.container_builder_and_filler.ContainerBuilderAndFiller;
import com.hurynovich.service.container_builder_and_filler.impl.ArrayContainerBuilderAndFiller;
import com.hurynovich.service.container_builder_and_filler.impl.CollectionContainerBuilderAndFiller;
import com.hurynovich.service.container_builder_and_filler.impl.MapContainerBuilderAndFiller;

import java.util.EnumMap;
import java.util.Map;

public class ContainerBuilderAndFillerFactory {

	private static final Map<ContainerDescriptorType, ContainerBuilderAndFiller> CONTAINER_BUILDERS =
			new EnumMap<>(ContainerDescriptorType.class);

	static {
		CONTAINER_BUILDERS.put(ContainerDescriptorType.ARRAY, new ArrayContainerBuilderAndFiller());
		CONTAINER_BUILDERS.put(ContainerDescriptorType.COLLECTION, new CollectionContainerBuilderAndFiller());
		CONTAINER_BUILDERS.put(ContainerDescriptorType.MAP, new MapContainerBuilderAndFiller());
	}

	private ContainerBuilderAndFillerFactory() {

	}

	public static ContainerBuilderAndFiller build(final ContainerDescriptorType type) {
		return CONTAINER_BUILDERS.get(type);
	}

}
