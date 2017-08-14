package com.soffid.iam.webservice;

import javax.ws.rs.core.Response.Status;

public class SCIMResponse {
	final static String [] SCHEMAS = new String[]{"urn:ietf:params:scim:api:messages:2.0:Error"};
	
	String detail;
	String status;
	String[] schemas;
	
	public SCIMResponse(String message)
	{
		schemas = SCHEMAS;
		detail = message;
		status = "500";
	}

	public SCIMResponse(String message, Status status)
	{
		schemas = SCHEMAS;
		detail = message;
		this.status = String.valueOf(status.getStatusCode());
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String[] getSchemas() {
		return schemas;
	}

	public void setSchemas(String[] schemas) {
		this.schemas = schemas;
	}
}
