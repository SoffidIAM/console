package com.soffid.iam.web.popup;

import es.caib.seycon.ng.comu.TypeEnumeration;

public class BulkActionAttributeAction {
	public BulkActionAttributeAction(String name, TypeEnumeration type) {
		super();
		this.name = name;
		this.type = type;
	}

	String name;
	TypeEnumeration type;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public TypeEnumeration getType() {
		return type;
	}
	
	public void setType(TypeEnumeration type) {
		this.type = type;
	}
}