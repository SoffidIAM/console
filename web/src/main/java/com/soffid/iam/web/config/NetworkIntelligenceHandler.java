package com.soffid.iam.web.config;

import javax.naming.InitialContext;

import org.zkoss.zk.ui.event.Event;

import com.soffid.iam.api.NetworkIntelligence;
import com.soffid.iam.service.ejb.NetworkIntelligenceService;
import com.soffid.iam.service.ejb.NetworkIntelligenceServiceHome;
import com.soffid.iam.utils.NetworkIntelligenceParamTokenUtils;
import com.soffid.iam.web.component.CustomField3;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;

public class NetworkIntelligenceHandler extends FrameHandler {

	private static final long serialVersionUID = 1L;

	public NetworkIntelligenceHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		NetworkIntelligence ni = NetworkIntelligenceParamTokenUtils.getTokenFromParam();
		setTokenToZul(ni);
		if (ni!=null)
			validateToken(null);
	}

	public void validateToken(Event event) {
		String token = (String) ((CustomField3) getFellow("token")).getValue();
		if (token!=null && !token.trim().isEmpty()) {
			NetworkIntelligence ni = null;
			boolean serviceError = false;
			try {
				ni = validateLicense(token);
			} catch(Exception e) {
				serviceError = true;
			}
			if (serviceError) {
				warnTokenToZul("Error trying to validate the token with the service, try it later");
			} else {
				if (ni!=null) {
					try {
						NetworkIntelligenceParamTokenUtils.setTokenToParam(ni);
						setTokenToZul(ni);
					} catch (Exception e) {
						warnTokenToZul("Internal error validating token, contact with the administrator");
					}
					return;
				} else {
					warnTokenToZul("Token not valid");
					try {
						NetworkIntelligenceParamTokenUtils.removeTokenParam();
					} catch (Exception e) {
						warnTokenToZul("Token not valid and it has been an internal error tryint to remove it");
					}
				}
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

	public static NetworkIntelligence validateLicense(String token) throws Exception {
		NetworkIntelligenceService nis = (NetworkIntelligenceService) new InitialContext().lookup(NetworkIntelligenceServiceHome.JNDI_NAME);
		NetworkIntelligence ni = nis.validateToken(token);
		return ni;
	}
}
