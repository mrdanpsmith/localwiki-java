package com.siirush.detect.module;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.siirush.detect.service.AnnotatedDetectionServiceImpl;
import com.siirush.detect.service.DetectionService;
import com.siirush.localwiki.service.SearchService;

public class DetectionModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(DetectionService.class).to(AnnotatedDetectionServiceImpl.class);
	}
	
	@Provides
	Reflections getReflections() {
		return new Reflections(new ConfigurationBuilder()
	 					.filterInputsBy(new FilterBuilder().includePackage(SearchService.class.getPackage().getName()))
	 					.setUrls(ClasspathHelper.forJavaClassPath())
	 					.setScanners(new MethodAnnotationsScanner()));
	}
}
