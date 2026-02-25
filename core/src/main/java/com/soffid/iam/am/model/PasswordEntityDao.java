//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity PasswordEntity
 * @see com.soffid.iam.am.model.PasswordEntity
 */
public interface PasswordEntityDao

{
	/**
	 * Operation findLastByUserDomain
	 * @param user
	 * @param domain
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.am.model.PasswordEntity> findLastByUserDomain(
		com.soffid.iam.base.model.UserEntity user, 
		com.soffid.iam.am.model.PasswordDomainEntity domain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.am.model.PasswordEntity> findLastByUserDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity domain)
	;
	/**
	 * Operation findByUserDomain
	 * @param user
	 * @param domain
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.PasswordEntity> findByUserDomain(
		com.soffid.iam.base.model.UserEntity user, 
		com.soffid.iam.am.model.PasswordDomainEntity domain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordEntity> findByUserDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.base.model.UserEntity user, com.soffid.iam.am.model.PasswordDomainEntity domain)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.PasswordStatus} object 
	 */
	public void toPasswordStatus(com.soffid.iam.am.model.PasswordEntity source, com.soffid.iam.am.api.PasswordStatus target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordStatus} object 
	 */
	public com.soffid.iam.am.api.PasswordStatus toPasswordStatus(com.soffid.iam.am.model.PasswordEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordStatus} list 
	 */
	public java.util.List<com.soffid.iam.am.api.PasswordStatus> toPasswordStatusList (java.util.Collection<com.soffid.iam.am.model.PasswordEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.PasswordStatus} object 
	 */
	public void passwordStatusToEntity (com.soffid.iam.am.api.PasswordStatus source, com.soffid.iam.am.model.PasswordEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordStatus} object 
	 */
	public com.soffid.iam.am.model.PasswordEntity passwordStatusToEntity (com.soffid.iam.am.api.PasswordStatus instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordStatus} list 
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordEntity>  passwordStatusToEntityList (java.util.Collection<com.soffid.iam.am.api.PasswordStatus> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.PasswordEntity} .
	 */
	public com.soffid.iam.am.model.PasswordEntity newPasswordEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.PasswordEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.PasswordEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.PasswordEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.PasswordEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.PasswordEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.PasswordEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.PasswordEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.PasswordEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.PasswordEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.PasswordEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.PasswordEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.PasswordEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.PasswordEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.PasswordEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
