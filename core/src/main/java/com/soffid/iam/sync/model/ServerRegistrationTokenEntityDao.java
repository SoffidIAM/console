//
// (C) 2013 Soffid
//
//

package com.soffid.iam.sync.model;
/**
 * DAO for Entity ServerRegistrationTokenEntity
 * @see com.soffid.iam.sync.model.ServerRegistrationTokenEntity
 */
public interface ServerRegistrationTokenEntityDao

{
	/**
	 * Operation findByToken
	 * @param token
	 * @return
	**/
	public com.soffid.iam.sync.model.ServerRegistrationTokenEntity findByToken(
		java.lang.String token)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.sync.model.ServerRegistrationTokenEntity findByToken(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String token)
	;
	/**
	 * Operation removeExpiredTokens
	**/
	public void removeExpiredTokens() throws com.soffid.iam.exception.InternalErrorException ;

	/**
	 *  Copy data to {@link com.soffid.iam.sync.api.ServerRegistrationToken} object 
	 */
	public void toServerRegistrationToken(com.soffid.iam.sync.model.ServerRegistrationTokenEntity source, com.soffid.iam.sync.api.ServerRegistrationToken target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ServerRegistrationToken} object 
	 */
	public com.soffid.iam.sync.api.ServerRegistrationToken toServerRegistrationToken(com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.sync.api.ServerRegistrationToken} list 
	 */
	public java.util.List<com.soffid.iam.sync.api.ServerRegistrationToken> toServerRegistrationTokenList (java.util.Collection<com.soffid.iam.sync.model.ServerRegistrationTokenEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.sync.api.ServerRegistrationToken} object 
	 */
	public void serverRegistrationTokenToEntity (com.soffid.iam.sync.api.ServerRegistrationToken source, com.soffid.iam.sync.model.ServerRegistrationTokenEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ServerRegistrationToken} object 
	 */
	public com.soffid.iam.sync.model.ServerRegistrationTokenEntity serverRegistrationTokenToEntity (com.soffid.iam.sync.api.ServerRegistrationToken instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.sync.api.ServerRegistrationToken} list 
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerRegistrationTokenEntity>  serverRegistrationTokenToEntityList (java.util.Collection<com.soffid.iam.sync.api.ServerRegistrationToken> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} .
	 */
	public com.soffid.iam.sync.model.ServerRegistrationTokenEntity newServerRegistrationTokenEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.sync.model.ServerRegistrationTokenEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 */
	public com.soffid.iam.sync.model.ServerRegistrationTokenEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerRegistrationTokenEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.sync.model.ServerRegistrationTokenEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.sync.model.ServerRegistrationTokenEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.sync.model.ServerRegistrationTokenEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerRegistrationTokenEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.sync.model.ServerRegistrationTokenEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.sync.model.ServerRegistrationTokenEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
