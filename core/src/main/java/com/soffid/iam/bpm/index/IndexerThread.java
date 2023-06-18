package com.soffid.iam.bpm.index;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.bpm.job.NotLoggedThread;
import com.soffid.iam.bpm.service.BpmJobExecutor;
import com.soffid.iam.config.Config;
import com.soffid.iam.sync.service.TaskGenerator;

public class IndexerThread extends NotLoggedThread {
	
	public IndexerThread() {
		super();
		setName("IndexerThread"); //$NON-NLS-1$
	}

	boolean finish = false;
	int delay = 30000; // 30 segundos
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
		if ("true".equals(System.getProperty("soffid.indexer.disabled"))) 
			return;
		while (!finish)
		{
			BpmJobExecutor jobExecutor;
			try
			{
				jobExecutor = ServiceLocator.instance().getBpmJobExecutor();
				TaskGenerator tg = ServiceLocator.instance().getTaskGenerator();
				try {
					if ( tg.isMainServer() )
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
