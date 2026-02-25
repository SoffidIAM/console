//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity ChallengeEntity
 * @see com.soffid.iam.am.model.ChallengeEntity
 */
public interface ChallengeEntityDao

{
	/**
	 * Operation findByChallengeId
	 * @param challengeId
	 * @return
	**/
	public com.soffid.iam.am.model.ChallengeEntity findByChallengeId(
		java.lang.String challengeId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.ChallengeEntity findByChallengeId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String challengeId)
	;
	/**
	 * Operation findExpiredChallenges
	 * @param timeStamp
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.am.model.ChallengeEntity> findExpiredChallenges(
		java.util.Date timeStamp)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.am.model.ChallengeEntity> findExpiredChallenges(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.util.Date timeStamp)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.Challenge} object 
	 */
	public void toChallenge(com.soffid.iam.am.model.ChallengeEntity source, com.soffid.iam.am.api.Challenge target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Challenge} object 
	 */
	public com.soffid.iam.am.api.Challenge toChallenge(com.soffid.iam.am.model.ChallengeEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.Challenge} list 
	 */
	public java.util.List<com.soffid.iam.am.api.Challenge> toChallengeList (java.util.Collection<com.soffid.iam.am.model.ChallengeEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.Challenge} object 
	 */
	public void challengeToEntity (com.soffid.iam.am.api.Challenge source, com.soffid.iam.am.model.ChallengeEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Challenge} object 
	 */
	public com.soffid.iam.am.model.ChallengeEntity challengeToEntity (com.soffid.iam.am.api.Challenge instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.Challenge} list 
	 */
	public java.util.List<com.soffid.iam.am.model.ChallengeEntity>  challengeToEntityList (java.util.Collection<com.soffid.iam.am.api.Challenge> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.ChallengeEntity} .
	 */
	public com.soffid.iam.am.model.ChallengeEntity newChallengeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.ChallengeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.ChallengeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.ChallengeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.ChallengeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.ChallengeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.ChallengeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.ChallengeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.ChallengeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.ChallengeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.ChallengeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.ChallengeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.ChallengeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.ChallengeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.ChallengeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.ChallengeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
