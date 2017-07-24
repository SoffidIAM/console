package com.soffid.iam.webservice;

public class SCIMResponse {
	final static String [] SCHEMAS = new String[]{"urn:ietf:params:scim:api:messages:2.0:Error"};
	
	String detail;
	String status;
	String[] schemas;
	
	public SCIMResponse(Exception e)
	{
		schemas = SCHEMAS;
		detail = e.toString();
		status = "500";
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
