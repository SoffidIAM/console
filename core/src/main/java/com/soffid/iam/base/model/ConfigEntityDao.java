//
// (C) 2013 Soffid
//
//

package com.soffid.iam.base.model;
/**
 * DAO for Entity ConfigEntity
 * @see com.soffid.iam.base.model.ConfigEntity
 */
public interface ConfigEntityDao

{
	/**
	 * Operation findByCodeAndNetworkCode
	 * @param name
	 * @param networkName
	 * @return
	**/
	public com.soffid.iam.base.model.ConfigEntity findByCodeAndNetworkCode(
		java.lang.String name, 
		java.lang.String networkName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.ConfigEntity findByCodeAndNetworkCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String networkName)
	;
	/**
	 * Operation findByTenantNameAndNetwork
	 * @param tenant
	 * @param name
	 * @param networkName
	 * @return
	**/
	public com.soffid.iam.base.model.ConfigEntity findByTenantNameAndNetwork(
		java.lang.String tenant, 
		java.lang.String name, 
		java.lang.String networkName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.base.model.ConfigEntity findByTenantNameAndNetwork(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String tenant, java.lang.String name, java.lang.String networkName)
	;
	/**
	 * Operation findByFilter
	 * @param name
	 * @param network
	 * @param value
	 * @param description
	 * @return
	**/
	public java.util.List<com.soffid.iam.base.model.ConfigEntity> findByFilter(
		java.lang.String name, 
		java.lang.String network, 
		java.lang.String value, 
		java.lang.String description)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.base.model.ConfigEntity> findByFilter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name, java.lang.String network, java.lang.String value, java.lang.String description)
	;
	/**
	 * Operation createMasterConfig
	 * @param entity
	**/
	public void createMasterConfig(
		com.soffid.iam.base.model.ConfigEntity entity) throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.base.api.Configuration} object 
	 */
	public void toConfiguration(com.soffid.iam.base.model.ConfigEntity source, com.soffid.iam.base.api.Configuration target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Configuration} object 
	 */
	public com.soffid.iam.base.api.Configuration toConfiguration(com.soffid.iam.base.model.ConfigEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.Configuration} list 
	 */
	public java.util.List<com.soffid.iam.base.api.Configuration> toConfigurationList (java.util.Collection<com.soffid.iam.base.model.ConfigEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.Configuration} object 
	 */
	public void configurationToEntity (com.soffid.iam.base.api.Configuration source, com.soffid.iam.base.model.ConfigEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Configuration} object 
	 */
	public com.soffid.iam.base.model.ConfigEntity configurationToEntity (com.soffid.iam.base.api.Configuration instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.Configuration} list 
	 */
	public java.util.List<com.soffid.iam.base.model.ConfigEntity>  configurationToEntityList (java.util.Collection<com.soffid.iam.base.api.Configuration> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.base.model.ConfigEntity} .
	 */
	public com.soffid.iam.base.model.ConfigEntity newConfigEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.base.model.ConfigEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.base.model.ConfigEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.base.model.ConfigEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.base.model.ConfigEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.base.model.ConfigEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 */
	public com.soffid.iam.base.model.ConfigEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.base.model.ConfigEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.base.model.ConfigEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.base.model.ConfigEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.base.model.ConfigEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.base.model.ConfigEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.base.model.ConfigEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.base.model.ConfigEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.base.model.ConfigEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.base.model.ConfigEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
