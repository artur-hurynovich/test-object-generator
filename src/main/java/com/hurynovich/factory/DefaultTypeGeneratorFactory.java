package com.hurynovich.factory;

import com.hurynovich.generator.DefaultTypeGenerator;
import com.hurynovich.generator.impl.DefaultBooleanTypeGenerator;
import com.hurynovich.generator.impl.DefaultStringTypeGenerator;

import java.util.HashMap;
import java.util.Map;

public class DefaultTypeGeneratorFactory {

	private static final Map<Class<?>, DefaultTypeGenerator<?>> DEFAULT_TYPE_GENERATORS;

	static {
		DEFAULT_TYPE_GENERATORS = new HashMap<>();

		final DefaultBooleanTypeGenerator defaultBooleanTypeGenerator = new DefaultBooleanTypeGenerator();
		DEFAULT_TYPE_GENERATORS.put(boolean.class, defaultBooleanTypeGenerator);
		DEFAULT_TYPE_GENERATORS.put(Boolean.class, defaultBooleanTypeGenerator);

		DEFAULT_TYPE_GENERATORS.put(String.class, new DefaultStringTypeGenerator());
	}

	private DefaultTypeGeneratorFactory() {

	}

	@SuppressWarnings("unchecked")
	public static <T> DefaultTypeGenerator<T> build(final Class<T> defaultTypeClass) {
		return (DefaultTypeGenerator<T>) DEFAULT_TYPE_GENERATORS.get(defaultTypeClass);
	}

	public static boolean defaultTypeGeneratorExists(final Class<?> defaultTypeClass) {
		return DEFAULT_TYPE_GENERATORS.containsKey(defaultTypeClass);
	}

}
