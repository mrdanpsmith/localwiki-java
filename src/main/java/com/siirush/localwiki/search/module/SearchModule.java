package com.siirush.localwiki.search.module;

import com.google.inject.AbstractModule;
import com.siirush.localwiki.search.InMemoryLuceneFileCrawlerImpl;
import com.siirush.localwiki.search.LuceneFileCrawler;
import com.siirush.localwiki.search.LuceneFileSearcherImpl;
import com.siirush.localwiki.search.LuceneSearchEngineImpl;
import com.siirush.localwiki.search.LuceneSearcher;
import com.siirush.localwiki.search.SearchEngine;

public class SearchModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(SearchEngine.class).to(LuceneSearchEngineImpl.class);
		bind(LuceneFileCrawler.class).to(InMemoryLuceneFileCrawlerImpl.class);
		bind(LuceneSearcher.class).to(LuceneFileSearcherImpl.class);
	}
}
