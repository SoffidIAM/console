//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity ObjectMappingEntity
 * @see com.soffid.iam.iga.model.ObjectMappingEntity
 */
public interface ObjectMappingEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ObjectMapping} object 
	 */
	public void toObjectMapping(com.soffid.iam.iga.model.ObjectMappingEntity source, com.soffid.iam.iga.api.ObjectMapping target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMapping} object 
	 */
	public com.soffid.iam.iga.api.ObjectMapping toObjectMapping(com.soffid.iam.iga.model.ObjectMappingEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMapping} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ObjectMapping> toObjectMappingList (java.util.Collection<com.soffid.iam.iga.model.ObjectMappingEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ObjectMapping} object 
	 */
	public void objectMappingToEntity (com.soffid.iam.iga.api.ObjectMapping source, com.soffid.iam.iga.model.ObjectMappingEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMapping} object 
	 */
	public com.soffid.iam.iga.model.ObjectMappingEntity objectMappingToEntity (com.soffid.iam.iga.api.ObjectMapping instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMapping} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity>  objectMappingToEntityList (java.util.Collection<com.soffid.iam.iga.api.ObjectMapping> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} .
	 */
	public com.soffid.iam.iga.model.ObjectMappingEntity newObjectMappingEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ObjectMappingEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ObjectMappingEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ObjectMappingEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ObjectMappingEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ObjectMappingEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ObjectMappingEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
