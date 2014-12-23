// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.servei;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

import com.soffid.iam.api.RoleAccount;

import es.caib.bpm.vo.PredefinedProcessType;
import es.caib.seycon.ng.comu.Account;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.AdministracioAplicacio;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.AutoritzacioPuntEntrada;
import es.caib.seycon.ng.comu.AutoritzacioRol;
import es.caib.seycon.ng.comu.ContenidorRol;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.NetworkAuthorization;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolAccount;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.SoDRisk;
import es.caib.seycon.ng.comu.SoDRule;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.UserAccount;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariWFProcess;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownUserException;
import es.caib.seycon.ng.model.AccountEntity;
import es.caib.seycon.ng.model.AplicacioEntity;
import es.caib.seycon.ng.model.AutoritzacioPUERolEntity;
import es.caib.seycon.ng.model.AutoritzacioRolEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.GrupEntity;
import es.caib.seycon.ng.model.NotificacioEntity;
import es.caib.seycon.ng.model.Parameter;
import es.caib.seycon.ng.model.RolAccountEntity;
import es.caib.seycon.ng.model.RolAssociacioRolEntity;
import es.caib.seycon.ng.model.RolEntity;
import es.caib.seycon.ng.model.RolsGrupEntity;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.model.UsuariGrupEntity;
import es.caib.seycon.ng.model.ValorDominiAplicacioEntity;
import es.caib.seycon.ng.model.XarxaACEntity;
import es.caib.seycon.ng.model.criteria.CriteriaSearchConfiguration;
import es.caib.seycon.ng.utils.AutoritzacioSEU;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.DateUtils;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.servei.AplicacioService Versió remixed, remade &
 *      remodelled per les autoritzacions de SEU
 */
@SuppressWarnings("rawtypes")
public class AplicacioServiceImpl extends
        es.caib.seycon.ng.servei.AplicacioServiceBase {
	final int DIRECT = 0;
	final int INDIRECT = 1;
	final int ALL = 2;
	final int NONE=3;

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#getAplicacions()
     */
    protected java.util.Collection<Aplicacio> handleGetAplicacions()
            throws java.lang.Exception {
        Collection<AplicacioEntity> apps = AutoritzacionsUsuari
                .filtraAplicationsCanQuery(getAplicacioEntityDao().loadAll());
        List<Aplicacio> aplicacions = getAplicacioEntityDao().toAplicacioList(
                apps);
        return aplicacions;
    }

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#create(es.caib.seycon.ng.comu.Aplicacio)
     */
    protected es.caib.seycon.ng.comu.Aplicacio handleCreate(
            es.caib.seycon.ng.comu.Aplicacio aplicacio)
            throws java.lang.Exception {
        // Aquí l'autorització no te domini, aixina que amb tindre
        // application:create val
        if (AutoritzacionsUsuari.canCreateAplicacio(aplicacio.getCodi())) {
    		AplicacioEntity aplicationsSameCode = getAplicacioEntityDao().findByCodi(aplicacio.getCodi());
    		if(aplicationsSameCode != null)
    			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.CodeAplicationExists"),  //$NON-NLS-1$
    							aplicacio.getCodi())); 
            AplicacioEntity apl = getAplicacioEntityDao().aplicacioToEntity(
                    aplicacio);
            getAplicacioEntityDao().create(apl);
            aplicacio.setId(apl.getId());
            return (getAplicacioEntityDao().toAplicacio(apl));
        }
		throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NoUserPermission"), //$NON-NLS-1$
				getPrincipal().getName())); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#delete(es.caib.seycon.ng.comu.Aplicacio)
     */
    protected void handleDelete(es.caib.seycon.ng.comu.Aplicacio aplicacio)
            throws java.lang.Exception {
        // Esborrem els rols d'administració de l'aplicació i l'aplicació
        if (AutoritzacionsUsuari.canDeleteAplicacio(aplicacio.getCodi())) {
            // Obtenim els rols d'autorització de l'aplicació per esborrar-los
            Collection restriccions = findAdministracioAplicacioByCodiAplicacio(aplicacio
                    .getCodi());
            Iterator iterator = restriccions.iterator();
            while (iterator.hasNext()) {// els esborrem
                AdministracioAplicacio administracioAplicacio = (AdministracioAplicacio) iterator
                        .next();
                delete(administracioAplicacio);
            }
            AplicacioEntity aplEntity = getAplicacioEntityDao().aplicacioToEntity(aplicacio);
            if(!aplEntity.getRols().isEmpty())
            	throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.IntegrityExceptionRol"),  //$NON-NLS-1$
    							aplEntity.getCodi()));
            getAplicacioEntityDao().remove(aplEntity);
        } else {
            throw new SeyconAccessLocalException("aplicacioService", //$NON-NLS-1$
                    "delete (Aplicacio)", "application:delete", //$NON-NLS-1$ //$NON-NLS-2$
                    Messages.getString("AplicacioServiceImpl.NotAuthorizedToDelete")); //$NON-NLS-1$
            /*
             * throw new SeyconException(
             * "Usuari no té permisos per actualitzar l'aplicació amb codi '" +
             * aplicacio.getCodi() + "'.");
             */
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#update(es.caib.seycon.ng.comu.Aplicacio)
     */
    protected void handleUpdate(es.caib.seycon.ng.comu.Aplicacio aplicacio)
            throws java.lang.Exception {

        if (AutoritzacionsUsuari.canUpdateAplicacio(aplicacio.getCodi())) {
            getAplicacioEntityDao().update(
                    getAplicacioEntityDao().aplicacioToEntity(aplicacio));
        } else {
            throw new SeyconAccessLocalException("aplicacioService", //$NON-NLS-1$
                    "update (Aplicacio)", "application:update", //$NON-NLS-1$ //$NON-NLS-2$
                    Messages.getString("AplicacioServiceImpl.NotAuthorizedToUpdate")); //$NON-NLS-1$
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#findAplicacioByCodiAplicacio(java.lang.String)
     */
    protected Aplicacio handleFindAplicacioByCodiAplicacio(
            java.lang.String codiAplicacio) throws java.lang.Exception {

        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacio)) {
            AplicacioEntity aplicacioEntity = getAplicacioEntityDao()
                    .findByCodi(codiAplicacio);
            if (aplicacioEntity != null) {
                Aplicacio aplicacio = getAplicacioEntityDao().toAplicacio(
                        aplicacioEntity);
                return aplicacio;
            }
        }
        return null;
    }

    /*
     * No le aplicamos restricciones en la obtención de aplicaciones
     * (non-Javadoc)
     * 
     * @see es.caib.seycon.ng.servei.AplicacioServiceBase#
     * handleFindAplicacioByCodiAplicacioSenseRestriccions(java.lang.String)
     */
    protected Aplicacio handleFindAplicacioByCodiAplicacioSenseRestriccions(
            String codiAplicacio) throws Exception {
        AplicacioEntity aplicacioEntity = getAplicacioEntityDao().findByCodi(
                codiAplicacio);
        if (aplicacioEntity != null) {
            Aplicacio aplicacio = getAplicacioEntityDao().toAplicacio(
                    aplicacioEntity);
            return aplicacio;
        }

        return null;
    }

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#findRolsByAplicacio(es.caib.seycon.ng.comu.Aplicacio)
     */
    protected java.util.Collection<Rol> handleFindRolsByCodiAplicacio(
            String codiAplicacio) throws java.lang.Exception {
        // L'autoritzacio application:query [sense_domini,APLICACIONS]
        // NO n'hi ha restricció per veure els rols de l'aplicació
        // si l'usuari pot veure l'aplicació
        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacio)) {
            Collection rols = getRolEntityDao().findByCodiAplicacio(
                    codiAplicacio);
            return getRolEntityDao().toRolList(rols);
        }
        return new Vector();
    }

    protected Collection<Rol> handleFindRolsByCodiAplicacioSenseRestriccions(
            String codiAplicacio) throws Exception {
        Collection rols = getRolEntityDao().findByCodiAplicacio(codiAplicacio);
        return getRolEntityDao().toRolList(rols);
    }

    protected Collection<Aplicacio> handleFindAplicacioByCriteri(String codi, String nom,
            String directoriFonts, String responsable,
            String directoriExecutable, String bd, String rol,
            String gestionableWF) throws Exception
    {
    	int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$

        // Si no en té autorització li retornem un array buit
        // RTIR #29273
    	// LLEVEM AQUEST FILTRE: 
    	//   Ja se filtra més endavant en filtraAplicationsVOCanQuery
        /*if (!AutoritzacionsUsuari.hasQueryAplicacio())
            return new Vector();*/

        // Las obtenemos sin restricciones (en formato VO !!)
        if (codi != null
                && (codi.trim().compareTo("") == 0 || codi.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            codi = null;
        }
        if (nom != null
                && (nom.trim().compareTo("") == 0 || nom.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            nom = null;
        }
        if (directoriFonts != null
                && (directoriFonts.trim().compareTo("") == 0 || directoriFonts //$NON-NLS-1$
                        .trim().compareTo("%") == 0)) { //$NON-NLS-1$
            directoriFonts = null;
        }
        if (responsable != null
                && (responsable.trim().compareTo("") == 0 || responsable.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            responsable = null;
        }
        if (directoriExecutable != null
                && (directoriExecutable.trim().compareTo("") == 0 || directoriExecutable //$NON-NLS-1$
                        .trim().compareTo("%") == 0)) { //$NON-NLS-1$
            directoriExecutable = null;
        }
        if (bd != null
                && (bd.trim().compareTo("") == 0 || bd.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            bd = null;
        }
        if (gestionableWF != null
                && (gestionableWF.trim().compareTo("") == 0 || gestionableWF //$NON-NLS-1$
                        .trim().compareTo("%") == 0)) { //$NON-NLS-1$
            gestionableWF = null;
        }
        Collection aplicacions = getAplicacioEntityDao()
                .findAplicacioByCriteri(codi, nom, directoriFonts, responsable,
                        directoriExecutable, bd, gestionableWF);
        // Aplicamos las restricciones correspondientes
        if (aplicacions != null) {
            Collection<Aplicacio> aplicacionsTrobades = AutoritzacionsUsuari
                    .filtraAplicationsVOCanQuery(getAplicacioEntityDao()
                            .toAplicacioList(aplicacions)); // VO

			// filtrem per cercar aplicacions per rol
            Collection<Aplicacio> res = filtraPerRol(aplicacionsTrobades, rol); 

			// Check maximum number of results
            if ((res != null) && (res.size() > limitResults))
            {
            	return new LinkedList<Aplicacio>(res).subList(0, limitResults);
            }
            
            return res;
        }
        
        return new Vector();
    }

    protected Collection<Aplicacio> handleFindAplicacioByCriteriSenseRestriccions(
            String codi, String nom, String directoriFonts, String responsable,
            String directoriExecutable, String bd, String rol,
            String gestionableWF) throws Exception {

        /*
         * // Si no en té autorització li retornem un array buit // RTIR #29273
         * if (!AutoritzacionsUsuari.hasQueryAplicacio()) return new Vector();
         */

        if (codi != null
                && (codi.trim().compareTo("") == 0 || codi.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            codi = null;
        }
        if (nom != null
                && (nom.trim().compareTo("") == 0 || nom.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            nom = null;
        }
        if (directoriFonts != null
                && (directoriFonts.trim().compareTo("") == 0 || directoriFonts //$NON-NLS-1$
                        .trim().compareTo("%") == 0)) { //$NON-NLS-1$
            directoriFonts = null;
        }
        if (responsable != null
                && (responsable.trim().compareTo("") == 0 || responsable.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            responsable = null;
        }
        if (directoriExecutable != null
                && (directoriExecutable.trim().compareTo("") == 0 || directoriExecutable //$NON-NLS-1$
                        .trim().compareTo("%") == 0)) { //$NON-NLS-1$
            directoriExecutable = null;
        }
        if (bd != null
                && (bd.trim().compareTo("") == 0 || bd.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            bd = null;
        }
        if (gestionableWF != null
                && (gestionableWF.trim().compareTo("") == 0 || gestionableWF //$NON-NLS-1$
                        .trim().compareTo("%") == 0)) { //$NON-NLS-1$
            gestionableWF = null;
        }

        Collection<AplicacioEntity> aplicacions = getAplicacioEntityDao()
                .findAplicacioByCriteri(codi, nom, directoriFonts, responsable,
                        directoriExecutable, bd, gestionableWF);

        if (aplicacions != null) {
            // Filtrem per rol (si fa falta)
            Collection<Aplicacio> filtraPerRol = filtraPerRol(getAplicacioEntityDao()
                    .toAplicacioList(aplicacions), rol);
            // Mirem el número de registres després de filtar per rol (movem el
            // filtre de máx files)
            /*
             * if (filtraPerRol.size() >= 201) { throw new SeyconException(
             * "Massa registres trobats: és necessari donar un filtre més restrictiu."
             * ); }
             */
            return filtraPerRol;
        }
        return new Vector();
    }

    /**
     * Mètode per cercar aplicacions amb el filtre del rol (si existeix)
     * 
     * @param aplicacionsTrobades
     * @param rol
     * @return
     */
    private Collection<Aplicacio> filtraPerRol(Collection<Aplicacio> aplicacionsTrobades, String rol) {
        if (rol != null && rol.trim().compareTo("") != 0 //$NON-NLS-1$
                && rol.trim().compareTo("%") != 0 //$NON-NLS-1$
                && aplicacionsTrobades.size() > 0) {
            String aplicacions = ""; //$NON-NLS-1$
            Iterator aplicacionsIterator = aplicacionsTrobades.iterator();
            while (aplicacionsIterator.hasNext()) {
                Aplicacio aplicacio = (Aplicacio) aplicacionsIterator.next();
                aplicacions += "'" + aplicacio.getCodi() + "'" //$NON-NLS-1$ //$NON-NLS-2$
                        + (aplicacionsIterator.hasNext() ? "," : ""); //$NON-NLS-1$ //$NON-NLS-2$
            }
            String query = "select distinct rol.aplicacio " //$NON-NLS-1$
                    + "from es.caib.seycon.ng.model.RolEntity rol where " //$NON-NLS-1$
                    + "rol.nom like :rol and " + "rol.aplicacio.codi in (" //$NON-NLS-1$ //$NON-NLS-2$
                    + aplicacions + ")"; //$NON-NLS-1$
            Parameter parametres[] = { new Parameter("rol", rol) }; //$NON-NLS-1$
            Collection<AplicacioEntity> aplicacionsTrobadesE = getAplicacioEntityDao().query(query,
                    parametres);
            return getAplicacioEntityDao().toAplicacioList(aplicacionsTrobadesE);
        }
        return aplicacionsTrobades;
    }

    protected AdministracioAplicacio handleCreate(
            AdministracioAplicacio administracioAplicacio) throws Exception {
        // En principi no existeix cap autorització explícita per això
        // es comprova que l'usuari puga fer application:create o
        // applicacion:update

        // Comprovem que l'usuari creador no siga el mateix a qui se li atorga
        if (administracioAplicacio.getCodiUsuari().compareTo(
                getPrincipal().getName()) == 0) {
            throw new SeyconException(
                    Messages.getString("AplicacioServiceImpl.NotAdminPermissionAuthorized")); //$NON-NLS-1$
        }

        // si l'usuari té autorització per crear/modificar l'aplicació
        if (AutoritzacionsUsuari.canCreateAplicacio(administracioAplicacio
                .getCodiAplicacio())
                || AutoritzacionsUsuari
                        .canUpdateAplicacio(administracioAplicacio
                                .getCodiAplicacio())) {
            RolAccountEntity administracioAplicacioEntity = getRolAccountEntityDao()
                    .administracioAplicacioToEntity(administracioAplicacio);
            getRolAccountEntityDao().create(administracioAplicacioEntity);
            administracioAplicacio.setId(administracioAplicacioEntity.getId());
            administracioAplicacio = getRolAccountEntityDao()
                    .toAdministracioAplicacio(administracioAplicacioEntity);
            return administracioAplicacio;
        }
		throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NoPermissionToAsign"), //$NON-NLS-1$
				getPrincipal().getName(), administracioAplicacio.getCodiAplicacio()));
    }

    protected void handleDelete(AdministracioAplicacio administracioAplicacio)
            throws Exception {
        // En principi no existeix cap autorització explícita per això
        // comprovem que l'usuari puga fer application:create o
        // applicacion:update o application:delete

        if (AutoritzacionsUsuari.canCreateAplicacio(administracioAplicacio
                .getCodiAplicacio())
                || AutoritzacionsUsuari
                        .canUpdateAplicacio(administracioAplicacio
                                .getCodiAplicacio())
                || AutoritzacionsUsuari
                        .canDeleteAplicacio(administracioAplicacio
                                .getCodiAplicacio())) {
            RolAccountEntity administracioAplicacioEntity = getRolAccountEntityDao()
                    .administracioAplicacioToEntity(administracioAplicacio);
            getRolAccountEntityDao().remove(administracioAplicacioEntity);

        } else {
			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NoPermissionToDelete"), //$NON-NLS-1$
					getPrincipal().getName(), administracioAplicacio.getCodiAplicacio()));
        }
    }

    protected AdministracioAplicacio handleFindAdministracioAplicacioByNomRolAndCodiAplicacioAndCodiUsuar(
            String nomRol, String codiAplicacio, String codiUsuari)
            throws Exception {
        String query = "select rolsUsuaris " //$NON-NLS-1$
                + "from es.caib.seycon.ng.model.RolAccountEntity rolsUsuaris " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "rolsUsuaris.aplicacioAdministrada.codi = :codiAplicacioAdministrada and " //$NON-NLS-1$
                + "rolsUsuaris.usuari.codi = :codiUsuari and " //$NON-NLS-1$
                + "rolsUsuaris.rol.nom = :nomRol"; //$NON-NLS-1$
        Parameter codiAplicacioAdministradaParameter = new Parameter(
                "codiAplicacioAdministrada", codiAplicacio); //$NON-NLS-1$
        Parameter codiUsuariParameter = new Parameter("codiUsuari", codiUsuari); //$NON-NLS-1$
        Parameter nomRolParameter = new Parameter("nomRol", nomRol); //$NON-NLS-1$
        Parameter[] parameters = { codiAplicacioAdministradaParameter,
                codiUsuariParameter, nomRolParameter };
        Collection rolsUsuarisEntity = getRolAccountEntityDao().query(query,
                parameters);
        if (rolsUsuarisEntity != null) {
            if (rolsUsuarisEntity.size() == 1) {
                RolAccountEntity rolUsuariEntity = (RolAccountEntity) rolsUsuarisEntity
                        .iterator().next();
                AdministracioAplicacio administracioAplicacio = getRolAccountEntityDao()
                        .toAdministracioAplicacio(rolUsuariEntity);
                return administracioAplicacio;
            } else {
                throw new SeyconException(
                        Messages.getString("AplicacioServiceImpl.MoreInstancesFinded")); //$NON-NLS-1$
            }
        } else {
			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.ApplicationNotFounded"), //$NON-NLS-1$
					codiUsuari, codiAplicacio, nomRol)); 
        }
    }

    protected Collection<Usuari> handleFindUsuarisAdministrenAplicacioByNomRolAndCodiAplicacio(
            String nomRol, String codiAplicacio) throws Exception {
        String query = "select rolUsuari.usuari from " //$NON-NLS-1$
                + "es.caib.seycon.ng.model.RolAccountEntity rolUsuari " //$NON-NLS-1$
                + "where rolUsuari.rol.nom = :nomRol and " //$NON-NLS-1$
                + "rolUsuari.aplicacioAdministrada.codi = :codiAplicacio and " //$NON-NLS-1$
                + "rolUsuari.rol.aplicacio.codi = 'SEYCON'"; //$NON-NLS-1$
        Parameter nomRolParameter = new Parameter("nomRol", nomRol); //$NON-NLS-1$
        Parameter codiAplicacioParameter = new Parameter("codiAplicacio", //$NON-NLS-1$
                codiAplicacio);
        Parameter[] parameters = { nomRolParameter, codiAplicacioParameter };
        List<UsuariEntity> usuaris = getUsuariEntityDao().query(query,
                parameters);
        if (usuaris != null) {
            return getUsuariEntityDao().toUsuariList(usuaris);
        }
        return new Vector();
    }

    protected Collection<AdministracioAplicacio> handleFindAdministracioAplicacioByCodiAplicacio(
            String codiAplicacio) throws Exception {
        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacio)) {
            String query = "select rolsUsuaris " //$NON-NLS-1$
                    + "from es.caib.seycon.ng.model.RolAccountEntity rolsUsuaris " //$NON-NLS-1$
                    + "where " //$NON-NLS-1$
                    + "rolsUsuaris.aplicacioAdministrada.codi = :codiAplicacio"; //$NON-NLS-1$
            Parameter codiAplicacioParameter = new Parameter("codiAplicacio", //$NON-NLS-1$
                    codiAplicacio);
            Parameter[] parameters = { codiAplicacioParameter };
            Collection aplicacions = getRolAccountEntityDao().query(query,
                    parameters);
            if (aplicacions != null) {
                return getRolAccountEntityDao().toAdministracioAplicacioList(
                        aplicacions);
            }
        }
        return new Vector();
    }

    protected Collection<Usuari> handleFindUsuarisAmbPermisosActualitzacioByCodiAplicacio(
            String codiAplicacio) {
        String query = "select distinct rolusu.usuari from " //$NON-NLS-1$
                + "es.caib.seycon.ng.model.RolAccountEntity rolusu " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + " ( (rolusu.rol.nom = 'SC_ADMINISTRADOR_SEGURETAT') or " //$NON-NLS-1$
                + "(rolusu.rol.nom = 'SC_RESPONSABLE') or " //$NON-NLS-1$
                + "(rolusu.rol.nom = 'SC_RESPONSABLE_SEGURETAT') ) and " //$NON-NLS-1$
                + "(rolusu.aplicacioAdministrada.codi = :codiAplicacio)"; //$NON-NLS-1$
        Parameter codiAplicacioParameter = new Parameter("codiAplicacio", //$NON-NLS-1$
                codiAplicacio);
        Parameter[] parameters = { codiAplicacioParameter };
        List<UsuariEntity> usuaris = getUsuariEntityDao().query(query,
                parameters);
        if (usuaris != null) {
            return getUsuariEntityDao().toUsuariList(usuaris);
        }
        return new Vector();
    }

    protected Collection<Aplicacio> handleFindAplicacionsActualitzablesByCodiUsuari(
            String codiUsuari) {
        String query = "select distinct rolusu.aplicacioAdministrada from " //$NON-NLS-1$
                + "es.caib.seycon.ng.model.RolAccountEntity rolusu " //$NON-NLS-1$
                + "where " + "(" + "(rolusu.rol.nom = 'SC_ADMINISTRADOR') or " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + "(rolusu.rol.nom = 'SC_ADMINISTRADOR_SEGURETAT') or " //$NON-NLS-1$
                + "(rolusu.rol.nom = 'SC_RESPONSABLE') or " //$NON-NLS-1$
                + "(rolusu.rol.nom = 'SC_RESPONSABLE_SEGURETAT') " + ") and " //$NON-NLS-1$ //$NON-NLS-2$
                + "(rolusu.usuari.codi = :codiUsuari)"; //$NON-NLS-1$
        Parameter codiUsuariParameter = new Parameter("codiUsuari", codiUsuari); //$NON-NLS-1$
        Parameter[] parameters = { codiUsuariParameter };
        List<AplicacioEntity> aplicacions = getAplicacioEntityDao().query(query,
                parameters);
        if (aplicacions != null) {
            return getAplicacioEntityDao().toAplicacioList(aplicacions);
        }
        return new Vector();
    }

    protected Collection<Aplicacio> handleFindAplicacionsGestionablesWFAdministradesByCodiUsuari(
            String codiUsuari) throws Exception {
        String query = "select distinct rolusu.aplicacioAdministrada from " //$NON-NLS-1$
                + "es.caib.seycon.ng.model.RolAccountEntity rolusu " //$NON-NLS-1$
                + "where " + "(" + "(rolusu.rol.nom = 'SC_ADMINISTRADOR') or " //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                + "(rolusu.rol.nom = 'SC_ADMINISTRADOR_SEGURETAT') or " //$NON-NLS-1$
                + "(rolusu.rol.nom = 'SC_RESPONSABLE') or " //$NON-NLS-1$
                + "(rolusu.rol.nom = 'SC_RESPONSABLE_SEGURETAT') " + ") and " //$NON-NLS-1$ //$NON-NLS-2$
                + "(rolusu.usuari.codi = :codiUsuari) and " //$NON-NLS-1$
                + "(rolusu.aplicacioAdministrada is not null and " //$NON-NLS-1$
                + " rolusu.aplicacioAdministrada.gestionableWF='S')"; //$NON-NLS-1$
        Parameter codiUsuariParameter = new Parameter("codiUsuari", codiUsuari); //$NON-NLS-1$
        Parameter[] parameters = { codiUsuariParameter };
        List<AplicacioEntity> aplicacions = getAplicacioEntityDao().query(query,
                parameters);
        if (aplicacions != null) {
            return getAplicacioEntityDao().toAplicacioList(aplicacions);
        }
        return new Vector();

    }

    protected Collection<Rol> handleFindRolsByFiltre(String nom, String descripcio,
            String defecte, String baseDeDades, String contrasenya,
            String codiAplicacio) throws Exception {//6param
        if (nom != null
                && (nom.trim().compareTo("") == 0 || nom.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            nom = null;
        }
        if (descripcio != null
                && (descripcio.trim().compareTo("") == 0 || descripcio.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            descripcio = null;
        }
        if (defecte != null
                && (defecte.trim().compareTo("") == 0 || defecte.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            defecte = null;
        }
        if (baseDeDades != null
                && (baseDeDades.trim().compareTo("") == 0 || baseDeDades.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            baseDeDades = null;
        }
        if (contrasenya != null
                && (contrasenya.trim().compareTo("") == 0 || contrasenya.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            contrasenya = null;
        }
        if (codiAplicacio != null
                && (codiAplicacio.trim().compareTo("") == 0 || codiAplicacio //$NON-NLS-1$
                        .trim().compareTo("%") == 0)) { //$NON-NLS-1$
            codiAplicacio = null;
        }
        Collection<RolEntity> rols = getRolEntityDao().findRolsByFiltre(nom, descripcio,
                defecte, baseDeDades, contrasenya, codiAplicacio);
        if (rols != null) {
            // Filtrem els rols per l'aplicació
            // rol application:query [SENSE_DOMINI, APLICACIONS]

            Collection<RolEntity> rolsPermis = AutoritzacionsUsuari
                    .filtraRolsAplicationsCanQuery(rols);

            // Mirem si la cerca filtrada obté massa resultats
            if (rolsPermis.size() >= 201) {
                throw new SeyconException(
                        Messages.getString("AplicacioServiceImpl.VeryRegFinded")); //$NON-NLS-1$
            }

            return getRolEntityDao().toRolList(rolsPermis);
        }
        return new Vector();
    }

    protected Collection handleFindRolsByFiltre(String nom, String descripcio,
		String defecte, String baseDeDades, String contrasenya,
		String codiAplicacio, String gestionableWF) throws Exception
	{
    	int limitResults = Integer.parseInt(System.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
    	
        if (nom != null
                && (nom.trim().compareTo("") == 0 || nom.trim().compareTo("%") == 0)) { //$NON-NLS-1$ //$NON-NLS-2$
            nom = null;
        }
        if (descripcio != null
                && (descripcio.trim().compareTo("") == 0 || descripcio.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            descripcio = null;
        }
        if (defecte != null
                && (defecte.trim().compareTo("") == 0 || defecte.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            defecte = null;
        }
        if (baseDeDades != null
                && (baseDeDades.trim().compareTo("") == 0 || baseDeDades.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            baseDeDades = null;
        }
        if (contrasenya != null
                && (contrasenya.trim().compareTo("") == 0 || contrasenya.trim() //$NON-NLS-1$
                        .compareTo("%") == 0)) { //$NON-NLS-1$
            contrasenya = null;
        }
        if (codiAplicacio != null
                && (codiAplicacio.trim().compareTo("") == 0 || codiAplicacio //$NON-NLS-1$
                        .trim().compareTo("%") == 0)) { //$NON-NLS-1$
            codiAplicacio = null;
        }
        if (gestionableWF != null
                && (gestionableWF.trim().compareTo("") == 0 || gestionableWF //$NON-NLS-1$
                        .trim().compareTo("%") == 0)) { //$NON-NLS-1$
            gestionableWF = null;
        }
        Collection rols = getRolEntityDao().findRolsByFiltreGestionablesWF(nom,
                descripcio, defecte, baseDeDades, contrasenya, codiAplicacio,
                gestionableWF);
        if (rols != null) {
            Collection rolsFiltrats = AutoritzacionsUsuari
                    .filtraRolsAplicationsCanQuery(rols);
        
        	// Check maximum number of results
            if (rolsFiltrats.size() > limitResults)
            {
            	return getRolEntityDao().toRolList(rolsFiltrats)
					.subList(0, limitResults);
            }
            
            return getRolEntityDao().toRolList(rolsFiltrats);
        }
        
        return new Vector();
    }

    protected Rol handleFindRolByNomRolAndCodiAplicacioAndCodiDispatcher(
            java.lang.String nomRol, String codiAplicacio, String codiDispatcher)
            throws java.lang.Exception {
        // Cap dels tres paràmetres pot ésser null
        // Mirem l'autorització de l'aplicació (fer query als rols de la app
        // no requereixen tindre una autorització específica)
        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacio)) {
            RolEntity rolEntity = getRolEntityDao()
                    .findByNomRolAndCodiAplicacioAndCodiDispatcher(nomRol,
                            codiAplicacio, codiDispatcher);
            if (rolEntity != null) {
                return getRolEntityDao().toRol(rolEntity);
            } else
                return null;
        } else {
			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NoAccessToRol"),  //$NON-NLS-1$
					getPrincipal().getName(), nomRol));
        }

    }

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#findRolsByCodiUsuari(java.lang.String)
     */
    protected java.util.Collection<Rol> handleFindRolsByCodiUsuari(
            java.lang.String codiUsuari) throws java.lang.Exception {
        Collection rols = getRolEntityDao().findRolsByCodiUsuari(codiUsuari);// RolEntity
        if (rols != null) {
            // Si l'usuari peticionari es l'usuari on se demanen, no filtrem
            // (!!)
            Collection<RolEntity> rolsFiltrats = new ArrayList();
            if (getPrincipal().getName().compareTo(codiUsuari) != 0) {// altre
                                                                      // usuari
                // rolsFiltrats: són rolsEntity
                rolsFiltrats = AutoritzacionsUsuari
                        .filtraRolsAplicationsCanQuery(rols);
            } else { // per si mateix no es filtra
                rolsFiltrats = rols;
            }
            // Passem a VO:
            return getRolEntityDao().toRolList(rolsFiltrats);
        }
        return new Vector();
    }

    protected java.util.Collection<Rol> handleGetRols() throws java.lang.Exception {
        Collection<RolEntity> col = getRolEntityDao().loadAll();// RolEntity
        col = AutoritzacionsUsuari.filtraRolsAplicationsCanQuery(col);
        return getRolEntityDao().toRolList(col);
    }

    protected Collection<Usuari> handleFindUsuarisByNomRolAndCodiAplicacioRolAndCodiDispatcher(
            String nomRol, String codiAplicacio, String codiDispatcher)
            throws Exception {
        Rol rol = findRolByNomRolAndCodiAplicacioAndCodiDispatcher(nomRol,
                codiAplicacio, codiDispatcher);

        // NOTA: l'autorització ja s'ha verificat en el find
        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacio)) {
            RolEntity rolEntity = getRolEntityDao().rolToEntity(rol);
            Collection<Usuari> toReturn = new LinkedList<Usuari>();
            for (RolAccountEntity ra: rolEntity.getAccounts()) {
            	AccountEntity acc = ra.getAccount();
            	if (acc.getType().equals (AccountType.USER) && acc.getUsers().size() == 1) 
            	{
            		UsuariEntity user = acc.getUsers().iterator().next().getUser();
            		toReturn.add (getUsuariEntityDao().toUsuari(user));
            			
            	}
            }
            return toReturn;
        } else {
			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NotPermisionToSearch"), //$NON-NLS-1$
					getPrincipal().getName(), codiAplicacio));
        }
    }

    protected Collection<RolAccount> handleFindRolsUsuariByNomRolAndCodiAplicacioRolAndCodiDispatcher(
            String nomRol, String codiAplicacio, String codiDispatcher) throws InternalErrorException {
        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacio)) {
        	RolEntity rolEntity = getRolEntityDao()
                    .findByNomRolAndCodiAplicacioAndCodiDispatcher(nomRol,
                            codiAplicacio, codiDispatcher);
        	if (!AutoritzacionsUsuari.canQueryAplicacio(rolEntity.getAplicacio().getCodi()))
        		throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NoAccessToRol"),  //$NON-NLS-1$
					getPrincipal().getName(), nomRol));

            List<RolAccount> toReturn = new LinkedList<RolAccount>();
            for (RolAccountEntity ra: rolEntity.getAccounts()) {
            	toReturn.add (getRolAccountEntityDao().toRolAccount(ra));
            }
    		getSoDRuleService().qualifyRolAccountList(toReturn);
            return toReturn;
        } else {
			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NotPermisionToSearch"), //$NON-NLS-1$
					getPrincipal().getName(), codiAplicacio));
        }
    }

    protected Rol handleCreate(Rol rol) throws Exception {
        // if (usuariPotActualitzarAplicacio(rol.getCodiAplicacio())) {

        if (AutoritzacionsUsuari.canCreateAplicacio(rol.getCodiAplicacio())
                || AutoritzacionsUsuari.canUpdateAplicacio(rol
                        .getCodiAplicacio())) {

            Collection rolsMateixNomMateixDispatcher = getRolEntityDao()
                    .findRolsByFiltre(rol.getNom(), null, null,
                            rol.getBaseDeDades(), null, null);

            // No permitim crear un rol amb el mateix nom y base de dades si ja
            // existeix un altre
            if (rolsMateixNomMateixDispatcher.size() != 0) {
                Iterator it = rolsMateixNomMateixDispatcher.iterator();
                if (it.hasNext()) {
                    String aplicacio = ((RolEntity) it.next()).getAplicacio()
                            .getCodi();

					throw new SeyconException(
							String.format(Messages.getString("AplicacioServiceImpl.ExistentRole"),  //$NON-NLS-1$
									rol.getNom(), rol.getBaseDeDades(), aplicacio));
                }
            }

            // Obtenemos la entidad asociada al VO
            RolEntity rolEntity = getRolEntityDao().rolToEntity(rol);
            // Creamos la entidad asociada al VO Rol
            getRolEntityDao().create(rolEntity);

            return getRolEntityDao().toRol(rolEntity);
        }
        /*
         * throw new SeyconException("Usuari amb codi '" +
         * getPrincipal().getName() +
         * "' no pot actualitzar l'aplicació amb codi '" +
         * rol.getCodiAplicacio() + "'.");
         */
        else
            throw new SeyconAccessLocalException("AplicacioService", //$NON-NLS-1$
                    "create (Rol)", "application:update, application:create", //$NON-NLS-1$ //$NON-NLS-2$
                    Messages.getString("AplicacioServiceImpl.NotAuthorizedToManageRol")); //$NON-NLS-1$
    }

    protected void handleDelete(Rol rol) throws Exception {
        // if (usuariPotActualitzarAplicacio(rol.getCodiAplicacio())) {
        if (AutoritzacionsUsuari.canCreateAplicacio(rol.getCodiAplicacio())
                || AutoritzacionsUsuari.canUpdateAplicacio(rol
                        .getCodiAplicacio())
                || AutoritzacionsUsuari.canDeleteAplicacio(rol
                        .getCodiAplicacio())) {

            RolEntity rolEntity = getRolEntityDao()
                    .findByNomRolAndCodiAplicacioAndCodiDispatcher(
                            rol.getNom(), rol.getCodiAplicacio(),
                            rol.getBaseDeDades());
            
        	getSoDRuleService().internalRemovingRole(rolEntity.getId());
            getRolEntityDao().remove(rolEntity);
        } else {
            throw new SeyconAccessLocalException(
                    "AplicacioService", //$NON-NLS-1$
                    "delete (Rol)", //$NON-NLS-1$
                    "application:delete, application:update, application:create", //$NON-NLS-1$
                    Messages.getString("AplicacioServiceImpl.NotAuthorizedToManageRol")); //$NON-NLS-1$
            /*
             * throw new SeyconException("Usuari amb codi '" +
             * getPrincipal().getName() +
             * "' no pot actualitzar l'aplicació amb codi '" +
             * rol.getCodiAplicacio() + "'.");
             */
        }
    }

    protected Rol handleUpdate(Rol rol) throws Exception {
        if (AutoritzacionsUsuari.canCreateAplicacio(rol.getCodiAplicacio())
                || AutoritzacionsUsuari.canUpdateAplicacio(rol
                        .getCodiAplicacio())) {

            // Realizamos las operaciones necesarias a nivel de aplicación
            RolEntity rolEntity = getRolEntityDao().rolToEntity(rol);
            getRolEntityDao().update(rolEntity); // actualizamos cambios del rol

            return getRolEntityDao().toRol(rolEntity);
        }

		throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.UpdateApplicationError"), //$NON-NLS-1$
				getPrincipal().getName(), rol.getCodiAplicacio()));
    }

    protected RolAccount handleCreate(RolAccount rolsUsuaris)
            throws Exception {
        String codiAplicacio = rolsUsuaris.getCodiAplicacio();
        
        if (AutoritzacionsUsuari.canCreateUserRole(rolsUsuaris,
                getGrupEntityDao())) {
        	if (rolsUsuaris.getAccountId() == null && rolsUsuaris.getAccountName() != null)
        	{
        		AccountEntity acc = getAccountEntityDao().findByNameAndDispatcher(rolsUsuaris.getAccountName(), rolsUsuaris.getBaseDeDades());
        		if (acc != null)
        			rolsUsuaris.setAccountId(acc.getId());
        	}
        	// Verify the user has one account
        	if (rolsUsuaris.getAccountId() == null && rolsUsuaris.getCodiUsuari() != null)
        	{
            	Account account = null;
        		Security.nestedLogin(Security.getCurrentAccount(), new String[] { 
        			Security.AUTO_USER_QUERY+Security.AUTO_ALL,
        			Security.AUTO_ACCOUNT_QUERY, 
        			Security.AUTO_ACCOUNT_CREATE,
        			Security.AUTO_ACCOUNT_QUERY+Security.AUTO_ALL, 
        			Security.AUTO_ACCOUNT_CREATE+Security.AUTO_ALL});
            	try {
            		List<UserAccount> accounts = getAccountService().findUserAccounts(rolsUsuaris.getCodiUsuari(), rolsUsuaris.getBaseDeDades());
            		if (accounts.size() > 1)
            		{
            			throw new NeedsAccountNameException();
            		}
            		else if (accounts.size() == 0)
            		{
            			Usuari usu = getUsuariService().findUsuariByCodiUsuari(rolsUsuaris.getCodiUsuari());
            			DispatcherEntity dispatcher = getDispatcherEntityDao().findByCodi (rolsUsuaris.getBaseDeDades());
            			if (dispatcher == null)
            				throw new InternalErrorException(
    							String.format(Messages.getString("AplicacioServiceImpl.UnknownSystem"), //$NON-NLS-1$
    								rolsUsuaris.getBaseDeDades()));
            			account = getAccountService().createAccount(usu, getDispatcherEntityDao().toDispatcher(dispatcher), null);
            		}
            		else
            		{
            			account = accounts.iterator().next();
            		}
            	} finally {
            		Security.nestedLogoff();
            	}
        		rolsUsuaris.setAccountId(account.getId());
        	}
        	
            // Check group holder
        	checkGroupHolder (rolsUsuaris);
        	
            RolAccountEntity rolsUsuarisEntity = getRolAccountEntityDao()
                    .rolAccountToEntity(rolsUsuaris);
            // Disable assigning roles to himself
            for (UserAccountEntity ua: rolsUsuarisEntity.getAccount().getUsers())
            {
            	if (ua.getUser().getCodi().equals(getPrincipal().getName()))
            	{
                    throw new SeyconException(
                            Messages.getString("AplicacioServiceImpl.UserAddRolError")); //$NON-NLS-1$
            	}
            }
            // Enable or disable on dates
            rolsUsuarisEntity.setEnabled(getEnableState(rolsUsuarisEntity));
            // Check for Sod Rules
            SoDRule rule = getSoDRuleService().isAllowed(rolsUsuaris);
            if (rule != null && rule.getRisk() == SoDRisk.SOD_FORBIDDEN)
            {
            	throw new InternalErrorException (String.format(Messages.getString("AplicacioServiceImpl.SoDRuleNotAllowRole") //$NON-NLS-1$
            					, rule.getName()));
            }
            // Launch workflow approval process
            boolean nwap = needsWorkflowApprovalProcess(rolsUsuarisEntity);
            
           	rolsUsuarisEntity.setApprovalPending(nwap);
           	
            getRolAccountEntityDao().create(rolsUsuarisEntity);
            AccountEntity account = rolsUsuarisEntity.getAccount();
            account.getRoles().add(rolsUsuarisEntity);
            rolsUsuaris = getRolAccountEntityDao().toRolAccount(rolsUsuarisEntity);
        	
            if (nwap)
            	launchWorkflowApprovalProcess(rolsUsuarisEntity);
            else
            	getAccountEntityDao().propagateChanges(account);
            
            enableOrDisableOnDates (rolsUsuaris, rolsUsuarisEntity);
            
            return rolsUsuaris;
        }
		throw new SeyconAccessLocalException("aplicacioService", "create (RolAccount)", "user:role:create", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("AplicacioServiceImpl.UnableCreateRol"), codiAplicacio)); //$NON-NLS-1$

    }

    /**
     * @param rolAccountEntity
     * @throws InternalErrorException 
	 */
	private boolean needsWorkflowApprovalProcess (RolAccountEntity rolAccountEntity) throws InternalErrorException
	{
		RolEntity role = rolAccountEntity.getRol();
		if (role != null && "S".equals(role.getGestionableWF()))
		{
			AplicacioEntity app = role.getAplicacio();
			if (app != null && app.getApprovalProcess() != null)
			{
				return true;
			}
		}
		return false;
	}

    /**
     * @param rolAccountEntity
     * @throws InternalErrorException 
	 */
	private void launchWorkflowApprovalProcess (RolAccountEntity rolAccountEntity) throws InternalErrorException
	{
		RolEntity role = rolAccountEntity.getRol();
		if (role != null && "S".equals(role.getGestionableWF()))
		{
			AplicacioEntity app = role.getAplicacio();
			if (app != null && app.getApprovalProcess() != null)
			{
				List def = getBpmEngine().findProcessDefinitions(app.getApprovalProcess(), PredefinedProcessType.ROLE_APPROVAL);
				if (def.isEmpty())
					throw new InternalErrorException ("Approval process %s for application %s is not available",
									app.getApprovalProcess(), app.getCodi());
				JbpmContext ctx = getBpmEngine().getContext();
				try {
					ProcessInstance pi = ctx.newProcessInstance(app.getApprovalProcess());
					RolAccount ra = getRolAccountEntityDao().toRolAccount(rolAccountEntity);
		            SoDRule rule = getSoDRuleService().isAllowed(ra);
		            if (rule != null)
		            	ra.setSodRisk(rule.getRisk());

					pi.getContextInstance().createVariable("request", ra);
					pi.getContextInstance().createVariable("requesterAccount", Security.getCurrentAccount());
					pi.getContextInstance().createVariable("requesterUser", Security.getCurrentUser());
					pi.signal();
					ctx.save(pi);
					
					for (UserAccountEntity ua: rolAccountEntity.getAccount().getUsers())
					{
    					UsuariWFProcess uwp = new UsuariWFProcess();
    					uwp.setCodiUsuari(ua.getUser().getCodi());
    					uwp.setIdProces(pi.getId());
    					uwp.setFinalitzat(false);
    					getUsuariService().create(uwp);
					}
					rolAccountEntity.setApprovalProcess(pi.getId());
				} finally {
					ctx.close();
				}
				getRolAccountEntityDao().update(rolAccountEntity);
			}
		}
	}

	UsuariEntity getAccountUser (Long accountId)
    {
    	// Guess usuari
    	AccountEntity acc = getAccountEntityDao().load(accountId);
    	if (acc.getType().equals(AccountType.USER))
    	{
    		for (UserAccountEntity ua: acc.getUsers())
    		{
    			if (ua.getUser() != null)
    				return ua.getUser();
    		}
    		
    	}
    	return null;
    }
    /**
	 * @param rolsUsuaris
     * @throws InternalErrorException 
     * @throws UnknownUserException 
	 */
	private void checkGroupHolder (RolAccount rolsUsuaris) throws InternalErrorException, UnknownUserException
	{
		if ("never".equals(System.getProperty("soffid.entitlement.group.holder")))
			rolsUsuaris.setHolderGroup(null);
		else
		{
			UsuariEntity ue = getAccountUser(rolsUsuaris.getAccountId());
            if (ue == null)
    			rolsUsuaris.setHolderGroup(null);
            else
            {
            	if (rolsUsuaris.getHolderGroup() == null)
            	{
    				GrupEntity primaryGroup = ue.getGrupPrimari();
    				if (primaryGroup.getTipusUnitatOrganizativa() != null && 
    								primaryGroup.getTipusUnitatOrganizativa().isRoleHolder())
    					rolsUsuaris.setHolderGroup(primaryGroup.getCodi());
    			}
            	else if (rolsUsuaris.getHolderGroup().length () == 0 && "optional".equals(System.getProperty("soffid.entitlement.group.holder")))
            	{
            		rolsUsuaris.setHolderGroup(null);			
            	}
            	else
            	{
            		boolean found = false;
            		for (Grup grup: getUsuariService().getUserGroupsHierarchy(ue.getId()))
            		{
            			if (grup.getCodi().equals (rolsUsuaris.getHolderGroup()))
            			{
            				found = true;
            				break;
            			}
            		}
            		if (! found)
            			throw new InternalErrorException (String.format ("User %s is not member of '%s' group", ue.getCodi(), rolsUsuaris.getHolderGroup()));
            	}
            }
		}
	}

	protected void handleDelete(RolAccount rolsUsuaris) throws Exception {
        String codiAplicacio = rolsUsuaris.getCodiAplicacio();
        // if (esAdministracioPersonal(rolsUsuaris) || esAdministradorUsuaris())
        // {
        if (AutoritzacionsUsuari.canDeleteUserRole(rolsUsuaris,
                getGrupEntityDao())) {

        	RolAccountEntity rolsUsuarisEntity = getRolAccountEntityDao()
                    .load(rolsUsuaris.getId());
        	if (rolsUsuarisEntity.getRule() != null)
                throw new InternalErrorException(Messages.getString("AplicacioServiceImpl.CannotRevokeManually")); //$NON-NLS-1$
            // Disable assigning roles to himself
        	UsuariEntity user = null;
            for (UserAccountEntity ua: rolsUsuarisEntity.getAccount().getUsers())
            {
            	if (ua.getUser().getCodi().equals(getPrincipal().getName()))
            	{
                    throw new SeyconException(
                            Messages.getString("AplicacioServiceImpl.UserAddRolError")); //$NON-NLS-1$
            	}
            	user = ua.getUser();
            }
            
            deleteRolAccountEntity(rolsUsuarisEntity, user);
            return;
        } 
        throw new SeyconAccessLocalException("aplicacioService", "delete (RolAccount)", "user:role:delete", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("AplicacioServiceImpl.UnableDeleteRol"), codiAplicacio)); //$NON-NLS-1$
    }

	private void deleteRolAccountEntity (RolAccountEntity rolsUsuarisEntity,
					UsuariEntity user) throws InternalErrorException
	{
		if (rolsUsuarisEntity.isApprovalPending() && rolsUsuarisEntity.getApprovalProcess() != null)
		{
			JbpmContext ctx = getBpmEngine().getContext();
			try 
			{
				ProcessInstance pi = ctx.getProcessInstance(rolsUsuarisEntity.getApprovalProcess());
				if (pi != null && !pi.hasEnded())
				{
					pi.getRootToken().addComment("Requested role has been revoked");
					pi.getRootToken().end();
					pi.end();
					for (TaskInstance ti: pi.getTaskMgmtInstance().getUnfinishedTasks(pi.getRootToken()))
					{
						if (!ti.hasEnded())
							ti.cancel();
					}            			
					ctx.save(pi);
				}
			} finally {
				ctx.close();
			}
		}
		getRolAccountEntityDao().remove(rolsUsuarisEntity);
		
		if (user != null)
			getRuleEvaluatorService().applyRules(user);
		getAccountEntityDao().propagateChanges(rolsUsuarisEntity.getAccount());
	}

	@Override
	protected void handleDenyApproval(RolAccount rolsUsuaris) throws Exception {
        String codiAplicacio = rolsUsuaris.getCodiAplicacio();
        // if (esAdministracioPersonal(rolsUsuaris) || esAdministradorUsuaris())
        // {
        if (AutoritzacionsUsuari.canDeleteUserRole(rolsUsuaris,
                getGrupEntityDao())) {

        	RolAccountEntity rolsUsuarisEntity = getRolAccountEntityDao()
                    .rolAccountToEntity(rolsUsuaris);
        	if (rolsUsuarisEntity.getRule() != null)
                throw new InternalErrorException("This role cannot be manually revoked. It's granted by a rule.");
            // Disable assigning roles to himself
            for (UserAccountEntity ua: rolsUsuarisEntity.getAccount().getUsers())
            {
            	if (ua.getUser().getCodi().equals(getPrincipal().getName()))
            	{
                    throw new SeyconException(
                            Messages.getString("AplicacioServiceImpl.UserAddRolError")); //$NON-NLS-1$
            	}
            }
            
            getRolAccountEntityDao().remove(rolsUsuarisEntity);
            
            return;
        } 
        throw new SeyconAccessLocalException("aplicacioService", "delete (RolAccount)", "user:role:delete", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("AplicacioServiceImpl.UnableDeleteRol"), codiAplicacio)); //$NON-NLS-1$
    }

	protected RolAccount handleUpdate(RolAccount rolsUsuaris)
            throws Exception {
        RolAccount oldRolsUsuaris = getRolAccountEntityDao().toRolAccount(
        		getRolAccountEntityDao().load(rolsUsuaris.getId()));
        String codiAplicacio = rolsUsuaris.getCodiAplicacio();
        // Ho fem emprant autoritzacions (user:role:create [SENSE_DOMINI o
        // GRUPS]
        if (AutoritzacionsUsuari.canCreateUserRole(rolsUsuaris, 
        		getGrupEntityDao()) &&
        	AutoritzacionsUsuari.canCreateUserRole(oldRolsUsuaris, 
        			getGrupEntityDao()))
        {
        	if (! rolsUsuaris.getAccountName().equals(oldRolsUsuaris.getAccountName() ) ||
        			! rolsUsuaris.getAccountDispatcher().equals(oldRolsUsuaris.getAccountDispatcher()) ||
        			! rolsUsuaris.getBaseDeDades().equals(oldRolsUsuaris.getBaseDeDades()) ||
        			! rolsUsuaris.getNomRol().equals(oldRolsUsuaris.getNomRol()))
        	{
        		throw new SeyconAccessLocalException("aplicacioService", "create (RolAccount)", "user:role:create", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        				"Invalid rol grant change. Cannot change rol or account")); //$NON-NLS-1$
        	}
            RolAccountEntity rolsUsuarisEntity = getRolAccountEntityDao()
                    .rolAccountToEntity(rolsUsuaris);

        	rolsUsuarisEntity.setEnabled(getEnableState(rolsUsuarisEntity));
        	
        	getRolAccountEntityDao().update(rolsUsuarisEntity);
        	
            // Actualitzem darrera actualització de l'usuari
            getAccountEntityDao().propagateChanges(rolsUsuarisEntity.getAccount());
            
            return rolsUsuaris;
        }
        else
        	throw new SeyconAccessLocalException("aplicacioService", "create (RolAccount)", "user:role:create", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("AplicacioServiceImpl.UnableCreateRol"), codiAplicacio)); //$NON-NLS-1$
    }

    protected Collection<RolAccount> handleFindRolsUsuarisByCodiUsuari(String codiUsuari)
            throws Exception {// desde usuaris.zul para ver qué roles puede
                              // mostrar
        List<RolAccountEntity> rolusus = getRolAccountEntityDao().findByCodiUsuari(
                codiUsuari);

        if (rolusus != null) {
            // Filtrem per autoritzacions
            Collection<RolAccountEntity> rolsFiltrats = AutoritzacionsUsuari
                    .filtraRolsUsuariAplicationsCanQuery(rolusus);
            List<RolAccount> ra = getRolAccountEntityDao().toRolAccountList(rolsFiltrats);
    		getSoDRuleService().qualifyRolAccountList(ra);
    		return ra;
       }
        return new Vector();
    }

    protected Collection<ContenidorRol> handleFindInformacioTextualJerarquiaRolsUsuariByCodiUsuari(
            String codiUsuari) throws Exception {
        return handleFindInformacioTextualJerarquiaRolsUsuariByCodiUsuari(
                codiUsuari, "N"); //$NON-NLS-1$
    }

    
    protected Collection<ContenidorRol> handleFindInformacioTextualJerarquiaRolsUsuariByCodiUsuari(
            String codiUsuari, String filtraResultats) throws Exception {
        // Obtenemos el usuario
        UsuariEntity usuari = getUsuariEntityDao().findByCodi(codiUsuari);

		UsuariEntity user = getUsuariEntityDao().load(usuari.getId());
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateRoles(radSet, ALL, user);
		LinkedList<ContenidorRol> rgl = new LinkedList<ContenidorRol>();
		for (RolAccountDetail rad: radSet)
		{
			RolGrant rg = null;
			if (rad.rolRol != null)
			{
				RolAssociacioRolEntity rar = rad.rolRol;
                ContenidorRol cContingut = getRolAssociacioRolEntityDao()
                        .toContenidorRol(rar);
                // Y la información del rol contingut
                ContenidorRol crol = getRolEntityDao()
                        .toContenidorRol(rar.getRolContingut());
                if (rad.qualifier != null)
                	crol.setInfoContenidor(crol.getInfoContenidor()+" / "+rad.qualifier.getValor());
                if (rad.qualifierGroup != null)
                	crol.setInfoContenidor(crol.getInfoContenidor()+" / "+rad.qualifierGroup.getCodi());
                if (rad.qualifierAplicacio != null)
                	crol.setInfoContenidor(crol.getInfoContenidor()+" / "+rad.qualifierAplicacio.getCodi());
				// mostrem rol q el té atorgat
                crol.setMetaInfo(String.format(Messages.getString("AplicacioServiceImpl.RoleGrantedToRol"),  //$NON-NLS-1$
                        cContingut.getInfoContenidor()));
                rgl.add(crol); // Añadimos el
                                                 // contenedor

			}
			if (rad.rolGrup != null)
			{
                ContenidorRol cr = getRolEntityDao().toContenidorRol(rad.rolGrup.getRolOtorgat());
                cr.setMetaInfo(
                		String.format(Messages.getString("AplicacioServiceImpl.RoleGrantedToGroup"), //$NON-NLS-1$
                				rad.rolGrup.getGrupPosseidor().getCodi() ));
                rgl.add(cr); // Añadimos el contenedor
			}
		}

        // Filtrado de roles (si se requiere) - per autoritzacions
        if ("S".equals(filtraResultats)) { //$NON-NLS-1$
            // user:role:query [SENSE_DOMINI, GRUPS, APLICACIONS]
            return AutoritzacionsUsuari.filtraContenidorRolCanQuery(codiUsuari,
                    rgl, getGrupEntityDao());
        }

		return rgl;
    }

    protected Collection<RolAccount> handleFindRolsUsuarisByCodiUsuariAndNomRol(
            String codiUsuari, String nomRol) throws Exception {
        Collection<RolAccountEntity> rolusuEntity = getRolAccountEntityDao()
                .findByCodiUsuariAndNomRol(codiUsuari, nomRol);
        if (rolusuEntity != null) {

            Collection<RolAccountEntity> rolsFiltrats = AutoritzacionsUsuari
                    .filtraRolsUsuariAplicationsCanQuery(rolusuEntity);
            List<RolAccount> ra = getRolAccountEntityDao().toRolAccountList(rolsFiltrats);
    		getSoDRuleService().qualifyRolAccountList(ra);
    		return ra;

            /*
             * getRolAccountEntityDao().toRolAccountCollection(rolusuEntity);
             * 
             * Collection rolusu = new ArrayList(); for (Iterator it =
             * rolusuEntity.iterator(); it.hasNext(); ) { RolAccount ru =
             * (RolAccount) it.next(); if
             * (this.usuariPotAccedirAplicacio(ru.getCodiAplicacio())) {
             * rolusu.add(ru); } } return rolusu;
             */
        }
        return null;
    }

    protected Collection<RolAccount> handleFindRolsUsuarisByNomRol(String nomRol)
            throws Exception {
        Collection rolusus = getRolAccountEntityDao().findByNomRol(nomRol);
        if (rolusus != null) {
            Collection<RolAccountEntity> rolsFiltrats = AutoritzacionsUsuari
                    .filtraRolsUsuariAplicationsCanQuery(rolusus);
            List<RolAccount> ra = getRolAccountEntityDao().toRolAccountList(rolsFiltrats);
    		getSoDRuleService().qualifyRolAccountList(ra);
    		return ra;
        }
        return new Vector();
    }

    protected Collection<AdministracioAplicacio> handleFindAdministracioAplicacioByNomRolAndCodiAplicacio(
            String nomRol, String codiAplicacio) throws Exception {
        String query = "select rolsUsuaris " //$NON-NLS-1$
                + "from es.caib.seycon.ng.model.RolAccountEntity rolsUsuaris " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "rolsUsuaris.aplicacioAdministrada.codi = :codiAplicacioAdministrada and " //$NON-NLS-1$
                + "rolsUsuaris.rol.nom = :nomRol"; //$NON-NLS-1$
        Parameter codiAplicacioAdministradaParameter = new Parameter(
                "codiAplicacioAdministrada", codiAplicacio); //$NON-NLS-1$
        Parameter nomRolParameter = new Parameter("nomRol", nomRol); //$NON-NLS-1$
        Parameter[] parameters = { codiAplicacioAdministradaParameter,
                nomRolParameter };
        Collection rolsUsuarisEntity = getRolAccountEntityDao().query(query,
                parameters);
        if (rolsUsuarisEntity != null) {
            if (rolsUsuarisEntity.size() > 0) {
                return getRolAccountEntityDao().toAdministracioAplicacioList(
                        rolsUsuarisEntity);
            }
        }
        return new ArrayList<AdministracioAplicacio>();
    }

    protected Collection<Rol> handleFindRolsByNomDominiAndCodiAplicacio(
            String nomDomini, String codiAplicacio) throws Exception {
        String query = "select rol " //$NON-NLS-1$
                + "from " //$NON-NLS-1$
                + "es.caib.seycon.ng.model.RolEntity rol, " //$NON-NLS-1$
                + "es.caib.seycon.ng.model.DominiAplicacioEntity domini " //$NON-NLS-1$
                + "left join domini.aplicacio aplicacio " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "domini.rol = rol and " //$NON-NLS-1$
                + "domini.nom = :nomDomini and " //$NON-NLS-1$
                + "((:codiAplicacio is null and aplicacio is null) or (aplicacio.codi = :codiAplicacio))"; //$NON-NLS-1$

        Parameter nomDominiParameter = new Parameter("nomDomini", nomDomini); //$NON-NLS-1$
        Parameter nomRolParameter = new Parameter("codiAplicacio", //$NON-NLS-1$
                codiAplicacio);
        Parameter[] parametres = { nomDominiParameter, nomRolParameter };

        Collection rols = getRolEntityDao().query(query, parametres);
        if (rols != null) {
            return getRolEntityDao().toRolList(rols);
        }
        return new Vector();

    }

    protected AdministracioAplicacio handleUpdate(
            AdministracioAplicacio administracioAplicacio) throws Exception {
        if (AutoritzacionsUsuari.canCreateAplicacio(administracioAplicacio
                .getCodiAplicacio())
                || AutoritzacionsUsuari
                        .canUpdateAplicacio(administracioAplicacio
                                .getCodiAplicacio())) {

            RolAccountEntity administracioAplicacioEntity = getRolAccountEntityDao()
                    .administracioAplicacioToEntity(administracioAplicacio);
            getRolAccountEntityDao().update(administracioAplicacioEntity);
            administracioAplicacio.setId(administracioAplicacio.getId());
            administracioAplicacio = getRolAccountEntityDao()
                    .toAdministracioAplicacio(administracioAplicacioEntity);
            return administracioAplicacio;
        }
		throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NotPermisionToUpdate"), //$NON-NLS-1$
				getPrincipal().getName(), administracioAplicacio.getCodiAplicacio()));
    }

    protected Rol handleFindRolById(Long rolId) throws Exception {
        RolEntity rolEntity = getRolEntityDao().findById(rolId);
        if (rolEntity != null) {
            return getRolEntityDao().toRol(rolEntity);
        }
        return null;
    }

    protected Collection handleFindRolsContinguts(Rol contenidor)
            throws Exception {
        String query = "select associacio.rolContingut " //$NON-NLS-1$
                + "from es.caib.seycon.ng.model.RolAssociacioRolEntity associacio " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "associacio.rolContenidor.nom = :nomContenidor and " //$NON-NLS-1$
                + "associacio.rolContenidor.aplicacio.codi = :aplicacioContenidor and " //$NON-NLS-1$
                + "associacio.rolContenidor.baseDeDades.codi = :dispatcherContenidor"; //$NON-NLS-1$
        Parameter nomContenidor = new Parameter("nomContenidor", //$NON-NLS-1$
                contenidor.getNom());
        Parameter aplicacioContenidor = new Parameter("aplicacioContenidor", //$NON-NLS-1$
                contenidor.getCodiAplicacio());
        Parameter dispatcherContenidor = new Parameter("dispatcherContenidor", //$NON-NLS-1$
                contenidor.getBaseDeDades());
        Parameter parameters[] = { nomContenidor, aplicacioContenidor,
                dispatcherContenidor };
        Collection rolCollection = getRolAssociacioRolEntityDao().query(query,
                parameters);
        if (rolCollection != null) {
            return getRolEntityDao().toRolList(rolCollection);
        } else {
            return new Vector();
        }
    }

    protected Collection<Grup> handleFindGrupsPosseidorsdelRolByRol(Rol rol)
            throws Exception {
        RolEntity rolEntity = getRolEntityDao().rolToEntity(rol);
        Collection<RolsGrupEntity> rolsGrups = rolEntity.getGrupsPosseidorsRol();
        Collection<GrupEntity> grups = new ArrayList();
        for (Iterator<RolsGrupEntity> it = rolsGrups.iterator(); it.hasNext(); ) {
        	RolsGrupEntity rge = it.next();
        	grups.add(rge.getGrupPosseidor());
        }
        return getGrupEntityDao().toGrupList(grups);
        
    }

    protected Collection<Rol> handleFindRolsOtorgatsalGrupByGrup(Grup grup)
            throws Exception {
        GrupEntity grupEntity = getGrupEntityDao().grupToEntity(grup);
        Collection<RolsGrupEntity>  rolsGrups = grupEntity.getRolsOtorgatsGrup();
        Collection<RolEntity> rols = new ArrayList();
        for (Iterator<RolsGrupEntity> it = rolsGrups.iterator(); it.hasNext(); ) {
        	RolsGrupEntity rge = it.next();
        	rols.add(rge.getRolOtorgat());
        }
        return getRolEntityDao().toRolList(rols);
        
        
    }

    protected java.security.Principal getPrincipal() {
        return Security.getPrincipal();
    }

    public Collection findRolsUsuariByNomRolAndCodiAplicacioRolAndCodiDispatcher(
            String nomRol, String codiAplicacio, String codiDispatcher) throws InternalErrorException {

        return handleFindRolsUsuariByNomRolAndCodiAplicacioRolAndCodiDispatcher(
                nomRol, codiAplicacio, codiDispatcher);
    }

    /*
     * Obtenim els usuaris a qui es atorgat un rol (per herència) (!!)
     */
    protected Collection handleFindInformacioTextualJerarquiaRolsByRolAplicacioAndDispatcher(
            String nomRole, String codiAplicacioRol, String codiDispatcher) {

        // Obtenemos los roles contenidos del usuario (en modo textual !!)
        Collection resultat = new ArrayList();

        // Obtenemos el ROL
        RolEntity theRol = getRolEntityDao()
                .findByNomRolAndCodiAplicacioAndCodiDispatcher(nomRole,
                        codiAplicacioRol, codiDispatcher);

        // Analizamos las asociaciones con roles
        LinkedList associacioRolAnalitzar = new LinkedList(); // LIFO
        // Posem les associacions amb els rols a qui soc atorgat
        associacioRolAnalitzar.addAll(theRol.getRolAssociacioRolSocContingut());

        // Analizamos los grupos a los que es otorgado
        LinkedList grupsPosseidorsAnalitzar = new LinkedList(); // LIFO
        Collection grupsPos = theRol.getGrupsPosseidorsRol();
        for (Iterator it = grupsPos.iterator(); it.hasNext();) {
            RolsGrupEntity rolPosGrup = (RolsGrupEntity) it.next();
            GrupEntity grupPos = rolPosGrup.getGrupPosseidor();
            grupsPosseidorsAnalitzar.add(grupPos);
        }

        // Analizamos las asociaciones con roles (obtenemos usuarios)
        RolAssociacioRolEntity rolAsocActual = null;
        while ((rolAsocActual = (RolAssociacioRolEntity) associacioRolAnalitzar
                .poll()) != null) {
        	// rol a qui sóc otorgat
            RolEntity rolSocAtorgat = rolAsocActual.getRolContenidor(); 

            // añadimos los grupos poseedores del rol al que somos otorgados
            for (Iterator it = rolSocAtorgat.getGrupsPosseidorsRol().iterator(); it
                    .hasNext();) {
                RolsGrupEntity rolPosGrup = (RolsGrupEntity) it.next();
                GrupEntity grupPos = rolPosGrup.getGrupPosseidor();
                grupsPosseidorsAnalitzar.add(grupPos);
            }
            // Obtenemos los usuarios que tienen este rol
            String tipusDominiAsoc = rolAsocActual.getTipusDomini();
            String valorCodiGrup = null, valorCodiApli = null;
            Long idValorDomAp = null;
            boolean senseDomini = false;
            if (TipusDomini.GRUPS.equals(tipusDominiAsoc)
                    || TipusDomini.GRUPS_USUARI.equals(tipusDominiAsoc)) {
                // Grups
                if (rolAsocActual.getGrupDomini() != null) {
                    valorCodiGrup = rolAsocActual.getGrupDomini().getCodi();
                }
            } else if (TipusDomini.APLICACIONS.equals(tipusDominiAsoc)) {
                // Aplicacions
                if (rolAsocActual.getAplicacioDomini() != null) {
                    valorCodiApli = rolAsocActual.getAplicacioDomini()
                            .getCodi();
                }
            } else if (TipusDomini.DOMINI_APLICACIO.equals(tipusDominiAsoc)) {
                // Valor de domini de aplicació
                if (rolAsocActual.getValorDominiAplicacio() != null) {
                    idValorDomAp = rolAsocActual.getValorDominiAplicacio()
                            .getId();
                }
            } else if (TipusDomini.SENSE_DOMINI.equals(tipusDominiAsoc)
                    || tipusDominiAsoc == null) {
                senseDomini = true;
            }

            // Importante: el tipo de dominio tiene que venir por la asociación,
            // no por el rol (!!)
            Collection rolsUsuRolAtorgat = null;
            if (senseDomini
                    || (valorCodiGrup == null && valorCodiApli == null && idValorDomAp == null)) {
                rolsUsuRolAtorgat = getRolAccountEntityDao()
                        .findByRolAndTipusDomini(
                                rolSocAtorgat.getNom(),
                                rolSocAtorgat.getBaseDeDades().getCodi(),
                                rolSocAtorgat.getAplicacio().getCodi(),
                                tipusDominiAsoc);
            } else
                rolsUsuRolAtorgat = getRolAccountEntityDao()
                        .findByRolAndValorDomini(
                                rolSocAtorgat.getNom(),
                                rolSocAtorgat.getBaseDeDades().getCodi(),
                                rolSocAtorgat.getAplicacio().getCodi(),
                                tipusDominiAsoc, valorCodiGrup, valorCodiApli,
                                idValorDomAp);

            // Guardamos los usuarios y su meta-información
            for (Iterator it = rolsUsuRolAtorgat.iterator(); it.hasNext();) {
                RolAccountEntity ru = (RolAccountEntity) it.next();
                AccountEntity account = ru.getAccount();
                if (account.getType().equals(AccountType.USER) && account.getUsers().size() == 1)
                {
                    UsuariEntity usu = account.getUsers().iterator().next().getUser();
                    ContenidorRol cr = new ContenidorRol();
                    cr.setTipus(usu.getCodi()); // codi d'usuari
                    cr.setInfoContenidor(usu.getNom() + " " //$NON-NLS-1$
                            + usu.getPrimerLlinatge() + " " //$NON-NLS-1$
                            + usu.getSegonLlinatge());
                    cr.setMetaInfo("Rol atorgat al rol='"  //$NON-NLS-1$
                            + getRolAssociacioRolEntityDao().toContenidorRol(
                                    rolAsocActual).getInfoContenidor());
                    cr.setCertificationDate(ru.getCertificationDate());
                    resultat.add(cr); // Añadimos el contenedor
                }
                else
                {
                    ContenidorRol cr = new ContenidorRol();
                    cr.setTipus(account.getName()); // codi d'usuari
                    cr.setInfoContenidor(Messages.getString("AplicacioServiceImpl.SharedAccount")); //$NON-NLS-1$
                    cr.setMetaInfo(String.format(Messages.getString("AplicacioServiceImpl.RoleGrantedToRol") //$NON-NLS-1$
                           ,getRolAssociacioRolEntityDao().toContenidorRol(rolAsocActual).getInfoContenidor()));
                    cr.setCertificationDate(ru.getCertificationDate());
                    resultat.add(cr); // Añadimos el contenedor
                }

            }
        }

        // Ara cerquem els grups fills dels grups a qui és atorgat el rol (i
        // posseidors del rol)
        // Els usuaris dels grups no ténen condició
        HashMap totsGrups = new HashMap();
        GrupEntity grupActual = null;
        while ((grupActual = (GrupEntity) grupsPosseidorsAnalitzar.poll()) != null) {
            if (!totsGrups.containsKey(grupActual.getId())) {
                // Nuevo
                totsGrups.put(grupActual.getId(), grupActual);
                // Sus hijos
                Collection fills = grupActual.getFills();
                if (fills != null && fills.size() != 0) {
                    for (Iterator it = fills.iterator(); it.hasNext();) {
                        GrupEntity fill = (GrupEntity) it.next();
                        if (!totsGrups.containsKey(fill.getId())) {
                            grupsPosseidorsAnalitzar.add(fill);
                        }
                    }
                }
            }
        }

        for (Iterator it = totsGrups.entrySet().iterator(); it.hasNext();) {
            Entry entryGrup = (Entry) it.next();
            GrupEntity grup = (GrupEntity) entryGrup.getValue();
            Collection usuarisGrupP = grup.getUsuarisGrupPrimari();
            Collection usuarisGrupS = grup.getUsuarisGrupSecundari();

            // Els usuaris q ho tenen com a grup primari:
            for (Iterator uit = usuarisGrupP.iterator(); uit.hasNext();) {
                UsuariEntity usu = (UsuariEntity) uit.next();
                ContenidorRol cr = new ContenidorRol();
                cr.setTipus(usu.getCodi()); // codi d'usuari
                // Información específica:
                cr.setInfoContenidor(usu.getNom() + " " //$NON-NLS-1$
                        + usu.getPrimerLlinatge() + " " //$NON-NLS-1$
                        + usu.getSegonLlinatge());
                cr.setMetaInfo("Rol atorgat al grup '" + grup.getCodi() + "'");   //$NON-NLS-1$//$NON-NLS-2$
                resultat.add(cr);
            }

            // Procesem els que ho tenen com a grup secundari
            for (Iterator git = usuarisGrupS.iterator(); git.hasNext();) {
                UsuariGrupEntity usuGrup = (UsuariGrupEntity) git.next();
                ContenidorRol cr = new ContenidorRol();
                UsuariEntity usu = usuGrup.getUsuari();
                cr.setTipus(usuGrup.getUsuari().getCodi()); // codi d'usuari
                // Información específica:
                cr.setInfoContenidor(usu.getNom() + " " //$NON-NLS-1$
                        + usu.getPrimerLlinatge() + " " //$NON-NLS-1$
                        + usu.getSegonLlinatge());
                cr.setMetaInfo("Rol atorgat al grup '" + grup.getCodi() + "'");   //$NON-NLS-1$ //$NON-NLS-2$
                resultat.add(cr);
            }
        }

        // Cerquem els usuaris dels rols
        return resultat;

    }

    protected Collection<Object> handleGetNotificacionsPendents(String codiAplicacio)
            throws Exception {

        // Carreguem totes
        Collection notificacions = null;
        if (codiAplicacio == null)
            notificacions = getNotificacioEntityDao().loadAll();
        else
            notificacions = getNotificacioEntityDao().findByCodiAplicacio(
                    codiAplicacio);

        HashMap<Aplicacio,ArrayList<String>> hNotifica = new HashMap();
        // guardem les aplicacioEntity i la seua Aplicacio(VO) corresponent:
        HashMap aplicacions = new HashMap();

        if (notificacions != null && notificacions.size() > 0) {
        	// van per aplicació
            for (Iterator it = notificacions.iterator(); it.hasNext();) { 
                // Obtenim els components de la notificació:
                NotificacioEntity notif = (NotificacioEntity) it.next();
                AplicacioEntity aplicacio = notif.getAplicacio();
                Aplicacio aplic = null;
                // Fem xanxullo per obtindre l'aplicació només una vegada: per
                // no obtindre claus diferents
                if ((aplic = (Aplicacio) aplicacions.get(aplicacio)) == null) {
                	// Pasem a VO
                    aplic = getAplicacioEntityDao().toAplicacio(aplicacio); 
                    aplicacions.put(aplicacio, aplic);
                }
                RolEntity rol = notif.getRol();
                UsuariEntity usu = notif.getUsuari();

                // Les guardem per codi d'aplicació
                String modif = notif.getInformacio() != null ? notif
                        .getInformacio().indexOf("atorga") != -1 ? "d'atorgació: "   //$NON-NLS-1$ //$NON-NLS-2$
                        : "de revocació: "  //$NON-NLS-1$
                        : "de modificació: ";  //$NON-NLS-1$
                String missatge = notif.getInformacio()
                        + ": " //$NON-NLS-1$
                        + // ":\n     Aplicació: "+aplic.getCodi()+" ["+aplic.getNom()+"]"
                          // +
                        "\n     Usuari: "  //$NON-NLS-1$
                        + usu.getCodi()
                        + " [" //$NON-NLS-1$
                        + usu.getNom()
                        + " " //$NON-NLS-1$
                        + usu.getPrimerLlinatge()
                        + " " //$NON-NLS-1$
                        + usu.getSegonLlinatge()
                        + "]" //$NON-NLS-1$
                        + "\n     Rol: "  //$NON-NLS-1$
                        + rol.getNom()
                        + " [" //$NON-NLS-1$
                        + rol.getDescripcio()
                        + "]" //$NON-NLS-1$
                        + (notif.getDataModificacio() != null ? "\n     Data "  //$NON-NLS-1$
                                + modif
                                + DateUtils.dataToStringFull(notif
                                        .getDataModificacio()) : ""); //$NON-NLS-1$

                ArrayList<String> msg = null;
                // Si es la primera vez, creamos el arrayList
                if ((msg = hNotifica.get(aplic)) == null)
                    msg = new ArrayList<String>();
                msg.add(missatge);
                // Guardem la notificació d'aquesta aplicació
                hNotifica.put(aplic, msg); // cua de missatges de l'aplicació
            }

        }

        return (Collection) hNotifica.entrySet(); // Set<Entry<AplicacioEntity,LinkedList<String>>>
    }

    protected void handleDeleteNotificacionsEnviades(String codiAplicacio,
            Date dataDelete) throws Exception {

        Collection notificacions = null;

        if (codiAplicacio != null) // ordenades per data caducitat
            notificacions = getNotificacioEntityDao().findByCodiAplicacio(
                    codiAplicacio);
        else
        	// ordenades per data caducitat
            notificacions = getNotificacioEntityDao().findAll(); 

        if (notificacions != null) {
            Collection notifBorrar = new ArrayList();
            for (Iterator it = notificacions.iterator(); it.hasNext();) {
                NotificacioEntity notif = (NotificacioEntity) it.next();
                // Sólo borramos los anteriores a la fecha

                if (notif.getDataModificacio().getTime() <= dataDelete
                        .getTime()) {
                    notifBorrar.add(notif);
                }
            }
            if (notifBorrar.size() > 0) {// Las boramos todos juntas
                getNotificacioEntityDao().remove(notifBorrar);
            }
        }
    }


    protected Collection<AutoritzacioRol> handleFindAutoritzacionsRolByNomRolAndCodiAplicacioRolAndCodiDispatcher(
            String nomRole, String codiAplicacioRol, String codiDispatcher)
            throws Exception {
        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacioRol)) {
            RolEntity rolEntity = getRolEntityDao()
                    .findByNomRolAndCodiAplicacioAndCodiDispatcher(nomRole,
                            codiAplicacioRol, codiDispatcher);

            LinkedList<AutoritzacioRol> totPermis = new LinkedList();

            if (rolEntity != null) {
                // Obtenim tota la jerarquía de rols que té heretats
                Collection<RolEntity> totRol = getRolEntityHeretatsByRol(rolEntity);

                if (totRol != null) {
                    for (Iterator<RolEntity> rit = totRol.iterator(); rit
                            .hasNext();) {
                        RolEntity r = rit.next();

                        // Obtenim les autoritzacions (modifiquem select)
                        Collection autos = getAutoritzacioRolEntityDao()
                                .findByIdRol(r.getId());
                        // a VO
                        if (autos != null)
                            for (Iterator it = autos.iterator(); it.hasNext();) {
                                AutoritzacioRolEntity auto = (AutoritzacioRolEntity) it
                                        .next();
                                Collection desc = getAutoritzacioService()
                                        .getInformacioAutoritzacio(
                                                auto.getAutoritzacio());
                                AutoritzacioRol autoVO = getAutoritzacioRolEntityDao()
                                        .toAutoritzacioRol(auto);
                                if (desc != null && desc.iterator().hasNext()) {
                                    AutoritzacioSEU a = (AutoritzacioSEU) desc
                                            .iterator().next();
                                    autoVO.setDescripcio(a.getDescripcio());
                                    autoVO.setAmbit(a.getAmbit());
                                    autoVO.setHereta(a.getHereta());
                                    autoVO.setScope(a.getScope());
                                }
                                totPermis.add(autoVO);
                            }
                    }
                }
            }
            return totPermis;
        } else {
			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.UserNotAccesToApplication"), //$NON-NLS-1$
					getPrincipal().getName(), codiAplicacioRol));
        }

    }

    private Collection<RolEntity> getRolsHeretatsDirectes(RolEntity rolEntity) {
        HashMap<Long, RolEntity> fills = new HashMap();
        Collection rolsAsocHeretats = rolEntity
                .getRolAssociacioRolSocContenidor();
        if (rolsAsocHeretats != null) {
            for (Iterator it = rolsAsocHeretats.iterator(); it.hasNext();) {
                RolAssociacioRolEntity rar = (RolAssociacioRolEntity) it.next();
                RolEntity r = rar.getRolContingut();
                fills.put(r.getId(), r);
            }
        }
        return fills.values();
    }

    private Collection<RolEntity> getRolEntityHeretatsByRol(RolEntity rolEntity) {

        HashMap<Long, RolEntity> totRol = new HashMap<Long, RolEntity>();
        // Llista de rols a analitzar
        LinkedList<RolEntity> rolsAnalitzar = new LinkedList();

        if (rolEntity != null) {
            rolsAnalitzar.add(rolEntity);
            // Cerquem els rols que tinc heretats (continguts)
            RolEntity fill = null;
            while ((fill = rolsAnalitzar.poll()) != null) {
                totRol.put(fill.getId(), fill); // L'afegim
                Collection<RolEntity> hereta = getRolsHeretatsDirectes(fill);
                if (hereta != null)
                    for (Iterator<RolEntity> it = hereta.iterator(); it
                            .hasNext();) {
                        // Si no l'hem analitzat abans
                        RolEntity r = it.next();
                        if (!totRol.containsKey(r.getId()))
                            rolsAnalitzar.add(r);
                    }

            }

        }
        return totRol.values();

    }

    @Override
    protected Collection<AutoritzacioPuntEntrada> handleFindPuntsEntradaRolByNomRolAndCodiAplicacioRolAndCodiDispatcher(
            String nomRole, String codiAplicacioRol, String codiDispatcher)
            throws Exception {

        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacioRol)) {
            RolEntity rolEntity = getRolEntityDao()
                    .findByNomRolAndCodiAplicacioAndCodiDispatcher(nomRole,
                            codiAplicacioRol, codiDispatcher);

            LinkedList<AutoritzacioPuntEntrada> totPermis = new LinkedList();

            if (rolEntity != null) {
                // Obtenim tota la jerarquía de rols que té heretats
                Collection<RolEntity> totRol = getRolEntityHeretatsByRol(rolEntity);

                if (totRol != null) {
                    for (Iterator<RolEntity> rit = totRol.iterator(); rit
                            .hasNext();) {
                        RolEntity r = rit.next();

                        // Obtenim les autoritzacions (modifiquem select)
                        Collection autos = getAutoritzacioPUERolEntityDao()
                                .query(
                                        "select autor from es.caib.seycon.ng.model.AutoritzacioPUERolEntity " //$NON-NLS-1$
                                                + "autor where autor.idRol=:idRol", //$NON-NLS-1$
                                  new Parameter[] {
                                      new Parameter("idRol", r.getId()) //$NON-NLS-1$
                                 });
                        if (autos != null)
                            for (Iterator it = autos.iterator(); it.hasNext();) {
                                AutoritzacioPUERolEntity auto = (AutoritzacioPUERolEntity) it
                                        .next();
                                AutoritzacioPuntEntrada autoVO = getAutoritzacioPUERolEntityDao()
                                        .toAutoritzacioPuntEntrada(auto);
                                // Li canviem la descripció del rol per la
                                // descripció del PUE (!!)
                                autoVO.setCodiEntitatAutoritzada(auto
                                        .getPuntEntrada().getNom());
                                totPermis.add(autoVO);
                            }
                    }
                }
            }
            return totPermis;
        } else {
			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.UserNotAccesToApplication"), //$NON-NLS-1$
					getPrincipal().getName(), codiAplicacioRol));
        }

    }

    @Override
    protected Collection<NetworkAuthorization> handleFindACLsXarxesRolByNomRolAndCodiAplicacioRolAndCodiDispatcher(
            String nomRole, String codiAplicacioRol, String codiDispatcher)
            throws Exception {
        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacioRol)) {
            // Cerquem el rol
            RolEntity rolEntity = getRolEntityDao()
                    .findByNomRolAndCodiAplicacioAndCodiDispatcher(nomRole,
                            codiAplicacioRol, codiDispatcher);

            LinkedList<NetworkAuthorization> totPermis = new LinkedList<NetworkAuthorization>();

            if (rolEntity != null) {
                // Obtenim tota la jerarquía de rols que té heretats
                Collection<RolEntity> totRol = getRolEntityHeretatsByRol(rolEntity);

                if (totRol != null) {
                    for (Iterator<RolEntity> rit = totRol.iterator(); rit
                            .hasNext();) {
                        RolEntity r = rit.next();

                        // Obtenim les autoritzacions del rol
                        List<XarxaACEntity> autosXarxa = getXarxaACEntityDao()
                                .findByNomRolAndCodiAplicacioRolAndCodiDispatcher(
                                        r.getNom(), r.getAplicacio().getCodi(),
                                        r.getBaseDeDades().getCodi()); // XarxaACEntity
                        if (autosXarxa != null && autosXarxa.size() > 0) {
                            
                            totPermis.addAll(getXarxaACEntityDao()
                                    .toNetworkAuthorizationList(autosXarxa));
                        }

                    }
                }
            }

            return totPermis;

        } else {
			throw new SeyconException(
					String.format(Messages.getString("AplicacioServiceImpl.UserNotAccesToApplication"), //$NON-NLS-1$
					getPrincipal().getName(), codiAplicacioRol));
        }
    }
    
    private void populateRoles (Set<RolAccountDetail> rad, int type, UsuariEntity user)
    {
    	if (type == NONE)
    		return;
    	
    	for (UserAccountEntity uac: user.getAccounts())
    	{
    		if (uac.getAccount().getType().equals (AccountType.USER))
    		{
    			populateAccountRoles (rad, type, uac.getAccount(), user);
    		}
    	}
    	
    	if (type == INDIRECT || type == ALL)
    	{
    		populateGroupRoles (rad, ALL, user.getGrupPrimari(), user);
    		for (UsuariGrupEntity ug: user.getGrupsSecundaris())
    		{
    			populateGroupRoles (rad, ALL, ug.getGrup(), user);
    		}
    	}
    	
    }
    
	private void populateGroupRoles(Set<RolAccountDetail> rad, int type, GrupEntity grup, UsuariEntity originUser)
	{
		if (type == NONE)
			return;
		
		for (RolsGrupEntity rg: grup.getRolsOtorgatsGrup())
		{
			for (AccountEntity ae: getAccountsForDispatcher(originUser, null, rg.getRolOtorgat().getBaseDeDades()))
			{
				RolAccountDetail n = new RolAccountDetail(rg, ae);
				n.granteeGrup = grup;
				if ( ! rad.contains(n))
				{
					if (type == DIRECT || type == ALL) 
						rad.add(n);
					if (type == INDIRECT || type == ALL)
					{
						for (AccountEntity acc: getAccountsForDispatcher(originUser, null, rg.getRolOtorgat().getBaseDeDades()))
							populateRoleRoles (rad, ALL, n, originUser, acc);
					}
				}
			}
		}
		if (grup.getPare() != null)
			populateGroupRoles(rad, type, grup.getPare(), originUser);
	}

	private List<AccountEntity> getAccountsForDispatcher (UsuariEntity usuari, 
			AccountEntity account, DispatcherEntity dispatcher)
	{
		List<AccountEntity> accounts = new LinkedList<AccountEntity>();
		if (account == null)
		{
			if (usuari != null)
			{
				for (UserAccountEntity ua2: usuari.getAccounts())
				{
					if (ua2.getAccount().getDispatcher().getId().equals(dispatcher.getId()) &&
						ua2.getAccount().getType().equals(AccountType.USER))
					{
						accounts.add(ua2.getAccount());
					}
				}
			}
		}
		else if (account.getDispatcher().getId().equals(dispatcher.getId()))
		{
			accounts.add(account);
		}
		else if (account.getType().equals(AccountType.USER))
		{
			for (UserAccountEntity ua2: usuari.getAccounts())
			{
				if (ua2.getAccount().getDispatcher().getId().equals(dispatcher.getId()) &&
					ua2.getAccount().getType().equals(AccountType.USER))
				{
					accounts.add(ua2.getAccount());
				}
			}
		}
		if (accounts.isEmpty())
			accounts.add(null);
		
		return accounts;
	}
	
	private void populateRoleRoles(Set<RolAccountDetail> rad, int type,
			RolAccountDetail currentRol, UsuariEntity originUser, AccountEntity originAccount)
	{
		if (type == NONE)
			return;
		
		RolEntity rol = currentRol.granted;
		
		for (RolAssociacioRolEntity ra: rol.getRolAssociacioRolSocContenidor())
		{
			for (AccountEntity ae: getAccountsForDispatcher(originUser,
					originAccount, ra.getRolContingut().getBaseDeDades()))
			{
				RolAccountDetail n = new RolAccountDetail(ra, ae, currentRol);
				n.granteeRol = rol;
				if (rol.getTipusDomini().equals(TipusDomini.GRUPS) && currentRol.qualifierGroup != null)
					n.qualifierGroup = currentRol.qualifierGroup;
				if (rol.getTipusDomini().equals(TipusDomini.GRUPS_USUARI) && currentRol.qualifierGroup != null)
					n.qualifierGroup = currentRol.qualifierGroup;
				if (rol.getTipusDomini().equals(TipusDomini.APLICACIONS) && currentRol.qualifierAplicacio != null)
					n.qualifierAplicacio = currentRol.qualifierAplicacio;
				if (rol.getTipusDomini().equals(TipusDomini.DOMINI_APLICACIO) && currentRol.qualifierGroup != null)
					n.qualifier = currentRol.qualifier;
					
				
				if ( ! rad.contains(n))
				{
					if (type == DIRECT || type == ALL) 
						rad.add(n);
					if (type == INDIRECT || type == ALL)
						populateRoleRoles(rad, ALL, n, originUser, originAccount);
				}
			}
		}
	}

	
	private boolean shouldBeEnabled (RolAccountEntity e)
	{
		if (e.isApprovalPending())
			return false;
		if (e.getStartDate() == null && e.getEndDate() == null)
			return true;
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		Date today = c.getTime();
		c.add(Calendar.DAY_OF_YEAR, 1);
		Date tomorrow = c.getTime();
		
		return (e.getStartDate() == null || tomorrow.after(e.getStartDate())) &&
				(e.getEndDate() == null  || today.equals(e.getEndDate()) || today.before(e.getEndDate()));
	}
	
	private void populateAccountRoles(Set<RolAccountDetail> rad, int type, AccountEntity account, UsuariEntity user)
	{
		for (RolAccountEntity ra: account.getRoles())
		{
			RolAccountDetail n = new RolAccountDetail(ra, account);
			if ( ! rad.contains(n))
			{
				if (type == DIRECT || type == ALL) 
					rad.add(n);
				if ((type == INDIRECT || type == ALL) && shouldBeEnabled(ra))
					populateRoleRoles(rad, ALL, n, user, account);
			}
		}
	}

	@Override
	protected Collection<RolGrant> handleFindRolGrantByAccount(Long accountId)
			throws Exception
	{
		AccountEntity account = getAccountEntityDao().load(accountId);
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateAccountRoles(radSet, DIRECT, account, null);
		LinkedList<RolGrant> rg = new LinkedList<RolGrant>();
		for (RolAccountDetail rad: radSet)
		{
			if (rad.granted.getBaseDeDades().getId().equals (account.getDispatcher().getId()))
			{
				if (rad.rolAccount != null && shouldBeEnabled(rad.rolAccount))
					rg.add (getRolAccountEntityDao().toRolGrant(rad.rolAccount));
				if (rad.rolRol != null)
					rg.add (getRolAssociacioRolEntityDao().toRolGrant(rad.rolRol));
				if (rad.rolGrup != null)
					rg.add (getRolsGrupEntityDao().toRolGrant(rad.rolGrup));
			}
		}
		return rg;
	}

	@Override
	protected Collection<RolAccount> handleFindRolAccountByAccount(long accountId)
			throws Exception
	{
		AccountEntity account = getAccountEntityDao().load(accountId);
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateAccountRoles(radSet, DIRECT, account, null);
		LinkedList<RolAccount> rg = new LinkedList<RolAccount>();
		for (RolAccountDetail rad: radSet)
		{
			if (rad.granted.getBaseDeDades().getId().equals (account.getDispatcher().getId()))
			{
				if (rad.rolAccount != null)
					rg.add (getRolAccountEntityDao().toRolAccount(rad.rolAccount));
			}
		}
		getSoDRuleService().qualifyRolAccountList(rg);
		return rg;
	}

	@Override
	protected Collection<RolGrant> handleFindEffectiveRolGrantByUser(long userId)
			throws Exception
	{
		UsuariEntity user = getUsuariEntityDao().load(userId);
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateRoles(radSet, ALL, user);
		LinkedList<RolGrant> rgl = new LinkedList<RolGrant>();
		for (RolAccountDetail rad: radSet)
		{
			RolGrant rg = null;
			if (rad.rolAccount != null && shouldBeEnabled(rad.rolAccount))
				rg = (getRolAccountEntityDao().toRolGrant(rad.rolAccount));
			if (rad.rolRol != null)
			{
				rg = (getRolAssociacioRolEntityDao().toRolGrant(rad.rolRol));
				if (rad.qualifier != null)
					rg.setDomainValue(rad.qualifier.getValor());
				else if (rad.qualifierAplicacio != null)
					rg.setDomainValue(rad.qualifierAplicacio.getCodi());
				else if (rad.qualifierGroup != null)
					rg.setDomainValue(rad.qualifierGroup.getCodi());
			}
			if (rad.rolGrup != null)
				rg = (getRolsGrupEntityDao().toRolGrant(rad.rolGrup));
			if (rg != null)
			{
    			if (rad.account != null)
    				rg.setOwnerAccountName(rad.account.getName());
    			rgl.add(rg);
			}
		}
		return rgl;
	}

	@Override
	protected Collection<RolGrant> handleFindEffectiveRolGrantByAccount(long accountId)
			throws Exception
	{
		AccountEntity account = getAccountEntityDao().load(accountId);
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		if (account.getType().equals(AccountType.USER))
		{
			for (UserAccountEntity user: account.getUsers())
			{
				populateAccountRoles(radSet, ALL, account, user.getUser());
				populateRoles(radSet, INDIRECT, user.getUser());
			}
		}
		else
			populateAccountRoles(radSet, ALL, account, null);
		LinkedList<RolGrant> rg = new LinkedList<RolGrant>();
		for (RolAccountDetail rad: radSet)
		{
			if (rad.account != null && rad.account.getId().longValue() == accountId)
			{
				if (rad.rolAccount != null && shouldBeEnabled(rad.rolAccount))
					rg.add (getRolAccountEntityDao().toRolGrant(rad.rolAccount));
				if (rad.rolRol != null)
				{
					RolGrant r = getRolAssociacioRolEntityDao().toRolGrant(rad.rolRol);
					if (rad.qualifier != null)
						r.setDomainValue(rad.qualifier.getValor());
					else if (rad.qualifierAplicacio != null)
						r.setDomainValue(rad.qualifierAplicacio.getCodi());
					else if (rad.qualifierGroup != null)
						r.setDomainValue(rad.qualifierGroup.getCodi());
					rg.add(r);
				}
				if (rad.rolGrup != null)
					rg.add (getRolsGrupEntityDao().toRolGrant(rad.rolGrup));
			}
		}
		return rg;
	}

	@Override
	protected Collection<RolGrant> handleFindRolGrantByRol(Long rolId,
			Long numRegistres) throws Exception
	{
        // Contem quants usuaris hi ha registrats
    	CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
    	if (numRegistres != null)
    		config.setMaximumResultSize(new Integer(numRegistres.intValue()));

    	Collection<RolAccountEntity> usuaris = getRolAccountEntityDao().query(
                        "select account from es.caib.seycon.ng.model.RolEntity rol join rol.accounts as account " //$NON-NLS-1$
                                + "where rol.id = :rolId", //$NON-NLS-1$ 
                          new Parameter[] {
                                new Parameter("rolId", rolId)//$NON-NLS-1$
                                },
                          config);
    	// Remove inactive grants
    	for (Iterator<RolAccountEntity> it = usuaris.iterator(); it.hasNext();)
    	{
    		RolAccountEntity rae = it.next();
    		if (! shouldBeEnabled(rae))
    			it.remove();
    	}
    	// Convert to VO
        return getRolAccountEntityDao().toRolGrantList(usuaris);
	}

	@Override
	protected Collection<RolGrant> handleFindEffectiveRolGrantsByRolId(Long rolId)
			throws Exception
	{
		RolEntity rol = getRolEntityDao().load(rolId);
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		
		LinkedList<RolGrant> rg = new LinkedList<RolGrant>();
		populateParentGrantsForRol(radSet, rol, null);
		for (RolAccountDetail rad: radSet)
		{
			RolGrant grant;
			if (rad.rolAccount != null)
				grant = getRolAccountEntityDao().toRolGrant(rad.rolAccount);
			else if (rad.rolRol != null)
			{
				grant = getRolAssociacioRolEntityDao().toRolGrant(rad.rolRol);
				if (rad.qualifier != null)
					grant.setDomainValue(rad.qualifier.getValor());
				else if (rad.qualifierAplicacio != null)
					grant.setDomainValue(rad.qualifierAplicacio.getCodi());
				else if (rad.qualifierGroup != null)
					grant.setDomainValue(rad.qualifierGroup.getCodi());
			}
			else
				grant = getRolsGrupEntityDao().toRolGrant(rad.rolGrup);
			if (rad.account != null)
			{
				grant.setOwnerAccountName(rad.account.getName());
				grant.setOwnerDispatcher(rad.account.getDispatcher().getCodi());
				if (rad.account.getType().equals (AccountType.USER))
				{
					for (UserAccountEntity ua: rad.account.getUsers())
					{
						grant.setUser (ua.getUser().getCodi());
					}
				}
			}
			rg.add(grant);
		}
		return rg;
	}

	private void populateParentGrantsForRol(HashSet<RolAccountDetail> radSet, RolEntity rol, 
			Object originalGrant)
	{
		for (RolAccountEntity rac: rol.getAccounts())
		{
			if (shouldBeEnabled(rac))
			{
    			RolAccountDetail rad;
    			if (originalGrant == null)
    				rad = new RolAccountDetail(rac, rac.getAccount());
    			else if (originalGrant instanceof RolAccountEntity)
    				rad = new RolAccountDetail((RolAccountEntity) originalGrant, rac.getAccount());
    			else if (originalGrant instanceof RolAssociacioRolEntity)
    				rad = new RolAccountDetail((RolAssociacioRolEntity) originalGrant, rac.getAccount(), null);
    			else
    				rad = new RolAccountDetail((RolsGrupEntity) originalGrant, rac.getAccount());
    			if (! radSet.contains(rad))
    			{
    				radSet.add(rad);
    			}
			}
		}
		
		for (RolAssociacioRolEntity ra: rol.getRolAssociacioRolSocContingut())
		{
			populateParentGrantsForRol(radSet, ra.getRolContenidor(), originalGrant == null? ra: originalGrant);
		}

		
		for (RolsGrupEntity rg: rol.getGrupsPosseidorsRol())
		{
			populateParentGrantsForGroup(radSet, rg.getGrupPosseidor(), originalGrant == null ? rg: originalGrant);
		}
	}

	private void populateParentGrantsForGroup(HashSet<RolAccountDetail> radSet,
			GrupEntity grup, Object originalGrant)
	{
		for (UsuariEntity u: grup.getUsuarisGrupPrimari())
		{
			populateParentGrantsForUser(radSet, u, originalGrant);
		}
		
		for ( UsuariGrupEntity sg: grup.getUsuarisGrupSecundari())
		{
			populateParentGrantsForUser(radSet, sg.getUsuari(), originalGrant);
		}

		for ( GrupEntity fill: grup.getFills())
		{
			populateParentGrantsForGroup(radSet, fill, originalGrant);
		}
	}

	private void populateParentGrantsForUser(HashSet<RolAccountDetail> radSet,
			UsuariEntity u, Object originalGrant)
	{
		DispatcherEntity de ;
		if (originalGrant instanceof RolAssociacioRolEntity)
			de = ((RolAssociacioRolEntity)originalGrant).getRolContingut().getBaseDeDades();
		else
			de = ((RolsGrupEntity)originalGrant).getRolOtorgat().getBaseDeDades();
			
		for (AccountEntity acc: getAccountsForDispatcher(u, null, de))
		{
			if (acc != null)
			{
				RolAccountDetail rad;
				if (originalGrant instanceof RolAccountEntity)
					rad = new RolAccountDetail((RolAccountEntity) originalGrant, acc);
				else
					rad = new RolAccountDetail((RolsGrupEntity) originalGrant, acc);
				if ( ! radSet.contains(rad))
				{
					radSet.add(rad);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AplicacioServiceBase#handleEnableOrDisableOnDates(es.caib.seycon.ng.comu.RolAccount)
	 */
	@Override
	protected RolAccount handleEnableOrDisableOnDates (RolAccount rolAccount)
					throws Exception
	{
		RolAccountEntity entity = getRolAccountEntityDao().load(rolAccount.getId());
		enableOrDisableOnDates(rolAccount, entity);
		return rolAccount;
		
	}

	private void enableOrDisableOnDates (RolAccount rolAccount, RolAccountEntity entity)
					throws InternalErrorException
	{
		rolAccount.setEnabled(getEnableState(entity));

		if (entity.isEnabled() != rolAccount.isEnabled())
		{
    		entity.setEnabled(rolAccount.isEnabled());
    		getRolAccountEntityDao().update(entity);
    		getAccountEntityDao().propagateChanges(entity.getAccount());
		}
	}

	private boolean getEnableState (RolAccountEntity entity)
	{
		Date now = new Date ();
		if (entity.getEndDate() != null && entity.getEndDate().before(now))
		{
			return false;
		}
		else if (entity.getStartDate() == null || entity.getStartDate().before(now))
		{
			return true;
		}
		else 
		{
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AplicacioServiceBase#handleEnableOrDisableAllOnDates()
	 */
	@Override
	protected void handleEnableOrDisableAllOnDates () throws Exception
	{
		Date now = new Date();
		for (RolAccountEntity toEnable: getRolAccountEntityDao().findRolAccountToEnable(now))
		{
			toEnable.setEnabled(true);
    		getRolAccountEntityDao().update(toEnable);
    		getAccountEntityDao().propagateChanges(toEnable.getAccount());
		}

		for (RolAccountEntity toDisable: getRolAccountEntityDao().findRolAccountToDisable(now))
		{
			toDisable.setEnabled(false);
    		getRolAccountEntityDao().update(toDisable);
    		getAccountEntityDao().propagateChanges(toDisable.getAccount());
		}

	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AplicacioServiceBase#handleRevokeRolesHoldedOnGroup(long, long)
	 */
	@Override
	protected void handleRevokeRolesHoldedOnGroup (long userId, long groupId)
					throws Exception
	{
		// Check that the group is not assigned as primary or secondary group
		UsuariEntity user = getUsuariEntityDao().load(userId);
		if (user.getGrupPrimari().getId().equals(groupId))
			return;
		
		for (UsuariGrupEntity uge: user.getGrupsSecundaris())
		{
			if (uge.getGrup().getId().equals(groupId))
				return;
		}
		
		GrupEntity ge = getGrupEntityDao().load(groupId);
		for (UserAccountEntity uae: user.getAccounts())
		{
			AccountEntity acc = uae.getAccount();
			
			for (RolAccountEntity rae: new LinkedList<RolAccountEntity>(acc.getRoles()))
			{
				if (rae.getHolderGroup() != null && rae.getHolderGroup().getId().equals (groupId))
				{
					deleteRolAccountEntity(rae, user);
				}
			}
		}
	}

	@Override
	protected Rol handleFindRoleByNameAndSystem(String name, String system)
			throws Exception {
        RolEntity rolEntity = getRolEntityDao()
                .findByNameAndDispatcher(name, system);
        if (rolEntity == null)
        	return null;
        // Cap dels tres paràmetres pot ésser null
        // Mirem l'autorització de l'aplicació (fer query als rols de la app
        // no requereixen tindre una autorització específica)
        if (AutoritzacionsUsuari.canQueryAplicacio(rolEntity.getAplicacio().getCodi())) {
            return getRolEntityDao().toRol(rolEntity);
        } else {
			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NoAccessToRol"),  //$NON-NLS-1$
					getPrincipal().getName(), name));
        }
	}


}

class RolAccountDetail
{
	RolEntity granted;
	GrupEntity qualifierGroup;
	AplicacioEntity qualifierAplicacio;
	ValorDominiAplicacioEntity qualifier;
	RolEntity granteeRol;
	GrupEntity granteeGrup;
	RolAccountEntity rolAccount;
	RolsGrupEntity rolGrup;
	RolAssociacioRolEntity rolRol;
	AccountEntity account;
	
	String hash;
	public RolAccountDetail(RolsGrupEntity rg, AccountEntity account)
	{
		this.account = account; 
		granted = rg.getRolOtorgat();
		rolGrup = rg;
		generateHash();
	}
	
	public RolAccountDetail(RolAssociacioRolEntity ra, AccountEntity account, RolAccountDetail previous)
	{
		this.account = account; 
		granted = ra.getRolContingut();
		qualifier = ra.getValorDominiAplicacio();
		qualifierAplicacio = ra.getAplicacioDomini();
		qualifierGroup = ra.getGrupDomini();
		if (qualifier == null && previous != null)
			qualifier = previous.qualifier;
		if (qualifierAplicacio == null && previous != null)
			qualifierAplicacio = previous.qualifierAplicacio;
		if (qualifierGroup == null && previous != null)
			qualifierGroup = previous.qualifierGroup;
		rolRol = ra;
		generateHash();
	}
	
	public RolAccountDetail(RolAccountEntity ra, AccountEntity account)
	{
		this.account = account; 
		granted = ra.getRol();
		qualifier = ra.getValorDominiAplicacio();
		qualifierAplicacio = ra.getAplicacioAdministrada();
		qualifierGroup = ra.getGrup();
		rolAccount = ra;
		generateHash();
	}
	
	public void generateHash ()
	{
		StringBuffer b = new StringBuffer ();
		if (account == null)
			b.append("?"); //$NON-NLS-1$
		else
			b.append(account.getId());
		b.append(":"); //$NON-NLS-1$
		b.append(granted.getNom());
		b.append("@"); //$NON-NLS-1$
		b.append(granted.getBaseDeDades().getCodi());
		if ( qualifierGroup != null)
			b.append("/").append(qualifierGroup.getCodi()); //$NON-NLS-1$
		if ( qualifierAplicacio != null)
			b.append("/").append(qualifierAplicacio.getCodi()); //$NON-NLS-1$
		if ( qualifier != null)
			b.append("/").append(qualifier.getValor()); //$NON-NLS-1$
		hash = b.toString();
	}
	
	@Override
	public int hashCode()
	{
		return hash.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj instanceof RolAccountDetail)
		{
			RolAccountDetail detail = (RolAccountDetail) obj;
			return detail.hash.equals(hash);
		}
		else
			return false;
	}
	
}
