package com.siirush.localwiki.search;

import java.util.List;

import javax.inject.Inject;

import com.siirush.localwiki.search.index.LuceneIndex;
import com.siirush.localwiki.search.query.LuceneQueryEngine;

public class LuceneSearchEngineImpl implements SearchEngine {
	private final LuceneIndex luceneIndex;
	private final LuceneQueryEngine queryEngine;
	
	@Inject
	public LuceneSearchEngineImpl(LuceneIndex luceneIndex, LuceneQueryEngine queryEngine) {
		this.luceneIndex = luceneIndex;
		this.queryEngine = queryEngine;
	}
	
	public void index() {
		luceneIndex.update();
	}

	public List<String> search(String query) {
		return queryEngine.search(query);
	}
}
