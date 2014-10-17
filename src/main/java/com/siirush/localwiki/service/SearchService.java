package com.siirush.localwiki.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("/search")
public interface SearchService {		
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	List<String> search(@QueryParam("q") String query);
}
