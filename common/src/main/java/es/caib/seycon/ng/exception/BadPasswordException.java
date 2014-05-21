package es.caib.seycon.ng.exception;

/*
 * Contraseña no apropiada ( no cumple los requisitos de seguridad) 
 *
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.1 $
 */
 

public class BadPasswordException extends Exception {
  /**
   * Crea una <code>BadPasswordException</code> sin mensaje.
   */
  public BadPasswordException() {
    super ();
  }
  

  /**
   * Construye <code>BadPasswordException</code> con el mensaje especificado.
   * @param msg mensaje a retornar
   */
  public BadPasswordException(String msg) {
    super(msg);
  }
}

