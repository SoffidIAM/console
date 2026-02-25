//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.model;
/**
 * DAO for Entity IssuePolicyEntity
 * @see com.soffid.iam.rc.model.IssuePolicyEntity
 */
public interface IssuePolicyEntityDao

{
	/**
	 * Operation findByType
	 * @param type
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.rc.model.IssuePolicyEntity> findByType(
		java.lang.String type)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.rc.model.IssuePolicyEntity> findByType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String type)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.IssuePolicy} object 
	 */
	public void toIssuePolicy(com.soffid.iam.rc.model.IssuePolicyEntity source, com.soffid.iam.rc.api.IssuePolicy target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssuePolicy} object 
	 */
	public com.soffid.iam.rc.api.IssuePolicy toIssuePolicy(com.soffid.iam.rc.model.IssuePolicyEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssuePolicy} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.IssuePolicy> toIssuePolicyList (java.util.Collection<com.soffid.iam.rc.model.IssuePolicyEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.IssuePolicy} object 
	 */
	public void issuePolicyToEntity (com.soffid.iam.rc.api.IssuePolicy source, com.soffid.iam.rc.model.IssuePolicyEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssuePolicy} object 
	 */
	public com.soffid.iam.rc.model.IssuePolicyEntity issuePolicyToEntity (com.soffid.iam.rc.api.IssuePolicy instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssuePolicy} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyEntity>  issuePolicyToEntityList (java.util.Collection<com.soffid.iam.rc.api.IssuePolicy> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} .
	 */
	public com.soffid.iam.rc.model.IssuePolicyEntity newIssuePolicyEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssuePolicyEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssuePolicyEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssuePolicyEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssuePolicyEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssuePolicyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssuePolicyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssuePolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
