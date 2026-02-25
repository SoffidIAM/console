//
// (C) 2013 Soffid
//
//

package com.soffid.iam.rc.model;
/**
 * DAO for Entity SoDRuleMatrixEntity
 * @see com.soffid.iam.rc.model.SoDRuleMatrixEntity
 */
public interface SoDRuleMatrixEntityDao

{
	/**
	 *  Copy data to {@link com.soffid.iam.rc.api.SoDRuleMatrix} object 
	 */
	public void toSoDRuleMatrix(com.soffid.iam.rc.model.SoDRuleMatrixEntity source, com.soffid.iam.rc.api.SoDRuleMatrix target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRuleMatrix} object 
	 */
	public com.soffid.iam.rc.api.SoDRuleMatrix toSoDRuleMatrix(com.soffid.iam.rc.model.SoDRuleMatrixEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.rc.api.SoDRuleMatrix} list 
	 */
	public java.util.List<com.soffid.iam.rc.api.SoDRuleMatrix> toSoDRuleMatrixList (java.util.Collection<com.soffid.iam.rc.model.SoDRuleMatrixEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.rc.api.SoDRuleMatrix} object 
	 */
	public void soDRuleMatrixToEntity (com.soffid.iam.rc.api.SoDRuleMatrix source, com.soffid.iam.rc.model.SoDRuleMatrixEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRuleMatrix} object 
	 */
	public com.soffid.iam.rc.model.SoDRuleMatrixEntity soDRuleMatrixToEntity (com.soffid.iam.rc.api.SoDRuleMatrix instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.rc.api.SoDRuleMatrix} list 
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity>  soDRuleMatrixToEntityList (java.util.Collection<com.soffid.iam.rc.api.SoDRuleMatrix> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} .
	 */
	public com.soffid.iam.rc.model.SoDRuleMatrixEntity newSoDRuleMatrixEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.rc.model.SoDRuleMatrixEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.rc.model.SoDRuleMatrixEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.rc.model.SoDRuleMatrixEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 */
	public com.soffid.iam.rc.model.SoDRuleMatrixEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleMatrixEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleMatrixEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.rc.model.SoDRuleMatrixEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.rc.model.SoDRuleMatrixEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.rc.model.SoDRuleMatrixEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
