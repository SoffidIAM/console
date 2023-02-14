package com.soffid.iam.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.common.TransactionalTask;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.utils.Security;

public class AsyncRunnerServiceImpl extends AsyncRunnerServiceBase {

	@Override
	protected void handleRun(final Runnable runnable, final AsyncList result) throws Exception {
		final AsyncRunnerService ars = getAsyncRunnerService();
		final List<String> auths = Security.getAuthorizations();
		final String tenant = Security.getCurrentTenantName();
		final String account = Security.getCurrentAccount();
		final SoffidPrincipal principal = Security.getSoffidPrincipal();
		new Thread (  ) {
			public void run () {
				try {
					Security.nestedLogin(principal);
					ars.runInternal(runnable, result);
				} catch (Throwable e) {
					result.cancel(e);
				} finally {
					Security.nestedLogoff();
				}
			}
		}.start();
	}

	Log log = LogFactory.getLog(getClass());

	@Override
	protected void handleRunInternal(Runnable runnable, AsyncList result) throws Exception {
		try {
			runnable.run();
			result.done();
		} catch (Throwable th) {
			log.info("Exception during async run process "+runnable.toString(), th );
			if (th instanceof RuntimeException && th.getCause() != null) 
				result.cancel(th.getCause());
			else
				result.cancel(th);
		}
	}

	@Override
	protected Object handleRunTransaction(TransactionalTask runnable) throws Exception {
		return runnable.run();
	}

	@Override
	protected Object handleRunNewTransaction(TransactionalTask runnable) throws Exception {
		return runnable.run();
	}
}
