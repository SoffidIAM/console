package com.soffid.iam.db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.DriverPropertyInfo;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.openejb.cipher.PasswordCipher;

public class SoffidBrokerDriver implements Driver {
	Driver actualDriver = null;
	private String user;
	private String url;
	private static String pass;
	private String validationQuery;
	
	private Driver getDriver() throws SQLException {
		if (actualDriver != null)
			return actualDriver;
		
    	if (! "1".equals( System.getProperty("dbStatus")))
    		throw new SQLException ("Database is not configured yet");
    
    	user = System.getProperty("dbUser");
    	url = System.getProperty("dbDriverUrl");
    	String driverClassname = (System.getProperty("dbDriverClass"));
    	validationQuery = (System.getProperty("dbValidationQuery"));

    	if (pass == null) {
	    	pass = (System.getProperty("dbPassword"));
	    	String pcc = System.getProperty("dbPasswordCipher");
	    	if (pcc != null && !"PlainText".equalsIgnoreCase(pcc)) {
	    		PasswordCipher pc;
				try {
					pc = (PasswordCipher) Class.forName(pcc).newInstance();
				} catch (Exception e) {
					throw new SQLException(e);
				}
	    		pass = pc.decrypt(pass.toCharArray());
	    	}
	    	
	    	System.getProperties().remove("dbPassword");
    	}
    	
    	try {
    		actualDriver = (Driver) Class.forName(driverClassname).newInstance();
    		DriverManager.registerDriver(actualDriver);
    		return actualDriver;
    	} catch (Exception e) {
    		throw new SQLException(e);
    	}
	}

	@Override
	public Connection connect(String url, Properties info) throws SQLException {
		Driver driver = getDriver();
		info.put("user", user);
		info.put("password", pass);
		return driver.connect(this.url, info);
	}

	@Override
	public boolean acceptsURL(String url) throws SQLException {
		return url.startsWith("jdbc:soffid:");
	}

	@Override
	public DriverPropertyInfo[] getPropertyInfo(String url, Properties info) throws SQLException {
		return getDriver().getPropertyInfo(url, info);
	}

	@Override
	public int getMajorVersion() {
		if (actualDriver != null)
			return actualDriver.getMajorVersion();
		else
			return 1;
	}

	@Override
	public int getMinorVersion() {
		if (actualDriver != null)
			return actualDriver.getMinorVersion();
		else
			return 0;
	}

	@Override
	public boolean jdbcCompliant() {
		if (actualDriver != null)
			return actualDriver.jdbcCompliant();
		else
			return false;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		if (actualDriver != null)
			return actualDriver.getParentLogger();
		else
			throw new SQLFeatureNotSupportedException();
	}

}
