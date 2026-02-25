//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.service.ejb;
/**
 * EJB Home NetworkIntelligenceService
 */
public interface NetworkIntelligenceServiceHome
	extends jakarta.ejb.EJBLocalHome
 {
	/**
	 * The logical JDNI name
	 */
	public static final String COMP_NAME="java:comp/ejb/NetworkIntelligenceService";

	/**
	 * The physical JDNI name
	 */
	public static final String JNDI_NAME="openejb:/local/soffid.ejb.com.soffid.iam.rc.service.NetworkIntelligenceService";


}
