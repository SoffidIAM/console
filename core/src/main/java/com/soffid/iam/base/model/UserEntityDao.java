//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity UserEntity
 * @see com.soffid.iam.base.model.UserEntity
 */
public interface UserEntityDao

{
	/**
	 * Operation getPasswordsStatus
	 * @param usuariEntity
	 * @param dominiContrasenyes
	 * @return
	**/
	public com.soffid.iam.am.api.PasswordStatus getPasswordsStatus(
		com.soffid.iam.base.model.UserEntity usuariEntity, 
		com.soffid.iam.am.model.PasswordDomainEntity dominiContrasenyes)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.api.PasswordStatus getPasswordsStatus(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.model.UserEntity usuariEntity, com.soffid.iam.am.model.PasswordDomainEntity dominiContrasenyes)
	;
	/**
	 * Operation toUser
	 * @param entity
	 * @param attributes
	 * @return
	**/
	public com.soffid.iam.base.api.User toUser(
		com.soffid.iam.base.model.UserEntity entity, 
		java.lang.String[] attributes) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation findByAccount
	 * @param account
	 * @param system
	 * @return
	**/
	public com.soffid.iam.base.model.UserEntity findByAccount(
		java.lang.String account, 
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.UserEntity findByAccount(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String account, java.lang.String system)
	;
	/**
	 * Operation findByUserName
	 * @param userName
	 * @return
	**/
	public com.soffid.iam.base.model.UserEntity findByUserName(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.UserEntity findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findById
	 * @param id
	 * @return
	**/
	public com.soffid.iam.base.model.UserEntity findById(
		java.lang.Long id)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.UserEntity findById(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	;
	/**
	 * Operation findByNationalID
	 * @param nif
	 * @return
	**/
	public com.soffid.iam.base.model.UserEntity findByNationalID(
		java.lang.String nif)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.UserEntity findByNationalID(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nif)
	;
	/**
	 * Operation findByUserNameDeleted
	 * @param userName
	 * @return
	**/
	public com.soffid.iam.base.model.UserEntity findByUserNameDeleted(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.UserEntity findByUserNameDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findUserByDataValue
	 * @param dataType
	 * @param value
	 * @return
	**/
	public com.soffid.iam.base.model.UserEntity findUserByDataValue(
		java.lang.String dataType, 
		java.lang.String value)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.UserEntity findUserByDataValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String dataType, java.lang.String value)
	;
	/**
	 * Operation countByGrupPrimari
	 * @param primaryGroupName
	 * @return
	**/
	public java.lang.Number countByGrupPrimari(
		java.lang.String primaryGroupName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Number countByGrupPrimari(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String primaryGroupName)
	;
	/**
	 * Operation generateUserName
	 * @return
	**/
	public java.lang.String generateUserName()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.String generateUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation getNextUserName
	 * @return
	**/
	public java.lang.String getNextUserName()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.String getNextUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation getNextAnonimUser
	 * @return
	**/
	public java.lang.String getNextAnonimUser()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.String getNextAnonimUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation getNextUserIDRequest
	 * @return
	**/
	public java.lang.String getNextUserIDRequest()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.String getNextUserIDRequest(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation refreshCanvis
	 * @param codiUsuari
	 * @return
	**/
	public java.lang.String refreshCanvis(
		java.lang.String codiUsuari) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation getTasks
	 * @param codiUsuari
	 * @return
	**/
	public java.lang.String[] getTasks(
		java.lang.String codiUsuari)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.String[] getTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String codiUsuari)
	;
	/**
	 * Operation findByShortNameAndDomain
	 * @param shortName
	 * @param mailDomainId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.UserEntity> findByShortNameAndDomain(
		java.lang.String shortName, 
		java.lang.Long mailDomainId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserEntity> findByShortNameAndDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String shortName, java.lang.Long mailDomainId)
	;
	/**
	 * Operation findByPrimaryGroup
	 * @param primaryGroupName
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserEntity> findByPrimaryGroup(
		java.lang.String primaryGroupName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> findByPrimaryGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String primaryGroupName)
	;
	/**
	 * Operation findUserNames
	 * @return
	**/
	public java.util.List<java.lang.String> findUserNames()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<java.lang.String> findUserNames(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findUsersByNationalID
	 * @param nif
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserEntity> findUsersByNationalID(
		java.lang.String nif)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> findUsersByNationalID(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nif)
	;
	/**
	 * Operation findUsersGroupAndSubgroupsByGroupCode
	 * @param codiGrup
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.UserEntity> findUsersGroupAndSubgroupsByGroupCode(
		java.lang.String codiGrup)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> findUsersGroupAndSubgroupsByGroupCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String codiGrup)
	;
	/**
	 * Operation merge
	 * @param src
	 * @param target
	**/
	public void merge(
		java.lang.Long src, 
		java.lang.Long target) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation createUpdateTasks
	 * Generates UPDATE_MAIL_LIST tasks for any affected mail list
	 * @param user
	 * @param oldValue
	**/
	public void createUpdateTasks(
		com.soffid.iam.base.model.UserEntity user, 
		com.soffid.iam.base.api.User oldValue) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.bpm.api.BPMUser} object 
	 */
	public void toBPMUser(com.soffid.iam.base.model.UserEntity source, com.soffid.iam.bpm.api.BPMUser target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.bpm.api.BPMUser} object 
	 */
	public com.soffid.iam.bpm.api.BPMUser toBPMUser(com.soffid.iam.base.model.UserEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.bpm.api.BPMUser} list 
	 */
	public java.util.List<com.soffid.iam.bpm.api.BPMUser> toBPMUserList (java.util.Collection<com.soffid.iam.base.model.UserEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.bpm.api.BPMUser} object 
	 */
	public void bPMUserToEntity (com.soffid.iam.bpm.api.BPMUser source, com.soffid.iam.base.model.UserEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.bpm.api.BPMUser} object 
	 */
	public com.soffid.iam.base.model.UserEntity bPMUserToEntity (com.soffid.iam.bpm.api.BPMUser instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.bpm.api.BPMUser} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity>  bPMUserToEntityList (java.util.Collection<com.soffid.iam.bpm.api.BPMUser> instances) ;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void toIdentity(com.soffid.iam.base.model.UserEntity source, com.soffid.iam.base.api.Identity target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public com.soffid.iam.base.api.Identity toIdentity(com.soffid.iam.base.model.UserEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Identity> toIdentityList (java.util.Collection<com.soffid.iam.base.model.UserEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void identityToEntity (com.soffid.iam.base.api.Identity source, com.soffid.iam.base.model.UserEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Identity} object 
	 */
	public com.soffid.iam.base.model.UserEntity identityToEntity (com.soffid.iam.base.api.Identity instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity>  identityToEntityList (java.util.Collection<com.soffid.iam.base.api.Identity> instances) ;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.User} object 
	 */
	public void toUser(com.soffid.iam.base.model.UserEntity source, com.soffid.iam.base.api.User target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.User} object 
	 */
	public com.soffid.iam.base.api.User toUser(com.soffid.iam.base.model.UserEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.User} list 
	 */
	public java.util.List<com.soffid.iam.base.api.User> toUserList (java.util.Collection<com.soffid.iam.base.model.UserEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.User} object 
	 */
	public void userToEntity (com.soffid.iam.base.api.User source, com.soffid.iam.base.model.UserEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.User} object 
	 */
	public com.soffid.iam.base.model.UserEntity userToEntity (com.soffid.iam.base.api.User instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.User} list 
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity>  userToEntityList (java.util.Collection<com.soffid.iam.base.api.User> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.UserEntity} .
	 */
	public com.soffid.iam.base.model.UserEntity newUserEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.UserEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.UserEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.UserEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.UserEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.UserEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.UserEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.UserEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.UserEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.UserEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.UserEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.UserEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.UserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.UserEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
