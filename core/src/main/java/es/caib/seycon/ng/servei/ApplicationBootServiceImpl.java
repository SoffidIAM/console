package es.caib.seycon.ng.servei;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
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

import com.soffid.iam.api.AttributeVisibilityEnum;
import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.ScheduledTaskHandler;
import com.soffid.iam.service.SystemScheduledTasks;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.servei.BpmConfigService;
import es.caib.bpm.vo.ConfigParameterVO;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.AutoritzacioRol;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.DominiContrasenya;
import es.caib.seycon.ng.comu.DominiUsuari;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.OsType;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.PoliticaContrasenya;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration;
import es.caib.seycon.ng.comu.TipusUsuari;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.Xarxa;
import es.caib.seycon.ng.config.Config;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;

public class ApplicationBootServiceImpl extends ApplicationBootServiceBase implements ApplicationContextAware
{

	/**
	 * 
	 */
	private AccountService accountSvc;
	private PuntEntradaService peSvc;
	private DadesAddicionalsService tdSvc;
	private AutoritzacioService autSvc;
	private InternalPasswordService passSvc;
	private UsuariService usuariSvc;
	private XarxaService xarxaSvc;
	private DispatcherService dispatcherSvc;
	private DominiUsuariService dominiSvc;
	private GrupService grupSvc;
	private AplicacioService appSvc;
	private ConfiguracioService configSvc;
	private BpmConfigService bpmConfigSvc;

	private Log log = LogFactory.getLog(ApplicationBootService.class);
	private ApplicationContext applicationContext;

	
	@Override
	protected void handleSyncServerBoot() throws Exception
	{
		System.setProperty("soffid.ui.maxrows", Integer.toString(Integer.MAX_VALUE)); //$NON-NLS-1$
		configureSystemProperties();
		System.setProperty("soffid.ui.maxrows", Integer.toString(Integer.MAX_VALUE)); //$NON-NLS-1$
	}

	@Override
	protected void handleConsoleBoot() throws Exception
	{
		SeyconServiceLocator.instance();
		
		System.setProperty("soffid.ui.maxrows", //$NON-NLS-1$
			Integer.toString(Integer.MAX_VALUE)); //$NON-NLS-1$

		System.setProperty("soffid.ui.wildcards", "auto"); //$NON-NLS-1$ //$NON-NLS-2$
		
		configureDatabase();
		
		Config.configureClient(getServerList(), getServerPort());
		
		configureWildcards();
		
		configureMaxRowsToLists();

		configureSystemProperties();

		createScheduledTasks();
		
		loadWorkflows ();
	}
	
	/**
	 * 
	 */
	private void loadWorkflows ()
	{
		String jbossHome = System.getProperty ("jboss.server.home.dir"); //$NON-NLS-1$
		if (jbossHome != null)
		{
    		File jbossdir = new File(jbossHome); //$NON-NLS-1$
    		File soffiddir = new File(jbossdir, "soffid"); //$NON-NLS-1$
    		File bpm = new File(soffiddir, "bpm"); //$NON-NLS-1$
    		if (bpm.isDirectory())
    		{
        		for (File wf: bpm.listFiles())
        		{
        			if (wf.getName().endsWith(".par")) //$NON-NLS-1$
        			{
        				try {
        					log.info(String.format("Verifying BPM definition %s", wf.toString())); //$NON-NLS-1$
        					FileInputStream in = new FileInputStream(wf);
        					getBpmEngine().upgradeParFile(in);
        				} catch (IOException e) 
        				{
        					log.info(String.format("Error reading file %s: %s", wf.toString(), e.toString())); //$NON-NLS-1$
            			} catch (InternalErrorException e) 
            			{
        					log.info(String.format("Error uploading workflow definition file %s: %s", wf.toString(), e.toString())); //$NON-NLS-1$
            			}
        			}
        		}
    		}
		}
	}

	/**
	 * Method that implements the functionality to add max number of rows to show on lists.
	 */
	private void configureMaxRowsToLists() throws InternalErrorException
	{
		Collection<Configuracio> result =
			configSvc.findConfiguracioByFiltre("soffid.ui.maxrows", //$NON-NLS-1$
				null, null, null);
		
		if (result.isEmpty())
		{
			Configuracio configuracio = new Configuracio();
			configuracio.setCodi("soffid.ui.maxrows"); //$NON-NLS-1$
			configuracio.setValor("200"); //$NON-NLS-1$
			configuracio.setDescripcio(Messages
				.getString("ApplicationBootServiceImpl.maxRowsDescription")); //$NON-NLS-1$
			configSvc.create(configuracio);
		}
		
		else
		{
			System.setProperty("soffid.ui.maxrows", //$NON-NLS-1$
				result.iterator().next().getValor());
		}
	}

	/**
	 * Method to implements the functionality to add wildcards parameter
	 * to configuration.
	 * @throws InternalErrorException
	 */
	private void configureWildcards () throws InternalErrorException
	{
		Collection<Configuracio> result =
			configSvc.findConfiguracioByFiltre("soffid.ui.wildcards", //$NON-NLS-1$
				null, null, null);
		
		if (result.isEmpty())
		{
			Configuracio configuracio = new Configuracio();
			configuracio.setCodi("soffid.ui.wildcards"); //$NON-NLS-1$
			configuracio.setValor("auto"); //$NON-NLS-1$
			configuracio.setDescripcio(Messages.getString("ApplicationBootServiceImpl.WildcardsDescription")); //$NON-NLS-1$
			configSvc.create(configuracio);
		}
		
		else
		{
			System.setProperty("soffid.ui.wildcards", //$NON-NLS-1$
				result.iterator().next().getValor());
		}
	}

	private String getServerPort() throws InternalErrorException, SQLException, NamingException {
        Configuracio parametre = getConfiguracioService().findParametreByCodiAndCodiXarxa(
                "seycon.https.port", null); //$NON-NLS-1$
        if (parametre != null)
        	return parametre.getValor();
        else
        	return null;
    }

    private String getServerList() throws InternalErrorException, SQLException, NamingException {
        Configuracio parametre = getConfiguracioService().findParametreByCodiAndCodiXarxa(
                "seycon.server.list", null); //$NON-NLS-1$
        if (parametre != null)
        	return parametre.getValor();
        else
        	return null;
    }


	private void configureDatabase() throws SystemException, NotSupportedException,
			InternalErrorException, RollbackException, HeuristicMixedException,
			HeuristicRollbackException, NeedsAccountNameException, AccountAlreadyExistsException, BPMException, IOException, NamingException, SQLException
	{
		Security.nestedLogin("Anonymous", new String[] { //$NON-NLS-1$
				Security.AUTO_APPLICATION_CREATE + Security.AUTO_ALL,
						Security.AUTO_USER_CREATE + Security.AUTO_ALL,
						Security.AUTO_HOST_ALL_CREATE,
						Security.AUTO_INTRANETMENUS_ADMIN,
						Security.AUTO_AUTHORIZATION_ALL });
		try
		{
			bpmConfigSvc = getBpmConfigService();
			configSvc = getConfiguracioService();
			appSvc = getAplicacioService();
			grupSvc = getGrupService();
			dominiSvc = getDominiUsuariService();
			dispatcherSvc = getDispatcherService();
			xarxaSvc = getXarxaService();
			usuariSvc = getUsuariService();
			passSvc = getInternalPasswordService();
			autSvc = getAutoritzacioService();
			tdSvc = getDadesAddicionalsService();
			peSvc = getPuntEntradaService();
			accountSvc = getAccountService();

			Configuracio cfg = configSvc.findParametreByCodiAndCodiXarxa(
					"versionLevel", null); //$NON-NLS-1$
			boolean firstSetup = (cfg == null);
			if (firstSetup)
			{
				createInitialData();
				cfg = new Configuracio("versionLevel", "1"); //$NON-NLS-1$ //$NON-NLS-2$
				configSvc.create(cfg);
			}
			if (cfg.getValor().equals("1")) { //$NON-NLS-1$
				configureIndexDir(cfg);
				cfg.setValor("2"); //$NON-NLS-1$
				configSvc.update(cfg);
			}
			if (cfg.getValor().equals("2")) { //$NON-NLS-1$
				if (! firstSetup)
					updateToVersion1_2(cfg);
				cfg.setValor("3"); //$NON-NLS-1$
				configSvc.update(cfg);
			}
			if (cfg.getValor().equals("3")) { //$NON-NLS-1$
				upgradeOperatingSystems();
				cfg.setValor("4"); //$NON-NLS-1$
				configSvc.update(cfg);
			}
			if (cfg.getValor().equals("4")) { //$NON-NLS-1$
				configureDocumentManager();
				upgradeServers();
				cfg.setValor("5"); //$NON-NLS-1$
				configSvc.update(cfg);
			}
			if (cfg.getValor().equals("5")) { //$NON-NLS-1$
				upgradeAuthoritativeSources();
				cfg.setValor("6"); //$NON-NLS-1$
				configSvc.update(cfg);
			}
			if (cfg.getValor().equals("6")) { //$NON-NLS-1$
				upgradeAttributeMappings();
				cfg.setValor("7"); //$NON-NLS-1$
				configSvc.update(cfg);
			}
			if (cfg.getValor().equals("7")) { //$NON-NLS-1$
				cfg.setValor("8"); //$NON-NLS-1$
				configSvc.update(cfg);
			}
			if (cfg.getValor().equals("8")) { //$NON-NLS-1$
				cfg.setValor("9"); //$NON-NLS-1$
				updateRolAccounts();
			}
		}
		finally
		{
			Security.nestedLogoff();
		}
	}
	
	
	/**
	 * @throws SQLException 
	 * 
	 */
	private void updateRolAccounts () throws SQLException
	{
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		final Connection conn = ds.getConnection();
		
		try
		{
			executeSentence(conn, "UPDATE SC_ROLUSU SET RLU_ENABLE=? WHERE RLU_ENABLE IS NULL",
							new Object[] {true});
		}
		finally
		{
			conn.close();
		}
	}

	/**
	 * @throws InternalErrorException 
	 * 
	 */
	private void createScheduledTasks () throws InternalErrorException
	{
		Map<String, ScheduledTaskHandler> handlers = new HashMap<String, ScheduledTaskHandler>();
		Map<String, ScheduledTask> tasks = new HashMap<String, ScheduledTask>();;
		for (ScheduledTaskHandler handler : getScheduledTaskService().listHandlers())
		{
			handlers.put (handler.getName(), handler);
		}
		
		 if (! handlers.containsKey(SystemScheduledTasks.EXPIRE_UNTRUSTED_PASSWORDS))
		 {
			 ScheduledTaskHandler handler = new ScheduledTaskHandler();
			 handler.setClassName("com.soffid.iam.sync.engine.cron.ExpireUntrustedPasswordsTask"); //$NON-NLS-1$
			 handler.setName(SystemScheduledTasks.EXPIRE_UNTRUSTED_PASSWORDS);
			 getScheduledTaskService().create(handler);
		 }

		 if (! handlers.containsKey(SystemScheduledTasks.AUTHORITATIVE_DATA_IMPORT))
		 {
			 ScheduledTaskHandler handler = new ScheduledTaskHandler();
			 handler.setClassName("com.soffid.iam.sync.engine.cron.AuthoritativeImportTask"); //$NON-NLS-1$
			 handler.setName(SystemScheduledTasks.AUTHORITATIVE_DATA_IMPORT);
			 getScheduledTaskService().create(handler);
		 }


		 if (! handlers.containsKey(SystemScheduledTasks.DISABLE_EXPIRE_PASSWORDS))
		 {
			 ScheduledTaskHandler handler = new ScheduledTaskHandler();
			 handler.setClassName("com.soffid.iam.sync.engine.cron.DisableExpiredPasswordsTask"); //$NON-NLS-1$
			 handler.setName(SystemScheduledTasks.DISABLE_EXPIRE_PASSWORDS);
			 getScheduledTaskService().create(handler);
		 }

		 if (! handlers.containsKey(SystemScheduledTasks.RECONCILE_DISPATCHER))
		 {
			 ScheduledTaskHandler handler = new ScheduledTaskHandler();
			 handler.setClassName("com.soffid.iam.sync.engine.cron.ReconcileAgentTask"); //$NON-NLS-1$
			 handler.setName(SystemScheduledTasks.RECONCILE_DISPATCHER);
			 getScheduledTaskService().create(handler);
		 }

		 if (! handlers.containsKey(SystemScheduledTasks.ENABLE_DISABLE_ROLES))
		 {
			 ScheduledTaskHandler handler = new ScheduledTaskHandler();
			 handler.setClassName("com.soffid.iam.sync.engine.cron.ExpireRoleAssignmentsTask");
			 handler.setName(SystemScheduledTasks.ENABLE_DISABLE_ROLES);
			 getScheduledTaskService().create(handler);
		 }

		 for (ScheduledTask task: getScheduledTaskService().listTasks())
		 {
			 String id = task.getHandlerName();
			 if (task.getParams() != null)
				 id = id + ":"+ task.getParams(); //$NON-NLS-1$
			 tasks.put(id, task);
		 }

		 if (! tasks.containsKey(SystemScheduledTasks.EXPIRE_UNTRUSTED_PASSWORDS))
		 {
			 ScheduledTask task = new ScheduledTask();
			 task.setActive(true);
			 task.setDayOfWeekPattern("*"); //$NON-NLS-1$
			 task.setDayPattern("*"); //$NON-NLS-1$
			 task.setHandlerName(SystemScheduledTasks.EXPIRE_UNTRUSTED_PASSWORDS);
			 task.setHoursPattern("0"); //$NON-NLS-1$
			 task.setMinutesPattern("0"); //$NON-NLS-1$
			 task.setMonthsPattern("*"); //$NON-NLS-1$
			 task.setName("Expire untrusted passwords"); //$NON-NLS-1$
			 getScheduledTaskService().create(task);
		 }

		 if (! tasks.containsKey(SystemScheduledTasks.DISABLE_EXPIRE_PASSWORDS))
		 {
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
		 
		 for (Dispatcher dispatcher: dispatcherSvc.findDispatchersByFiltre(null, null, null, null, null, null))
		 {
			 if (!tasks.containsKey(SystemScheduledTasks.RECONCILE_DISPATCHER+":"+dispatcher.getId())) //$NON-NLS-1$
			 {
				 ScheduledTask task = new ScheduledTask();
				 task.setActive(false);
				 task.setDayOfWeekPattern("*"); //$NON-NLS-1$
				 task.setDayPattern("*"); //$NON-NLS-1$
				 task.setHandlerName(SystemScheduledTasks.RECONCILE_DISPATCHER);
				 task.setHoursPattern("0"); //$NON-NLS-1$
				 task.setMinutesPattern("30"); //$NON-NLS-1$
				 task.setMonthsPattern("*"); //$NON-NLS-1$
				 task.setParams(dispatcher.getId().toString());
				 task.setName("Reconcile unmanaged accounts from "+dispatcher.getCodi()); //$NON-NLS-1$
				 getScheduledTaskService().create(task);
			 }
		 }

		 if (! tasks.containsKey(SystemScheduledTasks.ENABLE_DISABLE_ROLES))
		 {
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

	/**
	 * ALTER TABLE SC_AGEDES ADD ADE_ATTMAP BIT not null
	 * ALTER TABLE SC_DISPAT ADD DIS_TIMSTA datetime null
	 * @throws SQLException 
	 * @throws NamingException 
	 */
	private void upgradeAttributeMappings () throws SQLException, NamingException
	{
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		final Connection conn = ds.getConnection();
		
		try
		{
			executeSentence(conn,
							"UPDATE SC_AGEDES SET ADE_ATTMAP=0 WHERE ADE_ATTMAP IS NULL", //$NON-NLS-1$
							new Object[0]);
			executeSentence(conn,
						"UPDATE SC_DISPAT SET DIS_TIMSTA=? WHERE DIS_TIMSTA IS NULL", //$NON-NLS-1$
						new Object[] { new java.util.Date()});
		}
		finally
		{
			conn.close();
		}
	}

	/**
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws InternalErrorException 
	 * 
	 */
	private void upgradeAuthoritativeSources () throws NamingException, SQLException, InternalErrorException
	{
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		final Connection conn = ds.getConnection();
		
		try
		{
			executeSentence(conn,
							"UPDATE SC_AGEDES SET ADE_AUTHOR=0 WHERE ADE_AUTHOR IS NULL", //$NON-NLS-1$
							new Object[0]);
					}
		finally
		{
			conn.close();
		}
	}
	/**
	 * @throws NamingException 
	 * @throws SQLException 
	 * @throws InternalErrorException 
	 * 
	 */
	private void upgradeServers () throws NamingException, SQLException, InternalErrorException
	{
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		final Connection conn = ds.getConnection();
		
		try
		{
			for (Object [] row: executeQuery(conn, "SELECT SRV_ID, SRV_NOM FROM SC_SERVER WHERE SRV_TYPE IS NULL")) //$NON-NLS-1$
			{
				String url = "https://"+row[1]+":"+getServerPort()+"/"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				executeSentence(conn,
								"UPDATE SC_SERVER SET SRV_TYPE='server', SRV_URL=? WHERE SRV_ID=?", //$NON-NLS-1$
								new Object[] { url, row[0] });
				
			}
		}
		finally
		{
			conn.close();
		}
	}

	/** Method to upgrade previous operating system configured.
	 * @throws SQLException 
	 * @throws NamingException 
	 * @throws InternalErrorException 
	 */
	private void upgradeOperatingSystems ()
		throws SQLException, NamingException, InternalErrorException
	{
		
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		
		final Connection conn = ds.getConnection();
		List<OsType> osList = new LinkedList<OsType>();
		
		DatabaseMetaData metaData = conn.getMetaData();
		ResultSet result = metaData.getColumns(null, null,
			"SC_MAQUIN", "MAQ_SISOPE"); //$NON-NLS-1$ //$NON-NLS-2$
		if (result.next())
		{
			try
			{
				osList.add(new OsType(null, "WNT", "Windows NT")); //$NON-NLS-1$ //$NON-NLS-2$
				osList.add(new OsType(null, "LIN", "Linux")); //$NON-NLS-1$ //$NON-NLS-2$
				osList.add(new OsType(null, "W95", "Windows 95")); //$NON-NLS-1$ //$NON-NLS-2$
				osList.add(new OsType(null, "ALT", "Alternative OS")); //$NON-NLS-1$ //$NON-NLS-2$
				osList.add(new OsType(null, "SOL", "Solaris")); //$NON-NLS-1$ //$NON-NLS-2$
				osList.add(new OsType(null, "NTS", "NT Server")); //$NON-NLS-1$ //$NON-NLS-2$
				osList.add(new OsType(null, "WTS", "Windows Terminal Server")); //$NON-NLS-1$ //$NON-NLS-2$
				
				for (OsType os : osList)
				{
					// Check existing OS
					if (xarxaSvc.findOSTypeByName(os.getName()) == null)
					{
						os = xarxaSvc.create(os);
					}
					
					executeSentence(conn,
						"UPDATE SC_MAQUIN SET MAQ_OST_ID=? WHERE MAQ_SISOPE=?", //$NON-NLS-1$
						new Object[] { os.getId(), os.getName() });
				}
			}
			
			finally
			{
				conn.close();
			}
		}
		
		result.close();
	}

	private void executeSentence (Connection conn, String sql, Object ...objects ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
    		parseParameters(stmt, objects);
    		stmt.execute();
		} finally {
			stmt.close ();
		}
	}

	private List<Object[]> executeQuery (Connection conn, String sql, Object ...objects ) throws SQLException
	{
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			parseParameters(stmt, objects);
    		ResultSet rset = stmt.executeQuery();
    		try {
        		List<Object[]> result = new LinkedList<Object[]>();
        		int cols = rset.getMetaData().getColumnCount();
        		while (rset.next())
        		{
        			Object [] row = new Object[cols];
        			for (int i = 0; i < cols; i++)
        			{
        				row [i] = rset.getObject(i+1);
        			}
        			result.add(row);
        		}
    			return result;
    		} finally {
    			rset.close ();
    		}
		} finally {
			stmt.close ();
		}
	}

	private void executeQuery (Connection conn, String sql, Object []objects, RowProcessor processor ) throws SQLException, InternalErrorException
	{
		PreparedStatement stmt = conn.prepareStatement(sql);
		try {
			parseParameters(stmt, objects);
    		ResultSet rset = stmt.executeQuery();
    		try {
        		int cols = rset.getMetaData().getColumnCount();
        		while (rset.next())
        		{
        			Object [] row = new Object[cols];
        			for (int i = 0; i < cols; i++)
        			{
        				row [i] = rset.getObject(i+1);
        			}
        			processor.processRow(row);
        		}
    		} finally {
    			rset.close ();
    		}
		} finally {
			stmt.close ();
		}
	}

	protected void parseParameters (PreparedStatement stmt, Object... objects)
					throws SQLException
	{
		int id = 1;
		for (Object p: objects)
		{
			if (p == null)
				stmt.setString(id++, null);
			else if (p instanceof String)
				stmt.setString(id++, (String) p);
			else if (p instanceof Integer)
				stmt.setInt(id++, ((Integer) p).intValue());
			else if (p instanceof Long)
				stmt.setLong(id++, ((Long) p).longValue());
			else if (p instanceof Date)
				stmt.setDate(id++, (Date)p);
			else if (p instanceof java.util.Date)
				stmt.setDate(id++, new Date(((java.util.Date) p).getTime()));
			else 
				stmt.setObject(id++, p);
		}
	}

	protected void updateToVersion1_2 (Configuracio cfg) throws NamingException,
					SQLException, InternalErrorException
	{
		DataSource ds = (DataSource) applicationContext.getBean("dataSource"); //$NON-NLS-1$
		final Connection conn = ds.getConnection();
		

		try {
			executeQuery (conn, "SELECT COUNT(*) FROM SC_CODUSU", new Object[0]); //$NON-NLS-1$
		} catch (SQLException e) {
			conn.close();
			// Not a v1.0 database
			return;
		}
		try {
			final Map<String, Dispatcher> dispatchers = new HashMap<String, Dispatcher>();
			// Add new dispatchers info
			log.info(Messages.getString("ApplicationBootServiceImpl.UpdatingConnectorAgents")); //$NON-NLS-1$
			executeSentence(conn, "UPDATE SC_DISPAT SET DIS_MAIN=0 WHERE DIS_MAIN IS NULL"); //$NON-NLS-1$
			executeSentence(conn, "UPDATE SC_DISPAT SET DIS_RDONLY=0 WHERE DIS_RDONLY IS NULL"); //$NON-NLS-1$
			executeSentence(conn, "UPDATE SC_DISPAT SET DIS_DOU_ID=(SELECT DCN_DOU_ID FROM SC_DOMCON WHERE DCN_ID=DIS_DCN_ID)"); //$NON-NLS-1$
			log.info(Messages.getString("ApplicationBootServiceImpl.UpdatingAccounts")); //$NON-NLS-1$
			// Create accounts
			executeQuery(conn, 	"SELECT USU_ID, CUD_CODI, DIS_CODI, CUD_ID "+ //$NON-NLS-1$
						"FROM SC_USUARI, SC_CODUSU, SC_DISPAT " + //$NON-NLS-1$
						"WHERE USU_ID = CUD_USU_ID AND CUD_DOU_ID = DIS_DOU_ID AND CUD_CODI IS NOT NULL", new Object[0], //$NON-NLS-1$
				new RowProcessor()
				{
					
					public void processRow (Object[] row) throws SQLException, InternalErrorException
					{
						Long id = Long.decode(row[0].toString());
						Usuari usuari = usuariSvc.findUsuariByIdUsuari(id);
						Dispatcher dispatcher = dispatchers.get(row[2].toString());
						if (dispatcher == null)
						{
							dispatcher = dispatcherSvc.findDispatcherByCodi(row[2].toString());
							dispatchers.put(row[2].toString(), dispatcher);
						}

						String accountName = row[1].toString();
						if (accountSvc.findAccount(accountName, dispatcher.getCodi()) == null)
						{
							try
							{
								log.info(String.format(Messages.getString("ApplicationBootServiceImpl.CreatingAccount"), //$NON-NLS-1$
												row[1].toString(),
												dispatcher.getCodi(),
												usuari.getCodi()));
								accountSvc.createAccount(usuari, dispatcher, row[1].toString());
							}
							catch (NeedsAccountNameException e)
							{
								throw new InternalErrorException(Messages.getString("ApplicationBootServiceImpl.CanNotCreateAccount"), e); //$NON-NLS-1$
							}
							catch (AccountAlreadyExistsException e)
							{
								throw new InternalErrorException(Messages.getString("ApplicationBootServiceImpl.CanNotCreateAccount"), e); //$NON-NLS-1$
							}
							executeSentence(conn, "UPDATE SC_CODUSU SET CUD_CODI=NULL WHERE CUD_ID=?", new Object[]{row[3]}); //$NON-NLS-1$
						}
						else
						{
							log.info(String.format(Messages.getString("ApplicationBootServiceImpl.SkippingAccount"), //$NON-NLS-1$
											row[1].toString(),
											dispatcher.getCodi(),
											usuari.getCodi()));
						}
					}
				});
			log.info(Messages.getString("ApplicationBootServiceImpl.18")); //$NON-NLS-1$
			// Bind rolaccounts to accounts, not users
			executeSentence(conn, "UPDATE SC_ROLUSU " +  //$NON-NLS-1$
					"SET RLU_ACC_ID=(" +  //$NON-NLS-1$
						"SELECT ACC_ID " +  //$NON-NLS-1$
						"FROM SC_USUACC, SC_ACCOUN, SC_ROLES " +  //$NON-NLS-1$
						"WHERE UAC_USU_ID=RLU_IDUSU AND ACC_ID=UAC_ACC_ID AND ACC_DIS_ID=ROL_IDDISPAT AND ROL_ID=RLU_IDROL), " +  //$NON-NLS-1$
						"RLU_IDUSU=NULL "+  //$NON-NLS-1$
					"WHERE RLU_IDUSU IS NOT NULL");  //$NON-NLS-1$
			// Update user domains account type
			log.info(Messages.getString("ApplicationBootServiceImpl.26")); //$NON-NLS-1$
			executeSentence(conn, "UPDATE SC_DOMUSU SET DOU_TIPUS='" + TipusDominiUsuariEnumeration.PRINCIPAL.getValue()+"' WHERE DOU_TIPUS IS NULL");   //$NON-NLS-1$ //$NON-NLS-2$
			// Update PoliticaContraseñaDominio
			log.info(Messages.getString("ApplicationBootServiceImpl.29")); //$NON-NLS-1$
			executeSentence(conn, "UPDATE SC_POCODO SET PCD_PASQRY=1, PCD_PASCHG=1 WHERE PCD_TIPUS='A' AND PCD_PASQRY IS NULL");  //$NON-NLS-1$
			executeSentence(conn, "UPDATE SC_POCODO SET PCD_PASQRY=0, PCD_PASCHG=1 WHERE PCD_TIPUS='M' AND PCD_PASQRY IS NULL");  //$NON-NLS-1$
			// Update usuari seu value			
//			executeSentence(conn, "UPDATE SC_USUARI SET USU_USE_ID=INFORMACIO_SEU WHERE INFORMACIO_SEU IS NOT NULL AND USU_USE_ID IS NOT NULL");
			// Update password owner
			log.info(Messages.getString("ApplicationBootServiceImpl.32")); //$NON-NLS-1$
			
			try
			{
				if (executeQuery(conn,
						"SELECT * FROM SC_COD_USU", new Object()).isEmpty()) //$NON-NLS-1$
				{
					executeSentence(conn, "UPDATE SC_CONTRA " +  //$NON-NLS-1$
						"SET CTR_IDUSU=(SELECT CUD_USU_ID FROM SC_CODUSU WHERE CUD_ID = CTR_CUD_ID), " +  //$NON-NLS-1$
						"CTR_CUD_ID=NULL "+  //$NON-NLS-1$
						"WHERE CTR_CUD_ID IS NOT NULL");  //$NON-NLS-1$
				}
			}
			
			catch (SQLException ex) {}
			
			// Update default operating system
		} finally {
			conn.close ();
		}
		cfg.setValor("3");  //$NON-NLS-1$
		configSvc.update(cfg);
	}

	protected void configureIndexDir (Configuracio cfg) throws InternalErrorException,
					IOException, BPMException
	{
		Configuracio cfg2 = configSvc.findParametreByCodiAndCodiXarxa(
				"seycon.https.port", null); //$NON-NLS-1$
		if (cfg2 == null)
		{
			cfg2 = new Configuracio("seycon.https.port", "760"); //$NON-NLS-1$ //$NON-NLS-2$
			configSvc.create(cfg2);
		}
		
		try {
    		String installDir = System.getProperty("jboss.server.base.dir") + "../..";   //$NON-NLS-1$ //$NON-NLS-2$
    		File f = new File(installDir).getCanonicalFile();
    		ConfigParameterVO configVO = new ConfigParameterVO();
    		configVO.setApp("BPM");  //$NON-NLS-1$
    		configVO.setKey("lucene.dir");  //$NON-NLS-1$
    		configVO.setValue(new File(f, "docs/index").getAbsolutePath());  //$NON-NLS-1$
    		bpmConfigSvc.create(configVO);
		} catch (BPMException e) {
			if (! e.getMessage().equals ("Could not find datasource")) //$NON-NLS-1$
				throw e;
		}
	}
	
	/**
	 * Method that implements the functionality to set
	 * the document manager settings by default.
	 * @throws BPMException 
	 * @throws InternalErrorException 
	 * 
	 */
	protected void configureDocumentManager () throws InternalErrorException,
		BPMException, IOException
	{
		Collection<Configuracio> result =
			configSvc.findConfiguracioByFiltre("soffid.ui.docStrategy", //$NON-NLS-1$
				null, null, null);
		
		if (result.isEmpty())
		{
			Configuracio configuracio = new Configuracio();
			configuracio.setCodi("soffid.ui.docStrategy"); //$NON-NLS-1$
			configuracio.setValor("es.caib.bpm.nas.comm.LocalFileSystemStrategy"); //$NON-NLS-1$
			configSvc.create(configuracio);
		}
		
		else
		{
			System.setProperty("soffid.ui.docStrategy", //$NON-NLS-1$
				result.iterator().next().getValor());
		}
		
		result = configSvc
			.findConfiguracioByFiltre("soffid.ui.docPath", //$NON-NLS-1$
				null, null, null);
		
		if (result.isEmpty())
		{
			String installDir =
				System.getProperty("jboss.home.dir") + "../..";   //$NON-NLS-1$ //$NON-NLS-2$
			File f = new File(installDir).getCanonicalFile();
			File rootPath = new File(f, "/docs/data"); //$NON-NLS-1$
			Configuracio configuracio = new Configuracio();
			configuracio.setCodi("soffid.ui.docPath"); //$NON-NLS-1$
			configuracio.setValor(rootPath.getAbsolutePath());
			configSvc.create(configuracio);
			
			rootPath.mkdirs();
		}
		
		else
		{
			System.setProperty("soffid.ui.docPath", //$NON-NLS-1$
				result.iterator().next().getValor());
		}
		
		result = configSvc
			.findConfiguracioByFiltre("soffid.ui.docTempPath", //$NON-NLS-1$
				null, null, null);
		
		if (result.isEmpty())
		{
			String installDir =
				System.getProperty("jboss.home.dir") + "../..";   //$NON-NLS-1$ //$NON-NLS-2$
			File f = new File(installDir).getCanonicalFile();
			File rootPath = new File(f, "/docs/tmp"); //$NON-NLS-1$
			Configuracio configuracio = new Configuracio();
			configuracio.setCodi("soffid.ui.docTempPath"); //$NON-NLS-1$
			configuracio.setValor(rootPath.getAbsolutePath());
			configSvc.create(configuracio);
			
			rootPath.mkdirs();
		}
		
		else
		{
			System.setProperty("soffid.ui.docTempPath", //$NON-NLS-1$
				result.iterator().next().getValor());
		}
	}

	protected void createInitialData () throws InternalErrorException,
		NeedsAccountNameException, AccountAlreadyExistsException,
		SQLException, NamingException
	{
		Configuracio cfg = null;
		
		createScheduledTasks();

		DominiUsuari du = dominiSvc
				.findDominiUsuariByCodi("DEFAULT"); //$NON-NLS-1$
		if (du == null)
		{
			du = new DominiUsuari();
			du.setCodi("DEFAULT"); //$NON-NLS-1$
			du.setDescripcio("Default user domain"); //$NON-NLS-1$
			du.setTipus(TipusDominiUsuariEnumeration.PRINCIPAL);
			du = dominiSvc.create(du);
		}

		DominiContrasenya dc = dominiSvc.findDominiContrasenyaByCodi("DEFAULT");  //$NON-NLS-1$
		if (dc == null)
		{
			dc = new DominiContrasenya();
			dc.setCodi("DEFAULT"); //$NON-NLS-1$
			dc.setDescripcio("Default password domain"); //$NON-NLS-1$
			dc = dominiSvc.create(dc);
		}

		Collection<TipusUsuari> tus = dominiSvc.findAllTipusUsuari();
		TipusUsuari tipUs;
		if (tus.size() > 0)
		{
			tipUs = tus.iterator().next();
		}
		else
		{
			tipUs = new TipusUsuari();
			tipUs.setCodi("I"); //$NON-NLS-1$
			tipUs.setDescripcio("Internal user"); //$NON-NLS-1$
			dominiSvc.create(tipUs);
		}

		Collection<PoliticaContrasenya> pcs = dominiSvc
				.findAllPolitiquesContrasenyaDomini(dc.getCodi());
		if (pcs.size() == 0)
		{
			PoliticaContrasenya pol = new PoliticaContrasenya();
			pol.setCodiDominiContrasenya(dc.getCodi());
			pol.setCodiDominiUsuaris(du.getCodi());
			pol.setDescripcio("Default password policy"); //$NON-NLS-1$
			pol.setDuradaMaxima(new Long(365));
			pol.setDuradaMaximaCaducada(new Long(365));
			pol.setTipus("M"); //$NON-NLS-1$
			pol.setTipusUsuari(tipUs.getCodi());
			dominiSvc.create(pol);
		}

		Aplicacio app = appSvc.findAplicacioByCodiAplicacio("SOFFID"); //$NON-NLS-1$
		if (app == null)
		{
			app = new Aplicacio();
			app.setBd(""); //$NON-NLS-1$
			app.setCodi("SOFFID"); //$NON-NLS-1$
			app.setGestionableWF(new Boolean(false));
			app.setNom("SOFFID Identity Manager"); //$NON-NLS-1$
			app = appSvc.create(app);
		}

		Dispatcher dis = dispatcherSvc.findDispatcherByCodi("soffid"); //$NON-NLS-1$
		if (dis == null)
		{
			dis = new Dispatcher();
			dis.setBasRol(new Boolean(true));
			dis.setCodi("soffid"); //$NON-NLS-1$
			dis.setDescription("Soffid database");
			dis.setControlAccess(new Boolean(false));
			dis.setDominiContrasenyes(dc.getCodi());
			dis.setDominiUsuaris(du.getCodi());
			dis.setIdDominiContrasenyes(dc.getId());
			dis.setNomCla("- no class -"); //$NON-NLS-1$
			dis.setRelacioLaboral("I"); //$NON-NLS-1$
			dis = dispatcherSvc.create(dis);
		}

		Rol rol = appSvc.findRolByNomRolAndCodiAplicacioAndCodiDispatcher(
				"SOFFID_ADMIN", //$NON-NLS-1$
				app.getCodi(), "soffid"); //$NON-NLS-1$
		if (rol == null)
		{
			rol = new Rol();
			rol.setCodiAplicacio(app.getCodi());
			rol.setBaseDeDades("soffid"); //$NON-NLS-1$
			rol.setContrasenya(new Boolean(false));
			rol.setDefecte(new Boolean(true));
			rol.setDescripcio("SOFFID Administrator"); //$NON-NLS-1$
			rol.setGestionableWF(new Boolean(false));
			rol.setNom("SOFFID_ADMIN"); //$NON-NLS-1$
			rol.setDomini(new Domini());
			rol = appSvc.create(rol);
		}

		Xarxa x = xarxaSvc.findXarxaByCodi("loopback"); //$NON-NLS-1$
		if (x == null)
		{
			x = new Xarxa();
			x.setCodi("loopback"); //$NON-NLS-1$
			x.setAdreca("127.0.0.0"); //$NON-NLS-1$
			x.setMascara("255.255.255.0"); //$NON-NLS-1$
			x.setNormalitzada(new Boolean(false));
			xarxaSvc.create(x);
		}
		
		upgradeOperatingSystems();

		Maquina m = xarxaSvc.findMaquinaByNom("loopback"); //$NON-NLS-1$
		if (m == null)
		{
			m = new Maquina();
			m.setCodiXarxa("loopback"); //$NON-NLS-1$
			m.setAdreca("127.0.0.1"); //$NON-NLS-1$
			m.setCorreu(new Boolean(false));
			m.setDescripcio("Loopback host"); //$NON-NLS-1$
			m.setNom("loopback"); //$NON-NLS-1$
			m.setOfimatica(new Boolean(false));
			m.setServidorImpressores(new Boolean(false));
			m.setSistemaOperatiu("ALT"); //$NON-NLS-1$
			xarxaSvc.create(m);
		}

		m = xarxaSvc.findMaquinaByNom("null"); //$NON-NLS-1$
		if (m == null)
		{
			m = new Maquina();
			m.setCodiXarxa("loopback"); //$NON-NLS-1$
			m.setCorreu(new Boolean(false));
			m.setDescripcio("Void host"); //$NON-NLS-1$
			m.setNom("null"); //$NON-NLS-1$
			m.setOfimatica(new Boolean(false));
			m.setServidorImpressores(new Boolean(false));
			m.setSistemaOperatiu("ALT"); //$NON-NLS-1$
			xarxaSvc.create(m);
		}

		Grup grup = grupSvc.findGrupByCodiGrup("world"); //$NON-NLS-1$
		if (grup == null)
		{
			grup = new Grup();
			grup.setCodi("world"); //$NON-NLS-1$
			grup.setDescripcio("World"); //$NON-NLS-1$
			grup.setObsolet(new Boolean(false));
			grupSvc.create(grup);
		}

		grup = grupSvc.findGrupByCodiGrup("enterprise"); //$NON-NLS-1$
		if (grup == null)
		{
			grup = new Grup();
			grup.setCodi("enterprise"); //$NON-NLS-1$
			grup.setCodiPare("world"); //$NON-NLS-1$
			grup.setDescripcio("Entrprise"); //$NON-NLS-1$
			grup.setObsolet(new Boolean(false));
			grupSvc.create(grup);
		}
		;
		grup = grupSvc.findGrupByCodiGrup("admingroup"); //$NON-NLS-1$
		if (grup == null)
		{
			grup = new Grup();
			grup.setCodi("admingroup"); //$NON-NLS-1$
			grup.setCodiPare("enterprise"); //$NON-NLS-1$
			grup.setDescripcio("Enterprise Administrators"); //$NON-NLS-1$
			grup.setObsolet(new Boolean(false));
			grupSvc.create(grup);
		}

		TipusDada td = tdSvc.findTipusDadaByCodi("NIF"); //$NON-NLS-1$
		if (td == null)
		{
			td = new TipusDada();
			td.setCodi("NIF"); //$NON-NLS-1$
			td.setOrdre(new Long(1));
			tdSvc.create(td);
		}

		Usuari usu = usuariSvc.findUsuariByCodiUsuari("admin"); //$NON-NLS-1$
		if (usu == null)
		{
			usu = new Usuari();
			usu.setCodi("admin"); //$NON-NLS-1$
			usu.setCodiGrupPrimari("admingroup"); //$NON-NLS-1$
			usu.setComentari("Autocreated"); //$NON-NLS-1$
			usu.setMultiSessio(new Boolean(true));
			usu.setNom("Admin"); //$NON-NLS-1$
			usu.setPrimerLlinatge("Admin"); //$NON-NLS-1$
			usu.setServidorHome("null"); //$NON-NLS-1$
			usu.setServidorPerfil("null"); //$NON-NLS-1$
			usu.setServidorCorreu("null"); //$NON-NLS-1$
			usu.setTipusUsuari("I"); //$NON-NLS-1$
			usu.setActiu(new Boolean(true));
			usu = usuariSvc.create(usu);

			passSvc.storePassword(usu.getCodi(), dc.getCodi(),
					"changeit", false); //$NON-NLS-1$
			UserAccount account = null;
			for (UserAccount ua: accountSvc.listUserAccounts(usu))
			{
				if (ua.getDispatcher().equals (dis.getCodi()))
				{
					account = ua;
					break;
				}
			}
			if (account == null)
			{
				account = accountSvc.createAccount(usu, dis, null);
			}
		}

		Collection<AutoritzacioRol> auts = autSvc
				.getRolsAutoritzacio(Security.AUTO_AUTHORIZATION_ALL);
		if (auts.isEmpty())
		{
			AutoritzacioRol aut = new AutoritzacioRol();
			aut.setRol(rol);
			aut.setAutoritzacio(Security.AUTO_AUTHORIZATION_ALL);
			autSvc.create(aut);
		}

		boolean found = false;
		for (RolAccount ru : appSvc.findRolsUsuarisByCodiUsuariAndNomRol(
				usu.getCodi(), rol.getNom()))
		{
			if (ru.getBaseDeDades().equals(rol.getBaseDeDades()))
				found = true;
		}
		if (!found)
		{
			RolAccount ru = new RolAccount();
			ru.setBaseDeDades(rol.getBaseDeDades());
			ru.setCodiAplicacio(app.getCodi());
			ru.setCodiUsuari(usu.getCodi());
			ru.setNomRol(rol.getNom());
			ru.setAccountName("admin");  //$NON-NLS-1$
			appSvc.create(ru);
		}

		cfg = configSvc.findParametreByCodiAndCodiXarxa("SSOServer", null); //$NON-NLS-1$
		if (cfg == null)
		{

			cfg = new Configuracio(
					"SSOServer", System.getProperty("hostName") + "." //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
							+ System.getProperty("domainName")); //$NON-NLS-1$
			configSvc.create(cfg);
		}
	}
	
	protected void configureSystemProperties () throws InternalErrorException 
	{
		if (configSvc == null)
			configSvc = getConfiguracioService();
		for (Configuracio config: configSvc.findConfiguracioByFiltre("%", null, null, null)) //$NON-NLS-1$
		{
			if (config.getCodiXarxa() == null)
			{
				System.setProperty(config.getCodi(), config.getValor());
			}
		}
		if (System.getProperty("soffid.ui.wildcards") == null) //$NON-NLS-1$
			System.setProperty("soffid.ui.wildcards", "auto"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext (ApplicationContext applicationContext)
					throws BeansException
	{
		this.applicationContext = applicationContext;
	}
}

interface RowProcessor {
	void processRow (Object [] row) throws SQLException, InternalErrorException;
}


