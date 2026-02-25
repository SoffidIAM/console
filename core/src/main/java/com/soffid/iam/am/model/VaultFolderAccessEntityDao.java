//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity VaultFolderAccessEntity
 * Contains the access control list for a vault folder
 * @see com.soffid.iam.am.model.VaultFolderAccessEntity
 */
public interface VaultFolderAccessEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} .
	 */
	public com.soffid.iam.am.model.VaultFolderAccessEntity newVaultFolderAccessEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.VaultFolderAccessEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.VaultFolderAccessEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.VaultFolderAccessEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.VaultFolderAccessEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderAccessEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.VaultFolderAccessEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.VaultFolderAccessEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.VaultFolderAccessEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderAccessEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.VaultFolderAccessEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderAccessEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
