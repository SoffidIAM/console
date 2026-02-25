//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity UserProcessEntity
 * @see com.soffid.iam.iga.model.UserProcessEntity
 */
public interface UserProcessEntityDao

{
	/**
	 * Operation findByUserName
	 * @param userName
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByUserName(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByUserName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 * Operation findByProcessId
	 * @param processId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByProcessId(
		java.lang.Long processId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByProcessId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.Long processId)
	;
	/**
	 * Operation findByUserNationalId
	 * @param nationalId
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByUserNationalId(
		java.lang.String nationalId)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> findByUserNationalId(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String nationalId)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.BpmUserProcess} object 
	 */
	public void toBpmUserProcess(com.soffid.iam.iga.model.UserProcessEntity source, com.soffid.iam.iga.api.BpmUserProcess target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.BpmUserProcess} object 
	 */
	public com.soffid.iam.iga.api.BpmUserProcess toBpmUserProcess(com.soffid.iam.iga.model.UserProcessEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.BpmUserProcess} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.BpmUserProcess> toBpmUserProcessList (java.util.Collection<com.soffid.iam.iga.model.UserProcessEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.BpmUserProcess} object 
	 */
	public void bpmUserProcessToEntity (com.soffid.iam.iga.api.BpmUserProcess source, com.soffid.iam.iga.model.UserProcessEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.BpmUserProcess} object 
	 */
	public com.soffid.iam.iga.model.UserProcessEntity bpmUserProcessToEntity (com.soffid.iam.iga.api.BpmUserProcess instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.BpmUserProcess} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserProcessEntity>  bpmUserProcessToEntityList (java.util.Collection<com.soffid.iam.iga.api.BpmUserProcess> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} .
	 */
	public com.soffid.iam.iga.model.UserProcessEntity newUserProcessEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserProcessEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserProcessEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserProcessEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserProcessEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserProcessEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserProcessEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserProcessEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserProcessEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserProcessEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserProcessEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserProcessEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserProcessEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserProcessEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
