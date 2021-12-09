package com.soffid.iam.remote;

import java.net.HttpURLConnection;

import javax.net.ssl.HttpsURLConnection;

public interface HeadersFactory {
	public void addHeaders(HttpURLConnection connection);
}
