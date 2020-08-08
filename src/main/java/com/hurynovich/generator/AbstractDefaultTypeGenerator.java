package com.hurynovich.generator;

import com.hurynovich.service.Randomizer;
import com.hurynovich.service.impl.GeneralRandomizer;

public abstract class AbstractDefaultTypeGenerator implements DefaultTypeGenerator<Object> {

	private static final Randomizer RANDOMIZER = new GeneralRandomizer();

	public Randomizer getRandomizer() {
		return RANDOMIZER;
	}

}
