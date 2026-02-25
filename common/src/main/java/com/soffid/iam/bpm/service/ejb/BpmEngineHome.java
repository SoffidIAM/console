//
// (C) 2013 Soffid
//
//

package com.soffid.iam.bpm.service.ejb;
/**
 * EJB Home BpmEngine
 */
public interface BpmEngineHome
	extends jakarta.ejb.EJBLocalHome
 {
	/**
	 * The logical JDNI name
	 */
	public static final String COMP_NAME="java:comp/ejb/BpmEngine";

	/**
	 * The physical JDNI name
	 */
	public static final String JNDI_NAME="openejb:/local/soffid.ejb.com.soffid.iam.bpm.service.BpmEngine";


}
