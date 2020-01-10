//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import java.util.Collection;
import java.util.Date;

import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.iam.model.VaultFolderEntity;
import com.soffid.iam.service.ACLService;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.PasswordValidation;

@Entity(table = "SC_ACCOUN", translatedName = "AccountEntity", translatedPackage = "com.soffid.iam.model",
	tenantFilter="system.tenant.id"
)
@Depends({ es.caib.seycon.ng.model.DispatcherEntity.class,
		es.caib.seycon.ng.comu.Account.class,
		es.caib.seycon.ng.model.RolEntity.class,
		es.caib.seycon.ng.model.GrupEntity.class,
		es.caib.seycon.ng.model.UsuariEntity.class,
		es.caib.seycon.ng.model.TasqueEntity.class,
		es.caib.seycon.ng.model.AuditoriaEntity.class,
		es.caib.seycon.ng.model.RolAccountEntity.class,
		es.caib.seycon.ng.model.UserAccountEntity.class,
		es.caib.seycon.ng.model.AccountAccessEntity.class,
		es.caib.seycon.ng.model.AccountPasswordEntity.class,
		es.caib.seycon.ng.model.TipusUsuariEntity.class,
	ACLService.class
})
public abstract class AccountEntity {

	@Column(name = "ACC_ID")
	@Identifier
	public java.lang.Long id;

	@ForeignKey(foreignColumn = "RLU_ACC_ID")
	public java.util.Collection<es.caib.seycon.ng.model.RolAccountEntity> roles;

	@ForeignKey(foreignColumn = "UAC_ACC_ID")
	public java.util.Collection<es.caib.seycon.ng.model.UserAccountEntity> users;

	@Column(name = "ACC_TYPE", length = 1)
	public es.caib.seycon.ng.comu.AccountType type;

	@Column(name = "ACC_NAME", length = 128)
	@Nullable
	public java.lang.String name;

	@Column(name = "ACC_DIS_ID", translated = "system")
	public es.caib.seycon.ng.model.DispatcherEntity dispatcher;

	@Column (name="ACC_OLDNAM", length=128)
	@Nullable
	public java.lang.String oldName;

	@ForeignKey(foreignColumn = "AAC_ACC_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AccountAccessEntity> acl;

	@Column(name = "ACC_DESCRI", length = 255)
	@Nullable
	public java.lang.String description;

	@Column(name = "ACC_CREATE", defaultValue="new java.util.Date()")
	@Nullable
	public java.util.Date created;

	@Column(name = "ACC_LASUPD")
	@Nullable
	public java.util.Date lastUpdated;

	@Column(name = "ACC_LASPAS")
	@Nullable
	public java.util.Date lastPasswordSet;

	@Column(name = "ACC_PASEXP")
	@Nullable
	public java.util.Date passwordExpiration;

	@Column (name="ACC_LASLOG")
	@Nullable
	public java.util.Date lastLogin;

	@Column (name="ACC_SECRET", length=65000)
	@Nullable
	public java.lang.String secrets;

	@Column(name = "ACC_DISABL", defaultValue = "false")
	@Description("Do not use. Use status instead")
	public boolean disabled;

	@Column (name="ACC_STATUS")
	@Nullable
	public AccountStatus status;

	@Column (name="ACC_VAF_ID", reverseAttribute="accounts")
	@Nullable
	VaultFolderEntity folder;
	
	@Column (name="ACC_INHPER", defaultValue="false")
	@Nullable
	Boolean inheritNewPermissions;
/*
	@Column(name="ACC_SVCTYP", length = 50)
	@Nullable
	@Description("Known values are Windows, Linux and Database")
	String serviceType;
*/	
	@Column (name="ACC_URL")
	@Nullable
	String loginUrl;

	@Description("Login name. Used for SSO accounts")
	@Nullable
	@Column (name="ACC_LOGNAM")
	String loginName;

	@Column (name="ACC_LAUTYP")
	@Nullable
	com.soffid.iam.api.LaunchType launchType;
	
	@Column (name="ACC_JSG_ID")
	@Nullable
	JumpServerGroupEntity jumpServerGroup;
	
	@ForeignKey (foreignColumn="APW_ACC_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AccountPasswordEntity> passwords;

	@Column(name = "ACC_TUS_ID")
	public es.caib.seycon.ng.model.TipusUsuariEntity passwordPolicy;

	@Column(name = "ACC_PWDSTA", length = 25)
	@Nullable
	public String passwordStatus;


	@Operation(translated = "findByNameAndSystem")
	@DaoFinder("from com.soffid.iam.model.AccountEntity acc\n"
			+ "where acc.name = :name and acc.system.name=:dispatcher "
			+ "and acc.system.tenant.id=:tenantId")
	public es.caib.seycon.ng.model.AccountEntity findByNameAndDispatcher(
			java.lang.String name, java.lang.String dispatcher) {
		return null;
	}

	@Operation()
	@DaoFinder("select acc\n"
			+ "from   com.soffid.iam.model.AccountEntity acc\n"
			+ "left join     acc.users as users\n"
			+ "left join     users.user as user\n"
			+ "where acc.type='U' and user.id = :userId  "
			+ "order by acc.name")
	public java.util.List<es.caib.seycon.ng.model.AccountEntity> findByUser(
			Long userId) {
		return null;
	}


	@Operation(translated = "findByUserAndSystem")
	@DaoFinder("select acc\n"
			+ "from   com.soffid.iam.model.AccountEntity acc\n"
			+ "left join     acc.users as users\n"
			+ "left join     users.user as user\n"
			+ "left join     acc.system as dispatcher\n"
			+ "where acc.type='U' and user.userName = :user and dispatcher.name = :dispatcher "
			+ "and dispatcher.tenant.id = :tenantId "
			+ "order by user.userName, acc.name")
	public java.util.List<es.caib.seycon.ng.model.AccountEntity> findByUsuariAndDispatcher(
			java.lang.String user, java.lang.String dispatcher) {
		return null;
	}

	@DaoFinder("select acc from com.soffid.iam.model.AccountEntity acc "
			+ "where acc.name = :name and acc.system.name = :system and acc.system.tenant.id = :tenantId "
			+ "order by acc.name")
	public java.util.List<es.caib.seycon.ng.model.AccountEntity> findSharedAccounts(
			@Nullable java.lang.String name, @Nullable java.lang.String system) {
		return null;
	}

	@DaoOperation
	public void propagateChanges(es.caib.seycon.ng.model.AccountEntity account)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@DaoFinder("select acc\n" 
			+ "from com.soffid.iam.model.AccountEntity acc\n"
			+ "join acc.users as users "
			+ "join users.user as user "
			+ "where user.userName=:user and user.tenant.id=:tenantId and "
			+ " acc.system.passwordDomain.name=:domain and acc.type='U'")
	public java.util.List<es.caib.seycon.ng.model.AccountEntity> findByUserAndDomain(
			java.lang.String user, java.lang.String domain) {
		return null;
	}

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.AccountEntity> findByCriteria(
			es.caib.seycon.ng.comu.AccountCriteria criteria) {
		return null;
	}
	
	@DaoFinder("select account.name "
			+ "from com.soffid.iam.model.AccountEntity as account "
			+ "where account.system.name=:systemName and "
			+ "account.system.tenant.id = :tenantId")
	public java.util.List<String> findAcountNames(
			String systemName) {
		return null;
	}
	
	@DaoOperation
	public void update (AccountEntity entity, @Nullable String auditType) 
	{
		
	}

	@DaoOperation
	public void removeFromCache (AccountEntity entity) 
	{
		
	}

	@DaoFinder("from com.soffid.iam.model.AccountEntity where :text is null")
	public Collection<AccountEntity>findByText (String text) { return null; }

	@DaoFinder("select count(a.id) "
			+ "from com.soffid.iam.model.AccountEntity as a "
			+ "where a.type='P' and a.system.url is not null and a.system.tenant.id=:tenantId")
	public Long getHPAccounts() {return null;}
	
	@DaoFinder("select count(ua.id) "
			+ "from com.soffid.iam.model.UserAccountEntity as ua "
			+ "where ua.account.type='P' and ua.account.system.url is not null and ua.account.system.tenant.id=:tenantId")
	public Long getReservedHPAccounts() {return null;}

	@DaoFinder("select count(a.id) "
			+ "from com.soffid.iam.model.AccountEntity as a "
			+ "where a.launchType = 'P' and a.system.tenant.id=:tenantId")
	public Long getPamAccounts() {return null;}

	@DaoFinder("select count(a.id) "
			+ "from com.soffid.iam.model.AccountEntity as a "
			+ "where a.launchType = 'P' and a.passwordStatus = 'PASSWORD_WRONG' and a.system.tenant.id=:tenantId")
	public Long getPamAccountsWrongPassword() {return null;}

	@DaoFinder("select count(a.id) "
			+ "from com.soffid.iam.model.AccountEntity as a "
			+ "where a.launchType = 'P' and a.passwordStatus = 'PASSWORD_GOOD_EXPIRED' and a.system.tenant.id=:tenantId")
	public Long getPamAccountsExpiredPassword() {return null;}

}

@Index (name="SC_ACCOUN_NAME",	unique=true,
entity=es.caib.seycon.ng.model.AccountEntity.class,
columns={"ACC_NAME", "ACC_DIS_ID"})
abstract class AccountIndex {
}

