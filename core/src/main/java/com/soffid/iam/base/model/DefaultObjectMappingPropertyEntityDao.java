//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity DefaultObjectMappingPropertyEntity
 * @see com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity
 */
public interface DefaultObjectMappingPropertyEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} .
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity newDefaultObjectMappingPropertyEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.DefaultObjectMappingPropertyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
