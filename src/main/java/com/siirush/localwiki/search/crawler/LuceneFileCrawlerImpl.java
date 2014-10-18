package com.siirush.localwiki.search.crawler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;

import com.siirush.localwiki.binding.annotation.Encoding;

public class LuceneFileCrawlerImpl implements LuceneCrawler {
	private final Logger logger;
	private final String encoding;
	private final IndexWriterConfig config;
	private final FileCrawler fileCrawler;
	
	@Inject
	public LuceneFileCrawlerImpl(Logger logger, 
								@Encoding String encoding, 
								IndexWriterConfig config, 
								FileCrawler fileCrawler) {
		this.logger = logger;
		this.encoding = encoding;
		this.config = config;
		this.fileCrawler = fileCrawler;
	}
	
	public void crawl(Directory directory) {
		try {
			crawlFiles(directory);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void crawlFiles(Directory directory) throws IOException {
		final IndexWriter indexWriter = new IndexWriter(directory,config);
		fileCrawler.crawl(new FileProcessor() {
			public void process(File file) {
				try {
					logger.info("Adding: " + getFilename(file));
					indexWriter.addDocument(document(file));
					logger.info("Added: " + getFilename(file));
				} catch (IOException e) {
					logger.warning("IOException adding: " + getFilename(file) + " to index.  File will not be added.");
					logger.warning(e.getStackTrace().toString());
				}
			}
		});
		indexWriter.close();
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
