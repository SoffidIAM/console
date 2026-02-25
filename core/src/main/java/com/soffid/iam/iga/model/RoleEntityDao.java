//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity RoleEntity
 * @see com.soffid.iam.iga.model.RoleEntity
 */
public interface RoleEntityDao

{
	/**
	 * Operation create
	 * @param role
	 * @param updateOwnedRoles
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity create(
		com.soffid.iam.iga.api.Role role, 
		boolean updateOwnedRoles) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation findByExternalIdAndDispatcher
	 * @param externalId
	 * @param system
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity findByExternalIdAndDispatcher(
		java.lang.String externalId, 
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.RoleEntity findByExternalIdAndDispatcher(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId, java.lang.String system)
	;
	/**
	 * Operation findById
	 * @param id
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity findById(
		java.lang.Long id)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.RoleEntity findById(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	;
	/**
	 * Operation findByNameAndSystem
	 * @param roleName
	 * @param system
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity findByNameAndSystem(
		java.lang.String roleName, 
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.RoleEntity findByNameAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String system)
	;
	/**
	 * Operation findByNameAndSystemDeleted
	 * @param roleName
	 * @param system
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity findByNameAndSystemDeleted(
		java.lang.String roleName, 
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.RoleEntity findByNameAndSystemDeleted(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String system)
	;
	/**
	 * Operation findRoleByNameInformationSystemAndStystem
	 * @param roleName
	 * @param informationSystem
	 * @param system
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity findRoleByNameInformationSystemAndStystem(
		java.lang.String roleName, 
		java.lang.String informationSystem, 
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.RoleEntity findRoleByNameInformationSystemAndStystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String informationSystem, java.lang.String system)
	;
	/**
	 * Operation findByShortName
	 * @param shortName
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity findByShortName(
		java.lang.String shortName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.RoleEntity findByShortName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String shortName)
	;
	/**
	 * Operation update
	 * @param role
	 * @param updateOwnedRoles
	 * @return
	**/
	public com.soffid.iam.iga.model.RoleEntity update(
		com.soffid.iam.iga.api.Role role, 
		boolean updateOwnedRoles) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation findApplicationManagementRoles
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> findApplicationManagementRoles() throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> findApplicationManagementRoles(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	throws com.soffid.iam.exception.InternalErrorException;
	/**
	 * Operation findGroupManagementRoles
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> findGroupManagementRoles() throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.RoleEntity> findGroupManagementRoles(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	throws com.soffid.iam.exception.InternalErrorException;
	/**
	 * Operation findByInformationSystem
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByInformationSystem(
		java.lang.String informationSystem)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByInformationSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	;
	/**
	 * Operation findByExternalId
	 * @param externalId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByExternalId(
		java.lang.String externalId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByExternalId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	;
	/**
	 * Operation findByInformationSystemAndDomain
	 * @param informationSystem
	 * @param domainName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByInformationSystemAndDomain(
		java.lang.String informationSystem, 
		java.lang.String domainName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findByInformationSystemAndDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem, java.lang.String domainName)
	;
	/**
	 * Operation findRoleNames
	 * @param system
	 * @return
	**/
	public java.util.List<java.lang.String> findRoleNames(
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<java.lang.String> findRoleNames(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	;
	/**
	 * Operation findRolesByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findRolesByUserName(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findRolesByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findApplicationRolesByUserAndInformationSystem
	 * @param userName
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findApplicationRolesByUserAndInformationSystem(
		java.lang.String userName, 
		java.lang.String informationSystem)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> findApplicationRolesByUserAndInformationSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String informationSystem)
	;
	/**
	 * Operation commitDefinition
	 * @param role
	**/
	public void commitDefinition(
		com.soffid.iam.iga.model.RoleEntity role) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation remove
	 * @param role
	**/
	public void remove(
		com.soffid.iam.iga.api.Role role) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation rollbackDefinition
	 * @param role
	**/
	public void rollbackDefinition(
		com.soffid.iam.iga.model.RoleEntity role) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation updateMailLists
	 * Creates update mail tasks for each mail list affected by the role
	 * @param role
	**/
	public void updateMailLists(
		com.soffid.iam.iga.model.RoleEntity role) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void toIdentity(com.soffid.iam.iga.model.RoleEntity source, com.soffid.iam.base.api.Identity target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} object 
	 */
	public com.soffid.iam.base.api.Identity toIdentity(com.soffid.iam.iga.model.RoleEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Identity> toIdentityList (java.util.Collection<com.soffid.iam.iga.model.RoleEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Identity} object 
	 */
	public void identityToEntity (com.soffid.iam.base.api.Identity source, com.soffid.iam.iga.model.RoleEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Identity} object 
	 */
	public com.soffid.iam.iga.model.RoleEntity identityToEntity (com.soffid.iam.base.api.Identity instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Identity} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity>  identityToEntityList (java.util.Collection<com.soffid.iam.base.api.Identity> instances) ;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Role} object 
	 */
	public void toRole(com.soffid.iam.iga.model.RoleEntity source, com.soffid.iam.iga.api.Role target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Role} object 
	 */
	public com.soffid.iam.iga.api.Role toRole(com.soffid.iam.iga.model.RoleEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Role} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Role> toRoleList (java.util.Collection<com.soffid.iam.iga.model.RoleEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Role} object 
	 */
	public void roleToEntity (com.soffid.iam.iga.api.Role source, com.soffid.iam.iga.model.RoleEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Role} object 
	 */
	public com.soffid.iam.iga.model.RoleEntity roleToEntity (com.soffid.iam.iga.api.Role instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Role} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity>  roleToEntityList (java.util.Collection<com.soffid.iam.iga.api.Role> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RoleEntity} .
	 */
	public com.soffid.iam.iga.model.RoleEntity newRoleEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RoleEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RoleEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RoleEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RoleEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RoleEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RoleEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RoleEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
