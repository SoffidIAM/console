package es.caib.seycon.ng.ui;

import java.io.Serializable;

import es.caib.seycon.ng.comu.Password;

public class SeyconTask implements Serializable, Comparable {

	private static final long serialVersionUID = 1L;

	// Id de la Tasca
	private java.lang.Long id;

	// Descripció de la tasca
	String descripcioTasca;

	// El estat d'execució de la tasca als diferents agents
	String[] estatExecucioAgents;
	
	// URL agent
	private String urlAgent;
	
	public static class Estat {
		public static String PENDING = "PENDING"; //$NON-NLS-1$
		public static String DONE = "DONE"; //$NON-NLS-1$
		public static String ERROR = "ERROR"; //$NON-NLS-1$
		public static String UNKNOWN = "UNKNOWN"; //$NON-NLS-1$
	}

	public SeyconTask(Long id, String descripcioTasca) {
		this.id = id;
		this.descripcioTasca = descripcioTasca;
	}

	public SeyconTask(Long id, String descripcioTasca, String[] estatExecucioAgents) {
		this.id = id;
		this.descripcioTasca = descripcioTasca;
		this.estatExecucioAgents = estatExecucioAgents;
	}

	// A nivell d'agent: REVISAR
	/** codigo de transacción */
	String transactionCode;
	/** código de usuario */
	String user;
	/** contraseña */
	Password password;
	/** nombre de carpeta */
	String folder;
	/** tipo de carpeta ( U / G ) */
	String folderType;
	/** nombre de cola de impresión */
	String printer;
	/** nombre de máquina */
	String host;
	/** nombre de red */
	String network;
	/** debe cambiar la contraseña */
	String mustChange;
	/** alias de correo */
	String alias;
	/** subdominio de correo */
	String domain;
	/** código de grupo */
	String group;
	/** código de rol */
	String role;
	/** agente (del rol) */
	String bd;
	/** logs de ejecución en cada {@link TaskDispatcher} */
	// TaskLog logs[];
	/** dispatcher en el cual ejecutar */
	String dispatcher;
	/** tarea cancelada */
	boolean cancelled;
	/** resultado de la tarea ValidatePassword */
	boolean validated;
	/** Instante de creación de la tarea */
	java.util.Date date;
	/** tiempo máximo admisible para validar la contraseña */
	long timeout = 0;
	/** contador interno de tareas */
	static int contador = 0;
	/** estado almacenado en la base de datos */
	String savedStatus;
	/** mensaje almacenado en la base de datos */
	String savedMessage;
	/** Servidor que ha generado el mensaje */
	String server;
	/** Hash para evitar tareas duplicadas */
	String hash;
	public java.lang.Long getId() {
		return id;
	}

	public String getDescripcioTasca() {
		return descripcioTasca;
	}

	public void setDescripcioTasca(String descripcioTasca) {
		this.descripcioTasca = descripcioTasca;
	}

	public String[] getEstatExecucioAgents() {
		return estatExecucioAgents;
	}

	public void setEstatExecucioAgents(String[] estatExecucioAgents) {
		this.estatExecucioAgents = estatExecucioAgents;
	}

	public int compareTo(Object o) {
		if (o instanceof SeyconTask && getDescripcioTasca() != null)
			return getDescripcioTasca().compareTo(
					((SeyconTask) o).getDescripcioTasca());
		return 0;
	}

	/**
	 * @return the urlAgent
	 */
	public String getUrlAgent ()
	{
		return urlAgent;
	}

	/**
	 * @param urlAgent the urlAgent to set
	 */
	public void setUrlAgent (String urlAgent)
	{
		this.urlAgent = urlAgent;
	}

}
