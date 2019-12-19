package com.soffid.iam.utils;
import java.net.InetAddress;
import java.net.UnknownHostException;
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
  public byte[] ip;
  /** true si la IP hace referencia a una subred. false si hace referencia a un host */
  public boolean xarxa ;
  /** vector con los cuatro dígitos de la máscara IP (0..3) */
  public byte[] mask;
private String sMask;

  /** incrementar la dirección IP ( generar la siguiente ) */
  public void incrementa () 
  {
    int i = ip.length - 1;
    ip [ i ] = (byte) (ip [ i ] + 1);
    while ( ip [ i ] == 0 && i > 0) 
    {
      i --;
      ip [ i ] ++;
    }
  }

  /**
   * Constructor de una IP de host a partir de la forma textual de la dirección IP
   * @param s cadena con formato aa.bb.cc.dd
   * @throws InvalidIPException cuando la cadena no tiene el formato apropiado
 * @throws UnknownHostException 
   */
  public IPAddress ( String s ) 
    throws InvalidIPException, UnknownHostException 
  {
    xarxa = false;
    mask = null;
    ip = InetAddress.getByName(s).getAddress();
  }

  /**
   * Constructor de una IP de subred a partir de la forma textual de la dirección IP
   * @param s cadena con formato aa.bb.cc.dd representando la dirección de subred
   * @param sMask cadena con formato aa.bb.cc.dd representando la máscara de subred
   * @throws InvalidIPException cuando la cadena no tiene el formato apropiado
 * @throws UnknownHostException 
   */
  public IPAddress ( String s, String sMask ) 
    throws InvalidIPException, UnknownHostException 
  {
    xarxa = true;
    ip = InetAddress.getByName(s).getAddress();
	if ( ! sMask.contains("."))
	{
		this.sMask = sMask;
		int bits = Integer.parseInt(sMask);
		mask = new byte [ip.length];
		for (int i = mask.length-1; i >= 0; i--)
		{
			mask[i] = -1;
			for (int j = 1; j <=  128; j = j * 2 )
			{
				if (bits > 0) {
					mask [i] = (byte) ( mask [i] & ( ~ j ) );
					bits --;
				}
			}
		}
	}
	else
	{
		mask = InetAddress.getByName(sMask).getAddress();
		this.sMask = InetAddress.getByAddress("", mask).getHostAddress();
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
    
    if (ip.length != hostIp.ip.length)
    	return false;
    
	for ( int i = 0; i < ip.length; i++)
	{
		if ( ( mask[i] &  hostIp.ip[i]) != ip[i])
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
    
    for ( int i = 0; i < ip.length ; i++) 
    {
    	int m = mask[i];
    	int ip = hostIp.ip[i];
    	if ( (m | ip) != 255)
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
    for ( int i = 0; i < ip.length ; i++) 
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
    String s;
	try {
		s = InetAddress.getByAddress(ip).getHostAddress();
	} catch (UnknownHostException e) {
		throw new RuntimeException(e);
	}
    if (xarxa) 
      s = s+"/"+sMask;
    return s;
  }

  public int networkSize () {
	  int size = 1;
	  for ( int i = mask.length - 1 ; i >= 0; i-- )
	  {
		  for (int j = 1; j < 256; j += j)
		  {
			  if ( (mask[i] & j) == 0)
				  size += size;
			  else
				  return size;
		  }
	  }
	  return size;
  }
}
