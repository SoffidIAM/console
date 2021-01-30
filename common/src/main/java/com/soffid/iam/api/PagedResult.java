package com.soffid.iam.api;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import es.caib.seycon.ng.comu.ControlAcces;

public class PagedResult<E>  {
	private static final long serialVersionUID = 1L;
	Integer startIndex;
	Integer itemsPerPage;
	Integer totalResults;
	List<E> resources;
	
	public PagedResult() {
		
	}
	
	public PagedResult(PagedResult<?> source, List<E> resources) {
		this.startIndex = source.startIndex;
		this.itemsPerPage = source.itemsPerPage;
		this.totalResults = source.totalResults;
		this.resources = resources;
	}
	
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
	public List<E> getResources() {
		return resources;
	}
	public void setResources(List<E> resources) {
		this.resources = resources;
	}
}
