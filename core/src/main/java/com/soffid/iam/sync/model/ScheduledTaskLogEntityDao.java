//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.model;
/**
 * DAO for Entity ScheduledTaskLogEntity
 * @see com.soffid.iam.sync.model.ScheduledTaskLogEntity
 */
public interface ScheduledTaskLogEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.ScheduledTaskLog} object 
	 */
	public void toScheduledTaskLog(com.soffid.iam.sync.model.ScheduledTaskLogEntity source, com.soffid.iam.sync.api.ScheduledTaskLog target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTaskLog} object 
	 */
	public com.soffid.iam.sync.api.ScheduledTaskLog toScheduledTaskLog(com.soffid.iam.sync.model.ScheduledTaskLogEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTaskLog} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.ScheduledTaskLog> toScheduledTaskLogList (java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskLogEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.ScheduledTaskLog} object 
	 */
	public void scheduledTaskLogToEntity (com.soffid.iam.sync.api.ScheduledTaskLog source, com.soffid.iam.sync.model.ScheduledTaskLogEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTaskLog} object 
	 */
	public com.soffid.iam.sync.model.ScheduledTaskLogEntity scheduledTaskLogToEntity (com.soffid.iam.sync.api.ScheduledTaskLog instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTaskLog} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity>  scheduledTaskLogToEntityList (java.util.Collection<com.soffid.iam.sync.api.ScheduledTaskLog> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} .
	 */
	public com.soffid.iam.sync.model.ScheduledTaskLogEntity newScheduledTaskLogEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ScheduledTaskLogEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ScheduledTaskLogEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ScheduledTaskLogEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskLogEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskLogEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskLogEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskLogEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskLogEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
