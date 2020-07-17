package com.hurynovich.generator;

import java.io.Serializable;

public interface PrimitiveTypeGenerator<T extends Serializable> {

	T generate();

}
