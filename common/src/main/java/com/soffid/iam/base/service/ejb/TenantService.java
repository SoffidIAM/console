//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service.ejb;
/**
 * EJB TenantService
 */
public interface TenantService

 {

	com.soffid.iam.base.api.Tenant importTenant(
		final java.io.InputStream in)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Tenant create(
		final com.soffid.iam.base.api.Tenant tenant)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Tenant getMasterTenant()
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Tenant getTenant(
		final java.lang.Long id)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Tenant getTenant(
		final java.lang.String name)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.base.api.Tenant update(
		final com.soffid.iam.base.api.Tenant tenant)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.Collection<com.soffid.iam.base.api.Tenant> listTenants()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<java.lang.String> getDisabledPermissions(
		final com.soffid.iam.base.api.Tenant tenant)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<java.lang.String> getTenantServers(
		final com.soffid.iam.base.api.Tenant tenant)
	throws com.soffid.iam.exception.InternalErrorException;

	void addTenantServer(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String server)
	throws com.soffid.iam.exception.InternalErrorException;

	void disablePermission(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String permission)
	throws com.soffid.iam.exception.InternalErrorException;

	void enablePermission(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String permission)
	throws com.soffid.iam.exception.InternalErrorException;

	void remove(
		final com.soffid.iam.base.api.Tenant tenant)
	throws com.soffid.iam.exception.InternalErrorException;

	void removeTenantServer(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String server)
	throws com.soffid.iam.exception.InternalErrorException;

	void exportTenant(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.io.OutputStream out)
	throws com.soffid.iam.exception.InternalErrorException;

}
