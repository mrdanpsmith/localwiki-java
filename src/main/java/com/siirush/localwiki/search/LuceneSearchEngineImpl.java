package com.siirush.localwiki.search;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.lucene.store.Directory;

import com.siirush.localwiki.search.crawler.LuceneFileCrawler;

public class LuceneSearchEngineImpl implements SearchEngine {
	private Directory index;
	private final Provider<LuceneFileCrawler> crawlerProvider;
	private final LuceneSearcher searcher;
	
	@Inject
	public LuceneSearchEngineImpl(Provider<LuceneFileCrawler> crawlerProvider, LuceneSearcher searcher) {
		this.crawlerProvider = crawlerProvider;
		this.searcher = searcher;
	}
	
	public void index() {
		index = crawlerProvider.get().crawl();
	}

	public List<String> search(String query) {
		return searcher.search(index,query);
	}
}
