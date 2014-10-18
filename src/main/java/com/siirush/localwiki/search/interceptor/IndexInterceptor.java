package com.siirush.localwiki.search.interceptor;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.siirush.localwiki.search.SearchEngine;

public class IndexInterceptor implements MethodInterceptor {
	@Inject
	private SearchEngine searchEngine;
	
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
