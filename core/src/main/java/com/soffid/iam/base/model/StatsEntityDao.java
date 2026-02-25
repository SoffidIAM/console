//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity StatsEntity
 * @see com.soffid.iam.base.model.StatsEntity
 */
public interface StatsEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @param since
	 * @param until
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.StatsEntity> findByName(
		java.lang.String name, 
		java.lang.String since, 
		java.lang.String until)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.base.model.StatsEntity> findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String since, java.lang.String until)
	;
	/**
	 * Operation purge
	 * @param days
	**/
	public void purge(
		int days) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.StatsEntity} .
	 */
	public com.soffid.iam.base.model.StatsEntity newStatsEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.StatsEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.StatsEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.StatsEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.StatsEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.StatsEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.StatsEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.StatsEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.StatsEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.StatsEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.StatsEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.StatsEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.StatsEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.StatsEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.StatsEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.StatsEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
