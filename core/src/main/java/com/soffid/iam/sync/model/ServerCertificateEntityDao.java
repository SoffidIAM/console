//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.model;
/**
 * DAO for Entity ServerCertificateEntity
 * @see com.soffid.iam.sync.model.ServerCertificateEntity
 */
public interface ServerCertificateEntityDao

{
	/**
	 * Operation findByServer
	 * @param serverId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ServerCertificateEntity> findByServer(
		long serverId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerCertificateEntity> findByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long serverId)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} .
	 */
	public com.soffid.iam.sync.model.ServerCertificateEntity newServerCertificateEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ServerCertificateEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ServerCertificateEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ServerCertificateEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ServerCertificateEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerCertificateEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ServerCertificateEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ServerCertificateEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ServerCertificateEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ServerCertificateEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ServerCertificateEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerCertificateEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerCertificateEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerCertificateEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
