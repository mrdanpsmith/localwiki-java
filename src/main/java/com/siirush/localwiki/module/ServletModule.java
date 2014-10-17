package com.siirush.localwiki.module;

import com.siirush.localwiki.provider.GsonMessageBodyReader;
import com.siirush.localwiki.provider.GsonMessageBodyWriter;
import com.siirush.localwiki.service.SearchService;
import com.siirush.localwiki.service.SearchServiceImpl;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class ServletModule extends com.google.inject.servlet.ServletModule {
	protected void configureServlets() {
		bind(SearchService.class).to(SearchServiceImpl.class);
		bind(GsonMessageBodyReader.class);
		bind(GsonMessageBodyWriter.class);
		serve("/*").with( GuiceContainer.class );
	}
}