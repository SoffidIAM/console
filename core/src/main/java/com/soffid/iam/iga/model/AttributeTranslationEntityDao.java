//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity AttributeTranslationEntity
 * @see com.soffid.iam.iga.model.AttributeTranslationEntity
 */
public interface AttributeTranslationEntityDao

{
	/**
	 * Operation findByColumn1
	 * @param domain
	 * @param column1
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByColumn1(
		java.lang.String domain, 
		java.lang.String column1)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByColumn1(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domain, java.lang.String column1)
	;
	/**
	 * Operation findByColumn2
	 * @param domain
	 * @param column2
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByColumn2(
		java.lang.String domain, 
		java.lang.String column2)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByColumn2(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domain, java.lang.String column2)
	;
	/**
	 * Operation findByExample
	 * @param domain
	 * @param column1
	 * @param column2
	 * @return
	**/
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByExample(
		java.lang.String domain, 
		java.lang.String column1, 
		java.lang.String column2)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> findByExample(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String domain, java.lang.String column1, java.lang.String column2)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.AttributeTranslation} object 
	 */
	public void toAttributeTranslation(com.soffid.iam.iga.model.AttributeTranslationEntity source, com.soffid.iam.iga.api.AttributeTranslation target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AttributeTranslation} object 
	 */
	public com.soffid.iam.iga.api.AttributeTranslation toAttributeTranslation(com.soffid.iam.iga.model.AttributeTranslationEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.AttributeTranslation} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.AttributeTranslation> toAttributeTranslationList (java.util.Collection<com.soffid.iam.iga.model.AttributeTranslationEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.AttributeTranslation} object 
	 */
	public void attributeTranslationToEntity (com.soffid.iam.iga.api.AttributeTranslation source, com.soffid.iam.iga.model.AttributeTranslationEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.AttributeTranslation} object 
	 */
	public com.soffid.iam.iga.model.AttributeTranslationEntity attributeTranslationToEntity (com.soffid.iam.iga.api.AttributeTranslation instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.AttributeTranslation} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeTranslationEntity>  attributeTranslationToEntityList (java.util.Collection<com.soffid.iam.iga.api.AttributeTranslation> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} .
	 */
	public com.soffid.iam.iga.model.AttributeTranslationEntity newAttributeTranslationEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.AttributeTranslationEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.AttributeTranslationEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.AttributeTranslationEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.AttributeTranslationEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeTranslationEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeTranslationEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeTranslationEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.AttributeTranslationEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeTranslationEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.AttributeTranslationEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.AttributeTranslationEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
