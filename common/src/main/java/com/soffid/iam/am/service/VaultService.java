//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service;
/**
 * Service VaultService
 */
public interface VaultService {
	public final static String SERVICE_NAME = "com.soffid.iam.am.service.VaultService";

	/**
	 * Operation create

	 * @param folder 
	 * @return 
	 */
	com.soffid.iam.am.api.VaultElement create(
		final com.soffid.iam.am.api.VaultElement folder)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findVaultElement

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.am.api.VaultElement findVaultElement(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param folder 
	 * @return 
	 */
	com.soffid.iam.am.api.VaultElement update(
		final com.soffid.iam.am.api.VaultElement folder)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation create

	 * @param folder 
	 * @return 
	 */
	com.soffid.iam.am.api.VaultFolder create(
		final com.soffid.iam.am.api.VaultFolder folder)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findFolder

	 * @param id 
	 * @return 
	 */
	com.soffid.iam.am.api.VaultFolder findFolder(
		final long id)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPersonalFolder

	 * @return 
	 */
	com.soffid.iam.am.api.VaultFolder getPersonalFolder()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation update

	 * @param folder 
	 * @return 
	 */
	com.soffid.iam.am.api.VaultFolder update(
		final com.soffid.iam.am.api.VaultFolder folder)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getFolderPermissions

	 * @param folder 
	 * @return 
	 */
	com.soffid.iam.am.api.VaultFolderPermissions getFolderPermissions(
		final com.soffid.iam.am.api.VaultFolder folder)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation addToFolder

	 * @param account 
	 * @return 
	 */
	com.soffid.iam.base.api.Account addToFolder(
		final com.soffid.iam.base.api.Account account)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findFolders

	 * @param q 
	 * @return 
	 */
	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.VaultFolder> findFolders(
		final com.soffid.zkdb.api.Query q)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findAccounts

	 * @param filter 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.Account> findAccounts(
		final java.lang.String filter)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findFolders

	 * @param filter 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.VaultFolder> findFolders(
		final java.lang.String filter)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation findVaultElementByText

	 * @param filter 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.VaultElement> findVaultElementByText(
		final java.lang.String filter)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getChildren

	 * @param parent 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.VaultElement> getChildren(
		final com.soffid.iam.am.api.VaultElement parent)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getChildren

	 * @param parent 
	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.VaultFolder> getChildren(
		final com.soffid.iam.am.api.VaultFolder parent)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getPublicRootFolders

	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.VaultFolder> getPublicRootFolders()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation getRootFolders

	 * @return 
	 */
	java.util.List<com.soffid.iam.am.api.VaultFolder> getRootFolders()
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation list

	 * @param folder 
	 * @return 
	 */
	java.util.List<com.soffid.iam.base.api.Account> list(
		final com.soffid.iam.am.api.VaultFolder folder)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation applyFolderPermissions

	 * @param permissions 
	 */
	void applyFolderPermissions(
		final com.soffid.iam.am.api.VaultFolderPermissions permissions)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation remove

	 * @param folder 
	 */
	void remove(
		final com.soffid.iam.am.api.VaultElement folder)
			throws com.soffid.iam.exception.InternalErrorException;

	/**
	 * Operation remove

	 * @param folder 
	 */
	void remove(
		final com.soffid.iam.am.api.VaultFolder folder)
			throws com.soffid.iam.exception.InternalErrorException;

}
