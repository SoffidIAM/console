package com.soffid.iam.api;

import java.util.List;

import com.soffid.iam.api.AsyncList;
import com.soffid.mda.annotation.Operation;

import roles.Tothom;

public class CrudHandler<E> {
	@Operation(grantees = {Tothom.class})
	public E create (E object) {return null;}
	
	@Operation(grantees = {Tothom.class})
	public PagedResult<E> read (String filter, Integer start, Integer end ) {return null;}
	
	@Operation(grantees = {Tothom.class})
	public List<E> read (String filter ) {return null;}
	
	@Operation(grantees = {Tothom.class})
	public AsyncList<E> readAsync (String filter ) {return null;}
	
	@Operation(grantees = {Tothom.class})
	public E update (E object) {return null;}
	
	@Operation(grantees = {Tothom.class})
	public void delete (E object) {}

}
