package com.hurynovich.generator.test_object_generator.impl;

import com.hurynovich.exception.TestObjectGeneratorException;
import com.hurynovich.factory.DefaultTypeGeneratorFactory;
import com.hurynovich.factory.TestObjectGeneratorHelperFactory;
import com.hurynovich.generator.default_type_generator.DefaultTypeGenerator;
import com.hurynovich.generator.test_object_generator.TestObjectGenerator;
import com.hurynovich.service.test_object_generator_helper.TestObjectGeneratorHelper;
import com.hurynovich.model.field_descriptor.FieldDescriptor;
import com.hurynovich.model.object_container_descriptor.ContainerDescriptor;
import com.hurynovich.util.ExtendedReflectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GeneralTestObjectGenerator implements TestObjectGenerator {

	private static final Log LOGGER = LogFactory.getLog(GeneralTestObjectGenerator.class);

	private final TestObjectGeneratorHelper helper = TestObjectGeneratorHelperFactory.build();

	private Collection<FieldDescriptor> ignoreFields;

	private Map<FieldDescriptor, DefaultTypeGenerator<?>> customTypeGenerators;

	private Map<FieldDescriptor, ContainerDescriptor> containerDescriptors;

	private GeneralTestObjectGenerator() {

	}

	public static class GeneralTestObjectGeneratorBuilder {

		private final GeneralTestObjectGenerator generator;

		public GeneralTestObjectGeneratorBuilder() {
			generator = new GeneralTestObjectGenerator();
		}

		public GeneralTestObjectGeneratorBuilder withIgnoreFields(final Collection<FieldDescriptor> ignoreFields) {
			generator.ignoreFields = ignoreFields;

			return this;
		}

		public GeneralTestObjectGeneratorBuilder withCustomTypeGenerators(
				final Map<FieldDescriptor, DefaultTypeGenerator<?>> customTypeGenerators) {
			generator.customTypeGenerators = customTypeGenerators;

			return this;
		}

		public GeneralTestObjectGeneratorBuilder withContainerDescriptors(final Map<FieldDescriptor, ContainerDescriptor> containerDescriptors) {
			generator.containerDescriptors = containerDescriptors;

			return this;
		}

		public GeneralTestObjectGenerator build() {
			if (generator.ignoreFields == null) {
				generator.ignoreFields = Collections.emptyList();
			}

			if (generator.customTypeGenerators == null) {
				generator.customTypeGenerators = Collections.emptyMap();
			}

			if (generator.containerDescriptors == null) {
				generator.containerDescriptors = Collections.emptyMap();
			}

			return generator;
		}

	}

	public static GeneralTestObjectGeneratorBuilder builder() {
		return new GeneralTestObjectGeneratorBuilder();
	}

	@Override
	public <T> T generate(final Class<T> objectClass) {
		if (objectClass.isEnum()) {
			return helper.generateRandomEnumValue(objectClass);
		} else if (DefaultTypeGeneratorFactory.defaultTypeGeneratorExists(objectClass)) {
			return DefaultTypeGeneratorFactory.build(objectClass).generate();
		} else {
			final T testObject = instantiate(objectClass);

			ReflectionUtils.doWithFields(objectClass, field -> {
				if (notIgnoredField(objectClass, field, ignoreFields)) {
					generateField(field, testObject);
				}
			});

			return testObject;
		}
	}

	private <T> T instantiate(final Class<T> objectClass) {
		try {
			return ReflectionUtils.accessibleConstructor(objectClass).newInstance();
		} catch (final NoSuchMethodException e) {
			throw new TestObjectGeneratorException("No default constructor found for class '" +
					objectClass + "'", e);
		} catch (final InstantiationException | IllegalAccessException | InvocationTargetException e) {
			throw new TestObjectGeneratorException("Failed to instantiate object of class '" +
					objectClass + "'", e);
		}
	}

	private boolean notIgnoredField(final Class<?> containerObjectClass, final Field field,
									final Collection<FieldDescriptor> ignoreFields) {
		boolean notIgnoredField = true;

		for (final FieldDescriptor fieldDescriptor : ignoreFields) {
			if (fieldDescriptor.matches(field, containerObjectClass)) {
				notIgnoredField = false;

				break;
			}
		}

		return notIgnoredField;
	}

	private void generateField(final Field field, final Object object) {
		final Class<?> objectClass = object.getClass();

		final Class<?> fieldType = field.getType();

		final List<DefaultTypeGenerator<?>> matchingCustomTypeGenerators =
				findMatchingCustomTypeGenerators(field, objectClass);

		if (matchingCustomTypeGenerators.isEmpty()) {
			final Object fieldValue;

			if (DefaultTypeGeneratorFactory.defaultTypeGeneratorExists(objectClass)) {
				fieldValue = DefaultTypeGeneratorFactory.build(fieldType).generate();
			} else {
				if (fieldType.isArray() || fieldType.isAssignableFrom(Collection.class) || fieldType.isAssignableFrom(Map.class)) {
					final List<ContainerDescriptor> matchingContainerDescriptors =
							findMatchingContainerDescriptors(field, objectClass);

					if (matchingContainerDescriptors.isEmpty()) {
						fieldValue = null;

						LOGGER.warn("No ContainerDescriptor implementation found " +
								"for field with name '" + field.getName() + "' of type '" + fieldType.getName() + "' " +
								"in class '" + objectClass.getName() + "'. Field value will be set to null");
					} else if (matchingContainerDescriptors.size() == 1) {
						final ContainerDescriptor matchingContainerDescriptor = matchingContainerDescriptors.iterator().next();

						fieldValue = helper.buildAndFillContainer(matchingContainerDescriptor, this::generate);
					} else {
						fieldValue = null;

						LOGGER.warn("More than one ContainerDescriptor implementation found " +
								"for field with name '" + field.getName() + "' of type '" + fieldType.getName() + "' " +
								"in class '" + objectClass.getName() + "'. Field value will be set to null");
					}
				} else {
					fieldValue = generate(fieldType);
				}
			}

			setFieldValue(field, object, fieldValue);
		} else if (matchingCustomTypeGenerators.size() == 1) {
			final DefaultTypeGenerator<?> defaultTypeGenerator = matchingCustomTypeGenerators.iterator().next();

			final Object generatedFieldValue = defaultTypeGenerator.generate();

			final Class<?> generatedFieldValueClass = generatedFieldValue.getClass();

			if (generatedFieldValueClass.equals(fieldType)) {
				setFieldValue(field, object, generatedFieldValue);
			} else {
				throw new TestObjectGeneratorException("'" + defaultTypeGenerator.getClass().getName() + "' " +
						"is not a valid DefaultTypeGenerator implementation " +
						"for field of type '" + fieldType.getName() + "'");
			}
		} else {
			setFieldValue(field, object, null);

			LOGGER.warn("More than one DefaultTypeGenerator implementation found " +
					"for field with name '" + field.getName() + "' of type '" + fieldType.getName() + "' " +
					"in class '" + objectClass.getName() + "'. Field value will be set to null");
		}
	}

	private List<DefaultTypeGenerator<?>> findMatchingCustomTypeGenerators(final Field field, final Class<?> containerObjectClass) {
		return customTypeGenerators.entrySet().stream().
				filter(entry -> entry.getKey().matches(field, containerObjectClass)).
				map(Map.Entry::getValue).
				collect(Collectors.toList());
	}

	private List<ContainerDescriptor> findMatchingContainerDescriptors(final Field field, final Class<?> containerObjectClass) {
		return containerDescriptors.entrySet().stream().
				filter(entry -> entry.getKey().matches(field, containerObjectClass) && entry.getValue().matches(containerObjectClass)).
				map(Map.Entry::getValue).
				collect(Collectors.toList());
	}

	private void setFieldValue(final Field field, final Object object, final Object fieldValue) {
		final Class<?> objectClass = object.getClass();

		final Class<?> fieldType = field.getType();

		final Method setter = ExtendedReflectionUtils.findStandardSetterForField(field, objectClass);

		if (setter != null) {
			ReflectionUtils.invokeMethod(setter, object, fieldValue);
		} else {
			LOGGER.warn("No standard setter found for field of type '" + fieldType +
					"' and name '" + field.getName() + "' in class '" + objectClass.getName() + "'");
		}
	}

}
