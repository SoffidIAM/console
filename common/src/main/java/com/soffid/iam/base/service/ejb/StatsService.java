//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB StatsService
 */
public interface StatsService

 {

	com.soffid.iam.base.api.Stats findStats(
		final java.lang.String name, 
		final java.util.Date since, 
		final java.util.Date until, 
		final int step)
	throws com.soffid.iam.exception.InternalErrorException;

}
