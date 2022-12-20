package com.soffid.iam.addon.scim.response;

import java.util.Collection;

import com.soffid.iam.addon.scim.util.PaginationUtil;
import com.soffid.iam.api.PagedResult;

public class SCIMResponseList {

	static final String[] SCHEMAS = new String[] {"urn:ietf:params:scim:api:messages:2.0:ListResponse"};
	int totalResults = 0;
	Collection<Object> resources = null;

	int itemsPerPage = 0;
	int startIndex = 0;

	public SCIMResponseList(Collection<Object> list) {
		this.resources = list;
		this.totalResults = this.resources.size();
		this.itemsPerPage = this.resources.size();
		this.startIndex = 1;
	}

	public SCIMResponseList(Collection<Object> list, PaginationUtil p) {
		if (p.isActive()) {
			this.resources = list;
			this.totalResults = p.getTotalResults();
			this.itemsPerPage = p.getItemsPerPage();
			this.startIndex = p.getStartIndex();
		} else {
			this.resources = list;
			this.totalResults = this.resources.size();
			this.itemsPerPage = this.resources.size();
			this.startIndex = 1;
		}
	}

	public SCIMResponseList(Collection<Object> list, PagedResult p) {
		this.resources = list;
		this.totalResults = p.getTotalResults();
		this.itemsPerPage = list.size();
		this.startIndex = p.getStartIndex() + 1;
	}

	public String[] getSchemas() {
		return SCHEMAS;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public Collection<Object> getResources() {
		return resources;
	}

	public void setResources(Collection<Object> resources) {
		this.resources = resources;
	}

	public int getItemsPerPage() {
		return itemsPerPage;
	}

	public void setItemsPerPage(int itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}

	public int getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
}