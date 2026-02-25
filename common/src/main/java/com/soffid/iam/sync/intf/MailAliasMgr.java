/*
 * MailAliasMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
 
package com.soffid.iam.sync.intf;

import com.soffid.iam.iga.api.MailList;
import com.soffid.iam.base.api.User;
import com.soffid.iam.exception.InternalErrorException;

public interface MailAliasMgr extends java.rmi.Remote {
  public void updateUserAlias(String alias, User user) throws InternalErrorException;
  public void removeUserAlias(String alias) throws InternalErrorException;
  public void updateListAlias(MailList list) throws InternalErrorException;
  public void removeListAlias(String alias, String externalId) throws InternalErrorException;
}
