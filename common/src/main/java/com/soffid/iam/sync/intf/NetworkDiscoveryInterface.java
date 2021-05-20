/*
 * UserMgr.java
 *
 * Created on May 8, 2000, 10:44 AM
 */
package com.soffid.iam.sync.intf;

import java.rmi.RemoteException;
import java.util.List;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Network;
import com.soffid.iam.api.Password;
import com.soffid.iam.sync.intf.discovery.DiscoveryEvent;

 
public interface NetworkDiscoveryInterface extends java.rmi.Remote {
  public void startDiscovery(Network network, List<Account> accounts);
  
  public List<DiscoveryEvent> getDiscoveryEvents();
  
  public boolean isFinished();
}

