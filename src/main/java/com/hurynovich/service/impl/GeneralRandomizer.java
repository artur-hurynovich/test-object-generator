package com.hurynovich.service.impl;

import com.hurynovich.service.Randomizer;

import java.nio.charset.StandardCharsets;
import java.util.Random;

public class GeneralRandomizer implements Randomizer {

	private final Random random = new Random();

	@Override
	public boolean randomBooleanValue() {
		final int intValue = random.nextInt(2);

		return intValue == 0;
	}

	@Override
	public String randomStringValue(final int count) {
		byte[] byteArray = new byte[count];
		random.nextBytes(byteArray);

		return new String(byteArray, StandardCharsets.UTF_8);
	}

}
