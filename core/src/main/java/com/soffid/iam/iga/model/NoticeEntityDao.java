//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity NoticeEntity
 * @see com.soffid.iam.iga.model.NoticeEntity
 */
public interface NoticeEntityDao

{
	/**
	 * Operation findAll
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> findAll()  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> findAll(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria)
	;
	/**
	 * Operation findByApplicationCode
	 * @param informationSystem
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> findByApplicationCode(
		java.lang.String informationSystem)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> findByApplicationCode(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String informationSystem)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Notice} object 
	 */
	public void toNotice(com.soffid.iam.iga.model.NoticeEntity source, com.soffid.iam.iga.api.Notice target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Notice} object 
	 */
	public com.soffid.iam.iga.api.Notice toNotice(com.soffid.iam.iga.model.NoticeEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Notice} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Notice> toNoticeList (java.util.Collection<com.soffid.iam.iga.model.NoticeEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Notice} object 
	 */
	public void noticeToEntity (com.soffid.iam.iga.api.Notice source, com.soffid.iam.iga.model.NoticeEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Notice} object 
	 */
	public com.soffid.iam.iga.model.NoticeEntity noticeToEntity (com.soffid.iam.iga.api.Notice instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Notice} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity>  noticeToEntityList (java.util.Collection<com.soffid.iam.iga.api.Notice> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.NoticeEntity} .
	 */
	public com.soffid.iam.iga.model.NoticeEntity newNoticeEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.NoticeEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.NoticeEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.NoticeEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.NoticeEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.NoticeEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.NoticeEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.NoticeEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.NoticeEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.NoticeEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.NoticeEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.NoticeEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.NoticeEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.NoticeEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
