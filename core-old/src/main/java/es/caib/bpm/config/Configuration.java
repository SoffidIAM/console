package es.caib.bpm.config;

import org.jbpm.JbpmConfiguration;

public class Configuration {
	static JbpmConfiguration jBpmConfiguration = null;
	
    public static JbpmConfiguration getConfig () {
        if (jBpmConfiguration == null) {
            jBpmConfiguration = JbpmConfiguration.getInstance();
        }
        return jBpmConfiguration;
    }

	/**
	 * 
	 */
	public static void configureForServer ()
	{
		jBpmConfiguration = JbpmConfiguration.getInstance("es/caib/seycon/ng/sync/jbpm/jbpm.cfg.xml");
	}

	/**
	 * 
	 */
	public static void configureForConsole ()
	{
        jBpmConfiguration = JbpmConfiguration.getInstance();
	}


}
