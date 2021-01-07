package com.soffid.iam.api;

import java.util.Collection;
import java.util.List;

public abstract class PagedResult<E> implements Collection<E> {
	private static final long serialVersionUID = 1L;
	Integer startIndex;
	Integer itemsPerPage;
	Integer totalResults;
	List resources;
}
