package com.hurynovich.generator;

import com.hurynovich.service.impl.GeneralRandomizer;
import com.hurynovich.service.Randomizer;

import java.io.Serializable;

public abstract class AbstractDefaultPrimitiveTypeGenerator implements PrimitiveTypeGenerator<Serializable> {

	private static final Randomizer RANDOMIZER = new GeneralRandomizer();

	public Randomizer getRandomizer() {
		return RANDOMIZER;
	}

}
