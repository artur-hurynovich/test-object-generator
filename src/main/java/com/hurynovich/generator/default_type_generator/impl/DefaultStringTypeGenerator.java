package com.hurynovich.generator.default_type_generator.impl;

import com.hurynovich.generator.default_type_generator.AbstractDefaultTypeGenerator;

public class DefaultStringTypeGenerator extends AbstractDefaultTypeGenerator<String> {

	private static final int DEFAULT_STRING_LENGTH = 10;

	@Override
	public String generate() {
		return getRandomizer().randomStringValue(DEFAULT_STRING_LENGTH);
	}

}
