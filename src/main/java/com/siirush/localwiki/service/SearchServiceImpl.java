package com.siirush.localwiki.service;

import java.util.List;

import javax.inject.Inject;

import com.siirush.detect.model.Support;
import com.siirush.localwiki.search.SearchEngine;

public class SearchServiceImpl implements SearchService {
	private final SearchEngine searchEngine;
			
	@Inject
	public SearchServiceImpl(SearchEngine searchEngine) {
		this.searchEngine = searchEngine;
	}

	@Support(version = "0.1")
	public List<String> search(String query) {
		return searchEngine.search(query);
	}
}
