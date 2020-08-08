package com.hurynovich.factory;

import com.hurynovich.generator.DefaultTypeGenerator;
import com.hurynovich.generator.impl.DefaultBooleanTypeGenerator;
import com.hurynovich.generator.impl.DefaultStringTypeGenerator;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DefaultTypeGeneratorFactory {

	private static final Map<Class<?>, DefaultTypeGenerator<Serializable>> DEFAULT_TYPE_GENERATORS;

	static {
		DEFAULT_TYPE_GENERATORS = new HashMap<>();

		DEFAULT_TYPE_GENERATORS.put(boolean.class, new DefaultBooleanTypeGenerator());
		DEFAULT_TYPE_GENERATORS.put(Boolean.class, new DefaultBooleanTypeGenerator());
		DEFAULT_TYPE_GENERATORS.put(String.class, new DefaultStringTypeGenerator());
	}

	private DefaultTypeGeneratorFactory() {

	}

	public static DefaultTypeGenerator<Serializable> build(final Class<?> primitiveTypeClass) {
		return DEFAULT_TYPE_GENERATORS.get(primitiveTypeClass);
	}

}
