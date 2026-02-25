//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity TenantServerEntity
 * @see com.soffid.iam.base.model.TenantServerEntity
 */
public interface TenantServerEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.TenantServerEntity} .
	 */
	public com.soffid.iam.base.model.TenantServerEntity newTenantServerEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.TenantServerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.TenantServerEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.TenantServerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.TenantServerEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.TenantServerEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.TenantServerEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.TenantServerEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.TenantServerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.TenantServerEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.TenantServerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.TenantServerEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.TenantServerEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.TenantServerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.TenantServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.TenantServerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
