//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity CustomObjectEntity
 * @see com.soffid.iam.iga.model.CustomObjectEntity
 */
public interface CustomObjectEntityDao

{
	/**
	 * Operation findByTypeAndName
	 * @param objectType
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.CustomObjectEntity findByTypeAndName(
		java.lang.String objectType, 
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity findByTypeAndName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String objectType, java.lang.String name)
	;
	/**
	 * Operation findByTypeAndNameDeleted
	 * @param objectType
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.CustomObjectEntity findByTypeAndNameDeleted(
		java.lang.String objectType, 
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity findByTypeAndNameDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String objectType, java.lang.String name)
	;
	/**
	 * Operation findCustomObjectNames
	 * @param type
	 * @return
	**/
	public java.util.List<java.lang.String> findCustomObjectNames(
		java.lang.String type)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<java.lang.String> findCustomObjectNames(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public void toCustomObject(com.soffid.iam.iga.model.CustomObjectEntity source, com.soffid.iam.iga.api.CustomObject target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public com.soffid.iam.iga.api.CustomObject toCustomObject(com.soffid.iam.iga.model.CustomObjectEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObject} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.CustomObject> toCustomObjectList (java.util.Collection<com.soffid.iam.iga.model.CustomObjectEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public void customObjectToEntity (com.soffid.iam.iga.api.CustomObject source, com.soffid.iam.iga.model.CustomObjectEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObject} object 
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity customObjectToEntity (com.soffid.iam.iga.api.CustomObject instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObject} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectEntity>  customObjectToEntityList (java.util.Collection<com.soffid.iam.iga.api.CustomObject> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} .
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity newCustomObjectEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.CustomObjectEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.CustomObjectEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.CustomObjectEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.CustomObjectEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.CustomObjectEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.CustomObjectEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
