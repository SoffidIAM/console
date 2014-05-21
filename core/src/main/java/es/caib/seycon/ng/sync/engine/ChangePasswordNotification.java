package es.caib.seycon.ng.sync.engine;

public class ChangePasswordNotification {
    String user;
    long   sessionId;
    String host;
    int    portNumber;
    String url;
    
    public String getUrl ()
	{
		return url;
	}
	public void setUrl (String url)
	{
		this.url = url;
	}
	public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public long getSessionId() {
        return sessionId;
    }
    public void setSessionId(long sessionId) {
        this.sessionId = sessionId;
    }
    public String getHost() {
        return host;
    }
    public void setHost(String host) {
        this.host = host;
    }
    public int getPortNumber() {
        return portNumber;
    }
    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }
    
}
