package com.siirush.localwiki.search;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Provider;

import org.apache.lucene.store.Directory;

public class LuceneSearchEngineImpl implements SearchEngine {
	private Directory index;
	private final Provider<LuceneFileCrawler> crawlerProvider;
	private final Provider<LuceneSearcher> searchProvider;
	
	@Inject
	public LuceneSearchEngineImpl(Provider<LuceneFileCrawler> crawlerProvider, Provider<LuceneSearcher> searchProvider) {
		this.crawlerProvider = crawlerProvider;
		this.searchProvider = searchProvider;
	}
	
	public void index() {
		index = crawlerProvider.get().crawl();
	}

	public List<String> search(String query) {
		return searchProvider.get().search(index,query);
	}
}
