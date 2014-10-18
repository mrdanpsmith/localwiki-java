package com.siirush.localwiki.search.crawler;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.siirush.localwiki.binding.annotation.Encoding;

public class InMemoryLuceneFileCrawlerImpl implements LuceneFileCrawler {
	private final String directoryToIndex;
	private final Integer maxDepth;
	private final FilenameFilter filenameFilter;
	private final Version luceneVersion;
	private final String encoding;
	private final Logger logger;
	
	private IndexWriter indexWriter;
	
	@Inject
	public InMemoryLuceneFileCrawlerImpl(@Encoding String encoding, Logger logger) {
		this.directoryToIndex = ".";
		this.maxDepth = 0;
		this.filenameFilter = new FilenameFilter() {
		    public boolean accept(File dir, String name) {
		        return name.matches(".*\\.md");
		    }
		};
		this.luceneVersion = Version.LATEST;
		this.encoding = encoding;
		this.logger = logger;
	}
	
	public Directory crawl() {
		Directory index = new RAMDirectory();
		File basePath = new File(directoryToIndex);
		IndexWriterConfig config = new IndexWriterConfig(luceneVersion,new StandardAnalyzer());
		try {
			indexWriter = new IndexWriter(index, config);
			crawl(basePath);
			indexWriter.close();
			return index;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private void crawl(File path) {
		crawl(path,1);
	}
	
	private void crawl(File path, int currentLevel) {
		if (path.canRead() && path.isDirectory()) {
			for (File file: path.listFiles(filenameFilter)) {
				if (file.canRead() && file.isDirectory() && currentLevel - maxDepth != 0) {
					crawl(file,currentLevel+1);
				} else if (file.canRead() && file.isFile()) {
					addToIndex(file);
				}
			}
		}
	}

	private void addToIndex(File file) {
		try {
			logger.info("Adding: " + getFilename(file));
			indexWriter.addDocument(document(file));
			logger.info("Added: " + getFilename(file));
		} catch (IOException e) {
			logger.warning("IOException adding: " + getFilename(file) + " to index.  File will not be added.");
			logger.warning(e.getStackTrace().toString());
		}
	}

	private Document document(File file) throws IOException {
		Document doc = new Document();
		doc.add(new TextField("filename", getFilename(file), Field.Store.YES));
		doc.add(new TextField("content", getContent(file), Field.Store.YES));
		logger.finest(doc.toString());
		return doc;
	}

	private String getFilename(File file) {
		return file.getName();
	}
	
	private String getContent(File file) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(file.getCanonicalPath()));
		return new String(encoded, encoding);
	}
}
