//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity TranslatedLabelEntity
 * @see com.soffid.iam.iga.model.TranslatedLabelEntity
 */
public interface TranslatedLabelEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.base.api.TranslatedLabel} object 
	 */
	public void toTranslatedLabel(com.soffid.iam.iga.model.TranslatedLabelEntity source, com.soffid.iam.base.api.TranslatedLabel target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.TranslatedLabel} object 
	 */
	public com.soffid.iam.base.api.TranslatedLabel toTranslatedLabel(com.soffid.iam.iga.model.TranslatedLabelEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.base.api.TranslatedLabel} list 
	 */
	public java.util.List<com.soffid.iam.base.api.TranslatedLabel> toTranslatedLabelList (java.util.Collection<com.soffid.iam.iga.model.TranslatedLabelEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.base.api.TranslatedLabel} object 
	 */
	public void translatedLabelToEntity (com.soffid.iam.base.api.TranslatedLabel source, com.soffid.iam.iga.model.TranslatedLabelEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.TranslatedLabel} object 
	 */
	public com.soffid.iam.iga.model.TranslatedLabelEntity translatedLabelToEntity (com.soffid.iam.base.api.TranslatedLabel instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.base.api.TranslatedLabel} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.TranslatedLabelEntity>  translatedLabelToEntityList (java.util.Collection<com.soffid.iam.base.api.TranslatedLabel> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} .
	 */
	public com.soffid.iam.iga.model.TranslatedLabelEntity newTranslatedLabelEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.TranslatedLabelEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.TranslatedLabelEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.TranslatedLabelEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.TranslatedLabelEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.TranslatedLabelEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.TranslatedLabelEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.TranslatedLabelEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.TranslatedLabelEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.TranslatedLabelEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.TranslatedLabelEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.TranslatedLabelEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
