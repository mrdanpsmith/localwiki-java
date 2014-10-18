package com.siirush.localwiki.search.crawler;

import org.apache.lucene.store.Directory;

public interface LuceneFileCrawler {
	Directory crawl();
}
