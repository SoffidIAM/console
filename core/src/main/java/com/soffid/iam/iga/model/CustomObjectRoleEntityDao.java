//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity CustomObjectRoleEntity
 * @see com.soffid.iam.iga.model.CustomObjectRoleEntity
 */
public interface CustomObjectRoleEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public void toCustomObject(com.soffid.iam.iga.model.CustomObjectRoleEntity source, com.soffid.iam.iga.api.CustomObject target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public com.soffid.iam.iga.api.CustomObject toCustomObject(com.soffid.iam.iga.model.CustomObjectRoleEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObject} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.CustomObject> toCustomObjectList (java.util.Collection<com.soffid.iam.iga.model.CustomObjectRoleEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public void customObjectToEntity (com.soffid.iam.iga.api.CustomObject source, com.soffid.iam.iga.model.CustomObjectRoleEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public com.soffid.iam.iga.model.CustomObjectRoleEntity customObjectToEntity (com.soffid.iam.iga.api.CustomObject instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObject} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity>  customObjectToEntityList (java.util.Collection<com.soffid.iam.iga.api.CustomObject> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} .
	 */
	public com.soffid.iam.iga.model.CustomObjectRoleEntity newCustomObjectRoleEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.CustomObjectRoleEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.CustomObjectRoleEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.CustomObjectRoleEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.CustomObjectRoleEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectRoleEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectRoleEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectRoleEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectRoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
