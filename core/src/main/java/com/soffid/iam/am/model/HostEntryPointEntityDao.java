//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity HostEntryPointEntity
 * @see com.soffid.iam.am.model.HostEntryPointEntity
 */
public interface HostEntryPointEntityDao

{
	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.HostEntryPointEntity} .
	 */
	public com.soffid.iam.am.model.HostEntryPointEntity newHostEntryPointEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.HostEntryPointEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.HostEntryPointEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.HostEntryPointEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.HostEntryPointEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.HostEntryPointEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.HostEntryPointEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.HostEntryPointEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.HostEntryPointEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.HostEntryPointEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntryPointEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.HostEntryPointEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.HostEntryPointEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.HostEntryPointEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.HostEntryPointEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.HostEntryPointEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.HostEntryPointEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.HostEntryPointEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.HostEntryPointEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntryPointEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.HostEntryPointEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntryPointEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
