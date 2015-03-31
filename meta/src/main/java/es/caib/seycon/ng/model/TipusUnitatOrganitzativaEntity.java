//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_TIPUSUO", translatedName="GroupTypeEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.TipusUnitatOrganitzativa.class,
	es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity.class,
	es.caib.seycon.ng.model.GrupEntity.class})
public abstract class TipusUnitatOrganitzativaEntity {

	@Column (name="TUO_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="TUO_CODI", length=20, translated="code")
	public java.lang.String codi;

	@Column (name="TUO_DESC", length=50, translated="description")
	@Nullable
	public java.lang.String descripcio;

	@Column (name="TUO_PARE", translated="parent")
	@Nullable
	public es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity pare;
	
	@Description("True if business units of this type can hold user roles")
	@Column (name="TUO_ROLHOL", defaultValue="false")
	@Nullable
	public boolean roleHolder;

	@ForeignKey (foreignColumn="TUO_PARE", translated="childs")
	public java.util.Collection<es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity> fills;

	@ForeignKey (foreignColumn="GRU_TIPUS", translated="groupEntities")
	public java.util.Collection<es.caib.seycon.ng.model.GrupEntity> grupEntities;

	/************************* DAOS ***********************************/
	@Operation(translated="findByCode")
	@DaoFinder
	public es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity findByCodi(
		java.lang.String codi) {
	 return null;
	}
	@Operation(translated="findByFilter")
	@DaoFinder("select uo from es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity uo where (:codi is null or (:codi is not null and uo.codi like :codi)) and (:descripcio is null or (:descripcio is not null and uo.descripcio like :descripcio))")
	public java.util.List<es.caib.seycon.ng.model.TipusUnitatOrganitzativaEntity> findByFiltre(
		@Nullable java.lang.String codi, 
		@Nullable String descripcio) {
	 return null;
	}
}
