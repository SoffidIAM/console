//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity RoleAccountEntity
 * @see com.soffid.iam.iga.model.RoleAccountEntity
 */
public interface RoleAccountEntityDao

{
	/**
	 * Operation findAllByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllByUserName(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findAllRolAccountToEndDelegation
	 * Search delegations to end
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllRolAccountToEndDelegation(
		java.util.Date now)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllRolAccountToEndDelegation(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	;
	/**
	 * Operation findAllRolAccountToStartDelegation
	 * Search delegations to start
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllRolAccountToStartDelegation(
		java.util.Date now)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findAllRolAccountToStartDelegation(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	;
	/**
	 * Operation findByGroupName
	 * @param groupName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByGroupName(
		java.lang.String groupName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	;
	/**
	 * Operation findByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByUserName(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findByExternalId
	 * @param externalId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByExternalId(
		java.lang.String externalId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByExternalId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	;
	/**
	 * Operation findByQualifierGroup
	 * @param groupName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByQualifierGroup(
		java.lang.String groupName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByQualifierGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	;
	/**
	 * Operation findByInformationSystem
	 * Gets all granted roles for an information system
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByInformationSystem(
		java.lang.String informationSystem)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByInformationSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	;
	/**
	 * Operation findByQualifierIS
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByQualifierIS(
		java.lang.String informationSystem)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByQualifierIS(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	;
	/**
	 * Operation findByRoleAndDomainType
	 * @param roleName
	 * @param systemName
	 * @param informationSystemName
	 * @param domainType
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByRoleAndDomainType(
		java.lang.String roleName, 
		java.lang.String systemName, 
		java.lang.String informationSystemName, 
		java.lang.String domainType)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByRoleAndDomainType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String systemName, java.lang.String informationSystemName, java.lang.String domainType)
	;
	/**
	 * Operation findByRoleAndDomainValue
	 * @param roleName
	 * @param systemName
	 * @param domainType
	 * @param groupScope
	 * @param informationSystemScope
	 * @param domainValueId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByRoleAndDomainValue(
		java.lang.String roleName, 
		java.lang.String systemName, 
		java.lang.String domainType, 
		java.lang.String groupScope, 
		java.lang.String informationSystemScope, 
		java.lang.Long domainValueId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByRoleAndDomainValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String systemName, java.lang.String domainType, java.lang.String groupScope, java.lang.String informationSystemScope, java.lang.Long domainValueId)
	;
	/**
	 * Operation findByUserAndRule
	 * @param userId
	 * @param ruleId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByUserAndRule(
		java.lang.Long userId, 
		java.lang.Long ruleId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findByUserAndRule(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId, java.lang.Long ruleId)
	;
	/**
	 * Operation findDelegatedRolAccounts
	 * Search delegations done by a user
	 * @param user
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findDelegatedRolAccounts(
		java.lang.String user)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findDelegatedRolAccounts(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	;
	/**
	 * Operation findHistoryByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findHistoryByUserName(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findHistoryByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findMatching
	 * @param accountId
	 * @param roleId
	 * @param domainType
	 * @param groupName
	 * @param informationSystem
	 * @param domainValue
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findMatching(
		java.lang.Long accountId, 
		java.lang.Long roleId, 
		java.lang.String domainType, 
		java.lang.String groupName, 
		java.lang.String informationSystem, 
		java.lang.String domainValue)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findMatching(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long accountId, java.lang.Long roleId, java.lang.String domainType, java.lang.String groupName, java.lang.String informationSystem, java.lang.String domainValue)
	;
	/**
	 * Operation findRolAccountToDisable
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRolAccountToDisable(
		java.util.Date now)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRolAccountToDisable(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	;
	/**
	 * Operation findRolAccountToEnable
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRolAccountToEnable(
		java.util.Date now)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRolAccountToEnable(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date now)
	;
	/**
	 * Operation findRoleAccountToEndDelegation
	 * Search delegations to end
	 * @param user
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRoleAccountToEndDelegation(
		java.lang.String user, 
		java.util.Date now)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRoleAccountToEndDelegation(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.util.Date now)
	;
	/**
	 * Operation findRoleAccountToStartDelegation
	 * Search delegations to start
	 * @param user
	 * @param now
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRoleAccountToStartDelegation(
		java.lang.String user, 
		java.util.Date now)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> findRoleAccountToStartDelegation(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.util.Date now)
	;
	/**
	 * Operation update
	 * @param entity
	 * @param auditOperation
	**/
	public void update(
		com.soffid.iam.iga.model.RoleAccountEntity entity, 
		java.lang.String auditOperation) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.RoleAccount} object 
	 */
	public void toRoleAccount(com.soffid.iam.iga.model.RoleAccountEntity source, com.soffid.iam.iga.api.RoleAccount target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleAccount} object 
	 */
	public com.soffid.iam.iga.api.RoleAccount toRoleAccount(com.soffid.iam.iga.model.RoleAccountEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleAccount} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.RoleAccount> toRoleAccountList (java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.RoleAccount} object 
	 */
	public void roleAccountToEntity (com.soffid.iam.iga.api.RoleAccount source, com.soffid.iam.iga.model.RoleAccountEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleAccount} object 
	 */
	public com.soffid.iam.iga.model.RoleAccountEntity roleAccountToEntity (com.soffid.iam.iga.api.RoleAccount instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleAccount} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>  roleAccountToEntityList (java.util.Collection<com.soffid.iam.iga.api.RoleAccount> instances) ;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void toRoleGrant(com.soffid.iam.iga.model.RoleAccountEntity source, com.soffid.iam.iga.api.RoleGrant target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.api.RoleGrant toRoleGrant(com.soffid.iam.iga.model.RoleAccountEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.RoleGrant> toRoleGrantList (java.util.Collection<com.soffid.iam.iga.model.RoleAccountEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant source, com.soffid.iam.iga.model.RoleAccountEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.model.RoleAccountEntity roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity>  roleGrantToEntityList (java.util.Collection<com.soffid.iam.iga.api.RoleGrant> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} .
	 */
	public com.soffid.iam.iga.model.RoleAccountEntity newRoleAccountEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RoleAccountEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RoleAccountEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RoleAccountEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RoleAccountEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RoleAccountEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAccountEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RoleAccountEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAccountEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RoleAccountEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleAccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
