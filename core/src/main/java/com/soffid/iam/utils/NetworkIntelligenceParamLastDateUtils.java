package com.soffid.iam.utils;

import java.util.Date;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.service.ConfigurationService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class NetworkIntelligenceParamLastDateUtils {

	private static final String PARAM_VERIFYDOMAINS_LASTDATE = "network-intelligence.verifyDomains.lastDate";

	public static Long getLastDateFromParam() {
		try {
			ConfigurationService cs = ServiceLocator.instance().getConfigurationService();
			Configuration param = cs.findParameterByNameAndNetworkName(PARAM_VERIFYDOMAINS_LASTDATE, null);
			Long l = Long.valueOf(param.getValue());
			return l;
		} catch (Exception e) {}
		return null;
	}

	public static void setLastDateToParam() throws InternalErrorException {
		Date now = new Date();
		setLastDateToParam(now.getTime());
	}

	public static void setLastDateToParam(long lastDate) throws InternalErrorException {
		ConfigurationService cs = ServiceLocator.instance().getConfigurationService();
		Configuration param = cs.findParameterByNameAndNetworkName(PARAM_VERIFYDOMAINS_LASTDATE, null);
		if (param==null) {
			param = new Configuration();
			param.setName(PARAM_VERIFYDOMAINS_LASTDATE);
			param.setValue(String.valueOf(lastDate));
			cs.create(param);
		} else {
			param.setValue(String.valueOf(lastDate));
			cs.update(param);
		}
	}
}
