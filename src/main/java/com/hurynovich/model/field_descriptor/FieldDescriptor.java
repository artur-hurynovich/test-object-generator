package com.hurynovich.model.field_descriptor;

public interface FieldDescriptor {

	Class<?> getContainerObjectClass();

	Class<?> getFieldClass();

	String getFieldName();

}
