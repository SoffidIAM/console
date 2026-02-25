//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity DefaultObjectMappingEntity
 * @see com.soffid.iam.base.model.DefaultObjectMappingEntity
 */
public interface DefaultObjectMappingEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} .
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingEntity newDefaultObjectMappingEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.DefaultObjectMappingEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.DefaultObjectMappingEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.DefaultObjectMappingEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.DefaultObjectMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
