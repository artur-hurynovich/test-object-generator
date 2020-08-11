package com.hurynovich.service.test_object_generator_helper.impl;

import com.hurynovich.exception.TestObjectGeneratorException;
import com.hurynovich.factory.ContainerBuilderAndFillerFactory;
import com.hurynovich.model.object_container_descriptor.ContainerDescriptor;
import com.hurynovich.model.object_container_descriptor.ContainerDescriptorType;
import com.hurynovich.service.container_builder_and_filler.ContainerBuilderAndFiller;
import com.hurynovich.service.test_object_generator_helper.TestObjectGeneratorHelper;

import java.util.Random;
import java.util.function.Function;

public class GeneralTestObjectGeneratorHelper implements TestObjectGeneratorHelper {

	private static final Random RANDOM = new Random();

	@Override
	public <T> T generateRandomEnumValue(final Class<T> enumClass) {
		final T[] enumConstants = enumClass.getEnumConstants();

		final int randomEnumConstantIndex = RANDOM.nextInt(enumConstants.length);

		return enumConstants[randomEnumConstantIndex];
	}

	@Override
	public Object buildAndFillContainer(final ContainerDescriptor containerDescriptor,
										final Function<Class<?>, Object> containerElementsSupplier) {
		final ContainerDescriptorType type = containerDescriptor.getType();

		final ContainerBuilderAndFiller containerBuilderAndFiller = ContainerBuilderAndFillerFactory.build(type);

		if (containerBuilderAndFiller != null) {
			if (containerBuilderAndFiller.hasContainerBuilder(containerDescriptor)) {
				return containerBuilderAndFiller.buildAndFill(containerDescriptor, containerElementsSupplier);
			} else {
				throw new TestObjectGeneratorException("No container builder found for container implementation class: '" +
						containerDescriptor.getContainerImplementationClass().getName() + "'");
			}
		} else {
			throw new TestObjectGeneratorException("No ContainerBuilder implementation found for ContainerDescriptorType: '" +
					type + "'");
		}
	}

}
