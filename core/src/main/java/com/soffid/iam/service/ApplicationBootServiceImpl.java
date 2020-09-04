package com.soffid.iam.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.soffid.iam.api.Application;
import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Domain;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.OsType;
import com.soffid.iam.api.PasswordDomain;
import com.soffid.iam.api.PasswordPolicy;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.ScheduledTaskHandler;
import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.api.UserDomain;
import com.soffid.iam.api.UserType;
import com.soffid.iam.bpm.api.ConfigParameterVO;
import com.soffid.iam.bpm.service.BpmConfigService;
import com.soffid.iam.config.Config;
import com.soffid.iam.doc.nas.comm.DatabaseStrategy;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.service.impl.DatabaseParser;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.tools.db.persistence.XmlReader;
import com.soffid.tools.db.schema.Column;
import com.soffid.tools.db.schema.Database;
import com.soffid.tools.db.schema.ForeignKey;
import com.soffid.tools.db.schema.Table;

import es.caib.bpm.exception.BPMException;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;

public class ApplicationBootServiceImpl extends
		com.soffid.iam.service.ApplicationBootServiceBase implements
		ApplicationContextAware {

	/**
     * 
     */
	private com.soffid.iam.service.AccountService accountSvc;
	private EntryPointService peSvc;
	private AdditionalDataService tdSvc;
	private AuthorizationService autSvc;
	private com.soffid.iam.service.InternalPasswordService passSvc;
	private UserService usuariSvc;
	private NetworkService xarxaSvc;
	private com.soffid.iam.service.DispatcherService dispatcherSvc;
	private UserDomainService dominiSvc;
	private GroupService grupSvc;
	private ApplicationService appSvc;
	private ConfigurationService configSvc;
	private BpmConfigService bpmConfigSvc;
	private TenantService tenantService;
	Boolean started = Boolean.FALSE;

	private Log log = LogFactory
			.getLog(com.soffid.iam.service.ApplicationBootService.class);
	private ApplicationContext applicationContext;

	@Override
	protected void handleSyncServerBoot() throws Exception {
		loadServiceHandlers();
		ConfigurationCache.setProperty(
				"soffid.ui.maxrows", Integer.toString(Integer.MAX_VALUE)); //$NON-NLS-1$
		configureSystemProperties();
		ConfigurationCache.setProperty(
				"soffid.ui.maxrows", Integer.toString(Integer.MAX_VALUE)); //$NON-NLS-1$
		started = Boolean.TRUE;
	}

	@Override
	protected void handleConsoleBoot() throws Exception {
		log.info("Running console boot");
		
		ServiceLocator.instance();
		
		loadServiceHandlers();

		ConfigurationCache.setProperty("soffid.ui.maxrows", //$NON-NLS-1$
				Integer.toString(Integer.MAX_VALUE)); //$NON-NLS-1$

		ConfigurationCache.setProperty("soffid.ui.wildcards", "auto"); //$NON-NLS-1$ //$NON-NLS-2$
		
		getCrudRegistryService().registerDefaultHandlers();

		configureDatabase();

		configureIndexDir();

		Config.configureClient(getServerList(), getServerPort());

		configureWildcards();

		configureMaxRowsToLists();

		configureGlobalTimeOut();

		configureSystemProperties();

		createScheduledTasks();

		loadWorkflows();
		
		loadConnectors();
		
		started = Boolean.TRUE;
	}

	
	private void loadServiceHandlers ()
	{
		bpmConfigSvc = getBpmConfigService();
		configSvc = getConfigurationService();
		appSvc = getApplicationService();
		grupSvc = getGroupService();
		dominiSvc = getUserDomainService();
		dispatcherSvc = getDispatcherService();
		xarxaSvc = getNetworkService();
		usuariSvc = getUserService();
		passSvc = getInternalPasswordService();
		autSvc = getAuthorizationService();
		tdSvc = getAdditionalDataService();
		peSvc = getEntryPointService();
		accountSvc = getAccountService();
		tenantService = getTenantService();
	}
	/**
	 * 
	 */
	private void loadWorkflows() {
		String jbossHome = System.getProperty("catalina.base"); //$NON-NLS-1$
		if (jbossHome != null) {
			File jbossdir = new File(jbossHome); //$NON-NLS-1$
			File soffiddir = new File(jbossdir, "soffid"); //$NON-NLS-1$
			File bpm = new File(soffiddir, "bpm"); //$NON-NLS-1$
			if (bpm.isDirectory()) {
				for (File wf : bpm.listFiles()) {
					if (wf.getName().endsWith(".par")) //$NON-NLS-1$
					{
						try {
							log.info(String
									.format("Verifying BPM definition %s", wf.toString())); //$NON-NLS-1$
							FileInputStream in = new FileInputStream(wf);
							getBpmEngine().upgradeParFile(in);
						} catch (IOException e) {
							log.info(String
									.format("Error reading file %s: %s", wf.toString(), e.toString())); //$NON-NLS-1$
						} catch (InternalErrorException e) {
							log.info(String
									.format("Error uploading workflow definition file %s: %s", wf.toString(), e.toString())); //$NON-NLS-1$
						}
					}
				}
			}
		}
	}

	private void loadConnectors() {
		String jbossHome = System.getProperty("catalina.base"); //$NON-NLS-1$
		if (jbossHome != null) {
			File jbossdir = new File(jbossHome); //$NON-NLS-1$
			File soffiddir = new File(jbossdir, "soffid"); //$NON-NLS-1$
			File bpm = new File(soffiddir, "plugins"); //$NON-NLS-1$
			if (bpm.isDirectory()) {
				for (File wf : bpm.listFiles()) {
					if (wf.getName().endsWith(".jar")) //$NON-NLS-1$
					{
						try {
							log.info(String
									.format("Verifying Plugin %s", wf.toString())); //$NON-NLS-1$
							FileInputStream in = new FileInputStream(wf);
							ByteArrayOutputStream out = new ByteArrayOutputStream();
							for (int read = in.read(); read >= 0; read = in.read())
								out.write(read);
							out.close();
							in.close();
							getServerPluginService().updatePlugin(out.toByteArray());
						} catch (IOException e) {
							log.info(String
									.format("Error reading file %s: %s", wf.toString(), e.toString())); //$NON-NLS-1$
						} catch (Exception e) {
							log.info(String
									.format("Error uploading plugin file %s", wf.toString()), e); //$NON-NLS-1$
						}
					}
				}
			}
		}
	}

	/**
	 * Method that implements the functionality to add max number of rows to
	 * show on lists.
	 */
	private void configureMaxRowsToLists() throws InternalErrorException {
		Collection<Configuration> result = configSvc.findConfigurationByFilter(
				"soffid.ui.maxrows", null, null, null);

		if (result.isEmpty()) {
			Configuration configuracio = new Configuration();
			configuracio.setCode("soffid.ui.maxrows"); //$NON-NLS-1$
			configuracio.setValue("200"); //$NON-NLS-1$
			configuracio
					.setDescription(Messages
							.getString("ApplicationBootServiceImpl.maxRowsDescription")); //$NON-NLS-1$
			configSvc.create(configuracio);
		}

		else {
			System.setProperty("soffid.ui.maxrows", result.iterator().next()
					.getValue());
		}
	}

	private void configureGlobalTimeOut() throws InternalErrorException {
		Collection<Configuration> result = configSvc.findConfigurationByFilter(TimeOutUtils.PROPERTY_TIMEOUT, null, null, null);
		if (result.isEmpty()) {
			Configuration configuracio = new Configuration();
			configuracio.setCode(TimeOutUtils.PROPERTY_TIMEOUT);
			configuracio.setValue("10000"); //$NON-NLS-1$
			configuracio.setDescription(Messages.getString("ApplicationBootServiceImpl.globalTimeOutDescription")); //$NON-NLS-1$
			configSvc.create(configuracio);
		} else {
			System.setProperty(TimeOutUtils.PROPERTY_TIMEOUT, result.iterator().next().getValue());
		}
	}

	/**
	 * Method to implements the functionality to add wildcards parameter to
	 * configuration.
	 * 
	 * @throws InternalErrorException
	 */
	private void configureWildcards() throws InternalErrorException {
		Collection<Configuration> result = configSvc.findConfigurationByFilter(
				"soffid.ui.wildcards", null, null, null);

		if (result.isEmpty()) {
			Configuration configuracio = new Configuration();
			configuracio.setCode("soffid.ui.wildcards"); //$NON-NLS-1$
			configuracio.setValue("auto"); //$NON-NLS-1$
			configuracio
					.setDescription(Messages
							.getString("ApplicationBootServiceImpl.WildcardsDescription")); //$NON-NLS-1$
			configSvc.create(configuracio);
		}

		else {
			System.setProperty("soffid.ui.wildcards", result.iterator().next()
					.getValue());
		}
	}

	private String getServerPort() throws InternalErrorException, SQLException,
			NamingException {
		Configuration parametre = getConfigurationService()
				.findParameterByNameAndNetworkName("seycon.https.port", null); //$NON-NLS-1$
		if (parametre != null)
			return parametre.getValue();
		else
			return null;
	}

	private String getServerList() throws InternalErrorException, SQLException,
			NamingException {
		Configuration parametre = getConfigurationService()
				.findParameterByNameAndNetworkName("seycon.server.list", null); //$NON-NLS-1$
		if (parametre != null)
			return parametre.getValue();
		else
			return null;
	}

	private void configureDatabase() throws Exception {
		Security.nestedLogin("master\\Anonymous", Security.ALL_PERMISSIONS);
		try {

			log.info("Checking data status");
			Configuration cfg = null;
			for (Configuration cfg2: configSvc.findConfigurationByFilter("masterVersionLevel", null, null, null))
			{
				if (cfg == null)
				{
					cfg = cfg2;
				}
				else 
					configSvc.delete(cfg2);
			}

			if (cfg == null)
			{
				cfg = new Configuration("masterVersionLevel", "0"); //$NON-NLS-1$ //$NON-NLS-2$
				configSvc.create(cfg);				
			}
			int version = Integer.parseInt(cfg.getValue()); 
			log.info("Soffid 2.0 database level: "+version);
			if ( version >= 0 && version < 100)
			{
				updateFromVersion1 ();
				cfg.setValue("100"); //$NON-NLS-1$
				configSvc.update(cfg);
			}

			// Create initial tenant data
			configureTenantDatabase("master");

			if (version < 101) { //$NON-NLS-1$
				cfg.setValue("101"); //$NON-NLS-1$
				updateMandatoryRolGrant();
				configSvc.update(cfg);
			}

			if (version < 102) { //$NON-NLS-1$
				cfg.setValue("102"); //$NON-NLS-1$
				updateBlobTenant();
				configSvc.update(cfg);
			}

			if (version < 103) { //$NON-NLS-1$
				cfg.setValue("103"); //$NON-NLS-1$
				updateDisabledAttribute();
				configSvc.update(cfg);
			}

			if (version < 104) { //$NON-NLS-1$
				cfg.setValue("104"); //$NON-NLS-1$
				updateFromVersion1();
				configSvc.update(cfg);
			}

		} finally {
			Security.nestedLogoff();
		}
	}

	private void updateMandatoryRolGrant () throws SQLException
	{
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		final Connection conn = ds.getConnection();
		
		try
		{
			if (conn.getMetaData().getDatabaseProductName().equalsIgnoreCase("PostgreSQL"))
				executeSentence(conn, "UPDATE SC_ROLROL SET RRL_MANDAT=true WHERE RRL_MANDAT IS NULL",
							new Object[0]);
			else
				executeSentence(conn, "UPDATE SC_ROLROL SET RRL_MANDAT=1 WHERE RRL_MANDAT IS NULL",
					new Object[0]);
			executeSentence(conn, "UPDATE SC_TIPDAD SET TDA_SCOPE='user' WHERE TDA_SCOPE IS NULL",
					new Object[0]);
		}
		finally
		{
			conn.close();
		}
	}


	private void configureTenantDatabase(String tenantName) throws Exception {

		Configuration cfg = configSvc.findParameterByNameAndNetworkName(
				"tenantVersionLevel", null); //$NON-NLS-1$
		boolean firstSetup = (cfg == null);
		if (firstSetup) {
			createInitialData();
			configureDocumentManager();
			
			String externalURL = ConfigurationCache.getMasterProperty("AutoSSOURL");
			{
				if (externalURL != null && ! tenantName.equals("master"))
				try {
					URL u = new URL(externalURL);
					URL u2 = new URL (u.getProtocol(), tenantName+"."+u.getHost(), u.getPort(), u.getFile());
					cfg  = new Configuration ("AutoSSOURL", u2.toExternalForm());
					configSvc.create(cfg);
				} catch (Exception e) {
					log.warn("Error parsing url "+externalURL, e);
				}
			}
			
			cfg = new Configuration("tenantVersionLevel", "101"); //$NON-NLS-1$ //$NON-NLS-2$
			configSvc.create(cfg);
		} 
		if (cfg.getValue().equals("101"))
		{
			createStandardAttributes();
			cfg.setValue("102");
			configSvc.update(cfg);
		}
		if (cfg.getValue().equals("102"))
		{
			createScheduledTasks();
			cfg.setValue("103");
			configSvc.update(cfg);
		}
		if (cfg.getValue().equals("103"))
		{
			addTenant();
			cfg.setValue("104");
			configSvc.update(cfg);
		}
		if (cfg.getValue().equals("104"))
		{
			configureSSOAttributes();
			cfg.setValue("105");
			configSvc.update(cfg);
		}
		if (cfg.getValue().equals("105"))
		{
			createStandardAttributes();
			cfg.setValue("200");
			configSvc.update(cfg);
		}
	}

	private void configureSSOAttributes() throws InternalErrorException {
		if ( dispatcherSvc.findDispatcherByName("SSO") == null)
			return;
		DataType td = new DataType();
		td.setCode("type");
		td.setLabel("Account type");
		LinkedList<String> l = new LinkedList<String>();
		l.add("Windows");
		l.add("Linux");
		l.add("Database");
		td.setValues(l);
		td.setOrder(40L);
		td.setRequired(false);
		td.setScope(MetadataScope.ACCOUNT);
		td.setSystemName("SSO");
		td.setType(TypeEnumeration.STRING_TYPE);
		createIfNotExists(td);
		
		td = new DataType();
		td.setCode("SshPrivateKey");
		td.setLabel("SSH Private key");
		td.setOrder(41L);
		td.setRequired(false);
		td.setScope(MetadataScope.ACCOUNT);
		td.setSystemName("SSO");
		td.setType(TypeEnumeration.PASSWORD_TYPE);
		createIfNotExists(td);

		td = new DataType();
		td.setCode("SshPublicKey");
		td.setLabel("SSH Public key");
		td.setOrder(42L);
		td.setRequired(false);
		td.setScope(MetadataScope.ACCOUNT);
		td.setSystemName("SSO");
		td.setType(TypeEnumeration.STRING_TYPE);
		createIfNotExists(td);

		td = new DataType();
		td.setCode("passwordStatus");
		td.setLabel("Password synchronization");
		l = new LinkedList<String>();
		l.add("PASSWORD_GOOD: Valid");
		l.add("PASSWORD_GOOD_EXPIRED: Expired");
		l.add("PASSWORD_WRONG: Invalid");
		td.setValues(l);
		td.setOrder(43L);
		td.setReadOnly(true);
		td.setRequired(false);
		td.setScope(MetadataScope.ACCOUNT);
		td.setSystemName("SSO");
		td.setType(TypeEnumeration.STRING_TYPE);
		createIfNotExists(td);

		createScheduledTasks();
	}

	private void updateFromVersion1() throws IOException, Exception 
	{
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		final Connection conn = ds.getConnection();
		
		try
		{
			Statement stmt = conn.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT CON_VALOR FROM SC_CONFIG WHERE CON_CODI='versionLevel'");
			if (rset.next())
			{
				log.info("Upgrading from version 1 to version 2");
	
				Long tenantId = tenantService.getMasterTenant().getId();
				
		    	Database db = new Database();
		    	XmlReader reader = new XmlReader();
		    	PathMatchingResourcePatternResolver rpr = new PathMatchingResourcePatternResolver(getClass().getClassLoader());
				parseResources(rpr, db, reader, "console-ddl.xml");
		    	parseResources(rpr, db, reader, "core-ddl.xml");
		    	parseResources(rpr, db, reader, "plugin-ddl.xml");
	
		    	
		    	for (ForeignKey fk: db.foreignKeys)
		    	{
		    		if (fk.foreignTable.equals("SC_TENANT"))
		    		{
		    			log.info("Assigning tenant on table "+fk.tableName);
						executeSentence(conn, "UPDATE "+fk.tableName+" SET "+fk.columns.get(0)+"=? WHERE "+fk.columns.get(0)+" IS NULL",
								new Object[] {tenantId});
		    			
						executeSentence(conn, "UPDATE "+fk.tableName+" SET "+fk.columns.get(0)+"=? WHERE "+fk.columns.get(0)+" = 0",
								new Object[] {tenantId});
		    		}
		    	}
		    	try {
	    			log.info("Assigning tenant on BPM tables");
					executeSentence(conn, "UPDATE JBPM_MODULEDEFINITION SET TENANT_=?  WHERE TENANT_ IS NULL",
							new Object[] {tenantId});
					executeSentence(conn, "UPDATE JBPM_MODULEINSTANCE SET TENANT_=? WHERE TENANT_ IS NULL",
							new Object[] {tenantId});
					executeSentence(conn, "UPDATE JBPM_TASKINSTANCE SET TENANT_=? WHERE TENANT_ IS NULL",
							new Object[] {tenantId});

					executeSentence(conn, "INSERT INTO JBPM_MODULEDEFINITION (ID_, CLASS_, NAME_, PROCESSDEFINITION_, TENANT_) "
							+ "SELECT ID_, 'E', 'com.soffid.iam.bpm.model.TenantModuleDefinition', ID_, ? "
							+ "FROM JBPM_PROCESSDEFINITION "
							+ "WHERE ID_ NOT IN (SELECT PROCESSDEFINITION_ FROM JBPM_MODULEDEFINITION WHERE CLASS_='E')",
							new Object[] {tenantId});

					executeSentence(conn, "INSERT INTO JBPM_MODULEINSTANCE (ID_, CLASS_, NAME_, PROCESSINSTANCE_, TENANT_, VERSION_) "
							+ "SELECT ID_, 'E', 'com.soffid.iam.bpm.model.TenantModule', ID_, ?, 1 "
							+ "FROM JBPM_PROCESSINSTANCE "
							+ "WHERE ID_ NOT IN (SELECT PROCESSINSTANCE_ FROM JBPM_MODULEINSTANCE WHERE CLASS_='E')",
							new Object[] {tenantId});

		    	} catch (SQLException e) {
		    		// Those tables do not exists during test cases
		    	}
				log.info("Assigning sync server to master tenant");
				executeSentence(conn, "INSERT INTO SC_TENSER(TNS_ID,TNS_TEN_ID,TNS_SRV_ID) "
						+ "SELECT SRV_ID, ?, SRV_ID "
						+ "FROM SC_SERVER "
						+ "WHERE SRV_ID NOT IN (SELECT TNS_SRV_ID FROM SC_TENSER)",
						new Object[] {tenantId});
			}
			rset.close();
			stmt.close();
		}
		finally
		{
			conn.close();
		}
	}

	private void updateBlobTenant() throws IOException, Exception 
	{
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		final Connection conn = ds.getConnection();
		
		try
		{
			Long tenantId = tenantService.getMasterTenant().getId();
			PreparedStatement stmt = conn.prepareStatement("UPDATE SC_BLOCON SET BCO_TEN_ID=? WHERE BCO_TEN_ID IS NULL OR BCO_TEN_ID=0");
			stmt.setLong(1, tenantId);
			stmt.execute();
			stmt.close();
		}
		finally
		{
			conn.close();
		}
	}

	private void addTenant() throws IOException, Exception 
	{
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		final Connection conn = ds.getConnection();
		
		try
		{
			Long tenantId = tenantService.getMasterTenant().getId();
			PreparedStatement stmt = conn
					.prepareStatement("UPDATE SC_VAUFOL SET VAF_TEN_ID=? WHERE VAF_TEN_ID IS NULL OR VAF_TEN_ID=0");
			stmt.setLong(1, tenantId);
			stmt.execute();
			stmt.close();

			stmt = conn
					.prepareStatement("UPDATE BPM_DOCBLO SET DBL_TEN_ID=? WHERE DBL_TEN_ID IS NULL OR DBL_TEN_ID=0");
			stmt.setLong(1, tenantId);
			stmt.execute();
			stmt.close();

			stmt = conn
					.prepareStatement("UPDATE SC_SAMLREQ SET REQ_TEN_ID=? WHERE REQ_TEN_ID IS NULL OR REQ_TEN_ID=0");
			stmt.setLong(1, tenantId);
			stmt.execute();
			stmt.close();

			stmt = conn
					.prepareStatement("UPDATE SC_RECACO SET RAC_TEN_ID=? WHERE RAC_TEN_ID IS NULL OR RAC_TEN_ID=0");
			stmt.setLong(1, tenantId);
			stmt.execute();
			stmt.close();

			stmt = conn
					.prepareStatement("UPDATE SC_RECASI SET RAS_TEN_ID=? WHERE RAS_TEN_ID IS NULL OR RAS_TEN_ID=0");
			stmt.setLong(1, tenantId);
			stmt.execute();
			stmt.close();

			stmt = conn
					.prepareStatement("UPDATE SC_RECROL SET RRL_TEN_ID=? WHERE RRL_TEN_ID IS NULL OR RRL_TEN_ID=0");
			stmt.setLong(1, tenantId);
			stmt.execute();
			stmt.close();

		}
		finally
		{
			conn.close();
		}
	}


	private void updateDisabledAttribute() throws IOException, Exception 
	{
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		final Connection conn = ds.getConnection();
		
		try
		{
			PreparedStatement stmt = conn.prepareStatement(
					conn.getMetaData().getDatabaseProductName().equalsIgnoreCase("PostgreSQL") ?
							"UPDATE SC_USUGRU SET UGR_DISABLED=false WHERE UGR_DISABLED IS NULL":
							"UPDATE SC_USUGRU SET UGR_DISABLED=0 WHERE UGR_DISABLED IS NULL");
			stmt.execute();
			stmt.close();

			stmt = conn.prepareStatement(
					conn.getMetaData().getDatabaseProductName().equalsIgnoreCase("PostgreSQL") ?
							"UPDATE SC_ACCACC SET AAC_DISABLED=false WHERE AAC_DISABLED IS NULL":
							"UPDATE SC_ACCACC SET AAC_DISABLED=0 WHERE AAC_DISABLED IS NULL");
			stmt.execute();
			stmt.close();

			stmt = conn.prepareStatement(
					conn.getMetaData().getDatabaseProductName().equalsIgnoreCase("PostgreSQL") ?
							"UPDATE SC_USULCO SET ULC_DISABLED=false WHERE ULC_DISABLED IS NULL":
							"UPDATE SC_USULCO SET ULC_DISABLED=0 WHERE ULC_DISABLED IS NULL");
			stmt.execute();
			stmt.close();

		}
		finally
		{
			conn.close();
		}
	}

	private void parseResources(ResourcePatternResolver rpr, Database db,
			XmlReader reader, String path) throws IOException, Exception {
		Enumeration<URL> resources = Thread.currentThread().getContextClassLoader().getResources(path);
    	while (resources.hasMoreElements())
    	{
    		reader.parse(db, resources.nextElement().openStream());
    	}
	}

	/**
	 * @throws InternalErrorException
	 * 
	 */
	private void createScheduledTasks() throws InternalErrorException {
		Map<String, ScheduledTaskHandler> handlers = new HashMap<String, ScheduledTaskHandler>();
		Map<String, ScheduledTask> tasks = new HashMap<String, ScheduledTask>();

		for (ScheduledTaskHandler handler : getScheduledTaskService()
				.listHandlers()) {
			handlers.put(handler.getName(), handler);
		}

		if (!handlers
				.containsKey(SystemScheduledTasks.EXPIRE_UNTRUSTED_PASSWORDS)) {
			ScheduledTaskHandler handler = new ScheduledTaskHandler();
			handler.setClassName("com.soffid.iam.sync.engine.cron.ExpireUntrustedPasswordsTask"); //$NON-NLS-1$
			handler.setName(SystemScheduledTasks.EXPIRE_UNTRUSTED_PASSWORDS);
			getScheduledTaskService().create(handler);
		}

		if (!handlers
				.containsKey(SystemScheduledTasks.AUTHORITATIVE_DATA_IMPORT)) {
			ScheduledTaskHandler handler = new ScheduledTaskHandler();
			handler.setClassName("com.soffid.iam.sync.engine.cron.AuthoritativeImportTask"); //$NON-NLS-1$
			handler.setName(SystemScheduledTasks.AUTHORITATIVE_DATA_IMPORT);
			getScheduledTaskService().create(handler);
		}

		if (!handlers
				.containsKey(SystemScheduledTasks.DISABLE_EXPIRE_PASSWORDS)) {
			ScheduledTaskHandler handler = new ScheduledTaskHandler();
			handler.setClassName("com.soffid.iam.sync.engine.cron.DisableExpiredPasswordsTask"); //$NON-NLS-1$
			handler.setName(SystemScheduledTasks.DISABLE_EXPIRE_PASSWORDS);
			getScheduledTaskService().create(handler);
		}

		if (!handlers.containsKey(SystemScheduledTasks.RECONCILE_DISPATCHER)) {
			ScheduledTaskHandler handler = new ScheduledTaskHandler();
			handler.setClassName("com.soffid.iam.sync.engine.cron.ReconcileAgentTask"); //$NON-NLS-1$
			handler.setName(SystemScheduledTasks.RECONCILE_DISPATCHER);
			getScheduledTaskService().create(handler);
		}

		if (!handlers.containsKey(SystemScheduledTasks.ENABLE_DISABLE_ROLES)) {
			ScheduledTaskHandler handler = new ScheduledTaskHandler();
			handler.setClassName("com.soffid.iam.sync.engine.cron.ExpireRoleAssignmentsTask");
			handler.setName(SystemScheduledTasks.ENABLE_DISABLE_ROLES);
			getScheduledTaskService().create(handler);
		}

		if (!handlers
				.containsKey(SystemScheduledTasks.DISPATCHER_IMPACT)) {
			ScheduledTaskHandler handler = new ScheduledTaskHandler();
			handler.setClassName("com.soffid.iam.sync.engine.cron.AgentImpactTask"); //$NON-NLS-1$
			handler.setName(SystemScheduledTasks.DISPATCHER_IMPACT);
			getScheduledTaskService().create(handler);
		}

		if (!handlers
				.containsKey(SystemScheduledTasks.UPDATE_STATS)) {
			ScheduledTaskHandler handler = new ScheduledTaskHandler();
			handler.setClassName("com.soffid.iam.sync.engine.cron.UpdateStatsTask"); //$NON-NLS-1$
			handler.setName(SystemScheduledTasks.UPDATE_STATS);
			getScheduledTaskService().create(handler);
		}

		for (ScheduledTask task : getScheduledTaskService().listTasks()) {
			String id = task.getHandlerName();
			if (task.getParams() != null)
				id = id + ":" + task.getParams(); //$NON-NLS-1$
			tasks.put(id, task);
		}

		if (!tasks.containsKey(SystemScheduledTasks.EXPIRE_UNTRUSTED_PASSWORDS)) {
			ScheduledTask task = new ScheduledTask();
			task.setActive(false);
			task.setDayOfWeekPattern("*"); //$NON-NLS-1$
			task.setDayPattern("*"); //$NON-NLS-1$
			task.setHandlerName(SystemScheduledTasks.EXPIRE_UNTRUSTED_PASSWORDS);
			task.setHoursPattern("0"); //$NON-NLS-1$
			task.setMinutesPattern("0"); //$NON-NLS-1$
			task.setMonthsPattern("*"); //$NON-NLS-1$
			task.setName("Expire untrusted passwords"); //$NON-NLS-1$
			task.setTenant(Security.getCurrentTenantName());
			getScheduledTaskService().create(task);
		}

		if (!tasks.containsKey(SystemScheduledTasks.DISABLE_EXPIRE_PASSWORDS)) {
			ScheduledTask task = new ScheduledTask();
			task.setActive(false);
			task.setDayOfWeekPattern("*"); //$NON-NLS-1$
			task.setDayPattern("*"); //$NON-NLS-1$
			task.setHandlerName(SystemScheduledTasks.DISABLE_EXPIRE_PASSWORDS);
			task.setHoursPattern("0"); //$NON-NLS-1$
			task.setMinutesPattern("30"); //$NON-NLS-1$
			task.setMonthsPattern("*"); //$NON-NLS-1$
			task.setName("Disable expired passwords"); //$NON-NLS-1$
			getScheduledTaskService().create(task);
		}

		for (com.soffid.iam.api.System dispatcher : dispatcherSvc
				.findDispatchersByFilter(null, null, null, null, null, null)) {
			if (dispatcher.getUrl() != null && 
					!tasks.containsKey(SystemScheduledTasks.RECONCILE_DISPATCHER
					+ ":" + dispatcher.getId())) {
				ScheduledTask task = new ScheduledTask();
				task.setActive(false);
				task.setDayOfWeekPattern("*");
				task.setDayPattern("*");
				task.setHandlerName(SystemScheduledTasks.RECONCILE_DISPATCHER);
				task.setHoursPattern("0");
				task.setMinutesPattern("30");
				task.setMonthsPattern("*");
				task.setParams(dispatcher.getId().toString());
				task.setName("Reconcile unmanaged accounts from "
						+ dispatcher.getName());
				getScheduledTaskService().create(task);
			}
			if (dispatcher.getUrl() != null && 
					!tasks.containsKey(SystemScheduledTasks.DISPATCHER_IMPACT
					+ ":" + dispatcher.getId())) {
				ScheduledTask task = new ScheduledTask();
				task.setActive(false);
				task.setDayOfWeekPattern("*");
				task.setDayPattern("*");
				task.setHandlerName(SystemScheduledTasks.DISPATCHER_IMPACT);
				task.setHoursPattern("0");
				task.setMinutesPattern("30");
				task.setMonthsPattern("*");
				task.setParams(dispatcher.getId().toString());
				task.setName("Analyze impact for changes on "
						+ dispatcher.getName());
				getScheduledTaskService().create(task);
			}
		}

		if (!tasks.containsKey(SystemScheduledTasks.ENABLE_DISABLE_ROLES)) {
			ScheduledTask task = new ScheduledTask();
			task.setActive(false);
			task.setDayOfWeekPattern("*");
			task.setDayPattern("*");
			task.setHandlerName(SystemScheduledTasks.ENABLE_DISABLE_ROLES);
			task.setHoursPattern("0");
			task.setMinutesPattern("5");
			task.setMonthsPattern("*");
			task.setName("Apply date restrictions on roles");
			getScheduledTaskService().create(task);
		}


		if (!tasks.containsKey(SystemScheduledTasks.UPDATE_STATS)) {
			ScheduledTask task = new ScheduledTask();
			task.setActive(false);
			task.setDayOfWeekPattern("*");
			task.setDayPattern("*");
			task.setHandlerName(SystemScheduledTasks.UPDATE_STATS);
			task.setHoursPattern("*");
			task.setMinutesPattern("*/5");
			task.setMonthsPattern("*");
			task.setName("Feed statistic tables");
			getScheduledTaskService().create(task);
		}
	}

	private void executeSentence(Connection conn, String sql, Object... objects)
			throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			parseParameters(stmt, objects);
			stmt.execute();
		} finally {
			stmt.close();
		}
	}

	private List<Object[]> executeQuery(Connection conn, String sql,
			Object... objects) throws SQLException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			parseParameters(stmt, objects);
			ResultSet rset = stmt.executeQuery();
			try {
				List<Object[]> result = new LinkedList<Object[]>();
				int cols = rset.getMetaData().getColumnCount();
				while (rset.next()) {
					Object[] row = new Object[cols];
					for (int i = 0; i < cols; i++) {
						row[i] = rset.getObject(i + 1);
					}
					result.add(row);
				}
				return result;
			} finally {
				rset.close();
			}
		} finally {
			stmt.close();
		}
	}

	private void executeQuery(Connection conn, String sql, Object[] objects,
			RowProcessor processor) throws SQLException, InternalErrorException {
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			parseParameters(stmt, objects);
			ResultSet rset = stmt.executeQuery();
			try {
				int cols = rset.getMetaData().getColumnCount();
				while (rset.next()) {
					Object[] row = new Object[cols];
					for (int i = 0; i < cols; i++) {
						row[i] = rset.getObject(i + 1);
					}
					processor.processRow(row);
				}
			} finally {
				rset.close();
			}
		} finally {
			stmt.close();
		}
	}

	protected void parseParameters(PreparedStatement stmt, Object... objects)
			throws SQLException {
		int id = 1;
		for (Object p : objects) {
			if (p == null)
				stmt.setString(id++, null);
			else if (p instanceof String)
				stmt.setString(id++, (String) p);
			else if (p instanceof Integer)
				stmt.setInt(id++, ((Integer) p).intValue());
			else if (p instanceof Long)
				stmt.setLong(id++, ((Long) p).longValue());
			else if (p instanceof Date)
				stmt.setDate(id++, (Date) p);
			else if (p instanceof java.util.Date)
				stmt.setDate(id++, new Date(((java.util.Date) p).getTime()));
			else
				stmt.setObject(id++, p);
		}
	}

	protected void configureIndexDir() throws InternalErrorException,
			IOException, BPMException {
		try {
			String installDir = System.getProperty("jboss.server.base.dir") + "../.."; //$NON-NLS-1$ //$NON-NLS-2$
			File f = new File(installDir).getCanonicalFile();
			ConfigParameterVO configVO = new ConfigParameterVO();
			configVO.setApp("BPM"); //$NON-NLS-1$
			configVO.setKey("lucene.dir"); //$NON-NLS-1$
			configVO.setValue(new File(f, "docs/index").getAbsolutePath()); //$NON-NLS-1$
			bpmConfigSvc.create(configVO);
		} catch (BPMException e) {
			if (!e.getMessage().equals("Could not find datasource")) //$NON-NLS-1$
				throw e;
		}
	}

	/**
	 * Method that implements the functionality to set the document manager
	 * settings by default.
	 * 
	 * @throws BPMException
	 * @throws InternalErrorException
	 * 
	 */
	protected void configureDocumentManager() throws InternalErrorException,
			BPMException, IOException {
		Collection<Configuration> result = configSvc.findConfigurationByFilter(
				"soffid.ui.docStrategy", null, null, null);

		if (result.isEmpty()) {
			Configuration configuracio = new Configuration();
			configuracio.setCode("soffid.ui.docStrategy"); //$NON-NLS-1$
			configuracio
					.setValue( DatabaseStrategy.class.getName());
			configSvc.create(configuracio);
		}

		else {
			System.setProperty("soffid.ui.docStrategy", result.iterator()
					.next().getValue());
		}

		result = configSvc.findConfigurationByFilter("soffid.ui.docPath", null,
				null, null);

		
		if (result.isEmpty()) {
			Configuration configuracio = new Configuration();
			configuracio.setCode("soffid.ui.docPath"); //$NON-NLS-1$
			configuracio.setValue("-");
			configSvc.create(configuracio);
		}
		else {
			System.setProperty("soffid.ui.docPath", result.iterator().next()
					.getValue());
		}

		result = configSvc.findConfigurationByFilter("soffid.ui.docTempPath",
				null, null, null);

		if (result.isEmpty()) {
			Configuration configuracio = new Configuration();
			configuracio.setCode("soffid.ui.docTempPath"); //$NON-NLS-1$
			configuracio.setValue(System.getProperty("java.io.tmpdir"));
			configSvc.create(configuracio);
		}
		else {
			System.setProperty("soffid.ui.docTempPath", result.iterator()
					.next().getValue());
		}
	}

	protected void createInitialData() throws Exception {
		Configuration cfg = null;

		tenantService.getMasterTenant();
		
		createScheduledTasks();

		UserDomain du = dominiSvc.findUserDomainByName("DEFAULT"); //$NON-NLS-1$
		if (du == null) {
			du = new UserDomain();
			du.setCode("DEFAULT"); //$NON-NLS-1$
			du.setDescription("Default user domain"); //$NON-NLS-1$
			du.setType(TipusDominiUsuariEnumeration.PRINCIPAL);
			du = dominiSvc.create(du);
		}

		PasswordDomain dc = dominiSvc.findPasswordDomainByName("DEFAULT"); //$NON-NLS-1$
		if (dc == null) {
			dc = new PasswordDomain();
			dc.setCode("DEFAULT"); //$NON-NLS-1$
			dc.setDescription("Default password domain"); //$NON-NLS-1$
			dc = dominiSvc.create(dc);
		}

		UserType tipUs;
		HashSet<String> tus2 = new HashSet<String>();
		for (UserType tus: dominiSvc.findAllUserType())
		{
			tus2.add (tus.getCode());
		}
		if (!tus2.contains("S"))
		{
			tipUs = new UserType();
			tipUs.setCode("S"); //$NON-NLS-1$
			tipUs.setDescription("SSO account"); //$NON-NLS-1$
			dominiSvc.create(tipUs);

		}
		if (!tus2.contains("I"))
		{	
			tipUs = new UserType();
			tipUs.setCode("E"); //$NON-NLS-1$
			tipUs.setDescription("External user"); //$NON-NLS-1$
			dominiSvc.create(tipUs);
		}
		if (!tus2.contains("E"))
		{
			tipUs = new UserType();
			tipUs.setCode("I"); //$NON-NLS-1$
			tipUs.setDescription("Internal user"); //$NON-NLS-1$
			dominiSvc.create(tipUs);
		}

		Collection<PasswordPolicy> pcs = dominiSvc
				.findAllPasswordPolicyDomain(dc.getCode());
		if (pcs.size() == 0) {
			for (UserType tu: dominiSvc.findAllUserType())
			{
				PasswordPolicy pol = new PasswordPolicy();
				pol.setPasswordDomainCode(dc.getCode());
				pol.setUsersDomainCode(du.getCode());
				pol.setDescription("Default password policy"); //$NON-NLS-1$
				pol.setMaximumPeriod(new Long(365));
				pol.setMaximumPeriodExpired(new Long(365));
				pol.setType("M"); //$NON-NLS-1$
				pol.setUserType(tu.getCode());
				dominiSvc.create(pol);
			}
		}

		Application app = appSvc.findApplicationByApplicationName("SOFFID"); //$NON-NLS-1$
		if (app == null) {
			app = new Application();
			app.setDatabase(""); //$NON-NLS-1$
			app.setName("SOFFID"); //$NON-NLS-1$
			app.setBpmEnforced(new Boolean(false));
			app.setDescription("SOFFID Identity Manager"); //$NON-NLS-1$
			app = appSvc.create(app);
		}

		com.soffid.iam.api.System dis = dispatcherSvc
				.findDispatcherByName("soffid"); //$NON-NLS-1$
		if (dis == null) {
			dis = new com.soffid.iam.api.System();
			dis.setRolebased(new Boolean(true));
			dis.setName("soffid"); //$NON-NLS-1$
			dis.setDescription("Soffid system");
			dis.setAccessControl(new Boolean(false));
			dis.setPasswordsDomain(dc.getCode());
			dis.setUsersDomain(du.getCode());
			dis.setPasswordsDomainId(dc.getId());
			dis.setClassName("- no class -"); //$NON-NLS-1$
			dis.setUserTypes("I"); //$NON-NLS-1$
			dis = dispatcherSvc.create(dis);
		}

		com.soffid.iam.api.System disSso = dispatcherSvc
				.findDispatcherByName("SSO"); //$NON-NLS-1$
		if (disSso == null) {
			disSso = new com.soffid.iam.api.System();
			disSso.setRolebased(new Boolean(false));
			disSso.setManualAccountCreation(true);
			disSso.setName("SSO"); //$NON-NLS-1$
			disSso.setDescription("External SSO accounts");
			disSso.setAccessControl(new Boolean(false));
			disSso.setManualAccountCreation(true);
			disSso.setPasswordsDomain(dc.getCode());
			disSso.setUsersDomain(du.getCode());
			disSso.setPasswordsDomainId(dc.getId());
			disSso.setClassName("com.soffid.iam.sync.sso.agent.SSOAgent"); //$NON-NLS-1$
			disSso.setUserTypes("S"); //$NON-NLS-1$
			disSso.setUrl("local");
			disSso = dispatcherSvc.create(disSso);

			DataType td = new DataType();
			td.setCode("SSO:1");
			td.setLabel("Form data");
			td.setOrder(3L);
			td.setRequired(false);
			td.setScope(MetadataScope.ACCOUNT);
			td.setSystemName(disSso.getName());
			td.setType(TypeEnumeration.SSO_FORM_TYPE);
			getAdditionalDataService().create(td);
			
			configureSSOAttributes();

		}

		Role rol = appSvc
				.findRoleByRoleNameAndApplicationNameAndDispatcherName(
						"SOFFID_ADMIN", app.getName(), "soffid"); //$NON-NLS-1$
		if (rol == null) {
			rol = new Role();
			rol.setInformationSystemName(app.getName());
			rol.setSystem("soffid"); //$NON-NLS-1$
			rol.setPassword(new Boolean(false));
			rol.setEnableByDefault(new Boolean(true));
			rol.setDescription("SOFFID Administrator"); //$NON-NLS-1$
			rol.setBpmEnforced(new Boolean(false));
			rol.setName("SOFFID_ADMIN"); //$NON-NLS-1$
			rol.setDomain(null);
			rol = appSvc.create(rol);
		}

		Network x = xarxaSvc.findNetworkByName("loopback"); //$NON-NLS-1$
		if (x == null) {
			x = new Network();
			x.setCode("loopback"); //$NON-NLS-1$
			x.setDescription("loopback");
			x.setIp("127.0.0.0"); //$NON-NLS-1$
			x.setMask("255.255.255.0"); //$NON-NLS-1$
			x.setLanAccess(new Boolean(false));
			xarxaSvc.create(x);
		}

		upgradeOperatingSystems();

		Host m = xarxaSvc.findHostByName("loopback"); //$NON-NLS-1$
		if (m == null) {
			m = new Host();
			m.setNetworkCode("loopback"); //$NON-NLS-1$
			m.setIp("127.0.0.1"); //$NON-NLS-1$
			m.setMail(new Boolean(false));
			m.setDescription("Loopback host"); //$NON-NLS-1$
			m.setName("loopback"); //$NON-NLS-1$
			m.setOffice(new Boolean(false));
			m.setPrintersServer(new Boolean(false));
			m.setOs("ALT"); //$NON-NLS-1$
			m.setSerialNumber("loopback"+System.currentTimeMillis());
			xarxaSvc.create(m);
		}

		m = xarxaSvc.findHostByName("null"); //$NON-NLS-1$
		if (m == null) {
			m = new Host();
			m.setNetworkCode("loopback"); //$NON-NLS-1$
			m.setMail(new Boolean(false));
			m.setDescription("Void host"); //$NON-NLS-1$
			m.setName("null"); //$NON-NLS-1$
			m.setOffice(new Boolean(false));
			m.setPrintersServer(new Boolean(false));
			m.setOs("ALT"); //$NON-NLS-1$
			m.setSerialNumber("null"+System.currentTimeMillis());
			xarxaSvc.create(m);
		}

		Group grup = grupSvc.findGroupByGroupName("world"); //$NON-NLS-1$
		if (grup == null) {
			grup = new Group();
			grup.setName("world"); //$NON-NLS-1$
			grup.setDescription("World"); //$NON-NLS-1$
			grup.setObsolete(new Boolean(false));
			grupSvc.create(grup);
		}

		grup = grupSvc.findGroupByGroupName("enterprise"); //$NON-NLS-1$
		if (grup == null) {
			grup = new Group();
			grup.setName("enterprise"); //$NON-NLS-1$
			grup.setParentGroup("world"); //$NON-NLS-1$
			grup.setDescription("Enterprise"); //$NON-NLS-1$
			grup.setObsolete(new Boolean(false));
			grupSvc.create(grup);
		}
		;
		grup = grupSvc.findGroupByGroupName("admingroup"); //$NON-NLS-1$
		if (grup == null) {
			grup = new Group();
			grup.setName("admingroup"); //$NON-NLS-1$
			grup.setParentGroup("enterprise"); //$NON-NLS-1$
			grup.setDescription("Enterprise Administrators"); //$NON-NLS-1$
			grup.setObsolete(new Boolean(false));
			grupSvc.create(grup);
		}

		User usu = usuariSvc.findUserByUserName("admin"); //$NON-NLS-1$
		if (usu == null) {
			usu = new User();
			usu.setUserName("admin"); //$NON-NLS-1$
			usu.setPrimaryGroup("admingroup"); //$NON-NLS-1$
			usu.setComments("Autocreated"); //$NON-NLS-1$
			usu.setMultiSession(new Boolean(true));
			usu.setFirstName("Soffid"); //$NON-NLS-1$
			usu.setLastName("Administrator"); //$NON-NLS-1$
			usu.setHomeServer("null"); //$NON-NLS-1$
			usu.setProfileServer("null"); //$NON-NLS-1$
			usu.setMailServer("null"); //$NON-NLS-1$
			usu.setUserType("I"); //$NON-NLS-1$
			usu.setActive(new Boolean(true));
			usu = usuariSvc.create(usu);

			passSvc.storePassword(usu.getUserName(), dc.getCode(),
					"changeit", false); //$NON-NLS-1$
		}

		UserAccount account = null;
		for (UserAccount ua : accountSvc.listUserAccounts(usu)) {
			if (ua.getSystem().equals(dis.getName())) {
				account = ua;
				break;
			}
		}
		if (account == null) {
			account = accountSvc.createAccount(usu, dis, null);
		}

		Collection<AuthorizationRole> auts = autSvc
				.getAuthorizationRoles(Security.AUTO_AUTHORIZATION_ALL);
		if (auts.isEmpty()) {
			AuthorizationRole aut = new AuthorizationRole();
			aut.setRole(rol);
			aut.setAuthorization(Security.AUTO_AUTHORIZATION_ALL);
			autSvc.create(aut);
		}

		boolean found = false;
		for (RoleAccount ru : appSvc.findRoleAccountByAccount(account.getId())) {
			if (ru.getSystem().equals(rol.getSystem())
					&& ru.getRoleName().equals(rol.getName()))
				found = true;
		}
		if (!found) {
			RoleAccount ru = new RoleAccount();
			ru.setSystem(rol.getSystem());
			ru.setInformationSystemName(app.getName());
			ru.setUserCode(usu.getUserName());
			ru.setRoleName(rol.getName());
			ru.setAccountName("admin"); //$NON-NLS-1$
			appSvc.create(ru);
		}

		cfg = configSvc.findParameterByNameAndNetworkName("SSOServer", null); //$NON-NLS-1$
		if (cfg == null && System.getProperty("hostName") != null) {
			cfg = new Configuration("SSOServer", System.getProperty("hostName")); //$NON-NLS-1$
			configSvc.create(cfg);
		}

		cfg = configSvc.findParameterByNameAndNetworkName("AutoSSOSystem", null); //$NON-NLS-1$
		if (cfg == null)
			configSvc.create( new Configuration("AutoSSOSystem", disSso.getName()) ); //$NON-NLS-1$

		cfg = configSvc.findParameterByNameAndNetworkName("AutoSSOPolicy", null); //$NON-NLS-1$
		if (cfg == null)
			configSvc.create( new Configuration("AutoSSOPolicy", "S" )); //$NON-NLS-1$

		cfg = configSvc.findParameterByNameAndNetworkName("AutoSSOURL", null); //$NON-NLS-1$
		if (cfg == null && System.getProperty("hostName") != null)
			configSvc.create( new Configuration("AutoSSOURL", 
					"http://"+System.getProperty("hostName") + ":8080/" )); //$NON-NLS-1$


		Configuration cfg2 = configSvc.findParameterByNameAndNetworkName(
				"seycon.https.port", null); //$NON-NLS-1$
		if (cfg2 == null) {
			cfg2 = new Configuration("seycon.https.port", "760"); //$NON-NLS-1$ //$NON-NLS-2$
			configSvc.create(cfg2);
		}

		
		cfg = configSvc.findParameterByNameAndNetworkName(
				"soffid.task.limit", null); //$NON-NLS-1$
		if (cfg == null)
		{
			cfg = new Configuration("soffid.task.limit", "15"); //$NON-NLS-1$ //$NON-NLS-2$
			configSvc.create(cfg);
		}
		
		cfg = configSvc.findParameterByNameAndNetworkName(
				"soffid.task.mode", null); //$NON-NLS-1$
		if (cfg == null)
		{
			cfg = new Configuration("soffid.task.mode", "manual"); //$NON-NLS-1$ //$NON-NLS-2$
			configSvc.create(cfg);
		}


		cfg = configSvc.findParameterByNameAndNetworkName(
				"tenantDbLevel", null); //$NON-NLS-1$
		if (cfg == null)
		{
			cfg = new Configuration("tenantDbLevel", "1"); //$NON-NLS-1$ //$NON-NLS-2$
			configSvc.create(cfg);
		}
		else
		{
			cfg.setValue("1");
			configSvc.update(cfg);
		}
	}

	private void createIfNotExists(DataType td) throws InternalErrorException {
		DataType td2 = getAdditionalDataService().findSystemDataType(td.getSystemName(), td.getCode());
		if (td2 == null)
			getAdditionalDataService().create(td);
	}

	private void createStandardAttributes() throws Exception {
		getAdditionalDataService().registerStandardObject("com/soffid/iam/api/Group.ui.json", MetadataScope.GROUP, false);
		getAdditionalDataService().registerStandardObject("com/soffid/iam/api/GroupUser.ui.json", MetadataScope.GROUP_MEMBERSHIP, false);
		getAdditionalDataService().registerStandardObject("com/soffid/iam/api/Host.ui.json", null, false);
		getAdditionalDataService().registerStandardObject("com/soffid/iam/api/Application.ui.json", MetadataScope.APPLICATION, false);
		getAdditionalDataService().registerStandardObject("com/soffid/iam/api/Role.ui.json", MetadataScope.ROLE, false);
		getAdditionalDataService().registerStandardObject("com/soffid/iam/api/Account.ui.json", MetadataScope.ACCOUNT, false);
		getAdditionalDataService().registerStandardObject("com/soffid/iam/api/User.ui.json", MetadataScope.USER, false);
	}

	static Database database = null;
	
	private void createStandardField(int order, MetadataScope scope, Class<User> class1, String att, String table,
			String column, TypeEnumeration type, String filterExpression, boolean unique) throws Exception {
		Column c = searchColumn (table, column);
		if (c == null)
			throw new InternalErrorException ("Unable to find column "+table+"."+column);
		createStandardField2(order, scope, class1, att, type, filterExpression, unique,
				c.length == null || c.length.isEmpty()? 100: Integer.parseInt(c.length), 
				c.notNull);
	}
	
	private void createStandardField2(int order, MetadataScope scope, Class<User> class1, String att, TypeEnumeration type,
			String filterExpression, boolean unique, int size, boolean nullable) throws Exception {
		MetaDataEntity dataType = getMetaDataEntityDao().newMetaDataEntity();
		for ( MetaDataEntity dt: getMetaDataEntityDao().findDataTypesByScopeAndName(scope, att))
		{
			if (Boolean.TRUE == dt.getBuiltin() && att.equals(dt.getName()))
			{
				dataType = dt;
				String nlsLabel = class1.getName()+"."+att;
				if (nlsLabel.equals(dataType.getNlsLabel())) {
					dataType.setNlsLabel(nlsLabel);
					getMetaDataEntityDao().update(dataType);
				}
				break;
			}
		}
		
		dataType.setName( att );
		dataType.setType( type);
		dataType.setBuiltin(true);
		dataType.setFilterExpression(filterExpression);
		dataType.setNlsLabel(class1.getName()+"."+att);
		if (dataType.getOrder() == null)
			dataType.setOrder( new Long (order) );
		dataType.setName(att);
		dataType.setUnique(unique);
		dataType.setScope(scope);
		dataType.setRequired( ! nullable );
		if (dataType.getSize() == null || dataType.getSize().intValue() > size)
			dataType.setSize( size );
		if (dataType.getId() == null)
			getMetaDataEntityDao().create(dataType);
		else
			getMetaDataEntityDao().update(dataType);
	}

	private Column searchColumn(String tableName, String columnName) throws Exception {
		if (database == null)
			database = new DatabaseParser().parse();
		for (Table table: database.tables)
		{
			if (table.name.equals(tableName))
			{
				for (Column column: table.columns)
				{
					if (column.name.equals(columnName))
						return column;
				}
			}
		}
		return null;
	}

	protected void configureSystemProperties() throws InternalErrorException {
		if (configSvc == null)
			configSvc = getConfigurationService();
		for (Configuration config : configSvc.findConfigurationByFilter("%",
				null, null, null)) {
			if (config.getNetworkCode() == null) {
				ConfigurationCache.setProperty(config.getCode(), config.getValue());
			}
		}
		if (ConfigurationCache.getProperty("soffid.ui.wildcards") == null) //$NON-NLS-1$
			ConfigurationCache.setProperty("soffid.ui.wildcards", "auto"); //$NON-NLS-1$ //$NON-NLS-2$

		System.setProperty("org.apache.cxf.useSpringClassHelpers", "0");
	}

	/**
	 * Method to upgrade previous operating system configured.
	 * 
	 * @throws SQLException
	 * @throws NamingException
	 * @throws InternalErrorException
	 */
	private void upgradeOperatingSystems() throws SQLException,
			NamingException, InternalErrorException {

		List<OsType> osList = new LinkedList<OsType>();

		osList.add(new OsType(null, "WNT", "Windows Desktop")); //$NON-NLS-1$ //$NON-NLS-2$
		osList.add(new OsType(null, "LIN", "Linux")); //$NON-NLS-1$ //$NON-NLS-2$
		osList.add(new OsType(null, "ALT", "Unknown OS")); //$NON-NLS-1$ //$NON-NLS-2$
		osList.add(new OsType(null, "NTS", "Windows Server")); //$NON-NLS-1$ //$NON-NLS-2$

		for (OsType os : osList) {
			// Check existing OS
			if (xarxaSvc.findOSTypeByName(os.getName()) == null) {
				os = xarxaSvc.create(os);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextAware#setApplicationContext
	 * (org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	@Override
	protected void handleTenantBoot(Tenant tenant) throws Exception {
		loadServiceHandlers();
		
		Security.nestedLogin(tenant.getName(),  "Anonymous", Security.ALL_PERMISSIONS);
		try {
			configureTenantDatabase(tenant.getName());
		} finally {
			Security.nestedLogoff();
		}
	}

}

interface RowProcessor {
	void processRow(Object[] row) throws SQLException, InternalErrorException;
}
