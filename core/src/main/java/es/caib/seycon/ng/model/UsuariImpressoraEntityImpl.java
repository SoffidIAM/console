//
// (C) 2013 Soffid
//
//

package es.caib.seycon.ng.model;

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.XarxaServiceImpl;
import es.caib.seycon.ng.utils.Security;

/**
 * Entity UsuariImpressoraEntity implementation
 */
public class UsuariImpressoraEntityImpl extends es.caib.seycon.ng.model.UsuariImpressoraEntity
	implements SecurityScopeEntity
{

	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		if (getUsuari() != null && getUsuari().isAllowed(permission))
				return true;
		
        // Només ho comprovem si en té l'autorització
        if (Security.isUserInRole(Security.AUTO_USER_ACL_PRINTER_CREATE) &&
        		Security.AUTO_USER_PRINTER_CREATE.equals (permission) ||
        		Security.isUserInRole(Security.AUTO_USER_ACL_PRINTER_CREATE) &&
        		Security.AUTO_USER_PRINTER_DELETE.equals (permission))
        {
                // Obtenim el nivell d'accés a la màquina servidora d'impressores
                MaquinaEntity serverImp = getImpressora().getServidor();
                Long nivell;
				try {
					nivell = ServiceLocator.instance().getXarxaService()
							.findNivellAccesByNomMaquinaAndCodiXarxa
								(serverImp.getNom(), serverImp.getXarxa().getCodi());
				} catch (InternalErrorException e) {
					throw new RuntimeException (e);
				}

                // Nivell mínim: suport
                if (nivell >= XarxaServiceImpl.SUPORT)
                        return true;

        }


		
		return false;
	}

}
