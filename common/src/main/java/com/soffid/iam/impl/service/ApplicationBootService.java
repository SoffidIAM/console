//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service ApplicationBootService
 */
public interface ApplicationBootService {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.ApplicationBootService";

	/**
	 * Operation doUpgrade

	 * @return 
	 */
	com.soffid.iam.impl.api.UpgradeStatus doUpgrade()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getUpgradeStatus

	 * @return 
	 */
	com.soffid.iam.impl.api.UpgradeStatus getUpgradeStatus()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation consoleBoot

	 */
	void consoleBoot()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation doSyncUpgrade

	 */
	void doSyncUpgrade()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation syncServerBoot

	 */
	void syncServerBoot()
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation tenantBoot

	 * @param tenant 
	 */
	void tenantBoot(
		final com.soffid.iam.base.api.Tenant tenant)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
