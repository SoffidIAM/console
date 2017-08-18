package com.soffid.iam.webservice.group;

import java.util.Collection;

public class GroupQuery {

	String[] schemas = new String[] { "urn:ietf:params:scim:api:messages:2.0:ListResponse" };
	int totalResults;
	Collection<ExtendedGroup> resources;

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

	public Collection<ExtendedGroup> getResources() {
		return resources;
	}

	public void setResources(Collection<ExtendedGroup> resources) {
		this.resources = resources;
	}
}
