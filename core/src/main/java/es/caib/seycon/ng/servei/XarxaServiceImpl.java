// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.impl.LogFactoryImpl;

import com.soffid.iam.reconcile.model.ReconcileAccountEntityDao;
import com.soffid.iam.reconcile.model.ReconcileAssignmentEntityDao;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.UnknownHostException;
import es.caib.seycon.ng.exception.UnknownNetworkException;
import es.caib.seycon.ng.comu.AliasMaquina;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.AutoritzacioAccesHostComAdministrador;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Identitat;
import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.comu.NetworkAuthorization;
import es.caib.seycon.ng.comu.OsType;
import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.Sessio;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.Xarxa;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.model.AliasMaquinaEntity;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.MaquinaEntity;
import es.caib.seycon.ng.model.OsTypeEntity;
import es.caib.seycon.ng.model.OsTypeEntityDao;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.SessioEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.XarxaACEntity;
import es.caib.seycon.ng.model.XarxaEntity;
import es.caib.seycon.ng.model.XarxaEntityDao;
import es.caib.seycon.ng.model.criteria.CriteriaSearchConfiguration;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.DateUtils;
import es.caib.seycon.ng.utils.Security;
import es.caib.seycon.util.TimedOutException;
import es.caib.seycon.util.TimedProcess;

/**
 * @see es.caib.seycon.ng.servei.XarxaService
 */
public class XarxaServiceImpl extends es.caib.seycon.ng.servei.XarxaServiceBase {

    private Log log;

    public XarxaServiceImpl() {
        log = LogFactoryImpl.getLog(getClass());
    }

    public static final int SENSE_PERMISOS = -1;
    public static final int CONSULTA = 0;
    public static final int SUPORT = 1;
    public static final int ADMINISTRACIO = 2;

    protected Boolean handleTeXarxaAdministrada() throws Exception {
        Usuari usuari = getUsuariService().getCurrentUsuari();
        Collection codisXarxa = getCodiXarxesAmbAccesAdministracio(usuari.getCodi());
        return new Boolean(codisXarxa.size() > 0);
    }

    protected Boolean handleEsXarxaAdministrada(String codiXarxa) throws Exception {
        Usuari usuari = getUsuariService().getCurrentUsuari();
        if (usuari == null)
        	return false;
        Collection codisXarxa = getCodiXarxesAmbAccesAdministracio(usuari.getCodi());
        for (Iterator iterator = codisXarxa.iterator(); iterator.hasNext();) {
            String currentCodiXarxa = (String) iterator.next();
            if (currentCodiXarxa.compareTo(codiXarxa) == 0) {
                return new Boolean(true);
            }
        }
        return new Boolean(false);
    }

    protected Collection<Sessio> handleFindSessionsByNomMaquina(String nomMaquina) {
        Collection<SessioEntity> sessions = getSessioEntityDao().findSessionsByCriteri(null, null,
                nomMaquina, null);
        if (sessions != null) {
            return getSessioEntityDao().toSessioList(sessions);
        }
        return new Vector();
    }

    protected Long handleFindNivellAccesByNomMaquinaAndCodiXarxa(String nomMaquina, String codiXarxa)
            throws Exception {
        String codiUsuari = Security.getCurrentUser();
        if (codiUsuari == null)
        	return new Long(SENSE_PERMISOS);
        
        Collection networkAuthorizations = findALLNetworkAuthorizationsByCodiUsuari(codiUsuari);
        
        
        Long level = getAccessLevel(networkAuthorizations, nomMaquina, codiXarxa);
        if (level == null || level.longValue() < SUPORT)
        {
        	Date longAgo = new Date(0);
            for (AutoritzacioAccesHostComAdministradorEntity aut:
            	getAutoritzacioAccesHostComAdministradorEntityDao().findByNomHostIDataPeticio(nomMaquina, longAgo, new Date(), longAgo))
            {
            	if (aut.getUsuari().getCodi().equals (codiUsuari) )
            		level = new Long(SUPORT);
            }
        }
        return level;
    }

    protected Usuari handleFindUsuariByIdSessio(java.lang.Long idSessio) throws Exception {
        SessioEntity sessioEntity = getSessioEntityDao().findById(idSessio);
        if (sessioEntity != null) {
            UsuariEntity usuariEntity = sessioEntity.getUsuari();
            Usuari usuari = getUsuariEntityDao().toUsuari(usuariEntity);
            return usuari;
        } else {
            throw new SeyconException(String.format(Messages.getString("XarxaServiceImpl.SessionIDNotFound"), //$NON-NLS-1$
                    idSessio.longValue()));
        }
    }

    protected Boolean handleTeAccesAXarxa(String codiUsuari, String codiXarxa) throws Exception {
        Collection codiXarxes = getCodiXarxesAmbAcces(codiUsuari);
        return new Boolean(codiXarxes.contains(codiXarxa));
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#getXarxes()
     */
    protected java.util.Collection<Xarxa> handleGetXarxes() throws java.lang.Exception {
        return getXarxaEntityDao().toXarxaList(getXarxaEntityDao().loadAll());
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#create(es.caib.seycon.ng.comu.Xarxa)
     */
    protected es.caib.seycon.ng.comu.Xarxa handleCreate(es.caib.seycon.ng.comu.Xarxa xarxa)
            throws java.lang.Exception {
        // network:create [SENSE_DOMINI]
        if (AutoritzacionsUsuari.canCreateAllNetworks()) {
        	Parameter parameters[] = new Parameter[]{new Parameter("code", xarxa.getCodi())}; //$NON-NLS-1$
        	Parameter param[] = new Parameter[]{new Parameter("adrip", xarxa.getAdreca())}; //$NON-NLS-1$
    		Collection networksSameCode = getXarxaEntityDao().query("select codi from es.caib.seycon.ng.model.XarxaEntity where codi=:code", parameters); //$NON-NLS-1$
    		if(networksSameCode != null && !networksSameCode.isEmpty())
    			throw new SeyconException(String.format(Messages.getString("XarxaServiceImpl.CodeNetworkExists"),  //$NON-NLS-1$
    							xarxa.getCodi())); 
    		networksSameCode = getXarxaEntityDao().query("select adreca from es.caib.seycon.ng.model.XarxaEntity where adreca=:adrip", param); //$NON-NLS-1$
    		if(networksSameCode != null && !networksSameCode.isEmpty())
    			throw new SeyconException(String.format(Messages.getString("XarxaServiceImpl.IpNetworkExists"),  //$NON-NLS-1$
    							xarxa.getAdreca())); 
            XarxaEntity entity = getXarxaEntityDao().xarxaToEntity(xarxa);
            getXarxaEntityDao().create(entity);
            xarxa.setId(entity.getId());
            return getXarxaEntityDao().toXarxa(entity);
        }
        throw new SeyconException(Messages.getString("XarxaServiceImpl.NotAuthorizedMakeNet")); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#update(es.caib.seycon.ng.comu.Xarxa)
     */
    protected void handleUpdate(es.caib.seycon.ng.comu.Xarxa xarxa) throws java.lang.Exception {
        if (AutoritzacionsUsuari.canUpdateAllNetworks()
                || hasNetworkAuthorizations(Security.getCurrentUser(), xarxa.getCodi(),
                        new int[] { ADMINISTRACIO })) {

            @SuppressWarnings("rawtypes")
            Collection maquines = findMaquinaByFiltre(null, null, null, null, null, null, null,
                    null, null, xarxa.getCodi(), null, new Boolean(false));
            for (Iterator iterator = maquines.iterator(); iterator.hasNext();) {
                Maquina maquina = (Maquina) (iterator.next());
                if (!maquinaCompatibleAmbXarxa(maquina.getAdreca(), xarxa.getAdreca(),
                        xarxa.getMascara())) {
                    throw new SeyconException(String.format(
                            Messages.getString("XarxaServiceImpl.IncompatibleIPMessage"),  //$NON-NLS-1$
                            xarxa.getAdreca(), xarxa.getMascara(), maquina.getAdreca()));
                }
            }

            getXarxaEntityDao().update(getXarxaEntityDao().xarxaToEntity(xarxa));
        } else {
            throw new SeyconException(Messages.getString("XarxaServiceImpl.NotAuthorizedUpdateNet")); //$NON-NLS-1$
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#delete(es.caib.seycon.ng.comu.Xarxa)
     */
    protected void handleDelete(es.caib.seycon.ng.comu.Xarxa xarxa) throws java.lang.Exception {
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
        	XarxaEntity xarxaEntity = getXarxaEntityDao().xarxaToEntity(xarxa);
        	if(!xarxaEntity.getMaquines().isEmpty() || xarxaEntity.getAutoritzacions().isEmpty())
        		throw new SeyconException(String.format(Messages.getString("XarxaServiceImpl.IntegrityViolationHosts"),  //$NON-NLS-1$
        						new Object[]{xarxaEntity.getCodi()}));
            getXarxaEntityDao().remove(xarxaEntity);
        } else {
            throw new SeyconException(Messages.getString("XarxaServiceImpl.NotAuthorizedDeleteNet")); //$NON-NLS-1$
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#revoke(es.caib.seycon.ng.comu.XarxaAC)
     */
    protected void handleRevoke(es.caib.seycon.ng.comu.NetworkAuthorization xarxaAC)
            throws java.lang.Exception {
        // Abans tenia esAdministrador()
        if (AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()
                || hasNetworkAuthorizations(Security.getCurrentUser(),
                        xarxaAC.getCodiXarxa(), new int[] { ADMINISTRACIO })) {
            XarxaACEntity entity = getXarxaACEntityDao().networkAuthorizationToEntity(xarxaAC);
            getXarxaACEntityDao().remove(entity);
        } else {
            throw new SeyconException(
                    Messages.getString("XarxaServiceImpl.NotAuthorizedDeleteAuthorizations")); //$NON-NLS-1$
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#getACL(es.caib.seycon.ng.comu.Xarxa)
     */
    protected java.util.Collection<NetworkAuthorization> handleGetACL(
            es.caib.seycon.ng.comu.Xarxa xarxa) throws java.lang.Exception {
        if (AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()
                || AutoritzacionsUsuari.canSupportAllNetworks_VNC()
                || hasNetworkAuthorizations(Security.getCurrentUser(), xarxa.getCodi(),
                        new int[] { ADMINISTRACIO, CONSULTA, SUPORT })) {
            XarxaEntity entity = getXarxaEntityDao().xarxaToEntity(xarxa);
            Collection<XarxaACEntity> acls = getXarxaACEntityDao().findByXarxa(entity);
            if (acls != null) {
                return getXarxaACEntityDao().toNetworkAuthorizationList(acls);
            }
            return new LinkedList<NetworkAuthorization>();
        }
        throw new SeyconException(
                Messages.getString("XarxaServiceImpl.NotAuthorizedViewListAccess")); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#findXarxaByCodi(String)
     */
    protected Xarxa handleFindXarxaByCodi(String codi) throws java.lang.Exception {
        XarxaEntity xarxaEntity = getXarxaEntityDao().findByCodi(codi);
        if (xarxaEntity != null) {
            Xarxa xarxa = getXarxaEntityDao().toXarxa(xarxaEntity);
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
    protected es.caib.seycon.ng.comu.Maquina handleCreate(es.caib.seycon.ng.comu.Maquina maquina)
            throws java.lang.Exception {
        if (AutoritzacionsUsuari.canCreateAllHosts() || maquinaPermesa(maquina, ADMINISTRACIO)) {
            MaquinaEntity entity = getMaquinaEntityDao().maquinaToEntity(maquina);
            entity.setDeleted(new Boolean(false));
            getMaquinaEntityDao().create(entity);
            maquina.setId(entity.getId());
            return getMaquinaEntityDao().toMaquina(entity);
        }
        throw new SeyconException(String.format(
                Messages.getString("XarxaServiceImpl.NotAuthorizedMakeMachine"), maquina.getNom())); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#update(es.caib.seycon.ng.comu.Maquina)
     */
    protected void handleUpdate(es.caib.seycon.ng.comu.Maquina maquina) throws java.lang.Exception {
        if (teAccesEscripturaMaquina(maquina)) {
            getMaquinaEntityDao().update(getMaquinaEntityDao().maquinaToEntity(maquina));
        } else {
            // Comprovem permís per actualitzar el SO de la màquina
            if (AutoritzacionsUsuari.canUpdateHostOS()) {
                // Fem el canvi del SO a la maquina de la base de dades
                // per verificar que només s'ha canviat el SO (jur jur)
                // Cerquem per ID de la màquina (ja existeix !!)
                Maquina maquinaTrobada = null;
                if (maquina.getId() != null)
                    maquinaTrobada = findMaquinaById(maquina.getId());
                else
                    maquinaTrobada = findMaquinaByNom(maquina.getNom());

                if (maquinaTrobada == null) {
                    throw new SeyconException(String.format(
                            Messages.getString("XarxaServiceImpl.MachineNotFound"), maquina.getNom())); //$NON-NLS-1$
                }
                // Fem els canvis permesos a la màquina original
                // perquè la comparació dóne OK (només permet aquestos canvis)
                // SO
                maquinaTrobada.setSistemaOperatiu(maquina.getSistemaOperatiu());
                // La marquem com a servidor d'impressores
                if (maquina.getServidorImpressores() != null)
                    maquinaTrobada.setServidorImpressores(maquina.getServidorImpressores());
                // I les comparem
                if (maquinesIguals(maquinaTrobada, maquina)) {
                    getMaquinaEntityDao().update(
                            getMaquinaEntityDao().maquinaToEntity(maquinaTrobada));
                } else {
                    throw new SeyconException(
                            String.format(
                                    Messages.getString("XarxaServiceImpl.OnlyChangeSOMachine"), //$NON-NLS-1$
                                    maquina.getNom()));
                }
            } else {
                throw new SeyconException(String.format(
                        Messages.getString("XarxaServiceImpl.NotAuthorizedToUpdateMachine"), //$NON-NLS-1$
                        maquina.getNom()));
            }
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#delete(es.caib.seycon.ng.comu.Maquina)
     */
    protected void handleDelete(es.caib.seycon.ng.comu.Maquina maquina)
		throws java.lang.Exception
	{
    	if (AutoritzacionsUsuari.canDeleteAllHosts() ||
			maquinaPermesa(maquina, ADMINISTRACIO))
    	{
    		CriteriaSearchConfiguration csc = new CriteriaSearchConfiguration();
    		csc.setMaximumResultSize(1);
    		
    		// Check access logs
        	if (getRegistreAccesEntityDao().query(
					"from es.caib.seycon.ng.model.RegistreAccesEntity rac " + //$NON-NLS-1$
					"where rac.servidor.id=:id or rac.client.id=:id", //$NON-NLS-1$
					new Parameter[] {new Parameter ("id", maquina.getId())}, //$NON-NLS-1$
					csc).isEmpty())
        	{
        		// Check associated printers
        		if (getImpressoraService().findImpressoresByCriteri(null, null,
						null, maquina.getNom()).isEmpty())
        		{
        			getMaquinaEntityDao().remove(getMaquinaEntityDao()
        				.maquinaToEntity(maquina));
        		}
        		
        		else
        		{
        			throw new InternalErrorException(
						Messages.getString("XarxaServiceImpl.UnableDeleteHostMessage")); //$NON-NLS-1$
        		}
        	}
        	else
        	{
        		MaquinaEntity entity = getMaquinaEntityDao().maquinaToEntity(maquina);
        		entity.setDeleted(true);
        		getMaquinaEntityDao().update(entity);
        	}
        }
    	else
    	{
            throw new SeyconException(Messages.getString("XarxaServiceImpl.NotAuthorizedToDeleteMachine")); //$NON-NLS-1$
        }
    }

    protected Collection<Maquina> handleGetServidorsCorreu() throws java.lang.Exception {
        return findMaquinaByFiltre(null, null, null, null, "S", null, null, null, null, null, null, //$NON-NLS-1$
                new Boolean(false));
    }

    protected Collection<Maquina> handleGetServidorsPerfil() throws java.lang.Exception {
        return findMaquinaByFiltre(null, null, null, null, null, "S", null, null, null, null, null, //$NON-NLS-1$
                new Boolean(false));
    }

    protected Collection<Maquina> handleGetServidorsHome() throws java.lang.Exception {
        return findMaquinaByFiltre(null, null, null, null, null, "S", null, null, null, null, null, //$NON-NLS-1$
                new Boolean(false));
    }

    protected Maquina handleFindMaquinaByNom(java.lang.String nom) throws java.lang.Exception {
        MaquinaEntity maquinaEntity = getMaquinaEntityDao().findByNom(nom);
        if (maquinaEntity != null) {
            Maquina maquina = getMaquinaEntityDao().toMaquina(maquinaEntity);
            if (this.teAccesLecturaMaquina(maquina)) {
                return maquina;
            }
            throw new SeyconException(String.format(
                    Messages.getString("XarxaServiceImpl.NotAuthorizedSearchMachine"), nom)); //$NON-NLS-1$
        }
        return null;
    }

    // Emprat des de xarxesllista.zul (parametres.zul) i xarxes.zul
    protected java.util.Collection<Xarxa> handleFindXarxaByFiltre(java.lang.String codi,
		java.lang.String adreca, java.lang.String descripcio,
		java.lang.String mascara, java.lang.String normalitzada,
		java.lang.String dhcp, String maquina)
		throws java.lang.Exception
	{
		Collection xarxes = localFindXarxaByFiltre(codi, adreca, descripcio,
			mascara, normalitzada, dhcp, maquina);
    	int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
    	
    	if (xarxes != null && xarxes.size() != 0)
    	{
    		Collection<Xarxa> xarxesTrobades = filtraXarxes(getXarxaEntityDao().toXarxaList(xarxes));
            Collection<Xarxa> res = filtraPerMaquina(xarxesTrobades, maquina);
            
			// Check maximum number of results
			if (res.size() > limitResults)
			{
				return new LinkedList<Xarxa>(res).subList(0, limitResults);
//				throw new SeyconException(
//					Messages.getString("XarxaServiceImpl.BigSearchResults")); //$NON-NLS-1$
			}
			
			return res;
		}
    	
    	return new LinkedList<Xarxa>();
	}

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#getMaquines()
     */
    protected java.util.Collection<Maquina> handleGetMaquines() throws java.lang.Exception {
        return getMaquinaEntityDao().toMaquinaList(getMaquinaEntityDao().loadAll());
    }

    /**
     * @see es.caib.seycon.ng.servei.XarxaService#findMaquinaByXarxa(es.caib.seycon.ng.comu.Xarxa)
     */
    @SuppressWarnings("rawtypes")
    protected java.util.Collection<Maquina> handleFindMaquinesByXarxa(
            es.caib.seycon.ng.comu.Xarxa xarxa) throws java.lang.Exception {
        Collection<Maquina> maquines = findMaquinaByFiltre(null, null, null, null, null, null,
                null, null, null, xarxa.getCodi(), null, new Boolean(false));
        return maquines;
    }

    protected Collection<Maquina> handleFindMaquinaByFiltreSenseRestriccions(String nom,
            String sistemaOperatiu, String adreca, String dhcp, String correu, String ofimatica,
            String alias, String mac, String descripcio, String xarxa, String codiUsuari,
            Boolean filtra) throws Exception {
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

        Collection<MaquinaEntity> maquines = null;

        LinkedList<Parameter> params = new LinkedList<Parameter>();
        // Realizamos la siguiente consulta (sin tener cuenta el alias)
        String query = "select distinct maquina from " //$NON-NLS-1$
                + " es.caib.seycon.ng.model.SessioEntity sessio " //$NON-NLS-1$
                + " right outer join sessio.maquina as maquina " //$NON-NLS-1$
                + " left outer join sessio.usuari as usuari" + //$NON-NLS-1$
                " where maquina.deleted = false "; //$NON-NLS-1$
        if (nom != null ) {
            query = query + "and maquina.nom like :nom "; //$NON-NLS-1$
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
            query = query + "and maquina.adreca like :adreca "; //$NON-NLS-1$
            params.add(new Parameter("adreca", adreca)); //$NON-NLS-1$
        }
        if (dhcp != null) {
            query = query + "and maquina.dhcp like :dhcp "; //$NON-NLS-1$
            params.add(new Parameter("adreca", adreca)); //$NON-NLS-1$
        }
        if (correu != null) {
            query = query + "and maquina.correu like :correu "; //$NON-NLS-1$
            params.add(new Parameter("correu", correu)); //$NON-NLS-1$
        }
        if (ofimatica != null) {
            query = query + "and maquina.ofimatica like :ofimatica) "; //$NON-NLS-1$
            params.add(new Parameter("ofimatica", ofimatica)); //$NON-NLS-1$
        }
        if (mac != null) {
            query = query + "and maquina.mac like :mac "; //$NON-NLS-1$
            params.add(new Parameter("mac", mac)); //$NON-NLS-1$
        }
        if (descripcio != null) {
            query = query + "and maquina.descripcio like :descripcio "; //$NON-NLS-1$
            params.add(new Parameter("descripcio", descripcio)); //$NON-NLS-1$
        }
        if (xarxa != null) {
            query = query + "and maquina.xarxa.codi like :xarxa "; //$NON-NLS-1$
            params.add(new Parameter("xarxa", xarxa)); //$NON-NLS-1$
        }
        if (codiUsuari != null) {
            query = query + "and usuari.codi like :codiUsuari "; //$NON-NLS-1$
            params.add(new Parameter("codiUsuari", codiUsuari)); //$NON-NLS-1$
        }
        query = query + "order by maquina.nom "; //$NON-NLS-1$

        maquines = getMaquinaEntityDao().query(query, params.toArray(new Parameter[0]));

        // Filtramos por alias (si se ha especificado algún valor)
        if (alias != null) {
            Collection<AliasMaquinaEntity> maquinesAlias = getAliasMaquinaEntityDao()
                    .findMaquinaByAlias(alias);
            HashSet<Long> h_maquinesAlias = new HashSet(maquinesAlias.size());
            for (Iterator it = maquinesAlias.iterator(); it.hasNext();) {
                MaquinaEntity maqAlias = (MaquinaEntity) it.next();
                h_maquinesAlias.add(maqAlias.getId());
            }
            // Nos quedamos sólo con las máquinas de la búsqueda que tengan el
            // alias indicado
            for (Iterator it = maquines.iterator(); it.hasNext();) {
                MaquinaEntity maq = (MaquinaEntity) it.next();
                if (!h_maquinesAlias.contains(maq.getId()))
                    it.remove(); // Lo eliminamos (no tiene el alias buscado)
            }

        }
        return getMaquinaEntityDao().toMaquinaList(maquines);
    }

    // Emprar des de maquines.zul per cercar
    protected Collection<Maquina> handleFindMaquinaByFiltre(String nom,
		String sistemaOperatiu, String adreca, String dhcp, String correu,
		String ofimatica, String alias, String mac, String descripcio,
		String xarxa, String codiUsuari, Boolean filtra) throws Exception
	{
		Collection<Maquina> maquines = null;
    	int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
    	
    	// Cridem al "nou" mètode i després restringim
    	// NOTA: El mètode ens torna Maquina (VO), si llevem la crida
    	// després hem de transformar les entity a VO
    	maquines = handleFindMaquinaByFiltreSenseRestriccions(nom,
			sistemaOperatiu, adreca, dhcp, correu, ofimatica, alias, mac,
			descripcio, xarxa, codiUsuari, filtra);
    	
    	if (maquines != null)
    	{
    		if ((filtra != null) && filtra.booleanValue() &&
				(maquines.size() > limitResults))
			{
    			maquines = new LinkedList<Maquina>(maquines)
					.subList(0, limitResults);
//                throw new SeyconException(
//                        Messages.getString("XarxaServiceImpl.BigSearchResults")); //$NON-NLS-1$
			}
    		
    		// getMaquinaEntityDao().toMaquinaCollection(maquines);
    		maquines = filtraMaquines(maquines, CONSULTA);
    		return new LinkedList<Maquina>(maquines);
		}
    	
    	return new LinkedList<Maquina>();
	}

    protected NetworkAuthorization handleCreate(NetworkAuthorization accessList) throws Exception {
    	String u = Security.getCurrentUser();
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()
                || hasNetworkAuthorizations(u,
                        accessList.getCodiXarxa(), new int[] { ADMINISTRACIO })) {
            XarxaACEntity entity = getXarxaACEntityDao().networkAuthorizationToEntity(accessList);
            if (entity.getUsuari() != null) {
                if (entity.getUsuari().getCodi().compareTo(u) == 0) {
                    throw new SeyconException(
                            Messages.getString("XarxaServiceImpl.NoAutoassignNetLists")); //$NON-NLS-1$
                }
            }
            Identitat identity = accessList.getIdentitat();
            if(identity.getCodiIdentitat() != null && !identity.getCodiIdentitat().isEmpty())
            	getXarxaACEntityDao().create(entity);
            else
            	throw new SeyconException(String.format(Messages.getString("XarxaServiceImpl.IdentityValidation"), entity.getXarxa().getCodi())); //$NON-NLS-1$
            return getXarxaACEntityDao().toNetworkAuthorization(entity);
        }
        throw new SeyconException(Messages.getString("XarxaServiceImpl.NotAuthorizedAdminNet")); //$NON-NLS-1$
    }

    protected void handleDelete(NetworkAuthorization accessList) throws Exception {
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()
                || hasNetworkAuthorizations(Security.getCurrentUser(),
                        accessList.getCodiXarxa(), new int[] { ADMINISTRACIO })) {
            XarxaACEntity entity = getXarxaACEntityDao().networkAuthorizationToEntity(accessList);
            getXarxaACEntityDao().remove(entity);
        } else {
            throw new SeyconException(Messages.getString("XarxaServiceImpl.NoAdminNets")); //$NON-NLS-1$
        }
    }

    protected NetworkAuthorization handleUpdate(NetworkAuthorization accessList) throws Exception {
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()
                || hasNetworkAuthorizations(Security.getCurrentUser(),
                        accessList.getCodiXarxa(), new int[] { ADMINISTRACIO })) {
            XarxaACEntity entity = getXarxaACEntityDao().networkAuthorizationToEntity(accessList);
            getXarxaACEntityDao().update(entity);
            return getXarxaACEntityDao().toNetworkAuthorization(entity);
        }
        throw new SeyconException(Messages.getString("XarxaServiceImpl.NoAdminNets")); //$NON-NLS-1$
    }

    protected Collection<Identitat> handleFindIdentitatsByCodi(String codi) throws Exception {
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()) {
            if (codi != null && (codi.trim().compareTo("") == 0 || codi.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
                codi = null;
            }

            Collection<Identitat> identitats = new LinkedList<Identitat>();

            Collection<Usuari> usuaris = this.getUsuariService().findUsuariByCriteri(codi, null,
                    null, null, null, null, null, null, null, null, null, null, null, null, null,
                    null, null, null, new Boolean(false));
            if (usuaris != null && usuaris.size() > 0) {
                List<UsuariEntity> usuariEntities = getUsuariEntityDao()
                        .usuariToEntityList(usuaris);
                identitats = getUsuariEntityDao().toIdentitatList(usuariEntities);
            }

            Collection<RolEntity> rols = getRolEntityDao().findRolsByFiltre(codi, null, null, null,
                    null, null);
            if (rols != null) {
                identitats.addAll(getRolEntityDao().toIdentitatList(rols));
            }

            Collection<GrupEntity> grups = getGrupEntityDao().findByFiltre(codi, null, null, null,
                    null, null);
            if (grups != null) {
                identitats.addAll(getGrupEntityDao().toIdentitatList(grups));
            }

            return identitats;
        }
        throw new SeyconException(Messages.getString("XarxaServiceImpl.AdminNet")); //$NON-NLS-1$
    }

    protected Identitat handleFindIdentitatByCodi(String codi) throws Exception {
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()) {
            if (codi != null && (codi.trim().compareTo("") == 0 || codi.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
                return null;
            }

            UsuariEntity usuari = getUsuariEntityDao().findByCodi(codi);
            if (usuari != null) {
                Identitat identitat = getUsuariEntityDao().toIdentitat(usuari);
                return identitat;
            }

            RolEntity rol = null;
            String[] partsCodi = codi.split("@"); //$NON-NLS-1$
            String[] partsCodi2 = partsCodi[1].split(">"); //$NON-NLS-1$
            rol = getRolEntityDao().findByNomRolAndCodiAplicacioAndCodiDispatcher(partsCodi[0],
                    partsCodi2[1], partsCodi2[0]);
            if (rol != null) {
                Identitat identitat = getRolEntityDao().toIdentitat(rol);
                return identitat;
            }

            GrupEntity grup = getGrupEntityDao().findByCodi(codi);
            if (grup != null) {
                Identitat identitat = getGrupEntityDao().toIdentitat(grup);
                return identitat;
            }

            return null;
        }
        throw new SeyconException(Messages.getString("XarxaServiceImpl.NoAdminNets")); //$NON-NLS-1$
    }

    protected NetworkAuthorization handleFindNetworkAuthorizationByCodiXarxaAndCodiIdentitat(
            String codiXarxa, String codiIdentitat) throws Exception {
        // Si pot crear qualsevol xarxa o actualitzar qualsevol xarxa
        if (AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()) {
            XarxaACEntity xarxaACEntity = getXarxaACEntityDao().findByCodiXarxaAndCodiIdentiat(
                    codiXarxa, codiIdentitat);
            if (xarxaACEntity != null) {
                NetworkAuthorization networkAuthorization = getXarxaACEntityDao()
                        .toNetworkAuthorization(xarxaACEntity);
                return networkAuthorization;
            }
            return null;
        }
        throw new SeyconException(Messages.getString("XarxaServiceImpl.NoAdminNets")); //$NON-NLS-1$
    }

    /*
     * Obté les autoritzacions DIRECTES de Xarxa a aquest grup (non-Javadoc)
     * 
     * @see es.caib.seycon.ng.servei.XarxaServiceBase#
     * handleFindNetworkAuthorizationsByCodiGrup(java.lang.String)
     */
    protected Collection<NetworkAuthorization> handleFindNetworkAuthorizationsByCodiGrup(
            String codiGrup) throws Exception {
        Collection<XarxaACEntity> xarxesAC = getXarxaACEntityDao().findByCodiGrup(codiGrup);
        if (xarxesAC != null) {
            return getXarxaACEntityDao().toNetworkAuthorizationList(xarxesAC);
        }
        return new LinkedList<NetworkAuthorization>();
    }

    // Antic mètode getNetworkAuthorizations(codiUsuari)
    protected Collection<NetworkAuthorization> handleFindALLNetworkAuthorizationsByCodiUsuari(
            String codiUsuari) throws Exception {
        Collection<NetworkAuthorization> xarxes = new LinkedList();
        // acces list per codi d'usuari
        UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(codiUsuari);
        if (usuariEntity != null) {
            Collection<NetworkAuthorization> networkAuthorizations = findNetworkAuthorizationsByCodiUsuari(codiUsuari);
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                xarxes.add(newtworkAuthorization);
            }
        } else {
            throw new SeyconException(
                    String.format(Messages.getString("XarxaServiceImpl.UserNotFound"), codiUsuari)); //$NON-NLS-1$
        }
        // llistes d'acces per grups
        Collection<Grup> grups = getGrupService().findGrupsByCodiUsuari(codiUsuari);
        Iterator grupIterator = grups.iterator();
        while (grupIterator.hasNext()) {
            Grup grup = (Grup) grupIterator.next();
            Collection networkAuthorizations = findNetworkAuthorizationsByCodiGrup(grup.getCodi());
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                xarxes.add(newtworkAuthorization);
            }
        }
        // llistes d'acces per rols: filtra els rols
        Collection<Rol> rols = getAplicacioService().findRolsByCodiUsuari(codiUsuari);

        Iterator rolsIterator = rols.iterator();
        while (rolsIterator.hasNext()) {
            Rol rol = (Rol) rolsIterator.next();
            Collection networkAuthorizations = findNetworkAuthorizationsByRol(rol);
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                xarxes.add(newtworkAuthorization);
            }
        }
        return xarxes;
    }

    /*
     * Obté les autoritzacions DIRECTES de Xarxa a aquest usuari (non-Javadoc)
     * 
     * @see es.caib.seycon.ng.servei.XarxaServiceBase#
     * handleFindNetworkAuthorizationsByCodiUsuari(java.lang.String)
     */
    protected Collection<NetworkAuthorization> handleFindNetworkAuthorizationsByCodiUsuari(
            String codiUsuari) throws Exception {
        Collection<XarxaACEntity> xarxesAC = getXarxaACEntityDao().findByCodiUsuari(codiUsuari);
        if (xarxesAC != null) {
            return getXarxaACEntityDao().toNetworkAuthorizationList(xarxesAC);
        }
        return new Vector();
    }

    /*
     * Obté les autoritzacions DIRECTES de Xarxa a aquest ROL (non-Javadoc)
     * 
     * @see es.caib.seycon.ng.servei.XarxaServiceBase#
     * handleFindNetworkAuthorizationsByNomRol(java.lang.String)
     */
    protected Collection<NetworkAuthorization> handleFindNetworkAuthorizationsByNomRol(String nomRol)
            throws Exception {
        Collection<XarxaACEntity> xarxesAC = getXarxaACEntityDao().findByNomRol(nomRol);
        if (xarxesAC != null) {
            return getXarxaACEntityDao().toNetworkAuthorizationList(xarxesAC);
        }
        return new Vector();
    }

    protected String handleGetPrimeraIPLliure(String codiXarxa) throws Exception {
        XarxaEntity xarxa = getXarxaEntityDao().findByCodi(codiXarxa);
        if (xarxa != null) {
            String ipLliure = getXarxaEntityDao().getPrimeraIPLliure(xarxa.getAdreca(),
                    xarxa.getMascara());
            return ipLliure;
        }
        throw new SeyconException(String.format(Messages.getString("XarxaServiceImpl.NetNotFound"), codiXarxa)); //$NON-NLS-1$
    }

    protected Long handleGetIPsOcupades(String codiXarxa) throws Exception {
        XarxaEntity xarxaEntity = getXarxaEntityDao().findByCodi(codiXarxa);
        if (xarxaEntity != null) {
            Xarxa xarxa = getXarxaEntityDao().toXarxa(xarxaEntity);
            if (teAccesLecturaXarxa(xarxa)) {
                Long count = getXarxaEntityDao().getIPsOcupades(xarxaEntity.getAdreca(),
                        xarxaEntity.getMascara());
                return count;
            }
            throw new SeyconException(String.format(
                    Messages.getString("XarxaServiceImpl.NoReadNetPermission"), codiXarxa)); //$NON-NLS-1$
        }
        throw new SeyconException(String.format(Messages.getString("XarxaServiceImpl.NetNotFound"), codiXarxa)); //$NON-NLS-1$
    }

    protected Long handleGetIPsBuides(String codiXarxa) throws Exception {
        XarxaEntity xarxaEntity = getXarxaEntityDao().findByCodi(codiXarxa);
        if (xarxaEntity != null) {
            Xarxa xarxa = getXarxaEntityDao().toXarxa(xarxaEntity);
            if (teAccesLecturaXarxa(xarxa)) {
                Long count = getXarxaEntityDao().getIPsBuides(xarxaEntity.getAdreca(),
                        xarxaEntity.getMascara());
                return count;
            }
            throw new SeyconException(String.format(
                    Messages.getString("XarxaServiceImpl.NoReadNetPermission"), codiXarxa)); //$NON-NLS-1$
        }
        throw new SeyconException(String.format(Messages.getString("XarxaServiceImpl.NetNotFound"), codiXarxa)); //$NON-NLS-1$
    }

    protected Maquina handleFindMaquinaById(Long idMaquina) throws Exception {
        MaquinaEntity maquinaEntity = getMaquinaEntityDao().findById(idMaquina);
        if (maquinaEntity != null) {
            return getMaquinaEntityDao().toMaquina(maquinaEntity);
        }
        return null;
    }

    protected Maquina handleFindMaquinaByIp(String ip) throws Exception {
        Collection coll = getMaquinaEntityDao().query(
                "select maquina from es.caib.seycon.ng.model.MaquinaEntity as maquina " //$NON-NLS-1$
                        + "where maquina.adreca=:adreca", //$NON-NLS-1$
                new Parameter[] { new Parameter("adreca", ip) }); //$NON-NLS-1$
        if (coll == null || coll.size() == 0)
            return null;
        else {
            MaquinaEntity maq = (MaquinaEntity) coll.iterator().next();
            return getMaquinaEntityDao().toMaquina(maq);
        }
    }


    protected String[] handleGetTasques(String nomMaquina) throws Exception {
        String[] resultats = getMaquinaEntityDao().getTasques(nomMaquina);
        return resultats;
    }

    protected Collection<AliasMaquina> handleFindAliasByNomMaquina(String nomMaquina)
            throws Exception {
        Collection<AliasMaquinaEntity> alias = getAliasMaquinaEntityDao().findAliasByNomMaquina(
                nomMaquina);
        return getAliasMaquinaEntityDao().toAliasMaquinaList(alias);
    }

    protected AliasMaquina handleCreate(AliasMaquina aliasMaquina) throws Exception {
        AliasMaquinaEntity entity = getAliasMaquinaEntityDao().aliasMaquinaToEntity(aliasMaquina);
        Maquina maquina = getMaquinaEntityDao().toMaquina(entity.getMaquina());
        if (teAccesEscripturaMaquina(maquina)) {
            getAliasMaquinaEntityDao().create(entity);
            return getAliasMaquinaEntityDao().toAliasMaquina(entity);
        } else
            throw new SeyconException(
                    Messages.getString("XarxaServiceImpl.NoMakeAliasPermission")); //$NON-NLS-1$

    }

    protected void handleDelete(AliasMaquina aliasMaquina) throws Exception {
        AliasMaquinaEntity entity = getAliasMaquinaEntityDao().aliasMaquinaToEntity(aliasMaquina);
        Maquina maquina = getMaquinaEntityDao().toMaquina(entity.getMaquina());
        if (teAccesEscripturaMaquina(maquina)) {

            getAliasMaquinaEntityDao().remove(entity);
        } else
            throw new SeyconException(
                    Messages.getString("XarxaServiceImpl.NoDeleteAliasPermission")); //$NON-NLS-1$
    }

    protected void handleUpdate(AliasMaquina aliasMaquina) throws Exception {
        AliasMaquinaEntity entity = getAliasMaquinaEntityDao().aliasMaquinaToEntity(aliasMaquina);
        Maquina maquina = getMaquinaEntityDao().toMaquina(entity.getMaquina());
        if (teAccesEscripturaMaquina(maquina)) {
            getAliasMaquinaEntityDao().update(entity);
        } else
            throw new SeyconException(
                    Messages.getString("XarxaServiceImpl.NoUpdateAliasPermission")); //$NON-NLS-1$

    }

    protected AutoritzacioAccesHostComAdministrador handleCreate(
            AutoritzacioAccesHostComAdministrador autoritzacioAccesComAdministrador)
            throws Exception {
        AutoritzacioAccesHostComAdministradorEntity entity = getAutoritzacioAccesHostComAdministradorEntityDao()
                .autoritzacioAccesHostComAdministradorToEntity(autoritzacioAccesComAdministrador);
        getAutoritzacioAccesHostComAdministradorEntityDao().create(entity);
        // auditem la petició (ara es fa des del workflow)
        auditaPeticioAdministrarHost(autoritzacioAccesComAdministrador.getNomHost(), autoritzacioAccesComAdministrador.getCodiUsuari(),"C"); //$NON-NLS-1$
        return getAutoritzacioAccesHostComAdministradorEntityDao()
                .toAutoritzacioAccesHostComAdministrador(entity);
    }

    protected Collection<AutoritzacioAccesHostComAdministrador> handleFindAutoritzacionsAccesMaquinaComAdministradorByHostAndDataPeticio(
            String nomHost, String dataPeticio, String dataCaducitat) throws Exception {

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

        Collection<AutoritzacioAccesHostComAdministradorEntity> res = getAutoritzacioAccesHostComAdministradorEntityDao()
                .findByNomHostIDataPeticio(nomHost, d_dataPeticio, d_dataCaducitat,
                        DateUtils.nullDate);
        return getAutoritzacioAccesHostComAdministradorEntityDao()
                .toAutoritzacioAccesHostComAdministradorList(res);
    }

    protected String[] handleGetUsuariAndContrasenyaAdministradorHost(String nomMaquina)
            throws Exception {

        // Añadimos auditoría de la petición
        auditaSolicitudDadesAdministradorHost(nomMaquina); // AUDITORIA

        MaquinaEntity host = getMaquinaEntityDao().findByNom(nomMaquina);

        String dataEstabliment = DateUtils.dataToStringFull(host.getDataContrasenyaAdministrador());

        Password p = Password.decode(host.getContrasenyaAdministrador()); // desencriptem
                                                                          // el
                                                                          // passwd

        return new String[] { host.getUsuariAdministrador(), p.getPassword(), dataEstabliment };
    }

    private Collection<String> getCodiXarxesAmbAccesAdministracio(String codiUsuari)
            throws Exception {
        Collection<NetworkAuthorization> networkAuthorizations = findALLNetworkAuthorizationsByCodiUsuari(codiUsuari);
        Collection<String> codiXarxes = new LinkedList();
        Iterator iterator = networkAuthorizations.iterator();
        while (iterator.hasNext()) {
            NetworkAuthorization netAuth = (NetworkAuthorization) iterator.next();
            if (netAuth.getNivell() == ADMINISTRACIO) {
                codiXarxes.add(netAuth.getCodiXarxa());
            }
        }
        return codiXarxes;
    }

    private static boolean teAcces(String codiMaquina, String expresio) {
        Pattern pattern = Pattern.compile("^" + expresio + "$"); //$NON-NLS-1$ //$NON-NLS-2$
        Matcher matcher = pattern.matcher(codiMaquina);
        boolean matches = matcher.find();
        return matches;
    }

    private boolean teAccesLecturaXarxa(Xarxa xarxa) throws InternalErrorException {
        if (AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canCreateAllHosts()
                || AutoritzacionsUsuari.canUpdateAllHosts()
                || AutoritzacionsUsuari.canCreateAllNetworks()
                || AutoritzacionsUsuari.canUpdateAllNetworks()
                || AutoritzacionsUsuari.canSupportAllNetworks_VNC()
                || hasNetworkAuthorizations(Security.getCurrentUser(), xarxa.getCodi(),
                        new int[] { ADMINISTRACIO, CONSULTA, SUPORT })) {
            return true;
        }
        return false;
    }

    private boolean teAccesEscripturaMaquina(Maquina maquina) throws InternalErrorException {
        if (AutoritzacionsUsuari.canUpdateAllHosts() || AutoritzacionsUsuari.canCreateAllHosts()) {
            return true;
        }
        if (maquinaPermesa(maquina, ADMINISTRACIO)) {
            return true;
        }
        return false;
    }

    private boolean teAccesLecturaMaquina(Maquina maquina) throws InternalErrorException {
        if (AutoritzacionsUsuari.canQueryAllHosts()) {
            return true;
        }
        if (maquinaPermesa(maquina, CONSULTA)) {
            return true;
        }
        return false;
    }

    private Collection<Xarxa> filtraXarxes(Collection<Xarxa> xarxes) throws Exception {
        if (AutoritzacionsUsuari.canQueryAllNetworks()
                || AutoritzacionsUsuari.canSupportAllNetworks_VNC()) {
            return xarxes;
        }
        String codiUsuari = Security.getCurrentUser();
        Collection xarxesPermeses = new LinkedList();
        if (codiUsuari != null)
        {
	        Collection codiXarxes = getCodiXarxesAmbAcces(codiUsuari);
	        Iterator iterator = xarxes.iterator();
	        while (iterator.hasNext()) {
	            Xarxa xarxa = (Xarxa) iterator.next();
	            if (codiXarxes.contains(xarxa.getCodi())) {
	                xarxesPermeses.add(xarxa);
	            }
	        }
        }
        return xarxesPermeses;
    }

    private boolean maquinaPermesa(Maquina maquina, int accessLevel) throws InternalErrorException {
        String codiUsuari = Security.getCurrentUser();
        if (codiUsuari != null)
        {
	        Collection networkAuthorizations = findALLNetworkAuthorizationsByCodiUsuari(codiUsuari);
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
            if (codiXarxa.compareTo(networkAuthorization.getCodiXarxa()) == 0) {
                if (teAcces(nomMaquina, networkAuthorization.getMascara())) {
                    int nivell = networkAuthorization.getNivell();
                    if (nivell > maximNivell)
                        maximNivell = nivell;
                }
            }
        }
        return new Long(maximNivell);
    }

    private Collection<Maquina> filtraMaquines(Collection maquines, int accessLevel)
            throws InternalErrorException {
        // if (esAdministrador() || esOperador() || teAccesMaquinesVNC() ) {
        // Mirem si té permis per veure totes les xarxes o per fer VNC
        if (AutoritzacionsUsuari.canQueryAllNetworks() || AutoritzacionsUsuari.canQueryAllHosts()
                || AutoritzacionsUsuari.canSupportAllNetworks_VNC()) {
            return maquines;
        }
        String codiUsuari = Security.getCurrentUser();
        Collection<Maquina> maquinesPermeses = new LinkedList();
        if ( codiUsuari != null)
        {
	        Collection<NetworkAuthorization> networkAuthorizations = findALLNetworkAuthorizationsByCodiUsuari(codiUsuari);
	        Iterator iterator = maquines.iterator();
	        while (iterator.hasNext()) {
	            Maquina maquina = (Maquina) iterator.next();
	            if (maquinaPermesa(networkAuthorizations, maquina, accessLevel)) {
	                maquinesPermeses.add(maquina);
	            }
	        }
        }
        return maquinesPermeses;
    }

    private boolean maquinaPermesa(Collection networkAuthorizations, Maquina maquina,
            int accessLevel) {
        if (maquina.getCodiXarxa() == null)
        	return false;
        Iterator iterator = networkAuthorizations.iterator();
        while (iterator.hasNext()) {
            NetworkAuthorization networkAuthorization = (NetworkAuthorization) iterator.next();
            if (maquina.getCodiXarxa().compareTo(networkAuthorization.getCodiXarxa()) == 0) {
                if (teAcces(maquina.getNom(), networkAuthorization.getMascara())) {
                    // Si és >= accessLevel ja hem trobat una autorització..
                    if (networkAuthorization.getNivell() >= accessLevel)
                        return true;
                }
            }
        }
        return false;
    }

    private Collection<String> getCodiXarxesAmbAcces(String codiUsuari) throws Exception {
        Collection<NetworkAuthorization> networkAuthorizations = findALLNetworkAuthorizationsByCodiUsuari(codiUsuari);
        Collection<String> codiXarxes = new LinkedHashSet(); // perquè no es
                                                             // repetisquen
        Iterator iterator = networkAuthorizations.iterator();
        while (iterator.hasNext()) {
            NetworkAuthorization netAuth = (NetworkAuthorization) iterator.next();
            codiXarxes.add(netAuth.getCodiXarxa());
        }
        return codiXarxes;
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
        es.caib.seycon.ng.model.UsuariEntityDao usuariEntityDao = getUsuariEntityDao();
        UsuariEntity usuariEntity = usuariEntityDao.findByCodi(codiUsuari);
        if (usuariEntity != null) {
            Collection networkAuthorizations = findNetworkAuthorizationsByCodiUsuari(codiUsuari);
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                if (codiXarxa == null || newtworkAuthorization.getCodiXarxa().equals(codiXarxa)) {
                    for (int i = 0; i < autoritzacions.length; i++) {
                        if (newtworkAuthorization.getNivell() == autoritzacions[i])
                            return true; // La hemos encontrado
                    }
                }
            }
        } else {
            throw new SeyconException(
                    String.format(Messages.getString("XarxaServiceImpl.UserNotFound"), codiUsuari)); //$NON-NLS-1$
        }
        // llistes d'acces per rols
        Collection rols = getAplicacioService().findRolsByCodiUsuari(codiUsuari);
        Iterator rolsIterator = rols.iterator();
        while (rolsIterator.hasNext()) {
            Rol rol = (Rol) rolsIterator.next();
            Collection networkAuthorizations = findNetworkAuthorizationsByRol(rol);
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                if (codiXarxa == null || newtworkAuthorization.getCodiXarxa().equals(codiXarxa)) {
                    for (int i = 0; i < autoritzacions.length; i++) {
                        if (newtworkAuthorization.getNivell() == autoritzacions[i])
                            return true; // La hemos encontrado
                    }
                }
            }
        }
        // llistes d'acces per grups (esto puede tardar más.. el último q
        // miramos)
        Collection grups = getGrupService().findGrupsByCodiUsuari(codiUsuari);
        Iterator grupIterator = grups.iterator();
        while (grupIterator.hasNext()) {
            Grup grup = (Grup) grupIterator.next();
            Collection networkAuthorizations = findNetworkAuthorizationsByCodiGrup(grup.getCodi());
            Iterator iterator = networkAuthorizations.iterator();
            while (iterator.hasNext()) {
                NetworkAuthorization newtworkAuthorization = (NetworkAuthorization) iterator.next();
                if (codiXarxa == null || newtworkAuthorization.getCodiXarxa().equals(codiXarxa)) {
                    for (int i = 0; i < autoritzacions.length; i++) {
                        if (newtworkAuthorization.getNivell() == autoritzacions[i])
                            return true; // La hemos encontrado
                    }
                }
            }
        }
        return false; // No se ha encontrado
    }

    private boolean adrecaCorrecta(String adreca) {
        if (adreca != null && adreca.trim().compareTo("") != 0) { //$NON-NLS-1$
            String[] adreces = adreca.split("\\."); //$NON-NLS-1$
            if (adreces.length == 4) {
                boolean correcte = true;
                for (int i = 0; i < 4 && correcte; i++) {
                    try {
                        correcte = Integer.parseInt(adreces[i]) <= 255;
                    } catch (Exception e) {
                        return false;
                    }
                }
                return correcte;
            }
        }
        return false;
    }

    private boolean maquinaCompatibleAmbXarxa(String adrecaMaquina, String adrecaXarxa,
            String mascaraXarxa) {
        if (adrecaXarxa.compareTo("0.0.0.0") == 0 && mascaraXarxa.compareTo("0.0.0.0") == 0) { //$NON-NLS-1$ //$NON-NLS-2$
            return true;
        }

        if (!adrecaCorrecta(mascaraXarxa)) {
            return false;
        }
        String[] mascaresXarxa = mascaraXarxa.split("\\."); //$NON-NLS-1$
        if (!adrecaCorrecta(adrecaXarxa)) {
            return false;
        }
        String[] adrecaXarxes = adrecaXarxa.split("\\."); //$NON-NLS-1$
        if (!adrecaCorrecta(adrecaMaquina)) {
            return false;
        }
        String[] adrecaMaquines = adrecaMaquina.split("\\."); //$NON-NLS-1$
        boolean compatible = true;
        for (int i = 0; i < 4 && compatible; i++) {
            int currentMascaraXarxa = Integer.parseInt(mascaresXarxa[i]);
            int currentAdrecaMaquina = Integer.parseInt(adrecaMaquines[i]);
            int currentAdrecaXarxa = Integer.parseInt(adrecaXarxes[i]);
            compatible = (currentMascaraXarxa & currentAdrecaXarxa) == (currentMascaraXarxa & currentAdrecaMaquina);
        }
        return compatible;
    }

    private boolean maquinesIguals(Maquina maquinaA, Maquina maquinaB) {
        return (!(((maquinaA.getNom() == null && maquinaB.getNom() != null) || (maquinaA.getNom() != null && maquinaB
                .getNom() == null))
                && ((maquinaA.getSistemaOperatiu() == null || maquinaB.getSistemaOperatiu() != null) || (maquinaA
                        .getSistemaOperatiu() != null || maquinaB.getSistemaOperatiu() == null))
                && ((maquinaA.getAdreca() == null && maquinaB.getAdreca() != null) || (maquinaA
                        .getAdreca() != null && maquinaB.getAdreca() == null))
                && ((maquinaA.getDescripcio() == null && maquinaB.getDescripcio() != null) || (maquinaA
                        .getDescripcio() != null && maquinaB.getDescripcio() == null))
                && ((maquinaA.getDhcp() == null && maquinaB.getDhcp() != null) || (maquinaA
                        .getDhcp() != null && maquinaB.getDhcp() == null))
                && ((maquinaA.getCorreu() == null && maquinaB.getCorreu() != null) || (maquinaA
                        .getCorreu() != null && maquinaB.getCorreu() == null))
                && ((maquinaA.getOfimatica() == null && maquinaB.getOfimatica() != null) || (maquinaA
                        .getOfimatica() != null && maquinaB.getOfimatica() == null))
                && ((maquinaA.getAliasMaquina() == null && maquinaB.getAliasMaquina() != null) || (maquinaA
                        .getAliasMaquina() != null && maquinaB.getAliasMaquina() == null))
                && ((maquinaA.getCodiXarxa() == null && maquinaB.getCodiXarxa() != null) || (maquinaA
                        .getCodiXarxa() != null && maquinaB.getCodiXarxa() == null))
                && ((maquinaA.getMac() == null && maquinaB.getMac() != null) || (maquinaA.getMac() != null && maquinaB
                        .getMac() == null)) && ((maquinaA.getServidorImpressores() == null && maquinaB
                .getServidorImpressores() != null) || (maquinaA.getServidorImpressores() != null && maquinaB
                .getServidorImpressores() == null))))
                // condicions
                && (((maquinaA.getNom() == null && maquinaB.getNom() == null) || (maquinaA.getNom()
                        .compareTo(maquinaB.getNom()) == 0))
                        && ((maquinaA.getSistemaOperatiu() == null || maquinaB.getSistemaOperatiu() == null) || (maquinaA
                                .getSistemaOperatiu().compareTo(maquinaB.getSistemaOperatiu()) == 0))
                        && ((maquinaA.getAdreca() == null && maquinaB.getAdreca() == null) || (maquinaA
                                .getAdreca().compareTo(maquinaB.getAdreca()) == 0))
                        && ((maquinaA.getDescripcio() == null && maquinaB.getDescripcio() == null) || (maquinaA
                                .getDescripcio().compareTo(maquinaB.getDescripcio()) == 0))
                        && ((maquinaA.getDhcp() == null && maquinaB.getDhcp() == null) || (maquinaA
                                .getDhcp().compareTo(maquinaB.getDhcp()) == 0))
                        && ((maquinaA.getCorreu() == null && maquinaB.getCorreu() == null) || (maquinaA
                                .getCorreu().compareTo(maquinaB.getCorreu()) == 0))
                        && ((maquinaA.getOfimatica() == null && maquinaB.getOfimatica() == null) || (maquinaA
                                .getOfimatica().compareTo(maquinaB.getOfimatica()) == 0))
                        && ((maquinaA.getAliasMaquina() == null && maquinaB.getAliasMaquina() == null) || (sonAliasIguales(
                                maquinaA.getAliasMaquina(), maquinaB.getAliasMaquina())))
                        && ((maquinaA.getCodiXarxa() == null && maquinaB.getCodiXarxa() == null) || (maquinaA
                                .getCodiXarxa().compareTo(maquinaB.getCodiXarxa()) == 0))
                        && ((maquinaA.getMac() == null && maquinaB.getMac() == null) || (maquinaA
                                .getMac().compareTo(maquinaB.getMac()) == 0)) && ((maquinaA
                        .getServidorImpressores() == null && maquinaB.getServidorImpressores() == null) || (maquinaA
                        .getServidorImpressores().compareTo(maquinaB.getServidorImpressores()) == 0)));
    }

    private boolean sonAliasIguales(String alias1, String alias2) {
        if ((alias1 == null && alias2 != null) || (alias1 != null && alias2 == null))
            return false; // solo 1 nulo
        if (alias1 == null && alias2 == null)
            return true; // ambos nulos
        HashSet h_alias1 = new HashSet();
        HashSet h_alias2 = new HashSet();
        // alias1 y alias2 NO son nulos
        String[] v_alias1 = alias1.split(" "); //$NON-NLS-1$
        String[] v_alias2 = alias2.split(" "); //$NON-NLS-1$
        // Los guardamos en los sets
        if (v_alias1 != null)
            for (int i = 0; i < v_alias1.length; i++) {
                String act = v_alias1[i];
                if (act != null && !"".equals(act.trim())) //$NON-NLS-1$
                    h_alias1.add(act);
            }
        if (v_alias2 != null)
            for (int i = 0; i < v_alias2.length; i++) {
                String act = v_alias2[i];
                if (act != null && !"".equals(act.trim())) //$NON-NLS-1$
                    h_alias2.add(act);
            }
        if (h_alias1.size() != h_alias2.size())
            return false; // No tienen el mismo tamaño
        // Los comparamos buscando todos los del primero en el segundo:
        for (Iterator it = h_alias1.iterator(); it.hasNext();) {
            String elem = (String) it.next();
            if (!h_alias2.contains(elem))
                return false;
        }
        return true;
    }

    private Collection<XarxaEntity> localFindXarxaByFiltre(java.lang.String codi,
            java.lang.String adreca, java.lang.String descripcio, java.lang.String mascara,
            java.lang.String normalitzada, java.lang.String dhcp, String maquina)
            throws java.lang.Exception {
        if (codi != null && (codi.trim().compareTo("") == 0 || codi.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            codi = null;
        }
        if (adreca != null
                && (adreca.trim().compareTo("") == 0 || adreca.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            adreca = null;
        }
        if (descripcio != null
                && (descripcio.trim().compareTo("") == 0 || descripcio.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            descripcio = null;
        }
        if (mascara != null
                && (mascara.trim().compareTo("") == 0 || mascara.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            mascara = null;
        }
        if (normalitzada != null
                && (normalitzada.trim().compareTo("") == 0 || normalitzada.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            normalitzada = null;
        }
        if (dhcp != null && (dhcp.trim().compareTo("") == 0 || dhcp.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            dhcp = null;
        }
        String query = "select xarxa " + "from es.caib.seycon.ng.model.XarxaEntity xarxa " //$NON-NLS-1$ //$NON-NLS-2$
                + "where " + "(:codi is null or xarxa.codi like :codi) and " //$NON-NLS-1$ //$NON-NLS-2$
                + "(:adreca is null or xarxa.adreca like :adreca) and " //$NON-NLS-1$
                + "(:descripcio is null or xarxa.descripcio like :descripcio) and " //$NON-NLS-1$
                + "(:mascara is null or xarxa.mascara like :mascara) and " //$NON-NLS-1$
                + "(:normalitzada is null or xarxa.normalitzada = :normalitzada) and " //$NON-NLS-1$
                + "(:dhcp is null or xarxa.dhcp like :dhcp) " + "order by xarxa.codi"; //$NON-NLS-1$ //$NON-NLS-2$
        Parameter codiParameter = new Parameter("codi", codi); //$NON-NLS-1$
        Parameter adrecaParameter = new Parameter("adreca", adreca); //$NON-NLS-1$
        Parameter descripcioParameter = new Parameter("descripcio", descripcio); //$NON-NLS-1$
        Parameter normalitzadaParameter = new Parameter("normalitzada", normalitzada); //$NON-NLS-1$
        Parameter mascaraParameter = new Parameter("mascara", mascara); //$NON-NLS-1$
        Parameter dhcpParameter = new Parameter("dhcp", dhcp); //$NON-NLS-1$
        Parameter parametres[] = { codiParameter, adrecaParameter, mascaraParameter,
                descripcioParameter, normalitzadaParameter, dhcpParameter };
        Collection<XarxaEntity> xarxesTrobades = getXarxaEntityDao().query(query, parametres);
        return xarxesTrobades;
    }

    private Collection<Xarxa> filtraPerMaquina(Collection<Xarxa> xarxesTrobades, String maquina) {
        if (maquina != null && maquina.compareTo("") != 0 && maquina.compareTo("%") != 0 //$NON-NLS-1$ //$NON-NLS-2$
                && xarxesTrobades.size() > 0) {
            String xarxes = ""; //$NON-NLS-1$
            Iterator xarxesIterator = xarxesTrobades.iterator();
            while (xarxesIterator.hasNext()) {
                Xarxa xarxa = (Xarxa) xarxesIterator.next();
                xarxes += "'" + xarxa.getCodi() + "'" + (xarxesIterator.hasNext() ? "," : ""); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            }
            String query = "select distinct maquina.xarxa " //$NON-NLS-1$
                    + "from es.caib.seycon.ng.model.MaquinaEntity maquina " + "where " //$NON-NLS-1$ //$NON-NLS-2$
                    + "maquina.nom like :maquina and " + "maquina.xarxa.codi in (" + xarxes + ") " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    + "order by maquina.xarxa.codi"; //$NON-NLS-1$
            Parameter parametres[] = { new Parameter("maquina", maquina) }; //$NON-NLS-1$
            List<XarxaEntity> xarxesList = getXarxaEntityDao().query(query, parametres);
            return getXarxaEntityDao().toXarxaList(xarxesList);
        }
        return xarxesTrobades;
    }

    private Collection<NetworkAuthorization> findNetworkAuthorizationsByRol(Rol rol) {
        String query = "select xarxaAC from " //$NON-NLS-1$
                + "es.caib.seycon.ng.model.XarxaACEntity xarxaAC where " //$NON-NLS-1$
                + "xarxaAC.role.nom = :nom and " //$NON-NLS-1$
                + "xarxaAC.role.baseDeDades.codi = :dispatcher and " //$NON-NLS-1$
                + "xarxaAC.role.aplicacio.codi = :aplicacio"; //$NON-NLS-1$
        Parameter parametres[] = { new Parameter("nom", rol.getNom()), //$NON-NLS-1$
                new Parameter("dispatcher", rol.getBaseDeDades()), //$NON-NLS-1$
                new Parameter("aplicacio", rol.getCodiAplicacio()) }; //$NON-NLS-1$
        Collection<XarxaACEntity> xarxaACsTrobades = getXarxaACEntityDao().query(query, parametres);
        if (xarxaACsTrobades != null) {
            return getXarxaACEntityDao().toNetworkAuthorizationList(xarxaACsTrobades);
        }
        return new LinkedList<NetworkAuthorization>();
    }

    private void auditaSolicitudDadesAdministradorHost(String maquina) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();
        auditoria.setAccio("A"); // Administrador //$NON-NLS-1$
        auditoria.setMaquina(maquina);
        auditoria.setAutor(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObjecte("SC_ADMMAQ"); //$NON-NLS-1$

        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    protected Collection<Maquina> handleFindMaquinaOfirmaticaUsuariByFiltre(
		String nom, String sistemaOperatiu, String adreca, String dhcp,
		String correu, String ofimatica, String alias, String mac,
		String descripcio, String xarxa, String codiUsuari,
		Boolean restringeixCerca, String servidorImpressores) throws Exception
	{
    	int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$

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

        Collection<MaquinaEntity> maquines = null;

        // Realizamos la siguiente consulta (sin tener cuenta el alias)
        String query = "select distinct maquina from " //$NON-NLS-1$
                + " es.caib.seycon.ng.model.SessioEntity sessio " //$NON-NLS-1$
                + " right outer join sessio.maquina as maquina " //$NON-NLS-1$
                + " left outer join sessio.usuari as usuari" //$NON-NLS-1$
                + " where " //$NON-NLS-1$
                + "(:nom is null or maquina.nom like :nom) and (:sistemaOperatiu is null or " //$NON-NLS-1$
                + "maquina.operatingSystem.name like :sistemaOperatiu) and (:adreca is null or " //$NON-NLS-1$
                + "maquina.adreca like :adreca) and (:dhcp is null or " //$NON-NLS-1$
                + "maquina.dhcp like :dhcp) and (:correu is null or " //$NON-NLS-1$
                + "maquina.correu like :correu) and (:ofimatica is null or " //$NON-NLS-1$
                + "maquina.ofimatica like :ofimatica) " //$NON-NLS-1$
                + "and (:mac is null or maquina.mac like :mac) and " //$NON-NLS-1$
                + "(:descripcio is null or maquina.descripcio like :descripcio) and " //$NON-NLS-1$
                + "(:xarxa is null or maquina.xarxa.codi like :xarxa) and " //$NON-NLS-1$
                + "(:codiUsuari is null  or (usuari is not null and  usuari.codi like :codiUsuari))" //$NON-NLS-1$
                + "and (:servidorImpressores is null or maquina.servidorImpressores like :servidorImpressores) " //$NON-NLS-1$
                + "order by maquina.nom "; //$NON-NLS-1$

        Parameter[] params = new Parameter[] { new Parameter("nom", nom), //$NON-NLS-1$
                new Parameter("sistemaOperatiu", sistemaOperatiu), new Parameter("adreca", adreca), //$NON-NLS-1$ //$NON-NLS-2$
                new Parameter("dhcp", dhcp), new Parameter("correu", correu), //$NON-NLS-1$ //$NON-NLS-2$
                new Parameter("ofimatica", ofimatica), new Parameter("mac", mac), //$NON-NLS-1$ //$NON-NLS-2$
                new Parameter("descripcio", descripcio), new Parameter("xarxa", xarxa), //$NON-NLS-1$ //$NON-NLS-2$
                new Parameter("codiUsuari", codiUsuari), //$NON-NLS-1$
                new Parameter("servidorImpressores", servidorImpressores) }; //$NON-NLS-1$
        maquines = getMaquinaEntityDao().query(query, params);

        // Filtramos por alias (si se ha especificado algún valor)
        if (alias != null) {
            Collection maquinesAlias = getAliasMaquinaEntityDao().findMaquinaByAlias(alias);
            HashSet h_maquinesAlias = new HashSet(maquinesAlias.size());
            for (Iterator it = maquinesAlias.iterator(); it.hasNext();) {
                MaquinaEntity maqAlias = (MaquinaEntity) it.next();
                h_maquinesAlias.add(maqAlias.getId());
            }
            // Nos quedamos sólo con las máquinas de la búsqueda que tengan el
            // alias indicado
            for (Iterator it = maquines.iterator(); it.hasNext();) {
                MaquinaEntity maq = (MaquinaEntity) it.next();
                if (!h_maquinesAlias.contains(maq.getId()))
                    it.remove(); // Lo eliminamos (no tiene el alias buscado)
            }
        }
        
        // Check results list lenght
        if (maquines.size() > limitResults)
        {
        	return getMaquinaEntityDao().toMaquinaList(maquines)
				.subList(0, limitResults);
        }
        
        return getMaquinaEntityDao().toMaquinaList(maquines);
    }

    private void auditaVNC(SessioEntity sessio, String accio) {
        String codiUsuari = Security.getCurrentAccount();
        Auditoria auditoria = new Auditoria();

        auditoria.setAutor(codiUsuari); // usuari auditador
        auditoria.setUsuari(sessio.getUsuari().getCodi()); // usuari auditat
        auditoria.setAccio(accio);
        auditoria.setMaquina(sessio.getMaquina().getNom());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(GregorianCalendar.getInstance().getTime()));
        auditoria.setObjecte("VNC"); //$NON-NLS-1$

        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    private Long internalGetAccessLevel(String nomMaquina, String codiXarxa)
            throws InternalErrorException {
        // Si té l'autorització, no es miren els ACL
        if (AutoritzacionsUsuari.canSupportAllNetworks_VNC())
            return new Long(SUPORT);

        String codiUsuari = Security.getCurrentUser();
        if (codiUsuari == null)
        	return new Long (SENSE_PERMISOS);
        
        Collection networkAuthorizations = findALLNetworkAuthorizationsByCodiUsuari(codiUsuari);
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

        try {
            SessioEntity sessio = getSessioEntityDao().findById(sessioId);

            if (sessio == null)
                throw new InternalErrorException(
                        String.format(
                                Messages.getString("XarxaServiceImpl.NoSessionIDFound"), //$NON-NLS-1$
                                sessioId));

            if (sessio.getMaquinaClient() != null)
                return false;

            // Mirem si és autoritzat (autorització o acls)
            if (internalGetAccessLevel(sessio.getMaquina().getNom(), sessio.getMaquina().getXarxa()
                    .getCodi()) < SUPORT)
                throw new java.lang.SecurityException(Messages.getString("XarxaServiceImpl.NoPermissionMessage")); //$NON-NLS-1$

            InetAddress addr = InetAddress.getLocalHost();

            if (addr == null)
                throw new InternalErrorException(
                        Messages.getString("XarxaServiceImpl.NoGetInetAddress")); //$NON-NLS-1$

            TimedProcess t = new TimedProcess(20000);
            if (addr.getHostName().toLowerCase().startsWith("epreinf14")) //$NON-NLS-1$
                t.exec(new String[] { "rsh", "spreinfsun2", "rsh", sessio.getMaquina().getNom(), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                        "vnc" }); //$NON-NLS-1$
            else
                t.exec(new String[] { "rsh", sessio.getMaquina().getNom(), "vnc" }); //$NON-NLS-1$ //$NON-NLS-2$

            if (t.getOutput().indexOf("concedit") > 0) { //$NON-NLS-1$

                auditaVNC(sessio, "S"); //$NON-NLS-1$
                /*
                 * registraAuditoria (sessio.getMaquina().Name,
                 * usuaris.getIdUsuari(autor), sessio.getUsuari()!=null ?
                 * sessio.getUsuari().User : "", "S");
                 */
                return true;
            } else
                auditaVNC(sessio, "N"); //$NON-NLS-1$
            /*
             * registraAuditoria( sessio.getMaquina().Name,
             * usuaris.getIdUsuari(autor), sessio.getUsuari() != null ?
             * sessio.getUsuari().User : "", "N");
             */
            return false;

        } catch (java.net.UnknownHostException e) {
            throw new InternalErrorException(e.toString());
        }
    }

    protected Boolean handleHasAnyACLXarxes(String codiUsuari) throws Exception {
        return hasNetworkAuthorizations(codiUsuari, null, new int[] { ADMINISTRACIO, CONSULTA,
                SUPORT });
    }

    @Override
    protected AutoritzacioAccesHostComAdministrador handleRevocarAccesHostComAdministrador(
            AutoritzacioAccesHostComAdministrador autoritzacioAccesComAdministrador)
            throws Exception {
        // Aquest mètode ja és restringit pel jboss (host:admin:query)

        // 1) canviem la data de l'autorització a ara mateix
        Calendar araMateix = Calendar.getInstance();

        // Comprovem que no estiga caducada
        if (autoritzacioAccesComAdministrador.getDataCaducitatAutoritzacioAcces().getTimeInMillis() < araMateix
                .getTimeInMillis()) {
            throw new SeyconException(Messages.getString("XarxaServiceImpl.AuthorizacionExpired")); //$NON-NLS-1$
        }

        autoritzacioAccesComAdministrador.setDataCaducitatAutoritzacioAcces(araMateix);
        AutoritzacioAccesHostComAdministradorEntity entity = getAutoritzacioAccesHostComAdministradorEntityDao()
                .autoritzacioAccesHostComAdministradorToEntity(autoritzacioAccesComAdministrador);
        // Actualitzem les dades
        getAutoritzacioAccesHostComAdministradorEntityDao().update(entity);

        // Auditem el canvi
        // Indiquem com a M l'acció (modificació)
        auditaPeticioAdministrarHost(autoritzacioAccesComAdministrador.getNomHost(),
                autoritzacioAccesComAdministrador.getCodiUsuari(), "R"); //$NON-NLS-1$

        return getAutoritzacioAccesHostComAdministradorEntityDao()
                .toAutoritzacioAccesHostComAdministrador(entity);
    }

    private void auditaPeticioAdministrarHost(String maquina, String codiUsuari, String accio)
            throws Exception {

        String codiUsuariCanvi = Security.getCurrentAccount();
        // Fem un nestedlogin per obtindre autorització per fer auditoria

        Auditoria auditoria = new Auditoria();
        auditoria.setAccio(accio); // Aprovat / Rebujat /
        auditoria.setMaquina(maquina);
        auditoria.setAutor(codiUsuariCanvi);
        auditoria.setUsuari(codiUsuari);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(Calendar.getInstance().getTime()));
        auditoria.setObjecte("SC_ADMMAQ"); //$NON-NLS-1$

        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    private void auditaCanviDadesAdministradorHost(String maquina) throws Exception {

        String codiUsuariCanvi = Security.getCurrentAccount(); //$NON-NLS-1$
        // Fem un nestedlogin per obtindre autorització per fer auditoria

        Auditoria auditoria = new Auditoria();
        auditoria.setAccio("U"); //$NON-NLS-1$
        auditoria.setMaquina(maquina);
        auditoria.setAutor(codiUsuariCanvi);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
        auditoria.setData(dateFormat.format(Calendar.getInstance().getTime()));
        auditoria.setObjecte("SC_ADMMAQ"); //$NON-NLS-1$

        AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao().auditoriaToEntity(auditoria);
        getAuditoriaEntityDao().create(auditoriaEntity);
    }

    @Override
    protected void handleSetContrasenyaAdministrador(String nomMaquina, String adminUser,
            String adminPass) throws Exception {
        // Añadimos auditoría de la petición
        auditaCanviDadesAdministradorHost(nomMaquina); // AUDITORIA

        MaquinaEntity host = getMaquinaEntityDao().findByNom(nomMaquina);

        host.setUsuariAdministrador(adminUser);
        host.setContrasenyaAdministrador(new Password(adminPass).toString());
        host.setDataContrasenyaAdministrador(new Date());
        getMaquinaEntityDao().update(host);
    }

    @Override
    protected Maquina handleFindMaquinaBySerialNumber(String serialNumber) throws Exception {
        MaquinaEntity maquina = getMaquinaEntityDao().findBySerialNumber(serialNumber);
        if (maquina == null)
            return null;
        else
            return getMaquinaEntityDao().toMaquina(maquina);
    }

    @Override
    protected Maquina handleRegisterDynamicIP(String nomMaquina, String ip, String serialNumber)
            throws es.caib.seycon.ng.exception.UnknownHostException, UnknownNetworkException {
        boolean anyChange = false;
        // First. Test if this IP belongs to anybody else
        MaquinaEntity old = getMaquinaEntityDao().findByAdreca(ip);
        MaquinaEntity maquina = null;
        if (old != null) {
            if (serialNumber.equals(old.getSerialNumber())) {
                maquina = old;
                // Coincide serial number 
                if (!nomMaquina.equals(maquina.getNom())) {
                    // Host name changed
                    // Check if already exists such a name
                    old = getMaquinaEntityDao().findByNom(nomMaquina);
                    if (old != null) {
                        old.setDeleted(true);
                        getMaquinaEntityDao().update(old);
                    }
                    maquina.setNom(nomMaquina);
                    anyChange = true;
                }
            } else {
                if (old.getDeleted().booleanValue() || old.getDynamicIP().booleanValue()) {
                    old.setAdreca(null);
                    old.setXarxa(null);
                    getMaquinaEntityDao().update(old);
                } else {
                    log.warn(String
                            .format(Messages.getString("XarxaServiceImpl.HostsCollisionMessage"), //$NON-NLS-1$
                                    nomMaquina, nomMaquina, ip, serialNumber));
                    throw new UnknownHostException(String.format(Messages.getString("XarxaServiceImpl.IPAssignedMessage"), //$NON-NLS-1$
                            ip, old.getNom()));
                }
            }
        }
        // Second. Test if this name belongs to anybody else
        if (maquina == null) {
            // Found a host with no serial number => Bind it
            old = getMaquinaEntityDao().findByNom(nomMaquina);
            if (old == null) {
                // Nothing to do
            } else if (old.getSerialNumber() == null && old.getDynamicIP().booleanValue()) {
                // Replace unused host
                maquina = old;
                maquina.setSerialNumber(serialNumber);
                maquina.setAdreca(ip);
                maquina.setLastSeen(new Date());
                getMaquinaEntityDao().update(maquina);
            } else if (serialNumber.equals(old.getSerialNumber())) {
                // Found host entry
                maquina = old;
            } else if (old.getDynamicIP().booleanValue()) {
                // Autodelete
                old.setDeleted(true);
                getMaquinaEntityDao().update(old);
            } else {
                log.warn(String.format(
                        Messages.getString("XarxaServiceImpl.HostsCollisionMessage"), //$NON-NLS-1$
                        nomMaquina, nomMaquina, ip, serialNumber));
                throw new UnknownHostException(nomMaquina);
            }
        }
        // Third. Test if this serial is already used (with another name)
        if (maquina == null) {
            maquina = getMaquinaEntityDao().findBySerialNumber(serialNumber);
            if (maquina != null && !nomMaquina.equals(maquina.getNom())) {
            	anyChange = true;
                maquina.setNom(nomMaquina);
            }
        }
        
        if (maquina == null) {
        	try {
                InetAddress addr = InetAddress.getByName(ip);
                XarxaEntity x = guessNetwork(addr.getAddress());
                if (x == null)
                {
                	String msg = String.format(Messages.getString("XarxaServiceImpl.RequestUnmanagedIP"), nomMaquina, ip); //$NON-NLS-1$ 
                	log.warn(msg);
                    throw new UnknownNetworkException(msg);
                }
                maquina = getMaquinaEntityDao().newMaquinaEntity();
                maquina.setAdreca(ip);
                maquina.setCorreu("N"); //$NON-NLS-1$
                maquina.setDeleted(false);
                DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.MEDIUM);
                maquina.setDescripcio(Messages.getString("XarxaServiceImpl.AutocreatedMessage") + //$NON-NLS-1$
                				" "+ // $NON-NLS-1$ //$NON-NLS-1$
                				df.format(new Date())); //$NON-NLS-1$
                maquina.setDynamicIP(new Boolean(true));
                maquina.setNom(nomMaquina);
                maquina.setOfimatica("N"); //$NON-NLS-1$
                maquina.setSerialNumber(serialNumber);
                maquina.setServidorImpressores("N"); //$NON-NLS-1$
                maquina.setOperatingSystem(getOsTypeEntityDao().findOSTypeByName("ALT")); //$NON-NLS-1$
                maquina.setXarxa(x);
                getMaquinaEntityDao().create(maquina);
        	} catch (java.net.UnknownHostException e) {
            	String msg = String.format(Messages.getString("XarxaServiceImpl.RequestUnmanagedIP"), nomMaquina, "??"); //$NON-NLS-1$ //$NON-NLS-2$ 
            	log.warn(msg);
                throw new UnknownNetworkException(msg); 
        	}
        }

        if (!ip.equals(maquina.getAdreca()) || 
        		maquina.getXarxa() == null ||  
        		!Boolean.FALSE.equals(maquina.getDeleted())) {
        	try {
                InetAddress addr = InetAddress.getByName(ip);
                XarxaEntity x = guessNetwork(addr.getAddress());
                if (x != null) {
                	if (x.isDchpSupport()) {
                		anyChange = true;
                		maquina.setDeleted(Boolean.FALSE);
    	                maquina.setAdreca(ip);
    	                maquina.setXarxa(x);
                	} else {
                        throw new UnknownNetworkException(String.format(
                                Messages.getString("XarxaServiceImpl.RequestWithoutDHCP"),  //$NON-NLS-1$
                                nomMaquina, ip, x.getCodi()));
                	}
                } else {
                    throw new UnknownNetworkException(String.format(
                            Messages.getString("XarxaServiceImpl.RequestUnmanagedIP"), nomMaquina, ip)); //$NON-NLS-1$
                }
        	} catch (java.net.UnknownHostException e) {
            	String msg = String.format(Messages.getString("XarxaServiceImpl.RequestUnmanagedIP"), nomMaquina, "??"); //$NON-NLS-1$ //$NON-NLS-2$ 
            	log.warn(msg);
                throw new UnknownNetworkException(msg); 
        	}
        }
        if (anyChange || 
        		maquina.getLastSeen() == null ||
        		System.currentTimeMillis() - maquina.getLastSeen().getTime() > 8 * 60L * 60L * 1000L) // each 8 hours update last seen
        {
        	maquina.setLastSeen(new Date());
        	getMaquinaEntityDao().update(maquina);
        }

        return getMaquinaEntityDao().toMaquina(maquina);
    }

    private XarxaEntity guessNetwork(byte[] b) {
        XarxaEntityDao dao = getXarxaEntityDao();
        XarxaEntity xarxa = null;
        for (int bc = b.length - 1; xarxa == null && bc >= 0; bc--) {
            byte mascara = (byte) 255;
            for (int bits = 0; xarxa == null && bits < 8; bits++) {
                mascara = (byte) (mascara << 1);
                b[bc] = (byte) (b[bc] & mascara);
                InetAddress addr2;
                try {
                    addr2 = InetAddress.getByAddress(b);
                    String addrText = addr2.getHostAddress();
                    xarxa = dao.findByAdreca(addrText);
                } catch (java.net.UnknownHostException e) {
                    e.printStackTrace();
                }
            }
        }
        if (xarxa == null)
        {
        	String defaultNetwork = System.getProperty("soffid.network.internet"); //$NON-NLS-1$
        	if (defaultNetwork != null)
        	{
        		xarxa = dao.findByCodi(defaultNetwork);
        		if (xarxa == null)
        		{
        			xarxa = dao.newXarxaEntity();
        			xarxa.setCodi(defaultNetwork);
        			xarxa.setAdreca("0.0.0.0"); //$NON-NLS-1$
        			xarxa.setMascara("255.255.255.255"); //$NON-NLS-1$
        			xarxa.setDchpSupport(true);
        			xarxa.setDescripcio("Autocreated network for unknown IP adresses"); //$NON-NLS-1$
        			xarxa.setNormalitzada("N"); //$NON-NLS-1$
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
	protected List<OsType> handleFindAllOSType () throws Exception
	{
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
			throw new SeyconException(String.format(Messages.getString("XarxaServiceImpl.IntegrityViolationMachines"), osTypeEntity.getName())); //$NON-NLS-1$
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

}
