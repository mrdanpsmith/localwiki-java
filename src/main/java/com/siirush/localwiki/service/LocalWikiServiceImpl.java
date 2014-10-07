package com.siirush.localwiki.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.inject.Inject;

import com.siirush.localwiki.binding.annotation.Encoding;

public class LocalWikiServiceImpl implements LocalWikiService {
	private final String encoding;
	
	@Inject
	public LocalWikiServiceImpl(@Encoding String encoding) {
		this.encoding = encoding;
	}
	
	public String getVersion() {
		return "1.0";
	}
	
	public void save(String filename, String content) {
		System.out.println(filename);
		System.out.println(content);
		Writer writer;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), encoding));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
