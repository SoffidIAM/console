//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB Home GenAIProviderService
 */
public interface GenAIProviderServiceHome
	extends jakarta.ejb.EJBLocalHome
 {
	/**
	 * The logical JDNI name
	 */
	public static final String COMP_NAME="java:comp/ejb/GenAIProviderService";

	/**
	 * The physical JDNI name
	 */
	public static final String JNDI_NAME="openejb:/local/soffid.ejb.com.soffid.iam.base.service.GenAIProviderService";


}
