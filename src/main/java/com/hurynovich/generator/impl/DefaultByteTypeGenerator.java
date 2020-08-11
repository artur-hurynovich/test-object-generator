package com.hurynovich.generator.impl;

import com.hurynovich.generator.AbstractDefaultTypeGenerator;

public class DefaultByteTypeGenerator extends AbstractDefaultTypeGenerator<Byte> {

	@Override
	public Byte generate() {
		return (byte) getRandomizer().randomIntegerValue();
	}

}
