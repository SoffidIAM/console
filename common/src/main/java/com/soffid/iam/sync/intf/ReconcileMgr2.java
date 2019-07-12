/**
 * 
 */
package com.soffid.iam.sync.intf;

import java.rmi.RemoteException;
import java.util.List;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleGrant;

import es.caib.seycon.ng.exception.InternalErrorException;

/**
 * Interface to reconcile information.
 * 
 * @author (C) 2013 Soffid
 * 
 */
public interface ReconcileMgr2 extends java.rmi.Remote
{

	/**
	 * Enumerate user accounts (only account name)
	 * 
	 * <p>
	 * Implements the functionality to obtain a list of user accounts.
	 * 
	 * @return List of user accounts.
	 * 
	 * @throws RemoteException
	 *             Manage communication errors.
	 * @throws InternalErrorException
	 *             Manage errors produced by another causes.
	 */
	public List<String> getAccountsList() throws RemoteException,
			InternalErrorException;

	/**
	 * Get full user information
	 * 
	 * <p>
	 * Implements the functionality to obtain the full user information from a
	 * user account.
	 * 
	 * @param userAccount
	 *            User account name about obtain information.
	 * @return Full user information.
	 * 
	 * @throws RemoteException
	 *             Manage communication errors.
	 * @throws InternalErrorException
	 *             Manage errors produced by another causes.
	 */
	public com.soffid.iam.api.Account getAccountInfo(String userAccount) throws RemoteException,
			InternalErrorException;

	/**
	 * Enumerate roles (only role name)
	 * 
	 * <p>
	 * Implements the functionality to obtain a list of roles names.
	 * 
	 * @return List of user roles names.
	 * 
	 * @throws RemoteException
	 *             Manage communication errors.
	 * @throws InternalErrorException
	 *             Manage errors produced by another causes.
	 */
	public List<String> getRolesList() throws RemoteException,
			InternalErrorException;

	/**
	 * Get full role information from role name
	 * 
	 * <p>
	 * Implements the functionality to obtain the full role information from a
	 * role name.
	 * 
	 * @param roleName
	 *            Role name about obtain information.
	 * @return Full role information.
	 * 
	 * @throws RemoteException
	 *             Manage communication errors.
	 * @throws InternalErrorException
	 *             Manage errors produced by another causes.
	 */
	public Role getRoleFullInfo(String roleName) throws RemoteException,
			InternalErrorException;


	/**
	 * Get the list of roles assigned to account.
	 * 
	 * <p>
	 * Implements the functionality to obtain the full list of roles assigned to
	 * reconcile account.
	 * 
	 * @param userAccount
	 *            Account to get roles.
	 * @return List of roles assigned to account.
	 * 
	 * @throws RemoteException
	 *             Manage communication errors.
	 * @throws InternalErrorException
	 *             Manage errors produced by another causes.
	 */
	public List<RoleGrant> getAccountGrants(String userAccount)
			throws RemoteException, InternalErrorException;


	public List<String[]> getAccountChangesToApply (Account account) throws RemoteException, InternalErrorException;

	public List<String[]> getRoleChangesToApply (Role role) throws RemoteException, InternalErrorException;
}
