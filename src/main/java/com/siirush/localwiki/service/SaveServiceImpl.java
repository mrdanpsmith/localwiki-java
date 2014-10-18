package com.siirush.localwiki.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.inject.Inject;

import com.siirush.detect.annotation.Support;
import com.siirush.localwiki.binding.annotation.Encoding;
import com.siirush.localwiki.configuration.LocalwikiConfiguration;

public class SaveServiceImpl implements SaveService {
	private final String encoding;
	private final LocalwikiConfiguration config;
	
	@Inject
	public SaveServiceImpl(@Encoding String encoding, LocalwikiConfiguration config) {
		this.encoding = encoding;
		this.config = config;
	}
	
	@Support(version = "0.1")
	public void save(String filename, String content) {
		try {
			securityCheck(filename);
			Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), encoding));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	void securityCheck(String filename) throws IOException {
		File file = new File(filename);
		if (isAboveDir(file,config.getPath().getBaseDir())) {
			throw new SecurityException();
		}
		if (isInDirectory(file,config.getPath().getErrorDir())) {
			throw new SecurityException();
		}
	}

	private boolean isAboveDir(File file, String dir) throws IOException {
		File path = new File(dir);
		return !file.getCanonicalPath().startsWith(path.getCanonicalPath());
	}
	
	private boolean isInDirectory(File file, String dir) throws IOException {
		File path = new File(dir);
		return file.getCanonicalPath().startsWith(withTrailingPathDelimiter(path.getCanonicalPath()));
	}

	private String withTrailingPathDelimiter(String path) {
		return path.endsWith(File.separator) ? path : addTrailingPathDelimiter(path);
	}
	
	private String addTrailingPathDelimiter(String path) {
		StringBuilder sb = new StringBuilder();
		sb.append(path);
		sb.append(File.separator);
		return sb.toString();
	}
}
