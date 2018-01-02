package com.soffid.iam.bpm.index;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.bpm.job.NotLoggedThread;
import com.soffid.iam.bpm.service.ejb.BpmJobExecutor;

public class IndexerThread extends NotLoggedThread {
	
	public IndexerThread() {
		super();
		setName("IndexerThread"); //$NON-NLS-1$
	}

	boolean finish = false;
	int delay = 300000; // 5 minutos
	private Log logger = LogFactory.getLog(IndexerThread.class);
	
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	public boolean isFinish() {
		return finish;
	}

	public void setFinish(boolean finish) {
		this.finish = finish;
		if (finish)
			interrupt();
	}

	public void run() {
		while (!finish)
		{
			BpmJobExecutor jobExecutor;
			try
			{
				jobExecutor = EJBLocator.getBpmJobExecutor();
				try {
					jobExecutor.indexPendingProcesses();
				} catch (Exception e) {
					if (!finish)
						logger.warn(Messages.getString("IndexerThread.IndexingError"), e); //$NON-NLS-1$
				}
			} catch (Exception e) {
				if (!finish)
					logger.warn(Messages.getString("IndexerThread.IndexingError"), e); //$NON-NLS-1$
			}
			if (!finish) {
				try {
					sleep(delay);
				} catch (InterruptedException e) {
				}
			}
		}
	}

}
