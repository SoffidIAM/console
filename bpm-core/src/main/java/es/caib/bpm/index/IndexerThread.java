package es.caib.bpm.index;

import java.io.IOException;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmContext;

import es.caib.bpm.config.Configuration;
import es.caib.bpm.index.Indexer;
import es.caib.bpm.job.NotLoggedThread;
import es.caib.bpm.servei.BPMJobExecutor;
import es.caib.seycon.ng.servei.SeyconServiceLocator;

public class IndexerThread extends NotLoggedThread {

	public IndexerThread() {
		super();
		setName("IndexerThread");
	}

	boolean finish = false;
	int delay = 300000; // 5 minutos
	Logger logger = Logger.getLogger(IndexerThread.class);
	
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
				BPMJobExecutor jobExecutor = SeyconServiceLocator.instance().getBPMJobExecutor();
				jobExecutor.indexPendingProcesses();
			} catch (Exception e) {
				logger.warn("Error indexant", e);
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
