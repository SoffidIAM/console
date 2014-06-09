/**
 * 
 */
package com.soffid.iam.authoritative.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

import org.jbpm.JbpmContext;
import org.jbpm.graph.def.ProcessDefinition;
import org.jbpm.graph.exe.ProcessInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

import com.soffid.iam.authoritative.model.AuthoritativeChangeEntity;

import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.TipusDada;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariGrup;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.AuditoriaEntity;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.UsuariEntity;
import es.caib.seycon.ng.sync.intf.AuthoritativeChange;
import es.caib.seycon.ng.sync.intf.AuthoritativeChangeIdentifier;
import es.caib.seycon.ng.utils.Security;

/**
 * @author gbuades
 *
 */
public class AuthoritativeChangeServiceImpl extends AuthoritativeChangeServiceBase
{

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AuthoritativeChangeServiceBase#handleFinishAuthoritativeChange(es.caib.seycon.ng.sync.intf.AuthoritativeChange)
	 */
	@Override
	protected void handleFinishAuthoritativeChange (AuthoritativeChange change)
					throws Exception
	{
		AuthoritativeChangeIdentifier changeId = change.getId();
		AuthoritativeChangeEntity ch = getAuthoritativeChangeEntityDao().load(changeId.getInternalId());
		if (ch != null)
		{
			applyChange(change);
			getAuthoritativeChangeEntityDao().remove(ch);
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AuthoritativeChangeServiceBase#handleCancelAuthoritativeChange(es.caib.seycon.ng.sync.intf.AuthoritativeChange)
	 */
	@Override
	protected void handleCancelAuthoritativeChange (AuthoritativeChange change)
					throws Exception
	{
		AuthoritativeChangeIdentifier changeId = change.getId();
		AuthoritativeChangeEntity ch = getAuthoritativeChangeEntityDao().load(changeId.getInternalId());
		if (ch != null)
		{
			getAuthoritativeChangeEntityDao().remove(ch);
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.ng.servei.AuthoritativeChangeServiceBase#handleStartAuthoritativeChange(es.caib.seycon.ng.sync.intf.AuthoritativeChange)
	 */
	@Override
	protected boolean handleStartAuthoritativeChange (AuthoritativeChange change)
					throws Exception
	{
		DispatcherEntity dispatcher = getDispatcherEntityDao().findByCodi(change.getSourceSystem());
		if (dispatcher == null)
			throw new InternalErrorException(String.format("Invalid source change %s", change.getSourceSystem()));
		
		if (change.getGroups() != null && change.getUser().getCodiGrupPrimari() != null)
			change.getGroups().remove(change.getUser().getCodiGrupPrimari());
		
		if (dispatcher.getAuthoritativeProcess() == null || dispatcher.getAuthoritativeProcess().isEmpty())
		{
			applyChange(change);
			return true;
		}
		else
		{
			AuthoritativeChangeIdentifier changeId = change.getId();
			UsuariEntity ue = null;
			// Cancels any pending workflow
			if (change.getUser() != null && change.getUser().getCodi() != null)
			{
				ue = getUsuariEntityDao().findByCodi(change.getUser().getCodi());
				if (ue != null)
				{
					for ( AuthoritativeChangeEntity ch: ue.getPendingAuthoritativeChanges())
					{
						if (ch.getChangeDate() != null || ch.getChangeId() != null)
						{
							if (ch.getChangeDate() == null && changeId.getDate() == null ||
								ch.getChangeDate() != null && ch.getChangeDate().equals(changeId.getDate()))
							{
								if (ch.getChangeId() == null && changeId.getChangeId() == null ||
									ch.getChangeId() != null && ch.getChangeId().toString().equals(changeId.getChangeId()))
								{
									// This change is being managed => Ignore it
									return true;
								}
								
							}
						}
						cancelProcess (ch.getProcessId());
						getAuthoritativeChangeEntityDao().remove(ch);
					}
				}
			}
			
			if (! detectChange (change))
			{
				return true;
			}
			// Creates record on data base
			AuthoritativeChangeEntity ch = getAuthoritativeChangeEntityDao().newAuthoritativeChangeEntity();
			ch.setChangeDate(change.getId().getDate());
			if (changeId.getChangeId() != null)
				ch.setChangeId(changeId.getChangeId().toString());
			if (changeId.getEmployeeId() != null)
				ch.setEmployeeId(changeId.getEmployeeId().toString());
			ch.setUser(ue);
			ch.setDispatcher(dispatcher);
			getAuthoritativeChangeEntityDao().create(ch);

			// Starts the workflow
			changeId.setInternalId(ch.getId());
			Long processId = createProcessInstance(change, dispatcher) ;
			
			// Now updates the data base record
			ch.setProcessId(processId);
			getAuthoritativeChangeEntityDao().update(ch);

			return false;
		}
		
	}

	/**
	 * @param processId
	 * @throws InternalErrorException 
	 */
	private void cancelProcess (Long processId) throws InternalErrorException
	{
		if (processId != null)
		{
    		JbpmContext ctx = getBpmEngine().getContext();
    		try {
    			ProcessInstance pi =  ctx.getProcessInstance(processId.longValue());
    			if (pi != null && !pi.hasEnded())
    			{
    				pi.getRootToken().addComment("Change has been cancelled");
    				pi.getRootToken().end();
    				pi.end();
    				for (TaskInstance ti: pi.getTaskMgmtInstance().getUnfinishedTasks(pi.getRootToken()))
    				{
    					if (!ti.hasEnded()) {
    						ti.cancel();
    					}
    				}            			
    				ctx.save(pi);
    			}
    		} finally {
    			ctx.close();
    		}
		}
	}

	private long createProcessInstance (AuthoritativeChange change,
					DispatcherEntity dispatcher) throws InternalErrorException
	{
		JbpmContext ctx = getBpmEngine().getContext();
		try {
			ProcessDefinition def = ctx.getGraphSession().findLatestProcessDefinition(dispatcher.getAuthoritativeProcess());
			if (def == null)
				throw new InternalErrorException (String.format("The configured process %s is no longer available", dispatcher.getAuthoritativeProcess()));
			
			ProcessInstance pi = def.createProcessInstance();
			
			Usuari u = change.getUser();
			if (u.getCodi() != null)
				u = getUsuariService().findUsuariByCodiUsuari(u.getCodi());
			
			AuthoritativeChange current = getCurrentAttributes(u.getCodi());

			pi.getContextInstance().createVariable("change", change);
			pi.getContextInstance().createVariable("current", current);
			pi.getContextInstance().createVariable("user", u);
			
			ctx.save(pi);
			pi.signal();
			ctx.save(pi);
			return pi.getId();
		} finally {
			ctx.close();
		}
	}

	private boolean detectChange (AuthoritativeChange change) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InternalErrorException
	{
		if ( ! detectUserChange (change) && ! detectGroupChange (change) && ! detecteAttributeChange (change))
			return false;
		else
			return true;
	}
	
	/**
	 * @param change
	 * @return
	 * @throws InternalErrorException 
	 */
	private boolean detecteAttributeChange (AuthoritativeChange change) throws InternalErrorException
	{
		for (String attribute: change.getAttributes().keySet())
		{
			Object value = change.getAttributes().get(attribute);
			if (value != null && value instanceof Date)
			{
				Calendar c = Calendar.getInstance();
				c.setTime( (Date) value);
				value = c;
			}
			DadaUsuari dada = getUsuariService().findDadaByCodiTipusDada(change.getUser().getCodi(), attribute);
			if (dada == null && value != null)
				return true;
			else if (value == null && dada!= null)
				return true;
			else if (value != null && value instanceof byte[] && ! ((byte[])value).equals(dada.getBlobDataValue())) 
				return true;
			else if (value != null && value instanceof Calendar  && ! ((Calendar)value).equals(dada.getBlobDataValue())) 
				return true;
			else if (value != null && ! value.equals(dada.getValorDada())) 
				return true;
			
		}
		return false;
	}
	

	/**
	 * @param change
	 * @return
	 * @throws InternalErrorException 
	 */
	private boolean detectGroupChange (AuthoritativeChange change) throws InternalErrorException
	{
		Collection<UsuariGrup> grups = getGrupService().findUsuariGrupsByCodiUsuari(change.getUser().getCodi());
		
		Set<String> actualGroups = new HashSet<String>(change.getGroups());
		
		// First remove
		for (Iterator<UsuariGrup> it = grups.iterator(); it.hasNext();)
		{
			UsuariGrup ug = it.next();
			if (actualGroups.contains(ug.getCodiGrup()))
			{
				actualGroups.remove(ug.getCodiGrup());
			}
			else
			{
				return true;
			}
		}
		
		return ! actualGroups.isEmpty();
	}
	
	

	/**
	 * @param change
	 * @return
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws NoSuchMethodException 
	 * @throws InternalErrorException 
	 * 
	 */
	private boolean detectUserChange (AuthoritativeChange change) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InternalErrorException
	{
		if (change.getUser().getCodi() == null)
			return true;
		Usuari old = getUsuariService().findUsuariByCodiUsuari(change.getUser().getCodi());
		if (old == null)
			return true;
		else
			return !compareUsers(change.getUser(), old);
	}

	/**
	 * @param change
	 * @throws NoSuchMethodException 
	 * @throws InternalErrorException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private void applyChange (AuthoritativeChange change) throws SecurityException, InternalErrorException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		Security.nestedLogin(change.getSourceSystem(), 
			new String [] { 
				Security.AUTO_USER_CREATE+Security.AUTO_ALL,
				Security.AUTO_USER_QUERY+Security.AUTO_ALL,
				Security.AUTO_USER_UPDATE+Security.AUTO_ALL,
				Security.AUTO_GROUP_CREATE+Security.AUTO_ALL,
				Security.AUTO_GROUP_QUERY+Security.AUTO_ALL,
				Security.AUTO_GROUP_UPDATE+Security.AUTO_ALL,
				Security.AUTO_USER_GROUP_CREATE+Security.AUTO_ALL,
				Security.AUTO_USER_GROUP_DELETE+Security.AUTO_ALL,
				Security.AUTO_USER_ROLE_CREATE+Security.AUTO_ALL,
				Security.AUTO_USER_ROLE_DELETE+Security.AUTO_ALL,
				Security.AUTO_USER_ROLE_QUERY+Security.AUTO_ALL,
				Security.AUTO_METADATA_CREATE+Security.AUTO_ALL,
				Security.AUTO_METADATA_QUERY+Security.AUTO_ALL,
				Security.AUTO_METADATA_UPDATE+Security.AUTO_ALL,
				Security.AUTO_USER_METADATA_UPDATE+Security.AUTO_ALL
			});
		try {
			ProcessTracker tracker = new ProcessTracker();
			tracker.change = change;
			tracker.auditGenerated = false;
    		Usuari user = applyUserChange (tracker);
    		if (change.getAttributes() != null)
    			applyAttributesChange (user, tracker);
    		if (change.getGroups() != null)
    			applyGroupChange (user, tracker);
		} finally {
			Security.nestedLogoff();
		}
	}

	/**
	 * @param user
	 * @param change
	 * @throws InternalErrorException 
	 */
	private void applyGroupChange (Usuari user, ProcessTracker tracker) throws InternalErrorException
	{
		Collection<UsuariGrup> grups = getGrupService().findUsuariGrupsByCodiUsuari(user.getCodi());
		
		AuthoritativeChange change = tracker.change;
		Set<String> actualGroups = change.getGroups();
		
		// First remove
		for (Iterator<UsuariGrup> it = grups.iterator(); it.hasNext();)
		{
			UsuariGrup ug = it.next();
			if (actualGroups.contains(ug.getCodiGrup()))
			{
				actualGroups.remove(ug.getCodiGrup());
			}
			else
			{
				auditAuthoritativeChange(user.getCodi(), tracker);
				getGrupService().removeGrupFromUsuari(user.getCodi(), ug.getCodiGrup());
			}
		}
		
		for (String group: actualGroups)
		{
			auditAuthoritativeChange(user.getCodi(), tracker);
			getGrupService().addGrupToUsuari(user.getCodi(), group);
		}
		
	}

	private void auditAuthoritativeChange (String user, ProcessTracker tracker)
	{
		if (!tracker.auditGenerated)
		{
            AuditoriaEntity auditoria = getAuditoriaEntityDao().newAuditoriaEntity();
            auditoria.setAccio("U");
            auditoria.setData(new Date());
            auditoria.setUsuari(user);
            auditoria.setObjecte("AUTH_IDENT");
            auditoria.setBbdd(tracker.change.getSourceSystem());
            getAuditoriaEntityDao().create(auditoria);
            tracker.auditGenerated = true;
		}
	}
	/**
	 * @param change
	 * @return 
	 * @throws InternalErrorException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	private Usuari applyUserChange (ProcessTracker tracker) throws InternalErrorException, SecurityException, NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		AuthoritativeChange change = tracker.change;
		Usuari user = change.getUser();
		Usuari oldUser = getUsuariService().findUsuariByCodiUsuari(user.getCodi());
		if (oldUser == null)
		{
			if (user.getActiu() == null) user.setActiu(Boolean.TRUE);
			if (user.getMultiSessio() == null) user.setMultiSessio(Boolean.FALSE);
			if (user.getServidorCorreu() == null) user.setServidorCorreu("null");
			if (user.getServidorHome() == null) user.setServidorHome("null");
			if (user.getServidorPerfil() == null) user.setServidorPerfil("null");
			if (user.getTipusUsuari() == null) user.setTipusUsuari("I");
			oldUser = getUsuariService().create(user);
		} else {
			boolean anyChange = !compareUsers(user, oldUser);
			if (anyChange)
			{
				auditAuthoritativeChange(oldUser.getCodi(), tracker);
				getUsuariService().update(oldUser);
			}
		}
		return oldUser;
	}

	private boolean compareUsers (Usuari user, Usuari oldUser)
					throws NoSuchMethodException, IllegalAccessException,
					InvocationTargetException
	{
		boolean anyChange = false;
		for (String att: new String[] {"Actiu", "AliesCorreu", "CodiGrupPrimari", "Comentari", 
						"DominiCorreu", "MultiSessio", "NIF", "Nom", "NomCurt", "PrimerLlinatge",
						"SegonLlinatge", "ServidorCorreu", "ServidorPerfil", "ServidorHome", 
						"Telefon", "TipusUsuari"})
		{
			Method getter = Usuari.class.getMethod("get"+att);
			Method setter = Usuari.class.getMethod("set"+att, getter.getReturnType());
			Object value = getter.invoke(user);
			if ("".equals(value))
				value = null;
			if (value != null)
			{
				Object oldValue = getter.invoke(oldUser);
				if ("".equals(oldValue))
					oldValue = null;
				
				if (oldValue == null || !oldValue.equals(value))
				{
					setter.invoke(oldUser, value);
					anyChange = true;
				}
			}
		}
		return ! anyChange;
	}

	/**
	 * @param change
	 * @throws InternalErrorException 
	 */
	private void applyAttributesChange (Usuari user, ProcessTracker tracker) throws InternalErrorException
	{
		AuthoritativeChange change = tracker.change;
		
		for (String attribute: change.getAttributes().keySet())
		{
			Object value = change.getAttributes().get(attribute);
			if (value != null && value instanceof Date)
			{
				Calendar c = Calendar.getInstance();
				c.setTime( (Date) value);
				value = c;
			}
			TipusDada tda = getDadesAddicionalsService().findTipusDadaByCodi(attribute);
			if (tda == null)
			{
				long i = 100;
				tda = new TipusDada();
				for (TipusDada tda2: getDadesAddicionalsService().getTipusDades())
				{
					if (tda2.getOrdre().longValue() >= i)
						i = tda2.getOrdre().longValue()+1;
				}
				auditAuthoritativeChange(user.getCodi(), tracker);
				tda.setOrdre(i);
				tda.setCodi(attribute);
				tda = getDadesAddicionalsService().create(tda);
			}
			DadaUsuari dada = getUsuariService().findDadaByCodiTipusDada(user.getCodi(), attribute);
			if (dada == null && value != null)
			{
				auditAuthoritativeChange(user.getCodi(), tracker);
				dada = new DadaUsuari();
				dada.setCodiDada(tda.getCodi());
				dada.setCodiUsuari(user.getCodi());
				if (value instanceof byte[])
					dada.setBlobDataValue((byte[]) value);
				else if (value instanceof Calendar)
					dada.setValorDadaDate((Calendar) value);
				else if (value != null)
					dada.setValorDada(value.toString());
				getDadesAddicionalsService().create(dada);
			} 
			else if (value == null && dada!= null)
			{
				auditAuthoritativeChange(user.getCodi(), tracker);
				getDadesAddicionalsService().delete(dada);
			} 
			else if (value != null && value instanceof byte[] && ! ((byte[])value).equals(dada.getBlobDataValue())) 
			{
				auditAuthoritativeChange(user.getCodi(), tracker);
				dada.setBlobDataValue((byte[])value);
				getDadesAddicionalsService().update(dada);
			}
			else if (value != null && value instanceof Calendar  && ! ((Calendar)value).equals(dada.getBlobDataValue())) 
			{
				auditAuthoritativeChange(user.getCodi(), tracker);
				dada.setValorDadaDate((Calendar)value);
				getDadesAddicionalsService().update(dada);
			}
			else if (value != null && ! value.equals(dada.getValorDada())) 
			{
				auditAuthoritativeChange(user.getCodi(), tracker);
				dada.setValorDada(value.toString());
				getDadesAddicionalsService().update(dada);
			}
		}
	}
	
	private AuthoritativeChange getCurrentAttributes (String user) throws InternalErrorException
	{
		if (user == null)
			return null;
		
		AuthoritativeChange current = new AuthoritativeChange();
		current.setUser(getUsuariService().findUsuariByCodiUsuari(user));
		if (current.getUser() == null)
			return null;
		else
		{
    		current.setGroups(new HashSet<String>());
    		for (Grup grup: getUsuariService().getUserGroups(current.getUser().getId()))
    		{
    			current.getGroups().add(grup.getCodi());
    		}
    		
    		current.setAttributes(new HashMap<String, Object>());
    		for (DadaUsuari dus: getUsuariService().findDadesUsuariByCodiUsuari(user))
    		{
    			current.getAttributes().put(dus.getCodiDada(), 
    							dus.getBlobDataValue() != null ? dus.getBlobDataValue():
    							dus.getValorDadaDate() != null ? dus.getValorDadaDate():
    							dus.getValorDada());
    		}
    		return current;
		}
	}
}

class ProcessTracker {
	AuthoritativeChange change;
	boolean auditGenerated;
}