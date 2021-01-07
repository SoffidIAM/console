//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.iam.api.SodRuleType;
import com.soffid.mda.annotation.*;

import es.caib.seycon.ng.model.SoDRuleEntity;

@ValueObject (translatedName="SoDRule", translatedPackage="com.soffid.iam.api")
@JsonObject(hibernateClass = SoDRuleEntity.class)
public class SoDRule {

	@Nullable
	public java.lang.Long id;

	public java.lang.String name;

	@Nullable
	public Integer number;

	public es.caib.seycon.ng.comu.SoDRisk risk;

	@Nullable
	@Description("Type of SoDRule")
	SodRuleType type;

	@JsonAttribute(hibernateAttribute = "application.name")
	public java.lang.String application;

}
