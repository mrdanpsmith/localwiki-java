package com.siirush.localwiki.search;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopScoreDocCollector;
import org.apache.lucene.store.Directory;

public class LuceneFileSearcherImpl implements LuceneSearcher {
	private final Logger logger;
	
	@Inject
	public LuceneFileSearcherImpl(Logger logger) {
		this.logger = logger;
	}
	
	public List<String> search(Directory index, String query) {
		logger.info("Search query: " + query);
		try {
			IndexReader reader = DirectoryReader.open(index);
			IndexSearcher searcher = new IndexSearcher(reader);
			
			TopScoreDocCollector collector = TopScoreDocCollector.create(1000, true);
			
			if (!query.contains("*")) {
				query += "*";
			}
			
			Query q = MultiFieldQueryParser.parse(new String[] { query, query }, new String[] { "filename", "content" }, new SimpleAnalyzer());
			
			searcher.search(q, collector);

			ScoreDoc[] hits = collector.topDocs().scoreDocs;
			
			logger.info("Hits: " + collector.getTotalHits());
			
			List<String> results = new ArrayList<String>();
			
			for (ScoreDoc hit: hits) {
				Document d = searcher.doc(hit.doc);
				results.add(d.get("filename"));
			}
			return results;
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		return Collections.<String>emptyList();
	}
}
