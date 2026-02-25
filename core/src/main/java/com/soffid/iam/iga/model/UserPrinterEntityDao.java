//
// (C) 2013 Soffid
//
//

package com.soffid.iam.iga.model;
/**
 * DAO for Entity UserPrinterEntity
 * @see com.soffid.iam.iga.model.UserPrinterEntity
 */
public interface UserPrinterEntityDao

{
	/**
	 * Operation findUserByUserAndPrinter
	 * @param userName
	 * @param printer
	 * @return
	**/
	public com.soffid.iam.iga.model.UserPrinterEntity findUserByUserAndPrinter(
		java.lang.String userName, 
		java.lang.String printer)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public com.soffid.iam.iga.model.UserPrinterEntity findUserByUserAndPrinter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName, java.lang.String printer)
	;
	/**
	 * Operation findByPrinter
	 * @param printer
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> findByPrinter(
		java.lang.String printer)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> findByPrinter(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String printer)
	;
	/**
	 * Operation findByUser
	 * @param userName
	 * @return
	**/
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> findByUser(
		java.lang.String userName)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> findByUser(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String userName)
	;
	/**
	 *  Copy data to {@link com.soffid.iam.iga.api.PrinterUser} object 
	 */
	public void toPrinterUser(com.soffid.iam.iga.model.UserPrinterEntity source, com.soffid.iam.iga.api.PrinterUser target) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.PrinterUser} object 
	 */
	public com.soffid.iam.iga.api.PrinterUser toPrinterUser(com.soffid.iam.iga.model.UserPrinterEntity entity) ;

	/**
	 *  Transforms to {@link com.soffid.iam.iga.api.PrinterUser} list 
	 */
	public java.util.List<com.soffid.iam.iga.api.PrinterUser> toPrinterUserList (java.util.Collection<com.soffid.iam.iga.model.UserPrinterEntity> entities) ;

	/**
	 *  Copy data from {@link com.soffid.iam.iga.api.PrinterUser} object 
	 */
	public void printerUserToEntity (com.soffid.iam.iga.api.PrinterUser source, com.soffid.iam.iga.model.UserPrinterEntity target, boolean copyIfNull) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.PrinterUser} object 
	 */
	public com.soffid.iam.iga.model.UserPrinterEntity printerUserToEntity (com.soffid.iam.iga.api.PrinterUser instance) ;

	/**
	 *  Transforms from {@link com.soffid.iam.iga.api.PrinterUser} list 
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity>  printerUserToEntityList (java.util.Collection<com.soffid.iam.iga.api.PrinterUser> instances) ;

	/**
	 * Creates an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} .
	 */
	public com.soffid.iam.iga.model.UserPrinterEntity newUserPrinterEntity();

	/**
	 * Adds an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} to the persistent store.
	 */
	public void create (com.soffid.iam.iga.model.UserPrinterEntity entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} at the persistent store.
	 */
	public void update (com.soffid.iam.iga.model.UserPrinterEntity entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 */
	public void remove (com.soffid.iam.iga.model.UserPrinterEntity entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 */
	public com.soffid.iam.iga.model.UserPrinterEntity load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.iga.model.UserPrinterEntity} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.iga.model.UserPrinterEntity> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.iga.model.UserPrinterEntity} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.iga.model.UserPrinterEntity> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.iga.model.UserPrinterEntity> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.iga.model.UserPrinterEntity} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.iga.model.UserPrinterEntity> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
