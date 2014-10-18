package com.siirush.localwiki.search.module;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.siirush.localwiki.search.LuceneFileSearcherImpl;
import com.siirush.localwiki.search.LuceneSearchEngineImpl;
import com.siirush.localwiki.search.LuceneSearcher;
import com.siirush.localwiki.search.SearchEngine;
import com.siirush.localwiki.search.annotation.IndexAfter;
import com.siirush.localwiki.search.crawler.InMemoryLuceneFileCrawlerImpl;
import com.siirush.localwiki.search.crawler.LuceneFileCrawler;
import com.siirush.localwiki.search.interceptor.IndexInterceptor;

public class SearchModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(SearchEngine.class).to(LuceneSearchEngineImpl.class);
		bind(LuceneFileCrawler.class).to(InMemoryLuceneFileCrawlerImpl.class);
		bind(LuceneSearcher.class).to(LuceneFileSearcherImpl.class);
		IndexInterceptor indexInterceptor = new IndexInterceptor();
		requestInjection(indexInterceptor);
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(IndexAfter.class),indexInterceptor);
	}
}
