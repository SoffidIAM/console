package com.soffid.iam.model;

import java.util.Collection;
import java.util.Date;

import com.soffid.iam.api.CustomObject;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;
import com.soffid.mda.annotation.Nullable;

@Entity(table="SC_CUSOBJ")
@Depends({CustomObject.class})
public class CustomObjectEntity {
	@Column (name="COB_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name="COB_NAME")
	public String name;
	
	@Column(name="COB_DESCR", length=250)
	public String description;

	@Column(name="COB_COT_ID", composition=true, reverseAttribute="objects")
	public CustomObjectTypeEntity type;

	@Description("Returns true if the permission on this object is granted")
	public boolean isAllowed(String permission) { return false; }
	
	@DaoFinder("-")
	public Collection<CustomObjectEntity> findByText(String objectType, String text) {return null;}

	@DaoFinder("select o "
			+ "from com.soffid.iam.model.CustomObjectEntity as o "
			+ "where o.type.name = :objectType and "
			+ "o.name = :name and o.type.tenant.id=:tenantId")
	public CustomObjectEntity findByTypeAndName(String objectType, String name) {return null;}

	@DaoFinder("select o.name "
			+ "from com.soffid.iam.model.CustomObjectEntity as o "
			+ "where o.type.name=:type and o.type.tenant.id=:tenantId")
	public java.util.List<String> findCustomObjectNames(String type) {
		return null;
	}
}

@Index(columns={"COB_COT_ID", "COB_NAME"}, unique=true, name="SC_CUSOBJ_UK", entity=CustomObjectEntity.class)
class CustomObjectEntityKey {
}
