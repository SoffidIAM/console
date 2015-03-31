//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_OSTYPE", translatedName="OsTypeEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.OsType.class,
	es.caib.seycon.ng.model.MaquinaEntity.class})
public abstract class OsTypeEntity {

	@Column (name="OST_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="OST_NAME", length=20)
	public java.lang.String name;

	@Column (name="OST_DESC", length=50)
	@Nullable
	public java.lang.String description;

	@ForeignKey (foreignColumn="MAQ_OST_ID")
	public java.util.Collection<es.caib.seycon.ng.model.MaquinaEntity> operatingSystemHost;

	@DaoFinder
	public es.caib.seycon.ng.model.OsTypeEntity findOSTypeByName(
		java.lang.String name) {
	 return null;
	}
}
