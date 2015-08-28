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

import es.caib.seycon.ng.servei.*;

import com.soffid.iam.api.AgentStatusInfo;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.SyncAgentTaskLog;
import com.soffid.iam.api.SyncServerInfo;
import com.soffid.iam.config.Config;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.TaskLogEntity;
import com.soffid.iam.remote.RemoteInvokerFactory;
import com.soffid.iam.remote.RemoteServiceLocator;
import com.soffid.iam.remote.URLManager;
import com.soffid.iam.service.ConfigurationService;
import com.soffid.iam.ssl.ConnectionFactory;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.sync.service.SyncStatusService;
import com.soffid.iam.ui.SeyconTask;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.util.Base64;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import javax.naming.NamingException;
import javax.net.ssl.HttpsURLConnection;

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
        String name = getServerList();
        String port = getServerPort();
        LinkedList<SyncServerInfo> serversInfo = new LinkedList<SyncServerInfo>();
        String serv[] = name.split("[ ,]+"); //$NON-NLS-1$

        try {
            Config.configureClient(name, port);
        } catch (Throwable th) {
            // JUST IN CASE OF ACCIDENT (!!)
            if (serv != null) {
                // Afegim un SeyconServerInfo amb error
                for (int i = 0; i < serv.length; i++) {
                    URLManager m = new URLManager(serv[i]);
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
                    serversInfo.add(new SyncServerInfo(m.getServerURL().toString(), "", "-", "ERROR", "ERROR", "" + numTasquesPendents, "ERROR", "ERROR", "ERROR", null, null, null, null, "ERROR"));
                }
            }
            return serversInfo;
        }

        for (int i = 0; i < serv.length; i++) {
            RemoteServiceLocator rsl = createRemoteServiceLocator(serv[i]);
            URLManager m = new URLManager(serv[i]);
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
                SyncServerInfo info = status.getSyncServerStatus();
                info.setNumberOfPendingTasks("" + numTasquesPendents);
                serversInfo.add(info);
            } catch (Throwable e) {
                serversInfo.add(new SyncServerInfo(m.getServerURL().toString(), "", "-", "ERROR", "ERROR", "" + numTasquesPendents, "ERROR", "ERROR", "ERROR", null, null, null, null, "ERROR"));
            }
        }

        return serversInfo;

    }

    private RemoteServiceLocator createRemoteServiceLocator(String string) throws IOException, InternalErrorException {
        RemoteServiceLocator rsl = new RemoteServiceLocator(string);
        URLManager um = new URLManager(string);
        ServerEntity server = getServerEntityDao().findByName(um.getServerURL().getHost());
        if (server != null)
            rsl.setAuthToken(server.getAuth());
        return rsl;
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
            Collection<AgentStatusInfo> agentstatus = status.getSyncAgentsInfo();

            // Hem de contar les tasques pendents realment en cada agent
            // hem de tindre: url server[url], codiAgent[nomAgent]
            URLManager m = new URLManager(url);
            String host = m.getServerURL().getHost();

            return agentstatus != null ? agentstatus : new LinkedList<AgentStatusInfo>();

        } catch (Throwable th) {
            throw new SeyconException(String.format(
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
                Collection tasques = getTaskEntityDao().
                		findByServer(host);

                if (tasques != null) {
                    // Si n'hi ha cap tasca pendent obtenim tots els TASKLOGs
                    // d'aquest server per optimitzar les consultes sql (encara
                    // que sobrecarreguem la sessió actual a nivell de memoria)
                    Collection allTaskLog = getTaskLogEntityDao().findAllHavingTasqueByServer(host);
                    HashMap<Long, String[]> estatAllTasks = new HashMap<Long, String[]>();

                    for (Iterator tit = allTaskLog.iterator(); tit.hasNext(); ) {
                        TaskLogEntity tl = (TaskLogEntity) tit.next();
                        String[] estatsTL = estatAllTasks.get(tl.getTask().getId());
                        if (estatsTL == null) {
                            estatsTL = new String[nomAgentsActius.size()];
                        }
                        Integer estatAgentTascaActual = agentsPos.get(tl.getSystem().getName());
                        if (estatAgentTascaActual != null) {
                            estatsTL[estatAgentTascaActual] = "S".equals(tl.getCompleted()) ? SeyconTask.Estat.DONE : "N".equals(tl.getCompleted()) ? SeyconTask.Estat.ERROR : SeyconTask.Estat.PENDING;
                            estatAllTasks.put(tl.getTask().getId(), estatsTL);
                        }
                    }

                    // Ara tenim carregats els estats dels agents (per a totes
                    // les tasques), obtenim les tasques

                    for (Iterator it = tasques.iterator(); it.hasNext(); ) {
                        TaskEntity t = (TaskEntity) it.next();
                        String[] estats = estatAllTasks.get(t.getId());
                        if (estats == null) estats = new String[nomAgentsActius.size()];
                        if (t.getSystemName() == null) {
                            for (int i = 0; i < estats.length; i++) if (estats[i] == null) estats[i] = SeyconTask.Estat.PENDING;
                        } else {
                            int estatAgentTascaActual = agentsPos.get(t.getSystemName());
                            for (int i = 0; i < estats.length; i++) {
                                if (estats[i] == null) {
                                    if (i != estatAgentTascaActual) estats[i] = SeyconTask.Estat.DONE; else estats[i] = SeyconTask.Estat.PENDING;
                                }
                            }
                        }
                        SeyconTask st = new SeyconTask(t.getId(), tascaToString(t), estats);
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
        URLManager m = new URLManager(url);
        // Ens quedem només amb el host
        String host = m.getServerURL().getHost();

        // hem d'agafar TOTES les tasques del servidor

        // Obtenim les tasques pendents (ordentades per id desc) D'AQUEST SERVER
        // Tipus TasqueEntity: ordenats per prioritat, data e id
        // Emprem un mètode existent canviant la select...
        Collection<TaskEntity> tasques = getTaskEntityDao().
        		findByServerAndSystem(host, agentCodi);
        		
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
            Collection allTaskLog = getTaskLogEntityDao()
            		.findByServerAndSystem(host, agentCodi);
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
                if (tl != null && "S".equals(tl.getCompleted())) continue;
                if (tl == null) {
                    tasquesAgent.add(new SyncAgentTaskLog(t.getId(), tascaToString(t), agentCodi, "PENDING", "", null, null, null, null, null, null, t.getPriority(), null));
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
                    tasquesAgent.add(new SyncAgentTaskLog(t.getId(), tascaToString(t), agentCodi, tl.getCompleted(), tl.getMessage(), dataCreacio, tl.getLastExecution(), dataDarreraExecucio, tl.getNextExecution(), dataProximaExecucio, tl.getExecutionsNumber(), t.getPriority(), tl.getStackTrace()));
                }
            }

        }// fi_tasques-not-null

        return tasquesAgent;
    }

    protected InputStream handleGetSeyconServerLog(String urlServer) throws Exception {
        // Aquí asumim que la configuració ja està feta (!!)
        // Config.configureClient(name, port);

        try {
            URLManager m = new URLManager(urlServer);
            URL url = m.getServerLogFileURL();

            HttpsURLConnection c = ConnectionFactory.getConnection(url);

            c.setDoInput(true);
            c.setDoOutput(false);
            c.setAllowUserInteraction(false);
            ServerEntity server = getServerEntityDao().findByName(url.getHost());
            if (server == null || server.getAuth() == null)
                throw new InternalErrorException(
                        Messages.getString("SeyconServerServiceImpl.AuthenticationNoObtained") //$NON-NLS-1$
                                + url.getHost());

            if (server.getAuth() != null) {
                String seu = "-seu-:" + server.getAuth(); //$NON-NLS-1$
                byte bytes[] = seu.getBytes("UTF-8"); //$NON-NLS-1$
                String tag = "Basic " //$NON-NLS-1$
                        + Base64.encodeBytes(bytes, 0, bytes.length, Base64.DONT_BREAK_LINES);
                c.addRequestProperty("Authorization", tag); //$NON-NLS-1$

            }
            c.connect();

            // Consumir el stream
            InputStream in = c.getInputStream();

            return in;

        } catch (Throwable th) {
            return null;
        }

    }

    private String getServerList() throws InternalErrorException, SQLException, NamingException {
        ConfigurationService configuracioService = getConfigurationService();
        Configuration parametre = configuracioService.findParameterByNameAndNetworkName("seycon.server.list", null); //$NON-NLS-1$
        
        if (parametre != null)
        	return parametre.getValue();
        
        else
        	return new String();
    }

    private String getServerPort() throws InternalErrorException, SQLException, NamingException {
        ConfigurationService configuracioService = getConfigurationService();
        Configuration parametre = configuracioService.findParameterByNameAndNetworkName("seycon.https.port", null); //$NON-NLS-1$
        return parametre.getValue();
    }

    private String tascaToString(TaskEntity tasca) {
        String result = tasca.getTransaction();
        String transactionCode = tasca.getTransaction();

        if (transactionCode.equals(TaskHandler.UPDATE_USER) || transactionCode.equals(TaskHandler.UPDATE_USER_PASSWORD)
                || transactionCode.equals(TaskHandler.PROPAGATE_PASSWORD)
                || transactionCode.equals(TaskHandler.UPDATE_PROPAGATED_PASSWORD)
                || transactionCode.equals(TaskHandler.UPDATE_ACCOUNT) 
                || transactionCode.equals(TaskHandler.UPDATE_ACCOUNT_PASSWORD) 
                || transactionCode.equals(TaskHandler.VALIDATE_PASSWORD)
                || transactionCode.equals(TaskHandler.UPDATE_USER_ALIAS)
                || transactionCode.equals(TaskHandler.EXPIRE_USER_PASSWORD)
                || transactionCode.equals(TaskHandler.EXPIRE_USER_UNTRUSTED_PASSWORD) 
                || transactionCode.equals(TaskHandler.RECONCILE_USER))
            result = result + " " + tasca.getUser(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_ACCOUNT) ||
        	transactionCode.equals(TaskHandler.UPDATE_ACCOUNT_PASSWORD)) //$NON-NLS-1$
            result = result + "@" + tasca.getSystemName(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_HOST)) //$NON-NLS-1$
            result = result + " " + tasca.getHost(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_GROUP)) //$NON-NLS-1$
            result = result + " " + tasca.getGroup(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_ROLE)  
        		|| transactionCode.equals(TaskHandler.RECONCILE_ROLE)) //$NON-NLS-1$
            result = result + " " + tasca.getRole(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.CREATE_FOLDER)) //$NON-NLS-1$
            result = result + " " + tasca.getFolder(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_OBJECT )
        				|| transactionCode.equals(TaskHandler.DELETE_OBJECT)) //$NON-NLS-1$
            result = result + " " + tasca.getEntity()+"#"+tasca.getPrimaryKeyValue(); //$NON-NLS-1$ //$NON-NLS-2$

        if (transactionCode.equals(TaskHandler.UPDATE_LIST_ALIAS)) //$NON-NLS-1$
            result = result + " " + tasca.getAlias() + "@" + tasca.getMailDomain(); //$NON-NLS-1$ //$NON-NLS-2$

        return result;

    }

    private SyncServerInfo generaTascaPendentBuidaTotOK() {
        // url, descripcio, versio, estat, numAgents,
        // numTasquesPendents, sso, jetty, ssoDaemon, taskGenerator,
        // caducitatRootCertificate,
        // caducitatMainCertificate, dataActualServer, databaseConnections
        return new SyncServerInfo("Tasques sense planificar", "", "", "OK", "Cap Tasca pendent de Planificar", "0", "ERROR", "ERROR", "ERROR", null, null, null, null, "ERROR"); //$NON-NLS-1$
    }

    protected Collection<SyncServerInfo> handleGetPendingTasksInfo() throws Exception {
        LinkedList<SyncServerInfo> serversInfo = new LinkedList();
        try {
            // Afegim tasques sense planificar
            // posem url ficticia TASQUES_SENSE_PLANIFICAR

            // Obtenim el número de tasques
            Collection<TaskEntity> tasquesPlanning = getTaskEntityDao().
            		query("select count(*),min(tasqueEntity.date) "
            				+ "from com.soffid.iam.model.TaskEntity as tasqueEntity "
            				+ "where tasqueEntity.server is null", new Parameter[0]);

            if (tasquesPlanning != null) {
                Iterator it = tasquesPlanning.iterator();
                if (it.hasNext()) { // n'hi ha dues dades: numTasks i timestamp
                    Object dades[] = (Object[]) it.next();
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
                        serversInfo.add(new SyncServerInfo(Messages.getString("SeyconServerServiceImpl.NoTasksPlan"), "", "", colorTasquesPendents, descripcioEstat, "" + numTasquesPendents, "ERROR", "ERROR", "ERROR", null, null, null, null, "ERROR")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

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

    // Funció per cridar diferents mètodes de seycon-server
    protected Collection<Object> handleGetSeyconServerInfo(String url, String quinaInfo,
            String[] params) throws Exception {

        URLManager m = new URLManager(url);
        RemoteServiceLocator rsl = createRemoteServiceLocator(url);
//        rsl.setAuthToken(quinaInfo);

        if ("serverinfo".equalsIgnoreCase(quinaInfo)) { //$NON-NLS-1$
            LinkedList<Object> serversInfo = new LinkedList<Object>();
            try {
                // Obtenim informació del servidor
                SyncStatusService status = rsl.getSyncStatusService();

                // Obtenim informació del servidor
                Object obj = status.getSyncServerInfo();
                if (obj instanceof SyncServerInfo) {
                    SyncServerInfo info = (SyncServerInfo) obj;
                    serversInfo.add(info);
                }
            } catch (Throwable th) {
                // th.printStackTrace();
                // url, versio, estat, numAgents, numTasquesPendents, sso,
                // jetty, ssoDaemon, taskGenerator, caducitatRootCertificate,
                // caducitatMainCertificate, dataActualServer,
                // databaseConnections, serverAgentHostsURL)
                /*
                 * serversInfo.add(new SeyconServerInfo(url, "ERROR", "ERROR",
                 * "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", "ERROR", null,
                 * null, null, "ERROR"));
                 */
              /*  throw new SeyconException(String.format(
                        Messages.getString("SeyconServerServiceImpl.NoConnectionToServer"), th.getMessage())); //$NON-NLS-1$*/
            	throw new SeyconException(Messages.getString("SeyconServerServiceImpl.NoConnectionToServer")); //$NON-NLS-1$
            }

            return serversInfo;
        } else if ("agentServersInfo".equalsIgnoreCase(quinaInfo)) { //$NON-NLS-1$
            // Obtenim les urls dels servidors
            SyncStatusService status = rsl.getSyncStatusService();

            Object obj = status.getServerAgentHostsURL();
            if (obj instanceof Collection) {
                return (Collection) obj;
            }
        } else if ("reset".equalsIgnoreCase(quinaInfo)) { //$NON-NLS-1$
            LinkedList<Object> result = new LinkedList();
            if (params != null && params.length == 1) {
                SyncStatusService status = rsl.getSyncStatusService();

                if ("agents".equals(params[0])) { //$NON-NLS-1$
                    String res = status.resetAllServer();
                    result.add(res);
                } else {
                    String res = status.resetServerAgents(params[0]);
                    result.add(res);
                }
                return result;
            } else {
                throw new SeyconException(Messages.getString("SeyconServerServiceImpl.ServerNotAdded")); //$NON-NLS-1$
            }

        } else if ("dbpool".equalsIgnoreCase(quinaInfo)) { //$NON-NLS-1$
            LinkedList<Object> result = new LinkedList();
            SyncStatusService status = rsl.getSyncStatusService();
            result.add(status.getDBConnectionStatus());
            return result;
        }
        return null;
    }

    int roundrobin = 0;
	@Override
	protected Object handleGetServerService(String servicePath)
			throws Exception {
        String name = getServerList();
        String serv[] = name.split("[ ,]+"); //$NON-NLS-1$
        
        roundrobin ++;
        for (int i = 0; i < serv.length; i++) {
        	String serverName = serv [ (i + roundrobin) % serv.length];
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
        String name = getServerList();
        if (name != null && name.trim().length() > 0)
        {
	        String serv[] = name.split("[ ,]+"); //$NON-NLS-1$
	        
	        roundrobin ++;
	        for (int i = 0; i < serv.length; i++) {
	        	String serverName = serv [ (i + roundrobin) % serv.length];
	            RemoteServiceLocator rsl = createRemoteServiceLocator(serverName);
	            try {
	                SyncStatusService service = rsl.getSyncStatusService();
	                if (service != null)
	                	service.reconfigureDispatchers();;
	            } catch (Throwable e) {
	            }
	        }
        }
	}

}

