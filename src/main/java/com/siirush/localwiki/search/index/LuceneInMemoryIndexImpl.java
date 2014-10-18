package com.siirush.localwiki.search.index;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import com.siirush.localwiki.search.crawler.LuceneCrawler;

@Singleton
public class LuceneInMemoryIndexImpl implements LuceneIndex {
	private final LuceneCrawler crawler;
	private Directory directory;
	
	@Inject
	public LuceneInMemoryIndexImpl(LuceneCrawler crawler) {
		this.crawler = crawler;
		directory = crawl();
	}

	public Directory getDirectory() {
		return directory;
	}

	public synchronized void update() {
		directory = crawl();
	}
	
	public Directory crawl() {
		directory = new RAMDirectory();
		crawler.crawl(directory);
		return directory;
	}
}
