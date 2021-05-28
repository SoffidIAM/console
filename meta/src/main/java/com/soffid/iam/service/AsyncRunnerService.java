package com.soffid.iam.service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.common.TransactionalTask;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import roles.Tothom;

@Service
@Depends({AsyncRunnerService.class})
public class AsyncRunnerService {
	@Operation()
	@Transactional(noRollbackFor = { java.lang.Exception.class }, readOnly=true)
	public void run (Runnable runnable, AsyncList result) {	}

	@Operation()
	@Transactional(noRollbackFor = { java.lang.Exception.class }, readOnly=true)
	public void runInternal (Runnable runnable, AsyncList result) {	}

	@Operation(grantees={Tothom.class})
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public Object runTransaction (TransactionalTask runnable) { return null; }

	@Operation(grantees={Tothom.class})
	@Transactional(rollbackFor = { java.lang.Exception.class }, propagation = Propagation.REQUIRES_NEW)
	public Object runNewTransaction (TransactionalTask runnable) { return null; }
}
