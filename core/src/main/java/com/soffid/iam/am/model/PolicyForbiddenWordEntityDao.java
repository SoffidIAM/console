//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity PolicyForbiddenWordEntity
 * @see com.soffid.iam.am.model.PolicyForbiddenWordEntity
 */
public interface PolicyForbiddenWordEntityDao

{
	/**
	 * Operation findByPasswordPolicy
	 * @param passwordPolicy
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> findByPasswordPolicy(
		com.soffid.iam.am.api.PasswordPolicy passwordPolicy)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> findByPasswordPolicy(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.api.PasswordPolicy passwordPolicy)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} object 
	 */
	public void toPasswordPolicyForbbidenWord(com.soffid.iam.am.model.PolicyForbiddenWordEntity source, com.soffid.iam.am.api.PasswordPolicyForbbidenWord target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} object 
	 */
	public com.soffid.iam.am.api.PasswordPolicyForbbidenWord toPasswordPolicyForbbidenWord(com.soffid.iam.am.model.PolicyForbiddenWordEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} list 
	 */
	public java.util.List<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> toPasswordPolicyForbbidenWordList (java.util.Collection<com.soffid.iam.am.model.PolicyForbiddenWordEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} object 
	 */
	public void passwordPolicyForbbidenWordToEntity (com.soffid.iam.am.api.PasswordPolicyForbbidenWord source, com.soffid.iam.am.model.PolicyForbiddenWordEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} object 
	 */
	public com.soffid.iam.am.model.PolicyForbiddenWordEntity passwordPolicyForbbidenWordToEntity (com.soffid.iam.am.api.PasswordPolicyForbbidenWord instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.PasswordPolicyForbbidenWord} list 
	 */
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity>  passwordPolicyForbbidenWordToEntityList (java.util.Collection<com.soffid.iam.am.api.PasswordPolicyForbbidenWord> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} .
	 */
	public com.soffid.iam.am.model.PolicyForbiddenWordEntity newPolicyForbiddenWordEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.PolicyForbiddenWordEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.PolicyForbiddenWordEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.PolicyForbiddenWordEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.PolicyForbiddenWordEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.PolicyForbiddenWordEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.PolicyForbiddenWordEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.PolicyForbiddenWordEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.PolicyForbiddenWordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.PolicyForbiddenWordEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
