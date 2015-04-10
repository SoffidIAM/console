//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;

import com.soffid.mda.annotation.*;

@Entity(table = "SC_TIPUSUO", translatedName = "GroupTypeEntity", translatedPackage = "com.soffid.iam.model")
@Depends({ es.caib.seycon.ng.comu.TipusUnitatOrganitzativa.class,
		es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity.class,
		es.caib.seycon.ng.model.GrupEntity.class })
public abstract class TipusUnitatOrganitzativaEntity {

	@Column(name = "TUO_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "TUO_CODI", length = 20, translated = "name")
	public java.lang.String codi;

	@Column(name = "TUO_DESC", length = 50, translated = "description")
	@Nullable
	public java.lang.String descripcio;

	@Column(name = "TUO_PARE", translated = "parent")
	@Nullable
	public es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity pare;

	@Description("True if business units of this type can hold user roles")
	@Column(name = "TUO_ROLHOL", defaultValue = "false")
	@Nullable
	public boolean roleHolder;

	@ForeignKey(foreignColumn = "TUO_PARE", translated = "childs")
	public java.util.Collection<es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity> fills;

	@ForeignKey(foreignColumn = "GRU_TIPUS", translated = "groupEntities")
	public java.util.Collection<es.caib.seycon.ng.model.GrupEntity> grupEntities;

	/************************* DAOS ***********************************/
	@Operation(translated = "findByName")
	@DaoFinder
	public es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity findByCodi(
			java.lang.String name) {
		return null;
	}

	@Operation(translated = "findByFilter")
	@DaoFinder("select uo from com.soffid.iam.model.GroupTypeEntity uo "
			+ "where (:name is null or (:name is not null and uo.name like :name)) and "
			+ " (:description is null or (:description is not null and uo.description like :description))")
	public java.util.List<es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity> findByFiltre(
			@Nullable java.lang.String name, @Nullable String description) {
		return null;
	}
}
