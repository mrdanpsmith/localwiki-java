package com.siirush.localwiki.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class StringUtilImpl implements StringUtil {
	private static final String EMPTY_STRING = "";
	
	public String concatenate(String... strings) {
		StringBuilder sb = new StringBuilder();
		for (String s: strings) {
			sb.append(s);
		}
		return sb.toString();
	}
	
	public String inputStreamToString(InputStream inputStream, String encoding) {
		if (inputStream == null) {
			return EMPTY_STRING;
		}
		RuntimeException thrown = null;
		Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, encoding));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch (UnsupportedEncodingException e) {
			thrown = new RuntimeException(e);
		} catch (IOException e) {
			thrown = new RuntimeException(e);
		} finally {
            try {
				inputStream.close();
			} catch (IOException e) {
				thrown = new RuntimeException(e);
			}
        }
        if (thrown != null) {
        	throw thrown;
        }
        return writer.toString();
	}
}
