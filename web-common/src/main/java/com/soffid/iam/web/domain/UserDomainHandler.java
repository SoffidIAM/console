package com.soffid.iam.web.domain;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;

import com.soffid.codemirror.Codemirror;
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.web.component.FrameHandler;

import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Select;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.XPathUtils;

public class UserDomainHandler extends FrameHandler implements AfterCompose {

	private boolean canCreateUserDomain;
	private boolean canUpdateUserDomain;
	private boolean canDeleteUserDomain;
	private boolean canQueryUserDomain;
	private boolean canModifyUserDomain;

	public UserDomainHandler() throws InternalErrorException {
		super();
		canCreateUserDomain = AutoritzacionsUsuari.hasCreateDominisUsuari();
		canUpdateUserDomain = AutoritzacionsUsuari.hasUpdateDominisUsuari();
		canDeleteUserDomain = AutoritzacionsUsuari.hasDeleteDominisUsuari();
		canQueryUserDomain = AutoritzacionsUsuari.hasQueryDominisUsuari();
		canModifyUserDomain = canCreateUserDomain || canUpdateUserDomain;
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
		setVariable("canCreateUserDomain", canCreateUserDomain, true);
		setVariable("canUpdateUserDomain", canUpdateUserDomain, true);
		setVariable("canDeleteUserDomain", canDeleteUserDomain, true);
		setVariable("canQueryUserDomain", canQueryUserDomain, true);
		setVariable("canModifyUserDomain", canModifyUserDomain, true);
		setVariable("e_tipusDominiUsuari", UserDomainType.values, true);
	}

	public void onChangeForm(Event event) {
		DataNode registre;
		try {
			registre = (DataNode) XPathUtils.getValue(getForm(), "/");
		} catch (Exception e) {
			return;
		}
		UserDomain ud = (UserDomain) registre.getInstance();

		if (registre.isNew() && ud.getType() == null) {
			XPathUtils.setValue(getForm(), "/type", TipusDominiUsuariEnumeration.PRINCIPAL);
		}

		com.soffid.codemirror.Codemirror cm = (Codemirror) getFellow("du_script1");
		cm.setReadonly(!ud.getType().equals(TipusDominiUsuariEnumeration.SHELL));

		com.soffid.codemirror.Codemirror cm2 = (Codemirror) getFellow("du_script2");
		cm2.setReadonly(!ud.getType().equals(TipusDominiUsuariEnumeration.SHELL));

		Select lb = (Select) getFellow("lbGenerator");
		lb.setDisabled (! ud.getType().equals(TipusDominiUsuariEnumeration.SPRINGCLASS));
	}

	public void onChangeType(Event event) {
		DataNode registre = (DataNode) XPathUtils.getValue(getForm(), "/");
		UserDomain ud = (UserDomain) registre.getInstance();

		Select select = (Select) event.getTarget(); 
		TipusDominiUsuariEnumeration type = (TipusDominiUsuariEnumeration) select.getSelectedValue();
		//Missatgebox.confirmaOK_CANCEL ("sel "+tipus);
		Select lb = (Select) getFellow("lbGenerator");
		lb.setDisabled (! type.equals(TipusDominiUsuariEnumeration.SPRINGCLASS));

		com.soffid.codemirror.Codemirror cm = (Codemirror) getFellow("du_script1");
		cm.setReadonly (! type.equals(TipusDominiUsuariEnumeration.SHELL));

		com.soffid.codemirror.Codemirror cm2 = (Codemirror) getFellow("du_script2");
		cm2.setReadonly (! type.equals(TipusDominiUsuariEnumeration.SHELL));
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		try {
			com.soffid.codemirror.Codemirror cm = (Codemirror) getFellow("du_script1");
			cm.setGlobalVars(new com.soffid.iam.web.agent.ScriptEnviroment().getDomainVars());
		} catch (InternalErrorException | NamingException | CreateException e) {
		}
		try {
			com.soffid.codemirror.Codemirror cm = (Codemirror) getFellow("du_script2");
			cm.setGlobalVars(new com.soffid.iam.web.agent.ScriptEnviroment().getDomainVars());
		} catch (InternalErrorException | NamingException | CreateException e) {
		}
	}
	
	public void scriptHelp() {
		Executions.getCurrent().sendRedirect("https://confluence.soffid.com/display/SOF/Account+naming+rules+script", "_blank");
	}
}
