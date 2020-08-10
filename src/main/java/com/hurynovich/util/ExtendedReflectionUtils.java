package com.hurynovich.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ExtendedReflectionUtils extends org.springframework.util.ReflectionUtils {

	private static final String SETTER_PREFIX = "set";

	public static <T> Method findStandardSetterForField(final Field field, final Class<T> clazz) {
		final String setterName = resolveSetterName(field);

		final Method setter = org.springframework.util.ReflectionUtils.findMethod(clazz, setterName, field.getType());

		if (setter != null && isPublic(setter)) {
			return setter;
		} else {
			return null;
		}
	}

	private static String resolveSetterName(final Field field) {
		final String fieldName = field.getName();

		return SETTER_PREFIX + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	private static boolean isPublic(final Method method) {
		final int modifiers = method.getModifiers();

		return Modifier.isPublic(modifiers);
	}

}
