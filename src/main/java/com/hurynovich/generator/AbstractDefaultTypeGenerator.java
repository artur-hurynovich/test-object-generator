package com.hurynovich.generator;

import com.hurynovich.service.Randomizer;
import com.hurynovich.service.impl.GeneralRandomizer;

import java.io.Serializable;

public abstract class AbstractDefaultTypeGenerator implements DefaultTypeGenerator<Serializable> {

	private static final Randomizer RANDOMIZER = new GeneralRandomizer();

	public Randomizer getRandomizer() {
		return RANDOMIZER;
	}

}
