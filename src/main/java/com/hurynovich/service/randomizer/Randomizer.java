package com.hurynovich.service.randomizer;

public interface Randomizer {

	boolean randomBooleanValue();

	int randomIntegerValue();

	long randomLongValue();

	float randomFloatValue();

	double randomDoubleValue();

	char randomCharValue();

	String randomStringValue(int count);

}
