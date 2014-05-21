package es.caib.seycon.ng.utils;
import java.util.StringTokenizer;

/**
 * Implementación de una dirección IP con funciones de manipulación de subredes
 * @author $Author: u89559 $
 * @version $Revision: 1.1 $
 */

// $Log: IPAddress.java,v $
// Revision 1.1  2008-03-13 08:04:22  u89559
// [T390] version con bugs resueltos
//
// Revision 1.2  2004-03-16 10:44:26  u07286
// Agregada informacion Javadoc y gestion de Puntos de Entrada
//

public class IPAddress 
{
  /** vector con los cuatro dígitos de la dirección IP (0..3) */
  public int ip [];
  /** true si la IP hace referencia a una subred. false si hace referencia a un host */
  public boolean xarxa ;
  /** vector con los cuatro dígitos de la máscara IP (0..3) */
  public int mask [];

  /** incrementar la dirección IP ( generar la siguiente ) */
  public void incrementa () 
  {
    int i = 3;
    ip [ i ] = ip [ i ] + 1;
    while ( ip [ i ] == 256 && i >= 0) 
    {
      ip [ i ] = 0;
      i --;
      ip [ i ] ++;
    }
  }

  /** Traduce una cadena a un vector de cuatro dígitos 
   * @param s cadena con formato aa.bb.cc.dd
   * @param ip vector con cuatro enteros que albergará la cadena analizada
   * @throws InvalidIPException cuando la cadena no tiene el formato apropiado
   */
  private void tradueix (String s, int ip[])
    throws InvalidIPException 
  {
    if (s == null) throw new InvalidIPException (Messages.getString("IPAddress.NullIP")); //$NON-NLS-1$
    StringTokenizer tokenizer = new StringTokenizer (s, ".", false); //$NON-NLS-1$
    int i = 0;
    while (tokenizer.hasMoreTokens()) 
    {
      String token = tokenizer.nextToken();
      if ( i >= 4 ) throw new InvalidIPException ( Messages.getString("IPAddress.InvalidLenght")); //$NON-NLS-1$
      try {
        int x = new Integer (token).intValue ();
        if ( x > 255 || x < 0 )
          throw new InvalidIPException (String.format(Messages.getString("IPAddress.IncorrectDigit"), x));   //$NON-NLS-1$
        ip [ i ++ ] = x;
      } catch (NumberFormatException e) {
        throw new InvalidIPException (String.format(Messages.getString("IPAddress.NotANumber"), token)); //$NON-NLS-1$
      }
    }
  }

  /**
   * Constructor de una IP de host a partir de la forma textual de la dirección IP
   * @param s cadena con formato aa.bb.cc.dd
   * @throws InvalidIPException cuando la cadena no tiene el formato apropiado
   */
  public IPAddress ( String s ) 
    throws InvalidIPException 
  {
    xarxa = false;
    mask = null;
    ip = new int [ 4 ];
    tradueix (s, ip);
  }

  /**
   * Constructor de una IP de subred a partir de la forma textual de la dirección IP
   * @param s cadena con formato aa.bb.cc.dd representando la dirección de subred
   * @param sMask cadena con formato aa.bb.cc.dd representando la máscara de subred
   * @throws InvalidIPException cuando la cadena no tiene el formato apropiado
   */
  public IPAddress ( String s, String sMask ) 
    throws InvalidIPException 
  {
    xarxa = true;
    mask = new int [ 4 ];
    ip = new int [ 4 ];
    tradueix (s, ip);
    tradueix (sMask, mask);
    for (int i = 0; i < 4; i++)
    {
      if ( ( ip [ i ] & mask [ i ] ) != ip [ i ] )
        throw new InvalidIPException (String.format(Messages.getString("IPAddress.InvalidMasc"), s)); //$NON-NLS-1$
    }
  }

  /**
   * Verificar si la dirección IP (de subred) contiene la dirección (de host)
   * @param s cadena con formato aa.bb.cc.dd representando la dirección de subred
   * @param sMask cadena con formato aa.bb.cc.dd representando la máscara de subred
   * @throws InvalidIPException cuando la cadena no tiene el formato apropiado
   */
  public boolean conte ( IPAddress hostIp )
  {
    if ( ! xarxa ) return false;
    for ( int i = 0; i < 4 ; i++) 
    {
      if ( (hostIp.ip[i]  & mask [i]) != ip [i] )
        return false;
    }
    return true;
  }

  /** determina si la dirección pasada como parámetro se corresponde a la 
   * dirección de broadcast de la subred a la que hace referencia el objeto
   * invocante
   * @param hostIP dirección supuestamente de broadcast
   * @return true si hostIP es broadcast de la subred invocante
   */
  public boolean esBroadcast ( IPAddress hostIp )
  {
    if ( ! conte (hostIp)) return false;
    
    for ( int i = 0; i < 4 ; i++) 
    {
      if ( (255 & hostIp.ip[i]  & ( ~ mask [i]) ) != ( 255 & (~ mask [i]) ))
        return false;
    }
    return true;
  }

  /**
   * true si la dirección IP es la direccción propia de la subred a la que
   * hace referencia el objeto invocante
   * @param hostIP dirección supuestamente de subred
   * @return true si hostIP es dirección de subred
   */
  public boolean esXarxa ( IPAddress hostIp )
  {
    for ( int i = 0; i < 4 ; i++) 
    {
      if ( hostIp.ip[i] != ip [ i ] )
        return false;
    }
    return true;
  }

  /**
   * Determinar si una IP es válida para un host dentro de una subred
   * @param hostIP dirección supuestamente válida
   * @return true si es una dirección dentro del rango y que no pertenece
   * a broadcast ni a la subred
   */
  public boolean esHostValid (IPAddress hostIp)
  {
    return conte ( hostIp) && ! esXarxa (hostIp) && ! esBroadcast (hostIp);
  }


  /** Convertir la dirección IP a texto
   * @return cadena con formato aa.bb.cc.dd (/ee.ff.gg.hh)
   */
  public String toString ()
  {
    String s = ""+ip[0]+"."+ip[1]+"."+ip[2]+"."+ip[3]; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    if (xarxa) 
      s = s+"/"+mask[0]+"."+mask[1]+"."+mask[2]+"."+mask[3]; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    return s;
  }

}
