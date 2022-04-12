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

import java.io.File;
import java.io.UnsupportedEncodingException;
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
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;
import org.jbpm.JbpmContext;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.json.JSONException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.soffid.iam.api.AccessTreeAuthorization;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.ApplicationAdministration;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.BpmUserProcess;
import com.soffid.iam.api.ContainerRole;
import com.soffid.iam.api.Domain;
import com.soffid.iam.api.DomainType;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.NetworkAuthorization;
import com.soffid.iam.api.PagedResult;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleAccount;
import com.soffid.iam.api.RoleDependencyStatus;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.SoDRule;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserAccount;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.ApplicationAttributeEntity;
import com.soffid.iam.model.AuthorizationEntity;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.EntryPointRoleEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.MetaDataEntity;
import com.soffid.iam.model.MetaDataEntityDao;
import com.soffid.iam.model.NetworkAuthorizationEntity;
import com.soffid.iam.model.NoticeEntity;
import com.soffid.iam.model.Parameter;
import com.soffid.iam.model.QueryBuilder;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleAttributeEntity;
import com.soffid.iam.model.RoleAttributeEntityImpl;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserAccountEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupAttributeEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.service.attribute.AttributePersister;
import com.soffid.iam.service.impl.AttributeValidationService;
import com.soffid.iam.service.impl.RolGrantDiffReport;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.DateUtils;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.SoffidAuthorization;
import com.soffid.iam.utils.TimeOutUtils;
import com.soffid.scimquery.EvalException;
import com.soffid.scimquery.HQLQuery;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.parser.ExpressionParser;
import com.soffid.scimquery.parser.ParseException;
import com.soffid.scimquery.parser.TokenMgrError;

import es.caib.bpm.vo.PredefinedProcessType;
import com.soffid.iam.api.DelegationStatus;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.SoDRisk;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.AccountAlreadyExistsException;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.NeedsAccountNameException;
import es.caib.seycon.ng.exception.SeyconAccessLocalException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownUserException;

/**
 * @see es.caib.seycon.ng.servei.AplicacioService Versió remixed, remade &
 *      remodelled per les autoritzacions de SEU
 */
@SuppressWarnings("rawtypes")
public class ApplicationServiceImpl extends
        com.soffid.iam.service.ApplicationServiceBase 
        implements ApplicationContextAware
{
	Log log = LogFactory.getLog(getClass());
	final int DIRECT = 0;
	final int INDIRECT = 1;
	final int ALL = 2;
	final int NONE=3;
	private ApplicationContext ctx;

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	public void setApplicationContext (ApplicationContext applicationContext)
					throws BeansException
	{
		ctx = applicationContext;
		
	}
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
            updateApplicationAttributes(aplicacio, apl);
            return (getInformationSystemEntityDao().toApplication(apl));
        }
		throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NoUserPermission"), //$NON-NLS-1$
				Security.getCurrentAccount())); //$NON-NLS-1$
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
            updateApplicationAttributes(aplicacio, aplEntity);
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
    	int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$

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
                aplicacions += "\'" + aplicacio.getName().replace('\'', '_').replace('\"', '_') + "\'" + (aplicacionsIterator.hasNext() ? "," : ""); //$NON-NLS-1$ //$NON-NLS-2$
            }
            String query = "select distinct rol.informationSystem " //$NON-NLS-1$
                    + "from com.soffid.iam.model.RoleEntity rol where " //$NON-NLS-1$
                    + "rol.name like :rol and " //$NON-NLS-1$
                    + "rol.informationSystem.name in (" //$NON-NLS-1$ //$NON-NLS-2$
                    + aplicacions + ") and"
                    + "rol.informationSystem.tenant.id = :tenantId"; //$NON-NLS-1$
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
        if (administracioAplicacio.getUserName().compareTo(Security.getCurrentUser()) == 0) {
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
		throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NoPermissionToAsign"), Security.getCurrentAccount(), administracioAplicacio.getInformationSystemName()));
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
			throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NoPermissionToDelete"), 
					Security.getCurrentAccount(), administracioAplicacio.getInformationSystemName()));
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
    	int limitResults = Integer.parseInt(ConfigurationCache.getProperty("soffid.ui.maxrows")); //$NON-NLS-1$
    	
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
						Security.getCurrentAccount(), nomRol));
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
                if (acc.getType().equals(AccountType.USER) && acc.getUsers().size() == 1 && ra.isEnabled()) {
                    UserEntity user = acc.getUsers().iterator().next().getUser();
                    toReturn.add(getUserEntityDao().toUser(user));
                }
            }
            return toReturn;
        } else {
			throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NotPermisionToSearch"), //$NON-NLS-1$
				Security.getCurrentAccount(), codiAplicacio));
        }
    }

    protected Collection<RoleAccount> handleFindUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName(String nomRol, String codiAplicacio, String codiDispatcher) throws InternalErrorException {
        RoleEntity rolEntity = getRoleEntityDao().findRoleByNameInformationSystemAndStystem(nomRol, codiAplicacio, codiDispatcher);
    	if (!getAuthorizationService().hasPermission(Security.AUTO_ROLE_QUERY, rolEntity))
    		throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NoAccessToRol"),  //$NON-NLS-1$
				Security.getCurrentAccount(), nomRol));

        List<RoleAccount> toReturn = new LinkedList<RoleAccount>();
        for (RoleAccountEntity ra : rolEntity.getAccounts()) {
        	if (ra.isEnabled())
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
        rolEntity = getRoleEntityDao().create(rol, false);

        updateRoleAttributes(rol, rolEntity);

        return getRoleEntityDao().toRole(rolEntity);
    }

    protected Role handleCreate2(Role rol) throws Exception {
        // if (usuariPotActualitzarAplicacio(rol.getCodiAplicacio())) {

        RoleEntity existingRole = getRoleEntityDao()
                .findByNameAndSystem(rol.getName(),
                        rol.getSystem());

        // No permitim crear un rol amb el mateix nom y base de dades si ja
        // existeix un altre
        if (existingRole != null) {
                String aplicacio = existingRole.getInformationSystem()
                        .getName();

				throw new SeyconException(
						String.format(Messages.getString("AplicacioServiceImpl.ExistentRole"),  //$NON-NLS-1$
								rol.getName(), rol.getSystem(), aplicacio));
        }

        // Obtenemos la entidad asociada al VO
        RoleEntity rolEntity = getRoleEntityDao().roleToEntity(rol);
        
        if  (! getAuthorizationService().hasPermission(Security.AUTO_ROLE_CREATE, rolEntity))
            throw new SeyconAccessLocalException("AplicacioService", //$NON-NLS-1$
                    "create (Rol)", "application:update, application:create", //$NON-NLS-1$ //$NON-NLS-2$
                    Messages.getString("AplicacioServiceImpl.NotAuthorizedToManageRol")); //$NON-NLS-1$
        // Creamos la entidad asociada al VO Rol
        rolEntity = getRoleEntityDao().create(rol, true);

        updateRoleAttributes(rol, rolEntity);
        
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
        	for (RoleAccountEntity ra: new LinkedList<RoleAccountEntity> (rolEntity.getAccounts()))
        	{
        		if ( ! ra.isEnabled())
        		{
        			log.info ("Removing roleAccount "+ra.getId());
        			getRoleAccountEntityDao().remove(ra);
        			rolEntity.getAccounts().remove(ra);
        		}
        		else
        			log.info ("Keeping roleAccount "+ra.getId());
        	}
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
        String oldName = rolEntity.getName();
        if (getAuthorizationService().hasPermission(Security.AUTO_ROLE_UPDATE, rolEntity)) {

            rolEntity = getRoleEntityDao().update(rol, false); // actualizamos cambios del rol

            updateRoleAttributes(rol, rolEntity);
            
            if (!oldName.equals(rol.getName()))
            {
        		TaskEntity t = getTaskEntityDao().newTaskEntity();
       			t.setTransaction("UpdateRole");
       			t.setRole(oldName);
        		t.setSystemName(rol.getSystem());
        		t.setDb(rol.getSystem());
        		getTaskEntityDao().create(t);
            	// Propagate users
            	for ( RoleGrant user: handleFindEffectiveRoleGrantsByRoleId(rolEntity.getId()))
            	{
            		t = getTaskEntityDao().newTaskEntity();
            		if (user.getUser() != null)
            		{
            			t.setTransaction("UpdateUser");
            			t.setUser(user.getUser());
            		}
            		else
            		{
            			t.setTransaction("UpdateAccount");
            			t.setUser(user.getOwnerAccountName());
            		}
            		t.setSystemName(rol.getSystem());
            		t.setDb(rol.getSystem());
            		getTaskEntityDao().create(t);
            	}
            }
            
            return getRoleEntityDao().toRole(rolEntity);
        }

		throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.UpdateApplicationError"), 
				Security.getCurrentAccount(), rol.getInformationSystemName()));
    }

    protected RoleAccount handleCreate(RoleAccount rolsUsuaris) throws Exception {
        if (rolsUsuaris.getAccountId() == null && rolsUsuaris.getAccountName() != null)
        {
        	AccountEntity acc = getAccountEntityDao().findByNameAndSystem(rolsUsuaris.getAccountName(), rolsUsuaris.getSystem());
        	if (acc != null) {
        		rolsUsuaris.setAccountId(acc.getId());
        	}
        }
        	
        if ( rolsUsuaris.getStartDate() == null)
        	rolsUsuaris.setStartDate(new Date());
        
        List<RoleAccount> grantsToCreate = new LinkedList<RoleAccount>();
        grantsToCreate.add(rolsUsuaris);
        boolean first = true;
        while ( ! grantsToCreate.isEmpty())
        {
        	RoleAccount ra = grantsToCreate.get(0);
        	grantsToCreate.remove(0);
        	ra = performCreateRolAccount(rolsUsuaris, ra, grantsToCreate, first);
        	if (first)
        		rolsUsuaris = ra;
            first = false;
        }
        
        if (rolsUsuaris.getUserCode() != null && rolsUsuaris.getRuleId() == null)
        	notifyUserChange(getUserEntityDao().findByUserName(rolsUsuaris.getUserCode()));

        return rolsUsuaris;
    }

	private RoleAccount performCreateRolAccount(RoleAccount inital,
			RoleAccount ra,
			List<RoleAccount> grantsToCreate, boolean first)
			throws InternalErrorException, NeedsAccountNameException,
			AccountAlreadyExistsException, UnknownUserException {
    	String codiAplicacio = inital.getInformationSystemName();
        boolean skip = false;
        // Verify the user has one account
        if (ra.getAccountId() == null && ra.getUserCode() != null)
        {
           	Account account = null;
        	Security.nestedLogin(Security.getCurrentAccount(), new String[] { 
        		Security.AUTO_USER_QUERY+Security.AUTO_ALL,
        		Security.AUTO_ACCOUNT_UPDATE,
        		Security.AUTO_ACCOUNT_QUERY, 
        		Security.AUTO_ACCOUNT_CREATE,
        		Security.AUTO_ACCOUNT_QUERY+Security.AUTO_ALL, 
        		Security.AUTO_ACCOUNT_CREATE+Security.AUTO_ALL});
           	try {
           		List<UserAccount> accounts = getAccountService().findUsersAccounts(ra.getUserCode(), ra.getSystem());
           		if (accounts.size() > 1)
           		{
           			if (first)
						throw new NeedsAccountNameException(String.format("Please, specify account for user %s on system %s",
								ra.getUserCode(), ra.getSystem()));
           		}
           		else if (accounts.size() == 0)
           		{
           			User usu = getUserService().findUserByUserName(inital.getUserCode());
           			SystemEntity dispatcher = getSystemEntityDao().findByName(ra.getSystem());
           			if (dispatcher == null)
           				throw new InternalErrorException(String.format(Messages.getString("ApplicationServiceImpl.UnknownSystem"), 
           						inital.getSystem()));
           			account = getAccountService().createAccount(usu, getSystemEntityDao().toSystem(dispatcher), null);
           		}
           		else
           		{
           			account = accounts.iterator().next();
           		}
           	} finally {
           		Security.nestedLogoff();
           	}
        	ra.setAccountId(account.getId());
        	ra.setAccountName(account.getName());
        }
        	
        // Check group holder
        checkGroupHolder (ra);
        // Check for Sod Rules
        SoDRule rule = getSoDRuleService().isAllowed(ra);
        if (rule != null && rule.getRisk() == SoDRisk.SOD_FORBIDDEN)
        {
        	if (first)
        		throw new InternalErrorException (String.format(Messages.getString("ApplicationServiceImpl.SoDRuleNotAllowRole") //$NON-NLS-1$
           					, rule.getName()));
        	else
        		skip = true;
        }
        if (!skip)
        {
		    RoleAccountEntity rolsUsuarisEntity = getRoleAccountEntityDao()
		            .roleAccountToEntity(ra);
		    // Enable or disable on dates
		    rolsUsuarisEntity.setEnabled(getEnableState(rolsUsuarisEntity));
		    // Launch workflow approval process
		    boolean nwap = first && needsWorkflowApprovalProcess(rolsUsuarisEntity);
		    
		   	rolsUsuarisEntity.setApprovalPending(nwap);
		   	
		   	if (first && 
		   			! getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_CREATE, rolsUsuarisEntity))
				throw new SeyconAccessLocalException("aplicacioService", "create (RolAccount)", "user:role:create", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						Messages.getString("AplicacioServiceImpl.UnableCreateRol"), codiAplicacio)); //$NON-NLS-1$

		   	// Test if role is already granted
		   	for (RoleAccountEntity rg: new LinkedList<RoleAccountEntity> ( rolsUsuarisEntity.getAccount().getRoles()))
		   	{
		   		if (rg.isEnabled()) {
			   		if (rg.getRole().getName().equals(ra.getRoleName()) && 
			   				rg.getRole().getSystem().getName().equals(ra.getSystem()))
			   		{
			   			// Granted on another group
			   			if (rg.getGroup() != null && 
			   					! rg.getGroup().getName().equals( ra.getDomainValue().getValue()))
			   				continue ;
			   			// Granted on another information system
			   			else if (rg.getInformationSystem() != null && 
			   					! rg.getInformationSystem().getName().equals( ra.getDomainValue().getValue()))
			   				continue;
			   			// Granted on another custom domain value
			   			else if (rg.getDomainValue() != null && 
			   					! rg.getDomainValue().getValue().equals( ra.getDomainValue().getValue()))
			   				continue ;
			   			else if (rg.isEnabled() && (rg.getEndDate() == null || rg.getEndDate().after(new Date()))) {
			   				if (rg.getRule() == null && ra.getRuleId() != null) {
			   					rg.setRule(getRuleEntityDao().load(ra.getRuleId()));
			   					getRoleAccountEntityDao().update(rg);
			   				}
			   				return getRoleAccountEntityDao().toRoleAccount(rg);
			   			}
			   			else
			   			{
			   				deleteRoleAccountEntity(rg, null, true);
			   				rolsUsuarisEntity.getAccount().getRoles().remove(rg);
			   			}
			   		}
			   		else if (rolsUsuarisEntity.getRole().getInformationSystem() == rg.getRole().getInformationSystem() &&
			   			Boolean.TRUE.equals(rolsUsuarisEntity.getRole().getInformationSystem().getSingleRole()))
			   		{
		   				deleteRoleAccountEntity(rg, null, true);
		   				rolsUsuarisEntity.getAccount().getRoles().remove(rg);
			   		}
		   		}
		   	}
		   	getRoleAccountEntityDao().create(rolsUsuarisEntity);
		    AccountEntity account = rolsUsuarisEntity.getAccount();
		    account.getRoles().add(rolsUsuarisEntity);
		    
		    if (first)
		    	inital = getRoleAccountEntityDao().toRoleAccount(rolsUsuarisEntity);
			
		    if (nwap)
		    	launchWorkflowApprovalProcess(rolsUsuarisEntity, "grant");
		    else
		    {
		       	if ( ! nwap )
		       	{
		       		for ( RoleDependencyEntity grantedRole: rolsUsuarisEntity.getRole().getContainedRoles())
		       		{
		       			if (grantedRole.getMandatory() != null && ! grantedRole.getMandatory().booleanValue())
		       			{
		       				if (ra.getUserCode() != null || 
		       						rolsUsuarisEntity.getAccount().getSystem() == grantedRole.getContained().getSystem())
		       				{
		           				RoleAccount ra2 = new RoleAccount();
		           				ra2.setAccountSystem( grantedRole.getContained().getSystem().getName() );
		           				ra2.setApprovalPending(false);
		           				ra2.setSystem(grantedRole.getContained().getSystem().getName() );
		           				ra2.setCertificationDate(new Date ());
		           				ra2.setInformationSystemName(grantedRole.getContained().getInformationSystem().getName());
		           				ra2.setUserGroupCode(inital.getUserGroupCode());
		           				ra2.setUserCode(inital.getUserCode());
		           				ra2.setRoleDescription(grantedRole.getContained().getDescription());
		           				ra2.setStartDate(inital.getStartDate());
		           				ra2.setEndDate(inital.getEndDate());
		           				ra2.setHolderGroup(inital.getHolderGroup());
		           				ra2.setRoleName(grantedRole.getContained().getName());
		           				ra2.setParentGrant(rolsUsuarisEntity.getId());
		           				if ( grantedRole.getDomainApplication() != null) {
		           					ra2.setDomainValue(new DomainValue());
		           					ra2.getDomainValue().setValue(grantedRole.getDomainApplication().getName());
		           				}
		           				else if (grantedRole.getDomainApplicationValue() != null) {
		           					ra2.setDomainValue(new DomainValue());
		           					ra2.getDomainValue().setExternalCodeDomain(grantedRole.getDomainApplicationValue().getDomain().getName());
		           					ra2.getDomainValue().setValue(grantedRole.getDomainApplicationValue().getValue());
		           				}
		           				else if (grantedRole.getDomainGroup() != null ) {
		           					ra2.setDomainValue(new DomainValue());
		           					ra2.getDomainValue().setValue(grantedRole.getDomainGroup().getName());
		           				} else if ( grantedRole.getContainer().getDomainType() != null &&
		           						grantedRole.getContainer().getDomainType().equals(grantedRole.getContained().getDomainType())) {
		           					ra2.setDomainValue(inital.getDomainValue()); 
		           				} else {
		           					ra2.setDomainValue(new DomainValue());
		           				}
		           				grantsToCreate.add(ra2);
		       				}
		       			}
		       		}
		       	}
		       	if (first & ! account.getType().equals(AccountType.IGNORED))
		       		getAccountEntityDao().propagateChanges(account);
		    }
		    
		    enableOrDisableOnDates (inital, rolsUsuarisEntity);
		    return getRoleAccountEntityDao().toRoleAccount(rolsUsuarisEntity);
		}
		else
			return null;
        	
    }

    	/**
     * @param RoleAccountEntity
     * @throws InternalErrorException 
	 */
	private boolean needsWorkflowApprovalProcess(RoleAccountEntity RoleAccountEntity) throws InternalErrorException {
		RoleEntity role = RoleAccountEntity.getRole();
		if (role != null)
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
     * @param roleAccountEntity
     * @throws InternalErrorException 
	 */
	private void launchWorkflowApprovalProcess(RoleAccountEntity roleAccountEntity, String action) throws InternalErrorException {
		RoleEntity role = roleAccountEntity.getRole();
		if (role != null)
		{
			InformationSystemEntity app = role.getInformationSystem();
			if (app != null && app.getApprovalProcess() != null)
			{
				List def = getBpmEngine().findProcessDefinitions(app.getApprovalProcess(), PredefinedProcessType.ROLE_GRANT_APPROVAL);
				if (def.isEmpty())
					throw new InternalErrorException("Approval process %s for application %s is not available", app.getApprovalProcess(), app.getName());
				JbpmContext ctx = getBpmEngine().getContext();
				try {
					ProcessInstance pi = ctx.newProcessInstance(app.getApprovalProcess());
					RoleAccount ra = getRoleAccountEntityDao().toRoleAccount(roleAccountEntity);
		            SoDRule rule = getSoDRuleService().isAllowed(ra);
		            if (rule != null)
		            	ra.setSodRisk(rule.getRisk());

					pi.getContextInstance().createVariable("request", ra);
					pi.getContextInstance().createVariable("requesterAccount", Security.getCurrentAccount());
					pi.getContextInstance().createVariable("requesterUser", Security.getCurrentUser());
					pi.getContextInstance().createVariable("action", action);
					// For new workflow interface
					pi.signal();
					ctx.save(pi);
					
					for (UserAccountEntity ua : roleAccountEntity.getAccount().getUsers()) {
                        BpmUserProcess uwp = new BpmUserProcess();
                        uwp.setUserCode(ua.getUser().getUserName());
                        uwp.setProcessId(pi.getId());
                        uwp.setTerminated(false);
                        getUserService().create(uwp);
                    }
					roleAccountEntity.setApprovalProcess(pi.getId());
				} finally {
					ctx.close();
				}
				getRoleAccountEntityDao().update(roleAccountEntity);
			}
		}
	}

	UserEntity getAccountUser(Long accountId) throws InternalErrorException {
    	// Guess usuari
    	AccountEntity acc = getAccountEntityDao().load(accountId);
    	if (acc == null) throw new InternalErrorException("Cannot find account "+accountId);
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
		if ("never".equals(ConfigurationCache.getProperty("soffid.entitlement.group.holder")) || 
				rolsUsuaris.getRuleId() != null)
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
            	else if (rolsUsuaris.getHolderGroup().length () == 0 && "optional".equals(ConfigurationCache.getProperty("soffid.entitlement.group.holder")))
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

	private void notifyUserChange (UserEntity user) throws InternalErrorException
	{
		for (String name : ctx.getBeanNamesForType(SoffidEventListener.class))
		{
			SoffidEventListener bean = (SoffidEventListener) ctx.getBean(name);
			if (bean != null)
				bean.onUserChange(user);
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
        	{
        		if (Security.isSyncServer()) // SYNC SERVER
            		return;
        		throw new InternalErrorException(Messages.getString("AplicacioServiceImpl.CannotRevokeManually")); //$NON-NLS-1$
        	}
            // Disable assigning roles to himself
        	UserEntity user = null;
            for (UserAccountEntity ua : rolsUsuarisEntity.getAccount().getUsers()) {
                if (ua.getUser().getUserName().equals(Security.getCurrentUser())) {
                    throw new SeyconException(Messages.getString("ApplicationServiceImpl.UserAddRolError"));
                }
                user = ua.getUser();
            }
            
            deleteRoleAccountEntity(rolsUsuarisEntity, user, false);
            if (user != null && rolsUsuarisEntity.getRule() == null)
            	notifyUserChange(user);
            
            return;
        } 
        throw new SeyconAccessLocalException("aplicacioService", "delete (RolAccount)", "user:role:delete", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("ApplicationServiceImpl.UnableDeleteRol"), codiAplicacio)); //$NON-NLS-1$
    }

	private void deleteRoleAccountEntity(RoleAccountEntity rolsUsuarisEntity, UserEntity user, boolean force) throws InternalErrorException {
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
		
		if (!force && rolsUsuarisEntity.getRemovalPending() != null && rolsUsuarisEntity.getRemovalPending().booleanValue())
		{
			JbpmContext ctx = getBpmEngine().getContext();
			try 
			{
				ProcessInstance pi = ctx.getProcessInstance(rolsUsuarisEntity.getApprovalProcess());
				if (pi != null && !pi.hasEnded())
				{
					if (force)
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
					else
					{
						// Remove is in process
						return;
					}
				}
			} finally {
				ctx.close();
			}
		}

		// Launch workflow approval process
	    boolean nwap = !force && needsWorkflowApprovalProcess(rolsUsuarisEntity);
	    
	    if (nwap && ! rolsUsuarisEntity.isApprovalPending() )
	    {
		   	rolsUsuarisEntity.setRemovalPending(Boolean.TRUE);
		   	launchWorkflowApprovalProcess(rolsUsuarisEntity, "revoke");
	    }
	    else
	    {
			if (rolsUsuarisEntity.getParent() != null)
			{
				rolsUsuarisEntity.getChildren().remove(rolsUsuarisEntity);
			}
			for ( RoleAccountEntity child: new LinkedList<RoleAccountEntity> (rolsUsuarisEntity.getChildren()) )
				deleteRoleAccountEntity(child, null, true);
			if (ConfigurationCache.isHistoryEnabled())
			{
				rolsUsuarisEntity.setEnabled(false);
				if (rolsUsuarisEntity.getEndDate() == null ||
						rolsUsuarisEntity.getEndDate().after(new Date()))
					rolsUsuarisEntity.setEndDate(new Date());
				getRoleAccountEntityDao().update(rolsUsuarisEntity, "D");
			}
			else
			{
				getRoleAccountEntityDao().remove(rolsUsuarisEntity);
			}
			if (Hibernate.isInitialized(rolsUsuarisEntity.getAccount().getRoles()))
				rolsUsuarisEntity.getAccount().getRoles().remove(rolsUsuarisEntity);
			if (Hibernate.isInitialized(rolsUsuarisEntity.getRole().getAccounts()))
				rolsUsuarisEntity.getRole().getAccounts().remove(rolsUsuarisEntity);
			getAccountEntityDao().propagateChanges(rolsUsuarisEntity.getAccount());
	    }
	}

	@Override
    protected void handleDenyApproval(RoleAccount rolsUsuaris) throws Exception {
		RoleAccountEntity rolsUsuarisEntity = getRoleAccountEntityDao().load(rolsUsuaris.getId());
		if (rolsUsuarisEntity == null)
			return ;
		if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_DELETE, rolsUsuarisEntity))
		{
            // Disable assigning roles to himself
            for (UserAccountEntity ua : rolsUsuarisEntity.getAccount().getUsers()) {
                if (ua.getUser().getUserName().equals(Security.getCurrentUser())) {
                    throw new SeyconException(Messages.getString("ApplicationServiceImpl.UserAddRolError"));
                }
            }
            
            if (rolsUsuarisEntity.getRule() != null)
            {
            	rolsUsuarisEntity.setApprovalPending(false);
            	rolsUsuarisEntity.setEndDate(new Date());
            	rolsUsuarisEntity.setStartDate(new Date());
            	rolsUsuarisEntity.setEnabled(false);
            	getRoleAccountEntityDao().update(rolsUsuarisEntity);
            }
            else
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

	        if (!rolsUsuaris.getAccountName().equals(oldRolsUsuaris.getAccountName()) || !rolsUsuaris.getAccountSystem().equals(oldRolsUsuaris.getAccountSystem()) || 
	        		!rolsUsuaris.getSystem().equals(oldRolsUsuaris.getSystem()) || !rolsUsuaris.getRoleName().equals(oldRolsUsuaris.getRoleName()))
	        {
	        	log.info("Trying to modify " + oldRolsUsuaris);
	        	log.info("To "+rolsUsuaris);
        		throw new SeyconAccessLocalException("aplicacioService", "create (RolAccount)", "user:role:create", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        				"Invalid rol grant change. Cannot change rol or account " )); //$NON-NLS-1$
	        }

	        RoleAccountEntity roleAccountEntity = getRoleAccountEntityDao().roleAccountToEntity(rolsUsuaris);

        	roleAccountEntity.setEnabled(getEnableState(roleAccountEntity));
        	
    		if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_CREATE, roleAccountEntity))
    		{
        		getRoleAccountEntityDao().update(roleAccountEntity);
        	
	        	// Create non mandatory role - role dependencies first time the grant is enabled
	        	if (oldRolsUsuaris.isApprovalPending() && ! rolsUsuaris.isApprovalPending())
	        	{
	        		LinkedList<RoleAccount> grantsToCreate = new LinkedList<RoleAccount>();
		       		for ( RoleDependencyEntity grantedRole: roleAccountEntity.getRole().getContainedRoles())
		       		{
		       			if (grantedRole.getMandatory() != null && ! grantedRole.getMandatory().booleanValue())
		       			{
		       				if (rolsUsuaris.getUserCode() != null || 
		       						roleAccountEntity.getAccount().getSystem() == grantedRole.getContained().getSystem())
		       				{
		           				RoleAccount ra2 = new RoleAccount();
		           				ra2.setAccountSystem( grantedRole.getContained().getSystem().getName() );
		           				ra2.setApprovalPending(false);
		           				ra2.setSystem(grantedRole.getContained().getSystem().getName() );
		           				ra2.setCertificationDate(new Date ());
		           				ra2.setInformationSystemName(grantedRole.getContained().getInformationSystem().getName());
		           				ra2.setUserGroupCode(rolsUsuaris.getUserGroupCode());
		           				ra2.setUserCode(rolsUsuaris.getUserCode());
		           				ra2.setRoleDescription(grantedRole.getContained().getDescription());
		           				ra2.setEndDate(rolsUsuaris.getEndDate());
		           				ra2.setHolderGroup(rolsUsuaris.getHolderGroup());
		           				ra2.setRoleName(grantedRole.getContained().getName());
		           				ra2.setParentGrant(roleAccountEntity.getId());
		           				grantsToCreate.add(ra2);
		       				}
		       			}
		       		}
		       		while ( ! grantsToCreate.isEmpty())
		       		{
		       			RoleAccount ra = grantsToCreate.get(0);
		       			grantsToCreate.remove(0);
			       		performCreateRolAccount(rolsUsuaris, ra, grantsToCreate, false);
		       		}
	        	}
            	// Actualitzem darrera actualització de l'usuari
            	getAccountEntityDao().propagateChanges(roleAccountEntity.getAccount());
            
                if (rolsUsuaris.getUserCode() != null && rolsUsuaris.getRuleId() == null)
                	notifyUserChange(getUserEntityDao().findByUserName(rolsUsuaris.getUserCode()));

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
    	return internalFindUserRolesByUserName (codiUsuari, true, false);
    }

    protected Collection<RoleAccount> handleFindUserRolesHistoryByUserName(String codiUsuari) throws Exception {// desde usuaris.zul para ver qué roles puede
    	return internalFindUserRolesByUserName (codiUsuari, true, true);
    }

    private Collection<RoleAccount> internalFindUserRolesByUserName(String codiUsuari, boolean sod, boolean history) throws InternalErrorException {
    	List<RoleAccountEntity> rolusus = getRoleAccountEntityDao().findByUserName(codiUsuari);
    	
    	if (rolusus != null) {
    		// Filtrem per autoritzacions
    		List<RoleAccount> ra = new LinkedList<RoleAccount>();
    		for (RoleAccountEntity rae : rolusus) {
    			if ( history || 
    					(rae.isEnabled() && shouldBeEnabled(rae)) ||
    					(!history && rae.isApprovalPending())) {
    				if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_QUERY, rae)) 
    					ra.add(getRoleAccountEntityDao().toRoleAccount(rae));
    			}
    		}
    		if (sod)
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
		populateRoles(radSet, ALL, user, null);
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
                ContainerRole cr = getRoleEntityDao().toContainerRole(rad.rolGrup.getGrantedRole());
                if (rad.qualifier != null) cr.setContainerInfo(cr.getContainerInfo() + " / " + rad.qualifier.getValue());
                if (rad.qualifierGroup != null) cr.setContainerInfo(cr.getContainerInfo() + " / " + rad.qualifierGroup.getName());
                if (rad.qualifierAplicacio != null) cr.setContainerInfo(cr.getContainerInfo() + " / " + rad.qualifierAplicacio.getName());
                cr.setMetaInfo(String.format(Messages.getString("ApplicationServiceImpl.RoleGrantedToGroup"), rad.rolGrup.getGroup().getName()));
                if (!"S".equals(filtraResultats) || getAuthorizationService().hasPermission(Security.AUTO_GROUP_ROLE_QUERY, rad.rolGrup)) rgl.add(cr);
            }
        }

		return rgl;
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
		throw new SeyconException(String.format(Messages.getString("ApplicationServiceImpl.NotPermisionToUpdate"), 
				Security.getCurrentAccount(), administracioAplicacio.getInformationSystemName()));
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
    	for (RoleDependencyEntity dep : parent.getContainedRoles()) {
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
            grups.add(rge.getGroup());
        }
        return getGroupEntityDao().toGroupList(grups);
        
    }

    protected Collection<Role> handleFindGrantedRolesToGroupByGroup(Group grup) throws Exception {
        GroupEntity grupEntity = getGroupEntityDao().groupToEntity(grup);
        Collection<RoleGroupEntity> rolsGrups = grupEntity.getGrantedRoles();
        Collection<RoleEntity> rols = new ArrayList();
        for (Iterator<RoleGroupEntity> it = rolsGrups.iterator(); it.hasNext(); ) {
            RoleGroupEntity rge = it.next();
            rols.add(rge.getGrantedRole());
        }
        return getRoleEntityDao().toRoleList(rols);
        
        
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
		populateParentGrantsForRol(radSet, theRol, null, null);
		for (RolAccountDetail rad : radSet) {
			if (rad.account != null && rad.account.getId() == null)
			{
				// Ignore
			} else if (rad.granteeRol != null) {
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
                                SoffidAuthorization a = (SoffidAuthorization) desc.iterator().next();
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
					Security.getCurrentAccount(), codiAplicacioRol));
        }

    }

    private Collection<RoleEntity> getRolsHeretatsDirectes(RoleEntity rolEntity) {
        HashMap<Long, RoleEntity> fills = new HashMap();
        Collection rolsAsocHeretats = rolEntity.getContainedRoles();
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
					Security.getCurrentAccount(), codiAplicacioRol));
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
					Security.getCurrentAccount(), codiAplicacioRol));
        }
    }
    
    private void populateRoles(Set<RolAccountDetail> rad, int type, UserEntity user, GroupEntity holderGroup) {
    	if (type == NONE)
    		return;

    	for (AccountEntity account: getAccountEntityDao().findByUser(user.getId()))
    	{
   			populateAccountRoles (rad, type, account, user, holderGroup);
    	}
    	
    	if (type == INDIRECT || type == ALL)
    	{
    		if (holderGroup == null || holderGroup  == user.getPrimaryGroup())
    			populateGroupRoles(rad, ALL, user.getPrimaryGroup(), user);
    		for (UserGroupEntity ug : user.getSecondaryGroups()) {
        		if (! Boolean.TRUE.equals(ug.getDisabled()) &&
        				(holderGroup == null || holderGroup  == ug.getGroup()))
        			populateGroupRoles(rad, ALL, ug.getGroup(), user);
            }
    	}
    	
    }
    
	private void populateGroupRoles(Set<RolAccountDetail> rad, int type, GroupEntity grup, UserEntity originUser) {
		if (type == NONE)
			return;
		
		for (RoleGroupEntity rg : grup.getGrantedRoles()) {
            for (AccountEntity ae : getAccountsForDispatcher(originUser, null, rg.getGrantedRole().getSystem())) {
                RolAccountDetail n = new RolAccountDetail(rg, ae);
                n.granteeGrup = grup;
                if (!rad.contains(n)) {
                    if (type == DIRECT || type == ALL) rad.add(n);
                    if (type == INDIRECT || type == ALL) {
                        for (AccountEntity acc : getAccountsForDispatcher(originUser, null, rg.getGrantedRole().getSystem())) populateRoleRoles(rad, ALL, n, originUser, acc);
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
		
		for (RoleDependencyEntity ra : rol.getContainedRoles()) {
			if ((ra.getMandatory() == null || ra.getMandatory().booleanValue()) &&
					matchesGranteeDomainValue (currentRol, ra) && 
					(ra.getStatus() == null || 
					 ra.getStatus().equals(RoleDependencyStatus.STATUS_ACTIVE) ||
					 ra.getStatus().equals(RoleDependencyStatus.STATUS_TOREMOVE)))
            if (matchesGranteeDomainValue(currentRol, ra)) {
                for (AccountEntity ae : getAccountsForDispatcher(originUser, originAccount, ra.getContained().getSystem())) {
                    RolAccountDetail n = new RolAccountDetail(ra, ae, currentRol);
                    n.granteeRol = rol;
                    n.generateHash();
                    if (! rad.contains(n)) {
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
		if (DelegationStatus.DELEGATION_ACTIVE.equals(e.getDelegationStatus()) &&
				e.getDelegateUntil() != null &&
				new Date ().after(e.getDelegateUntil()))
		{
			return false;
		}
		
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
	
	private void populateAccountRoles(Set<RolAccountDetail> rad, int type, AccountEntity account, UserEntity user, GroupEntity holderGroup) {
		for (RoleAccountEntity ra : account.getRoles()) {
			if (holderGroup == null || 
				ra.getHolderGroup() == null ||
				ra.getHolderGroup() == holderGroup)
			{
	            RolAccountDetail n = new RolAccountDetail(ra, account);
	            if (!rad.contains(n) && !ra.isApprovalPending() && shouldBeEnabled(ra) && ra.isEnabled()) {
	                if (type == DIRECT || type == ALL) rad.add(n);
	                if ((type == INDIRECT || type == ALL) && shouldBeEnabled(ra)) populateRoleRoles(rad, ALL, n, user, account);
	            }
			}
        }
	}

	@Override
    protected Collection<RoleGrant> handleFindRoleGrantByAccount(Long accountId) throws Exception {
		AccountEntity account = getAccountEntityDao().load(accountId);
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateAccountRoles(radSet, DIRECT, account, null, null);
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
    protected RoleAccount handleFindRoleAccountById(long id) throws Exception {
		RoleAccountEntity entity = getRoleAccountEntityDao().load(id);
		if (entity == null)
			return null;
		
		return getRoleAccountEntityDao().toRoleAccount(entity);
	}

	@Override
    protected Collection<RoleAccount> handleFindRoleAccountByAccount(long accountId) throws Exception {
		LinkedList<RoleAccount> rg = new LinkedList<RoleAccount>();
		AccountEntity account = getAccountEntityDao().load(accountId);
		if (account == null)
			return rg;
		
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateAccountRoles(radSet, DIRECT, account, null, null);
		for (RolAccountDetail rad : radSet) {
            if (rad.granted.getSystem().getId().equals(account.getSystem().getId())) {
                if (rad.rolAccount != null) rg.add(getRoleAccountEntityDao().toRoleAccount(rad.rolAccount));
            }
        }
		getSoDRuleService().qualifyRolAccountList(rg);
		return rg;
	}

    protected Collection<RoleAccount> handleFindRoleAccountHistoryByAccount(long accountId) throws Exception {
		LinkedList<RoleAccount> rg = new LinkedList<RoleAccount>();
		AccountEntity account = getAccountEntityDao().load(accountId);
		if (account == null)
			return rg;
		
    	Collection<RoleAccountEntity> rolusus = account.getRoles();
    	
		// Filtrem per autoritzacions
		List<RoleAccount> ra = new LinkedList<RoleAccount>();
		for (RoleAccountEntity rae : rolusus) {
			ra.add(getRoleAccountEntityDao().toRoleAccount(rae));
		}
		return ra;
	}


    @Override
    protected Collection<RoleGrant> handleFindEffectiveRoleGrantByUser(long userId) throws Exception {
		UserEntity user = getUserEntityDao().load(userId);
		if (user.getUserName().equals(Security.getCurrentUser()) && 
				! "true".equals(ConfigurationCache.getProperty("soffid.delegation.disable")))
		{
			for ( RoleAccount ra: getEntitlementDelegationService().findDelegationsToAccept())
			{
				getEntitlementDelegationService().acceptDelegation(ra);
			}
			getEntitlementDelegationService().revertExpiredDelegations();
		}
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateRoles(radSet, ALL, user, null);
		LinkedList<RoleGrant> rgl = new LinkedList<RoleGrant>();
		for (RolAccountDetail rad : radSet) {
            RoleGrant rg = null;
            if (rad.rolAccount != null && shouldBeEnabled(rad.rolAccount)) rg = (getRoleAccountEntityDao().toRoleGrant(rad.rolAccount));
            if (rad.rolRol != null) {
                rg = (getRoleDependencyEntityDao().toRoleGrant(rad.rolRol));
                if (rad.qualifier != null) rg.setDomainValue(rad.qualifier.getValue()); 
                else if (rad.qualifierAplicacio != null) rg.setDomainValue(rad.qualifierAplicacio.getName()); 
                else if (rad.qualifierGroup != null) rg.setDomainValue(rad.qualifierGroup.getName());
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
    protected Collection<RoleGrant> handleFindEffectiveRoleGrantByUserAndHolderGroup(long userId, long groupId) throws Exception {
		UserEntity user = getUserEntityDao().load(userId);
		if (user.getUserName().equals(Security.getCurrentUser()) && 
				! "true".equals(ConfigurationCache.getProperty("soffid.delegation.disable")))
		{
			for ( RoleAccount ra: getEntitlementDelegationService().findDelegationsToAccept())
			{
				getEntitlementDelegationService().acceptDelegation(ra);
			}
			getEntitlementDelegationService().revertExpiredDelegations();
		}
		GroupEntity group = getGroupEntityDao().load(groupId);
		if (group == null)
		{
			throw new InternalErrorException("Uknown group "+groupId);
		}
		HashSet<RolAccountDetail> radSet = new HashSet<RolAccountDetail>();
		populateRoles(radSet, ALL, user, group);
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
				populateAccountRoles(radSet, ALL, account, user.getUser(), null);
				populateRoles(radSet, INDIRECT, user.getUser(), null);
			}
		}
		else
			populateAccountRoles(radSet, ALL, account, null, null);
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
            if (rae.isEnabled() && shouldBeEnabled(rae)) result.add(getRoleAccountEntityDao().toRoleGrant(rae));
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
		
		populateParentGrantsForRol(radSet, rol, null, null);
		toRoleGrantList(radSet, rg);
		return rg;
	}

	private void toRoleGrantList(HashSet<RolAccountDetail> radSet, LinkedList<RoleGrant> rg) {
		for (RolAccountDetail rad : radSet) {
            RoleGrant grant;
            if (rad.rolAccount != null) 
            	grant = getRoleAccountEntityDao().toRoleGrant(rad.rolAccount); 
            else if (rad.rolRol != null) 
            {
                grant = getRoleDependencyEntityDao().toRoleGrant(rad.rolRol);
                if (rad.qualifier != null) 
                	grant.setDomainValue(rad.qualifier.getValue());
                else if (rad.qualifierAplicacio != null) 
                	grant.setDomainValue(rad.qualifierAplicacio.getName()); else if (rad.qualifierGroup != null) grant.setDomainValue(rad.qualifierGroup.getName());
            } else 
            	grant = getRoleGroupEntityDao().toRoleGrant(rad.rolGrup);
            if (rad.account != null && rad.account.getId() != null) {
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
	}

	private void populateParentGrantsForRol(HashSet<RolAccountDetail> radSet, 
			RoleEntity rol, 
			RoleDependencyEntity originalGrant,
			RoleEntity roleToAddOrUpdate) {
		
		for (RoleAccountEntity rac : rol.getAccounts()) {
            if (rac.isEnabled() && shouldBeEnabled(rac)) {
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

		if (rol != null && roleToAddOrUpdate != null && rol.getId().equals( roleToAddOrUpdate.getId()))
			rol = roleToAddOrUpdate; // Use new role configuration			

		for (RoleDependencyEntity ra : rol.getContainerRoles()) {
			if ((ra.getStatus() == null || 
				ra.getStatus().equals(RoleDependencyStatus.STATUS_ACTIVE) || 
				ra.getStatus().equals(RoleDependencyStatus.STATUS_TOREMOVE)) && 
					(ra.getMandatory() == null || ra.getMandatory().booleanValue()))
			{
				populateParentGrantsForRol(radSet, ra.getContainer(), originalGrant == null ? ra: originalGrant, roleToAddOrUpdate);
			}
        }

		for (RoleGroupEntity rg : rol.getContainerGroups()) {
            populateParentGrantsForGroup(radSet, rg.getGroup(), originalGrant == null ? rg : originalGrant);
        }
	}

	private void populateParentGrantsForGroup(HashSet<RolAccountDetail> radSet, GroupEntity grup, Object originalGrant) {
		for (UserEntity u : grup.getPrimaryGroupUsers()) {
			if (u.getActive().equals("S"))
				populateParentGrantsForUser(radSet, u, originalGrant, grup);
        }
		
		for (UserGroupEntity sg : grup.getSecondaryGroupUsers()) {
			if (! Boolean.TRUE.equals(sg.getDisabled())  && sg.getUser().getActive().equals("S"))
				populateParentGrantsForUser(radSet, sg.getUser(), originalGrant, grup);
        }

		for (GroupEntity fill : grup.getChildren()) {
            populateParentGrantsForGroup(radSet, fill, originalGrant);
        }
	}

	private void populateParentGrantsForUser(HashSet<RolAccountDetail> radSet, 
			UserEntity u, Object originalGrant, 
			GroupEntity granteeGroup) {
		if (u.getActive().equals("S"))
		{
			SystemEntity de;
			if (originalGrant instanceof RoleDependencyEntity)
				de = ((RoleDependencyEntity) originalGrant).getContained().getSystem();
			else
				de = ((RoleGroupEntity) originalGrant).getGrantedRole().getSystem();
				
			
			List<AccountEntity> accounts = getAccountsForDispatcher(u, null, de);
			for (AccountEntity acc : accounts) {
	            if (acc != null) {
	                addAccountGrant(acc, radSet, originalGrant, granteeGroup);
	            }
	            else
	            {
					AccountEntity dummyAccount;
					try {
						String accName = getAccountService().guessAccountName(u.getUserName(), de.getName());
						if (accName != null)
						{
							dummyAccount = getAccountEntityDao().newAccountEntity();
							dummyAccount.setName(accName);
							dummyAccount.setDescription(u.getUserName());
							dummyAccount.setDisabled(false);
							dummyAccount.setSystem(de);
							dummyAccount.setType(AccountType.USER);
							UserAccountEntity uac = getUserAccountEntityDao().newUserAccountEntity();
							uac.setUser(u);
							uac.setAccount(dummyAccount);
							dummyAccount.getUsers().add(uac);
						    addAccountGrant(dummyAccount, radSet, originalGrant, granteeGroup);
						}
					} catch (InternalErrorException e) {
						// Ignore
					}
				}
	        }
		}
	}
	
	private void addAccountGrant(AccountEntity account, HashSet<RolAccountDetail> radSet, Object originalGrant,
			GroupEntity granteeGroup) {
		RolAccountDetail rad;
		if (originalGrant instanceof RoleAccountEntity) 
		{
			rad = new RolAccountDetail((RoleAccountEntity) originalGrant, account);
		}
		else if (originalGrant instanceof RoleGroupEntity)
		{
			rad = new RolAccountDetail((RoleGroupEntity) originalGrant, account);
		}
		else if (originalGrant instanceof RoleDependencyEntity)
		{
			rad = new RolAccountDetail((RoleDependencyEntity) originalGrant, account, null);
		}
		else
			rad = null;
		if (rad != null && !radSet.contains(rad)) {
			rad.granteeGrup = granteeGroup;
		    radSet.add(rad);
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
		if (! "true".equals(ConfigurationCache.getProperty("soffid.delegation.disable")))
		{
			for (RoleAccountEntity toDelegate: getRoleAccountEntityDao().findAllRolAccountToStartDelegation(now))
			{
				toDelegate.setDelegationStatus(DelegationStatus.DELEGATION_ACTIVE);
				toDelegate.setAccount(toDelegate.getDelegateAccount());
				getRoleAccountEntityDao().update(toDelegate, "l");
			}

			for (RoleAccountEntity toRevert: getRoleAccountEntityDao().findAllRolAccountToEndDelegation(now))
			{
				toRevert.setDelegationStatus(null);
				toRevert.setAccount(toRevert.getDelegateAccount());
				toRevert.setDelegateSince(null);
				toRevert.setDelegateUntil(null);
				getRoleAccountEntityDao().update(toRevert, "m");
			}
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
            if (uge.getGroup().getId().equals(groupId) && ! Boolean.TRUE.equals(uge.getDisabled())) 
            	return;
        }
		
		GroupEntity ge = getGroupEntityDao().load(groupId);
		for (UserAccountEntity uae : new LinkedList<UserAccountEntity>(user.getAccounts())) {
            AccountEntity acc = uae.getAccount();
            for (RoleAccountEntity rae : new LinkedList<RoleAccountEntity>(acc.getRoles())) {
                if (rae.getHolderGroup() != null && rae.getHolderGroup().getId().equals(groupId) &&
                		rae.getRule() == null) {
                    deleteRoleAccountEntity(rae, user, false);
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
					Security.getCurrentAccount(), name));
        }
	}

	@Override
    protected Collection<RoleAccount> handleFindUserRolesByInformationSystem(String informationSystem) throws Exception {
		return getRoleAccountEntityDao().toRoleAccountList(getRoleAccountEntityDao().findByInformationSystem(informationSystem));
	}

	@Override
	protected Role handleUpdate2(Role rol) throws Exception {
        RoleEntity rolEntity = getRoleEntityDao().roleToEntity(rol);
        if (getAuthorizationService().hasPermission(Security.AUTO_ROLE_UPDATE, rolEntity)) {

        	
            rolEntity = getRoleEntityDao().update(rol, true); // actualizamos cambios del rol
            
            updateRoleAttributes(rol, rolEntity);
            
            return getRoleEntityDao().toRole(rolEntity);
            
        }

		throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.UpdateApplicationError"), //$NON-NLS-1$
				getPrincipal().getName(), rol.getInformationSystemName()));
	}

	@Override
	protected Role handleApproveRoleDefinition(Role rol) throws Exception {
		RoleEntity entity = getRoleEntityDao().load(rol.getId());
	    if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_UPDATE, entity)) {
	    	getRoleEntityDao().commitDefinition(entity);
	    	return getRoleEntityDao().toRole(entity);
		}
	    else
	    	throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.UpdateApplicationError"), //$NON-NLS-1$
				getPrincipal().getName(), rol.getInformationSystemName()));
	
	}

	@Override
	protected Role handleDenyRoleDefinition(Role rol) throws Exception {
		RoleEntity entity = getRoleEntityDao().load(rol.getId());
	    if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_UPDATE, entity)) {
	    	getRoleEntityDao().rollbackDefinition(entity);
	    	return getRoleEntityDao().toRole(entity);
		}
	    else
	    	throw new SeyconException(String.format(Messages.getString("AplicacioServiceImpl.UpdateApplicationError"), //$NON-NLS-1$
				getPrincipal().getName(), rol.getInformationSystemName()));
	}

	private void updateApplicationAttributes (Application app, InformationSystemEntity entity) throws InternalErrorException
	{
		if (entity != null)
		{
			Map<String, Object> attributes = app.getAttributes();
			if (attributes == null)
				attributes = (new HashMap<String, Object>());
			
			LinkedList<ApplicationAttributeEntity> entities = new LinkedList<ApplicationAttributeEntity> (entity.getAttributes());
			HashSet<String> keys = new HashSet<String>();
			for (String key: attributes.keySet() )
			{
				for (MetaDataEntity metadata: getMetaDataEntityDao().findDataTypesByScopeAndName(MetadataScope.APPLICATION, key))
				{
					Object v = attributes.get(key);
					if (v == null)
					{
						// Do nothing
					}
					else if (v instanceof Collection)
					{
						Collection l = (Collection) v;
						for (Object o: (Collection) v)
						{
							if (o != null)
							{
								updateApplicationAttribute(entity, entities, key, metadata, o);
							}
						}
					}
					else
					{
						updateApplicationAttribute(entity, entities, key, metadata, v);
					}
				}
			}
			
			entity.getAttributes().removeAll(entities);
			getInformationSystemEntityDao().update(entity);

			Collection<MetaDataEntity> md = getMetaDataEntityDao().findByScope(MetadataScope.APPLICATION);
			
			for ( MetaDataEntity m: md) if ( m.getBuiltin() == null || ! m.getBuiltin().booleanValue() )
			{
				Object o = attributes.get(m.getName());
				if ( o == null || "".equals(o))
				{
					if (m.getRequired() != null && m.getRequired().booleanValue())
						throw new InternalErrorException(String.format("Missing attribute %s", m.getLabel()));
				} else {
					if (m.getUnique() != null && m.getUnique().booleanValue())
					{
						Collection<String> l = o instanceof Collection? (Collection) o: Collections.singletonList(o);
						for (String v: l)
						{
							List<ApplicationAttributeEntity> p = getApplicationAttributeEntityDao().findByNameAndValue(m.getName(), v);
							if (p.size() > 1)
								throw new InternalErrorException(String.format("Already exists a user with %s %s",
										m.getLabel(), v));
						}
					}
				}
			}
		}
	}

	private void updateApplicationAttribute(InformationSystemEntity entity, LinkedList<ApplicationAttributeEntity> attributes, String key,
			MetaDataEntity metadata, Object value) throws InternalErrorException {
		ApplicationAttributeEntity aae = findApplicationAttributeEntity(attributes, key, value);
		if (aae == null)
		{
			getAttributeValidationService().validate(metadata.getType(), metadata.getDataObjectType(), value);
			aae = getApplicationAttributeEntityDao().newApplicationAttributeEntity();
			aae.setInformationSystem(entity);
			aae.setMetadata(metadata);
			aae.setObjectValue(value);
			getApplicationAttributeEntityDao().create(aae);
			entity.getAttributes().add(aae);
		}
		else
			attributes.remove(aae);
	}

	private ApplicationAttributeEntity findApplicationAttributeEntity(LinkedList<ApplicationAttributeEntity> entities, String key,
			Object o) {
		for (ApplicationAttributeEntity aae: entities)
		{
			if (aae.getMetadata().getName().equals(key))
			{
				if (aae.getObjectValue() != null && aae.getObjectValue().equals(o))
					return aae;
			}
		}
		return null;
	}


	private void updateRoleAttributes (Role app, RoleEntity entity) throws InternalErrorException
	{
		if (entity != null)
		{
			Map<String, Object> attributes = app.getAttributes();
			if (attributes == null)
				attributes = (new HashMap<String, Object>());
			
			LinkedList<RoleAttributeEntity> entities = new LinkedList<RoleAttributeEntity> (entity.getAttributes());
			HashSet<String> keys = new HashSet<String>();
			for (String key: attributes.keySet() )
			{
				for (MetaDataEntity metadata: getMetaDataEntityDao().findDataTypesByScopeAndName(MetadataScope.ROLE, key))
				{
					Object v = attributes.get(key);
					if (v == null)
					{
						// Do nothing
					}
					else if (v instanceof Collection)
					{
						Collection l = (Collection) v;
						for (Object o: (Collection) v)
						{
							if (o != null)
							{
								updateRoleAttribute(entity, entities, key, metadata, o);
							}
						}
					}
					else
					{
						updateRoleAttribute(entity, entities, key, metadata, v);
					}
				}
			}
			
			entity.getAttributes().removeAll(entities);
			getRoleEntityDao().update(entity);
			Collection<MetaDataEntity> md = getMetaDataEntityDao().findByScope(MetadataScope.ROLE);
			
			for ( MetaDataEntity m: md) if ( m.getBuiltin() == null || ! m.getBuiltin().booleanValue() )
			{
				Object o = attributes.get(m.getName());
				if ( o == null || "".equals(o))
				{
					if (m.getRequired() != null && m.getRequired().booleanValue())
						throw new InternalErrorException(String.format("Missing attribute %s", m.getLabel()));
				} else {
					if (m.getUnique() != null && m.getUnique().booleanValue())
					{
						Collection<String> l = o instanceof Collection? (Collection) o: Collections.singletonList(o);
						for (String v: l)
						{
							List<RoleAttributeEntity> p = getRoleAttributeEntityDao().findByNameAndValue(m.getName(), v);
							if (p.size() > 1)
								throw new InternalErrorException(String.format("Already exists a role with %s %s",
										m.getLabel(), v));
						}
					}
				}
			}
		}
	}

	private void updateRoleAttribute(RoleEntity entity, LinkedList<RoleAttributeEntity> attributes, String key,
			MetaDataEntity metadata, Object value) throws InternalErrorException {
		RoleAttributeEntity aae = findRoleAttributeEntity(attributes, key, value);
		if (aae == null)
		{
			getAttributeValidationService().validate(metadata.getType(), metadata.getDataObjectType(), value);
			aae = getRoleAttributeEntityDao().newRoleAttributeEntity();
			aae.setRole(entity);
			aae.setMetadata(metadata);
			aae.setObjectValue(value);
			getRoleAttributeEntityDao().create(aae);
			entity.getAttributes().add(aae);
		}
		else
			attributes.remove(aae);
	}

	private RoleAttributeEntity findRoleAttributeEntity(LinkedList<RoleAttributeEntity> entities, String key,
			Object o) {
		for (RoleAttributeEntity aae: entities)
		{
			if (aae.getMetadata().getName().equals(key))
			{
				if (aae.getObjectValue() != null && aae.getObjectValue().equals(o))
					return aae;
			}
		}
		return null;
	}


	private PagedResult<Role> findRoleByJsonQuery(AsyncList<Role> result, String query, CriteriaSearchConfiguration cs) throws Exception {
		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();;

		AbstractExpression expr = ExpressionParser.parse(query);
		HQLQuery hql = expr.generateHSQLString(Role.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.system.tenant.id = :tenantId";
		else
			qs = "("+qs+") and o.system.tenant.id = :tenantId";
		
		hql.setWhereString(new StringBuffer(qs));
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size()+1];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId());
		@SuppressWarnings("unchecked")
		List <RoleEntity> roles = ( List <RoleEntity>) new QueryBuilder()
				.query(hql.toString(), 
						paramArray,
						cs.getFirstResult(), 
						cs.getMaximumResultSize());
		int totalResults = 0;
		for (RoleEntity ue : roles) {
			if (result.isCancelled())
				return null;
			Role u = getRoleEntityDao().toRole(ue);
			if (!hql.isNonHQLAttributeUsed() || expr.evaluate(u)) {
				if (getAuthorizationService().hasPermission(
						Security.AUTO_ROLE_QUERY, ue)) {
					result.add(u);
				}
			}
		}
		PagedResult<Role> pagedResult = new PagedResult<Role>();
		pagedResult.setResources(result);
		pagedResult.setStartIndex( cs.getFirstResult() != null ? cs.getFirstResult(): 0);
		pagedResult.setItemsPerPage( cs.getMaximumResultSize());
		if ( cs.getMaximumResultSize()  != null) {
			@SuppressWarnings("unchecked")
			List <Long> ll = ( List <Long>) new QueryBuilder()
					.query(hql.toCountString(), 
							paramArray,
							null, 
							null);
			for ( Long l: ll ) {
				pagedResult.setTotalResults( new Integer(l.intValue()) );
			}
		} else {
			pagedResult.setTotalResults(totalResults);
		}
		return pagedResult;
	}

	@Override
	protected Collection<Role> handleFindRoleByText(String text) throws Exception {
		LinkedList<Role> result = new LinkedList<Role>();
		TimeOutUtils tou = new TimeOutUtils();
		for (RoleEntity ue : getRoleEntityDao().findByText(text)) {
			if (getAuthorizationService().hasPermission(
					Security.AUTO_ROLE_QUERY, ue)) {
				Role u = getRoleEntityDao().toRole(ue);
				result.add(u);
			}
			if (tou.timedOut())
				return result;
		}

		return result;
	}

	@Override
	protected AsyncList<Role> handleFindRoleByTextAsync(final String text) throws Exception {
		final AsyncList<Role> result = new AsyncList<Role>();
		getAsyncRunnerService().run(
				new Runnable() {
					public void run () {
						try {
							for (RoleEntity e : getRoleEntityDao().findByText(text)) {
								if (result.isCancelled())
									return;
								if (getAuthorizationService().hasPermission(
										Security.AUTO_ROLE_QUERY, e)) {
									Role v = getRoleEntityDao().toRole(e);
									result.add(v);
								}
							}
						} catch (InternalErrorException e) {
							throw new RuntimeException(e);
						}
					}
				}, result);
		return result;
	}

	@Override
	protected Collection<Application> handleFindApplicationByJsonQuery(String query) throws Exception {
		AsyncList<Application> result = new AsyncList<Application>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		findApplicationByJsonQuery(result, query, new CriteriaSearchConfiguration());
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return result.get();
	}

	@Override
	protected PagedResult<Application> handleFindApplicationByJsonQuery(String query, Integer startIndex, Integer count) throws Exception {
		AsyncList<Application> result = new AsyncList<Application>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		CriteriaSearchConfiguration sc = new CriteriaSearchConfiguration();
		sc.setFirstResult(startIndex);
		sc.setMaximumResultSize(count);
		PagedResult<Application> pr = findApplicationByJsonQuery(result, query, sc);
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return pr;
	}


	@Override
	protected AsyncList<Application> handleFindApplicationByJsonQueryAsync(final String query) throws Exception {
		final AsyncList<Application> result = new AsyncList<Application>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					findApplicationByJsonQuery(result, query, new CriteriaSearchConfiguration());
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}

	protected PagedResult<Application> findApplicationByJsonQuery ( AsyncList<Application> result, String query, CriteriaSearchConfiguration cs) 
			throws EvalException, InternalErrorException, UnsupportedEncodingException, ClassNotFoundException, JSONException, ParseException
	{

		// Register virtual attributes for additional data
		AdditionalDataJSONConfiguration.registerVirtualAttributes();;

		// Prepare query HQL
		AbstractExpression expr = ExpressionParser.parse(query);
		HQLQuery hql = expr.generateHSQLString(Application.class);
		String qs = hql.getWhereString().toString();
		if (qs.isEmpty())
			qs = "o.tenant.id = :tenantId";
		else
			qs = "(" + qs + ") and o.tenant.id = :tenantId";
		hql.setWhereString(new StringBuffer(qs));

		// Include HQL parameters
		Map<String, Object> params = hql.getParameters();
		Parameter paramArray[] = new Parameter[params.size() + 1];
		int i = 0;
		for (String s : params.keySet())
			paramArray[i++] = new Parameter(s, params.get(s));
		paramArray[i++] = new Parameter("tenantId", Security.getCurrentTenantId());

		// Execute HQL and generate result
		@SuppressWarnings("unchecked")
		List <InformationSystemEntity> apps = ( List <InformationSystemEntity>) new QueryBuilder()
				.query(hql.toString(), 
						paramArray,
						cs.getFirstResult(), 
						cs.getMaximumResultSize());
		int totalResults = 0;
		for (InformationSystemEntity applicationEntity : getInformationSystemEntityDao().query(hql.toString(), paramArray)) {
			if (result.isCancelled())
				return null;
			Application ApplicationVO = getInformationSystemEntityDao().toApplication(applicationEntity);
			if (!hql.isNonHQLAttributeUsed() || expr.evaluate(ApplicationVO)) {
				if (getAuthorizationService().hasPermission(Security.AUTO_APPLICATION_QUERY, applicationEntity)) {
					totalResults ++;
					result.add(ApplicationVO);
				}
			}
		}
		PagedResult<Application> pagedResult = new PagedResult<Application>();
		pagedResult.setResources(result);
		pagedResult.setStartIndex( cs.getFirstResult() != null ? cs.getFirstResult(): 0);
		pagedResult.setItemsPerPage( cs.getMaximumResultSize());
		if ( cs.getMaximumResultSize()  != null) {
			@SuppressWarnings("unchecked")
			List <Long> ll = ( List <Long>) new QueryBuilder()
					.query(hql.toCountString(), 
							paramArray,
							null, 
							null);
			for ( Long l: ll ) {
				pagedResult.setTotalResults( new Integer(l.intValue()) );
			}
		} else {
			pagedResult.setTotalResults(totalResults);
		}
		return pagedResult;
	}

	String generateQuickSearchQuery (String text) {
		if (text == null )
			return  "";
		List<MetaDataEntity> atts = getMetaDataEntityDao().findByScope(MetadataScope.APPLICATION);
		String[] split = text.trim().split(" +");
		
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < split.length; i++)
		{
			String t = split[i].replaceAll("\\\\","\\\\\\\\").replaceAll("\"", "\\\\\"");
			if (sb.length() > 0)
				sb.append(" and ");
			sb.append("(");
			sb.append("name co \""+t+"\"");
			sb.append(" or description co \""+t+"\"");
			for (MetaDataEntity att: atts)
			{
				if (att.getSearchCriteria() != null && att.getSearchCriteria().booleanValue())
				{
					sb.append(" or attributes."+att.getName()+" co \""+t+"\"");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}
	
	@Override
	protected AsyncList<Application> handleFindApplicationByTextAndFilterAsync(String text, String filter) throws Exception {
		String q = generateQuickSearchQuery(text);
		if (!q.isEmpty() && filter != null && ! filter.trim().isEmpty())
			q = "("+q+") and ("+filter+")";
		else if ( filter != null && ! filter.trim().isEmpty())
			q = filter;
		return handleFindApplicationByJsonQueryAsync(q);
			
	}

	@Override
	protected Collection<Application> handleFindApplicationByTextAndFilter(String text, String filter) throws Exception {
		String q = generateQuickSearchQuery(text);
		if (!q.isEmpty() && filter != null && ! filter.trim().isEmpty())
			q = "("+q+") and ("+filter+")";
		else if ( filter != null && ! filter.trim().isEmpty())
			q = filter;
		return handleFindApplicationByJsonQuery(q);
	}

	@Override
	protected Collection<Application> handleFindApplicationByText(String text) throws Exception {
		return handleFindApplicationByTextAndFilter(text, null);
	}

	@Override
	protected AsyncList<Application> handleFindApplicationByTextAsync(final String text) throws Exception {
		return handleFindApplicationByTextAndFilterAsync(text, null);
	}

	@Override
	protected Collection<DomainValue> handleFindDomainValueByText(Domain domain, String text) throws Exception {
		LinkedList<DomainValue> result = new LinkedList<DomainValue>();
		TimeOutUtils tou = new TimeOutUtils();
		for (DomainValueEntity ue : getDomainValueEntityDao().findByText(domain, text)) {
			DomainValue u = getDomainValueEntityDao().toDomainValue(ue);
			result.add(u);
			if (tou.timedOut())
				return result;
		}

		return result;
	}

	
	@Override
	protected AsyncList<DomainValue> handleFindDomainValueByTextAsync(final Domain domain, final String text) throws Exception {
		final AsyncList<DomainValue> result = new AsyncList<DomainValue>();
		getAsyncRunnerService().run(
				new Runnable() {
					public void run () {
						for (DomainValueEntity e : getDomainValueEntityDao().findByText(domain, text)) {
							if (result.isCancelled())
								return;
							DomainValue v = getDomainValueEntityDao().toDomainValue(e);
							result.add(v);
						}
					}
				}, result);
		return result;
	}

	private void findEffectiveRoleGrantsRecursively (RoleEntity r,
			String domainValue,
			RoleEntity roleToModify,
			Set<RolAccountDetail> radSet) throws Exception
	{
		
		HashSet<RolAccountDetail> s = new HashSet<RolAccountDetail>();
		populateParentGrantsForRol(s, r, null, roleToModify);
		
		for (RolAccountDetail rg: s) 
		{
			if (domainValue == null ||
					rg.qualifier != null && rg.qualifier.getValue().equals(domainValue) ||
					rg.qualifierAplicacio != null && rg.qualifierAplicacio.getName().equals( domainValue ) ||
					rg.qualifierGroup != null && rg.qualifierGroup.getName().equals( domainValue) )
			{
				radSet.add(rg);
			}
		}
		
		
		for ( RoleDependencyEntity rg: r.getContainedRoles())
		{
			if (rg.getMandatory() != null && rg.getMandatory().booleanValue())
			{
				if (rg.getDomainApplicationValue() != null)
					findEffectiveRoleGrantsRecursively(rg.getContained(),
							rg.getDomainApplicationValue().getValue(), 
							roleToModify,
							radSet);
				else if (rg.getDomainApplication() != null)
					findEffectiveRoleGrantsRecursively(rg.getContained(), rg.getDomainApplicationValue().getValue(), roleToModify, radSet);
				else if (rg.getDomainGroup() != null)
					findEffectiveRoleGrantsRecursively(rg.getContained(), rg.getDomainGroup().getName(), roleToModify, radSet);
				else if (rg.getContained().getDomainType().equals(r.getDomainType()) )
					findEffectiveRoleGrantsRecursively(rg.getContained(), domainValue, roleToModify, radSet);
				else
					findEffectiveRoleGrantsRecursively(rg.getContained(), null, roleToModify, radSet);
			}
		}
	}
	
	@Override
	protected String handleGenerateChangesReport(Role rol) throws Exception {			
		// Get current grantees
		HashSet<RolAccountDetail> set1 = new HashSet<RolAccountDetail>();
		if (rol.getId() != null)
		{
			RoleEntity entity = getRoleEntityDao().load(rol.getId());
			findEffectiveRoleGrantsRecursively(entity, null, null, set1);
		}
		// Now look for existing grants on new dependencies
		for ( RoleGrant rg: rol.getOwnedRoles())
		{
			if (rg.getMandatory() != null && rg.getMandatory().booleanValue())
			{
				RoleEntity role = getRoleEntityDao().findByNameAndSystem(rg.getRoleName(), rg.getSystem());
				if (role != null)
				{
					findEffectiveRoleGrantsRecursively(role, rg.getDomainValue(), null, set1);
				}
			}
		}
		
		
		LinkedList<RoleGrant> list1 = new LinkedList<RoleGrant>();
		toRoleGrantList(set1, list1);
		
		// Get new grantees
		HashSet<RolAccountDetail> set2 = new HashSet<RolAccountDetail>();
		computeNewGrantees(rol, set2 );
		LinkedList<RoleGrant> list2 = new LinkedList<RoleGrant>();
		toRoleGrantList(set2, list2);
		
		RolGrantDiffReport report = new RolGrantDiffReport ();
		report.setAccountEntityDao(getAccountEntityDao());
		report.setUserEntityDao(getUserEntityDao());
		File f = report.generateReport (list1, list2);
		
		return f.getAbsolutePath();
	}

	private void computeNewGrantees(Role rol, HashSet<RolAccountDetail> radSet) throws Exception {
		
		RoleEntity dummyEntity ;
		if (rol.getId() == null)
		{
			dummyEntity = getRoleEntityDao().newRoleEntity();
		}
		else
		{
			// Calculate grants for children, removing current link
			dummyEntity = getRoleEntityDao().load(rol.getId());
			for (RoleDependencyEntity containedRole: dummyEntity.getContainedRoles())
			{
				containedRole.getContained().getContainerRoles().remove(containedRole);
				findEffectiveRoleGrantsRecursively(dummyEntity, null, dummyEntity, radSet);				
			}
		}
		getRoleEntityDao().roleToEntity(rol, dummyEntity, true);

		dummyEntity.getContainedRoles().clear();
		dummyEntity.getContainerRoles().clear();
		dummyEntity.getContainerGroups().clear();
		
		for (RoleGrant grantedRole: rol.getOwnedRoles())
		{
			RoleDependencyEntity rde = toGrantedRoleEntity(grantedRole, dummyEntity);
			dummyEntity.getContainedRoles().add(rde);
		}
		
		for (RoleGrant grantedRole: rol.getOwnerRoles())
		{
			RoleDependencyEntity rde = toGranteeRoleEntity(grantedRole, dummyEntity);
			dummyEntity.getContainerRoles().add(rde);
		}
		
		for (RoleGrant granteeGroup: rol.getGranteeGroups())
		{
			RoleGroupEntity rge = toRoleGroupEntity(granteeGroup, dummyEntity);
			dummyEntity.getContainerGroups().add(rge );
		}
		
		findEffectiveRoleGrantsRecursively(dummyEntity, null, dummyEntity, radSet);
	}

	private RoleGroupEntity toRoleGroupEntity(RoleGrant granteeGroup, RoleEntity dummyEntity) {
		RoleGroupEntity target = getRoleGroupEntityDao().newRoleGroupEntity();
		
		target.setGrantedRole(dummyEntity);
		target.setGroup( getGroupEntityDao().findByName(granteeGroup.getOwnerGroup()));
		generateGrantedDomainValue(granteeGroup, target);

		return target;
	}

	private RoleDependencyEntity toGrantedRoleEntity(RoleGrant source, RoleEntity dummyEntity) {
		RoleDependencyEntity target = getRoleDependencyEntityDao().newRoleDependencyEntity();
		getRoleDependencyEntityDao().roleGrantToEntity(source, target, true);
		target.setContained( getRoleEntityDao().load(source.getRoleId()));
		target.setContainer(dummyEntity);
		target.setMandatory(source.getMandatory());
		target.setStatus(RoleDependencyStatus.STATUS_ACTIVE);
		
		assignRoleDependencyDomainValue(source, target);

		return target;
	}

	private RoleDependencyEntity toGranteeRoleEntity(RoleGrant source, RoleEntity dummyEntity) {
		RoleDependencyEntity target = getRoleDependencyEntityDao().newRoleDependencyEntity();
		getRoleDependencyEntityDao().roleGrantToEntity(source, target, true);
		target.setContainer( getRoleEntityDao().load(source.getOwnerRole()));
		target.setContained(dummyEntity);
		target.setMandatory(source.getMandatory());
		target.setStatus(RoleDependencyStatus.STATUS_ACTIVE);
		
		assignRoleDependencyDomainValue(source, target);

		return target;
	}

	private void assignRoleDependencyDomainValue(RoleGrant source, RoleDependencyEntity target) {
		generateGrantedDomainValue(source, target);

		generateGranteeDomainValue(source, target);
	}

	private void generateGranteeDomainValue(RoleGrant source, RoleDependencyEntity target) {
		if (source.getOwnerRolDomainValue() != null && target.getContainer() != null)
		{
			if (target.getContainer().getDomainType().equals(DomainType.APLICACIONS))
			{
				target.setGranteeApplicationDomain(
						getInformationSystemEntityDao().findByCode(source.getOwnerRolDomainValue()));
			}
			if (target.getContainer().getDomainType().equals(DomainType.GRUPS) ||
					target.getContained().getDomainType().equals(DomainType.GRUPS_USUARI))
			{
				target.setGranteeGroupDomain(
						getGroupEntityDao().findByName(source.getOwnerRolDomainValue()));
			}
			if (target.getContainer().getDomainType().equals(DomainType.DOMINI_APLICACIO))
			{
				target.setGranteeDomainValue(
						getDomainValueEntityDao().findByApplicationDomainValue(
								target.getContainer().getInformationSystem().getName(), 
								target.getContainer().getApplicationDomain().getName(), 
								source.getOwnerRolDomainValue()));
			}
		}
	}

	private void generateGrantedDomainValue(RoleGrant source, RoleDependencyEntity target) {
		if (source.getDomainValue() != null && target.getContained() != null)
		{
			if (target.getContained().getDomainType().equals(DomainType.APLICACIONS))
			{
				target.setDomainApplication(
						getInformationSystemEntityDao().findByCode(source.getDomainValue()));
			}
			if (target.getContained().getDomainType().equals(DomainType.GRUPS) ||
					target.getContained().getDomainType().equals(DomainType.GRUPS_USUARI))
			{
				target.setDomainGroup(
						getGroupEntityDao().findByName(source.getDomainValue()));
			}
			if (target.getContained().getDomainType().equals(DomainType.DOMINI_APLICACIO))
			{
				target.setDomainApplicationValue(
						getDomainValueEntityDao().findByApplicationDomainValue(
								target.getContained().getInformationSystem().getName(), 
								target.getContained().getApplicationDomain().getName(), 
								source.getDomainValue()));
			}
		}
	}

	private void generateGrantedDomainValue(RoleGrant source, RoleGroupEntity target) {
		if (source.getDomainValue() != null && target.getGrantedRole() != null)
		{
			if (target.getGrantedRole().getDomainType().equals(DomainType.APLICACIONS))
			{
				target.setGrantedApplicationDomain(
						getInformationSystemEntityDao().findByCode(source.getDomainValue()));
			}
			if (target.getGrantedRole().getDomainType().equals(DomainType.GRUPS) ||
					target.getGrantedRole().getDomainType().equals(DomainType.GRUPS_USUARI))
			{
				target.setGrantedGroupDomain(
						getGroupEntityDao().findByName(source.getDomainValue()));
			}
			if (target.getGrantedRole().getDomainType().equals(DomainType.DOMINI_APLICACIO))
			{
				target.setGrantedDomainValue(
						getDomainValueEntityDao().findByApplicationDomainValue(
								target.getGrantedRole().getInformationSystem().getName(), 
								target.getGrantedRole().getApplicationDomain().getName(), 
								source.getDomainValue()));
			}
		}
	}

	@Override
	protected Collection<String> handleFindRoleNames(String systemName) throws Exception {
		return getRoleEntityDao().findRoleNames(systemName);
	}

	@Override
	protected Collection<Role> handleFindApplicationManagementRoles() throws Exception {
		Collection<RoleEntity> roles = getRoleEntityDao().findApplicationManagementRoles();
		return getRoleEntityDao().toRoleList(roles);
	}

	@Override
	protected Collection<RoleAccount> handleFindApplicationManagers(String informationSystem, String roleName)
			throws Exception {
		Collection<RoleAccount> ra = new LinkedList<RoleAccount>();
		for (RoleEntity role: getRoleEntityDao().findApplicationManagementRoles())
		{
			if (role.getName().equals(roleName))
			{
				Collection<RoleAccountEntity> grants = getRoleAccountEntityDao().findByRoleAndDomainValue(
						role.getName(),
						role.getSystem().getName(),
						DomainType.APLICACIONS,
						null,
						informationSystem,
						null
						);
				ra.addAll(getRoleAccountEntityDao().toRoleAccountList(grants));
			}
		}
		return ra;
	}

	@Override
	protected Collection<Role> handleFindGroupManagementRoles() throws Exception {
		Collection<RoleEntity> roles = getRoleEntityDao().findGroupManagementRoles();
		return getRoleEntityDao().toRoleList(roles);
	}

	@Override
	protected Collection<RoleAccount> handleFindGroupManagers(String group, String roleName)
			throws Exception {
		Collection<RoleAccount> ra = new LinkedList<RoleAccount>();
		for (RoleEntity role: getRoleEntityDao().findGroupManagementRoles())
		{
			if (role.getName().equals(roleName))
			{
				Collection<RoleAccountEntity> grants = getRoleAccountEntityDao().findByRoleAndDomainValue(
						role.getName(),
						role.getSystem().getName(),
						DomainType.GRUPS,
						group,
						null,
						null
						);
				ra.addAll(getRoleAccountEntityDao().toRoleAccountList(grants));
			}
		}
		return ra;
	}

	@Override
	public void handleApproveDelete(RoleAccount rolsUsuaris) throws InternalErrorException, InternalErrorException {
		RoleAccountEntity ra = getRoleAccountEntityDao().load(rolsUsuaris.getId());
		if (ra.getRemovalPending() != null && ra.getRemovalPending().booleanValue())
		{
			UserEntity u = null;
			for ( UserAccountEntity users: ra.getAccount().getUsers())
				u = users.getUser();
			
			deleteRoleAccountEntity(ra, u, true);
		}
	}

	@Override
	public void handleDenyDelete(RoleAccount rolsUsuaris) throws InternalErrorException, InternalErrorException {
		RoleAccountEntity ra = getRoleAccountEntityDao().load(rolsUsuaris.getId());
		if (ra != null && ra.getRemovalPending() != null && ra.getRemovalPending().booleanValue())
		{
			ra.setRemovalPending(false);
			ra.setRule(null);
			getRoleAccountEntityDao().update(ra);
		}
	}

	@Override
	protected void handleDeleteByRuleEvaluation(RoleAccount rolsUsuaris) throws Exception {
        String codiAplicacio = rolsUsuaris.getInformationSystemName();
        // if (esAdministracioPersonal(rolsUsuaris) || esAdministradorUsuaris())
        // {
        RoleAccountEntity rolsUsuarisEntity = getRoleAccountEntityDao().load(rolsUsuaris.getId());
    	if (rolsUsuarisEntity == null)
    		return;
    	
        if (getAuthorizationService().hasPermission(Security.AUTO_USER_ROLE_DELETE, rolsUsuarisEntity)) {

        	if (rolsUsuarisEntity.getRule() == null)
        	{
        		if (Security.isSyncServer()) // SYNC SERVER
            		return;
        		throw new InternalErrorException(Messages.getString("AplicacioServiceImpl.CannotRevokeManually")); //$NON-NLS-1$
        	}

        	UserEntity user = null;
            for (UserAccountEntity ua : rolsUsuarisEntity.getAccount().getUsers()) {
                user = ua.getUser();
            }
            
            deleteRoleAccountEntity(rolsUsuarisEntity, user, false);
            return;
        } 
        throw new SeyconAccessLocalException("aplicacioService", "delete (RolAccount)", "user:role:delete", String.format( //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				Messages.getString("ApplicationServiceImpl.UnableDeleteRol"), codiAplicacio)); //$NON-NLS-1$
	}

	@Override
	protected Collection<RoleAccount> handleFindUserRolesByUserNameNoSoD(String codiUsuari) throws Exception {
    	return internalFindUserRolesByUserName (codiUsuari, false, false);
	}


	class RoleAttributePersister extends AttributePersister<RoleEntity,RoleAttributeEntity> {
		@Override
		protected List<RoleAttributeEntity> findAttributeEntityByNameAndValue(MetaDataEntity m, String v) {
			return getRoleAttributeEntityDao().findByNameAndValue(m.getName(), v);
		}

		@Override
		protected void updateEntity(RoleEntity entity) {
			getRoleEntityDao().update(entity);
		}

		@Override
		protected MetadataScope getMetadataScope() {
			return MetadataScope.GROUP;
		}

		@Override
		protected Collection<RoleAttributeEntity> getEntityAttributes(RoleEntity entity) {
			return entity.getAttributes();
		}

		@Override
		protected RoleAttributeEntity createNewAttribute(RoleEntity entity, MetaDataEntity metadata, Object value) {
			RoleAttributeEntity aae = getRoleAttributeEntityDao().newRoleAttributeEntity();
			aae.setRole(entity);
			aae.setMetadata(metadata);
			aae.setObjectValue(value);
			getRoleAttributeEntityDao().create(aae);
			return aae;
		}

		@Override
		protected RoleAttributeEntity findAttributeEntity(LinkedList<RoleAttributeEntity> entities, String key,
				Object o) {
			for (RoleAttributeEntity aae: entities)
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
			return ApplicationServiceImpl.this.getMetaDataEntityDao();
		}

		@Override
		protected AttributeValidationService getAttributeValidationService() {
			return ApplicationServiceImpl.this.getAttributeValidationService();
		}
		
	}

	class ApplicationAttributePersister extends AttributePersister<InformationSystemEntity,ApplicationAttributeEntity> {
		@Override
		protected List<ApplicationAttributeEntity> findAttributeEntityByNameAndValue(MetaDataEntity m, String v) {
			return getApplicationAttributeEntityDao().findByNameAndValue(m.getName(), v);
		}

		@Override
		protected void updateEntity(InformationSystemEntity entity) {
			getInformationSystemEntityDao().update(entity);
		}

		@Override
		protected MetadataScope getMetadataScope() {
			return MetadataScope.GROUP;
		}

		@Override
		protected Collection<ApplicationAttributeEntity> getEntityAttributes(InformationSystemEntity entity) {
			return entity.getAttributes();
		}

		@Override
		protected ApplicationAttributeEntity createNewAttribute(InformationSystemEntity entity, MetaDataEntity metadata, Object value) {
			ApplicationAttributeEntity aae = getApplicationAttributeEntityDao().newApplicationAttributeEntity();
			aae.setInformationSystem(entity);
			aae.setMetadata(metadata);
			aae.setObjectValue(value);
			getApplicationAttributeEntityDao().create(aae);
			return aae;
		}

		@Override
		protected ApplicationAttributeEntity findAttributeEntity(LinkedList<ApplicationAttributeEntity> entities, String key,
				Object o) {
			for (ApplicationAttributeEntity aae: entities)
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
			return ApplicationServiceImpl.this.getMetaDataEntityDao();
		}

		@Override
		protected AttributeValidationService getAttributeValidationService() {
			return ApplicationServiceImpl.this.getAttributeValidationService();
		}
		
	}

	String generateRoleQuickSearchQuery (String text) {
		if (text == null )
			return  "";
		List<MetaDataEntity> atts = getMetaDataEntityDao().findByScope(MetadataScope.ROLE);
		String[] split = text.trim().split(" +");
		
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < split.length; i++)
		{
			String t = split[i].replaceAll("\\\\","\\\\\\\\").replaceAll("\"", "\\\\\"");
			if (sb.length() > 0)
				sb.append(" and ");
			sb.append("(");
			sb.append("name co \""+t+"\"");
			sb.append(" or description co \""+t+"\"");
			for (MetaDataEntity att: atts)
			{
				if (att.getSearchCriteria() != null && att.getSearchCriteria().booleanValue())
				{
					sb.append(" or attributes."+att.getName()+" co \""+t+"\"");
				}
			}
			sb.append(")");
		}
		return sb.toString();
	}
	
	@Override
	public AsyncList<Role> handleFindRoleByTextAndFilterAsync(String text, String filter) throws Exception {
		String q = generateRoleQuickSearchQuery(text);
		if (!q.isEmpty() && filter != null && ! filter.trim().isEmpty())
			q = "("+q+") and ("+filter+")";
		else if ( filter != null && ! filter.trim().isEmpty())
			q = filter;
		return handleFindRoleByJsonQueryAsync(q);
	}
	@Override
	public Collection<Role> handleFindRoleByTextAndFilter(String text, String filter) throws Exception {
		String q = generateRoleQuickSearchQuery(text);
		if (!q.isEmpty() && filter != null && ! filter.trim().isEmpty())
			q = "("+q+") and ("+filter+")";
		else if ( filter != null && ! filter.trim().isEmpty())
			q = filter;
		return handleFindRoleByJsonQuery(q);
	}

	@Override
	protected AsyncList<Role> handleFindRoleByJsonQueryAsync(final String query) throws Exception {
		final AsyncList<Role> result = new AsyncList<Role>();
		getAsyncRunnerService().run(new Runnable() {
			public void run() {
				try {
					findRoleByJsonQuery(result, query, new CriteriaSearchConfiguration());
				} catch (Exception e) {
					result.cancel(e);
				}
			}
		}, result);
		return result;
	}

	@Override
	protected Collection<Role> handleFindRoleByJsonQuery(String query) throws Exception {
		AsyncList<Role> result = new AsyncList<Role>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		findRoleByJsonQuery(result, query, new CriteriaSearchConfiguration());
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return result.get();
	}

	@Override
	protected PagedResult<Role> handleFindRoleByJsonQuery(String query, Integer startIndex, Integer count) throws Exception {
		AsyncList<Role> result = new AsyncList<Role>();
		result.setTimeout(TimeOutUtils.getGlobalTimeOut());
		CriteriaSearchConfiguration sc = new CriteriaSearchConfiguration();
		sc.setFirstResult(startIndex);
		sc.setMaximumResultSize(count);
		PagedResult<Role> pr = findRoleByJsonQuery(result, query, sc);
		if (result.isCancelled())
			TimeOutUtils.generateException();
		result.done();
		return pr;
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
		granted = rg.getGrantedRole();
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
