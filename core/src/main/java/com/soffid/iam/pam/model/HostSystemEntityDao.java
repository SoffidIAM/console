//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity HostSystemEntity
 * @see com.soffid.iam.pam.model.HostSystemEntity
 */
public interface HostSystemEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} .
	 */
	public com.soffid.iam.pam.model.HostSystemEntity newHostSystemEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.HostSystemEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.HostSystemEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.HostSystemEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.HostSystemEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.HostSystemEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.HostSystemEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.HostSystemEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.HostSystemEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.HostSystemEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.HostSystemEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.HostSystemEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.HostSystemEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.HostSystemEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
