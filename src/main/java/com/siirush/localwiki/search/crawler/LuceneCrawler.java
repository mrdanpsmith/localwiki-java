package com.siirush.localwiki.search.crawler;

import org.apache.lucene.store.Directory;

public interface LuceneCrawler {
	void crawl(Directory directory);
}
