//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.model;
/**
 * DAO for Entity TaskLogEntity
 * @see com.soffid.iam.sync.model.TaskLogEntity
 */
public interface TaskLogEntityDao

{
	/**
	 * Operation countTasksByServerAndSystem
	 * @param server
	 * @return
	**/
	public java.util.Collection<java.lang.Object[]> countTasksByServerAndSystem(
		java.lang.String server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<java.lang.Object[]> countTasksByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	;
	/**
	 * Operation countTasksByServerAndSystem
	 * @param server
	 * @param serverInstance
	 * @return
	**/
	public java.util.Collection<java.lang.Object[]> countTasksByServerAndSystem(
		java.lang.String server, 
		java.lang.String serverInstance)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<java.lang.Object[]> countTasksByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	;
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
	 * Operation findAllHavingTasqueByAgentAndServer
	 * @param server
	 * @param system
	 * @param status
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByAgentAndServer(
		java.lang.String server, 
		java.lang.String system, 
		java.lang.String status)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByAgentAndServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String system, java.lang.String status)
	;
	/**
	 * Operation findAllHavingTasqueByServer
	 * @param server
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByServer(
		java.lang.String server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByServer(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	;
	/**
	 * Operation findAllHavingTasqueByServerAndServerInstance
	 * @param server
	 * @param serverInstance
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByServerAndServerInstance(
		java.lang.String server, 
		java.lang.String serverInstance)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findAllHavingTasqueByServerAndServerInstance(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance)
	;
	/**
	 * Operation findByTaskID
	 * @param taskId
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByTaskID(
		java.lang.Long taskId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByTaskID(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long taskId)
	;
	/**
	 * Operation findByServerAndSystem
	 * @param server
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByServerAndSystem(
		java.lang.String server, 
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String system)
	;
	/**
	 * Operation findByServerAndSystem
	 * @param server
	 * @param serverInstance
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByServerAndSystem(
		java.lang.String server, 
		java.lang.String serverInstance, 
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findByServerAndSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server, java.lang.String serverInstance, java.lang.String system)
	;
	/**
	 * Operation findBySystem
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findBySystem(
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} .
	 */
	public com.soffid.iam.sync.model.TaskLogEntity newTaskLogEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.TaskLogEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.TaskLogEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.TaskLogEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.TaskLogEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.TaskLogEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.TaskLogEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.TaskLogEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.TaskLogEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.TaskLogEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.sync.model.TaskLogEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.TaskLogEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
