package com.hurynovich.generator.default_type_generator.impl;

import com.hurynovich.generator.default_type_generator.AbstractDefaultTypeGenerator;

public class DefaultCharTypeGenerator extends AbstractDefaultTypeGenerator<Character> {

	@Override
	public Character generate() {
		return getRandomizer().randomCharValue();
	}

}
