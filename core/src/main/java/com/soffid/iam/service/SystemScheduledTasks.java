/**
 * 
 */
package com.soffid.iam.service;

/**
 * @author bubu
 *
 */
public class SystemScheduledTasks
{
	public static final String EXPIRE_UNTRUSTED_PASSWORDS = "system:expire-password"; //$NON-NLS-1$
	public static final String DISABLE_EXPIRE_PASSWORDS = "system:disable-password"; //$NON-NLS-1$
	public static final String RECONCILE_DISPATCHER = "system:reconcile-dispatcher"; //$NON-NLS-1$
	public static final String AUTHORITATIVE_DATA_IMPORT = "system:authoritative-import"; //$NON-NLS-1$
	public static final String ENABLE_DISABLE_ROLES = "system:enable-disable-roles";  //$NON-NLS-1$
	public static final String DISPATCHER_IMPACT = "system:dispatcher-impact"; //$NON-NLS-1$
	public static final String UPDATE_STATS = "system:update-stats";
	public static final String RELEASE_PRIVILEGED_ACCOUNTS = "system:release-priv-accounts";
	public static final String NETWORK_DISCOVERY = "system:network-discovery";
}
