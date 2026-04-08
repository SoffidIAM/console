//
// (C) 2013 Soffid
//
//

package com.soffid.iam.remote;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;


import com.soffid.iam.config.Config;
import com.soffid.iam.exception.InternalErrorException;
import com.soffid.iam.remote.RemoteInvokerFactory;
import com.soffid.iam.remote.URLManager;

/**
 * Locates and provides all available application services.
 */
public class RemoteServiceLocator
{

	String server = null;
	String authToken = null;
	String tenant = null;
	public static RemoteServiceLocatorProxy serviceLocatorProxy = null;
	
	public RemoteServiceLocator()
	{
	}

	public RemoteServiceLocator(String server)
	{
		setServer (server);
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getTenant() {
		return tenant;
	}

	public void setTenant(String tenant) {
		this.tenant = tenant;
	}

	
	private static int roundRobin = 0;
	
	public Object getRemoteService (String serviceName) throws IOException, com.soffid.iam.exception.InternalErrorException {
		Object robj;
		RemoteInvokerFactory factory = new RemoteInvokerFactory();
		
		Config config = Config.getConfig();

		String list[];
		if (server == null) {
			list = config.getServerList().split("[, ]+");
		} else {
			list = new String[] {server} ;
		}
			
		roundRobin ++;
		Exception lastException  = null;
		for (int i = 0; i < list.length; i++) {
			URLManager m = null;
			try {
				m = new URLManager(list[ (i+roundRobin) % list.length ]);
				if (authToken == null)
	                return factory.getInvoker(m.getHttpURL(serviceName));
	            else

	                return factory.getInvoker(m.getHttpURL(serviceName), tenant, authToken);
			} catch (Exception e) {
				lastException = e;
			}
		}
		throw new IOException ("Unable to locate remote service "+serviceName, lastException);
	}
 
 	/**
	 * Gets the remote service AccessLogService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.am.service.AccessLogService getAccessLogService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.am.service.AccessLogService) serviceLocatorProxy.getService("com.soffid.iam.am.service.AccessLogService");
		else
			return ( com.soffid.iam.am.service.AccessLogService ) getRemoteService ("/seycon/com.soffid.iam.am.service.AccessLogService");
	}
	
	/**
	 * Gets the remote service BrowserService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.am.service.BrowserService getBrowserService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.am.service.BrowserService) serviceLocatorProxy.getService("com.soffid.iam.am.service.BrowserService");
		else
			return ( com.soffid.iam.am.service.BrowserService ) getRemoteService ("/seycon/com.soffid.iam.am.service.BrowserService");
	}
	
	/**
	 * Gets the remote service CertificateValidationService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.am.service.CertificateValidationService getCertificateValidationService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.am.service.CertificateValidationService) serviceLocatorProxy.getService("com.soffid.iam.am.service.CertificateValidationService");
		else
			return ( com.soffid.iam.am.service.CertificateValidationService ) getRemoteService ("/seycon/com.soffid.iam.am.service.CertificateValidationService");
	}
	
	/**
	 * Gets the remote service EntryPointService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.am.service.EntryPointService getEntryPointService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.am.service.EntryPointService) serviceLocatorProxy.getService("com.soffid.iam.am.service.EntryPointService");
		else
			return ( com.soffid.iam.am.service.EntryPointService ) getRemoteService ("/seycon/com.soffid.iam.am.service.EntryPointService");
	}
	
	/**
	 * Gets the remote service NetworkService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.am.service.NetworkService getNetworkService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.am.service.NetworkService) serviceLocatorProxy.getService("com.soffid.iam.am.service.NetworkService");
		else
			return ( com.soffid.iam.am.service.NetworkService ) getRemoteService ("/seycon/com.soffid.iam.am.service.NetworkService");
	}
	
	/**
	 * Gets the remote service PasswordService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.am.service.PasswordService getPasswordService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.am.service.PasswordService) serviceLocatorProxy.getService("com.soffid.iam.am.service.PasswordService");
		else
			return ( com.soffid.iam.am.service.PasswordService ) getRemoteService ("/seycon/com.soffid.iam.am.service.PasswordService");
	}
	
	/**
	 * Gets the remote service ServiceService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.am.service.ServiceService getServiceService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.am.service.ServiceService) serviceLocatorProxy.getService("com.soffid.iam.am.service.ServiceService");
		else
			return ( com.soffid.iam.am.service.ServiceService ) getRemoteService ("/seycon/com.soffid.iam.am.service.ServiceService");
	}
	
	/**
	 * Gets the remote service SessionService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.am.service.SessionService getSessionService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.am.service.SessionService) serviceLocatorProxy.getService("com.soffid.iam.am.service.SessionService");
		else
			return ( com.soffid.iam.am.service.SessionService ) getRemoteService ("/seycon/com.soffid.iam.am.service.SessionService");
	}
	
	/**
	 * Gets the remote service AccountService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.base.service.AccountService getAccountService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.base.service.AccountService) serviceLocatorProxy.getService("com.soffid.iam.base.service.AccountService");
		else
			return ( com.soffid.iam.base.service.AccountService ) getRemoteService ("/seycon/com.soffid.iam.base.service.AccountService");
	}
	
	/**
	 * Gets the remote service AdditionalDataService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.base.service.AdditionalDataService getAdditionalDataService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.base.service.AdditionalDataService) serviceLocatorProxy.getService("com.soffid.iam.base.service.AdditionalDataService");
		else
			return ( com.soffid.iam.base.service.AdditionalDataService ) getRemoteService ("/seycon/com.soffid.iam.base.service.AdditionalDataService");
	}
	
	/**
	 * Gets the remote service ConfigurationService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.base.service.ConfigurationService getConfigurationService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.base.service.ConfigurationService) serviceLocatorProxy.getService("com.soffid.iam.base.service.ConfigurationService");
		else
			return ( com.soffid.iam.base.service.ConfigurationService ) getRemoteService ("/seycon/com.soffid.iam.base.service.ConfigurationService");
	}
	
	/**
	 * Gets the remote service UserService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.base.service.UserService getUserService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.base.service.UserService) serviceLocatorProxy.getService("com.soffid.iam.base.service.UserService");
		else
			return ( com.soffid.iam.base.service.UserService ) getRemoteService ("/seycon/com.soffid.iam.base.service.UserService");
	}
	
	/**
	 * Gets the remote service BpmEngine.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.bpm.service.BpmEngine getBpmEngine( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.bpm.service.BpmEngine) serviceLocatorProxy.getService("com.soffid.iam.bpm.service.BpmEngine");
		else
			return ( com.soffid.iam.bpm.service.BpmEngine ) getRemoteService ("/seycon/com.soffid.iam.bpm.service.BpmEngine");
	}
	
	/**
	 * Gets the remote service ApplicationService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.ApplicationService getApplicationService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.ApplicationService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.ApplicationService");
		else
			return ( com.soffid.iam.iga.service.ApplicationService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.ApplicationService");
	}
	
	/**
	 * Gets the remote service AttributeTranslationService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.AttributeTranslationService getAttributeTranslationService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.AttributeTranslationService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.AttributeTranslationService");
		else
			return ( com.soffid.iam.iga.service.AttributeTranslationService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.AttributeTranslationService");
	}
	
	/**
	 * Gets the remote service CustomObjectService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.CustomObjectService getCustomObjectService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.CustomObjectService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.CustomObjectService");
		else
			return ( com.soffid.iam.iga.service.CustomObjectService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.CustomObjectService");
	}
	
	/**
	 * Gets the remote service DispatcherService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.DispatcherService getDispatcherService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.DispatcherService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.DispatcherService");
		else
			return ( com.soffid.iam.iga.service.DispatcherService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.DispatcherService");
	}
	
	/**
	 * Gets the remote service DomainService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.DomainService getDomainService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.DomainService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.DomainService");
		else
			return ( com.soffid.iam.iga.service.DomainService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.DomainService");
	}
	
	/**
	 * Gets the remote service GroupService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.GroupService getGroupService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.GroupService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.GroupService");
		else
			return ( com.soffid.iam.iga.service.GroupService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.GroupService");
	}
	
	/**
	 * Gets the remote service GroupTypeService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.GroupTypeService getGroupTypeService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.GroupTypeService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.GroupTypeService");
		else
			return ( com.soffid.iam.iga.service.GroupTypeService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.GroupTypeService");
	}
	
	/**
	 * Gets the remote service MailListsService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.MailListsService getMailListsService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.MailListsService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.MailListsService");
		else
			return ( com.soffid.iam.iga.service.MailListsService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.MailListsService");
	}
	
	/**
	 * Gets the remote service MailService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.MailService getMailService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.MailService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.MailService");
		else
			return ( com.soffid.iam.iga.service.MailService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.MailService");
	}
	
	/**
	 * Gets the remote service PrinterService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.PrinterService getPrinterService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.PrinterService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.PrinterService");
		else
			return ( com.soffid.iam.iga.service.PrinterService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.PrinterService");
	}
	
	/**
	 * Gets the remote service RulesService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.RulesService getRulesService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.RulesService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.RulesService");
		else
			return ( com.soffid.iam.iga.service.RulesService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.RulesService");
	}
	
	/**
	 * Gets the remote service UserDomainService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.iga.service.UserDomainService getUserDomainService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.iga.service.UserDomainService) serviceLocatorProxy.getService("com.soffid.iam.iga.service.UserDomainService");
		else
			return ( com.soffid.iam.iga.service.UserDomainService ) getRemoteService ("/seycon/com.soffid.iam.iga.service.UserDomainService");
	}
	
	/**
	 * Gets the remote service OTPValidationService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.impl.service.OTPValidationService getOTPValidationService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.impl.service.OTPValidationService) serviceLocatorProxy.getService("com.soffid.iam.impl.service.OTPValidationService");
		else
			return ( com.soffid.iam.impl.service.OTPValidationService ) getRemoteService ("/seycon/com.soffid.iam.impl.service.OTPValidationService");
	}
	
	/**
	 * Gets the remote service NetworkDiscoveryService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.pam.service.NetworkDiscoveryService getNetworkDiscoveryService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.pam.service.NetworkDiscoveryService) serviceLocatorProxy.getService("com.soffid.iam.pam.service.NetworkDiscoveryService");
		else
			return ( com.soffid.iam.pam.service.NetworkDiscoveryService ) getRemoteService ("/seycon/com.soffid.iam.pam.service.NetworkDiscoveryService");
	}
	
	/**
	 * Gets the remote service IssueService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.rc.service.IssueService getIssueService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.rc.service.IssueService) serviceLocatorProxy.getService("com.soffid.iam.rc.service.IssueService");
		else
			return ( com.soffid.iam.rc.service.IssueService ) getRemoteService ("/seycon/com.soffid.iam.rc.service.IssueService");
	}
	
	/**
	 * Gets the remote service AgentManager.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.sync.agent.AgentManager getAgentManager( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.sync.agent.AgentManager) serviceLocatorProxy.getService("com.soffid.iam.sync.agent.AgentManager");
		else
			return ( com.soffid.iam.sync.agent.AgentManager ) getRemoteService ("/seycon/AgentManager");
	}
	
	/**
	 * Gets the remote service CertificateEnrollService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.sync.service.CertificateEnrollService getCertificateEnrollService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.sync.service.CertificateEnrollService) serviceLocatorProxy.getService("com.soffid.iam.sync.service.CertificateEnrollService");
		else
			return ( com.soffid.iam.sync.service.CertificateEnrollService ) getRemoteService ("/seycon/CertificateEnrollService");
	}
	
	/**
	 * Gets the remote service ConsoleLogonService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.sync.service.ConsoleLogonService getConsoleLogonService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.sync.service.ConsoleLogonService) serviceLocatorProxy.getService("com.soffid.iam.sync.service.ConsoleLogonService");
		else
			return ( com.soffid.iam.sync.service.ConsoleLogonService ) getRemoteService ("/SEU/LogonService");
	}
	
	/**
	 * Gets the remote service LogonService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.sync.service.LogonService getLogonService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.sync.service.LogonService) serviceLocatorProxy.getService("com.soffid.iam.sync.service.LogonService");
		else
			return ( com.soffid.iam.sync.service.LogonService ) getRemoteService ("/seycon/com.soffid.iam.sync.service.LogonService");
	}
	
	/**
	 * Gets the remote service PamProxySessionService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.sync.service.PamProxySessionService getPamProxySessionService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.sync.service.PamProxySessionService) serviceLocatorProxy.getService("com.soffid.iam.sync.service.PamProxySessionService");
		else
			return ( com.soffid.iam.sync.service.PamProxySessionService ) getRemoteService ("/seycon/com.soffid.iam.sync.service.PamProxySessionService");
	}
	
	/**
	 * Gets the remote service SecretStoreService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.sync.service.SecretStoreService getSecretStoreService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.sync.service.SecretStoreService) serviceLocatorProxy.getService("com.soffid.iam.sync.service.SecretStoreService");
		else
			return ( com.soffid.iam.sync.service.SecretStoreService ) getRemoteService ("/seycon/com.soffid.iam.sync.service.SecretStoreService");
	}
	
	/**
	 * Gets the remote service ServerService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.sync.service.ServerService getServerService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.sync.service.ServerService) serviceLocatorProxy.getService("com.soffid.iam.sync.service.ServerService");
		else
			return ( com.soffid.iam.sync.service.ServerService ) getRemoteService ("/seycon/com.soffid.iam.sync.service.ServerService");
	}
	
	/**
	 * Gets the remote service SyncServerStatsService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.sync.service.SyncServerStatsService getSyncServerStatsService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.sync.service.SyncServerStatsService) serviceLocatorProxy.getService("com.soffid.iam.sync.service.SyncServerStatsService");
		else
			return ( com.soffid.iam.sync.service.SyncServerStatsService ) getRemoteService ("SEU/SyncServerStatsService");
	}
	
	/**
	 * Gets the remote service SyncStatusService.
	 *
	 * @return Remote object
	 **/
	public com.soffid.iam.sync.service.SyncStatusService getSyncStatusService( ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return (com.soffid.iam.sync.service.SyncStatusService) serviceLocatorProxy.getService("com.soffid.iam.sync.service.SyncStatusService");
		else
			return ( com.soffid.iam.sync.service.SyncStatusService ) getRemoteService ("SEU/SyncStatusService");
	}
	
	/**
	 * Gets any remote service.
	 *
	 * @return Remote object
	 **/
	public Object getService( String serviceName ) throws IOException, com.soffid.iam.exception.InternalErrorException {
		if (serviceLocatorProxy != null && server == null)
			return serviceLocatorProxy.getService(serviceName);
		else
			return getRemoteService ("/seycon/"+serviceName);
	}
	

}
