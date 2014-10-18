package com.siirush.localwiki.search.query;

import java.util.List;

public interface LuceneQueryEngine {
	List<String> search(String query);
}
