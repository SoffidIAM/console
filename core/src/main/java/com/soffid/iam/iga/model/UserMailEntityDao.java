//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity UserMailEntity
 * @see com.soffid.iam.iga.model.UserMailEntity
 */
public interface UserMailEntityDao

{
	/**
	 * Operation findByListAndUser
	 * @param mailList
	 * @param domain
	 * @param user
	 * @return
	**/
	public com.soffid.iam.iga.model.UserMailEntity findByListAndUser(
		java.lang.String mailList, 
		java.lang.String domain, 
		java.lang.String user)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.UserMailEntity findByListAndUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String mailList, java.lang.String domain, java.lang.String user)
	;
	/**
	 * Operation findByUser
	 * @param user
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> findByUser(
		java.lang.String user)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String user)
	;
	/**
	 * Operation findByMailList
	 * @param mailList
	 * @param domain
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> findByMailList(
		java.lang.String mailList, 
		java.lang.String domain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> findByMailList(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String mailList, java.lang.String domain)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.UserMailList} object 
	 */
	public void toUserMailList(com.soffid.iam.iga.model.UserMailEntity source, com.soffid.iam.iga.api.UserMailList target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserMailList} object 
	 */
	public com.soffid.iam.iga.api.UserMailList toUserMailList(com.soffid.iam.iga.model.UserMailEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserMailList} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.UserMailList> toUserMailListList (java.util.Collection<com.soffid.iam.iga.model.UserMailEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.UserMailList} object 
	 */
	public void userMailListToEntity (com.soffid.iam.iga.api.UserMailList source, com.soffid.iam.iga.model.UserMailEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserMailList} object 
	 */
	public com.soffid.iam.iga.model.UserMailEntity userMailListToEntity (com.soffid.iam.iga.api.UserMailList instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserMailList} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity>  userMailListToEntityList (java.util.Collection<com.soffid.iam.iga.api.UserMailList> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserMailEntity} .
	 */
	public com.soffid.iam.iga.model.UserMailEntity newUserMailEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserMailEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserMailEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserMailEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserMailEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserMailEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserMailEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserMailEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserMailEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserMailEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserMailEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserMailEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserMailEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserMailEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
