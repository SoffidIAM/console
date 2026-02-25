//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity ObjectMappingTriggerEntity
 * @see com.soffid.iam.iga.model.ObjectMappingTriggerEntity
 */
public interface ObjectMappingTriggerEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ObjectMappingTrigger} object 
	 */
	public void toObjectMappingTrigger(com.soffid.iam.iga.model.ObjectMappingTriggerEntity source, com.soffid.iam.iga.api.ObjectMappingTrigger target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMappingTrigger} object 
	 */
	public com.soffid.iam.iga.api.ObjectMappingTrigger toObjectMappingTrigger(com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ObjectMappingTrigger} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ObjectMappingTrigger> toObjectMappingTriggerList (java.util.Collection<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ObjectMappingTrigger} object 
	 */
	public void objectMappingTriggerToEntity (com.soffid.iam.iga.api.ObjectMappingTrigger source, com.soffid.iam.iga.model.ObjectMappingTriggerEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMappingTrigger} object 
	 */
	public com.soffid.iam.iga.model.ObjectMappingTriggerEntity objectMappingTriggerToEntity (com.soffid.iam.iga.api.ObjectMappingTrigger instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ObjectMappingTrigger} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity>  objectMappingTriggerToEntityList (java.util.Collection<com.soffid.iam.iga.api.ObjectMappingTrigger> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} .
	 */
	public com.soffid.iam.iga.model.ObjectMappingTriggerEntity newObjectMappingTriggerEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ObjectMappingTriggerEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ObjectMappingTriggerEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingTriggerEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingTriggerEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ObjectMappingTriggerEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ObjectMappingTriggerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ObjectMappingTriggerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
