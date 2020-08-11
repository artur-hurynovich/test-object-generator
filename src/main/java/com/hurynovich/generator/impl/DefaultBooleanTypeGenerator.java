package com.hurynovich.generator.impl;

import com.hurynovich.generator.AbstractDefaultTypeGenerator;

public class DefaultBooleanTypeGenerator extends AbstractDefaultTypeGenerator<Boolean> {

	@Override
	public Boolean generate() {
		return getRandomizer().randomBooleanValue();
	}

}
