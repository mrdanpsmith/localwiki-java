package com.siirush.localwiki.search.crawler;

public interface FileCrawler {
	void setFileProcessor(FileProcessor fileProcessor);
	void crawl();
}
