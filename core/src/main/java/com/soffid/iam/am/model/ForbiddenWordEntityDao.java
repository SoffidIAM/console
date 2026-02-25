//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.model;
/**
 * DAO for Entity ForbiddenWordEntity
 * @see com.soffid.iam.am.model.ForbiddenWordEntity
 */
public interface ForbiddenWordEntityDao

{
	/**
	 * Operation findByName
	 * @param forbiddenWord
	 * @return
	**/
	public com.soffid.iam.am.model.ForbiddenWordEntity findByName(
		java.lang.String forbiddenWord)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String forbiddenWord)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.am.api.ForbiddenWord} object 
	 */
	public void toForbiddenWord(com.soffid.iam.am.model.ForbiddenWordEntity source, com.soffid.iam.am.api.ForbiddenWord target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.ForbiddenWord} object 
	 */
	public com.soffid.iam.am.api.ForbiddenWord toForbiddenWord(com.soffid.iam.am.model.ForbiddenWordEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.am.api.ForbiddenWord} list 
	 */
	public java.util.List<com.soffid.iam.am.api.ForbiddenWord> toForbiddenWordList (java.util.Collection<com.soffid.iam.am.model.ForbiddenWordEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.am.api.ForbiddenWord} object 
	 */
	public void forbiddenWordToEntity (com.soffid.iam.am.api.ForbiddenWord source, com.soffid.iam.am.model.ForbiddenWordEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.ForbiddenWord} object 
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntity forbiddenWordToEntity (com.soffid.iam.am.api.ForbiddenWord instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.am.api.ForbiddenWord} list 
	 */
	public java.util.List<com.soffid.iam.am.model.ForbiddenWordEntity>  forbiddenWordToEntityList (java.util.Collection<com.soffid.iam.am.api.ForbiddenWord> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} .
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntity newForbiddenWordEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.am.model.ForbiddenWordEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.am.model.ForbiddenWordEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.am.model.ForbiddenWordEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 */
	public com.soffid.iam.am.model.ForbiddenWordEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.am.model.ForbiddenWordEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.am.model.ForbiddenWordEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.am.model.ForbiddenWordEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.am.model.ForbiddenWordEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.am.model.ForbiddenWordEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.am.model.ForbiddenWordEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.am.model.ForbiddenWordEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.am.model.ForbiddenWordEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.am.model.ForbiddenWordEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
