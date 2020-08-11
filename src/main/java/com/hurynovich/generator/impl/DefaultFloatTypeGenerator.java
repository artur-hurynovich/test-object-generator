package com.hurynovich.generator.impl;

import com.hurynovich.generator.AbstractDefaultTypeGenerator;

public class DefaultFloatTypeGenerator extends AbstractDefaultTypeGenerator<Float> {

	@Override
	public Float generate() {
		return getRandomizer().randomFloatValue();
	}

}
