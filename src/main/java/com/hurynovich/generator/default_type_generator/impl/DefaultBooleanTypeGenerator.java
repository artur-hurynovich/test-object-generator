package com.hurynovich.generator.default_type_generator.impl;

import com.hurynovich.generator.default_type_generator.AbstractDefaultTypeGenerator;

public class DefaultBooleanTypeGenerator extends AbstractDefaultTypeGenerator<Boolean> {

	@Override
	public Boolean generate() {
		return getRandomizer().randomBooleanValue();
	}

}
