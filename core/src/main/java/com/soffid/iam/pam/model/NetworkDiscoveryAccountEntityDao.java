//
// (C) 2013 Soffid
//
//

package com.soffid.iam.pam.model;
/**
 * DAO for Entity NetworkDiscoveryAccountEntity
 * @see com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity
 */
public interface NetworkDiscoveryAccountEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public void toHostPort(com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity source, com.soffid.iam.pam.api.HostPort target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public com.soffid.iam.pam.api.HostPort toHostPort(com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.pam.api.HostPort} list 
	 */
	public java.util.List<com.soffid.iam.pam.api.HostPort> toHostPortList (java.util.Collection<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public void hostPortToEntity (com.soffid.iam.pam.api.HostPort source, com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostPort} object 
	 */
	public com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity hostPortToEntity (com.soffid.iam.pam.api.HostPort instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.pam.api.HostPort} list 
	 */
	public java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity>  hostPortToEntityList (java.util.Collection<com.soffid.iam.pam.api.HostPort> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} .
	 */
	public com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity newNetworkDiscoveryAccountEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 */
	public com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.pam.model.NetworkDiscoveryAccountEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
