package com.hurynovich.generator.impl;

import com.hurynovich.exception.TestObjectGeneratorException;
import com.hurynovich.factory.DefaultTypeGeneratorFactory;
import com.hurynovich.factory.TestObjectClassValidatorFactory;
import com.hurynovich.generator.TestObjectGenerator;
import com.hurynovich.model.field_descriptor.FieldDescriptor;
import com.hurynovich.util.ExtendedReflectionUtils;
import com.hurynovich.validator.TestObjectClassValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;

public class GeneralTestObjectGenerator implements TestObjectGenerator {

	private static final Log LOGGER = LogFactory.getLog(GeneralTestObjectGenerator.class);

	private final TestObjectClassValidator validator = TestObjectClassValidatorFactory.build();

	@Override
	public <T> T generate(final Class<T> objectClass) {
		return generate(objectClass, Collections.emptyList());
	}

	@Override
	public <T> T generate(final Class<T> objectClass, final Collection<FieldDescriptor> ignoreFields) {
		validator.validate(objectClass);

		if (DefaultTypeGeneratorFactory.defaultTypeGeneratorExists(objectClass)) {
			return DefaultTypeGeneratorFactory.build(objectClass).generate();
		} else {
			final T testObject = instantiate(objectClass);

			ExtendedReflectionUtils.doWithFields(objectClass, field -> {
				if (notIgnoredField(objectClass, field, ignoreFields)) {
					processSetField(field, testObject, ignoreFields);
				}
			});

			return testObject;
		}
	}

	private <T> T instantiate(final Class<T> objectClass) {
		try {
			return ExtendedReflectionUtils.accessibleConstructor(objectClass).newInstance();
		} catch (final NoSuchMethodException e) {
			throw new TestObjectGeneratorException("No default constructor found for class '" +
					objectClass + "'", e);
		} catch (final InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new TestObjectGeneratorException("Failed to instantiate object of class '" +
					objectClass + "'", e);
		}
	}

	private boolean notIgnoredField(final Class<?> containerObjectClass, final Field field,
									final Collection<FieldDescriptor> ignoreFields) {
		boolean notIgnoredField = true;

		for (final FieldDescriptor fieldDescriptor : ignoreFields) {
			if (matchesField(containerObjectClass, field, fieldDescriptor)) {
				notIgnoredField = false;

				break;
			}
		}

		return notIgnoredField;
	}

	private boolean matchesField(final Class<?> containerObjectClass, final Field field,
								 final FieldDescriptor fieldDescriptor) {
		boolean matchesField = true;

		final Class<?> containerObjectClass1 = fieldDescriptor.getContainerObjectClass();
		if (containerObjectClass1 != null) {
			matchesField = containerObjectClass.equals(containerObjectClass1);
		}

		final Class<?> fieldClass = fieldDescriptor.getFieldClass();
		if (fieldClass != null) {
			matchesField &= field.getType().equals(fieldClass);
		}

		final String fieldName = fieldDescriptor.getFieldName();
		if (fieldName != null) {
			matchesField &= field.getName().equals(fieldName);
		}

		return matchesField;
	}

	private <T> void processSetField(final Field field, final T object, final Collection<FieldDescriptor> ignoreFields) {
		final Class<?> objectClass = object.getClass();

		final Class<?> fieldType = field.getType();

		final Method setter = ExtendedReflectionUtils.findStandardSetterForField(field, objectClass);

		if (setter != null) {
			final Object fieldValue;

			if (DefaultTypeGeneratorFactory.defaultTypeGeneratorExists(objectClass)) {
				fieldValue = DefaultTypeGeneratorFactory.build(fieldType).generate();
			} else {
				fieldValue = generate(fieldType, ignoreFields);
			}

			ExtendedReflectionUtils.invokeMethod(setter, object, fieldValue);
		} else {
			LOGGER.warn("No standard setter found for field of type '" + fieldType +
					"' and name '" + field.getName() + "' in class '" + objectClass.getName() + "'");
		}
	}


}
