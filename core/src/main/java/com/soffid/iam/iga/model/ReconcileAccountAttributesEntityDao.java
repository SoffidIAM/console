//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity ReconcileAccountAttributesEntity
 * @see com.soffid.iam.iga.model.ReconcileAccountAttributesEntity
 */
public interface ReconcileAccountAttributesEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} .
	 */
	public com.soffid.iam.iga.model.ReconcileAccountAttributesEntity newReconcileAccountAttributesEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ReconcileAccountAttributesEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ReconcileAccountAttributesEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ReconcileAccountAttributesEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ReconcileAccountAttributesEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ReconcileAccountAttributesEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ReconcileAccountAttributesEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
