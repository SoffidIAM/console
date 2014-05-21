package es.caib.sso.client;

import java.applet.Applet;
import java.net.Authenticator;
import java.net.URL;

import netscape.javascript.JSObject;

import java.net.InetAddress;
import java.security.AccessController;
import java.security.PrivilegedAction;

public class FormAuthenticator {

	public FormAuthenticator() {
		AccessController.doPrivileged(
				new PrivilegedAction () {
					public Object run() {
						try {
							System.loadLibrary("javasso"); //$NON-NLS-1$
						} catch (Exception e) {
							e.printStackTrace();
						}
						return null;
					}
				}
			);
	}
	
	public boolean doLogin (Applet applet) {
		
		try {
			WebAuthenticator auth = new WebAuthenticator ();

			URL url = applet.getCodeBase();
			String host = url.getHost();
			InetAddress addr = InetAddress.getByName(host);
			String port = Integer.toString(url.getPort());
			if ("-1".equals(port)) //$NON-NLS-1$
			{
				if ("http".equals(url.getProtocol())) //$NON-NLS-1$
					port = "80"; //$NON-NLS-1$
				if ("https".equals(url.getProtocol())) //$NON-NLS-1$
					port = "443"; //$NON-NLS-1$
			}
			String ip = addr.getHostAddress();
			String info [] = auth.getSchemeUserAndPassword(ip, port); 
				
			if ( info != null &&
				 info.length == 3 &&
				 ( "http".equalsIgnoreCase(info[0]) || //$NON-NLS-1$
				   "https".equalsIgnoreCase(info[0]) ))  //$NON-NLS-1$
			{
				JSObject win = JSObject.getWindow(applet);
				JSObject doc = (JSObject) win.getMember("document"); //$NON-NLS-1$
				JSObject form = (JSObject) doc.getMember("formUC"); //$NON-NLS-1$
				JSObject user = (JSObject) form.getMember("j_username"); //$NON-NLS-1$
				user.setMember("value", info[1]); //$NON-NLS-1$
				JSObject pass = (JSObject) form.getMember("j_password"); //$NON-NLS-1$
				pass.setMember("value", info[2]); //$NON-NLS-1$
				form.call("submit", null); //$NON-NLS-1$
				return true;
			}
		} catch (Throwable t) {
			t.printStackTrace();
		}
		return false;
	}
	


}
