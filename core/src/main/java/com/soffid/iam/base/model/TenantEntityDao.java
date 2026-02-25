//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity TenantEntity
 * @see com.soffid.iam.base.model.TenantEntity
 */
public interface TenantEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.base.model.TenantEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.TenantEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findByServer
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.TenantEntity> findByServer(
		java.lang.String server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.TenantEntity> findByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Tenant} object 
	 */
	public void toTenant(com.soffid.iam.base.model.TenantEntity source, com.soffid.iam.base.api.Tenant target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Tenant} object 
	 */
	public com.soffid.iam.base.api.Tenant toTenant(com.soffid.iam.base.model.TenantEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Tenant} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Tenant> toTenantList (java.util.Collection<com.soffid.iam.base.model.TenantEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Tenant} object 
	 */
	public void tenantToEntity (com.soffid.iam.base.api.Tenant source, com.soffid.iam.base.model.TenantEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Tenant} object 
	 */
	public com.soffid.iam.base.model.TenantEntity tenantToEntity (com.soffid.iam.base.api.Tenant instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Tenant} list 
	 */
	public java.util.List<com.soffid.iam.base.model.TenantEntity>  tenantToEntityList (java.util.Collection<com.soffid.iam.base.api.Tenant> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.TenantEntity} .
	 */
	public com.soffid.iam.base.model.TenantEntity newTenantEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.TenantEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.TenantEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.TenantEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.TenantEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.TenantEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.TenantEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.TenantEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.TenantEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.TenantEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.TenantEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.TenantEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.TenantEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.TenantEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.TenantEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.TenantEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
