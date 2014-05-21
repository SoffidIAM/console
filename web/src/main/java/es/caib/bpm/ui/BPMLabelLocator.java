package es.caib.bpm.ui;

import java.net.URL;
import java.util.Locale;

import org.zkoss.util.resource.LabelLocator;

public class BPMLabelLocator implements LabelLocator 
{
	public URL locate(Locale locale) throws Exception 
	{
		URL resource= null;
		String ubicacion= "iam-label_" + locale.getLanguage() + "_" + locale.getCountry() + ".properties"; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
		resource= BPMLabelLocator.class.getClassLoader().getResource(ubicacion);
		
		if(resource== null)
		{
			ubicacion= "iam-label_" + locale.getLanguage() + ".properties"; //$NON-NLS-1$ //$NON-NLS-2$
			resource= BPMLabelLocator.class.getClassLoader().getResource(ubicacion);
			if(resource== null)
			{
				ubicacion= "iam-label.properties"; //$NON-NLS-1$
				resource= BPMLabelLocator.class.getClassLoader().getResource(ubicacion);
			}
		}
		
		
		return resource;
	}

}
