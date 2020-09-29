package com.soffid.iam.api;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PagedResult<E>  {
	private static final long serialVersionUID = 1L;
	Integer startIndex;
	Integer itemsPerPage;
	Integer totalResults;
	Collection<E> resources;
	public Integer getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(Integer startIndex) {
		this.startIndex = startIndex;
	}
	public Integer getItemsPerPage() {
		return itemsPerPage;
	}
	public void setItemsPerPage(Integer itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public Integer getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Integer totalResults) {
		this.totalResults = totalResults;
	}
	public Collection<E> getResources() {
		return resources;
	}
	public void setResources(Collection<E> resources) {
		this.resources = resources;
	}
}
