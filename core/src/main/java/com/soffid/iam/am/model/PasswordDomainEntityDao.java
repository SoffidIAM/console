//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity PasswordDomainEntity
 * @see com.soffid.iam.am.model.PasswordDomainEntity
 */
public interface PasswordDomainEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.PasswordDomainEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findBySystem
	 * @param systemName
	 * @return
	**/
	public com.soffid.iam.am.model.PasswordDomainEntity findBySystem(
		java.lang.String systemName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity findBySystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String systemName)
	;
	/**
	 * Operation findDefaultDomain
	 * @param userId
	 * @return
	**/
	public com.soffid.iam.am.model.PasswordDomainEntity findDefaultDomain(
		long userId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity findDefaultDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, long userId)
	;
	/**
	 * Operation findByUser
	 * @param id
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> findByUser(
		java.lang.Long id)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long id)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.PasswordDomain} object 
	 */
	public void toPasswordDomain(com.soffid.iam.am.model.PasswordDomainEntity source, com.soffid.iam.am.api.PasswordDomain target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordDomain} object 
	 */
	public com.soffid.iam.am.api.PasswordDomain toPasswordDomain(com.soffid.iam.am.model.PasswordDomainEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordDomain} list 
	 */
	public java.util.List<com.soffid.iam.am.api.PasswordDomain> toPasswordDomainList (java.util.Collection<com.soffid.iam.am.model.PasswordDomainEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.PasswordDomain} object 
	 */
	public void passwordDomainToEntity (com.soffid.iam.am.api.PasswordDomain source, com.soffid.iam.am.model.PasswordDomainEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordDomain} object 
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity passwordDomainToEntity (com.soffid.iam.am.api.PasswordDomain instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordDomain} list 
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity>  passwordDomainToEntityList (java.util.Collection<com.soffid.iam.am.api.PasswordDomain> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} .
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity newPasswordDomainEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.PasswordDomainEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.PasswordDomainEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.PasswordDomainEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.PasswordDomainEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.PasswordDomainEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.PasswordDomainEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.PasswordDomainEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.PasswordDomainEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.PasswordDomainEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordDomainEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
