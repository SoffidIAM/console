//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.model;
/**
 * DAO for Entity IssueEntity
 * @see com.soffid.iam.rc.model.IssueEntity
 */
public interface IssueEntityDao

{
	/**
	 * Operation countPending
	 * @param actor
	 * @return
	**/
	public java.lang.Long countPending(
		java.lang.String actor)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long countPending(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String actor)
	;
	/**
	 * Operation findActiveRoles
	 * @return
	**/
	public java.util.Collection<java.lang.String> findActiveRoles()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<java.lang.String> findActiveRoles(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findByIssueAndUser
	 * @param type
	 * @param user
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findByIssueAndUser(
		java.lang.String type, 
		java.lang.String user)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findByIssueAndUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type, java.lang.String user)
	;
	/**
	 * Operation findBySearchHash
	 * @param type
	 * @param searchHash
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findBySearchHash(
		java.lang.String type, 
		java.lang.String searchHash)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findBySearchHash(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type, java.lang.String searchHash)
	;
	/**
	 * Operation findByUserName
	 * @param user
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findByUserName(
		java.lang.String user)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssueEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.Issue} object 
	 */
	public void toIssue(com.soffid.iam.rc.model.IssueEntity source, com.soffid.iam.rc.api.Issue target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.Issue} object 
	 */
	public com.soffid.iam.rc.api.Issue toIssue(com.soffid.iam.rc.model.IssueEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.Issue} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.Issue> toIssueList (java.util.Collection<com.soffid.iam.rc.model.IssueEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.Issue} object 
	 */
	public void issueToEntity (com.soffid.iam.rc.api.Issue source, com.soffid.iam.rc.model.IssueEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.Issue} object 
	 */
	public com.soffid.iam.rc.model.IssueEntity issueToEntity (com.soffid.iam.rc.api.Issue instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.Issue} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueEntity>  issueToEntityList (java.util.Collection<com.soffid.iam.rc.api.Issue> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssueEntity} .
	 */
	public com.soffid.iam.rc.model.IssueEntity newIssueEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssueEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssueEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssueEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssueEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssueEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssueEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssueEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssueEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssueEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssueEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssueEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
