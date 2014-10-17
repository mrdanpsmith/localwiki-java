package com.siirush.localwiki.module;

import com.google.inject.AbstractModule;
import com.siirush.localwiki.binding.annotation.Encoding;
import com.siirush.localwiki.configuration.LocalwikiConfiguration;
import com.siirush.localwiki.configuration.LocalwikiConfigurationProvider;
import com.siirush.localwiki.configuration.binding.annotation.ConfigurationFilePath;
import com.siirush.localwiki.search.LuceneSearchEngineImpl;
import com.siirush.localwiki.search.SearchEngine;
import com.siirush.localwiki.search.SearchModule;
import com.siirush.localwiki.util.StringUtil;
import com.siirush.localwiki.util.StringUtilImpl;

public class MainModule extends AbstractModule {
	@Override
	protected void configure() {
		install(new ServletModule());
		install(new SearchModule());
		install(new DetectionModule());
		bind(String.class).annotatedWith(ConfigurationFilePath.class).toInstance("config.json");
		bind(String.class).annotatedWith(Encoding.class).toInstance("UTF-8");
		bind(LocalwikiConfiguration.class).toProvider(LocalwikiConfigurationProvider.class);
		bind(StringUtil.class).to(StringUtilImpl.class);
		bind(SearchEngine.class).to(LuceneSearchEngineImpl.class);
	}
}
