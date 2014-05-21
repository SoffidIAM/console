//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_DEOBPR" )
@Depends ({es.caib.seycon.ng.model.DefaultObjectMappingEntity.class})
public abstract class DefaultObjectMappingPropertyEntity {

	@Column (name="DOP_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="DOP_PROPER", length=50)
	public java.lang.String property;

	@Column (name="DOP_VALUE", length=250)
	public java.lang.String value;

	@Column (name="DOP_DOM_ID")
	public es.caib.seycon.ng.model.DefaultObjectMappingEntity object;

}
