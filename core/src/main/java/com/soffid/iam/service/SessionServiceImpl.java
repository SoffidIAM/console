// license-header java merge-point
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

import com.soffid.iam.api.Session;
import com.soffid.iam.config.Config;
import com.soffid.iam.model.AccessLogEntity;
import com.soffid.iam.model.AccessLogEntityDao;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.PamPolicyEntity;
import com.soffid.iam.model.PamPolicyJITPermissionEntity;
import com.soffid.iam.model.ServiceEntity;
import com.soffid.iam.model.ServiceEntityDao;
import com.soffid.iam.model.SessionEntity;
import com.soffid.iam.model.SessionEntityDao;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.TipusSessio;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.UnknownUserException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @see es.caib.seycon.ng.sync.servei.SessioService
 */
public class SessionServiceImpl extends com.soffid.iam.service.SessionServiceBase {
    private String hostName;
    Log log = LogFactory.getLog(getClass());
    
    public SessionServiceImpl() throws FileNotFoundException, IOException {
        hostName = Config.getConfig().getHostName();
        if (hostName == null)
        {
        	try {
        		hostName = InetAddress.getLocalHost().getHostName();
        	} catch (Exception e) {
        		log.warn ("Error resolving local host name", e);
        		hostName = "127.0.0.1";
        	}
        }
    }

    private HostEntity findMaquina(String nomOrIp) {
        HostEntity maquina = getHostEntityDao().findByName(nomOrIp);

        if (maquina == null) {
        	maquina = getHostEntityDao().findBySerialNumber(nomOrIp);
        	if (maquina != null && Boolean.TRUE.equals(maquina.getDeleted()))
        		maquina = null;
        }
        
        if (maquina == null)
            maquina = findMaquinaByIp(nomOrIp);

        if (maquina == null) {
            try {
                InetAddress ip = InetAddress.getByName(nomOrIp);
                maquina = findMaquinaByIp(ip.toString());
            } catch (UnknownHostException e) {
            }
        }

        return maquina;

    }

    private HostEntity findMaquinaByIp(String nomOrIp) {
        CriteriaSearchConfiguration criteria = new CriteriaSearchConfiguration();
        criteria.setMaximumResultSize(1);

        @SuppressWarnings(value = "rawtypes")
        Collection maquines = getHostEntityDao().findHostByCriteria(criteria , null, null, nomOrIp, null, null, null, null, null, null, null);
        if (maquines.size() >= 1)
            return (HostEntity) maquines.iterator().next();
        else
            return null;
    }

    /**
     * @see es.caib.seycon.ng.sync.servei.SessioService#registraSessioWeb(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    protected com.soffid.iam.api.Session handleRegisterWebSession(java.lang.String codiUsuari, java.lang.String nomMaquina, java.lang.String nomMaquinaClient, java.lang.String url, String authenticationMethod) throws java.lang.Exception {
        return doCreateSession(codiUsuari, nomMaquina, nomMaquinaClient, TipusSessio.WSSO, url, null, null, authenticationMethod);
    }

    static HashMap<TipusSessio, String[]> types = new HashMap<TipusSessio, String[]>() {{
    	put(TipusSessio.ESSO, new String[] {"esso", "Soffid Enterprise SSO"});
    	put(TipusSessio.WSSO, new String[] {"wsso", "Soffid Web SSO"});
    	put(TipusSessio.PAM, new String[] {"PAM", "PAM Web Gateway"});
    	put(TipusSessio.PAMRDP, new String[] {"PAMRDP", "PAM RDP Gateway"});
    	put(TipusSessio.PAMSSH, new String[] {"PAMSSH", "PAM SSH Gateway"});
    	put(TipusSessio.CONSOLE, new String[] {"CONSOLE", "Soffid Console"});
    }};
    private com.soffid.iam.api.Session doCreateSession(java.lang.String codiUsuari, java.lang.String nomMaquina, java.lang.String nomMaquinaClient, TipusSessio tipus, 
    		java.lang.String url, java.lang.Long port, String key, String authenticationMethod) throws UnknownHostException, UnknownUserException {
        HostEntity me = findMaquina(nomMaquina);
        HostEntity meClient = null;
        
        UserEntity ue = getUserEntityDao().findByUserName(codiUsuari);
        if (ue == null)
            throw new UnknownUserException(codiUsuari);
        
        if (key == null)
            key = generateRandomKey();

        
        SessionEntity sessio = getSessionEntityDao().newSessionEntity(); 
        sessio.setStartDate(new Date());
        sessio.setHost(me);
        sessio.setUser(ue);
        sessio.setKey(key.toString());
        sessio.setType(tipus);
        if (port != null && port > 0)
        	sessio.setPort(port);
        sessio.setAuthenticationMethod(authenticationMethod);
        if (me == null)
        {
        	sessio.setHostName(nomMaquina);
       	} else {
       		sessio.setHostName(me.getName());
       	}
    	try
    	{
    		InetAddress addr = InetAddress.getByName(sessio.getHostName());
    		sessio.setHostAddress(addr.getHostAddress());
    	} catch (Exception e )
    	{
    	}

        if (nomMaquinaClient != null)
        {
        	meClient = findMaquina(nomMaquinaClient);
            if (meClient == null)
            {
                sessio.setClientHostName(nomMaquinaClient);
                try
                {
                	InetAddress addr = InetAddress.getByName(sessio.getClientHostName());
                	sessio.setClientAddress(addr.getHostAddress());
                } catch (Exception e )
                {
                }
            }
            else
            {
                sessio.setClientHost(meClient);
                sessio.setClientHostName(meClient.getName());
                sessio.setClientAddress(meClient.getHostIP());
            }
        }
        sessio.setWebHandler(url);
        
        ServiceEntityDao seDao = getServiceEntityDao(); 
        ServiceEntity se;
        String typeText[] = types.get(tipus);
        se = seDao.findByName(typeText[0]); //$NON-NLS-1$
        if (se == null) {
            se = getServiceEntityDao().newServiceEntity();
            se.setName(typeText[0]); //$NON-NLS-1$
            se.setDescription(typeText[1]); //$NON-NLS-1$
            seDao.create(se);
        }
        
        AccessLogEntityDao raDao = getAccessLogEntityDao();
        AccessLogEntity ra = getAccessLogEntityDao().newAccessLogEntity();
        ra.setClient(meClient);
        ra.setSystem(hostName);
        ra.setStartDate(new Date());
        ra.setEndDate(null);
        ra.setSessionId("-");
        ra.setProtocol(se);
        ra.setServer(me);
        ra.setAccessType("L"); //$NON-NLS-1$
        ra.setUser(ue);
        ra.setHostName(sessio.getHostName());
        ra.setHostAddress(sessio.getHostAddress());
        ra.setClientHostName(sessio.getClientHostName());
        ra.setClientAddress(sessio.getClientAddress());
        if (sessio.getAuthenticationMethod() != null)
        	ra.setInformation("Auth method: "+sessio.getAuthenticationMethod());
        raDao.create (ra);
        

        sessio.setLoginLogInfo(ra);
        getSessionEntityDao().create(sessio);
        
        ra.setSessionId(sessio.getId().toString());
        getAccessLogEntityDao().update(ra);
        
        Session svo = getSessionEntityDao().toSession(sessio);
        svo.setKey(sessio.getKey());
        svo.setTemporaryKey(sessio.getNewKey());
        
        return svo;
    }

    Random random;
    private String generateRandomKey() {
        StringBuffer key;
        key = new StringBuffer(50);
        if (random == null)
            random = new Random(System.currentTimeMillis()+this.hashCode());
        for (int i = 0; i < 50; i++) {
            int next = random.nextInt() % 62;
            if (next < 0)
                next += 62;
            if (next < 26) 
                key.append((char) ('a' + next ));
            else if (next < 52)
                key.append((char) ('A' + next - 26));
            else
                key.append((char) ('0' + next - 52));
        }
        return key.toString();
    }

    /**
     * @see es.caib.seycon.ng.sync.servei.SessioService#getSessionByKey(java.lang.String)
     */
    protected com.soffid.iam.api.Session handleGetSession(long id, java.lang.String key) throws java.lang.Exception {
        SessionEntity se = getSesssioEntity(id, key);

        if (se != null) {
            Session s = getSessionEntityDao().toSession(se);
            s.setKey(key);
            s.setTemporaryKey(se.getNewKey());
            return s;
        }
        else
            return null;
    }

    private SessionEntity getSesssioEntity(long id, java.lang.String key) {
        SessionEntity se = getSessionEntityDao().findById(id);
        if (se == null)
            return null;
        if (se.getKey() != null && !se.getKey().equals(key))
            return null;
        return se;
    }

    /**
     * @see es.caib.seycon.ng.sync.servei.SessioService#sessioKeepAlive(java.lang.String)
     */
    @Override
    protected void handleSessionKeepAlive(Session session) throws Exception {
        SessionEntity se = getSessionEntityDao().load(session.getId());
        if (se != null) {
	        se.setKeepAliveDate(new Date());
	        getSessionEntityDao().update(se);
        }
    }

    @Override
    protected void handleDestroySession(Session sessio) throws Exception {
        SessionEntity se = getSessionEntityDao().load(sessio.getId());

        if (se != null) {
	        if (se.getLoginLogInfo() != null)
	        {
	        	se.getLoginLogInfo().setEndDate(new Date());
	        	getAccessLogEntityDao().update(se.getLoginLogInfo());
	        }
	        if (se.getType() == TipusSessio.PAM && se.getJustInTimePermissionToRemove() != null) {
	        	List<String> l = new LinkedList<>();
	        	for (String s: se.getJustInTimePermissionToRemove().split("&")) {
	        		l.add(URLDecoder.decode(s, StandardCharsets.UTF_8));
	        	}
        		AccountEntity account = se.getAccount();
    			getDispatcherService().removeTemporaryPermissions (se.getHostName(), 
        					account.getName(), account.getSystem().getName(), l);
	        }
	        getSessionEntityDao().remove(se);
        }
    }

    @Override
    protected Session handleRegisterSession(String codiUsuari, String nomMaquina, String nomMaquinaClient, int port, String key, String authenticationMethod) throws Exception {
        return doCreateSession(codiUsuari, nomMaquina, nomMaquinaClient, TipusSessio.ESSO, null, new Long(port), key, authenticationMethod);
    }

    @Override
    protected String handleUpdateTransientKey(long id, String key)
            throws Exception {
        String newKey = generateRandomKey();
        SessionEntity se = getSesssioEntity(id, key);
        se.setNewKey(newKey);
        getSessionEntityDao().update(se);
        return newKey;

    }

    @Override
    protected Collection<Session> handleGetActiveSessions() throws Exception {
        SessionEntityDao dao = getSessionEntityDao();
        LinkedList<Session> sessions = new LinkedList<Session>();
        for (Iterator<SessionEntity> it = dao.loadAll().iterator(); it.hasNext(); ) {
            SessionEntity sessioEntity = it.next();
            Session s = dao.toSession(sessioEntity);
            s.setKey(sessioEntity.getKey());
            s.setTemporaryKey(sessioEntity.getNewKey());
            sessions.add(s);
        }
        return sessions;
    }

    @Override
    protected Collection<Session> handleGetActiveSessions(long idUsuari) throws Exception {
        SessionEntityDao dao = getSessionEntityDao();
        LinkedList<Session> sessions = new LinkedList<Session>();
        UserEntity usuari = getUserEntityDao().load(new Long(idUsuari));
        for (Iterator<SessionEntity> it = usuari.getSessions().iterator(); it.hasNext(); ) {
            SessionEntity sessioEntity = it.next();
            Session s = dao.toSession(sessioEntity);
            s.setKey(sessioEntity.getKey());
            s.setTemporaryKey(sessioEntity.getNewKey());
            sessions.add(s);
        }
        return sessions;
    }

    @Override
    protected void handleCleanTransientKey(long id, String key) throws Exception {
        SessionEntity se = getSesssioEntity(id, key);
        se.setNewKey(null);
        getSessionEntityDao().update(se);
    }

    @Override
    protected Session handleGetSessionByHost(long id, String hostIp) throws Exception {
        SessionEntity se = getSessionEntityDao().load(id);

        if (se != null && se.getHost() != null && hostIp.equals(se.getHost().getHostIP())) {
            Session s = getSessionEntityDao().toSession(se);
            s.setKey(se.getKey());
            s.setTemporaryKey(se.getNewKey());
            return s;
        } else
            return null;
    }

	@Override
	protected Collection<Session> handleFindActiveSessions() throws Exception {
        SessionEntityDao dao = getSessionEntityDao();
        LinkedList<Session> sessions = new LinkedList<Session>();
        for (Iterator<SessionEntity> it = dao.loadAll().iterator(); it.hasNext(); ) {
            SessionEntity sessioEntity = it.next();
            if (sessioEntity.getUser().getTenant().getId().equals(Security.getCurrentTenantId())) {
            	if (isAllowed(sessioEntity)) {
		            Session s = dao.toSession(sessioEntity);
		            s.setKey(null);
		            s.setTemporaryKey(null);
		            sessions.add(s);
            	}
            }
        }
        return sessions;
	}

	private boolean isAllowed(SessionEntity sessioEntity) throws InternalErrorException {
		if (Security.isSyncServer())
			return true;
		final TipusSessio type = sessioEntity.getType();
		if (type == TipusSessio.PAM || type == TipusSessio.PAMRDP || type == TipusSessio.PAMSSH) {
			AccountEntity account = sessioEntity.getAccount();
			if (account == null)
				return false;
			AccountAccessLevelEnum level = getAccountEntityDao().getAccessLevel(account, Security.getCurrentUser());
			return level == AccountAccessLevelEnum.ACCESS_OWNER;
		} else if ( sessioEntity.getUser() != null &&
				sessioEntity.getUser().isAllowed("user:session:query")) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	protected Session handleRegisterConsoleSessio(String codiUsuari, String nomMaquina, String authenticationMethod)
			throws Exception {
        return doCreateSession(codiUsuari, nomMaquina, null, TipusSessio.CONSOLE, null, null, 
        		null, authenticationMethod);
	}

	@Override
	protected Session handleJoinEssoSession(long id, String key, int port) throws Exception {
        SessionEntity se = getSesssioEntity(id, key);

        if (se != null && se.getType() == TipusSessio.ESSO &&
        		(se.getPort() == null || se.getPort().intValue() <= 0)) {
        	se.setPort((long) port);
        	getSessionEntityDao().update(se);
            return getSessionEntityDao().toSession(se);
        }
        else
            return null;
	}

}
