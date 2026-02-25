//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB Home PreferencesService
 * Manages user preferences
 */
public interface PreferencesServiceHome
	extends jakarta.ejb.EJBLocalHome
 {
	/**
	 * The logical JDNI name
	 */
	public static final String COMP_NAME="java:comp/ejb/PreferencesService";

	/**
	 * The physical JDNI name
	 */
	public static final String JNDI_NAME="openejb:/local/soffid.ejb.com.soffid.iam.base.service.PreferencesService";


}
