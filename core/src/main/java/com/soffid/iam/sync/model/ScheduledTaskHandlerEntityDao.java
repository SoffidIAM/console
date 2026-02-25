//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.model;
/**
 * DAO for Entity ScheduledTaskHandlerEntity
 * @see com.soffid.iam.sync.model.ScheduledTaskHandlerEntity
 */
public interface ScheduledTaskHandlerEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.ScheduledTaskHandler} object 
	 */
	public void toScheduledTaskHandler(com.soffid.iam.sync.model.ScheduledTaskHandlerEntity source, com.soffid.iam.sync.api.ScheduledTaskHandler target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTaskHandler} object 
	 */
	public com.soffid.iam.sync.api.ScheduledTaskHandler toScheduledTaskHandler(com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ScheduledTaskHandler} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.ScheduledTaskHandler> toScheduledTaskHandlerList (java.util.Collection<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.ScheduledTaskHandler} object 
	 */
	public void scheduledTaskHandlerToEntity (com.soffid.iam.sync.api.ScheduledTaskHandler source, com.soffid.iam.sync.model.ScheduledTaskHandlerEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTaskHandler} object 
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity scheduledTaskHandlerToEntity (com.soffid.iam.sync.api.ScheduledTaskHandler instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ScheduledTaskHandler} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity>  scheduledTaskHandlerToEntityList (java.util.Collection<com.soffid.iam.sync.api.ScheduledTaskHandler> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} .
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity newScheduledTaskHandlerEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ScheduledTaskHandlerEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ScheduledTaskHandlerEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ScheduledTaskHandlerEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ScheduledTaskHandlerEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
