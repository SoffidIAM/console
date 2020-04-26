package es.caib.bpm.ui;

import java.net.URL;
import java.util.Locale;

import org.zkoss.util.resource.LabelLocator;

public class V2LabelLocator implements LabelLocator 
{
	public URL locate(Locale locale) throws Exception 
	{
		URL resource= null;
		String ubicacion= "com/soffid/iam/web/iam-label_" + locale.getLanguage() + "_" + locale.getCountry() + ".properties"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		resource= V2LabelLocator.class.getClassLoader().getResource(ubicacion);
		
		if(resource== null)
		{
			ubicacion= "com/soffid/iam/web/iam-label_" + locale.getLanguage() + ".properties"; //$NON-NLS-1$ //$NON-NLS-2$
			resource= V2LabelLocator.class.getClassLoader().getResource(ubicacion);
			if(resource== null)
			{
				ubicacion= "com/soffid/iam/web/iam-label.properties"; //$NON-NLS-1$
				resource= V2LabelLocator.class.getClassLoader().getResource(ubicacion);
			}
		}
		
		
		return resource;
	}

}
