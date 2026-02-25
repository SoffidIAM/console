//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.model;
/**
 * DAO for Entity IssueUserEntity
 * @see com.soffid.iam.rc.model.IssueUserEntity
 */
public interface IssueUserEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.IssueUser} object 
	 */
	public void toIssueUser(com.soffid.iam.rc.model.IssueUserEntity source, com.soffid.iam.rc.api.IssueUser target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueUser} object 
	 */
	public com.soffid.iam.rc.api.IssueUser toIssueUser(com.soffid.iam.rc.model.IssueUserEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueUser} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.IssueUser> toIssueUserList (java.util.Collection<com.soffid.iam.rc.model.IssueUserEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.IssueUser} object 
	 */
	public void issueUserToEntity (com.soffid.iam.rc.api.IssueUser source, com.soffid.iam.rc.model.IssueUserEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssueUser} object 
	 */
	public com.soffid.iam.rc.model.IssueUserEntity issueUserToEntity (com.soffid.iam.rc.api.IssueUser instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssueUser} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueUserEntity>  issueUserToEntityList (java.util.Collection<com.soffid.iam.rc.api.IssueUser> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} .
	 */
	public com.soffid.iam.rc.model.IssueUserEntity newIssueUserEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssueUserEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssueUserEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssueUserEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssueUserEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueUserEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssueUserEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssueUserEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssueUserEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssueUserEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssueUserEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueUserEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueUserEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueUserEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
