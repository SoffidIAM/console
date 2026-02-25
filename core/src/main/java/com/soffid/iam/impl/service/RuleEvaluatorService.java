//
// (C) 2013 Soffid
//
//

package com.soffid.iam.impl.service;
/**
 * Service RuleEvaluatorService
 */
public interface RuleEvaluatorService {
	public final static String SERVICE_NAME = "com.soffid.iam.impl.service.RuleEvaluatorService";

	/**
	 * Operation applyAsync

	 * @param rule 
	 * @return 
	 */
	com.soffid.iam.base.api.AsyncProcessTracker applyAsync(
		final com.soffid.iam.iga.model.RuleEntity rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation queryProcessStatus
	 * Query the rule process status

	 * @param process 
	 * @return 
	 */
	com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(
		final com.soffid.iam.base.api.AsyncProcessTracker process)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation dryRun

	 * @param rule 
	 * @return 
	 */
	java.io.File dryRun(
		final com.soffid.iam.iga.model.RuleEntity rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation apply

	 * @param rule 
	 */
	void apply(
		final com.soffid.iam.iga.model.RuleEntity rule)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation apply

	 * @param rule 
	 * @param user 
	 */
	void apply(
		final com.soffid.iam.iga.model.RuleEntity rule, 
		final com.soffid.iam.base.model.UserEntity user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation applyRules

	 * @param user 
	 */
	void applyRules(
		final com.soffid.iam.base.model.UserEntity user)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
