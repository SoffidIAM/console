package com.soffid.iam.model;

import com.soffid.iam.api.CustomObjectType;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;

@Entity(table="SC_CUOBTY")
@Depends({
	CustomObjectType.class
})
public class CustomObjectTypeEntity {
	@Column (name="COT_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name="COT_NAME")
	public String name;
	
	@Column(name="COT_DESCR", length=250)
	public String description;

	@Column(name="COT_BUILTIN")
	public boolean builtin;

	@Column(name="COT_TEN_ID")
	public TenantEntity tenant;

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }

	public CustomObjectTypeEntity findByName (String name) {return null;}
}

@Index(columns={"COT_TEN_ID","COT_NAME"}, unique=true, name="SC_CUOBTY_UK", entity=CustomObjectTypeEntity.class)
class CustomObjectTypeEntityKey {
}
