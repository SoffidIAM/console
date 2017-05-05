//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.hibernate.Hibernate;

import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.Messages;

/**
 * DAO AccountAttributeEntity implementation
 */
public class ApplicationAttributeEntityDaoImpl extends ApplicationAttributeEntityDaoBase
{

    @Override
	public void create(ApplicationAttributeEntity entity) {
    	if (entity.getMetadata().getUnique() != null &&
    			entity.getMetadata().getUnique().booleanValue() )
    	{
			for (ApplicationAttributeEntity du: findByNameAndValue(entity.getMetadata().getCodi(), entity.getValue()))
			{
				throw new SeyconException(String.format(com.soffid.iam.model.Messages.getString("AccountAttributeEntityDaoImpl.2"), 
						du.getValue(), du.getInformationSystem().getCodi())); //$NON-NLS-1$
			}
    	}
		
		super.create(entity);
		if (Hibernate.isInitialized(entity.getInformationSystem().getAttributes()))
			entity.getInformationSystem().getAttributes().add(entity);
	}


	@Override
	public void remove(ApplicationAttributeEntity entity) {
		if (Hibernate.isInitialized(entity.getInformationSystem().getAttributes()))
			entity.getInformationSystem().getAttributes().remove(entity);
		super.remove(entity);
	}

	@Override
	public void update(ApplicationAttributeEntity entity) {
    	if (entity.getMetadata().getUnique() != null &&
    			entity.getMetadata().getUnique().booleanValue() )
    	{
			for (ApplicationAttributeEntity du: findByNameAndValue(entity.getMetadata().getCodi(), entity.getValue()) )
			{
				if (!du.getId().equals(entity.getId()))
					throw new SeyconException(String.format(com.soffid.iam.model.Messages.getString("AccountAttributeEntityDaoImpl.2"), 
							du.getValue(), du.getInformationSystem().getCodi())); //$NON-NLS-1$
			}
    	}
		super.update(entity);
	}
}
