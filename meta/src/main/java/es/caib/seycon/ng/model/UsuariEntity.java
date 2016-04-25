//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.comu.Usuari;

@Entity(table = "SC_USUARI", translatedName = "UserEntity", translatedPackage = "com.soffid.iam.model")
@Depends({
		es.caib.seycon.ng.model.RolsGrupEntity.class,
		es.caib.seycon.ng.model.UsuariSEUEntity.class,
		es.caib.seycon.ng.model.TipusUsuariEntity.class,
		es.caib.seycon.ng.model.ContrasenyaEntity.class,
		es.caib.seycon.ng.model.DominiUsuariEntity.class,
		es.caib.seycon.ng.comu.Usuari.class,
		es.caib.seycon.ng.model.DadaUsuariEntity.class,
		es.caib.seycon.ng.model.TipusDadaEntity.class,
		es.caib.seycon.ng.model.MaquinaEntity.class,
		es.caib.seycon.ng.comu.Identitat.class,
		es.caib.seycon.ng.model.UsuariGrupEntity.class,
		es.caib.seycon.ng.model.GrupEntity.class,
		es.caib.seycon.ng.model.DominiCorreuEntity.class,
		es.caib.seycon.ng.model.RolAccountEntity.class,
		es.caib.seycon.ng.model.AuditoriaEntity.class,
		es.caib.seycon.ng.model.LlistaCorreuEntity.class,
		es.caib.seycon.ng.model.LlistaCorreuUsuariEntity.class,
		es.caib.seycon.ng.model.TasqueEntity.class,
		DispatcherEntity.class,
		es.caib.bpm.vo.BPMUser.class,
		es.caib.seycon.ng.comu.UsuariAnonim.class,
		es.caib.seycon.ng.model.XarxaACEntity.class,
		es.caib.seycon.ng.model.UsuariImpressoraEntity.class,
		es.caib.seycon.ng.model.SessioEntity.class,
		es.caib.seycon.ng.model.AplicacioEntity.class,
		es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity.class,
		es.caib.seycon.ng.model.NotificacioEntity.class,
		es.caib.seycon.ng.model.SecretEntity.class,
		es.caib.seycon.ng.model.UserAccountEntity.class,
		es.caib.seycon.ng.model.AccountAccessEntity.class, RolEntity.class })
public abstract class UsuariEntity {

	@Column(name = "USU_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "USU_CODI", length = 150, translated = "userName")
	public java.lang.String codi;

	@Column(name = "USU_NOM", length = 50, translated = "firstName")
	public java.lang.String nom;

	@Column(name = "USU_PRILLI", length = 50, translated = "lastName")
	public java.lang.String primerLlinatge;

	@Column(name = "USU_NOMCUR", length = 25, translated = "shortName")
	@Nullable
	public java.lang.String nomCurt;

	@Column(name = "USU_USUMOD", length = 50, translated = "lastUserModification")
	@Nullable
	public java.lang.String usuariDarreraModificacio;

	@Column(name = "USU_DATMOD", translated = "lastModificationDate")
	@Nullable
	public java.util.Date dataDarreraModificacio;

	@Column(name = "USU_DATCRE", translated = "creationDate")
	public java.util.Date dataCreacio;

	@Column(name = "USU_USUCRE", length = 50, translated = "creationUser")
	@Nullable
	public java.lang.String usuariCreacio;

	@Column(name = "USU_ACTIU", length = 1, translated = "active")
	public java.lang.String actiu;

	@Column(name = "USU_SEGLLI", length = 50, translated = "middleName")
	@Nullable
	public java.lang.String segonLlinatge;

	@Column(name = "USU_COMENT", length = 1024, translated = "comment")
	@Nullable
	public java.lang.String comentari;

	@ForeignKey(foreignColumn = "AXA_IDUSU", translated = "ACNetwork")
	public java.util.Collection<es.caib.seycon.ng.model.XarxaACEntity> xarxesAC;

	@Column(name = "USU_IDMACO", translated = "mailServer")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity servidorCorreu;

	@Column(name = "USU_IDMAQ", translated = "homeServer")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity servidorOfimatic;

	@Column(name = "USU_IDDCO", translated = "mailDomain")
	@Nullable
	public es.caib.seycon.ng.model.DominiCorreuEntity dominiCorreu;

	@Column(name = "USU_IDMAPR", translated = "profileServer")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity servidorPerfil;

	@Column(name = "USU_IDGRU", translated = "primaryGroup")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity grupPrimari;

	@ForeignKey(foreignColumn = "DUS_IDUSU", translated = "userData")
	public java.util.Collection<es.caib.seycon.ng.model.DadaUsuariEntity> dadaUsuari;

	@ForeignKey(foreignColumn = "UGR_IDUSU", translated = "secondaryGroups")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariGrupEntity> grupsSecundaris;

	@ForeignKey(foreignColumn = "UIM_IDUSU", translated = "printers")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariImpressoraEntity> impressores;

	@ForeignKey(foreignColumn = "SES_IDUSU", translated = "sessions")
	public java.util.Collection<es.caib.seycon.ng.model.SessioEntity> sessions;

	@ForeignKey(foreignColumn = "ULC_IDUSU", translated = "userMailList")
	public java.util.Collection<es.caib.seycon.ng.model.LlistaCorreuUsuariEntity> llistaDeCorreuUsuari;

	@Column(name = "USU_MULSES", length = 1)
	@Nullable
	public java.lang.String multiSessio;

	@Column (name="USU_TEN_ID")
	TenantEntity tenant;
	
	/**
	 * @Column (name="USU_ALCOAN", length=240)
	 * @Nullable Not used public java.lang.String aliesCorreu;
	 */

	@ForeignKey(foreignColumn = "APL_IDCONTACT", translated = "ApplicationResponsible")
	public java.util.Collection<es.caib.seycon.ng.model.AplicacioEntity> aplicacioSocPersonaResponsable;

	@ForeignKey(foreignColumn = "ADM_USUID", translated = "accessHostAsAdministratorAuthorization")
	public java.util.Collection<es.caib.seycon.ng.model.AutoritzacioAccesHostComAdministradorEntity> autoritzacioAccesHostComAdministrador;

	@ForeignKey(foreignColumn = "USE_USUID", translated = "SEUInformation")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariSEUEntity> informacioSEU;

	@Column(name = "USU_TUS_ID", translated = "userType")
	public es.caib.seycon.ng.model.TipusUsuariEntity tipusUsuari;

	@ForeignKey(foreignColumn = "SEC_IDUSU", translated = "secrets")
	public java.util.Collection<es.caib.seycon.ng.model.SecretEntity> secrets;

	@ForeignKey(foreignColumn = "UAC_USU_ID", translated = "accounts")
	public java.util.Collection<es.caib.seycon.ng.model.UserAccountEntity> accounts;

	@ForeignKey(foreignColumn = "AAC_USU_ID", translated = "accountAccess")
	public java.util.Collection<es.caib.seycon.ng.model.AccountAccessEntity> accountAccess;

	@ForeignKey(foreignColumn = "CTR_IDUSU", translated = "passwords")
	public java.util.Collection<es.caib.seycon.ng.model.ContrasenyaEntity> contrasenyes;

	@ForeignKey(foreignColumn = "PAU_USU_ID")
	public java.util.Collection<com.soffid.iam.authoritative.model.AuthoritativeChangeEntity> pendingAuthoritativeChanges;

	/************************ DAOs **********************************/
	@Operation(translated = "findByUserName")
	@DaoFinder("from com.soffid.iam.model.UserEntity  where userName = :userName and tenant.id = :tenantId")
	public es.caib.seycon.ng.model.UsuariEntity findByCodi(
			java.lang.String userName) {
		return null;
	}

	@Operation(translated = "generateUserName")
	@DaoFinder
	public String generaCodiUsuari() {
		return null;
	}

	@DaoFinder("from com.soffid.iam.model.UserEntity where id = :id")
	public es.caib.seycon.ng.model.UsuariEntity findById(java.lang.Long id) {
		return null;
	}

	@Operation(translated = "findByNationalID")
	@DaoFinder("select usuari from com.soffid.iam.model.UserEntity usuari "
			+ "join usuari.userData as dadaUsuari "
			+ "where dadaUsuari.dataType.name = 'NIF' and dadaUsuari.value = :nif and usuari.tenant.id = :tenantId")
	public es.caib.seycon.ng.model.UsuariEntity findByNIF(java.lang.String nif) {
		return null;
	}

	@Operation(translated = "findByShortName")
	@DaoFinder
	public es.caib.seycon.ng.model.UsuariEntity findByNomCurt(
			java.lang.String shortName) {
		return null;
	}

	@DaoOperation
	@Description("Generates UPDATE_MAIL_LIST tasks for any affected mail list")
	void createUpdateTasks(UsuariEntity user, Usuari oldValue) {

	}

	@DaoOperation
	public java.lang.String refreshCanvis(java.lang.String codiUsuari) {
		return null;
	}

	@Operation(translated = "getTasks")
	@DaoFinder("- CUSTOM -")
	public java.lang.String[] getTasques(java.lang.String codiUsuari) {
		return null;
	}

	@DaoFinder
	@Operation(translated = "getNextUserName")
	public java.lang.String getSeguentCodi() {
		return null;
	}

	@Operation(translated = "findUserByDataValue")
	@DaoFinder("SELECT distinct usu \n"
			+ "FROM com.soffid.iam.model.UserEntity usu "
			+ "join usu.userData as dada \n" 
			+ "WHERE \n"
			+ "   dada.dataType.name = :dataType  and \n"
			+ "   dada.value = :value and "
			+ "   usu.tenant.id = :tenantId ")
	public es.caib.seycon.ng.model.UsuariEntity findUsuariByCodiTipusDadaIValorDada(
			java.lang.String dataType, java.lang.String value) {
		return null;
	}

	@Operation(translated = "getNextAnonimUser")
	@DaoFinder
	public java.lang.String getSeguentCodiAnonim() {
		return null;
	}

	@Operation(translated = "findByPrimaryGroup")
	@DaoFinder("select usuari "
			+ "from com.soffid.iam.model.UserEntity as usuari "
			+ "join usuari.primaryGroup as grup "
			+ "where grup.name=:primaryGroupName and "
			+ "usuari.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> findByGrupPrimari(
			java.lang.String primaryGroupName) {
		return null;
	}

	@Operation(translated = "findUsersByNationalID")
	@DaoFinder("select usuari from com.soffid.iam.model.UserEntity usuari "
			+ "join usuari.userData as dadaUsuari "
			+ "where dadaUsuari.dataType.name = 'NIF' and dadaUsuari.value = :nif and "
			+ "usuari.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> findUsuarisByNIF(
			java.lang.String nif) {
		return null;
	}

	@Operation(translated = "findUsersGroupAndSubgroupsByGroupCode")
	@DaoFinder("- CUSTOM -")
	public java.util.List<es.caib.seycon.ng.model.UsuariEntity> findUsuarisGrupISubgrupsByCodiGrup(
			java.lang.String codiGrup) {
		return null;
	}

	@Operation(translated = "getNextUserIDRequest")
	@DaoFinder("- CUSTOM -")
	public java.lang.String getSeguentNumSolicitudVerificarIdentitatUsuari() {
		return null;
	}

	@DaoFinder("- CUSTOM -")
	public es.caib.seycon.ng.comu.EstatContrasenya getPasswordsStatus(
			es.caib.seycon.ng.model.UsuariEntity usuariEntity,
			es.caib.seycon.ng.model.DominiContrasenyaEntity dominiContrasenyes) {
		return null;
	}

	@DaoFinder("select ue\n"
			+ "from com.soffid.iam.model.UserEntity ue\n"
			+ "join ue.accounts as accounts\n"
			+ "join accounts.account as acc\n"
			+ "join acc.system as dispatcher\n"
			+ "where acc.type='U' and "
			+ "dispatcher.name=:system and "
			+ "acc.name=:account and "
			+ "dispatcher.tenant.id = :tenantId\n")
	public es.caib.seycon.ng.model.UsuariEntity findByAccount(
			java.lang.String account, java.lang.String system) {
		return null;
	}

	public java.lang.String getFullName() {
		return null;
	}
	
	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }
}

@Index (name="USU_UK_CODI",	unique=true,
entity=es.caib.seycon.ng.model.UsuariEntity.class,
columns={"USU_TEN_ID", "USU_CODI"})
abstract class UsuariIndex {
}


@Index (name="USU_UK_NOMCUR_IDDCO",	unique=true,
entity=es.caib.seycon.ng.model.UsuariEntity.class,
columns={"USU_TEN_ID", "USU_NOMCUR", "USU_IDDCO"})
abstract class UsuariNomCurtIndex {
}

