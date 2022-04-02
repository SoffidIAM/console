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

import com.soffid.iam.api.AccessTree;
import com.soffid.iam.api.AdministratorAuthorizationToAccessHost;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Host;
import com.soffid.iam.api.HostAlias;
import com.soffid.iam.api.Identity;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.NetworkAuthorization;
import com.soffid.iam.api.OsType;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Session;
import com.soffid.iam.api.System;
import com.soffid.iam.api.User;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.EntryPointEntity;
import com.soffid.iam.model.GroupAttributeEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.HostAdminEntity;
import com.soffid.iam.model.HostAliasEntity;
import com.soffid.iam.model.HostAttributeEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.HostEntityDao;
import com.soffid.iam.model.HostEntryPointEntity;
import com.soffid.iam.model.HostSystemEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.MetaDataEntityDao;
import com.soffid.iam.model.NetworkAuthorizationEntity;
import com.soffid.iam.model.NetworkEntity;
import com.soffid.iam.model.NetworkEntityDao;
import com.soffid.iam.model.OsTypeEntity;
import com.soffid.iam.model.OsTypeEntityDao;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.SessionEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.reconcile.model.ReconcileAccountEntityDao;
import com.soffid.iam.reconcile.model.ReconcileAssignmentEntityDao;
import com.soffid.iam.service.GroupServiceImpl.GroupAttributePersister;
import com.soffid.iam.service.attribute.AttributePersister;
import com.soffid.iam.service.impl.AttributeValidationService;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.DateUtils;
import com.soffid.iam.utils.IPAddress;
import com.soffid.iam.utils.InvalidIPException;
import com.soffid.iam.utils.Security;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.XarxaSearchCriteria;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownHostException;
import es.caib.seycon.ng.exception.UnknownNetworkException;
import es.caib.seycon.ng.exception.UnknownUserException;
import es.caib.seycon.util.TimedOutException;
import es.caib.seycon.util.TimedProcess;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;
import org.json.JSONException;

/**
 * @see es.caib.seycon.ng.servei.XarxaService
 */
public class NetworkServiceImpl extends com.soffid.iam.service.NetworkServiceBase {

    private Log log;

    public NetworkServiceImpl() {
        log = LogFactoryImpl.getLog(getClass());
    }

    public static final int SENSE_PERMISOS = -1;
    public static final int CONSULTA = 0;
    public static final int SUPORT = 1;
    public static final int ADMINISTRACIO = 2;
    public static final int LOGIN = 3;

    protected Boolean handleHasManagedNetwork() throws Exception {
        User usuari = getUserService().getCurrentUser();
        Collection codisXarxa = getCodiXarxesAmbAccesAdministracio(usuari.getUserName());
        return new Boolean(codisXarxa.size() > 0);
    }

    protected Boolean handleIsManaged(String codiXarxa) throws Exception {
        User usuari = getUserService().getCurrentUser();
        if (usuari == null)
        	return false;
        Collection codisXarxa = getCodiXarxesAmbAccesAdministracio(usuari.getUserName());
        for (Iterator iterator = codisXarxa.iterator(); iterator.hasNext();) {
            String currentCodiXarxa = (String) iterator.next();
            if (currentCodiXarxa.compareTo(codiXarxa) == 0) {
                return new Boolean(true);
            }
        }
        return new Boolean(false);
    }

    protected List<Session> handleFindSessionsByHostName(String nomMaquina) {
        Collection<SessionEntity> sessions = getSessionEntityDao().findSessionsByCriteria(null, null, nomMaquina, null);
        if (sessions != null) {
            return getSessionEntityDao().toSessionList(sessions);
        }
        return new Vector();
    }

    protected Long handleFindAccessLevelByHostNameAndNetworkName(String nomMaquina, String codiXarxa) throws Exception {
    	if ( Security.isUserInRole(Security.AUTO_HOST_ALL_UPDATE))
    		return new Long(ADMINISTRACIO);

    	String codiUsuari = Security.getCurrentUser();
        if (codiUsuari == null)
        	return new Long(SENSE_PERMISOS);
        
        Collection networkAuthorizations = findALLNetworkAuthorizationsByUserName(codiUsuari);
        
        
        Long level = getAccessLevel(networkAuthorizations, nomMaquina, codiXarxa);
        if (level == null || level.longValue() < SUPORT)
        {
        	Date longAgo = new Date(0);
            for (HostAdminEntity aut : getHostAdminEntityDao().findByHostNameAndRequestDate(nomMaquina, longAgo, new Date(), longAgo)) {
                if (aut.getUser().getUserName().equals(codiUsuari)) level = new Long(SUPORT);
            }
        }
        return level;
    }

    protected User handleFindUsuariByIdSessio(java.lang.Long idSessio) throws Exception {
        SessionEntity sessioEntity = getSessionEntityDao().findById(idSessio);
        if (sessioEntity != null) {
            UserEntity usuariEntity = sessioEntity.getUser();
            User usuari = getUserEntityDao().toUser(usuariEntity);
            return usuari;
        } else {
            throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.SessionIDNotFound"), //$NON-NLS-1$
                    idSessio.longValue()));
        }
    }

    protected Boolean handleHasNetworkAccess(String codiUsuari, String codiXarxa) throws Exception {
        Collection codiXarxes = getCodiXarxesAmbAcces(codiUsuari);
        return new Boolean(codiXarxes.contains(codiXarxa));
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#getXarxes()
     */
    protected List<Network> handleGetNetworks() throws java.lang.Exception {
        return getNetworkEntityDao().toNetworkList(getNetworkEntityDao().loadAll());
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#create(es.caib.seycon.ng.comu.Xarxa)
     */
    protected com.soffid.iam.api.Network handleCreate(com.soffid.iam.api.Network xarxa) throws java.lang.Exception {
        // network:create [SENSE_DOMINI]
        if (AutoritzacionsUsuari.canCreateAllNetworks()) {
        	NetworkEntity sameName = getNetworkEntityDao().findByName(xarxa.getCode());
    		if(sameName != null )
    			throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.CodeNetworkExists"), xarxa.getCode()));
    		NetworkEntity sameIp = getNetworkEntityDao().findByAddress(xarxa.getIp());
    		if(sameIp != null)
    			throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.IpNetworkExists"), xarxa.getIp())); 
            NetworkEntity entity = getNetworkEntityDao().networkToEntity(xarxa);
            getNetworkEntityDao().create(entity);
            xarxa.setId(entity.getId());
            return getNetworkEntityDao().toNetwork(entity);
        }
        throw new SeyconException(Messages.getString("NetworkServiceImpl.NotAuthorizedMakeNet")); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#update(es.caib.seycon.ng.comu.Xarxa)
     */
    protected void handleUpdate(com.soffid.iam.api.Network xarxa) throws java.lang.Exception {
        if (AutoritzacionsUsuari.canUpdateAllNetworks() || hasNetworkAuthorizations(Security.getCurrentUser(), xarxa.getCode(), new int[]{ADMINISTRACIO})) {

            @SuppressWarnings(value = "rawtypes")
            Collection maquines = findHostByFilter(null, null, null, null, null, null, null, null, null, xarxa.getCode(), null, new Boolean(false));
            for (Iterator iterator = maquines.iterator(); iterator.hasNext(); ) {
                Host maquina = (Host) (iterator.next());
                if (maquina.getIp() != null && !maquinaCompatibleAmbXarxa(maquina.getIp(), xarxa.getIp(), xarxa.getMask())) {
                    throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.IncompatibleIPMessage"), xarxa.getIp(), xarxa.getMask(), maquina.getIp()));
                }
            }

            getNetworkEntityDao().update(getNetworkEntityDao().networkToEntity(xarxa));
        } else {
            throw new SeyconException(Messages.getString("NetworkServiceImpl.NotAuthorizedUpdateNet")); //$NON-NLS-1$
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#delete(es.caib.seycon.ng.comu.Xarxa)
     */
    protected void handleDelete(Network xarxa) throws java.lang.Exception {
        if (AutoritzacionsUsuari.canDeleteAllNetworks() /*
                                                         * ||
                                                         * hasNetworkAuthorizations
                                                         * (
                                                         * Security.getPrincipal
                                                         * (
                                                         * ).getName(),xarxa.getCodi
                                                         * (), new
                                                         * int[]{ADMINISTRACIO})
                                                         */) {
        	NetworkEntity xarxaEntity = getNetworkEntityDao().networkToEntity(xarxa);
        	getNetworkAuthorizationEntityDao().remove( xarxaEntity.getAuthorizations());
        	for ( HostEntity maq: xarxaEntity.getHosts())
        	{
        		if (maq.getDeleted() != null && maq.getDeleted().booleanValue()) {
        			getAccessLogEntityDao().remove(
        					getAccessLogEntityDao().findByHostId(maq.getId()));
        			getHostEntityDao().remove(maq);
        			createHostTask(maq.getName());
        		}
        		else
            		throw new SeyconException(String.format(Messages.getString("XarxaServiceImpl.IntegrityViolationHosts"),  //$NON-NLS-1$
    						new Object[]{xarxaEntity.getName(), maq.getName()}));
        	}
            getNetworkEntityDao().remove(xarxaEntity);
        } else {
            throw new SeyconException(Messages.getString("NetworkServiceImpl.NotAuthorizedDeleteNet")); //$NON-NLS-1$
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#revoke(es.caib.seycon.ng.comu.XarxaAC)
     */
    protected void handleRevoke(com.soffid.iam.api.NetworkAuthorization xarxaAC) throws java.lang.Exception {
        // Abans tenia esAdministrador()
        if (AutoritzacionsUsuari.canCreateAllNetworks() || AutoritzacionsUsuari.canUpdateAllNetworks() || hasNetworkAuthorizations(Security.getCurrentUser(), xarxaAC.getNetworkCode(), new int[]{ADMINISTRACIO})) {
            NetworkAuthorizationEntity entity = getNetworkAuthorizationEntityDao().networkAuthorizationToEntity(xarxaAC);
            getNetworkAuthorizationEntityDao().remove(entity);
        } else {
            throw new SeyconException(
                    Messages.getString("NetworkServiceImpl.NotAuthorizedDeleteAuthorizations")); //$NON-NLS-1$
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#getACL(es.caib.seycon.ng.comu.Xarxa)
     */
    protected List<NetworkAuthorization> handleGetACL(com.soffid.iam.api.Network xarxa) throws java.lang.Exception {
        if (AutoritzacionsUsuari.canCreateAllNetworks() || AutoritzacionsUsuari.canQueryAllNetworks() || AutoritzacionsUsuari.canUpdateAllNetworks() || AutoritzacionsUsuari.canSupportAllNetworks_VNC() || hasNetworkAuthorizations(Security.getCurrentUser(), xarxa.getCode(), new int[]{ADMINISTRACIO, CONSULTA, SUPORT})) {
            NetworkEntity entity = getNetworkEntityDao().networkToEntity(xarxa);
            Collection<NetworkAuthorizationEntity> acls = getNetworkAuthorizationEntityDao().findByNetwork(entity);
            if (acls != null) {
                return getNetworkAuthorizationEntityDao().toNetworkAuthorizationList(acls);
            }
            return new LinkedList<NetworkAuthorization>();
        }
        throw new SeyconException(
                Messages.getString("NetworkServiceImpl.NotAuthorizedViewListAccess")); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#findXarxaByCodi(String)
     */
    protected Network handleFindNetworkByName(String codi) throws java.lang.Exception {
        NetworkEntity xarxaEntity = getNetworkEntityDao().findByName(codi);
        if (xarxaEntity != null) {
            Network xarxa = getNetworkEntityDao().toNetwork(xarxaEntity);
            // if (teAccesLecturaXarxa(xarxa)) {
            return xarxa;

            // }
            // throw new SeyconException(
            // "L'usuari no té accés a la xarxa amb codi '" + codi + "'");
        }
        return null;
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#create(es.caib.seycon.ng.comu.Maquina)
     */
    protected com.soffid.iam.api.Host handleCreate(com.soffid.iam.api.Host maquina) throws java.lang.Exception {
        if (AutoritzacionsUsuari.canCreateAllHosts() || maquinaPermesa(maquina, ADMINISTRACIO)) {
        	HostEntity old = getHostEntityDao().findByName(maquina.getName());
        	if (old != null)
        	{
        		if ( ! Boolean.TRUE.equals( old.getDeleted() ) )
        			throw new InternalErrorException (String.format("The host %s already exists", maquina.getName()));
        		maquina.setId(old.getId());
        		getHostEntityDao().hostToEntity(maquina, old, true);
                old.setDeleted(new Boolean(false));
                getHostEntityDao().update(old);
                updateHostAttributes(maquina, old);
    			createHostTask(maquina.getName());
                updateHostAlias(old, maquina.getHostAlias());
	            return getHostEntityDao().toHost(old);                
        	} else {
	            HostEntity entity = getHostEntityDao().hostToEntity(maquina);
	            entity.setDeleted(new Boolean(false));
	            getHostEntityDao().create(entity);
	            maquina.setId(entity.getId());
	            updateHostAttributes(maquina, entity);
	            updateHostAlias(entity, maquina.getHostAlias());
    			createHostTask(maquina.getName());
	            return getHostEntityDao().toHost(entity);
        	}
        }
        throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.NotAuthorizedMakeMachine"), maquina.getName())); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#update(es.caib.seycon.ng.comu.Maquina)
     */
    protected void handleUpdate(com.soffid.iam.api.Host maquina) throws java.lang.Exception {
        if (teAccesEscripturaMaquina(maquina)) {
            HostEntity hostEntity = getHostEntityDao().load(maquina.getId());
            if (!hostEntity.getName().equals(maquina.getName()))
    			createHostTask(hostEntity.getName());
            getHostEntityDao().hostToEntity(maquina, hostEntity, true);
			getHostEntityDao().update(hostEntity);
            updateHostAttributes(maquina, hostEntity);
			createHostTask(maquina.getName());
            updateHostAlias(hostEntity, maquina.getHostAlias());
        } else {
            // Comprovem permís per actualitzar el SO de la màquina
            if (AutoritzacionsUsuari.canUpdateHostOS()) {
                // Fem el canvi del SO a la maquina de la base de dades
                // per verificar que només s'ha canviat el SO (jur jur)
                // Cerquem per ID de la màquina (ja existeix !!)
                Host maquinaTrobada = null;
                if (maquina.getId() != null)
                    maquinaTrobada = findHostById(maquina.getId());
                else
                    maquinaTrobada = findHostByName(maquina.getName());

                if (maquinaTrobada == null) {
                    throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.MachineNotFound"), maquina.getName())); //$NON-NLS-1$
                }
                // Fem els canvis permesos a la màquina original
                // perquè la comparació dóne OK (només permet aquestos canvis)
                // SO
                maquinaTrobada.setOs(maquina.getOs());
                // La marquem com a servidor d'impressores
                if (maquina.getPrintersServer() != null)
                    maquinaTrobada.setPrintersServer(maquina.getPrintersServer());
                // I les comparem
                if (maquinesIguals(maquinaTrobada, maquina)) {
                    getHostEntityDao().update(getHostEntityDao().hostToEntity(maquinaTrobada));
        			createHostTask(maquina.getName());
                } else {
                    throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.OnlyChangeSOMachine"), maquina.getName()));
                }
            } else {
                throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.NotAuthorizedToUpdateMachine"), maquina.getName()));
            }
        }
    }

    private void updateHostAlias(HostEntity host, List<String> hostAlias) {
    	HashSet<String> l = new HashSet<>(hostAlias);
    	for (HostAliasEntity hae: new LinkedList<HostAliasEntity>(host.getHostAlias())) {
    		if (l.contains(hae.getAlias()))
    			l.remove(hae.getAlias());
    		else {
    			host.getHostAlias().remove(hae);
    			getHostAliasEntityDao().remove(hae);
    		}
    	}
    	for (String s: l) {
    		HostAliasEntity ha = getHostAliasEntityDao().newHostAliasEntity();
    		ha.setHost(host);
    		ha.setAlias(s);
    		getHostAliasEntityDao().create(ha);
    		host.getHostAlias().add(ha);
    	}
	}

	/**
     * @see es.caib.seycon.ng.servei.XarxaService#delete(es.caib.seycon.ng.comu.Maquina)
     */
    protected void handleDelete(com.soffid.iam.api.Host maquina) throws java.lang.Exception {
    	if (AutoritzacionsUsuari.canDeleteAllHosts() ||
			maquinaPermesa(maquina, ADMINISTRACIO))
    	{
    		CriteriaSearchConfiguration csc = new CriteriaSearchConfiguration();
    		csc.setMaximumResultSize(1);
    		
    		// Check access logs
        	if (getAccessLogEntityDao().findByHostId(csc, maquina.getId()).isEmpty())
        	{
        		// Check associated printers
        		if (getPrinterService().findPrintersByFilter(null, null, null, maquina.getName()).isEmpty())
        		{
        			HostEntity entity = getHostEntityDao().load(maquina.getId());
        			for (HostEntryPointEntity hep: entity.getEntryPoints()) {
        				EntryPointEntity ep = hep.getEntryPoint();
						ep.getHosts().remove(hep);
        				getHostEntryPointEntityDao().remove(hep);
        				if (ep.getHosts().isEmpty()) {
	        				AccessTree at = getEntryPointEntityDao().toAccessTree(ep);
	        				getEntryPointService().delete(at);
        				}
        			}
        			entity.getEntryPoints().clear();
        			for (HostSystemEntity hse: entity.getSystems()) {
        				SystemEntity se = hse.getSystem();
        				se.getHosts().remove(hse);
        				getHostSystemEntityDao().remove(hse);
        				if (se.getHosts().isEmpty()) {
	        				System s = getSystemEntityDao().toSystem(se);
	        				s.setUrl(null);
	        				s.setUrl2(null);
	        				getDispatcherService().update(s);
        				}
        			}
        			entity.getSystems().clear();
        			getHostEntityDao().remove(entity);
        			createHostTask(maquina.getName());
        		}
        		else
        		{
        			throw new InternalErrorException(
						Messages.getString("NetworkServiceImpl.UnableDeleteHostMessage")); //$NON-NLS-1$
        		}
        	}
        	else
        	{
        		HostEntity entity = getHostEntityDao().hostToEntity(maquina);
        		entity.setDeleted(true);
        		getHostEntityDao().update(entity);
    			createHostTask(maquina.getName());
        	}
        }
    	else
    	{
            throw new SeyconException(Messages.getString("NetworkServiceImpl.NotAuthorizedToDeleteMachine")); //$NON-NLS-1$
        }
    }

    protected List<Host> handleGetMailServers() throws java.lang.Exception {
        return findHostByFilter(null, null, null, null, "S", null, null, null, null, null, null, new Boolean(false));
    }

    protected List<Host> handleGetProfileServers() throws java.lang.Exception {
        return findHostByFilter(null, null, null, null, null, "S", null, null, null, null, null, new Boolean(false));
    }

    protected List<Host> handleGetHomeServers() throws java.lang.Exception {
        return findHostByFilter(null, null, null, null, null, "S", null, null, null, null, null, new Boolean(false));
    }

    protected Host handleFindHostByName(java.lang.String nom) throws java.lang.Exception {
        HostEntity maquinaEntity = getHostEntityDao().findByName(nom);
        if (maquinaEntity != null) {
            Host maquina = getHostEntityDao().toHost(maquinaEntity);
            if (this.teAccesLecturaMaquina(maquina)) {
                return maquina;
            }
            throw new SeyconException(String.format(
                    Messages.getString("NetworkServiceImpl.NotAuthorizedSearchMachine"), nom)); //$NON-NLS-1$
        }
        return null;
    }

    // Emprat des de xarxesllista.zul (parametres.zul) i xarxes.zul
    protected List<Network> handleFindNetworkByFilter(java.lang.String codi, java.lang.String adreca, java.lang.String descripcio, java.lang.String mascara, java.lang.String normalitzada, java.lang.String dhcp, String maquina) throws java.lang.Exception {
		List<NetworkEntity> xarxes = localFindXarxaByFiltre(codi, adreca, descripcio,
			mascara, normalitzada, dhcp, maquina);
    	int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
    	
    	if (xarxes != null && xarxes.size() != 0)
    	{
    		List<Network> xarxesTrobades = filtraXarxes(getNetworkEntityDao().toNetworkList(xarxes));
            List<Network> res = filtraPerMaquina(xarxesTrobades, maquina);
            
			// Check maximum number of results
			if (res.size() > limitResults)
			{
				return new LinkedList<Network>(res).subList(0, limitResults);
//				throw new SeyconException(
//					Messages.getString("NetworkServiceImpl.BigSearchResults")); //$NON-NLS-1$
			}
			
			return res;
		}
    	
    	return new LinkedList<Network>();
	}

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#getMaquines()
     */
    protected List<Host> handleGetHosts() throws java.lang.Exception {
        return getHostEntityDao().toHostList(getHostEntityDao().loadAll());
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#findMaquinaByXarxa(es.caib.seycon.ng.comu.Xarxa)
     */
    @SuppressWarnings(value = "rawtypes")
    protected List<Host> handleFindHostsByNetwork(com.soffid.iam.api.Network xarxa) throws java.lang.Exception {
        List<Host> maquines = findHostByFilter(null, null, null, null, null, null, null, null, null, xarxa.getCode(), null, new Boolean(false));
        return maquines;
    }

    protected List<Host> handleFindHostByFilterUnrestricted(String nom, String sistemaOperatiu, String adreca, String dhcp, String correu, String ofimatica, String alias, String mac, String descripcio, String xarxa, String codiUsuari, Boolean filtra) throws Exception {
        if (nom != null && (nom.trim().compareTo("") == 0 || nom.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            nom = null;
        }
        if (sistemaOperatiu != null
                && (sistemaOperatiu.trim().compareTo("") == 0 || sistemaOperatiu.trim().compareTo( //$NON-NLS-1$
                        "%") == 0)) { //$NON-NLS-1$
            sistemaOperatiu = null;
        }
        if (adreca != null
                && (adreca.trim().compareTo("") == 0 || adreca.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            adreca = null;
        }
        if (dhcp != null && (dhcp.trim().compareTo("") == 0 || dhcp.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            dhcp = null;
        }
        if (correu != null
                && (correu.trim().compareTo("") == 0 || correu.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            correu = null;
        }
        if (ofimatica != null
                && (ofimatica.trim().compareTo("") == 0 || ofimatica.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            ofimatica = null;
        }
        if (alias != null && (alias.trim().compareTo("") == 0 || alias.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            alias = null;
        }
        if (mac != null && (mac.trim().compareTo("") == 0 || mac.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            mac = null;
        }
        if (descripcio != null
                && (descripcio.trim().compareTo("") == 0 || descripcio.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            descripcio = null;
        }
        if (xarxa != null && (xarxa.trim().compareTo("") == 0 || xarxa.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            xarxa = null;
        }

        if (codiUsuari != null
                && (codiUsuari.trim().compareTo("") == 0 || codiUsuari.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            codiUsuari = null;
        }

        Collection<HostEntity> maquines = null;

        LinkedList<Parameter> params = new LinkedList<Parameter>();
        // Realizamos la siguiente consulta (sin tener cuenta el alias)
        String query = "select distinct maquina from " //$NON-NLS-1$
                + " com.soffid.iam.model.SessionEntity sessio " //$NON-NLS-1$
                + " right outer join sessio.host as maquina " //$NON-NLS-1$
                + " left outer join sessio.user as usuari" + //$NON-NLS-1$
                " where maquina.deleted = false and maquina.tenant.id = :tenantId "; //$NON-NLS-1$
        params.add(new Parameter("tenantId", Security.getCurrentTenantId())); //$NON-NLS-1$
        if (nom != null ) {
            query = query + "and maquina.name like :nom "; //$NON-NLS-1$
            params.add(new Parameter("nom", nom)); //$NON-NLS-1$
        }
        if (sistemaOperatiu != null) {
//            query = query + "and maquina.oldSistemaOperatiu like :sistemaOperatiu "
//				+ "or maquina.operatingSystem = :operatingSystem ";
//            params.add(new Parameter("sistemaOperatiu", sistemaOperatiu)); //$NON-NLS-1$
          query = query + "and maquina.operatingSystem.name = :operatingSystem "; //$NON-NLS-1$
            params.add(new Parameter("operatingSystem", sistemaOperatiu)); //$NON-NLS-1$
        }
        if (adreca != null) {
            query = query + "and maquina.hostIP like :adreca "; //$NON-NLS-1$
            params.add(new Parameter("adreca", adreca)); //$NON-NLS-1$
        }
        if (dhcp != null) {
            query = query + "and maquina.dhcp like :dhcp "; //$NON-NLS-1$
            params.add(new Parameter("dhcp", dhcp)); //$NON-NLS-1$
        }
        if (correu != null) {
            query = query + "and maquina.mail like :correu "; //$NON-NLS-1$
            params.add(new Parameter("correu", correu)); //$NON-NLS-1$
        }
        if (ofimatica != null) {
            query = query + "and maquina.folders like :ofimatica "; //$NON-NLS-1$
            params.add(new Parameter("ofimatica", ofimatica)); //$NON-NLS-1$
        }
        if (mac != null) {
            query = query + "and maquina.mac like :mac "; //$NON-NLS-1$
            params.add(new Parameter("mac", mac)); //$NON-NLS-1$
        }
        if (descripcio != null) {
            query = query + "and maquina.description like :descripcio "; //$NON-NLS-1$
            params.add(new Parameter("descripcio", descripcio)); //$NON-NLS-1$
        }
        if (xarxa != null) {
            query = query + "and maquina.network.name like :xarxa "; //$NON-NLS-1$
            params.add(new Parameter("xarxa", xarxa)); //$NON-NLS-1$
        }
        if (codiUsuari != null) {
            query = query + "and usuari.userName like :codiUsuari "; //$NON-NLS-1$
            params.add(new Parameter("codiUsuari", codiUsuari)); //$NON-NLS-1$
        }
        query = query + "order by maquina.name "; //$NON-NLS-1$

        maquines = getHostEntityDao().query(query, params.toArray(new Parameter[0]));

        // Filtramos por alias (si se ha especificado algún valor)
        if (alias != null) {
            Collection<HostAliasEntity> maquinesAlias = getHostAliasEntityDao().findHostByAlias(alias);
            HashSet<Long> h_maquinesAlias = new HashSet(maquinesAlias.size());
            for (Iterator it = maquinesAlias.iterator(); it.hasNext(); ) {
                HostEntity maqAlias = (HostEntity) it.next();
                h_maquinesAlias.add(maqAlias.getId());
            }
            // Nos quedamos sólo con las máquinas de la búsqueda que tengan el
            // alias indicado
            for (Iterator it = maquines.iterator(); it.hasNext(); ) {
                HostEntity maq = (HostEntity) it.next();
                if (!h_maquinesAlias.contains(maq.getId())) it.remove();
            }

        }
        return getHostEntityDao().toHostList(maquines);
    }

    // Emprar des de maquines.zul per cercar
    protected List<Host> handleFindHostByFilter(String nom, String sistemaOperatiu, String adreca, String dhcp, String correu, String ofimatica, String alias, String mac, String descripcio, String xarxa, String codiUsuari, Boolean filtra) throws Exception {
		Collection<Host> maquines = null;
    	int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
    	
    	// Cridem al "nou" mètode i després restringim
    	// NOTA: El mètode ens torna Maquina (VO), si llevem la crida
    	// després hem de transformar les entity a VO
    	maquines = handleFindHostByFilterUnrestricted(nom, sistemaOperatiu, adreca, dhcp, correu, ofimatica, alias, mac, descripcio, xarxa, codiUsuari, filtra);
    	
    	if (maquines != null)
    	{
    		if ((filtra != null) && filtra.booleanValue() &&
				(maquines.size() > limitResults))
			{
    			maquines = new LinkedList<Host>(maquines).subList(0, limitResults);
//                throw new SeyconException(
//                        Messages.getString("NetworkServiceImpl.BigSearchResults")); //$NON-NLS-1$
			}
    		
    		// getMaquinaEntityDao().toMaquinaCollection(maquines);
    		maquines = filtraMaquines(maquines, CONSULTA);
    		return new LinkedList<Host>(maquines);
		}
    	
    	return new LinkedList<Host>();
	}

    protected NetworkAuthorization handleCreate(NetworkAuthorization accessList) throws Exception {
    	String u = Security.getCurrentUser();
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canCreateAllNetworks() || AutoritzacionsUsuari.canUpdateAllNetworks() || hasNetworkAuthorizations(u, accessList.getNetworkCode(), new int[]{ADMINISTRACIO})) {
            NetworkAuthorizationEntity entity = getNetworkAuthorizationEntityDao().networkAuthorizationToEntity(accessList);
            if (entity.getUser() != null) {
                if (entity.getUser().getUserName().compareTo(u) == 0) {
                    throw new SeyconException(
                            Messages.getString("NetworkServiceImpl.NoAutoassignNetLists")); //$NON-NLS-1$
                }
            }
            Identity identity = accessList.getIdentity();
            if(identity.getIdentityCode() != null && !identity.getIdentityCode().isEmpty())
            	getNetworkAuthorizationEntityDao().create(entity);
            else
            	throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.IdentityValidation"), entity.getNetwork().getName())); //$NON-NLS-1$
            return getNetworkAuthorizationEntityDao().toNetworkAuthorization(entity);
        }
        throw new SeyconException(Messages.getString("NetworkServiceImpl.NotAuthorizedAdminNet")); //$NON-NLS-1$
    }

    protected void handleDelete(NetworkAuthorization accessList) throws Exception {
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canCreateAllNetworks() || AutoritzacionsUsuari.canUpdateAllNetworks() || hasNetworkAuthorizations(Security.getCurrentUser(), accessList.getNetworkCode(), new int[]{ADMINISTRACIO})) {
            NetworkAuthorizationEntity entity = getNetworkAuthorizationEntityDao().networkAuthorizationToEntity(accessList);
            getNetworkAuthorizationEntityDao().remove(entity);
        } else {
            throw new SeyconException(Messages.getString("NetworkServiceImpl.NoAdminNets")); //$NON-NLS-1$
        }
    }

    protected NetworkAuthorization handleUpdate(NetworkAuthorization accessList) throws Exception {
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canCreateAllNetworks() || AutoritzacionsUsuari.canUpdateAllNetworks() || hasNetworkAuthorizations(Security.getCurrentUser(), accessList.getNetworkCode(), new int[]{ADMINISTRACIO})) {
            NetworkAuthorizationEntity entity = getNetworkAuthorizationEntityDao().networkAuthorizationToEntity(accessList);
            getNetworkAuthorizationEntityDao().update(entity);
            return getNetworkAuthorizationEntityDao().toNetworkAuthorization(entity);
        }
        throw new SeyconException(Messages.getString("NetworkServiceImpl.NoAdminNets")); //$NON-NLS-1$
    }

    protected List<Identity> handleFindIdentitiesByName(String codi) throws Exception {
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()) {
            if (codi != null && (codi.trim().compareTo("") == 0 || codi.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
                codi = null;
            }

            List<Identity> identitats = new LinkedList<Identity>();

            Collection<User> usuaris = this.getUserService().findUserByCriteria(codi, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, new Boolean(false));
            if (usuaris != null && usuaris.size() > 0) {
                List<UserEntity> usuariEntities = getUserEntityDao().userToEntityList(usuaris);
                identitats = getUserEntityDao().toIdentityList(usuariEntities);
            }

            Collection<RoleEntity> rols = getRoleEntityDao().findRolesByCriteria(codi, null, null, null, null, null);
            if (rols != null) {
                identitats.addAll(getRoleEntityDao().toIdentityList(rols));
            }

            Collection<GroupEntity> grups = getGroupEntityDao().findByCriteria(codi, null, null, null, null, null);
            if (grups != null) {
                identitats.addAll(getGroupEntityDao().toIdentityList(grups));
            }

            return identitats;
        }
        throw new SeyconException(Messages.getString("NetworkServiceImpl.AdminNet")); //$NON-NLS-1$
    }

    protected Identity handleFindIdentityByName(String codi) throws Exception {
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()) {
            if (codi != null && (codi.trim().compareTo("") == 0 || codi.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
                return null;
            }

            UserEntity usuari = getUserEntityDao().findByUserName(codi);
            if (usuari != null) {
                Identity identitat = getUserEntityDao().toIdentity(usuari);
                return identitat;
            }

            RoleEntity rol = null;
            String[] partsCodi = codi.split("@"); //$NON-NLS-1$
            String[] partsCodi2 = partsCodi[1].split(">"); //$NON-NLS-1$
            rol = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(partsCodi[0], partsCodi2[1], partsCodi2[0]);
            if (rol != null) {
                Identity identitat = getRoleEntityDao().toIdentity(rol);
                return identitat;
            }

            GroupEntity grup = getGroupEntityDao().findByName(codi);
            if (grup != null) {
                Identity identitat = getGroupEntityDao().toIdentity(grup);
                return identitat;
            }

            return null;
        }
        throw new SeyconException(Messages.getString("NetworkServiceImpl.NoAdminNets")); //$NON-NLS-1$
    }

    protected NetworkAuthorization handleFindNetworkAuthorizationsByNetworkNameAndIdentityName(String codiXarxa, String codiIdentitat) throws Exception {
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()) {
            NetworkAuthorizationEntity xarxaACEntity = getNetworkAuthorizationEntityDao().findByNetworkAndIdentity(codiXarxa, codiIdentitat);
            if (xarxaACEntity != null) {
                NetworkAuthorization networkAuthorization = getNetworkAuthorizationEntityDao().toNetworkAuthorization(xarxaACEntity);
                return networkAuthorization;
            }
            return null;
        }
        throw new SeyconException(Messages.getString("NetworkServiceImpl.NoAdminNets")); //$NON-NLS-1$
    }

    /*
     * Obté les autoritzacions DIRECTES de Xarxa a aquest grup (non-Javadoc)
     * 
     * @see es.caib.seycon.ng.servei.XarxaServiceBase#
     * handleFindNetworkAuthorizationsByCodiGrup(java.lang.String)
     */
    protected List<NetworkAuthorization> handleFindNetworkAuthorizationsByGroupName(String codiGrup) throws Exception {
        Collection<NetworkAuthorizationEntity> xarxesAC = getNetworkAuthorizationEntityDao().findByGroupName(codiGrup);
        if (xarxesAC != null) {
            return getNetworkAuthorizationEntityDao().toNetworkAuthorizationList(xarxesAC);
        }
        return new LinkedList<NetworkAuthorization>();
    }

    // Antic mètode getNetworkAuthorizations(codiUsuari)
    protected List<NetworkAuthorization> handleFindALLNetworkAuthorizationsByUserName(String codiUsuari) throws Exception {
        Collection<NetworkAuthorization> xarxes = new LinkedList();
        // acces list per codi d'usuari
        UserEntity usuariEntity = getUserEntityDao().findByUserName(codiUsuari);
        if (usuariEntity != null) {
            Collection<NetworkAuthorization> networkAuthorizations = findNetworkAuthorizationsByUserName(codiUsuari);
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                xarxes.add(newtworkAuthorization);
            }
        } else {
            throw new SeyconException(
                    String.format(Messages.getString("NetworkServiceImpl.UserNotFound"), codiUsuari)); //$NON-NLS-1$
        }
        // llistes d'acces per grups
        Collection<Group> grups = getGroupService().findGroupsByUserName(codiUsuari);
        Iterator grupIterator = grups.iterator();
        while (grupIterator.hasNext()) {
            Group grup = (Group) grupIterator.next();
            Collection networkAuthorizations = findNetworkAuthorizationsByGroupName(grup.getName());
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                xarxes.add(newtworkAuthorization);
            }
        }
        // llistes d'acces per rols: filtra els rols
        Collection<Role> rols = getApplicationService().findRolesByUserName(codiUsuari);

        Iterator rolsIterator = rols.iterator();
        while (rolsIterator.hasNext()) {
            Role rol = (Role) rolsIterator.next();
            Collection networkAuthorizations = findNetworkAuthorizationsByRol(rol);
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                xarxes.add(newtworkAuthorization);
            }
        }
        return (List<NetworkAuthorization>) xarxes;
    }

    /*
     * Obté les autoritzacions DIRECTES de Xarxa a aquest usuari (non-Javadoc)
     * 
     * @see es.caib.seycon.ng.servei.XarxaServiceBase#
     * handleFindNetworkAuthorizationsByCodiUsuari(java.lang.String)
     */
    protected List<NetworkAuthorization> handleFindNetworkAuthorizationsByUserName(String codiUsuari) throws Exception {
        Collection<NetworkAuthorizationEntity> xarxesAC = getNetworkAuthorizationEntityDao().findByUserName(codiUsuari);
        if (xarxesAC != null) {
            return getNetworkAuthorizationEntityDao().toNetworkAuthorizationList(xarxesAC);
        }
        return new Vector();
    }

    /*
     * Obté les autoritzacions DIRECTES de Xarxa a aquest ROL (non-Javadoc)
     * 
     * @see es.caib.seycon.ng.servei.XarxaServiceBase#
     * handleFindNetworkAuthorizationsByNomRol(java.lang.String)
     */
    protected List<NetworkAuthorization> handleFindNetworkAuthorizationsByRoleName(String nomRol) throws Exception {
        Collection<NetworkAuthorizationEntity> xarxesAC = getNetworkAuthorizationEntityDao().findByRoleName(nomRol);
        if (xarxesAC != null) {
            return getNetworkAuthorizationEntityDao().toNetworkAuthorizationList(xarxesAC);
        }
        return new Vector();
    }

    protected String handleGetFirstAvailableIP(String codiXarxa) throws Exception {
        NetworkEntity xarxa = getNetworkEntityDao().findByName(codiXarxa);
        if (xarxa != null) {
            String ipLliure = getNetworkEntityDao().getFirstFreeIP(xarxa.getAddress(), xarxa.getMask());
            return ipLliure;
        }
        throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.NetNotFound"), codiXarxa)); //$NON-NLS-1$
    }

    protected Long handleGetNotAvailableIPs(String codiXarxa) throws Exception {
        NetworkEntity xarxaEntity = getNetworkEntityDao().findByName(codiXarxa);
        if (xarxaEntity != null) {
            Network xarxa = getNetworkEntityDao().toNetwork(xarxaEntity);
            if (teAccesLecturaXarxa(xarxa)) {
    	        return getNetworkEntityDao().countByNetwork(codiXarxa);
            }
            throw new SeyconException(String.format(
                    Messages.getString("NetworkServiceImpl.NoReadNetPermission"), codiXarxa)); //$NON-NLS-1$
        }
        throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.NetNotFound"), codiXarxa)); //$NON-NLS-1$
    }

    protected Long handleGetAvailableIPs(String codiXarxa) throws Exception {
        NetworkEntity xarxaEntity = getNetworkEntityDao().findByName(codiXarxa);
        if (xarxaEntity != null) {
        	Network xarxa = getNetworkEntityDao().toNetwork(xarxaEntity);
        	if (teAccesLecturaXarxa(xarxa)) {
	            try 
	            {
	    	        IPAddress ip = new IPAddress(xarxaEntity.getAddress(), xarxaEntity.getMask());
	    	        int total = ip.networkSize() ;
	    	        total = total - getNetworkEntityDao().countByNetwork(codiXarxa).intValue();
	    	        return new Long(total);
	    		} catch (InvalidIPException e) {
	    			throw new RuntimeException("Invalid IP", e);
	    		}
            }
            throw new SeyconException(String.format(
                    Messages.getString("NetworkServiceImpl.NoReadNetPermission"), codiXarxa)); //$NON-NLS-1$
        }
        throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.NetNotFound"), codiXarxa)); //$NON-NLS-1$
    }

    protected Host handleFindHostById(Long idMaquina) throws Exception {
        HostEntity maquinaEntity = getHostEntityDao().load(idMaquina);
        if (maquinaEntity != null) {
            return getHostEntityDao().toHost(maquinaEntity);
        }
        return null;
    }

    protected Host handleFindHostByIp(String ip) throws Exception {
    	for (HostEntity host: getHostEntityDao().findByIP(ip))
    	{
    		if (host.getDeleted() == null || ! host.getDeleted().booleanValue())
    			return getHostEntityDao().toHost(host);
    	}
    	for (HostEntity host: getHostEntityDao().findByIP(ip))
    	{
   			return getHostEntityDao().toHost(host);
    	}
        return null;
    }


    protected String[] handleGetTasks(String nomMaquina) throws Exception {
        String[] resultats = getHostEntityDao().getTasks(nomMaquina);
        return resultats;
    }

    protected List<HostAlias> handleFindAliasByHostName(String nomMaquina) throws Exception {
        Collection<HostAliasEntity> alias = getHostAliasEntityDao().findAliasByHostName(nomMaquina);
        return getHostAliasEntityDao().toHostAliasList(alias);
    }

    protected HostAlias handleCreate(HostAlias aliasMaquina) throws Exception {
        HostAliasEntity entity = getHostAliasEntityDao().hostAliasToEntity(aliasMaquina);
        Host maquina = getHostEntityDao().toHost(entity.getHost());
        if (teAccesEscripturaMaquina(maquina)) {
            getHostAliasEntityDao().create(entity);
            return getHostAliasEntityDao().toHostAlias(entity);
        } else
            throw new SeyconException(
                    Messages.getString("NetworkServiceImpl.NoMakeAliasPermission")); //$NON-NLS-1$

    }

    protected void handleDelete(HostAlias aliasMaquina) throws Exception {
        HostAliasEntity entity = getHostAliasEntityDao().hostAliasToEntity(aliasMaquina);
        Host maquina = getHostEntityDao().toHost(entity.getHost());
        if (teAccesEscripturaMaquina(maquina)) {

            getHostAliasEntityDao().remove(entity);
        } else
            throw new SeyconException(
                    Messages.getString("NetworkServiceImpl.NoDeleteAliasPermission")); //$NON-NLS-1$
    }

    protected void handleUpdate(HostAlias aliasMaquina) throws Exception {
        HostAliasEntity entity = getHostAliasEntityDao().hostAliasToEntity(aliasMaquina);
        Host maquina = getHostEntityDao().toHost(entity.getHost());
        if (teAccesEscripturaMaquina(maquina)) {
            getHostAliasEntityDao().update(entity);
        } else
            throw new SeyconException(
                    Messages.getString("NetworkServiceImpl.NoUpdateAliasPermission")); //$NON-NLS-1$

    }

    protected AdministratorAuthorizationToAccessHost handleCreate(AdministratorAuthorizationToAccessHost autoritzacioAccesComAdministrador) throws Exception {
        HostAdminEntity entity = getHostAdminEntityDao().administratorAuthorizationToAccessHostToEntity(autoritzacioAccesComAdministrador);
        getHostAdminEntityDao().create(entity);
        // auditem la petició (ara es fa des del workflow)
        auditaPeticioAdministrarHost(autoritzacioAccesComAdministrador.getHostName(), autoritzacioAccesComAdministrador.getUserCode(), "C"); //$NON-NLS-1$
        return getHostAdminEntityDao().toAdministratorAuthorizationToAccessHost(entity);
    }

    protected List<AdministratorAuthorizationToAccessHost> handleFindAuthorizationsToAccessHostWithAdministratorRigthsByHostsAndRequestDate(String nomHost, String dataPeticio, String dataCaducitat) throws Exception {

        Date d_dataPeticio = DateUtils.nullDate;
        Date d_dataCaducitat = DateUtils.nullDate;

        if (dataPeticio != null && !"".equals(dataPeticio)) //$NON-NLS-1$
            d_dataPeticio = DateUtils.stringToDate(dataPeticio, false);

        if (dataCaducitat != null && !"".equals(dataCaducitat)) { //$NON-NLS-1$
            if ("hui".equals(dataCaducitat)) { //$NON-NLS-1$
                d_dataCaducitat = new Date(); // posem la data de hui
            } else
                d_dataCaducitat = DateUtils.stringToDate(dataCaducitat, false);
        }

        Collection<HostAdminEntity> res = getHostAdminEntityDao().findByHostNameAndRequestDate(nomHost, d_dataPeticio, d_dataCaducitat, DateUtils.nullDate);

        return getHostAdminEntityDao().toAdministratorAuthorizationToAccessHostList(res);
    }

    protected String[] handleGetHostAdminUserAndPassword(String nomMaquina) throws Exception {

        // Añadimos auditoría de la petición
        auditaSolicitudDadesAdministradorHost(nomMaquina); // AUDITORIA

        HostEntity host = getHostEntityDao().findByName(nomMaquina);

        String dataEstabliment = DateUtils.dataToStringFull(host.getAdministratorPasswordDate());

        Password p = Password.decode(host.getAdministratorPassword()); // desencriptem
                                                                          // el
                                                                          // passwd

        return new String[]{host.getAdministratorUser(), p.getPassword(), dataEstabliment};
    }

    private Collection<String> getCodiXarxesAmbAccesAdministracio(String codiUsuari)
            throws Exception {
        Collection<NetworkAuthorization> networkAuthorizations = findALLNetworkAuthorizationsByUserName(codiUsuari);
        Collection<String> codiXarxes = new LinkedList();
        Iterator iterator = networkAuthorizations.iterator();
        while (iterator.hasNext()) {
            NetworkAuthorization netAuth = (NetworkAuthorization) iterator.next();
            if (netAuth.getLevel() == ADMINISTRACIO) {
                codiXarxes.add(netAuth.getNetworkCode());
            }
        }
        return codiXarxes;
    }

    private static boolean teAcces(String codiMaquina, String expresio) {
    	if (expresio == null || expresio.trim().isEmpty())
    		return true;
        Pattern pattern = Pattern.compile("^" + expresio + "$"); //$NON-NLS-1$ //$NON-NLS-2$
        Matcher matcher = pattern.matcher(codiMaquina);
        boolean matches = matcher.find();
        return matches;
    }

    private boolean teAccesLecturaXarxa(Network xarxa) throws InternalErrorException {
        if (AutoritzacionsUsuari.canQueryAllNetworks() || AutoritzacionsUsuari.canCreateAllHosts() || AutoritzacionsUsuari.canUpdateAllHosts() || AutoritzacionsUsuari.canCreateAllNetworks() || AutoritzacionsUsuari.canUpdateAllNetworks() || AutoritzacionsUsuari.canSupportAllNetworks_VNC() || hasNetworkAuthorizations(Security.getCurrentUser(), xarxa.getCode(), new int[]{ADMINISTRACIO, CONSULTA, SUPORT})) {
            return true;
        }
        return false;
    }

    private boolean teAccesEscripturaMaquina(Host maquina) throws InternalErrorException {
        if (AutoritzacionsUsuari.canUpdateAllHosts() || AutoritzacionsUsuari.canCreateAllHosts()) {
            return true;
        }
        if (maquinaPermesa(maquina, ADMINISTRACIO)) {
            return true;
        }
        return false;
    }

    private boolean teAccesLecturaMaquina(Host maquina) throws InternalErrorException {
        if (AutoritzacionsUsuari.canQueryAllHosts()) {
            return true;
        }
        if (maquinaPermesa(maquina, CONSULTA)) {
            return true;
        }
        return false;
    }

    private List<Network> filtraXarxes(List<Network> xarxes) throws Exception {
        if (AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canSupportAllNetworks_VNC()) {
            return xarxes;
        }
        String codiUsuari = Security.getCurrentUser();
        List xarxesPermeses = new LinkedList();
        if (codiUsuari != null)
        {
	        List codiXarxes = getCodiXarxesAmbAcces(codiUsuari);
	        Iterator iterator = xarxes.iterator();
	        while (iterator.hasNext()) {
	            Network xarxa = (Network) iterator.next();
	            if (codiXarxes.contains(xarxa.getCode())) {
	                xarxesPermeses.add(xarxa);
	            }
	        }
        }
        return xarxesPermeses;
    }

    private boolean maquinaPermesa(Host maquina, int accessLevel) throws InternalErrorException {
        String codiUsuari = Security.getCurrentUser();
        if (codiUsuari != null)
        {
	        Collection networkAuthorizations = findALLNetworkAuthorizationsByUserName(codiUsuari);
	        if (maquinaPermesa(networkAuthorizations, maquina, accessLevel)) {
	            return true;
	        }
        }
        return false;
    }

    private Long getAccessLevel(Collection networkAuthorizations, String nomMaquina,
            String codiXarxa) {
        Iterator iterator = networkAuthorizations.iterator();
        // obtenim el màxim nivell d'accés
        int maximNivell = SENSE_PERMISOS;
        while (iterator.hasNext()) {
            NetworkAuthorization networkAuthorization = (NetworkAuthorization) iterator.next();
            if (codiXarxa.compareTo(networkAuthorization.getNetworkCode()) == 0) {
                if (teAcces(nomMaquina, networkAuthorization.getMask())) {
                    int nivell = networkAuthorization.getLevel();
                    if ( nivell != LOGIN && nivell > maximNivell)
                        maximNivell = nivell;
                }
            }
        }
        return new Long(maximNivell);
    }

    private Collection<Host> filtraMaquines(Collection maquines, int accessLevel) throws InternalErrorException {
        // if (esAdministrador() || esOperador() || teAccesMaquinesVNC() ) {
        // Mirem si té permis per veure totes les xarxes o per fer VNC
        if (AutoritzacionsUsuari.canQueryAllNetworks() || AutoritzacionsUsuari.canQueryAllHosts()
                || AutoritzacionsUsuari.canSupportAllNetworks_VNC()) {
            return maquines;
        }
        String codiUsuari = Security.getCurrentUser();
        Collection<Host> maquinesPermeses = new LinkedList();
        if ( codiUsuari != null)
        {
	        Collection<NetworkAuthorization> networkAuthorizations = findALLNetworkAuthorizationsByUserName(codiUsuari);
	        Iterator iterator = maquines.iterator();
	        while (iterator.hasNext()) {
	            Host maquina = (Host) iterator.next();
	            if (maquinaPermesa(networkAuthorizations, maquina, accessLevel)) {
	                maquinesPermeses.add(maquina);
	            }
	        }
        }
        return maquinesPermeses;
    }

    private boolean maquinaPermesa(Collection networkAuthorizations, Host maquina, int accessLevel) {
        if (maquina.getNetworkCode() == null)
        	return false;
        Iterator iterator = networkAuthorizations.iterator();
        while (iterator.hasNext()) {
            NetworkAuthorization networkAuthorization = (NetworkAuthorization) iterator.next();
            if (maquina.getNetworkCode().compareTo(networkAuthorization.getNetworkCode()) == 0) {
                if (teAcces(maquina.getName(), networkAuthorization.getMask())) {
                    // Si és >= accessLevel ja hem trobat una autorització..
                    if (networkAuthorization.getLevel() >= accessLevel)
                        return true;
                }
            }
        }
        return false;
    }

    private List<String> getCodiXarxesAmbAcces(String codiUsuari) throws Exception {
        Collection<NetworkAuthorization> networkAuthorizations = findALLNetworkAuthorizationsByUserName(codiUsuari);
        Set<String> codiXarxes = new LinkedHashSet(); // perquè no es
                                                             // repetisquen
        Iterator iterator = networkAuthorizations.iterator();
        while (iterator.hasNext()) {
            NetworkAuthorization netAuth = (NetworkAuthorization) iterator.next();
            codiXarxes.add(netAuth.getNetworkCode());
        }
        return new LinkedList<String>(codiXarxes);
    }

    /**
     * Busca entre las autorizaciones de red hasta encontrar las que se le pasan
     * como parámetro. Permite acelerar la búsqueda de autorizaciones
     * 
     * @param codiUsuari
     * @param autoritzacions
     * @return
     * @throws InternalErrorException
     */
    private boolean hasNetworkAuthorizations(String codiUsuari, String codiXarxa,
            int autoritzacions[]) throws InternalErrorException {
    	if (codiUsuari == null)
    		return false;
    	
        // Su código es igual al de getNetworkAuthorizations, pero no devuelve
        // la lista
        // acces list per codi d'usuari
        com.soffid.iam.model.UserEntityDao usuariEntityDao = getUserEntityDao();
        UserEntity usuariEntity = usuariEntityDao.findByUserName(codiUsuari);
        if (usuariEntity != null) {
            Collection networkAuthorizations = findNetworkAuthorizationsByUserName(codiUsuari);
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                if (codiXarxa == null || newtworkAuthorization.getNetworkCode().equals(codiXarxa)) {
                    for (int i = 0; i < autoritzacions.length; i++) {
                        if (newtworkAuthorization.getLevel() == autoritzacions[i]) return true;
                    }
                }
            }
        } else {
            throw new SeyconException(
                    String.format(Messages.getString("NetworkServiceImpl.UserNotFound"), codiUsuari)); //$NON-NLS-1$
        }
        // llistes d'acces per rols
        Collection rols = getApplicationService().findRolesByUserName(codiUsuari);
        Iterator rolsIterator = rols.iterator();
        while (rolsIterator.hasNext()) {
            Role rol = (Role) rolsIterator.next();
            Collection networkAuthorizations = findNetworkAuthorizationsByRol(rol);
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                if (codiXarxa == null || newtworkAuthorization.getNetworkCode().equals(codiXarxa)) {
                    for (int i = 0; i < autoritzacions.length; i++) {
                        if (newtworkAuthorization.getLevel() == autoritzacions[i]) return true;
                    }
                }
            }
        }
        // llistes d'acces per grups (esto puede tardar más.. el último q
        // miramos)
        Collection grups = getGroupService().findGroupsByUserName(codiUsuari);
        Iterator grupIterator = grups.iterator();
        while (grupIterator.hasNext()) {
            Group grup = (Group) grupIterator.next();
            Collection networkAuthorizations = findNetworkAuthorizationsByGroupName(grup.getName());
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                if (codiXarxa == null || newtworkAuthorization.getNetworkCode().equals(codiXarxa)) {
                    for (int i = 0; i < autoritzacions.length; i++) {
                        if (newtworkAuthorization.getLevel() == autoritzacions[i]) return true;
                    }
                }
            }
        }
        return false; // No se ha encontrado
    }

    private boolean adrecaCorrecta(String adreca) {
    	try {
			InetAddress.getByName(adreca);
			return true;
		} catch (java.net.UnknownHostException e1) {
			return false;
		}
    }

    private boolean maquinaCompatibleAmbXarxa(String adrecaMaquina, String adrecaXarxa,
            String mascaraXarxa) throws InternalErrorException {
        try {
			if (adrecaXarxa.compareTo("0.0.0.0") == 0 && mascaraXarxa.compareTo("0.0.0.0") == 0) { //$NON-NLS-1$ //$NON-NLS-2$
			    return true;
			}

			if (!adrecaCorrecta(mascaraXarxa)) {
			    return false;
			}
			String[] mascaresXarxa = mascaraXarxa.split("\\."); //$NON-NLS-1$
			String[] adrecaXarxes = adrecaXarxa.split("\\."); //$NON-NLS-1$
			if (!adrecaCorrecta(adrecaMaquina)) {
			    return false;
			}
			byte[] adrHost = InetAddress.getByName(adrecaMaquina).getAddress();
			byte [] adrNet = InetAddress.getByName(adrecaXarxa).getAddress();
			byte [] mask;
			if ( mascaresXarxa.length == 1)
			{
				int bits = Integer.parseInt(mascaraXarxa);
				mask = new byte [adrNet.length];
				for (int i = 0; i < mask.length; i++)
				{
					mask[i] = 0;
					for (byte j = (byte) 128; j >=  1; j = (byte) (j / 2) )
					{
						if (bits > 0) mask [i] = (byte) ( mask [i] | j );
					}
				}
			}
			else
				mask = InetAddress.getByName(mascaraXarxa).getAddress();
			
			if (adrNet.length != adrHost.length || adrNet.length != mask.length)
				return false;
			for (int i = 0; i < adrNet.length; i++)
			{
			    if ( ( adrNet[i] & mask[i] ) != ( adrHost[i] & mask[i]) )
			    	return false;
			}
			return true;
		} catch (NumberFormatException e) {
			throw new InternalErrorException ("Unable to parse network "+adrecaXarxa+"/"+mascaraXarxa+": expected numeric mask");
		} catch (java.net.UnknownHostException e) {
			throw new InternalErrorException ("Unable to parse address "+e.getMessage());
		}
    }

    private boolean maquinesIguals(Host maquinaA, Host maquinaB) {
        return (!(((maquinaA.getName() == null && maquinaB.getName() != null) || (maquinaA.getName() != null && maquinaB.getName() == null)) && 
        		((maquinaA.getOs() == null || maquinaB.getOs() != null) || (maquinaA.getOs() != null || maquinaB.getOs() == null)) && 
        		((maquinaA.getIp() == null && maquinaB.getIp() != null) || (maquinaA.getIp() != null && maquinaB.getIp() == null)) && 
        		((maquinaA.getDescription() == null && maquinaB.getDescription() != null) || (maquinaA.getDescription() != null && maquinaB.getDescription() == null)) && 
        		((maquinaA.getDhcp() == null && maquinaB.getDhcp() != null) || (maquinaA.getDhcp() != null && maquinaB.getDhcp() == null)) && 
        		((maquinaA.getMail() == null && maquinaB.getMail() != null) || (maquinaA.getMail() != null && maquinaB.getMail() == null)) && 
        		((maquinaA.getOffice() == null && maquinaB.getOffice() != null) || (maquinaA.getOffice() != null && maquinaB.getOffice() == null)) && 
        		((maquinaA.getHostAlias() == null && maquinaB.getHostAlias() != null) || (maquinaA.getHostAlias() != null && maquinaB.getHostAlias() == null)) &&
        		((maquinaA.getNetworkCode() == null && maquinaB.getNetworkCode() != null) || (maquinaA.getNetworkCode() != null && maquinaB.getNetworkCode() == null)) && 
        		((maquinaA.getMac() == null && maquinaB.getMac() != null) || (maquinaA.getMac() != null && maquinaB.getMac() == null)) && 
        		((maquinaA.getPrintersServer() == null && maquinaB.getPrintersServer() != null) || (maquinaA.getPrintersServer() != null && maquinaB.getPrintersServer() == null)))) && 
        		(((maquinaA.getName() == null && maquinaB.getName() == null) || (maquinaA.getName().compareTo(maquinaB.getName()) == 0)) && 
        				((maquinaA.getOs() == null || maquinaB.getOs() == null) || (maquinaA.getOs().compareTo(maquinaB.getOs()) == 0)) && 
        				((maquinaA.getIp() == null && maquinaB.getIp() == null) || (maquinaA.getIp().compareTo(maquinaB.getIp()) == 0)) && 
        				((maquinaA.getDescription() == null && maquinaB.getDescription() == null) || (maquinaA.getDescription().compareTo(maquinaB.getDescription()) == 0)) && 
        				((maquinaA.getDhcp() == null && maquinaB.getDhcp() == null) || (maquinaA.getDhcp().compareTo(maquinaB.getDhcp()) == 0)) && 
        				((maquinaA.getMail() == null && maquinaB.getMail() == null) || (maquinaA.getMail().compareTo(maquinaB.getMail()) == 0)) && 
        				((maquinaA.getOffice() == null && maquinaB.getOffice() == null) || (maquinaA.getOffice().compareTo(maquinaB.getOffice()) == 0)) && 
        				((maquinaA.getHostAlias() == null && maquinaB.getHostAlias() == null) || (sonAliasIguales(maquinaA.getHostAlias(), maquinaB.getHostAlias()))) && 
        				((maquinaA.getNetworkCode() == null && maquinaB.getNetworkCode() == null) || (maquinaA.getNetworkCode().compareTo(maquinaB.getNetworkCode()) == 0)) && 
        				((maquinaA.getMac() == null && maquinaB.getMac() == null) || (maquinaA.getMac().compareTo(maquinaB.getMac()) == 0)) && 
        				((maquinaA.getPrintersServer() == null && maquinaB.getPrintersServer() == null) || (maquinaA.getPrintersServer().compareTo(maquinaB.getPrintersServer()) == 0)));
    }

    private boolean sonAliasIguales(List<String> v_alias1, List<String> v_alias2) {
        if ((v_alias1 == null && v_alias2 != null) || (v_alias1 != null && v_alias2 == null))
            return false; // solo 1 nulo
        if (v_alias2 == null && v_alias1 == null)
            return true; // ambos nulos
        
        return new HashSet<String>(v_alias1).equals( new HashSet<String>(v_alias2));
    }

    private List<NetworkEntity> localFindXarxaByFiltre(java.lang.String codi, java.lang.String adreca, java.lang.String descripcio, java.lang.String mascara, java.lang.String normalitzada, java.lang.String dhcp, String maquina) throws java.lang.Exception {
        XarxaSearchCriteria c = new XarxaSearchCriteria();
        c.setCodi(codi);
        c.setAdreca(adreca);
        c.setDescripcio(descripcio);
        c.setNormalitzada(normalitzada);
        c.setMascara(mascara);
        c.setDhcp(dhcp);
        
        List<NetworkEntity> xarxesTrobades = getNetworkEntityDao().findByFilter(c);
        
        return xarxesTrobades;
    }

    private List<Network> filtraPerMaquina(List<Network> xarxesTrobades, String maquina) {
        if (maquina != null && maquina.compareTo("") != 0 && maquina.compareTo("%") != 0 //$NON-NLS-1$ //$NON-NLS-2$
                && xarxesTrobades.size() > 0) {
            String xarxes = ""; //$NON-NLS-1$
            Iterator xarxesIterator = xarxesTrobades.iterator();
            while (xarxesIterator.hasNext()) {
                Network xarxa = (Network) xarxesIterator.next();
                xarxes += "\'" + xarxa.getCode() + "\'" + (xarxesIterator.hasNext() ? "," : ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            }
            String query = "select distinct maquina.network " //$NON-NLS-1$
                    + "from com.soffid.iam.model.HostEntity maquina " //$NON-NLS-1$
            		+ "where " //$NON-NLS-1$ 
                    + "maquina.name like :maquina and maquina.tenant.id=:tenantId and "
            		+ "maquina.network.name in (" + xarxes + ") " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    + "order by maquina.network.name"; //$NON-NLS-1$
            Parameter parametres[] = { new Parameter("maquina", maquina), 
            		new Parameter("tenantId", Security.getCurrentTenantId()) }; //$NON-NLS-1$
            List<NetworkEntity> xarxesList = getNetworkEntityDao().query(query, parametres);
            return getNetworkEntityDao().toNetworkList(xarxesList);
        }
        return xarxesTrobades;
    }

    private List<NetworkAuthorization> findNetworkAuthorizationsByRol(Role rol) {
        String query = "select xarxaAC from " //$NON-NLS-1$
                + "com.soffid.iam.model.NetworkAuthorizationEntity xarxaAC where " //$NON-NLS-1$
                + "xarxaAC.role.name = :nom and " //$NON-NLS-1$
                + "xarxaAC.role.system.name = :dispatcher and " //$NON-NLS-1$
                + "xarxaAC.role.system.tenant.id = :tenantId and " //$NON-NLS-1$
                + "xarxaAC.role.informationSystem.name = :aplicacio"; //$NON-NLS-1$
        Parameter[] parametres = {new Parameter("nom", rol.getName()), 
    			new Parameter("tenantId", Security.getCurrentTenantId()), 
        			new Parameter("dispatcher", rol.getSystem()), 
        			new Parameter("aplicacio", rol.getInformationSystemName())}; //$NON-NLS-1$
        Collection<NetworkAuthorizationEntity> xarxaACsTrobades = getNetworkAuthorizationEntityDao().query(query, parametres);
        if (xarxaACsTrobades != null) {
            return getNetworkAuthorizationEntityDao().toNetworkAuthorizationList(xarxaACsTrobades);
        }
        return new LinkedList<NetworkAuthorization>();
    }

    private void auditaSolicitudDadesAdministradorHost(String maquina) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();
        auditoria.setAction("A"); // Administrador //$NON-NLS-1$
        auditoria.setHost(maquina);
        auditoria.setAuthor(codiUsuari);
        auditoria.setObject("SC_ADMMAQ"); //$NON-NLS-1$

        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    protected List<Host> handleFindOfficeHostUserByFilter(String nom, String sistemaOperatiu, String adreca, String dhcp, String correu, String ofimatica, String alias, String mac, String descripcio, String xarxa, String codiUsuari, Boolean restringeixCerca, String servidorImpressores) throws Exception {
    	int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$

        if (nom != null && (nom.trim().compareTo("") == 0 || nom.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            nom = null;
        }
        if (sistemaOperatiu != null
                && (sistemaOperatiu.trim().compareTo("") == 0 || sistemaOperatiu.trim().compareTo( //$NON-NLS-1$
                        "%") == 0)) { //$NON-NLS-1$
            sistemaOperatiu = null;
        }
        if (adreca != null
                && (adreca.trim().compareTo("") == 0 || adreca.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            adreca = null;
        }
        if (dhcp != null && (dhcp.trim().compareTo("") == 0 || dhcp.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            dhcp = null;
        }
        if (correu != null
                && (correu.trim().compareTo("") == 0 || correu.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            correu = null;
        }
        if (ofimatica != null
                && (ofimatica.trim().compareTo("") == 0 || ofimatica.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            ofimatica = null;
        }
        if (alias != null && (alias.trim().compareTo("") == 0 || alias.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            alias = null;
        }
        if (mac != null && (mac.trim().compareTo("") == 0 || mac.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            mac = null;
        }
        if (descripcio != null
                && (descripcio.trim().compareTo("") == 0 || descripcio.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            descripcio = null;
        }
        if (xarxa != null && (xarxa.trim().compareTo("") == 0 || xarxa.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            xarxa = null;
        }

        if (codiUsuari != null
                && (codiUsuari.trim().compareTo("") == 0 || codiUsuari.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            codiUsuari = null;
        }

        if (servidorImpressores != null
                && (servidorImpressores.trim().compareTo("") == 0 || servidorImpressores.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            servidorImpressores = null;
        }

        Collection<HostEntity> maquines = null;

        // Realizamos la siguiente consulta (sin tener cuenta el alias)
        String query = "select distinct maquina from " //$NON-NLS-1$
                + " com.soffid.iam.model.SessionEntity sessio " //$NON-NLS-1$
                + " right outer join sessio.host as maquina " //$NON-NLS-1$
                + " left outer join sessio.user as usuari" //$NON-NLS-1$
                + " where " //$NON-NLS-1$
                + "(:nom is null or maquina.name like :nom) and (:sistemaOperatiu is null or " //$NON-NLS-1$
                + "maquina.operatingSystem.name like :sistemaOperatiu) and (:adreca is null or " //$NON-NLS-1$
                + "maquina.hostIP like :adreca) and (:dhcp is null or " //$NON-NLS-1$
                + "maquina.dhcp like :dhcp) and (:correu is null or " //$NON-NLS-1$
                + "maquina.mail like :correu) and (:ofimatica is null or " //$NON-NLS-1$
                + "maquina.folders like :ofimatica) " //$NON-NLS-1$
                + "and (:mac is null or maquina.mac like :mac) and " //$NON-NLS-1$
                + "(:descripcio is null or maquina.description like :descripcio) and " //$NON-NLS-1$
                + "(:xarxa is null or maquina.network.name like :xarxa) and " //$NON-NLS-1$
                + "(:codiUsuari is null  or (usuari is not null and  usuari.userName like :codiUsuari)) " //$NON-NLS-1$
                + "and (:servidorImpressores is null or maquina.printersServer like :servidorImpressores) " //$NON-NLS-1$
                + "and (maquina.tenant.id = :tenantId) " //$NON-NLS-1$
                + "order by maquina.name "; //$NON-NLS-1$

/*        query = "select distinct maquina from " //$NON-NLS-1$
                + " com.soffid.iam.model.HostEntity as maquina " //$NON-NLS-1$
                + " where "
                + "(:ofimatica is null or maquina.folders like :ofimatica) and " //$NON-NLS-1$
                + "(maquina.tenant.id = :tenantId) " //$NON-NLS-1$
                + "order by maquina.name "; //$NON-NLS-1$
*/
        Parameter[] params = new Parameter[] {
        		new Parameter("nom", nom), //$NON-NLS-1$
                new Parameter("sistemaOperatiu", sistemaOperatiu), new Parameter("adreca", adreca), //$NON-NLS-1$ //$NON-NLS-2$
                new Parameter("dhcp", dhcp), new Parameter("correu", correu), //$NON-NLS-1$ //$NON-NLS-2$
                new Parameter("ofimatica", ofimatica), 
                new Parameter("mac", mac), //$NON-NLS-1$ //$NON-NLS-2$
                new Parameter("descripcio", descripcio), new Parameter("xarxa", xarxa), //$NON-NLS-1$ //$NON-NLS-2$
                new Parameter("codiUsuari", codiUsuari), //$NON-NLS-1$
                new Parameter("tenantId", Security.getCurrentTenantId()), //$NON-NLS-1$
                new Parameter("servidorImpressores", servidorImpressores) 
                }; //$NON-NLS-1$
        maquines = getHostEntityDao().query(query, params);

        // Filtramos por alias (si se ha especificado algún valor)
        if (alias != null) {
            Collection maquinesAlias = getHostAliasEntityDao().findHostByAlias(alias);
            HashSet h_maquinesAlias = new HashSet(maquinesAlias.size());
            for (Iterator it = maquinesAlias.iterator(); it.hasNext(); ) {
                HostEntity maqAlias = (HostEntity) it.next();
                h_maquinesAlias.add(maqAlias.getId());
            }
            // Nos quedamos sólo con las máquinas de la búsqueda que tengan el
            // alias indicado
            for (Iterator it = maquines.iterator(); it.hasNext(); ) {
                HostEntity maq = (HostEntity) it.next();
                if (!h_maquinesAlias.contains(maq.getId())) it.remove();
            }
        }
        
        // Check results list lenght
        if (maquines.size() > limitResults)
        {
        	return getHostEntityDao().toHostList(maquines).subList(0, limitResults);
        }
        
        return getHostEntityDao().toHostList(maquines);
    }

    private void auditaVNC(SessionEntity sessio, String accio) {
        String codiUsuari = Security.getCurrentAccount();
        Audit auditoria = new Audit();

        auditoria.setAuthor(codiUsuari); // usuari auditador
        auditoria.setUser(sessio.getUser().getUserName()); // usuari auditat
        auditoria.setAction(accio);
        auditoria.setHost(sessio.getHost().getName());

        auditoria.setObject("VNC"); //$NON-NLS-1$

        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    private Long internalGetAccessLevel(String nomMaquina, String codiXarxa)
            throws InternalErrorException {
        // Si té l'autorització, no es miren els ACL
        if (AutoritzacionsUsuari.canSupportAllNetworks_VNC())
            return new Long(SUPORT);

        String codiUsuari = Security.getCurrentUser();
        if (codiUsuari == null)
        	return new Long (SENSE_PERMISOS);
        
        Collection networkAuthorizations = findALLNetworkAuthorizationsByUserName(codiUsuari);
        return getAccessLevel(networkAuthorizations, nomMaquina, codiXarxa);
    }

    /**
     * Lanzar el VNC contra la sesión indicada. Se verificará que el usuario
     * esté autorizado a lanzar capturas en la red asociada
     * 
     * @param sessioId
     *            id de la sesión que se quiere capturar
     * @return true si el usuario acepta la captura de pantalla y teclado
     * @throws InternalErrorException
     *             error en la configuración del servidor
     * @throws es.caib.seycon.util.TimedOutException
     *             el usuario no ha respondido en el tiempo especificado
     * @throws java.io.IOException
     *             error en la comunicación con el puesto de trabajo
     */
    protected Boolean handleLaunchVNC(Long sessioId) throws java.io.IOException, TimedOutException,
            InternalErrorException {
    	return false;
    }

    protected Boolean handleHasAnyACLNetworks(String codiUsuari) throws Exception {
        return hasNetworkAuthorizations(codiUsuari,(String) null, new int[] { ADMINISTRACIO, CONSULTA,
                SUPORT });
    }

    @Override
    protected AdministratorAuthorizationToAccessHost handleRevokeAdministratorAccessHost(AdministratorAuthorizationToAccessHost autoritzacioAccesComAdministrador) throws Exception {
        // Aquest mètode ja és restringit pel jboss (host:admin:query)

        // 1) canviem la data de l'autorització a ara mateix
        Calendar araMateix = Calendar.getInstance();

        // Comprovem que no estiga caducada
        if (autoritzacioAccesComAdministrador.getAuthorizationAccessExpirationDate().getTimeInMillis() < araMateix.getTimeInMillis()) {
            throw new SeyconException(Messages.getString("NetworkServiceImpl.AuthorizacionExpired")); //$NON-NLS-1$
        }

        autoritzacioAccesComAdministrador.setAuthorizationAccessExpirationDate(araMateix);
        HostAdminEntity entity = getHostAdminEntityDao().administratorAuthorizationToAccessHostToEntity(autoritzacioAccesComAdministrador);
        // Actualitzem les dades
        getHostAdminEntityDao().update(entity);

        // Auditem el canvi
        // Indiquem com a M l'acció (modificació)
        auditaPeticioAdministrarHost(autoritzacioAccesComAdministrador.getHostName(), autoritzacioAccesComAdministrador.getUserCode(), "R"); //$NON-NLS-1$

        return getHostAdminEntityDao().toAdministratorAuthorizationToAccessHost(entity);
    }

    private void auditaPeticioAdministrarHost(String maquina, String codiUsuari, String accio)
            throws Exception {

        String codiUsuariCanvi = Security.getCurrentAccount();
        // Fem un nestedlogin per obtindre autorització per fer auditoria

        Audit auditoria = new Audit();
        auditoria.setAction(accio); // Aprovat / Rebujat /
        auditoria.setHost(maquina);
        auditoria.setAuthor(codiUsuariCanvi);
        auditoria.setUser(codiUsuari);
        auditoria.setObject("SC_ADMMAQ"); //$NON-NLS-1$

        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    private void auditaCanviDadesAdministradorHost(String maquina) throws Exception {

        String codiUsuariCanvi = Security.getCurrentAccount(); //$NON-NLS-1$
        // Fem un nestedlogin per obtindre autorització per fer auditoria

        Audit auditoria = new Audit();
        auditoria.setAction("U"); //$NON-NLS-1$
        auditoria.setHost(maquina);
        auditoria.setAuthor(codiUsuariCanvi);
        auditoria.setObject("SC_ADMMAQ"); //$NON-NLS-1$

        AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
        getAuditEntityDao().create(auditoriaEntity);
    }

    @Override
    protected void handleSetAdministratorPassword(String nomMaquina, String adminUser, String adminPass) throws Exception {
        // Añadimos auditoría de la petición
        auditaCanviDadesAdministradorHost(nomMaquina); // AUDITORIA

        HostEntity host = getHostEntityDao().findByName(nomMaquina);

        host.setAdministratorUser(adminUser);
        host.setAdministratorPassword(new Password(adminPass).toString());
        host.setAdministratorPasswordDate(new Date());
        getHostEntityDao().update(host);
    }

    @Override
    protected Host handleFindHostBySerialNumber(String serialNumber) throws Exception {
        HostEntity maquina = getHostEntityDao().findBySerialNumber(serialNumber);
        if (maquina == null)
            return null;
        else
            return getHostEntityDao().toHost(maquina);
    }

    protected void createHostTask(String host) {
        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(java.lang.System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_HOST);
        tasque.setHost(host);
        getTaskEntityDao().create(tasque);
    }
    
    @Override
    protected Host handleRegisterDynamicIP(String nomMaquina, String ip, String serialNumber) throws es.caib.seycon.ng.exception.UnknownHostException, UnknownNetworkException, InternalErrorException {
        boolean anyChange = false;
        // First. Test if this IP belongs to anybody else
        // Second. Test if this name belongs to anybody else
        // Found a host with no serial number => Bind it
        HostEntity maquina = null;
        HostEntity old = getHostEntityDao().findByName(nomMaquina);
        if (old == null) {
            // Nothing to do
        } else if (old.getSerialNumber() == null || old.getSerialNumber().trim().isEmpty()) {
            // Replace unused host
            maquina = old;
            maquina.setSerialNumber(serialNumber);
            if (old.getDynamicIP().booleanValue())
            	maquina.setHostIP(ip);
            maquina.setLastSeen(new Date());
            getHostEntityDao().update(maquina);
			createHostTask(maquina.getName());
        } else if (serialNumber.equals(old.getSerialNumber())) {
            // Found host entry
            maquina = old;
            maquina.setLastSeen(new Date());
            getHostEntityDao().update(maquina);
			createHostTask(maquina.getName());
        } else if (old.getDynamicIP().booleanValue() ) { // Serial number has changed => Register a new host
            // Autodelete
            old.setDeleted(true);
            getHostEntityDao().update(old);
			createHostTask(old.getName());
        } else {
            log.warn(String.format(
                    Messages.getString("NetworkServiceImpl.HostsCollisionMessage"), //$NON-NLS-1$
                    nomMaquina, nomMaquina, ip, serialNumber));
            throw new UnknownHostException(nomMaquina);
        }
        // Third. Test if this serial is already used (with another name)
        if (maquina == null) {
            maquina = getHostEntityDao().findBySerialNumber(serialNumber);
            if (maquina != null && !nomMaquina.equals(maquina.getName())) {
            	anyChange = true;
                maquina.setName(nomMaquina);
            }
        }
        
        if (maquina == null) {
        	try {
                InetAddress addr = InetAddress.getByName(ip);
                NetworkEntity x = guessNetwork(addr.getAddress());
                if (x == null)
                {
                	String msg = String.format(Messages.getString("NetworkServiceImpl.RequestUnmanagedIP"), nomMaquina, ip); //$NON-NLS-1$ 
                	log.warn(msg);
                    throw new UnknownNetworkException(msg);
                }
                maquina = getHostEntityDao().newHostEntity();
                maquina.setHostIP(ip);
                maquina.setMail("N"); //$NON-NLS-1$
                maquina.setDeleted(false);
                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
                maquina.setDescription(Messages.getString("NetworkServiceImpl.AutocreatedMessage") + " " + df.format(new Date())); //$NON-NLS-1$
                maquina.setDynamicIP(new Boolean(true));
                maquina.setName(nomMaquina);
                maquina.setFolders("N"); //$NON-NLS-1$
                maquina.setSerialNumber(serialNumber);
                maquina.setPrintersServer("N"); //$NON-NLS-1$
                maquina.setOperatingSystem(getOsTypeEntityDao().findOSTypeByName("ALT")); //$NON-NLS-1$
                maquina.setNetwork(x);
                getHostEntityDao().create(maquina);
    			createHostTask(maquina.getName());
        	} catch (java.net.UnknownHostException e) {
            	String msg = String.format(Messages.getString("NetworkServiceImpl.RequestUnmanagedIP"), nomMaquina, "??"); //$NON-NLS-1$ //$NON-NLS-2$ 
            	log.warn(msg);
                throw new UnknownNetworkException(msg); 
        	}
        }

        if (!ip.equals(maquina.getHostIP()) || maquina.getNetwork() == null || !Boolean.FALSE.equals(maquina.getDeleted())) {
        	try {
                InetAddress addr = InetAddress.getByName(ip);
                NetworkEntity x = guessNetwork(addr.getAddress());
                if (x != null) {
                	if (x.isDchpSupport()) {
                		log.info("Register host "+maquina.getName()+" address "+ip);
                		anyChange = true;
                		maquina.setDeleted(Boolean.FALSE);
    	                maquina.setHostIP(ip);
    	                maquina.setNetwork(x);
                	} else {
                        throw new UnknownNetworkException(String.format(Messages.getString("NetworkServiceImpl.RequestWithoutDHCP"), nomMaquina, ip, x.getName()));
                	}
                } else {
                    throw new UnknownNetworkException(String.format(
                            Messages.getString("NetworkServiceImpl.RequestUnmanagedIP"), nomMaquina, ip)); //$NON-NLS-1$
                }
        	} catch (java.net.UnknownHostException e) {
            	String msg = String.format(Messages.getString("NetworkServiceImpl.RequestUnmanagedIP"), nomMaquina, "??"); //$NON-NLS-1$ //$NON-NLS-2$ 
            	log.warn(msg);
                throw new UnknownNetworkException(msg); 
        	}
        }
        if (anyChange || 
        		maquina.getLastSeen() == null ||
        		java.lang.System.currentTimeMillis() - maquina.getLastSeen().getTime() > 8 * 60L * 60L * 1000L) // each 8 hours update last seen
        {
        	maquina.setLastSeen(new Date());
        	getHostEntityDao().update(maquina);
			createHostTask(maquina.getName());
        }

        return getHostEntityDao().toHost(maquina);
    }

    private NetworkEntity guessNetwork(byte[] b) throws InternalErrorException {
        NetworkEntityDao dao = getNetworkEntityDao();
        NetworkEntity xarxa = null;
        for (int bc = b.length - 1; xarxa == null && bc >= 0; bc--) {
            byte mascara = (byte) 255;
            for (int bits = 0; xarxa == null && bits < 8; bits++) {
                mascara = (byte) (mascara << 1);
                b[bc] = (byte) (b[bc] & mascara);
                InetAddress addr2;
                try {
                    addr2 = InetAddress.getByAddress(b);
                    String addrText = addr2.getHostAddress();
                    xarxa = dao.findByAddress(addrText);
                } catch (java.net.UnknownHostException e) {
                    throw new InternalErrorException("Unable to parse address "+e.getMessage());
                }
            }
        }
        if (xarxa == null)
        {
        	String defaultNetwork = ConfigurationCache.getProperty("soffid.network.internet"); //$NON-NLS-1$
        	if (defaultNetwork == null)
        		defaultNetwork = "internet";
        	if (! "disabled".equals(defaultNetwork))
        	{
        		xarxa = dao.findByName(defaultNetwork);
        		if (xarxa == null)
        		{
        			xarxa = dao.newNetworkEntity();
        			xarxa.setName(defaultNetwork);
        			xarxa.setAddress("0.0.0.0"); //$NON-NLS-1$
        			xarxa.setMask("255.255.255.255"); //$NON-NLS-1$
        			xarxa.setDchpSupport(true);
        			xarxa.setDescription("Autocreated network for unknown IP adresses"); //$NON-NLS-1$
        			xarxa.setNormalized("N"); //$NON-NLS-1$
        			dao.create(xarxa);
        		}
        	}
       	}
        return xarxa;
    }

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.XarxaServiceBase#handleFindAllOSType()
	 */
	@Override
    protected List<OsType> handleFindAllOSTypes() throws Exception {
		OsTypeEntityDao entity = getOsTypeEntityDao();
		
		return getOsTypeEntityDao().toOsTypeList(entity.loadAll());
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.XarxaServiceBase#handleFindOSTypeById(java.lang.Long)
	 */
	@Override
	protected OsType handleFindOSTypeById (Long osId) throws Exception
	{
		OsTypeEntityDao entityDAO = getOsTypeEntityDao();
		
		return getOsTypeEntityDao().toOsType(entityDAO.load(osId));
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.XarxaServiceBase#handleCreateOSType(es.caib.seycon.ng.comu.OsType)
	 */
	@Override
	protected OsType handleCreate (OsType osType) throws Exception
	{
		OsTypeEntity entity = getOsTypeEntityDao().osTypeToEntity(osType);
		getOsTypeEntityDao().create(entity);
		osType.setId(entity.getId());
		
		return getOsTypeEntityDao().toOsType(entity);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.XarxaServiceBase#handleDeleteOSType(es.caib.seycon.ng.comu.OsType)
	 */
	@Override
	protected void handleDelete (OsType osType) throws Exception
	{
		OsTypeEntity osTypeEntity = getOsTypeEntityDao().osTypeToEntity(osType);
		if(!osTypeEntity.getOperatingSystemHost().isEmpty())
			throw new SeyconException(String.format(Messages.getString("NetworkServiceImpl.IntegrityViolationMachines"), osTypeEntity.getName())); //$NON-NLS-1$
		getOsTypeEntityDao().remove(osTypeEntity);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.XarxaServiceBase#handleUpdateOSType(es.caib.seycon.ng.comu.OsType)
	 */
	@Override
	protected void handleUpdate (OsType osType) throws Exception
	{
		OsTypeEntity entity = getOsTypeEntityDao().osTypeToEntity(osType);
		
		getOsTypeEntityDao().update(entity);
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.XarxaServiceBase#handleFindOSTypeByName(java.lang.String)
	 */
	@Override
	protected OsType handleFindOSTypeByName (String osName) throws Exception
	{
		OsTypeEntity osTypeEntity = getOsTypeEntityDao().findOSTypeByName(osName);
		
		if (osTypeEntity != null)
		{
			return getOsTypeEntityDao().toOsType(osTypeEntity);
		}
		
		else
		{
			return null;
		}
	}

	@Override
	protected Network handleFindNetworkByIpAddress(String ipAdress) throws Exception {
        InetAddress addr = InetAddress.getByName(ipAdress);
        NetworkEntity x = guessNetwork(addr.getAddress());
        if (x == null)
        	return null;
        else
        	return getNetworkEntityDao().toNetwork(x);
	}

	@Override
	protected AsyncList<Host> handleFindHostByTextAndJsonQueryAsync(String text, String jsonQuery) throws Exception {
		final AsyncList<Host> result = new AsyncList<Host>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindHostByTextAndJsonQuery(text, jsonQuery, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	@Override
	protected AsyncList<Network> handleFindNetworkByTextAndJsonQueryAsync(final String text, final String jsonQuery)
			throws Exception {
		final AsyncList<Network> result = new AsyncList<Network>();
		getAsyncRunnerService().run(new Runnable() {
			@Override
			public void run() {
				try {
					doFindNetworkByTextAndJsonQuery(text, jsonQuery, null, null, result);
				} catch (Throwable e) {
					throw new RuntimeException(e);
				}				
			}
		}, result);

		return result;
	}

	private PagedResult<Network> doFindNetworkByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			List<Network> result) throws TokenMgrError, Exception {
		final NetworkEntityDao dao = getNetworkEntityDao();
		ScimHelper h = new ScimHelper(Network.class);
		h.setPrimaryAttributes(new String[] { "name", "description", "address"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");

		final boolean any = AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canSupportAllNetworks_VNC();
		final List<String> networks = new LinkedList<>();
        if (!any) {
	        String codiUsuari = Security.getCurrentUser();
	        if (codiUsuari != null)
	        {
	        	networks.addAll( getCodiXarxesAmbAcces(Security.getCurrentUser()) );
	        }
        }

		h.setGenerator((entity) -> {
			NetworkEntity ne = (NetworkEntity) entity;
			if (any || networks.contains( ne.getName()))
				return dao.toNetwork((NetworkEntity) entity);
			else 
				return null;
		}); 
		h.search(text, jsonQuery, (Collection) result); 
		PagedResult<Network> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}
	
	private PagedResult<Host> doFindHostByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize,
			List<Host> result) throws UnsupportedEncodingException, ClassNotFoundException, InternalErrorException, EvalException, JSONException, ParseException, TokenMgrError {
		final HostEntityDao dao = getHostEntityDao();
		ScimHelper h = new ScimHelper(Host.class);
		h.setPrimaryAttributes(new String[] { "name", "description", "ip"});
		CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
		config.setFirstResult(start);
		config.setMaximumResultSize(pageSize);
		h.setConfig(config);
		h.setTenantFilter("tenant.id");
		h.setGenerator((entity) -> {
			return dao.toHost((HostEntity) entity);
		}); 
		h.search(text, jsonQuery, (Collection) result); 
		PagedResult<Host> pr = new PagedResult<>();
		pr.setStartIndex(start);
		pr.setItemsPerPage(pageSize);
		pr.setTotalResults(h.count());
		pr.setResources(result);
		return pr;
	}

	@Override
	protected PagedResult<Host> handleFindHostByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize) throws Exception {
		final LinkedList<Host> result = new LinkedList<Host>();
		return doFindHostByTextAndJsonQuery(text, jsonQuery, start, pageSize, result);
	}

	@Override
	protected PagedResult<Network> handleFindNetworkByTextAndJsonQuery(String text, String jsonQuery,
			Integer start, Integer pageSize) throws Exception {
		final LinkedList<Network> result = new LinkedList<Network>();
		return doFindNetworkByTextAndJsonQuery(text, jsonQuery, start, pageSize, result);
	}


    private boolean hasNetworkAuthorizations(UserEntity userEntity, HostEntity host,
            int autoritzacions[]) throws InternalErrorException, UnknownUserException {
        Collection<RoleGrant> grants = null;
        Collection<Group> groups  = null;
        for (NetworkAuthorizationEntity auth: host.getNetwork().getAuthorizations()) {
        	if (arrayContains(autoritzacions, auth.getLevel().intValue()) &&
        			teAcces(host.getName(), auth.getHostsName())) {
        		if (auth.getUser() == userEntity )
        			return true;
        		if (auth.getRole() != null) {
        			if (grants == null) grants = getApplicationService().findEffectiveRoleGrantByUser(userEntity.getId());
        			for (RoleGrant grant: grants) {
        				if (grant.getRoleId().equals(auth.getRole().getId()))
        					return true;
        			}
        		}
        		if (auth.getGroup() == null) {
        			if (groups == null) groups = getUserService().getUserGroupsHierarchy(userEntity.getId());
        			for (Group group: groups) {
        				if (group.getId().equals(auth.getGroup().getId()))
        					return true;
        			}
        		}
        	}
        }
        return false; // Not found
    }

    private boolean arrayContains(int[] autoritzacions, int intValue) {
    	for (int a: autoritzacions)
    		if (a == intValue)
    			return true;
    	return false;
	}

	@Override
	protected boolean handleCanLogin(String user, String host) throws Exception {
        HostEntity maquinaEntity = getHostEntityDao().findByName(host);
        if (maquinaEntity == null)  {
        	for (HostEntity hostEntity: getHostEntityDao().findByIP(host)) {
        		if ( ! Boolean.TRUE.equals( hostEntity.getDeleted()))
        			maquinaEntity = hostEntity;
        	}
        }
        if (maquinaEntity == null)
        	return false;
        
        if (! Boolean.TRUE.equals(maquinaEntity.getNetwork().getLoginRestriction() ))
        	return true;
        
    	if (user == null)
    		return false;
    	
        UserEntity userEntity = getUserEntityDao().findByUserName(user);
        if (userEntity == null)
        	return false;

        return hasNetworkAuthorizations(userEntity, maquinaEntity, new int[] { LOGIN, SUPORT });
	}
	
	private void updateHostAttributes (Host app, HostEntity entity) throws InternalErrorException
	{
		new HostAttributePersister().updateAttributes(app.getAttributes(), entity);
	}

	class HostAttributePersister extends AttributePersister<HostEntity,HostAttributeEntity> {
		@Override
		protected List<HostAttributeEntity> findAttributeEntityByNameAndValue(MetaDataEntity m, String v) {
			return getHostAttributeEntityDao().findByNameAndValue(m.getName(), v);
		}

		@Override
		protected void updateEntity(HostEntity entity) {
			getHostEntityDao().update(entity);
		}

		@Override
		protected String getMetadataScope() {
			return Host.class.getName();
		}

		@Override
		protected Collection<HostAttributeEntity> getEntityAttributes(HostEntity entity) {
			return entity.getAttributes();
		}

		@Override
		protected HostAttributeEntity createNewAttribute(HostEntity entity, MetaDataEntity metadata, Object value) {
			HostAttributeEntity aae = getHostAttributeEntityDao().newHostAttributeEntity();
			aae.setHost(entity);
			aae.setMetadata(metadata);
			aae.setObjectValue(value);
			getHostAttributeEntityDao().create(aae);
			return aae;
		}

		@Override
		protected HostAttributeEntity findAttributeEntity(LinkedList<HostAttributeEntity> entities, String key,
				Object o) {
			for (HostAttributeEntity aae: entities)
			{
				if (aae.getMetadata().getName().equals(key))
				{
					if (aae.getObjectValue() != null && aae.getObjectValue().equals(o))
						return aae;
				}
			}
			return null;
		}

		@Override
		protected MetaDataEntityDao getMetaDataEntityDao() {
			return NetworkServiceImpl.this.getMetaDataEntityDao();
		}

		@Override
		protected AttributeValidationService getAttributeValidationService() {
			return NetworkServiceImpl.this.getAttributeValidationService();
		}

		@Override
		protected void removeAttributes(Collection<HostAttributeEntity> entities) {
			getHostAttributeEntityDao().remove(entities);
		}
		
	}
}
