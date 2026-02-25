//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.model;
/**
 * DAO for Entity IssuePolicyActionEntity
 * @see com.soffid.iam.rc.model.IssuePolicyActionEntity
 */
public interface IssuePolicyActionEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.IssuePolicyAction} object 
	 */
	public void toIssuePolicyAction(com.soffid.iam.rc.model.IssuePolicyActionEntity source, com.soffid.iam.rc.api.IssuePolicyAction target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssuePolicyAction} object 
	 */
	public com.soffid.iam.rc.api.IssuePolicyAction toIssuePolicyAction(com.soffid.iam.rc.model.IssuePolicyActionEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.IssuePolicyAction} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.IssuePolicyAction> toIssuePolicyActionList (java.util.Collection<com.soffid.iam.rc.model.IssuePolicyActionEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.IssuePolicyAction} object 
	 */
	public void issuePolicyActionToEntity (com.soffid.iam.rc.api.IssuePolicyAction source, com.soffid.iam.rc.model.IssuePolicyActionEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssuePolicyAction} object 
	 */
	public com.soffid.iam.rc.model.IssuePolicyActionEntity issuePolicyActionToEntity (com.soffid.iam.rc.api.IssuePolicyAction instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.IssuePolicyAction} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity>  issuePolicyActionToEntityList (java.util.Collection<com.soffid.iam.rc.api.IssuePolicyAction> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} .
	 */
	public com.soffid.iam.rc.model.IssuePolicyActionEntity newIssuePolicyActionEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.IssuePolicyActionEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.IssuePolicyActionEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.IssuePolicyActionEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.IssuePolicyActionEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyActionEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyActionEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.IssuePolicyActionEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.rc.model.IssuePolicyActionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.IssuePolicyActionEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
