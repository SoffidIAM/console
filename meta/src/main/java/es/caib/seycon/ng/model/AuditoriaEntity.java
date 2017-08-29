//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_AUDITO", translatedName="AuditEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.Auditoria.class,
	es.caib.seycon.ng.model.LlistaCorreuEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.ImpressoraEntity.class,
	es.caib.seycon.ng.model.AplicacioEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.XarxaEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class,
	es.caib.seycon.ng.model.DominiCorreuEntity.class,
	es.caib.seycon.ng.model.AccountEntity.class,
	es.caib.seycon.ng.servei.PasswordService.class})
public abstract class AuditoriaEntity {

	@Column (name="AUD_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="AUD_DATA", translated="date")
	public java.util.Date data;

	@Column (name="AUD_USUAUD", length=100, translated="user")
	@Nullable
	public java.lang.String usuari;

	@Column (name="AUD_ACCIO", length=1, translated="action")
	public java.lang.String accio;

	@Column (name="AUD_INFO", length=1024, translated="object")
	@Nullable
	public java.lang.String objecte;

	@Column (name="AUD_IDGRU", translated="group")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grup;

	@Column (name="AUD_DOMRLU", length=100, translated="domain")
	@Nullable
	public java.lang.String domini;

	@Column (name="AUD_VALDOMRLU", length=100, translated="domainValue")
	@Nullable
	public java.lang.String valorDomini;

	@Column (name="AUD_CON", length=100, translated="configurationParameter")
	@Nullable
	public java.lang.String parametreConfiguracio;

	@Column (name="AUD_DIS", length=150, translated="db")
	@Nullable
	public java.lang.String bbdd;

	@Column (name="AUD_IMP", length=100, translated="printer")
	@Nullable
	public java.lang.String impressora;

	@Column (name="AUD_APL", length=500, translated="informationSystem")
	@Nullable
	public java.lang.String aplicacio;

	@Column (name="AUD_DCO", length=100, translated="mailDomain")
	@Nullable
	public java.lang.String dominiCorreu;

	@Column (name="AUD_LCO", length=100, translated="mailList")
	@Nullable
	public java.lang.String llistaCorreu;

	@Column (name="AUD_XAR", length=100, translated="network")
	@Nullable
	public java.lang.String xarxa;

	@Column (name="AUD_ROL", length=100, translated="role")
	@Nullable
	public java.lang.String rol;

	@Column (name="AUD_MAQ", length=100, translated="host")
	@Nullable
	public java.lang.String maquina;

	@Column (name="AUD_AUT", length=100, translated="authorization")
	@Nullable
	public java.lang.String autoritzacio;

	@Column (name="AUD_FITXER", translated="fileId")
	@Nullable
	public java.lang.Long fitxerId;

	@Column (name="AUD_FED", length=100, translated="identityFederation")
	@Nullable
	public java.lang.String federacioIdentitats;

	@Column (name="AUD_LCO2", length=100, translated="belongsMailList")
	@Nullable
	public java.lang.String llistaCorreuPertany;

	@Column (name="AUD_DCO2", length=100, translated="belongsmailDomain")
	@Nullable
	public java.lang.String dominiCorreuPertany;

	@Column (name="AUD_ACCOUN", length=128)
	@Nullable
	public String account;

	@Column (name="AUD_ACC_ID")
	@Nullable
	public es.caib.seycon.ng.model.AccountEntity accountAssoc;

	@Column (name="AUD_MESSAG", length=1024)
	@Nullable
	public java.lang.String message;

	@Column (name="AUD_TIPUSU", length=255)
	@Nullable
	public java.lang.String userType;

	@Column (name="AUD_DOMUSU", length=100)
	@Nullable
	public java.lang.String userDomain;

	@Column (name="AUD_DOMCON", length=100)
	@Nullable
	public java.lang.String passwordDomain;

	@Column (name="AUD_RULE", length=200)
	@Nullable
	public java.lang.String rule;

	@Column (name="AUD_SCHTAS")
	@Nullable
	public java.lang.String scheduledTask;
	
	@Column (name="AUD_CUOBNA")
	@Nullable
	public java.lang.String customObjectName;
	
	@Column (name="AUD_CUOBTY")
	@Nullable
	public java.lang.String customObjectType;
	
	@Column (name="AUD_TEN_ID")
	public TenantEntity tenant;

	@DaoFinder
	public es.caib.seycon.ng.model.AuditoriaEntity findById(
		java.lang.Long id) {
	 return null;
	}
	@Operation(translated="findAuditByCriteria1")
	@DaoFinder("select audit from com.soffid.iam.model.AuditEntity audit\n "
			+ "left join audit.accountAssoc accountAssoc \n"
			+ "where  (:user is null or audit.user like :user) and \n"
			+ " (:object is null or audit.object like :object) and \n"
			+ " (:author is null or accountAssoc.name like :author)  and\n"
			+ " (:action is null or audit.action=:action) and \n"
			+ " audit.tenant.id = :tenantId\n"
			+ "order by audit.date asc")
	public java.util.List<es.caib.seycon.ng.model.AuditoriaEntity> findAuditoriesByCriteri1(
		java.lang.String author, 
		java.lang.String object, 
		java.lang.String user, 
		java.lang.String action) {
	 return null;
	}
	@Operation(translated="findAuditByCriteria2")
	@DaoFinder("select audit from com.soffid.iam.model.AuditEntity audit\n "
			+ "left join audit.accountAssoc accountAssoc \n"
			+ "where  (:user is null or audit.user like :user) and \n"
			+ " (:object is null or audit.object like :object) and \n"
			+ " (:author is null or accountAssoc.name like :author)  and\n"
			+ " (:action is null or audit.action=:action)  \n"
			+ "and (:column is null or \n"
			+ "   (:column is not null  and  \n"
			+ "    ( \n"
			+ "      ((:column='domini' or :column='domain') and audit.domain like :value) \n"
			+ "      or ((:column='valorDomini' or :column='domainValue') and audit.domainValue like :value) \n"
			+ "      or ((:column='parametreConfiguracio' or :column='configurationParameter') and audit.configurationParameter like :value) \n"
			+ "      or ((:column='bbdd' or :column='database') and audit.db like :value) \n"
			+ "      or ((:column='impressora' or :column='printer') and audit.printer like :value) \n"
			+ "      or ((:column='aplicacio' or :column='informatinSystem') and audit.informationSystem like :value) \n"
			+ "      or ((:column='dominiCorreu' or :column='mailDomain') and audit.mailDomain like :value) \n"
			+ "      or ((:column='llistaCorreu' or :column='mailList') and audit.mailList like :value) \n"
			+ "      or ((:column='fitxer' or :column='file') and audit.fileId like :value) \n"
			+ "      or ((:column='xarxa' or :column='network') and audit.network like :value) \n"
			+ "      or ((:column='rol' or :column='role') and audit.role like :value) \n"
			+ "      or ((:column='maquina' or :column='host') and audit.host like :value) \n"
			+ "      or ((:column='autoritzacio' or :column='authorization') and audit.authorization like :value) \n"
			+ "      or ((:column='grup' or :column='group') and audit.group is not null and audit.group.name like :value)\n"
			+ "      or (:column='userType' and audit.userType like :value)\n"
			+ "      or (:column='passwordDomain' and audit.passwordDomain like :value)\n"
			+ "      or (:column='userDomain' and audit.userDomain like :value)\n"
			+ "    )\n"
			+ "   )\n"
			+ ") and audit.tenant.id = :tenantId\n"
			+ "order by audit.date asc")
	public java.util.List<es.caib.seycon.ng.model.AuditoriaEntity> findAuditoriesByCriteri2(
		java.lang.String author, 
		java.lang.String object, 
		java.lang.String user, 
		java.lang.String column, 
		java.lang.String value, 
		java.lang.String action) {
	 return null;
	}
	@Operation(translated="findAuditByCriteria3")
	@DaoFinder("select audit from com.soffid.iam.model.AuditEntity audit\n "
			+ "left join audit.accountAssoc accountAssoc \n"
			+ "where  (:user is null or audit.user like :user) and \n"
			+ " (:until = :nullDate or audit.date < :until ) and\n"
			+ " (:since = :nullDate or audit.date > :since ) and\n "
			+ " (:object is null or audit.object like :object) and \n"
			+ " (:author is null or accountAssoc.name like :author)  and\n"
			+ " (:action is null or audit.action=:action) and\n"
			+ " audit.tenant.id = :tenantId \n"
			+ "order by audit.date asc")
	public java.util.List<es.caib.seycon.ng.model.AuditoriaEntity> findAuditoriesByCriteri3(
		java.util.Date nullDate, 
		java.util.Date until, 
		java.util.Date since, 
		java.lang.String author, 
		java.lang.String object, 
		java.lang.String user, 
		java.lang.String action) {
	 return null;
	}
	@Operation(translated="findAuditByCriteria4")
	@DaoFinder("select audit from com.soffid.iam.model.AuditEntity audit\n "
			+ "left join audit.accountAssoc accountAssoc \n"
			+ "where  (:user is null or audit.user like :user) and \n"
			+ " (:until = :nullDate or audit.date < :until ) and\n"
			+ " (:since = :nullDate or audit.date > :since ) and\n "
			+ " (:object is null or audit.object like :object) and \n"
			+ " (:author is null or accountAssoc.name like :author)  and\n"
			+ " (:action is null or audit.action=:action) and \n"
			+ " (:column is null or \n"
			+ "   (:column is not null  and  \n"
			+ "    ( \n"
			+ "      ((:column='domini' or :column='domain') and audit.domain like :value) \n"
			+ "      or ((:column='valorDomini' or :column='domainValue') and audit.domainValue like :value) \n"
			+ "      or ((:column='parametreConfiguracio' or :column='configurationParameter') and audit.configurationParameter like :value) \n"
			+ "      or ((:column='bbdd' or :column='database') and audit.db like :value) \n"
			+ "      or ((:column='impressora' or :column='printer') and audit.printer like :value) \n"
			+ "      or ((:column='aplicacio' or :column='informatinSystem') and audit.informationSystem like :value) \n"
			+ "      or ((:column='dominiCorreu' or :column='mailDomain') and audit.mailDomain like :value) \n"
			+ "      or ((:column='llistaCorreu' or :column='mailList') and audit.mailList like :value) \n"
			+ "      or ((:column='fitxer' or :column='file') and audit.fileId like :value) \n"
			+ "      or ((:column='xarxa' or :column='network') and audit.network like :value) \n"
			+ "      or ((:column='rol' or :column='role') and audit.role like :value) \n"
			+ "      or ((:column='maquina' or :column='host') and audit.host like :value) \n"
			+ "      or ((:column='autoritzacio' or :column='authorization') and audit.authorization like :value) \n"
			+ "      or ((:column='grup' or :column='group') and audit.group is not null and audit.group.name like :value)\n"
			+ "      or (:column='userType' and audit.userType like :value)\n"
			+ "      or (:column='passwordDomain' and audit.passwordDomain like :value)\n"
			+ "      or (:column='userDomain' and audit.userDomain like :value)\n"
			+ "    )\n"
			+ "   )\n"
			+ ") and\n"
			+ "audit.tenant.id = :tenantId \n"
			+ "order by audit.date asc")
	public java.util.List<es.caib.seycon.ng.model.AuditoriaEntity> findAuditoriesByCriteri4(
		java.util.Date nullDate, 
		java.util.Date until, 
		java.util.Date since, 
		java.lang.String author, 
		java.lang.String object, 
		java.lang.String user, 
		java.lang.String column, 
		java.lang.String value, 
		java.lang.String action) {
	 return null;
	}
	
	@Description("Unlinks audit logs from account that is going to be removed")
	@DaoOperation
	public void unlinkAccounts(AccountEntity account) {}
}

@Index (name="SC_AUDITO_BORRAR",	unique=false,
entity=es.caib.seycon.ng.model.AuditoriaEntity.class,
columns={"AUD_DATA"})
abstract class AuditoriaDataIndex {
}

