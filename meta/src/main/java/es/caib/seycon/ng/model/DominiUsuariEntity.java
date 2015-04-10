//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DOMUSU", translatedName="UserDomainEntity", translatedPackage="com.soffid.iam.model")
@Depends ({es.caib.seycon.ng.model.DominiContrasenyaEntity.class,
	es.caib.seycon.ng.model.AuditoriaEntity.class,
	es.caib.seycon.ng.comu.DominiUsuari.class,
	es.caib.seycon.ng.model.TipusUsuariEntity.class,
	es.caib.seycon.ng.model.DispatcherEntity.class})
public abstract class DominiUsuariEntity {

	@Column (name="DOU_ID")
	@Identifier
	public java.lang.Long id;

	@Column (name="DOU_CODI", length=50, translated="name")
	public java.lang.String codi;

	@Column (name="DOU_DESC", length=100, translated="description")
	@Nullable
	public java.lang.String descripcio;

	@Column (name="DOU_TIPUS", length=1, translated="type")
	@Nullable
	public es.caib.seycon.ng.comu.TipusDominiUsuariEnumeration tipus;

	@ForeignKey (foreignColumn="DIS_DOU_ID", translated="systems")
	public java.util.Collection<es.caib.seycon.ng.model.DispatcherEntity> dispatchers;

	@Column (name="DOU_EXPRES", length=1024)
	@Nullable
	public java.lang.String bshExpr;

	@Column (name="DOU_GENERA", length=64)
	@Nullable
	public java.lang.String beanGenerator;

	@Operation(translated="findByName")
	@DaoFinder
	public es.caib.seycon.ng.model.DominiUsuariEntity findByCodi(
		java.lang.String name) {
	 return null;
	}
	@Operation(translated="findBySytem")
	@DaoFinder("select du "
			+ "from com.soffid.iam.model.SystemEntity as dispatcher "
			+ "left join dispatcher.userDomain as du "
			+ "where dispatcher.name=:system")
	public es.caib.seycon.ng.model.DominiUsuariEntity findByDispatcher(
		java.lang.String system) {
	 return null;
	}
}
