//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity UserAccountEntity
 * @see com.soffid.iam.base.model.UserAccountEntity
 */
public interface UserAccountEntityDao

{
	/**
	 * Operation findByAccountSystemAndName
	 * @param account
	 * @param systemName
	 * @param user
	 * @return
	**/
	public com.soffid.iam.base.model.UserAccountEntity findByAccountSystemAndName(
		java.lang.String account, 
		java.lang.String systemName, 
		java.lang.String user)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.UserAccountEntity findByAccountSystemAndName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String account, java.lang.String systemName, java.lang.String user)
	;
	/**
	 * Operation findByUserAndDispatcher
	 * @param user
	 * @param dispatcher
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity> findByUserAndDispatcher(
		java.lang.String user, 
		java.lang.String dispatcher)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity> findByUserAndDispatcher(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String dispatcher)
	;
	/**
	 * Operation propagateChanges
	 * @param account
	**/
	public void propagateChanges(
		com.soffid.iam.base.model.UserAccountEntity account) throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.UserAccount} object 
	 */
	public void toUserAccount(com.soffid.iam.base.model.UserAccountEntity source, com.soffid.iam.base.api.UserAccount target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserAccount} object 
	 */
	public com.soffid.iam.base.api.UserAccount toUserAccount(com.soffid.iam.base.model.UserAccountEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserAccount} list 
	 */
	public java.util.List<com.soffid.iam.base.api.UserAccount> toUserAccountList (java.util.Collection<com.soffid.iam.base.model.UserAccountEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.UserAccount} object 
	 */
	public void userAccountToEntity (com.soffid.iam.base.api.UserAccount source, com.soffid.iam.base.model.UserAccountEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserAccount} object 
	 */
	public com.soffid.iam.base.model.UserAccountEntity userAccountToEntity (com.soffid.iam.base.api.UserAccount instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserAccount} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity>  userAccountToEntityList (java.util.Collection<com.soffid.iam.base.api.UserAccount> instances) ;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.UserAccountHistory} object 
	 */
	public void toUserAccountHistory(com.soffid.iam.base.model.UserAccountEntity source, com.soffid.iam.base.api.UserAccountHistory target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserAccountHistory} object 
	 */
	public com.soffid.iam.base.api.UserAccountHistory toUserAccountHistory(com.soffid.iam.base.model.UserAccountEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.UserAccountHistory} list 
	 */
	public java.util.List<com.soffid.iam.base.api.UserAccountHistory> toUserAccountHistoryList (java.util.Collection<com.soffid.iam.base.model.UserAccountEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.UserAccountHistory} object 
	 */
	public void userAccountHistoryToEntity (com.soffid.iam.base.api.UserAccountHistory source, com.soffid.iam.base.model.UserAccountEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserAccountHistory} object 
	 */
	public com.soffid.iam.base.model.UserAccountEntity userAccountHistoryToEntity (com.soffid.iam.base.api.UserAccountHistory instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.UserAccountHistory} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity>  userAccountHistoryToEntityList (java.util.Collection<com.soffid.iam.base.api.UserAccountHistory> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.UserAccountEntity} .
	 */
	public com.soffid.iam.base.model.UserAccountEntity newUserAccountEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.UserAccountEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.UserAccountEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.UserAccountEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.UserAccountEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.UserAccountEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.UserAccountEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.UserAccountEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.UserAccountEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.UserAccountEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.UserAccountEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.UserAccountEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.UserAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.UserAccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
