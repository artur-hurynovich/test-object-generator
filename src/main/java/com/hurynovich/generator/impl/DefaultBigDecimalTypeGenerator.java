package com.hurynovich.generator.impl;

import com.hurynovich.generator.AbstractDefaultTypeGenerator;

import java.math.BigDecimal;

public class DefaultBigDecimalTypeGenerator extends AbstractDefaultTypeGenerator<BigDecimal> {

	@Override
	public BigDecimal generate() {
		return BigDecimal.valueOf(getRandomizer().randomDoubleValue());
	}

}
