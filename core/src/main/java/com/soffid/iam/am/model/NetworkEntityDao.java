//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity NetworkEntity
 * @see com.soffid.iam.am.model.NetworkEntity
 */
public interface NetworkEntityDao

{
	/**
	 * Operation findByAddress
	 * @param ip
	 * @return
	**/
	public com.soffid.iam.am.model.NetworkEntity findByAddress(
		java.lang.String ip)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.NetworkEntity findByAddress(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ip)
	;
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.am.model.NetworkEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.NetworkEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation countByNetwork
	 * @param network
	 * @return
	**/
	public java.lang.Long countByNetwork(
		java.lang.String network)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long countByNetwork(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String network)
	;
	/**
	 * Operation getFirstFreeIP
	 * @param ipXarxa
	 * @param mascara
	 * @return
	**/
	public java.lang.String getFirstFreeIP(
		java.lang.String ipXarxa, 
		java.lang.String mascara)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.String getFirstFreeIP(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String ipXarxa, java.lang.String mascara)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Network} object 
	 */
	public void toNetwork(com.soffid.iam.am.model.NetworkEntity source, com.soffid.iam.am.api.Network target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Network} object 
	 */
	public com.soffid.iam.am.api.Network toNetwork(com.soffid.iam.am.model.NetworkEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Network} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Network> toNetworkList (java.util.Collection<com.soffid.iam.am.model.NetworkEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Network} object 
	 */
	public void networkToEntity (com.soffid.iam.am.api.Network source, com.soffid.iam.am.model.NetworkEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Network} object 
	 */
	public com.soffid.iam.am.model.NetworkEntity networkToEntity (com.soffid.iam.am.api.Network instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Network} list 
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkEntity>  networkToEntityList (java.util.Collection<com.soffid.iam.am.api.Network> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.NetworkEntity} .
	 */
	public com.soffid.iam.am.model.NetworkEntity newNetworkEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.NetworkEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.NetworkEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.NetworkEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.NetworkEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.NetworkEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.NetworkEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.NetworkEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.NetworkEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.NetworkEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.NetworkEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.NetworkEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.NetworkEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.NetworkEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
