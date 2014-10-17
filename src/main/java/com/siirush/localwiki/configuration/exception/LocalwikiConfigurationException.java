package com.siirush.localwiki.configuration.exception;

public class LocalwikiConfigurationException extends RuntimeException {
	private static final long serialVersionUID = 383967515054093893L;
	public LocalwikiConfigurationException() {}
	public LocalwikiConfigurationException(String message) {
		super(message);
	}
	public LocalwikiConfigurationException(Throwable cause) {
		super(cause);
	}
	public LocalwikiConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}
	public LocalwikiConfigurationException(String message, Throwable cause, boolean enableSuppression, boolean writeableStackTrace) {
		super(message, cause, enableSuppression, writeableStackTrace);
	}
}
