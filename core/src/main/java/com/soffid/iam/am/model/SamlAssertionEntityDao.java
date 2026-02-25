//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity SamlAssertionEntity
 * @see com.soffid.iam.am.model.SamlAssertionEntity
 */
public interface SamlAssertionEntityDao

{
	/**
	 * Operation findByExternalId
	 * @param externalId
	 * @return
	**/
	public com.soffid.iam.am.model.SamlAssertionEntity findByExternalId(
		java.lang.String externalId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.SamlAssertionEntity findByExternalId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String externalId)
	;
	/**
	 * Operation findExpired
	 * @param d
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.am.model.SamlAssertionEntity> findExpired(
		java.util.Date d)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.am.model.SamlAssertionEntity> findExpired(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date d)
	;
	/**
	 * Operation deleteExpired
	**/
	public void deleteExpired() throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} .
	 */
	public com.soffid.iam.am.model.SamlAssertionEntity newSamlAssertionEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.SamlAssertionEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.SamlAssertionEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.SamlAssertionEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.SamlAssertionEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.SamlAssertionEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.SamlAssertionEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.SamlAssertionEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.SamlAssertionEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.SamlAssertionEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.SamlAssertionEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.SamlAssertionEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.SamlAssertionEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.SamlAssertionEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
