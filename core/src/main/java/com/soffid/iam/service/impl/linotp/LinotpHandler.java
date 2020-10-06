package com.soffid.iam.service.impl.linotp;

import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Random;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Challenge;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.User;
import com.soffid.iam.service.impl.OTPHandler;
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.util.Base64;

public class LinotpHandler implements OTPHandler {

	Log log = LogFactory.getLog(getClass());

	private boolean isEnabled ()
	{
		String enabled = ConfigurationCache.getProperty("soffid.linotp.enabled");
		return "true".equals(enabled);
	}
	
	private String getUrl (String url) throws InternalErrorException
	{
		String base = ConfigurationCache.getProperty("soffid.linotp.server");
		if (base == null)
			throw new InternalErrorException("Missing soffid.linotp.server configuration parameter");
		if (base.endsWith("/") && url.startsWith("/"))
			return base + url.substring(1);
		else if (base.endsWith("/") || url.startsWith("/"))
			return base + url;
		else
			return base + "/" + url;
	}
	
	private String getUser ()
	{
		return ConfigurationCache.getProperty("soffid.linotp.user");
	}
	
	private Password getPassword ()
	{
		String p = ConfigurationCache.getProperty("soffid.linotp.password");
		if ( p == null )
			return null;
		else
			return Password.decode(p);
	}
	
	private String getUserDomain ()
	{
		String p = ConfigurationCache.getProperty("soffid.linotp.userDomain");
		if ( p == null )
			return "DEFAULT";
		else
			return p;
	}

	private String getLinotpUserName (Challenge challenge)
	{
		if ( challenge.getAccount() != null )
			return challenge.getAccount().getName();
		String ud = getUserDomain();
		if (ud == null)
			return challenge.getUser().getUserName();
		
		try {
			return ServiceLocator.instance().getAccountService().guessAccountNameForDomain(challenge.getUser().getUserName(), ud);
		} catch (Exception e) {
			return null;
		}
	}

	String sessionId = null;
	private String getSessionId()
	{
		if (sessionId == null)
		{
			Random r = new Random();
			byte b[] = new byte[64];
			for (int i = 0; i < b.length; i++)
			{
				b[i] = (byte) r.nextInt(256);
			}
			sessionId = Base64.encodeBytes(b,Base64.DONT_BREAK_LINES);
		}
		return sessionId;
	}

	@Override
	public Challenge selectToken(Challenge challenge) throws Exception {
		if (isEnabled())
		{
			log.debug("selectToken");
			String linOtpUser = getLinotpUserName(challenge);
			int attempts = 0;
			boolean retry;
			do
			{
				retry = false;
				attempts++;
				Response response = 
					WebClient
					.create(getUrl("/admin/show"), getUser(), getPassword().getPassword(), null)
					.accept(MediaType.APPLICATION_JSON)
					.cookie(new Cookie("admin_session", getSessionId(), null, null, 0))
					.form(new Form()
						.param("user", linOtpUser)
						.param("session", getSessionId()));
				if ( response.getStatus() != HttpStatus.SC_OK)
					throw new InternalErrorException("Error invoking lintop web service: "+response.getStatusInfo().getReasonPhrase());
				log.debug(response.getHeaderString("Content-Type"));
				log.debug(response.getStatus());
				log.debug(response.getStatusInfo().getReasonPhrase());
				JSONObject result  = new JSONObject( new JSONTokener( response.readEntity( String.class   ) ) );
				JSONObject r;
				if ( (r = result.optJSONObject("result")) != null)
				{
					if (r.getBoolean("status")) {
						JSONArray data = r.getJSONObject("value").getJSONArray("data");
						for (int i = 0; i< data.length(); i++)
						{
							JSONObject token = data.optJSONObject(i);
							if (token != null && token.getBoolean("LinOtp.Isactive"))
							{
								if (token.getInt("LinOtp.FailCount") > token.getInt("LinOtp.MaxFail")) {
									// Skip. The token is locked
								} else {
									challenge.setCardNumber(token.getString("LinOtp.TokenSerialnumber"));
									challenge.setCell("Value");
									if ( "sms".equalsIgnoreCase(token.optString("LinOtp.TokenType"))) {
										
										String pass = "";
										String realm = "";
										
										log.debug("validate/smspin");
										log.debug("- getUser()="+getUser());
//										log.debug("- getPassword().getPassword()="+getPassword().getPassword());
										log.debug("- user="+linOtpUser);
										log.debug("- pass()="+pass);
										log.debug("- realm()="+realm);
										log.debug("- session="+getSessionId());
										
										Response response2 =
												WebClient
												.create(getUrl("/validate/smspin"), getUser(), getPassword().getPassword(), null)
												.accept(MediaType.APPLICATION_JSON)
												.cookie(new Cookie("admin_session", getSessionId(), null, null, 0))
												.form(new Form()
														.param("user", linOtpUser)
														.param("pass", "")
														.param("realm", "")
														.param("session", getSessionId()));
									}
									return challenge;
								}
							}
						}
						for (int i = 0; i< data.length(); i++)
						{
							JSONObject token = data.optJSONObject(i);
							if (token != null)
							{
								if (!token.getBoolean("LinOtp.Isactive"))
									throw new InternalErrorException("The token is not active");
								if (token.getInt("LinOtp.FailCount") > token.getInt("LinOtp.MaxFail"))
									throw new InternalErrorException("The token is locked");
							}
						}
					}
					else
					{
						JSONObject error = r.optJSONObject("error");
						if (error != null)
						{
							String message = error.getString("message");
							if (message != null && message.contains("MySQL server has gone away"))
								retry = true;
						}
					}
				}
				log.debug(result.toString());
			} while (retry && attempts <= 3);
			
		}
		
		return challenge;
						
	}

	@Override
	public boolean validatePin(Challenge challenge, String pin) throws IllegalArgumentException, InternalErrorException {
		if (isEnabled())
		{
			log.debug("validatePin");
			String linOtpUser = getLinotpUserName(challenge);
			int attempts = 0;
			boolean retry;
			do
			{
				retry = false;
				attempts++;
				Response response =
					WebClient
						.create(getUrl("/validate"), getUser(), getPassword().getPassword(), null)
						.accept(MediaType.APPLICATION_JSON)
						.cookie(new Cookie("admin_session", getSessionId(), null, null, 0))
						.form(new Form()
							.param("user", linOtpUser)
							.param("pass", pin)
							.param("realm", "")
							.param("session", getSessionId()));
				if ( response.getStatus() == HttpStatus.SC_INTERNAL_SERVER_ERROR)
					response = WebClient
						.create(getUrl("/validate"), getUser(), getPassword().getPassword(), null)
						.accept(MediaType.APPLICATION_JSON)
						.cookie(new Cookie("admin_session", getSessionId(), null, null, 0))
						.form(new Form()
							.param("user", linOtpUser)
							.param("pass", pin)
							.param("realm", "")
							.param("session", getSessionId()));
				if ( response.getStatus() != HttpStatus.SC_OK)
					throw new InternalErrorException("Error invoking lintop web service: "+response.getStatusInfo().getReasonPhrase());
				
				log.debug(response.getHeaderString("Content-Type"));
				log.debug(response.getStatus());
				log.debug(response.getStatusInfo().getReasonPhrase());
				JSONObject result;
				try {
					result = new JSONObject( response.readEntity(String.class));
					JSONObject r;
					if ( (r = result.optJSONObject("result")) != null)
					{
						if (r.optBoolean("status") && r.optBoolean("value")) {
							return true;
						}
						else
						{
							JSONObject error = r.optJSONObject("error");
							if (error != null)
							{
								String message = error.getString("message");
								if (message != null && message.contains("MySQL server has gone away"))
									retry = true;
							}
						}
					}
					log.debug(result.toString());
				} catch (JSONException e) {
					throw new InternalErrorException("Error decoding LinOTP response", e);
				}
			} while (retry && attempts <= 3);
		}
		
		return false;
	}

}
