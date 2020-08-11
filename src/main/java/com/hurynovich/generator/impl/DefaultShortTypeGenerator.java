package com.hurynovich.generator.impl;

import com.hurynovich.generator.AbstractDefaultTypeGenerator;

public class DefaultShortTypeGenerator extends AbstractDefaultTypeGenerator<Short> {

	@Override
	public Short generate() {
		return (short) getRandomizer().randomIntegerValue();
	}

}
