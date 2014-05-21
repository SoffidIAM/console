package es.caib.bpm.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Esta clase nos permite trabajar con las fechas
 */

public class FechaUtils 
{
	/**
	 * Dada una fecha la transforma para que conincida con el comienzo del día
	 * 
	 * @param fechaResultado
	 * @return
	 * @throws Exception
	 */
	public static Date establecerFechaInicioDia (Date fechaResultado) throws Exception
	{
		//declaramos
		Calendar calAuxiliar= new GregorianCalendar();
	
		//establecemos la fecha en el calendario
		calAuxiliar.setTime(fechaResultado);
		
		//establecemos el principio del día
		calAuxiliar.set(Calendar.HOUR_OF_DAY, 0);
		calAuxiliar.set(Calendar.MINUTE, 0);
		calAuxiliar.set(Calendar.SECOND, 0);
		calAuxiliar.set(Calendar.MILLISECOND, 0);
		
		//retornamos la fecha de inicio del día
		return calAuxiliar.getTime();
	}
	/**
	 * Dada una fecha la transforma para que conincida con el final del día
	 * 
	 * @param fechaResultado
	 * @return
	 * @throws Exception
	 */
	public static Date establecerFechaFinDia(Date fechaResultado) throws Exception
	{
		//declaramos
		Calendar calAuxiliar= new GregorianCalendar();
	
		//establecemos la fecha en el calendario
		calAuxiliar.setTime(fechaResultado);
		
		//establecemos el principio del día
		calAuxiliar.set(Calendar.HOUR_OF_DAY, 23);
		calAuxiliar.set(Calendar.MINUTE,59);
		calAuxiliar.set(Calendar.SECOND, 59);
		calAuxiliar.set(Calendar.MILLISECOND, 59);
		
		//retornamos la fecha de inicio del día
		return calAuxiliar.getTime();
	}
}
