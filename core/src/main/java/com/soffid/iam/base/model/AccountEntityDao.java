//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity AccountEntity
 * @see com.soffid.iam.base.model.AccountEntity
 */
public interface AccountEntityDao

{
	/**
	 * Operation getAccessLevel
	 * @param account
	 * @param user
	 * @return
	**/
	public com.soffid.iam.base.api.AccountAccessLevelEnum getAccessLevel(
		com.soffid.iam.base.model.AccountEntity account, 
		java.lang.String user) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation findByExternalIdAndDispatcher
	 * @param externalId
	 * @param dispatcher
	 * @return
	**/
	public com.soffid.iam.base.model.AccountEntity findByExternalIdAndDispatcher(
		java.lang.String externalId, 
		java.lang.String dispatcher)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.AccountEntity findByExternalIdAndDispatcher(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId, java.lang.String dispatcher)
	;
	/**
	 * Operation findByLoginNameAndSystem
	 * @param loginName
	 * @param dispatcher
	 * @return
	**/
	public com.soffid.iam.base.model.AccountEntity findByLoginNameAndSystem(
		java.lang.String loginName, 
		java.lang.String dispatcher)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.AccountEntity findByLoginNameAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String loginName, java.lang.String dispatcher)
	;
	/**
	 * Operation findByNameAndSystem
	 * @param name
	 * @param dispatcher
	 * @return
	**/
	public com.soffid.iam.base.model.AccountEntity findByNameAndSystem(
		java.lang.String name, 
		java.lang.String dispatcher)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.AccountEntity findByNameAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String dispatcher)
	;
	/**
	 * Operation findByNameAndSystemDeleted
	 * @param name
	 * @param dispatcher
	 * @return
	**/
	public com.soffid.iam.base.model.AccountEntity findByNameAndSystemDeleted(
		java.lang.String name, 
		java.lang.String dispatcher)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.AccountEntity findByNameAndSystemDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String dispatcher)
	;
	/**
	 * Operation getHPAccounts
	 * @return
	**/
	public java.lang.Long getHPAccounts()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long getHPAccounts(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation getPamAccounts
	 * @return
	**/
	public java.lang.Long getPamAccounts()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long getPamAccounts(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation getPamAccountsExpiredPassword
	 * @return
	**/
	public java.lang.Long getPamAccountsExpiredPassword()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long getPamAccountsExpiredPassword(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation getPamAccountsWrongPassword
	 * @return
	**/
	public java.lang.Long getPamAccountsWrongPassword()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long getPamAccountsWrongPassword(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation getReservedHPAccounts
	 * @return
	**/
	public java.lang.Long getReservedHPAccounts()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long getReservedHPAccounts(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findByText
	 * @param text
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.AccountEntity> findByText(
		java.lang.String text)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.base.model.AccountEntity> findByText(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String text)
	;
	/**
	 * Operation findAcountNames
	 * @param systemName
	 * @return
	**/
	public java.util.List<java.lang.String> findAcountNames(
		java.lang.String systemName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<java.lang.String> findAcountNames(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	;
	/**
	 * Operation findByUser
	 * @param userId
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUser(
		java.lang.Long userId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId)
	;
	/**
	 * Operation findByUserAndDomain
	 * @param user
	 * @param domain
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUserAndDomain(
		java.lang.String user, 
		java.lang.String domain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUserAndDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String domain)
	;
	/**
	 * Operation findByUserAndSystem
	 * @param user
	 * @param dispatcher
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUserAndSystem(
		java.lang.String user, 
		java.lang.String dispatcher)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findByUserAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String dispatcher)
	;
	/**
	 * Operation findSharedAccounts
	 * @param name
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findSharedAccounts(
		java.lang.String name, 
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> findSharedAccounts(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String system)
	;
	/**
	 * Operation propagateChanges
	 * @param account
	**/
	public void propagateChanges(
		com.soffid.iam.base.model.AccountEntity account) throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation refresh
	 * @param entity
	**/
	public void refresh(
		com.soffid.iam.base.model.AccountEntity entity) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation removeFromCache
	 * @param entity
	**/
	public void removeFromCache(
		com.soffid.iam.base.model.AccountEntity entity) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation update
	 * @param entity
	 * @param auditType
	**/
	public void update(
		com.soffid.iam.base.model.AccountEntity entity, 
		java.lang.String auditType) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Account} object 
	 */
	public void toAccount(com.soffid.iam.base.model.AccountEntity source, com.soffid.iam.base.api.Account target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Account} object 
	 */
	public com.soffid.iam.base.api.Account toAccount(com.soffid.iam.base.model.AccountEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Account} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Account> toAccountList (java.util.Collection<com.soffid.iam.base.model.AccountEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Account} object 
	 */
	public void accountToEntity (com.soffid.iam.base.api.Account source, com.soffid.iam.base.model.AccountEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Account} object 
	 */
	public com.soffid.iam.base.model.AccountEntity accountToEntity (com.soffid.iam.base.api.Account instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Account} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity>  accountToEntityList (java.util.Collection<com.soffid.iam.base.api.Account> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AccountEntity} .
	 */
	public com.soffid.iam.base.model.AccountEntity newAccountEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AccountEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AccountEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AccountEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AccountEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AccountEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AccountEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AccountEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AccountEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AccountEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AccountEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AccountEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.AccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
