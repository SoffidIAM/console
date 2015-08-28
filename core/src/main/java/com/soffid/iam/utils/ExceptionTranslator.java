package com.soffid.iam.utils;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExceptionTranslator {
	ExceptionTranslator instance = new ExceptionTranslator();	
	
	public static String VALOR_CAMP_MASSA_GRAN = Messages.getString("ExceptionTranslator.BigValueToImput"); //$NON-NLS-1$
	
	private static HashMap tipusNom;
	
	static{
		tipusNom = new HashMap();
			tipusNom.put("APP","Aplicació"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("MAQ", "Màquina"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("UGR", "Associació d'usuari amb grup"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("UIM", "Associació d'usuari amb impressora"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("ULC", "Associació d'usuari amb llista de correu"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("GRU", "Grup"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("GIM", "Associació de grup amb impresora"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("FIT", "Fitxer"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("DCO", "Domini de correu"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("DOM", "Domini d'aplicació"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("DIS", "Dispatcher"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("DUS", "Dades d'usuari"); //$NON-NLS-1$ //$NON-NLS-2$
			tipusNom.put("DAS", "Dades d'usuari"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("CTR", "Contrasenya"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("CON", "Configuració"); //$NON-NLS-1$ //$NON-NLS-2$
			tipusNom.put("APL", "Aplicació"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("ADS", "Administrador de seguretat organitzatiu"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("ADP", "Administració d'aplicació"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("AXA", "Autorització de xarxes"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("AUD", "Auditoria"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("USU", "Usuari"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("TDU", "Tipus de dada d'usuari"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("TCP", "Tarja de CPD"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("RLU", "Associació de rol amb usuari"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("RLF", "Associació de rol amb fitxer"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("ROL", "Rol"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("RCP", "Registre de CPD"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("RAC", "Registre d'accés"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("PCP", "Porta de CPD"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("LLC", "Associació entre dues llistes de correu"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("RES", "Associació entre usuari i responsable de seguretat organitzatiu"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("CTA", "Control de targetes"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("GIM", "Associació entre grup i impressora"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("IMP", "Impressora"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("LCO", "Llistes de correu"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("LCO2", "Llistes de correu"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("PEF", "Petifions de farmàcia"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("TAR", "Targeta"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("TUO", "Tipus d'unitat organitzativa"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("VDO", "Valor de domini"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("XAR", "Xarxa"); //$NON-NLS-1$ //$NON-NLS-2$
		tipusNom.put("TDA", "Tipus de dada d'usuari"); //$NON-NLS-1$ //$NON-NLS-2$

	}
	
	private ExceptionTranslator(){			
	}
	
	public static String translate(Throwable e){
		String sqlError = e.getMessage();
		if(e.getCause() != null){
			sqlError = e.getCause().getMessage();
		}
		if (sqlError != null) {
			// Mostrem el stacktrace al log 
			// (per poder obtindre més informació)
			e.printStackTrace();
		    return getErrorMessage(sqlError);
		} else
		    return e.toString();
	}

	private static String getErrorMessage(String sqlError)
	{
		String missatgeError = getPKUKError(sqlError);
		if(missatgeError == null){
			missatgeError = getFKError(sqlError);
		}
		if(missatgeError == null){
			missatgeError = getValueTooLargeForColumn(sqlError);
		}
		if(missatgeError == null){
			missatgeError = String.format(Messages.getString("ExceptionTranslator.UnknownError"), sqlError);  //$NON-NLS-1$
		}
		return missatgeError;
	}
	
	private static String getValueTooLargeForColumn(String sqlError){
		if(sqlError!=null && sqlError.contains("value too large for column")){  //$NON-NLS-1$
			return VALOR_CAMP_MASSA_GRAN;
		}
		return null;
	}
	
	private static String getPKUKError(String sqlError){
		try {
			String regexp = "([a-zA-Z]{3})_(PK|UK)_"; //$NON-NLS-1$
			Pattern pattern = 
				Pattern.compile(regexp);			
			Matcher matcher = 
	            pattern.matcher(sqlError);
			matcher.find();
            String codiTipus = matcher.group(1);            
            return String.format(Messages.getString("ExceptionTranslator.AlreadyExists"), //$NON-NLS-1$
            		getNomTipus(codiTipus), matcher.group(0), sqlError);
		} catch (Exception e) {
			return null;
		}
	}
	
	private static String getFKError(String sqlError){
		try {
			String regexp = "([a-zA-Z]{3})_([a-zA-Z]{3}[2]?)_FK"; //$NON-NLS-1$
			Pattern pattern = 
				Pattern.compile(regexp);			
			Matcher matcher = 
	            pattern.matcher(sqlError);
			matcher.find();
            String from = matcher.group(1);
            String to = matcher.group(2);
            return String.format(Messages.getString("ExceptionTranslator.DeleteError"), //$NON-NLS-1$
            		getNomTipus(to), getNomTipus(from), matcher.group(0), sqlError);
		} catch (Exception e) {
			return null;
		}
	}
	
	private static String getNomTipus(String prefix) {
		return (String)tipusNom.get(prefix);
	}
}

