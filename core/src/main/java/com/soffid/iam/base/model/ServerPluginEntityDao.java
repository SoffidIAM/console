//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity ServerPluginEntity
 * @see com.soffid.iam.base.model.ServerPluginEntity
 */
public interface ServerPluginEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.base.model.ServerPluginEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.ServerPluginEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findAll
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> findAll()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> findAll(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findAllBasicData
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> findAllBasicData()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> findAllBasicData(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findAgentsBasicDataByServerPluginID
	 * @param id
	 * @return
	**/
	public java.util.List<java.lang.Object[]> findAgentsBasicDataByServerPluginID(
		java.lang.Long id)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<java.lang.Object[]> findAgentsBasicDataByServerPluginID(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.ServerPlugin} object 
	 */
	public void toServerPlugin(com.soffid.iam.base.model.ServerPluginEntity source, com.soffid.iam.base.api.ServerPlugin target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.ServerPlugin} object 
	 */
	public com.soffid.iam.base.api.ServerPlugin toServerPlugin(com.soffid.iam.base.model.ServerPluginEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.ServerPlugin} list 
	 */
	public java.util.List<com.soffid.iam.base.api.ServerPlugin> toServerPluginList (java.util.Collection<com.soffid.iam.base.model.ServerPluginEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.ServerPlugin} object 
	 */
	public void serverPluginToEntity (com.soffid.iam.base.api.ServerPlugin source, com.soffid.iam.base.model.ServerPluginEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.ServerPlugin} object 
	 */
	public com.soffid.iam.base.model.ServerPluginEntity serverPluginToEntity (com.soffid.iam.base.api.ServerPlugin instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.ServerPlugin} list 
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity>  serverPluginToEntityList (java.util.Collection<com.soffid.iam.base.api.ServerPlugin> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} .
	 */
	public com.soffid.iam.base.model.ServerPluginEntity newServerPluginEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.ServerPluginEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.ServerPluginEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.ServerPluginEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.ServerPluginEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.ServerPluginEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.ServerPluginEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.ServerPluginEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
