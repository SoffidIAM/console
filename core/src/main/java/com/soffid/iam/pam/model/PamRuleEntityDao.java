//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity PamRuleEntity
 * @see com.soffid.iam.pam.model.PamRuleEntity
 */
public interface PamRuleEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.pam.model.PamRuleEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.pam.model.PamRuleEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.PamRule} object 
	 */
	public void toPamRule(com.soffid.iam.pam.model.PamRuleEntity source, com.soffid.iam.pam.api.PamRule target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamRule} object 
	 */
	public com.soffid.iam.pam.api.PamRule toPamRule(com.soffid.iam.pam.model.PamRuleEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.PamRule} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.PamRule> toPamRuleList (java.util.Collection<com.soffid.iam.pam.model.PamRuleEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.PamRule} object 
	 */
	public void pamRuleToEntity (com.soffid.iam.pam.api.PamRule source, com.soffid.iam.pam.model.PamRuleEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.PamRule} object 
	 */
	public com.soffid.iam.pam.model.PamRuleEntity pamRuleToEntity (com.soffid.iam.pam.api.PamRule instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.PamRule} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.PamRuleEntity>  pamRuleToEntityList (java.util.Collection<com.soffid.iam.pam.api.PamRule> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} .
	 */
	public com.soffid.iam.pam.model.PamRuleEntity newPamRuleEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.PamRuleEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.PamRuleEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.PamRuleEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.PamRuleEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.PamRuleEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.PamRuleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.PamRuleEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.PamRuleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.PamRuleEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.PamRuleEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.PamRuleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.PamRuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.PamRuleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
