/**
 * 
 */
/**
 * 
 */
package com.soffid.iam.service;

import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.UserEntity;

/**
 * @author bubu
 *
 */
public class SoffidEventListenerImpl extends SoffidEventListenerBase
{

	/**
	 * 
	 */
	public SoffidEventListenerImpl ()
	{
	}

	/* (non-Javadoc)
	 * @see com.soffid.iam.service.SoffidEventListenerBase#handleOnUserChange(es.caib.seycon.ng.model.UsuariEntity)
	 */
	@Override
    protected void handleOnUserChange(UserEntity user) throws Exception {

	}

	@Override
	protected void handleOnGrant(RoleAccountEntity grant) throws Exception {
		
	}

	@Override
	protected void handleOnRevoke(RoleAccountEntity grant) throws Exception {
	}

}
