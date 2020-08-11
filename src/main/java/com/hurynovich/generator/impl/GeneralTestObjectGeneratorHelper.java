package com.hurynovich.generator.impl;

import com.hurynovich.generator.TestObjectGeneratorHelper;

import java.util.Random;

public class GeneralTestObjectGeneratorHelper implements TestObjectGeneratorHelper {

	private static final Random RANDOM = new Random();

	@Override
	public <T> T generateRandomEnumValue(final Class<T> enumClass) {
		final T[] enumConstants = enumClass.getEnumConstants();

		final int randomEnumConstantIndex = RANDOM.nextInt(enumConstants.length);

		return enumConstants[randomEnumConstantIndex];
	}

}
