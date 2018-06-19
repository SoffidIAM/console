package com.soffid.iam.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

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
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.tools.db.persistence.XmlReader;
import com.soffid.tools.db.schema.Database;
import com.soffid.tools.db.schema.ForeignKey;

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
	}

	@Override
	protected void handleConsoleBoot() throws Exception {
		ServiceLocator.instance();
		
		loadServiceHandlers();

		ConfigurationCache.setProperty("soffid.ui.maxrows", //$NON-NLS-1$
				Integer.toString(Integer.MAX_VALUE)); //$NON-NLS-1$

		ConfigurationCache.setProperty("soffid.ui.wildcards", "auto"); //$NON-NLS-1$ //$NON-NLS-2$

		configureDatabase();

		configureIndexDir();

		Config.configureClient(getServerList(), getServerPort());

		configureWildcards();

		configureMaxRowsToLists();

		configureGlobalTimeOut();

		configureSystemProperties();

		createScheduledTasks();

		loadWorkflows();
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
			configureTenantDatabase();

			Configuration cfg = null;
			for (Configuration cfg2: configSvc.findConfigurationByFilter("masterVersionLevel", null, null, null))
			{
				if (cfg == null)
					cfg = cfg2;
				else 
					configSvc.delete(cfg2);
			}
			if (cfg == null)
			{
				cfg = new Configuration("masterVersionLevel", "0"); //$NON-NLS-1$ //$NON-NLS-2$
				configSvc.create(cfg);				
			}
			int version = Integer.parseInt(cfg.getValue()); 
			if ( version >= 0 && version < 100)
			{
				updateFromVersion1 ();
				cfg.setValue("100"); //$NON-NLS-1$
				configSvc.update(cfg);
			}
			if (version < 101) { //$NON-NLS-1$
				cfg.setValue("101"); //$NON-NLS-1$
				updateMandatoryRolGrant();
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


	private void configureTenantDatabase() throws SystemException,
			NotSupportedException, InternalErrorException, RollbackException,
			HeuristicMixedException, HeuristicRollbackException,
			NeedsAccountNameException, AccountAlreadyExistsException,
			BPMException, IOException, NamingException, SQLException {

		Configuration cfg = configSvc.findParameterByNameAndNetworkName(
				"tenantVersionLevel", null); //$NON-NLS-1$
		boolean firstSetup = (cfg == null);
		if (firstSetup) {
			createInitialData();
			configureDocumentManager();
			cfg = new Configuration("tenantVersionLevel", "101"); //$NON-NLS-1$ //$NON-NLS-2$
			configSvc.create(cfg);
		} 
	}

	private void updateFromVersion1() throws IOException, Exception 
	{
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		final Connection conn = ds.getConnection();
		
		try
		{
			Long tenantId = Security.getCurrentTenantId();
			
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
					executeSentence(conn, "UPDATE "+fk.tableName+" SET "+fk.columns.get(0)+"=? WHERE "+fk.columns.get(0)+" IS NULL",
							new Object[] {tenantId});
	    			
					executeSentence(conn, "UPDATE "+fk.tableName+" SET "+fk.columns.get(0)+"=? WHERE "+fk.columns.get(0)+" = 0",
							new Object[] {tenantId});
	    		}
	    	}
	    	try {
				executeSentence(conn, "UPDATE JBPM_MODULEDEFINITION SET TENANT_=?  WHERE TENANT_ IS NULL",
						new Object[] {tenantId});
				executeSentence(conn, "UPDATE JBPM_MODULEINSTANCE SET TENANT_=? WHERE TENANT_ IS NULL",
						new Object[] {tenantId});
				executeSentence(conn, "UPDATE JBPM_TASKINSTANCE SET TENANT_=? WHERE TENANT_ IS NULL",
						new Object[] {tenantId});
	    	} catch (SQLException e) {
	    		// Those tables do not exists during test cases
	    	}
			executeSentence(conn, "INSERT INTO SC_TENSER(TNS_ID,TNS_TEN_ID,TNS_SRV_ID) "
					+ "SELECT SRV_ID, ?, SRV_ID "
					+ "FROM SC_SERVER "
					+ "WHERE SRV_ID NOT IN (SELECT TNS_SRV_ID FROM SC_TENSER)",
					new Object[] {tenantId});
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
		;
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

		for (ScheduledTask task : getScheduledTaskService().listTasks()) {
			String id = task.getHandlerName();
			if (task.getParams() != null)
				id = id + ":" + task.getParams(); //$NON-NLS-1$
			tasks.put(id, task);
		}

		if (!tasks.containsKey(SystemScheduledTasks.EXPIRE_UNTRUSTED_PASSWORDS)) {
			ScheduledTask task = new ScheduledTask();
			task.setActive(true);
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
			task.setActive(true);
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
		}

		if (!tasks.containsKey(SystemScheduledTasks.ENABLE_DISABLE_ROLES)) {
			ScheduledTask task = new ScheduledTask();
			task.setActive(true);
			task.setDayOfWeekPattern("*");
			task.setDayPattern("*");
			task.setHandlerName(SystemScheduledTasks.ENABLE_DISABLE_ROLES);
			task.setHoursPattern("0");
			task.setMinutesPattern("5");
			task.setMonthsPattern("*");
			task.setName("Apply date restrictions on roles");
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

	protected void createInitialData() throws InternalErrorException,
			NeedsAccountNameException, AccountAlreadyExistsException,
			SQLException, NamingException {
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
		Collection<UserType> tus = dominiSvc.findAllUserType();
		if (tus.size() > 0) {
			tipUs = tus.iterator().next();
		} else {
			tipUs = new UserType();
			tipUs.setCode("S"); //$NON-NLS-1$
			tipUs.setDescription("SSO account"); //$NON-NLS-1$
			dominiSvc.create(tipUs);

			tipUs = new UserType();
			tipUs.setCode("E"); //$NON-NLS-1$
			tipUs.setDescription("External user"); //$NON-NLS-1$
			dominiSvc.create(tipUs);

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
			disSso.setPasswordsDomain(dc.getCode());
			disSso.setUsersDomain(du.getCode());
			disSso.setPasswordsDomainId(dc.getId());
			disSso.setClassName("- no class -"); //$NON-NLS-1$
			disSso.setUserTypes("S"); //$NON-NLS-1$
			disSso = dispatcherSvc.create(disSso);

			DataType td = new DataType();
			td.setCode("SSO:Server");
			td.setLabel("Server");
			td.setOrder(1L);
			td.setRequired(false);
			td.setScope(MetadataScope.ACCOUNT);
			td.setSystemName(disSso.getName());
			td.setType(TypeEnumeration.STRING_TYPE);
			getAdditionalDataService().create(td);

			td = new DataType();
			td.setCode("SSO:URL");
			td.setLabel("URL");
			td.setOrder(2L);
			td.setRequired(false);
			td.setScope(MetadataScope.ACCOUNT);
			td.setSystemName(disSso.getName());
			td.setType(TypeEnumeration.STRING_TYPE);
			getAdditionalDataService().create(td);

			td = new DataType();
			td.setCode("SSO:1");
			td.setLabel("Form data");
			td.setOrder(3L);
			td.setRequired(false);
			td.setScope(MetadataScope.ACCOUNT);
			td.setSystemName(disSso.getName());
			td.setType(TypeEnumeration.SSO_FORM_TYPE);
			getAdditionalDataService().create(td);
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
			rol.setDomain(new Domain());
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

		DataType td = tdSvc.findDataTypeByName("NIF"); //$NON-NLS-1$
		if (td == null) {
			td = new DataType();
			td.setCode("NIF"); //$NON-NLS-1$
			td.setOrder(new Long(1));
			td.setScope(MetadataScope.USER);
			td.setType(TypeEnumeration.STRING_TYPE);
			tdSvc.create(td);
		}

		td = tdSvc.findDataTypeByName("PHONE"); //$NON-NLS-1$
		if (td == null) {
			td = new DataType();
			td.setCode("PHONE"); //$NON-NLS-1$
			td.setOrder(new Long(2));
			td.setScope(MetadataScope.USER);
			td.setType(TypeEnumeration.STRING_TYPE);
			tdSvc.create(td);
		}

		User usu = usuariSvc.findUserByUserName("admin"); //$NON-NLS-1$
		if (usu == null) {
			usu = new User();
			usu.setUserName("admin"); //$NON-NLS-1$
			usu.setPrimaryGroup("admingroup"); //$NON-NLS-1$
			usu.setComments("Autocreated"); //$NON-NLS-1$
			usu.setMultiSession(new Boolean(true));
			usu.setFirstName("Admin"); //$NON-NLS-1$
			usu.setLastName("Admin"); //$NON-NLS-1$
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
		if (cfg == null) {

			cfg = new Configuration(
					"SSOServer", System.getProperty("hostName") + "." + System.getProperty("domainName")); //$NON-NLS-1$
			configSvc.create(cfg);
		}

		cfg = configSvc.findParameterByNameAndNetworkName("AutoSSOSystem", null); //$NON-NLS-1$
		if (cfg == null)
			configSvc.create( new Configuration("AutoSSOSystem", disSso.getName()) ); //$NON-NLS-1$

		cfg = configSvc.findParameterByNameAndNetworkName("AutoSSOPolicy", null); //$NON-NLS-1$
		if (cfg == null)
			configSvc.create( new Configuration("AutoSSOPolicy", "S" )); //$NON-NLS-1$

		cfg = configSvc.findParameterByNameAndNetworkName("AutoSSOURL", null); //$NON-NLS-1$
		if (cfg == null)
			configSvc.create( new Configuration("AutoSSOURL", 
					"http://"+System.getProperty("hostName") + "." + System.getProperty("domainName")+":8080/" )); //$NON-NLS-1$


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

		osList.add(new OsType(null, "WNT", "Windows NT")); //$NON-NLS-1$ //$NON-NLS-2$
		osList.add(new OsType(null, "LIN", "Linux")); //$NON-NLS-1$ //$NON-NLS-2$
		osList.add(new OsType(null, "W95", "Windows 95")); //$NON-NLS-1$ //$NON-NLS-2$
		osList.add(new OsType(null, "ALT", "Alternative OS")); //$NON-NLS-1$ //$NON-NLS-2$
		osList.add(new OsType(null, "SOL", "Solaris")); //$NON-NLS-1$ //$NON-NLS-2$
		osList.add(new OsType(null, "NTS", "NT Server")); //$NON-NLS-1$ //$NON-NLS-2$
		osList.add(new OsType(null, "WTS", "Windows Terminal Server")); //$NON-NLS-1$ //$NON-NLS-2$

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
			configureTenantDatabase();
		} finally {
			Security.nestedLogoff();
		}
	}
}

interface RowProcessor {
	void processRow(Object[] row) throws SQLException, InternalErrorException;
}
