package com.hurynovich.generator.default_type_generator;

import com.hurynovich.service.randomizer.Randomizer;
import com.hurynovich.service.randomizer.impl.GeneralRandomizer;

public abstract class AbstractDefaultTypeGenerator<T> implements DefaultTypeGenerator<T> {

	private final Randomizer randomizer = new GeneralRandomizer();

	public Randomizer getRandomizer() {
		return randomizer;
	}

}
