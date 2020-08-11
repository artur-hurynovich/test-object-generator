package com.hurynovich.generator.default_type_generator.impl;

import com.hurynovich.generator.default_type_generator.AbstractDefaultTypeGenerator;

public class DefaultLongTypeGenerator extends AbstractDefaultTypeGenerator<Long> {

	@Override
	public Long generate() {
		return getRandomizer().randomLongValue();
	}

}
