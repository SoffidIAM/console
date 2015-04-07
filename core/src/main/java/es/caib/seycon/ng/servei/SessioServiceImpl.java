// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import com.soffid.iam.model.AccessLogEntity;
import com.soffid.iam.model.AccessLogEntityDao;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.ServiceEntity;
import com.soffid.iam.model.ServiceEntityDao;
import com.soffid.iam.model.SessionEntity;
import com.soffid.iam.model.SessionEntityDao;
import com.soffid.iam.model.UserEntity;
import es.caib.seycon.ng.comu.Sessio;
import es.caib.seycon.ng.comu.TipusSessio;
import es.caib.seycon.ng.config.Config;
import es.caib.seycon.ng.exception.UnknownUserException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

/**
 * @see es.caib.seycon.ng.sync.servei.SessioService
 */
public class SessioServiceImpl extends es.caib.seycon.ng.servei.SessioServiceBase {
    private String hostName;

    public SessioServiceImpl() throws FileNotFoundException, IOException {
        hostName = Config.getConfig().getHostName();
        if (hostName == null)
            hostName = InetAddress.getLocalHost().getHostName();
    }

    private HostEntity findMaquina(String nomOrIp) {
        HostEntity maquina = getHostEntityDao().findByName(nomOrIp);

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
        @SuppressWarnings(value = "rawtypes")
        Collection maquines = getHostEntityDao().findHostByCriteria(null, null, nomOrIp, null, null, null, null, null, null, null);
        if (maquines.size() >= 1)
            return (HostEntity) maquines.iterator().next();
        else
            return null;
    }

    /**
     * @see es.caib.seycon.ng.sync.servei.SessioService#registraSessioWeb(java.lang.String,
     *      java.lang.String, java.lang.String, java.lang.String)
     */
    protected es.caib.seycon.ng.comu.Sessio handleRegistraSessioWeb(java.lang.String codiUsuari, java.lang.String nomMaquina, java.lang.String nomMaquinaClient, java.lang.String url)
        throws java.lang.Exception
    {
        return doCreateSession(codiUsuari, nomMaquina, nomMaquinaClient, TipusSessio.WSSO, url, null, null);
    }

    private es.caib.seycon.ng.comu.Sessio doCreateSession(java.lang.String codiUsuari,
            java.lang.String nomMaquina, java.lang.String nomMaquinaClient, TipusSessio tipus, java.lang.String url, java.lang.Long port, String key)
            throws UnknownHostException, UnknownUserException {
        HostEntity me = findMaquina(nomMaquina);
        HostEntity meClient = null;
        
        UserEntity ue = getUserEntityDao().findByCode(codiUsuari);
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
        sessio.setPort(port);
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
                sessio.setExternalClientIp (nomMaquinaClient);
                sessio.setClientHostName(nomMaquinaClient);
            }
            else
            {
                sessio.setClientHost(meClient);
                sessio.setClientHostName(meClient.getName());
            }
        	try
        	{
           		InetAddress addr = InetAddress.getByName(sessio.getClientHostName());
          		sessio.setClientAddress(addr.getHostAddress());
        	} catch (Exception e )
        	{
        	}
        }
        sessio.setWebHandler(url);
        
        getSessionEntityDao().create(sessio);
        
        ServiceEntityDao seDao = getServiceEntityDao(); 
        ServiceEntity se = seDao.findByCode("sso"); //$NON-NLS-1$
        if (se == null) {
            se = getServiceEntityDao().newServiceEntity();
            se.setCode("sso"); //$NON-NLS-1$
            se.setDescription("SEU Single Sign On"); //$NON-NLS-1$
            seDao.create(se);
        }
        
        AccessLogEntityDao raDao = getAccessLogEntityDao();
        AccessLogEntity ra = getAccessLogEntityDao().newAccessLogEntity();
        ra.setClient(meClient);
        ra.setCodeAge(hostName);
        ra.setStartDate(new Date());
        ra.setEndDate(null);
        ra.setSessionID(sessio.getId().toString());
        ra.setProtocol(se);
        ra.setServer(me);
        ra.setAccessType("L"); //$NON-NLS-1$
        ra.setUser(ue);
        ra.setHostName(sessio.getHostName());
        ra.setHostAddress(sessio.getHostAddress());
        ra.setClientHostName(sessio.getClientHostName());
        ra.setClientAddress(sessio.getClientAddress());
        raDao.create (ra);
        

        sessio.setLoginLogInfo(ra);
        Sessio svo = getSessionEntityDao().toSessio(sessio);
        svo.setClau(sessio.getKey());
        svo.setClauTemporal(sessio.getNewKey());
        
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
    protected es.caib.seycon.ng.comu.Sessio handleGetSession(long id, java.lang.String key)
            throws java.lang.Exception {
        SessionEntity se = getSesssioEntity(id, key);

        if (se != null) {
            Sessio s = getSessionEntityDao().toSessio(se);
            s.setClau(key);
            s.setClauTemporal(se.getNewKey());
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
    protected void handleSessioKeepAlive(Sessio session) throws Exception {
        SessionEntity se = getSessionEntityDao().load(session.getId());
        se.setKeepAliveDate(new Date());
        getSessionEntityDao().update(se);
    }

    @Override
    protected void handleDestroySessio(Sessio sessio) throws Exception {
        SessionEntity se = getSessionEntityDao().load(sessio.getId());
        
        se.getLoginLogInfo().setEndDate(new Date());
        getAccessLogEntityDao().update(se.getLoginLogInfo());
        getSessionEntityDao().remove(se);
    }

    @Override
    protected Sessio handleRegisterSessio(String codiUsuari, String nomMaquina,
            String nomMaquinaClient, int port, String key) throws Exception {
        return doCreateSession(codiUsuari, nomMaquina, nomMaquinaClient, TipusSessio.ESSO, null, new Long(port), key);
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
    protected Collection<Sessio> handleGetActiveSessions() throws Exception {
        SessionEntityDao dao = getSessionEntityDao();
        LinkedList<Sessio> sessions = new LinkedList<Sessio>();
        for (Iterator<SessionEntity> it = dao.loadAll().iterator(); it.hasNext(); ) {
            SessionEntity sessioEntity = it.next();
            Sessio s = dao.toSessio(sessioEntity);
            s.setClau(sessioEntity.getKey());
            s.setClauTemporal(sessioEntity.getNewKey());
            sessions.add(s);
        }
        return sessions;
    }

    @Override
    protected Collection<Sessio> handleGetActiveSessions(long idUsuari) throws Exception {
        SessionEntityDao dao = getSessionEntityDao();
        LinkedList<Sessio> sessions = new LinkedList<Sessio>();
        UserEntity usuari = getUserEntityDao().load(new Long(idUsuari));
        for (Iterator<SessionEntity> it = usuari.getSessions().iterator(); it.hasNext(); ) {
            SessionEntity sessioEntity = it.next();
            Sessio s = dao.toSessio(sessioEntity);
            s.setClau(sessioEntity.getKey());
            s.setClauTemporal(sessioEntity.getNewKey());
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
    protected Sessio handleGetSessioByHost(long id, String hostIp) throws Exception {
        SessionEntity se = getSessionEntityDao().load(id);

        if (se != null && se.getHost() != null && hostIp.equals(se.getHost().getHostIP())) {
            Sessio s = getSessionEntityDao().toSessio(se);
            s.setClau(se.getKey());
            s.setClauTemporal(se.getNewKey());
            return s;
        } else
            return null;
    }

}
