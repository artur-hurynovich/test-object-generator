package com.hurynovich.generator.default_type_generator.impl;

import com.hurynovich.generator.default_type_generator.AbstractDefaultTypeGenerator;

import java.math.BigDecimal;

public class DefaultBigDecimalTypeGenerator extends AbstractDefaultTypeGenerator<BigDecimal> {

	@Override
	public BigDecimal generate() {
		return BigDecimal.valueOf(getRandomizer().randomDoubleValue());
	}

}
