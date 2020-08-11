package com.hurynovich.factory;

import com.hurynovich.generator.TestObjectGeneratorHelper;
import com.hurynovich.generator.impl.GeneralTestObjectGeneratorHelper;

public class TestObjectGeneratorHelperFactory {

	private static final TestObjectGeneratorHelper HELPER = new GeneralTestObjectGeneratorHelper();

	private TestObjectGeneratorHelperFactory() {

	}

	public static TestObjectGeneratorHelper build() {
		return HELPER;
	}

}
