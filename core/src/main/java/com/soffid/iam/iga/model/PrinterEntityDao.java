//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity PrinterEntity
 * @see com.soffid.iam.iga.model.PrinterEntity
 */
public interface PrinterEntityDao

{
	/**
	 * Operation findByName
	 * @param name
	 * @return
	**/
	public com.soffid.iam.iga.model.PrinterEntity findByName(
		java.lang.String name)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.PrinterEntity findByName(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String name)
	;
	/**
	 * Operation findPrintersByCriteria
	 * @param model
	 * @param name
	 * @param local
	 * @param host
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity> findPrintersByCriteria(
		java.lang.String model, 
		java.lang.String name, 
		java.lang.String local, 
		java.lang.String host)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity> findPrintersByCriteria(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String model, java.lang.String name, java.lang.String local, java.lang.String host)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.Printer} object 
	 */
	public void toPrinter(com.soffid.iam.iga.model.PrinterEntity source, com.soffid.iam.iga.api.Printer target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Printer} object 
	 */
	public com.soffid.iam.iga.api.Printer toPrinter(com.soffid.iam.iga.model.PrinterEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.Printer} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.Printer> toPrinterList (java.util.Collection<com.soffid.iam.iga.model.PrinterEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.Printer} object 
	 */
	public void printerToEntity (com.soffid.iam.iga.api.Printer source, com.soffid.iam.iga.model.PrinterEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Printer} object 
	 */
	public com.soffid.iam.iga.model.PrinterEntity printerToEntity (com.soffid.iam.iga.api.Printer instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.Printer} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity>  printerToEntityList (java.util.Collection<com.soffid.iam.iga.api.Printer> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.PrinterEntity} .
	 */
	public com.soffid.iam.iga.model.PrinterEntity newPrinterEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.PrinterEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.PrinterEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.PrinterEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.PrinterEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.PrinterEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.PrinterEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.PrinterEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.PrinterEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.PrinterEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.PrinterEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.PrinterEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
