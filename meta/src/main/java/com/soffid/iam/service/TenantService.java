package com.soffid.iam.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;

import roles.Tothom;
import roles.tenantCreate;
import roles.tenantQuery;
import roles.tenantUpdate;

import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.TenantCriteria;
import com.soffid.iam.model.TenantDisabledPermissionEntity;
import com.soffid.iam.model.TenantEntity;
import com.soffid.iam.model.TenantServerEntity;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Description;
import com.soffid.mda.annotation.Operation;
import com.soffid.mda.annotation.Service;

import es.caib.seycon.ng.model.ServerEntity;
import es.caib.seycon.ng.servei.ApplicationBootService;

@Service
@Depends ( {
	TenantEntity.class,
	ApplicationBootService.class,
	TenantDisabledPermissionEntity.class,
	TenantServerEntity.class,
	ServerEntity.class
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

	@Description("Gets the list of disabled permissions for a tenant")
	@Operation(grantees={tenantUpdate.class})
	public List<String> getDisabledPermissions (Tenant tenant) {
		return null;
	}

	@Description("Disables a permission on a tenant")
	@Operation(grantees={tenantUpdate.class})
	public void disablePermission (Tenant tenant, String permission) {
	}

	@Description("Enables a permission on a tenant")
	@Operation(grantees={tenantUpdate.class})
	public void enablePermission (Tenant tenant, String permission) {
	}

	@Description("List allowed tenant. Open to anybody, as everybody can query its own tenant, at least")
	@Operation(grantees={Tothom.class})
	public Collection<Tenant> listTenants() {
		return null;
	}
	
	@Description("Gets the list of servers for a tenant")
	@Operation(grantees={tenantUpdate.class})
	public List<String> getTenantServers (Tenant tenant) {
		return null;
	}

	@Description("Adds a server to a tenant")
	@Operation(grantees={tenantUpdate.class})
	public void addTenantServer (Tenant tenant, String server) {
	}

	@Description("Removes a server for a tenant")
	@Operation(grantees={tenantUpdate.class})
	public void removeTenantServer (Tenant tenant, String server) {
	}

	@Description("Exports tenant to a file")
	@Operation(grantees={tenantUpdate.class})
	void exportTenant ( Tenant tenant, OutputStream out ) {}

	@Description("Ipmorts a tenant to a file")
	@Operation(grantees={tenantCreate.class})
	Tenant importTenant ( InputStream in ) {return null;}

}
