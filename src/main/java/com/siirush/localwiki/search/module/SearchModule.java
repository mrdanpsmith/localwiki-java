package com.siirush.localwiki.search.module;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.siirush.localwiki.search.LuceneFileSearcherImpl;
import com.siirush.localwiki.search.SearchEngine;
import com.siirush.localwiki.search.annotation.IndexAfter;
import com.siirush.localwiki.search.crawler.InMemoryLuceneFileCrawlerImpl;
import com.siirush.localwiki.search.crawler.LuceneFileCrawler;
import com.siirush.localwiki.search.interceptor.IndexInterceptor;
import com.siirush.localwiki.search.query.LuceneQueryEngine;
import com.siirush.localwiki.search.query.LuceneQueryEngineImpl;

public class SearchModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(SearchEngine.class).to(LuceneQueryEngineImpl.class);
		bind(LuceneFileCrawler.class).to(InMemoryLuceneFileCrawlerImpl.class);
		bind(LuceneQueryEngine.class).to(LuceneFileSearcherImpl.class);
		IndexInterceptor indexInterceptor = new IndexInterceptor();
		requestInjection(indexInterceptor);
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(IndexAfter.class),indexInterceptor);
	}
}
