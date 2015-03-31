//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SODRUL", translatedName="SoDRuleEntity", translatedPackage="com.soffid.iam.model" )
@Depends ({es.caib.seycon.ng.comu.SoDRule.class,
	es.caib.seycon.ng.model.AplicacioEntity.class,
	es.caib.seycon.ng.model.SoDRoleEntity.class})
public abstract class SoDRuleEntity {

	@Column (name="SOD_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="SOD_NAME")
	public java.lang.String name;

	@Column (name="SOD_RISK")
	public es.caib.seycon.ng.comu.SoDRisk risk;

	@Column (name="SOD_APL_ID")
	public es.caib.seycon.ng.model.AplicacioEntity application;

	@ForeignKey (foreignColumn="SOR_SOD_ID")
	public java.util.Collection<es.caib.seycon.ng.model.SoDRoleEntity> roles;

}
