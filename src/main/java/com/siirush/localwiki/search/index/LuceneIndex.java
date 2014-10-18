package com.siirush.localwiki.search.index;

import org.apache.lucene.store.Directory;

public interface LuceneIndex {
	Directory getDirectory();
	void update();
}