package com.soffid.iam.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.JumpServerGroup;
import com.soffid.iam.api.NewPamSession;
import com.soffid.iam.api.PamSession;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordValidation;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.JumpServerEntity;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.exception.InternalErrorException;

public class PamSessionServiceImpl extends PamSessionServiceBase {

	@Override
	protected JumpServerGroup handleCreate(JumpServerGroup jumpServerGroup) throws Exception {
		checkServerGroup(jumpServerGroup);
		JumpServerGroupEntity entity = getJumpServerGroupEntityDao().jumpServerGroupToEntity(jumpServerGroup);
		getJumpServerGroupEntityDao().create(entity);
		
		for (String s: jumpServerGroup.getJumpServers())
		{
			if ( s != null && !s.trim().isEmpty())
			{
				JumpServerEntity js = getJumpServerEntityDao().newJumpServerEntity();
				js.setUrl(s);
				js.setJumpServerGroup(entity);
				getJumpServerEntityDao().create(js);
				entity.getJumpServers().add(js);
			}
		}
		return getJumpServerGroupEntityDao().toJumpServerGroup(entity);
	}

	@Override
	protected JumpServerGroup handleUpdate(JumpServerGroup jumpServerGroup) throws Exception {
		checkServerGroup(jumpServerGroup);
		JumpServerGroupEntity entity = getJumpServerGroupEntityDao().jumpServerGroupToEntity(jumpServerGroup);
		getJumpServerGroupEntityDao().update(entity);
		
		LinkedList<String> list = new LinkedList<String>( jumpServerGroup.getJumpServers());
		
		for ( JumpServerEntity js:  new LinkedList<JumpServerEntity>(entity.getJumpServers()))
		{
			if ( ! list.contains(js.getUrl()))
			{
				entity.getJumpServers().remove(js);
				getJumpServerEntityDao().remove(js);
			}
			else
			{
				list.remove(js.getUrl());
			}
		}
		
		for (String s: list)
		{
			if ( s != null && !s.trim().isEmpty())
			{
				JumpServerEntity js = getJumpServerEntityDao().newJumpServerEntity();
				js.setUrl(s);
				js.setJumpServerGroup(entity);
				getJumpServerEntityDao().create(js);
				entity.getJumpServers().add(js);
			}
		}
		return getJumpServerGroupEntityDao().toJumpServerGroup(entity);
	}

	@Override
	protected void handleRemove(JumpServerGroup jumpServerGroup) throws Exception {
		JumpServerGroupEntity entity = getJumpServerGroupEntityDao().jumpServerGroupToEntity(jumpServerGroup);
		getJumpServerGroupEntityDao().update(entity);
		
		for ( JumpServerEntity js:  new LinkedList<JumpServerEntity>(entity.getJumpServers()))
		{
			entity.getJumpServers().remove(js);
			getJumpServerEntityDao().remove(js);
		}
		
		getJumpServerGroupEntityDao().remove(entity);
	}

	@Override
	protected NewPamSession handleCreateJumpServerSession(Account account) throws Exception {
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		JumpServerGroupEntity jumpServerGroup = entity.getJumpServerGroup();
		if (jumpServerGroup == null)
			throw new InternalErrorException("Cannot start session. Please, assign a jump server group to account "+account.getDescription());
		return createJumpServerSession (entity, jumpServerGroup, account.getLoginUrl());
	}
	
	@Override
	protected NewPamSession handleCreateJumpServerSession(Account account, String entryPointDescriptor)
			throws Exception {
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		
		Properties p = new Properties();
		p.load(new StringReader(entryPointDescriptor));
		String url = p.getProperty("url");
		if (url == null || url.trim().isEmpty())
			throw new InternalErrorException(String.format("The entry point descriptor does not have a value for url"));
		String serverGroup = p.getProperty("serverGroup");
		if (serverGroup == null || serverGroup.trim().isEmpty())
			throw new InternalErrorException(String.format("The entry point descriptor does not have a value for serverGroup"));
		JumpServerGroupEntity jumpServerGroup = getJumpServerGroupEntityDao().findByName(serverGroup);
		if (jumpServerGroup == null)
			throw new InternalErrorException(String.format("Cannot start session. Server group %s does not exist",serverGroup));
		return createJumpServerSession (entity, jumpServerGroup, url);
	}

	private NewPamSession createJumpServerSession (AccountEntity entity, JumpServerGroupEntity jumpServerGroup, String targetUrl) throws InternalErrorException, MalformedURLException, JSONException
	{
		Password password = getAccountService().queryAccountPasswordBypassPolicy(entity.getId(), AccountAccessLevelEnum.ACCESS_USER);
		if (password == null)
			throw new InternalErrorException("Cannot retrieve password for account "+entity.getDescription());
		
		Account account = getAccountEntityDao().toAccount(entity);
		if ( account.getType() != AccountType.IGNORED) {
			PasswordValidation status = getAccountService().checkPasswordSynchronizationStatus(account);
			if (! PasswordValidation.PASSWORD_GOOD.equals(status))
				throw new InternalErrorException("The password stored is not accepted by the target system");
		}
		URL url2 = new URL(jumpServerGroup.getStoreUrl());
		String base = url2.getProtocol()+"://"+url2.getHost()+
				(url2.getPort() == -1 ? "": ":"+url2.getPort());
		String storeUrl = base+"/store/session/create";
		Response response;
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		HashMap<String, Object> secrets = new HashMap<String, Object>();
		data.put("serverUrl", targetUrl);
		data.put("user", Security.getCurrentUser());
		data.put("secrets", secrets);
		secrets.put("accountName", entity.getLoginName() == null? entity.getName(): entity.getLoginName());
		secrets.put("password", password.getPassword());

		try {
			response = 
					WebClient
					.create(storeUrl, jumpServerGroup.getStoreUserName(), 
							Password.decode(jumpServerGroup.getPassword()).getPassword(), null)
					.type(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.post(data);
					
		} catch (Exception e) {
			throw new InternalErrorException ("Error connecting to "+storeUrl+": "+e.getMessage() );
		}
		
		if (response.getStatus() != 200)
			throw new InternalErrorException("Error connecting to " + base + ": " + "HTTP/"
					+ response.getStatusInfo().getStatusCode() + " " + response.getStatusInfo().getReasonPhrase());

		HashMap map = response.readEntity(HashMap.class);
		if (Boolean.TRUE.equals(map.get("error"))) {
			throw new InternalErrorException("Error generating session: " + map.get("cause"));
		}
		String sessionKey = (String) map.get("sessionKey");
		if (sessionKey == null)
			throw new InternalErrorException("PAM Store did not return a session key");

		String selected = selectJumpServer(jumpServerGroup);

		if (selected == null)
			throw new InternalErrorException("There is no jump server available");

		URL url = new URL(selected);
		
		NewPamSession nps = new NewPamSession();
		nps.setJumpServerGroup(jumpServerGroup.getName());
		nps.setSessionId(sessionKey);
		nps.setUrl(new URL (url.getProtocol() + "://" + url.getHost() + (url.getPort() == -1 ? "" : ":" + url.getPort())
				+ "/launch/start?sessionId=" + URLEncoder.encode(sessionKey)));
		
		Audit audit = new Audit();
		audit.setAuthor(Security.getCurrentAccount());
		audit.setAction("L");
		audit.setObject("PAM");
		audit.setHost(url.getHost());
		audit.setAccount(entity.getName());
		audit.setDatabase(entity.getSystem().getName());
		audit.setUser(entity.getLoginName());
		audit.setPamSessionId((String) map.get("sessionId"));
		audit.setJumpServerGroup(jumpServerGroup.getName());
		AuditEntity auditEntity = getAuditEntityDao().auditToEntity(audit);
		getAuditEntityDao().create(auditEntity);

		return nps;
		
	}

	public String selectJumpServer(JumpServerGroupEntity jumpServerGroup)
			throws MalformedURLException, InternalErrorException, JSONException {
		
		if ( jumpServerGroup.getJumpServers().size() == 0)
			return null;
		
		if ( jumpServerGroup.getJumpServers().size() == 1)
			return jumpServerGroup.getJumpServers().iterator().next().getUrl();
		
		int selectedSessions = Integer.MAX_VALUE;
		String selected = null;
		JumpServerEntity[] jumpServers = jumpServerGroup.getJumpServers().toArray(new JumpServerEntity[0]);
		int start = new SecureRandom().nextInt(jumpServers.length);
		for (int i = 0; i < jumpServers.length; i++) {
			String next = jumpServers[(i + start) % jumpServers.length].getUrl();
			try {
				Integer used = getUsedThreads(next);
				if (used != null && used.intValue() < selectedSessions) {
					selected = next;
					selectedSessions = used.intValue();
				}
			} catch (ProcessingException e) {
				// Ignore
			}
		}
		return selected;
	}

	@Override
	protected List<JumpServerGroup> handleFindJumpServerGroups() throws Exception {
		if (Security.isUserInRole("jumpServer:query"))
			return getJumpServerGroupEntityDao().toJumpServerGroupList(
				getJumpServerGroupEntityDao().loadAll());
		else
			return new LinkedList<JumpServerGroup>();
	}
	
	void checkServerGroup (JumpServerGroup group) throws MalformedURLException, InternalErrorException, JSONException
	{
		checkStore (group.getStoreUrl(), group.getStoreUserName(), group.getPassword());
		for ( String server: group.getJumpServers())
		{
			checkJumpServer(server);
		}
	}

	private void checkStore(String storeUrl, String storeUserName, String password) throws MalformedURLException, InternalErrorException {
		try {
			URL url2 = new URL(storeUrl);
			String base = url2.getProtocol()+"://"+url2.getHost()+
					(url2.getPort() == -1 ? "": ":"+url2.getPort());
			Response response = 
					WebClient
					.create(base+"/store/check", storeUserName, Password.decode(password).getPassword(), null)
					.accept(MediaType.APPLICATION_JSON)
					.get();
			if (response.hasEntity())
				response.getEntity();
					
			if (response.getStatus() != 200)
				throw new InternalErrorException ("Error connecting to "+base+": "
						+ "HTTP/"+response.getStatusInfo().getStatusCode()+" "+response.getStatusInfo().getReasonPhrase());
		} catch (ProcessingException e) {
			throw new InternalErrorException ("Error connecting to "+storeUrl+": "+e.getMessage() );
		}
	}

	private void checkJumpServer(String server) throws MalformedURLException, InternalErrorException, JSONException {
		try {
			Integer size = getUsedThreads(server);
			if ( size == null )
				throw new InternalErrorException("Error checking connection with "+server+". Status servlet failed");
		} catch (ProcessingException e) {
			throw new InternalErrorException ("Error connecting to "+server+": "+e.getMessage() );
		}
	}

	private Integer getUsedThreads(String server) throws MalformedURLException, InternalErrorException, JSONException {
		URL url2 = new URL(server);
		String base = url2.getProtocol()+"://"+url2.getHost()+
				(url2.getPort() == -1 ? "": ":"+url2.getPort());
		Response response = 
				WebClient
				.create(base+"/launch/status")
				.accept(MediaType.APPLICATION_JSON)
				.get();
		if (response.getStatus() != 200)
			throw new InternalErrorException ("Error connecting to "+base+": "
					+ "HTTP/"+response.getStatusInfo().getStatusCode()+" "+response.getStatusInfo().getReasonPhrase());
		JSONObject result  = new JSONObject( new JSONTokener( response.readEntity( String.class   ) ) );
		return result.optInt("sessions");
	}

	@Override
	protected PamSession handleFindSession(String serverGroup, String sessionId) throws Exception {
		JumpServerGroupEntity jumpServerGroup = getJumpServerGroupEntityDao().findByName(serverGroup);
		if (jumpServerGroup == null)
			throw new InternalErrorException(String.format("Server group %s does not exist ", serverGroup));
		
		URL url2 = new URL(jumpServerGroup.getStoreUrl());
		String base = url2.getProtocol()+"://"+url2.getHost()+
				(url2.getPort() == -1 ? "": ":"+url2.getPort());
		String storeUrl = base+"/store/session/query?sessionId=" + URLEncoder.encode(sessionId, "UTF-8");
		Response response;
		
		try {
			response = 
					WebClient
					.create(storeUrl, jumpServerGroup.getStoreUserName(), 
							Password.decode(jumpServerGroup.getPassword()).getPassword(), null)
					.accept(MediaType.APPLICATION_JSON)
					.get();
					
		} catch (Exception e) {
			throw new InternalErrorException ("Error connecting to "+storeUrl+": "+e.getMessage() );
		}
		
		if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode())
			return null;

		if (response.getStatus() != 200)
			throw new InternalErrorException("Error connecting to " + base + ": " + "HTTP/"
					+ response.getStatusInfo().getStatusCode() + " " + response.getStatusInfo().getReasonPhrase());

		PamSession r = response.readEntity(PamSession.class);
		
		r.setJumpServerGroup(serverGroup);

		return r;
	}

	@Override
	protected void handleGenerateKeystrokes(PamSession session, OutputStream stream) throws Exception {
		JumpServerGroupEntity serverGroup = getJumpServerGroupEntityDao().findByName(session.getJumpServerGroup());
		URL url = new URL(serverGroup.getStoreUrl());
		url = new URL ( url.getProtocol()+"://"+url.getHost()+(url.getPort() > 0? ":"+url.getPort(): "") + "/store/downloadKeystrokes/"+
				session.getPath());
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setDoInput(true);
		conn.setDoOutput(false);
		String auth = serverGroup.getStoreUserName()+":"+
				Password.decode(serverGroup.getPassword()).getPassword();
		conn.addRequestProperty("Authorization", "Basic "+Base64.encodeBase64String(auth.getBytes("UTF-8")));
		
		conn.connect();
		
		if ( conn.getResponseCode() != 200 && conn.getResponseCode() != HttpServletResponse.SC_PARTIAL_CONTENT)
		{
			throw new InternalErrorException ("Unexpected error received from store server: HTTP/"+conn.getResponseCode());
		} else {
			InputStream in = conn.getInputStream();
			for (int i = in.read(); i >= 0; i = in.read())
				stream.write(i);
			in.close();
		}
		
		conn.disconnect();
	}

	@Override
	public void handleGenerateVideo(PamSession session, long chapter, OutputStream stream, long start, long end) throws InternalErrorException, IOException {
		JumpServerGroupEntity serverGroup = getJumpServerGroupEntityDao().findByName(session.getJumpServerGroup());
		URL url = new URL(serverGroup.getStoreUrl());
		url = new URL ( url.getProtocol()+"://"+url.getHost()+(url.getPort() > 0? ":"+url.getPort(): "") + "/store/downloadVideo/"+
				session.getPath()+"/"+chapter);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.addRequestProperty("Range", "bytes="+start+"-"+end);
		conn.setDoInput(true);
		conn.setDoOutput(false);
		String auth = serverGroup.getStoreUserName()+":"+
				Password.decode(serverGroup.getPassword()).getPassword();
		conn.addRequestProperty("Authorization", "Basic "+Base64.encodeBase64String(auth.getBytes("UTF-8")));
		
		conn.connect();
		
		if ( conn.getResponseCode() != 200 && conn.getResponseCode() != HttpServletResponse.SC_PARTIAL_CONTENT)
		{
			throw new InternalErrorException ("Unexpected error received from store server: HTTP/"+conn.getResponseCode());
		} else {
			InputStream in = conn.getInputStream();
			for (int i = in.read(); i >= 0; i = in.read())
				stream.write(i);
			in.close();
		}
		
		conn.disconnect();
	}

	@Override
	public long handleGetVideoSize(PamSession session, long chapter) throws InternalErrorException, IOException {
		JumpServerGroupEntity serverGroup = getJumpServerGroupEntityDao().findByName(session.getJumpServerGroup());
		URL url = new URL(serverGroup.getStoreUrl());
		url = new URL ( url.getProtocol()+"://"+url.getHost()+(url.getPort() > 0? ":"+url.getPort(): "") + "/store/downloadVideo/"+
				session.getPath()+"/"+chapter);
		
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("HEAD");
		conn.setDoInput(true);
		conn.setDoOutput(false);
		String auth = serverGroup.getStoreUserName()+":"+
				Password.decode(serverGroup.getPassword()).getPassword();
		conn.addRequestProperty("Authorization", "Basic "+Base64.encodeBase64String(auth.getBytes("UTF-8")));
		
		conn.connect();
		
		if ( conn.getResponseCode() != 200 )
		{
			throw new InternalErrorException ("Unexpected error received from store server: HTTP/"+conn.getResponseCode());
		}
		
		long size = conn.getContentLengthLong();
		conn.disconnect();
		return size;
	}

	@Override
	public Integer handleGetActiveSessions(String server) throws InternalErrorException, MalformedURLException, JSONException {
		try {
			return getUsedThreads(server);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Long handleGetConsoleFreeSpace(String jumpServerGroup) throws Exception {
		JumpServerGroupEntity sg = getJumpServerGroupEntityDao().findByName(jumpServerGroup);
		if (sg == null)
			return null;
		
		try {
			URL url2 = new URL(sg.getStoreUrl());
			String base = url2.getProtocol()+"://"+url2.getHost()+
					(url2.getPort() == -1 ? "": ":"+url2.getPort());
			Response response = 
					WebClient
					.create(base+"/store/check", sg.getStoreUserName(), Password.decode(sg.getPassword()).getPassword(), null)
					.accept(MediaType.APPLICATION_JSON)
					.get();
			if (response.hasEntity())
				response.getEntity();
					
			if (response.getStatus() != 200)
				return null;
			
			JSONObject result  = new JSONObject( new JSONTokener( response.readEntity( String.class   ) ) );
			return result.optLong("freeSpace");
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	protected Long handleGetConsoleUsedSpace(String jumpServerGroup) throws Exception {
		JumpServerGroupEntity sg = getJumpServerGroupEntityDao().findByName(jumpServerGroup);
		if (sg == null)
			return null;
		
		try {
			URL url2 = new URL(sg.getStoreUrl());
			String base = url2.getProtocol()+"://"+url2.getHost()+
					(url2.getPort() == -1 ? "": ":"+url2.getPort());
			Response response = 
					WebClient
					.create(base+"/store/check", sg.getStoreUserName(), Password.decode(sg.getPassword()).getPassword(), null)
					.accept(MediaType.APPLICATION_JSON)
					.get();
			if (response.hasEntity())
				response.getEntity();
					
			if (response.getStatus() != 200)
				return null;
			
			JSONObject result  = new JSONObject( new JSONTokener( response.readEntity( String.class   ) ) );
			return result.optLong("usedSpace");
		} catch (Exception e) {
			return null;
		}
	}

}
