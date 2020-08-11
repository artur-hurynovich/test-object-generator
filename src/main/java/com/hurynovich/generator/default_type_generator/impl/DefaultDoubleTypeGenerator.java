package com.hurynovich.generator.default_type_generator.impl;

import com.hurynovich.generator.default_type_generator.AbstractDefaultTypeGenerator;

public class DefaultDoubleTypeGenerator extends AbstractDefaultTypeGenerator<Double> {

	@Override
	public Double generate() {
		return getRandomizer().randomDoubleValue();
	}

}
