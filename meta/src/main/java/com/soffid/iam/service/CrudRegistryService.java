package com.soffid.iam.service;

import com.soffid.iam.api.CrudHandler;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import roles.Tothom;

@Service(internal = false)
public class CrudRegistryService {
	public<E> void registerHandler (Class<E> cl, CrudHandler<E> handler) {}
	
	public void registerDefaultHandlers ( ) {}

	@Operation(grantees = {Tothom.class})
	public<E> CrudHandler<E> getHandler(Class<E> cl) {return null;}
}
