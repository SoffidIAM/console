//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity ServiceEntity
 * @see com.soffid.iam.am.model.ServiceEntity
 */
public interface ServiceEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.ServiceEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.ServiceEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findAllByName
	 * @param name
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.ServiceEntity> findAllByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.ServiceEntity> findAllByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Service} object 
	 */
	public void toService(com.soffid.iam.am.model.ServiceEntity source, com.soffid.iam.am.api.Service target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Service} object 
	 */
	public com.soffid.iam.am.api.Service toService(com.soffid.iam.am.model.ServiceEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Service} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Service> toServiceList (java.util.Collection<com.soffid.iam.am.model.ServiceEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Service} object 
	 */
	public void serviceToEntity (com.soffid.iam.am.api.Service source, com.soffid.iam.am.model.ServiceEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Service} object 
	 */
	public com.soffid.iam.am.model.ServiceEntity serviceToEntity (com.soffid.iam.am.api.Service instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Service} list 
	 */
	public java.util.List<com.soffid.iam.am.model.ServiceEntity>  serviceToEntityList (java.util.Collection<com.soffid.iam.am.api.Service> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.ServiceEntity} .
	 */
	public com.soffid.iam.am.model.ServiceEntity newServiceEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.ServiceEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.ServiceEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.ServiceEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.ServiceEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.ServiceEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.ServiceEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.ServiceEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.ServiceEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.ServiceEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.ServiceEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.ServiceEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.ServiceEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.ServiceEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.ServiceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.ServiceEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
