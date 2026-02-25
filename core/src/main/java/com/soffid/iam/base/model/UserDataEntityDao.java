//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity UserDataEntity
 * @see com.soffid.iam.base.model.UserDataEntity
 */
public interface UserDataEntityDao

{
	/**
	 * Operation findByUserAndAttribute
	 * @param userId
	 * @param attributes
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> findByUserAndAttribute(
		java.lang.Long userId, 
		java.lang.String[] attributes)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> findByUserAndAttribute(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId, java.lang.String[] attributes)
	;
	/**
	 * Operation findByDataType
	 * @param userName
	 * @param dataType
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.UserDataEntity> findByDataType(
		java.lang.String userName, 
		java.lang.String dataType)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserDataEntity> findByDataType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String dataType)
	;
	/**
	 * Operation findByTypeAndValue
	 * @param dataType
	 * @param value
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> findByTypeAndValue(
		java.lang.String dataType, 
		java.lang.String value)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> findByTypeAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String dataType, java.lang.String value)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.UserData} object 
	 */
	public void toUserData(com.soffid.iam.base.model.UserDataEntity source, com.soffid.iam.base.api.UserData target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserData} object 
	 */
	public com.soffid.iam.base.api.UserData toUserData(com.soffid.iam.base.model.UserDataEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserData} list 
	 */
	public java.util.List<com.soffid.iam.base.api.UserData> toUserDataList (java.util.Collection<com.soffid.iam.base.model.UserDataEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.UserData} object 
	 */
	public void userDataToEntity (com.soffid.iam.base.api.UserData source, com.soffid.iam.base.model.UserDataEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserData} object 
	 */
	public com.soffid.iam.base.model.UserDataEntity userDataToEntity (com.soffid.iam.base.api.UserData instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserData} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity>  userDataToEntityList (java.util.Collection<com.soffid.iam.base.api.UserData> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.UserDataEntity} .
	 */
	public com.soffid.iam.base.model.UserDataEntity newUserDataEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.UserDataEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.UserDataEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.UserDataEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.UserDataEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.UserDataEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.UserDataEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.UserDataEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.UserDataEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.UserDataEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.UserDataEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.UserDataEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.UserDataEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.UserDataEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
