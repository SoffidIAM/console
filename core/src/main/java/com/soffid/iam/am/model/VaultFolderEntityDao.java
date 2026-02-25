//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity VaultFolderEntity
 * @see com.soffid.iam.am.model.VaultFolderEntity
 */
public interface VaultFolderEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findByParent
	 * @param parent
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findByParent(
		com.soffid.iam.am.model.VaultFolderEntity parent)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findByParent(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.model.VaultFolderEntity parent)
	;
	/**
	 * Operation findPersonalFolders
	 * @param user
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findPersonalFolders(
		java.lang.String user)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findPersonalFolders(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	;
	/**
	 * Operation findPublicRoots
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findPublicRoots()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findPublicRoots(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findRoots
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findRoots()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> findRoots(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.VaultFolder} object 
	 */
	public void toVaultFolder(com.soffid.iam.am.model.VaultFolderEntity source, com.soffid.iam.am.api.VaultFolder target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.VaultFolder} object 
	 */
	public com.soffid.iam.am.api.VaultFolder toVaultFolder(com.soffid.iam.am.model.VaultFolderEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.VaultFolder} list 
	 */
	public java.util.List<com.soffid.iam.am.api.VaultFolder> toVaultFolderList (java.util.Collection<com.soffid.iam.am.model.VaultFolderEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.VaultFolder} object 
	 */
	public void vaultFolderToEntity (com.soffid.iam.am.api.VaultFolder source, com.soffid.iam.am.model.VaultFolderEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.VaultFolder} object 
	 */
	public com.soffid.iam.am.model.VaultFolderEntity vaultFolderToEntity (com.soffid.iam.am.api.VaultFolder instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.VaultFolder} list 
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity>  vaultFolderToEntityList (java.util.Collection<com.soffid.iam.am.api.VaultFolder> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} .
	 */
	public com.soffid.iam.am.model.VaultFolderEntity newVaultFolderEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.VaultFolderEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.VaultFolderEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.VaultFolderEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.VaultFolderEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.VaultFolderEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.VaultFolderEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.VaultFolderEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.VaultFolderEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.VaultFolderEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.VaultFolderEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.VaultFolderEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
