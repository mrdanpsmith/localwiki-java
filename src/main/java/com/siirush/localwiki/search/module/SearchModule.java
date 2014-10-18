package com.siirush.localwiki.search.module;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.util.Version;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;
import com.siirush.localwiki.search.LuceneSearchEngineImpl;
import com.siirush.localwiki.search.SearchEngine;
import com.siirush.localwiki.search.annotation.IndexAfter;
import com.siirush.localwiki.search.crawler.FileCrawler;
import com.siirush.localwiki.search.crawler.FileCrawlerImpl;
import com.siirush.localwiki.search.crawler.LuceneCrawler;
import com.siirush.localwiki.search.crawler.LuceneFileCrawlerImpl;
import com.siirush.localwiki.search.index.LuceneInMemoryIndexImpl;
import com.siirush.localwiki.search.index.LuceneIndex;
import com.siirush.localwiki.search.interceptor.IndexInterceptor;
import com.siirush.localwiki.search.query.LuceneQueryEngine;
import com.siirush.localwiki.search.query.LuceneQueryEngineImpl;

public class SearchModule extends AbstractModule {
	@Override
	protected void configure() {
		bind(SearchEngine.class).to(LuceneSearchEngineImpl.class);
		bind(LuceneCrawler.class).to(LuceneFileCrawlerImpl.class);
		bind(LuceneQueryEngine.class).to(LuceneQueryEngineImpl.class);
		bind(FileCrawler.class).to(FileCrawlerImpl.class);
		bind(LuceneIndex.class).to(LuceneInMemoryIndexImpl.class);
		bind(IndexWriterConfig.class).toInstance(new IndexWriterConfig(Version.LATEST,new StandardAnalyzer()));
		IndexInterceptor indexInterceptor = new IndexInterceptor();
		requestInjection(indexInterceptor);
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(IndexAfter.class),indexInterceptor);
	}
}
