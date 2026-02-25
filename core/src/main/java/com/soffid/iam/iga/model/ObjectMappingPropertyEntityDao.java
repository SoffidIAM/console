//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity ObjectMappingPropertyEntity
 * @see com.soffid.iam.iga.model.ObjectMappingPropertyEntity
 */
public interface ObjectMappingPropertyEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ObjectMappingProperty} object 
	 */
	public void toObjectMappingProperty(com.soffid.iam.iga.model.ObjectMappingPropertyEntity source, com.soffid.iam.iga.api.ObjectMappingProperty target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMappingProperty} object 
	 */
	public com.soffid.iam.iga.api.ObjectMappingProperty toObjectMappingProperty(com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMappingProperty} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ObjectMappingProperty> toObjectMappingPropertyList (java.util.Collection<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ObjectMappingProperty} object 
	 */
	public void objectMappingPropertyToEntity (com.soffid.iam.iga.api.ObjectMappingProperty source, com.soffid.iam.iga.model.ObjectMappingPropertyEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMappingProperty} object 
	 */
	public com.soffid.iam.iga.model.ObjectMappingPropertyEntity objectMappingPropertyToEntity (com.soffid.iam.iga.api.ObjectMappingProperty instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMappingProperty} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity>  objectMappingPropertyToEntityList (java.util.Collection<com.soffid.iam.iga.api.ObjectMappingProperty> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} .
	 */
	public com.soffid.iam.iga.model.ObjectMappingPropertyEntity newObjectMappingPropertyEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ObjectMappingPropertyEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ObjectMappingPropertyEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingPropertyEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingPropertyEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingPropertyEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingPropertyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingPropertyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
