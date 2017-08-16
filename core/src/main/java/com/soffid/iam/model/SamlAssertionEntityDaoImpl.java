//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * DAO SAMLAssertionEntity implementation
 */
public class SamlAssertionEntityDaoImpl extends SamlAssertionEntityDaoBase
{

	@Override
	protected void handleDeleteExpired() throws Exception {
		Calendar c = new GregorianCalendar();
		c.add(Calendar.HOUR, -1);
		for ( SamlAssertionEntity as: findExpired(c.getTime()))
		{
			remove(as);
		}
	}
}
