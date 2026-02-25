//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity AccountPasswordEntity
 * @see com.soffid.iam.am.model.AccountPasswordEntity
 */
public interface AccountPasswordEntityDao

{
	/**
	 * Operation findLastByAccount
	 * @param accountId
	 * @return
	**/
	public com.soffid.iam.am.model.AccountPasswordEntity findLastByAccount(
		long accountId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.AccountPasswordEntity findLastByAccount(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long accountId)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.PasswordStatus} object 
	 */
	public void toPasswordStatus(com.soffid.iam.am.model.AccountPasswordEntity source, com.soffid.iam.am.api.PasswordStatus target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordStatus} object 
	 */
	public com.soffid.iam.am.api.PasswordStatus toPasswordStatus(com.soffid.iam.am.model.AccountPasswordEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordStatus} list 
	 */
	public java.util.List<com.soffid.iam.am.api.PasswordStatus> toPasswordStatusList (java.util.Collection<com.soffid.iam.am.model.AccountPasswordEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.PasswordStatus} object 
	 */
	public void passwordStatusToEntity (com.soffid.iam.am.api.PasswordStatus source, com.soffid.iam.am.model.AccountPasswordEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordStatus} object 
	 */
	public com.soffid.iam.am.model.AccountPasswordEntity passwordStatusToEntity (com.soffid.iam.am.api.PasswordStatus instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordStatus} list 
	 */
	public java.util.List<com.soffid.iam.am.model.AccountPasswordEntity>  passwordStatusToEntityList (java.util.Collection<com.soffid.iam.am.api.PasswordStatus> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} .
	 */
	public com.soffid.iam.am.model.AccountPasswordEntity newAccountPasswordEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.AccountPasswordEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.AccountPasswordEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.AccountPasswordEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.AccountPasswordEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.AccountPasswordEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.AccountPasswordEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.AccountPasswordEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.AccountPasswordEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.AccountPasswordEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.AccountPasswordEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.AccountPasswordEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.AccountPasswordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.AccountPasswordEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
