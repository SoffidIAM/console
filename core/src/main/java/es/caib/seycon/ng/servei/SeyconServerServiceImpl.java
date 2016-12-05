/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

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

import org.apache.commons.logging.LogFactory;
import org.zkoss.util.logging.Log;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.comu.AgentStatusInfo;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.SeyconAgentTaskLog;
import es.caib.seycon.ng.comu.SeyconServerInfo;
import es.caib.seycon.ng.config.Config;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.ServerEntity;
import es.caib.seycon.ng.model.TaskLogEntity;
import es.caib.seycon.ng.model.TasqueEntity;
import es.caib.seycon.ng.remote.RemoteInvokerFactory;
import es.caib.seycon.ng.remote.RemoteServiceLocator;
import es.caib.seycon.ng.remote.URLManager;
import es.caib.seycon.ng.sync.engine.TaskHandler;
import es.caib.seycon.ng.sync.servei.SyncStatusService;
import es.caib.seycon.ng.ui.SeyconTask;
import es.caib.seycon.ssl.ConnectionFactory;
import es.caib.seycon.util.Base64;

/**
 * @see es.caib.seycon.ng.servei.SeyconServerService
 */
public class SeyconServerServiceImpl extends es.caib.seycon.ng.servei.SeyconServerServiceBase {

    /**
     * @see es.caib.seycon.ng.servei.SeyconServerService#getSeyconServersStatus()
     */
    // Obtenim informació dels servidors de seycon
    protected java.util.Collection<SeyconServerInfo> handleGetSeyconServersStatus()
            throws java.lang.Exception {
        String name = getServerList();
        String port = getServerPort();
        LinkedList<SeyconServerInfo> serversInfo = new LinkedList<SeyconServerInfo>();
        String serv[] = name.split("[ ,]+"); //$NON-NLS-1$

        try {
            Config.configureClient(name, port);
        } catch (Throwable th) {
            // JUST IN CASE OF ACCIDENT (!!)
            if (serv != null) {
                // Afegim un SeyconServerInfo amb error
                for (int i = 0; i < serv.length; i++) {
                    URLManager m = new URLManager(serv[i]);

                    Long numTasquesPendents = 0l;

                    // Cerquem les tasques pendents d'aquest server
                    // (ha donat error, per tindre més info)
                    Collection tPendents = getTasqueEntityDao().findDadesTasquesPendentsServer(
                            m.getServerURL().getHost());
                    if (tPendents != null) {
                        Iterator it = tPendents.iterator();
                        if (it.hasNext()) {
                            Object dades = (Object) it.next();
                            if (dades != null && dades instanceof Long) {
                                numTasquesPendents = (Long) dades;
                            }
                        }
                    }
                    // JUST IN CASE OF ACCIDENT
                    // url, versio, estat, numAgents,
                    // numTasquesPendents, sso, jetty, ssoDaemon, taskGenerator,
                    // caducitatRootCertificate,
                    // caducitatMainCertificate, dataActualServer,
                    // databaseConnections
                    // SeyconServerInfo(url, descripcio, versio, estat,
                    // numAgents, numTasquesPendents, sso, jetty, ssoDaemon,
                    // taskGenerator, caducitatRootCertificate,
                    // caducitatMainCertificate, dataActualServer,
                    // databaseConnections)
                    serversInfo.add(new SeyconServerInfo(m.getServerURL().toString(), "", "-", //$NON-NLS-1$ //$NON-NLS-2$
                            "ERROR", "ERROR", "" + numTasquesPendents, "ERROR", "ERROR", "ERROR", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$ //$NON-NLS-6$
                            null, null, null, null, "ERROR")); //$NON-NLS-1$
                }
            }
            return serversInfo;
        }

        for (int i = 0; i < serv.length; i++) {
            RemoteServiceLocator rsl = createRemoteServiceLocator(serv[i]);
            URLManager m = new URLManager(serv[i]);
            // Obtenim les tasques pendents de la bbdd
            // és més correcte que el que ens informa el server (!!)
            Long numTasquesPendents = 0L;

            // Cerquem les tasques pendents d'aquest server
            Collection tPendents = getTasqueEntityDao().findDadesTasquesPendentsServer(
                    m.getServerURL().getHost());
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

                // Obtenim informació del servidor
                SeyconServerInfo info = status.getSeyconServerStatus();
                info.setNumTasquesPendents("" + numTasquesPendents); //$NON-NLS-1$
                serversInfo.add(info);

            } catch (Throwable e) {
                // Ha ocorregut un error (!!)

                // url, versio, estat, numAgents,
                // numTasquesPendents, sso, jetty, ssoDaemon, taskGenerator,
                // caducitatRootCertificate,
                // caducitatMainCertificate, dataActualServer,
                // databaseConnections
                serversInfo.add(new SeyconServerInfo(m.getServerURL().toString(), "", "-", "ERROR", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        "ERROR", "" + numTasquesPendents, "ERROR", "ERROR", "ERROR", null, null, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                        null, null, "ERROR")); //$NON-NLS-1$
            }
        }

        return serversInfo;

    }

    private RemoteServiceLocator createRemoteServiceLocator(String string) throws IOException, InternalErrorException {
        RemoteServiceLocator rsl = new RemoteServiceLocator(string);
        URLManager um = new URLManager(string);
        ServerEntity server = getServerEntityDao().findByNom(um.getServerURL().getHost());
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
            Collection<AgentStatusInfo> agentstatus = status.getSeyconAgentsInfo();

            // Hem de contar les tasques pendents realment en cada agent
            // hem de tindre: url server[url], codiAgent[nomAgent]
            URLManager m = new URLManager(url);
            String host = m.getServerURL().getHost();

            return agentstatus != null ? agentstatus : new LinkedList<AgentStatusInfo>();

        } catch (Throwable th) {
        	LogFactory.getLog(getClass()).info("Unable to connecto to "+url, th);
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
                Collection agentsActius = getDispatcherEntityDao().findActius();
                HashMap<String, Integer> agentsPos = new HashMap(agentsActius.size());

                if (agentsActius != null) {

                    Integer index = 0;
                    for (Iterator it = agentsActius.iterator(); it.hasNext();) {
                        DispatcherEntity ag = (DispatcherEntity) it.next();
                        agentsPos.put(ag.getCodi(), index++);
                        nomAgentsActius.add(ag.getCodi());// per guardar l'ordre
                    }
                }

                // Obtenim les tasques pendents D'AQUEST SERVER
                // Ordenades per data i id
                Collection tasques = getTasqueEntityDao()
                        .query(
                                "select tasqueEntity from es.caib.seycon.ng.model.TasqueEntity as tasqueEntity " //$NON-NLS-1$
                                        + "where tasqueEntity.server = :server order by tasqueEntity.data, tasqueEntity.id", //$NON-NLS-1$
                                new Parameter[]{new Parameter("server", host)}); //$NON-NLS-1$

                if (tasques != null) {
                    // Si n'hi ha cap tasca pendent obtenim tots els TASKLOGs
                    // d'aquest server per optimitzar les consultes sql (encara
                    // que sobrecarreguem la sessió actual a nivell de memoria)
                    Collection allTaskLog = getTaskLogEntityDao().findAllHavingTasqueByServer(host);
                    HashMap<Long, String[]> estatAllTasks = new HashMap<Long, String[]>();

                    for (Iterator tit = allTaskLog.iterator(); tit.hasNext();) {
                        TaskLogEntity tl = (TaskLogEntity) tit.next();
                        String[] estatsTL = estatAllTasks.get(tl.getTasca().getId());
                        if (estatsTL == null) {
                            // Creem un de nou
                            estatsTL = new String[nomAgentsActius.size()];
                            // No l'inicialitzem (!!) es farà a nivell de tasca
                        }
                        Integer estatAgentTascaActual = agentsPos.get(tl.getDispatcher().getCodi());
                        // Posem l'estat del tasklog actual
                        // NOTA: pot ser que l'agent no estiga actiu (tornarà
                        // null)
                        if (estatAgentTascaActual != null) {
                            estatsTL[estatAgentTascaActual] = "S".equals(tl.getComplet()) ? SeyconTask.Estat.DONE //$NON-NLS-1$
                                    : "N".equals(tl.getComplet()) ? SeyconTask.Estat.ERROR //$NON-NLS-1$
                                            : SeyconTask.Estat.PENDING;
                            // guardem l'estat a estatAllTask
                            estatAllTasks.put(tl.getTasca().getId(), estatsTL);
                        }
                    }

                    // Ara tenim carregats els estats dels agents (per a totes
                    // les tasques), obtenim les tasques

                    for (Iterator it = tasques.iterator(); it.hasNext();) {
                        TasqueEntity t = (TasqueEntity) it.next();
                        // la tenim ja
                        String[] estats = estatAllTasks.get(t.getId());

                        // Si és null (tot és posible..) creem els estats
                        if (estats == null)
                            estats = new String[nomAgentsActius.size()];

                        // Si la tasca no té agent atorgat, posem tots encara no
                        // inicialitzats com a PENDING
                        if (t.getCoddis() == null) {// Tasques per a tothom:
                                                    // estat inicial = PENDING
                            for (int i = 0; i < estats.length; i++)
                                if (estats[i] == null)
                                    estats[i] = SeyconTask.Estat.PENDING;

                        } else {
                            // Si en té agent atorgat, posem a la resta com a
                            // DONE (si son buits)
                            int estatAgentTascaActual = agentsPos.get(t.getCoddis());
                            for (int i = 0; i < estats.length; i++) {
                                if (estats[i] == null) {// només si és buit
                                    if (i != estatAgentTascaActual)
                                        estats[i] = SeyconTask.Estat.DONE;
                                    else
                                        estats[i] = SeyconTask.Estat.PENDING;
                                }
                            }
                        }// else

                        // Afegim la tasca
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
    protected java.util.Collection<SeyconAgentTaskLog> handleGetAgentTasks(java.lang.String url,
            java.lang.String agentCodi) throws java.lang.Exception {
        URLManager m = new URLManager(url);
        // Ens quedem només amb el host
        String host = m.getServerURL().getHost();

        // hem d'agafar TOTES les tasques del servidor

        // Obtenim les tasques pendents (ordentades per id desc) D'AQUEST SERVER
        // Tipus TasqueEntity: ordenats per prioritat, data e id
        // Emprem un mètode existent canviant la select...
        Collection<TasqueEntity> tasques = getTasqueEntityDao()
                .query(
                        "select tasqueEntity from es.caib.seycon.ng.model.TasqueEntity as tasqueEntity " //$NON-NLS-1$
                                + "where tasqueEntity.server = :server and (tasqueEntity.coddis is null or tasqueEntity.coddis=:tasca) " //$NON-NLS-1$
                                + "order by tasqueEntity.prioritat,tasqueEntity.data,tasqueEntity.id", //$NON-NLS-1$
                 new Parameter[] {
                      new Parameter ("server", host), //$NON-NLS-1$
                      new Parameter ("tasca", agentCodi)  //$NON-NLS-1$
                                });

        // Construim el resultat
        LinkedList<SeyconAgentTaskLog> tasquesAgent = new LinkedList();

        if (tasques != null) {
            // Obtenim les tasklogs de totes les tasques de l'agent (compertes i
            // no complertes..)
            // si cerquem només les No compertes podem perdre les PENDING
            // (encara no existeixen al tasklog)
            // Tipus TaskLogEntity
            // En principi no importa l'ordre.. (es fa amb l'ordre de les
            // tasques)
            Collection allTaskLog = getTaskLogEntityDao()
                    .query(
                            "select tlog from es.caib.seycon.ng.model.TaskLogEntity tlog where " //$NON-NLS-1$
                                    + "tlog.dispatcher.codi=:agent  " //$NON-NLS-1$
                                    + "and exists ( select 1 from es.caib.seycon.ng.model.TasqueEntity tasca where " //$NON-NLS-1$
                                    + "tasca.id =tlog.tasca.id and ( (:server is not null and tasca.server=:server) " //$NON-NLS-1$
                                    + "or (:server is null and tasca.server is null) ) ) order by tlog.tasca.id, " //$NON-NLS-1$
                                    + "tlog.dispatcher.codi", //$NON-NLS-1$
                                    
                              new Parameter[] {
                                    new Parameter("server", host), //$NON-NLS-1$
                                    new Parameter ("agent", agentCodi) //$NON-NLS-1$
                                    });
            HashMap<Long, TaskLogEntity> allTaskLogs = new HashMap<Long, TaskLogEntity>();

            for (Iterator tit = allTaskLog.iterator(); tit.hasNext();) {
                TaskLogEntity tl = (TaskLogEntity) tit.next();
                // guardem l'estat a estatAllTask per id
                allTaskLogs.put(tl.getTasca().getId(), tl);
            }

            // Ara tenim carregats els estat de l'agent (per a totes
            // les tasques), obtenim les tasques

            for (Iterator it = tasques.iterator(); it.hasNext();) {
                TasqueEntity t = (TasqueEntity) it.next();
                TaskLogEntity tl = allTaskLogs.get(t.getId());

                // Les que ja són completades no les mostrem
                if (tl != null && "S".equals(tl.getComplet())) //$NON-NLS-1$
                    continue;

                // Ara tenim les que ténen error o estàn pendents
                if (tl == null) {// No té tasklog: pendent
                    // new SeyconAgentTaskLog(idTasca, descripcioTasca,
                    // codiAgent, complet, missatge, dataCreacio,
                    // darreraExecucio, dataDarreraExecucio, proximaExecucio,
                    // dataProximaExecucio, numExecucions, prioritat,
                    // stackTrace)
                    tasquesAgent.add(new SeyconAgentTaskLog(t.getId(), tascaToString(t), agentCodi,
                            "PENDING", "", null, null, null, null, null, null, t.getPrioritat(), //$NON-NLS-1$ //$NON-NLS-2$
                            null));
                } else {// amb error (les completes ja les hem trobades)
                    Calendar dataDarreraExecucio = null;
                    if (tl.getDarreraExecucio() != null) {
                        dataDarreraExecucio = Calendar.getInstance();
                        dataDarreraExecucio.setTimeInMillis(tl.getDarreraExecucio());
                    }
                    Calendar dataProximaExecucio = null;
                    if (tl.getProximaExecucio() != null) {
                        dataProximaExecucio = Calendar.getInstance();
                        dataProximaExecucio.setTimeInMillis(tl.getProximaExecucio());
                    }
                    // new SeyconAgentTaskLog(idTasca, descripcioTasca,
                    // codiAgent, complet, missatge, dataCreacio,
                    // darreraExecucio, dataDarreraExecucio, proximaExecucio,
                    // dataProximaExecucio, numExecucions, prioritat,
                    // stackTrace)
                    Calendar dataCreacio = null;
                    if (tl.getDataCreacio() != null) {
                        dataCreacio = Calendar.getInstance();
                        dataCreacio.setTime(tl.getDataCreacio());
                    }
                    tasquesAgent.add(new SeyconAgentTaskLog(t.getId(), tascaToString(t), agentCodi,
                            tl.getComplet(), tl.getMissatge(), dataCreacio,
                            tl.getDarreraExecucio(), dataDarreraExecucio, tl.getProximaExecucio(),
                            dataProximaExecucio, tl.getNumExecucions(), t.getPrioritat(), tl
                                    .getStackTrace()));
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
            ServerEntity server = getServerEntityDao().findByNom(url.getHost());
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
        ConfiguracioService configuracioService = getConfiguracioService();
        Configuracio parametre = configuracioService.findParametreByCodiAndCodiXarxa(
                "seycon.server.list", null); //$NON-NLS-1$
        
        if (parametre != null)
        	return parametre.getValor();
        
        else
        	return new String();
    }

    private String getServerPort() throws InternalErrorException, SQLException, NamingException {
        ConfiguracioService configuracioService = getConfiguracioService();
        Configuracio parametre = configuracioService.findParametreByCodiAndCodiXarxa(
                "seycon.https.port", null); //$NON-NLS-1$
        return parametre.getValor();
    }

    private String tascaToString(TasqueEntity tasca) {
        String result = tasca.getTransa();
        String transactionCode = tasca.getTransa();

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
            result = result + " " + tasca.getUsuari(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_ACCOUNT) ||
        	transactionCode.equals(TaskHandler.UPDATE_ACCOUNT_PASSWORD)) //$NON-NLS-1$
            result = result + "@" + tasca.getCoddis(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_HOST)) //$NON-NLS-1$
            result = result + " " + tasca.getMaquin(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_GROUP)) //$NON-NLS-1$
            result = result + " " + tasca.getGrup(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_ROLE)  
        		|| transactionCode.equals(TaskHandler.RECONCILE_ROLE)) //$NON-NLS-1$
            result = result + " " + tasca.getRole(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.CREATE_FOLDER)) //$NON-NLS-1$
            result = result + " " + tasca.getCarpet(); //$NON-NLS-1$

        if (transactionCode.equals(TaskHandler.UPDATE_OBJECT )
        				|| transactionCode.equals(TaskHandler.DELETE_OBJECT)) //$NON-NLS-1$
            result = result + " " + tasca.getEntity()+"#"+tasca.getPrimaryKeyValue(); //$NON-NLS-1$ //$NON-NLS-2$

        if (transactionCode.equals(TaskHandler.UPDATE_LIST_ALIAS)) //$NON-NLS-1$
            result = result + " " + tasca.getAlies() + "@" + tasca.getDomcor(); //$NON-NLS-1$ //$NON-NLS-2$

        return result;

    }

    private SeyconServerInfo generaTascaPendentBuidaTotOK() {
        // url, descripcio, versio, estat, numAgents,
        // numTasquesPendents, sso, jetty, ssoDaemon, taskGenerator,
        // caducitatRootCertificate,
        // caducitatMainCertificate, dataActualServer, databaseConnections
        return new SeyconServerInfo("Tasques sense planificar", "", "", "OK", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
                "Cap Tasca pendent de Planificar", "0", "ERROR", "ERROR", "ERROR", null, null, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                null, null, "ERROR"); //$NON-NLS-1$
    }

    protected Collection<SeyconServerInfo> handleGetPendingTasksInfo() throws Exception {
        LinkedList<SeyconServerInfo> serversInfo = new LinkedList();
        try {
            // Afegim tasques sense planificar
            // posem url ficticia TASQUES_SENSE_PLANIFICAR

            // Obtenim el número de tasques
            Collection<TasqueEntity> tasquesPlanning = getTasqueEntityDao()
                    .query(
                            "select count(*),min(tasqueEntity.data) " //$NON-NLS-1$
                            + "from es.caib.seycon.ng.model.TasqueEntity as tasqueEntity " //$NON-NLS-1$
                            + "where tasqueEntity.server is null",  //$NON-NLS-1$
                            new Parameter[0]);

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
                        serversInfo.add(new SeyconServerInfo(Messages.getString("SeyconServerServiceImpl.NoTasksPlan"), "", "", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                                colorTasquesPendents, descripcioEstat, "" + numTasquesPendents, //$NON-NLS-1$
                                "ERROR", "ERROR", "ERROR", null, null, null, null, "ERROR")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

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
                Object obj = status.getSeyconServerInfo();
                if (obj instanceof SeyconServerInfo) {
                    SeyconServerInfo info = (SeyconServerInfo) obj;
                    serversInfo.add(info);
                }
            } catch (Throwable th) {
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

	@Override
	protected void handleBoostTask(long taskId) throws Exception {
		TasqueEntity task = getTasqueEntityDao().load(taskId);
		
		String server = task.getServer();
		
		if (server == null)
		{
			throw new InternalErrorException(String.format("Task %d is not scheduled yet", taskId));
		} else {
	        RemoteServiceLocator rsl = createRemoteServiceLocator(server);
	        
	        SyncStatusService status = rsl.getSyncStatusService();

	        status.boostTask(taskId);
		}
	}

	@Override
	protected void handleCancelTask(long taskId) throws Exception {
		TasqueEntity task = getTasqueEntityDao().load(taskId);
		if (task == null)
			return;
		
		String server = task.getServer();
		
		if (server == null)
		{
			getTasqueEntityDao().remove(task);
		} else {
	        RemoteServiceLocator rsl = createRemoteServiceLocator(server);
	        
	        SyncStatusService status = rsl.getSyncStatusService();

	        status.cancelTask(taskId);
		}
		
	}

}

