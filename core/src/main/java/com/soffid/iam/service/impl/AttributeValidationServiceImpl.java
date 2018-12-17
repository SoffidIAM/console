package com.soffid.iam.service.impl;

import com.soffid.iam.model.CustomObjectTypeEntity;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;

public class AttributeValidationServiceImpl extends AttributeValidationServiceBase {

	@Override
	protected void handleValidate(TypeEnumeration type, CustomObjectTypeEntity customObjectType, Object value)
			throws Exception {
		if (value == null || value.equals("")) //$NON-NLS-1$
			return;
		if (type == TypeEnumeration.APPLICATION_TYPE)
		{
			if (getInformationSystemEntityDao().findByCode(value.toString()) == null)
				throw new InternalErrorException ( String.format(Messages.getString("AttributeValidationServiceImpl.1"), value)); //$NON-NLS-1$
		}
		if (type == TypeEnumeration.CUSTOM_OBJECT_TYPE && customObjectType != null)
		{
			if (getCustomObjectEntityDao().findByTypeAndName( customObjectType.getName(), value.toString()) == null)
				throw new InternalErrorException ( String.format(Messages.getString("AttributeValidationServiceImpl.2"), customObjectType.getDescription(), value)); //$NON-NLS-1$
		}
		if (type == TypeEnumeration.EMAIL_TYPE)
		{
			String s = value.toString();
			if ( ! s.contains("@")) //$NON-NLS-1$
				throw new InternalErrorException ( String.format(Messages.getString("AttributeValidationServiceImpl.4"), value)); //$NON-NLS-1$
		}
		if (type == TypeEnumeration.GROUP_TYPE)
		{
			if (getGroupEntityDao().findByName( value.toString()) == null)
				throw new InternalErrorException ( String.format(Messages.getString("AttributeValidationServiceImpl.5"), value)); //$NON-NLS-1$
		}
		if (type == TypeEnumeration.USER_TYPE)
		{
			if (getUserEntityDao().findByUserName( value.toString()) == null)
				throw new InternalErrorException ( String.format(Messages.getString("AttributeValidationServiceImpl.6"), value)); //$NON-NLS-1$
		}
	}

}
