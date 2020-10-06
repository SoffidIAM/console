package com.soffid.iam.init;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.soffid.iam.tomcat.SoffidPasswordCipher;

public class Configuration {
	public Configuration() {
		
	}
	
	public boolean isConfigured() {
		return System.getProperty("dbStatus") != null;
	}
	

	public void configureHostName (String hostname) throws SQLException, FileNotFoundException, IOException {
		Properties props = new Properties();
		String tomcat_home = System.getProperty("catalina.home");
		String fileName = tomcat_home+"/conf/system.properties";
		props.load(new FileInputStream(fileName));
		props.setProperty("hostName", hostname);
		
		System.setProperties(props);
		
		props.store(new FileOutputStream(fileName), "Configured from soffid console");
		
	}

	public void configureDatabase (String user, String password, String driverName, String url) throws SQLException, FileNotFoundException, IOException {
		Driver driver = DriverManager.getDriver(url);
		if (driver != null)
			driverName = driver.getClass().getName();
		
		Connection conn = DriverManager.getConnection(url, user, password);
		
		String encoded = new String( new SoffidPasswordCipher().encrypt(password) );
		String dbValidationQuery =
				url.startsWith("jdbc:oracle") ? "select 1 from dual" :
				url.startsWith("jdbc:sqlserver") ? "select 1 from sysobjects" :
					"select 1";

		Properties props = new Properties();
		String tomcat_home = System.getProperty("catalina.home");
		String fileName = tomcat_home+"/conf/system.properties";
		props.load(new FileInputStream(fileName));
		props.setProperty("dbUser", user);
		props.setProperty("dbPassword", encoded);
		props.setProperty("dbDriverUrl", url);
		props.setProperty("dbDriverClass", driverName);
		props.setProperty("dbValidationQuery", dbValidationQuery);
		props.setProperty("dbStatus", "1");
		
		System.setProperties(props);
		
		props.store(new FileOutputStream(fileName), "Configured from soffid console");
		
		updateDriverName(driverName);
	}

	private void updateDriverName(String driverName) throws IOException {
		String tomcat_home = System.getProperty("catalina.home");
		String fileName = tomcat_home+"/conf/tomee.xml";
		
		InputStream in = new FileInputStream(fileName);
		
		BufferedReader reader = new BufferedReader( new InputStreamReader(in) );
		StringBuffer full = new StringBuffer();
		for ( String line = reader.readLine(); line != null; line = reader.readLine()) {
			if (line.contains("jdbcDriver"))
				line = "    jdbcDriver = "+driverName;
			full.append(line).append("\n");
		}
		reader.close();
		in.close();
		
		FileOutputStream out = new FileOutputStream(fileName);
		out.write(full.toString().getBytes());
		out.close();
	}
}
