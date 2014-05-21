//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.bpm.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( grantees={Roles.anonymous.class})
@Depends ({es.caib.bpm.servei.BpmConfigService.class,
	es.caib.bpm.servei.BpmEngine.class})
public abstract class BpmJobExecutor {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.List getJobs(
		java.lang.String lockOwner)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void executeJob(
		long id)
		throws es.caib.seycon.ng.exception.InternalErrorException, java.lang.Exception {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void anotateFailure(
		long id, 
		java.lang.Exception e)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Date getNextDueDate(
		java.lang.String lockOwner)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void unlockOverdueJobs(
		java.util.Date threshold)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public void indexPendingProcesses()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
}
