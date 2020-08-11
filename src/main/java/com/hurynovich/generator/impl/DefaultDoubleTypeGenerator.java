package com.hurynovich.generator.impl;

import com.hurynovich.generator.AbstractDefaultTypeGenerator;

public class DefaultDoubleTypeGenerator extends AbstractDefaultTypeGenerator<Double> {

	@Override
	public Double generate() {
		return getRandomizer().randomDoubleValue();
	}

}
