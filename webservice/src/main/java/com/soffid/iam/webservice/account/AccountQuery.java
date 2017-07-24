package com.soffid.iam.webservice.account;

import java.util.Collection;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement()
public class AccountQuery {
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
	public Collection<ExtendedAccount> getResources() {
		return resources;
	}
	public void setResources(Collection<ExtendedAccount> resources) {
		this.resources = resources;
	}
	String [] schemas = new String [] {"urn:ietf:params:scim:api:messages:2.0:ListResponse"};
	int totalResults;
	Collection<ExtendedAccount> resources;
}