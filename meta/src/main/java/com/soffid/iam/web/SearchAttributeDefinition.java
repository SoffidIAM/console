package com.soffid.iam.web;

import java.util.List;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.TypeEnumeration;

@ValueObject
public class SearchAttributeDefinition {
	String name;
	
	@Nullable String localizedName;
	@Nullable String labelName;
	
	TypeEnumeration type;
	
	@Nullable List<String> values;
}
