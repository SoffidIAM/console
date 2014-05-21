package es.caib.bpm.index;

import java.io.IOException;

import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;

import es.caib.bpm.config.Configuration;
import es.caib.bpm.index.Indexer;
import es.caib.bpm.job.NotLoggedThread;
import es.caib.bpm.servei.BpmJobExecutor;
import es.caib.seycon.ng.servei.SeyconServiceLocator;

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
			try {
				BpmJobExecutor jobExecutor = SeyconServiceLocator.instance().getBpmJobExecutor();
				jobExecutor.indexPendingProcesses();
			} catch (Exception e) {
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
