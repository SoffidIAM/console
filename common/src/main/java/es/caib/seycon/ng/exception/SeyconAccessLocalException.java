package es.caib.seycon.ng.exception;


/**
 * Classe per generar una excepció d'accés sense autorització a un mètode
 * protegit per autoritzacions del SEU
 * 
 * Alejandro Usero Ruiz - 19 d'agost de 2011
 * 
 * @author u88683
 */
public class SeyconAccessLocalException extends SecurityException {

	private static final long serialVersionUID = 1L;

	// Generem el missatge d'error
	private static String generateError(String ejbName, String method,
			String requiredRoles, String msg) {
		return String.format(
		        Messages.getString("SeyconAccessLocalException.InsufficientPermissions"), //$NON-NLS-1$
		        ejbName, method, requiredRoles, (msg != null ? "\n"+msg : "")); //$NON-NLS-1$ //$NON-NLS-2$
	}

	/**
	 * Llancem una excepció de falta d'autoritzacions.
	 * @param ejbName
	 * @param method
	 * @param requiredRoles
	 */
	public SeyconAccessLocalException(String ejbName, String method,
			String requiredRoles) {
		// el requiredRoles: consultar el ejb-jar.xml
		super(generateError(ejbName, method, requiredRoles, null));
	}

	/**
	 * Llancem una excepció de falta d'autoritzacions. Amb missatge adicional
	 * @param ejbName
	 * @param method
	 * @param requiredRoles
	 * @param msg
	 */
	public SeyconAccessLocalException(String ejbName, String method,
			String requiredRoles, String msg) {
		// el requiredRoles: consultar el ejb-jar.xml
		super(generateError(ejbName, method, requiredRoles, msg));
	}


	public String getMessage() {
		return super.getMessage();
	}


}
