/*
 * InvalidPasswordException.java
 *
 * Created on May 8, 2000, 11:51 AM
 */

package com.soffid.iam.exception;

/** 
 * Contraseña incorrecta
 *
 * @author  $Author: u07286 $
 * @version  $Revision: 1.1.2.1 $
 */

public class InvalidPasswordException extends Exception {
  /**
   * Creates new <code>InvalidPasswordException</code> without detail message.
   */
  public InvalidPasswordException() {
    super ();
  }
  

  /**
   * Constructs an <code>InvalidPasswordException</code> with the specified detail message.
   * @param msg the detail message.
   */
  public InvalidPasswordException(String msg) {
    super(msg);
  }
}

