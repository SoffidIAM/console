//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_OBMAPR", translatedName="ObjectMappingPropertyEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.ObjectMappingProperty.class,
	es.caib.seycon.ng.model.ObjectMappingEntity.class})
public abstract class ObjectMappingPropertyEntity {

	@Column (name="OMP_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="OMP_PROPER", length=50)
	public java.lang.String property;

	@Column (name="OMP_VALUE", length=1500)
	public java.lang.String value;

	@Column (name="OMP_OBM_ID")
	public es.caib.seycon.ng.model.ObjectMappingEntity object;

}
