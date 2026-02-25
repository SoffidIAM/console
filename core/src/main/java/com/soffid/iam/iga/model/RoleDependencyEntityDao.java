//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity RoleDependencyEntity
 * @see com.soffid.iam.iga.model.RoleDependencyEntity
 */
public interface RoleDependencyEntityDao

{
	/**
	 * Operation findByContainer
	 * @param containerRole
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findByContainer(
		com.soffid.iam.iga.model.RoleEntity containerRole)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findByContainer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity containerRole)
	;
	/**
	 * Operation findContainerByRoleNameAndApplicationCodeAndDBCode
	 * @param roleName
	 * @param informationSystem
	 * @param systemName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findContainerByRoleNameAndApplicationCodeAndDBCode(
		java.lang.String roleName, 
		java.lang.String informationSystem, 
		java.lang.String systemName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findContainerByRoleNameAndApplicationCodeAndDBCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String informationSystem, java.lang.String systemName)
	;
	/**
	 * Operation findRolesAssociationRole
	 * @param containedRole
	 * @param containerRole
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findRolesAssociationRole(
		com.soffid.iam.iga.model.RoleEntity containedRole, 
		com.soffid.iam.iga.model.RoleEntity containerRole)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findRolesAssociationRole(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity containedRole, com.soffid.iam.iga.model.RoleEntity containerRole)
	;
	/**
	 * Operation findRolesAssociationContainerRole
	 * @param containedRole
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findRolesAssociationContainerRole(
		com.soffid.iam.iga.model.RoleEntity containedRole)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> findRolesAssociationContainerRole(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity containedRole)
	;
	/**
	 * Operation assignDomainValue
	 * @param entity
	 * @param valueObject
	 * @param grantedRole
	 * @param granteeRole
	**/
	public void assignDomainValue(
		com.soffid.iam.iga.model.RoleDependencyEntity entity, 
		com.soffid.iam.iga.api.RoleGrant valueObject, 
		com.soffid.iam.iga.model.RoleEntity grantedRole, 
		com.soffid.iam.iga.model.RoleEntity granteeRole) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation assignGranteeDomainValue
	 * @param entity
	 * @param valueObject
	 * @param grantedRole
	 * @param granteeRole
	**/
	public void assignGranteeDomainValue(
		com.soffid.iam.iga.model.RoleDependencyEntity entity, 
		com.soffid.iam.iga.api.RoleGrant valueObject, 
		com.soffid.iam.iga.model.RoleEntity grantedRole, 
		com.soffid.iam.iga.model.RoleEntity granteeRole) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void toRoleGrant(com.soffid.iam.iga.model.RoleDependencyEntity source, com.soffid.iam.iga.api.RoleGrant target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.api.RoleGrant toRoleGrant(com.soffid.iam.iga.model.RoleDependencyEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.RoleGrant> toRoleGrantList (java.util.Collection<com.soffid.iam.iga.model.RoleDependencyEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant source, com.soffid.iam.iga.model.RoleDependencyEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.model.RoleDependencyEntity roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity>  roleGrantToEntityList (java.util.Collection<com.soffid.iam.iga.api.RoleGrant> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} .
	 */
	public com.soffid.iam.iga.model.RoleDependencyEntity newRoleDependencyEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RoleDependencyEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RoleDependencyEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RoleDependencyEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RoleDependencyEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RoleDependencyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RoleDependencyEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RoleDependencyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RoleDependencyEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RoleDependencyEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleDependencyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleDependencyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
