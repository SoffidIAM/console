//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity ReconcileRoleEntity
 * @see com.soffid.iam.iga.model.ReconcileRoleEntity
 */
public interface ReconcileRoleEntityDao

{
	/**
	 * Operation findByProcessId
	 * @param processId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> findByProcessId(
		java.lang.Long processId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> findByProcessId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ReconcileRole} object 
	 */
	public void toReconcileRole(com.soffid.iam.iga.model.ReconcileRoleEntity source, com.soffid.iam.iga.api.ReconcileRole target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileRole} object 
	 */
	public com.soffid.iam.iga.api.ReconcileRole toReconcileRole(com.soffid.iam.iga.model.ReconcileRoleEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileRole} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ReconcileRole> toReconcileRoleList (java.util.Collection<com.soffid.iam.iga.model.ReconcileRoleEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ReconcileRole} object 
	 */
	public void reconcileRoleToEntity (com.soffid.iam.iga.api.ReconcileRole source, com.soffid.iam.iga.model.ReconcileRoleEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileRole} object 
	 */
	public com.soffid.iam.iga.model.ReconcileRoleEntity reconcileRoleToEntity (com.soffid.iam.iga.api.ReconcileRole instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileRole} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity>  reconcileRoleToEntityList (java.util.Collection<com.soffid.iam.iga.api.ReconcileRole> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} .
	 */
	public com.soffid.iam.iga.model.ReconcileRoleEntity newReconcileRoleEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ReconcileRoleEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ReconcileRoleEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ReconcileRoleEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ReconcileRoleEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileRoleEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileRoleEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileRoleEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileRoleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileRoleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
