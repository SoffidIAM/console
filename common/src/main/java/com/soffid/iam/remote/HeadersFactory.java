package com.soffid.iam.remote;

import javax.net.ssl.HttpsURLConnection;

public interface HeadersFactory {
	public void addHeaders(HttpsURLConnection connection);
}
