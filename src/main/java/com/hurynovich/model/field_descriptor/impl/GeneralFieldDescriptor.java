package com.hurynovich.model.field_descriptor.impl;

import com.hurynovich.model.field_descriptor.FieldDescriptor;

import java.lang.reflect.Field;

public class GeneralFieldDescriptor implements FieldDescriptor {

	private Class<?> containerObjectClass;

	private Class<?> fieldClass;

	private String fieldName;

	private GeneralFieldDescriptor() {

	}

	public static class GeneralFieldDescriptorBuilder {

		private final GeneralFieldDescriptor fieldDescriptor;

		public GeneralFieldDescriptorBuilder() {
			fieldDescriptor = new GeneralFieldDescriptor();
		}

		public GeneralFieldDescriptorBuilder withContainerObjectClass(final Class<?> containerObjectClass) {
			fieldDescriptor.containerObjectClass = containerObjectClass;

			return this;
		}

		public GeneralFieldDescriptorBuilder withFieldClass(final Class<?> fieldClass) {
			fieldDescriptor.fieldClass = fieldClass;

			return this;
		}

		public GeneralFieldDescriptorBuilder withFieldName(final String fieldName) {
			fieldDescriptor.fieldName = fieldName;

			return this;
		}

		public GeneralFieldDescriptor build() {
			return fieldDescriptor;
		}

	}

	public static GeneralFieldDescriptorBuilder builder() {
		return new GeneralFieldDescriptorBuilder();
	}

	@Override
	public boolean matches(final Field field, final Class<?> containerObjectClass1) {
		boolean matches = true;

		if (containerObjectClass != null) {
			matches = containerObjectClass.equals(containerObjectClass1);
		}

		if (fieldClass != null) {
			matches &= fieldClass.equals(field.getType());
		}

		if (fieldName != null) {
			matches &= fieldName.equals(field.getName());
		}

		return matches;
	}

}
