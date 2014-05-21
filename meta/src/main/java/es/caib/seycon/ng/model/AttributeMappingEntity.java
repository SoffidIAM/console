//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_ATRMAP" )
@Depends ({es.caib.seycon.ng.comu.AttributeMapping.class,
	es.caib.seycon.ng.model.ObjectMappingEntity.class})
public abstract class AttributeMappingEntity {

	@Column (name="ATM_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="ATM_SOFATR")
	public java.lang.String soffidAttribute;

	@Column (name="ATM_SYSATR")
	public java.lang.String systemAttribute;

	@Column (name="ATM_DIRECT")
	public es.caib.seycon.ng.comu.AttributeDirection direction;

	@Column (name="ATM_OBM_ID")
	public es.caib.seycon.ng.model.ObjectMappingEntity object;

}
