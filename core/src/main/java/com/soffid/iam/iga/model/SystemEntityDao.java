//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity SystemEntity
 * @see com.soffid.iam.iga.model.SystemEntity
 */
public interface SystemEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.SystemEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.SystemEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findSoffidSystem
	 * @return
	**/
	public com.soffid.iam.iga.model.SystemEntity findSoffidSystem()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.SystemEntity findSoffidSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findByUsage
	 * @param usage
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findByUsage(
		java.lang.String usage)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findByUsage(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String usage)
	;
	/**
	 * Operation findServerTenants
	 * @param server
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findServerTenants(
		java.lang.String server)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findServerTenants(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String server)
	;
	/**
	 * Operation findServices
	 * @param url
	 * @param t
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findServices(
		java.lang.String url, 
		boolean t)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.SystemEntity> findServices(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String url, boolean t)
	;
	/**
	 * Operation findActives
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> findActives()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> findActives(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findByFilter
	 * @param name
	 * @param className
	 * @param url
	 * @param roleBased
	 * @param trusted
	 * @param active
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> findByFilter(
		java.lang.String name, 
		java.lang.String className, 
		java.lang.String url, 
		java.lang.String roleBased, 
		java.lang.String trusted, 
		java.lang.String active)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> findByFilter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String className, java.lang.String url, java.lang.String roleBased, java.lang.String trusted, java.lang.String active)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.System} object 
	 */
	public void toSystem(com.soffid.iam.iga.model.SystemEntity source, com.soffid.iam.iga.api.System target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.System} object 
	 */
	public com.soffid.iam.iga.api.System toSystem(com.soffid.iam.iga.model.SystemEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.System} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.System> toSystemList (java.util.Collection<com.soffid.iam.iga.model.SystemEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.System} object 
	 */
	public void systemToEntity (com.soffid.iam.iga.api.System source, com.soffid.iam.iga.model.SystemEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.System} object 
	 */
	public com.soffid.iam.iga.model.SystemEntity systemToEntity (com.soffid.iam.iga.api.System instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.System} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity>  systemToEntityList (java.util.Collection<com.soffid.iam.iga.api.System> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.SystemEntity} .
	 */
	public com.soffid.iam.iga.model.SystemEntity newSystemEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.SystemEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.SystemEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.SystemEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.SystemEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.SystemEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.SystemEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.SystemEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.SystemEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.SystemEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.SystemEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.SystemEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.SystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.SystemEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
