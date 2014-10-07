package com.siirush.localwiki.search;

import org.apache.lucene.store.Directory;

public interface LuceneFileCrawler {
	Directory crawl();
}
