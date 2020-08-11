package com.hurynovich.factory;

import com.hurynovich.service.test_object_generator_helper.TestObjectGeneratorHelper;
import com.hurynovich.service.test_object_generator_helper.impl.GeneralTestObjectGeneratorHelper;

public class TestObjectGeneratorHelperFactory {

	private static final TestObjectGeneratorHelper HELPER = new GeneralTestObjectGeneratorHelper();

	private TestObjectGeneratorHelperFactory() {

	}

	public static TestObjectGeneratorHelper build() {
		return HELPER;
	}

}
