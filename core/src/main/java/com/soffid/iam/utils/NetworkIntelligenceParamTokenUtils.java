package com.soffid.iam.utils;

import java.util.Date;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.NetworkIntelligence;
import com.soffid.iam.service.ConfigurationService;

import es.caib.seycon.ng.exception.InternalErrorException;

public class NetworkIntelligenceParamTokenUtils {

	private static final String PARAM_TOKEN = "network-intelligence.token";

	public static NetworkIntelligence getTokenFromParam() {
		ConfigurationService cs = ServiceLocator.instance().getConfigurationService();
		Configuration param = null;
		try {
			param = cs.findParameterByNameAndNetworkName(PARAM_TOKEN, null);
		} catch (InternalErrorException e) {}
		if (param==null)
			return null;

		NetworkIntelligence ni = null;
		try {
			String[] paramA = param.getValue().split(";");
			ni = new NetworkIntelligence();
			ni.setToken(paramA[0].split("=")[1]);
			ni.setLevel(paramA[1].split("=")[1]);
			ni.setStart(new Date(Long.parseLong(paramA[2].split("=")[1])));
			ni.setEnd(new Date(Long.parseLong(paramA[3].split("=")[1])));
		} catch (Exception e) {
			ni = null;
		}
		return ni;
	}

	public static void setTokenToParam(NetworkIntelligence ni) throws InternalErrorException {
		ConfigurationService cs = ServiceLocator.instance().getConfigurationService();
		Configuration param = null;
		try {
			param = cs.findParameterByNameAndNetworkName(PARAM_TOKEN, null);
		} catch (InternalErrorException e) {}

		String paramS = "token="+ni.getToken()+";level="+ni.getLevel()+";start="+ni.getStart().getTime()+";end="+ni.getEnd().getTime();
		if (param==null) {
			param = new Configuration();
			param.setName(PARAM_TOKEN);
			param.setValue(paramS);
			cs.create(param);
		} else {
			param.setValue(paramS);
			cs.update(param);
		}
	}

	public static void removeTokenParam() throws InternalErrorException {
		ConfigurationService cs = ServiceLocator.instance().getConfigurationService();
		Configuration param = cs.findParameterByNameAndNetworkName(PARAM_TOKEN, null);
		if (param!=null)
			cs.delete(param);
	}
}
