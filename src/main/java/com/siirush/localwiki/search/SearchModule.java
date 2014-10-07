package com.siirush.localwiki.search;

import com.google.inject.AbstractModule;

public class SearchModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(SearchEngine.class).to(LuceneSearchEngineImpl.class);
		bind(LuceneFileCrawler.class).to(InMemoryLuceneFileCrawlerImpl.class);
		bind(LuceneSearcher.class).to(LuceneFileSearcherImpl.class);
	}
}
