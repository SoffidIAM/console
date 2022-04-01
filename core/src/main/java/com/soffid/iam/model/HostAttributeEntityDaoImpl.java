//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import org.hibernate.Hibernate;

import es.caib.seycon.ng.exception.SeyconException;

/**
 * DAO AccountAttributeEntity implementation
 */
public class HostAttributeEntityDaoImpl extends HostAttributeEntityDaoBase
{

    @Override
	public void create(HostAttributeEntity entity) {
    	if (entity.getMetadata().getUnique() != null &&
    			entity.getMetadata().getUnique().booleanValue() )
    	{
			for (HostAttributeEntity du: findByNameAndValue(entity.getMetadata().getName(), entity.getValue()))
			{
				throw new SeyconException(String.format(com.soffid.iam.model.Messages.getString("AccountAttributeEntityDaoImpl.2"), 
						du.getValue(), du.getHost().getName())); //$NON-NLS-1$
			}
    	}
		
		super.create(entity);
		if (Hibernate.isInitialized(entity.getHost().getAttributes()))
			entity.getHost().getAttributes().add(entity);
	}


	@Override
	public void remove(HostAttributeEntity entity) {
		if (Hibernate.isInitialized(entity.getHost().getAttributes()))
			entity.getHost().getAttributes().remove(entity);
		super.remove(entity);
	}

	@Override
	public void update(HostAttributeEntity entity) {
    	if (entity.getMetadata().getUnique() != null &&
    			entity.getMetadata().getUnique().booleanValue() )
    	{
			for (HostAttributeEntity du: findByNameAndValue(entity.getMetadata().getName(), entity.getValue()) )
			{
				if (!du.getId().equals(entity.getId()))
					throw new SeyconException(String.format(com.soffid.iam.model.Messages.getString("AccountAttributeEntityDaoImpl.2"), 
							du.getValue(), du.getHost().getName())); //$NON-NLS-1$
			}
    	}
		super.update(entity);
	}
}
