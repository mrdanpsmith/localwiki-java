package com.siirush.localwiki.search.crawler;

import java.io.File;
import java.io.FilenameFilter;

import javax.inject.Inject;

import com.siirush.localwiki.configuration.LocalwikiConfiguration;

public class FileCrawlerImpl implements FileCrawler {
	private final File basePath;
	private final FilenameFilter filenameFilter;
	private final Integer maxDepth;
	
	@Inject
	public FileCrawlerImpl(LocalwikiConfiguration configuration) {
		final String filePattern = configuration.getServer().getSearchIndex().getFilePattern();
		this.basePath = new File(configuration.getPath().getBaseDir());
		this.maxDepth = configuration.getServer().getSearchIndex().getMaxDepth();
		this.filenameFilter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.matches(filePattern);
			}
		};
	}
	
	public void crawl(FileProcessor fileProcessor) {
		crawl(fileProcessor, basePath, 1);
	}
	
	private void crawl(FileProcessor fileProcessor, File path, int currentLevel) {
		if (path.canRead() && path.isDirectory()) {
			for (File file: path.listFiles(filenameFilter)) {
				if (file.canRead() && file.isDirectory() && currentLevel - maxDepth != 0) {
					crawl(fileProcessor,file,currentLevel+1);
				} else if (file.canRead() && file.isFile()) {
					fileProcessor.process(file);
				}
			}
		}
	}
}
