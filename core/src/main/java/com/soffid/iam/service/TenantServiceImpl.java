package com.soffid.iam.service;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.TenantCriteria;
import com.soffid.iam.model.ServerEntity;
import com.soffid.iam.model.TenantDisabledPermissionEntity;
import com.soffid.iam.model.TenantEntity;
import com.soffid.iam.model.TenantServerEntity;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class TenantServiceImpl extends TenantServiceBase {
	public final static String MASTER_NAME = "master";
	
	@Override
	protected Tenant handleGetMasterTenant() throws Exception {
		Tenant t = handleGetTenant(MASTER_NAME);
		return t;
	}

	@Override
	protected Tenant handleGetTenant(String name) throws Exception {
		TenantEntity t = getTenantEntityDao().findByName(name);
		if (t == null)
		{
			if ( name.equals(MASTER_NAME))
			{
				t = getTenantEntityDao().newTenantEntity();
				t.setDescription("Master tenant");
				t.setName(MASTER_NAME);
				t.setDescription("Autocreated master tenant");
				getTenantEntityDao().create(t);
			}
			else
				return null;
		}
		return getTenantEntityDao().toTenant(t);
	}

	@Override
	protected Tenant handleGetTenant(Long id) throws Exception {
		TenantEntity t = getTenantEntityDao().load(id);
		if (t == null)
			return null;
		return getTenantEntityDao().toTenant(t);
	}

	@Override
	protected Tenant handleCreate(Tenant tenant) throws Exception {
		if (! MASTER_NAME.equals (Security.getCurrentTenantName()))
		{
			throw new SecurityException("Not allowed to create tenants from "+Security.getCurrentTenantName()+" tenant");
		}
		TenantEntity entity = getTenantEntityDao().tenantToEntity(tenant);
		getTenantEntityDao().create(entity);
		Tenant t = getTenantEntityDao().toTenant(entity);
		getApplicationBootService().tenantBoot(t);
		return t;
	}

	@Override
	protected List<Tenant> handleFind(TenantCriteria criteria) throws Exception {
		List<TenantEntity> entitiesList = getTenantEntityDao().findByCriteria(criteria);
		if (! MASTER_NAME.equals (Security.getCurrentTenantName()))
		{
			for (Iterator<TenantEntity> it = entitiesList.iterator(); it.hasNext(); )
			{
				TenantEntity entity = it.next();
				if (!entity.getName().equals(Security.getCurrentTenantName()))
					it.remove();
			}
		}
		return getTenantEntityDao().toTenantList(entitiesList);
		
	}

	@Override
	protected Tenant handleUpdate(Tenant tenant) throws Exception {
		if (! MASTER_NAME.equals (Security.getCurrentTenantName()))
		{
			throw new SecurityException("Not allowed to update tenants from "+Security.getCurrentTenantName()+" tenant");
		}
		disabledPermissions.remove(tenant.getId());
		TenantEntity entity = getTenantEntityDao().tenantToEntity(tenant);
		getTenantEntityDao().update(entity);
		return getTenantEntityDao().toTenant(entity);
	}

	@Override
	protected void handleDisablePermission(Tenant tenant, String permission)
			throws Exception {
		TenantEntity te = getTenantEntityDao().load(tenant.getId());
		if (te.getName().equals("master"))
			throw new InternalErrorException("Permissions cannot be disabled for Master tenant");
		TenantDisabledPermissionEntity tep = getTenantDisabledPermissionEntityDao().newTenantDisabledPermissionEntity();
		tep.setAppliesTo(te);
		tep.setPermission(permission);
		getTenantDisabledPermissionEntityDao().create(tep);
		disabledPermissions.remove(tenant.getId());
	}

	@Override
	protected void handleEnablePermission(Tenant tenant, String permission)
			throws Exception {
		TenantEntity te = getTenantEntityDao().load(tenant.getId());
		for ( TenantDisabledPermissionEntity tep: te.getDisabledPermissions())
		{
			if (tep.getPermission().equals(permission))
			{
				te.getDisabledPermissions().remove(tep);
//				getTenantDisabledPermissionEntityDao().remove(tep);
				break;
			}
		}
		disabledPermissions.remove(tenant.getId());
	}

	
	Map<Long, List<String>> disabledPermissions = new HashMap<Long, List<String>>();
	@Override
	protected List<String> handleGetDisabledPermissions(Tenant tenant)
			throws Exception {
		List<String> result = disabledPermissions.get(tenant.getId());
		
		if (result == null)
		{
			result = new LinkedList<String>();
		
			TenantEntity te = getTenantEntityDao().load(tenant.getId());
			for (TenantDisabledPermissionEntity tep: te.getDisabledPermissions())
			{
				result.add(tep.getPermission());
			}
			
			if ( ! result.isEmpty())
				result.add(Security.AUTO_AUTHORIZATION_ALL);
			
			disabledPermissions.put(tenant.getId(), result);
		}
		
		return result;
	}

	@Override
	protected void handleAddTenantServer(Tenant tenant, String server)
			throws Exception {
		TenantEntity te = getTenantEntityDao().load(tenant.getId());
		ServerEntity serverEntity = getServerEntityDao().findByName(server);
		if (serverEntity == null)
			throw new InternalErrorException(String.format("Server %s does not exist", server));
		TenantServerEntity tep = getTenantServerEntityDao().newTenantServerEntity();
		tep.setServerTenant(te);
		tep.setTenantServer(serverEntity);
		getTenantServerEntityDao().create(tep);
	}

	@Override
	protected List<String> handleGetTenantServers(Tenant tenant)
			throws Exception {
		List<String> result = new LinkedList<String>();
		
		TenantEntity te = getTenantEntityDao().load(tenant.getId());
		for (TenantServerEntity tep: te.getServers())
		{
			result.add(tep.getTenantServer().getName());
		}
		
		return result;
	}

	@Override
	protected Collection<Tenant> handleListTenants() throws Exception {
		if (Security.isUserInRole(Security.AUTO_TENANT_QUERY))
		{
			return getTenantEntityDao().toTenantList(
					getTenantEntityDao().loadAll());
		}
		else
		{
			String currentTenant = Security.getCurrentTenantName();
			return Collections.singleton(handleGetTenant(currentTenant));
		}
	}

	@Override
	protected void handleRemoveTenantServer(Tenant tenant, String server)
			throws Exception {
		TenantEntity te = getTenantEntityDao().load(tenant.getId());
		for ( TenantServerEntity tep: te.getServers())
		{
			if (tep.getTenantServer().getName().equals(server))
			{
				te.getServers().remove(tep);
				break;
			}
		}
	}

}
