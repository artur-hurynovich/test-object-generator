package com.hurynovich.service.container_builder_and_filler.impl;

import com.hurynovich.model.object_container_descriptor.ContainerDescriptor;
import com.hurynovich.service.container_builder_and_filler.ContainerBuilderAndFiller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

public class MapContainerBuilderAndFiller implements ContainerBuilderAndFiller {

	private final Map<Class<?>, Supplier<Map<Object, Object>>> containerBuilders = new HashMap<>();

	public MapContainerBuilderAndFiller() {
		containerBuilders.put(HashMap.class, HashMap::new);
		containerBuilders.put(LinkedHashMap.class, LinkedHashMap::new);
	}

	@Override
	public boolean hasContainerBuilder(final ContainerDescriptor containerDescriptor) {
		return containerBuilders.containsKey(containerDescriptor.getContainerImplementationClass());
	}

	@Override
	public Object buildAndFill(final ContainerDescriptor containerDescriptor, final Function<Class<?>, ?> elementsGenerator) {
		final Class<?> containerImplementationClass = containerDescriptor.getContainerImplementationClass();

		final Map<Object, Object> objects = containerBuilders.get(containerImplementationClass).get();

		for (int i = 0; i < containerDescriptor.getElementsQuantity(); i++) {
			final Class<?> keyClass = containerDescriptor.getKeyClass();
			final Object key = elementsGenerator.apply(keyClass);

			final Class<?> valueClass = containerDescriptor.getValueClass();
			final Object value = elementsGenerator.apply(valueClass);

			objects.put(key, value);
		}

		return objects;
	}

}
