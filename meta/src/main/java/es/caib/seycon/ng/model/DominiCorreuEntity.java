//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DOMCOR", translatedName="EmailDomainEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.DominiCorreu.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.LlistaCorreuEntity.class})
public abstract class DominiCorreuEntity {

	@Column (name="DCO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="DCO_DESCRI", length=50, translated="description")
	@Nullable
	public java.lang.String descripcio;

	@Column (name="DCO_CODI", length=50, translated="name")
	public java.lang.String codi;

	@ForeignKey (foreignColumn="USU_IDDCO", translated="users")
	public java.util.Collection<es.caib.seycon.ng.model.UsuariEntity> usuaris;

	@ForeignKey (foreignColumn="LCO_IDDCO", translated="mailLists")
	public java.util.Collection<es.caib.seycon.ng.model.LlistaCorreuEntity> llistesCorreu;

	@Column (name="DCO_OBSOLET", length=5, translated="obsolete")
	public java.lang.String obsolet;

	@Column (name="DCO_TEN_ID")
	TenantEntity tenant;
	
	@Operation(translated="findByCode")
	@DaoFinder
	public es.caib.seycon.ng.model.DominiCorreuEntity findByCodi(
		java.lang.String name) {
	 return null;
	}
	@Operation(translated="findByCriteria")
	@DaoFinder("from com.soffid.iam.model.EmailDomainEntity dominiCorreu "
			+ "where (:name is null or dominiCorreu.name like :name) and "
			+ "(:description is null or dominiCorreu.description like :description) and "
			+ "(:obsolete is null or dominiCorreu.obsolete = :obsolete) and "
			+ "dominiCorreu.tenant.id = :tenantId "
			+ "order by dominiCorreu.name")
	public java.util.List<es.caib.seycon.ng.model.DominiCorreuEntity> findByFiltre(
		java.lang.String name, 
		java.lang.String description, 
		java.lang.String obsolete) {
	 return null;
	}
}

@Index (name="DCO_UK_CODI",	unique=true,
entity=es.caib.seycon.ng.model.DominiCorreuEntity.class,
columns={"DCO_TEN_ID", "DCO_CODI"})
abstract class DominiCorreuIndex {
}

