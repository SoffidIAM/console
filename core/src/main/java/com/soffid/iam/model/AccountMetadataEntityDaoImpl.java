//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.api.DataType;

import es.caib.seycon.ng.comu.TypeEnumeration;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

/**
 * DAO AccountMetadataEntity implementation
 */
public class AccountMetadataEntityDaoImpl extends AccountMetadataEntityDaoBase
{

	@Override
    public void toDataType(AccountMetadataEntity source, DataType target) {
		super.toDataType(source, target);
		
		target.setCode(source.getName());
		target.setSystemName( source.getSystem().getName());
		target.setOrder(source.getOrder());


		if (source.getLabel() == null)
			target.setLabel(source.getName());
		if (source.getValues() == null || source.getValues().length() == 0)
			target.setValues ( new LinkedList<String>() );
		else
		{
			List<String> values = new LinkedList<String>();
			for (String s: source.getValues().split(" "))
			{
				try
				{
					values.add (URLDecoder.decode(s, "UTF-8"));
				}
				catch (UnsupportedEncodingException e)
				{
					throw new RuntimeException (e);
				}
			}
			target.setValues(values);
		}
		if (source.getDataObjectType() != null)
			target.setDataObjectType(source.getDataObjectType().getName());
	}

	@Override
    public void dataTypeToEntity(DataType source, AccountMetadataEntity target, boolean copyIfNull) {
		super.dataTypeToEntity(source, target, copyIfNull);

		target.setName(source.getCode());
		target.setSystem( getSystemEntityDao().findByName(source.getSystemName()) );
		target.setOrder(source.getOrder());

		if (source.getValues() == null || source.getValues().isEmpty())
			target.setValues(null);
		else
		{
			StringBuffer b = new StringBuffer();
			for (String s: source.getValues())
			{
				if (b.length() > 0)
					b.append (" ");
				try
				{
					b.append (URLEncoder.encode(s, "UTF-8"));
				}
				catch (UnsupportedEncodingException e)
				{
					throw new RuntimeException (e);
				}
			}
			target.setValues(b.toString());
		}
		if (source.getType() == TypeEnumeration.CUSTOM_OBJECT_TYPE)
		{
			CustomObjectTypeEntity cot = getCustomObjectTypeEntityDao().findByName(source.getDataObjectType());
			if (cot == null)
				throw new RuntimeException("Invalid custom object type "+source.getCustomObjectType());
			target.setDataObjectType(cot);
		}
	}
}
