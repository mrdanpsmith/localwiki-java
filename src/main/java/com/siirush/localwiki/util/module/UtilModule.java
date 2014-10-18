package com.siirush.localwiki.util.module;

import com.google.inject.AbstractModule;
import com.siirush.localwiki.util.StringUtil;
import com.siirush.localwiki.util.StringUtilImpl;

public class UtilModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(StringUtil.class).to(StringUtilImpl.class);
	}
}
