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

import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AuthorizationEntity;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.EntryPointRoleEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.NetworkAuthorizationEntity;
import com.soffid.iam.model.NoticeEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;

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

import com.soffid.iam.model.Parameter;

import es.caib.seycon.ng.utils.AutoritzacioSEU;
import es.caib.seycon.ng.utils.AutoritzacionsUsuari;
import es.caib.seycon.ng.utils.DateUtils;
import es.caib.seycon.ng.utils.Security;

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
        Collection<InformationSystemEntity> apps = AutoritzacionsUsuari.filtraAplicationsCanQuery(getInformationSystemEntityDao().loadAll());
        List<Aplicacio> aplicacions = getInformationSystemEntityDao().toAplicacioList(apps);
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
    		InformationSystemEntity aplicationsSameCode = getInformationSystemEntityDao().findByCode(aplicacio.getCodi());
    		if(aplicationsSameCode != null)
    			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.CodeAplicationExists"),  //$NON-NLS-1$
    							aplicacio.getCodi())); 
            InformationSystemEntity apl = getInformationSystemEntityDao().aplicacioToEntity(aplicacio);
            getInformationSystemEntityDao().create(apl);
            aplicacio.setId(apl.getId());
            return (getInformationSystemEntityDao().toAplicacio(apl));
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
            InformationSystemEntity aplEntity = getInformationSystemEntityDao().aplicacioToEntity(aplicacio);
            if(!aplEntity.getRoles().isEmpty())
            	throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.IntegrityExceptionRol"), aplEntity.getName()));
            getInformationSystemEntityDao().remove(aplEntity);
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
            getInformationSystemEntityDao().update(getInformationSystemEntityDao().aplicacioToEntity(aplicacio));
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
            InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao().findByCode(codiAplicacio);
            if (aplicacioEntity != null) {
                Aplicacio aplicacio = getInformationSystemEntityDao().toAplicacio(aplicacioEntity);
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
        InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao().findByCode(codiAplicacio);
        if (aplicacioEntity != null) {
            Aplicacio aplicacio = getInformationSystemEntityDao().toAplicacio(aplicacioEntity);
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
            Collection rols = getRoleEntityDao().findByInformationSystem(codiAplicacio);
            return getRoleEntityDao().toRolList(rols);
        }
        return new Vector();
    }

    protected Collection<Rol> handleFindRolsByCodiAplicacioSenseRestriccions(
            String codiAplicacio) throws Exception {
        Collection rols = getRoleEntityDao().findByInformationSystem(codiAplicacio);
        return getRoleEntityDao().toRolList(rols);
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
        Collection aplicacions = getInformationSystemEntityDao().findByFilter(codi, nom, directoriFonts, responsable, directoriExecutable, bd, gestionableWF);
        // Aplicamos las restricciones correspondientes
        if (aplicacions != null) {
            Collection<Aplicacio> aplicacionsTrobades = AutoritzacionsUsuari.filtraAplicationsVOCanQuery(getInformationSystemEntityDao().toAplicacioList(aplicacions)); // VO

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

        Collection<InformationSystemEntity> aplicacions = getInformationSystemEntityDao()
        		.findByFilter(codi, nom, directoriFonts, responsable, directoriExecutable, bd, gestionableWF);

        if (aplicacions != null) {
            // Filtrem per rol (si fa falta)
            Collection<Aplicacio> filtraPerRol = filtraPerRol(getInformationSystemEntityDao().toAplicacioList(aplicacions), rol);
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
            String query = "select distinct rol.informationSystem " //$NON-NLS-1$
                    + "from com.soffid.iam.model.RoleEntity rol where " //$NON-NLS-1$
                    + "rol.name like :rol and " + "rol.informationSystem.name in (" //$NON-NLS-1$ //$NON-NLS-2$
                    + aplicacions + ")"; //$NON-NLS-1$
            Parameter parametres[] = { new Parameter("rol", rol) }; //$NON-NLS-1$
            Collection<InformationSystemEntity> aplicacionsTrobadesE = getInformationSystemEntityDao().query(query, parametres);
            return getInformationSystemEntityDao().toAplicacioList(aplicacionsTrobadesE);
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
            RoleAccountEntity administracioAplicacioEntity = getRoleAccountEntityDao().administracioAplicacioToEntity(administracioAplicacio);
            getRoleAccountEntityDao().create(administracioAplicacioEntity);
            administracioAplicacio.setId(administracioAplicacioEntity.getId());
            administracioAplicacio = getRoleAccountEntityDao().toAdministracioAplicacio(administracioAplicacioEntity);
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
            RoleAccountEntity administracioAplicacioEntity = getRoleAccountEntityDao().administracioAplicacioToEntity(administracioAplicacio);
            getRoleAccountEntityDao().remove(administracioAplicacioEntity);

        } else {
			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NoPermissionToDelete"), //$NON-NLS-1$
					getPrincipal().getName(), administracioAplicacio.getCodiAplicacio()));
        }
    }

    protected Collection<AdministracioAplicacio> handleFindAdministracioAplicacioByCodiAplicacio(
            String codiAplicacio) throws Exception {
        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacio)) {
        	List<RoleAccountEntity> aplicacions = getRoleAccountEntityDao().findByQualifierIS(codiAplicacio);
            if (aplicacions != null) {
                return getRoleAccountEntityDao().toAdministracioAplicacioList(aplicacions);
            }
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
        Collection<RoleEntity> rols = getRoleEntityDao().findRolesByCriteria(nom, descripcio, defecte, baseDeDades, contrasenya, codiAplicacio);
        if (rols != null) {
            // Filtrem els rols per l'aplicació
            // rol application:query [SENSE_DOMINI, APLICACIONS]

            Collection<RoleEntity> rolsPermis = AutoritzacionsUsuari.filtraRolsAplicationsCanQuery(rols);

            // Mirem si la cerca filtrada obté massa resultats
            if (rolsPermis.size() >= 201) {
                throw new SeyconException(
                        Messages.getString("AplicacioServiceImpl.VeryRegFinded")); //$NON-NLS-1$
            }

            return getRoleEntityDao().toRolList(rolsPermis);
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
        Collection rols = getRoleEntityDao().findRolesByManageableWFCriteria(nom, descripcio, defecte, baseDeDades, contrasenya, codiAplicacio, gestionableWF);
        if (rols != null) {
            Collection rolsFiltrats = AutoritzacionsUsuari
                    .filtraRolsAplicationsCanQuery(rols);
        
        	// Check maximum number of results
            if (rolsFiltrats.size() > limitResults)
            {
            	return getRoleEntityDao().toRolList(rolsFiltrats).subList(0, limitResults);
            }
            
            return getRoleEntityDao().toRolList(rolsFiltrats);
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
            RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRol, codiAplicacio, codiDispatcher);
            if (rolEntity != null) {
                return getRoleEntityDao().toRol(rolEntity);
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
        Collection rols = getRoleEntityDao().findRolesByUserName(codiUsuari);// RolEntity
        if (rols != null) {
            // Si l'usuari peticionari es l'usuari on se demanen, no filtrem
            // (!!)
            Collection<RoleEntity> rolsFiltrats = new ArrayList();
            if (getPrincipal().getName().compareTo(codiUsuari) != 0) {// altre
                                                                      // usuari
                // rolsFiltrats: són rolsEntity
                rolsFiltrats = AutoritzacionsUsuari
                        .filtraRolsAplicationsCanQuery(rols);
            } else { // per si mateix no es filtra
                rolsFiltrats = rols;
            }
            // Passem a VO:
            return getRoleEntityDao().toRolList(rolsFiltrats);
        }
        return new Vector();
    }

    protected java.util.Collection<Rol> handleGetRols() throws java.lang.Exception {
        Collection<RoleEntity> col = getRoleEntityDao().loadAll();// RolEntity
        col = AutoritzacionsUsuari.filtraRolsAplicationsCanQuery(col);
        return getRoleEntityDao().toRolList(col);
    }

    protected Collection<Usuari> handleFindUsuarisByNomRolAndCodiAplicacioRolAndCodiDispatcher(
            String nomRol, String codiAplicacio, String codiDispatcher)
            throws Exception {
        Rol rol = findRolByNomRolAndCodiAplicacioAndCodiDispatcher(nomRol,
                codiAplicacio, codiDispatcher);

        // NOTA: l'autorització ja s'ha verificat en el find
        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacio)) {
            RoleEntity rolEntity = getRoleEntityDao().rolToEntity(rol);
            Collection<Usuari> toReturn = new LinkedList<Usuari>();
            for (RoleAccountEntity ra : rolEntity.getAccounts()) {
                AccountEntity acc = ra.getAccount();
                if (acc.getType().equals(AccountType.USER) && acc.getUsers().size() == 1) {
                    UserEntity user = acc.getUsers().iterator().next().getUser();
                    toReturn.add(getUserEntityDao().toUsuari(user));
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
        	RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRol, codiAplicacio, codiDispatcher);
        	if (!AutoritzacionsUsuari.canQueryAplicacio(rolEntity.getInformationSystem().getName()))
        		throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NoAccessToRol"),  //$NON-NLS-1$
					getPrincipal().getName(), nomRol));

            List<RolAccount> toReturn = new LinkedList<RolAccount>();
            for (RoleAccountEntity ra : rolEntity.getAccounts()) {
                toReturn.add(getRoleAccountEntityDao().toRolAccount(ra));
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

            RoleEntity existingRole = getRoleEntityDao()
                    .findByNameAndSystem(rol.getNom(),
                            rol.getBaseDeDades());

            // No permitim crear un rol amb el mateix nom y base de dades si ja
            // existeix un altre
            if (existingRole != null) {
                    String aplicacio = existingRole.getInformationSystem()
                            .getName();

					throw new SeyconException(
							String.format(Messages.getString("AplicacioServiceImpl.ExistentRole"),  //$NON-NLS-1$
									rol.getNom(), rol.getBaseDeDades(), aplicacio));
            }

            // Obtenemos la entidad asociada al VO
            RoleEntity rolEntity = getRoleEntityDao().rolToEntity(rol);
            // Creamos la entidad asociada al VO Rol
            getRoleEntityDao().create(rolEntity);

            return getRoleEntityDao().toRol(rolEntity);
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

            RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(rol.getNom(), rol.getCodiAplicacio(), rol.getBaseDeDades());
            
        	getSoDRuleService().internalRemovingRole(rolEntity.getId());
            getRoleEntityDao().remove(rolEntity);
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
            RoleEntity rolEntity = getRoleEntityDao().rolToEntity(rol);
            getRoleEntityDao().update(rolEntity); // actualizamos cambios del rol

            return getRoleEntityDao().toRol(rolEntity);
        }

		throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.UpdateApplicationError"), //$NON-NLS-1$
				getPrincipal().getName(), rol.getCodiAplicacio()));
    }

    protected RolAccount handleCreate(RolAccount rolsUsuaris)
            throws Exception {
        String codiAplicacio = rolsUsuaris.getCodiAplicacio();
        
        if (AutoritzacionsUsuari.canCreateUserRole(rolsUsuaris, getGroupEntityDao())) {
        	if (rolsUsuaris.getAccountId() == null && rolsUsuaris.getAccountName() != null)
        	{
        		AccountEntity acc = getAccountEntityDao().findByNameAndSystem(rolsUsuaris.getAccountName(), rolsUsuaris.getBaseDeDades());
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
            			SystemEntity dispatcher = getSystemEntityDao().findByName(rolsUsuaris.getBaseDeDades());
            			if (dispatcher == null)
            				throw new InternalErrorException(
    							String.format(Messages.getString("AplicacioServiceImpl.UnknownSystem"), //$NON-NLS-1$
    								rolsUsuaris.getBaseDeDades()));
            			account = getAccountService().createAccount(usu, getSystemEntityDao().toDispatcher(dispatcher), null);
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
        	
            RoleAccountEntity rolsUsuarisEntity = getRoleAccountEntityDao().rolAccountToEntity(rolsUsuaris);
            // Disable assigning roles to himself
            for (UserAccountEntity ua : rolsUsuarisEntity.getAccount().getUsers()) {
                if (ua.getUser().getUserName().equals(getPrincipal().getName())) {
                    throw new SeyconException(Messages.getString("AplicacioServiceImpl.UserAddRolError"));
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
           	
            getRoleAccountEntityDao().create(rolsUsuarisEntity);
            AccountEntity account = rolsUsuarisEntity.getAccount();
            account.getRoles().add(rolsUsuarisEntity);
            rolsUsuaris = getRoleAccountEntityDao().toRolAccount(rolsUsuarisEntity);
        	
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
	private boolean needsWorkflowApprovalProcess(RoleAccountEntity rolAccountEntity) throws InternalErrorException {
		RoleEntity role = rolAccountEntity.getRole();
		if (role != null && "S".equals(role.getManageableWF()))
		{
			InformationSystemEntity app = role.getInformationSystem();
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
	private void launchWorkflowApprovalProcess(RoleAccountEntity rolAccountEntity) throws InternalErrorException {
		RoleEntity role = rolAccountEntity.getRole();
		if (role != null && "S".equals(role.getManageableWF()))
		{
			InformationSystemEntity app = role.getInformationSystem();
			if (app != null && app.getApprovalProcess() != null)
			{
				List def = getBpmEngine().findProcessDefinitions(app.getApprovalProcess(), PredefinedProcessType.ROLE_APPROVAL);
				if (def.isEmpty())
					throw new InternalErrorException("Approval process %s for application %s is not available", app.getApprovalProcess(), app.getName());
				JbpmContext ctx = getBpmEngine().getContext();
				try {
					ProcessInstance pi = ctx.newProcessInstance(app.getApprovalProcess());
					RolAccount ra = getRoleAccountEntityDao().toRolAccount(rolAccountEntity);
		            SoDRule rule = getSoDRuleService().isAllowed(ra);
		            if (rule != null)
		            	ra.setSodRisk(rule.getRisk());

					pi.getContextInstance().createVariable("request", ra);
					pi.getContextInstance().createVariable("requesterAccount", Security.getCurrentAccount());
					pi.getContextInstance().createVariable("requesterUser", Security.getCurrentUser());
					pi.signal();
					ctx.save(pi);
					
					for (UserAccountEntity ua : rolAccountEntity.getAccount().getUsers()) {
                        UsuariWFProcess uwp = new UsuariWFProcess();
                        uwp.setCodiUsuari(ua.getUser().getUserName());
                        uwp.setIdProces(pi.getId());
                        uwp.setFinalitzat(false);
                        getUsuariService().create(uwp);
                    }
					rolAccountEntity.setApprovalProcess(pi.getId());
				} finally {
					ctx.close();
				}
				getRoleAccountEntityDao().update(rolAccountEntity);
			}
		}
	}

	UserEntity getAccountUser(Long accountId) {
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
			UserEntity ue = getAccountUser(rolsUsuaris.getAccountId());
            if (ue == null)
    			rolsUsuaris.setHolderGroup(null);
            else
            {
            	if (rolsUsuaris.getHolderGroup() == null)
            	{
    				GroupEntity primaryGroup = ue.getPrimaryGroup();
    				if (primaryGroup.getUnitType() != null && primaryGroup.getUnitType().isRoleHolder())
    					rolsUsuaris.setHolderGroup(primaryGroup.getName());
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
            			throw new InternalErrorException(String.format("User %s is not member of \'%s\' group", ue.getUserName(), rolsUsuaris.getHolderGroup()));
            	}
            }
		}
	}

	protected void handleDelete(RolAccount rolsUsuaris) throws Exception {
        String codiAplicacio = rolsUsuaris.getCodiAplicacio();
        // if (esAdministracioPersonal(rolsUsuaris) || esAdministradorUsuaris())
        // {
        if (AutoritzacionsUsuari.canDeleteUserRole(rolsUsuaris, getGroupEntityDao())) {

        	RoleAccountEntity rolsUsuarisEntity = getRoleAccountEntityDao().load(rolsUsuaris.getId());
        	if (rolsUsuarisEntity.getRule() != null)
                throw new InternalErrorException(Messages.getString("AplicacioServiceImpl.CannotRevokeManually")); //$NON-NLS-1$
            // Disable assigning roles to himself
        	UserEntity user = null;
            for (UserAccountEntity ua : rolsUsuarisEntity.getAccount().getUsers()) {
                if (ua.getUser().getUserName().equals(getPrincipal().getName())) {
                    throw new SeyconException(Messages.getString("AplicacioServiceImpl.UserAddRolError"));
                }
                user = ua.getUser();
            }
            
            deleteRolAccountEntity(rolsUsuarisEntity, user);
            return;
        } 
        throw new SeyconAccessLocalException("aplicacioService", "delete (RolAccount)", "user:role:delete", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("AplicacioServiceImpl.UnableDeleteRol"), codiAplicacio)); //$NON-NLS-1$
    }

	private void deleteRolAccountEntity(RoleAccountEntity rolsUsuarisEntity, UserEntity user) throws InternalErrorException {
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
		getRoleAccountEntityDao().remove(rolsUsuarisEntity);
		
		if (user != null)
			getRuleEvaluatorService().applyRules(user);
		getAccountEntityDao().propagateChanges(rolsUsuarisEntity.getAccount());
	}

	@Override
	protected void handleDenyApproval(RolAccount rolsUsuaris) throws Exception {
        String codiAplicacio = rolsUsuaris.getCodiAplicacio();
        // if (esAdministracioPersonal(rolsUsuaris) || esAdministradorUsuaris())
        // {
        if (AutoritzacionsUsuari.canDeleteUserRole(rolsUsuaris, getGroupEntityDao())) {

        	RoleAccountEntity rolsUsuarisEntity = getRoleAccountEntityDao().rolAccountToEntity(rolsUsuaris);
        	if (rolsUsuarisEntity.getRule() != null)
                throw new InternalErrorException("This role cannot be manually revoked. It's granted by a rule.");
            // Disable assigning roles to himself
            for (UserAccountEntity ua : rolsUsuarisEntity.getAccount().getUsers()) {
                if (ua.getUser().getUserName().equals(getPrincipal().getName())) {
                    throw new SeyconException(Messages.getString("AplicacioServiceImpl.UserAddRolError"));
                }
            }
            
            getRoleAccountEntityDao().remove(rolsUsuarisEntity);
            
            return;
        } 
        throw new SeyconAccessLocalException("aplicacioService", "delete (RolAccount)", "user:role:delete", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("AplicacioServiceImpl.UnableDeleteRol"), codiAplicacio)); //$NON-NLS-1$
    }

	protected RolAccount handleUpdate(RolAccount rolsUsuaris)
            throws Exception {
        RolAccount oldRolsUsuaris = getRoleAccountEntityDao().toRolAccount(getRoleAccountEntityDao().load(rolsUsuaris.getId()));
        String codiAplicacio = rolsUsuaris.getCodiAplicacio();
        // Ho fem emprant autoritzacions (user:role:create [SENSE_DOMINI o
        // GRUPS]
        if (AutoritzacionsUsuari.canCreateUserRole(rolsUsuaris, getGroupEntityDao()) && AutoritzacionsUsuari.canCreateUserRole(oldRolsUsuaris, getGroupEntityDao()))
        {
        	if (! rolsUsuaris.getAccountName().equals(oldRolsUsuaris.getAccountName() ) ||
        			! rolsUsuaris.getAccountDispatcher().equals(oldRolsUsuaris.getAccountDispatcher()) ||
        			! rolsUsuaris.getBaseDeDades().equals(oldRolsUsuaris.getBaseDeDades()) ||
        			! rolsUsuaris.getNomRol().equals(oldRolsUsuaris.getNomRol()))
        	{
        		throw new SeyconAccessLocalException("aplicacioService", "create (RolAccount)", "user:role:create", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        				"Invalid rol grant change. Cannot change rol or account")); //$NON-NLS-1$
        	}
            RoleAccountEntity rolsUsuarisEntity = getRoleAccountEntityDao().rolAccountToEntity(rolsUsuaris);

        	rolsUsuarisEntity.setEnabled(getEnableState(rolsUsuarisEntity));
        	
        	getRoleAccountEntityDao().update(rolsUsuarisEntity);
        	
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
        List<RoleAccountEntity> rolusus = getRoleAccountEntityDao().findByUserName(codiUsuari);

        if (rolusus != null) {
            // Filtrem per autoritzacions
            Collection<RoleAccountEntity> rolsFiltrats = AutoritzacionsUsuari.filtraRolsUsuariAplicationsCanQuery(rolusus);
            List<RolAccount> ra = getRoleAccountEntityDao().toRolAccountList(rolsFiltrats);
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
        UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);

		UserEntity user = getUserEntityDao().load(usuari.getId());
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateRoles(radSet, ALL, user);
		LinkedList<ContenidorRol> rgl = new LinkedList<ContenidorRol>();
		for (RolAccountDetail rad : radSet) {
            RolGrant rg = null;
            if (rad.rolRol != null) {
                RoleDependencyEntity rar = rad.rolRol;
                ContenidorRol cContingut = getRoleDependencyEntityDao().toContenidorRol(rar);
                ContenidorRol crol = getRoleEntityDao().toContenidorRol(rar.getContained());
                if (rad.qualifier != null) crol.setInfoContenidor(crol.getInfoContenidor() + " / " + rad.qualifier.getValue());
                if (rad.qualifierGroup != null) crol.setInfoContenidor(crol.getInfoContenidor() + " / " + rad.qualifierGroup.getName());
                if (rad.qualifierAplicacio != null) crol.setInfoContenidor(crol.getInfoContenidor() + " / " + rad.qualifierAplicacio.getName());
                crol.setMetaInfo(String.format(Messages.getString("AplicacioServiceImpl.RoleGrantedToRol"), cContingut.getInfoContenidor()));
                rgl.add(crol);
            }
            if (rad.rolGrup != null) {
                ContenidorRol cr = getRoleEntityDao().toContenidorRol(rad.rolGrup.getAssignedRole());
                if (rad.qualifier != null) cr.setInfoContenidor(cr.getInfoContenidor() + " / " + rad.qualifier.getValue());
                if (rad.qualifierGroup != null) cr.setInfoContenidor(cr.getInfoContenidor() + " / " + rad.qualifierGroup.getName());
                if (rad.qualifierAplicacio != null) cr.setInfoContenidor(cr.getInfoContenidor() + " / " + rad.qualifierAplicacio.getName());
                cr.setMetaInfo(String.format(Messages.getString("AplicacioServiceImpl.RoleGrantedToGroup"), rad.rolGrup.getOwnerGroup().getName()));
                rgl.add(cr);
            }
        }

        // Filtrado de roles (si se requiere) - per autoritzacions
        if ("S".equals(filtraResultats)) { //$NON-NLS-1$
            // user:role:query [SENSE_DOMINI, GRUPS, APLICACIONS]
            return AutoritzacionsUsuari.filtraContenidorRolCanQuery(codiUsuari, rgl, getGroupEntityDao());
        }

		return rgl;
    }

    protected Collection<RolAccount> handleFindRolsUsuarisByCodiUsuariAndNomRol(
            String codiUsuari, String nomRol) throws Exception {
        Collection<RoleAccountEntity> rolusuEntity = getRoleAccountEntityDao().findByUserAndRole(codiUsuari, nomRol);
        if (rolusuEntity != null) {

            Collection<RoleAccountEntity> rolsFiltrats = AutoritzacionsUsuari.filtraRolsUsuariAplicationsCanQuery(rolusuEntity);
            List<RolAccount> ra = getRoleAccountEntityDao().toRolAccountList(rolsFiltrats);
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
        Collection rolusus = getRoleAccountEntityDao().findByRole(nomRol);
        if (rolusus != null) {
            Collection<RoleAccountEntity> rolsFiltrats = AutoritzacionsUsuari.filtraRolsUsuariAplicationsCanQuery(rolusus);
            List<RolAccount> ra = getRoleAccountEntityDao().toRolAccountList(rolsFiltrats);
    		getSoDRuleService().qualifyRolAccountList(ra);
    		return ra;
        }
        return new Vector();
    }

    protected Collection<Rol> handleFindRolsByNomDominiAndCodiAplicacio(
            String nomDomini, String codiAplicacio) throws Exception {
    	List<RoleEntity> roles = getRoleEntityDao().findByInformationSystemAndDomain(codiAplicacio, nomDomini);
        if (roles != null) {
            return getRoleEntityDao().toRolList(roles);
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

            RoleAccountEntity administracioAplicacioEntity = getRoleAccountEntityDao().administracioAplicacioToEntity(administracioAplicacio);
            getRoleAccountEntityDao().update(administracioAplicacioEntity);
            administracioAplicacio.setId(administracioAplicacio.getId());
            administracioAplicacio = getRoleAccountEntityDao().toAdministracioAplicacio(administracioAplicacioEntity);
            return administracioAplicacio;
        }
		throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NotPermisionToUpdate"), //$NON-NLS-1$
				getPrincipal().getName(), administracioAplicacio.getCodiAplicacio()));
    }

    protected Rol handleFindRolById(Long rolId) throws Exception {
        RoleEntity rolEntity = getRoleEntityDao().findById(rolId);
        if (rolEntity != null) {
            return getRoleEntityDao().toRol(rolEntity);
        }
        return null;
    }

    protected Collection handleFindRolsContinguts(Rol contenidor)
            throws Exception {
    	RoleEntity parent = getRoleEntityDao().load(contenidor.getId());
    	LinkedList<Rol> children = new LinkedList<Rol>();
    	for (RoleDependencyEntity dep: parent.getContainedRole())
    	{
    		children.add( getRoleEntityDao().toRol(dep.getContained()));
    	}
    	return children;
    }

    protected Collection<Grup> handleFindGrupsPosseidorsdelRolByRol(Rol rol)
            throws Exception {
        RoleEntity rolEntity = getRoleEntityDao().rolToEntity(rol);
        Collection<RoleGroupEntity> rolsGrups = rolEntity.getContainerGroups();
        Collection<GroupEntity> grups = new ArrayList();
        for (Iterator<RoleGroupEntity> it = rolsGrups.iterator(); it.hasNext(); ) {
            RoleGroupEntity rge = it.next();
            grups.add(rge.getOwnerGroup());
        }
        return getGroupEntityDao().toGrupList(grups);
        
    }

    protected Collection<Rol> handleFindRolsOtorgatsalGrupByGrup(Grup grup)
            throws Exception {
        GroupEntity grupEntity = getGroupEntityDao().grupToEntity(grup);
        Collection<RoleGroupEntity> rolsGrups = grupEntity.getAllowedRolesToGroup();
        Collection<RoleEntity> rols = new ArrayList();
        for (Iterator<RoleGroupEntity> it = rolsGrups.iterator(); it.hasNext(); ) {
            RoleGroupEntity rge = it.next();
            rols.add(rge.getAssignedRole());
        }
        return getRoleEntityDao().toRolList(rols);
        
        
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
        Collection<ContenidorRol> resultat = new ArrayList<ContenidorRol>();

        
        // Obtenemos el ROL
        RoleEntity theRol = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRole, codiAplicacioRol, codiDispatcher);

		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		
		LinkedList<RolGrant> rg = new LinkedList<RolGrant>();
		populateParentGrantsForRol(radSet, theRol, null);
		for (RolAccountDetail rad : radSet) {
            if (rad.granteeRol != null) {
                if (rad.account.getType().equals(AccountType.USER)) {
                    for (UserAccountEntity uae : rad.account.getUsers()) {
                        ContenidorRol cr = new ContenidorRol();
                        UserEntity ue = uae.getUser();
                        cr.setTipus(ue.getUserName());
                        cr.setInfoContenidor(ue.getFullName());
                        cr.setMetaInfo(rad.granteeRol.toRoleDescription());
                        resultat.add(cr);
                    }
                } else {
                    ContenidorRol cr = new ContenidorRol();
                    cr.setTipus(rad.account.getName());
                    cr.setInfoContenidor(rad.account.getDescription());
                    cr.setMetaInfo(rad.granteeRol.toRoleDescription());
                    resultat.add(cr);
                }
            } else if (rad.granteeGrup != null) {
                if (rad.account.getType().equals(AccountType.USER)) {
                    for (UserAccountEntity uae : rad.account.getUsers()) {
                        ContenidorRol cr = new ContenidorRol();
                        UserEntity ue = uae.getUser();
                        cr.setTipus(ue.getUserName());
                        cr.setInfoContenidor(ue.getFullName());
                        cr.setMetaInfo(rad.granteeGrup.getName() + " - " + rad.granteeGrup.getDescription());
                        resultat.add(cr);
                    }
                } else {
                    ContenidorRol cr = new ContenidorRol();
                    cr.setTipus(rad.account.getName());
                    cr.setInfoContenidor(rad.account.getDescription());
                    cr.setMetaInfo(rad.granteeGrup.getName() + " - " + rad.granteeGrup.getDescription());
                    resultat.add(cr);
                }
            }
        }
		return resultat;

    }

    protected Collection<Object> handleGetNotificacionsPendents(String codiAplicacio)
            throws Exception {

        // Carreguem totes
        Collection notificacions = null;
        if (codiAplicacio == null)
            notificacions = getNoticeEntityDao().loadAll();
        else
            notificacions = getNoticeEntityDao().findByApplicationCode(codiAplicacio);

        HashMap<Aplicacio,ArrayList<String>> hNotifica = new HashMap();
        // guardem les aplicacioEntity i la seua Aplicacio(VO) corresponent:
        HashMap aplicacions = new HashMap();

        if (notificacions != null && notificacions.size() > 0) {
        	// van per aplicació
            for (Iterator it = notificacions.iterator(); it.hasNext(); ) {
                NoticeEntity notif = (NoticeEntity) it.next();
                InformationSystemEntity aplicacio = notif.getApplication();
                Aplicacio aplic = null;
                if ((aplic = (Aplicacio) aplicacions.get(aplicacio)) == null) {
                    aplic = getInformationSystemEntityDao().toAplicacio(aplicacio);
                    aplicacions.put(aplicacio, aplic);
                }
                RoleEntity rol = notif.getRole();
                UserEntity usu = notif.getUser();
                String modif = notif.getInformation() != null ? notif.getInformation().indexOf("atorga") != -1 ? "d\'atorgaci\u00f3: " : "de revocaci\u00f3: " : "de modificaci\u00f3: ";
                String missatge = notif.getInformation() + ": " + "\n     Usuari: " + usu.getUserName() + " [" + usu.getFirstName() + " " + usu.getLastName() + " " + usu.getMiddleName() + "]" + "\n     Rol: " + rol.getName() + " [" + rol.getDescription() + "]" + (notif.getModificationDate() != null ? "\n     Data " + modif + DateUtils.dataToStringFull(notif.getModificationDate()) : "");
                ArrayList<String> msg = null;
                if ((msg = hNotifica.get(aplic)) == null) msg = new ArrayList<String>();
                msg.add(missatge);
                hNotifica.put(aplic, msg);
            }

        }

        return (Collection) hNotifica.entrySet(); // Set<Entry<AplicacioEntity,LinkedList<String>>>
    }

    protected void handleDeleteNotificacionsEnviades(String codiAplicacio,
            Date dataDelete) throws Exception {

        Collection notificacions = null;

        if (codiAplicacio != null) // ordenades per data caducitat
            notificacions = getNoticeEntityDao().findByApplicationCode(codiAplicacio);
        else
        	// ordenades per data caducitat
            notificacions = getNoticeEntityDao().findAll(); 

        if (notificacions != null) {
            Collection notifBorrar = new ArrayList();
            for (Iterator it = notificacions.iterator(); it.hasNext(); ) {
                NoticeEntity notif = (NoticeEntity) it.next();
                if (notif.getModificationDate().getTime() <= dataDelete.getTime()) {
                    notifBorrar.add(notif);
                }
            }
            if (notifBorrar.size() > 0) {// Las boramos todos juntas
                getNoticeEntityDao().remove(notifBorrar);
            }
        }
    }


    protected Collection<AutoritzacioRol> handleFindAutoritzacionsRolByNomRolAndCodiAplicacioRolAndCodiDispatcher(
            String nomRole, String codiAplicacioRol, String codiDispatcher)
            throws Exception {
        if (AutoritzacionsUsuari.canQueryAplicacio(codiAplicacioRol)) {
            RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRole, codiAplicacioRol, codiDispatcher);

            LinkedList<AutoritzacioRol> totPermis = new LinkedList();

            if (rolEntity != null) {
                // Obtenim tota la jerarquía de rols que té heretats
                Collection<RoleEntity> totRol = getRolEntityHeretatsByRol(rolEntity);

                if (totRol != null) {
                    for (Iterator<RoleEntity> rit = totRol.iterator(); rit.hasNext(); ) {
                        RoleEntity r = rit.next();
                        Collection autos = getAuthorizationEntityDao().findByRoleID(r.getId());
                        if (autos != null) for (Iterator it = autos.iterator(); it.hasNext(); ) {
                            AuthorizationEntity auto = (AuthorizationEntity) it.next();
                            Collection desc = getAutoritzacioService().getInformacioAutoritzacio(auto.getAuthorization());
                            AutoritzacioRol autoVO = getAuthorizationEntityDao().toAutoritzacioRol(auto);
                            if (desc != null && desc.iterator().hasNext()) {
                                AutoritzacioSEU a = (AutoritzacioSEU) desc.iterator().next();
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

    private Collection<RoleEntity> getRolsHeretatsDirectes(RoleEntity rolEntity) {
        HashMap<Long, RoleEntity> fills = new HashMap();
        Collection rolsAsocHeretats = rolEntity.getContainedRole();
        if (rolsAsocHeretats != null) {
            for (Iterator it = rolsAsocHeretats.iterator(); it.hasNext(); ) {
                RoleDependencyEntity rar = (RoleDependencyEntity) it.next();
                RoleEntity r = rar.getContained();
                fills.put(r.getId(), r);
            }
        }
        return fills.values();
    }

    private Collection<RoleEntity> getRolEntityHeretatsByRol(RoleEntity rolEntity) {

        HashMap<Long, RoleEntity> totRol = new HashMap<Long, RoleEntity>();
        // Llista de rols a analitzar
        LinkedList<RoleEntity> rolsAnalitzar = new LinkedList();

        if (rolEntity != null) {
            rolsAnalitzar.add(rolEntity);
            // Cerquem els rols que tinc heretats (continguts)
            RoleEntity fill = null;
            while ((fill = rolsAnalitzar.poll()) != null) {
                totRol.put(fill.getId(), fill); // L'afegim
                Collection<RoleEntity> hereta = getRolsHeretatsDirectes(fill);
                if (hereta != null)
                    for (Iterator<RoleEntity> it = hereta.iterator(); it.hasNext(); ) {
                    RoleEntity r = it.next();
                    if (!totRol.containsKey(r.getId())) rolsAnalitzar.add(r);
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
            RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRole, codiAplicacioRol, codiDispatcher);

            LinkedList<AutoritzacioPuntEntrada> totPermis = new LinkedList();

            if (rolEntity != null) {
                // Obtenim tota la jerarquía de rols que té heretats
                Collection<RoleEntity> totRol = getRolEntityHeretatsByRol(rolEntity);

                if (totRol != null) {
                    for (Iterator<RoleEntity> rit = totRol.iterator(); rit.hasNext(); ) {
                        RoleEntity r = rit.next();
                        for (EntryPointRoleEntity aut: getEntryPointRoleEntityDao().findByRoleId (r.getId()))
                        {
                            AutoritzacioPuntEntrada autoVO = getEntryPointRoleEntityDao().toAutoritzacioPuntEntrada(aut);
                            autoVO.setCodiEntitatAutoritzada(aut.getEntryPoint().getName());
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
            RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRole, codiAplicacioRol, codiDispatcher);

            LinkedList<NetworkAuthorization> totPermis = new LinkedList<NetworkAuthorization>();

            if (rolEntity != null) {
                // Obtenim tota la jerarquía de rols que té heretats
                Collection<RoleEntity> totRol = getRolEntityHeretatsByRol(rolEntity);

                if (totRol != null) {
                    for (Iterator<RoleEntity> rit = totRol.iterator(); rit.hasNext(); ) {
                        RoleEntity r = rit.next();
                        List<NetworkAuthorizationEntity> autosXarxa = getNetworkAuthorizationEntityDao().findByRole(r.getName(), r.getInformationSystem().getName(), r.getSystem().getName());
                        if (autosXarxa != null && autosXarxa.size() > 0) {
                            totPermis.addAll(getNetworkAuthorizationEntityDao().toNetworkAuthorizationList(autosXarxa));
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
    
    private void populateRoles(Set<RolAccountDetail> rad, int type, UserEntity user) {
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
    		populateGroupRoles(rad, ALL, user.getPrimaryGroup(), user);
    		for (UserGroupEntity ug : user.getSecondaryGroups()) {
                populateGroupRoles(rad, ALL, ug.getGroup(), user);
            }
    	}
    	
    }
    
	private void populateGroupRoles(Set<RolAccountDetail> rad, int type, GroupEntity grup, UserEntity originUser) {
		if (type == NONE)
			return;
		
		for (RoleGroupEntity rg : grup.getAllowedRolesToGroup()) {
            for (AccountEntity ae : getAccountsForDispatcher(originUser, null, rg.getAssignedRole().getSystem())) {
                RolAccountDetail n = new RolAccountDetail(rg, ae);
                n.granteeGrup = grup;
                if (!rad.contains(n)) {
                    if (type == DIRECT || type == ALL) rad.add(n);
                    if (type == INDIRECT || type == ALL) {
                        for (AccountEntity acc : getAccountsForDispatcher(originUser, null, rg.getAssignedRole().getSystem())) populateRoleRoles(rad, ALL, n, originUser, acc);
                    }
                }
            }
        }
		if (grup.getParent() != null)
			populateGroupRoles(rad, type, grup.getParent(), originUser);
	}

	private List<AccountEntity> getAccountsForDispatcher(UserEntity usuari, AccountEntity account, SystemEntity dispatcher) {
		List<AccountEntity> accounts = new LinkedList<AccountEntity>();
		if (account == null)
		{
			if (usuari != null)
			{
				for (UserAccountEntity ua2 : usuari.getAccounts()) {
                    if (ua2.getAccount().getSystem().getId().equals(dispatcher.getId()) && ua2.getAccount().getType().equals(AccountType.USER)) {
                        accounts.add(ua2.getAccount());
                    }
                }
			}
		}
		else if (account.getSystem().getId().equals(dispatcher.getId()))
		{
			accounts.add(account);
		}
		else if (account.getType().equals(AccountType.USER))
		{
			for (UserAccountEntity ua2 : usuari.getAccounts()) {
                if (ua2.getAccount().getSystem().getId().equals(dispatcher.getId()) && ua2.getAccount().getType().equals(AccountType.USER)) {
                    accounts.add(ua2.getAccount());
                }
            }
		}
		if (accounts.isEmpty())
			accounts.add(null);
		
		return accounts;
	}
	
	private void populateRoleRoles(Set<RolAccountDetail> rad, int type, RolAccountDetail currentRol, UserEntity originUser, AccountEntity originAccount) {
		if (type == NONE)
			return;
		
		RoleEntity rol = currentRol.granted;
		
		for (RoleDependencyEntity ra : rol.getContainedRole()) {
            if (matchesGranteeDomainValue(currentRol, ra)) {
                for (AccountEntity ae : getAccountsForDispatcher(originUser, originAccount, ra.getContained().getSystem())) {
                    RolAccountDetail n = new RolAccountDetail(ra, ae, currentRol);
                    n.granteeRol = rol;
                    n.generateHash();
                    if (!rad.contains(n)) {
                        if (type == DIRECT || type == ALL) rad.add(n);
                        if (type == INDIRECT || type == ALL) populateRoleRoles(rad, ALL, n, originUser, originAccount);
                    }
                }
            }
        }
	}

	
	private boolean matchesGranteeDomainValue(RolAccountDetail currentRol, RoleDependencyEntity ra) {
		if (ra.getContainer().getDomainType() == null || ra.getContainer().getDomainType().equals(TipusDomini.SENSE_DOMINI))
			return true;
		else if (ra.getContainer().getDomainType().equals(TipusDomini.APLICACIONS))
		{
			return ra.getGranteeApplicationDomain() == null ||
					currentRol.qualifierAplicacio == null ||
					ra.getGranteeApplicationDomain().getId().equals (currentRol.qualifierAplicacio.getId()); 
		}
		else if (ra.getContainer().getDomainType().equals(TipusDomini.GRUPS) || ra.getContainer().getDomainType().equals(TipusDomini.GRUPS_USUARI))
		{
			return ra.getGranteeGroupDomain() == null ||
					currentRol.qualifierGroup == null || 
					ra.getGranteeGroupDomain().getId().equals (currentRol.qualifierGroup.getId()); 
		}
		else if (ra.getContainer().getDomainType().equals(TipusDomini.DOMINI_APLICACIO))
		{
			return ra.getGranteeDomainValue() == null ||
					currentRol.qualifier == null || 
					ra.getGranteeDomainValue().getId().equals (currentRol.qualifier.getId()); 
		}
		else
		{
			throw new RuntimeException("Unexpected domain value " + ra.getContainer().getDomainType());
		}
	}

	private boolean shouldBeEnabled(RoleAccountEntity e) {
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
	
	private void populateAccountRoles(Set<RolAccountDetail> rad, int type, AccountEntity account, UserEntity user) {
		for (RoleAccountEntity ra : account.getRoles()) {
            RolAccountDetail n = new RolAccountDetail(ra, account);
            if (!rad.contains(n)) {
                if (type == DIRECT || type == ALL) rad.add(n);
                if ((type == INDIRECT || type == ALL) && shouldBeEnabled(ra)) populateRoleRoles(rad, ALL, n, user, account);
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
		for (RolAccountDetail rad : radSet) {
            if (rad.granted.getSystem().getId().equals(account.getSystem().getId())) {
                if (rad.rolAccount != null && shouldBeEnabled(rad.rolAccount)) rg.add(getRoleAccountEntityDao().toRolGrant(rad.rolAccount));
                if (rad.rolRol != null) rg.add(getRoleDependencyEntityDao().toRolGrant(rad.rolRol));
                if (rad.rolGrup != null) rg.add(getRoleGroupEntityDao().toRolGrant(rad.rolGrup));
            }
        }
		return rg;
	}

	@Override
	protected Collection<RolAccount> handleFindRolAccountByAccount(long accountId)
			throws Exception
	{
		LinkedList<RolAccount> rg = new LinkedList<RolAccount>();
		AccountEntity account = getAccountEntityDao().load(accountId);
		if (account == null)
			return rg;
		
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateAccountRoles(radSet, DIRECT, account, null);
		for (RolAccountDetail rad : radSet) {
            if (rad.granted.getSystem().getId().equals(account.getSystem().getId())) {
                if (rad.rolAccount != null) rg.add(getRoleAccountEntityDao().toRolAccount(rad.rolAccount));
            }
        }
		getSoDRuleService().qualifyRolAccountList(rg);
		return rg;
	}

	@Override
	protected Collection<RolGrant> handleFindEffectiveRolGrantByUser(long userId)
			throws Exception
	{
		UserEntity user = getUserEntityDao().load(userId);
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateRoles(radSet, ALL, user);
		LinkedList<RolGrant> rgl = new LinkedList<RolGrant>();
		for (RolAccountDetail rad : radSet) {
            RolGrant rg = null;
            if (rad.rolAccount != null && shouldBeEnabled(rad.rolAccount)) rg = (getRoleAccountEntityDao().toRolGrant(rad.rolAccount));
            if (rad.rolRol != null) {
                rg = (getRoleDependencyEntityDao().toRolGrant(rad.rolRol));
                if (rad.qualifier != null) rg.setDomainValue(rad.qualifier.getValue()); else if (rad.qualifierAplicacio != null) rg.setDomainValue(rad.qualifierAplicacio.getName()); else if (rad.qualifierGroup != null) rg.setDomainValue(rad.qualifierGroup.getName());
            }
            if (rad.rolGrup != null) rg = (getRoleGroupEntityDao().toRolGrant(rad.rolGrup));
            if (rg != null) {
                if (rad.account != null) rg.setOwnerAccountName(rad.account.getName());
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
		for (RolAccountDetail rad : radSet) {
            if (rad.account != null && rad.account.getId().longValue() == accountId) {
                if (rad.rolAccount != null && shouldBeEnabled(rad.rolAccount)) rg.add(getRoleAccountEntityDao().toRolGrant(rad.rolAccount));
                if (rad.rolRol != null) {
                    RolGrant r = getRoleDependencyEntityDao().toRolGrant(rad.rolRol);
                    if (rad.qualifier != null) r.setDomainValue(rad.qualifier.getValue()); else if (rad.qualifierAplicacio != null) r.setDomainValue(rad.qualifierAplicacio.getName()); else if (rad.qualifierGroup != null) r.setDomainValue(rad.qualifierGroup.getName());
                    rg.add(r);
                }
                if (rad.rolGrup != null) rg.add(getRoleGroupEntityDao().toRolGrant(rad.rolGrup));
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

    	RoleEntity role = getRoleEntityDao().load(rolId);
    	// Remove inactive grants
    	List<RolGrant> result = new LinkedList<RolGrant>();
    	for (RoleAccountEntity rae: role.getAccounts() ) {
            if (shouldBeEnabled(rae)) 
            	result.add ( getRoleAccountEntityDao().toRolGrant(rae));
        }
        return result;
	}

	@Override
	protected Collection<RolGrant> handleFindEffectiveRolGrantsByRolId(Long rolId)
			throws Exception
	{
		RoleEntity rol = getRoleEntityDao().load(rolId);
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		
		LinkedList<RolGrant> rg = new LinkedList<RolGrant>();
		if (rol == null)
			return rg;
		
		populateParentGrantsForRol(radSet, rol, null);
		for (RolAccountDetail rad : radSet) {
            RolGrant grant;
            if (rad.rolAccount != null) grant = getRoleAccountEntityDao().toRolGrant(rad.rolAccount); else if (rad.rolRol != null) {
                grant = getRoleDependencyEntityDao().toRolGrant(rad.rolRol);
                if (rad.qualifier != null) grant.setDomainValue(rad.qualifier.getValue()); else if (rad.qualifierAplicacio != null) grant.setDomainValue(rad.qualifierAplicacio.getName()); else if (rad.qualifierGroup != null) grant.setDomainValue(rad.qualifierGroup.getName());
            } else grant = getRoleGroupEntityDao().toRolGrant(rad.rolGrup);
            if (rad.account != null) {
                grant.setOwnerAccountName(rad.account.getName());
                grant.setOwnerDispatcher(rad.account.getSystem().getName());
                if (rad.account.getType().equals(AccountType.USER)) {
                    for (UserAccountEntity ua : rad.account.getUsers()) {
                        grant.setUser(ua.getUser().getUserName());
                    }
                }
            }
            rg.add(grant);
        }
		return rg;
	}

	private void populateParentGrantsForRol(HashSet<RolAccountDetail> radSet, RoleEntity rol, RoleDependencyEntity originalGrant) {
		for (RoleAccountEntity rac : rol.getAccounts()) {
            if (shouldBeEnabled(rac)) {
                RolAccountDetail rad;
                if (originalGrant == null) 
                	rad = new RolAccountDetail(rac, rac.getAccount()); 
                else {
                    RolAccountDetail previousRad = new RolAccountDetail(rac, rac.getAccount());
                    rad = new RolAccountDetail((RoleDependencyEntity) originalGrant, rac.getAccount(), previousRad);
                    rad.granteeRol = rol;
                }
                if (!radSet.contains(rad)) {
                    if (originalGrant == null || matchesGranteeDomainValue(rad, originalGrant)) {
                        radSet.add(rad);
                    }
                }
            }
        }
		
		for (RoleDependencyEntity ra : rol.getContainerRoles()) {
            populateParentGrantsForRol(radSet, ra.getContainer(), originalGrant == null ? ra : originalGrant);
        }

		for (RoleGroupEntity rg : rol.getContainerGroups()) {
            populateParentGrantsForGroup(radSet, rg.getOwnerGroup(), originalGrant == null ? rg : originalGrant);
        }
	}

	private void populateParentGrantsForGroup(HashSet<RolAccountDetail> radSet, GroupEntity grup, Object originalGrant) {
		for (UserEntity u : grup.getPrimaryGroupUsers()) {
            populateParentGrantsForUser(radSet, u, originalGrant, grup);
        }
		
		for (UserGroupEntity sg : grup.getSecondaryGroupUsers()) {
            populateParentGrantsForUser(radSet, sg.getUser(), originalGrant, grup);
        }

		for (GroupEntity fill : grup.getChildrens()) {
            populateParentGrantsForGroup(radSet, fill, originalGrant);
        }
	}

	private void populateParentGrantsForUser(HashSet<RolAccountDetail> radSet, 
			UserEntity u, Object originalGrant, 
			GroupEntity granteeGroup) {
		SystemEntity de;
		if (originalGrant instanceof RoleDependencyEntity)
			de = ((RoleDependencyEntity) originalGrant).getContained().getSystem();
		else
			de = ((RoleGroupEntity) originalGrant).getAssignedRole().getSystem();
			
		for (AccountEntity acc : getAccountsForDispatcher(u, null, de)) {
            if (acc != null) {
                RolAccountDetail rad;
                if (originalGrant instanceof RoleAccountEntity) 
                {
                	rad = new RolAccountDetail((RoleAccountEntity) originalGrant, acc);
                }
                else if (originalGrant instanceof RoleGroupEntity)
                {
                	rad = new RolAccountDetail((RoleGroupEntity) originalGrant, acc);
                }
                else
                {
                	rad = new RolAccountDetail((RoleDependencyEntity) originalGrant, acc, null);
                }
                rad.granteeGrup = granteeGroup;
                if (!radSet.contains(rad)) {
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
		RoleAccountEntity entity = getRoleAccountEntityDao().load(rolAccount.getId());
		enableOrDisableOnDates(rolAccount, entity);
		return rolAccount;
		
	}

	private void enableOrDisableOnDates(RolAccount rolAccount, RoleAccountEntity entity) throws InternalErrorException {
		rolAccount.setEnabled(getEnableState(entity));

		if (entity.isEnabled() != rolAccount.isEnabled())
		{
    		entity.setEnabled(rolAccount.isEnabled());
    		getRoleAccountEntityDao().update(entity);
    		getAccountEntityDao().propagateChanges(entity.getAccount());
		}
	}

	private boolean getEnableState(RoleAccountEntity entity) {
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
		for (RoleAccountEntity toEnable : getRoleAccountEntityDao().findRolAccountToEnable(now)) {
            toEnable.setEnabled(true);
            getRoleAccountEntityDao().update(toEnable);
            getAccountEntityDao().propagateChanges(toEnable.getAccount());
        }

		for (RoleAccountEntity toDisable : getRoleAccountEntityDao().findRolAccountToDisable(now)) {
            toDisable.setEnabled(false);
            getRoleAccountEntityDao().update(toDisable);
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
		UserEntity user = getUserEntityDao().load(userId);
		if (user.getPrimaryGroup().getId().equals(groupId))
			return;
		
		for (UserGroupEntity uge : user.getSecondaryGroups()) {
            if (uge.getGroup().getId().equals(groupId)) return;
        }
		
		GroupEntity ge = getGroupEntityDao().load(groupId);
		for (UserAccountEntity uae : user.getAccounts()) {
            AccountEntity acc = uae.getAccount();
            for (RoleAccountEntity rae : new LinkedList<RoleAccountEntity>(acc.getRoles())) {
                if (rae.getHolderGroup() != null && rae.getHolderGroup().getId().equals(groupId)) {
                    deleteRolAccountEntity(rae, user);
                }
            }
        }
	}

	@Override
	protected Rol handleFindRoleByNameAndSystem(String name, String system)
			throws Exception {
        RoleEntity rolEntity = getRoleEntityDao().findByNameAndSystem(name, system);
        if (rolEntity == null)
        	return null;
        // Cap dels tres paràmetres pot ésser null
        // Mirem l'autorització de l'aplicació (fer query als rols de la app
        // no requereixen tindre una autorització específica)
        if (AutoritzacionsUsuari.canQueryAplicacio(rolEntity.getInformationSystem().getName())) {
            return getRoleEntityDao().toRol(rolEntity);
        } else {
			throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.NoAccessToRol"),  //$NON-NLS-1$
					getPrincipal().getName(), name));
        }
	}

	@Override
	protected Collection<RolAccount> handleFindRolsUsuarisByInformationSystem(
			String informationSystem) throws Exception {
		return getRoleAccountEntityDao().toRolAccountList(
				getRoleAccountEntityDao().findByInformationSystem(informationSystem));
	}


}

class RolAccountDetail
{
	RoleEntity granted;
	GroupEntity qualifierGroup;
	InformationSystemEntity qualifierAplicacio;
	DomainValueEntity qualifier;
	RoleEntity granteeRol;
	GroupEntity granteeGrup;
	RoleAccountEntity rolAccount;
	RoleGroupEntity rolGrup;
	RoleDependencyEntity rolRol;
	AccountEntity account;
	
	String hash;
	public RolAccountDetail(RoleGroupEntity rg, AccountEntity account) {
		this.account = account; 
		granted = rg.getAssignedRole();
		qualifier = rg.getGrantedDomainValue();
		qualifierAplicacio = rg.getGrantedApplicationDomain();
		qualifierGroup = rg.getGrantedGroupDomain();
		rolGrup = rg;
		generateHash();
	}
	
	public RolAccountDetail(RoleDependencyEntity ra, AccountEntity account, RolAccountDetail previous) {
		this.account = account; 
		granted = ra.getContained();
		qualifier = ra.getDomainApplicationValue();
		qualifierAplicacio = ra.getDomainApplication();
		qualifierGroup = ra.getDomainGroup();
		if (qualifier == null && previous != null)
			qualifier = previous.qualifier;
		if (qualifierAplicacio == null && previous != null)
			qualifierAplicacio = previous.qualifierAplicacio;
		if (qualifierGroup == null && previous != null)
			qualifierGroup = previous.qualifierGroup;
		if (previous != null && previous.granted != null)
			granteeRol = previous.granted;
		rolRol = ra;
		generateHash();
	}
	
	public RolAccountDetail(RoleAccountEntity ra, AccountEntity account) {
		this.account = account; 
		granted = ra.getRole();
		qualifier = ra.getDomainValue();
		qualifierAplicacio = ra.getInformationSystem();
		qualifierGroup = ra.getGroup();
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
		b.append(granted.getName());
		b.append("@"); //$NON-NLS-1$
		b.append(granted.getSystem().getName());
		if ( qualifierGroup != null)
			b.append("/").append(qualifierGroup.getName()); //$NON-NLS-1$
		if ( qualifierAplicacio != null)
			b.append("/").append(qualifierAplicacio.getName()); //$NON-NLS-1$
		if ( qualifier != null)
			b.append("/").append(qualifier.getValue()); //$NON-NLS-1$
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
