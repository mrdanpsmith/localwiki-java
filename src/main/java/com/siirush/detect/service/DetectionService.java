package com.siirush.detect.service;

import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.siirush.detect.model.Feature;

@Path("/")
public interface DetectionService {	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	Set<Feature> getSupport();
}