//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity PamActionEntity
 * @see com.soffid.iam.pam.model.PamActionEntity
 */
public interface PamActionEntityDao

{
	/**
	 * Operation create
	 * @param action
	 * @return
	**/
	public com.soffid.iam.pam.api.PamAction create(
		com.soffid.iam.pam.api.PamAction action) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation update
	 * @param action
	 * @return
	**/
	public com.soffid.iam.pam.api.PamAction update(
		com.soffid.iam.pam.api.PamAction action) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation findByPolicy
	 * @param policy
	 * @return
	**/
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> findByPolicy(
		java.lang.String policy)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> findByPolicy(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String policy)
	;
	/**
	 * Operation findByPolicyAndRule
	 * @param policy
	 * @param rule
	 * @return
	**/
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> findByPolicyAndRule(
		java.lang.String policy, 
		java.lang.String rule)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> findByPolicyAndRule(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String policy, java.lang.String rule)
	;
	/**
	 * Operation getActionsByPolicy
	 * @param policy
	 * @return
	**/
	public java.util.List<com.soffid.iam.pam.api.PamAction> getActionsByPolicy(
		java.lang.String policy) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation remove
	 * @param action
	**/
	public void remove(
		com.soffid.iam.pam.api.PamAction action) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.PamAction} object 
	 */
	public void toPamAction(com.soffid.iam.pam.model.PamActionEntity source, com.soffid.iam.pam.api.PamAction target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamAction} object 
	 */
	public com.soffid.iam.pam.api.PamAction toPamAction(com.soffid.iam.pam.model.PamActionEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamAction} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.PamAction> toPamActionList (java.util.Collection<com.soffid.iam.pam.model.PamActionEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.PamAction} object 
	 */
	public void pamActionToEntity (com.soffid.iam.pam.api.PamAction source, com.soffid.iam.pam.model.PamActionEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.PamAction} object 
	 */
	public com.soffid.iam.pam.model.PamActionEntity pamActionToEntity (com.soffid.iam.pam.api.PamAction instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.PamAction} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity>  pamActionToEntityList (java.util.Collection<com.soffid.iam.pam.api.PamAction> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.PamActionEntity} .
	 */
	public com.soffid.iam.pam.model.PamActionEntity newPamActionEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.PamActionEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.PamActionEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.PamActionEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.PamActionEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.PamActionEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.PamActionEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.PamActionEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.PamActionEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.PamActionEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.PamActionEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.PamActionEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamActionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.PamActionEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
