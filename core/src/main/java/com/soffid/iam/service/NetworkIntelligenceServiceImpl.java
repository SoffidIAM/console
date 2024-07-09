package com.soffid.iam.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.soffid.iam.ServiceLocator;
import com.soffid.iam.api.Issue;
import com.soffid.iam.api.MailDomain;
import com.soffid.iam.api.NetworkIntelligence;
import com.soffid.iam.api.User;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.utils.NetworkIntelligenceIssuesUtils;
import com.soffid.iam.utils.NetworkIntelligenceParamLastDateUtils;
import com.soffid.iam.utils.NetworkIntelligenceParamTokenUtils;

import es.caib.seycon.ng.exception.InternalErrorException;

public class NetworkIntelligenceServiceImpl extends NetworkIntelligenceServiceBase {

	private static final String ENDPOINT_GEOINFORMATION = "/geo-information";
	private static final String ENDPOINT_BREACHES = "/breaches";
	private Log log = LogFactory.getLog(getClass());

	protected NetworkIntelligence handleValidateToken(String token) throws Exception {
		try {
			String ssokmUrl = ConfigurationCache.getProperty("network-intelligence.url")+ENDPOINT_GEOINFORMATION;
			if (ssokmUrl==null || ssokmUrl.trim().isEmpty())
				throw new InternalErrorException("The Network Intelligence Service is not configured correctly, contact the administrator");

			URL httpURL = new URL(ssokmUrl);
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			conn.setRequestMethod("POST");
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

			URL httpURL = new URL(ssokmUrl+"/isPasswordBreached?password="+password);
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			conn.setRequestMethod("POST");
			conn.addRequestProperty("Content-Type", "application/json");
			conn.setDoOutput(true);
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
					Boolean b = Boolean.valueOf(s);
					return b;
				} catch (Exception e) {
					throw new InternalErrorException("Network Intelligence unexpected response error, contact the administrator");
				}
			}
		} catch (Exception e) {
			log.warn("Network Intelligence generic unexpected response error, contact the administrator");
		}
		return Boolean.valueOf(false);
	}

	public Boolean handleIsAccountBreached(String account, String system) {
		try {
			String ssokmUrl = ConfigurationCache.getProperty("network-intelligence.url")+ENDPOINT_BREACHES;
			if (ssokmUrl==null || ssokmUrl.trim().isEmpty())
				throw new InternalErrorException("The Network Intelligence Service is not configured correctly, contact the administrator");

			URL httpURL = new URL(ssokmUrl+"/isAccountBreached");
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			conn.setRequestMethod("POST");
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
		return Boolean.valueOf(false);
	}

	public String handleIsEmailBreached(String shortName, String mailDomain) {
		try {
			String ssokmUrl = ConfigurationCache.getProperty("network-intelligence.url")+ENDPOINT_BREACHES;
			if (ssokmUrl==null || ssokmUrl.trim().isEmpty())
				throw new InternalErrorException("The Network Intelligence Service is not configured correctly, contact the administrator");

			String sURL = ssokmUrl+"/isEmailBreached?shortName="+shortName+"&mailDomain="+mailDomain;
			Long lastDate = NetworkIntelligenceParamLastDateUtils.getLastDateFromParam();
			if (lastDate!=null)
				sURL = sURL+"lastDate="+lastDate;
			URL httpURL = new URL(sURL);
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(false);
			conn.connect();

			if (conn.getResponseCode() != HttpStatus.SC_OK) {
				throw new InternalErrorException("Network Intelligence unexpected error, contact the administrator");
			} else {
				try {
					InputStream in = conn.getInputStream();
					JSONArray ja = new JSONArray(new JSONTokener(in));
					return ja.toString();
				} catch (Exception e) {
					throw new InternalErrorException("Network Intelligence unexpected response error, contact the administrator");
				}
			}
		} catch (Exception e) {
			log.warn("Network Intelligence generic unexpected response error, contact the administrator");
		}
		return null;
	}

	public void handleVerifyDomains(PrintWriter out) {
		out.println("Starting the task Verify Domain, this is a functionality of the Network Intelligence service.");
		String token = getToken();
		if (token!=null) {
			boolean valid = false;
			try {
				valid = validateToken(token)!=null;
			} catch(Exception e) {
				out.println("It has not been possible to validate your licence, check it later.");
			}
			if (valid) {
				out.println("Valid licence.");
				Long lastDate = NetworkIntelligenceParamLastDateUtils.getLastDateFromParam();
				if (lastDate!=null)
					out.println("Last verification "+new Date(lastDate));
				try {
					for (MailDomain md : retrieveMailDomains()) {
						out.println("Searching for "+md.getName()+"...");
						for (String breachedEmails : requestBreachedEmails(md.getName(), lastDate)) {
							List<Issue> li = openIssuesIfEmailBreached(md.getName(), breachedEmails);
							if (li!=null && li.size()==1) {
								out.println("A breached email "+breachedEmails+"@"+md.getName()+" has been found out, a security issue has been raised.");
							} else if (li!=null && li.size()>1) {
								out.println("A breached email "+breachedEmails+"@"+md.getName()+" has been found out, "+li.size()+" security issues have been raised.");
							}
						}
					}
					NetworkIntelligenceParamLastDateUtils.setLastDateToParam();
				} catch (InternalErrorException e) {
					out.println("Error trying to check domains or accoutns: "+e.getMessage());
				}
			} else {
				out.println("You do not have a valid licence to run this process.");
			}
		} else {
			out.println("You do not have a licence to run this process.");
		}
		out.println("Task finished.");
	}

	private List<MailDomain> retrieveMailDomains() throws InternalErrorException {
		return ServiceLocator.instance().getMailListsService().findMailDomainsByJsonQuery("", null, null).getResources();
	}

	private ArrayList<String> requestBreachedEmails(String mailDomain, Long lastDate) {
		try {
			String ssokmUrl = ConfigurationCache.getProperty("network-intelligence.url")+ENDPOINT_BREACHES;
			if (ssokmUrl==null || ssokmUrl.trim().isEmpty())
				throw new InternalErrorException("The Network Intelligence Service is not configured correctly, contact the administrator");

			String sURL = ssokmUrl+"/verifyDomain?domain="+mailDomain;
			if (lastDate!=null)
				sURL = sURL+"&lastDate="+String.valueOf(lastDate);
			URL httpURL = new URL(sURL);
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			conn.setRequestMethod("POST");
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

	private List<Issue> openIssuesIfEmailBreached(String mailDomain, String breachedEmail) {
		List<Issue> li = new ArrayList<Issue>();
		try {
			UserService us = ServiceLocator.instance().getUserService();
			List<User> lu = us.findUserByJsonQuery("emailAddress eq \""+breachedEmail+"@"+mailDomain+"\"");
			if (!lu.isEmpty()) {
				String response = handleIsEmailBreached(breachedEmail, mailDomain);
				JSONArray ja = new JSONArray(new JSONTokener(response));
				for (int i=0; i<ja.length(); i++) {
					JSONObject jo = (JSONObject) ja.get(i);
					String breachName = jo.getString("Name");
					String breachDecription = generateBreachDescription(jo);
					Issue is = (new NetworkIntelligenceIssuesUtils()).openIssueEmailBreached(mailDomain, breachedEmail, lu, breachName, breachDecription);
					li.add(is);
				}
			}
			return li;
		} catch (InternalErrorException e) {
			e.printStackTrace();
		}
		return li;
	}

	private String generateBreachDescription(JSONObject jo) {
		try {
			String name = jo.getString("Name");
			String title = jo.getString("Title");
			String domain = jo.getString("Domain");
			String breachDate = jo.getString("BreachDate");
			String addedDate = jo.getString("AddedDate");
			String description = jo.getString("Description");
			StringBuffer sb = new StringBuffer();
			sb.append("The breach \""+name+" - "+title+"\" ");
			if (!domain.isEmpty())
				sb.append("for the domain \""+domain+"\" ");
			sb.append("had been breached at "+breachDate+" and published at "+addedDate+", ");
			sb.append("this is the official description: "+description);
			return sb.toString();
		} catch(Exception e) {
			log.warn("generateLastBreachDescription e="+e);
			return "";
		}
	}

	private String getToken() {
		NetworkIntelligence ni = NetworkIntelligenceParamTokenUtils.getTokenFromParam();
		if (ni!=null && ni.getToken()!=null)
			return ni.getToken();
		return null;
	}
}
