package com.soffid.iam.service;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.JumpServerGroup;
import com.soffid.iam.api.NewPamSession;
import com.soffid.iam.api.PamSession;
import com.soffid.iam.api.Password;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.JumpServerEntity;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.iam.utils.Security;

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
		
		Password password = getAccountService().queryAccountPasswordBypassPolicy(account.getId());
		if (password == null)
			throw new InternalErrorException("Cannot retrieve password for account "+account.getDescription());
		URL url2 = new URL(jumpServerGroup.getStoreUrl());
		String base = url2.getProtocol()+"://"+url2.getHost()+
				(url2.getPort() == -1 ? "": ":"+url2.getPort());
		String storeUrl = base+"/store/session/create";
		Response response;
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		HashMap<String, Object> secrets = new HashMap<String, Object>();
		data.put("serverUrl", entity.getLoginUrl());
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

		if (selected == null)
			throw new InternalErrorException("There is no jump server available");

		URL url = new URL(selected);
		
		NewPamSession nps = new NewPamSession();
		nps.setJumpServerGroup(jumpServerGroup.getName());
		nps.setSessionId(sessionKey);
		nps.setUrl(new URL (url.getProtocol() + "://" + url.getHost() + (url.getPort() == -1 ? "" : ":" + url.getPort())
				+ "/launch/start?sessionId=" + URLEncoder.encode(sessionKey)));
		return nps;
		
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
	protected void handleGenerateKeystrokes(PamSession session, long start, long end, OutputStream stream)
			throws Exception {
	}

	@Override
	protected void handleGenerateVideo(PamSession session, long start, long end, OutputStream stream) throws Exception {
	}

}
