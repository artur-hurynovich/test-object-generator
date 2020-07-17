package com.hurynovich;

import java.io.Serializable;

public interface PrimitiveTypeGenerator<T extends Serializable> {

	T generate();

}
