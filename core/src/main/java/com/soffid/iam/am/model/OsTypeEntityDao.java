//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity OsTypeEntity
 * @see com.soffid.iam.am.model.OsTypeEntity
 */
public interface OsTypeEntityDao

{
	/**
	 * Operation findOSTypeByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.OsTypeEntity findOSTypeByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.OsTypeEntity findOSTypeByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.OsType} object 
	 */
	public void toOsType(com.soffid.iam.am.model.OsTypeEntity source, com.soffid.iam.am.api.OsType target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.OsType} object 
	 */
	public com.soffid.iam.am.api.OsType toOsType(com.soffid.iam.am.model.OsTypeEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.OsType} list 
	 */
	public java.util.List<com.soffid.iam.am.api.OsType> toOsTypeList (java.util.Collection<com.soffid.iam.am.model.OsTypeEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.OsType} object 
	 */
	public void osTypeToEntity (com.soffid.iam.am.api.OsType source, com.soffid.iam.am.model.OsTypeEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.OsType} object 
	 */
	public com.soffid.iam.am.model.OsTypeEntity osTypeToEntity (com.soffid.iam.am.api.OsType instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.OsType} list 
	 */
	public java.util.List<com.soffid.iam.am.model.OsTypeEntity>  osTypeToEntityList (java.util.Collection<com.soffid.iam.am.api.OsType> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.OsTypeEntity} .
	 */
	public com.soffid.iam.am.model.OsTypeEntity newOsTypeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.OsTypeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.OsTypeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.OsTypeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.OsTypeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.OsTypeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.OsTypeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.OsTypeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.OsTypeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.OsTypeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.OsTypeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.OsTypeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.OsTypeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.OsTypeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.OsTypeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.OsTypeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
