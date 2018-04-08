//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.bpm.servei;
import com.soffid.iam.service.MailService;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service ( grantees={roles.anonymous.class},
		translatedName="BpmJobExecutor", translatedPackage="com.soffid.iam.bpm.service")
@Depends ({es.caib.bpm.servei.BpmConfigService.class,
	MailService.class,
	es.caib.bpm.servei.BpmEngine.class})
public abstract class BpmJobExecutor {

	@Transactional(rollbackFor={java.lang.Exception.class},propagation=Propagation.REQUIRES_NEW)
	public java.util.List getJobs(
		java.lang.String lockOwner)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class},propagation=Propagation.REQUIRES_NEW)
	public void executeJob(
		long id)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.lang.Exception {
	}
	@Transactional(rollbackFor={java.lang.Exception.class},propagation=Propagation.REQUIRES_NEW)
	public void anotateFailure(
		long id, 
		java.lang.Exception e)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class},propagation=Propagation.REQUIRES_NEW)
	public java.util.Date getNextDueDate(
		java.lang.String lockOwner)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class},propagation=Propagation.REQUIRES_NEW)
	public void unlockOverdueJobs(
		java.util.Date threshold)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class},propagation=Propagation.REQUIRES_NEW)
	public void indexPendingProcesses()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
}
