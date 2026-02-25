//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.service.ejb;
/**
 * EJB Home ScheduledTaskService
 */
public interface ScheduledTaskServiceHome
	extends jakarta.ejb.EJBLocalHome
 {
	/**
	 * The logical JDNI name
	 */
	public static final String COMP_NAME="java:comp/ejb/ScheduledTaskService";

	/**
	 * The physical JDNI name
	 */
	public static final String JNDI_NAME="openejb:/local/soffid.ejb.com.soffid.iam.sync.service.ScheduledTaskService";


}
