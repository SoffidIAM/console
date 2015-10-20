/**
 * Modificación de la clase org.jbpm.Mail para que se pueda extender sus funcionalidades.
 */
package es.caib.bpm.mail;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.webdav.lib.properties.GetContentLengthProperty;
import org.jboss.system.server.ServerLoader;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmException;
import org.jbpm.graph.def.Action;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.def.Event;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.jpdl.el.ELException;
import org.jbpm.jpdl.el.VariableResolver;
import org.jbpm.jpdl.el.impl.JbpmExpressionEvaluator;
import org.jbpm.mail.AddressResolver;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.SwimlaneInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;
import org.jbpm.util.ClassLoaderUtil;
import org.jbpm.util.XmlUtil;

import es.caib.bpm.servei.BpmConfigService;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.vo.ConfigParameterVO;
import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.AutoritzacioRol;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.DadaUsuari;
import es.caib.seycon.ng.comu.Dispatcher;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.RolGrant;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.comu.UsuariGrup;
import es.caib.seycon.ng.comu.lang.MessageFactory;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.model.DispatcherEntity;
import es.caib.seycon.ng.model.DispatcherEntityDao;
import es.caib.seycon.ng.servei.AplicacioService;
import es.caib.seycon.ng.servei.AutoritzacioService;
import es.caib.seycon.ng.servei.ConfiguracioService;
import es.caib.seycon.ng.servei.GrupService;
import es.caib.seycon.ng.servei.SeyconServiceLocator;
import es.caib.seycon.ng.utils.MailUtils;
import es.caib.seycon.ng.utils.Security;

public class Mail implements ActionHandler {
	private static final long serialVersionUID = 1L;

	protected ExecutionContext executionContext = null;

	private String mailHost = "localhost"; //$NON-NLS-1$

	private String mailFrom = "no-reply@soffid.com"; //$NON-NLS-1$

	// Template can be: 
	// Event.EVENTTYPE_TASK_ASSIGN ("task-assign")
	// "task-reminder"
	
	private String template;

	public String getTemplate ()
	{
		return template;
	}

	public void setTemplate (String template)
	{
		this.template = template;
	}

	public void initialize() {
		Configuracio param1;
		try {
			ConfiguracioService configService = ServiceLocator.instance().getConfiguracioService();
			param1 = configService.findParametreByCodiAndCodiXarxa("mail.host", null); //$NON-NLS-1$
			if (param1 != null)
				mailHost = param1.getValor();
			Configuracio param2 = configService.findParametreByCodiAndCodiXarxa("mail.from", null); //$NON-NLS-1$
			if (param2 != null)
				mailFrom = param2.getValor();
		} catch (Exception e) {
		}
	}

	public Mail() {
		initialize();
	}

    public Mail(String template, String actors, String to, String subject, String text)
    {
        this.template = template;
        initialize();
    }

	public void execute(ExecutionContext executionContext) throws InternalErrorException, IOException {
		debug(Messages.getString("Mail.ExecuteBegin")); //$NON-NLS-1$
		this.__processInstanceId = executionContext.getProcessInstance().getId();
		this.executionContext = executionContext;
		send();
		debug(Messages.getString("Mail.ExecuteEnd")); //$NON-NLS-1$
	}


	public void send() throws InternalErrorException, IOException {
		
		if (Event.EVENTTYPE_TASK_ASSIGN.equals(getTemplate()) )
		{
			if (executionContext.getTask() != null)
				executionContext.getTaskInstance().assign(executionContext);

    		String subject = Messages.getString("Mail.4"); //$NON-NLS-1$
    		
    		InputStream in = getMailContent();
    		InputStreamReader reader = new InputStreamReader(in);
    		StringBuffer buffer = new StringBuffer ();
    		int ch = reader.read();
    		while ( ch >= 0)
    		{
    			buffer.append((char) ch);
    			ch = reader.read ();
    		}
    		
    		send(mailFrom, getRecipients(), evaluate(subject), evaluate (buffer.toString()));
		}
		if ("task-reminder".equals(getTemplate()) ) //$NON-NLS-1$
		{
    		String subject = Messages.getString("Mail.8"); //$NON-NLS-1$
    		
    		InputStream in = getMailContent();
    		InputStreamReader reader = new InputStreamReader(in);
    		StringBuffer buffer = new StringBuffer ();
    		int ch = reader.read();
    		while ( ch >= 0)
    		{
    			buffer.append((char) ch);
    			ch = reader.read ();
    		}
    		
    		send(mailFrom, getRecipients(), evaluate(subject), evaluate (buffer.toString()));
		}
	}

	private InputStream getMailContent ()
	{
		Locale locale = MessageFactory.getLocale();
		
		InputStream in = getClass().getResourceAsStream(template+"_"+locale.getLanguage()+"-custom.html"); //$NON-NLS-1$ //$NON-NLS-2$
		if (in == null)
			in = getClass().getResourceAsStream(template+"-custom.html"); //$NON-NLS-1$
		if (in == null)
			in = getClass().getResourceAsStream(template+"_"+locale.getLanguage()+"-template.html"); //$NON-NLS-1$ //$NON-NLS-2$
		if (in == null)
			in = getClass().getResourceAsStream(template+"-template.html"); //$NON-NLS-1$
		return in;
	}

	public void send(String fromAddress,
			Set<InternetAddress> targetAddresses, String subject, String text) {
		if ((targetAddresses == null) || (targetAddresses.isEmpty())) {
			debug(Messages.getString("Mail.SkippingMail")); //$NON-NLS-1$
			return;
		}

		try {
			int retries = 5;
			while (0 < retries) {
				retries--;
				try {
					log.info("Sending mail ["+subject+"] to "+targetAddresses);
					sendMailInternal(fromAddress,
							targetAddresses, subject, text);
					break;
				} catch (MessagingException msgex) {
					if (retries == 0)
						throw msgex;

					// System.out.println("Cannot send mail, now retrying: " +
					// msgex);
					error(String.format(Messages.getString("Mail.NotSendMailError"), msgex));  //$NON-NLS-1$
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			throw new JbpmException(Messages.getString("Mail.NotSendMail"), e); //$NON-NLS-1$
		}
	}

	protected void sendMailInternal(String fromAddress, Set<InternetAddress> targetAddresses,
			String subject, String text) throws Exception {
		
		debug(String.format(Messages.getString("Mail.SendingMailMessage"), targetAddresses, subject)); //$NON-NLS-1$

		MailUtils.sendHtmlMail(mailHost, targetAddresses, fromAddress, subject, text);
	}



	String evaluate(String expression) {
		if (expression == null) {
			return null;
		}
		VariableResolver variableResolver = JbpmExpressionEvaluator
				.getUsedVariableResolver();
		if (variableResolver != null) {
			variableResolver = new MailVariableResolver(System.getProperties(),
					variableResolver);
		}
		return (String) JbpmExpressionEvaluator.evaluate(expression,
				executionContext, variableResolver,
				JbpmExpressionEvaluator.getUsedFunctionMapper());
	}

	class MailVariableResolver implements VariableResolver, Serializable {
		private static final long serialVersionUID = 1L;
		Map templateVariables = null;
		VariableResolver variableResolver = null;

		public MailVariableResolver(Map templateVariables,
				VariableResolver variableResolver) {
			this.templateVariables = templateVariables;
			this.variableResolver = variableResolver;
		}

		public Object resolveVariable(String pName) throws ELException {
			if ((templateVariables != null)
					&& (templateVariables.containsKey(pName))) {
				return templateVariables.get(pName);
			}
			return variableResolver.resolveVariable(pName);
		}
	}


	private long __processInstanceId = 0;

	private Log log = LogFactory.getLog(getClass());

	public void debug(String message) {
		log.info(
				"[" + __processInstanceId + "] " + message); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void error(String message, Throwable t) {
		log.error(
				"[" + __processInstanceId + "] " + message, t); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public void error(String message) {
		log.error(
				"[" + __processInstanceId + "] " + message); //$NON-NLS-1$ //$NON-NLS-2$
	}

	
	public Set<InternetAddress> getRecipients () throws InternalErrorException, UnsupportedEncodingException
	{
		TaskInstance taskInstance = executionContext.getTaskInstance();
		if (taskInstance != null)
		{
			if (taskInstance.getActorId() != null)
			{
				return getAddress (taskInstance.getActorId());
			}
			else if (taskInstance.getSwimlaneInstance() != null)
			{
				SwimlaneInstance swimlane = taskInstance.getSwimlaneInstance();
				return getSwimlaneRecipients(swimlane);
			}
			else if (taskInstance.getPooledActors() != null)
				return getAddress(taskInstance.getPooledActors());
		}
		return null;
	}

	private Set<InternetAddress> getSwimlaneRecipients (SwimlaneInstance swimlane) throws InternalErrorException, UnsupportedEncodingException
	{
		if (swimlane.getActorId() != null)
			return getAddress (swimlane.getActorId());
		else if (swimlane.getPooledActors() != null)
			return getAddress (swimlane.getPooledActors());
		else
			return null;
	}

	/**
	 * @param actorId
	 * @return
	 * @throws InternalErrorException 
	 * @throws UnsupportedEncodingException 
	 */
	private Set<InternetAddress> getAddress (String actorId) throws InternalErrorException, UnsupportedEncodingException
	{
		HashSet<InternetAddress> result = new HashSet<InternetAddress>();
		if (actorId == null)
			return result;
		debug ("Resolving address for "+actorId);
		if (actorId.startsWith("auth:")) //$NON-NLS-1$
		{
			String autorization = actorId.substring(5);
			String domain = null;
			int i = autorization.indexOf('/');
			if (i > 0)
			{
				domain = autorization.substring(i + 1);
				autorization = autorization.substring(0,i);
			}
			AutoritzacioService autService = ServiceLocator.instance().getAutoritzacioService();
			debug ("Resolving address for AUTHORIZATION "+autorization);
			for (AutoritzacioRol ar: autService.getRolsAutoritzacio(autorization))
			{
				String rol = ar.getRol().getNom();
				if (domain != null)
					rol = rol + "/" + domain; //$NON-NLS-1$
				rol = rol+"@"+ar.getRol().getBaseDeDades(); //$NON-NLS-1$
				result.addAll(getAddress(rol));
			}
			return result;
			
		}
		Security.nestedLogin("mail-server", new String[] { //$NON-NLS-1$
						Security.AUTO_USER_QUERY + Security.AUTO_ALL,
						Security.AUTO_GROUP_QUERY + Security.AUTO_ALL,
						Security.AUTO_USER_ROLE_QUERY + Security.AUTO_ALL,
						Security.AUTO_ACCOUNT_QUERY + Security.AUTO_ALL,
						Security.AUTO_APPLICATION_QUERY + Security.AUTO_ALL});
		try {
    		Usuari usuari = ServiceLocator.instance().getUsuariService().findUsuariByCodiUsuari(actorId);
    		if (usuari != null)
    		{
    			debug ("Resolving address for user "+usuari.getCodi());
    			if (usuari.getActiu().booleanValue())
    			{
        			if (usuari.getNomCurt() != null && usuari.getDominiCorreu() != null)
        			{
        				result.add(new InternetAddress( 
        							usuari.getNomCurt()+"@"+usuari.getDominiCorreu(),
        							usuari.getFullName())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            			return result;
        			}
        			else
        			{
        				DadaUsuari dada = ServiceLocator.instance().getUsuariService().findDadaByCodiTipusDada(actorId, "EMAIL"); //$NON-NLS-1$
        				if (dada != null && dada.getValorDada() != null)
        				{
            				result.add(new InternetAddress(dada.getValorDada(),
            						usuari.getFullName())); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
            				return result;
        				}
        			}
    			}
    		}
    		else
    		{
    			GrupService gs = ServiceLocator.instance().getGrupService();
    			Grup grup = gs.findGrupByCodiGrup(actorId);
    			if (grup != null)
    			{
    				StringBuffer sb = new StringBuffer();
        			debug ("Resolving group members: "+grup.getCodi());
    				for (UsuariGrup ug: gs.findUsuarisPertanyenAlGrupByCodiGrup(actorId))
    				{
    					result.addAll( getAddress(ug.getCodiUsuari()) );
    				}
    				return result;
    			}
    			else
    			{
    				int i = actorId.indexOf('@');
    				String roleName;
    				String dispatcher;
    				String scope = null;
    				if (i >= 0)
    				{
    					roleName = actorId.substring(0, i);
    					dispatcher = actorId.substring(i+1);
    				}
    				else
    				{
    					roleName = actorId;
    					DispatcherEntityDao dao = (DispatcherEntityDao) ServiceLocator.instance().getService("dispatcherEntityDao");
						DispatcherEntity defaultDispatcher = dao.findSoffidDispatcher();
    					dispatcher = defaultDispatcher.getCodi();
    				}
    				i = roleName.lastIndexOf('/');
    				if (i >= 0)
    				{
    					scope = roleName.substring(i+1);
    					roleName = roleName.substring(0, i);
    				}
        			debug ("Resolving role"+roleName+"@"+dispatcher);
    				AplicacioService aplicacioService = ServiceLocator.instance().getAplicacioService();
					for (Rol role: aplicacioService.findRolsByFiltre(roleName, "%", "%", dispatcher, "%", "%")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    				{
	        			debug ("Resolving role grantees: "+role.getNom()+"@"+role.getBaseDeDades());
    					for (RolGrant grant: aplicacioService.findEffectiveRolGrantsByRolId(role.getId()))
    					{
    						if (scope == null || scope.equals (grant.getDomainValue()))
    						{
    							result.addAll(getAddress(grant.getUser()));
    						}
    					}
    				}
    				return result;
    			}
    		}
			debug ("Unable to resolve address for "+actorId);
    		return result;
		} finally {
			Security.nestedLogoff();
		}
		
	}

	/**
	 * @param pooledActors
	 * @return
	 * @throws InternalErrorException 
	 * @throws UnsupportedEncodingException 
	 */
	private Set<InternetAddress> getAddress (Set<PooledActor> pooledActors) throws InternalErrorException, UnsupportedEncodingException
	{
		HashSet<InternetAddress> result = new HashSet<InternetAddress>();
		debug ("Resolving addres for actor pool");
		for (PooledActor actor: pooledActors)
		{
			if (actor.getActorId() != null)
				result.addAll(getAddress(actor.getActorId()));
			else if (actor.getSwimlaneInstance() != null)
			{
				debug ("Resolving addres for swimlane "+actor.getSwimlaneInstance().getName());
				result.addAll(getSwimlaneRecipients(actor.getSwimlaneInstance()));
			}
		}
		return result;
	}
}
