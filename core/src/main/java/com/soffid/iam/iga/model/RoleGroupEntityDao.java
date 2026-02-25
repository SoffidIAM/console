//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity RoleGroupEntity
 * @see com.soffid.iam.iga.model.RoleGroupEntity
 */
public interface RoleGroupEntityDao

{
	/**
	 * Operation findOwnerGroupsByRole
	 * @param rolOtorgat
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> findOwnerGroupsByRole(
		com.soffid.iam.iga.model.RoleEntity rolOtorgat)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> findOwnerGroupsByRole(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.RoleEntity rolOtorgat)
	;
	/**
	 * Operation findAssignedRolesByGroup
	 * @param grup
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> findAssignedRolesByGroup(
		com.soffid.iam.iga.model.GroupEntity grup)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> findAssignedRolesByGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.iga.model.GroupEntity grup)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.GroupRoles} object 
	 */
	public void toGroupRoles(com.soffid.iam.iga.model.RoleGroupEntity source, com.soffid.iam.iga.api.GroupRoles target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.GroupRoles} object 
	 */
	public com.soffid.iam.iga.api.GroupRoles toGroupRoles(com.soffid.iam.iga.model.RoleGroupEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.GroupRoles} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.GroupRoles> toGroupRolesList (java.util.Collection<com.soffid.iam.iga.model.RoleGroupEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.GroupRoles} object 
	 */
	public void groupRolesToEntity (com.soffid.iam.iga.api.GroupRoles source, com.soffid.iam.iga.model.RoleGroupEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.GroupRoles} object 
	 */
	public com.soffid.iam.iga.model.RoleGroupEntity groupRolesToEntity (com.soffid.iam.iga.api.GroupRoles instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.GroupRoles} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity>  groupRolesToEntityList (java.util.Collection<com.soffid.iam.iga.api.GroupRoles> instances) ;

	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void toRoleGrant(com.soffid.iam.iga.model.RoleGroupEntity source, com.soffid.iam.iga.api.RoleGrant target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.api.RoleGrant toRoleGrant(com.soffid.iam.iga.model.RoleGroupEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.RoleGrant> toRoleGrantList (java.util.Collection<com.soffid.iam.iga.model.RoleGroupEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public void roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant source, com.soffid.iam.iga.model.RoleGroupEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} object 
	 */
	public com.soffid.iam.iga.model.RoleGroupEntity roleGrantToEntity (com.soffid.iam.iga.api.RoleGrant instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RoleGrant} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity>  roleGrantToEntityList (java.util.Collection<com.soffid.iam.iga.api.RoleGrant> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} .
	 */
	public com.soffid.iam.iga.model.RoleGroupEntity newRoleGroupEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RoleGroupEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RoleGroupEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RoleGroupEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RoleGroupEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RoleGroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RoleGroupEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RoleGroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RoleGroupEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RoleGroupEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RoleGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RoleGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
