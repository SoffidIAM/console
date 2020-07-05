package com.soffid.iam.api;

import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface CrudHandler<E> {
	public E create (E object) throws InternalErrorException, NamingException, CreateException;
	
	public List<E> read (String text, String filter, Integer start, Integer maxobjects ) throws InternalErrorException, NamingException, CreateException ;
	
	public AsyncList<E> readAsync (String text, String filter ) throws InternalErrorException, NamingException, CreateException ;
	
	public E update (E object) throws InternalErrorException, NamingException, CreateException;
	
	public void delete (E object) throws InternalErrorException, NamingException, CreateException ;
}
