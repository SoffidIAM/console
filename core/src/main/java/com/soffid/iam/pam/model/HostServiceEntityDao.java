//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity HostServiceEntity
 * @see com.soffid.iam.pam.model.HostServiceEntity
 */
public interface HostServiceEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.HostService} object 
	 */
	public void toHostService(com.soffid.iam.pam.model.HostServiceEntity source, com.soffid.iam.pam.api.HostService target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostService} object 
	 */
	public com.soffid.iam.pam.api.HostService toHostService(com.soffid.iam.pam.model.HostServiceEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostService} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.HostService> toHostServiceList (java.util.Collection<com.soffid.iam.pam.model.HostServiceEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.HostService} object 
	 */
	public void hostServiceToEntity (com.soffid.iam.pam.api.HostService source, com.soffid.iam.pam.model.HostServiceEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostService} object 
	 */
	public com.soffid.iam.pam.model.HostServiceEntity hostServiceToEntity (com.soffid.iam.pam.api.HostService instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostService} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.HostServiceEntity>  hostServiceToEntityList (java.util.Collection<com.soffid.iam.pam.api.HostService> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} .
	 */
	public com.soffid.iam.pam.model.HostServiceEntity newHostServiceEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.HostServiceEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.HostServiceEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.HostServiceEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.HostServiceEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.HostServiceEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.HostServiceEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.HostServiceEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.HostServiceEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.HostServiceEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.HostServiceEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.HostServiceEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostServiceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.HostServiceEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
