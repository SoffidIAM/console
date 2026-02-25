//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity RuleAssignedRoleEntity
 * @see com.soffid.iam.iga.model.RuleAssignedRoleEntity
 */
public interface RuleAssignedRoleEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.RuleAssignedRole} object 
	 */
	public void toRuleAssignedRole(com.soffid.iam.iga.model.RuleAssignedRoleEntity source, com.soffid.iam.iga.api.RuleAssignedRole target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RuleAssignedRole} object 
	 */
	public com.soffid.iam.iga.api.RuleAssignedRole toRuleAssignedRole(com.soffid.iam.iga.model.RuleAssignedRoleEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.RuleAssignedRole} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.RuleAssignedRole> toRuleAssignedRoleList (java.util.Collection<com.soffid.iam.iga.model.RuleAssignedRoleEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.RuleAssignedRole} object 
	 */
	public void ruleAssignedRoleToEntity (com.soffid.iam.iga.api.RuleAssignedRole source, com.soffid.iam.iga.model.RuleAssignedRoleEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RuleAssignedRole} object 
	 */
	public com.soffid.iam.iga.model.RuleAssignedRoleEntity ruleAssignedRoleToEntity (com.soffid.iam.iga.api.RuleAssignedRole instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.RuleAssignedRole} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity>  ruleAssignedRoleToEntityList (java.util.Collection<com.soffid.iam.iga.api.RuleAssignedRole> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} .
	 */
	public com.soffid.iam.iga.model.RuleAssignedRoleEntity newRuleAssignedRoleEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.RuleAssignedRoleEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.RuleAssignedRoleEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.RuleAssignedRoleEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.RuleAssignedRoleEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.RuleAssignedRoleEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.RuleAssignedRoleEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.RuleAssignedRoleEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.RuleAssignedRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.RuleAssignedRoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
