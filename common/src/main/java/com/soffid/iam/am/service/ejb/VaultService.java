//
// (C) 2013 Soffid
//
//

package com.soffid.iam.am.service.ejb;
/**
 * EJB VaultService
 */
public interface VaultService

 {

	com.soffid.iam.am.api.VaultElement create(
		final com.soffid.iam.am.api.VaultElement folder)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.VaultElement findVaultElement(
		final long id)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.VaultElement update(
		final com.soffid.iam.am.api.VaultElement folder)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.VaultFolder create(
		final com.soffid.iam.am.api.VaultFolder folder)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.VaultFolder findFolder(
		final long id)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.VaultFolder getPersonalFolder()
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.VaultFolder update(
		final com.soffid.iam.am.api.VaultFolder folder)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.iam.am.api.VaultFolderPermissions getFolderPermissions(
		final com.soffid.iam.am.api.VaultFolder folder)
	throws com.soffid.iam.exception.InternalErrorException;

	com.soffid.zkdb.api.PagedResult<com.soffid.iam.am.api.VaultFolder> findFolders(
		final com.soffid.zkdb.api.Query q)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.Account> findAccounts(
		final java.lang.String filter)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.VaultFolder> findFolders(
		final java.lang.String filter)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.VaultElement> findVaultElementByText(
		final java.lang.String filter)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.VaultElement> getChildren(
		final com.soffid.iam.am.api.VaultElement parent)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.VaultFolder> getChildren(
		final com.soffid.iam.am.api.VaultFolder parent)
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.VaultFolder> getPublicRootFolders()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.am.api.VaultFolder> getRootFolders()
	throws com.soffid.iam.exception.InternalErrorException;

	java.util.List<com.soffid.iam.base.api.Account> list(
		final com.soffid.iam.am.api.VaultFolder folder)
	throws com.soffid.iam.exception.InternalErrorException;

	void applyFolderPermissions(
		final com.soffid.iam.am.api.VaultFolderPermissions permissions)
	throws com.soffid.iam.exception.InternalErrorException;

	void remove(
		final com.soffid.iam.am.api.VaultElement folder)
	throws com.soffid.iam.exception.InternalErrorException;

	void remove(
		final com.soffid.iam.am.api.VaultFolder folder)
	throws com.soffid.iam.exception.InternalErrorException;

}
