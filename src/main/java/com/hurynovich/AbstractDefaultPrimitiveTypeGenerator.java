package com.hurynovich;

import java.io.Serializable;

public abstract class AbstractDefaultPrimitiveTypeGenerator implements PrimitiveTypeGenerator<Serializable> {

	private static final Randomizer RANDOMIZER = new GeneralRandomizer();

	public Randomizer getRandomizer() {
		return RANDOMIZER;
	}

}
