package com.hurynovich.generator.default_type_generator.impl;

import com.hurynovich.generator.default_type_generator.AbstractDefaultTypeGenerator;

public class DefaultFloatTypeGenerator extends AbstractDefaultTypeGenerator<Float> {

	@Override
	public Float generate() {
		return getRandomizer().randomFloatValue();
	}

}
