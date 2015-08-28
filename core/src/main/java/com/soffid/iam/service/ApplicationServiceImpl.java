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

import com.soffid.iam.api.AccessTreeAuthorization;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.ApplicationAdministration;
import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.BpmUserProcess;
import com.soffid.iam.api.ContainerRole;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.NetworkAuthorization;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AuthorizationEntity;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.EntryPointRoleEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.NetworkAuthorizationEntity;
import com.soffid.iam.model.NoticeEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.utils.AutoritzacioSEU;
import com.soffid.iam.utils.AutoritzacionsUsuari;
import com.soffid.iam.utils.DateUtils;
import com.soffid.iam.utils.Security;

import es.caib.bpm.vo.PredefinedProcessType;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.SoDRisk;
import es.caib.seycon.ng.comu.SoDRule;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownUserException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
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
public class ApplicationServiceImpl extends
        com.soffid.iam.service.ApplicationServiceBase {
	final int DIRECT = 0;
	final int INDIRECT = 1;
	final int ALL = 2;
	final int NONE=3;

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#getAplicacions()
     */
    protected java.util.Collection<Application> handleGetApplications() throws java.lang.Exception {
        Collection<InformationSystemEntity> apps = filterAplicationsCanQuery(getInformationSystemEntityDao().loadAll());
        List<Application> aplicacions = getInformationSystemEntityDao().toApplicationList(apps);
        return aplicacions;
    }

    private Collection<InformationSystemEntity> filterAplicationsCanQuery(
			List<InformationSystemEntity> entities) throws InternalErrorException {
    	AuthorizationService autSvc = getAuthorizationService();
    	LinkedList<InformationSystemEntity> result = new LinkedList<InformationSystemEntity>();
    	for ( InformationSystemEntity entity: entities)
    		if (autSvc.hasPermission(Security.AUTO_APPLICATION_QUERY, entity))
    			result.add (entity);
    	return result;
	}

	/**
     * @see es.caib.seycon.ng.servei.AplicacioService#create(es.caib.seycon.ng.comu.Aplicacio)
     */
    protected com.soffid.iam.api.Application handleCreate(com.soffid.iam.api.Application aplicacio) throws java.lang.Exception {
        // Aquí l'autorització no te domini, aixina que amb tindre
        // application:create val
    	InformationSystemEntity aplicationsSameCode = getInformationSystemEntityDao().findByCode(aplicacio.getName());
		if(aplicationsSameCode != null)
			throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.CodeAplicationExists"), aplicacio.getName())); 
        InformationSystemEntity apl = getInformationSystemEntityDao().applicationToEntity(aplicacio);
        if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_CREATE, apl))
        {
            getInformationSystemEntityDao().create(apl);
            aplicacio.setId(apl.getId());
            return (getInformationSystemEntityDao().toApplication(apl));
        }
		throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NoUserPermission"), //$NON-NLS-1$
				getPrincipal().getName())); //$NON-NLS-1$
    }

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#delete(es.caib.seycon.ng.comu.Aplicacio)
     */
    protected void handleDelete(com.soffid.iam.api.Application aplicacio) throws java.lang.Exception {
        // Esborrem els rols d'administració de l'aplicació i l'aplicació
        InformationSystemEntity aplEntity = getInformationSystemEntityDao().applicationToEntity(aplicacio);
        if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_DELETE, aplEntity))
        {
            // Obtenim els rols d'autorització de l'aplicació per esborrar-los
            Collection<ApplicationAdministration> restriccions = findApplicationManageByApplicationName(aplicacio.getName());
            Iterator<ApplicationAdministration> iterator = restriccions.iterator();
            while (iterator.hasNext()) {// els esborrem
                ApplicationAdministration administracioAplicacio = iterator.next();
                manageApplication(administracioAplicacio);
            }
            if(!aplEntity.getRoles().isEmpty())
            	throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.IntegrityExceptionRol"), aplEntity.getName()));
            getInformationSystemEntityDao().remove(aplEntity);
        } else {
            throw new SeyconAccessLocalException("aplicacioService", //$NON-NLS-1$
                    "delete (Aplicacio)", "application:delete", //$NON-NLS-1$ //$NON-NLS-2$
                    Messages.getString("ApplicationServiceImpl.NotAuthorizedToDelete")); //$NON-NLS-1$
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
    protected void handleUpdate(com.soffid.iam.api.Application aplicacio) throws java.lang.Exception {

        InformationSystemEntity aplEntity = getInformationSystemEntityDao().applicationToEntity(aplicacio);
        if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_UPDATE, aplEntity)) {
            getInformationSystemEntityDao().update(aplEntity);
        } else {
            throw new SeyconAccessLocalException("aplicacioService", //$NON-NLS-1$
                    "update (Aplicacio)", "application:update", //$NON-NLS-1$ //$NON-NLS-2$
                    Messages.getString("ApplicationServiceImpl.NotAuthorizedToUpdate")); //$NON-NLS-1$
        }
    }

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#findAplicacioByCodiAplicacio(java.lang.String)
     */
    protected Application handleFindApplicationByApplicationName(java.lang.String codiAplicacio) throws java.lang.Exception {

        InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao().findByCode(codiAplicacio);
        if (aplicacioEntity != null && getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_QUERY, aplicacioEntity)) {
            if (aplicacioEntity != null) {
                Application aplicacio = getInformationSystemEntityDao().toApplication(aplicacioEntity);
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
    protected Application handleFindApplicationByApplicationNameUnrestricted(String codiAplicacio) throws Exception {
        InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao().findByCode(codiAplicacio);
        if (aplicacioEntity != null) {
            Application aplicacio = getInformationSystemEntityDao().toApplication(aplicacioEntity);
            return aplicacio;
        }

        return null;
    }

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#findRolsByAplicacio(es.caib.seycon.ng.comu.Aplicacio)
     */
    protected java.util.Collection<Role> handleFindRolesByApplicationName(String codiAplicacio) throws java.lang.Exception {
        // L'autoritzacio application:query [sense_domini,APLICACIONS]
        // NO n'hi ha restricció per veure els rols de l'aplicació
        // si l'usuari pot veure l'aplicació
        InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao().findByCode(codiAplicacio);
        if (aplicacioEntity != null) {
        	if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_QUERY, aplicacioEntity)) {
        		LinkedList<Role> rols = new LinkedList<Role>();
        		for (RoleEntity roleEntity : aplicacioEntity.getRoles()) {
                    if (getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, roleEntity)) rols.add(getRoleEntityDao().toRole(roleEntity));
                }
        		return rols;
        	}
		}
        return Collections.emptyList();
    }

    protected Collection<Role> handleFindRolesByApplicationNameUnrestricted(String codiAplicacio) throws Exception {
        Collection rols = getRoleEntityDao().findByInformationSystem(codiAplicacio);
        return getRoleEntityDao().toRoleList(rols);
    }

    protected Collection<Application> handleFindApplicationByCriteria(String codi, String nom, String directoriFonts, String responsable, String directoriExecutable, String bd, String rol, String gestionableWF) throws Exception {
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
        Collection<InformationSystemEntity> aplicacions = getInformationSystemEntityDao().findByFilter(codi, nom, directoriFonts, responsable, directoriExecutable, bd, gestionableWF);
        // Aplicamos las restricciones correspondientes
        if (aplicacions != null) {
        	Collection<Application> res = new LinkedList<Application>();
        	for (InformationSystemEntity appEntity : aplicacions) {
                if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_QUERY, appEntity)) {
                    res.add(getInformationSystemEntityDao().toApplication(appEntity));
                }
            }
			// filtrem per cercar aplicacions per rol
            res = filtraPerRol(res, rol); 

			// Check maximum number of results
            if ((res != null) && (res.size() > limitResults))
            {
            	return new LinkedList<Application>(res).subList(0, limitResults);
            }
            
            return res;
        }
        
        return Collections.emptyList();
    }

    protected Collection<Application> handleFindApplicationByCriteriaUnrestricted(String codi, String nom, String directoriFonts, String responsable, String directoriExecutable, String bd, String rol, String gestionableWF) throws Exception {

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
            Collection<Application> filtraPerRol = filtraPerRol(getInformationSystemEntityDao().toApplicationList(aplicacions), rol);
            // Mirem el número de registres després de filtar per rol (movem el
            // filtre de máx files)
            /*
             * if (filtraPerRol.size() >= 201) { throw new SeyconException(
             * "Massa registres trobats: és necessari donar un filtre més restrictiu."
             * ); }
             */
            return filtraPerRol;
        }
        return Collections.emptyList();
    }

    /**
     * Mètode per cercar aplicacions amb el filtre del rol (si existeix)
     * 
     * @param aplicacionsTrobades
     * @param rol
     * @return
     */
    private Collection<Application> filtraPerRol(Collection<Application> aplicacionsTrobades, String rol) {
        if (rol != null && rol.trim().compareTo("") != 0 //$NON-NLS-1$
                && rol.trim().compareTo("%") != 0 //$NON-NLS-1$
                && aplicacionsTrobades.size() > 0) {
            String aplicacions = ""; //$NON-NLS-1$
            Iterator aplicacionsIterator = aplicacionsTrobades.iterator();
            while (aplicacionsIterator.hasNext()) {
                Application aplicacio = (Application) aplicacionsIterator.next();
                aplicacions += "\'" + aplicacio.getName() + "\'" + (aplicacionsIterator.hasNext() ? "," : ""); //$NON-NLS-1$ //$NON-NLS-2$
            }
            String query = "select distinct rol.informationSystem " //$NON-NLS-1$
                    + "from com.soffid.iam.model.RoleEntity rol where " //$NON-NLS-1$
                    + "rol.name like :rol and " + "rol.informationSystem.name in (" //$NON-NLS-1$ //$NON-NLS-2$
                    + aplicacions + ")"; //$NON-NLS-1$
            Parameter parametres[] = { new Parameter("rol", rol) }; //$NON-NLS-1$
            Collection<InformationSystemEntity> aplicacionsTrobadesE = getInformationSystemEntityDao().query(query, parametres);
            return getInformationSystemEntityDao().toApplicationList(aplicacionsTrobadesE);
        }
        return aplicacionsTrobades;
    }

    protected ApplicationAdministration handleCreate(ApplicationAdministration administracioAplicacio) throws Exception {
        // En principi no existeix cap autorització explícita per això
        // es comprova que l'usuari puga fer application:create o
        // applicacion:update

        // Comprovem que l'usuari creador no siga el mateix a qui se li atorga
        if (administracioAplicacio.getUserName().compareTo(getPrincipal().getName()) == 0) {
            throw new SeyconException(
                    Messages.getString("ApplicationServiceImpl.NotAdminPermissionAuthorized")); //$NON-NLS-1$
        }
        RoleAccountEntity administracioAplicacioEntity = getRoleAccountEntityDao().applicationAdministrationToEntity(administracioAplicacio);

        // si l'usuari té autorització per crear/modificar l'aplicació
        if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_UPDATE, administracioAplicacioEntity) || getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_CREATE, administracioAplicacioEntity))
        {
            getRoleAccountEntityDao().create(administracioAplicacioEntity);
            administracioAplicacio.setId(administracioAplicacioEntity.getId());
            administracioAplicacio = getRoleAccountEntityDao().toApplicationAdministration(administracioAplicacioEntity);
            return administracioAplicacio;
        }
		throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NoPermissionToAsign"), getPrincipal().getName(), administracioAplicacio.getInformationSystemName()));
    }

    protected void handleManageApplication(ApplicationAdministration administracioAplicacio) throws Exception {
        // En principi no existeix cap autorització explícita per això
        // comprovem que l'usuari puga fer application:create o
        // applicacion:update o application:delete

        RoleAccountEntity administracioAplicacioEntity = getRoleAccountEntityDao().applicationAdministrationToEntity(administracioAplicacio);
        if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_UPDATE, administracioAplicacioEntity) || getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_CREATE, administracioAplicacioEntity))
        {
            getRoleAccountEntityDao().remove(administracioAplicacioEntity);
        } else {
			throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NoPermissionToDelete"), getPrincipal().getName(), administracioAplicacio.getInformationSystemName()));
        }
    }

    protected Collection<ApplicationAdministration> handleFindApplicationManageByApplicationName(String codiAplicacio) throws Exception {
        List<RoleAccountEntity> aplicacions = getRoleAccountEntityDao().findByQualifierIS(codiAplicacio);
        if (aplicacions != null) {
        	LinkedList<ApplicationAdministration> vo = new LinkedList<ApplicationAdministration>();
        	for (RoleAccountEntity ra : aplicacions) {
                if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_QUERY, ra)) vo.add(getRoleAccountEntityDao().toApplicationAdministration(ra));
            }
        	return vo;
        }
        else
        	return Collections.emptyList();
    }

    protected Collection<Role> handleFindRolesByFilter(String nom, String descripcio, String defecte, String baseDeDades, String contrasenya, String codiAplicacio) throws Exception {//6param
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

        	for (Iterator<RoleEntity> it = rols.iterator(); it.hasNext(); ) {
                RoleEntity re = it.next();
                if (!getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, re)) it.remove();
            }

            if (rols.size() >= 201) {
                throw new SeyconException(
                        Messages.getString("ApplicationServiceImpl.VeryRegFinded")); //$NON-NLS-1$
            }

            return getRoleEntityDao().toRoleList(rols);
        }
        return new Vector();
    }

    protected Collection handleFindRolesByFilter(String nom, String descripcio, String defecte, String baseDeDades, String contrasenya, String codiAplicacio, String gestionableWF) throws Exception {
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
        Collection<RoleEntity> rols = getRoleEntityDao().findRolesByManageableWFCriteria(nom, descripcio, defecte, baseDeDades, contrasenya, codiAplicacio, gestionableWF);
        if (rols != null) {
        	for (Iterator<RoleEntity> it = rols.iterator(); it.hasNext(); ) {
                if (!getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, it.next())) it.remove();
            }
        	// Check maximum number of results
            if (rols.size() > limitResults)
            {
            	return getRoleEntityDao().toRoleList(rols).subList(0, limitResults);
            }
            
            return getRoleEntityDao().toRoleList(rols);
        }
        
        return new Vector();
    }

    protected Role handleFindRoleByRoleNameAndApplicationNameAndDispatcherName(java.lang.String nomRol, String codiAplicacio, String codiDispatcher) throws java.lang.Exception {
        // Cap dels tres paràmetres pot ésser null
        // Mirem l'autorització de l'aplicació (fer query als rols de la app
        // no requereixen tindre una autorització específica)
        RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRol, codiAplicacio, codiDispatcher);
        if (rolEntity != null)
        {
        	if (getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, rolEntity)) {
                return getRoleEntityDao().toRole(rolEntity);
	        } else {
				throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NoAccessToRol"),  //$NON-NLS-1$
						getPrincipal().getName(), nomRol));
	        }
        } else
            return null;

    }

    /**
     * @see es.caib.seycon.ng.servei.AplicacioService#findRolsByCodiUsuari(java.lang.String)
     */
    protected java.util.Collection<Role> handleFindRolesByUserName(java.lang.String codiUsuari) throws java.lang.Exception {
    	String currentUser = Security.getCurrentUser();
        Collection<RoleEntity> rols = getRoleEntityDao().findRolesByUserName(codiUsuari);// RolEntity
        if (rols != null) {
        	for (Iterator<RoleEntity> it = rols.iterator(); it.hasNext(); ) {
                RoleEntity re = it.next();
                if (!codiUsuari.equals(currentUser) && !getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, re)) it.remove();
            }
            // Passem a VO:
            return getRoleEntityDao().toRoleList(rols);
        }
        return Collections.emptyList();
    }

    protected java.util.Collection<Role> handleGetRoles() throws java.lang.Exception {
        Collection<RoleEntity> col = getRoleEntityDao().loadAll();// RolEntity
    	for (Iterator<RoleEntity> it = col.iterator(); it.hasNext(); ) {
            RoleEntity re = it.next();
            if (!getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, re)) it.remove();
        }
        return getRoleEntityDao().toRoleList(col);
    }

    protected Collection<User> handleFindUsersByRoleNameAndRoleApplicationNameAndDispatcherName(String nomRol, String codiAplicacio, String codiDispatcher) throws Exception {
        RoleEntity rolEntity = getRoleEntityDao().findByNameAndSystem(nomRol, codiDispatcher);

        // NOTA: l'autorització ja s'ha verificat en el find
        if (getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, rolEntity)) 
        {
            Collection<User> toReturn = new LinkedList<User>();
            for (RoleAccountEntity ra : rolEntity.getAccounts()) {
                AccountEntity acc = ra.getAccount();
                if (acc.getType().equals(AccountType.USER) && acc.getUsers().size() == 1) {
                    UserEntity user = acc.getUsers().iterator().next().getUser();
                    toReturn.add(getUserEntityDao().toUser(user));
                }
            }
            return toReturn;
        } else {
			throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NotPermisionToSearch"), //$NON-NLS-1$
					getPrincipal().getName(), codiAplicacio));
        }
    }

    protected Collection<RoleAccount> handleFindUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(String nomRol, String codiAplicacio, String codiDispatcher) throws InternalErrorException {
        RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRol, codiAplicacio, codiDispatcher);
    	if (!getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, rolEntity))
    		throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NoAccessToRol"),  //$NON-NLS-1$
				getPrincipal().getName(), nomRol));

        List<RoleAccount> toReturn = new LinkedList<RoleAccount>();
        for (RoleAccountEntity ra : rolEntity.getAccounts()) {
            toReturn.add(getRoleAccountEntityDao().toRoleAccount(ra));
        }
 		getSoDRuleService().qualifyRolAccountList(toReturn);
        return toReturn;
    }

    protected Role handleCreate(Role rol) throws Exception {
        // if (usuariPotActualitzarAplicacio(rol.getCodiAplicacio())) {

        RoleEntity existingRole = getRoleEntityDao().findByNameAndSystem(rol.getName(), rol.getSystem());
        if (existingRole != null) {
                String aplicacio = existingRole.getInformationSystem()
                        .getName();

				throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.ExistentRole"), rol.getName(), rol.getSystem(), aplicacio));
        }

        // Obtenemos la entidad asociada al VO
        RoleEntity rolEntity = getRoleEntityDao().roleToEntity(rol);
        
        if  (!getAuthorizationService().hasPermission(Security.AUTO_ROLE_CREATE, rolEntity))
            throw new SeyconAccessLocalException("AplicacioService", //$NON-NLS-1$
                    "create (Rol)", "application:update, application:create", //$NON-NLS-1$ //$NON-NLS-2$
                    Messages.getString("ApplicationServiceImpl.NotAuthorizedToManageRol")); //$NON-NLS-1$
        // Creamos la entidad asociada al VO Rol
        getRoleEntityDao().create(rolEntity);

        return getRoleEntityDao().toRole(rolEntity);
    }

    protected void handleDelete(Role rol) throws Exception {
        // if (usuariPotActualitzarAplicacio(rol.getCodiAplicacio())) {
        RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(rol.getName(), rol.getInformationSystemName(), rol.getSystem());
        if (rolEntity == null)
        	return;
        
        if (getAuthorizationService().hasPermission(Security.AUTO_ROLE_DELETE, rolEntity))
        {
        	getSoDRuleService().internalRemovingRole(rolEntity.getId());
            getRoleEntityDao().remove(rolEntity);
        } else {
            throw new SeyconAccessLocalException(
                    "AplicacioService", //$NON-NLS-1$
                    "delete (Rol)", //$NON-NLS-1$
                    "application:delete, application:update, application:create", //$NON-NLS-1$
                    Messages.getString("ApplicationServiceImpl.NotAuthorizedToManageRol")); //$NON-NLS-1$
        }
    }

    protected Role handleUpdate(Role rol) throws Exception {
        RoleEntity rolEntity = getRoleEntityDao().roleToEntity(rol);
        if (getAuthorizationService().hasPermission(Security.AUTO_ROLE_UPDATE, rolEntity)) {

            getRoleEntityDao().update(rolEntity); // actualizamos cambios del rol

            return getRoleEntityDao().toRole(rolEntity);
        }

		throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.UpdateApplicationError"), getPrincipal().getName(), rol.getInformationSystemName()));
    }

    protected RoleAccount handleCreate(RoleAccount rolsUsuaris) throws Exception {
        String codiAplicacio = rolsUsuaris.getInformationSystemName();
        
        if (rolsUsuaris.getAccountId() == null && rolsUsuaris.getAccountName() != null)
        {
        	AccountEntity acc = getAccountEntityDao().findByNameAndSystem(rolsUsuaris.getAccountName(), rolsUsuaris.getSystem());
        	if (acc != null)
        		rolsUsuaris.setAccountId(acc.getId());
        }
        // Verify the user has one account
        if (rolsUsuaris.getAccountId() == null && rolsUsuaris.getUserCode() != null)
        {
           	Account account = null;
        	Security.nestedLogin(Security.getCurrentAccount(), new String[] { 
        		Security.AUTO_USER_QUERY+Security.AUTO_ALL,
        		Security.AUTO_ACCOUNT_QUERY, 
        		Security.AUTO_ACCOUNT_CREATE,
        		Security.AUTO_ACCOUNT_QUERY+Security.AUTO_ALL, 
        		Security.AUTO_ACCOUNT_CREATE+Security.AUTO_ALL});
           	try {
           		List<UserAccount> accounts = getAccountService().findUsersAccounts(rolsUsuaris.getUserCode(), rolsUsuaris.getSystem());
           		if (accounts.size() > 1)
           		{
           			throw new NeedsAccountNameException();
           		}
           		else if (accounts.size() == 0)
           		{
           			User usu = getUserService().findUserByUserName(rolsUsuaris.getUserCode());
           			SystemEntity dispatcher = getSystemEntityDao().findByName(rolsUsuaris.getSystem());
           			if (dispatcher == null)
           				throw new InternalErrorException(String.format(Messages.getString("ApplicationServiceImpl.UnknownSystem"), rolsUsuaris.getSystem()));
           			account = getAccountService().createAccount(usu, getSystemEntityDao().toSystem(dispatcher), null);
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
        	
        RoleAccountEntity rolsUsuarisEntity = getRoleAccountEntityDao().roleAccountToEntity(rolsUsuaris);
        // Enable or disable on dates
        rolsUsuarisEntity.setEnabled(getEnableState(rolsUsuarisEntity));
        // Check for Sod Rules
        SoDRule rule = getSoDRuleService().isAllowed(rolsUsuaris);
        if (rule != null && rule.getRisk() == SoDRisk.SOD_FORBIDDEN)
        {
           	throw new InternalErrorException (String.format(Messages.getString("ApplicationServiceImpl.SoDRuleNotAllowRole") //$NON-NLS-1$
           					, rule.getName()));
        }
        // Launch workflow approval process
        boolean nwap = needsWorkflowApprovalProcess(rolsUsuarisEntity);
            
        rolsUsuarisEntity.setApprovalPending(nwap);
           	
       	
       	if (!getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_CREATE, rolsUsuarisEntity))
    		throw new SeyconAccessLocalException("aplicacioService", "create (RolAccount)", "user:role:create", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    				Messages.getString("ApplicationServiceImpl.UnableCreateRol"), codiAplicacio)); //$NON-NLS-1$


        getRoleAccountEntityDao().create(rolsUsuarisEntity);
        AccountEntity account = rolsUsuarisEntity.getAccount();
        account.getRoles().add(rolsUsuarisEntity);
        rolsUsuaris = getRoleAccountEntityDao().toRoleAccount(rolsUsuarisEntity);
        	
        if (nwap)
        	launchWorkflowApprovalProcess(rolsUsuarisEntity);
        else
         	getAccountEntityDao().propagateChanges(account);
            
        enableOrDisableOnDates (rolsUsuaris, rolsUsuarisEntity);
            
        return rolsUsuaris;
    }

    /**
     * @param RoleAccountEntity
     * @throws InternalErrorException 
	 */
	private boolean needsWorkflowApprovalProcess(RoleAccountEntity RoleAccountEntity) throws InternalErrorException {
		RoleEntity role = RoleAccountEntity.getRole();
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
     * @param RoleAccountEntity
     * @throws InternalErrorException 
	 */
	private void launchWorkflowApprovalProcess(RoleAccountEntity RoleAccountEntity) throws InternalErrorException {
		RoleEntity role = RoleAccountEntity.getRole();
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
					RoleAccount ra = getRoleAccountEntityDao().toRoleAccount(RoleAccountEntity);
		            SoDRule rule = getSoDRuleService().isAllowed(ra);
		            if (rule != null)
		            	ra.setSodRisk(rule.getRisk());

					pi.getContextInstance().createVariable("request", ra);
					pi.getContextInstance().createVariable("requesterAccount", Security.getCurrentAccount());
					pi.getContextInstance().createVariable("requesterUser", Security.getCurrentUser());
					pi.signal();
					ctx.save(pi);
					
					for (UserAccountEntity ua : RoleAccountEntity.getAccount().getUsers()) {
                        BpmUserProcess uwp = new BpmUserProcess();
                        uwp.setUserCode(ua.getUser().getUserName());
                        uwp.setProcessId(pi.getId());
                        uwp.setTerminated(false);
                        getUserService().create(uwp);
                    }
					RoleAccountEntity.setApprovalProcess(pi.getId());
				} finally {
					ctx.close();
				}
				getRoleAccountEntityDao().update(RoleAccountEntity);
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
	private void checkGroupHolder(RoleAccount rolsUsuaris) throws InternalErrorException, UnknownUserException {
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
            		for (Group grup : getUserService().getUserGroupsHierarchy(ue.getId())) {
                        if (grup.getName().equals(rolsUsuaris.getHolderGroup())) {
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

	protected void handleDelete(RoleAccount rolsUsuaris) throws Exception {
        String codiAplicacio = rolsUsuaris.getInformationSystemName();
        // if (esAdministracioPersonal(rolsUsuaris) || esAdministradorUsuaris())
        // {
        RoleAccountEntity rolsUsuarisEntity = getRoleAccountEntityDao().load(rolsUsuaris.getId());
    	if (rolsUsuarisEntity == null)
    		return;
    	
        if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_DELETE, rolsUsuarisEntity)) {

        	if (rolsUsuarisEntity.getRule() != null)
                throw new InternalErrorException(Messages.getString("ApplicationServiceImpl.CannotRevokeManually")); //$NON-NLS-1$
            // Disable assigning roles to himself
        	UserEntity user = null;
            for (UserAccountEntity ua : rolsUsuarisEntity.getAccount().getUsers()) {
                if (ua.getUser().getUserName().equals(getPrincipal().getName())) {
                    throw new SeyconException(Messages.getString("ApplicationServiceImpl.UserAddRolError"));
                }
                user = ua.getUser();
            }
            
            deleteRoleAccountEntity(rolsUsuarisEntity, user);
            return;
        } 
        throw new SeyconAccessLocalException("aplicacioService", "delete (RolAccount)", "user:role:delete", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("ApplicationServiceImpl.UnableDeleteRol"), codiAplicacio)); //$NON-NLS-1$
    }

	private void deleteRoleAccountEntity(RoleAccountEntity rolsUsuarisEntity, UserEntity user) throws InternalErrorException {
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
    protected void handleDenyApproval(RoleAccount rolsUsuaris) throws Exception {
		RoleAccountEntity rolsUsuarisEntity = getRoleAccountEntityDao().load(rolsUsuaris.getId());
		if (rolsUsuarisEntity == null)
			return ;
		if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_DELETE, rolsUsuarisEntity))
		{
        	if (rolsUsuarisEntity.getRule() != null)
                throw new InternalErrorException("This role cannot be manually revoked. It's granted by a rule.");
            // Disable assigning roles to himself
            for (UserAccountEntity ua : rolsUsuarisEntity.getAccount().getUsers()) {
                if (ua.getUser().getUserName().equals(getPrincipal().getName())) {
                    throw new SeyconException(Messages.getString("ApplicationServiceImpl.UserAddRolError"));
                }
            }
            
            getRoleAccountEntityDao().remove(rolsUsuarisEntity);
            
            return;
        } 
        throw new SeyconAccessLocalException("aplicacioService", "delete (RolAccount)", "user:role:delete", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("ApplicationServiceImpl.UnableDeleteRol"), rolsUsuarisEntity.getRole().getInformationSystem().getName())); //$NON-NLS-1$
    }

	protected RoleAccount handleUpdate(RoleAccount rolsUsuaris) throws Exception {
        RoleAccountEntity oldRoleAccountEntity = getRoleAccountEntityDao().load(rolsUsuaris.getId());
		if (oldRoleAccountEntity == null)
			return rolsUsuaris;
		if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_DELETE, oldRoleAccountEntity))
		{
	        RoleAccount oldRolsUsuaris = getRoleAccountEntityDao().toRoleAccount(oldRoleAccountEntity);
	        String codiAplicacio = rolsUsuaris.getInformationSystemName();

	        if (!rolsUsuaris.getAccountName().equals(oldRolsUsuaris.getAccountName()) || !rolsUsuaris.getAccountSystem().equals(oldRolsUsuaris.getAccountSystem()) || !rolsUsuaris.getSystem().equals(oldRolsUsuaris.getSystem()) || !rolsUsuaris.getRoleName().equals(oldRolsUsuaris.getRoleName()))
        	{
        		throw new SeyconAccessLocalException("aplicacioService", "create (RolAccount)", "user:role:create", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        				"Invalid rol grant change. Cannot change rol or account")); //$NON-NLS-1$
        	}
            RoleAccountEntity roleAccountEntity = getRoleAccountEntityDao().roleAccountToEntity(rolsUsuaris);

        	roleAccountEntity.setEnabled(getEnableState(roleAccountEntity));
        	
    		if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_CREATE, roleAccountEntity))
    		{
        		getRoleAccountEntityDao().update(roleAccountEntity);
        	
            	// Actualitzem darrera actualització de l'usuari
            	getAccountEntityDao().propagateChanges(roleAccountEntity.getAccount());
            
            	return rolsUsuaris;
    		} else {
            	throw new SeyconAccessLocalException("aplicacioService", "create (RolAccount)", "user:role:create", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        				Messages.getString("ApplicationServiceImpl.UnableCreateRol"), roleAccountEntity.getInformationSystem().getName())); //$NON-NLS-1$
    		}
        }
        else
        	throw new SeyconAccessLocalException("aplicacioService", "create (RolAccount)", "user:role:delete", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("ApplicationServiceImpl.UnableCreateRol"), 
					oldRoleAccountEntity.getRole().getInformationSystem().getName())); //$NON-NLS-1$
    }

    protected Collection<RoleAccount> handleFindUserRolesByUserName(String codiUsuari) throws Exception {// desde usuaris.zul para ver qué roles puede
                              // mostrar
        List<RoleAccountEntity> rolusus = getRoleAccountEntityDao().findByUserName(codiUsuari);

        if (rolusus != null) {
            // Filtrem per autoritzacions
            List<RoleAccount> ra = new LinkedList<RoleAccount>();
            for (RoleAccountEntity rae : rolusus) {
                if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_QUERY, rae)) ;
                ra.add(getRoleAccountEntityDao().toRoleAccount(rae));
            }
    		getSoDRuleService().qualifyRolAccountList(ra);
    		return ra;
       }
        return new Vector();
    }

    protected Collection<ContainerRole> handleFindTextualInformationAndUserRolesHierachyByUserName(String codiUsuari) throws Exception {
        return handleFindTextualInformationAndUserRolesHierachyByUserName(codiUsuari, "N"); //$NON-NLS-1$
    }

    
    protected Collection<ContainerRole> handleFindTextualInformationAndUserRolesHierachyByUserName(String codiUsuari, String filtraResultats) throws Exception {
        // Obtenemos el usuario
        UserEntity usuari = getUserEntityDao().findByUserName(codiUsuari);

		UserEntity user = getUserEntityDao().load(usuari.getId());
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateRoles(radSet, ALL, user);
		LinkedList<ContainerRole> rgl = new LinkedList<ContainerRole>();
		for (RolAccountDetail rad : radSet) {
            RoleGrant rg = null;
            if (rad.rolRol != null) {
                RoleDependencyEntity rar = rad.rolRol;
                ContainerRole cContingut = getRoleDependencyEntityDao().toContainerRole(rar);
                ContainerRole crol = getRoleEntityDao().toContainerRole(rar.getContained());
                if (rad.qualifier != null) crol.setContainerInfo(crol.getContainerInfo() + " / " + rad.qualifier.getValue());
                if (rad.qualifierGroup != null) crol.setContainerInfo(crol.getContainerInfo() + " / " + rad.qualifierGroup.getName());
                if (rad.qualifierAplicacio != null) crol.setContainerInfo(crol.getContainerInfo() + " / " + rad.qualifierAplicacio.getName());
                crol.setMetaInfo(String.format(Messages.getString("ApplicationServiceImpl.RoleGrantedToRol"), cContingut.getContainerInfo()));
                if (!"S".equals(filtraResultats) || getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, rad.rolRol)) rgl.add(crol);
            }
            if (rad.rolGrup != null) {
                ContainerRole cr = getRoleEntityDao().toContainerRole(rad.rolGrup.getAssignedRole());
                if (rad.qualifier != null) cr.setContainerInfo(cr.getContainerInfo() + " / " + rad.qualifier.getValue());
                if (rad.qualifierGroup != null) cr.setContainerInfo(cr.getContainerInfo() + " / " + rad.qualifierGroup.getName());
                if (rad.qualifierAplicacio != null) cr.setContainerInfo(cr.getContainerInfo() + " / " + rad.qualifierAplicacio.getName());
                cr.setMetaInfo(String.format(Messages.getString("ApplicationServiceImpl.RoleGrantedToGroup"), rad.rolGrup.getOwnerGroup().getName()));
                if (!"S".equals(filtraResultats) || getAuthorizationService().hasPermission(Security.AUTO_GROUP_ROLE_QUERY, rad.rolGrup)) rgl.add(cr);
            }
        }

		return rgl;
    }

    protected Collection<RoleAccount> handleFindUsersRolesByUserNameAndRoleName(String codiUsuari, String nomRol) throws Exception {
        Collection<RoleAccountEntity> rolusuEntity = getRoleAccountEntityDao().findByUserAndRole(codiUsuari, nomRol);
        if (rolusuEntity != null) {
            List<RoleAccount> ra = new LinkedList<RoleAccount>();
            for (RoleAccountEntity rae : rolusuEntity) {
                if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_QUERY, rae)) ra.add(getRoleAccountEntityDao().toRoleAccount(rae));
            }
    		getSoDRuleService().qualifyRolAccountList(ra);
    		return ra;

            /*
             * getRoleAccountEntityDao().toRolAccountCollection(rolusuEntity);
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

    protected Collection<RoleAccount> handleFindUsersRolesByRoleName(String nomRol) throws Exception {
        Collection<RoleAccountEntity> rolusus = getRoleAccountEntityDao().findByRole(nomRol);
        if (rolusus != null) {
            List<RoleAccount> ra = new LinkedList<RoleAccount>();
            for (RoleAccountEntity rae : rolusus) {
                if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_QUERY, rae)) ra.add(getRoleAccountEntityDao().toRoleAccount(rae));
            }
    		getSoDRuleService().qualifyRolAccountList(ra);
    		return ra;
        }
        return new Vector();
    }

    protected Collection<Role> handleFindRolesByDomainNameAndApplicationName(String nomDomini, String codiAplicacio) throws Exception {
    	List<RoleEntity> roles = getRoleEntityDao().findByInformationSystemAndDomain(codiAplicacio, nomDomini);
        if (roles != null) {
            return getRoleEntityDao().toRoleList(roles);
        }
        return new Vector();

    }

    protected ApplicationAdministration handleUpdate(ApplicationAdministration administracioAplicacio) throws Exception {
        RoleAccountEntity administracioAplicacioEntity = getRoleAccountEntityDao().applicationAdministrationToEntity(administracioAplicacio);
        if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_CREATE, administracioAplicacioEntity))
       	{
            getRoleAccountEntityDao().update(administracioAplicacioEntity);
            administracioAplicacio.setId(administracioAplicacio.getId());
            administracioAplicacio = getRoleAccountEntityDao().toApplicationAdministration(administracioAplicacioEntity);
            return administracioAplicacio;
        }
		throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NotPermisionToUpdate"), getPrincipal().getName(), administracioAplicacio.getInformationSystemName()));
    }

    protected Role handleFindRoleById(Long rolId) throws Exception {
        RoleEntity rolEntity = getRoleEntityDao().findById(rolId);
        if (rolEntity != null) {
            return getRoleEntityDao().toRole(rolEntity);
        }
        return null;
    }

    protected Collection handleFindRolsContinguts(Role contenidor) throws Exception {
    	RoleEntity parent = getRoleEntityDao().load(contenidor.getId());
    	LinkedList<Role> children = new LinkedList<Role>();
    	for (RoleDependencyEntity dep : parent.getContainedRole()) {
            children.add(getRoleEntityDao().toRole(dep.getContained()));
        }
    	return children;
    }

    protected Collection<Group> handleFindRoleHoldersGroupsByRole(Role rol) throws Exception {
        RoleEntity rolEntity = getRoleEntityDao().roleToEntity(rol);
        Collection<RoleGroupEntity> rolsGrups = rolEntity.getContainerGroups();
        Collection<GroupEntity> grups = new ArrayList();
        for (Iterator<RoleGroupEntity> it = rolsGrups.iterator(); it.hasNext(); ) {
            RoleGroupEntity rge = it.next();
            grups.add(rge.getOwnerGroup());
        }
        return getGroupEntityDao().toGroupList(grups);
        
    }

    protected Collection<Role> handleFindGrantedRolesToGroupByGroup(Group grup) throws Exception {
        GroupEntity grupEntity = getGroupEntityDao().groupToEntity(grup);
        Collection<RoleGroupEntity> rolsGrups = grupEntity.getAllowedRolesToGroup();
        Collection<RoleEntity> rols = new ArrayList();
        for (Iterator<RoleGroupEntity> it = rolsGrups.iterator(); it.hasNext(); ) {
            RoleGroupEntity rge = it.next();
            rols.add(rge.getAssignedRole());
        }
        return getRoleEntityDao().toRoleList(rols);
        
        
    }

    protected java.security.Principal getPrincipal() {
        return Security.getPrincipal();
    }

    public Collection findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(String nomRol, String codiAplicacio, String codiDispatcher) throws InternalErrorException {

        return handleFindUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(nomRol, codiAplicacio, codiDispatcher);
    }

    /*
     * Obtenim els usuaris a qui es atorgat un rol (per herència) (!!)
     */
    protected Collection handleFindTextualInformationAndRolesHierachyByApplicationRoleAndDispatcher(String nomRole, String codiAplicacioRol, String codiDispatcher) {

        // Obtenemos los roles contenidos del usuario (en modo textual !!)
        Collection<ContainerRole> resultat = new ArrayList<ContainerRole>();

        
        // Obtenemos el ROL
        RoleEntity theRol = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRole, codiAplicacioRol, codiDispatcher);

		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		
		LinkedList<RoleGrant> rg = new LinkedList<RoleGrant>();
		populateParentGrantsForRol(radSet, theRol, null);
		for (RolAccountDetail rad : radSet) {
            if (rad.granteeRol != null) {
                if (rad.account.getType().equals(AccountType.USER)) {
                    for (UserAccountEntity uae : rad.account.getUsers()) {
                        ContainerRole cr = new ContainerRole();
                        UserEntity ue = uae.getUser();
                        cr.setType(ue.getUserName());
                        cr.setContainerInfo(ue.getFullName());
                        cr.setMetaInfo(rad.granteeRol.toRoleDescription());
                        resultat.add(cr);
                    }
                } else {
                    ContainerRole cr = new ContainerRole();
                    cr.setType(rad.account.getName());
                    cr.setContainerInfo(rad.account.getDescription());
                    cr.setMetaInfo(rad.granteeRol.toRoleDescription());
                    resultat.add(cr);
                }
            } else if (rad.granteeGrup != null) {
                if (rad.account.getType().equals(AccountType.USER)) {
                    for (UserAccountEntity uae : rad.account.getUsers()) {
                        ContainerRole cr = new ContainerRole();
                        UserEntity ue = uae.getUser();
                        cr.setType(ue.getUserName());
                        cr.setContainerInfo(ue.getFullName());
                        cr.setMetaInfo(rad.granteeGrup.getName() + " - " + rad.granteeGrup.getDescription());
                        resultat.add(cr);
                    }
                } else {
                    ContainerRole cr = new ContainerRole();
                    cr.setType(rad.account.getName());
                    cr.setContainerInfo(rad.account.getDescription());
                    cr.setMetaInfo(rad.granteeGrup.getName() + " - " + rad.granteeGrup.getDescription());
                    resultat.add(cr);
                }
            }
        }
		return resultat;

    }

    protected Collection<Object> handleGetPendingAlerts(String codiAplicacio) throws Exception {

        // Carreguem totes
        Collection notificacions = null;
        if (codiAplicacio == null)
            notificacions = getNoticeEntityDao().loadAll();
        else
            notificacions = getNoticeEntityDao().findByApplicationCode(codiAplicacio);

        HashMap<Application, ArrayList<String>> hNotifica = new HashMap();
        // guardem les aplicacioEntity i la seua Aplicacio(VO) corresponent:
        HashMap aplicacions = new HashMap();

        if (notificacions != null && notificacions.size() > 0) {
        	// van per aplicació
            for (Iterator it = notificacions.iterator(); it.hasNext(); ) {
                NoticeEntity notif = (NoticeEntity) it.next();
                InformationSystemEntity aplicacio = notif.getApplication();
                Application aplic = null;
                if ((aplic = (Application) aplicacions.get(aplicacio)) == null) {
                    aplic = getInformationSystemEntityDao().toApplication(aplicacio);
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

    protected void handleRemoveSentAlerts(String codiAplicacio, Date dataDelete) throws Exception {

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


    protected Collection<AuthorizationRole> handleFindRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName(String nomRole, String codiAplicacioRol, String codiDispatcher) throws Exception {
        RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRole, codiAplicacioRol, codiDispatcher);

        if (getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, rolEntity) && getAuthorizationService().hasPermission(Security.AUTO_AUTHORIZATION_QUERY, null))
        {
            LinkedList<AuthorizationRole> totPermis = new LinkedList();

            if (rolEntity != null) {
                // Obtenim tota la jerarquía de rols que té heretats
                Collection<RoleEntity> totRol = getRolEntityHeretatsByRol(rolEntity);

                if (totRol != null) {
                    for (Iterator<RoleEntity> rit = totRol.iterator(); rit.hasNext(); ) {
                        RoleEntity r = rit.next();
                        Collection autos = getAuthorizationEntityDao().findByRoleID(r.getId());
                        if (autos != null) for (Iterator it = autos.iterator(); it.hasNext(); ) {
                            AuthorizationEntity auto = (AuthorizationEntity) it.next();
                            Collection desc = getAuthorizationService().getAuthorizationInfo(auto.getAuthorization());
                            AuthorizationRole autoVO = getAuthorizationEntityDao().toAuthorizationRole(auto);
                            if (desc != null && desc.iterator().hasNext()) {
                                AutoritzacioSEU a = (AutoritzacioSEU) desc.iterator().next();
                                autoVO.setDescription(a.getDescripcio());
                                autoVO.setScope(a.getAmbit());
                                autoVO.setInherit(a.getHereta());
                                autoVO.setBusinessGroupScope(a.getScope());
                            }
                            totPermis.add(autoVO);
                        }
                    }
                }
            }
            return totPermis;
        } else {
			throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.UserNotAccesToApplication"), //$NON-NLS-1$
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
    protected Collection<AccessTreeAuthorization> handleFindApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName(String nomRole, String codiAplicacioRol, String codiDispatcher) throws Exception {

        RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRole, codiAplicacioRol, codiDispatcher);
        if (getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, rolEntity))
        {
            LinkedList<AccessTreeAuthorization> totPermis = new LinkedList();

            if (rolEntity != null) {
                // Obtenim tota la jerarquía de rols que té heretats
                Collection<RoleEntity> totRol = getRolEntityHeretatsByRol(rolEntity);

                if (totRol != null) {
                    for (Iterator<RoleEntity> rit = totRol.iterator(); rit.hasNext(); ) {
                        RoleEntity r = rit.next();
                        for (EntryPointRoleEntity aut : getEntryPointRoleEntityDao().findByRoleId(r.getId())) {
                            AccessTreeAuthorization autoVO = getEntryPointRoleEntityDao().toAccessTreeAuthorization(aut);
                            autoVO.setAuthorizedEntityCode(aut.getEntryPoint().getName());
                            totPermis.add(autoVO);
                        }
                    }
                }
            }
            return totPermis;
        } else {
			throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.UserNotAccesToApplication"), //$NON-NLS-1$
					getPrincipal().getName(), codiAplicacioRol));
        }

    }

    @Override
    protected Collection<NetworkAuthorization> handleFindNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName(String nomRole, String codiAplicacioRol, String codiDispatcher) throws Exception {
        RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRole, codiAplicacioRol, codiDispatcher);
        if (getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, rolEntity))
        {
            // Cerquem el rol

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
					String.format(Messages.getString("ApplicationServiceImpl.UserNotAccesToApplication"), //$NON-NLS-1$
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
    protected Collection<RoleGrant> handleFindRoleGrantByAccount(Long accountId) throws Exception {
		AccountEntity account = getAccountEntityDao().load(accountId);
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateAccountRoles(radSet, DIRECT, account, null);
		LinkedList<RoleGrant> rg = new LinkedList<RoleGrant>();
		for (RolAccountDetail rad : radSet) {
            if (rad.granted.getSystem().getId().equals(account.getSystem().getId())) {
                if (rad.rolAccount != null && shouldBeEnabled(rad.rolAccount)) rg.add(getRoleAccountEntityDao().toRoleGrant(rad.rolAccount));
                if (rad.rolRol != null) rg.add(getRoleDependencyEntityDao().toRoleGrant(rad.rolRol));
                if (rad.rolGrup != null) rg.add(getRoleGroupEntityDao().toRoleGrant(rad.rolGrup));
            }
        }
		return rg;
	}

	@Override
    protected Collection<RoleAccount> handleFindRoleAccountByAccount(long accountId) throws Exception {
		LinkedList<RoleAccount> rg = new LinkedList<RoleAccount>();
		AccountEntity account = getAccountEntityDao().load(accountId);
		if (account == null)
			return rg;
		
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateAccountRoles(radSet, DIRECT, account, null);
		for (RolAccountDetail rad : radSet) {
            if (rad.granted.getSystem().getId().equals(account.getSystem().getId())) {
                if (rad.rolAccount != null) rg.add(getRoleAccountEntityDao().toRoleAccount(rad.rolAccount));
            }
        }
		getSoDRuleService().qualifyRolAccountList(rg);
		return rg;
	}

	@Override
    protected Collection<RoleGrant> handleFindEffectiveRoleGrantByUser(long userId) throws Exception {
		UserEntity user = getUserEntityDao().load(userId);
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateRoles(radSet, ALL, user);
		LinkedList<RoleGrant> rgl = new LinkedList<RoleGrant>();
		for (RolAccountDetail rad : radSet) {
            RoleGrant rg = null;
            if (rad.rolAccount != null && shouldBeEnabled(rad.rolAccount)) rg = (getRoleAccountEntityDao().toRoleGrant(rad.rolAccount));
            if (rad.rolRol != null) {
                rg = (getRoleDependencyEntityDao().toRoleGrant(rad.rolRol));
                if (rad.qualifier != null) rg.setDomainValue(rad.qualifier.getValue()); else if (rad.qualifierAplicacio != null) rg.setDomainValue(rad.qualifierAplicacio.getName()); else if (rad.qualifierGroup != null) rg.setDomainValue(rad.qualifierGroup.getName());
            }
            if (rad.rolGrup != null) rg = (getRoleGroupEntityDao().toRoleGrant(rad.rolGrup));
            if (rg != null) {
                if (rad.account != null) rg.setOwnerAccountName(rad.account.getName());
                rgl.add(rg);
            }
        }
		return rgl;
	}

	@Override
    protected Collection<RoleGrant> handleFindEffectiveRoleGrantByAccount(long accountId) throws Exception {
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
		LinkedList<RoleGrant> rg = new LinkedList<RoleGrant>();
		for (RolAccountDetail rad : radSet) {
            if (rad.account != null && rad.account.getId().longValue() == accountId) {
                if (rad.rolAccount != null && shouldBeEnabled(rad.rolAccount)) rg.add(getRoleAccountEntityDao().toRoleGrant(rad.rolAccount));
                if (rad.rolRol != null) {
                    RoleGrant r = getRoleDependencyEntityDao().toRoleGrant(rad.rolRol);
                    if (rad.qualifier != null) r.setDomainValue(rad.qualifier.getValue()); else if (rad.qualifierAplicacio != null) r.setDomainValue(rad.qualifierAplicacio.getName()); else if (rad.qualifierGroup != null) r.setDomainValue(rad.qualifierGroup.getName());
                    rg.add(r);
                }
                if (rad.rolGrup != null) rg.add(getRoleGroupEntityDao().toRoleGrant(rad.rolGrup));
            }
        }
		return rg;
	}

	@Override
    protected Collection<RoleGrant> handleFindRoleGrantByRole(Long rolId, Long numRegistres) throws Exception {
        // Contem quants usuaris hi ha registrats
    	CriteriaSearchConfiguration config = new CriteriaSearchConfiguration();
    	if (numRegistres != null)
    		config.setMaximumResultSize(new Integer(numRegistres.intValue()));

    	RoleEntity role = getRoleEntityDao().load(rolId);
    	// Remove inactive grants
    	List<RoleGrant> result = new LinkedList<RoleGrant>();
    	for (RoleAccountEntity rae : role.getAccounts()) {
            if (shouldBeEnabled(rae)) result.add(getRoleAccountEntityDao().toRoleGrant(rae));
        }
        return result;
	}

	@Override
    protected Collection<RoleGrant> handleFindEffectiveRoleGrantsByRoleId(Long rolId) throws Exception {
		RoleEntity rol = getRoleEntityDao().load(rolId);
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		
		LinkedList<RoleGrant> rg = new LinkedList<RoleGrant>();
		if (rol == null)
			return rg;
		
		populateParentGrantsForRol(radSet, rol, null);
		for (RolAccountDetail rad : radSet) {
            RoleGrant grant;
            if (rad.rolAccount != null) grant = getRoleAccountEntityDao().toRoleGrant(rad.rolAccount); else if (rad.rolRol != null) {
                grant = getRoleDependencyEntityDao().toRoleGrant(rad.rolRol);
                if (rad.qualifier != null) grant.setDomainValue(rad.qualifier.getValue()); else if (rad.qualifierAplicacio != null) grant.setDomainValue(rad.qualifierAplicacio.getName()); else if (rad.qualifierGroup != null) grant.setDomainValue(rad.qualifierGroup.getName());
            } else grant = getRoleGroupEntityDao().toRoleGrant(rad.rolGrup);
            if (rad.account != null) {
                grant.setOwnerAccountName(rad.account.getName());
                grant.setOwnerSystem(rad.account.getSystem().getName());
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
				else if (originalGrant instanceof RoleDependencyEntity)
                {
                	rad = new RolAccountDetail((RoleDependencyEntity) originalGrant, acc, null);
                }
				else
					rad = null;
                if (rad != null && !radSet.contains(rad)) {
					rad.granteeGrup = granteeGroup;
                    radSet.add(rad);
                }
            }
        }
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AplicacioServiceBase#handleEnableOrDisableOnDates(es.caib.seycon.ng.comu.RolAccount)
	 */
	@Override
    protected RoleAccount handleEnableOrDisableOnDates(RoleAccount rolAccount) throws Exception {
		RoleAccountEntity entity = getRoleAccountEntityDao().load(rolAccount.getId());
		enableOrDisableOnDates(rolAccount, entity);
		return rolAccount;
		
	}

	private void enableOrDisableOnDates(RoleAccount rolAccount, RoleAccountEntity entity) throws InternalErrorException {
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
                    deleteRoleAccountEntity(rae, user);
                }
            }
        }
	}

	@Override
    protected Role handleFindRoleByNameAndSystem(String name, String system) throws Exception {
        RoleEntity rolEntity = getRoleEntityDao().findByNameAndSystem(name, system);
        if (rolEntity == null)
        	return null;
        // Cap dels tres paràmetres pot ésser null
        // Mirem l'autorització de l'aplicació (fer query als rols de la app
        // no requereixen tindre una autorització específica)
        if (getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, rolEntity))
		{
            return getRoleEntityDao().toRole(rolEntity);
        } else {
			throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NoAccessToRol"),  //$NON-NLS-1$
					getPrincipal().getName(), name));
        }
	}

	@Override
    protected Collection<RoleAccount> handleFindUserRolesByInformationSystem(String informationSystem) throws Exception {
		return getRoleAccountEntityDao().toRoleAccountList(getRoleAccountEntityDao().findByInformationSystem(informationSystem));
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
		if (qualifier == null && previous != null && 
				previous.qualifier != null &&
				ra.getContained().getApplicationDomain() != null &&
				ra.getContained().getApplicationDomain().getName().equals(
						previous.qualifier.getDomain().getName()))
			qualifier = previous.qualifier;
		if (qualifierAplicacio == null && previous != null &&
				granted.getDomainType().equals(TipusDomini.APLICACIONS))
			qualifierAplicacio = previous.qualifierAplicacio;
		if (qualifierGroup == null && previous != null &&
				( granted.getDomainType().equals(TipusDomini.GRUPS) ||
						granted.getDomainType().equals(TipusDomini.GRUPS_USUARI)))
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
