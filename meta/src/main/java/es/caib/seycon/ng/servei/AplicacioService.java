//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.servei;

import java.util.Collection;

import org.springframework.transaction.annotation.Transactional;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.model.ApplicationAttributeEntity;
import com.soffid.iam.model.RoleAttributeEntity;
import com.soffid.iam.service.AsyncRunnerService;
import com.soffid.iam.service.EntitlementDelegationService;
import com.soffid.iam.service.impl.AttributeValidationService;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.bpm.servei.BpmEngine;
import es.caib.seycon.ng.comu.Domini;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.ValorDomini;
import es.caib.seycon.ng.model.TipusDadaEntity;
import es.caib.seycon.ng.model.UserAccountEntity;
import es.caib.seycon.ng.model.ValorDominiAplicacioEntity;

@Service(translatedName = "ApplicationService", translatedPackage = "com.soffid.iam.service")
@Depends({
		// / Entities
		es.caib.seycon.ng.model.NotificacioEntity.class,
		es.caib.seycon.ng.model.UsuariEntity.class,
		es.caib.seycon.ng.model.RolAccountEntity.class,
		es.caib.seycon.ng.model.GrupEntity.class,
		es.caib.seycon.ng.model.RolAssociacioRolEntity.class,
		es.caib.seycon.ng.model.TasqueEntity.class,
		es.caib.seycon.ng.model.DispatcherEntity.class,
		es.caib.seycon.ng.model.AccountEntity.class,
		es.caib.seycon.ng.model.RolsGrupEntity.class,
		es.caib.seycon.ng.model.AutoritzacioPUERolEntity.class,
		es.caib.seycon.ng.model.XarxaACEntity.class,
		es.caib.seycon.ng.model.AutoritzacioRolEntity.class,
		es.caib.seycon.ng.model.AplicacioEntity.class,
		es.caib.seycon.ng.model.RolEntity.class,
		UserAccountEntity.class,
		ValorDominiAplicacioEntity.class,
		// Services
		es.caib.seycon.ng.servei.UsuariService.class,
		es.caib.seycon.ng.servei.GrupService.class,
		es.caib.seycon.ng.servei.AccountService.class,
		com.soffid.iam.service.RuleEvaluatorService.class,
		es.caib.seycon.ng.servei.SoDRuleService.class,
		es.caib.seycon.ng.servei.AutoritzacioService.class, BpmEngine.class,
	ApplicationAttributeEntity.class,
	TipusDadaEntity.class,
	RoleAttributeEntity.class,
	EntitlementDelegationService.class,
	AttributeValidationService.class,
	AsyncRunnerService.class
	}
)
public abstract class AplicacioService {

	@Operation(grantees = { roles.application_query.class }, translated = "getApplications")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Aplicacio> getAplicacions()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_create.class }, translated = "create")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Aplicacio create(
			es.caib.seycon.ng.comu.Aplicacio aplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_delete.class }, translated = "delete")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void delete(es.caib.seycon.ng.comu.Aplicacio aplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(grantees = { roles.application_update.class }, translated = "update")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void update(es.caib.seycon.ng.comu.Aplicacio aplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findApplicationByApplicationName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Aplicacio findAplicacioByCodiAplicacio(
			java.lang.String codiAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findApplicationByCriteria")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Aplicacio> findAplicacioByCriteri(
			@Nullable java.lang.String codi, @Nullable java.lang.String nom,
			@Nullable java.lang.String directoriFonts,
			@Nullable java.lang.String responsable,
			@Nullable java.lang.String directoriExecutable,
			@Nullable java.lang.String bd, @Nullable java.lang.String rol,
			@Nullable java.lang.String gestionableWF)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class, roles.Tothom.class }, translated = "findRolesByApplicationName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRolsByCodiAplicacio(
			java.lang.String codiAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_create.class,
			roles.application_update.class }, translated = "create")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.AdministracioAplicacio create(
			es.caib.seycon.ng.comu.AdministracioAplicacio administracioAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_create.class,
			roles.application_update.class, roles.application_delete.class }, translated = "manageApplication")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void delete(
			es.caib.seycon.ng.comu.AdministracioAplicacio administracioAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findApplicationManageByApplicationName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.AdministracioAplicacio> findAdministracioAplicacioByCodiAplicacio(
			java.lang.String codiAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Finds the management roles for any information system")
	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.RolAccount> findApplicationManagers(
			java.lang.String informationSystem, String roleName)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Finds the managers with a set management role for an application")
	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findApplicationManagementRoles()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	
	@Description("Finds the management roles for any group")
	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.RolAccount> findGroupManagers(
			java.lang.String group, String roleName)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Finds the managers with a set management role for a group")
	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findGroupManagementRoles()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findRolesByUserName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRolsByCodiUsuari(
			java.lang.String codiUsuari)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "getRoles")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> getRols()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findUsersByRoleNameAndRoleApplicationNameAndDispatcherName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Usuari> findUsuarisByNomRolAndCodiAplicacioRolAndCodiDispatcher(
			java.lang.String nomRole, java.lang.String codiAplicacioRol,
			java.lang.String codiDispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.role_query.class }, translated = "findRolesByFilter")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRolsByFiltre(
			@Nullable java.lang.String nom,
			@Nullable java.lang.String descripcio,
			@Nullable java.lang.String defecte,
			@Nullable java.lang.String baseDeDades,
			@Nullable java.lang.String contrasenya,
			@Nullable java.lang.String codiAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findRoleByRoleNameAndApplicationNameAndDispatcherName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Rol findRolByNomRolAndCodiAplicacioAndCodiDispatcher(
			java.lang.String nomRol, java.lang.String codiAplicacio,
			java.lang.String codiDispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Rol findRoleByNameAndSystem(
			java.lang.String name, java.lang.String system)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_create.class,
			roles.application_update.class }, translated = "create")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	@Description ("This method does NOT add grants made to the new role")
	public es.caib.seycon.ng.comu.Rol create(es.caib.seycon.ng.comu.Rol rol)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation ( grantees={roles.application_create.class,
			roles.application_update.class},
			translated="create2")
	@Transactional(rollbackFor={java.lang.Exception.class})
	@Description ("This method does add grants made to the new role")
	public es.caib.seycon.ng.comu.Rol create2(
		es.caib.seycon.ng.comu.Rol rol)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation(grantees = { roles.application_create.class,
			roles.application_update.class, roles.application_delete.class }, translated = "delete")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void delete(es.caib.seycon.ng.comu.Rol rol)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(grantees = { roles.application_update.class }, translated = "update")
	@Description("Updates role, including grantee roles and groups, but not roles granted to this one")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Rol update(es.caib.seycon.ng.comu.Rol rol)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation ( 
			grantees={roles.application_update.class},
			translated="update2")
	@Description("Updates role, including roles granted to this role, and role and group grantee")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Rol update2(
		es.caib.seycon.ng.comu.Rol rol)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( 
			grantees={roles.application_update.class})
	@Description("Makes role dependencies persistent")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Rol approveRoleDefinition(
		es.caib.seycon.ng.comu.Rol rol)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation ( 
			grantees={roles.application_update.class})
	@Description("Makes role dependencies persistent")
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Rol denyRoleDefinition(
		es.caib.seycon.ng.comu.Rol rol)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation(grantees = { roles.user_role_create.class }, translated = "create")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.RolAccount create(
			es.caib.seycon.ng.comu.RolAccount rolsUsuaris)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.user_role_delete.class,
			roles.application_delete.class }, translated = "delete")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void delete(es.caib.seycon.ng.comu.RolAccount rolsUsuaris)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation()
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void deleteByRuleEvaluation(es.caib.seycon.ng.comu.RolAccount rolsUsuaris)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void denyApproval(es.caib.seycon.ng.comu.RolAccount rolsUsuaris)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void approveDelete(es.caib.seycon.ng.comu.RolAccount rolsUsuaris)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void denyDelete(es.caib.seycon.ng.comu.RolAccount rolsUsuaris)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(translated = "update")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.RolAccount update(
			es.caib.seycon.ng.comu.RolAccount rolsUsuaris)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Enables or disable a rolAccount based on the start and end dates")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.RolAccount enableOrDisableOnDates(
			es.caib.seycon.ng.comu.RolAccount rolsUsuaris)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Description("Enables or disable any rolAccount based on the start and end dates")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void enableOrDisableAllOnDates()
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findRolesByDomainNameAndApplicationName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRolsByNomDominiAndCodiAplicacio(
			java.lang.String nomDomini, java.lang.String codiAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_update.class }, translated = "update")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.AdministracioAplicacio update(
			es.caib.seycon.ng.comu.AdministracioAplicacio administracioAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class,
			roles.authorization_query.class }, translated = "findRoleById")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Rol findRolById(java.lang.Long rolId)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findRoleHoldersGroupsByRole")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Grup> findGrupsPosseidorsdelRolByRol(
			es.caib.seycon.ng.comu.Rol rol)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findGrantedRolesToGroupByGroup")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRolsOtorgatsalGrupByGrup(
			es.caib.seycon.ng.comu.Grup grup)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class,
			roles.user_role_query.class }, translated = "findTextualInformationAndUserRolesHierachyByUserName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.ContenidorRol> findInformacioTextualJerarquiaRolsUsuariByCodiUsuari(
			java.lang.String codiUsuari)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findApplicationByApplicationNameUnrestricted")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public es.caib.seycon.ng.comu.Aplicacio findAplicacioByCodiAplicacioSenseRestriccions(
			java.lang.String codiAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(translated = "findApplicationByCriteriaUnrestricted")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Aplicacio> findAplicacioByCriteriSenseRestriccions(
			@Nullable java.lang.String codi, @Nullable java.lang.String nom,
			@Nullable java.lang.String directoriFonts,
			@Nullable java.lang.String responsable,
			@Nullable java.lang.String directoriExecutable,
			@Nullable java.lang.String bd, @Nullable java.lang.String rol,
			@Nullable java.lang.String gestionableWF)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findRolesByApplicationNameUnrestricted")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRolsByCodiAplicacioSenseRestriccions(
			java.lang.String codiAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findUserRolesByRoleNameAndRoleApplicationNameAndDispatcherName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.RolAccount> findRolsUsuariByNomRolAndCodiAplicacioRolAndCodiDispatcher(
			java.lang.String nomRole, java.lang.String codiAplicacioRol,
			java.lang.String codiDispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class, roles.Tothom.class }, translated = "findRolesByFilter")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRolsByFiltre(
			@Nullable java.lang.String nom,
			@Nullable java.lang.String descripcio,
			@Nullable java.lang.String defecte,
			@Nullable java.lang.String baseDeDades,
			@Nullable java.lang.String contrasenya,
			@Nullable java.lang.String codiAplicacio,
			@Nullable java.lang.String gestionableWF)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findTextualInformationAndRolesHierachyByApplicationRoleAndDispatcher")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.ContenidorRol> findInformacioTextualJerarquiaRolsByRolAplicacioAndDispatcher(
			java.lang.String nomRole, java.lang.String codiAplicacioRol,
			java.lang.String codiDispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.user_role_query.class,
			roles.application_query.class }, translated = "findTextualInformationAndUserRolesHierachyByUserName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.ContenidorRol> findInformacioTextualJerarquiaRolsUsuariByCodiUsuari(
			java.lang.String codiUsuari, java.lang.String filtraResultats)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(translated = "getPendingAlerts")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<java.lang.Object> getNotificacionsPendents(
			@Nullable java.lang.String codiAplicacio)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(translated = "removeSentAlerts")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void deleteNotificacionsEnviades(
			@Nullable java.lang.String codiAplicacio, java.util.Date dataDelete)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findRoleGrantByRole")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> findRolGrantByRol(
			java.lang.Long rolId, @Nullable java.lang.Long numRegistres)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findRoleAuthorizationsByRoleNameAndApplicationNameAndDispatcherName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.AutoritzacioRol> findAutoritzacionsRolByNomRolAndCodiAplicacioRolAndCodiDispatcher(
			java.lang.String nomRole, java.lang.String codiAplicacioRol,
			java.lang.String codiDispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findApplicationAccessTreeRolesByRoleNameAndRoleApplicationNameAndDispatcherName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.AutoritzacioPuntEntrada> findPuntsEntradaRolByNomRolAndCodiAplicacioRolAndCodiDispatcher(
			java.lang.String nomRole, java.lang.String codiAplicacioRol,
			java.lang.String codiDispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findNetworkACLRolesByRoleNameAndApplicationNameAndDispatcherName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.NetworkAuthorization> findACLsXarxesRolByNomRolAndCodiAplicacioRolAndCodiDispatcher(
			java.lang.String nomRole, java.lang.String codiAplicacioRol,
			java.lang.String codiDispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findRoleGrantByAccount")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> findRolGrantByAccount(
			java.lang.Long accountId)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findRoleAccountByAccount")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.RolAccount> findRolAccountByAccount(
			long accountId)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findEffectiveRoleGrantByUser")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> findEffectiveRolGrantByUser(
			long userId)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findEffectiveRoleGrantByUserAndHolderGroup")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> findEffectiveRolGrantByUserAndHolderGroup(
			long userId, long groupId)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class }, translated = "findEffectiveRoleGrantByAccount")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> findEffectiveRolGrantByAccount(
			long accountId)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(translated = "findEffectiveRoleGrantsByRoleId")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.RolGrant> findEffectiveRolGrantsByRolId(
			java.lang.Long rolId)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class,
			roles.user_query.class }, translated = "findUserRolesByUserName")
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.RolAccount> findRolsUsuarisByCodiUsuari(
			java.lang.String codiUsuari)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class,
			roles.user_query.class },
			translated = "findUserRolesByInformationSystem")
	public java.util.Collection<es.caib.seycon.ng.comu.RolAccount> findRolsUsuarisByInformationSystem(
			java.lang.String informationSystem)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void revokeRolesHoldedOnGroup(long userId, long groupId) {
	}

	@Operation(grantees = { roles.role_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRoleByJsonQuery(
			@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.role_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Rol> findRoleByText(
			@Nullable String text)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.role_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<es.caib.seycon.ng.comu.Rol> findRoleByTextAsync(
			@Nullable String text)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Aplicacio> findApplicationByJsonQuery(
			@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<es.caib.seycon.ng.comu.Aplicacio> findApplicationByJsonQueryAsync(
			@Nullable String query)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.Aplicacio> findApplicationByText(
			@Nullable String text)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.application_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<es.caib.seycon.ng.comu.Aplicacio> findApplicationByTextAsync(
			@Nullable String text)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.role_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Collection<es.caib.seycon.ng.comu.ValorDomini> findDomainValueByText(
			Domini domain,
			@Nullable String text)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation(grantees = { roles.role_query.class })
	@Transactional(rollbackFor = { java.lang.Exception.class })
	public AsyncList<es.caib.seycon.ng.comu.ValorDomini> findDomainValueByTextAsync(
			Domini domain,
			@Nullable String text)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Operation (grantees={roles.application_update.class})
	@Description("Generates a report to view the changes that a role change will generat")
	@Transactional(readOnly=true, noRollbackFor={java.lang.Exception.class})
	public String generateChangesReport(Rol rol)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}

	@Operation
	@Transactional(readOnly=true)
	protected Collection<String> findRoleNames(String systemName) throws Exception { return null; }

}
