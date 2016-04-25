package com.soffid.iam.model;

import java.util.List;

import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.TenantCriteria;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.DaoFinder;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Index;

@Entity(table="SC_TENANT")
@Depends({Tenant.class})
public class TenantEntity {
	@Column(name = "TEN_ID")
	@Identifier
	public java.lang.Long id;

	@Column(name = "TEN_NAME")
	public java.lang.String name;

	@Column(name = "TEN_DESCRI")
	public java.lang.String description;

	@DaoFinder
	public TenantEntity findByName (String name)
	{ return null;}
	
	@DaoFinder
	public List<TenantEntity> findByCriteria (TenantCriteria criteria)
	{
		return null;
	}
}

@Index (columns="TEN_NAME", unique = true, entity = TenantEntity.class, name = "SC_TEN_NAME_UK")
class TenantEntityUK {
	
}
