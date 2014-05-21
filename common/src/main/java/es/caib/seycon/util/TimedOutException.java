
// Copyright (c) 2000 Govern  de les Illes Balears
package es.caib.seycon.util;


/**
 * Excepción por proceso sin finalizar en el tiempo establecido
 * <P>
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.2 $
 * @see TimedProcess
 */
@SuppressWarnings("serial")
public class TimedOutException extends Exception
{

  /**
   * Constructor
   */
  public TimedOutException()
  {
  }
  /**
   * Genera una TimedOutException con el texto indicado
   * @param msg texto de la excepción
   */
  public TimedOutException(String msg) {
    super(msg);
  }
}

 