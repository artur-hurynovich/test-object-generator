package com.hurynovich;

import java.util.Random;

public class GeneralRandomizer implements Randomizer {

	private final Random random = new Random();

	@Override
	public boolean randomBooleanValue() {
		final int intValue = random.nextInt(2);

		return intValue == 0;
	}

}
