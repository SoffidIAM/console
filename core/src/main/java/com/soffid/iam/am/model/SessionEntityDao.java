//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity SessionEntity
 * @see com.soffid.iam.am.model.SessionEntity
 */
public interface SessionEntityDao

{
	/**
	 * Operation findById
	 * @param id
	 * @return
	**/
	public com.soffid.iam.am.model.SessionEntity findById(
		java.lang.Long id)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.SessionEntity findById(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	;
	/**
	 * Operation findSessionByCriteria
	 * @param port
	 * @param userName
	 * @param serverHostName
	 * @param clientHostName
	 * @return
	**/
	public com.soffid.iam.am.model.SessionEntity findSessionByCriteria(
		java.lang.Long port, 
		java.lang.String userName, 
		java.lang.String serverHostName, 
		java.lang.String clientHostName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.SessionEntity findSessionByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long port, java.lang.String userName, java.lang.String serverHostName, java.lang.String clientHostName)
	;
	/**
	 * Operation findByKey
	 * @param key
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.am.model.SessionEntity> findByKey(
		java.lang.String key)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.am.model.SessionEntity> findByKey(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String key)
	;
	/**
	 * Operation findByBrowserId
	 * @param browserId
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findByBrowserId(
		java.lang.Long browserId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findByBrowserId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long browserId)
	;
	/**
	 * Operation findSessionByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findSessionByUserName(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findSessionByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findSessionsByCriteria
	 * @param port
	 * @param userName
	 * @param serverHostName
	 * @param clientHostName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findSessionsByCriteria(
		java.lang.Long port, 
		java.lang.String userName, 
		java.lang.String serverHostName, 
		java.lang.String clientHostName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> findSessionsByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long port, java.lang.String userName, java.lang.String serverHostName, java.lang.String clientHostName)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Session} object 
	 */
	public void toSession(com.soffid.iam.am.model.SessionEntity source, com.soffid.iam.am.api.Session target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Session} object 
	 */
	public com.soffid.iam.am.api.Session toSession(com.soffid.iam.am.model.SessionEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Session} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Session> toSessionList (java.util.Collection<com.soffid.iam.am.model.SessionEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Session} object 
	 */
	public void sessionToEntity (com.soffid.iam.am.api.Session source, com.soffid.iam.am.model.SessionEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Session} object 
	 */
	public com.soffid.iam.am.model.SessionEntity sessionToEntity (com.soffid.iam.am.api.Session instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Session} list 
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity>  sessionToEntityList (java.util.Collection<com.soffid.iam.am.api.Session> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.SessionEntity} .
	 */
	public com.soffid.iam.am.model.SessionEntity newSessionEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.SessionEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.SessionEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.SessionEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.SessionEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.SessionEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.SessionEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.SessionEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.SessionEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.SessionEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.SessionEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.SessionEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.SessionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.SessionEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
