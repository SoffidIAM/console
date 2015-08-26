/*
 * SharedFolderMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package com.soffid.iam.sync.intf;

import com.soffid.iam.api.Group;
import com.soffid.iam.api.User;

import es.caib.seycon.ng.exception.InternalErrorException;

public interface SharedFolderMgr extends java.rmi.Remote {
  public void createUserFolder (User user) 
    throws java.rmi.RemoteException, 
            InternalErrorException;

  public void createGroupFolder (Group group) 
		    throws java.rmi.RemoteException, 
		            InternalErrorException;
}
