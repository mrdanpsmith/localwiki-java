package com.siirush.localwiki.module;

import com.google.inject.AbstractModule;
import com.siirush.localwiki.binding.annotation.Encoding;
import com.siirush.localwiki.binding.annotation.Port;
import com.siirush.localwiki.util.StringUtil;
import com.siirush.localwiki.util.StringUtilImpl;

public class MainModule extends AbstractModule {
	@Override
	protected void configure() {
		install(new ServletModule());
		bind(String.class).annotatedWith(Encoding.class).toInstance("UTF-8");
		bind(Integer.class).annotatedWith(Port.class).toInstance(Integer.valueOf(8080));
		bind(StringUtil.class).to(StringUtilImpl.class);
	}
}
