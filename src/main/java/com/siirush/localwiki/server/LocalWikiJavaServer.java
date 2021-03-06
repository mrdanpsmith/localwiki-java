package com.siirush.localwiki.server;

import java.util.EnumSet;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.servlet.DispatcherType;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceFilter;
import com.siirush.localwiki.configuration.LocalwikiConfiguration;
import com.siirush.localwiki.module.MainModule;

public class LocalWikiJavaServer {
	private static final String ROOT_PATH = "/";
	
	private final Logger logger;
	private final LocalwikiConfiguration configuration;
	private final GuiceFilter guiceFilter;
	
	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new MainModule());
		injector.getInstance(LocalWikiJavaServer.class).start();
	}
	
	@Inject
	private LocalWikiJavaServer(Logger logger, LocalwikiConfiguration configuration, GuiceFilter guiceFilter) {
		this.logger = logger;
		this.configuration = configuration;
		this.guiceFilter = guiceFilter;
	}

	private String getContextPath(String apiDir) {
		StringBuilder sb = new StringBuilder();
		sb.append(ROOT_PATH);
		sb.append(apiDir);
		return sb.toString();
	}

	private void start() throws Exception {
		Server server = new Server(configuration.getServer().getPort());
		ServletContextHandler guiceHandler = new ServletContextHandler();
		
		FilterHolder guiceFilterHolder = new FilterHolder(guiceFilter);
		guiceHandler.setContextPath(getContextPath(configuration.getPath().getApiDir()));
		guiceHandler.addFilter(guiceFilterHolder, "/*", EnumSet.allOf(DispatcherType.class));
		
		ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(false);
        resourceHandler.setWelcomeFiles(new String[]{ "index.html" });
        
        resourceHandler.setResourceBase(configuration.getPath().getBaseDir());
        
        logger.info("Base directory set to: " + resourceHandler.getResourceBase());
 
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { guiceHandler, resourceHandler, new DefaultHandler() });
        server.setHandler(handlers);
        
		server.start();
		server.join();
	}
}
