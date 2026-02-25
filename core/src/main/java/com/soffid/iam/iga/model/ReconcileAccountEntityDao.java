//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity ReconcileAccountEntity
 * @see com.soffid.iam.iga.model.ReconcileAccountEntity
 */
public interface ReconcileAccountEntityDao

{
	/**
	 * Operation findByProcessId
	 * @param processId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> findByProcessId(
		java.lang.Long processId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> findByProcessId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ReconcileAccount} object 
	 */
	public void toReconcileAccount(com.soffid.iam.iga.model.ReconcileAccountEntity source, com.soffid.iam.iga.api.ReconcileAccount target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileAccount} object 
	 */
	public com.soffid.iam.iga.api.ReconcileAccount toReconcileAccount(com.soffid.iam.iga.model.ReconcileAccountEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileAccount} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ReconcileAccount> toReconcileAccountList (java.util.Collection<com.soffid.iam.iga.model.ReconcileAccountEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ReconcileAccount} object 
	 */
	public void reconcileAccountToEntity (com.soffid.iam.iga.api.ReconcileAccount source, com.soffid.iam.iga.model.ReconcileAccountEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileAccount} object 
	 */
	public com.soffid.iam.iga.model.ReconcileAccountEntity reconcileAccountToEntity (com.soffid.iam.iga.api.ReconcileAccount instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileAccount} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity>  reconcileAccountToEntityList (java.util.Collection<com.soffid.iam.iga.api.ReconcileAccount> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} .
	 */
	public com.soffid.iam.iga.model.ReconcileAccountEntity newReconcileAccountEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ReconcileAccountEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ReconcileAccountEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ReconcileAccountEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ReconcileAccountEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
