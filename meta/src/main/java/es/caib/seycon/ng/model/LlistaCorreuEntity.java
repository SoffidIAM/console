//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.servei.AplicacioService;
import es.caib.seycon.ng.servei.GrupService;

@Entity (table="SC_LLICOR", translatedName="EmailListEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.UsuariEntity.class,
	es.caib.seycon.ng.model.DominiCorreuEntity.class,
	es.caib.seycon.ng.model.CorreuExternEntity.class,
	es.caib.seycon.ng.comu.LlistaCorreu.class,
	es.caib.seycon.ng.model.RelacioLlistaCorreuEntity.class,
	es.caib.seycon.ng.model.LlistaCorreuUsuariEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.model.TasqueEntity.class,
	GrupService.class,
	AplicacioService.class})
public abstract class LlistaCorreuEntity {

	@Column (name="LCO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="LCO_DESCRI", length=50, translated="description")
	@Nullable
	public java.lang.String descripcio;

	@Column (name="LCO_NOM", length=25, translated="name")
	public java.lang.String nom;

	@ForeignKey (foreignColumn="ELC_IDLCO", translated="externals")
	public java.util.Collection<es.caib.seycon.ng.model.CorreuExternEntity> externs;

	@Column (name="LCO_IDDCO", translated="domain")
	@Nullable
	public es.caib.seycon.ng.model.DominiCorreuEntity domini;
	
	@Column (name="LCO_TEN_ID")
	TenantEntity tenant;

	@ForeignKey (foreignColumn="ULC_IDLCO", translated="userMailLists")
	public java.util.Collection<es.caib.seycon.ng.model.LlistaCorreuUsuariEntity> llistaDeCorreuUsuari;

	@ForeignKey (foreignColumn="LLC_IDLCO1", translated="mailListContent")
	public java.util.Collection<es.caib.seycon.ng.model.RelacioLlistaCorreuEntity> relacioLlistaCorreuFromConte;

	@ForeignKey (foreignColumn="LLC_IDLCO2", translated="mailListPertain")
	public java.util.Collection<es.caib.seycon.ng.model.RelacioLlistaCorreuEntity> relacioLlistaCorreuFromPertany;

	@Operation(translated="findByName")
	public java.util.List<es.caib.seycon.ng.model.LlistaCorreuEntity> findByNom(
		java.lang.String name) {
	 return null;
	}
	@Operation(translated="findByData")
	@DaoFinder("select llistaCorreu "
			+ "from com.soffid.iam.model.EmailListEntity llistaCorreu "
			+ "left join llistaCorreu.domain as domini "
			+ "where (:domain is null or domini.name like :domain) and "
			+ "(:description is null or llistaCorreu.description like :description) and "
			+ "(:name is null or llistaCorreu.name like :name) and "
			+ "llistaCorreu.tenant.id = :tenantId "
			+ "order by llistaCorreu.name, llistaCorreu.domain.name")
	public java.util.List<es.caib.seycon.ng.model.LlistaCorreuEntity> findByDades(
		java.lang.String name, 
		java.lang.String domain, 
		java.lang.String description) {
	 return null;
	}
	
	@Operation(translated="findByNameAndDomain")
	@DaoFinder("select llistaCorreu "
			+ "from com.soffid.iam.model.EmailListEntity llistaCorreu "
			+ "left join llistaCorreu.domain as dominiCorreu "
			+ "where llistaCorreu.name = :name and "
			+ "(( :domain is null and dominiCorreu is null) or "
			+ " ( :domain is not null and  dominiCorreu is not null and  dominiCorreu.name = :domain)) and "
			+ "llistaCorreu.tenant.id = :tenantId "
			+ "order by dominiCorreu.name, llistaCorreu.name")
	public es.caib.seycon.ng.model.LlistaCorreuEntity findByNomAndCodiDomini(
		java.lang.String name, 
		java.lang.String domain) {
	 return null;
	}
	
	
	@DaoOperation
	public void generateUpdateTasks (LlistaCorreuEntity entity)
	{
		
	}
}

@Index (name="LCO_UK_NOM_IDDCO",	unique=true,
entity=es.caib.seycon.ng.model.LlistaCorreuEntity.class,
columns={"LCO_NOM", "LCO_IDDCO"})
abstract class LlistaCorreuIndex {
}

