package com.hurynovich.generator;

import java.io.Serializable;

public interface DefaultTypeGenerator<T extends Serializable> {

	T generate();

}
