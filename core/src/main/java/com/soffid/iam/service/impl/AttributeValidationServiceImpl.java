package com.soffid.iam.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.soffid.iam.model.AccountMetadataEntity;
import com.soffid.iam.model.CustomObjectTypeEntity;
import com.soffid.iam.model.MetaDataEntity;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;

public class AttributeValidationServiceImpl extends AttributeValidationServiceBase {

	@Override
	protected void handleValidate(MetaDataEntity metadata, Object value)
			throws Exception {
		TypeEnumeration type = metadata.getType();
		CustomObjectTypeEntity customObjectType = metadata.getDataObjectType();
		final String values = metadata.getValues();
		validate(value, type, customObjectType, values);
	}

	@Override
	protected void handleValidate(AccountMetadataEntity metadata, Object value)
			throws Exception {
		TypeEnumeration type = metadata.getType();
		CustomObjectTypeEntity customObjectType = metadata.getDataObjectType();
		final String values = metadata.getValues();
		validate(value, type, customObjectType, values);
	}


	protected void validate(Object value, TypeEnumeration type, CustomObjectTypeEntity customObjectType,
			final String values) throws InternalErrorException, UnsupportedEncodingException {
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
		if (type == TypeEnumeration.USER_TYPE_TYPE)
		{
			if (getUserTypeEntityDao().findByName( value.toString()) == null)
				throw new InternalErrorException ( String.format(Messages.getString("AttributeValidationServiceImpl.7"), value)); //$NON-NLS-1$
		}
		if (type == TypeEnumeration.HOST_TYPE)
		{
			if (getHostEntityDao().findByName( value.toString()) == null)
				throw new InternalErrorException ( String.format(Messages.getString("AttributeValidationServiceImpl.8"), value)); //$NON-NLS-1$
		}
		if (type == TypeEnumeration.NETWORK_TYPE)
		{
			if (getNetworkEntityDao().findByName( value.toString()) == null)
				throw new InternalErrorException ( String.format(Messages.getString("AttributeValidationServiceImpl.9"), value)); //$NON-NLS-1$
		}
		if (values != null && !values.trim().isEmpty()) {
			boolean valid = false;
			for (String t: values.split(" ")) {
				String v = URLDecoder.decode(t, "UTF-8");
				if (v.contains(":"))
					v = v.substring(0, v.indexOf(":"));
				if (v.trim().equals(value.toString().trim())) {
					valid = true;
					break;
				}
			}
			if (!valid)
				throw new InternalErrorException ( String.format(Messages.getString("AttributeValidationServiceImpl.10"), value)); //$NON-NLS-1$
		}
	}

}
