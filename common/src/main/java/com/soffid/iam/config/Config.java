package com.soffid.iam.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Properties;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.ServerRedirectException;

import com.soffid.iam.api.Password;
import com.soffid.iam.remote.RemoteServiceLocator;
import com.soffid.iam.remote.URLManager;
import com.soffid.iam.sync.service.ServerService;

public class Config {
    public static final String PORT_PROPERTY = "port";
    public static final String LOCAL_PORT_PROPERTY = "localPort";
    public static final String DB_PROPERTY = "db";
	public static final String BACKUPDB_PROPERTY = "backupdb";
	public static final String BACKUP_PASSWORD_PROPERTY = "backuppassword";
	public static final String BACKUPUSER_PROPERTY = "backupuser";
	public static final String PASSWORD_PROPERTY = "password";
	public static final String ROL_PROPERTY = "rol";
	public static final String JAVA_OPT_PROPERTY = "java_opt";
	public static final String USER_PROPERTY = "user";
	public static final String SEYCON_SERVER_STANDBY_PROPERTY = "seycon.server.standby";
	public static final String AUTOUPDATE_PROPERTY = "autoupdate";
	public static final String SERVERLIST_PROPERTY = "serverlist";
	private static Config theConfig;
    Properties prop = new Properties();

    boolean inMemory = false;
    private ServerService serverService;

    private Config() throws FileNotFoundException, IOException {
        reload();
    }
    
    static final String []propertiesToMerge = new String [] {
    	ROL_PROPERTY,
    	SERVERLIST_PROPERTY,
    	PORT_PROPERTY,
    	DB_PROPERTY,
    	PASSWORD_PROPERTY,
    	USER_PROPERTY,
    	BACKUPDB_PROPERTY,
    	BACKUP_PASSWORD_PROPERTY,
    	BACKUPUSER_PROPERTY,
    	JAVA_OPT_PROPERTY
    };
    
    public void updateFromServer () throws IOException, InternalErrorException
    {
    	RemoteServiceLocator rsl = new RemoteServiceLocator();
    	boolean repeat;
        do
        {
        	repeat = false;
    		try
    		{
    			mergeProperties( serverService.getMyConfig() );
    		}
    		catch (ServerRedirectException e)
    		{
    			if (e.getServerList().isEmpty())
    				throw new InternalErrorException("No servers available");
    			String firstServer = e.getServerList().iterator().next();
    			rsl = new RemoteServiceLocator(firstServer);
    			repeat = true;
    		}
        } while (repeat);
    	
    }
    public void mergeProperties (Properties newProp) throws IOException
    {
    	for (String s: propertiesToMerge)
    	{
    		String value = newProp.getProperty(s);
    		if (value == null)
    			prop.remove(s);
    		else
    			prop.setProperty(s, newProp.getProperty(s));
    	}
    	update();
    }

	public void reload() throws IOException, FileNotFoundException
	{
		File config = getConfigFile();
        if (config.canRead()) {
            prop.load(new FileInputStream(config));
            inMemory = false;
        }
	}

    public ServerService getServerService() {
        return serverService;
    }

    public void setServerService(ServerService server) {
        this.serverService = server;
    }

    private Config(String serverList, String port)
            throws FileNotFoundException, IOException {
        inMemory = true;
        setRole("client"); //$NON-NLS-1$
        setServerList(serverList);
        setPort(port);
    }

    private File getConfigFile() throws IOException {
        return new File(getHomeDir(), "/conf/seycon.properties"); //$NON-NLS-1$
    }

    private void update() throws IOException {
        if (!inMemory)
        {
        	try
			{
				prop.setProperty("java_opt", getJVMOptions());
			}
			catch (InternalErrorException e)
			{
			}
            prop.store(new FileOutputStream(getConfigFile()),
                    "Soffid autogenerated file"); //$NON-NLS-1$
        }
     }

    public File getHomeDir() throws IOException {
        String exe = System.getProperty("exe4j.moduleName"); //$NON-NLS-1$
        if (exe != null) {
            return new File(exe).getParentFile().getParentFile();
        }
        URL url = Config.class.getResource("Config.class"); //$NON-NLS-1$
        if ("jar".equals(url.getProtocol())) { //$NON-NLS-1$
            int i = url.getFile().lastIndexOf('!');
            if (i > 0) {
                URL jarFileUrl = new URL(url.getFile().substring(0, i));
                if ("file".equals(jarFileUrl.getProtocol())) { //$NON-NLS-1$
                    String jarURL = jarFileUrl.getFile();
                    jarURL = jarURL.replace("%20", " "); //$NON-NLS-1$ //$NON-NLS-2$
                    File f = new File(jarURL);
                    return f.getParentFile().getParentFile().getCanonicalFile();
                }
            }
        }

        if ("file".equals(url.getProtocol())) { //$NON-NLS-1$
            File classRoot = new File(url.getFile()). // /classes/es/caib/seycon/config/Config.class
                    getParentFile(). // /classes/es/caib/seycon/config
                    getParentFile(). // /classes/es/caib/seycon
                    getParentFile(). // /classes/es/caib
                    getParentFile(). // /classes/es
                    getParentFile(); // /classes
            return new File(classRoot.getParent(), "server-test").getCanonicalFile(); //$NON-NLS-1$
        }

        return new File("."); //$NON-NLS-1$
    }

    public File getLogFile() throws IOException {
        File dir = getLogDir();
        return new File(dir, "syncserver.log"); //$NON-NLS-1$
    }

    public File getLogDir() throws IOException {
        File home = getHomeDir();
        File dir;
        if (File.separatorChar == '\\') {
            dir = new File(home, "log"); //$NON-NLS-1$
        } else {
            dir = new File("/var/log/soffid"); //$NON-NLS-1$
        }
	dir.mkdirs();
        return dir;
    }

    public static Config getConfig() throws FileNotFoundException, IOException {
        if (theConfig == null)
            theConfig = new Config();
        return theConfig;
    }

    public static void configureClient(String serverList, String port)
            throws FileNotFoundException, IOException {
        theConfig = new Config(serverList, port);
    }

    public String getServerList() throws RemoteException,
            InternalErrorException {
        if (isServer()) {
            return serverService.getConfig("seycon.server.list"); //$NON-NLS-1$
        } else
            return prop.getProperty(SERVERLIST_PROPERTY); //$NON-NLS-1$
    }

    public String getJVMOptions() throws RemoteException,
            InternalErrorException {
        String properties = prop.getProperty(JAVA_OPT_PROPERTY); //$NON-NLS-1$
        if (properties == null || properties.trim().length() == 0) {
            if (isServer()) {
                properties = "-Xmx512m"; //$NON-NLS-1$
            } else {
                properties = "-Xmx64m"; //$NON-NLS-1$
            }
        }
        return properties;
    }

    public void setServerList(String list) throws IOException {
    	if (list == null)
    		prop.remove(SERVERLIST_PROPERTY);
    	else
    		prop.setProperty(SERVERLIST_PROPERTY, list); //$NON-NLS-1$
        update();
    }

    public String getRole() {
        return prop.getProperty(ROL_PROPERTY); //$NON-NLS-1$
    }

    public boolean isServer() {
        return "server".equals(getRole()) && serverService != null; //$NON-NLS-1$
    }

    public boolean isAgent() {
        return "agent".equals(getRole()); //$NON-NLS-1$
    }

    public boolean isUpdateEnabled() {
        String s = prop.getProperty(AUTOUPDATE_PROPERTY); //$NON-NLS-1$
        if (s == null) {
            prop.setProperty(AUTOUPDATE_PROPERTY, "true"); //$NON-NLS-1$ //$NON-NLS-2$
            try {
                update();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }
        return !"false".equals(s); //$NON-NLS-1$
    }

    public boolean isStandbyServer() throws RemoteException,
            InternalErrorException {
        return isServer() && !isActiveServer();
    }

    public boolean isActiveServer() throws RemoteException,
            InternalErrorException {
        if (serverService == null)
            return false;
        String standbyservers = serverService.getConfig(SEYCON_SERVER_STANDBY_PROPERTY); //$NON-NLS-1$
        if (standbyservers == null)
            return true;
        String split[] = standbyservers.split("[, ]+"); //$NON-NLS-1$
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals(getHostName()))
                return false;
        }
        return true;
    }

    public void setRole(String list) throws IOException {
        prop.setProperty(ROL_PROPERTY, list); //$NON-NLS-1$
        update();
    }

    public String getDbUser() {
        return prop.getProperty(USER_PROPERTY); //$NON-NLS-1$
    }

    public void setDbUser(String list) throws IOException {
        prop.setProperty(USER_PROPERTY, list); //$NON-NLS-1$
        update();
    }

    public Password getPassword() {
        String s = prop.getProperty(PASSWORD_PROPERTY); //$NON-NLS-1$
        if (s == null)
            return null;
        return Password.decode(s); 
    }

    public void setPassword(Password list) throws IOException {
        prop.setProperty(PASSWORD_PROPERTY, list.toString()); //$NON-NLS-1$
        update();
    }


    public String getBackupDbUser() {
        return prop.getProperty(BACKUPUSER_PROPERTY); //$NON-NLS-1$
    }

    public void setBackupDbUser(String list) throws IOException {
        prop.setProperty(BACKUPUSER_PROPERTY, list); //$NON-NLS-1$
        update();
    }

    public Password getBackupPassword() {
        String s = prop.getProperty(BACKUP_PASSWORD_PROPERTY); //$NON-NLS-1$
        if (s == null)
            return null;
        return Password.decode(s);
    }

    public void setBackupPassword(Password list) throws IOException {
        prop.setProperty(BACKUP_PASSWORD_PROPERTY, list.toString()); //$NON-NLS-1$
        update();
    }

    public String getBackupDB() {
        return prop.getProperty(BACKUPDB_PROPERTY); //$NON-NLS-1$
    }

    public void setBackupDB(String list) throws IOException {
        prop.setProperty(BACKUPDB_PROPERTY, list); //$NON-NLS-1$
        update();
    }


    public String getDB() {
        return prop.getProperty(DB_PROPERTY); //$NON-NLS-1$
    }

    public void setDB(String list) throws IOException {
        prop.setProperty(DB_PROPERTY, list); //$NON-NLS-1$
        update();
    }

    public Password getSSLKey() {
        String s = prop.getProperty("sslkey"); //$NON-NLS-1$
        if (s == null)
            return null;
        return Password.decode(s);
    }

    public void setSSLKey(Password list) throws IOException {
        prop.setProperty("sslkey", list.toString()); //$NON-NLS-1$
        update();
    }

    public String getHostName() {
        return prop.getProperty("hostname"); //$NON-NLS-1$
    }

    public void setHostName(String list) throws IOException {
        prop.setProperty("hostname", list); //$NON-NLS-1$
        update();
    }

	public String getLocalPort() throws RemoteException, InternalErrorException {
        if (isServer())
            return getPort(); //$NON-NLS-1$
        else
        {
        	String lp = prop.getProperty(LOCAL_PORT_PROPERTY);
        	if (lp != null)
        		return lp;
        	else
        		return getPort();
        }
    }

	public String port = null;
    public String getPort() throws RemoteException, InternalErrorException {
        if (isServer())
        {
        	if (port == null)
                port = serverService.getConfig("seycon.https.port"); //$NON-NLS-1$
        	return port;
        }
        else
            return prop.getProperty(PORT_PROPERTY); //$NON-NLS-1$
    }

    public void setPort(String list) throws IOException {
        prop.setProperty(PORT_PROPERTY, list); //$NON-NLS-1$
        update();
    }

    public boolean isRMIEnabled() {

        return "true".equals(prop.getProperty("rmi")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public boolean isBroadcastListen() {

        return "true".equals(prop.getProperty("broadcast_listen")); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public void setRMIEnabled(boolean enabled) throws IOException {
        prop.setProperty("rmi", Boolean.toString(enabled)); //$NON-NLS-1$
        update();
    }

    public boolean isMainServer() throws FileNotFoundException, IOException,
            InternalErrorException {
        return isServer() && isFirstServer();
    }

    public URLManager getURL() throws FileNotFoundException, IOException,
            InternalErrorException {
        if (isServer()) {
            String list = serverService.getConfig("seycon.server.list"); //$NON-NLS-1$
            String split[] = list.split("[, ]+"); //$NON-NLS-1$
            for (int i = 0; i < split.length; i++) {
                URLManager manager = new URLManager(split[i]);
                if (manager.getServerURL().getHost().equals(getHostName()))
                    return manager;
            }
            return null;
        } else {
            return new URLManager("https://" + getHostName() + ":" + getPort() //$NON-NLS-1$ //$NON-NLS-2$
                    + "/seycon/Agent"); //$NON-NLS-1$
        }
    }


    private static String FILE_SEPARATOR = File.separator;

    public static boolean hasKeystore() {
        String BASE_DIRECTORY = null;
        try {
            BASE_DIRECTORY = getConfig().getHomeDir().getAbsolutePath();
        } catch (Exception e) {
            return false;
        }
        File file = new File(BASE_DIRECTORY + FILE_SEPARATOR + "conf" //$NON-NLS-1$
                + FILE_SEPARATOR + "keystore.jks"); //$NON-NLS-1$
        return file.exists();
    }

    private boolean isFirstServer() throws FileNotFoundException, IOException,
            InternalErrorException {
        Config config = getConfig();
        String[] split = getSeyconServerHostList();
        String firstHost = split[0];
        if (firstHost.equals(config.getHostName()))
            return true;
        else
            return false;
    }

    public int numberOfDamemonThreads() throws RemoteException,
            InternalErrorException {
        String numberOfThreads;
        numberOfThreads = serverService.getConfig("seycon.jetty.threads"); //$NON-NLS-1$
        Integer numberOfThreadsInteger = Integer.decode(numberOfThreads);
        return numberOfThreadsInteger.intValue();
    }

    public String[] getSeyconServerHostList() throws InternalErrorException,
            IOException {
        String list = getRawSeyconServerList();
        if (list == null)
            return null;
        String[] split = list.split("[, ]+"); //$NON-NLS-1$
        String split2[] = new String[split.length];
        for (int i = 0; i < split.length; i++) {
            split2[i] = new URLManager(split[i]).getServerURL().getHost();
        }
        return split2;
    }

    public String getRawSeyconServerList() throws RemoteException,
            InternalErrorException {
        String list = ""; //$NON-NLS-1$
        if (isServer()) {
            list = serverService.getConfig("seycon.server.list"); //$NON-NLS-1$
        } else {
            list = prop.getProperty(SERVERLIST_PROPERTY); //$NON-NLS-1$
        }
        return list;
    }

    public boolean isAnyServer() throws FileNotFoundException, IOException,
            InternalErrorException {
        Config config = getConfig();
        String[] split = getSeyconServerHostList();
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals(config.getHostName()))
                return true;
        }
        return false;
    }

    public String getVersion() {
        try {
            InputStream in = Config.class
                	.getResourceAsStream("/META-INF/maven/com.soffid.iam.sync/syncserver/pom.properties"); //$NON-NLS-1$
            if (in == null)
                in = Config.class
                        .getResourceAsStream("/META-INF/maven/es.caib.seycon.ng/seycon-base/pom.properties"); //$NON-NLS-1$
            if (in == null)
                return "UNKNOWN"; //$NON-NLS-1$
            else {
                Properties p = new Properties();
                p.load(in);
                return p.getProperty("version"); //$NON-NLS-1$
            }
        } catch (IOException e) {
            return "UNKNOWN"; //$NON-NLS-1$
        }
    }

    public boolean isDebug() {
        return prop.getProperty("debug") != null; //$NON-NLS-1$
    }
    
    public boolean canUpdateComponent (String component) {
        if (!isUpdateEnabled())
            return false;
        
        String frozen = prop.getProperty("frozenComponents"); //$NON-NLS-1$
        if (frozen != null) {
            String[] frozenArray = frozen.split("[, ]+"); //$NON-NLS-1$
            for (int i = 0; i < frozenArray.length ; i++)
                if (frozenArray[i].equals(component))
                    return false;
        }
        return true;
    }
    
    public String getRequestId() {
        return prop.getProperty("requestId"); //$NON-NLS-1$
    }
    
    public void setRequestId(String value) throws IOException {
        if (value != null)
            prop.setProperty("requestId", value); //$NON-NLS-1$
        else
            prop.remove("requestId"); //$NON-NLS-1$
        update();
    }
}
