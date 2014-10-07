package com.siirush.localwiki.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import com.siirush.localwiki.binding.annotation.Encoding;
import com.siirush.localwiki.search.SearchEngine;

public class LocalWikiServiceImpl implements LocalWikiService {
	private final String encoding;
	private final SearchEngine searchEngine;
	
	@Inject
	public LocalWikiServiceImpl(@Encoding String encoding, SearchEngine searchEngine) {
		this.encoding = encoding;
		this.searchEngine = searchEngine;
	}
	
	public List<String> getSupport() {
		return Arrays.asList(new String[] { "save", "search" });
	}
	
	public void save(String filename, String content) {
		Writer writer;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), encoding));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public List<String> search(String query) {
		return searchEngine.search(query);
	}
}
