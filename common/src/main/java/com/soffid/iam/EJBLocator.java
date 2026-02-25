//
// (C) 2013 Soffid
//
//

package com.soffid.iam;

/**
 * Locates and provides all available application services.
 */
public class EJBLocator
{

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.ejb.AccessLogService}.
	 */
	public static com.soffid.iam.am.service.ejb.AccessLogService getAccessLogService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.am.service.ejb.AccessLogService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.am.service.ejb.AccessLogServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.ejb.BrowserService}.
	 */
	public static com.soffid.iam.am.service.ejb.BrowserService getBrowserService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.am.service.ejb.BrowserService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.am.service.ejb.BrowserServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.ejb.CertificateValidationService}.
	 */
	public static com.soffid.iam.am.service.ejb.CertificateValidationService getCertificateValidationService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.am.service.ejb.CertificateValidationService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.am.service.ejb.CertificateValidationServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.ejb.EntryPointService}.
	 */
	public static com.soffid.iam.am.service.ejb.EntryPointService getEntryPointService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.am.service.ejb.EntryPointService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.am.service.ejb.EntryPointServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.ejb.NetworkService}.
	 */
	public static com.soffid.iam.am.service.ejb.NetworkService getNetworkService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.am.service.ejb.NetworkService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.am.service.ejb.NetworkServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.ejb.PasswordService}.
	 */
	public static com.soffid.iam.am.service.ejb.PasswordService getPasswordService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.am.service.ejb.PasswordService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.am.service.ejb.PasswordServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.ejb.ServiceService}.
	 */
	public static com.soffid.iam.am.service.ejb.ServiceService getServiceService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.am.service.ejb.ServiceService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.am.service.ejb.ServiceServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.ejb.SessionService}.
	 */
	public static com.soffid.iam.am.service.ejb.SessionService getSessionService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.am.service.ejb.SessionService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.am.service.ejb.SessionServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.ejb.VaultService}.
	 */
	public static com.soffid.iam.am.service.ejb.VaultService getVaultService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.am.service.ejb.VaultService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.am.service.ejb.VaultServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ejb.AccountService}.
	 */
	public static com.soffid.iam.base.service.ejb.AccountService getAccountService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.base.service.ejb.AccountService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.base.service.ejb.AccountServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ejb.AdditionalDataService}.
	 */
	public static com.soffid.iam.base.service.ejb.AdditionalDataService getAdditionalDataService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.base.service.ejb.AdditionalDataService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.base.service.ejb.AdditionalDataServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ejb.AuthorizationService}.
	 */
	public static com.soffid.iam.base.service.ejb.AuthorizationService getAuthorizationService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.base.service.ejb.AuthorizationService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.base.service.ejb.AuthorizationServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ejb.ConfigurationService}.
	 */
	public static com.soffid.iam.base.service.ejb.ConfigurationService getConfigurationService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.base.service.ejb.ConfigurationService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.base.service.ejb.ConfigurationServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ejb.GenAIProviderService}.
	 */
	public static com.soffid.iam.base.service.ejb.GenAIProviderService getGenAIProviderService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.base.service.ejb.GenAIProviderService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.base.service.ejb.GenAIProviderServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ejb.LicenseService}.
	 */
	public static com.soffid.iam.base.service.ejb.LicenseService getLicenseService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.base.service.ejb.LicenseService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.base.service.ejb.LicenseServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ejb.PreferencesService}.
	 */
	public static com.soffid.iam.base.service.ejb.PreferencesService getPreferencesService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.base.service.ejb.PreferencesService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.base.service.ejb.PreferencesServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ejb.ServerPluginService}.
	 */
	public static com.soffid.iam.base.service.ejb.ServerPluginService getServerPluginService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.base.service.ejb.ServerPluginService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.base.service.ejb.ServerPluginServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ejb.StatsService}.
	 */
	public static com.soffid.iam.base.service.ejb.StatsService getStatsService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.base.service.ejb.StatsService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.base.service.ejb.StatsServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ejb.TenantService}.
	 */
	public static com.soffid.iam.base.service.ejb.TenantService getTenantService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.base.service.ejb.TenantService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.base.service.ejb.TenantServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ejb.UserService}.
	 */
	public static com.soffid.iam.base.service.ejb.UserService getUserService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.base.service.ejb.UserService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.base.service.ejb.UserServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.bpm.service.ejb.BpmConfigService}.
	 */
	public static com.soffid.iam.bpm.service.ejb.BpmConfigService getBpmConfigService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.bpm.service.ejb.BpmConfigService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.bpm.service.ejb.BpmConfigServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.bpm.service.ejb.BpmEngine}.
	 */
	public static com.soffid.iam.bpm.service.ejb.BpmEngine getBpmEngine()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.bpm.service.ejb.BpmEngine) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.bpm.service.ejb.BpmEngineHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.bpm.service.ejb.BpmJobExecutor}.
	 */
	public static com.soffid.iam.bpm.service.ejb.BpmJobExecutor getBpmJobExecutor()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.bpm.service.ejb.BpmJobExecutor) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.bpm.service.ejb.BpmJobExecutorHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.doc.service.ejb.DocumentService}.
	 */
	public static com.soffid.iam.doc.service.ejb.DocumentService getDocumentService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.doc.service.ejb.DocumentService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.doc.service.ejb.DocumentServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.ApplicationService}.
	 */
	public static com.soffid.iam.iga.service.ejb.ApplicationService getApplicationService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.ApplicationService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.ApplicationServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.AttributeTranslationService}.
	 */
	public static com.soffid.iam.iga.service.ejb.AttributeTranslationService getAttributeTranslationService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.AttributeTranslationService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.AttributeTranslationServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.CustomObjectService}.
	 */
	public static com.soffid.iam.iga.service.ejb.CustomObjectService getCustomObjectService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.CustomObjectService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.CustomObjectServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.DispatcherService}.
	 */
	public static com.soffid.iam.iga.service.ejb.DispatcherService getDispatcherService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.DispatcherService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.DispatcherServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.DomainService}.
	 */
	public static com.soffid.iam.iga.service.ejb.DomainService getDomainService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.DomainService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.DomainServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.EntitlementDelegationService}.
	 */
	public static com.soffid.iam.iga.service.ejb.EntitlementDelegationService getEntitlementDelegationService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.EntitlementDelegationService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.EntitlementDelegationServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.GroupService}.
	 */
	public static com.soffid.iam.iga.service.ejb.GroupService getGroupService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.GroupService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.GroupServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.GroupTypeService}.
	 */
	public static com.soffid.iam.iga.service.ejb.GroupTypeService getGroupTypeService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.GroupTypeService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.GroupTypeServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.MailListsService}.
	 */
	public static com.soffid.iam.iga.service.ejb.MailListsService getMailListsService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.MailListsService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.MailListsServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.MailService}.
	 */
	public static com.soffid.iam.iga.service.ejb.MailService getMailService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.MailService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.MailServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.PrinterService}.
	 */
	public static com.soffid.iam.iga.service.ejb.PrinterService getPrinterService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.PrinterService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.PrinterServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.RulesService}.
	 */
	public static com.soffid.iam.iga.service.ejb.RulesService getRulesService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.RulesService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.RulesServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.SelfService}.
	 */
	public static com.soffid.iam.iga.service.ejb.SelfService getSelfService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.SelfService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.SelfServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ejb.UserDomainService}.
	 */
	public static com.soffid.iam.iga.service.ejb.UserDomainService getUserDomainService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.iga.service.ejb.UserDomainService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.iga.service.ejb.UserDomainServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.ejb.ApplicationBootService}.
	 */
	public static com.soffid.iam.impl.service.ejb.ApplicationBootService getApplicationBootService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.impl.service.ejb.ApplicationBootService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.impl.service.ejb.ApplicationBootServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.ejb.ApplicationShutdownService}.
	 */
	public static com.soffid.iam.impl.service.ejb.ApplicationShutdownService getApplicationShutdownService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.impl.service.ejb.ApplicationShutdownService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.impl.service.ejb.ApplicationShutdownServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.ejb.AsyncRunnerService}.
	 */
	public static com.soffid.iam.impl.service.ejb.AsyncRunnerService getAsyncRunnerService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.impl.service.ejb.AsyncRunnerService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.impl.service.ejb.AsyncRunnerServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.ejb.AuthorizationInformationService}.
	 */
	public static com.soffid.iam.impl.service.ejb.AuthorizationInformationService getAuthorizationInformationService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.impl.service.ejb.AuthorizationInformationService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.impl.service.ejb.AuthorizationInformationServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.ejb.CreateDisableUserService}.
	 */
	public static com.soffid.iam.impl.service.ejb.CreateDisableUserService getCreateDisableUserService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.impl.service.ejb.CreateDisableUserService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.impl.service.ejb.CreateDisableUserServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.ejb.CrudRegistryService}.
	 */
	public static com.soffid.iam.impl.service.ejb.CrudRegistryService getCrudRegistryService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.impl.service.ejb.CrudRegistryService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.impl.service.ejb.CrudRegistryServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.ejb.OTPValidationService}.
	 */
	public static com.soffid.iam.impl.service.ejb.OTPValidationService getOTPValidationService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.impl.service.ejb.OTPValidationService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.impl.service.ejb.OTPValidationServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.pam.service.ejb.NetworkDiscoveryService}.
	 */
	public static com.soffid.iam.pam.service.ejb.NetworkDiscoveryService getNetworkDiscoveryService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.pam.service.ejb.NetworkDiscoveryService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.pam.service.ejb.NetworkDiscoveryServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.pam.service.ejb.PamPolicyService}.
	 */
	public static com.soffid.iam.pam.service.ejb.PamPolicyService getPamPolicyService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.pam.service.ejb.PamPolicyService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.pam.service.ejb.PamPolicyServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.pam.service.ejb.PamSessionService}.
	 */
	public static com.soffid.iam.pam.service.ejb.PamSessionService getPamSessionService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.pam.service.ejb.PamSessionService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.pam.service.ejb.PamSessionServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.ejb.AuditService}.
	 */
	public static com.soffid.iam.rc.service.ejb.AuditService getAuditService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.rc.service.ejb.AuditService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.rc.service.ejb.AuditServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.ejb.GeoInformationService}.
	 */
	public static com.soffid.iam.rc.service.ejb.GeoInformationService getGeoInformationService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.rc.service.ejb.GeoInformationService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.rc.service.ejb.GeoInformationServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.ejb.IssuePolicyService}.
	 */
	public static com.soffid.iam.rc.service.ejb.IssuePolicyService getIssuePolicyService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.rc.service.ejb.IssuePolicyService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.rc.service.ejb.IssuePolicyServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.ejb.IssueService}.
	 */
	public static com.soffid.iam.rc.service.ejb.IssueService getIssueService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.rc.service.ejb.IssueService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.rc.service.ejb.IssueServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.ejb.NetworkIntelligenceService}.
	 */
	public static com.soffid.iam.rc.service.ejb.NetworkIntelligenceService getNetworkIntelligenceService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.rc.service.ejb.NetworkIntelligenceService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.rc.service.ejb.NetworkIntelligenceServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.ejb.SamlService}.
	 */
	public static com.soffid.iam.rc.service.ejb.SamlService getSamlService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.rc.service.ejb.SamlService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.rc.service.ejb.SamlServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.ejb.SoDRuleService}.
	 */
	public static com.soffid.iam.rc.service.ejb.SoDRuleService getSoDRuleService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.rc.service.ejb.SoDRuleService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.rc.service.ejb.SoDRuleServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.reconcile.service.ejb.ReconcileService}.
	 */
	public static com.soffid.iam.reconcile.service.ejb.ReconcileService getReconcileService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.reconcile.service.ejb.ReconcileService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.reconcile.service.ejb.ReconcileServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.ejb.ScheduledTaskService}.
	 */
	public static com.soffid.iam.sync.service.ejb.ScheduledTaskService getScheduledTaskService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.sync.service.ejb.ScheduledTaskService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.sync.service.ejb.ScheduledTaskServiceHome.JNDI_NAME);
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.ejb.SyncServerService}.
	 */
	public static com.soffid.iam.sync.service.ejb.SyncServerService getSyncServerService()
		throws javax.naming.NamingException
	{

		return (com.soffid.iam.sync.service.ejb.SyncServerService) 
			new javax.naming.InitialContext().
				lookup(com.soffid.iam.sync.service.ejb.SyncServerServiceHome.JNDI_NAME);
	}

}
