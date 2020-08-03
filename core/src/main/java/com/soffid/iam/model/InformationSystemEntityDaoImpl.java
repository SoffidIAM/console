// license-header java merge-point
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
/**
 * This is only generated once! It will never be overwritten.
 * You can (and have to!) safely modify it by hand.
 */
package com.soffid.iam.model;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.soffid.iam.api.ApplicationType;
import com.soffid.iam.api.Audit;
import com.soffid.iam.api.DomainValue;
import com.soffid.iam.bpm.service.BpmEngine;
import com.soffid.iam.model.criteria.CriteriaSearchConfiguration;
import com.soffid.iam.utils.ExceptionTranslator;
import com.soffid.iam.utils.Security;

import es.caib.bpm.vo.PredefinedProcessType;
import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SeyconException;

/**
 * @see es.caib.seycon.ng.model.AplicacioEntity
 */
public class InformationSystemEntityDaoImpl
    extends com.soffid.iam.model.InformationSystemEntityDaoBase
{	
	
	private void auditarAplicacions(String accio, String codiAplicacio) throws InternalErrorException {
		String codiUsuari = Security.getCurrentAccount(); //$NON-NLS-1$
		Audit auditoria = new Audit();
		auditoria.setAction(accio);
		auditoria.setApplication(codiAplicacio);
		auditoria.setAuthor(codiUsuari);
		auditoria.setObject("SC_APLICA"); //$NON-NLS-1$

		AuditEntity auditoriaEntity = getAuditEntityDao().auditToEntity(auditoria);
		getAuditEntityDao().create(auditoriaEntity);
	}
	
	public void create(com.soffid.iam.model.InformationSystemEntity aplicacio) throws RuntimeException {
		try {						
			super.create(aplicacio);
			getSession(false).flush();
			auditarAplicacions("C", aplicacio.getName()); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("InformationSystemEntityDaoImpl.0"), aplicacio.getName(), message));
		}
	}
	
	public void update(InformationSystemEntity aplicacio) {
		try {
			super.update(aplicacio);
			getSession(false).flush();
			auditarAplicacions("U", aplicacio.getName()); //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("InformationSystemEntityDaoImpl.1"), aplicacio.getName(), message));
		}
	}
	
	public void remove(com.soffid.iam.model.InformationSystemEntity aplicacio) throws RuntimeException {
		try {
			String codiAplicacio = aplicacio.getName();
			super.remove(aplicacio);
			getSession(false).flush();
			auditarAplicacions("D", codiAplicacio);			 //$NON-NLS-1$
		} catch (Throwable e) {
			String message = ExceptionTranslator.translate(e);
			throw new SeyconException(String.format(Messages.getString("InformationSystemEntityDaoImpl.2"), aplicacio.getName(), message));
		}
	}

	
	/**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#toAplicacio(es.caib.seycon.ng.model.AplicacioEntity)
     */
    public com.soffid.iam.api.DomainValue toDomainValue(final com.soffid.iam.model.InformationSystemEntity entity) {
    	DomainValue valorDomini = new DomainValue();
    	valorDomini.setExternalCodeDomain(null);
    	valorDomini.setDescription(entity.getDescription());
    	valorDomini.setDomainName(TipusDomini.APLICACIONS);
    	valorDomini.setValue(entity.getName());
    	return valorDomini;
    }
	
    /**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#toAplicacio(es.caib.seycon.ng.model.AplicacioEntity, es.caib.seycon.ng.comu.Aplicacio)
     */
    public void toApplication(com.soffid.iam.model.InformationSystemEntity sourceEntity, com.soffid.iam.api.Application targetVO) {
        // @todo verify behavior of toAplicacio
        super.toApplication(sourceEntity, targetVO);
        UserEntity usuariEntity = sourceEntity.getContactPerson();
        if(usuariEntity != null){
        	targetVO.setOwner(usuariEntity.getUserName());
    		String nom = usuariEntity.getFirstName();
    		nom = nom != null ? nom : ""; //$NON-NLS-1$
    		String primerCognom = usuariEntity.getLastName();
    		primerCognom = primerCognom != null ? primerCognom : ""; //$NON-NLS-1$
    		String segonCognom = usuariEntity.getMiddleName();
    		segonCognom = segonCognom != null ? segonCognom : ""; //$NON-NLS-1$
    		targetVO.setOwnerName(nom + " " + primerCognom + " " + segonCognom);
        }
        toAplicacioCustom(sourceEntity, targetVO);
    }
    
    
    private void toAplicacioCustom(com.soffid.iam.model.InformationSystemEntity sourceEntity, com.soffid.iam.api.Application targetVO) {
		targetVO.setBpmEnforced(new Boolean(sourceEntity.getWfManagement().compareTo("S") == 0)); //$NON-NLS-1$

		targetVO.setAttributes(new HashMap<String, Object>());
		Map<String, Object> attributes = targetVO.getAttributes();
		for (ApplicationAttributeEntity att : sourceEntity.getAttributes()) {
			if (att.getMetadata().getMultiValued() != null && att.getMetadata().getMultiValued().booleanValue())
			{
				LinkedList<Object> r = (LinkedList<Object>) attributes.get(att.getMetadata().getName());
				if (r == null)
				{
					r = new LinkedList<Object>();
					attributes.put(att.getMetadata().getName(), r);
				}
				r.add(att.getObjectValue());
			}
			else
			{
				attributes.put(att.getMetadata().getName(),att.getObjectValue());
			}
		}

		for (Object o: attributes.values())
		{
			if (o != null && o instanceof List) Collections.sort((List) o);
		}
		if (targetVO.getType() == null)
			targetVO.setType(ApplicationType.APPLICATION);
    }


    /**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#toAplicacio(es.caib.seycon.ng.model.AplicacioEntity)
     */
    public com.soffid.iam.api.Application toApplication(final com.soffid.iam.model.InformationSystemEntity entity) {
        // @todo verify behavior of toAplicacio
        return super.toApplication(entity);
    }


    /**
     * Retrieves the entity object that is associated with the specified value object
     * from the object store. If no such entity object exists in the object store,
     * a new, blank entity is created
     */
    private com.soffid.iam.model.InformationSystemEntity loadAplicacioEntityFromAplicacio(com.soffid.iam.api.Application aplicacio) {
        
    	InformationSystemEntity aplicacioEntity = null;
    	if(aplicacio.getId() != null){
    		aplicacioEntity = load(aplicacio.getId());
    	}        
        if (aplicacioEntity == null)
        {
            aplicacioEntity = newInformationSystemEntity();
        }
        return aplicacioEntity;
    }

    
    /**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#aplicacioToEntity(es.caib.seycon.ng.comu.Aplicacio)
     */
    public com.soffid.iam.model.InformationSystemEntity applicationToEntity(com.soffid.iam.api.Application aplicacio) {
        // @todo verify behavior of aplicacioToEntity
        com.soffid.iam.model.InformationSystemEntity entity = this.loadAplicacioEntityFromAplicacio(aplicacio);
        this.applicationToEntity(aplicacio, entity, true);
        return entity;
    }

    /**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#aplicacioToEntity(es.caib.seycon.ng.comu.Aplicacio)
     */
    public com.soffid.iam.model.InformationSystemEntity domainValueToEntity(com.soffid.iam.api.DomainValue valorDomini) {
    	throw new SeyconException(Messages.getString("InformationSystemEntityDaoImpl.3")); //$NON-NLS-1$
    }
    
    /**
     * @see es.caib.seycon.ng.model.AplicacioEntityDao#aplicacioToEntity(es.caib.seycon.ng.comu.Aplicacio, es.caib.seycon.ng.model.AplicacioEntity)
     */
    public void applicationToEntity(com.soffid.iam.api.Application sourceVO, com.soffid.iam.model.InformationSystemEntity targetEntity, boolean copyIfNull) {
        // @todo verify behavior of aplicacioToEntity
        super.applicationToEntity(sourceVO, targetEntity, copyIfNull);
        String codiPersonaContacte = sourceVO.getOwner();
        if(codiPersonaContacte != null){
        	if("".equals(codiPersonaContacte.trim())){ //$NON-NLS-1$
            	targetEntity.setContactPerson(null);
            	
            }else{	        	
	        	UserEntity usuariEntity = getUserEntityDao().findByUserName(codiPersonaContacte);
	        	if(usuariEntity != null){
	        		targetEntity.setContactPerson(usuariEntity);
	        	}else{
					throw new SeyconException(String.format(Messages.getString("InformationSystemEntityDaoImpl.4"),  //$NON-NLS-1$
							codiPersonaContacte));
	        	} 
            }
        }
        aplicacioToEntityCustom(sourceVO, targetEntity);
    }
    
	private void aplicacioToEntityCustom(com.soffid.iam.api.Application sourceVO, com.soffid.iam.model.InformationSystemEntity targetEntity) {
		Boolean gestionableWF = sourceVO.getBpmEnforced();
		if (gestionableWF != null) {
			targetEntity.setWfManagement(sourceVO.getBpmEnforced().booleanValue() ? "S" : "N"); //$NON-NLS-1$ //$NON-NLS-2$
		} else {
			targetEntity.setWfManagement("N"); //$NON-NLS-1$
		}
		if (sourceVO.getParent() != null && ! sourceVO.getParent().trim().isEmpty()) {
			targetEntity.setParent( findByCode(sourceVO.getParent()));
			if (targetEntity.getParent() == null)
				throw new RuntimeException("Cannot find parent application "+sourceVO.getParent());
		} else {
			targetEntity.setParent(null);
		}
		// Verifiquem les adreces de correu per rebre les notificacions
		if (sourceVO.getNotificationEmails() != null && !"".equals(sourceVO.getNotificationEmails())) { //$NON-NLS-1$
			String correusFormatejats = ""; //$NON-NLS-1$
			// Validamos las direcciones
			String valor = sourceVO.getNotificationEmails();
			if (valor==null || "".equals(valor.trim())) return; //$NON-NLS-1$
			String [] valors = valor.split(","); //$NON-NLS-1$
			if (valors.length>=1) {
				java.util.regex.Pattern p = java.util.regex.Pattern.compile("[a-zA-Z0-9_.]+@[a-zA-Z0-9_]+(\\.[a-zA-Z]+)+"); //$NON-NLS-1$
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
									Messages.getString("InformationSystemEntityDaoImpl.5"), //$NON-NLS-1$
									valors[i]));
					   	}
					 }
				}//-for
				targetEntity.setNotificationMail(correusFormatejats); //eliminem espais innecesaris
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
				definitions = bpm.findProcessDefinitions(sourceVO.getApprovalProcess(), PredefinedProcessType.ROLE_GRANT_APPROVAL);
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
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof InformationSystemEntity) {
                InformationSystemEntity entity = (InformationSystemEntity) obj;
                this.create(entity);
            }
        } 
	}

	public void update(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof InformationSystemEntity) {
                InformationSystemEntity entity = (InformationSystemEntity) obj;
                this.update(entity);
            }
        }
	}

	public void remove(Collection entities) {
		if (entities!=null) for (Iterator it = entities.iterator(); it.hasNext(); ) {
            Object obj = it.next();
            if (obj instanceof InformationSystemEntity) {
                InformationSystemEntity entity = (InformationSystemEntity) obj;
                this.remove(entity);
            }
        }
	}
	
	    /**
	     * @see es.caib.seycon.ng.model.AplicacioEntityDao#findUsuarisByNomRolAndCodiAplicacioRolAndCodiDispatcher(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	     */
	    public java.util.List<com.soffid.iam.model.InformationSystemEntity> findUsuarisByNomRolAndCodiAplicacioRolAndCodiDispatcher(final java.lang.String queryString, final java.lang.String nomRole, final java.lang.String codiAplicacioRol, final java.lang.String codiDispatcher, final java.lang.String numRegistres) {

	        try
	        {
	            org.hibernate.Query queryObject = super.getSession(false).createQuery(queryString);
	            queryObject.setParameter("tenantId", Security.getCurrentTenantId()); //$NON-NLS-1$
	            queryObject.setParameter("nomRole", nomRole); //$NON-NLS-1$
	            queryObject.setParameter("codiAplicacioRol", codiAplicacioRol); //$NON-NLS-1$
	            queryObject.setParameter("codiDispatcher", codiDispatcher); //$NON-NLS-1$
	            if (numRegistres != null && numRegistres.length() > 0)
	                queryObject.setMaxResults(Integer.decode(numRegistres).intValue());
	            java.util.List results = queryObject.list();
	            return (java.util.List<com.soffid.iam.model.InformationSystemEntity>) results;
	        }
	        catch (org.hibernate.HibernateException ex)
	        {
	            throw super.convertHibernateAccessException(ex);
	        }
	    }

		@Override
		public Collection<InformationSystemEntity> findByText(CriteriaSearchConfiguration criteria, String text) {
			String[] split = text.trim().split(" +");
			Parameter[] params = new Parameter[split.length + 1];
			
			StringBuffer sb = new StringBuffer("select u "
					+ "from com.soffid.iam.model.InformationSystemEntity as u "
					+ "where u.tenant.id = :tenantId");
			params[0] = new Parameter("tenantId", Security.getCurrentTenantId());
			for (int i = 0; i < split.length; i++)
			{
				sb.append(" and ");
				params[i+1] = new Parameter("param"+i, "%"+split[i].toUpperCase()+"%");
				sb.append("(upper(u.name) like :param")
					.append(i)
					.append(" or upper(u.description) like :param")
					.append(i)
					.append(")");
			}
			return query(sb.toString(), params);
		}
	   
}