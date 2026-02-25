//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.model;
/**
 * DAO for Entity SoDRuleEntity
 * @see com.soffid.iam.rc.model.SoDRuleEntity
 */
public interface SoDRuleEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.SoDRule} object 
	 */
	public void toSoDRule(com.soffid.iam.rc.model.SoDRuleEntity source, com.soffid.iam.rc.api.SoDRule target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRule} object 
	 */
	public com.soffid.iam.rc.api.SoDRule toSoDRule(com.soffid.iam.rc.model.SoDRuleEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRule} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.SoDRule> toSoDRuleList (java.util.Collection<com.soffid.iam.rc.model.SoDRuleEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.SoDRule} object 
	 */
	public void soDRuleToEntity (com.soffid.iam.rc.api.SoDRule source, com.soffid.iam.rc.model.SoDRuleEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRule} object 
	 */
	public com.soffid.iam.rc.model.SoDRuleEntity soDRuleToEntity (com.soffid.iam.rc.api.SoDRule instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRule} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleEntity>  soDRuleToEntityList (java.util.Collection<com.soffid.iam.rc.api.SoDRule> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} .
	 */
	public com.soffid.iam.rc.model.SoDRuleEntity newSoDRuleEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.SoDRuleEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.SoDRuleEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.SoDRuleEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.SoDRuleEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.SoDRuleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.SoDRuleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
