/*
 * Created on 01-sep-2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package es.caib.seycon.net;

/**
 * @author u07286
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class WebSessionInfo implements java.io.Serializable {
	HostInfo hostInfo;
	boolean atm;
	boolean adsl;
	
	
	public WebSessionInfo(){
		hostInfo = new HostInfo();
		atm = false;
		adsl = false;
	}
	
	/**
	 * @param hostInfo
	 * @param atm
	 * @param adsl
	 */	
	public WebSessionInfo(HostInfo hostInfo, boolean atm, boolean adsl) {
		super();
		this.hostInfo = hostInfo;
		this.atm = atm;
		this.adsl = adsl;
	}

	/**
	 * @return Returns the adsl.
	 */
	public boolean isAdsl() {
		return adsl;
	}
	/**
	 * @return Returns the atm.
	 */
	public boolean isAtm() {
		return atm;
	}
	/**
	 * @return Returns the hostInfo.
	 */
	public HostInfo getHostInfo() {
		return hostInfo;
	}
}
