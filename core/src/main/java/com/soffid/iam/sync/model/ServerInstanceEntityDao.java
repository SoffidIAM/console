//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.model;
/**
 * DAO for Entity ServerInstanceEntity
 * @see com.soffid.iam.sync.model.ServerInstanceEntity
 */
public interface ServerInstanceEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerInstanceEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findByServerNameAndInstanceName
	 * @param serverName
	 * @param instanceName
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerInstanceEntity findByServerNameAndInstanceName(
		java.lang.String serverName, 
		java.lang.String instanceName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity findByServerNameAndInstanceName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serverName, java.lang.String instanceName)
	;
	/**
	 * Operation findByUrl
	 * @param url
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerInstanceEntity findByUrl(
		java.lang.String url)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity findByUrl(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String url)
	;
	/**
	 * Operation findByServerName
	 * @param serverName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ServerInstanceEntity> findByServerName(
		java.lang.String serverName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerInstanceEntity> findByServerName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serverName)
	;
	/**
	 * Operation findBestServerInstances
	 * @param serverName
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> findBestServerInstances(
		java.lang.String serverName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> findBestServerInstances(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serverName)
	;
	/**
	 * Operation findExpired
	 * @param lastSeen
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> findExpired(
		java.util.Date lastSeen)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> findExpired(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date lastSeen)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} .
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity newServerInstanceEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ServerInstanceEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ServerInstanceEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ServerInstanceEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ServerInstanceEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ServerInstanceEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ServerInstanceEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ServerInstanceEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ServerInstanceEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ServerInstanceEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerInstanceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerInstanceEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
