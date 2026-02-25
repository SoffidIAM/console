//
// (C) 2013 Soffid
//
//

package com.soffid.iam.bpm.service;
import org.springframework.transaction.annotation.Transactional;
/**
 * <p> 
 * Spring Service base class for <code>com.soffid.iam.bpm.service.BpmConfigService</code>,
 * provides access to all services and entities referenced by this service. 
 * </p>
 * 
 * see com.soffid.iam.bpm.service.BpmConfigService
 */
public abstract class BpmConfigServiceBase
 extends com.soffid.iam.impl.service.ApplicationBootServiceBase
	implements com.soffid.iam.bpm.service.BpmConfigService
 {

	/**
	 * Gets the current <code>principal</code> if one has been set,
	 * otherwise returns <code>null</code>.
	 *
	 * @return the current principal
	 */
	protected java.security.Principal getPrincipal()
	{
		return com.soffid.iam.PrincipalStore.get();
	}

}
