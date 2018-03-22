package com.soffid.iam.service;

import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.AsyncList;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

@Service(internal=true)
@Depends({AsyncRunnerService.class})
public class AsyncRunnerService {
	@Operation()
	@Transactional(noRollbackFor = { java.lang.Exception.class }, readOnly=true)
	public void run (Runnable runnable, AsyncList result) {	}

	@Operation()
	@Transactional(noRollbackFor = { java.lang.Exception.class }, readOnly=true)
	public void runInternal (Runnable runnable, AsyncList result) {	}
}
