package com.soffid.iam.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.common.TransactionalTask;
import com.soffid.iam.utils.Security;

public class AsyncRunnerServiceImpl extends AsyncRunnerServiceBase {

	@Override
	protected void handleRun(final Runnable runnable, final AsyncList result) throws Exception {
		final AsyncRunnerService ars = getAsyncRunnerService();
		final List<String> auths = Security.getAuthorizations();
		final String tenant = Security.getCurrentTenantName();
		final String account = Security.getCurrentAccount();
		new Thread (  ) {
			public void run () {
				try {
					Security.nestedLogin(tenant, account, auths.toArray(new String[auths.size()]));
					ars.runInternal(runnable, result);
				} catch (Throwable e) {
					result.cancel(e);
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
			result.cancel(th);
		}
	}

	@Override
	protected Object handleRunTransaction(TransactionalTask runnable) throws Exception {
		return runnable.run();
	}

}
