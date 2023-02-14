package com.soffid.iam.web.syncserver;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.LogFactory;
import org.apache.openejb.cipher.PasswordCipher;
import org.json.JSONArray;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.ServerRegistrationToken;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.exception.InternalErrorException;


@WebServlet(urlPatterns = {"/anonymous/syncserver/script/*"})
public class InstallationScriptServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = req.getPathInfo();
		Security.nestedLogin(Security.ALL_PERMISSIONS);
		try {
			ServerRegistrationToken nr = ServiceLocator.instance().getDispatcherService().consumeRegistrationToken(path.substring(1));
			if (nr == null || nr.getStep() != 2) {
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			} else {
				String main = null;
				String gateway = null;
				for (Server server: ServiceLocator.instance().getDispatcherService().findAllServers()) {
					if (server.getType() == ServerType.MASTERSERVER)
						main = server.getUrl();
					if (server.getType() == ServerType.GATEWAY)
						gateway = server.getUrl();
				}
				if (main == null)
					generateFirstServer(req, resp);
				else
					generateSecondServer(req, resp, main, gateway);
			}
		} catch (InternalErrorException e) {
			LogFactory.getLog(getClass()).warn("Error generating configuration file", e);
			resp.sendError(500);
		} finally {
			Security.nestedLogoff();
		}
	}

	private void generateSecondServer(HttpServletRequest req, HttpServletResponse resp, String main, String gateway) throws IOException, InternalErrorException {
		ServerRegistrationToken register = new ServerRegistrationToken();
		register.setStep(3);
		String  token = ServiceLocator.instance().getDispatcherService().preRegisterServer(register );
    	JSONArray a = new JSONArray();
    	a.put("-server");
    	a.put(main);
    	if (gateway != null)
    		a.put("-remote");
    	a.put("-user");
    	a.put("");
    	a.put("-pass");
    	a.put(token);
    	a.put("-port");
    	a.put("1760");
		ServletOutputStream out = resp.getOutputStream();
		out.print(a.toString());
		out.close();
	}

	private void generateFirstServer(HttpServletRequest req, HttpServletResponse resp) throws FileNotFoundException, IOException {
		Properties props = new Properties();
		props.load(new FileInputStream(System.getProperty("catalina.home")+"/conf/system.properties"));
    	String user = props.getProperty("dbUser");
    	String url = props.getProperty("dbDriverUrl");
    	String pass = (props.getProperty("dbPassword"));
    	String pcc = props.getProperty("dbPasswordCipher");
    	if (pcc != null && !"PlainText".equalsIgnoreCase(pcc)) {
    		PasswordCipher pc;
			try {
				pc = (PasswordCipher) Class.forName(pcc).newInstance();
			} catch (Exception e) {
				throw new IOException(e);
			}
    		pass = pc.decrypt(pass.toCharArray());
    	}
    	
    	JSONArray a = new JSONArray();
    	a.put("-main");
    	a.put("-dbuser");
    	a.put(user);
    	a.put("-dbpass");
    	a.put(pass);
    	a.put("-dburl");
    	a.put(url);
    	a.put("-port");
    	a.put("1760");
		ServletOutputStream out = resp.getOutputStream();
		out.print(a.toString());
		out.close();
	}

}
