//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity AccountSnapshotEntity
 * @see com.soffid.iam.iga.model.AccountSnapshotEntity
 */
public interface AccountSnapshotEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} .
	 */
	public com.soffid.iam.iga.model.AccountSnapshotEntity newAccountSnapshotEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.AccountSnapshotEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.AccountSnapshotEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.AccountSnapshotEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.AccountSnapshotEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.AccountSnapshotEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.AccountSnapshotEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.AccountSnapshotEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.AccountSnapshotEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.AccountSnapshotEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.AccountSnapshotEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.AccountSnapshotEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
