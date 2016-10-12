package com.soffid.iam.service;

import java.util.List;

import roles.tenantCreate;
import roles.tenantQuery;
import roles.tenantUpdate;

import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.TenantCriteria;
import com.soffid.iam.model.TenantEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.servei.ApplicationBootService;

@Service
@Depends ( {
	TenantEntity.class,
	ApplicationBootService.class
})
public class TenantService extends Object {
	@Operation(grantees={tenantQuery.class})
	@Description("Finds a tenant by name. Used mainly internally")
	public Tenant getTenant (String name) { return null; }

	@Operation(grantees={tenantQuery.class})
	@Description("Finds a tenant by name. Used mainly internally")
	public Tenant getTenant (Long id) { return null; }

	@Description("Gets master tenant")
	@Operation(grantees={tenantQuery.class})
	public Tenant getMasterTenant () { return null; }
	
	@Description("Find tenants")
	@Operation(grantees={tenantQuery.class})
	public List<Tenant> find (TenantCriteria criteria) {
		return null;
	}

	@Description("Create a new tenant")
	@Operation(grantees={tenantCreate.class})
	public Tenant create (Tenant tenant) {
		return null;
	}

	@Description("Updates a tenant")
	@Operation(grantees={tenantUpdate.class})
	public Tenant update (Tenant tenant) {
		return null;
	}
}
