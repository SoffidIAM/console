//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SERVEI", translatedName="ServiceEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.comu.Servei.class,
	es.caib.seycon.ng.model.RegistreAccesEntity.class,
	es.caib.seycon.ng.model.SsoEntity.class})
public abstract class ServeiEntity {

	@Column (name="SER_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="SER_CODI", length=10, translated="name")
	public java.lang.String codi;

	@Column (name="SER_DESCRI", length=50, translated="description")
	@Nullable
	public java.lang.String descripcio;

	@Operation(translated="findByCriteria")
	@DaoFinder
	public java.util.List<es.caib.seycon.ng.model.ServeiEntity> findByCriteri(
		es.caib.seycon.ng.comu.ServeiSearchCriteria filtre) {
	 return null;
	}
	@Operation(translated="findByName")
	@DaoFinder
	public es.caib.seycon.ng.model.ServeiEntity findByCodi(
		java.lang.String name) {
	 return null;
	}
	@Operation(translated="findAllByName")
	@DaoFinder("select servei from com.soffid.iam.model.ServiceEntity servei where servei.name like :name order by servei.name")
	public java.util.List<es.caib.seycon.ng.model.ServeiEntity> findAllByCodi(
		java.lang.String name) {
	 return null;
	}
}
