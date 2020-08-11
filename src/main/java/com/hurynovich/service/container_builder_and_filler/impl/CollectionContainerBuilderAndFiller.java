package com.hurynovich.service.container_builder_and_filler.impl;

import com.hurynovich.model.object_container_descriptor.ContainerDescriptor;
import com.hurynovich.service.container_builder_and_filler.ContainerBuilderAndFiller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.function.Supplier;

public class CollectionContainerBuilderAndFiller implements ContainerBuilderAndFiller {

	private final Map<Class<?>, Supplier<Collection<Object>>> containerBuilders = new HashMap<>();

	public CollectionContainerBuilderAndFiller() {
		containerBuilders.put(ArrayList.class, ArrayList::new);
		containerBuilders.put(LinkedList.class, LinkedList::new);
		containerBuilders.put(HashSet.class, HashSet::new);
		containerBuilders.put(LinkedHashSet.class, LinkedHashSet::new);
		containerBuilders.put(TreeSet.class, TreeSet::new);
	}

	@Override
	public boolean hasContainerBuilder(final ContainerDescriptor containerDescriptor) {
		return containerBuilders.containsKey(containerDescriptor.getContainerImplementationClass());
	}

	@Override
	public Object buildAndFill(final ContainerDescriptor containerDescriptor, final Function<Class<?>, ?> elementsGenerator) {
		final Class<?> containerImplementationClass = containerDescriptor.getContainerImplementationClass();

		final Collection<Object> elements = containerBuilders.get(containerImplementationClass).get();

		for (int i = 0; i < containerDescriptor.getElementsQuantity(); i++) {
			final Class<?> elementClass = containerDescriptor.getElementClass();

			final Object element = elementsGenerator.apply(elementClass);

			elements.add(element);
		}

		return elements;
	}

}
