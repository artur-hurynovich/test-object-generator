package com.hurynovich.generator.impl;

import com.hurynovich.generator.AbstractDefaultTypeGenerator;

public class DefaultLongTypeGenerator extends AbstractDefaultTypeGenerator<Long> {

	@Override
	public Long generate() {
		return getRandomizer().randomLongValue();
	}

}
