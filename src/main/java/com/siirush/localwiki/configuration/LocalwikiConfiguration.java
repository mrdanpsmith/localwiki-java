package com.siirush.localwiki.configuration;

public class LocalwikiConfiguration {
	private Path path = new Path();
	private Server server = new Server();
	
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
		private String errorDir;
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
		public String getErrorDir() {
			return errorDir;
		}
		public void setErrorDir(String errorDir) {
			this.errorDir = errorDir;
		}
	}
	
	public static class Server {
		private SearchIndex searchIndex = new SearchIndex();
		private Integer port;

		public Integer getPort() {
			return port;
		}
		public void setPort(Integer port) {
			this.port = port;
		}
		public SearchIndex getSearchIndex() {
			return searchIndex;
		}
		public void setSearchIndex(SearchIndex searchIndex) {
			this.searchIndex = searchIndex;
		}

		public static class SearchIndex {
			private String filePattern;
			private Integer maxDepth;
			
			public String getFilePattern() {
				return filePattern;
			}
			public void setFilePattern(String filePattern) {
				this.filePattern = filePattern;
			}
			public Integer getMaxDepth() {
				return maxDepth;
			}
			public void setMaxDepth(Integer maxDepth) {
				this.maxDepth = maxDepth;
			}
		}
	}
}