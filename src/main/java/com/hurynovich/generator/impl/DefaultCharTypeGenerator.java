package com.hurynovich.generator.impl;

import com.hurynovich.generator.AbstractDefaultTypeGenerator;

public class DefaultCharTypeGenerator extends AbstractDefaultTypeGenerator<Character> {

	@Override
	public Character generate() {
		return getRandomizer().randomCharValue();
	}

}
