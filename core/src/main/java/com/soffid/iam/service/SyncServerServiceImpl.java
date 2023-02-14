/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.naming.NamingException;
import javax.net.ssl.HttpsURLConnection;

import org.apache.commons.logging.LogFactory;

import com.soffid.iam.api.AgentStatusInfo;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.SyncAgentTaskLog;
import com.soffid.iam.api.SyncServerInfo;
import com.soffid.iam.config.Config;
import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.model.ServerInstanceEntity;
import com.soffid.iam.model.StatsEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.TaskLogEntity;
import com.soffid.iam.model.TenantEntity;
import com.soffid.iam.model.TenantServerEntity;
import com.soffid.iam.remote.RemoteServiceLocator;
import com.soffid.iam.remote.URLManager;
import com.soffid.iam.ssl.ConnectionFactory;
import com.soffid.iam.sync.service.SyncServerStatsService;
import com.soffid.iam.sync.service.SyncStatusService;
import com.soffid.iam.ui.SeyconTask;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.util.Base64;

/**
 * @see es.caib.seycon.ng.servei.SeyconServerService
 */
public class SyncServerServiceImpl extends com.soffid.iam.service.SyncServerServiceBase {

    /**
     * @see es.caib.seycon.ng.servei.SeyconServerService#getSeyconServersStatus()
     */
    // Obtenim informació dels servidors de seycon
	@Override
    protected java.util.Collection<SyncServerInfo> handleGetSyncServersStatus() throws java.lang.Exception {
        LinkedList<SyncServerInfo> serversInfo = new LinkedList<SyncServerInfo>();
        List<String> serverList = getServerList();

        StringBuffer sb = new StringBuffer();
        for ( String s: serverList) {
        	if (sb.length() > 0)
        		sb.append (",");
        	sb.append (s);
        }
        
        try {
        	if ( ! Security.isSyncServer())
        		Config.configureClient(sb.toString(), getServerPort());
        } catch (Throwable th) {
            // JUST IN CASE OF ACCIDENT (!!)
            if (serverList != null) {
                // Afegim un SeyconServerInfo amb error
                for (String server: serverList) {
                    URLManager m = new URLManager(server);
                    Long numTasquesPendents = 0L;
                    Collection tPendents = getTaskEntityDao().findDataPendingTasks(m.getServerURL().getHost());
                    if (tPendents != null) {
                        Iterator it = tPendents.iterator();
                        if (it.hasNext()) {
                            Object dades = (Object) it.next();
                            if (dades != null && dades instanceof Long) {
                                numTasquesPendents = (Long) dades;
                            }
                        }
                    }
                    SyncServerInfo si = new SyncServerInfo();
                    si.setConnectedAgents(0);
                    si.setNumberOfAgents( getDispatcherService().findDispatchersByFilter(null, null, null, null, null, Boolean.TRUE).size() );
                    si.setStatus("ERROR");
                    si.setUrl(server);
                    serversInfo.add(si);
                }
            }
            return serversInfo;
        }

        for (String server: serverList) {
            RemoteServiceLocator rsl = createRemoteServiceLocator(server);
            URLManager m = new URLManager(server);
            Long numTasquesPendents = 0L;
            Collection tPendents = getTaskEntityDao().findDataPendingTasks(m.getServerURL().getHost());
            if (tPendents != null) {
                Iterator it = tPendents.iterator();
                if (it.hasNext()) {
                    Object dades = (Object) it.next();
                    if (dades != null && dades instanceof Long) {
                        numTasquesPendents = (Long) dades;
                    }
                }
            }
            try {
                SyncStatusService status = rsl.getSyncStatusService();
                SyncServerInfo info = status.getSyncServerStatus(Security.getCurrentTenantName());
                info.setNumberOfPendingTasks(numTasquesPendents);
                serversInfo.add(info);
            } catch (Throwable e) {
                SyncServerInfo si = new SyncServerInfo();
                si.setConnectedAgents(0);
                si.setNumberOfAgents( getDispatcherService().findDispatchersByFilter(null, null, null, null, null, Boolean.TRUE).size() );
                si.setStatus("ERROR");
                si.setUrl(server);
                si.setNumberOfPendingTasks(numTasquesPendents);
                serversInfo.add(si);
            }
        }

        return serversInfo;

    }

    private RemoteServiceLocator createServerRemoteServiceLocator(String serverName, String serverInstance) throws IOException, InternalErrorException {
    	ServerInstanceEntity si = serverInstance == null ? null:
    		getServerInstanceEntityDao().findByServerNameAndInstanceName(serverName, serverInstance);
		if (si != null) {
			RemoteServiceLocator rsl = new RemoteServiceLocator(si.getUrl());
			rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount());
			rsl.setAuthToken(si.getAuth());        		
			return rsl;
		} else {
			ServerEntity server = getServerEntityDao().findByName(serverName);
			if (server == null) {
				throw new InternalErrorException("Unknown server "+serverName);
			}
			else
			{
				RemoteServiceLocator rsl = new RemoteServiceLocator(server.getUrl());
				rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount());
				rsl.setAuthToken(server.getAuth());
				return rsl;
			}
			
		}
    }
    
    private RemoteServiceLocator createRemoteServiceLocator(String serverName) throws IOException, InternalErrorException {
        RemoteServiceLocator rsl = new RemoteServiceLocator(serverName);
        URLManager um = new URLManager(serverName);
        final String host = um.getServerURL().getHost();
		ServerInstanceEntity si = getServerInstanceEntityDao().findByUrl(serverName);
		if (si == null) 
			si = getServerInstanceEntityDao().findByName(host);
		if (si != null) {
			rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount());
			rsl.setAuthToken(si.getAuth());        		
			return rsl;
		} else {
			ServerEntity server = getServerEntityDao().findByName(host);
			if (server == null) {
				throw new InternalErrorException("Unknown server "+serverName);
			}
			else
			{
				rsl.setTenant(Security.getCurrentTenantName()+"\\"+Security.getCurrentAccount());
				rsl.setAuthToken(server.getAuth());
				return rsl;
			}
			
		}
    }

    /**
     * @see es.caib.seycon.ng.servei.SeyconServerService#getSergerAgentStatus(java.lang.String)
     */
    protected java.util.Collection<AgentStatusInfo> handleGetServerAgentStatus(java.lang.String url)
            throws java.lang.Exception {
        // Aquí asumim que la configuració ja està feta (!!)
        // Config.configureClient(name, port);

        try {
            RemoteServiceLocator rsl = createRemoteServiceLocator(url);
            
            SyncStatusService status = rsl.getSyncStatusService();

            // Obtenin tots els agents del servidor
            Collection<AgentStatusInfo> agentstatus0 = status.getSyncAgentsInfo(Security.getCurrentTenantName());
            LinkedList<AgentStatusInfo> agentstatus;
			if (agentstatus0 == null)
            	agentstatus = new LinkedList<AgentStatusInfo>();
			else
				agentstatus = new LinkedList<AgentStatusInfo> (agentstatus0);
			
			String server;
			try { server = new URL(url).getHost(); } catch (Exception e) {server = url;}
			
			List<AgentStatusInfo> m = new LinkedList<>();

			ServerInstanceEntity si = getServerInstanceEntityDao().findByUrl(url);
			if (si == null) 
				si = getServerInstanceEntityDao().findByName(server);
			Collection<Object[]> tasks = si == null ?
					getTaskEntityDao().countTasksBySystem(server) :
					getTaskEntityDao().countTasksBySystem(si.getServer().getName(), si.getName());
			Collection<Object[]> tl = si == null?
					getTaskLogEntityDao().countTasksByServerAndSystem(server) :
					getTaskLogEntityDao().countTasksByServerAndSystem(si.getServer().getName(), si.getName());

			for ( AgentStatusInfo as: agentstatus) {
				as.setPendingTasks(0);
				for (Object[] tll: tasks) {
					if ( tll[0] == null || tll[0].toString().trim().isEmpty() ||
							tll[0].equals(as.getAgentName())) {
						as.setPendingTasks( as.getPendingTasks() + ((Long) tll[1]).intValue());
					}
				}
				for (Object[] tll: tl) {
					if ( as.getAgentName().equals(tll[0])) {
						as.setPendingTasks( as.getPendingTasks() - ((Long) tll[1]).intValue());
					}
				}
			}

            Collections.sort(agentstatus, new Comparator<AgentStatusInfo>() {
				@Override
				public int compare(AgentStatusInfo o1, AgentStatusInfo o2) {
					return o1.getAgentName().compareToIgnoreCase(o2.getAgentName());
				}
			});

            return agentstatus;

        } catch (Throwable th) {
        	LogFactory.getLog(getClass()).info("Unable to connecto to "+url, th);
            throw new InternalErrorException(String.format(
                    Messages.getString("SeyconServerServiceImpl.NoConnectionToServer"), th.getMessage())); //$NON-NLS-1$
        }

    }

    /**
     * @see es.caib.seycon.ng.servei.SeyconServerService#getServerTasks(java.lang.String)
     */
    protected java.util.Collection<Object> handleGetServerTasks(java.lang.String url)
            throws java.lang.Exception {

        // Contindrà dos elements: 1) nom dels agents 2) tasques pendents
        // d'aquestos agents (amb estat)
        Collection<Object> resultat = new LinkedList();
        Collection<String> nomAgentsActius = new LinkedList<String>();
        Collection<SeyconTask> tasquesPendents = new LinkedList<SeyconTask>();

        try {
            URLManager m = new URLManager(url);
            // Ens quedem només amb el host
            String host = m.getServerURL().getHost();

            if (host != null) {
        		ServerInstanceEntity si = getServerInstanceEntityDao().findByUrl(url);
        		if (si == null)
        			si = getServerInstanceEntityDao().findByName(host);
                // Obtenim el llistat d'agents (capçalera)
                Collection agentsActius = getSystemEntityDao().findActives();
                HashMap<String, Integer> agentsPos = new HashMap(agentsActius.size());

                if (agentsActius != null) {

                    Integer index = 0;
                    for (Iterator it = agentsActius.iterator(); it.hasNext(); ) {
                        SystemEntity ag = (SystemEntity) it.next();
                        agentsPos.put(ag.getName(), index++);
                        nomAgentsActius.add(ag.getName());
                    }
                }

                // Obtenim les tasques pendents D'AQUEST SERVER
                // Ordenades per data i id
                Collection tasques = si == null?
                		getTaskEntityDao().findByServer(host):
                		getTaskEntityDao().findByServerAndServerInstance(si.getServer().getName(), si.getName());

                if (tasques != null) {
                    // Si n'hi ha cap tasca pendent obtenim tots els TASKLOGs
                    // d'aquest server per optimitzar les consultes sql (encara
                    // que sobrecarreguem la sessió actual a nivell de memoria)
                    Collection allTaskLog = si == null ?
                    		getTaskLogEntityDao().findAllHavingTasqueByServer(host):
                    		getTaskLogEntityDao().findAllHavingTasqueByServerAndServerInstance(si.getServer().getName(), si.getName());
                    HashMap<Long, String[][]> estatAllTasks = new HashMap<Long, String[][]>();

                    for (Iterator tit = allTaskLog.iterator(); tit.hasNext(); ) {
                        TaskLogEntity tl = (TaskLogEntity) tit.next();
                        Integer estatAgentTascaActual = agentsPos.get(tl.getSystem().getName());
                        if (estatAgentTascaActual != null) {
	                        String[][] s = estatAllTasks.get(tl.getTask().getId());
	                        String[]  estatsTL;
	                        String[]  messages;
	                        String[]  exceptions;
	                        if (s == null) {
	                            estatsTL = new String[nomAgentsActius.size()];
	                            messages = new String[nomAgentsActius.size()];
	                            exceptions = new String[nomAgentsActius.size()];
	                            estatAllTasks.put(tl.getTask().getId(), new String[][] { estatsTL, messages, exceptions});
	                        } else {
	                        	estatsTL = s[0];
	                        	messages = s[1];
	                        	exceptions = s[2];
	                        }
                        	if ("N".equals(tl.getCompleted())) {
                        		messages[estatAgentTascaActual] = tl.getMessage();
                        		exceptions[estatAgentTascaActual] = tl.getStackTrace();
                        	}
                            estatsTL[estatAgentTascaActual] = "S".equals(tl.getCompleted()) ? 
                            		SeyconTask.Estat.DONE : 
                            		"N".equals(tl.getCompleted()) ? SeyconTask.Estat.ERROR : 
                            			SeyconTask.Estat.PENDING;
                        }
                    }

                    // Ara tenim carregats els estats dels agents (per a totes
                    // les tasques), obtenim les tasques

                    for (Iterator it = tasques.iterator(); it.hasNext(); ) {
                        TaskEntity t = (TaskEntity) it.next();
                        String[][] s = estatAllTasks.get(t.getId());
                        String[] estats;
						if (s == null) 
							estats = new String[nomAgentsActius.size()];
                        else 
                        	estats = s[0];
                        if (t.getSystemName() == null) {
                            for (int i = 0; i < estats.length; i++) if (estats[i] == null) estats[i] = SeyconTask.Estat.PENDING;
                        } else {
                            int estatAgentTascaActual = agentsPos.get(t.getSystemName());
                            for (int i = 0; i < estats.length; i++) {
                                if (estats[i] == null) {
                                    if (i != estatAgentTascaActual) estats[i] = SeyconTask.Estat.DONE; 
                                    else estats[i] = SeyconTask.Estat.PENDING;
                                }
                            }
                        }
                        SeyconTask st = new SeyconTask(t.getId(), tascaToString(t), estats);
                        st.setMessage(s != null && s.length > 1 ? s[1]: null);
                        st.setException(s != null && s.length > 2 ? s[2]: null);
                        tasquesPendents.add(st);
                    }

                }// fi_tasques-not-null

            }
            resultat.add(nomAgentsActius);
            resultat.add(tasquesPendents);
            return resultat;

        } catch (Throwable th) {
            resultat.add(nomAgentsActius);
            resultat.add(tasquesPendents);
            return resultat;
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.SeyconServerService#getAgentTasks(java.lang.String,
     *      java.lang.String)
     */
    protected java.util.Collection<SyncAgentTaskLog> handleGetAgentTasks(java.lang.String url, java.lang.String agentCodi) throws java.lang.Exception {
        // Ens quedem només amb el host
        String host = new URL(url).getHost();

		ServerInstanceEntity si = getServerInstanceEntityDao().findByUrl(url);
		if (si == null) 
			si = getServerInstanceEntityDao().findByName(host);
        Collection<TaskEntity> tasques = si == null ? 
        		getTaskEntityDao().findByServerAndSystem(host, agentCodi):
       			getTaskEntityDao().findByServerAndSystem(si.getServer().getName(), si.getName(), agentCodi);
        		
        // Construim el resultat
        LinkedList<SyncAgentTaskLog> tasquesAgent = new LinkedList();

        if (tasques != null) {
            // Obtenim les tasklogs de totes les tasques de l'agent (compertes i
            // no complertes..)
            // si cerquem només les No compertes podem perdre les PENDING
            // (encara no existeixen al tasklog)
            // Tipus TaskLogEntity
            // En principi no importa l'ordre.. (es fa amb l'ordre de les
            // tasques)
            Collection allTaskLog = si == null ?
            		getTaskLogEntityDao().findByServerAndSystem(host, agentCodi) :
            		getTaskLogEntityDao().findByServerAndSystem(si.getServer().getName(), si.getName(), agentCodi);
            HashMap<Long, TaskLogEntity> allTaskLogs = new HashMap<Long, TaskLogEntity>();

            for (Iterator tit = allTaskLog.iterator(); tit.hasNext(); ) {
                TaskLogEntity tl = (TaskLogEntity) tit.next();
                allTaskLogs.put(tl.getTask().getId(), tl);
            }

            // Ara tenim carregats els estat de l'agent (per a totes
            // les tasques), obtenim les tasques

            for (Iterator it = tasques.iterator(); it.hasNext(); ) {
                TaskEntity t = (TaskEntity) it.next();
                TaskLogEntity tl = allTaskLogs.get(t.getId());
                SyncAgentTaskLog satl;
                if (tl != null && "S".equals(tl.getCompleted())) continue;
                satl = generateSeyconAgentTaskLog(agentCodi, t, tl);
                tasquesAgent.add(satl);
            }

        }// fi_tasques-not-null

        return tasquesAgent;
    }

	public SyncAgentTaskLog generateSeyconAgentTaskLog(java.lang.String agentCodi, TaskEntity t, TaskLogEntity tl) {
		SyncAgentTaskLog satl;
		if (tl == null) {
		    satl = new SyncAgentTaskLog(t.getId(), tascaToString(t), agentCodi, "PENDING", "", null, null, null, null, null, null, t.getPriority(), null);
		} else {
		    Calendar dataDarreraExecucio = null;
		    if (tl.getLastExecution() != null) {
		        dataDarreraExecucio = Calendar.getInstance();
		        dataDarreraExecucio.setTimeInMillis(tl.getLastExecution());
		    }
		    Calendar dataProximaExecucio = null;
		    if (tl.getNextExecution() != null) {
		        dataProximaExecucio = Calendar.getInstance();
		        dataProximaExecucio.setTimeInMillis(tl.getNextExecution());
		    }
		    Calendar dataCreacio = null;
		    if (tl.getCreationDate() != null) {
		        dataCreacio = Calendar.getInstance();
		        dataCreacio.setTime(tl.getCreationDate());
		    }
		    satl = new SyncAgentTaskLog(t.getId(), tascaToString(t), agentCodi, tl.getCompleted(), tl.getMessage(), dataCreacio, tl.getLastExecution(), dataDarreraExecucio, tl.getNextExecution(), dataProximaExecucio, tl.getExecutionsNumber(), t.getPriority(), tl.getStackTrace());
		}
		return satl;
	}

    protected InputStream handleGetSeyconServerLog(String urlServer) throws Exception {
        // Aquí asumim que la configuració ja està feta (!!)
        // Config.configureClient(name, port);

        try {
            URLManager m = new URLManager(urlServer);
            URL url = m.getServerLogFileURL();

            HttpURLConnection c = ConnectionFactory.getConnection(url);

            c.setDoInput(true);
            c.setDoOutput(false);
            c.setAllowUserInteraction(false);
            
            String seu = "-seu-" +
            		Security.getCurrentTenantName()+
            		"\\"+
            		Security.getCurrentAccount()+
            		":" ;
    		ServerInstanceEntity si = getServerInstanceEntityDao().findByUrl(urlServer);
    		if (si == null) 
    			si = getServerInstanceEntityDao().findByName(url.getHost());
    		if (si != null) {
                seu = seu + si.getAuth(); //$NON-NLS-1$
    		} else {
    			ServerEntity server = getServerEntityDao().findByName(url.getHost());
    			if (server == null) {
    				throw new InternalErrorException("Unknown server "+urlServer);
    			}
    			else
    			{
    				seu += server.getAuth();
    			}
    			
    		}
            
            byte bytes[] = seu.getBytes("UTF-8"); //$NON-NLS-1$
            String tag = "Basic " //$NON-NLS-1$
                    + Base64.encodeBytes(bytes, 0, bytes.length, Base64.DONT_BREAK_LINES);
            c.addRequestProperty("Authorization", tag); //$NON-NLS-1$
            c.addRequestProperty("Accept-Language", MessageFactory.getLocale().getLanguage());

            c.connect();

            // Consumir el stream
            InputStream in = c.getInputStream();

            return in;

        } catch (Throwable th) {
            return null;
        }

    }

    private List<String> getServerList() throws InternalErrorException, SQLException, NamingException {
    	Set<String> list = new HashSet<String>();
    	
    	TenantEntity tenant = getTenantEntityDao().findByName(Security.getCurrentTenantName());
    	for (TenantServerEntity s: tenant.getServers())
    	{
    		if (s.getTenantServer().getType() == ServerType.MASTERSERVER) {
    			Collection<ServerInstanceEntity> instances = s.getTenantServer().getInstances();
    			if (instances.isEmpty())
    				list.add(s.getTenantServer().getUrl());
    			else {
    				for (ServerInstanceEntity instance: instances) {
    					list.add(instance.getUrl());
    				}
    			}
    		}
    	}
		return new java.util.LinkedList<String>(list);
    }

    private String getServerPort() throws InternalErrorException, SQLException, NamingException {
    	String port = ConfigurationCache.getMasterProperty("seycon.https.port");
    	return port == null ? "760": port;
    }


    private SyncServerInfo generaTascaPendentBuidaTotOK() {
        return null;
    }

    protected Collection<SyncServerInfo> handleGetPendingTasksInfo() throws Exception {
        LinkedList<SyncServerInfo> serversInfo = new LinkedList();
        try {
            // Afegim tasques sense planificar
            // posem url ficticia TASQUES_SENSE_PLANIFICAR

            // Obtenim el número de tasques
            Collection<Object[]> tasquesPlanning = getTaskEntityDao().findDataUnplannedTasks();

            if (tasquesPlanning != null) {
                Iterator<Object[]> it = tasquesPlanning.iterator();
                if (it.hasNext()) { // n'hi ha dues dades: numTasks i timestamp
                    Object dades[] = it.next();
                    if (dades != null && dades.length >= 2) {
                        Long numTasquesPendents = (Long) dades[0];
                        Timestamp mindata = (Timestamp) dades[1];

                        String colorTasquesPendents = "PLANNING"; //$NON-NLS-1$
                        String descripcioEstat = Messages.getString("SeyconServerServiceImpl.PlanTasks"); //$NON-NLS-1$

                        if (numTasquesPendents == 0) {
                            colorTasquesPendents = "OK"; //$NON-NLS-1$
                            descripcioEstat = Messages.getString("SeyconServerServiceImpl.NoTasksToPlan"); //$NON-NLS-1$
                        } else if ((mindata.getTime() + (2 * 60 * 1000)) < Calendar.getInstance()
                                .getTimeInMillis()) {
                            // MÉS DE 2 MINUTS
                            colorTasquesPendents = "ERROR"; //$NON-NLS-1$
                            descripcioEstat = Messages.getString("SeyconServerServiceImpl.PlanTime"); //$NON-NLS-1$
                        }
                        // en altre cas ja està inicialitzat
                        /*
                         * else { colorTasquesPendents = "PLANNING"; }
                         */

                        // url, versio, estat,
                        // numAgents, numTasquesPendents, sso, jetty,
                        // ssoDaemon, taskGenerator, caducitatRootCertificate,
                        // caducitatMainCertificate, dataActualServer,
                        // databaseConnections
//                        serversInfo.add(new SyncServerInfo(Messages.getString("SeyconServerServiceImpl.NoTasksPlan"), "", "", colorTasquesPendents, descripcioEstat, "" + numTasquesPendents, "ERROR", "ERROR", "ERROR", null, null, null, null, "ERROR")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

                    } else {
                        serversInfo.add(generaTascaPendentBuidaTotOK());
                    }

                } else {
                    // No en queda cap tasca
                    serversInfo.add(generaTascaPendentBuidaTotOK());
                }
            }

        } catch (Throwable th) {
            // Ignorem aquest error... per mostrem traces d'errors
            th.printStackTrace();
        }
        return serversInfo;
    }


    int roundrobin = 0;
	@Override
	protected Object handleGetServerService(String servicePath)
			throws Exception {
        List<String> serv = getServerList();
        
        roundrobin ++;
        for (int i = 0; i < serv.size(); i++) {
        	String serverName = serv.get ( (i + roundrobin) % serv.size() );
            RemoteServiceLocator rsl = createRemoteServiceLocator(serverName);
            try {
                Object service = rsl.getRemoteService(servicePath);
                if (service != null)
                	return service;
            } catch (Throwable e) {
            }
        }
        return null;
	}

	@Override
	protected void handleUpdateDispatcherConfiguration() throws Exception {
        List<String> serv = getServerList();
	        
        roundrobin ++;
        for (int i = 0; i < serv.size(); i++) {
        	String serverName = serv.get( (i + roundrobin) % serv.size());
            try {
                RemoteServiceLocator rsl = createRemoteServiceLocator(serverName);
                SyncStatusService service = rsl.getSyncStatusService();
                if (service != null)
                	service.reconfigureDispatchers();;
            } catch (Throwable e) {
            }
        }
	}

	@Override
	protected void handleBoostTask(long taskId) throws Exception {
		TaskEntity task = getTaskEntityDao().load(taskId);
	
		if (task != null) {
			String server = task.getServer();
			
			if (server == null)
			{
				throw new InternalErrorException(String.format("Task %d is not scheduled yet", taskId));
			} else {
		        RemoteServiceLocator rsl = createServerRemoteServiceLocator(server, task.getServerInstance());
		        
		        SyncStatusService status = rsl.getSyncStatusService();
	
		        status.boostTask(taskId);
			}
		}
	}

	@Override
	protected void handleCancelTask(long taskId) throws Exception {
		TaskEntity task = getTaskEntityDao().load(taskId);
		if (task == null)
			return;
		
		String server = task.getServer();
		
		if (server == null)
		{
			getTaskEntityDao().remove(task);
		} else {
	        RemoteServiceLocator rsl = createServerRemoteServiceLocator(server, task.getServerInstance());
	        
	        SyncStatusService status = rsl.getSyncStatusService();

	        status.cancelTask(taskId);
		}
		
	}

	@Override
	protected SyncServerInfo handleGetSyncServerInfo(String url) throws Exception {
        URLManager m = new URLManager(url);
        RemoteServiceLocator rsl = createRemoteServiceLocator(url);

        try {
        	SyncStatusService status = rsl.getSyncStatusService();
        	SyncServerInfo si = status.getSyncServerInfo(Security.getCurrentTenantName());
        	si.setStatus("OK");
        	return si;
        } catch (Exception e) {
            SyncServerInfo si = new SyncServerInfo();
            si.setConnectedAgents(0);
            si.setNumberOfAgents( getDispatcherService().findDispatchersByFilter(null, null, null, null, null, Boolean.TRUE).size() );
            si.setStatus("OFFLINE");
            si.setUrl(url);
			si.setNumberOfPendingTasks(0);
			for (Long data: getTaskEntityDao().findDataPendingTasks(m.getServerURL().getHost())) {
				if (data != null)
					si.setNumberOfPendingTasks(data.longValue());
            }
            return si;
        }

	}

	@Override
	protected void handleResetSyncServer(String url, String agent) throws Exception {
		URLManager m = new URLManager(url);
        RemoteServiceLocator rsl = createRemoteServiceLocator(url);
        
        SyncStatusService status = rsl.getSyncStatusService();

        if (agent == null) { //$NON-NLS-1$
            status.resetAllServer();
        } else {
            status.resetServerAgents(agent);
        }
	}

	@Override
	protected Collection<SeyconTask> handleFindUnscheduledTasks() throws Exception {
		List<TaskEntity> l = getTaskEntityDao().findUnscheduled();
		List<SeyconTask> l2 = new LinkedList<SeyconTask>();
		for (TaskEntity e: l)
		{
			SeyconTask st = new SeyconTask(e.getId(), tascaToString(e));
			st.setStatus(e.getStatus() == null || e.getStatus().equals("P") ? SeyconTask.Estat.PENDING :
				e.getStatus().equals("E") ? SeyconTask.Estat.ERROR :
				e.getStatus().equals("X") ? SeyconTask.Estat.PAUSED :
				 SeyconTask.Estat.UNKNOWN
				);
			l2.add(st);
		}
		
		return l2;
	}

	private String tascaToString(TaskEntity e) {
		return e.toString();
	}

	@Override
	protected void handleReleaseAllTasks() throws Exception {
		getTaskEntityDao().releaseAll();		
	}

	@Override
	protected void handleCancelUnscheduledTasks() throws Exception {
		getTaskEntityDao().cancelUnscheduled();		
	}

	@Override
	protected void handleReleaseTask(long taskId) throws Exception {
		TaskEntity t = getTaskEntityDao().load(taskId);
		if ( t != null && t.getStatus().equals("X"))
		{
			t.setStatus("P");
			getTaskEntityDao().update(t);
		}
	}

	@Override
	protected Map<String, int[]> handleGetStats(String server, String metric, int seconds, int step) throws Exception {
        try {
            RemoteServiceLocator rsl = createRemoteServiceLocator(server);
            
            SyncServerStatsService stats = rsl.getSyncServerStatsService();

            return stats.getStats(metric, seconds, step);
        } catch (Throwable th) {
        	LogFactory.getLog(getClass()).info("Unable to connecto to "+server, th);
            throw new InternalErrorException(String.format(
                    Messages.getString("SeyconServerServiceImpl.NoConnectionToServer"), th.getMessage())); //$NON-NLS-1$
        }
	}

	@Override
	protected String[] handleTailServerLog(String urlServer) throws Exception {
        List<String> serv = getServerList();
        
        roundrobin ++;
        for (int i = 0; i < serv.size(); i++) {
        	String serverName = serv.get( (i + roundrobin) % serv.size());
            try {
                RemoteServiceLocator rsl = createRemoteServiceLocator(serverName);
                SyncStatusService stats = rsl.getSyncStatusService();
                
                return stats.tailServerLog(urlServer);
            } catch (Throwable e) {
            }
        }
		return null;
	}

	@Override
	protected Collection<Server> handleGetSyncServers() throws Exception {
        LinkedList<Server> list = new LinkedList<Server>();

    	TenantEntity tenant = getTenantEntityDao().findByName(Security.getCurrentTenantName());
    	HashSet<String> names = new HashSet<>();
    	for (TenantServerEntity s: tenant.getServers())
    	{
    		if (s.getTenantServer().getType() == ServerType.MASTERSERVER && !names.contains(s.getTenantServer().getUrl())) {
				if (s.getTenantServer().getInstances().isEmpty()) {
					Server server = getServerEntityDao().toServer(s.getTenantServer());
					server.setPk(null);
					server.setPublicKey(null);
					server.setUseMasterDatabase(false);
					server.setAuth(null);
					list.add( server);
					names.add(s.getTenantServer().getUrl());
				} else {
					for (ServerInstanceEntity si: s.getTenantServer().getInstances()) {
						Server server = getServerEntityDao().toServer(s.getTenantServer());
						server.setId(si.getId());
						server.setUrl(si.getUrl());
						server.setPk(null);
						server.setName(si.getName());
						server.setPublicKey(null);
						server.setUseMasterDatabase(false);
						server.setAuth(null);
						list.add( server);
						names.add(s.getTenantServer().getUrl());
					}
					
				}
				
    		}
    	}
    	return list;
	}

	@Override
	protected Collection<AgentStatusInfo> handleGetServerAgentStatus() throws Exception {
		List<AgentStatusInfo> m = new LinkedList<>();

		Collection<Object[]> tasks = getTaskEntityDao().countTasksBySystem();
		Collection<Object[]> tl = getTaskLogEntityDao().countTasksBySystem();
		for (SystemEntity agent: getSystemEntityDao().findActives()) {
			AgentStatusInfo s = new AgentStatusInfo();
			s.setAgentName(agent.getName());
			s.setClassName(agent.getClassName());
			s.setUrl(agent.getUrl());
			s.setPendingTasks(0);
			for (Object[] tll: tasks) {
				if ( tll[0] == null || tll[0].toString().trim().isEmpty() ||
						tll[0].equals(agent.getName())) {
					s.setPendingTasks( s.getPendingTasks() + ((Long) tll[1]).intValue());
				}
			}
			for (Object[] tll: tl) {
				if ( agent.getName().equals(tll[0])) {
					s.setPendingTasks(s.getPendingTasks() - ((Long) tll[1]).intValue());
				}
			}
			m.add(s);
		}
		Collections.sort(m, new Comparator<AgentStatusInfo>() {
			@Override
			public int compare(AgentStatusInfo o1, AgentStatusInfo o2) {
				return o1.getAgentName().compareTo(o2.getAgentName());
			}
		});
		return m;
	}

	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmm");
	@Override
	protected Map<String,Vector<Object[]>> handleGetPendingTasksStats() throws Exception {
		Calendar now = Calendar.getInstance();
		now.set(Calendar.SECOND, 0);
		now.set(Calendar.MILLISECOND, 0);
		
		Map<String, Vector<Object[]>> map = new HashMap<>();
		Calendar then = Calendar.getInstance();
		then.setTime(now.getTime());
		then.add(Calendar.MINUTE, -29);
		
		HashSet<String> names = new HashSet<String>();
		for (Server s: handleGetSyncServerInstances()) {
			names.add(s.getName());
		}

		List<TaskEntity> unscheduled = getTaskEntityDao().findUnscheduled();
		Vector<Object[]> data = null;
		Map<String, Integer> lastPosition = new HashMap<>();
		// Create data for unscheduled based on current task queue
		data = createSerie ("Unscheduled", then, map);
		for (int p = 0; p < 30; p++) {
			Date d2 = (Date) data.get(p)[0];
			long l = 0;
			for (TaskEntity task: unscheduled) {
				if (task.getDate().before(d2))
					l ++;
			}
			data.get(p)[1] = new Long(l);
		}
		// Load data from stats
		for ( StatsEntity stat: getStatsEntityDao().findByName("pending-tasks", 
				simpleDateFormat.format(then.getTime()),
				simpleDateFormat.format(now.getTime()))) {
			int lastposition = 0;
			Date d = simpleDateFormat.parse(stat.getDate());
			int position = (int) ( ( d.getTime() - then.getTimeInMillis() ) / 60000L);
			if ( ! map.containsKey(stat.getSerie() )) {
				data = createSerie(stat.getSerie(), then, map);
			} else {
				Integer p = lastPosition.get(stat.getSerie());
				lastposition = p == null ? -1: p.intValue();
				data = map.get(stat.getSerie());
			}
			lastPosition.put(stat.getSerie(), position);

			if (position >= 0 && position < 30) {
				data.get(position)[1] = stat.getValue();
				if (stat.getSerie().equals("Unscheduled")) {
					if (lastposition == -1) 
						for (int i = 0; i < position; i++)
							data.get(i)[1] = stat.getValue();
					else
						for (int i = lastposition + 1; i < position; i++)
							data.get(i)[1] = data.get(lastposition)[1];
				} else if (!names.contains(stat.getSerie())) {
					data.get(position)[1] = stat.getValue();
					for ( int i = position+1; i < 30; i++)
						data.get(i)[1] = new Long(0);
				} else {
					for ( int i = position; i < 30; i++)
						data.get(i)[1] = stat.getValue();
				}
			}
		}
		return map;
	}

	public Vector<Object[]> createSerie(String serie, Calendar start, Map<String, Vector<Object[]>> map) {
		Vector<Object[]> data;
		data = new Vector<Object[]>();
		
		Calendar c = Calendar.getInstance();
		c.setTime(start.getTime());;
		for ( int i = 0; i < 30; i++) {
			Object[] o = new Object[2];
			o[0] = c.getTime();
			o[1] = new Long(0);
			c.add(Calendar.MINUTE, +1);
			data.add(o);
		}
		map.put(serie, data);
		return data;
	}

	@Override
	protected void handleUpdatePendingTasks() throws Exception {
		String d = simpleDateFormat.format(new Date());
		Long c = getTaskEntityDao().countUnscheduledTasks();
		StatsEntity stats = getStatsEntityDao().newStatsEntity();
		stats.setDate(d);
		stats.setName("pending-tasks");
		stats.setSerie("Unscheduled");
		stats.setValue(c);
		getStatsEntityDao().create(stats);
    	TenantEntity tenant = getTenantEntityDao().findByName(Security.getCurrentTenantName());
    	HashSet<String> names = new HashSet<>();
    	for (TenantServerEntity s: tenant.getServers())
    	{
    		if (s.getTenantServer().getType() == ServerType.MASTERSERVER && !names.contains(s.getTenantServer().getUrl())) {
    			ServerEntity server = s.getTenantServer();
				if (server.getInstances().isEmpty()) {
					c = getTaskEntityDao().countTasksByServer(server.getName());
					stats = getStatsEntityDao().newStatsEntity();
					stats.setDate(d);
					stats.setName("pending-tasks");
					stats.setSerie(server.getName());
					stats.setValue(c);
					getStatsEntityDao().create(stats);			
				} else {
					for (ServerInstanceEntity si: s.getTenantServer().getInstances()) {
						c = getTaskEntityDao().countTasksByServerInstance(server.getName(), si.getName());
						stats = getStatsEntityDao().newStatsEntity();
						stats.setDate(d);
						stats.setName("pending-tasks");
						stats.setSerie(si.getName());
						stats.setValue(c);
						getStatsEntityDao().create(stats);			
					}
					
				}
				
    		}
    	}
	}

	@Override
	protected SyncAgentTaskLog handleGetAgentTasks(String url, String agentName, Long taskId) throws Exception {
		TaskEntity te = getTaskEntityDao().load(taskId);
		if (te == null)
			return null;
		for (TaskLogEntity tl: te.getTaskLogs()) {
			if (tl.getSystem().getName().equals(agentName)) 
				return generateSeyconAgentTaskLog(agentName, te, tl);
		}
		return generateSeyconAgentTaskLog(agentName, te, null);		
	}

	@Override
	protected Collection<Server> handleGetSyncServerInstances() throws Exception {
        LinkedList<Server> list = new LinkedList<Server>();

    	TenantEntity tenant = getTenantEntityDao().findByName(Security.getCurrentTenantName());
    	HashSet<String> names = new HashSet<>();
    	for (TenantServerEntity s: tenant.getServers())
    	{
    		final ServerEntity serverEntity = s.getTenantServer();
			if (serverEntity.getType() == ServerType.MASTERSERVER && !names.contains(serverEntity.getUrl())) {
    			
    			Server server = getServerEntityDao().toServer(serverEntity);
    			server.setPk(null);
    			server.setPublicKey(null);
    			server.setUseMasterDatabase(false);
    			server.setAuth(null);
    			
    			if (serverEntity.getInstances().isEmpty())
    				list.add( server);
    			else {
    				for (ServerInstanceEntity instance: serverEntity.getInstances()) {
    	    			server.setPk(null);
    	    			server.setPublicKey(null);
    	    			server.setUseMasterDatabase(false);
    	    			server.setAuth(null);
    					Server i = new Server(server);
    					i.setUrl(instance.getUrl());
    					i.setId(instance.getId());
    					i.setName(instance.getName());
    					list.add(i);
    				}
    			}
				names.add(serverEntity.getUrl());
    		}
    	}
    	return list;
	}

}

