package com.soffid.iam.web.obligation;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.sys.ExecutionCtrl;

import com.soffid.iam.api.RequestedObligationEnum;
import com.soffid.iam.common.security.SoffidPrincipal;
import com.soffid.iam.security.Obligation;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.zkib.zkiblaf.Missatgebox;

public class ObligationManager {
	private Desktop desktop;

	public ObligationManager() {
		desktop = Executions.getCurrent().getDesktop();
	}
	
	public List<Obligation> getCurrentObligations() {
		return (List<Obligation>) desktop.getAttribute("obligations");
	}
	
	public Event getCurrentEvent() {
		return (Event) desktop.getAttribute("obligation_event");		
	}

	public void setCurrentObligations(Event event, List<Obligation> obligations) {
		desktop.setAttribute("obligation_event", event);
		Collections.sort(obligations, new Comparator<Obligation>() {
			@Override
			public int compare(Obligation o1, Obligation o2) {
				int order1 = RequestedObligationEnum.MESSAGE.getValue().equals(o1.getObligation()) ? 3:
					RequestedObligationEnum.OTP.getValue().equals(o1.getObligation()) ? 2:
					RequestedObligationEnum.WORKFLOW.getValue().equals(o1.getObligation()) ? 1: 0;
				int order2 = RequestedObligationEnum.MESSAGE.getValue().equals(o2.getObligation()) ? 3:
					RequestedObligationEnum.OTP.getValue().equals(o2.getObligation()) ? 2:
					RequestedObligationEnum.WORKFLOW.getValue().equals(o2.getObligation()) ? 1: 0;
				return order1 - order2;
			}
		});
		desktop.setAttribute("obligations", obligations);
	}

	public void clearCurrentObligations() {
		desktop.setAttribute("obligation_event", null);
		desktop.setAttribute("obligations", null);
	}

	public Obligation getNextObligation() {
		List<Obligation> obligations = getCurrentObligations();
		if (obligations == null)
			return null;
		for (Obligation obligation: obligations) {
			Map<String, String> principalAttributes = Security.getSoffidPrincipal().getObligation(obligation.getObligation());
			if (principalAttributes == null)
				return obligation;
			Map<String, String> obligationAttributes = obligation.getAttributes();
			for (String key: obligationAttributes.keySet()) {
				if (! obligationAttributes.get(key).equals(principalAttributes.get(key))) {
					return obligation;
				}
			}
		}
		return null;
	}
	
	public void meetObligation(Obligation obligation) {
		SoffidPrincipal sp = Security.getSoffidPrincipal();
		
		String timeout = obligation.getAttributes().get("timeout");
		
		if (timeout == null)
			ConfigurationCache.getProperty("soffid.otp.timeout");
		
		if (timeout == null || timeout.trim().isEmpty())
			timeout = "60";
		
		long to = System.currentTimeMillis() + 1000 * Long.parseLong(timeout);
		
		sp.setObligation(obligation.getObligation(), obligation.getAttributes(), to);
	}


	public void handleObligation(final Obligation obligation) {
		try {
			if (obligation.getObligation().endsWith(RequestedObligationEnum.MESSAGE.getValue())) {
				
				Missatgebox.avis(obligation.getAttributes().get("text"),
						obligation.getAttributes().get("title"),
						(event) -> {
							meetObligation(obligation);
							handleNextObligation();
						});
			} 
			else if (obligation.getObligation().endsWith(RequestedObligationEnum.OTP.getValue())) {
				OtpObligationHandler h = new OtpObligationHandler();
				
				ExecutionCtrl execution = (ExecutionCtrl) Executions.getCurrent();
				Page page = execution.getCurrentPage();
				
				h.setPage(page.getId());
				h.setOptional(false);
				h.requestOtp();
				
			}
			else if (obligation.getObligation().endsWith(RequestedObligationEnum.WORKFLOW.getValue())) {
				WorkflowObligationHandler h = new WorkflowObligationHandler();
				
				h.startWorkflow(obligation.getAttributes());
			}
			else if (obligation.getObligation().endsWith(RequestedObligationEnum.NOTIFY_OWNER.getValue())) {
				MailObligationHandler h = new MailObligationHandler();
				
				h.sendMail(obligation.getAttributes());
				
				meetObligation(obligation);
				handleNextObligation();
				
			} else {
				throw new UiException("Unhandled unknown obligation "+obligation.getObligation());
			}
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	public void handleNextObligation() {
		if (getCurrentEvent() != null) {
			Obligation next = getNextObligation();
			if (next == null)
				Events.postEvent(getCurrentEvent());
			else
				handleObligation(next);
		}
	}
}
