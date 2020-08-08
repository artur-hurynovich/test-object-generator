package com.hurynovich.generator.impl;

import com.hurynovich.exception.TestObjectGeneratorException;
import com.hurynovich.factory.DefaultTypeGeneratorFactory;
import com.hurynovich.factory.TestObjectClassValidatorFactory;
import com.hurynovich.generator.DefaultTypeGenerator;
import com.hurynovich.generator.TestObjectGenerator;
import com.hurynovich.validator.TestObjectClassValidator;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Collections;

public class GeneralTestObjectGenerator implements TestObjectGenerator {

	private static final Log LOGGER = LogFactory.getLog(GeneralTestObjectGenerator.class);

	private static final String SETTER_PREFIX = "set";

	private final TestObjectClassValidator validator = TestObjectClassValidatorFactory.build();

	@Override
	public <T> T generate(final Class<T> objectClass) {
		return generate(objectClass, Collections.emptyList());
	}

	@Override
	public <T> T generate(final Class<T> objectClass, final Collection<String> ignoreFields) {
		validator.validate(objectClass);

		// recursive
		// if objectClass is String or boolean or so on?
		final T testObject = instantiate(objectClass);

		ReflectionUtils.doWithFields(objectClass, field -> {
			if (!ignoreFields.contains(field.getName())) {
				processSetField(field, testObject);
			}
		});

		return testObject;
	}

	private <T> T instantiate(final Class<T> objectClass) {
		try {
			return ReflectionUtils.accessibleConstructor(objectClass).newInstance();
		} catch (final InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			throw new TestObjectGeneratorException("Failed to instantiate test object of class '" +
					objectClass + "'", e);
		}
	}

	private <T> void processSetField(final Field field, final T object) {
		final Class<?> fieldType = field.getType();

		if (fieldType.isPrimitive() || fieldType.equals(String.class)) {
			final DefaultTypeGenerator<Serializable> defaultTypeGenerator =
					DefaultTypeGeneratorFactory.build(fieldType);

			final Class<?> objectClass = object.getClass();

			if (defaultTypeGenerator != null) {
				final Method setter = findStandardSetterForField(field, objectClass);

				if (setter != null) {
					ReflectionUtils.invokeMethod(setter, object, defaultTypeGenerator.generate());
				} else {
					LOGGER.warn("No standard setter found for field of type '" + fieldType +
							"' and name '" + field.getName() + "' in class '" + objectClass.getName() + "'");
				}
			}
		}

		// array, collection (including map); Generics???
	}

	private <T> Method findStandardSetterForField(final Field field, final Class<T> clazz) {
		final String setterName = resolveSetterName(field);

		final Method setter = ReflectionUtils.findMethod(clazz, setterName, field.getType());

		if (setter != null && isPublic(setter)) {
			return setter;
		} else {
			return null;
		}
	}

	private String resolveSetterName(final Field field) {
		final String fieldName = field.getName();

		return SETTER_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	private boolean isPublic(final Method method) {
		final int modifiers = method.getModifiers();

		return Modifier.isPublic(modifiers);
	}

}
