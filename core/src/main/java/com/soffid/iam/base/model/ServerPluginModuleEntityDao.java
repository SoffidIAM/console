//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity ServerPluginModuleEntity
 * @see com.soffid.iam.base.model.ServerPluginModuleEntity
 */
public interface ServerPluginModuleEntityDao

{
	/**
	 * Operation findByType
	 * @param type
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> findByType(
		com.soffid.iam.base.api.ServerPluginModuleType type)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> findByType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.api.ServerPluginModuleType type)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.ServerPluginModule} object 
	 */
	public void toServerPluginModule(com.soffid.iam.base.model.ServerPluginModuleEntity source, com.soffid.iam.base.api.ServerPluginModule target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.ServerPluginModule} object 
	 */
	public com.soffid.iam.base.api.ServerPluginModule toServerPluginModule(com.soffid.iam.base.model.ServerPluginModuleEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.ServerPluginModule} list 
	 */
	public java.util.List<com.soffid.iam.base.api.ServerPluginModule> toServerPluginModuleList (java.util.Collection<com.soffid.iam.base.model.ServerPluginModuleEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.ServerPluginModule} object 
	 */
	public void serverPluginModuleToEntity (com.soffid.iam.base.api.ServerPluginModule source, com.soffid.iam.base.model.ServerPluginModuleEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.ServerPluginModule} object 
	 */
	public com.soffid.iam.base.model.ServerPluginModuleEntity serverPluginModuleToEntity (com.soffid.iam.base.api.ServerPluginModule instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.ServerPluginModule} list 
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity>  serverPluginModuleToEntityList (java.util.Collection<com.soffid.iam.base.api.ServerPluginModule> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} .
	 */
	public com.soffid.iam.base.model.ServerPluginModuleEntity newServerPluginModuleEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.ServerPluginModuleEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.ServerPluginModuleEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.ServerPluginModuleEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.ServerPluginModuleEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginModuleEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginModuleEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.ServerPluginModuleEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.ServerPluginModuleEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.ServerPluginModuleEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
