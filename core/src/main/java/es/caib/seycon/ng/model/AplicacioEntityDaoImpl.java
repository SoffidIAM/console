// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package es.caib.seycon.ng.model;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import es.caib.bpm.servei.BpmEngine;
import es.caib.bpm.vo.PredefinedProcessType;
import es.caib.seycon.ng.comu.Auditoria;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.comu.ValorDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;
import es.caib.seycon.ng.utils.ExceptionTranslator;
import es.caib.seycon.ng.utils.Security;
/**
 * @see es.caib.seycon.ng.model.AplicacioEntity
 */
public class AplicacioEntityDaoImpl
    extends es.caib.seycon.ng.model.AplicacioEntityDaoBase
{	
	
	private void auditarAplicacions(String accio, String codiAplicacio) throws InternalErrorException {
		String codiUsuari = Security.getCurrentAccount(); //$NON-NLS-1$
		Auditoria auditoria = new Auditoria();
		auditoria.setAccio(accio);
		auditoria.setAplicacio(codiAplicacio);
		auditoria.setAutor(codiUsuari);
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd/MM/yyyy kk:mm:ss"); //$NON-NLS-1$
		auditoria.setData(dateFormat.format(GregorianCalendar.getInstance()
				.getTime()));
		auditoria.setObjecte("SC_APLICA"); //$NON-NLS-1$

		AuditoriaEntity auditoriaEntity = getAuditoriaEntityDao()
				.auditoriaToEntity(auditoria);
		getAuditoriaEntityDao().create(auditoriaEntity);
	}
	
	public void create(
			es.caib.seycon.ng.model.AplicacioEntity aplicacio)
			throws RuntimeException {
		try {						
			super.create(aplicacio);
			getSession(false).flush();
			auditarAplicacions("C", aplicacio.getCodi()); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AplicacioEntityDaoImpl.0"),  //$NON-NLS-1$
					aplicacio.getCodi(), 
					message));
		}
	}
	
	public void update(AplicacioEntity aplicacio) {
		try {
			super.update(aplicacio);
			getSession(false).flush();
			auditarAplicacions("U", aplicacio.getCodi()); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AplicacioEntityDaoImpl.1"),  //$NON-NLS-1$
					aplicacio.getCodi(),
					message));
		}
	}
	
	public void remove(
			es.caib.seycon.ng.model.AplicacioEntity aplicacio)
			throws RuntimeException {
		try {
			String codiAplicacio = aplicacio.getCodi();
			super.remove(aplicacio);
			getSession(false).flush();
			auditarAplicacions("D", codiAplicacio);			 //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("AplicacioEntityDaoImpl.2"), //$NON-NLS-1$
					aplicacio.getCodi(), 
					message));
		}
	}

	
	/**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#toAplicacio(es.caib.seycon.ng.model.AplicacioEntity)
     */
    public es.caib.seycon.ng.comu.ValorDomini toValorDomini(final es.caib.seycon.ng.model.AplicacioEntity entity)
    {
    	ValorDomini valorDomini = new ValorDomini();
    	valorDomini.setCodiExternDomini(null);
    	valorDomini.setDescripcio(entity.getNom());
    	valorDomini.setNomDomini(TipusDomini.APLICACIONS);
    	valorDomini.setValor(entity.getCodi());
    	return valorDomini;
    }
	
    /**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#toAplicacio(es.caib.seycon.ng.model.AplicacioEntity, es.caib.seycon.ng.comu.Aplicacio)
     */
    public void toAplicacio(
        es.caib.seycon.ng.model.AplicacioEntity sourceEntity,
        es.caib.seycon.ng.comu.Aplicacio targetVO)
    {
        // @todo verify behavior of toAplicacio
        super.toAplicacio(sourceEntity, targetVO);
        UsuariEntity usuariEntity = sourceEntity.getPersonaContacte();
        if(usuariEntity != null){
        	targetVO.setCodiPersonaContacte(usuariEntity.getCodi());
    		String nom = usuariEntity.getNom();
    		nom = nom != null ? nom : ""; //$NON-NLS-1$
    		String primerCognom = usuariEntity.getPrimerLlinatge();
    		primerCognom = primerCognom != null ? primerCognom : ""; //$NON-NLS-1$
    		String segonCognom = usuariEntity.getSegonLlinatge();
    		segonCognom = segonCognom != null ? segonCognom : ""; //$NON-NLS-1$
    		targetVO.setNomComplertPersonaContacte(nom + " " + primerCognom + " " //$NON-NLS-1$ //$NON-NLS-2$
    				+ segonCognom);
        }
        toAplicacioCustom(sourceEntity, targetVO);
    }
    
    
    private void toAplicacioCustom(es.caib.seycon.ng.model.AplicacioEntity sourceEntity,
            es.caib.seycon.ng.comu.Aplicacio targetVO)
    {
		targetVO.setGestionableWF(new Boolean(
				sourceEntity.getGestionableWF().compareTo("S") == 0)); //$NON-NLS-1$
    }


    /**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#toAplicacio(es.caib.seycon.ng.model.AplicacioEntity)
     */
    public es.caib.seycon.ng.comu.Aplicacio toAplicacio(final es.caib.seycon.ng.model.AplicacioEntity entity)
    {
        // @todo verify behavior of toAplicacio
        return super.toAplicacio(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private es.caib.seycon.ng.model.AplicacioEntity loadAplicacioEntityFromAplicacio(es.caib.seycon.ng.comu.Aplicacio aplicacio)
    {
        
    	AplicacioEntity aplicacioEntity = null;
    	if(aplicacio.getId() != null){
    		aplicacioEntity = load(aplicacio.getId());
    	}        
        if (aplicacioEntity == null)
        {
            aplicacioEntity = newAplicacioEntity();
        }
        return aplicacioEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#aplicacioToEntity(es.caib.seycon.ng.comu.Aplicacio)
     */
    public es.caib.seycon.ng.model.AplicacioEntity aplicacioToEntity(es.caib.seycon.ng.comu.Aplicacio aplicacio)
    {
        // @todo verify behavior of aplicacioToEntity
        es.caib.seycon.ng.model.AplicacioEntity entity = this.loadAplicacioEntityFromAplicacio(aplicacio);
        this.aplicacioToEntity(aplicacio, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#aplicacioToEntity(es.caib.seycon.ng.comu.Aplicacio)
     */
    public es.caib.seycon.ng.model.AplicacioEntity valorDominiToEntity(es.caib.seycon.ng.comu.ValorDomini valorDomini)
    {
    	throw new SeyconException(Messages.getString("AplicacioEntityDaoImpl.3")); //$NON-NLS-1$
    }
    
    /**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#aplicacioToEntity(es.caib.seycon.ng.comu.Aplicacio, es.caib.seycon.ng.model.AplicacioEntity)
     */
    public void aplicacioToEntity(
        es.caib.seycon.ng.comu.Aplicacio sourceVO,
        es.caib.seycon.ng.model.AplicacioEntity targetEntity,
        boolean copyIfNull)
    {
        // @todo verify behavior of aplicacioToEntity
        super.aplicacioToEntity(sourceVO, targetEntity, copyIfNull);
        String codiPersonaContacte = sourceVO.getCodiPersonaContacte();
        if(codiPersonaContacte != null){
        	if("".equals(codiPersonaContacte.trim())){ //$NON-NLS-1$
            	targetEntity.setPersonaContacte(null);
            	
            }else{	        	
	        	UsuariEntity usuariEntity = getUsuariEntityDao().findByCodi(codiPersonaContacte);
	        	if(usuariEntity != null){
	        		targetEntity.setPersonaContacte(usuariEntity);
	        	}else{
					throw new SeyconException(String.format(Messages.getString("AplicacioEntityDaoImpl.4"),  //$NON-NLS-1$
							codiPersonaContacte));
	        	} 
            }
        }
        aplicacioToEntityCustom(sourceVO, targetEntity);
    }
    
	private void aplicacioToEntityCustom(
			es.caib.seycon.ng.comu.Aplicacio sourceVO,
			es.caib.seycon.ng.model.AplicacioEntity targetEntity) {
		Boolean gestionableWF = sourceVO.getGestionableWF();
		if (gestionableWF != null) {
			targetEntity.setGestionableWF(sourceVO.getGestionableWF()
					.booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			targetEntity.setGestionableWF("N"); //$NON-NLS-1$
		}
		// Verifiquem les adreces de correu per rebre les notificacions
		if (sourceVO.getCorreusNotificacions()!=null && !"".equals(sourceVO.getCorreusNotificacions())) { //$NON-NLS-1$
			String correusFormatejats = ""; //$NON-NLS-1$
			// Validamos las direcciones
			String valor = sourceVO.getCorreusNotificacions();
			if (valor==null || "".equals(valor.trim())) return; //$NON-NLS-1$
			String [] valors = valor.split(","); //$NON-NLS-1$
			if (valors.length>=1) {
				java.util.regex.Pattern p = java.util.regex.Pattern.compile("[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z]+)+"); //$NON-NLS-1$
				for (int i=0; i < valors.length; i++) {
					if (valors[i]!=null) {
						java.util.regex.Matcher m = p.matcher(valors[i].trim());
					   	boolean matchFound = m.matches();
						java.util.StringTokenizer st = new java.util.StringTokenizer(valors[i], "."); //$NON-NLS-1$
							String lastToken = null;
					   	while (st.hasMoreTokens()) {
					    	lastToken = st.nextToken();
					   	}
					   	if (matchFound && lastToken.length() > 1
					      && valors[i].length() - 1 != lastToken.length()) {
					   		if (correusFormatejats.length()!=0) correusFormatejats+=","; //$NON-NLS-1$
							correusFormatejats+=valors[i].trim();
					   	}
					   	else {
							throw new SeyconException(String.format(
									Messages.getString("AplicacioEntityDaoImpl.5"), //$NON-NLS-1$
									valors[i]));
					   	}
					 }
				}//-for
				targetEntity.setCorreusNotificacions(correusFormatejats); //eliminem espais innecesaris
			}			
			
		}
		
		// Check aproval process
		if (sourceVO.getApprovalProcess() == null)
			targetEntity.setApprovalProcess(null);
		else
		{
			BpmEngine bpm = getBpmEngine ();
			List definitions;
			try
			{
				definitions = bpm.findProcessDefinitions(sourceVO.getApprovalProcess(), PredefinedProcessType.ROLE_APPROVAL);
				if (definitions.isEmpty())
				{
					throw new SeyconException(String.format("Unknown workflow '%s'", sourceVO.getApprovalProcess()));
				}
			}
			catch (InternalErrorException e)
			{
				throw new SeyconException (e.toString());
			}
		}

	}    
 
	
	public void create(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof AplicacioEntity) {
				AplicacioEntity entity = (AplicacioEntity) obj;
				this.create(entity); // cridem al mètode 1 per 1
			}
		} 
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof AplicacioEntity) {
				AplicacioEntity entity = (AplicacioEntity) obj;
				this.update(entity);// cridem al mètode 1 per 1
			}
		}
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext();) {
			Object obj = it.next();
			if (obj instanceof AplicacioEntity) {
				AplicacioEntity entity = (AplicacioEntity) obj;
				this.remove(entity);// cridem al mètode 1 per 1
			}
		}
	}
	
	    /**
	     * @see es.caib.seycon.ng.model.AplicacioEntityDao#findUsuarisByNomRolAndCodiAplicacioRolAndCodiDispatcher(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	     */
	    public java.util.List<es.caib.seycon.ng.model.AplicacioEntity> findUsuarisByNomRolAndCodiAplicacioRolAndCodiDispatcher(final java.lang.String queryString, final java.lang.String nomRole, final java.lang.String codiAplicacioRol, final java.lang.String codiDispatcher, final java.lang.String numRegistres)
	    {

	        try
	        {
	            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
	            queryObject.setParameter("nomRole", nomRole); //$NON-NLS-1$
	            queryObject.setParameter("codiAplicacioRol", codiAplicacioRol); //$NON-NLS-1$
	            queryObject.setParameter("codiDispatcher", codiDispatcher); //$NON-NLS-1$
	            if (numRegistres != null && numRegistres.length() > 0)
	                queryObject.setMaxResults(Integer.decode(numRegistres).intValue());
	            java.util.List results = queryObject.list();
	            return (java.util.List<es.caib.seycon.ng.model.AplicacioEntity>)results;
	        }
	        catch (org.hibernate.HibernateException ex)
	        {
	            throw super.convertHibernateAccessException(ex);
	        }
	    }

}