//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.model;
/**
 * DAO for Entity ScheduledTaskEntity
 * @see com.soffid.iam.sync.model.ScheduledTaskEntity
 */
public interface ScheduledTaskEntityDao

{
	/**
	 * Operation findByHandlerParams
	 * @param handlerName
	 * @param params
	 * @return
	**/
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByHandlerParams(
		java.lang.String handlerName, 
		java.lang.String params)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByHandlerParams(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String handlerName, java.lang.String params)
	;
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findByStopPendding
	 * @param id
	 * @return
	**/
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByStopPendding(
		java.lang.Long id)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity findByStopPendding(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	;
	/**
	 * Operation findAllByServer
	 * @param server
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> findAllByServer(
		java.lang.String server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> findAllByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	;
	/**
	 * Operation findEnabled
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> findEnabled()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> findEnabled(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.ScheduledTask} object 
	 */
	public void toScheduledTask(com.soffid.iam.sync.model.ScheduledTaskEntity source, com.soffid.iam.sync.api.ScheduledTask target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTask} object 
	 */
	public com.soffid.iam.sync.api.ScheduledTask toScheduledTask(com.soffid.iam.sync.model.ScheduledTaskEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTask} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.ScheduledTask> toScheduledTaskList (java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.ScheduledTask} object 
	 */
	public void scheduledTaskToEntity (com.soffid.iam.sync.api.ScheduledTask source, com.soffid.iam.sync.model.ScheduledTaskEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTask} object 
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity scheduledTaskToEntity (com.soffid.iam.sync.api.ScheduledTask instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTask} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskEntity>  scheduledTaskToEntityList (java.util.Collection<com.soffid.iam.sync.api.ScheduledTask> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} .
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity newScheduledTaskEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ScheduledTaskEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ScheduledTaskEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ScheduledTaskEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
