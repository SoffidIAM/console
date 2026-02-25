//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity ApplicationDomainEntity
 * @see com.soffid.iam.iga.model.ApplicationDomainEntity
 */
public interface ApplicationDomainEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @param informationSystem
	 * @return
	**/
	public com.soffid.iam.iga.model.ApplicationDomainEntity findByName(
		java.lang.String name, 
		java.lang.String informationSystem)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String informationSystem)
	;
	/**
	 * Operation findByDomainAndRole
	 * @param domainName
	 * @param roleName
	 * @return
	**/
	public com.soffid.iam.iga.model.ApplicationDomainEntity findByDomainAndRole(
		java.lang.String domainName, 
		java.lang.String roleName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity findByDomainAndRole(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domainName, java.lang.String roleName)
	;
	/**
	 * Operation findByInformationSystem
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> findByInformationSystem(
		java.lang.String informationSystem)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> findByInformationSystem(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	;
	/**
	 * Operation findByInformationSystemPattern
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> findByInformationSystemPattern(
		java.lang.String informationSystem)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> findByInformationSystemPattern(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Domain} object 
	 */
	public void toDomain(com.soffid.iam.iga.model.ApplicationDomainEntity source, com.soffid.iam.iga.api.Domain target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Domain} object 
	 */
	public com.soffid.iam.iga.api.Domain toDomain(com.soffid.iam.iga.model.ApplicationDomainEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Domain} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Domain> toDomainList (java.util.Collection<com.soffid.iam.iga.model.ApplicationDomainEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Domain} object 
	 */
	public void domainToEntity (com.soffid.iam.iga.api.Domain source, com.soffid.iam.iga.model.ApplicationDomainEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Domain} object 
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity domainToEntity (com.soffid.iam.iga.api.Domain instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Domain} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity>  domainToEntityList (java.util.Collection<com.soffid.iam.iga.api.Domain> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} .
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity newApplicationDomainEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.ApplicationDomainEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.ApplicationDomainEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.ApplicationDomainEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.ApplicationDomainEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.ApplicationDomainEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.ApplicationDomainEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.ApplicationDomainEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.ApplicationDomainEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.ApplicationDomainEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
