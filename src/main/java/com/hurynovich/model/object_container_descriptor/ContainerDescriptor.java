package com.hurynovich.model.object_container_descriptor;

public interface ContainerDescriptor {

	ContainerDescriptorType getType();

	Class<?> getContainerImplementationClass();

	Class<?> getElementClass();

	Class<?> getKeyClass();

	Class<?> getValueClass();

	Integer getElementsQuantity();

	boolean matches(Class<?> containerClass);

}
