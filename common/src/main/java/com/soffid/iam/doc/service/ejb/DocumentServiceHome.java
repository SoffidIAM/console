//
// (C) 2013 Soffid
//
//

package com.soffid.iam.doc.service.ejb;
/**
 * EJB Home DocumentService
 */
public interface DocumentServiceHome
	extends jakarta.ejb.EJBLocalHome
 {
	/**
	 * The logical JDNI name
	 */
	public static final String COMP_NAME="java:comp/ejb/DocumentService";

	/**
	 * The physical JDNI name
	 */
	public static final String JNDI_NAME="openejb:/local/soffid.ejb.com.soffid.iam.doc.service.DocumentService";


}
