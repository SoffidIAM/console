package com.soffid.iam.webservice.user;

import java.util.Collection;

public class UserQuery {
	public String[] getSchemas() {
		return schemas;
	}
	public void setSchemas(String[] schemas) {
		this.schemas = schemas;
	}
	public int getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
	public Collection<ExtendedUser> getResources() {
		return resources;
	}
	public void setResources(Collection<ExtendedUser> resources) {
		this.resources = resources;
	}
	String [] schemas = new String [] {"urn:ietf:params:scim:api:messages:2.0:ListResponse"};
	int totalResults;
	Collection<ExtendedUser> resources;
}