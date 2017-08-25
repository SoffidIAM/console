//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.reconcile.service;
import com.soffid.iam.reconcile.model.ReconcileAccountAttributesEntity;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( translatedName="ReconcileService",
	 translatedPackage="com.soffid.iam.reconcile.service")
@Depends ({com.soffid.iam.reconcile.model.ReconcileAssignmentEntity.class,
	com.soffid.iam.reconcile.model.ReconcileRoleEntity.class,
	com.soffid.iam.reconcile.model.ReconcileAccountEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.servei.UsuariService.class,
	es.caib.seycon.ng.servei.AplicacioService.class,
	es.caib.seycon.ng.servei.DispatcherService.class,
	es.caib.seycon.ng.servei.AccountService.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	ReconcileAccountAttributesEntity.class})
public abstract class ReconcileService {

	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.reconcile.common.ReconcileAccount addUser(
		com.soffid.iam.reconcile.common.ReconcileAccount userInfo)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.reconcile.common.ReconcileRole addRole(
		com.soffid.iam.reconcile.common.ReconcileRole roleInfo)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.reconcile.common.ReconcileAssignment addAssignment(
		com.soffid.iam.reconcile.common.ReconcileAssignment assignmentInfo)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void createReconcileTask(
		java.lang.Long processId, 
		java.lang.String dispatcher)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean isPendingTasks(
		java.lang.Long processId, 
		java.lang.Long taskId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.reconcile.common.ReconcileRole findReconRoleById(
		java.lang.Long roleId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.reconcile.common.ReconcileAccount findReconAccountById(
		java.lang.Long accountId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public com.soffid.iam.reconcile.common.ReconcileAssignment findReconAssignmentById(
		java.lang.Long assignId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateUser(
		com.soffid.iam.reconcile.common.ReconcileAccount userInfo)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateRole(
		com.soffid.iam.reconcile.common.ReconcileRole roleInfo)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void updateAssignment(
		com.soffid.iam.reconcile.common.ReconcileAssignment assignInfo)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.reconcile.common.ReconcileAccount> findAllReconAccounts(
		java.lang.Long processId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.reconcile.common.ReconcileAssignment> findAllReconAssignment(
		java.lang.Long processId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List<com.soffid.iam.reconcile.common.ReconcileRole> findAllReconRole(
		java.lang.Long processId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void reconcileUsers(
		java.lang.Long processId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void reconcileRoles(
		java.lang.Long processId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void reconcileAssignment(
		java.lang.Long processId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Operation ( grantees={com.soffid.iam.reconcile.user_reconcile.class})
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void reconcileData(
		java.lang.Long processId)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void validateReconcileAccount(
		com.soffid.iam.reconcile.common.ReconcileAccount accountinfo)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void validateReconcileRole(
		com.soffid.iam.reconcile.common.ReconcileRole roleInfo)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
}
