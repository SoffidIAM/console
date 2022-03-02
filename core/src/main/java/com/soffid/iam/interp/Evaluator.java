package com.soffid.iam.interp;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.remote.RemoteServiceLocator;

public abstract class Evaluator {
	static Map<String,String> remoteLanguages = new HashMap();
	public static Evaluator instance() throws InternalErrorException, IOException {
		String lang;
		if (Security.isSyncProxy())
		{
			lang = remoteLanguages.get(Security.getCurrentTenantName());
			if (lang == null) {
				lang = new RemoteServiceLocator().getServerService().getConfig("soffid.interpreter");
				if (lang == null) lang = "beanshell";
				remoteLanguages.put(Security.getCurrentTenantName(), lang);
			}
				
		}
		else
		{
			lang = ConfigurationCache.getProperty("soffid.interpreter");
		}

		Evaluator evaluator;
		if ("javascript".equalsIgnoreCase(lang))
			evaluator = new JavascriptEvaluator();
		else if ("autodetect".equalsIgnoreCase(lang))
			evaluator = new AutodetectEvaluator();
		else
			evaluator = new BeanshellEvaluator();
		return evaluator;
	}
	
	public abstract Object evaluate (String script, Map<String, Object> vars, String label) throws Exception;
	public abstract boolean isSecure();
	public abstract String translateFromBsh(String bshScript);
}
