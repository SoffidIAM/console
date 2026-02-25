//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity AccountAttributeEntity
 * @see com.soffid.iam.base.model.AccountAttributeEntity
 */
public interface AccountAttributeEntityDao

{
	/**
	 * Operation findByName
	 * @param system
	 * @param account
	 * @param name
	 * @return
	**/
	public com.soffid.iam.base.model.AccountAttributeEntity findByName(
		java.lang.String system, 
		java.lang.String account, 
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.AccountAttributeEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String account, java.lang.String name)
	;
	/**
	 * Operation findByNameAndValue
	 * @param system
	 * @param name
	 * @param value
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> findByNameAndValue(
		java.lang.String system, 
		java.lang.String name, 
		java.lang.String value)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> findByNameAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system, java.lang.String name, java.lang.String value)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.UserData} object 
	 */
	public void toUserData(com.soffid.iam.base.model.AccountAttributeEntity source, com.soffid.iam.base.api.UserData target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserData} object 
	 */
	public com.soffid.iam.base.api.UserData toUserData(com.soffid.iam.base.model.AccountAttributeEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserData} list 
	 */
	public java.util.List<com.soffid.iam.base.api.UserData> toUserDataList (java.util.Collection<com.soffid.iam.base.model.AccountAttributeEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.UserData} object 
	 */
	public void userDataToEntity (com.soffid.iam.base.api.UserData source, com.soffid.iam.base.model.AccountAttributeEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserData} object 
	 */
	public com.soffid.iam.base.model.AccountAttributeEntity userDataToEntity (com.soffid.iam.base.api.UserData instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserData} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity>  userDataToEntityList (java.util.Collection<com.soffid.iam.base.api.UserData> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} .
	 */
	public com.soffid.iam.base.model.AccountAttributeEntity newAccountAttributeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AccountAttributeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AccountAttributeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AccountAttributeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AccountAttributeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AccountAttributeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AccountAttributeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AccountAttributeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AccountAttributeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AccountAttributeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AccountAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
