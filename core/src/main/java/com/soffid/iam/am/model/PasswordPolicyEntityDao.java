//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity PasswordPolicyEntity
 * @see com.soffid.iam.am.model.PasswordPolicyEntity
 */
public interface PasswordPolicyEntityDao

{
	/**
	 * Operation findByPasswordDomainAndUserType
	 * @param passwordDomain
	 * @param userType
	 * @return
	**/
	public com.soffid.iam.am.model.PasswordPolicyEntity findByPasswordDomainAndUserType(
		java.lang.String passwordDomain, 
		java.lang.String userType)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity findByPasswordDomainAndUserType(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String passwordDomain, java.lang.String userType)
	;
	/**
	 * Operation findByPasswordDomain
	 * @param passwordDomain
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> findByPasswordDomain(
		java.lang.String passwordDomain)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> findByPasswordDomain(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String passwordDomain)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.PasswordPolicy} object 
	 */
	public void toPasswordPolicy(com.soffid.iam.am.model.PasswordPolicyEntity source, com.soffid.iam.am.api.PasswordPolicy target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordPolicy} object 
	 */
	public com.soffid.iam.am.api.PasswordPolicy toPasswordPolicy(com.soffid.iam.am.model.PasswordPolicyEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordPolicy} list 
	 */
	public java.util.List<com.soffid.iam.am.api.PasswordPolicy> toPasswordPolicyList (java.util.Collection<com.soffid.iam.am.model.PasswordPolicyEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.PasswordPolicy} object 
	 */
	public void passwordPolicyToEntity (com.soffid.iam.am.api.PasswordPolicy source, com.soffid.iam.am.model.PasswordPolicyEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordPolicy} object 
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity passwordPolicyToEntity (com.soffid.iam.am.api.PasswordPolicy instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordPolicy} list 
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity>  passwordPolicyToEntityList (java.util.Collection<com.soffid.iam.am.api.PasswordPolicy> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} .
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity newPasswordPolicyEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.PasswordPolicyEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.PasswordPolicyEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.PasswordPolicyEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.PasswordPolicyEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.PasswordPolicyEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.PasswordPolicyEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.PasswordPolicyEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.PasswordPolicyEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.PasswordPolicyEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.PasswordPolicyEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.PasswordPolicyEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
