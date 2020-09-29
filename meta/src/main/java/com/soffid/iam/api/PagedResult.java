package com.soffid.iam.api;

import java.util.Collection;
import java.util.List;

public abstract class PagedResult implements Collection {
	private static final long serialVersionUID = 1L;
	Integer startIndex;
	Integer itemsPerPage;
	Integer totalResults;
	List resources;
}
