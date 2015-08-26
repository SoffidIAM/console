package es.caib.seycon.net;

import es.caib.seycon.ng.servei.ConfiguracioService;
import es.caib.seycon.ng.servei.DadesAddicionalsService;
import es.caib.seycon.ng.servei.UsuariService;
import es.caib.seycon.ng.servei.XarxaService;

public class SeyconServiceLocator {
    private static es.caib.seycon.ng.ServiceLocator realLocator = null;
    private static SeyconServiceLocator locator = null;
    
    public static es.caib.seycon.ng.ServiceLocator instance () {
        if (realLocator == null)
        {
            realLocator = es.caib.seycon.ng.ServiceLocator.instance();
            realLocator.init("beanRefFactory.xml", "beanRefFactory"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return realLocator;
    }
    
}
