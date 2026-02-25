//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service AccountNameGenerator
 */
public interface AccountNameGenerator {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.AccountNameGenerator";

	/**
	 * Operation needsAccount

	 * @param user 
	 * @param dispatcher 
	 * @return 
	 */
	boolean needsAccount(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.iga.model.SystemEntity dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getAccountName

	 * @param user 
	 * @param dispatcher 
	 * @param userDomain 
	 * @return 
	 */
	java.lang.String getAccountName(
		final com.soffid.iam.base.model.UserEntity user, 
		final com.soffid.iam.iga.model.SystemEntity dispatcher, 
		final com.soffid.iam.iga.model.UserDomainEntity userDomain)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
