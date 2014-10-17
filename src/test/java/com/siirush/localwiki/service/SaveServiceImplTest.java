package com.siirush.localwiki.service;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import com.siirush.localwiki.configuration.LocalwikiConfiguration;

public class SaveServiceImplTest {
	LocalwikiConfiguration config = new LocalwikiConfiguration();
	SaveServiceImpl target = new SaveServiceImpl(null,config);
	
	@Test
	public void testSecurityCheck() throws IOException {
		config.getPath().setBaseDir(".");
		config.getPath().setErrorDir("error");
		
		boolean thrown = false;
		try {
			target.securityCheck("../should_not_pass.md");
		} catch (SecurityException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			target.securityCheck("error/hi.md");
		} catch (SecurityException e) {
			thrown = true;
		}
		assertTrue(thrown);
		
		thrown = false;
		try {
			target.securityCheck("error_it_just_a_little_bit.md");
		} catch (SecurityException e) {
			thrown = true;
		}
		assertFalse(thrown);
		
		thrown = false;
		try {
			target.securityCheck("subdir/something.md");
		} catch (SecurityException e) {
			thrown = true;
		}
		assertFalse(thrown);
	}
}
