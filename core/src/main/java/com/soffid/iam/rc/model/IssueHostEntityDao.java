//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.model;
/**
 * DAO for Entity IssueHostEntity
 * @see com.soffid.iam.rc.model.IssueHostEntity
 */
public interface IssueHostEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.IssueHost} object 
	 */
	public void toIssueHost(com.soffid.iam.rc.model.IssueHostEntity source, com.soffid.iam.rc.api.IssueHost target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueHost} object 
	 */
	public com.soffid.iam.rc.api.IssueHost toIssueHost(com.soffid.iam.rc.model.IssueHostEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssueHost} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.IssueHost> toIssueHostList (java.util.Collection<com.soffid.iam.rc.model.IssueHostEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.IssueHost} object 
	 */
	public void issueHostToEntity (com.soffid.iam.rc.api.IssueHost source, com.soffid.iam.rc.model.IssueHostEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssueHost} object 
	 */
	public com.soffid.iam.rc.model.IssueHostEntity issueHostToEntity (com.soffid.iam.rc.api.IssueHost instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssueHost} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueHostEntity>  issueHostToEntityList (java.util.Collection<com.soffid.iam.rc.api.IssueHost> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} .
	 */
	public com.soffid.iam.rc.model.IssueHostEntity newIssueHostEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssueHostEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssueHostEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssueHostEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssueHostEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueHostEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssueHostEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssueHostEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssueHostEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssueHostEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssueHostEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueHostEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssueHostEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssueHostEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
