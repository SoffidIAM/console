package com.soffid.iam.model;

import com.soffid.iam.api.CustomObject;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.model.RolEntity;

@Entity(table="SC_CUSBRO")
@Depends({CustomObject.class})
public class CustomObjectRoleEntity {
	@Column (name="COR_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name="COR_COT_ID", reverseAttribute = "accessRoles")
	public CustomObjectTypeEntity customObjectType;
	
	@Column(name="COR_ROL_ID", reverseAttribute = "customObjects")
	public RolEntity role;
	
	@Column(name="COR_LEVEL")
	public AccountAccessLevelEnum level;

}
