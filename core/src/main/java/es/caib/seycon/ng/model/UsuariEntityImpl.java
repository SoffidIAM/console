package es.caib.seycon.ng.model;

import java.util.Collection;
import java.util.Iterator;

import com.soffid.iam.model.security.SecurityScopeEntity;

import es.caib.seycon.ng.utils.Security;

public class UsuariEntityImpl extends UsuariEntity implements SecurityScopeEntity
{

	@Override
	public String getFullName()
	{
		String nameFormat = System.getProperty("soffid.nameformat");
		
		if (nameFormat == null)
		{
			StringBuffer b = new StringBuffer ();
			b.append (getNom());
			b.append (" "); //$NON-NLS-1$
			b.append (getPrimerLlinatge());
			if (getSegonLlinatge() != null && ! getSegonLlinatge().isEmpty())
			{
				b.append(" "); //$NON-NLS-1$
				b.append (getSegonLlinatge());
			}
			return b.toString();
		} else {
			return String.format(nameFormat, getNom(), getSegonLlinatge(), getPrimerLlinatge());
		}
	}

	public boolean isAllowed(String permission) {
		if (! permission.endsWith(":query"))
		{
			String user = Security.getCurrentUser();
			if ( user != null && user.equals (getCodi()))
				return false;
		}
		if (Security.isUserInRole(permission+Security.AUTO_ALL))
			return true;
		boolean trobat = false;
		if (getGrupPrimari() != null
						&& getGrupPrimari().isAllowed(permission))
			return true;
		if (!trobat)
		{ // mirem grups secundaris
			for (UsuariGrupEntity userGroup: getGrupsSecundaris())
			{
				if (userGroup.getGrup() != null && userGroup.getGrup().isAllowed(permission)) //$NON-NLS-1$
					return true;
			}
		}

		return false;
	}

}
