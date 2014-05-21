package es.caib.seycon.ng.model;

public class UsuariEntityImpl extends UsuariEntity
{

	@Override
	public String getFullName()
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
	}

}
