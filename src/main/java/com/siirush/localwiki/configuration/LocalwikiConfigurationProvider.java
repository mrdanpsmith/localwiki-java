package com.siirush.localwiki.configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.inject.Inject;
import javax.inject.Provider;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.siirush.localwiki.configuration.binding.annotation.ConfigurationFilePath;
import com.siirush.localwiki.configuration.exception.LocalwikiConfigurationException;

public class LocalwikiConfigurationProvider implements Provider<LocalwikiConfiguration> {
	private final String configPath;
	private final Gson gson;
	
	@Inject
	public LocalwikiConfigurationProvider(@ConfigurationFilePath String configPath, Gson gson) {
		this.configPath = configPath;
		this.gson = gson;
	}
	
	public LocalwikiConfiguration get() {
		try {
			return gson.fromJson(new FileReader(new File(configPath)), LocalwikiConfiguration.class);
		} catch (JsonSyntaxException e) {
			throw new LocalwikiConfigurationException(e);
		} catch (JsonIOException e) {
			throw new LocalwikiConfigurationException(e);
		} catch (FileNotFoundException e) {
			throw new LocalwikiConfigurationException(e);
		}
	}
}