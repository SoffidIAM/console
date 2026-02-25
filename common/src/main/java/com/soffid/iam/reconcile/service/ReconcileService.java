//
// (C) 2013 Soffid
//
//

package com.soffid.iam.reconcile.service;
/**
 * Service ReconcileService
 */
public interface ReconcileService {
	public final static String SERVICE_NAME = "com.soffid.iam.reconcile.service.ReconcileService";

	/**
	 * Operation isPendingTasks

	 * @param processId 
	 * @param taskId 
	 * @return 
	 */
	boolean isPendingTasks(
		final java.lang.Long processId, 
		final java.lang.Long taskId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addUser

	 * @param userInfo 
	 * @return 
	 */
	com.soffid.iam.iga.api.ReconcileAccount addUser(
		final com.soffid.iam.iga.api.ReconcileAccount userInfo)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findReconAccountById

	 * @param accountId 
	 * @return 
	 */
	com.soffid.iam.iga.api.ReconcileAccount findReconAccountById(
		final java.lang.Long accountId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addAssignment

	 * @param assignmentInfo 
	 * @return 
	 */
	com.soffid.iam.iga.api.ReconcileAssignment addAssignment(
		final com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findReconAssignmentById

	 * @param assignId 
	 * @return 
	 */
	com.soffid.iam.iga.api.ReconcileAssignment findReconAssignmentById(
		final java.lang.Long assignId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addRole

	 * @param roleInfo 
	 * @return 
	 */
	com.soffid.iam.iga.api.ReconcileRole addRole(
		final com.soffid.iam.iga.api.ReconcileRole roleInfo)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findReconRoleById

	 * @param roleId 
	 * @return 
	 */
	com.soffid.iam.iga.api.ReconcileRole findReconRoleById(
		final java.lang.Long roleId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllReconAccounts

	 * @param processId 
	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.ReconcileAccount> findAllReconAccounts(
		final java.lang.Long processId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllReconAssignment

	 * @param processId 
	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.ReconcileAssignment> findAllReconAssignment(
		final java.lang.Long processId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAllReconRole

	 * @param processId 
	 * @return 
	 */
	java.util.List<com.soffid.iam.iga.api.ReconcileRole> findAllReconRole(
		final java.lang.Long processId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation createReconcileTask

	 * @param processId 
	 * @param dispatcher 
	 */
	void createReconcileTask(
		final java.lang.Long processId, 
		final java.lang.String dispatcher)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reconcileAssignment

	 * @param processId 
	 */
	void reconcileAssignment(
		final java.lang.Long processId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reconcileData

	 * @param processId 
	 */
	void reconcileData(
		final java.lang.Long processId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reconcileRoles

	 * @param processId 
	 */
	void reconcileRoles(
		final java.lang.Long processId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation reconcileUsers

	 * @param processId 
	 */
	void reconcileUsers(
		final java.lang.Long processId)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateAssignment

	 * @param assignInfo 
	 */
	void updateAssignment(
		final com.soffid.iam.iga.api.ReconcileAssignment assignInfo)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateRole

	 * @param roleInfo 
	 */
	void updateRole(
		final com.soffid.iam.iga.api.ReconcileRole roleInfo)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation updateUser

	 * @param userInfo 
	 */
	void updateUser(
		final com.soffid.iam.iga.api.ReconcileAccount userInfo)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation validateReconcileAccount

	 * @param accountinfo 
	 */
	void validateReconcileAccount(
		final com.soffid.iam.iga.api.ReconcileAccount accountinfo)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation validateReconcileRole

	 * @param roleInfo 
	 */
	void validateReconcileRole(
		final com.soffid.iam.iga.api.ReconcileRole roleInfo)
			throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
