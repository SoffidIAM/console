//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity HostAttributeEntity
 * @see com.soffid.iam.am.model.HostAttributeEntity
 */
public interface HostAttributeEntityDao

{
	/**
	 * Operation findByNameAndValue
	 * @param name
	 * @param value
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.HostAttributeEntity> findByNameAndValue(
		java.lang.String name, 
		java.lang.String value)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.HostAttributeEntity> findByNameAndValue(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String value)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.HostAttributeEntity} .
	 */
	public com.soffid.iam.am.model.HostAttributeEntity newHostAttributeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.HostAttributeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.HostAttributeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.HostAttributeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.HostAttributeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.HostAttributeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.HostAttributeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.HostAttributeEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.HostAttributeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.HostAttributeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.HostAttributeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.HostAttributeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.HostAttributeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.HostAttributeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.HostAttributeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.HostAttributeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.HostAttributeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.HostAttributeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.HostAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.HostAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.HostAttributeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.HostAttributeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
