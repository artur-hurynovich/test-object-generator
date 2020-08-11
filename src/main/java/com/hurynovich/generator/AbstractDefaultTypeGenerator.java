package com.hurynovich.generator;

import com.hurynovich.service.Randomizer;
import com.hurynovich.service.impl.GeneralRandomizer;

public abstract class AbstractDefaultTypeGenerator<T> implements DefaultTypeGenerator<T> {

	private static final Randomizer RANDOMIZER = new GeneralRandomizer();

	public Randomizer getRandomizer() {
		return RANDOMIZER;
	}

}
