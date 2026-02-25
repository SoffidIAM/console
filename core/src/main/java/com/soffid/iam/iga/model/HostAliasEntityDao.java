//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity HostAliasEntity
 * @see com.soffid.iam.iga.model.HostAliasEntity
 */
public interface HostAliasEntityDao

{
	/**
	 * Operation findAliasByHostName
	 * @param nomMaquina
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findAliasByHostName(
		java.lang.String nomMaquina)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findAliasByHostName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nomMaquina)
	;
	/**
	 * Operation findAliasByHostNameAndAlias
	 * @param nomMaquina
	 * @param alias
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findAliasByHostNameAndAlias(
		java.lang.String nomMaquina, 
		java.lang.String alias)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findAliasByHostNameAndAlias(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nomMaquina, java.lang.String alias)
	;
	/**
	 * Operation findHostByAlias
	 * @param alias
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findHostByAlias(
		java.lang.String alias)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> findHostByAlias(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String alias)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.HostAlias} object 
	 */
	public void toHostAlias(com.soffid.iam.iga.model.HostAliasEntity source, com.soffid.iam.am.api.HostAlias target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.HostAlias} object 
	 */
	public com.soffid.iam.am.api.HostAlias toHostAlias(com.soffid.iam.iga.model.HostAliasEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.HostAlias} list 
	 */
	public java.util.List<com.soffid.iam.am.api.HostAlias> toHostAliasList (java.util.Collection<com.soffid.iam.iga.model.HostAliasEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.HostAlias} object 
	 */
	public void hostAliasToEntity (com.soffid.iam.am.api.HostAlias source, com.soffid.iam.iga.model.HostAliasEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.HostAlias} object 
	 */
	public com.soffid.iam.iga.model.HostAliasEntity hostAliasToEntity (com.soffid.iam.am.api.HostAlias instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.HostAlias} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity>  hostAliasToEntityList (java.util.Collection<com.soffid.iam.am.api.HostAlias> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} .
	 */
	public com.soffid.iam.iga.model.HostAliasEntity newHostAliasEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.HostAliasEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.HostAliasEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.HostAliasEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.HostAliasEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.HostAliasEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.HostAliasEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.HostAliasEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.HostAliasEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.HostAliasEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.HostAliasEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.HostAliasEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
