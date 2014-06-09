// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import es.caib.seycon.ng.comu.Sessio;
import es.caib.seycon.ng.comu.TipusSessio;
import es.caib.seycon.ng.config.Config;
import es.caib.seycon.ng.exception.UnknownUserException;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.RegistreAccesEntity;
import es.caib.seycon.ng.model.RegistreAccesEntityDao;
import es.caib.seycon.ng.model.ServeiEntity;
import es.caib.seycon.ng.model.ServeiEntityDao;
import es.caib.seycon.ng.model.SessioEntity;
import es.caib.seycon.ng.model.SessioEntityDao;
import es.caib.seycon.ng.model.UsuariEntity;

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

    private MaquinaEntity findMaquina(String nomOrIp) {
        MaquinaEntity maquina = getMaquinaEntityDao().findByNom(nomOrIp);

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

    private MaquinaEntity findMaquinaByIp(String nomOrIp) {
        @SuppressWarnings("rawtypes")
        Collection maquines = getMaquinaEntityDao().findMaquinaByFiltre(null, null, nomOrIp, null,
                null, null, null, null, null, null);
        if (maquines.size() >= 1)
            return (MaquinaEntity) maquines.iterator().next();
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
        MaquinaEntity me = findMaquina(nomMaquina);
        MaquinaEntity meClient = null;
        
        UsuariEntity ue = getUsuariEntityDao().findByCodi(codiUsuari);
        if (ue == null)
            throw new UnknownUserException(codiUsuari);
        
        if (key == null)
            key = generateRandomKey();

        
        SessioEntity sessio = getSessioEntityDao().newSessioEntity(); 
        sessio.setDataInici(new Date());
        sessio.setMaquina(me);
        sessio.setUsuari(ue);
        sessio.setClau( key.toString() );
        sessio.setTipus(tipus);
        sessio.setPort(port);
        if (me == null)
        {
        	sessio.setHostName(nomMaquina);
       	} else {
       		sessio.setHostName(me.getNom());
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
                sessio.setMaquinaClient(meClient);
                sessio.setClientHostName(meClient.getNom());
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
        
        getSessioEntityDao().create(sessio);
        
        ServeiEntityDao seDao = getServeiEntityDao() ; 
        ServeiEntity se = seDao.findByCodi ("sso"); //$NON-NLS-1$
        if (se == null) {
            se = getServeiEntityDao().newServeiEntity();
            se.setCodi("sso"); //$NON-NLS-1$
            se.setDescripcio("SEU Single Sign On"); //$NON-NLS-1$
            seDao.create(se);
        }
        
        RegistreAccesEntityDao raDao = getRegistreAccesEntityDao ();
        RegistreAccesEntity ra = getRegistreAccesEntityDao().newRegistreAccesEntity();
        ra.setClient(meClient);
        ra.setCodeAge(hostName);
        ra.setDataInici(new Date());
        ra.setDataFi(null);
        ra.setIdSessio(sessio.getId().toString());
        ra.setProtocol(se);
        ra.setServidor(me);
        ra.setTipusAcces("L"); //$NON-NLS-1$
        ra.setUsuari(ue);
        ra.setHostName(sessio.getHostName());
        ra.setHostAddress(sessio.getHostAddress());
        ra.setClientHostName(sessio.getClientHostName());
        ra.setClientAddress(sessio.getClientAddress());
        raDao.create (ra);
        

        sessio.setRegIstreLogin(ra);
        Sessio svo = getSessioEntityDao().toSessio(sessio);
        svo.setClau(sessio.getClau());
        svo.setClauTemporal(sessio.getNovaClau());
        
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
        SessioEntity se = getSesssioEntity(id, key);

        if (se != null) {
            Sessio s  = getSessioEntityDao().toSessio(se);
            s.setClau(key);
            s.setClauTemporal(se.getNovaClau());
            return s;
        }
        else
            return null;
    }

    private SessioEntity getSesssioEntity(long id, java.lang.String key) {
        SessioEntity se = getSessioEntityDao().findById(id);
        if (se == null)
            return null;
        if (se.getClau() != null && !se.getClau().equals(key))
            return null;
        return se;
    }

    /**
     * @see es.caib.seycon.ng.sync.servei.SessioService#sessioKeepAlive(java.lang.String)
     */
    @Override
    protected void handleSessioKeepAlive(Sessio session) throws Exception {
        SessioEntity se = getSessioEntityDao().load(session.getId());
        se.setDataKeepAlive(new Date());
        getSessioEntityDao().update(se);
    }

    @Override
    protected void handleDestroySessio(Sessio sessio) throws Exception {
        SessioEntity se = getSessioEntityDao().load(sessio.getId());
        
        se.getRegIstreLogin().setDataFi(new Date());
        getRegistreAccesEntityDao().update(se.getRegIstreLogin());
        getSessioEntityDao().remove(se);
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
        SessioEntity se = getSesssioEntity(id, key);
        se.setNovaClau(newKey);
        getSessioEntityDao().update(se);
        return newKey;

    }

    @Override
    protected Collection<Sessio> handleGetActiveSessions() throws Exception {
        SessioEntityDao dao = getSessioEntityDao();
        LinkedList<Sessio> sessions = new LinkedList<Sessio>();
        for (Iterator<SessioEntity> it = dao.loadAll().iterator(); it.hasNext(); ) {
            SessioEntity sessioEntity = it.next();
            Sessio s = dao.toSessio(sessioEntity);
            s.setClau(sessioEntity.getClau());
            s.setClauTemporal(sessioEntity.getNovaClau());
            sessions.add(s);
        }
        return sessions;
    }

    @Override
    protected Collection<Sessio> handleGetActiveSessions(long idUsuari) throws Exception {
        SessioEntityDao dao = getSessioEntityDao();
        LinkedList<Sessio> sessions = new LinkedList<Sessio>();
        UsuariEntity usuari = getUsuariEntityDao().load(new Long(idUsuari));
        for (Iterator<SessioEntity> it = usuari.getSessions().iterator(); it.hasNext(); ) {
            SessioEntity sessioEntity = it.next();
            Sessio s = dao.toSessio(sessioEntity);
            s.setClau(sessioEntity.getClau());
            s.setClauTemporal(sessioEntity.getNovaClau());
            sessions.add(s);
        }
        return sessions;
    }

    @Override
    protected void handleCleanTransientKey(long id, String key) throws Exception {
        SessioEntity se = getSesssioEntity(id, key);
        se.setNovaClau(null);
        getSessioEntityDao().update(se);
    }

    @Override
    protected Sessio handleGetSessioByHost(long id, String hostIp) throws Exception {
        SessioEntity se = getSessioEntityDao().load(id);

        if (se != null && se.getMaquina() != null && hostIp.equals(se.getMaquina().getAdreca())) {
            Sessio s = getSessioEntityDao().toSessio(se);
            s.setClau(se.getClau());
            s.setClauTemporal(se.getNovaClau());
            return s;
        } else
            return null;
    }

}
