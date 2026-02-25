//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB Home ApplicationService
 */
public interface ApplicationServiceHome
	extends jakarta.ejb.EJBLocalHome
 {
	/**
	 * The logical JDNI name
	 */
	public static final String COMP_NAME="java:comp/ejb/ApplicationService";

	/**
	 * The physical JDNI name
	 */
	public static final String JNDI_NAME="openejb:/local/soffid.ejb.com.soffid.iam.iga.service.ApplicationService";


}
