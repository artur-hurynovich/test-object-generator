package com.hurynovich;

public class DefaultPrimitiveBooleanTypeGenerator extends AbstractDefaultPrimitiveTypeGenerator {

	@Override
	public Boolean generate() {
		return getRandomizer().randomBooleanValue();
	}

}
