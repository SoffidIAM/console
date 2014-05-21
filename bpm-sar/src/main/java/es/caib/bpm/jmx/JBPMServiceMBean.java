package es.caib.bpm.jmx;

import org.jboss.system.ServiceMBean;
import org.jbpm.JbpmConfiguration;

public interface JBPMServiceMBean extends ServiceMBean {


        public int getScheduledInterval();

        public void setScheduledInterval(int interval);


        public int getMaxScheduledInterval();

        public void setMaxScheduledInterval(int interval);

        
        public int getSchedulerThreads() ;

        public void setSchedulerThreads(int schedulerThreads) ;

	// Operations

	public void start() throws Exception;

	public void stop();
	
	
	

}
