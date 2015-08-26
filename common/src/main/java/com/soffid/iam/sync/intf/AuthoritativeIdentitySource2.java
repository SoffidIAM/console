package com.soffid.iam.sync.intf;

import java.util.Collection;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * Enables authoritative data sources
 * 
 * @author Soffid
 *
 */
public interface AuthoritativeIdentitySource2
{
	/**
	 * Gets the pending changes
	 * 
	 * @return a list of changes to apply
	 * 
	 * @throws InternalErrorException
	 */
	public Collection<AuthoritativeChange> getChanges(String lastChange) throws InternalErrorException;
	
	/**
	 * Commits a change. It shouldn't be returned on getChanges from now on.
	 * 
	 * @param id change identifier
	 * 
	 * @throws InternalErrorException
	 */
	public boolean hasMoreData () throws InternalErrorException;

	/**
	 * Commits a change. It shouldn't be returned on getChanges from now on.
	 * 
	 * @param id change identifier
	 * 
	 * @throws InternalErrorException
	 */
	public String getNextChange () throws InternalErrorException;
}
