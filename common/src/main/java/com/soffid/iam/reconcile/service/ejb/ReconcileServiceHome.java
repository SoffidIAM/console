//
// (C) 2013 Soffid
//
//

package com.soffid.iam.reconcile.service.ejb;
/**
 * EJB Home ReconcileService
 */
public interface ReconcileServiceHome
	extends jakarta.ejb.EJBLocalHome
 {
	/**
	 * The logical JDNI name
	 */
	public static final String COMP_NAME="java:comp/ejb/ReconcileService";

	/**
	 * The physical JDNI name
	 */
	public static final String JNDI_NAME="openejb:/local/soffid.ejb.com.soffid.iam.reconcile.service.ReconcileService";


}
