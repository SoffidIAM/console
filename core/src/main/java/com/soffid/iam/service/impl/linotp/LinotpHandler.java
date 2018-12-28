package com.soffid.iam.service.impl.linotp;

import java.io.InputStream;
import java.util.Random;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
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

	private String getLinotpUserName (User user)
	{
		String ud = getUserDomain();
		if (ud == null)
			return user.getUserName();
		
		try {
			return ServiceLocator.instance().getAccountService().guessAccountNameForDomain(user.getUserName(), ud);
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
			String linOtpUser = getLinotpUserName(challenge.getUser());
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
			
			System.out.println(response.getHeaderString("Content-Type"));
			System.out.println(response.getStatus());
			System.out.println(response.getStatusInfo().getReasonPhrase());
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
							challenge.setCardNumber(token.getString("LinOtp.TokenSerialnumber"));
							challenge.setCell("Value");
							return challenge;
						}
					}
				}
			}
			System.out.println(result.toString());
		}
		
		return challenge;
						
	}

	@Override
	public boolean validatePin(Challenge challenge, String pin) throws IllegalArgumentException, InternalErrorException {
		if (isEnabled())
		{
			String linOtpUser = getLinotpUserName(challenge.getUser());
			Response response =
				WebClient
					.create(getUrl("/validate"), getUser(), getPassword().getPassword(), null)
					.accept(MediaType.APPLICATION_JSON)
					.cookie(new Cookie("admin_session", getSessionId(), null, null, 0))
					.form(new Form()
						.param("user", linOtpUser)
						.param("pass", pin)
						.param("session", getSessionId()));
			if ( response.getStatus() != HttpStatus.SC_OK)
				throw new InternalErrorException("Error invoking lintop web service: "+response.getStatusInfo().getReasonPhrase());
			
			System.out.println(response.getHeaderString("Content-Type"));
			System.out.println(response.getStatus());
			System.out.println(response.getStatusInfo().getReasonPhrase());
			JsonObject result = Json.createReader((InputStream) response.getEntity(  ))
				.readObject();
			JsonObject r;
			if ( (r = result.getJsonObject("result")) != null)
			{
				if (r.getBoolean("status") && r.getBoolean("value")) {
					return true;
				}
			}
			System.out.println(result.toString());
		}
		
		return false;
	}

}
