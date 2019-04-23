//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.sync.agent;

import com.soffid.mda.annotation.*;

import org.springframework.transaction.annotation.Transactional;

@Service(serverOnly = true, serverPath = "/seycon/AgentManager", serverRole = "server", translatedName = "AgentManager", translatedPackage = "com.soffid.iam.sync.agent")
public abstract class AgentManager {

	@Transactional(noRollbackFor = { java.lang.Exception.class })
	public java.lang.Object createLocalAgent(es.caib.seycon.ng.comu.Dispatcher dispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Transactional(noRollbackFor = { java.lang.Exception.class })
	public java.lang.String createAgent(es.caib.seycon.ng.comu.Dispatcher dispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Transactional(noRollbackFor = { java.lang.Exception.class })
	public java.lang.Object createLocalAgentDebug(es.caib.seycon.ng.comu.Dispatcher dispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Transactional(noRollbackFor = { java.lang.Exception.class })
	public java.lang.String createAgentDebug(es.caib.seycon.ng.comu.Dispatcher dispatcher)
			throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}

	@Transactional(rollbackFor = { java.lang.Exception.class })
	public void reset() throws es.caib.seycon.ng.exception.InternalErrorException {
	}

	@Transactional(rollbackFor = { java.lang.Exception.class })
	public java.util.Date getCertificateValidityDate() throws es.caib.seycon.ng.exception.InternalErrorException {
		return null;
	}
}
