package com.soffid.iam.init;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.soffid.iam.tomcat.SoffidPasswordCipher;

public class Configuration {
	boolean userAlreadyExist;
	Properties props; 

	public static Configuration instance = null;
	
	public static Configuration getConfiguration() throws FileNotFoundException, IOException {
		if (instance == null)
			instance = new Configuration();
		return instance;
	}
	
	private Configuration() throws FileNotFoundException, IOException {
		props = loadProperties();
	}
	
	public boolean isConfigured() {
		return System.getProperty("dbStatus") != null;
	}
	

	public void configureHostName (String hostname) throws SQLException, FileNotFoundException, IOException {
		props.setProperty("hostName", hostname);
		
	}

	public Properties loadProperties() throws IOException, FileNotFoundException {
		Properties props = new Properties();

		String tomcat_home = System.getProperty("catalina.home");
		String fileName = tomcat_home+"/conf/system.properties";
		if (new File(fileName).canRead())
			props.load(new FileInputStream(fileName));
		
		return props;
	}

	public void saveProperties() throws IOException, FileNotFoundException {
		String tomcat_home = System.getProperty("catalina.home");
		String fileName = tomcat_home+"/conf/system.properties";
		props.store(new FileOutputStream(fileName), "Configured from soffid startup wizard");

		for ( Object p: props.keySet()) {
			System.setProperty((String)p, props.getProperty((String)p));
		}
	}

	public void configureDatabase (String user, String password, String driverName, String url) throws Exception {
		userAlreadyExist = false;
		String driverClass = 
				"oracle".equals(driverName) ?  "oracle.jdbc.driver.OracleDriver": //$NON-NLS-1$
				"sqlserver".equals(driverName) ? "com.microsoft.sqlserver.jdbc.SQLServerDriver": //$NON-NLS-1$
				"postgresql".equals(driverName) ? "org.postgresql.Driver": //$NON-NLS-1$
				"mysql".equals(driverName) ? "com.mysql.jdbc.Driver" :
				"org.mariadb.jdbc.Driver"; //$NON-NLS-1$
		try {
			DriverManager.registerDriver((Driver) Class.forName(driverClass).newInstance());
			Driver driver = DriverManager.getDriver(url);
			if (driver != null) {
				driverClass = driver.getClass().getName();
				driverName = url.split(":")[1];
			}
			Connection conn = DriverManager.getConnection(url, user, password);
			try {
				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT COUNT(*) FROM SC_USUARI");
				rset.close();
				stmt.close();
				userAlreadyExist = true;
			} catch (Exception e) { 
				userAlreadyExist = false;
			}
			conn.close();
		} catch (Exception e) {
			throw new Exception("Cannot connect to the database: "+e.getMessage());
		}
		
		String encoded = new String( new SoffidPasswordCipher().encrypt(password) );
		String dbValidationQuery =
				url.startsWith("jdbc:oracle") ? "select 1 from dual" :
				url.startsWith("jdbc:sqlserver") ? "select 1 from sysobjects" :
					"select 1";

		props.setProperty("dbUser", user);
		props.setProperty("dbPassword", encoded);
		props.setProperty("dbDriverUrl", url);
		props.setProperty("dbDriverString", driverName);
		props.setProperty("dbDriverClass", driverClass);
		props.setProperty("dbValidationQuery", dbValidationQuery);
		props.setProperty("dbStatus", "1");
		props.setProperty("dbPasswordCipher", SoffidPasswordCipher.class.getCanonicalName());
		
		updateDriverName(dbValidationQuery);
	}

	private void updateDriverName(String validationString) throws IOException {
		String tomcat_home = System.getProperty("catalina.home");
		String srcName = tomcat_home+"/conf/tomee.xml.template";
		String targetName = tomcat_home+"/webapps/context.xml";
		
		InputStream in = new FileInputStream(srcName);
		
		BufferedReader reader = new BufferedReader( new InputStreamReader(in) );
		StringBuffer full = new StringBuffer();
		for ( String line = reader.readLine(); line != null; line = reader.readLine()) {
			if (line.contains("testOnBorrow"))
				line = "    testOnBorrow = true";
			if (line.contains("jdbcDriver"))
				line = "    validationQuery = "+validationString;
			full.append(line).append("\n");
		}
		reader.close();
		in.close();
		
		FileOutputStream out = new FileOutputStream(targetName);
		out.write(full.toString().getBytes());
		out.close();

		out = new FileOutputStream(targetName);
		out.write(full.toString().getBytes());
		out.close();
	}

	public void restartConsole() throws IOException {
		String fileName = "reset console.txt"; //$NON-NLS-1$
		String fileContent = "File to check reset console"; //$NON-NLS-1$
		String home = System.getProperty ("catalina.home"); //$NON-NLS-1$
		File addonFolder = new File(new File(home, "soffid"), "addons"); //$NON-NLS-1$ //$NON-NLS-2$
		
		addonFolder.mkdirs();
		File file = new File(addonFolder, fileName);
		FileWriter wr;
		wr = new FileWriter(file, true);
		wr.write(fileContent);
		wr.close();
		
	}
	
	public void configureAdmin (String userName, String firstName, String lastName, String password) throws FileNotFoundException, Exception {
		Properties props = loadProperties();
		if (userName != null && !userName.trim().isEmpty())
			props.setProperty("soffid.startup.userName", userName);
		if (firstName != null && !firstName.trim().isEmpty())
			props.setProperty("soffid.startup.firstName", firstName);
		if (lastName != null && !lastName.trim().isEmpty())
			props.setProperty("soffid.startup.lastName", lastName);
		System.setProperty("soffid.startup.password", password);
		
	}

	public boolean isUserAlreadyExist() {
		return userAlreadyExist;
	}

	public void setUserAlreadyExist(boolean userAlreadyExist) {
		this.userAlreadyExist = userAlreadyExist;
	}
}
