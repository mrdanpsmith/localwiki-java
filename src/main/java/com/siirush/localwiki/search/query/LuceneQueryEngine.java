package com.siirush.localwiki.search.query;

import java.util.List;

import org.apache.lucene.store.Directory;

public interface LuceneQueryEngine {
	List<String> search(Directory index, String query);
}
