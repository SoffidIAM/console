//
// (C) 2013 Soffid
//
//

package com.soffid.iam.doc.model;
/**
 * DAO for Entity FileSystem
 * @see com.soffid.iam.doc.model.FileSystem
 */
public interface FileSystemDao

{
	/**
	 * Operation nextNumberFor
	 * @param application
	 * @param year
	 * @return
	**/
	public java.lang.Long nextNumberFor(
		java.lang.String application, 
		int year)  ;

	/**
	 * CriteriaSearchConfiguration finder
	 */
	public java.lang.Long nextNumberFor(final com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria, java.lang.String application, int year)
	;
	/**
	 * Creates an instance of {@link com.soffid.iam.doc.model.FileSystem} .
	 */
	public com.soffid.iam.doc.model.FileSystem newFileSystem();

	/**
	 * Adds an instance of {@link com.soffid.iam.doc.model.FileSystem} to the persistent store.
	 */
	public void create (com.soffid.iam.doc.model.FileSystem entity);

	/**
	 * Updates an instance of {@link com.soffid.iam.doc.model.FileSystem} at the persistent store.
	 */
	public void update (com.soffid.iam.doc.model.FileSystem entity);

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 */
	public void remove (com.soffid.iam.doc.model.FileSystem entity);

	/**
	 * Loads an instance of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 */
	public com.soffid.iam.doc.model.FileSystem load(java.lang.Long id);

	/**
	 * Loads all instances of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 */
	public java.util.List<com.soffid.iam.doc.model.FileSystem> loadAll();

	/**
	 * Creates a collection of {@link com.soffid.iam.doc.model.FileSystem} and adds it to the persistent store.
	 */
	public void create (java.util.Collection<? extends com.soffid.iam.doc.model.FileSystem> entities);

	/**
	 * Updates a collection of {@link com.soffid.iam.doc.model.FileSystem} in the persistent store.
	 */
	public void update (java.util.Collection<? extends com.soffid.iam.doc.model.FileSystem> entities);

	/**
	 * Removes a collection of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 */
	public void remove (java.util.Collection<? extends com.soffid.iam.doc.model.FileSystem> entities);

	/**
	 * Removes an instance of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 */
	public void remove (java.lang.Long id);

	/**
	 * Query of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 */
	public java.util.List<com.soffid.iam.doc.model.FileSystem> query (String query, com.soffid.iam.model.Parameter[] parameters);

	/**
	 * Query of {@link com.soffid.iam.doc.model.FileSystem} from the persistent store.
	 * parameter query HQL Query String
	 * parameter parameters HQL Parameters
	 * parameter maxResults max number of rows to return
	 */
	public java.util.List<com.soffid.iam.doc.model.FileSystem> query (String query, com.soffid.iam.model.Parameter[] parameters, com.soffid.iam.model.criteria.CriteriaSearchConfiguration criteria);

}
