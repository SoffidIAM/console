package com.soffid.iam.init.rest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.init.Configuration;

@Path("/rest")
@Produces({"application/json"})
@Consumes({"application/json"})
public class ConfigureRest {
	Log log = LogFactory.getLog(getClass());
	
	@Path("/configure")
	@POST
	public Response configure ( ConfigureRequest req) throws FileNotFoundException, IOException {
		ConfigureResponse r = new ConfigureResponse();
		try {
			Configuration cfg = Configuration.getConfiguration();
			cfg.configureHostName(req.getHostname());
			cfg.configureDatabase(req.getUsername(), req.getPassword(), req.getDriver(), req.getUrl());
			r.success = true;
			r.createUser = ! cfg.isUserAlreadyExist();
			return Response.ok(r).build();
		} catch (Exception e) {
			log.warn("Error configuring sytem", e);
			r.success = false;
			r.message = e.getMessage();
			return Response.ok(r).build();
		}
	}

	@Path("/configure2")
	@POST
	public Response configure2 ( ConfigureRequest req) throws FileNotFoundException, IOException {
		ConfigureResponse r = new ConfigureResponse();
		try {
			Configuration cfg = Configuration.getConfiguration();
			if ( ! cfg.isUserAlreadyExist()) 
				cfg.configureAdmin(req.getAdminName(), req.getFirstName(), req.getLastName(), req.getAdminPassword());
			cfg.saveProperties();
			cfg.restartConsole();
			r.success = true;
			return Response.ok(r).build();
		} catch (Exception e) {
			log.warn("Error configuring sytem", e);
			r.success = false;
			r.message = e.getMessage();
			return Response.ok(r).build();
		}
	}
}