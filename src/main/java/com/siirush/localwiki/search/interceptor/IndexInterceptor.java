package com.siirush.localwiki.search.interceptor;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.siirush.localwiki.search.SearchEngine;

public class IndexInterceptor implements MethodInterceptor {
	private final SearchEngine searchEngine;
	
	@Inject
	public IndexInterceptor(SearchEngine searchEngine) {
		this.searchEngine = searchEngine;
	}
	
	public Object invoke(MethodInvocation invocation) throws Throwable {
		Throwable thrown = null;
		try {
			return invocation.proceed();
		} catch (Throwable t) {
			thrown = t;
			throw t;
		} finally {
			if (thrown == null) {
				searchEngine.index();
			}
		}
	}
}
