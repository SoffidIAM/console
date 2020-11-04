package com.soffid.iam.web.profile;

import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zul.Textbox;

import com.soffid.iam.utils.Security;
import com.soffid.iam.web.common.ChangePass;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Missatgebox;

public class PasswordChangeHandler extends FrameHandler {
	boolean forced = false;
	
	public PasswordChangeHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void afterCompose() {
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		try {
			String usuari = com.soffid.iam.utils.Security.getCurrentAccount();
			String nom = Security.getSoffidPrincipal().getFullName();

			javax.servlet.http.HttpServletRequest requ =
					(javax.servlet.http.HttpServletRequest) Executions.getCurrent()
					.getNativeRequest();

			String v_canviVoluntari = requ.getParameter("custom");
			boolean canviVoluntari = false;

			if ((v_canviVoluntari != null) && (!"".equals(v_canviVoluntari)))
			{
				canviVoluntari = true;
			}

			es.caib.seycon.ng.servei.ejb.PasswordService ps = es.caib.seycon.ng.EJBLocator.getPasswordService();
			String dispatcher = ps.getDefaultDispatcher();

			String []pds = ps.getPolicyDescription(usuari, dispatcher).split("\n");
			StringBuffer policyDescription = new StringBuffer ();
			for (String pd: pds)
			{
				if (pd.startsWith("- "))
					policyDescription.append("    ").append (pd).append("\n");

				else
					policyDescription.append("<LI>").append (pd).append ("</LI>");
			}
			setVariable("policyDescription", policyDescription.toString(), true);
			setVariable("usuari", usuari, true);
		} catch (Exception e) {
			throw new UiException(e);
		}

	}

	// Mètode per fer logout
	public void logout()
	{
		Execution ex = Executions.getCurrent();
		ex.sendRedirect("/");
		
		Map sessionScope = ex.getDesktop().getSession().getAttributes();
		sessionScope.put("$$SoffidPasswordChanged$$", Boolean.TRUE);
		String previousURL = (String) sessionScope.get("$$SoffidPassswordBack$$");

		javax.servlet.http.HttpServletRequest req =
				(javax.servlet.http.HttpServletRequest) Executions.getCurrent()
				.getNativeRequest();

		req.getSession().invalidate();
	}

	public void canviaPass()
	{
		Textbox passactual = (Textbox) getFellow("passactual");
		Textbox passnueva1 = (Textbox) getFellow("passnueva1");
		Textbox passnueva2 = (Textbox) getFellow("passnueva2");
		
		// Obtenim els camps actuals
		String contraAnt = passactual.getValue();
		String contraNova1 = passnueva1.getValue();
		String contraNova2 = passnueva2.getValue();
		
		// Check void passwords
		if (contraAnt.trim().isEmpty() || (contraNova1.trim().isEmpty()) || (contraNova2.trim().isEmpty()))
		{
			Missatgebox.error(org.zkoss.util.resource
					.Labels.getLabel("changepassPerfil.CampsBuits"),
				org.zkoss.util.resource
					.Labels.getLabel("changepassPerfil.Titol"));
			
			return;
		}
		
		if (!contraNova1.equals(contraNova2))
		{
			Missatgebox.error (org.zkoss.util.resource
					.Labels.getLabel("changepassPerfil.CampsCoincidir"),
				org.zkoss.util.resource
					.Labels.getLabel("changepassPerfil.Titol"));
			
			return;
		}
		
		ChangePass thechange = new ChangePass();
		try
		{
			String usuari = com.soffid.iam.utils.Security.getCurrentAccount();
			thechange.changePassword(usuari,contraAnt,contraNova1);
		
			if (Missatgebox.confirmaOK(org.zkoss.util.resource
							.Labels.getLabel("changepassPerfil.CanviOK"),
						org.zkoss.util.resource
							.Labels.getLabel("changepassPerfil.CanviPWD")))
			{
				if (forced)
					logout();
				else
					es.caib.zkib.zkiblaf.Application.setPage("/perfil//perfil.zul");
			}
		}
		
		catch (es.caib.seycon.ng.exception.BadPasswordException e)
		{
			Missatgebox.error(String.format(org.zkoss.util.resource
						.Labels.getLabel("changepassPerfil.ErrorNormativa"),
					new Object[] { e.getMessage() }),
				org.zkoss.util.resource
					.Labels.getLabel("changepassPerfil.PasswordInvalid"));
		}
		
		catch (es.caib.seycon.ng.exception.InvalidPasswordException e)
		{
			Missatgebox.error(org.zkoss.util.resource
					.Labels.getLabel("changepassPerfil.ErrorCanvi"),
				org.zkoss.util.resource
					.Labels.getLabel("changepassPerfil.PasswordInvalid"));
		}
		
		catch (Exception e)
		{
			String msg = e.getMessage();
			msg = (msg != null) ? ": " + msg : "";
			Missatgebox.error(String.format(org.zkoss.util.resource
					.Labels.getLabel("changepassPerfil.Error"),
				new Object [] {msg}));
		}
	}

	public void cancel() {
		es.caib.zkib.zkiblaf.Application.setPage("perfil/perfil.zul");
	}

	
	public boolean isForced() {
		return forced;
	}

	
	public void setForced(boolean forced) {
		this.forced = forced;
	}
}
