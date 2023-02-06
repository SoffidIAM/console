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
package com.soffid.iam.model;

import com.soffid.iam.api.Audit;
import com.soffid.iam.api.ContainerRole;
import com.soffid.iam.api.Domain;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.Identity;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleDependencyStatus;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.Task;
import com.soffid.iam.api.UserData;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.service.BpmEngine;
import com.soffid.iam.bpm.service.scim.ScimHelper;
import com.soffid.iam.model.ApplicationDomainEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.DomainValueEntity;
import com.soffid.iam.model.GroupEntity;
import com.soffid.iam.model.InformationSystemEntity;
import com.soffid.iam.model.MailListRoleMemberEntity;
import com.soffid.iam.model.RoleAccountEntity;
import com.soffid.iam.model.RoleDependencyEntity;
import com.soffid.iam.model.RoleDependencyEntityDaoImpl;
import com.soffid.iam.model.RoleEntity;
import com.soffid.iam.model.RoleGroupEntity;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.TaskEntity;
import com.soffid.iam.model.UserEntity;
import com.soffid.iam.model.UserGroupEntity;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.sync.engine.TaskHandler;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;
import com.soffid.iam.utils.TipusContenidorRol;

import es.caib.bpm.exception.BPMException;
import es.caib.bpm.vo.PredefinedProcessType;
import es.caib.seycon.ng.PrincipalStore;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.exception.UnknownRoleException;

import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Hibernate;

public class RoleEntityDaoImpl extends com.soffid.iam.model.RoleEntityDaoBase {

	private void auditarRol(String accio, String nomRol, String codiAplicacio,
			String bbdd) {
		String codiUsuari = Security.getCurrentAccount();
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setApplication(codiAplicacio);
		auditoria.setRole(nomRol);
		auditoria.setAuthor(codiUsuari);
		auditoria.setDatabase(bbdd);
		auditoria.setObject("SC_ROLES"); //$NON-NLS-1$
		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(
				auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}

	public void create(com.soffid.iam.model.RoleEntity Role)
			throws RuntimeException {
		try {
			// Importante: PRIMERO CREAMOS LA ENTIDAD Role (obtenemos id)
			super.create(Role); // Creamos la entidad
			getSession(false).flush();

		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(
					String.format(
							Messages.getString("RoleEntityDaoImpl.1"), Role.getName(), message)); //$NON-NLS-1$
		}
	}

	@Override
	protected void handleUpdateMailLists(RoleEntity role)
			throws InternalErrorException {
		updateMailLists(role, 10);
	}

	private void updateMailLists(RoleEntity role, int depth)
			throws InternalErrorException {
		for (MailListRoleMemberEntity lce : role.getMailLists()) {
			getEmailListEntityDao().generateUpdateTasks(lce.getMailList());
		}
		if (depth > 0) {
			for (RoleDependencyEntity child : role.getContainedRoles()) {
				updateMailLists(child.getContained(), depth - 1);
			}
		}
	}

	private void generatePropagationTasks(
			HashSet<UserEntity> usuarisPropagar,
			HashSet<AccountEntity> accountsPropagar,
			HashSet<RoleEntity> rolsPropagar, HashSet<GroupEntity> grupsPropagar,
			HashSet<UserEntity> usuarisPropagarAfter,
			HashSet<AccountEntity> accountsPropagarAfter,
			HashSet<RoleEntity> rolsPropagarAfter,
			HashSet<GroupEntity> grupsPropagarAfter)
			throws InternalErrorException {
		// En update, si no se modifica la tabla sc_roles, no se lanza un
		// updateRole

		// Ara fem la diferència entre els usuaris d'abans i els nous
		// Clonem els de després:
		// USUARIS:
		HashSet<UserEntity> usuarisBorrar = new HashSet<UserEntity>(usuarisPropagar); // abans
		usuarisBorrar.removeAll(usuarisPropagarAfter);// deixen només els
		                                              // usus que ja no
		                                              // tenen el Role

		HashSet<UserEntity> usuarisAfegir = new HashSet<UserEntity>(usuarisPropagarAfter); // després
		usuarisAfegir.removeAll(usuarisPropagar); // deixem els nous
		                                          // (eliminem els q es
		                                          // mantenen)

		// Fem la unió dels que hem d'afegir i els que hem de eliminar (hem
		// d'actualitzar tots dos)
		usuarisAfegir.addAll(usuarisBorrar);

		// ACCOUNTS:
		HashSet<AccountEntity> accountsBorrar = new HashSet<AccountEntity>(accountsPropagar); // abans
		accountsBorrar.removeAll(accountsPropagarAfter);// deixen només els
		                                              // usus que ja no
		                                              // tenen el Role

		HashSet<AccountEntity> accountsAfegir = new HashSet<AccountEntity>(accountsPropagarAfter); // després
		accountsAfegir.removeAll(accountsPropagar); // deixem els nous
		                                          // (eliminem els q es
		                                          // mantenen)

		// Fem la unió dels que hem d'afegir i els que hem de eliminar (hem
		// d'actualitzar tots dos)
		usuarisAfegir.addAll(usuarisBorrar);
	 
 
		// ROLS:
		HashSet<RoleEntity> rolsBorrar = new HashSet<RoleEntity>(rolsPropagar); // abans
		rolsBorrar.removeAll(rolsPropagarAfter); // deixem només els rols q
		                                         // ja no estan
	 
		HashSet<RoleEntity> rolsAfegir = new HashSet<RoleEntity>(rolsPropagarAfter); // després
		rolsAfegir.removeAll(rolsPropagar); // deixem els nous (eliminem els
		                                    // q es mantenen)
	 
		// Fem la unió dels que hem d'afegir i els que hem de eliminar (hem
		// d'actualitzar tots dos)
		rolsAfegir.addAll(rolsBorrar);
	 
		// GRUPS:
		HashSet<GroupEntity> grupsBorrar = new HashSet<GroupEntity>(grupsPropagar); // abans
		grupsBorrar.removeAll(grupsPropagarAfter);
	 
		HashSet<GroupEntity> grupsAfegir = new HashSet<GroupEntity>(grupsPropagarAfter); // després
		grupsAfegir.removeAll(grupsPropagar);
	 
		grupsAfegir.addAll(grupsBorrar);
	 
		// I fem la propagació: només dels que siguen "nous"
		propagarUsuarisRolsIGrups(usuarisAfegir, accountsAfegir, rolsAfegir, grupsAfegir);
	}
	 
	
	/**
	 * Returns true if an approval process has been initiated
	 * 
	 * @param role
	 * @return
	 * @throws InternalErrorException 
	 * @throws BPMException 
	 */
	private boolean startApprovalProcess (RoleEntity role) throws InternalErrorException, BPMException
	{
		java.util.Date now = new java.util.Date();
		String approvalProcess = role.getInformationSystem().getRoleDefinitionProcess();
		if (Security.isSyncServer()) {
			return false;
		} else if (Security.isUserInRole("internal:recertification-process")) {
			return true; 
		}
		else if (approvalProcess == null || approvalProcess.trim().isEmpty())
		{
			if (role.getApprovalStart() == null ||
					now.getTime() - role.getApprovalStart().getTime() > 2000) // Updated more than 2 secons ago
			{
				role.setApprovalEnd(now);
				role.setApprovalStart(now);
				update (role);
			}
			return false;
		}
		else
		{
			BpmEngine engine = getBpmEngine();
			Security.nestedLogin(Security.getCurrentAccount(), new String [] {
				"BPM_INTERNAL" //$NON-NLS-1$
			});
			try {
				
				if (role.getApprovalProcess() != null)
				{
					ProcessInstance oldProcess = engine.getProcess(role.getApprovalProcess());
					if (oldProcess.getEnd() == null)
					{
						if (role.getApprovalStart() != null &&
								now.getTime() - role.getApprovalStart().getTime() < 60000) // WF created less than one minute ago
						{
							return true;
						}
						else	// Cancel current WF and start a new one
							engine.cancel(oldProcess);
					}
				}
				// Starts new workflow
				List<com.soffid.iam.bpm.api.ProcessDefinition> defs = getBpmEngine().findProcessDefinitions(approvalProcess, PredefinedProcessType.ROLE_DEFINITION_APPROVAL);
				if (defs == null || defs.isEmpty())
					throw new InternalErrorException ("No business process found with name '%s'", approvalProcess);
				ProcessDefinition def = defs.get(0);
	
				ProcessInstance pi = engine.newProcess(def, false);
				pi.getVariables().put("roleId", role.getId());
				pi.getVariables().put("roleName", role.getName());
				pi.getVariables().put("system", role.getSystem().getName());
				pi.getVariables().put("application", role.getInformationSystem().getName());
				pi.getVariables().put("requester", Security.getCurrentUser());
				engine.update(pi);
				engine.startProcess(pi);
				
				role.setApprovalProcess( pi.getId());
				role.setApprovalEnd(null);
				role.setApprovalStart(new java.util.Date());
				update (role);
				
				return true;
			} finally {
				Security.nestedLogoff();
			}
		}
	}
	
	private void updateGranteeRoles(Role role, RoleEntity entity) throws InternalErrorException, BPMException {
		// Compraramos con los existentes anteriormente : i esborrem els que
		// ja no existeixen
		LinkedList<RoleDependencyEntity> currentGrants = new LinkedList<RoleDependencyEntity>(entity.getContainerRoles());
		if (role.getOwnerRoles() != null) {
			for (RoleGrant grant: role.getOwnerRoles())
			{
				boolean found = false;
				for ( Iterator<RoleDependencyEntity> it = currentGrants.iterator(); it.hasNext();)
				{
					RoleDependencyEntity grantEntity = it.next();
					if (grantEntity.getId().equals( grant.getId()))
					{
						found = true;
						it.remove();
						updateRolDependency(grantEntity, grant);
						break;
					}
						
				}
				if ( ! found )
				{
					grant.setRoleId(entity.getId());
					createRoleDependency(grant, true);
				}
			}
		}
		for ( RoleDependencyEntity grantEntity: currentGrants)
		{
			if (deleteRolDependency(grantEntity))
				entity.getContainerRoles().remove (grantEntity);
		}
	}
	 
	private void updateGranteeGroups(Role role, RoleEntity entity) throws InternalErrorException, BPMException {
		// Compraramos con los existentes anteriormente : i esborrem els que
		// ja no existeixen
		LinkedList<RoleGroupEntity> currentGrants = new LinkedList<RoleGroupEntity>(entity.getContainerGroups());
		if (role.getGranteeGroups() != null) {
			for (RoleGrant grant: role.getGranteeGroups())
			{
				boolean found = false;
				for ( Iterator<RoleGroupEntity> it = currentGrants.iterator(); it.hasNext();)
				{
					RoleGroupEntity grantEntity = it.next();
					if (grantEntity.getId().equals( grant.getId()))
					{
						found = true;
						it.remove();
						// NO update allowed
//						updateRoleGroupDependency(grantEntity, grant);
						break;
					}
						
				}
				if ( ! found )
					createRoleGroupDependency(grant);
			}
		}
		for ( RoleGroupEntity grantEntity: currentGrants)
		{
			deleteRoleGroupDependency(grantEntity);
		}
	}
	 
	private boolean updateGrantedRoles(Role role, RoleEntity entity) throws InternalErrorException, BPMException {
		// Compraramos con los existentes anteriormente : i esborrem els que
		// ja no existeixen
		boolean anyChange = false;
		
		LinkedList<RoleDependencyEntity> currentGrants = new LinkedList<RoleDependencyEntity>(entity.getContainedRoles());
		if (role.getOwnedRoles() != null) {
			for (RoleGrant grant: role.getOwnedRoles())
			{
				boolean found = false;
				for ( Iterator<RoleDependencyEntity> it = currentGrants.iterator(); it.hasNext();)
				{
					RoleDependencyEntity grantEntity = it.next();
					if (grantEntity.getId().equals( grant.getId()))
					{
						found = true;
						it.remove();
						if (updateRolDependency(grantEntity, grant))
							anyChange = true;
						break;
					}
						
				}
				if ( ! found )
				{
					grant.setOwnerRole(entity.getId());
					createRoleDependency(grant, false);
					anyChange = true;
				}
			}
		}
		for ( RoleDependencyEntity grantEntity: currentGrants)
		{
			anyChange = true;
			if (deleteRolDependency(grantEntity)) 
				entity.getContainedRoles().remove (grantEntity);
		}
		return anyChange;
	}
	 
	private void createRoleDependency(RoleGrant grant, boolean checkGranteeCycles) throws InternalErrorException, BPMException {
		StringBuffer path = new StringBuffer();

		RoleEntity ownerRole = grant.getOwnerRole() != null? 
				load (grant.getOwnerRole()) :
					findByNameAndSystem(grant.getOwnerRoleName(), grant.getOwnerSystem());
		if (ownerRole == null)
			throw new InternalErrorException("Invalid owner role");
		grant.setOwnerRole( ownerRole.getId());
		
        RoleEntity ownedRole = grant.getRoleId() != null ?
        		load (grant.getRoleId()) :
        			findByNameAndSystem(grant.getRoleName(), grant.getSystem());
		if (ownedRole == null)
			throw new InternalErrorException("Invalid owned role");
        grant.setRoleId(ownedRole.getId());
        
        if (checkNoCycles( grant, path, checkGranteeCycles)) {
        	RoleDependencyEntity entity = getRoleDependencyEntityDao().newRoleDependencyEntity();
        	
        	getRoleDependencyEntityDao().roleGrantToEntity(grant, entity, true);
	 
            if (startApprovalProcess(ownerRole))
            	entity.setStatus(RoleDependencyStatus.STATUS_TOAPPROVE);
            else
            	entity.setStatus(RoleDependencyStatus.STATUS_ACTIVE);
            
            getRoleDependencyEntityDao().create(entity);
            grant.setId(entity.getId());
            grant.setStatus(entity.getStatus());
        } else {
			throw new InternalErrorException(String.format(Messages.getString("RoleEntityDaoImpl.0"),   //$NON-NLS-1$
					ownedRole.toRoleDescription(), 
					ownerRole.toRoleDescription(), 
					path));
        }
	}


	private void createRoleGroupDependency(RoleGrant grant) throws InternalErrorException, BPMException {
		StringBuffer path = new StringBuffer();
        GroupEntity ownerGroup = getGroupEntityDao().findByName(grant.getOwnerGroup()); 
        RoleEntity ownedRole = load (grant.getRoleId()); 
    
    	RoleGroupEntity entity = getRoleGroupEntityDao().newRoleGroupEntity();
    	
        entity.setGrantedRole(ownedRole);
        entity.setGroup(ownerGroup);

        String nomDomini = ownedRole.getDomainType();
        if (TipusDomini.APLICACIONS.equals(nomDomini) ||
        		TipusDomini.APPLICATIONS.equals(nomDomini))
        {
        	entity.setGrantedApplicationDomain(getInformationSystemEntityDao().findByCode(grant.getDomainValue()));
        }
        else if (TipusDomini.GRUPS.equals(nomDomini) || TipusDomini.GRUPS_USUARI.equals(nomDomini) ||
        		TipusDomini.GROUPS.equals(nomDomini) || TipusDomini.MEMBERSHIPS.equals(nomDomini))
        {
        	entity.setGrantedGroupDomain(getGroupEntityDao().findByName(grant.getDomainValue()));
        }
        else if (TipusDomini.DOMINI_APLICACIO.equals(nomDomini) ||
        		TipusDomini.CUSTOM.equals(nomDomini))
        {
        	entity.setGrantedDomainValue(
        			getDomainValueEntityDao()
        				.findByApplicationDomainValue(
        						ownedRole.getInformationSystem().getName(), 
        						ownedRole.getApplicationDomain().getName(), 
        						grant.getDomainValue()));
	         }

        if ( Hibernate.isInitialized(ownedRole.getContainerGroups()))
        	ownedRole.getContainerGroups().add(entity);
        
        if ( Hibernate.isInitialized(ownerGroup.getGrantedRoles()))
        	ownerGroup.getGrantedRoles().add(entity);

        getRoleGroupEntityDao().create(entity);

	}

	private void deleteRoleGroupDependency(RoleGroupEntity entity) throws InternalErrorException, BPMException {
		getRoleGroupEntityDao().remove(entity);
	}
	
	private boolean deleteRolDependency(RoleDependencyEntity entity) throws InternalErrorException, BPMException {
        RoleEntity ownerRole = entity.getContainer();
        
        // Delete a not approved yet role or no approval is needed
        if (RoleDependencyStatus.STATUS_TOAPPROVE.equals( entity.getStatus()) || 
        		!startApprovalProcess(ownerRole))
        {
        	if (Hibernate.isInitialized(entity.getContained().getContainerRoles()))
        		entity.getContained().getContainedRoles().remove(entity);
        	if (Hibernate.isInitialized(entity.getContainer().getContainedRoles()))
        		entity.getContainer().getContainerRoles().remove(entity);
        	getRoleDependencyEntityDao().remove(entity);
        	return true;
        } else {
        	entity.setStatus(RoleDependencyStatus.STATUS_TOREMOVE);
        	getRoleDependencyEntityDao().update(entity);
        	return false;
        }
	}

	private boolean updateRolDependency(RoleDependencyEntity entity, RoleGrant grant) throws InternalErrorException, BPMException {
		boolean anyChange = false;
		if (RoleDependencyStatus.STATUS_TOREMOVE.equals(grant.getStatus()))
		{
			anyChange = true;
			deleteRolDependency(entity);
		}
		else
		{
			if ( entity.getMandatory() == null ||
					!entity.getMandatory().equals(grant.getMandatory()))
				anyChange = true;
			entity.setMandatory(grant.getMandatory());
			getRoleDependencyEntityDao().update(entity);
		}
		return anyChange;
	}

	public void remove(com.soffid.iam.model.RoleEntity roleEntity)
			throws RuntimeException {
		try {
			updateMailLists(roleEntity);
			// NO SE PUEDE BORRAR UN Role SI TIENE RELACIONES EXTERNAS
			// SE DA UN AVISO Y NO SE DEJA BORRAR EL Role

			// Obtenemos sus relaciones con otros roles (como contenedor o
			// contenido)
			Collection rolAssociacioRolSocContenidor = roleEntity.getContainedRoles();
			Collection rolAssociacioRolSocContingut = roleEntity.getContainerRoles();
			Collection grupsPosseidors = roleEntity.getContainerGroups();
			// Collection rolFitxers = Role.getRolFitxers();
			Collection rolsUsuari = roleEntity.getAccounts();
			Collection rolsAutoritzacioXarxa = roleEntity.getNetworkAuthorization();

			String msgError = ""; //$NON-NLS-1$
			if (rolAssociacioRolSocContenidor.size() != 0) {
				msgError += Messages.getString("RoleEntityDaoImpl.3"); //$NON-NLS-1$
			} else if (rolAssociacioRolSocContingut.size() != 0) {
				msgError += Messages.getString("RoleEntityDaoImpl.4"); //$NON-NLS-1$
			} else if (grupsPosseidors.size() != 0) {
				msgError += Messages.getString("RoleEntityDaoImpl.5"); //$NON-NLS-1$
				// } else if (rolFitxers.size() != 0) {
				//                msgError += Messages.getString("RoleEntityDaoImpl.6");  //$NON-NLS-1$
			} else if (rolsUsuari.size() != 0) {
				msgError += Messages.getString("RoleEntityDaoImpl.7"); //$NON-NLS-1$
			} else if (rolsAutoritzacioXarxa.size() != 0) {
				msgError += Messages.getString("RoleEntityDaoImpl.8"); //$NON-NLS-1$
			} else if (roleEntity.getNotificationEntities().size() != 0) {
				msgError += Messages.getString("RoleEntityDaoImpl.9"); //$NON-NLS-1$
			}

			// Generamos error si se cumple alguna de las condiciones
			if (!"".equals(msgError)) //$NON-NLS-1$
				throw new Exception(String.format(
						Messages.getString("RoleEntityDaoImpl.10"), msgError)); //$NON-NLS-1$

			// Eliminamos las asociaciones con otros ROLES (en ambos casos)
			// No se borra porque NO PUEDEN EXISTIR para poder borrar el Role(!!)
			/*
			 * for (Iterator it= rolAssociacioRolSocContenidor.iterator();
			 * it.hasNext();) { // El método remove(Collection) can be a little
			 * bit dangerous RoleDependencyEntity associacio =
			 * (RoleDependencyEntity) it.next();
			 * getRoleDependencyEntityDao().remove(associacio); }
			 * rolAssociacioRolSocContenidor.clear();
			 * 
			 * 
			 * for (Iterator it = rolAssociacioRolSocContingut.iterator();
			 * it.hasNext();) { RoleDependencyEntity associacio =
			 * (RoleDependencyEntity) it.next();
			 * getRoleDependencyEntityDao().remove(associacio); }
			 * rolAssociacioRolSocContingut.clear();
			 * 
			 * // Eliminamos relaciones con roles (padres) y con grupos //
			 * Obtenemos las relaciones con GRUPOS for (Iterator it=
			 * grupsPosseidors.iterator(); it.hasNext();) { RoleGroupEntity
			 * rolsgrup = (RoleGroupEntity) it.next();
			 * getRoleGroupEntityDao().remove(rolsgrup); }
			 * grupsPosseidors.clear();
			 * 
			 * // Roles: relaciones con Ficheros for (Iterator it =
			 * rolFitxers.iterator(); it.hasNext();) { RolFitxerEntity rolfitxer
			 * = (RolFitxerEntity) it.next();
			 * getRolFitxerEntityDao().remove(rolfitxer); } rolFitxers.clear();
			 * 
			 * // Realaciones con Usuarios for (Iterator it =
			 * rolsUsuari.iterator(); it.hasNext();) { RolsUsuarisEntity
			 * rolusuari = (RolsUsuarisEntity) it.next();
			 * getRolsUsuarisEntityDao().remove(rolusuari); }
			 * rolsUsuari.clear(); // Relaciones con XarxaAC for (Iterator it =
			 * rolsAutoritzacioXarxa.iterator(); it.hasNext();) { XarxaACEntity
			 * autoritzacio = (XarxaACEntity) it.next();
			 * getXarxaACEntityDao().remove (autoritzacio); }
			 */

			String nomRol = roleEntity.getName();
			String codiBaseDeDades = roleEntity.getSystem().getName();
			String codiAplicacio = roleEntity.getInformationSystem().getName();

			// Abans d'eliminar el Role, obtenim els grups, rols i usuaris
			// afectats indirectament (per herència)
			HashSet<UserEntity> usuarisPropagar = new HashSet<UserEntity>();
			HashSet<com.soffid.iam.model.AccountEntity> accountsPropagar = new HashSet<com.soffid.iam.model.AccountEntity>();
			HashSet<RoleEntity> rolsPropagar = new HashSet<RoleEntity>();
			HashSet<GroupEntity> grupsPropagar = new HashSet<GroupEntity>();
			// Obtenim el resultat de rols a la creació del Role
			// Ho cerquem a la base de dades (relació amb rols-grups): encara
			// que no hauria de tindre cap relació.. per si de cas
			getHerenciaRol_Usuaris_Rols_Grups(roleEntity, usuarisPropagar,
					accountsPropagar, rolsPropagar, grupsPropagar, true);

			if (Hibernate.isInitialized(roleEntity.getInformationSystem()) &&
					Hibernate.isInitialized(roleEntity.getInformationSystem().getRoles()))
				roleEntity.getInformationSystem().getRoles().remove(roleEntity);
			super.remove(roleEntity);
			getSession(false).flush();

			// Propaguem els canvis d'eliminar l'herència:
			propagarUsuarisRolsIGrups(usuarisPropagar, accountsPropagar,
					rolsPropagar, grupsPropagar);

			TaskEntity tasque = getTaskEntityDao().newTaskEntity();
			tasque.setDate(new Timestamp(System.currentTimeMillis()));
			tasque.setTransaction(TaskHandler.UPDATE_ROLE);
			tasque.setRole(roleEntity.getName());
			tasque.setDb(roleEntity.getSystem().getName());
			tasque.setSystemName(roleEntity.getSystem().getName());
			getTaskEntityDao().createNoFlush(tasque);
			auditarRol("D", nomRol, codiAplicacio, codiBaseDeDades); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(
					String.format(
							Messages.getString("RoleEntityDaoImpl.11"), roleEntity.getName(), message)); //$NON-NLS-1$
		}
	}

	public void toRole(com.soffid.iam.model.RoleEntity sourceEntity,
			com.soffid.iam.api.Role targetVO) {
		super.toRole(sourceEntity, targetVO);
		toRolCustom(sourceEntity, targetVO);
	}

	private void toRolCustom(com.soffid.iam.model.RoleEntity sourceEntity,
			com.soffid.iam.api.Role targetVO) {

		// Obtenemos la relación entre este Role y los grupos (1:N)
		Collection grupsEntityPosseidors = sourceEntity.getContainerGroups(); // tipo
																				// RoleGroupEntity
		Collection<Group> grupsPosseidors = new ArrayList<Group>(); // tipo Grup
		Collection<RoleGrant> granteeGroups = new ArrayList<RoleGrant>(); // tipo
																			// Grup
		if (grupsEntityPosseidors != null) {
			for (Iterator it = grupsEntityPosseidors.iterator(); it.hasNext();) {
				RoleGroupEntity rg = (RoleGroupEntity) it.next();
				if (rg.getGroup() != null) {
					GroupEntity posseidor = (GroupEntity) rg.getGroup();
					Group grupo = getGroupEntityDao().toGroup(posseidor);
					grupsPosseidors.add(grupo);
					granteeGroups.add(getRoleGroupEntityDao().toRoleGrant(rg));
				}
			}
		}
		targetVO.setOwnerGroups(grupsPosseidors);
		targetVO.setGranteeGroups(granteeGroups);

		String tipusDomini = sourceEntity.getDomainType();
		if (tipusDomini == null || tipusDomini.trim().isEmpty() ||
				TipusDomini.SENSE_DOMINI.equals(tipusDomini)) { //$NON-NLS-1$
			targetVO.setDomain(null);
		} else if (TipusDomini.GROUPS.equals(tipusDomini) ||
				TipusDomini.GRUPS.equals(tipusDomini) ||
				TipusDomini.MEMBERSHIPS.equals(tipusDomini) ||
				TipusDomini.GRUPS_USUARI.equals(tipusDomini)) {
			targetVO.setDomain(TipusDomini.GROUPS);
		} else if (tipusDomini.compareTo(TipusDomini.APLICACIONS) == 0 ||
				tipusDomini.equals(TipusDomini.APPLICATIONS)) {
			targetVO.setDomain(TipusDomini.APPLICATIONS);
		} else if (sourceEntity.getApplicationDomain() == null) {
			targetVO.setDomain(null);
		} else {
			targetVO.setDomain(sourceEntity.getApplicationDomain().getName());
		}

		InformationSystemEntity aplicacioEntity = sourceEntity
				.getInformationSystem();
		if (aplicacioEntity != null) {
			targetVO.setInformationSystemName(aplicacioEntity.getName());
		}
		targetVO.setEnableByDefault(new Boolean(sourceEntity.getDefaultRole()
				.compareTo("S") == 0)); //$NON-NLS-1$
		targetVO.setBpmEnforced(new Boolean(sourceEntity.getManageableWF()
				.compareTo("S") == 0)); //$NON-NLS-1$
		String contrasenya = sourceEntity.getPassword();
		if (contrasenya != null && contrasenya.trim().compareTo("") != 0) { //$NON-NLS-1$
			targetVO.setPassword(new Boolean(sourceEntity.getPassword()
					.compareTo("S") == 0)); //$NON-NLS-1$
		}
		SystemEntity baseDeDades = sourceEntity.getSystem();
		if (baseDeDades != null) {
			targetVO.setSystem(baseDeDades.getName());
		}

		// Obtenemos los roles padres (en los que estamos contenidos) - somos
		// otorgados
		Collection<RoleDependencyEntity> pares = sourceEntity
				.getContainerRoles();
		Collection<RoleGrant> rolsPosseidorsRol = new LinkedList<RoleGrant>();
		if (pares != null) {
			for (Iterator<RoleDependencyEntity> iterator = pares.iterator(); iterator
					.hasNext();) {
				RoleDependencyEntity currentPareRolAssociacioRol = (RoleDependencyEntity) iterator
						.next();
				RoleGrant rg = getRoleDependencyEntityDao().toRoleGrant(
						currentPareRolAssociacioRol);
				rolsPosseidorsRol.add(rg);
			}
		}
		targetVO.setOwnerRoles(rolsPosseidorsRol);

		// Obtenim els rols que tinc atorgats com a "fills"
		Collection<RoleDependencyEntity> fills = sourceEntity
				.getContainedRoles();
		Collection<RoleGrant> rolsAtorgatsRol = new LinkedList<RoleGrant>();
		if (fills != null) {
			for (Iterator<RoleDependencyEntity> iterator = fills.iterator(); iterator
					.hasNext();) {
				RoleDependencyEntity currentPareRolAssociacioRol = (RoleDependencyEntity) iterator
						.next();
				RoleGrant rg = getRoleDependencyEntityDao().toRoleGrant(
						currentPareRolAssociacioRol);
				rolsAtorgatsRol.add(rg);
			}
		}
		targetVO.setOwnedRoles(rolsAtorgatsRol);

		targetVO.setAttributes(new HashMap<String, Object>());
		Map<String, Object> attributes = targetVO.getAttributes();
		for (RoleAttributeEntity att : sourceEntity.getAttributes()) {
			if (att.getMetadata().getMultiValued() != null && att.getMetadata().getMultiValued().booleanValue())
			{
				LinkedList<Object> r = (LinkedList<Object>) attributes.get(att.getMetadata().getName());
				if (r == null)
				{
					r = new LinkedList<Object>();
					attributes.put(att.getMetadata().getName(), r);
				}
				r.add(att.getObjectValue());
			}
			else
			{
				attributes.put(att.getMetadata().getName(),att.getObjectValue());
			}
		}
		for (Object o: attributes.values())
		{
			if (o != null && o instanceof List) Collections.sort((List) o);
		}

	}

	/**
	 * @see RoleEntityDao#toRol(RoleEntity)
	 */
	public com.soffid.iam.api.Role toRole(
			final com.soffid.iam.model.RoleEntity entity) {
		com.soffid.iam.api.Role role = super.toRole(entity);
		// toRolCustom(entity, role); //NO ES FA ACI (!!)
		return role;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.RoleEntity loadRoleEntityFromRol(
			com.soffid.iam.api.Role role) {
		RoleEntity RoleEntity = null;
		if (role.getId() != null) {
			RoleEntity = load(role.getId());
		}
		if (RoleEntity == null) {
			RoleEntity = newRoleEntity();
		}
		return RoleEntity;
	}

	public com.soffid.iam.model.RoleEntity roleToEntity(
			com.soffid.iam.api.Role role) {
		com.soffid.iam.model.RoleEntity entity = this
				.loadRoleEntityFromRol(role);
		roleToEntity(role, entity, true);
		return entity;
	}

	private DomainValueEntity findValorDominiByNomDominiAndCodiAplicacioDominiAndValor(
			String nomDomini, String codiAplicacio, String valor) {
		return getDomainValueEntityDao().findByApplicationDomainValue(
				codiAplicacio, nomDomini, valor);
	}

	private void rolToEntityCustom(com.soffid.iam.api.Role sourceVO,
			com.soffid.iam.model.RoleEntity targetEntity) {// de VO a Entity

		// Transformación a nivel de Objeto (NO ACCESO BBDD)
		// targetEntity puede estar vacía (CREATE) o tener referencias (UPDATE)
		// [importante]

		updateEntityDomainType(sourceVO, targetEntity);
		updateEntityApplication(sourceVO, targetEntity);
		upateEntityOthers(sourceVO, targetEntity);

	}

	private void upateEntityOthers(com.soffid.iam.api.Role sourceVO,
			com.soffid.iam.model.RoleEntity targetEntity) {
		Boolean perDefecte = sourceVO.getEnableByDefault();
		if (perDefecte != null) {
			targetEntity.setDefaultRole(sourceVO.getEnableByDefault()
					.booleanValue() ? "S" : "N"); //$NON-NLS-1$
		} else {
			targetEntity.setDefaultRole("N"); //$NON-NLS-1$
		}
		Boolean contrasenya = sourceVO.getPassword();
		if (contrasenya != null) {
			targetEntity
					.setPassword(sourceVO.getPassword().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			targetEntity.setPassword("N"); //$NON-NLS-1$
		}
		Boolean gestionableWF = sourceVO.getBpmEnforced();
		if (gestionableWF != null) {
			targetEntity.setManageableWF(sourceVO.getBpmEnforced()
					.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
		} else
			targetEntity.setManageableWF("N"); //$NON-NLS-1$
		String codiDispatcher = sourceVO.getSystem();
		if (codiDispatcher != null && codiDispatcher.trim().compareTo("") != 0) { //$NON-NLS-1$
			SystemEntity dispatcherEntity = this.getSystemEntityDao()
					.findByName(codiDispatcher);
			if (dispatcherEntity != null) {
				targetEntity.setSystem(dispatcherEntity);
			} else {
				throw new SeyconException(String.format(
						Messages.getString("RoleEntityDaoImpl.18"), //$NON-NLS-1$
						codiDispatcher));
			}
		} else {
			targetEntity.setSystem(null);
		}
	}

	private void updateEntityApplication(com.soffid.iam.api.Role sourceVO,
			com.soffid.iam.model.RoleEntity targetEntity) {
		String codiAplicacio = sourceVO.getInformationSystemName();
		if (codiAplicacio != null) {
			InformationSystemEntity aplicacioEntity = getInformationSystemEntityDao()
					.findByCode(codiAplicacio);
			targetEntity.setInformationSystem(aplicacioEntity);
		} else {
			targetEntity.setInformationSystem(null);
		}
	}

	private void updateEntityGranteeGroups(com.soffid.iam.api.Role sourceVO,
			com.soffid.iam.model.RoleEntity targetEntity) {
		Collection<Group> grupsPosseidors = sourceVO.getOwnerGroups();
		Collection<RoleGrant> granteeGroups = sourceVO.getGranteeGroups();
		// Eliminamos las referencias existentes
		Collection<RoleGroupEntity> grupsPosseidorsRoleEntity = new HashSet<RoleGroupEntity>();
		if (granteeGroups == null && grupsPosseidors != null) {
			// Creamos las relaciones existentes con los grupos
			for (Iterator<Group> it = grupsPosseidors.iterator(); it.hasNext();) {
				Group grup = it.next();
				GroupEntity GroupEntity = getGroupEntityDao().load(grup.getId());
				RoleGroupEntity rge = getRoleGroupEntityDao()
						.newRoleGroupEntity();
				rge.setGrantedRole(targetEntity);
				rge.setGroup(GroupEntity);
				grupsPosseidorsRoleEntity.add(rge);
			}
		} else if (granteeGroups != null) {
			// Creamos las relaciones existentes con los grupos
			for (Iterator<RoleGrant> it = granteeGroups.iterator(); it
					.hasNext();) {
				RoleGrant grant = it.next();
				GroupEntity GroupEntity = getGroupEntityDao().findByName(
						grant.getOwnerGroup());
				if (GroupEntity == null)
					throw new java.lang.IllegalArgumentException("group " + grant.getOwnerGroup());
				RoleGroupEntity rge = getRoleGroupEntityDao()
						.newRoleGroupEntity();
				rge.setGrantedRole(targetEntity);
				rge.setGroup(GroupEntity);
				String nomDomini = targetEntity.getDomainType();
				if (TipusDomini.APLICACIONS.equals(nomDomini) ||
						TipusDomini.APPLICATIONS.equals(nomDomini)) {
					rge.setGrantedApplicationDomain(getInformationSystemEntityDao()
							.findByCode(grant.getDomainValue()));
				} else if (TipusDomini.GRUPS.equals(nomDomini)
						|| TipusDomini.MEMBERSHIPS.equals(nomDomini)
						|| TipusDomini.GRUPS_USUARI.equals(nomDomini)
						|| TipusDomini.GROUPS.equals(nomDomini)) {
					rge.setGrantedGroupDomain(getGroupEntityDao().findByName(
							grant.getDomainValue()));
				} else if (TipusDomini.DOMINI_APLICACIO.equals(nomDomini)
						|| TipusDomini.CUSTOM.equals(nomDomini)) {
					rge.setGrantedDomainValue(getDomainValueEntityDao()
							.findByApplicationDomainValue(
									targetEntity.getInformationSystem()
											.getName(),
									targetEntity.getApplicationDomain()
											.getName(), grant.getDomainValue()));
				}
				grupsPosseidorsRoleEntity.add(rge);
			}

		}
		targetEntity.setContainerGroups(grupsPosseidorsRoleEntity);
	}

	private void updateEntityDomainType(com.soffid.iam.api.Role sourceVO,
			com.soffid.iam.model.RoleEntity targetEntity) {
		String nomDomini = sourceVO.getDomain();
		if (nomDomini == null || nomDomini.trim().isEmpty()) { //$NON-NLS-1$
			targetEntity.setApplicationDomain(null);
			targetEntity.setDomainType(null);
		}
		else if (TipusDomini.APLICACIONS.equals(nomDomini) ||
				TipusDomini.APPLICATIONS.equals(nomDomini) ) { //$NON-NLS-1$
			targetEntity.setApplicationDomain(null);
			targetEntity.setDomainType(TipusDomini.APPLICATIONS);
		}
		else if (TipusDomini.GROUPS.equals(nomDomini) ||
				TipusDomini.GRUPS_USUARI.equals(nomDomini) || //$NON-NLS-1$
				TipusDomini.MEMBERSHIPS.equals(nomDomini) || //$NON-NLS-1$
				TipusDomini.GRUPS.equals(nomDomini) ) { //$NON-NLS-1$
			targetEntity.setApplicationDomain(null);
			targetEntity.setDomainType(TipusDomini.GROUPS);
		} else {
			ApplicationDomainEntity dominiAplicacioEntity = findDominiByNomAndCodiApliacio(
					nomDomini, sourceVO.getInformationSystemName());
			if (dominiAplicacioEntity != null) {
				targetEntity.setApplicationDomain(dominiAplicacioEntity);
				targetEntity.setDomainType(TipusDomini.DOMINI_APLICACIO);
			} else {
				throw new SeyconException(String.format(
						Messages.getString("RoleEntityDaoImpl.19"),
						nomDomini, sourceVO.getInformationSystemName()));
			}

		}
	}


	private ApplicationDomainEntity findDominiByNomAndCodiApliacio(String nom,
			String codiAplicacio) {

		return getApplicationDomainEntityDao().findByName(nom, codiAplicacio);
	}

	public void roleToEntity(com.soffid.iam.api.Role sourceVO,
			com.soffid.iam.model.RoleEntity targetEntity, boolean copyIfNull) {
		super.roleToEntity(sourceVO, targetEntity, copyIfNull);
		rolToEntityCustom(sourceVO, targetEntity);
	}

	/**
	 * @see GroupEntityDao#toIdentitat(GroupEntity,
	 *      es.caib.seycon.ng.comu.Identitat)
	 */
	public void toIdentity(com.soffid.iam.model.RoleEntity source,
			com.soffid.iam.api.Identity target) {
		super.toIdentity(source, target);
		toIdentitatCustom(source, target);
	}

	public void toIdentitatCustom(com.soffid.iam.model.RoleEntity source,
			com.soffid.iam.api.Identity target) {
		String nomRol = source.getName();
		InformationSystemEntity aplicacio = source.getInformationSystem();
		SystemEntity dispatcher = source.getSystem();
		target.setRoleName(nomRol + "@" + dispatcher.getName() );
		target.setIdentityCode(nomRol + "@" + dispatcher.getName());
		String descripcio = source.getDescription();
		target.setDescription(descripcio);
	}

	/**
	 * @see RoleEntityDao#toIdentitat(RoleEntity)
	 */
	public com.soffid.iam.api.Identity toIdentity(
			final com.soffid.iam.model.RoleEntity entity) {
		Identity identitat = super.toIdentity(entity);
		toIdentitatCustom(entity, identitat);
		return identitat;
	}

	/**
	 * Retrieves the entity object that is associated with the specified value
	 * object from the object store. If no such entity object exists in the
	 * object store, a new, blank entity is created
	 */
	private com.soffid.iam.model.RoleEntity loadRoleEntityFromIdentitat(
			com.soffid.iam.api.Identity identitat) {
		/*
		 * La identitat és read only
		 */
		String nomRolComplert = identitat.getRoleName();
		if (nomRolComplert != null) {
			RoleEntity RoleEntity = null;
			String[] partsNomRol = nomRolComplert.split("@"); //$NON-NLS-1$
			String[] partsNomRol2 = partsNomRol[1].split(">"); //$NON-NLS-1$
			RoleEntity = findRoleByNameInformationSystemAndStystem(
					partsNomRol[0], partsNomRol2[1], partsNomRol2[0]);
			if (RoleEntity != null) {
				return RoleEntity;
			} else {
				throw new SeyconException(
						String.format(
								Messages.getString("RoleEntityDaoImpl.21"), partsNomRol[0])); //$NON-NLS-1$
			}
		}
		throw new SeyconException(Messages.getString("RoleEntityDaoImpl.22")); //$NON-NLS-1$
	}

	/**
	 * @see RoleEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat)
	 */
	public com.soffid.iam.model.RoleEntity identityToEntity(
			com.soffid.iam.api.Identity identitat) {
		com.soffid.iam.model.RoleEntity entity = this
				.loadRoleEntityFromIdentitat(identitat);
		this.identityToEntity(identitat, entity, true);
		return entity;
	}

	/**
	 * @see RoleEntityDao#identitatToEntity(es.caib.seycon.ng.comu.Identitat,
	 *      RoleEntity)
	 */
	public void identityToEntity(com.soffid.iam.api.Identity source,
			com.soffid.iam.model.RoleEntity target, boolean copyIfNull) {
		super.identityToEntity(source, target, copyIfNull);
	}

	public RoleEntity containerRoleToEntity(ContainerRole contenidorRol) {
		return null;
	}

	public ContainerRole toContainerRole(RoleEntity entity) {
		ContainerRole contenidorRol = super.toContainerRole(entity); // Pasamos
																		// el id
		contenidorRol.setType(TipusContenidorRol.ROL_ENTITY);
		// Información específica:
		contenidorRol
				.setContainerInfo(entity.getName()
						+ "@" + entity.getSystem().getName() + " (" + entity.getInformationSystem().getName() + ")"); //$NON-NLS-1$

		return contenidorRol;
	}

	public void containerRoleToEntity(ContainerRole source, RoleEntity target,
			boolean copyIfNull) {
		super.containerRoleToEntity(source, target, copyIfNull);
	}

	private Collection<GroupEntity> getTotsFillsGrup(GroupEntity grupAnalitzar) {
		Collection<GroupEntity> fills = grupAnalitzar.getChildren();
		Collection<GroupEntity> totsFills = new HashSet<GroupEntity>();

		for (Iterator<GroupEntity> it = fills.iterator(); it.hasNext();) {
			GroupEntity fill = it.next();
			totsFills.add(fill);
			totsFills.addAll(getTotsFillsGrup(fill));
		}
		return totsFills;
	}

	private Collection<UserEntity> findCodiUsuarisPertanyenGrups(
			Collection<GroupEntity> grupsISubgrups) {
		// Conté els codi dels usuaris a propagar:
		HashSet<UserEntity> usuarisPropagar = new HashSet<UserEntity>();

		// Cerquem els usuaris que tenen com a grup primari qualque grup del
		// llistat i els que els tenen com a grup secundari
		for (Iterator<GroupEntity> it = grupsISubgrups.iterator(); it.hasNext();) {
			GroupEntity g = it.next();
			Collection<UserEntity> usuGPrim = null;
			usuGPrim = getUserEntityDao().findByPrimaryGroup(g.getName());
			if (usuGPrim != null)
				for (Iterator<UserEntity> gpr_it = usuGPrim.iterator(); gpr_it
						.hasNext();) {
					UserEntity usu = gpr_it.next();
					usuarisPropagar.add(usu);
				}
			Collection<UserGroupEntity> usuSec = g.getSecondaryGroupUsers();
			if (usuSec != null)
				for (Iterator<UserGroupEntity> gps_it = usuSec.iterator(); gps_it
						.hasNext();) {
					UserGroupEntity usugru = gps_it.next();
					usuarisPropagar.add(usugru.getUser());
				}
		}

		// Devolvemos los UserEntity:
		return usuarisPropagar;
	}

	/**
	 * Obté els usuaris, grups i rols que conté un Role a l'estat actual
	 * 
	 * @param rolsPropagar
	 * @param grupsPropagar
	 * @param usuarisPropagar
	 * @param grupsPropagarAfter
	 */
	private void getHerenciaRol_Usuaris_Rols_Grups(RoleEntity Role,
			HashSet<UserEntity> usuarisPropagar,
			HashSet<com.soffid.iam.model.AccountEntity> accountsPropagar,
			HashSet<RoleEntity> rolsPropagar,
			HashSet<GroupEntity> grupsPropagar, boolean cercaRolABaseDades) {

		// Sólo hemos de propagar a los usuarios que tienen el Role contenedor
		// con valor de dominio correspondiente (o si es SENSE_DOMINI o a
		// qualque valor)
		// Montamos un FIFO De roles (puede haber cadena de
		// herencia A atorgat B[sense domini] atorgat C ... atorgat Z[amb
		// domini]
		LinkedList<RoleEntity> rolsAnalitzar = new LinkedList<RoleEntity>(); // FIFO
		rolsAnalitzar.add(Role);
		RoleEntity rolActual = null;

		// Añadimos el Role actual a roles a propagar (se ha deshabilitado el
		// TRIGGER SC_ROLES_UPD !!)
		rolsPropagar.add(Role);

		while ((rolActual = rolsAnalitzar.poll()) != null) {
			// Ho cerquem a la base de dades si ja existeix (update)
			Collection<RoleDependencyEntity> socContingut = null;
			if (cercaRolABaseDades) // ho fem a l'update (per saber l'estat
									// abans)
				socContingut = getRoleDependencyEntityDao()
						.findRolesAssociationContainerRole(rolActual);
			else
				socContingut = rolActual.getContainerRoles();

			if (socContingut != null)
				for (Iterator<RoleDependencyEntity> it = socContingut
						.iterator(); it.hasNext();) {
					RoleDependencyEntity associacio = (RoleDependencyEntity) it
							.next();
                    if (associacio.getStatus() == null ||
                    		associacio.getStatus().equals (RoleDependencyStatus.STATUS_ACTIVE) ||
                    		associacio.getStatus().equals(RoleDependencyStatus.STATUS_TOREMOVE))
                    {
	                    // Obtenemos los usuarios del contenedor
	                    RoleEntity rolContenidor = associacio.getContainer();
	                    // Guardamos el Role para propagarlo
	                    rolsPropagar.add(rolContenidor);
	                    // Añadimos el Role contenedor para analizar si a su vez es
	                    // contenido en otro (atorgat)
	                    rolsAnalitzar.add(rolContenidor);
	                    Collection<RoleAccountEntity> rolsUsuarisRolContenidor = new ArrayList<RoleAccountEntity>();
	                    // Cerquem usuaris amb el Role d'usuari amb valor de domini
	                    // corresponent
	                    if (associacio.getGranteeGroupDomain() != null
	                            || associacio.getGranteeApplicationDomain() != null
	                            || associacio.getGranteeDomainValue() != null) {
	                        rolsUsuarisRolContenidor = getRoleAccountEntityDao()
	                                .findByRoleAndDomainValue(
	                                        rolContenidor.getName(),
	                                        rolContenidor.getSystem()
	                                                .getName(),
	                                        rolContenidor.getDomainType(),
	                                        associacio.getGranteeGroupDomain() != null ? associacio
	                                                .getGranteeGroupDomain().getName()
	                                                : null,
	                                        associacio.getGranteeApplicationDomain() != null ? associacio
	                                                .getGranteeApplicationDomain().getName()
	                                                : null,
	                                        associacio.getGranteeApplicationDomain() != null ? associacio
	                                                .getGranteeApplicationDomain()
	                                                .getId() : null);
	                    } else {// Cerquem a tots els valors de domini (sense_domini
	                            // o qualque_valor)
	                        rolsUsuarisRolContenidor = getRoleAccountEntityDao()
	                                .findByRoleAndDomainType(
	                                        rolContenidor.getName(),
	                                        rolContenidor.getSystem()
	                                                .getName(),
	                                        rolContenidor.getInformationSystem().getName(),
	                                        rolContenidor.getDomainType());
	                    }
	
	                    // Guardem el codi d'usuari (per propagar-los)
	                    if (rolsUsuarisRolContenidor != null)
	                        for (Iterator ruit = rolsUsuarisRolContenidor
	                                .iterator(); ruit.hasNext();) {
	                            RoleAccountEntity rui = (RoleAccountEntity) ruit
	                                    .next();
	                            if (rui.getAccount().getType().equals(AccountType.USER) &&
	                            		rui.getAccount().getUsers().size() == 1)
	                            	usuarisPropagar.add(rui.getAccount().getUsers().iterator().next().getUser());
	                            else
	                            	accountsPropagar.add(rui.getAccount());
	                        }

					}
				}
		}

		// Obtenemos las relaciones con GRUPOS
		// Buscamos en la base de datos (cas d'update, abans de fer els canvis)
		Collection<RoleGroupEntity> grupsPosseidors = null;
		if (cercaRolABaseDades)
			grupsPosseidors = getRoleGroupEntityDao()
					.findOwnerGroupsByRole(Role);
		else
			grupsPosseidors = Role.getContainerGroups();

		for (Iterator<RoleGroupEntity> it = grupsPosseidors.iterator(); it
				.hasNext();) {
			RoleGroupEntity rolsgrup = it.next();
			GroupEntity grupPosseidor = getGroupEntityDao().load(
					rolsgrup.getGroup().getId());
			grupsPropagar.add(grupPosseidor);
			Collection<GroupEntity> subgrups = getTotsFillsGrup(grupPosseidor);
			if (subgrups != null)
				grupsPropagar.addAll(subgrups);
			Collection<UserEntity> usuGrupIsubGrups = findCodiUsuarisPertanyenGrups(grupsPropagar);
			usuarisPropagar.addAll(usuGrupIsubGrups);
		}

	}

	private void propagarUsuarisRolsIGrups(
			Collection<UserEntity> usuarisPropagar,
			Collection<com.soffid.iam.model.AccountEntity> accountsPropagar,
			Collection<RoleEntity> rolsPropagar,
			Collection<GroupEntity> grupsPropagar)
			throws InternalErrorException {

		// Herencia:
		// Role: Atorgació del Role (aquest Role) a un altre Role (contenidor) : hem
		// de fer
		// updateRole(contenidor) i
		// updateUser(per_a_tot_usuari_ROL_contenidor)
		// GRUP: atorgació del Role (aquest Role) a un grup: hem de fer
		// updateUser(per_a_tot_usuari_GRUP_i_SUBGRUPS_del_GRUP_posseidor)
		// updateGrup(grup_posseidor_i_SUBGRUPS)
		// Els usuaris ho fem només una vegada

		// Creem les tasques

		// 1) Propaguem els rols
		if (rolsPropagar != null)
			for (Iterator<RoleEntity> it = rolsPropagar.iterator(); it
					.hasNext();) {
				RoleEntity role = it.next();
				Task updateRole = new Task();
				updateRole.setTransaction("UpdateRole");
				updateRole.setTaskDate(Calendar.getInstance());
				updateRole.setStatus("P");
				updateRole.setRole(role.getName());
				updateRole.setDatabase(role.getSystem().getName());
				updateRole.setSystemName(role.getSystem().getName());
				TaskEntity tasca = getTaskEntityDao().taskToEntity(updateRole);
				getTaskEntityDao().createNoFlush(tasca);
			}

		// 2) Propaguem els usuaris
		if (usuarisPropagar != null)
			for (Iterator<UserEntity> it = usuarisPropagar.iterator(); it
					.hasNext();) {
				UserEntity usu = it.next();
				Task updateUser = new Task();
				updateUser.setTransaction("UpdateUser");
				updateUser.setTaskDate(Calendar.getInstance());
				updateUser.setUser(usu.getUserName());
				updateUser.setStatus("P");
				TaskEntity tasca = getTaskEntityDao().taskToEntity(updateUser);
				getTaskEntityDao().createNoFlush(tasca);
			}

		// 3) Propaguem els accounts
		if (accountsPropagar != null)
			for (Iterator<com.soffid.iam.model.AccountEntity> it = accountsPropagar
					.iterator(); it.hasNext();) {
				com.soffid.iam.model.AccountEntity acc = it.next();
				Task updateAccount = new Task();
				updateAccount.setTransaction(TaskHandler.UPDATE_ACCOUNT);
				updateAccount.setTaskDate(Calendar.getInstance());
				updateAccount.setStatus("P");
				updateAccount.setUser(acc.getName());
				updateAccount.setDatabase(acc.getSystem().getName());
				updateAccount.setSystemName(acc.getSystem().getName());
				TaskEntity tasca = getTaskEntityDao().taskToEntity(
						updateAccount);
				getTaskEntityDao().createNoFlush(tasca);
			}
		// 4) Propaguem els grups
		if (grupsPropagar != null)
			for (Iterator<GroupEntity> it = grupsPropagar.iterator(); it
					.hasNext();) {
				GroupEntity grup = it.next();
				Task updateGrup = new Task();
				updateGrup.setTransaction("UpdateGroup");
				updateGrup.setTaskDate(Calendar.getInstance());
				updateGrup.setStatus("P");
				updateGrup.setGroup(grup.getName());
				TaskEntity tasca = getTaskEntityDao().taskToEntity(updateGrup);
				getTaskEntityDao().createNoFlush(tasca);
			}
	}

	public void create(Collection entities) {
		if (entities != null)
			for (Iterator it = entities.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof RoleEntity) {
					RoleEntity entity = (RoleEntity) obj;
					this.create(entity);
				}
			}
	}

	public void update(Collection entities) {
		if (entities != null)
			for (Iterator it = entities.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof RoleEntity) {
					RoleEntity entity = (RoleEntity) obj;
					this.update(entity);
				}
			}
	}

	public void remove(Collection entities) {
		if (entities != null)
			for (Iterator it = entities.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof RoleEntity) {
					RoleEntity entity = (RoleEntity) obj;
					this.remove(entity);
				}
			}
	}

	@Override
	protected void handleRemove(Role role) throws Exception {
        RoleEntity entity = load (role.getId());
        remove(entity);

	}

    private boolean checkNoCycles(
            RoleGrant grant, StringBuffer cami, boolean checkGranteeCycles) {
        RoleEntity contingut = load ( grant.getRoleId());
        RoleEntity pare = load (grant.getOwnerRole());

        if (grant.getRoleId().equals(grant.getOwnerRole()))
        {
        	cami.append(contingut.getName())
        		.append(" <=> ")
        		.append(contingut.getName()); //$NON-NLS-1$
       		return false;
        }
        	
        // Método: Para todo T,D / T & D son RoleEntity
        // no existe C(D,D1): D está contenido en D1 (contenedor) tal que
        // (versión breve)
        // exista un camino C(D1, T): D1 está contenido en T
        //
        // Obtenemos dónde está contenido el padre (el contenedor del Role)
        // return true;
        if (checkGranteeCycles)
        {
        	cami.append(contingut.getName() + " <= "); //$NON-NLS-1$
        	return checkNoCycles(contingut, pare, cami, checkGranteeCycles);
        }
        else
        {
        	cami.append(contingut.getName() + " => ");
        	return checkNoCycles(pare, contingut, cami, checkGranteeCycles);
        }
    }

    Log log = LogFactory.getLog(getClass());
    
    private boolean checkNoCycles(RoleEntity sourceRole,
            RoleEntity currentRole, StringBuffer cami, boolean checkGranteeCycles) {
        Collection<RoleDependencyEntity> nextGrants = checkGranteeCycles ? currentRole.getContainerRoles() : currentRole.getContainedRoles();
        boolean senseCicles = true;
        cami.append(currentRole.getName());
        if (checkGranteeCycles) cami.append(" <= "); //$NON-NLS-1$
        else cami.append(" => "); //$NON-NLS-1$
        int len = cami.length();
        log.info("Checking cycle "+cami.toString());
        for (Iterator<RoleDependencyEntity> it = nextGrants.iterator(); senseCicles && it.hasNext();) {
        	cami.setLength(len);
        	
            RoleDependencyEntity relacio = it.next();
            RoleEntity nextRole = checkGranteeCycles ? relacio.getContainer(): relacio.getContained();
            if (nextRole.equals(sourceRole)) {
                cami.append(nextRole.getName());
                senseCicles = false;
            } else {
                // Verificamos la descendencia del contenedor (padre)
                senseCicles = checkNoCycles(sourceRole, nextRole,
                        cami, checkGranteeCycles);
            }
        }
        return senseCicles;
    }


	@Override
	protected RoleEntity handleCreate(Role role, boolean updateOwnedRoles) throws Exception {
        try {
        	RoleEntity entity = newRoleEntity();
           	roleToEntity(role, entity, true);
            
            // 0) Obtenim els usuaris, grups i rols afectats abans del canvi
            HashSet<UserEntity> usuarisPropagar = new HashSet<UserEntity>();
            HashSet<AccountEntity> accountsPropagar = new HashSet<AccountEntity>();
            HashSet<RoleEntity> rolsPropagar = new HashSet<RoleEntity>();
            HashSet<GroupEntity> grupsPropagar = new HashSet<GroupEntity>();
            // Obtenim informació del Role abans de fer l'update (darrer
            // paràmetre a true)

            // Actualitzem el Role a la base de dades
            create (entity);
            
            
            if (role.getOwnedRoles() != null)
	            for (RoleGrant rg: role.getOwnedRoles())
	            	rg.setOwnerRole(entity.getId());
            
            if (role.getOwnerRoles() != null)
            	for (RoleGrant rg: role.getOwnerRoles())
            		rg.setRoleId(entity.getId());

            updateGranteeRoles(role, entity);

            if (updateOwnedRoles)
            	updateGrantedRoles(role, entity);

            updateGranteeGroups(role, entity);

            getSession(false).flush();
            auditarRol("C", entity.getName(), entity.getInformationSystem().getName(), entity //$NON-NLS-1$
                    .getSystem().getName());

            // Obtenim el Role una vegada s'hagi actualitzat (conté els afectats
            // abans del canvi)
            HashSet<UserEntity> usuarisPropagarAfter = new HashSet<UserEntity>();
            HashSet<AccountEntity> accountsPropagarAfter = new HashSet<AccountEntity>();
            HashSet<RoleEntity> rolsPropagarAfter = new HashSet<RoleEntity>();
            HashSet<GroupEntity> grupsPropagarAfter = new HashSet<GroupEntity>();

            getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagarAfter,
                    accountsPropagarAfter, rolsPropagarAfter, grupsPropagarAfter, false);

            generatePropagationTasks(usuarisPropagar, accountsPropagar,
					rolsPropagar, grupsPropagar, usuarisPropagarAfter,
					accountsPropagarAfter, rolsPropagarAfter,
					grupsPropagarAfter);

            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_ROLE);
            tasque.setRole(entity.getName());
            tasque.setSystemName(entity.getSystem().getName());
            tasque.setDb(entity.getSystem().getName());
            getTaskEntityDao().createNoFlush(tasque);

            getSession(false).flush();

            updateMailLists (entity);

            return entity;
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.2"), role.getName(), message));  //$NON-NLS-1$
        }
	}

	@Override
	protected RoleEntity handleUpdate(Role role, boolean updateOwnedRoles) throws Exception {
        try {
            RoleEntity entity = load (role.getId());
            if (entity == null)
            	throw new UnknownRoleException("id: "+role.getId());

            String oldName = entity.getName();
            String oldSystem = entity.getSystem().getName();
            boolean sameName = role.getName().equals ( oldName ) &&
            		role.getSystem().equals (oldSystem);
            	
           	roleToEntity(role, entity, true);
            
            // 0) Obtenim els usuaris, grups i rols afectats abans del canvi
            HashSet<UserEntity> usuarisPropagar = new HashSet<UserEntity>();
            HashSet<AccountEntity> accountsPropagar = new HashSet<AccountEntity>();
            HashSet<RoleEntity> rolsPropagar = new HashSet<RoleEntity>();
            HashSet<GroupEntity> grupsPropagar = new HashSet<GroupEntity>();
            // Obtenim informació del Role abans de fer l'update (darrer
            // paràmetre a true)
            getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagar, accountsPropagar,
                    rolsPropagar, grupsPropagar, true);

            // Actualitzem el Role a la base de dades
            update (entity);
            
            updateGranteeRoles(role, entity);

            boolean forcePropagation = false;
            if (updateOwnedRoles)
            	forcePropagation = updateGrantedRoles(role, entity);

            updateGranteeGroups(role, entity);
            
            getSession(false).flush();
            auditarRol("U", entity.getName(), entity.getInformationSystem().getName(), entity //$NON-NLS-1$
                    .getSystem().getName());

            // Obtenim el Role una vegada s'hagi actualitzat (conté els afectats
            // abans del canvi)
            HashSet<UserEntity> usuarisPropagarAfter = new HashSet<UserEntity>();
            HashSet<AccountEntity> accountsPropagarAfter = new HashSet<AccountEntity>();
            HashSet<RoleEntity> rolsPropagarAfter = new HashSet<RoleEntity>();
            HashSet<GroupEntity> grupsPropagarAfter = new HashSet<GroupEntity>();

            getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagarAfter,
                    accountsPropagarAfter, rolsPropagarAfter, grupsPropagarAfter, false);

            if (forcePropagation)
            {
            	usuarisPropagarAfter.addAll(usuarisPropagar);
            	accountsPropagarAfter.addAll(accountsPropagar);
                usuarisPropagar.clear();
                accountsPropagar.clear();
            }
            	
            generatePropagationTasks(usuarisPropagar, accountsPropagar,
					rolsPropagar, grupsPropagar, usuarisPropagarAfter,
					accountsPropagarAfter, rolsPropagarAfter,
					grupsPropagarAfter);

            TaskEntity tasque = getTaskEntityDao().newTaskEntity();
            tasque.setDate(new Timestamp(System.currentTimeMillis()));
            tasque.setTransaction(TaskHandler.UPDATE_ROLE);
            tasque.setRole(entity.getName());
            tasque.setSystemName(entity.getSystem().getName());
            tasque.setDb(entity.getSystem().getName());
            getTaskEntityDao().createNoFlush(tasque);
            if (! sameName)
            {
                tasque = getTaskEntityDao().newTaskEntity();
                tasque.setDate(new Timestamp(System.currentTimeMillis()));
                tasque.setTransaction(TaskHandler.UPDATE_ROLE);
                tasque.setRole(oldName);
                tasque.setSystemName(oldSystem);
                tasque.setDb(oldSystem);
                getTaskEntityDao().createNoFlush(tasque);
            }
            getSession(false).flush();

            updateMailLists (entity);
            return entity;
        } catch (Throwable e) {
            String message = ExceptionTranslator.translate(e);
            LogFactory.getLog(getClass()).warn("Error updating role: ", e);
			throw new SeyconException(String.format(Messages.getString("RoleEntityDaoImpl.2"), role.getName(), e));  //$NON-NLS-1$
        }
	}

	@Override
	protected void handleCommitDefinition(RoleEntity entity) throws Exception {
        // Calculate current users
        HashSet<UserEntity> usuarisPropagar = new HashSet<UserEntity>();
        HashSet<AccountEntity> accountsPropagar = new HashSet<AccountEntity>();
        HashSet<RoleEntity> rolsPropagar = new HashSet<RoleEntity>();
        HashSet<GroupEntity> grupsPropagar = new HashSet<GroupEntity>();
        getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagar, accountsPropagar,
                rolsPropagar, grupsPropagar, true);

        // Commit changes
        for ( RoleDependencyEntity grant: entity.getContainedRoles())
    	{
    		if ( RoleDependencyStatus.STATUS_TOAPPROVE.equals(grant.getStatus() ))
    		{
    			grant.setStatus(RoleDependencyStatus.STATUS_ACTIVE);
    			getRoleDependencyEntityDao().update(grant);
    		}
    		if ( RoleDependencyStatus.STATUS_TOREMOVE.equals(grant.getStatus() ))
    		{
    			getRoleDependencyEntityDao().remove(grant);
    		}
    	}

        // Calculate new users
        HashSet<UserEntity> usuarisPropagarAfter = new HashSet<UserEntity>();
        HashSet<AccountEntity> accountsPropagarAfter = new HashSet<AccountEntity>();
        HashSet<RoleEntity> rolsPropagarAfter = new HashSet<RoleEntity>();
        HashSet<GroupEntity> grupsPropagarAfter = new HashSet<GroupEntity>();

        // Generate propagation tasks
        getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagarAfter,
                accountsPropagarAfter, rolsPropagarAfter, grupsPropagarAfter, false);

        generatePropagationTasks(usuarisPropagar, accountsPropagar,
				rolsPropagar, grupsPropagar, usuarisPropagarAfter,
				accountsPropagarAfter, rolsPropagarAfter,
				grupsPropagarAfter);

        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ROLE);
        tasque.setRole(entity.getName());
        tasque.setSystemName(entity.getSystem().getName());
        tasque.setDb(entity.getSystem().getName());
        getTaskEntityDao().createNoFlush(tasque);

        entity.setApprovalEnd(new java.util.Date());
        entity.setApprovalProcess(null);
	}

	@Override
	protected void handleRollbackDefinition(RoleEntity entity) throws Exception {
        // Calculate current users
        HashSet<UserEntity> usuarisPropagar = new HashSet<UserEntity>();
        HashSet<AccountEntity> accountsPropagar = new HashSet<AccountEntity>();
        HashSet<RoleEntity> rolsPropagar = new HashSet<RoleEntity>();
        HashSet<GroupEntity> grupsPropagar = new HashSet<GroupEntity>();
        getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagar, accountsPropagar,
                rolsPropagar, grupsPropagar, true);

        // Commit changes
        for ( RoleDependencyEntity grant: entity.getContainedRoles())
    	{
    		if ( RoleDependencyStatus.STATUS_TOREMOVE.equals(grant.getStatus() ))
    		{
    			grant.setStatus(RoleDependencyStatus.STATUS_ACTIVE);
    			getRoleDependencyEntityDao().update(grant);
    		}
    		if ( RoleDependencyStatus.STATUS_TOAPPROVE.equals(grant.getStatus() ))
    		{
    			getRoleDependencyEntityDao().remove(grant);
    		}
    	}

        // Calculate new users
        HashSet<UserEntity> usuarisPropagarAfter = new HashSet<UserEntity>();
        HashSet<AccountEntity> accountsPropagarAfter = new HashSet<AccountEntity>();
        HashSet<RoleEntity> rolsPropagarAfter = new HashSet<RoleEntity>();
        HashSet<GroupEntity> grupsPropagarAfter = new HashSet<GroupEntity>();

        // Generate propagation tasks
        getHerenciaRol_Usuaris_Rols_Grups(entity, usuarisPropagarAfter,
                accountsPropagarAfter, rolsPropagarAfter, grupsPropagarAfter, false);

        generatePropagationTasks(usuarisPropagar, accountsPropagar,
				rolsPropagar, grupsPropagar, usuarisPropagarAfter,
				accountsPropagarAfter, rolsPropagarAfter,
				grupsPropagarAfter);

        TaskEntity tasque = getTaskEntityDao().newTaskEntity();
        tasque.setDate(new Timestamp(System.currentTimeMillis()));
        tasque.setTransaction(TaskHandler.UPDATE_ROLE);
        tasque.setRole(entity.getName());
        tasque.setSystemName(entity.getSystem().getName());
        tasque.setDb(entity.getSystem().getName());
        getTaskEntityDao().createNoFlush(tasque);

        entity.setApprovalEnd(new java.util.Date());
        entity.setApprovalProcess(null);
	}

	@Override
	public Collection<RoleEntity> findByText(CriteriaSearchConfiguration criteria, String text) {
		String[] split = ScimHelper.split(text);
		Parameter[] params = new Parameter[split.length + 1];
		
		StringBuffer sb = new StringBuffer("select u "
				+ "from com.soffid.iam.model.RoleEntity as u "
				+ "where u.system.tenant.id = :tenantId");
		params[0] = new Parameter("tenantId", Security.getCurrentTenantId());
		for (int i = 0; i < split.length; i++)
		{
			sb.append(" and ");
			params[i+1] = new Parameter("param"+i, "%"+split[i].toUpperCase()+"%");
			sb.append("(upper(u.name) like :param")
				.append(i)
				.append(" or upper(u.description) like :param")
				.append(i)
				.append(" or upper(u.system.name) like :param")
				.append(i)
				.append(")");
		}
		return query(sb.toString(), params);
	}

	@Override
	public RoleEntity findByShortName(CriteriaSearchConfiguration criteria, String shortName) {
		int i = shortName.lastIndexOf('@');
		if (i < 0)
			return null;
		
		String name = shortName.substring(0, i);
		String system = shortName.substring(i+1);
		return findByNameAndSystem(criteria, name, system);
	}
}
