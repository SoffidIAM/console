package es.caib.seycon.net;

import java.util.StringTokenizer;

/**
 * Informacion acerca de hosts Un host es cualquier dipositivo conectado a la
 * red, y por tanto, con una direccion IP
 * 
 * @author $Author: u07286 $
 * @version $Revision: 1.1.2.3 $
 */

// $Log: HostInfo.java,v $
// Revision 1.1.2.3  2012-09-25 08:57:19  u07286
// Multiples canvis
//
// Revision 1.1.2.2  2010-06-08 06:49:05  u88683
// Afegim comentaris (m�todes que no s'han d'emprar)
//
// Revision 1.1.2.1  2009-03-23 07:53:05  u89559
// *** empty log message ***
//
// Revision 1.1  2009-03-13 14:19:58  u07286
// Movidas las clases comunes de Seycon Legacy
//
// Revision 1.2  2008-08-12 09:58:10  u89559
// *** empty log message ***
//
// Revision 1.1 2008-05-22 11:52:13 u07286
// Version inicial
//
// Revision 1.1 2007-09-06 12:51:11 u89559
// [T252]
//
// Revision 1.3 2004-03-15 12:30:46 u07286
// Agregado JavaDoc
//
// Revision 1.2.2.1 2004/02/20 12:13:32 u07286
// Comentarios javadoc
public class HostInfo extends es.caib.seycon.ng.sync.intf.HostInfo {
	/**
	 * 
	 */
	public String Mac;
	public boolean serveiCorreu;
	public boolean serveiOfimatic;
	public Long id;

	public HostInfo() {
	}

	//TODO: En teoria s'ha d'eliminar (ja no s'emplea)
	public void setAlies(String nouAlies) {
		if (nouAlies == null)
			alias = new String[0];
		else {
			int elements = 0;
			StringTokenizer st = new StringTokenizer(nouAlies, " ,"); //$NON-NLS-1$
			while (st.hasMoreTokens()) {
				st.nextToken();
				elements++;
			}
			alias = new String[elements];
			elements = 0;
			st = new StringTokenizer(nouAlies, " ,"); //$NON-NLS-1$
			while (st.hasMoreTokens()) {
				alias[elements] = st.nextToken();
				elements++;
			}
		}
	}

	//TODO: En teoria s'ha d'eliminar (ja no s'emplea)
	public String getAlies() {
		String alies = ""; //$NON-NLS-1$
		if (alias == null || alias.length == 0)
			return ""; //$NON-NLS-1$
		else {
			alies = alias[0];
			for (int i = 1; i < alias.length; i++)
				alies = alies + " " + alias[i]; //$NON-NLS-1$
		}
		return alies;
	}
}