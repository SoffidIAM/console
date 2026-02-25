//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity CustomObjectTypeEntity
 * @see com.soffid.iam.iga.model.CustomObjectTypeEntity
 */
public interface CustomObjectTypeEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.CustomObjectTypeEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.CustomObjectType} object 
	 */
	public void toCustomObjectType(com.soffid.iam.iga.model.CustomObjectTypeEntity source, com.soffid.iam.iga.api.CustomObjectType target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObjectType} object 
	 */
	public com.soffid.iam.iga.api.CustomObjectType toCustomObjectType(com.soffid.iam.iga.model.CustomObjectTypeEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.CustomObjectType} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.CustomObjectType> toCustomObjectTypeList (java.util.Collection<com.soffid.iam.iga.model.CustomObjectTypeEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.CustomObjectType} object 
	 */
	public void customObjectTypeToEntity (com.soffid.iam.iga.api.CustomObjectType source, com.soffid.iam.iga.model.CustomObjectTypeEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObjectType} object 
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity customObjectTypeToEntity (com.soffid.iam.iga.api.CustomObjectType instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.CustomObjectType} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectTypeEntity>  customObjectTypeToEntityList (java.util.Collection<com.soffid.iam.iga.api.CustomObjectType> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} .
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity newCustomObjectTypeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.CustomObjectTypeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.CustomObjectTypeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.CustomObjectTypeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.CustomObjectTypeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectTypeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectTypeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectTypeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.CustomObjectTypeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectTypeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.CustomObjectTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.CustomObjectTypeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
