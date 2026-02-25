//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service.ejb;
/**
 * EJB Home ApplicationShutdownService
 */
public interface ApplicationShutdownServiceHome
	extends jakarta.ejb.EJBLocalHome
 {
	/**
	 * The logical JDNI name
	 */
	public static final String COMP_NAME="java:comp/ejb/ApplicationShutdownService";

	/**
	 * The physical JDNI name
	 */
	public static final String JNDI_NAME="openejb:/local/soffid.ejb.com.soffid.iam.impl.service.ApplicationShutdownService";


}
