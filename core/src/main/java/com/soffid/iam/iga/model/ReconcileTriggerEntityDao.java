//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity ReconcileTriggerEntity
 * @see com.soffid.iam.iga.model.ReconcileTriggerEntity
 */
public interface ReconcileTriggerEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ReconcileTrigger} object 
	 */
	public void toReconcileTrigger(com.soffid.iam.iga.model.ReconcileTriggerEntity source, com.soffid.iam.iga.api.ReconcileTrigger target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileTrigger} object 
	 */
	public com.soffid.iam.iga.api.ReconcileTrigger toReconcileTrigger(com.soffid.iam.iga.model.ReconcileTriggerEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileTrigger} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ReconcileTrigger> toReconcileTriggerList (java.util.Collection<com.soffid.iam.iga.model.ReconcileTriggerEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ReconcileTrigger} object 
	 */
	public void reconcileTriggerToEntity (com.soffid.iam.iga.api.ReconcileTrigger source, com.soffid.iam.iga.model.ReconcileTriggerEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileTrigger} object 
	 */
	public com.soffid.iam.iga.model.ReconcileTriggerEntity reconcileTriggerToEntity (com.soffid.iam.iga.api.ReconcileTrigger instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileTrigger} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity>  reconcileTriggerToEntityList (java.util.Collection<com.soffid.iam.iga.api.ReconcileTrigger> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} .
	 */
	public com.soffid.iam.iga.model.ReconcileTriggerEntity newReconcileTriggerEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ReconcileTriggerEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ReconcileTriggerEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ReconcileTriggerEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ReconcileTriggerEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileTriggerEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileTriggerEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileTriggerEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileTriggerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileTriggerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
