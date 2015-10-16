package es.caib.seycon.ng.utils;

public class TipusAutoritzacioPuntEntrada {
	
	// Para distinguir los tipos que pueden ser
	public final static String GRUP = "Grup"; //$NON-NLS-1$
	public final static String ROL = "Rol"; //$NON-NLS-1$
	public final static String USUARI = "Usuari"; //$NON-NLS-1$
	public final static String ACCOUNT = "Account"; //$NON-NLS-1$
	
	// Para convertir la constante a cadena el nivel de autorización
	public final static String NIVELL_A_DESCRIPCIO = "Administrador"; //$NON-NLS-1$
	public final static String NIVELL_A = "A"; //$NON-NLS-1$
	public final static String NIVELL_ALTRES_DESCRIPCIO = "Autoritzat"; //$NON-NLS-1$
	public final static String NIVELL_ALTRES = "C"; //$NON-NLS-1$
	
	/** Acceso de consulta ( y ejecución ) */
	public static final String NIVELL_QUERY_DESCRIPCIO = "Autoritzat"; //$NON-NLS-1$
	/** Acceso de administrador */
	public static final String NIVELL_ADMIN_DESCRIPCIO = "Administrador"; //$NON-NLS-1$
	

}
