//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity PrinterGroupEntity
 * @see com.soffid.iam.iga.model.PrinterGroupEntity
 */
public interface PrinterGroupEntityDao

{
	/**
	 * Operation findByGroupAndPrinter
	 * @param group
	 * @param printer
	 * @return
	**/
	public com.soffid.iam.iga.model.PrinterGroupEntity findByGroupAndPrinter(
		java.lang.String group, 
		java.lang.String printer)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntity findByGroupAndPrinter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String group, java.lang.String printer)
	;
	/**
	 * Operation findByGroup
	 * @param group
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> findByGroup(
		java.lang.String group)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> findByGroup(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String group)
	;
	/**
	 * Operation findByPrinter
	 * @param printer
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> findByPrinter(
		java.lang.String printer)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> findByPrinter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String printer)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.PrinterGroup} object 
	 */
	public void toPrinterGroup(com.soffid.iam.iga.model.PrinterGroupEntity source, com.soffid.iam.iga.api.PrinterGroup target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.PrinterGroup} object 
	 */
	public com.soffid.iam.iga.api.PrinterGroup toPrinterGroup(com.soffid.iam.iga.model.PrinterGroupEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.PrinterGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.PrinterGroup> toPrinterGroupList (java.util.Collection<com.soffid.iam.iga.model.PrinterGroupEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.PrinterGroup} object 
	 */
	public void printerGroupToEntity (com.soffid.iam.iga.api.PrinterGroup source, com.soffid.iam.iga.model.PrinterGroupEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.PrinterGroup} object 
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntity printerGroupToEntity (com.soffid.iam.iga.api.PrinterGroup instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.PrinterGroup} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity>  printerGroupToEntityList (java.util.Collection<com.soffid.iam.iga.api.PrinterGroup> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} .
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntity newPrinterGroupEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.PrinterGroupEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.PrinterGroupEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.PrinterGroupEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.PrinterGroupEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.PrinterGroupEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterGroupEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.PrinterGroupEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterGroupEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterGroupEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.PrinterGroupEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterGroupEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
