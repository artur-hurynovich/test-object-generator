package com.hurynovich.factory;

import com.hurynovich.generator.default_type_generator.DefaultTypeGenerator;
import com.hurynovich.generator.default_type_generator.impl.DefaultBigDecimalTypeGenerator;
import com.hurynovich.generator.default_type_generator.impl.DefaultBooleanTypeGenerator;
import com.hurynovich.generator.default_type_generator.impl.DefaultByteTypeGenerator;
import com.hurynovich.generator.default_type_generator.impl.DefaultCharTypeGenerator;
import com.hurynovich.generator.default_type_generator.impl.DefaultDoubleTypeGenerator;
import com.hurynovich.generator.default_type_generator.impl.DefaultFloatTypeGenerator;
import com.hurynovich.generator.default_type_generator.impl.DefaultIntegerTypeGenerator;
import com.hurynovich.generator.default_type_generator.impl.DefaultLongTypeGenerator;
import com.hurynovich.generator.default_type_generator.impl.DefaultShortTypeGenerator;
import com.hurynovich.generator.default_type_generator.impl.DefaultStringTypeGenerator;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DefaultTypeGeneratorFactory {

	private static final Map<Class<?>, DefaultTypeGenerator<?>> DEFAULT_TYPE_GENERATORS;

	static {
		DEFAULT_TYPE_GENERATORS = new HashMap<>();

		final DefaultTypeGenerator<Boolean> defaultBooleanTypeGenerator = new DefaultBooleanTypeGenerator();
		DEFAULT_TYPE_GENERATORS.put(boolean.class, defaultBooleanTypeGenerator);
		DEFAULT_TYPE_GENERATORS.put(Boolean.class, defaultBooleanTypeGenerator);

		final DefaultTypeGenerator<Byte> defaultByteTypeGenerator = new DefaultByteTypeGenerator();
		DEFAULT_TYPE_GENERATORS.put(byte.class, defaultByteTypeGenerator);
		DEFAULT_TYPE_GENERATORS.put(Byte.class, defaultByteTypeGenerator);

		final DefaultTypeGenerator<Short> defaultShortTypeGenerator = new DefaultShortTypeGenerator();
		DEFAULT_TYPE_GENERATORS.put(short.class, defaultShortTypeGenerator);
		DEFAULT_TYPE_GENERATORS.put(Short.class, defaultShortTypeGenerator);

		final DefaultTypeGenerator<Integer> defaultIntegerTypeGenerator = new DefaultIntegerTypeGenerator();
		DEFAULT_TYPE_GENERATORS.put(int.class, defaultIntegerTypeGenerator);
		DEFAULT_TYPE_GENERATORS.put(Integer.class, defaultIntegerTypeGenerator);

		final DefaultTypeGenerator<Long> defaultLongTypeGenerator = new DefaultLongTypeGenerator();
		DEFAULT_TYPE_GENERATORS.put(long.class, defaultLongTypeGenerator);
		DEFAULT_TYPE_GENERATORS.put(Long.class, defaultLongTypeGenerator);

		final DefaultTypeGenerator<Float> defaultFloatTypeGenerator = new DefaultFloatTypeGenerator();
		DEFAULT_TYPE_GENERATORS.put(float.class, defaultFloatTypeGenerator);
		DEFAULT_TYPE_GENERATORS.put(Float.class, defaultFloatTypeGenerator);

		final DefaultTypeGenerator<Double> defaultDoubleTypeGenerator = new DefaultDoubleTypeGenerator();
		DEFAULT_TYPE_GENERATORS.put(double.class, defaultDoubleTypeGenerator);
		DEFAULT_TYPE_GENERATORS.put(Double.class, defaultDoubleTypeGenerator);

		final DefaultTypeGenerator<Character> defaultCharTypeGenerator = new DefaultCharTypeGenerator();
		DEFAULT_TYPE_GENERATORS.put(char.class, defaultCharTypeGenerator);
		DEFAULT_TYPE_GENERATORS.put(Character.class, defaultCharTypeGenerator);

		DEFAULT_TYPE_GENERATORS.put(BigDecimal.class, new DefaultBigDecimalTypeGenerator());

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
