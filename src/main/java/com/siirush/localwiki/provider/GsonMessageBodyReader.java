package com.siirush.localwiki.provider;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.siirush.localwiki.binding.annotation.Encoding;
import com.siirush.localwiki.util.StringUtil;

@Singleton
@Provider
@Consumes({MediaType.APPLICATION_JSON, "text/json"})
public class GsonMessageBodyReader<T> implements MessageBodyReader<Object> {
	private final StringUtil stringUtil;
	private final Gson gson;
	private final String encoding;
	
	@Inject
	private GsonMessageBodyReader(StringUtil stringUtil, Gson gson, @Encoding String encoding) {
		this.stringUtil = stringUtil;
		this.gson = gson;
		this.encoding = encoding;
	}
	
	public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}
	
	public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
			InputStream in) throws IOException, WebApplicationException {
        return gson.fromJson(stringUtil.inputStreamToString(in, encoding), type);
	}
}
