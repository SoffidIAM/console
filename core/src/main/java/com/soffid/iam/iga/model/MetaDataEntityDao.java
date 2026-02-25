//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity MetaDataEntity
 * @see com.soffid.iam.iga.model.MetaDataEntity
 */
public interface MetaDataEntityDao

{
	/**
	 * Operation findDataTypeByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.MetaDataEntity findDataTypeByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.MetaDataEntity findDataTypeByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findByObjectTypeAndName
	 * @param type
	 * @param codi
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findByObjectTypeAndName(
		java.lang.String type, 
		java.lang.String codi)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findByObjectTypeAndName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type, java.lang.String codi)
	;
	/**
	 * Operation findByScope
	 * @param scope
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findByScope(
		com.soffid.iam.base.api.MetadataScope scope)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findByScope(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.api.MetadataScope scope)
	;
	/**
	 * Operation findDataTypesByName
	 * @param name
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findDataTypesByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findDataTypesByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findDataTypesByScopeAndName
	 * @param scope
	 * @param codi
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findDataTypesByScopeAndName(
		com.soffid.iam.base.api.MetadataScope scope, 
		java.lang.String codi)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> findDataTypesByScopeAndName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.api.MetadataScope scope, java.lang.String codi)
	;
	/**
	 * Operation renameAttributeValues
	 * @param type
	 * @param oldValue
	 * @param newValue
	**/
	public void renameAttributeValues(
		com.soffid.iam.base.api.TypeEnumeration type, 
		java.lang.String oldValue, 
		java.lang.String newValue) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.DataType} object 
	 */
	public void toDataType(com.soffid.iam.iga.model.MetaDataEntity source, com.soffid.iam.base.api.DataType target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.DataType} object 
	 */
	public com.soffid.iam.base.api.DataType toDataType(com.soffid.iam.iga.model.MetaDataEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.DataType} list 
	 */
	public java.util.List<com.soffid.iam.base.api.DataType> toDataTypeList (java.util.Collection<com.soffid.iam.iga.model.MetaDataEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.DataType} object 
	 */
	public void dataTypeToEntity (com.soffid.iam.base.api.DataType source, com.soffid.iam.iga.model.MetaDataEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.DataType} object 
	 */
	public com.soffid.iam.iga.model.MetaDataEntity dataTypeToEntity (com.soffid.iam.base.api.DataType instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.DataType} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity>  dataTypeToEntityList (java.util.Collection<com.soffid.iam.base.api.DataType> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} .
	 */
	public com.soffid.iam.iga.model.MetaDataEntity newMetaDataEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.MetaDataEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.MetaDataEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.MetaDataEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.MetaDataEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.MetaDataEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.MetaDataEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.MetaDataEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.MetaDataEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.MetaDataEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MetaDataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.MetaDataEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
