package com.hurynovich.generator.default_type_generator.impl;

import com.hurynovich.generator.default_type_generator.AbstractDefaultTypeGenerator;

public class DefaultByteTypeGenerator extends AbstractDefaultTypeGenerator<Byte> {

	@Override
	public Byte generate() {
		return (byte) getRandomizer().randomIntegerValue();
	}

}
