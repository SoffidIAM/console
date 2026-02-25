//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity RuleEntity
 * @see com.soffid.iam.iga.model.RuleEntity
 */
public interface RuleEntityDao

{
	/**
	 * Operation findByDescription
	 * @param description
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> findByDescription(
		java.lang.String description)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> findByDescription(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String description)
	;
	/**
	 * Operation findByRoleId
	 * @param roleId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> findByRoleId(
		java.lang.Long roleId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> findByRoleId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long roleId)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Rule} object 
	 */
	public void toRule(com.soffid.iam.iga.model.RuleEntity source, com.soffid.iam.iga.api.Rule target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Rule} object 
	 */
	public com.soffid.iam.iga.api.Rule toRule(com.soffid.iam.iga.model.RuleEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Rule} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Rule> toRuleList (java.util.Collection<com.soffid.iam.iga.model.RuleEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Rule} object 
	 */
	public void ruleToEntity (com.soffid.iam.iga.api.Rule source, com.soffid.iam.iga.model.RuleEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Rule} object 
	 */
	public com.soffid.iam.iga.model.RuleEntity ruleToEntity (com.soffid.iam.iga.api.Rule instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Rule} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity>  ruleToEntityList (java.util.Collection<com.soffid.iam.iga.api.Rule> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RuleEntity} .
	 */
	public com.soffid.iam.iga.model.RuleEntity newRuleEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RuleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RuleEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RuleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RuleEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RuleEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RuleEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RuleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RuleEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RuleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RuleEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RuleEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
