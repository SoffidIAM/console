//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.model;
/**
 * DAO for Entity TaskEntity
 * @see com.soffid.iam.sync.model.TaskEntity
 */
public interface TaskEntityDao

{
	/**
	 * Operation countTasks
	 * @return
	**/
	public java.lang.Long countTasks()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long countTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation countTasksByServer
	 * @param server
	 * @return
	**/
	public java.lang.Long countTasksByServer(
		java.lang.String server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long countTasksByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	;
	/**
	 * Operation countTasksByServerInstance
	 * @param server
	 * @param serverInstance
	 * @return
	**/
	public java.lang.Long countTasksByServerInstance(
		java.lang.String server, 
		java.lang.String serverInstance)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long countTasksByServerInstance(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	;
	/**
	 * Operation countUnscheduledTasks
	 * @return
	**/
	public java.lang.Long countUnscheduledTasks()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long countUnscheduledTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation startVirtualSourceTransaction
	 * @return
	**/
	public java.lang.String startVirtualSourceTransaction() throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation startVirtualSourceTransaction
	 * @param readonly
	 * @return
	**/
	public java.lang.String startVirtualSourceTransaction(
		boolean readonly) throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation startVirtualSourceTransaction
	 * @param readonly
	 * @param server
	 * @return
	**/
	public java.lang.String startVirtualSourceTransaction(
		boolean readonly, 
		java.lang.String server) throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation countTasksBySystem
	 * @return
	**/
	public java.util.Collection<java.lang.Object[]> countTasksBySystem()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation countTasksBySystem
	 * @param server
	 * @return
	**/
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(
		java.lang.String server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	;
	/**
	 * Operation countTasksBySystem
	 * @param server
	 * @param serverInstance
	 * @return
	**/
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(
		java.lang.String server, 
		java.lang.String serverInstance)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<java.lang.Object[]> countTasksBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	;
	/**
	 * Operation findByAccount
	 * @param user
	 * @param systemName
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByAccount(
		java.lang.String user, 
		java.lang.String systemName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByAccount(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user, java.lang.String systemName)
	;
	/**
	 * Operation findByHash
	 * @param hash
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByHash(
		java.lang.String hash)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByHash(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String hash)
	;
	/**
	 * Operation findByHost
	 * @param host
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByHost(
		java.lang.String host)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByHost(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String host)
	;
	/**
	 * Operation findByServer
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServer(
		java.lang.String server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	;
	/**
	 * Operation findByServerAndServerInstance
	 * @param server
	 * @param serverInstance
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndServerInstance(
		java.lang.String server, 
		java.lang.String serverInstance)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndServerInstance(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	;
	/**
	 * Operation findByServerAndSystem
	 * @param server
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndSystem(
		java.lang.String server, 
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String system)
	;
	/**
	 * Operation findByServerAndSystem
	 * @param server
	 * @param serverInstance
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndSystem(
		java.lang.String server, 
		java.lang.String serverInstance, 
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance, java.lang.String system)
	;
	/**
	 * Operation findBySystem
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findBySystem(
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	;
	/**
	 * Operation findByTaskAndServer
	 * @param transaction
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByTaskAndServer(
		java.lang.String transaction, 
		java.lang.String server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByTaskAndServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String transaction, java.lang.String server)
	;
	/**
	 * Operation findByUser
	 * @param user
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByUser(
		java.lang.String user)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	;
	/**
	 * Operation findDataPendingTasks
	 * @param server
	 * @return
	**/
	public java.util.List<java.lang.Long> findDataPendingTasks(
		java.lang.String server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<java.lang.Long> findDataPendingTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	;
	/**
	 * Operation findDataUnplannedTasks
	 * @return
	**/
	public java.util.List<java.lang.Object[]> findDataUnplannedTasks()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<java.lang.Object[]> findDataUnplannedTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findUnscheduled
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findUnscheduled()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> findUnscheduled(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation cancelUnscheduled
	**/
	public void cancelUnscheduled() throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation cancelUnscheduledCopies
	 * @param entity
	**/
	public void cancelUnscheduledCopies(
		com.soffid.iam.sync.model.TaskEntity entity) throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation createForce
	 * @param tasque
	**/
	public void createForce(
		com.soffid.iam.sync.model.TaskEntity tasque) throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation createNoFlush
	 * @param tasque
	**/
	public void createNoFlush(
		com.soffid.iam.sync.model.TaskEntity tasque) throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation finishVirtualSourceTransaction
	 * @param virtualTransactionId
	**/
	public void finishVirtualSourceTransaction(
		java.lang.String virtualTransactionId) throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Operation releaseAll
	**/
	public void releaseAll() throws com.soffid.iam.exception.InternalErrorException, com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.Task} object 
	 */
	public void toTask(com.soffid.iam.sync.model.TaskEntity source, com.soffid.iam.sync.api.Task target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.Task} object 
	 */
	public com.soffid.iam.sync.api.Task toTask(com.soffid.iam.sync.model.TaskEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.Task} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.Task> toTaskList (java.util.Collection<com.soffid.iam.sync.model.TaskEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.Task} object 
	 */
	public void taskToEntity (com.soffid.iam.sync.api.Task source, com.soffid.iam.sync.model.TaskEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.Task} object 
	 */
	public com.soffid.iam.sync.model.TaskEntity taskToEntity (com.soffid.iam.sync.api.Task instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.Task} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity>  taskToEntityList (java.util.Collection<com.soffid.iam.sync.api.Task> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.TaskEntity} .
	 */
	public com.soffid.iam.sync.model.TaskEntity newTaskEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.TaskEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.TaskEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.TaskEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.TaskEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.TaskEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.TaskEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.TaskEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.TaskEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.TaskEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.TaskEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.TaskEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.sync.model.TaskEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
