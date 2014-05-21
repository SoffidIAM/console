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


}
