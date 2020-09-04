package com.soffid.iam.utils;

public class TipusAutoritzacioPuntEntrada {
	
	// Para distinguir los tipos que pueden ser
	public final static String GRUP = "group"; //$NON-NLS-1$
	public final static String ROL = "role"; //$NON-NLS-1$
	public final static String USUARI = "user"; //$NON-NLS-1$
	public final static String ACCOUNT = "account"; //$NON-NLS-1$
	
	// Para convertir la constante a cadena el nivel de autorización
	public final static String NIVELL_A_DESCRIPCIO = "admin"; //$NON-NLS-1$
	public final static String NIVELL_A = "A"; //$NON-NLS-1$
	public final static String NIVELL_ALTRES_DESCRIPCIO = "exec"; //$NON-NLS-1$
	public final static String NIVELL_ALTRES = "C"; //$NON-NLS-1$
	
	/** Acceso de consulta ( y ejecución ) */
	public static final String NIVELL_QUERY_DESCRIPCIO = "exec"; //$NON-NLS-1$
	/** Acceso de administrador */
	public static final String NIVELL_ADMIN_DESCRIPCIO = "admin"; //$NON-NLS-1$
	

}
