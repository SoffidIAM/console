//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.servei;
import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service ( serverOnly=true,
	 serverPath="/seycon/CertificateEnrollService",
	 translatedName="CertificateEnrollService",
	 translatedPackage="com.soffid.iam.sync.service",
	 serverRole="")
@Depends ({es.caib.seycon.ng.sync.servei.ServerService.class,
	es.caib.seycon.ng.sync.servei.LogonService.class,
	es.caib.seycon.ng.servei.DispatcherService.class})
public abstract class CertificateEnrollService {

	@Transactional(rollbackFor={java.lang.Exception.class})
	public long createRequest(
		java.lang.String user, 
		java.lang.String password, 
		@Nullable java.lang.String domain, 
		java.lang.String hostName, 
		java.security.PublicKey key)
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return 0;
	}
	@Transactional(propagation=org.springframework.transaction.annotation.Propagation.REQUIRED ,isolation=org.springframework.transaction.annotation.Isolation.READ_COMMITTED ,rollbackForClassName={"java.lang.Exception"},noRollbackForClassName={"CertificateEnrollDenied"})
	public java.security.cert.X509Certificate getCertificate(
		java.lang.String user, 
		java.lang.String password, 
		@Nullable java.lang.String domain, 
		java.lang.String hostName, 
		java.lang.Long request)
		throws es.caib.seycon.ng.exception.InternalErrorException, es.caib.seycon.ng.exception.CertificateEnrollDenied, es.caib.seycon.ng.exception.CertificateEnrollWaitingForAproval {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.lang.String getServerList()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public int getServerPort()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return 0;
	}
	@Transactional(rollbackFor={java.lang.Exception.class})
	public java.security.cert.X509Certificate getRootCertificate()
		throws es.caib.seycon.ng.exception.InternalErrorException {
	 return null;
	}
}
