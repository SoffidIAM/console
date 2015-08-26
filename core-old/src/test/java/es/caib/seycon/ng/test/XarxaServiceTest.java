package es.caib.seycon.ng.test;

import java.util.Collection;

import es.caib.seycon.ng.comu.Maquina;
import es.caib.seycon.ng.exception.InternalErrorException;

public class XarxaServiceTest extends AbstractTest {
	
	public void testQuery () throws InternalErrorException
	{
		
		Collection<Maquina> list = xarxaSvc.findMaquinaByFiltre("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", false);
		
		assertEquals(list.size(), 0);
	}

	public void testQuery2 () throws InternalErrorException
	{
		
		Collection<Maquina> list = xarxaSvc.findMaquinaByFiltre("a", "b", "c", "d", "e", null, null, null, null, null, null, false);
	}

	public void testQuery3 () throws InternalErrorException
	{
		
		Collection<Maquina> list = xarxaSvc.findMaquinaByFiltre(null, null, null, null, null,  null, null, null, null, null, null, false);
		
	}

}
