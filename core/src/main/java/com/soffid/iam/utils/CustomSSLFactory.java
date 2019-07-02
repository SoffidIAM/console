package com.soffid.iam.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;

public class CustomSSLFactory extends javax.net.SocketFactory {
	private SSLSocketFactory sslFactory;

	public CustomSSLFactory () throws NoSuchAlgorithmException {
        SSLContext ctx = SSLContext.getDefault(); //$NON-NLS-1$

        sslFactory = ctx.getSocketFactory();
	}

	@Override
	public Socket createSocket(String host, int port) throws IOException, UnknownHostException {
		return sslFactory.createSocket(host, port);
	}

	@Override
	public Socket createSocket(InetAddress host, int port) throws IOException {
		return sslFactory.createSocket(host, port);
	}

	@Override
	public Socket createSocket(String host, int port, InetAddress localHost, int localPort)
			throws IOException, UnknownHostException {
		return sslFactory.createSocket(host, port, localHost, localPort);
	}

	@Override
	public Socket createSocket(InetAddress address, int port, InetAddress localAddress, int localPort)
			throws IOException {
		return sslFactory.createSocket(address, port, localAddress, localPort);
	}

}
