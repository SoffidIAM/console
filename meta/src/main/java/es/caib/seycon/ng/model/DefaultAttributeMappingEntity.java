//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DEATMA" )
@Depends ({es.caib.seycon.ng.model.DefaultObjectMappingEntity.class})
public abstract class DefaultAttributeMappingEntity {

	@Column (name="DAM_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="DAM_SOFATR", length=250)
	public java.lang.String soffidAttribute;

	@Column (name="DAM_SYSATR", length=250)
	public java.lang.String systemAttribute;

	@Column (name="DAM_DIRECT", length=2)
	public es.caib.seycon.ng.comu.AttributeDirection direction;

	@Column (name="DAM_DOM_ID")
	public es.caib.seycon.ng.model.DefaultObjectMappingEntity defaultObjectMapping;

}
