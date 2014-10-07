package com.siirush.localwiki.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
public interface LocalWikiService {
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	@Path("/version")
	String getVersion();
	
	@POST
	@Produces({MediaType.APPLICATION_JSON})
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Path("/file/{filename}")
	void save(@PathParam("filename") String filename, @FormParam("content") String content);
}
