//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity MailDomainEntity
 * @see com.soffid.iam.iga.model.MailDomainEntity
 */
public interface MailDomainEntityDao

{
	/**
	 * Operation findByCode
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.MailDomainEntity findByCode(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.MailDomainEntity findByCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.MailDomain} object 
	 */
	public void toMailDomain(com.soffid.iam.iga.model.MailDomainEntity source, com.soffid.iam.iga.api.MailDomain target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailDomain} object 
	 */
	public com.soffid.iam.iga.api.MailDomain toMailDomain(com.soffid.iam.iga.model.MailDomainEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.MailDomain} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.MailDomain> toMailDomainList (java.util.Collection<com.soffid.iam.iga.model.MailDomainEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.MailDomain} object 
	 */
	public void mailDomainToEntity (com.soffid.iam.iga.api.MailDomain source, com.soffid.iam.iga.model.MailDomainEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailDomain} object 
	 */
	public com.soffid.iam.iga.model.MailDomainEntity mailDomainToEntity (com.soffid.iam.iga.api.MailDomain instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.MailDomain} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.MailDomainEntity>  mailDomainToEntityList (java.util.Collection<com.soffid.iam.iga.api.MailDomain> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.MailDomainEntity} .
	 */
	public com.soffid.iam.iga.model.MailDomainEntity newMailDomainEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.MailDomainEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.MailDomainEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.MailDomainEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.MailDomainEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailDomainEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.MailDomainEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.MailDomainEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.MailDomainEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.MailDomainEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.MailDomainEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.MailDomainEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.MailDomainEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.MailDomainEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.MailDomainEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.MailDomainEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.MailDomainEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.MailDomainEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.MailDomainEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.MailDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.MailDomainEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
