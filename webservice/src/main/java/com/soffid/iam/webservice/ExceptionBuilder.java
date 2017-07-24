package com.soffid.iam.webservice;

import javax.ws.rs.core.Response;

public class ExceptionBuilder {

	private Exception e;

	public ExceptionBuilder(Exception e) {
		this.e = e;
	}
	
	public Response build ()
	{
		return Response.serverError().entity(new SCIMResponse(e)).build();
		
	}

}
