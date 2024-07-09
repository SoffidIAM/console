package com.soffid.iam.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;

import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.soffid.iam.api.NetworkIntelligence;
import com.soffid.iam.utils.ConfigurationCache;

import es.caib.seycon.ng.exception.InternalErrorException;

public class NetworkIntelligenceServiceImpl extends NetworkIntelligenceServiceBase {

	protected NetworkIntelligence handleValidateTokenFromSsokm(String token) throws Exception {
		try {
			String ssokmUrl = ConfigurationCache.getProperty("network-intelligence.url");
			if (ssokmUrl==null || ssokmUrl.trim().isEmpty())
				throw new InternalErrorException("The Network Intelligence Service is not configured correctly, contact the administrator");

			URL httpURL = new URL(ssokmUrl);
			HttpURLConnection conn = (HttpURLConnection) httpURL.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			conn.setDoOutput(false);
			conn.addRequestProperty("token", token);
			conn.connect();

			if (conn.getResponseCode() != HttpStatus.SC_OK) {
				throw new InternalErrorException("Token not found or not valid in the Network Intelligence Service");
			} else {
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
			}
		} catch (Exception e) {
			throw new InternalErrorException("Error validating token with the Network Intelligence Service", e);
		}
	}
}
