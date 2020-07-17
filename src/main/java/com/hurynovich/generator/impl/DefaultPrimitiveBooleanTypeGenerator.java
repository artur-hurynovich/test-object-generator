package com.hurynovich.generator.impl;

import com.hurynovich.generator.AbstractDefaultPrimitiveTypeGenerator;

public class DefaultPrimitiveBooleanTypeGenerator extends AbstractDefaultPrimitiveTypeGenerator {

	@Override
	public Boolean generate() {
		return getRandomizer().randomBooleanValue();
	}

}
