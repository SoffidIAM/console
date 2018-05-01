//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Date;

import com.soffid.mda.annotation.*;

import es.caib.bpm.servei.BpmEngine;
import es.caib.seycon.ng.comu.Rol;

@Entity(table = "SC_ROLES", tenantFilter="system.tenant.id",
	translatedName = "RoleEntity", translatedPackage = "com.soffid.iam.model")
@Depends({ es.caib.seycon.ng.model.AplicacioEntity.class,
		es.caib.seycon.ng.model.ValorDominiAplicacioEntity.class,
		es.caib.seycon.ng.model.GrupEntity.class,
		es.caib.seycon.ng.model.UsuariEntity.class,
		es.caib.seycon.ng.model.DispatcherEntity.class,
		es.caib.seycon.ng.comu.Identitat.class,
		es.caib.seycon.ng.model.DominiAplicacioEntity.class,
		es.caib.seycon.ng.model.AuditoriaEntity.class,
		es.caib.seycon.ng.comu.Rol.class,
		es.caib.seycon.ng.comu.ContenidorRol.class,
		es.caib.seycon.ng.model.RolAssociacioRolEntity.class,
		es.caib.seycon.ng.model.TasqueEntity.class,
		es.caib.seycon.ng.model.RolsGrupEntity.class,
		es.caib.seycon.ng.model.RolAccountEntity.class,
		es.caib.seycon.ng.model.XarxaACEntity.class,
		es.caib.seycon.ng.model.ControlAccessEntity.class,
		es.caib.seycon.ng.model.NotificacioEntity.class,
		es.caib.seycon.ng.model.AutoritzacioRolEntity.class,
		es.caib.seycon.ng.model.AccountAccessEntity.class,
		com.soffid.iam.model.RuleAssignedRoleEntity.class,
	    BpmEngine.class,
		es.caib.seycon.ng.model.SoDRoleEntity.class, LlistaCorreuEntity.class })
public abstract class RolEntity {

	@Column(name = "ROL_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "ROL_NOM", length = 150, translated = "name")
	public java.lang.String nom;

	@Nullable
	@Column (name="ROL_CATEGORY", length=150)
	public java.lang.String category;

	@Column (name="ROL_DESCRI", length=150, translated="description")
	public java.lang.String descripcio;

	@Column(name = "ROL_DEFECT", length = 1, translated = "defaultRole")
	public java.lang.String defecte;

	@Column(name = "ROL_CONTRA", length = 1, translated = "password")
	@Nullable
	public java.lang.String contrasenya;

	@Column(name = "ROL_IDAPL", translated = "informationSystem")
	public es.caib.seycon.ng.model.AplicacioEntity aplicacio;

	@ForeignKey(foreignColumn = "RLU_IDROL")
	public java.util.Collection<es.caib.seycon.ng.model.RolAccountEntity> accounts;

	@Column(name = "ROL_IDDISPAT", translated = "system")
	@Nullable
	public es.caib.seycon.ng.model.DispatcherEntity baseDeDades;

	@Column(name = "ROL_DOMAPP", translated = "applicationDomain")
	@Nullable
	public es.caib.seycon.ng.model.DominiAplicacioEntity dominiAplicacio;

	@Column(name = "ROL_TIPDOM", length = 50, translated = "domainType")
	@Nullable
	public java.lang.String tipusDomini;

	@ForeignKey(foreignColumn = "RRL_CONTINGUT", translated = "containerRoles")
	public java.util.Collection<es.caib.seycon.ng.model.RolAssociacioRolEntity> rolAssociacioRolSocContingut;

	@ForeignKey(foreignColumn = "RRL_CONTENIDOR", translated = "containedRoles")
	public java.util.Collection<es.caib.seycon.ng.model.RolAssociacioRolEntity> rolAssociacioRolSocContenidor;

	@ForeignKey(foreignColumn = "RLG_ROL", translated = "containerGroups")
	public java.util.Collection<es.caib.seycon.ng.model.RolsGrupEntity> grupsPosseidorsRol;

	@ForeignKey(foreignColumn = "AXA_IDROL", translated = "networkAuthorization")
	public java.util.Collection<es.caib.seycon.ng.model.XarxaACEntity> autoritzacionsXarxa;

	@ForeignKey(foreignColumn = "CAC_ROL_ID", translated = "accessControl")
	public java.util.Collection<es.caib.seycon.ng.model.ControlAccessEntity> controlAccess;

	@Column(name = "ROL_GEST_WF", length = 1, translated = "manageableWF")
	@Nullable
	public java.lang.String gestionableWF;

	@ForeignKey(foreignColumn = "NTF_ROL", translated = "notificationEntities")
	public java.util.Collection<es.caib.seycon.ng.model.NotificacioEntity> notificacioEntities;

	@ForeignKey(foreignColumn = "AUR_ROL", translated = "authorizations")
	public java.util.Collection<es.caib.seycon.ng.model.AutoritzacioRolEntity> autoritzacions;

	@ForeignKey(foreignColumn = "AAC_ROL_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AccountAccessEntity> accountAccess;

	@ForeignKey(foreignColumn = "RUR_ROL_ID")
	public java.util.Collection<com.soffid.iam.model.RuleAssignedRoleEntity> rules;

	@ForeignKey(foreignColumn = "SOR_ROL_ID")
	public java.util.Collection<es.caib.seycon.ng.model.SoDRoleEntity> sodRules;

	@Description("When an aproval process is needed to enable this rol grants")
	@Column(name="ROL_APRPRO")
	@Nullable
	public Long approvalProcess;

	@Column(name="ROL_APREND")
	@Nullable
	public Date approvalStart;

	@Column(name="ROL_APRSTA")
	@Nullable
	public Date approvalEnd;

	@Operation(translated = "findByInformationSystem")
	@DaoFinder("select role \n"
			+ "from com.soffid.iam.model.RoleEntity role \n"
			+ "where role.informationSystem.name = :informationSystem and "
			+ "      role.informationSystem.tenant.id = :tenantId \n"
			+ "order by role.name, role.system.name")
	public java.util.List<es.caib.seycon.ng.model.RolEntity> findByCodiAplicacio(
			java.lang.String informationSystem) {
		return null;
	}

	@DaoFinder
	public es.caib.seycon.ng.model.RolEntity findById(java.lang.Long id) {
		return null;
	}

	@Operation(translated = "findRoleByNameInformationSystemAndStystem")
	@DaoFinder("select rolEntity \n"
			+ "from com.soffid.iam.model.RoleEntity rolEntity \n"
			+ "where \n"
			+ "rolEntity.informationSystem.name = :informationSystem and\n"
			+ "rolEntity.name = :roleName and "
			+ "rolEntity.system.tenant.id = :tenantId and \n"
			+ "rolEntity.system.name = :system")
	public es.caib.seycon.ng.model.RolEntity findByNomRolAndCodiAplicacioAndCodiDispatcher(
			java.lang.String roleName, java.lang.String informationSystem,
			java.lang.String system) {
		return null;
	}

	@Operation(translated = "findRolesByCriteria")
	@DaoFinder("select rol "
			+ "from com.soffid.iam.model.RoleEntity rol "
			+ "left join rol.system baseDeDades where \n"
			+ "(:roleName is null or rol.name like :roleName) and "
			+ "(:description is null or rol.description like :description) and "
			+ "(:defaultRole is null or rol.defaultRole = :defaultRole) and "
			+ "(:system is null or baseDeDades.name like :system) and "
			+ "(:password is null or rol.password = :password)  and "
			+ "(:informationSystem is null or rol.informationSystem.name like :informationSystem) and\n"
			+ "baseDeDades.tenant.id = :tenantId "
			+ "order by rol.name, baseDeDades.name")
	public java.util.List<es.caib.seycon.ng.model.RolEntity> findRolsByFiltre(
			java.lang.String roleName, java.lang.String description,
			java.lang.String defaultRole, java.lang.String system,
			java.lang.String password, java.lang.String informationSystem) {
		return null;
	}

	@Operation(translated = "findApplicationRolesByUserAndInformationSystem")
	@DaoFinder("select role \n"
			+ "from com.soffid.iam.model.UserEntity user\n"
			+ " join user.accounts as accounts\n "
			+ "join accounts.account as account with account.type='U'\n "
			+ "join account.roles as roles\n "
			+ "join roles.role as role\n "
			+ "join role.informationSystem as informationSystem\n"
			+ "where user.userName = :userName and informationSystem.name = :informationSystem "
			+ "and user.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.RolEntity> getRolsAplicacioByCodiUsuariAndCodiAplicacio(
			java.lang.String userName, java.lang.String informationSystem) {
		return null;
	}

	@Operation(translated = "findRolesByUserName")
	@DaoFinder("select rol \n"
			+ "from com.soffid.iam.model.UserEntity usu\n"
			+ " join usu.accounts as accounts\n"
			+ " join accounts.account as account with account.type='U'\n"
			+ " join account.roles as roles\n"
			+ " join roles.role as rol\n"
			+ "where usu.userName = :userName and usu.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.RolEntity> findRolsByCodiUsuari(
			java.lang.String userName) {
		return null;
	}

	public java.lang.String toString() {
		return null;
	}

	@Operation(translated = "toRoleDescription")
	public java.lang.String toDescripcioRol() {
		return null;
	}

	@Operation(translated = "findRolesByManageableWFCriteria")
	@DaoFinder("select rol "
			+ "from com.soffid.iam.model.RoleEntity rol "
			+ "left join rol.system system where \n"
			+ "(:roleName is null or rol.name like :roleName) and "
			+ "(:description is null or rol.description like :description) and "
			+ "(:defaultRole is null or rol.defaultRole = :defaultRole) and "
			+ "(:system is null or system.name like :system) and "
			+ "(:password is null or rol.password = :password)  and "
			+ "(:informationSystem is null or rol.informationSystem.name like :informationSystem) and "
			+ "(:manageableWF is null or rol.manageableWF =:manageableWF) and\n"
			+ "system.tenant.id = :tenantId "
			+ "order by rol.name, system.name")
	public java.util.List<es.caib.seycon.ng.model.RolEntity> findRolsByFiltreGestionablesWF(
			java.lang.String roleName, java.lang.String description,
			java.lang.String defaultRole, java.lang.String system,
			java.lang.String password, java.lang.String informationSystem,
			java.lang.String manageableWF) {
		return null;
	}


	@Operation(translated = "findByNameAndSystem")
	@DaoFinder("select rolEntity \n"
			+ "from com.soffid.iam.model.RoleEntity rolEntity \n"
			+ "where rolEntity.name = :roleName and\n"
			+ "rolEntity.system.name = :system and "
			+ "rolEntity.system.tenant.id = :tenantId")
	public es.caib.seycon.ng.model.RolEntity findByNameAndDispatcher(
			java.lang.String roleName, 
			java.lang.String system) {
		return null;
	}

	@Description("Creates update mail tasks for each mail list affected by the role")
	@DaoOperation
	public void updateMailLists(RolEntity role) {

	}
	
	@DaoFinder( "select rol " //$NON-NLS-1$
                + "from " //$NON-NLS-1$
                + "com.soffid.iam.model.RoleEntity rol " //$NON-NLS-1$
                + "join rol.applicationDomain domini " //$NON-NLS-1$
                + "left join domini.informationSystem aplicacio " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "domini.name = :domainName and " //$NON-NLS-1$
                + "((:informationSystem is null and aplicacio is null) or (aplicacio.name = :informationSystem)) and "
                + "aplicacio.tenant.id = :tenantId") //$NON-NLS-1$
	public List<RolEntity> findByInformationSystemAndDomain(
			java.lang.String informationSystem, 
			java.lang.String domainName) {
		return null;
	}

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }
	
	// Value object operations
	
	@DaoOperation
	public RolEntity create (Rol role, boolean updateOwnedRoles)
	{ return null; }

	@DaoOperation
	public RolEntity update (Rol role, boolean updateOwnedRoles)
	{ return null; }

	@DaoOperation
	public void remove (Rol role)
	{ return ; }

	@DaoOperation
	public void commitDefinition (RolEntity role)
	{ return ; }

	@DaoOperation
	public void rollbackDefinition (RolEntity role)
	{ return ; }


	@DaoFinder("from com.soffid.iam.model.AccountEntity  where :text is null")
	public Collection<RolEntity>findByText (String text) { return null; }

	@DaoFinder("select u.name "
			+ "from com.soffid.iam.model.RoleEntity as u "
			+ "join u.system as s "
			+ "where s.name = :system")
	public java.util.List<String> findRoleNames(String system) {
		return null;
	}

	@Operation(grantees = { roles.application_query.class })
	@DaoFinder("select u "
			+ "from com.soffid.iam.model.RoleEntity as u "
			+ "where u.domainType = 'APLICACIONS'")
	public java.util.Collection<RolEntity> findApplicationManagementRoles()
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}


}


@Index (name="ROL_UK_NOM_IDDISPAT_IDAPL",	unique=true,
entity=es.caib.seycon.ng.model.RolEntity.class,
columns={"ROL_NOM", "ROL_IDDISPAT"})
abstract class RolIndex {
}

@Index (name="ROL_APL_NDX",	unique=false,
entity=es.caib.seycon.ng.model.RolEntity.class,
columns={"ROL_IDAPL"})
abstract class RolApplicationIndex {
}


