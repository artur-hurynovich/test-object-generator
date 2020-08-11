package com.hurynovich.generator.impl;

import com.hurynovich.generator.AbstractDefaultTypeGenerator;

public class DefaultStringTypeGenerator extends AbstractDefaultTypeGenerator<String> {

	private static final int DEFAULT_STRING_LENGTH = 10;

	@Override
	public String generate() {
		return getRandomizer().randomStringValue(DEFAULT_STRING_LENGTH);
	}

}
