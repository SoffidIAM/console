package com.soffid.iam.service.impl;

import java.io.IOException;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.soffid.iam.service.DispatcherService;
import com.soffid.iam.ssl.TrustedCertificateLoader;

import es.caib.seycon.ng.exception.InternalErrorException;

public class ConsoleTrustedCertificateLoader implements TrustedCertificateLoader {
	Log log = LogFactory.getLog(getClass());
	
	private DispatcherService dispatcherService;

	public ConsoleTrustedCertificateLoader(DispatcherService dispatcherSvc) {
		this.dispatcherService = dispatcherSvc;
	}

	@Override
	public List<X509Certificate> loadCerts(KeyStore ks) throws IOException {
        try {
			return dispatcherService.findValidCertificates();
		} catch (InternalErrorException e) {
			log.warn("Error loading trusted certificates", e);
			return null;
		}
	}

}
