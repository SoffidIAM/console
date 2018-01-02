package com.soffid.iam.api;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.SoffidObjectTrigger;
import es.caib.seycon.ng.comu.SoffidObjectType;

@ValueObject
public class ReconcileTrigger {
	@Nullable
	public java.lang.Long id;

	@Nullable
	public SoffidObjectType objectType;

	public SoffidObjectTrigger trigger;

	public java.lang.String script;

	public String system;
}
