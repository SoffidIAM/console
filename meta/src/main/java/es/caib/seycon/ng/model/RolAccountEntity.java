//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.soffid.mda.annotation.*;
import es.caib.seycon.ng.common.DelegationStatus;

@Entity(table = "SC_ROLUSU", translatedName = "RoleAccountEntity", translatedPackage = "com.soffid.iam.model",
		tenantFilter="role.system.tenant.id")
@Depends({
		// Other entties
		es.caib.seycon.ng.model.TasqueEntity.class,
		es.caib.seycon.ng.model.NotificacioEntity.class,
		es.caib.seycon.ng.model.UsuariEntity.class,
		es.caib.seycon.ng.model.RolEntity.class,
		es.caib.seycon.ng.model.GrupEntity.class,
		es.caib.seycon.ng.model.AplicacioEntity.class,
		es.caib.seycon.ng.model.ValorDominiAplicacioEntity.class,
		es.caib.seycon.ng.model.AuditoriaEntity.class,
		es.caib.seycon.ng.model.AccountEntity.class,
		es.caib.seycon.ng.model.DispatcherEntity.class,
		com.soffid.iam.model.RuleEntity.class,
		// Value objects
		es.caib.seycon.ng.comu.RolGrant.class,
		es.caib.seycon.ng.comu.ContenidorRol.class,
		es.caib.seycon.ng.comu.RolAccount.class,
		es.caib.seycon.ng.comu.AdministracioAplicacio.class,
		LlistaCorreuEntity.class })
public abstract class RolAccountEntity {

	@Description("Group that limits the scope of the role grant")
	@Column(name = "RLU_ROLUSU_GRU", translated = "group")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grup;

	@Column(name = "RLU_IDROL", translated = "role")
	public es.caib.seycon.ng.model.RolEntity rol;

	@Column(name = "RLU_ID")
	@Identifier
	public java.lang.Long id;

	@Description("Value that limits the scope of the role grant")
	@Column(name = "RLU_VALDOM", translated = "domainValue")
	@Nullable
	public es.caib.seycon.ng.model.ValorDominiAplicacioEntity valorDominiAplicacio;

	@Column(name = "RLU_TIPDOM", length = 20, translated = "domainType")
	@Nullable
	public java.lang.String tipusDomini;

	@Description("Information System that limits the scope of the role grant")
	@Column(name = "RLU_ADMAPP", translated = "informationSystem")
	@Nullable
	public es.caib.seycon.ng.model.AplicacioEntity aplicacioAdministrada;

	@Column(name = "RLU_ACC_ID")
	public es.caib.seycon.ng.model.AccountEntity account;

	@Column(name = "RLU_RUL_ID")
	@Nullable
	public com.soffid.iam.model.RuleEntity rule;

	@Column(name = "RLU_START")
	@Nullable
	public Date startDate;

	@Column(name = "RLU_END")
	@Nullable
	public Date endDate;

	@Column(name = "RLU_ENABLE", defaultValue = "true")
	public boolean enabled;

	@Column(name = "RLU_APRPEN", defaultValue = "false")
	public boolean approvalPending;

	@Description("This foreign key binds this the role assignment to the group membership that grants this role to the account. Not applicable for shared accounts")
	@Column(name = "RLU_GROUP")
	@Nullable
	public GrupEntity holderGroup;

	@Description("When an aproval process is needed to enable this rol assignment")
	@Column(name = "RLU_APRPRO")
	@Nullable
	public Long approvalProcess;

	@Description("Last certification date")
	@Column(name = "RLU_CERDAT")
	@Nullable
	public Date certificationDate;
	
	@Description("Parent grant. Used only in non mandatory rol to rol grants")
	@Column(name="RLU_RLU_ID", composition=true, reverseAttribute="children")
	@Nullable
	public RolAccountEntity parent;
	
	
	@Description("Delegation status")
	@Column(name="RLU_DELSTA")
	@Nullable
	public DelegationStatus delegationStatus;

	@Description("Entitled account who delegatse to delegateAccount")
	@Column (name="RLU_OMNACC_ID")
	@Nullable
	public es.caib.seycon.ng.model.AccountEntity ownerAccount;

	@Description("Delegate account")
	@Column (name="RLU_DELACC_ID")
	@Nullable
	public es.caib.seycon.ng.model.AccountEntity delegateAccount;

	@Description("Delegate since date")
	@Column (name="RLU_DELSIN")
	@Nullable
	public Date delegateSince;

	@Description("Delegate until date")
	@Column (name="RLU_DELUNT")
	@Nullable
	public Date delegateUntil;

	/**************************** DAOs ******************************/
	@DaoFinder("select ra" 
			+ " from com.soffid.iam.model.RoleAccountEntity ra\n"
			+ "inner join    ra.account as account\n"
			+ "inner join    account.system as dispatcher\n"
			+ "inner join    account.users as users\n"
			+ "inner join    users.user as user\n"
			+ "inner join    ra.role as role\n"
			+ "where user.userName = :userName and account.type='U' and user.tenant.id=:tenantId \n"
			+ "order by dispatcher.name, role.name\n")
	@Operation(translated="findByUserName")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findByCodiUsuari(
			java.lang.String userName) {
		return null;
	}

	public java.lang.String toString() {
		return null;
	}

	@Operation(translated = "findByGroupName")
	@DaoFinder("select ra\n"
			+ "from com.soffid.iam.model.RoleAccountEntity ra\n"
			+ "where ra.group.name=:groupName and ra.group.tenant.id = :tenantId ")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findByCodiGrup(
			java.lang.String groupName) {
		return null;
	}

	@Operation(translated = "findByRoleAndDomainValue")
	@DaoFinder("select ra\n"
			+ "from com.soffid.iam.model.RoleAccountEntity ra\n"
			+ "left join ra.role role \n"
			+ "left join ra.group as gr "
			+ "left join ra.informationSystem informationSystem \n"
			+ "left join ra.domainValue domainValue \n"
			+ "where (role.name = :roleName and role.system.name = :systemName) and \n"
			+ "ra.domainType=:domainType and \n"
			+ "( gr is null or :groupScope = gr.name) and \n"
			+ "( informationSystem is null or :informationSystemScope = informationSystem.name) and \n"
			+ "( domainValue is null or :domainValueId = domainValue.id) and "
			+ "role.system.tenant.id = :tenantId ")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findByRolAndValorDomini(
			java.lang.String roleName, 
			java.lang.String systemName,
			java.lang.String domainType,
			java.lang.String groupScope,
			java.lang.String informationSystemScope,
			java.lang.Long domainValueId) {
		return null;
	}

	@Operation(translated = "findByRoleAndDomainType")
	@DaoFinder("select ra from com.soffid.iam.model.RoleAccountEntity ra\n"
			+ "left join ra.role role \n"
			+ "where role.name = :roleName and "
			+ "role.system.name = :systemName and \n"
			+ "role.informationSystem.name = :informationSystemName and "
			+ "ra.domainType=:domainType and "
			+ "role.system.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findByRolAndTipusDomini(
			java.lang.String roleName, java.lang.String systemName,
			java.lang.String informationSystemName, java.lang.String domainType) {
		return null;
	}

	@Operation(translated = "findAllByUserName")
	@DaoFinder("select ra from com.soffid.iam.model.RoleAccountEntity ra\n"
			+ "join ra.account as account\n"
			+ "join account.users as users\n"
			+ "join users.user as user\n"
			+ "left join ra.role as role\n"
			+ "left join role.system as system\n"
			+ "where  user.userName = :userName and user.tenant.id = :tenantId\n"
			+ "order by system.name, role.name")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findAllByCodiUsuari(
			java.lang.String userName) {
		return null;
	}

	@Operation(translated = "findByQualifierGroup")
	@DaoFinder("select ra from com.soffid.iam.model.RoleAccountEntity ra\n"
			+ "where ra.group.name=:groupName and ra.group.tenant.id=:tenantId\n")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findByGrupQualifier(
			java.lang.String groupName) {
		return null;
	}

	@DaoFinder("select ra from com.soffid.iam.model.RoleAccountEntity ra\n"
			+ "where ra.informationSystem.name=:informationSystem and ra.role.system.tenant.id = :tenantId\n")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findByQualifierIS(
			java.lang.String informationSystem) {
		return null;
	}

	@DaoFinder("select ra from com.soffid.iam.model.RoleAccountEntity ra\n"
			+ "join ra.account.users as useraccount\n"
			+ "where ra.rule.id = :ruleId and useraccount.user.id = :userId and ra.account.type='U' "
			+ "and ra.account.system.tenant.id = :tenantId "
			+ "order by ra.account.name")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findByUserAndRule(
			java.lang.Long userId, java.lang.Long ruleId) {
		return null;
	}

	@DaoFinder("select ra "
			+ "from com.soffid.iam.model.RoleAccountEntity ra\n"
			+ "where ra.endDate < :now and ra.enabled = true "
			+ "and ra.account.system.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findRolAccountToDisable(
			Date now) {
		return null;
	}

	@DaoFinder("select ra "
			+ "from com.soffid.iam.model.RoleAccountEntity ra\n"
			+ "where ra.startDate < :now and (ra.endDate is null or ra.endDate >= :now) and ra.enabled = false "
			+ "and ra.account.system.tenant.id = :tenantId ")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findRolAccountToEnable(
			Date now) {
		return null;
	}
	
	@DaoFinder("select rolsUsuaris " //$NON-NLS-1$
                + "from " //$NON-NLS-1$
                + "com.soffid.iam.model.RoleAccountEntity rolsUsuaris " //$NON-NLS-1$
                + "left join rolsUsuaris.group grup " //$NON-NLS-1$
                + "left join rolsUsuaris.informationSystem aplicacio " //$NON-NLS-1$
                + "left join rolsUsuaris.domainValue valorDominiAplicacio " //$NON-NLS-1$
                + "where " //$NON-NLS-1$
                + "rolsUsuaris.account.id = :accountId and " //$NON-NLS-1$
                + "rolsUsuaris.role.id = :roleId and " //$NON-NLS-1$
                + "(rolsUsuaris.domainType = :domainType) and " //$NON-NLS-1$
                + "((:groupName is null and grup is null) or (grup.name = :groupName)) and " //$NON-NLS-1$
                + "((:informationSystem is null and aplicacio is null) or (aplicacio.name = :informationSystem)) and " //$NON-NLS-1$
                + "((:domainValue is null and valorDominiAplicacio is null) or (valorDominiAplicacio.value = :domainValue)) and "
                + "rolsUsuaris.account.system.tenant.id = :tenantId") //$NON-NLS-1$
     public java.util.List<RolAccountEntity> findMatching (
    		 Long accountId,
    		 Long roleId,
    		 String domainType,
    		 String groupName,
    		 String informationSystem,
    		 String domainValue
    		 )
	 {
        	return null;
	 }
	
	@Description("Gets all granted roles for an information system")
	@DaoFinder(	"select rolusu " //$NON-NLS-1$
				+ "from " //$NON-NLS-1$
				+ "com.soffid.iam.model.RoleAccountEntity rolusu " //$NON-NLS-1$
				+ "join rolusu.account.users as users "  //$NON-NLS-1$
				+ "join users.user as user " //$NON-NLS-1$
				+ "where " //$NON-NLS-1$
				+ "rolusu.role.informationSystem.name = :informationSystem and "
				+ "rolusu.role.system.tenant.id = :tenantId " //$NON-NLS-1$
				+ "order by user.userName, rolusu.role.name, rolusu.role.system.name") //$NON-NLS-1$
     public java.util.List<RolAccountEntity> findByInformationSystem (
    		 String informationSystem
    		 )
	 {
        	return null;
	 }


	@Description("Search delegations to start")
	@DaoFinder("select ra "
			   + "from com.soffid.iam.model.RoleAccountEntity as ra\n"
			   + "join ra.delegateAccount.users as userAccount "
			   + "join userAccount.user as user "
			   + "where ra.delegateSince < :now and (ra.delegateUntil is null or ra.delegateUntil >= :now) and ra.delegationStatus = 'P' and "
			   + "user.userName = :user and user.tenant.id=:tenantId")
	@Operation(translated="findRoleAccountToStartDelegation")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findRolAccountToStartDelegation(
		String user,
		Date now) {
		return null;
	}

	@Description("Search delegations to end")
	@DaoFinder("select ra "
			   + "from com.soffid.iam.model.RoleAccountEntity as ra\n"
			   + "join ra.delegateAccount.users as userAccount "
			   + "join userAccount.user as user "
			   + "where ra.delegateUntil < :now and ra.delegationStatus is not null and "
			   + "user.userName = :user and user.tenant.id=:tenantId")
	@Operation(translated="findRoleAccountToEndDelegation")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findRolAccountToEndDelegation(
		String user,
		Date now) {
		return null;
	}

	@Description("Search delegations done by a user")
	@DaoFinder("select ra "
			   + "from com.soffid.iam.model.RoleAccountEntity as ra\n"
			   + "join ra.ownerAccount.users as userAccount "
			   + "join userAccount.user as user "
			   + "where ra.delegationStatus='A' and "
			   + "user.userName = :user and user.tenant.id=:tenantId")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findDelegatedRolAccounts(
		String user) {
		return null;
	}

	@Description("Search delegations to start")
	@DaoFinder("select ra "
			   + "from com.soffid.iam.model.RoleAccountEntity as ra\n"
			   + "where ra.delegateSince < :now and (ra.delegateUntil is null or ra.delegateUntil >= :now) "
			   + "      and ra.delegationStatus = 'P' "
			   + "      and ra.role.system.tenant.id=:tenantId")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findAllRolAccountToStartDelegation(
		Date now) {
		return null;
	}

	@Description("Search delegations to end")
	@DaoFinder("select ra "
			   + "from com.soffid.iam.model.RoleAccountEntity as ra\n"
			   + "where ra.delegateUntil < :now and ra.delegationStatus is not null and"
			   + "      ra.role.system.tenant.id=:tenantId")
	public java.util.List<es.caib.seycon.ng.model.RolAccountEntity> findAllRolAccountToEndDelegation(
		Date now) {
		return null;
	}

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }
	
	@DaoOperation
	void update(RolAccountEntity entity, String auditOperation) {}

	public void customCache() {
	}
}
