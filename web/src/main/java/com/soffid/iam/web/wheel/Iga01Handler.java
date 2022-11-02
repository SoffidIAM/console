package com.soffid.iam.web.wheel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.CreateException;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

import org.zkoss.util.media.AMedia;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.ServerRegistrationToken;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.zkiblaf.ImageClic;


public class Iga01Handler extends Window {
	private Radiogroup radio;
	private Wizard wizard;
	private Button step2Button;
	private String type;
	private Button step3Button;
	private Label explanation2;
	private Div explanation2b;
	private Timer timer;
	private Image step3Wait;
	private String newToken;

	public void back(Event ev) {
		if (wizard.getSelected() == 0)
			setVisible(false);
		else
			wizard.previous();
		timer.stop();
	}
	
	public void onTimer(Event ev) throws InternalErrorException, NamingException, CreateException {
		if ( ! EJBLocator.getDispatcherService().isRegistrationTokenAlive(newToken)) {
			step3Button.setVisible(true);
			step3Wait.setVisible(false);
		}
	}
	
	public void step2(Event ev) throws MalformedURLException, InternalErrorException, IOException {
		type = radio.getSelectedItem().getValue();
		wizard.next();
		explanation2.setValue( Labels.getLabel( "psh".equals(type) ? "wheel.syncserver.download-windows" : "wheel.syncserver.download-linux") );
		downloadFile();
		step3Wait.setVisible(true);
		step3Button.setDisabled(false);
		step3Button.setVisible(false);
		timer.start();
	}
	
	public void onRadio(Event ev) {
		step2Button.setDisabled(false);
	}

	@Override
	public void doHighlighted() {
		super.doHighlighted();
		radio = (Radiogroup) getFellow("radio");
		wizard = (Wizard) getFellow("wizard");
		step2Button = (Button) getFellow("step2Button");
		step3Button = (Button) getFellow("step3Button");
		step2Button.setDisabled(true);
		timer = (Timer) getFellow("timer");
		step3Wait = (Image) getFellow("step3Wait");
		wizard.setSelected(0);
		
		explanation2 = (Label) getFellow("explanation2");
		explanation2b = (Div) getFellow("explanation2b");
	}
	
	private void downloadFile() throws InternalErrorException, MalformedURLException, IOException {
		String lastVersion = getLatestSyncserverVersion();
		ServerRegistrationToken srt = new ServerRegistrationToken();
		srt.setStep(2);
		newToken = ServiceLocator.instance().getDispatcherService().preRegisterServer(srt);


    	String fileName;
		if ("rpm".equals(type))
			fileName = "install-syncserver.sh";
		else if ("deb".equals(type))
			fileName = "install-syncserver.sh";
		else if ("psh".equals(type))
			fileName = "install-syncserver.psh";
		else {
			throw new UiException("Wrong type "+type);
		}


		String url = ConfigurationCache.getProperty("AutoSSOURL");
		if (url == null) {
			HttpServletRequest request = (HttpServletRequest) Executions.getCurrent().getNativeRequest();
			url = request.getScheme()+"//"+request.getHeader("Host")+":"+request.getLocalPort()+"/";
		}
		if (!url.endsWith("/")) url = url + "/";
		url += "soffid/anonymous/syncserver/script/"+newToken;
				
		
		String resource = readInputStream(getClass().getResourceAsStream("install-"+type+".sh"));
		resource = resource.replace("VERSION", lastVersion);
		resource = resource.replace("URL", url);

		while ( explanation2b.getFirstChild() != null )
			explanation2b.getFirstChild().detach();
		

		Textbox tb = new Textbox();
		tb.setVisible(false);
		explanation2b.appendChild(tb);
		for (String line: resource.split("\n")) {
			Div d = new Div();
			d.setStyle("margin-top: 20px");
			explanation2b.appendChild(d);
			Label l = new Label(line);
			l.setStyle("line-height: 20px; font-family: monospace; font-size: 10pt");
			d.appendChild(l);
			ImageClic c = new ImageClic();
			c.setStyle("vertical-align: middle; height: 24px; width: 24px; padding: 1px");
			c.setSrc("/img/copy.svg");
			c.setAction("onClick: {var e = document.getElementById('"+tb.getUuid()+"'); "
					+ "e.style.display='inline';"
					+ "e.value=this.previousElementSibling.innerText;"
					+ "e.focus(); "
					+ "e.select(); "
					+ "document.execCommand('copy');"
					+ "e.style.display='none';}");
			d.appendChild(c);
		}
	}

	private String getLatestSyncserverVersion() throws MalformedURLException, IOException {
		String coreVersion = ConfigurationCache.getProperty("component.iam-core.version");
		int i1 = coreVersion.indexOf('.');
		int i2 = coreVersion.indexOf('.', i1+1);
		String prefix = coreVersion.substring(0, i2+1);
		String list = downloadList("https://download.soffid.com/maven/com/soffid/iam/sync/syncserver/");
		Pattern p = Pattern.compile("<a[^>]*>([0-9.]+)</a>");
		Matcher m = p.matcher(list);
		String last = null;
		while (m.find()) {
			String version = m.group(1);
			if (version.startsWith(prefix)) {
				if (last == null || isNewer(version, last))
					last = version;
			}
		}
		return last;
	}
	
	private boolean isNewer(String version, String last) {
		String[] s1 = version.split("\\.");
		String[] s2 = last.split("\\.");
		int i = 0;
		while (i < s1.length) {
			if (i >= s2.length) return true;
			try {
				int i1 = Integer.parseInt(s1[i]);
				int i2 = Integer.parseInt(s2[i]);
				if (i1 > i2) return true;
				if (i1 < i2) return false;
			} catch (NumberFormatException e) {
				return false;
			}
			i++;
		}
		return false;
	}

	private String downloadList(String url) throws MalformedURLException, IOException {
		InputStream in = new URL(url).openStream();
		return readInputStream(in);
	}

	private String readInputStream(InputStream in) throws IOException, UnsupportedEncodingException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte b[] = new byte[64000];
		int read;
		while ((read = in.read(b)) >= 0 ) {
			out.write(b, 0, read);
		}
		in.close();
		return out.toString("UTF-8");
	}


}
