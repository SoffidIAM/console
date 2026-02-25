//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.service.ejb;
/**
 * EJB RulesService
 */
public interface RulesService

 {

	com.soffid.iam.base.api.AsyncProcessTracker applyAsync(
		final com.soffid.iam.iga.api.Rule rule)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.AsyncProcessTracker queryProcessStatus(
		final com.soffid.iam.base.api.AsyncProcessTracker process)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Rule create(
		final com.soffid.iam.iga.api.Rule rule)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.Rule update(
		final com.soffid.iam.iga.api.Rule rule)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.RuleAssignedRole create(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.RuleAssignedRole update(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.lang.String generateChangesReport(
		final com.soffid.iam.iga.api.Rule rule, 
		final java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> grants)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> findRuleAssignments(
		final com.soffid.iam.iga.api.Rule rule)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Rule> findRules(
		final java.lang.String description)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.iga.api.Rule> findRulesByRole(
		final java.lang.Long roleId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void apply(
		final com.soffid.iam.iga.api.Rule rule)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.Rule rule)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void delete(
		final com.soffid.iam.iga.api.RuleAssignedRole ruleAssignment)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
