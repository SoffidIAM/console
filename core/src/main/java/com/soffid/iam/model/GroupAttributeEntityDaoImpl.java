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
public class GroupAttributeEntityDaoImpl extends GroupAttributeEntityDaoBase
{

	private static final String DATE_FORMAT = "yyyy.MM.dd HH.mm.ss"; //$NON-NLS-1$

    @Override
	public void create(GroupAttributeEntity entity) {
    	if (entity.getMetadata().getUnique() != null &&
    			entity.getMetadata().getUnique().booleanValue() )
    	{
			for (GroupAttributeEntity du: findByNameAndValue(entity.getMetadata().getCodi(), entity.getValue()))
			{
				throw new SeyconException(String.format(com.soffid.iam.model.Messages.getString("AccountAttributeEntityDaoImpl.2"), 
						du.getValue(), du.getGroup().getCodi())); //$NON-NLS-1$
			}
    	}
		
		super.create(entity);
		if (Hibernate.isInitialized(entity.getGroup().getAttributes()))
			entity.getGroup().getAttributes().add(entity);
	}


	@Override
	public void remove(GroupAttributeEntity entity) {
		if (Hibernate.isInitialized(entity.getGroup().getAttributes()))
			entity.getGroup().getAttributes().remove(entity);
		super.remove(entity);
	}

	@Override
	public void update(GroupAttributeEntity entity) {
    	if (entity.getMetadata().getUnique() != null &&
    			entity.getMetadata().getUnique().booleanValue() )
    	{
			for (GroupAttributeEntity du: findByNameAndValue(entity.getMetadata().getCodi(), entity.getValue()) )
			{
				if (!du.getId().equals(entity.getId()))
					throw new SeyconException(String.format(com.soffid.iam.model.Messages.getString("AccountAttributeEntityDaoImpl.2"), 
							du.getValue(), du.getGroup().getCodi())); //$NON-NLS-1$
			}
    	}
		super.update(entity);
	}
}
