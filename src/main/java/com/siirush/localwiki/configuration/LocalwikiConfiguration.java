package com.siirush.localwiki.configuration;

public class LocalwikiConfiguration {
	private Path path;
	private Server server;
	
	public Path getPath() {
		return path;
	}
	public void setPath(Path path) {
		this.path = path;
	}
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}
	
	public static class Path {
		private String baseDir;
		private String apiDir;
		
		public String getBaseDir() {
			return baseDir;
		}
		public void setBaseDir(String baseDir) {
			this.baseDir = baseDir;
		}
		public String getApiDir() {
			return apiDir;
		}
		public void setApiDir(String apiDir) {
			this.apiDir = apiDir;
		}
	}
	
	public static class Server {
		private Integer port;

		public Integer getPort() {
			return port;
		}
		public void setPort(Integer port) {
			this.port = port;
		}
	}
}