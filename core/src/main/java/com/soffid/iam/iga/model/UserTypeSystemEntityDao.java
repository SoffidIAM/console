//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity UserTypeSystemEntity
 * @see com.soffid.iam.iga.model.UserTypeSystemEntity
 */
public interface UserTypeSystemEntityDao

{
	/**
	 * Operation findBySystem
	 * @param systemName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.UserTypeSystemEntity> findBySystem(
		java.lang.String systemName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserTypeSystemEntity> findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.UserTypeDispatcher} object 
	 */
	public void toUserTypeDispatcher(com.soffid.iam.iga.model.UserTypeSystemEntity source, com.soffid.iam.iga.api.UserTypeDispatcher target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserTypeDispatcher} object 
	 */
	public com.soffid.iam.iga.api.UserTypeDispatcher toUserTypeDispatcher(com.soffid.iam.iga.model.UserTypeSystemEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserTypeDispatcher} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.UserTypeDispatcher> toUserTypeDispatcherList (java.util.Collection<com.soffid.iam.iga.model.UserTypeSystemEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.UserTypeDispatcher} object 
	 */
	public void userTypeDispatcherToEntity (com.soffid.iam.iga.api.UserTypeDispatcher source, com.soffid.iam.iga.model.UserTypeSystemEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserTypeDispatcher} object 
	 */
	public com.soffid.iam.iga.model.UserTypeSystemEntity userTypeDispatcherToEntity (com.soffid.iam.iga.api.UserTypeDispatcher instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserTypeDispatcher} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity>  userTypeDispatcherToEntityList (java.util.Collection<com.soffid.iam.iga.api.UserTypeDispatcher> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} .
	 */
	public com.soffid.iam.iga.model.UserTypeSystemEntity newUserTypeSystemEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserTypeSystemEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserTypeSystemEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserTypeSystemEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserTypeSystemEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserTypeSystemEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserTypeSystemEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserTypeSystemEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserTypeSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserTypeSystemEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
