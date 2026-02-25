//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity HostPortEntity
 * @see com.soffid.iam.pam.model.HostPortEntity
 */
public interface HostPortEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public void toHostPort(com.soffid.iam.pam.model.HostPortEntity source, com.soffid.iam.pam.api.HostPort target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public com.soffid.iam.pam.api.HostPort toHostPort(com.soffid.iam.pam.model.HostPortEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostPort} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.HostPort> toHostPortList (java.util.Collection<com.soffid.iam.pam.model.HostPortEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public void hostPortToEntity (com.soffid.iam.pam.api.HostPort source, com.soffid.iam.pam.model.HostPortEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public com.soffid.iam.pam.model.HostPortEntity hostPortToEntity (com.soffid.iam.pam.api.HostPort instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostPort} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.HostPortEntity>  hostPortToEntityList (java.util.Collection<com.soffid.iam.pam.api.HostPort> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.HostPortEntity} .
	 */
	public com.soffid.iam.pam.model.HostPortEntity newHostPortEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.HostPortEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.HostPortEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.HostPortEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.HostPortEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.HostPortEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.HostPortEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.HostPortEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.HostPortEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.HostPortEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.HostPortEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.HostPortEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.HostPortEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.HostPortEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostPortEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.HostPortEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
