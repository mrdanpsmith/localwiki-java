package com.siirush.localwiki.provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;

import com.google.gson.Gson;
import com.siirush.localwiki.binding.annotation.Encoding;
import com.siirush.localwiki.util.StringUtil;

@Singleton
@Provider
@Produces({MediaType.APPLICATION_JSON, "text/json"})
public class GsonMessageBodyWriter<T> implements MessageBodyWriter<T> {
	private final Gson gson;
	private final String encoding;
	private final StringUtil stringUtil;
	
	private static final String CONTENT_TYPE_HEADER = "Content-Type";
	private static final String CHARSET_PARAMETER_NAME = "charset=";
	
	@Inject
	private GsonMessageBodyWriter(Gson gson, @Encoding String encoding, StringUtil stringUtil) {
		this.gson = gson;
		this.encoding = encoding;
		this.stringUtil = stringUtil;
	}
	
	public long getSize(T t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return -1;
	}

	public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
		return true;
	}

	public void writeTo(T t, Class<?> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, Object> httpHeaders,
			OutputStream entityStream) throws IOException, WebApplicationException {
        httpHeaders.get(CONTENT_TYPE_HEADER).add(stringUtil.concatenate(CHARSET_PARAMETER_NAME,encoding));
        entityStream.write(gson.toJson(t).getBytes(encoding));
	}
}