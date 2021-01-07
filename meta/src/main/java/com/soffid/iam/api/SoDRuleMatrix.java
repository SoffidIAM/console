package com.soffid.iam.api;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class SoDRuleMatrix {
	@Nullable
	public java.lang.Long id;

	Long ruleId;
	
	public es.caib.seycon.ng.comu.SoDRisk risk;

	Long row;

	Long column;
}
