//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.service;
/**
 * Service TenantService
 */
public interface TenantService {
	public final static String SERVICE_NAME = "com.soffid.iam.base.service.TenantService";

	/**
	 * Operation importTenant
	 * Ipmorts a tenant to a file

	 * @param in 
	 * @return 
	 */
	com.soffid.iam.base.api.Tenant importTenant(
		final java.io.InputStream in)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create
	 * Create a new tenant

	 * @param tenant 
	 * @return 
	 */
	com.soffid.iam.base.api.Tenant create(
		final com.soffid.iam.base.api.Tenant tenant)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getMasterTenant
	 * Gets master tenant

	 * @return 
	 */
	com.soffid.iam.base.api.Tenant getMasterTenant()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getTenant
	 * Finds a tenant by name. Used mainly internally

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.base.api.Tenant getTenant(
		final java.lang.Long id)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getTenant
	 * Finds a tenant by name. Used mainly internally

	 * @param name 
	 * @return 
	 */
	com.soffid.iam.base.api.Tenant getTenant(
		final java.lang.String name)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update
	 * Updates a tenant

	 * @param tenant 
	 * @return 
	 */
	com.soffid.iam.base.api.Tenant update(
		final com.soffid.iam.base.api.Tenant tenant)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation listTenants
	 * List allowed tenant. Open to anybody, as everybody can query its own tenant, at least

	 * @return 
	 */
	java.util.Collection<com.soffid.iam.base.api.Tenant> listTenants()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getDisabledPermissions
	 * Gets the list of disabled permissions for a tenant

	 * @param tenant 
	 * @return 
	 */
	java.util.List<java.lang.String> getDisabledPermissions(
		final com.soffid.iam.base.api.Tenant tenant)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getTenantServers
	 * Gets the list of servers for a tenant

	 * @param tenant 
	 * @return 
	 */
	java.util.List<java.lang.String> getTenantServers(
		final com.soffid.iam.base.api.Tenant tenant)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addTenantServer
	 * Adds a server to a tenant

	 * @param tenant 
	 * @param server 
	 */
	void addTenantServer(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String server)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation disablePermission
	 * Disables a permission on a tenant

	 * @param tenant 
	 * @param permission 
	 */
	void disablePermission(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String permission)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation enablePermission
	 * Enables a permission on a tenant

	 * @param tenant 
	 * @param permission 
	 */
	void enablePermission(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String permission)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation remove
	 * Remove at tenant

	 * @param tenant 
	 */
	void remove(
		final com.soffid.iam.base.api.Tenant tenant)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation removeTenantServer
	 * Removes a server for a tenant

	 * @param tenant 
	 * @param server 
	 */
	void removeTenantServer(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.lang.String server)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation exportTenant
	 * Exports tenant to a file

	 * @param tenant 
	 * @param out 
	 */
	void exportTenant(
		final com.soffid.iam.base.api.Tenant tenant, 
		final java.io.OutputStream out)
			throws com.soffid.iam.exception.InternalErrorException;

}
