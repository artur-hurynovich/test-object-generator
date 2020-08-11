package com.hurynovich.service.container_builder_and_filler.impl;

import com.hurynovich.model.object_container_descriptor.ContainerDescriptor;
import com.hurynovich.service.container_builder_and_filler.ContainerBuilderAndFiller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class ArrayContainerBuilderAndFiller implements ContainerBuilderAndFiller {

	private final Map<Class<?>, Supplier<Collection<Object>>> containerBuilders = new HashMap<>();

	public ArrayContainerBuilderAndFiller() {
		containerBuilders.put(ArrayList.class, ArrayList::new);
	}

	@Override
	public boolean hasContainerBuilder(final ContainerDescriptor containerDescriptor) {
		return containerBuilders.containsKey(containerDescriptor.getContainerImplementationClass());
	}

	@Override
	public Object buildAndFill(final ContainerDescriptor containerDescriptor, final Function<Class<?>, ?> elementsGenerator) {
		final Collection<Object> elements = containerBuilders.get(ArrayList.class).get();

		for (int i = 0; i < containerDescriptor.getElementsQuantity(); i++) {
			final Class<?> elementClass = containerDescriptor.getElementClass();

			final Object element = elementsGenerator.apply(elementClass);

			elements.add(element);
		}

		return elements.toArray();
	}

}
