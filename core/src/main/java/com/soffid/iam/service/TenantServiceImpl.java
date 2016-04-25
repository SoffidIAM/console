package com.soffid.iam.service;

import java.util.Iterator;
import java.util.List;

import com.soffid.iam.api.Tenant;
import com.soffid.iam.api.TenantCriteria;
import com.soffid.iam.model.TenantEntity;
import com.soffid.iam.utils.Security;

public class TenantServiceImpl extends TenantServiceBase {
	private final String MASTER_NAME = "master";
	
	@Override
	protected Tenant handleGetMasterTenant() throws Exception {
		Tenant t = handleGetTenant(MASTER_NAME);
		if ( t == null )
		{
			TenantEntity te = getTenantEntityDao().newTenantEntity();
			te.setDescription("Master tenant");
			te.setName(MASTER_NAME);
			te.setDescription("Autocreated master tenant");
			getTenantEntityDao().create(te);
			t = getTenantEntityDao().toTenant(te);
		}
		return t;
	}

	@Override
	protected Tenant handleGetTenant(String name) throws Exception {
		TenantEntity t = getTenantEntityDao().findByName(name);
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
		return getTenantEntityDao().toTenant(entity);
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
		TenantEntity entity = getTenantEntityDao().tenantToEntity(tenant);
		getTenantEntityDao().update(entity);
		return getTenantEntityDao().toTenant(entity);
	}

}
