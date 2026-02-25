//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity SecretEntity
 * @see com.soffid.iam.am.model.SecretEntity
 */
public interface SecretEntityDao

{
	/**
	 * Operation findByUserAndServer
	 * @param userId
	 * @param serverId
	 * @return
	**/
	public com.soffid.iam.am.model.SecretEntity findByUserAndServer(
		long userId, 
		long serverId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.SecretEntity findByUserAndServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long userId, long serverId)
	;
	/**
	 * Operation findByServer
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.SecretEntity> findByServer(
		com.soffid.iam.sync.model.ServerEntity server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.SecretEntity> findByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.sync.model.ServerEntity server)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.SecretEntity} .
	 */
	public com.soffid.iam.am.model.SecretEntity newSecretEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.SecretEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.SecretEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.SecretEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.SecretEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.SecretEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.SecretEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.SecretEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.SecretEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.SecretEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.SecretEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.SecretEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.SecretEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.SecretEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.SecretEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.SecretEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
