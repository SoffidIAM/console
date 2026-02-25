//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity BlobConfigurationEntity
 * @see com.soffid.iam.base.model.BlobConfigurationEntity
 */
public interface BlobConfigurationEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findByNameAndTenant
	 * @param name
	 * @param tenantId
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByNameAndTenant(
		java.lang.String name, 
		java.lang.Long tenantId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByNameAndTenant(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.Long tenantId)
	;
	/**
	 * Operation findByNameAndTenantName
	 * @param name
	 * @param tenantName
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByNameAndTenantName(
		java.lang.String name, 
		java.lang.String tenantName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> findByNameAndTenantName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String tenantName)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} .
	 */
	public com.soffid.iam.base.model.BlobConfigurationEntity newBlobConfigurationEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.BlobConfigurationEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.BlobConfigurationEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.BlobConfigurationEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.BlobConfigurationEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.BlobConfigurationEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.BlobConfigurationEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.BlobConfigurationEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.BlobConfigurationEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.BlobConfigurationEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.BlobConfigurationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.BlobConfigurationEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
