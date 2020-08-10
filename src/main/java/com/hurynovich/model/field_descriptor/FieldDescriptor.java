package com.hurynovich.model.field_descriptor;

import java.lang.reflect.Field;

public interface FieldDescriptor {

	boolean matches(Field field, Class<?> containerObjectClass);

}
