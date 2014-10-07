package com.siirush.localwiki.module;

import com.siirush.localwiki.provider.GsonMessageBodyReader;
import com.siirush.localwiki.provider.GsonMessageBodyWriter;
import com.siirush.localwiki.service.LocalWikiService;
import com.siirush.localwiki.service.LocalWikiServiceImpl;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

public class ServletModule extends com.google.inject.servlet.ServletModule {
	protected void configureServlets() {
		bind(LocalWikiService.class).to(LocalWikiServiceImpl.class);
		bind(GsonMessageBodyReader.class);
		bind(GsonMessageBodyWriter.class);
		serve("/*").with( GuiceContainer.class );
	}
}