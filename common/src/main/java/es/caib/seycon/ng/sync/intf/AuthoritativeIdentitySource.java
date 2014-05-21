package es.caib.seycon.ng.sync.intf;

import java.util.Collection;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * Enables authoritative data sources
 * 
 * @author Soffid
 *
 */
public interface AuthoritativeIdentitySource
{
	/**
	 * Gets the pending changes
	 * 
	 * @return a list of changes to apply
	 * 
	 * @throws InternalErrorException
	 */
	public Collection<AuthoritativeChange> getChanges() throws InternalErrorException;
	
	/**
	 * Commits a change. It shouldn't be returned on getChanges from now on.
	 * 
	 * @param id change identifier
	 * 
	 * @throws InternalErrorException
	 */
	public void commitChange (AuthoritativeChangeIdentifier id) throws InternalErrorException;
}
