//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity AuthorizationEntity
 * @see com.soffid.iam.base.model.AuthorizationEntity
 */
public interface AuthorizationEntityDao

{
	/**
	 * Operation findByAuthorization
	 * @param authorization
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> findByAuthorization(
		java.lang.String authorization)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> findByAuthorization(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String authorization)
	;
	/**
	 * Operation findByRoleID
	 * @param roleId
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> findByRoleID(
		java.lang.Long roleId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> findByRoleID(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long roleId)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.AuthorizationRole} object 
	 */
	public void toAuthorizationRole(com.soffid.iam.base.model.AuthorizationEntity source, com.soffid.iam.base.api.AuthorizationRole target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AuthorizationRole} object 
	 */
	public com.soffid.iam.base.api.AuthorizationRole toAuthorizationRole(com.soffid.iam.base.model.AuthorizationEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.AuthorizationRole} list 
	 */
	public java.util.List<com.soffid.iam.base.api.AuthorizationRole> toAuthorizationRoleList (java.util.Collection<com.soffid.iam.base.model.AuthorizationEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.AuthorizationRole} object 
	 */
	public void authorizationRoleToEntity (com.soffid.iam.base.api.AuthorizationRole source, com.soffid.iam.base.model.AuthorizationEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.AuthorizationRole} object 
	 */
	public com.soffid.iam.base.model.AuthorizationEntity authorizationRoleToEntity (com.soffid.iam.base.api.AuthorizationRole instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.AuthorizationRole} list 
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity>  authorizationRoleToEntityList (java.util.Collection<com.soffid.iam.base.api.AuthorizationRole> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} .
	 */
	public com.soffid.iam.base.model.AuthorizationEntity newAuthorizationEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.AuthorizationEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.AuthorizationEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.AuthorizationEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.AuthorizationEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.AuthorizationEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.AuthorizationEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.AuthorizationEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.AuthorizationEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.AuthorizationEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.AuthorizationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.AuthorizationEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
