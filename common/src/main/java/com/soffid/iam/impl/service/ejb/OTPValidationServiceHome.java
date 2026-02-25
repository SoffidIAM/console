//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service.ejb;
/**
 * EJB Home OTPValidationService
 */
public interface OTPValidationServiceHome
	extends jakarta.ejb.EJBLocalHome
 {
	/**
	 * The logical JDNI name
	 */
	public static final String COMP_NAME="java:comp/ejb/OTPValidationService";

	/**
	 * The physical JDNI name
	 */
	public static final String JNDI_NAME="openejb:/local/soffid.ejb.com.soffid.iam.impl.service.OTPValidationService";


}
