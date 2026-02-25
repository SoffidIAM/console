/*
 * UserMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
package com.soffid.iam.sync.intf;

import java.rmi.RemoteException;
import java.util.List;

import com.soffid.iam.am.api.Password;
import com.soffid.iam.base.api.Account;
import com.soffid.iam.pam.api.HostService;
import com.soffid.iam.exception.InternalErrorException;

/** 
 * Remote interface to change service passwords
 * 
 */
 
public interface ServiceMgr extends java.rmi.Remote {
	/**
	 * Change the service password
	 * 
	 * @param service
	 * @param account
	 * @param password
	 * @throws java.rmi.RemoteException
	 * @throws InternalErrorException
	 */
  public void setServicePassword (String service, Account account, Password password) 
    throws java.rmi.RemoteException, 
            InternalErrorException;
  
  public List<HostService> getHostServices() throws RemoteException, InternalErrorException;

}

