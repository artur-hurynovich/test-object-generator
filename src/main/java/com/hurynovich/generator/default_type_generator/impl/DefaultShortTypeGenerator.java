package com.hurynovich.generator.default_type_generator.impl;

import com.hurynovich.generator.default_type_generator.AbstractDefaultTypeGenerator;

public class DefaultShortTypeGenerator extends AbstractDefaultTypeGenerator<Short> {

	@Override
	public Short generate() {
		return (short) getRandomizer().randomIntegerValue();
	}

}
