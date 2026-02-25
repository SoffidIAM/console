//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity UserGroupEntity
 * @see com.soffid.iam.iga.model.UserGroupEntity
 */
public interface UserGroupEntityDao

{
	/**
	 * Operation findByUserAndGroup
	 * @param userName
	 * @param groupName
	 * @return
	**/
	public com.soffid.iam.iga.model.UserGroupEntity findByUserAndGroup(
		java.lang.String userName, 
		java.lang.String groupName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.UserGroupEntity findByUserAndGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String groupName)
	;
	/**
	 * Operation countByGroupName
	 * @param groupName
	 * @return
	**/
	public java.lang.Number countByGroupName(
		java.lang.String groupName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Number countByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	;
	/**
	 * Operation countByGroupName
	 * @param groupName
	 * @param date
	 * @return
	**/
	public java.lang.Number countByGroupName(
		java.lang.String groupName, 
		java.util.Date date)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Number countByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName, java.util.Date date)
	;
	/**
	 * Operation findByGroupName
	 * @param groupName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByGroupName(
		java.lang.String groupName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	;
	/**
	 * Operation findByGroupName
	 * @param groupName
	 * @param date
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByGroupName(
		java.lang.String groupName, 
		java.util.Date date)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName, java.util.Date date)
	;
	/**
	 * Operation findByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByUserName(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.UserGroup} object 
	 */
	public void toUserGroup(com.soffid.iam.iga.model.UserGroupEntity source, com.soffid.iam.iga.api.UserGroup target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserGroup} object 
	 */
	public com.soffid.iam.iga.api.UserGroup toUserGroup(com.soffid.iam.iga.model.UserGroupEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.UserGroup> toUserGroupList (java.util.Collection<com.soffid.iam.iga.model.UserGroupEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.UserGroup} object 
	 */
	public void userGroupToEntity (com.soffid.iam.iga.api.UserGroup source, com.soffid.iam.iga.model.UserGroupEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserGroup} object 
	 */
	public com.soffid.iam.iga.model.UserGroupEntity userGroupToEntity (com.soffid.iam.iga.api.UserGroup instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity>  userGroupToEntityList (java.util.Collection<com.soffid.iam.iga.api.UserGroup> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} .
	 */
	public com.soffid.iam.iga.model.UserGroupEntity newUserGroupEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserGroupEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserGroupEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserGroupEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserGroupEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserGroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserGroupEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserGroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserGroupEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserGroupEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
