//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.model;
/**
 * DAO for Entity ServerEntity
 * @see com.soffid.iam.sync.model.ServerEntity
 */
public interface ServerEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.sync.model.ServerEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findRemoteByUrl
	 * @param url
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerEntity findRemoteByUrl(
		java.lang.String url)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.sync.model.ServerEntity findRemoteByUrl(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String url)
	;
	/**
	 * Operation countServersByName
	 * @param name
	 * @return
	**/
	public java.lang.Long countServersByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long countServersByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findByTenant
	 * @param name
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ServerEntity> findByTenant(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerEntity> findByTenant(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findGatewayByTenant
	 * @param name
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ServerEntity> findGatewayByTenant(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ServerEntity> findGatewayByTenant(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.Server} object 
	 */
	public void toServer(com.soffid.iam.sync.model.ServerEntity source, com.soffid.iam.sync.api.Server target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.Server} object 
	 */
	public com.soffid.iam.sync.api.Server toServer(com.soffid.iam.sync.model.ServerEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.Server} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.Server> toServerList (java.util.Collection<com.soffid.iam.sync.model.ServerEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.Server} object 
	 */
	public void serverToEntity (com.soffid.iam.sync.api.Server source, com.soffid.iam.sync.model.ServerEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.Server} object 
	 */
	public com.soffid.iam.sync.model.ServerEntity serverToEntity (com.soffid.iam.sync.api.Server instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.Server} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerEntity>  serverToEntityList (java.util.Collection<com.soffid.iam.sync.api.Server> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ServerEntity} .
	 */
	public com.soffid.iam.sync.model.ServerEntity newServerEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ServerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ServerEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ServerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ServerEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ServerEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ServerEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ServerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ServerEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ServerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ServerEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ServerEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
