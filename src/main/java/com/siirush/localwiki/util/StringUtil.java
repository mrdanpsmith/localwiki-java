package com.siirush.localwiki.util;

import java.io.InputStream;

public interface StringUtil {
	String concatenate(String ... strings);
	String inputStreamToString(InputStream inputStream, String encoding);
}
