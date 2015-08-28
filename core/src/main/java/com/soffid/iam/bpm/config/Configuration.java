package com.soffid.iam.bpm.config;

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
		jBpmConfiguration = JbpmConfiguration.getInstance("com/soffid/iam/sync/jbpm/jbpm.cfg.xml");
	}

	/**
	 * 
	 */
	public static void configureForConsole ()
	{
        jBpmConfiguration = JbpmConfiguration.getInstance();
	}


}
