//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

import es.caib.seycon.ng.comu.TipusDada;

/**
 * DAO AccountMetadataEntity implementation
 */
public class AccountMetadataEntityDaoImpl extends AccountMetadataEntityDaoBase
{

	@Override
	public void toTipusDada(AccountMetadataEntity source, TipusDada target) {
		super.toTipusDada(source, target);
		
		target.setCodi(source.getName());
		target.setSystemName( source.getSystem().getName());
		target.setOrdre(source.getOrder());


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
	}

	@Override
	public void tipusDadaToEntity(TipusDada source,
			AccountMetadataEntity target, boolean copyIfNull) {
		super.tipusDadaToEntity(source, target, copyIfNull);

		target.setName(source.getCodi());
		target.setSystem( getSystemEntityDao().findByName(source.getSystemName()) );
		target.setOrder(source.getOrdre());

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
	}
}
