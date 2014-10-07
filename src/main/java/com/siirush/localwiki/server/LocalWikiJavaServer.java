package com.siirush.localwiki.server;

import java.util.EnumSet;

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
import com.siirush.localwiki.binding.annotation.Port;
import com.siirush.localwiki.module.MainModule;

public class LocalWikiJavaServer {
	private final Server server;
	
	public static void main(String[] args) throws Exception {
		Injector injector = Guice.createInjector(new MainModule());
		injector.getInstance(LocalWikiJavaServer.class).start();
	}
	
	@Inject
	private LocalWikiJavaServer(@Port Integer portNumber, GuiceFilter guiceFilter) {
		server = new Server(portNumber);
		ServletContextHandler guiceHandler = new ServletContextHandler();
		
		FilterHolder guiceFilterHolder = new FilterHolder(guiceFilter);
		guiceHandler.setContextPath("/api");
		guiceHandler.addFilter(guiceFilterHolder, "/*", EnumSet.allOf(DispatcherType.class));
		
		ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setDirectoriesListed(true);
        resourceHandler.setWelcomeFiles(new String[]{ "index.html" });
 
        resourceHandler.setResourceBase(".");
        
        System.out.println(resourceHandler.getResourceBase());
 
        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[] { guiceHandler, resourceHandler, new DefaultHandler() });
        server.setHandler(handlers);
	}

	private void start() throws Exception {
		server.start();
		server.join();
	}
	
	private void stop() throws Exception {
		server.stop();
	}
}
