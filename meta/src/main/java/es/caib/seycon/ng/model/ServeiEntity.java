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

	@Column (name="SER_CODI", length=10, translated="code")
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
	@Operation(translated="findByCode")
	@DaoFinder
	public es.caib.seycon.ng.model.ServeiEntity findByCodi(
		java.lang.String codi) {
	 return null;
	}
	@Operation(translated="findAllByCode")
	@DaoFinder("select servei from es.caib.seycon.ng.model.ServeiEntity servei where servei.codi like :codi order by servei.codi")
	public java.util.List<es.caib.seycon.ng.model.ServeiEntity> findAllByCodi(
		java.lang.String codi) {
	 return null;
	}
}
