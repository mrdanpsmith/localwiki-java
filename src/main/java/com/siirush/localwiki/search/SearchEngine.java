package com.siirush.localwiki.search;

import java.util.List;

public interface SearchEngine {
	List<String> search(String query);
	void index();
}
