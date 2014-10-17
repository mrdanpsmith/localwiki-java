package com.siirush.detect.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.reflections.Reflections;

public class AnnotatedDetectionServiceImplTest {
	AnnotatedDetectionServiceImpl target = new AnnotatedDetectionServiceImpl(new Reflections());
	
	@Test
	public void testRemoveGetterPrefix() throws NoSuchMethodException, SecurityException {
		String value = target.removeGetterPrefix("getValue");
		assertEquals("value",value);
	}
}
