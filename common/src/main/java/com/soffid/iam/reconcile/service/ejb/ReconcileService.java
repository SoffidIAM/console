//
// (C) 2013 Soffid
//
//

package com.soffid.iam.reconcile.service.ejb;
/**
 * EJB ReconcileService
 */
public interface ReconcileService

 {

	boolean isPendingTasks(
		final java.lang.Long processId, 
		final java.lang.Long taskId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ReconcileAccount addUser(
		final com.soffid.iam.iga.api.ReconcileAccount userInfo)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ReconcileAccount findReconAccountById(
		final java.lang.Long accountId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ReconcileAssignment addAssignment(
		final com.soffid.iam.iga.api.ReconcileAssignment assignmentInfo)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ReconcileAssignment findReconAssignmentById(
		final java.lang.Long assignId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ReconcileRole addRole(
		final com.soffid.iam.iga.api.ReconcileRole roleInfo)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.iga.api.ReconcileRole findReconRoleById(
		final java.lang.Long roleId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.iga.api.ReconcileAccount> findAllReconAccounts(
		final java.lang.Long processId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.iga.api.ReconcileAssignment> findAllReconAssignment(
		final java.lang.Long processId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.iga.api.ReconcileRole> findAllReconRole(
		final java.lang.Long processId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void createReconcileTask(
		final java.lang.Long processId, 
		final java.lang.String dispatcher)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void reconcileAssignment(
		final java.lang.Long processId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void reconcileData(
		final java.lang.Long processId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void reconcileRoles(
		final java.lang.Long processId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void reconcileUsers(
		final java.lang.Long processId)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void updateAssignment(
		final com.soffid.iam.iga.api.ReconcileAssignment assignInfo)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void updateRole(
		final com.soffid.iam.iga.api.ReconcileRole roleInfo)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

	void updateUser(
		final com.soffid.iam.iga.api.ReconcileAccount userInfo)
	throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException;

}
