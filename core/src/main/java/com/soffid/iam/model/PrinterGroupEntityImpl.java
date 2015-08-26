//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import com.soffid.iam.model.security.SecurityScopeEntity;
import com.soffid.iam.service.NetworkServiceImpl;
import com.soffid.iam.ServiceLocator;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.utils.Security;

/**
 * Entity GrupImpressoraEntity implementation
 */
public class PrinterGroupEntityImpl extends com.soffid.iam.model.PrinterGroupEntity 
	implements SecurityScopeEntity
{
	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		if (getGroup() != null && getGroup().isAllowed(permission))
			return true;
		
        // Només ho comprovem si en té l'autorització
        if (Security.isUserInRole(Security.AUTO_USER_ACL_PRINTER_CREATE) &&
        		Security.AUTO_GROUP_PRINTER_CREATE.equals (permission) ||
        		Security.isUserInRole(Security.AUTO_USER_ACL_PRINTER_CREATE) &&
        		Security.AUTO_GROUP_PRINTER_DELETE.equals (permission))
        {
                // Obtenim el nivell d'accés a la màquina servidora d'impressores
                HostEntity serverImp = getPrinter().getServer();
                Long nivell;
				try {
					nivell = ServiceLocator.instance().getNetworkService()
							.findAccessLevelByHostNameAndNetworkName(serverImp.getName(), serverImp.getNetwork().getName());
				} catch (InternalErrorException e) {
					throw new RuntimeException (e);
				}

                // Nivell mínim: suport
                if (nivell >= NetworkServiceImpl.SUPORT)
                        return true;

        }


		
		return false;
	}


}
