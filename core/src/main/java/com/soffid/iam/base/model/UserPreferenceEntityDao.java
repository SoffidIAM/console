//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity UserPreferenceEntity
 * @see com.soffid.iam.base.model.UserPreferenceEntity
 */
public interface UserPreferenceEntityDao

{
	/**
	 * Operation findByNameAndUserName
	 * @param name
	 * @param userName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> findByNameAndUserName(
		java.lang.String name, 
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> findByNameAndUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String userName)
	;
	/**
	 * Operation findByUserName
	 * @param userName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> findByUserName(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.base.model.UserPreferenceEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} .
	 */
	public com.soffid.iam.base.model.UserPreferenceEntity newUserPreferenceEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.UserPreferenceEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.UserPreferenceEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.UserPreferenceEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.UserPreferenceEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.UserPreferenceEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.UserPreferenceEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.UserPreferenceEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.UserPreferenceEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.UserPreferenceEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.UserPreferenceEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.UserPreferenceEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.UserPreferenceEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.UserPreferenceEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
