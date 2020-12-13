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
	 * @param lastChange the last succesful change loaded
	 * 
	 * @return a list of changes to apply
	 * 
	 * @throws InternalErrorException
	 */
	public Collection<AuthoritativeChange> getChanges(String lastChange) throws InternalErrorException;
	
	/**
	 * Check if there is more data to load
	 * 
	 * @return true if there is more data to load
	 * @throws InternalErrorException
	 */
	public boolean hasMoreData () throws InternalErrorException;

	/**
	 * Gets the next bunch of changes
	 * 
	 * @return a list of changes
	 * @throws InternalErrorException
	 */
	public String getNextChange () throws InternalErrorException;
}
