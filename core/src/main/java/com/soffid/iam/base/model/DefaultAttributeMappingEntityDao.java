//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity DefaultAttributeMappingEntity
 * @see com.soffid.iam.base.model.DefaultAttributeMappingEntity
 */
public interface DefaultAttributeMappingEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} .
	 */
	public com.soffid.iam.base.model.DefaultAttributeMappingEntity newDefaultAttributeMappingEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.DefaultAttributeMappingEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.DefaultAttributeMappingEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.DefaultAttributeMappingEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.DefaultAttributeMappingEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultAttributeMappingEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.DefaultAttributeMappingEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.DefaultAttributeMappingEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.DefaultAttributeMappingEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultAttributeMappingEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.DefaultAttributeMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultAttributeMappingEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
