//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity NetworkAuthorizationEntity
 * @see com.soffid.iam.am.model.NetworkAuthorizationEntity
 */
public interface NetworkAuthorizationEntityDao

{
	/**
	 * Operation findByNetworkAndIdentity
	 * @param networkName
	 * @param identity
	 * @return
	**/
	public com.soffid.iam.am.model.NetworkAuthorizationEntity findByNetworkAndIdentity(
		java.lang.String networkName, 
		java.lang.String identity)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntity findByNetworkAndIdentity(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String networkName, java.lang.String identity)
	;
	/**
	 * Operation findByGroupName
	 * @param groupName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByGroupName(
		java.lang.String groupName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByGroupName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String groupName)
	;
	/**
	 * Operation findByUserName
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByUserName(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findByRoleName
	 * @param roleName
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByRoleName(
		java.lang.String roleName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByRoleName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName)
	;
	/**
	 * Operation findByRole
	 * @param roleName
	 * @param informationSystem
	 * @param system
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByRole(
		java.lang.String roleName, 
		java.lang.String informationSystem, 
		java.lang.String system)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByRole(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String roleName, java.lang.String informationSystem, java.lang.String system)
	;
	/**
	 * Operation findByNetwork
	 * @param network
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByNetwork(
		com.soffid.iam.am.model.NetworkEntity network)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> findByNetwork(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, com.soffid.iam.am.model.NetworkEntity network)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.NetworkAuthorization} object 
	 */
	public void toNetworkAuthorization(com.soffid.iam.am.model.NetworkAuthorizationEntity source, com.soffid.iam.am.api.NetworkAuthorization target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.NetworkAuthorization} object 
	 */
	public com.soffid.iam.am.api.NetworkAuthorization toNetworkAuthorization(com.soffid.iam.am.model.NetworkAuthorizationEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.NetworkAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.api.NetworkAuthorization> toNetworkAuthorizationList (java.util.Collection<com.soffid.iam.am.model.NetworkAuthorizationEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.NetworkAuthorization} object 
	 */
	public void networkAuthorizationToEntity (com.soffid.iam.am.api.NetworkAuthorization source, com.soffid.iam.am.model.NetworkAuthorizationEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.NetworkAuthorization} object 
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntity networkAuthorizationToEntity (com.soffid.iam.am.api.NetworkAuthorization instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.NetworkAuthorization} list 
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity>  networkAuthorizationToEntityList (java.util.Collection<com.soffid.iam.am.api.NetworkAuthorization> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} .
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntity newNetworkAuthorizationEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.NetworkAuthorizationEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.NetworkAuthorizationEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.NetworkAuthorizationEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.NetworkAuthorizationEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.NetworkAuthorizationEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.NetworkAuthorizationEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.NetworkAuthorizationEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.NetworkAuthorizationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkAuthorizationEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
