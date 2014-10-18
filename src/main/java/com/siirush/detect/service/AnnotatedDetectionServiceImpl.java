package com.siirush.detect.service;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

import org.reflections.Reflections;

import com.google.inject.Inject;
import com.siirush.detect.annotation.Support;
import com.siirush.detect.model.Feature;

public class AnnotatedDetectionServiceImpl implements DetectionService {
	private final Reflections reflections;
	private final Set<Feature> support;
	
	private static final String GETTER_PREFIX = "get";
	private static final int GETTER_PREFIX_LENGTH = GETTER_PREFIX.length();
	
	@Inject
	public AnnotatedDetectionServiceImpl(Reflections reflections) {
		this.reflections = reflections;
		this.support = locateSupport();
	}

	public Set<Feature> getSupport() {
		return support;
	}
	
	private Set<Feature> locateSupport() {
		Set<Method> methods = reflections.getMethodsAnnotatedWith(Support.class);
		Set<Feature> support = new HashSet<Feature>();
		for (Method method: methods) {
			support.add(feature(method));
		}
		return support;
	}

	private Feature feature(Method method) {
		Feature feature = new Feature();
		Support support = method.getAnnotation(Support.class);
		feature.setName(support.feature().isEmpty() ? propertyName(method) : support.feature());
		feature.setVersion(support.version());
		return feature;
	}

	private String propertyName(Method method) {
		String methodName = method.getName();
		return methodName.startsWith(GETTER_PREFIX) ? removeGetterPrefix(methodName) : methodName;
	}

	String removeGetterPrefix(String methodName) {
		StringBuilder sb = new StringBuilder();
		sb.append(methodName.substring(GETTER_PREFIX_LENGTH,GETTER_PREFIX_LENGTH+1).toLowerCase());
		sb.append(methodName.substring(GETTER_PREFIX_LENGTH+1));
		return sb.toString();
	}
}
