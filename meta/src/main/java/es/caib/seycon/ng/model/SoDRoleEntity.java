//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SODROL" )
@Depends ({es.caib.seycon.ng.comu.SoDRole.class,
	es.caib.seycon.ng.model.RolEntity.class,
	es.caib.seycon.ng.model.SoDRuleEntity.class})
public abstract class SoDRoleEntity {

	@Column (name="SOR_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="SOR_ROL_ID")
	public es.caib.seycon.ng.model.RolEntity role;

	@Column (name="SOR_SOD_ID")
	public es.caib.seycon.ng.model.SoDRuleEntity rule;

}
