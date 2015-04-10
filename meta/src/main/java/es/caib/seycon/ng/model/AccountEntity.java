//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import com.soffid.mda.annotation.*;

@Entity(table = "SC_ACCOUN", translatedName = "AccountEntity", translatedPackage = "com.soffid.iam.model")
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
		es.caib.seycon.ng.model.TipusUsuariEntity.class })
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

	@ForeignKey(foreignColumn = "AAC_ACC_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AccountAccessEntity> acl;

	@Column(name = "ACC_DESCRI", length = 255)
	@Nullable
	public java.lang.String description;

	@Column(name = "ACC_LASUPD")
	@Nullable
	public java.util.Date lastUpdated;

	@Column(name = "ACC_LASPAS")
	@Nullable
	public java.util.Date lastPasswordSet;

	@Column(name = "ACC_PASEXP")
	@Nullable
	public java.util.Date passwordExpiration;

	@Column(name = "ACC_SECRET", length = 65000)
	@Nullable
	public java.lang.String secrets;

	@Column(name = "ACC_DISABL", defaultValue = "false")
	public boolean disabled;

	@ForeignKey(foreignColumn = "APW_ACC_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AccountPasswordEntity> passwords;

	@Column(name = "ACC_TUS_ID")
	public es.caib.seycon.ng.model.TipusUsuariEntity passwordPolicy;

	@Operation(translated = "findByNameAndSystem")
	@DaoFinder("from com.soffid.iam.model.AccountEntity acc\nwhere acc.name = :name and acc.system.name=:dispatcher")
	public es.caib.seycon.ng.model.AccountEntity findByNameAndDispatcher(
			java.lang.String name, java.lang.String dispatcher) {
		return null;
	}

	@Operation(translated = "findByUserAndSystem")
	@DaoFinder("select acc\n"
			+ "from   com.soffid.iam.model.AccountEntity acc\n"
			+ "left join     acc.users as users\n"
			+ "left join     users.user as user\n"
			+ "left join     acc.system as dispatcher\n"
			+ "where acc.type='U' and user.userName = :user and dispatcher.name = :dispatcher")
	public java.util.List<es.caib.seycon.ng.model.AccountEntity> findByUsuariAndDispatcher(
			java.lang.String user, java.lang.String dispatcher) {
		return null;
	}

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.AccountEntity> findSharedAccounts(
			@Nullable java.lang.String name, @Nullable java.lang.String system) {
		return null;
	}

	@DaoOperation
	public void propagateChanges(es.caib.seycon.ng.model.AccountEntity account)
			throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@DaoFinder("select acc\n" + "from com.soffid.iam.model.AccountEntity acc\n"
			+ "join acc.users as users\n"
			+ "join users.user as user with user.userName=:user\n"
			+ "where acc.system.passwordDomain.name=:domain and acc.type='U'")
	public java.util.List<es.caib.seycon.ng.model.AccountEntity> findByUserAndDomain(
			java.lang.String user, java.lang.String domain) {
		return null;
	}

	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.AccountEntity> findByCriteria(
			es.caib.seycon.ng.comu.AccountCriteria criteria) {
		return null;
	}
}
