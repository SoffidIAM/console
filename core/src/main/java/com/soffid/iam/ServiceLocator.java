//
// (C) 2013 Soffid
//
//

package com.soffid.iam;

/**
 * Locates and provides all available application services.
 */
public class ServiceLocator
{

	private ServiceLocator()
	{
		// shouldn't be instantiated
	}

	/**
	 * The shared instance of this ServiceLocator.
	 */
	private final static ServiceLocator instance = new ServiceLocator();

	/**
	 * Gets the shared instance of this Class
	 *
	 * @return the shared service locator instance.
	 */
	public static final ServiceLocator instance()
	{
		return instance;
	}

	/**
	 * The bean factory reference instance.
	 */
	private org.springframework.beans.factory.access.BeanFactoryReference beanFactoryReference;
	
	/**
	 * The bean factory reference location.
	 */
	private String beanFactoryReferenceLocation;
	
	/**
	 * The bean factory reference id.
	 */
	private String beanRefFactoryReferenceId;

	/**
	 * Initializes the Spring application context from
	 * the given <code>beanFactoryReferenceLocation</code>.  If <code>null</code>
	 * is specified for the <code>beanFactoryReferenceLocation</code>
	 * then the default application context will be used.
	 *
	 * @param beanFactoryReferenceLocation the location of the beanRefFactory reference.
	 */
	public synchronized void init(final String beanFactoryReferenceLocation, final String beanRefFactoryReferenceId)
	{
		this.beanFactoryReferenceLocation = beanFactoryReferenceLocation;
		this.beanRefFactoryReferenceId = beanRefFactoryReferenceId;
		this.beanFactoryReference = null;
	}

	/**
	 * Initializes the Spring application context from
	 * the given <code>beanFactoryReferenceLocation</code>.  If <code>null</code>
	 * is specified for the <code>beanFactoryReferenceLocation</code>
	 * then the default application context will be used.
	 *
	 * @param beanFactoryReferenceLocation the location of the beanRefFactory reference.
	 */
	public synchronized void init(final String beanFactoryReferenceLocation)
	{
		this.beanFactoryReferenceLocation = beanFactoryReferenceLocation;
		this.beanFactoryReference = null;
	}
	/**
	 * The default bean reference factory location.
	 */
	private final String DEFAULT_BEAN_REFERENCE_LOCATION = "beanRefFactory.xml";
	
	/**
	 * The default bean reference factory ID.
	 */
	private final String DEFAULT_BEAN_REFERENCE_ID = "beanRefFactory";

	/**
	 * Gets the Spring ApplicationContext.
	 */
	public synchronized org.springframework.context.ApplicationContext getContext()
	{
		if (this.beanFactoryReference == null)
		{
			if (this.beanFactoryReferenceLocation == null)
			{
				this.beanFactoryReferenceLocation = DEFAULT_BEAN_REFERENCE_LOCATION;
			}
			if (this.beanRefFactoryReferenceId == null)
			{
				this.beanRefFactoryReferenceId = DEFAULT_BEAN_REFERENCE_ID;
			}
			org.springframework.beans.factory.access.BeanFactoryLocator beanFactoryLocator =
				org.springframework.context.access.ContextSingletonBeanFactoryLocator.getInstance(
					this.beanFactoryReferenceLocation);
			this.beanFactoryReference = beanFactoryLocator.useBeanFactory(this.beanRefFactoryReferenceId);
		}
		return (org.springframework.context.ApplicationContext)this.beanFactoryReference.getFactory();
	}

	/**
	 * Shuts down the ServiceLocator and releases any used resources.
	 */
	public synchronized void shutdown()
	{
		if (this.beanFactoryReference != null)
		{
			this.beanFactoryReference.release();
			this.beanFactoryReference = null;
		}
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.AccessLogService}.
	 */
	public final com.soffid.iam.am.service.AccessLogService getAccessLogService()
	{
		return (com.soffid.iam.am.service.AccessLogService)
			getContext().getBean("com.soffid.iam.am.service.AccessLogService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.BrowserService}.
	 */
	public final com.soffid.iam.am.service.BrowserService getBrowserService()
	{
		return (com.soffid.iam.am.service.BrowserService)
			getContext().getBean("com.soffid.iam.am.service.BrowserService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.CertificateValidationService}.
	 */
	public final com.soffid.iam.am.service.CertificateValidationService getCertificateValidationService()
	{
		return (com.soffid.iam.am.service.CertificateValidationService)
			getContext().getBean("com.soffid.iam.am.service.CertificateValidationService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.EntryPointService}.
	 */
	public final com.soffid.iam.am.service.EntryPointService getEntryPointService()
	{
		return (com.soffid.iam.am.service.EntryPointService)
			getContext().getBean("com.soffid.iam.am.service.EntryPointService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.NetworkService}.
	 */
	public final com.soffid.iam.am.service.NetworkService getNetworkService()
	{
		return (com.soffid.iam.am.service.NetworkService)
			getContext().getBean("com.soffid.iam.am.service.NetworkService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.PasswordManagerService}.
	 */
	public final com.soffid.iam.am.service.PasswordManagerService getPasswordManagerService()
	{
		return (com.soffid.iam.am.service.PasswordManagerService)
			getContext().getBean("com.soffid.iam.am.service.PasswordManagerService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.PasswordService}.
	 */
	public final com.soffid.iam.am.service.PasswordService getPasswordService()
	{
		return (com.soffid.iam.am.service.PasswordService)
			getContext().getBean("com.soffid.iam.am.service.PasswordService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.ServiceService}.
	 */
	public final com.soffid.iam.am.service.ServiceService getServiceService()
	{
		return (com.soffid.iam.am.service.ServiceService)
			getContext().getBean("com.soffid.iam.am.service.ServiceService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.SessionService}.
	 */
	public final com.soffid.iam.am.service.SessionService getSessionService()
	{
		return (com.soffid.iam.am.service.SessionService)
			getContext().getBean("com.soffid.iam.am.service.SessionService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.SignalService}.
	 */
	public final com.soffid.iam.am.service.SignalService getSignalService()
	{
		return (com.soffid.iam.am.service.SignalService)
			getContext().getBean("com.soffid.iam.am.service.SignalService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.am.service.VaultService}.
	 */
	public final com.soffid.iam.am.service.VaultService getVaultService()
	{
		return (com.soffid.iam.am.service.VaultService)
			getContext().getBean("com.soffid.iam.am.service.VaultService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.AccountService}.
	 */
	public final com.soffid.iam.base.service.AccountService getAccountService()
	{
		return (com.soffid.iam.base.service.AccountService)
			getContext().getBean("com.soffid.iam.base.service.AccountService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.AdditionalDataService}.
	 */
	public final com.soffid.iam.base.service.AdditionalDataService getAdditionalDataService()
	{
		return (com.soffid.iam.base.service.AdditionalDataService)
			getContext().getBean("com.soffid.iam.base.service.AdditionalDataService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.AuthorizationService}.
	 */
	public final com.soffid.iam.base.service.AuthorizationService getAuthorizationService()
	{
		return (com.soffid.iam.base.service.AuthorizationService)
			getContext().getBean("com.soffid.iam.base.service.AuthorizationService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ConfigurationService}.
	 */
	public final com.soffid.iam.base.service.ConfigurationService getConfigurationService()
	{
		return (com.soffid.iam.base.service.ConfigurationService)
			getContext().getBean("com.soffid.iam.base.service.ConfigurationService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.GenAIProviderService}.
	 */
	public final com.soffid.iam.base.service.GenAIProviderService getGenAIProviderService()
	{
		return (com.soffid.iam.base.service.GenAIProviderService)
			getContext().getBean("com.soffid.iam.base.service.GenAIProviderService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.LicenseService}.
	 */
	public final com.soffid.iam.base.service.LicenseService getLicenseService()
	{
		return (com.soffid.iam.base.service.LicenseService)
			getContext().getBean("com.soffid.iam.base.service.LicenseService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.PreferencesService}.
	 */
	public final com.soffid.iam.base.service.PreferencesService getPreferencesService()
	{
		return (com.soffid.iam.base.service.PreferencesService)
			getContext().getBean("com.soffid.iam.base.service.PreferencesService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.RecycleBeanService}.
	 */
	public final com.soffid.iam.base.service.RecycleBeanService getRecycleBeanService()
	{
		return (com.soffid.iam.base.service.RecycleBeanService)
			getContext().getBean("com.soffid.iam.base.service.RecycleBeanService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.ServerPluginService}.
	 */
	public final com.soffid.iam.base.service.ServerPluginService getServerPluginService()
	{
		return (com.soffid.iam.base.service.ServerPluginService)
			getContext().getBean("com.soffid.iam.base.service.ServerPluginService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.StatsService}.
	 */
	public final com.soffid.iam.base.service.StatsService getStatsService()
	{
		return (com.soffid.iam.base.service.StatsService)
			getContext().getBean("com.soffid.iam.base.service.StatsService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.TenantService}.
	 */
	public final com.soffid.iam.base.service.TenantService getTenantService()
	{
		return (com.soffid.iam.base.service.TenantService)
			getContext().getBean("com.soffid.iam.base.service.TenantService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.base.service.UserService}.
	 */
	public final com.soffid.iam.base.service.UserService getUserService()
	{
		return (com.soffid.iam.base.service.UserService)
			getContext().getBean("com.soffid.iam.base.service.UserService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.bpm.service.BpmConfigService}.
	 */
	public final com.soffid.iam.bpm.service.BpmConfigService getBpmConfigService()
	{
		return (com.soffid.iam.bpm.service.BpmConfigService)
			getContext().getBean("com.soffid.iam.bpm.service.BpmConfigService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.bpm.service.BpmEngine}.
	 */
	public final com.soffid.iam.bpm.service.BpmEngine getBpmEngine()
	{
		return (com.soffid.iam.bpm.service.BpmEngine)
			getContext().getBean("com.soffid.iam.bpm.service.BpmEngine");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.bpm.service.BpmJobExecutor}.
	 */
	public final com.soffid.iam.bpm.service.BpmJobExecutor getBpmJobExecutor()
	{
		return (com.soffid.iam.bpm.service.BpmJobExecutor)
			getContext().getBean("com.soffid.iam.bpm.service.BpmJobExecutor");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.doc.service.DocumentService}.
	 */
	public final com.soffid.iam.doc.service.DocumentService getDocumentService()
	{
		return (com.soffid.iam.doc.service.DocumentService)
			getContext().getBean("com.soffid.iam.doc.service.DocumentService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.ApplicationService}.
	 */
	public final com.soffid.iam.iga.service.ApplicationService getApplicationService()
	{
		return (com.soffid.iam.iga.service.ApplicationService)
			getContext().getBean("com.soffid.iam.iga.service.ApplicationService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.AttributeTranslationService}.
	 */
	public final com.soffid.iam.iga.service.AttributeTranslationService getAttributeTranslationService()
	{
		return (com.soffid.iam.iga.service.AttributeTranslationService)
			getContext().getBean("com.soffid.iam.iga.service.AttributeTranslationService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.AuthoritativeChangeService}.
	 */
	public final com.soffid.iam.iga.service.AuthoritativeChangeService getAuthoritativeChangeService()
	{
		return (com.soffid.iam.iga.service.AuthoritativeChangeService)
			getContext().getBean("com.soffid.iam.iga.service.AuthoritativeChangeService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.CustomObjectService}.
	 */
	public final com.soffid.iam.iga.service.CustomObjectService getCustomObjectService()
	{
		return (com.soffid.iam.iga.service.CustomObjectService)
			getContext().getBean("com.soffid.iam.iga.service.CustomObjectService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.DispatcherService}.
	 */
	public final com.soffid.iam.iga.service.DispatcherService getDispatcherService()
	{
		return (com.soffid.iam.iga.service.DispatcherService)
			getContext().getBean("com.soffid.iam.iga.service.DispatcherService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.DomainService}.
	 */
	public final com.soffid.iam.iga.service.DomainService getDomainService()
	{
		return (com.soffid.iam.iga.service.DomainService)
			getContext().getBean("com.soffid.iam.iga.service.DomainService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.EntitlementDelegationService}.
	 */
	public final com.soffid.iam.iga.service.EntitlementDelegationService getEntitlementDelegationService()
	{
		return (com.soffid.iam.iga.service.EntitlementDelegationService)
			getContext().getBean("com.soffid.iam.iga.service.EntitlementDelegationService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.GroupService}.
	 */
	public final com.soffid.iam.iga.service.GroupService getGroupService()
	{
		return (com.soffid.iam.iga.service.GroupService)
			getContext().getBean("com.soffid.iam.iga.service.GroupService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.GroupTypeService}.
	 */
	public final com.soffid.iam.iga.service.GroupTypeService getGroupTypeService()
	{
		return (com.soffid.iam.iga.service.GroupTypeService)
			getContext().getBean("com.soffid.iam.iga.service.GroupTypeService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.MailListsService}.
	 */
	public final com.soffid.iam.iga.service.MailListsService getMailListsService()
	{
		return (com.soffid.iam.iga.service.MailListsService)
			getContext().getBean("com.soffid.iam.iga.service.MailListsService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.MailService}.
	 */
	public final com.soffid.iam.iga.service.MailService getMailService()
	{
		return (com.soffid.iam.iga.service.MailService)
			getContext().getBean("com.soffid.iam.iga.service.MailService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.PrinterService}.
	 */
	public final com.soffid.iam.iga.service.PrinterService getPrinterService()
	{
		return (com.soffid.iam.iga.service.PrinterService)
			getContext().getBean("com.soffid.iam.iga.service.PrinterService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.RulesService}.
	 */
	public final com.soffid.iam.iga.service.RulesService getRulesService()
	{
		return (com.soffid.iam.iga.service.RulesService)
			getContext().getBean("com.soffid.iam.iga.service.RulesService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.SelfService}.
	 */
	public final com.soffid.iam.iga.service.SelfService getSelfService()
	{
		return (com.soffid.iam.iga.service.SelfService)
			getContext().getBean("com.soffid.iam.iga.service.SelfService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.iga.service.UserDomainService}.
	 */
	public final com.soffid.iam.iga.service.UserDomainService getUserDomainService()
	{
		return (com.soffid.iam.iga.service.UserDomainService)
			getContext().getBean("com.soffid.iam.iga.service.UserDomainService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.ACLService}.
	 */
	public final com.soffid.iam.impl.service.ACLService getACLService()
	{
		return (com.soffid.iam.impl.service.ACLService)
			getContext().getBean("com.soffid.iam.impl.service.ACLService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.AccountNameGenerator}.
	 */
	public final com.soffid.iam.impl.service.AccountNameGenerator getAccountNameGenerator()
	{
		return (com.soffid.iam.impl.service.AccountNameGenerator)
			getContext().getBean("com.soffid.iam.impl.service.AccountNameGenerator");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.ApplicationBootService}.
	 */
	public final com.soffid.iam.impl.service.ApplicationBootService getApplicationBootService()
	{
		return (com.soffid.iam.impl.service.ApplicationBootService)
			getContext().getBean("com.soffid.iam.impl.service.ApplicationBootService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.ApplicationShutdownService}.
	 */
	public final com.soffid.iam.impl.service.ApplicationShutdownService getApplicationShutdownService()
	{
		return (com.soffid.iam.impl.service.ApplicationShutdownService)
			getContext().getBean("com.soffid.iam.impl.service.ApplicationShutdownService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.AsyncRunnerService}.
	 */
	public final com.soffid.iam.impl.service.AsyncRunnerService getAsyncRunnerService()
	{
		return (com.soffid.iam.impl.service.AsyncRunnerService)
			getContext().getBean("com.soffid.iam.impl.service.AsyncRunnerService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.AuthorizationInformationService}.
	 */
	public final com.soffid.iam.impl.service.AuthorizationInformationService getAuthorizationInformationService()
	{
		return (com.soffid.iam.impl.service.AuthorizationInformationService)
			getContext().getBean("com.soffid.iam.impl.service.AuthorizationInformationService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.CreateDisableUserService}.
	 */
	public final com.soffid.iam.impl.service.CreateDisableUserService getCreateDisableUserService()
	{
		return (com.soffid.iam.impl.service.CreateDisableUserService)
			getContext().getBean("com.soffid.iam.impl.service.CreateDisableUserService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.CrudRegistryService}.
	 */
	public final com.soffid.iam.impl.service.CrudRegistryService getCrudRegistryService()
	{
		return (com.soffid.iam.impl.service.CrudRegistryService)
			getContext().getBean("com.soffid.iam.impl.service.CrudRegistryService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.InternalPasswordService}.
	 */
	public final com.soffid.iam.impl.service.InternalPasswordService getInternalPasswordService()
	{
		return (com.soffid.iam.impl.service.InternalPasswordService)
			getContext().getBean("com.soffid.iam.impl.service.InternalPasswordService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.LuceneIndexService}.
	 */
	public final com.soffid.iam.impl.service.LuceneIndexService getLuceneIndexService()
	{
		return (com.soffid.iam.impl.service.LuceneIndexService)
			getContext().getBean("com.soffid.iam.impl.service.LuceneIndexService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.OTPValidationService}.
	 */
	public final com.soffid.iam.impl.service.OTPValidationService getOTPValidationService()
	{
		return (com.soffid.iam.impl.service.OTPValidationService)
			getContext().getBean("com.soffid.iam.impl.service.OTPValidationService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.RuleEvaluatorService}.
	 */
	public final com.soffid.iam.impl.service.RuleEvaluatorService getRuleEvaluatorService()
	{
		return (com.soffid.iam.impl.service.RuleEvaluatorService)
			getContext().getBean("com.soffid.iam.impl.service.RuleEvaluatorService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.impl.service.SoffidEventListener}.
	 */
	public final com.soffid.iam.impl.service.SoffidEventListener getSoffidEventListener()
	{
		return (com.soffid.iam.impl.service.SoffidEventListener)
			getContext().getBean("com.soffid.iam.impl.service.SoffidEventListener");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.pam.service.NetworkDiscoveryService}.
	 */
	public final com.soffid.iam.pam.service.NetworkDiscoveryService getNetworkDiscoveryService()
	{
		return (com.soffid.iam.pam.service.NetworkDiscoveryService)
			getContext().getBean("com.soffid.iam.pam.service.NetworkDiscoveryService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.pam.service.PamPolicyService}.
	 */
	public final com.soffid.iam.pam.service.PamPolicyService getPamPolicyService()
	{
		return (com.soffid.iam.pam.service.PamPolicyService)
			getContext().getBean("com.soffid.iam.pam.service.PamPolicyService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.pam.service.PamSecurityHandlerService}.
	 */
	public final com.soffid.iam.pam.service.PamSecurityHandlerService getPamSecurityHandlerService()
	{
		return (com.soffid.iam.pam.service.PamSecurityHandlerService)
			getContext().getBean("com.soffid.iam.pam.service.PamSecurityHandlerService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.pam.service.PamSessionService}.
	 */
	public final com.soffid.iam.pam.service.PamSessionService getPamSessionService()
	{
		return (com.soffid.iam.pam.service.PamSessionService)
			getContext().getBean("com.soffid.iam.pam.service.PamSessionService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.AuditService}.
	 */
	public final com.soffid.iam.rc.service.AuditService getAuditService()
	{
		return (com.soffid.iam.rc.service.AuditService)
			getContext().getBean("com.soffid.iam.rc.service.AuditService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.GeoInformationService}.
	 */
	public final com.soffid.iam.rc.service.GeoInformationService getGeoInformationService()
	{
		return (com.soffid.iam.rc.service.GeoInformationService)
			getContext().getBean("com.soffid.iam.rc.service.GeoInformationService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.IssuePolicyService}.
	 */
	public final com.soffid.iam.rc.service.IssuePolicyService getIssuePolicyService()
	{
		return (com.soffid.iam.rc.service.IssuePolicyService)
			getContext().getBean("com.soffid.iam.rc.service.IssuePolicyService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.IssueService}.
	 */
	public final com.soffid.iam.rc.service.IssueService getIssueService()
	{
		return (com.soffid.iam.rc.service.IssueService)
			getContext().getBean("com.soffid.iam.rc.service.IssueService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.NetworkIntelligenceService}.
	 */
	public final com.soffid.iam.rc.service.NetworkIntelligenceService getNetworkIntelligenceService()
	{
		return (com.soffid.iam.rc.service.NetworkIntelligenceService)
			getContext().getBean("com.soffid.iam.rc.service.NetworkIntelligenceService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.SamlService}.
	 */
	public final com.soffid.iam.rc.service.SamlService getSamlService()
	{
		return (com.soffid.iam.rc.service.SamlService)
			getContext().getBean("com.soffid.iam.rc.service.SamlService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.rc.service.SoDRuleService}.
	 */
	public final com.soffid.iam.rc.service.SoDRuleService getSoDRuleService()
	{
		return (com.soffid.iam.rc.service.SoDRuleService)
			getContext().getBean("com.soffid.iam.rc.service.SoDRuleService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.reconcile.service.ReconcileService}.
	 */
	public final com.soffid.iam.reconcile.service.ReconcileService getReconcileService()
	{
		return (com.soffid.iam.reconcile.service.ReconcileService)
			getContext().getBean("com.soffid.iam.reconcile.service.ReconcileService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.service.impl.AttributeValidationService}.
	 */
	public final com.soffid.iam.service.impl.AttributeValidationService getAttributeValidationService()
	{
		return (com.soffid.iam.service.impl.AttributeValidationService)
			getContext().getBean("com.soffid.iam.service.impl.AttributeValidationService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.agent.AgentManager}.
	 */
	public final com.soffid.iam.sync.agent.AgentManager getAgentManager()
	{
		return (com.soffid.iam.sync.agent.AgentManager)
			getContext().getBean("com.soffid.iam.sync.agent.AgentManager");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.CertificateEnrollService}.
	 */
	public final com.soffid.iam.sync.service.CertificateEnrollService getCertificateEnrollService()
	{
		return (com.soffid.iam.sync.service.CertificateEnrollService)
			getContext().getBean("com.soffid.iam.sync.service.CertificateEnrollService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.ChangePasswordNotificationQueue}.
	 */
	public final com.soffid.iam.sync.service.ChangePasswordNotificationQueue getChangePasswordNotificationQueue()
	{
		return (com.soffid.iam.sync.service.ChangePasswordNotificationQueue)
			getContext().getBean("com.soffid.iam.sync.service.ChangePasswordNotificationQueue");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.ConsoleLogonService}.
	 */
	public final com.soffid.iam.sync.service.ConsoleLogonService getConsoleLogonService()
	{
		return (com.soffid.iam.sync.service.ConsoleLogonService)
			getContext().getBean("com.soffid.iam.sync.service.ConsoleLogonService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.LogCollectorService}.
	 */
	public final com.soffid.iam.sync.service.LogCollectorService getLogCollectorService()
	{
		return (com.soffid.iam.sync.service.LogCollectorService)
			getContext().getBean("com.soffid.iam.sync.service.LogCollectorService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.LogonService}.
	 */
	public final com.soffid.iam.sync.service.LogonService getLogonService()
	{
		return (com.soffid.iam.sync.service.LogonService)
			getContext().getBean("com.soffid.iam.sync.service.LogonService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.PamProxySessionService}.
	 */
	public final com.soffid.iam.sync.service.PamProxySessionService getPamProxySessionService()
	{
		return (com.soffid.iam.sync.service.PamProxySessionService)
			getContext().getBean("com.soffid.iam.sync.service.PamProxySessionService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.QueryService}.
	 */
	public final com.soffid.iam.sync.service.QueryService getQueryService()
	{
		return (com.soffid.iam.sync.service.QueryService)
			getContext().getBean("com.soffid.iam.sync.service.QueryService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.ScheduledTaskService}.
	 */
	public final com.soffid.iam.sync.service.ScheduledTaskService getScheduledTaskService()
	{
		return (com.soffid.iam.sync.service.ScheduledTaskService)
			getContext().getBean("com.soffid.iam.sync.service.ScheduledTaskService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.SecretConfigurationService}.
	 */
	public final com.soffid.iam.sync.service.SecretConfigurationService getSecretConfigurationService()
	{
		return (com.soffid.iam.sync.service.SecretConfigurationService)
			getContext().getBean("com.soffid.iam.sync.service.SecretConfigurationService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.SecretStoreService}.
	 */
	public final com.soffid.iam.sync.service.SecretStoreService getSecretStoreService()
	{
		return (com.soffid.iam.sync.service.SecretStoreService)
			getContext().getBean("com.soffid.iam.sync.service.SecretStoreService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.ServerService}.
	 */
	public final com.soffid.iam.sync.service.ServerService getServerService()
	{
		return (com.soffid.iam.sync.service.ServerService)
			getContext().getBean("com.soffid.iam.sync.service.ServerService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.SyncServerService}.
	 */
	public final com.soffid.iam.sync.service.SyncServerService getSyncServerService()
	{
		return (com.soffid.iam.sync.service.SyncServerService)
			getContext().getBean("com.soffid.iam.sync.service.SyncServerService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.SyncServerStatsService}.
	 */
	public final com.soffid.iam.sync.service.SyncServerStatsService getSyncServerStatsService()
	{
		return (com.soffid.iam.sync.service.SyncServerStatsService)
			getContext().getBean("com.soffid.iam.sync.service.SyncServerStatsService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.SyncStatusService}.
	 */
	public final com.soffid.iam.sync.service.SyncStatusService getSyncStatusService()
	{
		return (com.soffid.iam.sync.service.SyncStatusService)
			getContext().getBean("com.soffid.iam.sync.service.SyncStatusService");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.TaskGenerator}.
	 */
	public final com.soffid.iam.sync.service.TaskGenerator getTaskGenerator()
	{
		return (com.soffid.iam.sync.service.TaskGenerator)
			getContext().getBean("com.soffid.iam.sync.service.TaskGenerator");
	}

	/**
	 * Gets an instance of {@link com.soffid.iam.sync.service.TaskQueue}.
	 */
	public final com.soffid.iam.sync.service.TaskQueue getTaskQueue()
	{
		return (com.soffid.iam.sync.service.TaskQueue)
			getContext().getBean("com.soffid.iam.sync.service.TaskQueue");
	}

	/**
	 * Gets an instance of the given service.
	 */
	public final Object getService(String serviceName)
	{
		return getContext().getBean(serviceName);
	}

}
