//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * DAO SAMLRequestEntity implementation
 */
public class SamlRequestEntityDaoImpl extends SamlRequestEntityDaoBase
{
	@Override
	protected void handleDeleteExpired() throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.HOUR, -1);
		for ( SamlRequestEntity as: findExpired(c.getTime()))
		{
			remove(as);
		}
	}
}
