// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.util.Collection;
import java.util.Iterator;

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.AutoritzacioService;
import es.caib.seycon.ng.utils.AutoritzacioSEU;
import es.caib.seycon.ng.utils.Security;

/**
 * @see es.caib.seycon.ng.model.GrupEntity
 */
public class GrupEntityImpl extends es.caib.seycon.ng.model.GrupEntity 
	implements SecurityScopeEntity
{
	/**
	 * The serial version UID of this class. Needed for serialization.
	 */
	private static final long serialVersionUID = -6294585483589486558L;

	/**
	 * @see es.caib.seycon.ng.model.GrupEntity#toString()
	 */
	public java.lang.String toString() {
		// @todo implement public java.lang.String toString()
		return String.format(Messages.getString("GrupEntityImpl.toString"), //$NON-NLS-1$
		        getId(), getCodi(),getDescripcio(), getTipusUnitatOrganizativa());
	}

	private boolean checkDeep (String permission, GrupEntity entity)
	{
		if (Security.isUserInRole(permission+"/"+entity.getCodi()))
			return true;
		for (GrupEntity child: entity.getFills())
		{
			if (checkDeep(permission, child))
				return true;
		}
		return false;
		
	}
	
	public boolean isAllowed(String permission) {
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
	
		if (Security.isUserInRole(permission+"/"+getCodi()))
			return true;
		
		try 
		{
			AutoritzacioService autService = ServiceLocator.instance().getAutoritzacioService();
			for (Iterator it = autService.getInformacioAutoritzacio(permission).iterator(); it.hasNext(); )
			{
				AutoritzacioSEU as = (AutoritzacioSEU) it.next();
				if ("parents".equals(as.getScope()) || "both".equals(as.getScope()))
				{
					for (GrupEntity child: getFills())
					{
						if (checkDeep(permission, child))
							return true;
					}
				}
				
				if ("children".equals(as.getScope()) || "both".equals(as.getScope()))
				{
					GrupEntity parent = getPare();
					while (parent != null)
					{
						if (Security.isUserInRole(permission+"/"+parent.getCodi()))
							return true;
						parent = parent.getPare();
					}
				}

			}
			return false;
		} catch (InternalErrorException e) {
			throw new SecurityException ("Unable to check permissions", e);
		}
	}
}