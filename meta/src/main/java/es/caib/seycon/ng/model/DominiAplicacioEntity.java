//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import com.soffid.mda.annotation.*;

@Entity(table = "SC_DOMAPP", translatedName = "ApplicationDomainEntity", translatedPackage = "com.soffid.iam.model")
@Depends({ es.caib.seycon.ng.comu.Domini.class,
		es.caib.seycon.ng.model.GrupEntity.class,
		es.caib.seycon.ng.model.AplicacioEntity.class,
		es.caib.seycon.ng.model.UsuariEntity.class,
		es.caib.seycon.ng.model.RolEntity.class,
		es.caib.seycon.ng.model.AuditoriaEntity.class,
		es.caib.seycon.ng.model.ValorDominiAplicacioEntity.class })
public abstract class DominiAplicacioEntity {

	@Column(name = "DOM_ID")
	@Identifier
	public java.lang.Long id;

	@ForeignKey(foreignColumn = "VDO_DOM", translated = "values")
	public java.util.Collection<es.caib.seycon.ng.model.ValorDominiAplicacioEntity> valors;

	@ForeignKey(foreignColumn = "ROL_DOMAPP", translated = "roles")
	public java.util.Collection<es.caib.seycon.ng.model.RolEntity> rols;

	@Column(name = "DOM_NOM", length = 30, translated = "name")
	public java.lang.String nom;

	@Column(name = "DOM_APP", translated = "informationSystem")
	@Nullable
	public es.caib.seycon.ng.model.AplicacioEntity aplicacio;

	@Column(name = "DOM_DESC", length = 50, translated = "description")
	@Nullable
	public java.lang.String descripcio;

	@Operation(translated = "findByDomainAndRole")
	@DaoFinder("select dominiAplicacio from com.soffid.iam.model.ApplicationDomainEntity as dominiAplicacio \n"
			+ "left join dominiAplicacio.roles as role "
			+ "where ((:roleName is null and role is null) or "
			+ "       (:roleName is not null and role.name = :roleName)) and "
			+ "  dominiAplicacio.name = :domainName and "
			+ "  dominiAplicacio.informationSystem.tenant.id = :tenantId")
	public es.caib.seycon.ng.model.DominiAplicacioEntity findByNomDominiAndNomRol(
			java.lang.String domainName, java.lang.String roleName) {
		return null;
	}

	@Operation(translated = "findByInformationSystem")
	@DaoFinder("select dominiAplicacio "
			+ "from com.soffid.iam.model.ApplicationDomainEntity dominiAplicacio "
			+ "left join dominiAplicacio.informationSystem aplicacio "
			+ "where aplicacio.name = :informationSystem and "
			+ "aplicacio.tenant.id = :tenantId "
			+ "order by dominiAplicacio.name")
	public java.util.List<es.caib.seycon.ng.model.DominiAplicacioEntity> findByCodiAplicacio(
			java.lang.String informationSystem) {
		return null;
	}

	@Operation(translated = "findByInformationSystemPattern")
	@DaoFinder("select dominiAplicacio "
			+ "from com.soffid.iam.model.ApplicationDomainEntity dominiAplicacio "
			+ "left join dominiAplicacio.informationSystem aplicacio "
			+ "where aplicacio.name like :informationSystem and "
			+ "aplicacio.tenant.id = :tenantId "
			+ "order by dominiAplicacio.name")
	public java.util.List<es.caib.seycon.ng.model.DominiAplicacioEntity> findByCodisAplicacions(
			java.lang.String informationSystem) {
		return null;
	}
	
	@DaoFinder("select dominiAplicacio "
			+ "from com.soffid.iam.model.ApplicationDomainEntity dominiAplicacio "
			+ "left join dominiAplicacio.informationSystem aplicacio "
			+ "where aplicacio.name = :informationSystem and "
			+ "dominiAplicacio.name = :name and "
			+ "aplicacio.tenant.id = :tenantId "
			+ "order by dominiAplicacio.name")
	public es.caib.seycon.ng.model.DominiAplicacioEntity findByName(
		String name,
		String informationSystem) {
	 return null;
	}

}

@Index (name="DOM_UK_NOM_APP",	unique=true,
entity=es.caib.seycon.ng.model.DominiAplicacioEntity.class,
columns={"DOM_APP", "DOM_NOM"})
abstract class DominiAplicacioIndex {
}
