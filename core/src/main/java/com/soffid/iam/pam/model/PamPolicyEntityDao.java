//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity PamPolicyEntity
 * @see com.soffid.iam.pam.model.PamPolicyEntity
 */
public interface PamPolicyEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.pam.model.PamPolicyEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.pam.model.PamPolicyEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.PamPolicy} object 
	 */
	public void toPamPolicy(com.soffid.iam.pam.model.PamPolicyEntity source, com.soffid.iam.pam.api.PamPolicy target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamPolicy} object 
	 */
	public com.soffid.iam.pam.api.PamPolicy toPamPolicy(com.soffid.iam.pam.model.PamPolicyEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamPolicy} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.PamPolicy> toPamPolicyList (java.util.Collection<com.soffid.iam.pam.model.PamPolicyEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.PamPolicy} object 
	 */
	public void pamPolicyToEntity (com.soffid.iam.pam.api.PamPolicy source, com.soffid.iam.pam.model.PamPolicyEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.PamPolicy} object 
	 */
	public com.soffid.iam.pam.model.PamPolicyEntity pamPolicyToEntity (com.soffid.iam.pam.api.PamPolicy instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.PamPolicy} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyEntity>  pamPolicyToEntityList (java.util.Collection<com.soffid.iam.pam.api.PamPolicy> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} .
	 */
	public com.soffid.iam.pam.model.PamPolicyEntity newPamPolicyEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.PamPolicyEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.PamPolicyEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.PamPolicyEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.PamPolicyEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.PamPolicyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.PamPolicyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.PamPolicyEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamPolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.PamPolicyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
