package com.hurynovich;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class PrimitiveTypeGeneratorFactory {

	private static final Map<Class<?>, PrimitiveTypeGenerator<? extends Serializable>> PRIMITIVE_TYPE_GENERATORS;

	static {
		PRIMITIVE_TYPE_GENERATORS = new HashMap<>();

		PRIMITIVE_TYPE_GENERATORS.put(Boolean.TYPE, new DefaultPrimitiveBooleanTypeGenerator());
	}

	private PrimitiveTypeGeneratorFactory() {

	}

	public static PrimitiveTypeGenerator<? extends Serializable> build(final Class<?> primitiveTypeClass) {
		return PRIMITIVE_TYPE_GENERATORS.get(primitiveTypeClass);
	}

}
