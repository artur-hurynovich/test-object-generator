package com.hurynovich.model.object_container_descriptor.impl;

import com.hurynovich.model.object_container_descriptor.ContainerDescriptor;
import com.hurynovich.model.object_container_descriptor.ContainerDescriptorType;

import java.util.Collection;
import java.util.Map;

public class GeneralContainerDescriptor implements ContainerDescriptor {

	private static final int DEFAULT_ELEMENTS_QUANTITY = 3;

	private final ContainerDescriptorType type;

	private Class<?> containerImplementationClass;

	private Class<?> elementClass;

	private Class<?> keyClass;

	private Class<?> valueClass;

	private Integer elementsQuantity;

	private GeneralContainerDescriptor(final ContainerDescriptorType type) {
		this.type = type;
	}

	public static class GeneralContainerDescriptorBuilder {

		private final GeneralContainerDescriptor containerDescriptor;

		public GeneralContainerDescriptorBuilder(final ContainerDescriptorType type) {
			containerDescriptor = new GeneralContainerDescriptor(type);
		}

		public GeneralContainerDescriptorBuilder withContainerImplementationClass(final Class<?> containerImplementationClass) {
			containerDescriptor.containerImplementationClass = containerImplementationClass;

			return this;
		}

		public GeneralContainerDescriptorBuilder withElementClass(final Class<?> elementClass) {
			containerDescriptor.elementClass = elementClass;

			return this;
		}

		public GeneralContainerDescriptorBuilder withKeyClass(final Class<?> keyClass) {
			containerDescriptor.keyClass = keyClass;

			return this;
		}

		public GeneralContainerDescriptorBuilder withValueClass(final Class<?> valueClass) {
			containerDescriptor.valueClass = valueClass;

			return this;
		}

		public GeneralContainerDescriptorBuilder withElementsQuantity(final int elementsQuantity) {
			containerDescriptor.elementsQuantity = elementsQuantity;

			return this;
		}

		public GeneralContainerDescriptor build() {
			if (containerDescriptor.elementsQuantity == null) {
				containerDescriptor.elementsQuantity = DEFAULT_ELEMENTS_QUANTITY;
			}

			return containerDescriptor;
		}

	}

	public static GeneralContainerDescriptorBuilder builder(final ContainerDescriptorType type) {
		return new GeneralContainerDescriptorBuilder(type);
	}

	@Override
	public ContainerDescriptorType getType() {
		return type;
	}

	@Override
	public Class<?> getContainerImplementationClass() {
		return containerImplementationClass;
	}

	@Override
	public Class<?> getElementClass() {
		return elementClass;
	}

	@Override
	public Class<?> getKeyClass() {
		return keyClass;
	}

	@Override
	public Class<?> getValueClass() {
		return valueClass;
	}

	@Override
	public Integer getElementsQuantity() {
		return elementsQuantity;
	}

	@Override
	public boolean matches(final Class<?> containerClass) {
		return (containerClass.isArray() && type == ContainerDescriptorType.ARRAY) ||
				(containerClass.isAssignableFrom(Collection.class) && type == ContainerDescriptorType.COLLECTION) ||
				(containerClass.isAssignableFrom(Map.class) && type == ContainerDescriptorType.MAP);
	}

}
