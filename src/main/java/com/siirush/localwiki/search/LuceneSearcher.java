package com.siirush.localwiki.search;

import java.util.List;

import org.apache.lucene.store.Directory;

public interface LuceneSearcher {
	List<String> search(Directory index, String query);
}
