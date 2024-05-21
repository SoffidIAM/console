package com.soffid.iam.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Account;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.IssueStatus;
import com.soffid.iam.api.IssueUser;
import com.soffid.iam.api.NetworkIntelligence;
import com.soffid.iam.api.System;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.Security;

import es.caib.seycon.ng.exception.InternalErrorException;

public class NetworkIntelligenceServiceImpl extends NetworkIntelligenceServiceBase {

	private static final String ENDPOINT_GEOINFORMATION = "/geo-information";
	private static final String ENDPOINT_BREACHES = "/breaches";
	private static final String PARAM_TOKEN = "soffid.network-intelligence.token";

	Log log = LogFactory.getLog(getClass());

	protected NetworkIntelligence handleValidateToken(String token) throws Exception {
		try {
			String ssokmUrl = ConfigurationCache.getProperty("network-intelligence.url")+ENDPOINT_GEOINFORMATION;
			if (ssokmUrl==null || ssokmUrl.trim().isEmpty())
				throw new InternalErrorException("The Network Intelligence Service is not configured correctly, contact the administrator");

			URL httpURL = new URL(ssokmUrl);
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoOutput(false);
			conn.addRequestProperty("token", token);
			conn.connect();

			if (conn.getResponseCode()==HttpStatus.SC_OK) {
				try {
					InputStream in = conn.getInputStream();
					JSONObject jo = new JSONObject(new JSONTokener(in));
					NetworkIntelligence ni = new NetworkIntelligence();
					ni.setToken(jo.getString("token"));
					ni.setLevel(jo.getString("level"));
					ni.setStart(new Date(jo.getLong("start")));
					ni.setEnd(new Date(jo.getLong("end")));
					return ni;
				} catch (Exception e) {
					throw new InternalErrorException("Error validating the token, contact with administrator");
				}
			} else if (conn.getResponseCode()==HttpStatus.SC_INTERNAL_SERVER_ERROR) {
				log.debug("Token not found or not valid in the Network Intelligence Service");
				return null;
			} else {
				log.debug("Generic error trying to validate the token in the Network Intelligence Service");
				return null;
			}
		} catch (Exception e) {
			throw new InternalErrorException("Error validating token with the Network Intelligence Service", e);
		}
	}

	public Boolean handleIsPasswordBreached(String password) {
		try {
			String ssokmUrl = ConfigurationCache.getProperty("network-intelligence.url")+ENDPOINT_BREACHES;
			if (ssokmUrl==null || ssokmUrl.trim().isEmpty())
				throw new InternalErrorException("The Network Intelligence Service is not configured correctly, contact the administrator");

			URL httpURL = new URL(ssokmUrl+"/isPasswordBreached");
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoOutput(false);
			conn.addRequestProperty("password", password);
			conn.connect();

			if (conn.getResponseCode() != HttpStatus.SC_OK) {
				throw new InternalErrorException("Network Intelligence unexpected error, contact the administrator");
			} else {
				try {
					InputStream in = conn.getInputStream();
					JSONObject jo = new JSONObject(new JSONTokener(in));
					boolean b = Boolean.parseBoolean(jo.toString());
					return b;
				} catch (Exception e) {
					throw new InternalErrorException("Network Intelligence unexpected response error, contact the administrator");
				}
			}
		} catch (Exception e) {
			log.warn("Network Intelligence generic unexpected response error, contact the administrator");
		}
		return new Boolean(false);
	}

	public Boolean handleIsAccountBreached(String account, String system) {
		try {
			String ssokmUrl = ConfigurationCache.getProperty("network-intelligence.url")+ENDPOINT_BREACHES;
			if (ssokmUrl==null || ssokmUrl.trim().isEmpty())
				throw new InternalErrorException("The Network Intelligence Service is not configured correctly, contact the administrator");

			URL httpURL = new URL(ssokmUrl+"/isAccountBreached");
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoOutput(false);
			conn.addRequestProperty("account", account);
			conn.addRequestProperty("system", system);
			conn.connect();

			if (conn.getResponseCode() != HttpStatus.SC_OK) {
				throw new InternalErrorException("Network Intelligence unexpected error, contact the administrator");
			} else {
				try {
					InputStream in = conn.getInputStream();
					JSONObject jo = new JSONObject(new JSONTokener(in));
					boolean b = Boolean.parseBoolean(jo.toString());
					return b;
				} catch (Exception e) {
					throw new InternalErrorException("Network Intelligence unexpected response error, contact the administrator");
				}
			}
		} catch (Exception e) {
			log.warn("Network Intelligence generic unexpected response error, contact the administrator");
		}
		return new Boolean(false);
	}

	public Boolean handleIsEmailBreached(String shortName, String mailDomain) {
		try {
			String ssokmUrl = ConfigurationCache.getProperty("network-intelligence.url")+ENDPOINT_BREACHES;
			if (ssokmUrl==null || ssokmUrl.trim().isEmpty())
				throw new InternalErrorException("The Network Intelligence Service is not configured correctly, contact the administrator");

			URL httpURL = new URL(ssokmUrl+"/isEmailBreached");
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoOutput(false);
			conn.addRequestProperty("shortName", shortName);
			conn.addRequestProperty("mailDomain", mailDomain);
			conn.connect();

			if (conn.getResponseCode() != HttpStatus.SC_OK) {
				throw new InternalErrorException("Network Intelligence unexpected error, contact the administrator");
			} else {
				try {
					InputStream in = conn.getInputStream();
					JSONObject jo = new JSONObject(new JSONTokener(in));
					boolean b = Boolean.parseBoolean(jo.toString());
					return b;
				} catch (Exception e) {
					throw new InternalErrorException("Network Intelligence unexpected response error, contact the administrator");
				}
			}
		} catch (Exception e) {
			log.warn("Network Intelligence generic unexpected response error, contact the administrator");
		}
		return new Boolean(false);
	}

	public void handleVerifyDomains(PrintWriter out) {
		out.println("Starting Verify Domains task, this is a functionality of the Network Intelligence service.");
		String token = getToken();
		if (token!=null) {
			boolean valid = false;
			try {
				valid = validateToken(token)!=null;
			} catch(Exception e) {
				out.println("It has not been possible to validate your licence, check it later.");
			}
			if (valid) {
				out.println("Validated licence.");
				try {
					for (System domain : retrieveActiveDomains()) {
						out.println("Searching for "+domain.getName()+"...");
						for (String breachedAccount : requestBreachedAccounts(domain)) {
							Issue i = openIssueIfAccountPawned(domain.getName(), breachedAccount);
							if (i!=null)
								out.println("Found account "+breachedAccount+" breached. An "+i.getType()+" with description \""+i.getDescription()+"\" has been opened.");
						}
					}
				} catch (InternalErrorException e) {
					out.println("Error trying to check domains or accoutns: "+e.getMessage());
				}
			} else {
				out.println("You do not have a valid licence to run this process.");
			}
		} else {
			out.println("You do not have a licence to run this process.");
		}
		out.println("Finished task.");
	}

	private Collection<System> retrieveActiveDomains() throws InternalErrorException {
		return ServiceLocator.instance().getDispatcherService().findAllActiveDispatchers();
	}

	private ArrayList<String> requestBreachedAccounts(System domain) {
		try {
			String ssokmUrl = ConfigurationCache.getProperty("network-intelligence.url")+ENDPOINT_BREACHES;
			if (ssokmUrl==null || ssokmUrl.trim().isEmpty())
				throw new InternalErrorException("The Network Intelligence Service is not configured correctly, contact the administrator");

			URL httpURL = new URL(ssokmUrl+"/verifyDomain?domain="+domain.getName());
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoOutput(false);
			conn.addRequestProperty("token", getToken());
			conn.connect();

			if (conn.getResponseCode() != HttpStatus.SC_OK) {
				throw new InternalErrorException("Network Intelligence unexpected error, contact the administrator");
			} else {
				try {
					InputStream in = conn.getInputStream();
					StringBuilder textBuilder = new StringBuilder();
					try (Reader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8))) {
						int c = 0;
				        while ((c = reader.read()) != -1) {
				        	textBuilder.append((char) c);
				        }
					}
					String s = textBuilder.toString();
					String[] sa = s.split("\n");
					return new ArrayList<String>(Arrays.asList(sa));
				} catch (Exception e) {
					throw new InternalErrorException("Network Intelligence unexpected response error, contact the administrator");
				}
			}
		} catch (Exception e) {
			log.warn("Network Intelligence generic unexpected response error, contact the administrator");
		}
		return new ArrayList<String>();
	}

	private Issue openIssueIfAccountPawned(String domain, String breachedAccount) {
		try {
			AccountService as = ServiceLocator.instance().getAccountService();
			Account a = as.findAccount(breachedAccount, domain);
			if (a!=null) {
				Issue i = new Issue();
				i.setCreated(new Date());
				i.setStatus(IssueStatus.NEW);
				i.setType("security-exception");
				if (Security.getSoffidPrincipal().getUserId() != null) {
					IssueUser iu = new IssueUser();
					iu.setUserId(Security.getSoffidPrincipal().getUserId());
					i.setUsers(Arrays.asList(iu));
				}
				i.setException("The account "+breachedAccount+"@"+domain+" has been found in a data breach, please check the correct action about it");
				i.setDescription("The account "+breachedAccount+"@"+domain+" has been found in a data breach, please check the correct action about it");
				IssueService is = ServiceLocator.instance().getIssueService();
				return is.createInternalIssue(i);
			}
		} catch (InternalErrorException e) {
			e.printStackTrace();
		}
		return null;
	}

	private String getToken() {
		ConfigurationService cs = ServiceLocator.instance().getConfigurationService();
		Configuration param = null;
		try {
			param = cs.findParameterByNameAndNetworkName(PARAM_TOKEN, null);
		} catch (InternalErrorException e) {}
		if (param==null)
			return null;

		try {
			String[] paramA = param.getValue().split(";");
			String token = paramA[0].split("=")[1];
			return token;
		} catch (Exception e) {
		}
		return null;
	}
}
