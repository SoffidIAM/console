package com.soffid.iam.api;

import java.util.List;

public interface CrudHandler<E> {
	public E create (E object) throws Exception;
	
	public List<E> read (String text, String filter, Integer start, Integer maxobjects ) throws Exception ;
	
	public AsyncList<E> readAsync (String text, String filter ) throws Exception ;
	
	public E update (E object) throws Exception;
	
	public void delete (E object) throws Exception ;
}
