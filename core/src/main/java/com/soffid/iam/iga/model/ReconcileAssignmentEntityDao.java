//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity ReconcileAssignmentEntity
 * @see com.soffid.iam.iga.model.ReconcileAssignmentEntity
 */
public interface ReconcileAssignmentEntityDao

{
	/**
	 * Operation findByProcessId
	 * @param processId
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> findByProcessId(
		java.lang.Long processId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> findByProcessId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.ReconcileAssignment} object 
	 */
	public void toReconcileAssignment(com.soffid.iam.iga.model.ReconcileAssignmentEntity source, com.soffid.iam.iga.api.ReconcileAssignment target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileAssignment} object 
	 */
	public com.soffid.iam.iga.api.ReconcileAssignment toReconcileAssignment(com.soffid.iam.iga.model.ReconcileAssignmentEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.ReconcileAssignment} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.ReconcileAssignment> toReconcileAssignmentList (java.util.Collection<com.soffid.iam.iga.model.ReconcileAssignmentEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.ReconcileAssignment} object 
	 */
	public void reconcileAssignmentToEntity (com.soffid.iam.iga.api.ReconcileAssignment source, com.soffid.iam.iga.model.ReconcileAssignmentEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileAssignment} object 
	 */
	public com.soffid.iam.iga.model.ReconcileAssignmentEntity reconcileAssignmentToEntity (com.soffid.iam.iga.api.ReconcileAssignment instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.ReconcileAssignment} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity>  reconcileAssignmentToEntityList (java.util.Collection<com.soffid.iam.iga.api.ReconcileAssignment> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} .
	 */
	public com.soffid.iam.iga.model.ReconcileAssignmentEntity newReconcileAssignmentEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ReconcileAssignmentEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ReconcileAssignmentEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ReconcileAssignmentEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ReconcileAssignmentEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAssignmentEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAssignmentEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAssignmentEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAssignmentEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAssignmentEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
