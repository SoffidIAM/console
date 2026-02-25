//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity UserDomainEntity
 * @see com.soffid.iam.iga.model.UserDomainEntity
 */
public interface UserDomainEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.UserDomainEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.UserDomainEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findBySytem
	 * @param system
	 * @return
	**/
	public com.soffid.iam.iga.model.UserDomainEntity findBySytem(
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.UserDomainEntity findBySytem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String system)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.UserDomain} object 
	 */
	public void toUserDomain(com.soffid.iam.iga.model.UserDomainEntity source, com.soffid.iam.iga.api.UserDomain target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserDomain} object 
	 */
	public com.soffid.iam.iga.api.UserDomain toUserDomain(com.soffid.iam.iga.model.UserDomainEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.UserDomain} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.UserDomain> toUserDomainList (java.util.Collection<com.soffid.iam.iga.model.UserDomainEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.UserDomain} object 
	 */
	public void userDomainToEntity (com.soffid.iam.iga.api.UserDomain source, com.soffid.iam.iga.model.UserDomainEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserDomain} object 
	 */
	public com.soffid.iam.iga.model.UserDomainEntity userDomainToEntity (com.soffid.iam.iga.api.UserDomain instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.UserDomain} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserDomainEntity>  userDomainToEntityList (java.util.Collection<com.soffid.iam.iga.api.UserDomain> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} .
	 */
	public com.soffid.iam.iga.model.UserDomainEntity newUserDomainEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserDomainEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserDomainEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserDomainEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserDomainEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserDomainEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserDomainEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserDomainEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserDomainEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserDomainEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserDomainEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserDomainEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserDomainEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
