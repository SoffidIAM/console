package com.soffid.iam.api;

import java.util.List;

import com.soffid.mda.annotation.ValueObject;

import es.caib.seycon.ng.comu.TipusDada;

@ValueObject
public class IssueActionDefinition {
	String name;
	
	String label;
	
	List<TipusDada> parameters;
	
	List<String> issueTypes;
	
	String handler;
}
