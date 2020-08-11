package com.hurynovich.service.randomizer.impl;

import com.hurynovich.service.randomizer.Randomizer;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public class GeneralRandomizer implements Randomizer {

	private final Random random = new Random();

	@Override
	public boolean randomBooleanValue() {
		final int intValue = random.nextInt(2);

		return intValue == 0;
	}

	@Override
	public int randomIntegerValue() {
		return random.nextInt();
	}

	@Override
	public long randomLongValue() {
		return random.nextLong();
	}

	@Override
	public float randomFloatValue() {
		return random.nextFloat();
	}

	@Override
	public double randomDoubleValue() {
		return random.nextDouble();
	}

	@Override
	public char randomCharValue() {
		return RandomStringUtils.randomAlphanumeric(1).charAt(0);
	}

	@Override
	public String randomStringValue(final int count) {
		return RandomStringUtils.randomAlphanumeric(count);
	}

}
