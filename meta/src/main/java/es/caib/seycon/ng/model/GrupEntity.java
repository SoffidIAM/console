//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_GRUPS", translatedName="GroupEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.Grup.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	es.caib.seycon.ng.model.UsuariGrupEntity.class,
	es.caib.seycon.ng.model.MaquinaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.comu.Identitat.class,
	es.caib.seycon.ng.comu.ValorDomini.class,
	es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.RolsGrupEntity.class,
	es.caib.seycon.ng.model.GrupImpressoraEntity.class,
	es.caib.seycon.ng.model.SsoEntity.class,
	es.caib.seycon.ng.model.XarxaACEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class,
	es.caib.seycon.ng.model.RolAccountEntity.class,
	es.caib.seycon.ng.model.GrupDispatcherEntity.class,
	es.caib.seycon.ng.model.AccountAccessEntity.class,
	LlistaCorreuEntity.class})
public abstract class GrupEntity {

	@Column (name="GRU_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="GRU_CODI", length=20, translated="code")
	public java.lang.String codi;

	@Column (name="GRU_UNIOFI", length=2, translated="officeUnit")
	@Nullable
	public java.lang.String unitatOfimatica;

	@Column (name="GRU_DESCRI", length=100, translated="description")
	@Nullable
	public java.lang.String descripcio;

	@ForeignKey (foreignColumn="GIM_IDGRU", translated="printers")
	public java.util.Collection<es.caib.seycon.ng.model.GrupImpressoraEntity> impressores;

	@ForeignKey (foreignColumn="UGR_IDGRU", translated="secondaryGroupUsers")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariGrupEntity> usuarisGrupSecundari;

	@Column (name="GRU_IDMAQ", translated="officeServer")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity servidorOfimatic;

	@ForeignKey (foreignColumn="AXA_IDGRU", translated="networkAuthorization")
	public java.util.Collection<es.caib.seycon.ng.model.XarxaACEntity> autoritzacionsXarxa;

	@Column (name="GRU_QUOTA", translated="quotaGroup")
	@Nullable
	public java.lang.Long quotaGrup;

	@ForeignKey (foreignColumn="GRU_PARE", translated="childrens")
	public java.util.Collection<es.caib.seycon.ng.model.GrupEntity> fills;

	@Column (name="GRU_PARE", translated="parent")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity pare;

	@ForeignKey (foreignColumn="RLU_ROLUSU_GRU", translated="usersRoles")
	public java.util.Collection<es.caib.seycon.ng.model.RolAccountEntity> rolsUsuaris;

	@Column (name="GRU_TIPUS", translated="organizatinalUnitType")
	@Nullable
	public es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity tipusUnitatOrganizativa;

	@Column (name="GRU_OBSOLET", length=5, translated="obsolete")
	public java.lang.String obsolet;

	@Column (name="GRU_ORGANITZATIU", length=300, translated="organizational")
	@Nullable
	public java.lang.String organitzatiu;

	@ForeignKey (foreignColumn="GRD_IDGRUP", translated="systemGroup")
	public java.util.Collection<es.caib.seycon.ng.model.GrupDispatcherEntity> grupDispatcher;

	@ForeignKey (foreignColumn="RLG_GRUP", translated="allowedRolesToGroup")
	public java.util.Collection<es.caib.seycon.ng.model.RolsGrupEntity> rolsOtorgatsGrup;

	@Column (name="GRU_SECPRE", length=50, translated="budgetSection")
	@Nullable
	public java.lang.String seccioPressupostaria;

	@ForeignKey (foreignColumn="USU_IDGRU", translated="primaryGroupUsers")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariEntity> usuarisGrupPrimari;

	@ForeignKey (foreignColumn="AAC_GRU_ID")
	public java.util.Collection<es.caib.seycon.ng.model.AccountAccessEntity> accountAccess;

	@Description("This foreign key binds a group with all the role assignments that are granted to members of this group because they are members of this group")
	@ForeignKey (foreignColumn="RLU_GROUP")
	public java.util.Collection<es.caib.seycon.ng.model.RolAccountEntity> holdedRoleAssignments;

	/*********************************************************** METHODS **************************************/
	
	
	@DaoFinder("from es.caib.seycon.ng.model.GrupEntity grup where grup.pare.codi = :codi")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findSubGrupsByCodi(
		java.lang.String codi) {
	 return null;
	}
	@Operation(translated="findGroupsByType")
	@DaoFinder("from es.caib.seycon.ng.model.GrupEntity where tipusUnitatOrganizativa = :tipus")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findGrupsByTipus(
		java.lang.String tipus) {
	 return null;
	}
	@DaoOperation
	public void setSuperGrup(
		java.lang.String codiSubGrup, 
		java.lang.String codiSuperGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}
	@DaoFinder
	public es.caib.seycon.ng.model.GrupEntity findById(
		java.lang.Long id) {
	 return null;
	}
	@Operation(translated="findByCode")
	@DaoFinder
	public es.caib.seycon.ng.model.GrupEntity findByCodi(
		java.lang.String codi) {
	 return null;
	}
	@Operation(translated="findByCriteria")
	@DaoFinder("select grup\nfrom\nes.caib.seycon.ng.model.GrupEntity grup\nleft join grup.pare pare\nleft join grup.tipusUnitatOrganizativa tipus \nwhere\n(:codi is null or grup.codi like :codi) and\n(:descripcio is null or grup.descripcio like :descripcio) and\n(:tipus is null or tipus.codi like :tipus) and\n(:unitatOfimatica is null or grup.unitatOfimatica like :unitatOfimatica) and\n(:pare is null or (:pare is not null and pare.codi like :pare)) and \n(:obsolet is null or grup.obsolet = :obsolet)")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findByFiltre(
		java.lang.String codi, 
		java.lang.String pare, 
		java.lang.String unitatOfimatica, 
		java.lang.String descripcio, 
		java.lang.String tipus, 
		java.lang.String obsolet) {
	 return null;
	}
	@Operation(translated="findPrimaryGroupByUserCode")
	@DaoFinder("select usuari.grupPrimari from es.caib.seycon.ng.model.UsuariEntity usuari where usuari.codi = :codiUsuari")
	public es.caib.seycon.ng.model.GrupEntity findGrupPrimariByCodiUsuari(
		java.lang.String codiUsuari) {
	 return null;
	}
	@Operation(translated="findGroupsFromUsersByUserCode")
	@DaoFinder("select  usuariGrup.grup from es.caib.seycon.ng.model.UsuariGrupEntity usuariGrup where usuariGrup.usuari.codi = :codiUsuari")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findGrupsFromUsuarisByCodiUsuari(
		java.lang.String codiUsuari) {
	 return null;
	}
	@Operation(translated="findGroupsFromRolesByUserCode")
	@DaoFinder("select grup\nfrom es.caib.seycon.ng.model.UsuariEntity usu\njoin usu.accounts as accounts\njoin accounts.account as account with account.type='U'\njoin account.roles as roles\njoin roles.grup as grup\nwhere usu.codi=:codiUsuari\n")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findGrupsFromRolsByCodiUsuari(
		java.lang.String codiUsuari) {
	 return null;
	}
	@DaoFinder("select grup.pare from es.caib.seycon.ng.model.GrupEntity grup where grup.codi = :codiGrup")
	public es.caib.seycon.ng.model.GrupEntity getSuperGrup(
		java.lang.String codiGrup) {
	 return null;
	}
	@DaoFinder("select conselleria from es.caib.seycon.ng.model.GrupEntity conselleria, es.caib.seycon.ng.model.GrupEntity direccioGeneral where conselleria.tipusUnitatOrganizativa = 'CONSELLERIA' and direccioGeneral.pare = conselleria")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> getConselleriesAmbDireccionsGenerals() {
	 return null;
	}
	public java.lang.String toString() {
	 return null;
	}
	@Operation(translated="findByCriteria")
	@DaoFinder("select grup\nfrom\nes.caib.seycon.ng.model.GrupEntity grup\nleft join grup.pare pare\nleft join grup.tipusUnitatOrganizativa tipus\nleft join grup.servidorOfimatic servofim  \nwhere\n(:codi is null or upper(grup.codi) like upper(:codi)) and\n(:descripcio is null or grup.descripcio like :descripcio) and\n(:tipus is null or tipus.codi like :tipus) and\n(:unitatOfimatica is null or grup.unitatOfimatica like :unitatOfimatica) and\n(:pare is null or (:pare is not null and pare.codi like :pare)) and \n(:obsolet is null or grup.obsolet = :obsolet) and \n(:servidorOfimatic is null or servofim.nom like :servidorOfimatic) and \n(:seccioPressupostaria is null or grup.seccioPressupostaria like :seccioPressupostaria)")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findByFiltre(
		java.lang.String codi, 
		java.lang.String pare, 
		java.lang.String unitatOfimatica, 
		java.lang.String descripcio, 
		java.lang.String tipus, 
		java.lang.String obsolet, 
		java.lang.String servidorOfimatic, 
		java.lang.String seccioPressupostaria) {
	 return null;
	}
}
