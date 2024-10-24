package com.soffid.iam.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.cxf.transport.http.HTTPConduit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.AccountStatus;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.JumpServerGroup;
import com.soffid.iam.api.NewPamSession;
import com.soffid.iam.api.PamSession;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.PasswordValidation;
import com.soffid.iam.api.Server;
import com.soffid.iam.api.Session;
import com.soffid.iam.interp.Evaluator;
import com.soffid.iam.model.AccessLogEntity;
import com.soffid.iam.model.AccountEntity;
import com.soffid.iam.model.AuditEntity;
import com.soffid.iam.model.HostEntity;
import com.soffid.iam.model.JumpServerEntity;
import com.soffid.iam.model.JumpServerGroupEntity;
import com.soffid.iam.model.PamPolicyEntity;
import com.soffid.iam.model.PamPolicyJITPermissionEntity;
import com.soffid.iam.model.ServiceEntity;
import com.soffid.iam.model.SessionEntity;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.comu.AccountAccessLevelEnum;
import es.caib.seycon.ng.comu.AccountType;
import es.caib.seycon.ng.comu.ServerType;
import es.caib.seycon.ng.comu.TipusSessio;
import es.caib.seycon.ng.exception.InternalErrorException;

public class PamSessionServiceImpl extends PamSessionServiceBase {
	Log log = LogFactory.getLog(getClass());
	
	private static String PROPERTY_TIMEOUT = "soffid.pam.search.recordings.timeout";

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
		return handleCreateCustomJumpServerSession(account, null, null, null);
	}
	
	@Override
	protected NewPamSession handleCreateJumpServerSession(Account account, String entryPointDescriptor)
			throws Exception {
		return handleCreateJumpServerSession(account, entryPointDescriptor, null);
	}
	
	@Override
	protected NewPamSession handleCreateJumpServerSession(Account account, String entryPointDescriptor, 
			String pamPolicy)
			throws Exception {
		AccountEntity entity = getAccountEntityDao().load(account.getId());

		getPamSecurityHandlerService().checkPermission(entity, "launch");
		
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

		if (pamPolicy == null) {
			pamPolicy = findPolicy(account, url);
		}
		if ( pamPolicy == null && entity.getFolder() != null && entity.getFolder().getPamPolicy() != null )
			pamPolicy = entity.getFolder().getPamPolicy().getName();
		return createJumpServerSession (entity, jumpServerGroup, url, pamPolicy, Security.getClientIp(), TipusSessio.PAM, null);
	}

	@Override
	protected NewPamSession handleCreateManualJumpServerSession(String accountName, Password password, String entryPointDescriptor, 
			String pamPolicy)
			throws Exception {
		String currentAccount = Security.getCurrentAccount();
		String currentSystem = getDispatcherService().findSoffidDispatcher().getName();
		AccountEntity entity = getAccountEntityDao().findByNameAndSystem(currentAccount, currentSystem);

		getPamSecurityHandlerService().checkPermission(entity, "launch");
		
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

		if (pamPolicy == null) {
			pamPolicy = findPolicy(getAccountEntityDao().toAccount(entity), url);
		}
		return createJumpServerSession (entity, jumpServerGroup, url, pamPolicy, Security.getClientIp(), TipusSessio.PAM, null,
				accountName, password );
	}

	private String findPolicy(Account account, String entryPointDescriptor) {
		List<PamPolicyEntity> l = getPamPolicyEntityDao().loadAll();
		
		l.sort(new Comparator<PamPolicyEntity>() {
			@Override
			public int compare(PamPolicyEntity o1, PamPolicyEntity o2) {
				int i1 = o1.getPriority() == null ? Integer.MAX_VALUE: o1.getPriority().intValue();
				int i2 = o2.getPriority() == null ? Integer.MAX_VALUE: o2.getPriority().intValue();
				return i1 - i2;
			}
		});
		
		Map<String, Object> vars = new HashMap<>();
		vars.put("account", account);
		vars.put("url", entryPointDescriptor);
		for (PamPolicyEntity rule: l) {
			if (rule.getExpression() != null && !rule.getExpression().isEmpty()) {
				try {
					if (Boolean.TRUE.equals(Evaluator.instance().evaluate(rule.getExpression(), 
							vars, "Policy "+rule.getName()))) {
						return rule.getName();
					}
				} catch (Exception e) {
					log.warn("Error evaluating rule "+rule.getExpression(), e);
				}
			}
		}
		return null;
	}

	private NewPamSession createJumpServerSession (AccountEntity entity, JumpServerGroupEntity jumpServerGroup, 
			String targetUrl, String pamPolicy, 
			String sourceIp, TipusSessio type, String info) 
			throws InternalErrorException, MalformedURLException, JSONException, UnsupportedEncodingException, URISyntaxException, UnknownHostException
	{
		return createJumpServerSession(entity, jumpServerGroup, targetUrl, pamPolicy, sourceIp, type, info, null, null);
	}
	
	private NewPamSession createJumpServerSession (AccountEntity entity, JumpServerGroupEntity jumpServerGroup, 
			String targetUrl, String pamPolicy, 
			String sourceIp, TipusSessio type, String info,
			String manualAccount, Password manualPassword) 
			throws InternalErrorException, MalformedURLException, JSONException, UnsupportedEncodingException, URISyntaxException, UnknownHostException
	{
		Password password = getAccountService().queryAccountPasswordBypassPolicy(entity.getId(), AccountAccessLevelEnum.ACCESS_USER);
		Password sshKey = null;
		try {
			sshKey = getAccountService().queryAccountSshKeyBypassPolicy(entity.getId(), AccountAccessLevelEnum.ACCESS_USER);
		} catch (Exception e) {
			// Ignore. Posible syncserver not updated yet
		}
		if (password == null && sshKey == null && manualPassword == null)
			throw new InternalErrorException("Cannot retrieve password for account "+entity.getDescription());

		if (entity.getStatus() == AccountStatus.LOCKED)
			throw new InternalErrorException("Account is locked");
		else if (entity.isDisabled())
			throw new InternalErrorException("Account is disabled");
		String loginName;
		if (manualAccount == null) {
			loginName = entity.getLoginName() == null? entity.getName(): entity.getLoginName();
			if (!loginName.contains("\\") && targetUrl.startsWith("rdp:")) {
				for (Entry<String, String> entry: getDispatcherService().findActiveDirectoryDomains().entrySet() ) {
					if (entry.getValue().equals(entity.getSystem().getName())) {
						loginName = entry.getKey()+"\\"+loginName;
						break;
					}
				}
			}
			Account account = getAccountEntityDao().toAccount(entity);
			if ( account.getType() != AccountType.IGNORED && sshKey == null) {
				PasswordValidation status = getAccountService().checkPasswordSynchronizationStatus(account);
				if (status != null && ! PasswordValidation.PASSWORD_GOOD.equals(status))
					throw new InternalErrorException("The password stored is not accepted by the target system");
			}
		} else {
			loginName = manualAccount;
		}
			
		URL url2 = new URL(jumpServerGroup.getStoreUrl());
		String base = url2.getProtocol()+"://"+url2.getHost()+
				(url2.getPort() == -1 ? "": ":"+url2.getPort());
		String storeUrl = base+"/store/session/create";
		Response response;
		
		HashMap<String, Object> data = new HashMap<String, Object>();
		HashMap<String, Object> secrets = new HashMap<String, Object>();
		data.put("serverUrl", targetUrl);
		data.put("policyName", pamPolicy);
		data.put("user", Security.getCurrentUser());
		data.put("secrets", secrets);
		List<String> syncServers = new LinkedList<>();
		for (Server ss: getDispatcherService().findTenantServers()) {
			if (ss.getType() == ServerType.MASTERSERVER)
				syncServers.add(ss.getUrl());
		}
		
		data.put("syncServers", syncServers);
		secrets.put("accountName", loginName);
		if (manualPassword != null)
			secrets.put("password", manualPassword.getPassword());
		else {
			if (password != null) secrets.put("password", password.getPassword());
			if (sshKey != null) secrets.put("sshKey", sshKey.getPassword());
		}

		try {
			response = 
					WebClient
					.create(storeUrl, jumpServerGroup.getStoreUserName(), 
							Password.decode(jumpServerGroup.getPassword()).getPassword(), null)
					.type(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.post( JSONObject.wrap(data).toString());
					
		} catch (Exception e) {
			throw new InternalErrorException ("Error connecting to "+storeUrl+": "+e.getMessage() );
		}
		
		if (response.getStatus() != 200)
			throw new InternalErrorException("Error connecting to " + base + ": " + "HTTP/"
					+ response.getStatusInfo().getStatusCode() + " " + response.getStatusInfo().getReasonPhrase());

		JSONObject map =  new JSONObject(response.readEntity(String.class));
		if (Boolean.TRUE.equals(map.get("error"))) {
			throw new InternalErrorException("Error generating session: " + map.optString("cause"));
		}
		String sessionKey = map.optString("sessionKey");
		if (sessionKey == null)
			throw new InternalErrorException("PAM Store did not return a session key");

		NewPamSession nps = new NewPamSession();
		nps.setJumpServerGroup(jumpServerGroup.getName());
		nps.setSessionId(sessionKey);
		String jumpServerUrlBase = null;
		if (type == TipusSessio.PAM) {
			String selected = selectJumpServer(jumpServerGroup);
	
			if (selected == null)
				throw new InternalErrorException("There is no jump server available");
	
			URL url = new URL(selected);
		
			jumpServerUrlBase = url.getProtocol() + "://" + url.getHost() + (url.getPort() == -1 ? "" : ":" + url.getPort());
			nps.setUrl(new URL (jumpServerUrlBase + "/launch/start?sessionId=" + URLEncoder.encode(sessionKey, "UTF-8")));
		}
		
		Audit audit = new Audit();
		audit.setAuthor(Security.getCurrentAccount());
		audit.setAction("L");
		audit.setObject("PAM");
		audit.setHost(targetUrl);
		audit.setAccount(entity.getName());
		audit.setDatabase(entity.getSystem().getName());
		audit.setUser(entity.getLoginName());
		audit.setPamSessionId((String) map.get("sessionId"));
		audit.setJumpServerGroup(jumpServerGroup.getName());
		audit.setComment(info);
		audit.setSourceIp(sourceIp);
		AuditEntity auditEntity = getAuditEntityDao().auditToEntity(audit);
		getAuditEntityDao().create(auditEntity);

		List<String> permissions = applyTemporaryPermissions(pamPolicy, targetUrl, entity);
		
		if (Security.getCurrentUser() != null) {
			AccessLogEntity log = getAccessLogEntityDao().newAccessLogEntity();
			log.setAccessType("L");
			log.setAccountName(loginName);
			final String host = new URI(targetUrl).getHost();
			log.setServer(findHost(host));
			InetAddress addr = InetAddress.getByName(host);
			log.setHostAddress(addr.getHostAddress());
			log.setHostName(addr.getHostName());
			log.setJumpServerGroup(jumpServerGroup.getName());
			log.setProtocol(findPamProtocol(type));
			String i;
			i = targetUrl;
			if (info != null)
				i += " "+info;
			log.setInformation(i);
			log.setStartDate(new Date());
			log.setClientAddress(sourceIp);
			log.setSystem(entity.getSystem().getName());
			log.setUser( getUserEntityDao().findByUserName(Security.getCurrentUser()) );
			log.setSessionId((String) map.get("sessionId"));
			getAccessLogEntityDao().create(log);
			
			SessionEntity session = getSessionEntityDao().newSessionEntity();
			session.setAuthenticationMethod("P");
			session.setHost(log.getServer());
			session.setHostName(log.getHostName());
			session.setHostAddress(log.getHostAddress());
			session.setClientAddress(sourceIp);
			session.setClientHost(findHost(sourceIp));
			session.setClientHostName(log.getClientHostName());
			session.setKeepAliveDate(new Date());
			session.setKey(sessionKey);
			session.setLoginLogInfo(log);
			session.setStartDate(new Date());
			session.setType( type == null ? TipusSessio.PAM: type);
			session.setUser(log.getUser());
			session.setAccount(entity);
			session.setWebHandler(base+"/store/session/check-alive");
			if (jumpServerUrlBase != null)
				session.setMonitorUrl(jumpServerUrlBase  + "/launch/connect?sessionId=" + URLEncoder.encode(sessionKey,"UTF-8"));
			session.setJustInTimePermissionToRemove(serialize(permissions));
			getSessionEntityDao().create(session);
		}
		return nps;
	}

	private String serialize(List<String> permissions) {
		if (permissions == null || permissions.isEmpty()) return null;
		StringBuffer sb = new StringBuffer();
		for (String l: permissions) {
			sb.append(URLEncoder.encode(l, StandardCharsets.UTF_8)).append("&");
		}
		return sb.toString();
	}

	private List<String> applyTemporaryPermissions(String pamPolicy, String targetUrl, AccountEntity entity) throws InternalErrorException {
		if (pamPolicy == null || targetUrl == null)
			return null;
		PamPolicyEntity pp = getPamPolicyEntityDao().findByName(pamPolicy);
		List<String> l = new LinkedList<>();
		for (PamPolicyJITPermissionEntity jit: pp.getJustInTimePermissions()) {
			l.add(jit.getName());
		}
		if (l.isEmpty())
			return null;
		String agentClass = null;
		try {
			URI uri;
			uri = new URI(targetUrl);
			if (uri.getHost() == null || uri.getHost().isBlank())
				return new LinkedList<>();
			else
				return getDispatcherService().assignTemporaryPermissions(uri.getHost(),  
					entity.getName(), entity.getSystem().getName(), l);
		} catch (URISyntaxException e) {
			return null;
		}
		
	}

	private HostEntity findHost(String hostName) throws InternalErrorException, UnknownHostException {
		HostEntity host = getHostEntityDao().findByName(hostName);
		if (host != null)
			return host;
		
		InetAddress addr = InetAddress.getByName(hostName);
		
		for (HostEntity host2: getHostEntityDao().findByIP(addr.getHostAddress())) {
			if ( ! Boolean.TRUE.equals( host2.getDeleted()) )
				return host2;
		};
		
		return null;
	}

	private ServiceEntity findPamProtocol(TipusSessio type) {
		String t = type == TipusSessio.PAMRDP ? "PAM_SSH": type == TipusSessio.PAMRDP ? "PAM_RDP": "PAM";
		ServiceEntity e = getServiceEntityDao().findByName(t);
		if (e == null) {
			e = getServiceEntityDao().newServiceEntity();
			e.setDescription("Soffid "+type+" Service");
			e.setName(t);
			getServiceEntityDao().create(e);
		}
		return e;
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
				log.info("Error querying jump server "+next, e);
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

		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssZ");
		String s = response.readEntity(String.class);
		JSONObject o = new JSONObject(s);
		PamSession r = new PamSession();
		r.setAccountName(o.optString("accountName"));
		r.setChapters(new LinkedList<>());
		JSONArray ch = o.optJSONArray("chapters");
		for (int i = 0; i < ch.length(); i++)
			r.getChapters().add(ch.getLong(i));
		r.setId(o.optString("id"));
		r.setPath(o.optString("path"));
		if (o.has("serverEnd"))
			r.setServerEnd(df.parse(o.optString("serverEnd")));
		if (o.has("serverStart"))
			r.setServerStart(df.parse(o.optString("serverStart")));
		r.setServerUrl(o.optString("serverUrl"));
		r.setUser(o.optString("user"));
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
		conn.addRequestProperty("Authorization", "Basic "+Base64.getEncoder().encodeToString(auth.getBytes("UTF-8")));
		
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
		conn.addRequestProperty("Authorization", "Basic "+Base64.getEncoder().encodeToString(auth.getBytes("UTF-8")));
		
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
		conn.addRequestProperty("Authorization", "Basic "+Base64.getEncoder().encodeToString(auth.getBytes("UTF-8")));
		
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

	@Override
	protected boolean handleCheckJumpServerSession(Session sessio) throws Exception {
    	URL url = new URL (sessio.getUrl()+"?sessionId="+ URLEncoder.encode(sessio.getKey(), "UTF-8"));

    	JumpServerGroupEntity selected = null;
    	for ( JumpServerGroupEntity js: getJumpServerGroupEntityDao().loadAll()) {
    		URL url2 = new URL(js.getStoreUrl());
    		if (url2.getHost().equals(url.getHost()) &&
    				url2.getPort() == url.getPort()) {
    			selected = js;
    			break;
    		}
    	}
    	
    	if (selected == null)
    		return false;
    	
		Response response;
		try {
			response = 
					WebClient
					.create(url.toString(), selected.getStoreUserName(), 
							Password.decode(selected.getPassword()).getPassword(), null)
					.accept(MediaType.APPLICATION_JSON)
					.get();
			String s = response.readEntity(String.class);
		} catch (Exception e) {
			throw new InternalErrorException ("Error connecting to "+selected.getStoreUrl()+": "+e.getMessage() );
		}
		
		if (response.getStatus() == HttpServletResponse.SC_NOT_FOUND)
			return false;
		else if (response.getStatus() == HttpServletResponse.SC_OK)
			return true;
		else
			throw new InternalErrorException("Error connecting to " + selected.getStoreUrl() + ": " + "HTTP/"
					+ response.getStatusInfo().getStatusCode() + " " + response.getStatusInfo().getReasonPhrase());
	}

	@Override
	protected List<PamSession> handleSearch(String jumpServerGroup, String url, String text, String user, Date since,
			Date until) throws Exception {
		return handleSearch(jumpServerGroup, url, text, null, user, since, until);
	}
	
	@Override
	protected List<PamSession> handleSearch(String jumpServerGroup, String url, String text, String screenshots, String user, Date since,
			Date until) throws Exception {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmssZ");
		List<PamSession> list = new LinkedList<>();
		for (JumpServerGroupEntity jsg: getJumpServerGroupEntityDao().loadAll()) {
			if ( jumpServerGroup == null || jumpServerGroup.trim().isEmpty() || jsg.getName().equals(jumpServerGroup)) {
				URL url2 = new URL(jsg.getStoreUrl());
				String base = url2.getProtocol()+"://"+url2.getHost()+
						(url2.getPort() == -1 ? "": ":"+url2.getPort());
				String storeUrl = base+"/store/search?";
				if (url != null)
					storeUrl += "url="+URLEncoder.encode(url, "UTF-8")+"&";
				if (text != null)
					storeUrl += "keystrokes="+URLEncoder.encode(text, "UTF-8")+"&";
				if (screenshots != null)
					storeUrl += "screenshots="+URLEncoder.encode(screenshots, "UTF-8")+"&";
				else
					storeUrl += "screenshots=&";
				if (user != null)
					storeUrl += "user="+URLEncoder.encode(user, "UTF-8")+"&";
				if (since != null)
					storeUrl += "since="+URLEncoder.encode(Long.toString(since.getTime()), "UTF-8")+"&";
				if (until != null) {
			        Calendar cal = Calendar.getInstance();
			        cal.setTime(until);
			        cal.add(Calendar.DATE, 1);
			        cal.set(Calendar.HOUR, 0);
			        cal.set(Calendar.MINUTE, 0);
			        cal.set(Calendar.MILLISECOND, 0);
			        until = cal.getTime();
					storeUrl += "until="+URLEncoder.encode(Long.toString(until.getTime()), "UTF-8")+"&";
				}
				Response response;
				try {
					WebClient client = WebClient.create(storeUrl, jsg.getStoreUserName(), Password.decode(jsg.getPassword()).getPassword(), null);
					HTTPConduit conduit = WebClient.getConfig(client).getHttpConduit();
					long milisecondsToWait = 60000;
					try {
						milisecondsToWait = Long.parseLong(ConfigurationCache.getMasterProperty(PROPERTY_TIMEOUT));
					} catch (Exception e) {}
					conduit.getClient().setConnectionTimeout(milisecondsToWait);
					conduit.getClient().setReceiveTimeout(milisecondsToWait);
					client.type(MediaType.APPLICATION_JSON);
					client.accept(MediaType.APPLICATION_JSON);
					response = client.get();
				} catch (Exception e) {
					if (e.getMessage().contains("SocketTimeoutException"))
						throw new InternalErrorException("Timeout reached in the query, use the parameter \"soffid.pam.search.recordings.timeout\" to specify a longer timeout in milliseconds (default 60000).");
					throw new InternalErrorException("Error connecting to "+storeUrl+": "+e.getMessage() );
				}
				
				if (response.getStatus() != 200)
					throw new InternalErrorException("Error connecting to " + base + ": " + "HTTP/"
							+ response.getStatusInfo().getStatusCode() + " " + response.getStatusInfo().getReasonPhrase());
				
				JSONObject result  = new JSONObject( new JSONTokener( response.readEntity( InputStream.class   ) ) );
				if (result.getBoolean("success"))
				{
					JSONArray items = result.getJSONArray("result");
					for (int num = 0; num < items.length(); num++) {
						JSONObject item = items.getJSONObject(num);
						JSONObject o = item.getJSONObject("session");
						PamSession r = new PamSession();
						r.setAccountName(o.optString("accountName"));
						r.setChapters(new LinkedList<>());
						JSONArray ch = o.optJSONArray("chapters");
						for (int i = 0; i < ch.length(); i++)
							r.getChapters().add(ch.getLong(i));
						r.setId(o.optString("id"));
						r.setPath(o.optString("path"));
						if (o.has("serverEnd"))
							r.setServerEnd(df.parse(o.optString("serverEnd")));
						r.setServerUrl(o.optString("serverUrl"));
						r.setUser(o.optString("user"));
						r.setJumpServerGroup(jsg.getName());
						if (o.has("serverStart") && item.has("timeOffsets"))
						{
							r.setServerStart(df.parse(o.optString("serverStart")));
							r.setBookmarks(new LinkedList<>());
							JSONArray timeOffsets = item.getJSONArray("timeOffsets");
							for (int i = 0; i < timeOffsets.length(); i++) {
								r.getBookmarks().add(timeOffsets.getLong(i));
							}
							list.add(r);
						}
					}
				}
				else 
					throw new InternalErrorException("Error connecting to " + base + ": " + result.optString("reason"));
			}
		}
		return list;
	}

	@Override
	protected NewPamSession handleCreateCustomJumpServerSession(Account account, String sourceIp, TipusSessio type, String info)
			throws Exception {
		if (type == null) type = TipusSessio.PAM;
		if (sourceIp == null) sourceIp = Security.getClientIp();
		
		AccountEntity entity = getAccountEntityDao().load(account.getId());
		JumpServerGroupEntity jumpServerGroup = entity.getJumpServerGroup();
		if (jumpServerGroup == null)
			throw new InternalErrorException("Cannot start session. Please, assign a jump server group to account "+account.getDescription());
		getPamSecurityHandlerService().checkPermission(entity, "launch");
		String policyName = findPolicy(account, account.getLoginUrl());
		if (policyName == null &&
				entity.getFolder() != null && 
				entity.getFolder().getPamPolicy() != null )
			policyName = entity.getFolder().getPamPolicy().getName();
		return createJumpServerSession (entity, jumpServerGroup, account.getLoginUrl(), 
				policyName, sourceIp, type, info);
	}
}
