package es.caib.seycon.ng.install;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Properties;
import java.util.Random;

import javax.swing.JOptionPane;

import com.install4j.api.context.Context;
import com.install4j.api.context.ProgressInterface;
import com.install4j.api.screens.Screen;

public class DatabaseCreator {
    private static final int MYSQL_DRIVER = 0;
    private static final int ORACLE_DRIVER_SID = 1;
    private static final int SQLSERVER_DRIVER=2;
    private static final int ORACLE_DRIVER_SVC = 3;
    private static final int POSTGRESQL_DRIVER=4;
    private static final int MARIADB_DRIVER = 5;
    Context ctx;
    private String user;
    private String password;
    private String host;
    private Long port;
    private String sid;
    private String schema;
    private String schemaPassword;
    Screen screen;
    private String tableTablespace;
    private String indexTablespace;
    private Long tableTablespaceSize;
    private Long indexTablespaceSize;
    private StringBuffer line;
    private StringBuffer buffer;
    private boolean plsql;

    private Statement stmt;
    private int driver;
    private String driverString;
    private String driverUrl;
    private String driverClass;
	private Connection currentConnection;
	private Boolean createUser;
	private String sanitySelect;

    public DatabaseCreator(com.install4j.api.context.Context ctx) throws IOException {
        this.ctx = ctx;
        new PropertiesStore(ctx).load();
        
        user = (String) ctx.getVariable("dbAdminUser"); //$NON-NLS-1$
        password = (String) ctx.getVariable("dbAdminPassword"); //$NON-NLS-1$
        host = (String) ctx.getVariable("dbHost"); //$NON-NLS-1$
        port = (Long) ctx.getVariable("dbPort"); //$NON-NLS-1$
        sid = (String) ctx.getVariable("dbSid"); //$NON-NLS-1$
        schema = (String) ctx.getVariable("dbUser"); //$NON-NLS-1$
        schemaPassword = (String) ctx.getVariable("dbPassword"); //$NON-NLS-1$
        createUser = (Boolean) ctx.getVariable("dbCreateUser"); //$NON-NLS-1$

        tableTablespace = (String) ctx.getVariable("dbTableTablespace"); //$NON-NLS-1$
        indexTablespace = (String) ctx.getVariable("dbIndexTablespace"); //$NON-NLS-1$
        tableTablespaceSize = (Long) ctx.getVariable("dbTableTablespaceSize"); //$NON-NLS-1$
        indexTablespaceSize = (Long) ctx.getVariable("dbIndexTablespaceSize"); //$NON-NLS-1$
        driver = ((Integer) ctx.getVariable("dbDriver")).intValue(); //$NON-NLS-1$
        if (driver == ORACLE_DRIVER_SID) {
            driverString = "oracle"; //$NON-NLS-1$
            driverUrl = "jdbc:oracle:thin:@"+host+":"+port+":"+sid; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            driverClass= "oracle.jdbc.driver.OracleDriver"; //$NON-NLS-1$
            sanitySelect = "select 1 from dual"; //$NON-NLS-1$
        } else if (driver == ORACLE_DRIVER_SVC) {
            driverString = "oracle"; //$NON-NLS-1$
            driverUrl = "jdbc:oracle:thin:@//"+host+":"+port+"/"+sid; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            driverClass= "oracle.jdbc.driver.OracleDriver"; //$NON-NLS-1$
            sanitySelect = "select 1 from dual"; //$NON-NLS-1$
        } else if(driver == SQLSERVER_DRIVER){
        	driverString = "sqlserver"; //$NON-NLS-1$
        	driverUrl = "jdbc:sqlserver://"+host+":"+port+";databaseName=" + sid + ";"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            driverClass= "com.microsoft.sqlserver.jdbc.SQLServerDriver"; //$NON-NLS-1$
            sanitySelect = "select 1 from sysobjects"; //$NON-NLS-1$
            //connectionChecker="org.jboss.resource.adapter.jdbc.vendor.MSSQLValidConnectionChecker"; //$NON-NLS-1$
        } else if(driver == POSTGRESQL_DRIVER){
        	driverString = "postgresql"; //$NON-NLS-1$
        	driverUrl = "jdbc:postgresql://"+host+":"+port+"/" + sid; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            driverClass= "org.postgresql.Driver"; //$NON-NLS-1$
            sanitySelect = "select 1"; //$NON-NLS-1$
            //connectionChecker="org.jboss.resource.adapter.jdbc.vendor.MSSQLValidConnectionChecker"; //$NON-NLS-1$
        } else if (driver == MYSQL_DRIVER ){
            driverString = "mysql"; //$NON-NLS-1$
            driverUrl = "jdbc:mysql://"+host+":"+port+"/"+sid+ //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            		"?useUnicode=yes&characterEncoding=UTF-8"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            driverClass= "com.mysql.jdbc.Driver"; //$NON-NLS-1$
            sanitySelect = "select 1"; //$NON-NLS-1$
        } else {
            driverString = "mariadb"; //$NON-NLS-1$
            driverUrl = "jdbc:mariadb://"+host+":"+port+"/"+sid+ //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            		""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            driverClass= "org.mariadb.jdbc.Driver"; //$NON-NLS-1$
            sanitySelect = "select 1"; //$NON-NLS-1$
        }
        ctx.setVariable("dbSchema", schema); //$NON-NLS-1$
        ctx.setVariable("dbDriverUrl", driverUrl); //$NON-NLS-1$
        ctx.setVariable("dbDriverClass", driverClass); //$NON-NLS-1$
        ctx.setVariable("dbDriverString", driverString); //$NON-NLS-1$
        ctx.setVariable("dbSanitySelect", sanitySelect); //$NON-NLS-1$
        ctx.setVariable("dbValidationQuery", sanitySelect); //$NON-NLS-1$
        ctx.setVariable("InstallationDirectory", ctx.getInstallationDirectory().getAbsolutePath()); //$NON-NLS-1$
        PropertiesStore ps = new PropertiesStore(ctx);
        ps.save();
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    Connection getMasterConnection() throws Exception {
        Class c = Class.forName(driverClass);
        DriverManager.registerDriver((java.sql.Driver) c.newInstance());

        String url;
        if (driver == (ORACLE_DRIVER_SID)){
            url = "jdbc:oracle:thin:@" + host + ":" + port + ":" + sid; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            Properties props = new Properties(); 
      	  	props.put("user", user); 
      	  	props.put("password", password);
      	  	props.put("internal_logon", "sysdba");
      	  	return DriverManager.getConnection (url, props);
        }
        else if (driver == (ORACLE_DRIVER_SID)){
            url = "jdbc:oracle:thin:@//" + host + ":" + port + "/" + sid; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            Properties props = new Properties(); 
      	  	props.put("user", user); 
      	  	props.put("password", password);
      	  	props.put("internal_logon", "sysdba");
      	  	return DriverManager.getConnection (url, props);
        }
        else if (driver == (POSTGRESQL_DRIVER)){
            url = "jdbc:postgresql://" + host + ":" + port + "/" + sid; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        	return DriverManager.getConnection(url, user, password);
        }
        else if (driver == (SQLSERVER_DRIVER)){
        	url = "jdbc:sqlserver://" + host + ":" + port;
        	return DriverManager.getConnection(url, user, password);
        }
        else if (driver == MYSQL_DRIVER){
            url = "jdbc:mysql://" + host + ":" + port + "/"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return DriverManager.getConnection(url, user, password);
        } 
        else{
            url = "jdbc:mariadb://" + host + ":" + port + "/"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            return DriverManager.getConnection(url, user, password);
        } 
    }

    Connection getConnection() throws Exception {
        Class c = Class.forName(driverClass);
        DriverManager.registerDriver((java.sql.Driver) c.newInstance());

        String url;
        return DriverManager.getConnection(driverUrl, schema, schemaPassword);
    }

    public void test() throws Exception {
        Connection c = getMasterConnection();
        PreparedStatement s = c.prepareStatement(driver == ORACLE_DRIVER_SID || driver == ORACLE_DRIVER_SVC ? "SELECT 1 FROM DUAL" //$NON-NLS-1$
                : "SELECT 1"); //$NON-NLS-1$
        ResultSet rset = s.executeQuery();
        rset.close();
        s.close();
        c.close();
    }

    private void createSchema(Connection c) throws SQLException, IOException {

        if (schemaPassword == null) {
            generatePassword();
        }

        ProgressInterface pi = ctx.getProgressInterface();
        pi.setPercentCompleted(6);

        pi.setStatusMessage("Creating user ....");  //$NON-NLS-1$
        pi.setPercentCompleted(1);
        if (ORACLE_DRIVER_SID == (driver) || ORACLE_DRIVER_SVC == driver) {
            PreparedStatement pstmt = c
                    .prepareStatement("SELECT 1 FROM SYS.ALL_USERS WHERE USERNAME=?"); //$NON-NLS-1$
            pstmt.setString(1, schema.toUpperCase());
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                executeSentence("ALTER USER " + schema + " IDENTIFIED BY \"" + schemaPassword //$NON-NLS-1$ //$NON-NLS-2$
                        + "\""); //$NON-NLS-1$
            } else {
                executeSentence("CREATE USER " + schema + " IDENTIFIED BY \"" + schemaPassword //$NON-NLS-1$ //$NON-NLS-2$
                        + "\""); //$NON-NLS-1$
            }
            rset.close();
            pstmt.close();
            executeSentence("GRANT DBA TO " + schema); //$NON-NLS-1$

            pi.setPercentCompleted(2);

            pi.setStatusMessage("Creating data tablespace ....");  //$NON-NLS-1$
            createTablespace(c, tableTablespace, tableTablespaceSize);
            executeSentence("ALTER USER " + schema + " QUOTA UNLIMITED ON " + tableTablespace); //$NON-NLS-1$ //$NON-NLS-2$

            pi.setPercentCompleted(6);
            pi.setStatusMessage("Creating index tablespace ....");  //$NON-NLS-1$

            createTablespace(c, indexTablespace, indexTablespaceSize);
            executeSentence("ALTER USER " + schema + " QUOTA UNLIMITED ON " + indexTablespace); //$NON-NLS-1$ //$NON-NLS-2$
        } else if (POSTGRESQL_DRIVER == (driver)) {
            PreparedStatement pstmt = c
                    .prepareStatement("select * from pg_catalog.pg_user where usename=?"); //$NON-NLS-1$
            pstmt.setString(1, schema.toLowerCase());
            ResultSet rset = pstmt.executeQuery();
            if (rset.next()) {
                executeSentence("ALTER USER " + schema + " WITH PASSWORD '" + schemaPassword //$NON-NLS-1$ //$NON-NLS-2$
                        + "'"); //$NON-NLS-1$
            } else {
                executeSentence("CREATE USER " + schema + " WITH PASSWORD '" + schemaPassword //$NON-NLS-1$ //$NON-NLS-2$
                        + "'"); //$NON-NLS-1$
            }
            rset.close();
            pstmt.close();
            executeSentence("GRANT ALL PRIVILEGES ON DATABASE "+sid+" TO " + schema); //$NON-NLS-1$
        } else if (MYSQL_DRIVER == (driver) || MARIADB_DRIVER == driver){
            PreparedStatement pstmt = c.prepareStatement("SHOW DATABASES"); //$NON-NLS-1$
            ResultSet rset = pstmt.executeQuery();
            boolean found = false;
            while (rset.next()) {
                String dbName = rset.getString(1);
                if (dbName.equals(sid)) {
                    found = true;
                    pi.setPercentCompleted(6);

                    break;
                }
            }
            if (!found) {
                pi.setStatusMessage("Creating database " + sid);  //$NON-NLS-1$
                executeSentence("CREATE DATABASE " + sid); //$NON-NLS-1$
            }
            rset.close();
            pstmt.close();
            pi.setPercentCompleted(6);
            pi.setStatusMessage("Creating " + schema + "user");   //$NON-NLS-1$ //$NON-NLS-2$
            pstmt = c.prepareStatement("SELECT 1 FROM mysql.user WHERE user=?"); //$NON-NLS-1$
            pstmt.setString(1, schema);
            rset = pstmt.executeQuery();
            if (!rset.next()) {
                executeSentence("CREATE USER " + schema + " IDENTIFIED BY '" + schemaPassword + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            rset.close();
            pstmt.close();
            pi.setPercentCompleted(8);
            pi.setStatusMessage("Granting access to " + schema + "@localhost"); //$NON-NLS-1$ //$NON-NLS-2$
            executeSentence("GRANT ALL ON " + sid + ".* TO " + schema //$NON-NLS-1$ //$NON-NLS-2$
                    + "@localhost IDENTIFIED BY '" + schemaPassword + "'"); //$NON-NLS-1$ //$NON-NLS-2$
            pi.setPercentCompleted(9);
            String hostname = InetAddress.getLocalHost().getHostName();
            pi.setStatusMessage("Granting access to " + schema + "@" + hostname); //$NON-NLS-1$ //$NON-NLS-2$
            executeSentence("GRANT ALL ON " + sid + ".* TO " + schema + "@'" + hostname //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    + "' IDENTIFIED BY '" + schemaPassword + "'"); //$NON-NLS-1$ //$NON-NLS-2$

        } else {	//Afegit per SQLSERVER
        	PreparedStatement pstmt = c.prepareStatement("Select name from master..sysdatabases"); //$NON-NLS-1$
            ResultSet rset = pstmt.executeQuery();
            boolean found = false;
            while (rset.next()) {
                String dbName = rset.getString(1);
                if (dbName.equals(sid)) {
                    found = true;
                    pi.setPercentCompleted(6);
                    break;
                }
            }
            if (!found) {
                pi.setStatusMessage("Creating database " + sid);  //$NON-NLS-1$
                executeSentence("CREATE DATABASE " + sid); //$NON-NLS-1$
            }
            rset.close();
            pstmt.close();
        	executeSentence("USE " + sid);
            pi.setPercentCompleted(6);
            pi.setStatusMessage("Creating " + schema + "user");   //$NON-NLS-1$ //$NON-NLS-2$
            pstmt = c.prepareStatement("SELECT 1 FROM  master..syslogins WHERE name=?");
            pstmt.setString(1, schema);
            rset = pstmt.executeQuery();
            if (!rset.next()) {
            	executeSentence("CREATE LOGIN " + schema + " WITH  password='" + schemaPassword + "'");
            }
            rset.close();
            pstmt.close();
            pstmt = c.prepareStatement("SELECT 1 FROM sys.database_principals WHERE name=?"); //$NON-NLS-1$
            pstmt.setString(1, schema);
            rset = pstmt.executeQuery();
            if (!rset.next()) {
            	executeSentence("CREATE USER " + schema + " FOR LOGIN " + schema); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            }
            rset.close();
            pstmt.close();
            pi.setPercentCompleted(8);
            pi.setStatusMessage("Granting access to " + schema); //$NON-NLS-1$ //$NON-NLS-2$
            executeSentence("USE " + sid);
            executeSentence("GRANT CONTROL TO " + schema);
            pi.setPercentCompleted(9);
        }
        pi.setPercentCompleted(10);
    }

    private void createTablespace(Connection c, String tablespaceName, Long tablespaceSize)
            throws SQLException {

        PreparedStatement pstmt = c
                .prepareStatement("SELECT 1 FROM SYS.DBA_TABLESPACES WHERE TABLESPACE_NAME=?"); //$NON-NLS-1$
        pstmt.setString(1, tablespaceName);
        ResultSet rset = pstmt.executeQuery();
        if (rset.next()) {
        	try{
	            JOptionPane.showMessageDialog(null, "Tablespace " + tablespaceName  //$NON-NLS-1$
	                    + " already exists and will be reused", "Warning",   //$NON-NLS-1$ //$NON-NLS-2$
	                    JOptionPane.INFORMATION_MESSAGE);
        	}catch(Exception error){
        		System.out.println("Tablespace " + tablespaceName  +" already exists and will be reused");
        	}
        } else {
            rset.close();
            rset = stmt
                    .executeQuery("SELECT FILE_NAME FROM SYS.DBA_DATA_FILES WHERE TABLESPACE_NAME='SYSTEM'"); //$NON-NLS-1$
            if (!rset.next()) {
            	try{
            		JOptionPane.showMessageDialog(null,
            				"Cannot guess data file name (no system tablespace found)", "Error",   //$NON-NLS-1$//$NON-NLS-2$
            				JOptionPane.WARNING_MESSAGE);
            	}catch(Exception error){
            		System.out.println("Cannot guess data file name (no system tablespace found)");
            	}
            } else {
                String n = rset.getString(1);
                int i = n.lastIndexOf(File.separatorChar);
                if (i < 0) {
                	try{
                		JOptionPane.showMessageDialog(null, "Cannot guess data directory (" + n + ")",   //$NON-NLS-1$ //$NON-NLS-2$
                				"Error", JOptionPane.WARNING_MESSAGE);  //$NON-NLS-1$
                	}catch(Exception error){
                		System.out.println("Cannot guess data directory (" + n + ")");
                	}
                } else {
                    String data_file = n.substring(0, i + 1) + tablespaceName + "01.dbf"; //$NON-NLS-1$
                    executeSentence("CREATE TABLESPACE " + tablespaceName + " DATAFILE '" //$NON-NLS-1$ //$NON-NLS-2$
                            + data_file + "' size " + tablespaceSize + "M " //$NON-NLS-1$ //$NON-NLS-2$
                            + "EXTENT MANAGEMENT LOCAL " + "AUTOALLOCATE " //$NON-NLS-1$ //$NON-NLS-2$
                            + "SEGMENT SPACE MANAGEMENT AUTO"); //$NON-NLS-1$
                }
            }
        }
        rset.close();
        pstmt.close();
    }

    public void createSchema() throws Exception {

        Connection connection = getMasterConnection();
        stmt = connection.createStatement();

        createSchema(connection);

        stmt.close();
        connection.close();
    }

    public void generatePassword() throws IOException {
        StringBuffer pass = new StringBuffer();
        Random r = new Random(System.currentTimeMillis());
        for (int i = 0; i < 8; i++) {
            int n = r.nextInt(i == 0 ? 52 : 62);
            if (n < 26)
                pass.append((char) ((int) 'A' + n));
            else if (n < 52)
                pass.append((char) ((int) 'a' + (n - 26)));
            else
                pass.append((char) ((int) '0' + (n - 52)));
        }

        schemaPassword = pass.toString();
        ctx.setVariable("dbPassword", schemaPassword); //$NON-NLS-1$
        new PropertiesStore(ctx).save();
    }

    File getFile(int status) {
        String s = "db/script" + status + ".sql"; //$NON-NLS-1$ //$NON-NLS-2$
        return new File(ctx.getInstallationDirectory(), s);
    }

    public boolean create() throws Exception {
        ProgressInterface pi = ctx.getProgressInterface();
        
    	if (createUser != null && createUser.booleanValue())
    	{

	        pi.setStatusMessage("Connecting ....");  //$NON-NLS-1$
	
            createSchema();
	
	        pi.setStatusMessage("Done");  //$NON-NLS-1$
	        ctx.setVariable("dbStatus", "1");
	        ctx.setVariable("b.dbCreateUser", "false");
    	} else if (schemaPassword != null ) {
	        pi.setStatusMessage("Testing connection ....");  //$NON-NLS-1$
            PropertiesStore ps = new PropertiesStore(ctx);
            ps.save();
    		getConnection();
	        ctx.setVariable("dbStatus", "1");
    	}
        PropertiesStore ps = new PropertiesStore(ctx);
        ps.save();
        return true;
    }

    private void executeSentence(String b) throws SQLException {
        try {
            String originalSQL = b;
            int i;
            while ((i = b.indexOf("${")) >= 0) { //$NON-NLS-1$
                int j = b.indexOf("}", i); //$NON-NLS-1$
                if (j < 0)
                    throw new SQLException("Unbound ${ on " + b); //$NON-NLS-1$
                String variable = b.substring(i + 2, j);
                Object value;
                if (variable.startsWith("crypt:")) { //$NON-NLS-1$
                    value = ctx.getVariable(variable.substring(6));
                    if (value != null)
                        value = new Password(value.toString());
                } else {
                    value = ctx.getVariable(variable);
                }
                if (value == null)
                    throw new SQLException("Unknown variable " + variable);  //$NON-NLS-1$
                b = b.substring(0, i) + value.toString() + b.substring(j + 1);
            }
            stmt.execute(b);
        } catch (SQLException e) {
            if ((driver == ORACLE_DRIVER_SID  || ORACLE_DRIVER_SVC == driver) 
            		&& (e.getErrorCode() == 955 || // Name
                                                                       // is
                                                                       // already
                                                                       // used
                    e.getErrorCode() == 1408 || // Columns already indexed
                    e.getErrorCode() == 2260 || // Table can only have on
                                                // primary key
                    e.getErrorCode() == 2261 || // Unique key already exists
                    e.getErrorCode() == 2264 || // Name is already used by
                                                // another constraint
                    e.getErrorCode() == 2275 || // Referential constraint
                                                // already exists
                    e.getErrorCode() == 1442) || // Column is already not null
                    (driver == MYSQL_DRIVER && (e.getErrorCode() == 1050 || // Table
                                                                            // already
                                                                            // exists
                            e.getErrorCode() == 1005 || // Constraint already exists
                            e.getErrorCode() == 1061 || // Constraint already
                                                        // exists
                    e.getErrorCode() == 1068))) // Primary key already exists
            {
                System.out.println("Ignoring error " + e.getErrorCode() + ": " + b);   //$NON-NLS-1$ //$NON-NLS-2$
            } else if(driver == SQLSERVER_DRIVER && e.getErrorCode() == 1779 || e.getErrorCode() == 1913
            		|| e.getErrorCode() == 2714 || e.getErrorCode() == 2705){
            	 System.out.println("Ignoring error " + e.getErrorCode() + ": " + b);
            }
            else
                throw new SQLException(e.getMessage() + "\n Error " + e.getErrorCode()  //$NON-NLS-1$
                        + " Executing: " + b, e.getSQLState(), e.getErrorCode());  //$NON-NLS-1$
        }
    }
}
