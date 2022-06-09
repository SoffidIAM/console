//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import java.util.Collection;
import java.util.Date;

import com.soffid.iam.model.TenantEntity;
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

	@Column (name="GRU_CODI", length=50, translated="name")
	public java.lang.String codi;

	@Column (name="GRU_UNIOFI", length=2, translated="driveLetter")
	@Nullable
	public java.lang.String unitatOfimatica;

	@Column (name="GRU_DESCRI", length=100, translated="description")
	@Nullable
	public java.lang.String descripcio;

	@ForeignKey (foreignColumn="GIM_IDGRU", translated="printers")
	public java.util.Collection<es.caib.seycon.ng.model.GrupImpressoraEntity> impressores;

	@ForeignKey (foreignColumn="UGR_IDGRU", translated="secondaryGroupUsers")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariGrupEntity> usuarisGrupSecundari;

	@Column (name="GRU_IDMAQ", translated="driveServer")
	@Nullable
	public es.caib.seycon.ng.model.MaquinaEntity servidorOfimatic;

	@ForeignKey (foreignColumn="AXA_IDGRU", translated="networkAuthorization")
	public java.util.Collection<es.caib.seycon.ng.model.XarxaACEntity> autoritzacionsXarxa;

	@Column (name="GRU_QUOTA", translated="quotaGroup")
	@Nullable
	public java.lang.Long quotaGrup;

	@ForeignKey (foreignColumn="GRU_PARE", translated="children")
	public java.util.Collection<es.caib.seycon.ng.model.GrupEntity> fills;

	@Column (name="GRU_PARE", translated="parent")
	@Nullable
	public es.caib.seycon.ng.model.GrupEntity pare;

	@ForeignKey (foreignColumn="RLU_ROLUSU_GRU", translated="usersRoles")
	public java.util.Collection<es.caib.seycon.ng.model.RolAccountEntity> rolsUsuaris;

	@Column (name="GRU_TIPUS", translated="unitType")
	@Nullable
	public es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity tipusUnitatOrganizativa;

	@Column (name="GRU_OBSOLET", length=5, translated="obsolete")
	public java.lang.String obsolet;

	@Column (name="GRU_ORGANITZATIU", length=300, translated="organizational")
	@Nullable
	public java.lang.String organitzatiu;

	@Column (name="GRU_STADAT")
	@Nullable
	public Date startDate;

	@Column (name="GRU_ENDDAT")
	@Nullable
	public Date endDate;

	@Column (name="GRU_TEN_ID")
	TenantEntity tenant;
	
	@ForeignKey (foreignColumn="GRD_IDGRUP", translated="systemGroup")
	public java.util.Collection<es.caib.seycon.ng.model.GrupDispatcherEntity> grupDispatcher;

	@ForeignKey (foreignColumn="RLG_GRUP", translated="grantedRoles")
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

	/// *********************************************************** METHODS **************************************
	
	
	@DaoFinder("from com.soffid.iam.model.GroupEntity grup where grup.parent.name = :parent "
			+ "and grup.tenant.id = :tenantId and grup.obsolete = 'N' "
			+ "order by grup.name")
	@Operation(translated="findByParent")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findSubGrupsByCodi(
		java.lang.String parent) {
	 return null;
	}

	@DaoFinder("from com.soffid.iam.model.GroupEntity grup where grup.parent.name = :parent "
			+ "and grup.tenant.id = :tenantId and "
			+ "(grup.startDate <= :d or grup.startDate = null) and (grup.endDate = null or grup.endDate > :d) "
			+ "order by grup.name")
	@Operation(translated="findByParent")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findByParentAndDate(
		java.lang.String parent, @Nullable Date d) {
	 return null;
	}

	@Operation(translated="findByType")
	@DaoFinder("from com.soffid.iam.model.GroupEntity grup where grup.unitType.name = :unitType and "
			+ "grup.unitType.tenant.id = :tenantId and grup.obsolete='N' "
			+ "order by grup.name")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findGrupsByTipus(
		java.lang.String unitType) {
	 return null;
	}
	@DaoOperation
	@Operation(translated="setParentGroup")
	public void setSuperGrup(
		java.lang.String codiSubGrup, 
		java.lang.String codiSuperGrup)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Operation(translated="findByName")
	@DaoFinder("from com.soffid.iam.model.GroupEntity grup "
			+ "where grup.name = :name and "
			+ "grup.tenant.id = :tenantId and grup.obsolete='N' "
			+ "order by grup.name")
	public es.caib.seycon.ng.model.GrupEntity findByCodi(
		java.lang.String name) {
	 return null;
	}

	@DaoFinder("from com.soffid.iam.model.GroupEntity grup "
			+ "where grup.name = :name and "
			+ "grup.tenant.id = :tenantId and grup.obsolete='N' and "
			+ "(grup.startDate <= :d or grup.startDate = null) and (grup.endDate = null or grup.endDate > :d) "
			+ "order by grup.name")
	public es.caib.seycon.ng.model.GrupEntity findByNameAndDate(
		java.lang.String name, @Nullable Date d) {
	 return null;
	}

	@Operation(translated="findByCriteria")
	@DaoFinder("select grup "
			+ "from com.soffid.iam.model.GroupEntity grup "
			+ "left join grup.parent parent "
			+ "left join grup.unitType tipus "
			+ "where (:name is null or grup.name like :name) and "
			+ "(:description is null or grup.description like :description) and "
			+ "(:type is null or tipus.name like :type) and "
			+ "(:homeDrive is null or grup.driveLetter like :homeDrive) and "
			+ "(:parent is null or (:parent is not null and parent.name like :parent)) and "
			+ "(:obsolete is null or grup.obsolete = :obsolete) and "
			+ "grup.tenant.id = :tenantId "
			+ "order by grup.name")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findByFiltre(
		java.lang.String name, 
		java.lang.String parent, 
		java.lang.String homeDrive, 
		java.lang.String description, 
		java.lang.String type, 
		java.lang.String obsolete) {
	 return null;
	}

	@Operation(translated="findPrimaryGroupByUser")
	@DaoFinder("select usuari.primaryGroup from com.soffid.iam.model.UserEntity usuari "
			+ "where usuari.userName = :userName and "
			+ "usuari.tenant.id = :tenantId" )
	public es.caib.seycon.ng.model.GrupEntity findGrupPrimariByCodiUsuari(
		java.lang.String userName) {
	 return null;
	}
	@Operation(translated="findGroupsByUser")
	@DaoFinder("select  usuariGrup.group "
			+ "from com.soffid.iam.model.UserGroupEntity usuariGrup "
			+ "where usuariGrup.user.userName = :userName and usuariGrup.user.tenant.id = :tenantId")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findGrupsFromUsuarisByCodiUsuari(
		java.lang.String userName) {
	 return null;
	}

	@Operation(translated="findByGrantedRolesToUser")
	@DaoFinder("select grup "
			+ "from com.soffid.iam.model.UserEntity usu "
			+ "join usu.accounts as accounts "
			+ "join accounts.account as account with account.type='U' "
			+ "join account.roles as roles "
			+ "join roles.group as grup "
			+ "where usu.userName=:userName and usu.tenant.id = :tenantId "
			+ "order by grup.name\n")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findGrupsFromRolsByCodiUsuari(
		java.lang.String userName) {
	 return null;
	}

	@DaoFinder("select grup.parent from com.soffid.iam.model.GroupEntity grup "
			+ "where grup.name = :groupName and grup.obsolete = 'N' and grup.tenant.id = :tenantId")
	@Operation(translated="findByChild")
	public es.caib.seycon.ng.model.GrupEntity getSuperGrup(
		java.lang.String groupName) {
	 return null;
	}

	public java.lang.String toString() {
	 return null;
	}

	@Operation(translated="findByCriteria")
	@DaoFinder("select grup "
			+ "from com.soffid.iam.model.GroupEntity grup "
			+ "left join grup.parent parent "
			+ "left join grup.unitType tipus "
			+ "left join grup.driveServer servofim  "
			+ "where (:name is null or grup.name like :name) and "
			+ "(:description is null or grup.description like :description) and "
			+ "(:type is null or tipus.name like :type) and "
			+ "(:homeDrive is null or grup.driveLetter like :homeDrive) and "
			+ "(:parent is null or (:parent is not null and parent.name like :parent)) and "
			+ "(:obsolete is null or grup.obsolete = :obsolete) and "
			+ "(:homeServer is null or servofim.name like :homeServer) and "
			+ "(:budgetSection is null or grup.budgetSection like :budgetSection) and "
			+ "grup.tenant.id = :tenantId "
			+ "order by grup.name ")
	public java.util.List<es.caib.seycon.ng.model.GrupEntity> findByFiltre(
		java.lang.String name, 
		java.lang.String parent, 
		java.lang.String homeDrive, 
		java.lang.String description, 
		java.lang.String type, 
		java.lang.String obsolete, 
		java.lang.String homeServer, 
		java.lang.String budgetSection) {
	 return null;
	}

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }

	@DaoFinder("from com.soffid.iam.model.GroupEntity   where :text is null")
	public Collection<GrupEntity>findByText (String text) { return null; }

	@DaoFinder("select g.name "
			+ "from com.soffid.iam.model.GroupEntity as g "
			+ "where g.obsolete = 'N'")
	public Collection<String>findGroupNames () { return null; }

	public void customCache() {
	}
}


@Index (name="GRU_UK_CODI",	unique=false, entity=es.caib.seycon.ng.model.GrupEntity.class, columns={"GRU_TEN_ID", "GRU_CODI"})
abstract class GrupIndex {
}

