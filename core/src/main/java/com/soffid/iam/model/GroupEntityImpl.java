// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import java.util.Collection;
import java.util.Iterator;

import org.hibernate.proxy.HibernateProxy;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.model.security.SecurityScopeEntity;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.spring.JCSCacheProvider;
import com.soffid.iam.utils.SoffidAuthorization;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * @see es.caib.seycon.ng.model.GrupEntity
 */
public class GroupEntityImpl extends com.soffid.iam.model.GroupEntity
	implements SecurityScopeEntity
{
	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = -6294585483589486558L;

	/**
	 * @see es.caib.seycon.ng.model.GrupEntity#toString()
	 */
	public java.lang.String toString() {
		// @todo implement public java.lang.String toString()
		return String.format(Messages.getString("GroupEntityImpl.toString"), getId(), getName(), getDescription(), getUnitType());
	}

	private boolean checkDeep (String permission, GroupEntity entity)
	{
		if (Security.isUserInRole(permission+"/"+entity.getName()))
			return true;
		for (GroupEntity child: entity.getChildren())
		{
			if (checkDeep(permission, child))
				return true;
		}
		return false;
		
	}
	
	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		if (Security.isUserInRole(permission+"/"+getName()))
			return true;
		
		return false;
	}

	@Override
	public void customCache() {
	}

}
