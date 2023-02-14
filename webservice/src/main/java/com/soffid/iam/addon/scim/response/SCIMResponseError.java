package com.soffid.iam.addon.scim.response;

import javax.ws.rs.core.Response.Status;

public class SCIMResponseError {

	final static String[] SCHEMAS = new String[] {"urn:ietf:params:scim:api:messages:2.0:Error"};
	String[] schemas = null;
	String detail = null;
	String status = null;

	public SCIMResponseError(String message) {
		schemas = SCHEMAS;
		detail = message;
		status = "500";
	}

	public SCIMResponseError(String message, Status status) {
		schemas = SCHEMAS;
		detail = message;
		this.status = String.valueOf(status.getStatusCode());
	}

	public String[] getSchemas() {
		return schemas;
	}

	public void setSchemas(String[] schemas) {
		this.schemas = schemas;
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
}
