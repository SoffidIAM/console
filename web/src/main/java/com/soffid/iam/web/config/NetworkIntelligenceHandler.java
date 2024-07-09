package com.soffid.iam.web.config;

import java.util.Date;

import javax.naming.InitialContext;

import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.NetworkIntelligence;
import com.soffid.iam.service.ConfigurationService;
import com.soffid.iam.service.ejb.NetworkIntelligenceService;
import com.soffid.iam.service.ejb.NetworkIntelligenceServiceHome;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Form;

public class NetworkIntelligenceHandler extends FrameHandler {

	private static final long serialVersionUID = 1L;
	private static final String PARAM_TOKEN = "soffid.network-intelligence.token";

	public NetworkIntelligenceHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		NetworkIntelligence ni = getTokenFromParam();
		setTokenToZul(ni);
		if (ni!=null)
			validateToken(null);
	}

	public void validateToken(Event event) {
		String token = (String) ((CustomField3) getFellow("token")).getValue();
		if (token!=null && !token.trim().isEmpty()) {
			NetworkIntelligence ni = validateTokenFromSsokm(token);
			if (ni!=null) {
				try {
					setTokenToParam(ni);
					setTokenToZul(ni);
				} catch (Exception e) {
					warnTokenToZul("Error validating token");
				}
				return;
			}
			try {
				warnTokenToZul("Token not valid");
				removeTokenParam();
			} catch (Exception e) {
				warnTokenToZul("Token not valid, error trying to update de system");
			}
		}
	}

	private void setTokenToZul(NetworkIntelligence ni) {
		setTokenToZul(ni, null);
	}

	private void setTokenToZul(NetworkIntelligence ni, String warnMessage) {
		if (ni==null) {
			if (warnMessage!=null)
				((CustomField3) getFellow("token")).setWarning(0, warnMessage);
			else
				((CustomField3) getFellow("token")).setValue(null);
			((CustomField3) getFellow("level")).setValue(null);
			((CustomField3) getFellow("start")).setValue(null);
			((CustomField3) getFellow("end")).setValue(null);

			((CustomField3) getFellow("level")).setVisible(false);
			((CustomField3) getFellow("start")).setVisible(false);
			((CustomField3) getFellow("end")).setVisible(false);
		} else {
			((CustomField3) getFellow("token")).setValue(ni.getToken());
			((CustomField3) getFellow("level")).setValue(ni.getLevel());
			((CustomField3) getFellow("start")).setValue(ni.getStart());
			((CustomField3) getFellow("end")).setValue(ni.getEnd());

			((CustomField3) getFellow("level")).setVisible(true);
			((CustomField3) getFellow("start")).setVisible(true);
			((CustomField3) getFellow("end")).setVisible(true);
		}
	}

	private void warnTokenToZul(String warnMessage) {
		setTokenToZul(null, warnMessage);
	}

	private NetworkIntelligence getTokenFromParam() {
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

	private void setTokenToParam(NetworkIntelligence ni) throws InternalErrorException {
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

	private void removeTokenParam() throws InternalErrorException {
		ConfigurationService cs = ServiceLocator.instance().getConfigurationService();
		Configuration param = cs.findParameterByNameAndNetworkName(PARAM_TOKEN, null);
		if (param!=null)
			cs.delete(param);
	}

	private NetworkIntelligence validateTokenFromSsokm(String token) {
		try {
			NetworkIntelligenceService nis = (NetworkIntelligenceService) new InitialContext().lookup(NetworkIntelligenceServiceHome.JNDI_NAME);
			NetworkIntelligence ni = nis.validateTokenFromSsokm(token);
			return ni;
		} catch (Exception e) {
			return null;
		}
	}
}
