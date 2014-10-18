package com.siirush.detect.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Target({ METHOD }) @Retention(RUNTIME)
public @interface Support {
	String version();
	String feature() default "";
}