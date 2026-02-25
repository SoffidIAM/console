//
// (C) 2013 Soffid
//
//

package com.soffid.iam.bpm.service.ejb;
/**
 * EJB Home BpmConfigService
 */
public interface BpmConfigServiceHome
	extends jakarta.ejb.EJBLocalHome
 {
	/**
	 * The logical JDNI name
	 */
	public static final String COMP_NAME="java:comp/ejb/BpmConfigService";

	/**
	 * The physical JDNI name
	 */
	public static final String JNDI_NAME="openejb:/local/soffid.ejb.com.soffid.iam.bpm.service.BpmConfigService";


}
