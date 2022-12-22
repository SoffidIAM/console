package com.soffid.iam.web.wheel;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.Role;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.service.ejb.ConfigurationService;
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.zkiblaf.Application;


public class Pam04Handler extends Window implements AfterCompose {
	public void back(Event ev) {
		detach();
	}
	
	public void apply(Event ev) throws NamingException, CreateException, InternalErrorException {
		configureAppend("soffid.otp.required", "/resource/account/vault.zul");
		configureAppend("soffid.otp.required", "/main/menu.zul?.*option=vault.*");
		configureAppend("soffid.otp.optional", "/addon/otp/otp.zul");
		configure("soffid.otp.timeout", "300");
		configure("otp.totp.allow", "true");
		configure("otp.hotp.allow", "true");
		createRole("SOFFID_ADMIN", "Soffid administrators");
		createRole("SOFFID_VAULT_MGR", "Password vault manager");
		createRole("SOFFID_VAULT_USER", "Password vault user");
		Application.jumpTo("/config/authentication.zul");
	}

	private void configure(String param, String value) throws NamingException, CreateException, InternalErrorException {
		ConfigurationService svc = EJBLocator.getConfigurationService();
		Configuration cfg = svc.findParameterByNameAndNetworkName(param, null);
		if (cfg == null )
		{
			cfg = new Configuration();
			cfg.setCode(param);
			cfg.setValue(value);
			cfg.setDescription("Auto configured");
			svc.create(cfg);
		}
		else
		{
			cfg.setValue(value);
			svc.update(cfg);
		}
	}
	
	private void configureAppend(String param, String value) throws NamingException, CreateException, InternalErrorException {
		ConfigurationService svc = EJBLocator.getConfigurationService();
		Configuration cfg = svc.findParameterByNameAndNetworkName(param, null);
		if (cfg == null )
		{
			cfg = new Configuration();
			cfg.setCode(param);
			cfg.setValue(value);
			cfg.setDescription("Auto configured");
			svc.create(cfg);
		}
		else
		{
			if (!cfg.getValue().contains(value)) {
				cfg.setValue(value+"\n"+cfg.getValue());
				svc.update(cfg);
			}
		}
	}

	private Role createRole(String name, String description) throws InternalErrorException, NamingException, CreateException {
		final String soffidSystem = EJBLocator.getDispatcherService().findSoffidDispatcher().getName();
		Role role = EJBLocator.getApplicationService().findRoleByNameAndSystem(name, soffidSystem);
		if (role == null) {
			role = new Role();
			role.setName(name);
			role.setDescription(description);
			role.setSystem(soffidSystem);
			role.setInformationSystemName("SOFFID");
			role = EJBLocator.getApplicationService().create(role);
		}
		
		grant("otp:user", role);
		return role;
	}


	private void grant(String authName, Role role) throws InternalErrorException {
		AuthorizationService autService = ServiceLocator.instance().getAuthorizationService();
		for (AuthorizationRole auth0: autService.getAuthorizationRoles(authName)) {
			if (auth0.getRole().getId().equals(role.getId()))
				return; // Already granted
		}
		AuthorizationRole auth = new AuthorizationRole();
		auth.setAuthorization(authName);
		auth.setRole(role);
		autService.create(auth );
	}

	@Override
	public void afterCompose() {
		doHighlighted();
	}

}
