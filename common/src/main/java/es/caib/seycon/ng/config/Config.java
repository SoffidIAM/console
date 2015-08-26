package es.caib.seycon.ng.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.Properties;

import com.soffid.iam.remote.URLManager;

import es.caib.seycon.ng.comu.Password;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.sync.servei.ServerService;



public class Config 
{
    public static final String PORT_PROPERTY = com.soffid.iam.config.Config.PORT_PROPERTY;
	public static final String DB_PROPERTY = com.soffid.iam.config.Config.DB_PROPERTY;
	public static final String BACKUPDB_PROPERTY = com.soffid.iam.config.Config.BACKUPDB_PROPERTY;
	public static final String BACKUP_PASSWORD_PROPERTY = com.soffid.iam.config.Config.BACKUP_PASSWORD_PROPERTY;
	public static final String BACKUPUSER_PROPERTY = com.soffid.iam.config.Config.BACKUPUSER_PROPERTY;
	public static final String PASSWORD_PROPERTY = com.soffid.iam.config.Config.PASSWORD_PROPERTY;
	public static final String ROL_PROPERTY = com.soffid.iam.config.Config.ROL_PROPERTY;
	public static final String USER_PROPERTY = com.soffid.iam.config.Config.USER_PROPERTY;
	public static final String SEYCON_SERVER_STANDBY_PROPERTY = com.soffid.iam.config.Config.SEYCON_SERVER_STANDBY_PROPERTY;
	public static final String AUTOUPDATE_PROPERTY = com.soffid.iam.config.Config.AUTOUPDATE_PROPERTY;
	public static final String SERVERLIST_PROPERTY = com.soffid.iam.config.Config.SERVERLIST_PROPERTY;

	
	static Config theConfig;
	com.soffid.iam.config.Config actualConfig;
	
	public Config () throws FileNotFoundException, IOException
	{
    	actualConfig = com.soffid.iam.config.Config.getConfig();
	}
	
    public static Config getConfig() throws FileNotFoundException, IOException {
        if (theConfig == null) 
        {
            theConfig = new Config();
        }
        return theConfig;
    }

	public int hashCode()
	{
		return actualConfig.hashCode();
	}

	public void updateFromServer() throws IOException, InternalErrorException
	{
		actualConfig.updateFromServer();
	}

	public void mergeProperties(Properties newProp) throws IOException
	{
		actualConfig.mergeProperties(newProp);
	}

	public void reload() throws IOException, FileNotFoundException
	{
		actualConfig.reload();
	}

	public boolean equals(Object obj)
	{
		return actualConfig.equals(obj);
	}

	public File getHomeDir() throws IOException
	{
		return actualConfig.getHomeDir();
	}

	public File getLogFile() throws IOException
	{
		return actualConfig.getLogFile();
	}

	public File getLogDir() throws IOException
	{
		return actualConfig.getLogDir();
	}

	public String getServerList() throws RemoteException,
			InternalErrorException
	{
		return actualConfig.getServerList();
	}

	public String getJVMOptions() throws RemoteException,
			InternalErrorException
	{
		return actualConfig.getJVMOptions();
	}

	public void setServerList(String list) throws IOException
	{
		actualConfig.setServerList(list);
	}

	public String getRole()
	{
		return actualConfig.getRole();
	}

	public boolean isServer()
	{
		return actualConfig.isServer();
	}

	public boolean isAgent()
	{
		return actualConfig.isAgent();
	}

	public boolean isUpdateEnabled()
	{
		return actualConfig.isUpdateEnabled();
	}

	public boolean isStandbyServer() throws RemoteException,
			InternalErrorException
	{
		return actualConfig.isStandbyServer();
	}

	public boolean isActiveServer() throws RemoteException,
			InternalErrorException
	{
		return actualConfig.isActiveServer();
	}

	public void setRole(String list) throws IOException
	{
		actualConfig.setRole(list);
	}

	public String getDbUser()
	{
		return actualConfig.getDbUser();
	}

	public void setDbUser(String list) throws IOException
	{
		actualConfig.setDbUser(list);
	}


	public String toString()
	{
		return actualConfig.toString();
	}

	public String getBackupDbUser()
	{
		return actualConfig.getBackupDbUser();
	}

	public void setBackupDbUser(String list) throws IOException
	{
		actualConfig.setBackupDbUser(list);
	}

	public void setBackupPassword(Password list) throws IOException
	{
		actualConfig.setBackupPassword(list);
	}

	public String getBackupDB()
	{
		return actualConfig.getBackupDB();
	}

	public void setBackupDB(String list) throws IOException
	{
		actualConfig.setBackupDB(list);
	}

	public String getDB()
	{
		return actualConfig.getDB();
	}

	public void setDB(String list) throws IOException
	{
		actualConfig.setDB(list);
	}

	public void setSSLKey(Password list) throws IOException
	{
		actualConfig.setSSLKey(list);
	}

	public es.caib.seycon.ng.comu.Password getPassword()
	{
		return es.caib.seycon.ng.comu.Password.toPassword(actualConfig.getPassword());
	}

	public void setPassword(com.soffid.iam.api.Password list)
			throws IOException
	{
		actualConfig.setPassword(list);
	}

	public es.caib.seycon.ng.comu.Password getBackupPassword()
	{
		return es.caib.seycon.ng.comu.Password.toPassword(actualConfig.getBackupPassword());
	}

	public void setBackupPassword(com.soffid.iam.api.Password list)
			throws IOException
	{
		actualConfig.setBackupPassword(list);
	}

	public es.caib.seycon.ng.comu.Password getSSLKey()
	{
		return es.caib.seycon.ng.comu.Password.toPassword(actualConfig.getSSLKey());
	}

	public String getHostName()
	{
		return actualConfig.getHostName();
	}

	public void setHostName(String list) throws IOException
	{
		actualConfig.setHostName(list);
	}

	public String getPort() throws RemoteException, InternalErrorException
	{
		return actualConfig.getPort();
	}

	public void setPort(String list) throws IOException
	{
		actualConfig.setPort(list);
	}

	public boolean isRMIEnabled()
	{
		return actualConfig.isRMIEnabled();
	}

	public boolean isBroadcastListen()
	{
		return actualConfig.isBroadcastListen();
	}

	public void setRMIEnabled(boolean enabled) throws IOException
	{
		actualConfig.setRMIEnabled(enabled);
	}

	public boolean isMainServer() throws FileNotFoundException, IOException,
			InternalErrorException
	{
		return actualConfig.isMainServer();
	}

	public URLManager getURL() throws FileNotFoundException, IOException,
			InternalErrorException
	{
		return actualConfig.getURL();
	}

	public int numberOfDamemonThreads() throws RemoteException,
			InternalErrorException
	{
		return actualConfig.numberOfDamemonThreads();
	}

	public String[] getSeyconServerHostList() throws InternalErrorException,
			IOException
	{
		return actualConfig.getSeyconServerHostList();
	}

	public String getRawSeyconServerList() throws RemoteException,
			InternalErrorException
	{
		return actualConfig.getRawSeyconServerList();
	}

	public boolean isAnyServer() throws FileNotFoundException, IOException,
			InternalErrorException
	{
		return actualConfig.isAnyServer();
	}

	public String getVersion()
	{
		return actualConfig.getVersion();
	}

	public boolean isDebug()
	{
		return actualConfig.isDebug();
	}

	public boolean canUpdateComponent(String component)
	{
		return actualConfig.canUpdateComponent(component);
	}

	public String getRequestId()
	{
		return actualConfig.getRequestId();
	}

	public void setRequestId(String value) throws IOException
	{
		actualConfig.setRequestId(value);
	}

}
