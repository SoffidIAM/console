/**
 * 
 */
package es.caib.seycon.ng.test;


import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.servei.ApplicationBootService;
import es.caib.seycon.ng.servei.ConfiguracioService;

/**
 * @author bubu
 *
 */
public class startupTest extends AbstractTest
{

	public void testStartup () throws InternalErrorException
	{
		
		ApplicationBootService startupSvc = (ApplicationBootService) context.getBean(ApplicationBootService.SERVICE_NAME);
		startupSvc.consoleBoot();
		
	}
}
