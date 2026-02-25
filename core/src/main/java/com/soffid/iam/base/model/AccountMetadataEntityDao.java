//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity AccountMetadataEntity
 * @see com.soffid.iam.base.model.AccountMetadataEntity
 */
public interface AccountMetadataEntityDao

{
	/**
	 * Operation findByName
	 * @param system
	 * @param name
	 * @return
	**/
	public com.soffid.iam.base.model.AccountMetadataEntity findByName(
		java.lang.String system, 
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.AccountMetadataEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String name)
	;
	/**
	 * Operation findBySystem
	 * @param systemName
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> findBySystem(
		java.lang.String systemName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.DataType} object 
	 */
	public void toDataType(com.soffid.iam.base.model.AccountMetadataEntity source, com.soffid.iam.base.api.DataType target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.DataType} object 
	 */
	public com.soffid.iam.base.api.DataType toDataType(com.soffid.iam.base.model.AccountMetadataEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.DataType} list 
	 */
	public java.util.List<com.soffid.iam.base.api.DataType> toDataTypeList (java.util.Collection<com.soffid.iam.base.model.AccountMetadataEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.DataType} object 
	 */
	public void dataTypeToEntity (com.soffid.iam.base.api.DataType source, com.soffid.iam.base.model.AccountMetadataEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.DataType} object 
	 */
	public com.soffid.iam.base.model.AccountMetadataEntity dataTypeToEntity (com.soffid.iam.base.api.DataType instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.DataType} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity>  dataTypeToEntityList (java.util.Collection<com.soffid.iam.base.api.DataType> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} .
	 */
	public com.soffid.iam.base.model.AccountMetadataEntity newAccountMetadataEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AccountMetadataEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AccountMetadataEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AccountMetadataEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AccountMetadataEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AccountMetadataEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AccountMetadataEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AccountMetadataEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AccountMetadataEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AccountMetadataEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountMetadataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AccountMetadataEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
