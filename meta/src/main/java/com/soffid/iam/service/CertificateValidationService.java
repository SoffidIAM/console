//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.service;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( serverPath="/seycon/CertificateValidationService",
	 translatedName="CertificateValidationService",
	 translatedPackage="com.soffid.iam.service",
	 serverRole = "agent",
	 grantees={roles.anonymous.class})
public abstract class CertificateValidationService {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.util.Collection<java.security.cert.X509Certificate> getRootCertificateList()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public boolean validateCertificate(
		java.util.List<java.security.cert.X509Certificate> certs)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return false;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Usuari getCertificateUser(
		java.util.List<java.security.cert.X509Certificate> certs)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public es.caib.seycon.ng.comu.Account getCertificateAccount(
		java.util.List<java.security.cert.X509Certificate> certs)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
