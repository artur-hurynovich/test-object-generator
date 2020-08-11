package com.hurynovich.generator.impl;

import com.hurynovich.generator.AbstractDefaultTypeGenerator;

public class DefaultIntegerTypeGenerator extends AbstractDefaultTypeGenerator<Integer> {

	@Override
	public Integer generate() {
		return getRandomizer().randomIntegerValue();
	}

}
