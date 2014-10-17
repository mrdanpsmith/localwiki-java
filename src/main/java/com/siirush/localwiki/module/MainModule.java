package com.siirush.localwiki.module;

import com.google.inject.AbstractModule;
import com.siirush.localwiki.binding.annotation.Encoding;
import com.siirush.localwiki.configuration.LocalwikiConfiguration;
import com.siirush.localwiki.configuration.LocalwikiConfigurationProvider;
import com.siirush.localwiki.configuration.binding.annotation.ConfigurationFilePath;
import com.siirush.localwiki.service.SaveService;
import com.siirush.localwiki.service.SaveServiceImpl;

public class MainModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(LocalwikiConfiguration.class).toProvider(LocalwikiConfigurationProvider.class);
		bind(String.class).annotatedWith(ConfigurationFilePath.class).toInstance("config.json");
		bind(String.class).annotatedWith(Encoding.class).toInstance("UTF-8");
		
		bind(SaveService.class).to(SaveServiceImpl.class);
		install(new ServletModule());
		install(new SearchModule());
		install(new DetectionModule());
		install(new UtilModule());
	}
}
