package es.caib.seycon.ng.servei;


public class SeyconServiceLocator {
    private static es.caib.seycon.ng.ServiceLocator realLocator = null;
    
    public static es.caib.seycon.ng.ServiceLocator instance () {
        if (realLocator == null)
        {
            realLocator = es.caib.seycon.ng.ServiceLocator.instance();
            realLocator.init("beanRefFactory.xml", "beanRefFactory"); //$NON-NLS-1$ //$NON-NLS-2$
        }
        return realLocator;
    }
    
}
