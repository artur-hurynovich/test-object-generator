package com.hurynovich.generator.default_type_generator.impl;

import com.hurynovich.generator.default_type_generator.AbstractDefaultTypeGenerator;

public class DefaultIntegerTypeGenerator extends AbstractDefaultTypeGenerator<Integer> {

	@Override
	public Integer generate() {
		return getRandomizer().randomIntegerValue();
	}

}
