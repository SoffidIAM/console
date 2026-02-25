//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity HostEntity
 * @see com.soffid.iam.am.model.HostEntity
 */
public interface HostEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.HostEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.HostEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findBySerialNumber
	 * @param serialNumber
	 * @return
	**/
	public com.soffid.iam.am.model.HostEntity findBySerialNumber(
		java.lang.String serialNumber)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.HostEntity findBySerialNumber(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String serialNumber)
	;
	/**
	 * Operation getTasks
	 * @param hostName
	 * @return
	**/
	public java.lang.String[] getTasks(
		java.lang.String hostName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.String[] getTasks(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String hostName)
	;
	/**
	 * Operation findByIP
	 * @param ip
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.am.model.HostEntity> findByIP(
		java.lang.String ip)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.am.model.HostEntity> findByIP(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ip)
	;
	/**
	 * Operation findByCurrentUserAndIpAddress
	 * @param userId
	 * @param ip
	 * @return
	**/
	public java.util.List<com.soffid.iam.am.model.HostEntity> findByCurrentUserAndIpAddress(
		java.lang.Long userId, 
		java.lang.String ip)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntity> findByCurrentUserAndIpAddress(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long userId, java.lang.String ip)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Host} object 
	 */
	public void toHost(com.soffid.iam.am.model.HostEntity source, com.soffid.iam.am.api.Host target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Host} object 
	 */
	public com.soffid.iam.am.api.Host toHost(com.soffid.iam.am.model.HostEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Host} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Host> toHostList (java.util.Collection<com.soffid.iam.am.model.HostEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Host} object 
	 */
	public void hostToEntity (com.soffid.iam.am.api.Host source, com.soffid.iam.am.model.HostEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Host} object 
	 */
	public com.soffid.iam.am.model.HostEntity hostToEntity (com.soffid.iam.am.api.Host instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Host} list 
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntity>  hostToEntityList (java.util.Collection<com.soffid.iam.am.api.Host> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.HostEntity} .
	 */
	public com.soffid.iam.am.model.HostEntity newHostEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.HostEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.HostEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.HostEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.HostEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.HostEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.HostEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.HostEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.HostEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.HostEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.HostEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.HostEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.HostEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.HostEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
