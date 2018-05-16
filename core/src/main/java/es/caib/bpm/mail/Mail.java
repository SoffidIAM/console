/**
 * Modificación de la clase org.jbpm.Mail para que se pueda extender sus funcionalidades.
 */
package es.caib.bpm.mail;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.JbpmException;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.def.Event;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.jpdl.el.ELException;
import org.jbpm.jpdl.el.VariableResolver;
import org.jbpm.jpdl.el.impl.JbpmExpressionEvaluator;
import org.jbpm.taskmgmt.exe.PooledActor;
import org.jbpm.taskmgmt.exe.SwimlaneInstance;
import org.jbpm.taskmgmt.exe.TaskInstance;

import es.caib.seycon.ng.ServiceLocator;
import es.caib.seycon.ng.comu.AutoritzacioRol;
import es.caib.seycon.ng.comu.Configuracio;
import es.caib.seycon.ng.comu.DadaUsuari;
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
import es.caib.seycon.ng.utils.MailUtils;
import es.caib.seycon.ng.utils.Security;

public class Mail implements ActionHandler {
	public String getActors() {
		return actors;
	}

	public void setActors(String actors) {
		this.actors = actors;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public Log getLog() {
		return log;
	}

	public void setLog(Log log) {
		this.log = log;
	}
	private static final long serialVersionUID = 1L;

	protected ExecutionContext executionContext = null;

	// Template can be: 
	// Event.EVENTTYPE_TASK_ASSIGN ("task-assign")
	// "task-reminder"
	
	private String template;

	private String actors;

	private String to;

	private String subject;

	private String text;

	private String from;

	public String getTemplate ()
	{
		return template;
	}

	public void setTemplate (String template)
	{
		this.template = template;
	}

	public void initialize() {
		from = System.getProperty("mail.from", "no-reply@soffid.com");
	}

	public Mail() {
		initialize();
	}

    public Mail(String template, String actors, String to, String subject, String text)
    {
        this.template = template;
        this.actors = actors;
        this.to = to;
        this.subject = subject;
        this.text = text;
        initialize();
    }

	public void execute(ExecutionContext executionContext) throws InternalErrorException, IOException, AddressException {
		debug(Messages.getString("Mail.ExecuteBegin")); //$NON-NLS-1$
		this.__processInstanceId = executionContext.getProcessInstance().getId();
		this.executionContext = executionContext;
		send();
		debug(Messages.getString("Mail.ExecuteEnd")); //$NON-NLS-1$
	}


	public void send() throws InternalErrorException, IOException, AddressException {
		
		if (Event.EVENTTYPE_TASK_ASSIGN.equals(getTemplate()) )
		{
			if (executionContext.getTask() != null)
				executionContext.getTaskInstance().assign(executionContext);

    		sendPredefinedMail("Mail.4");
		} 
		else if ("delegate".equals(getTemplate()) )
		{
			template = Event.EVENTTYPE_TASK_ASSIGN;
    		sendPredefinedMail("Mail.4");
		} 
		else if ("task-reminder".equals(getTemplate()) ) //$NON-NLS-1$
		{
    		sendPredefinedMail("Mail.8");
		}
		else
		{
    		sendCustomMail();
		}
	}

	private void sendPredefinedMail(String header) throws IOException,
			InternalErrorException, UnsupportedEncodingException {
		
		Locale previousLocale = MessageFactory.getThreadLocale();
		Security.nestedLogin("mail-server", new String[] { //$NON-NLS-1$
				Security.AUTO_USER_QUERY + Security.AUTO_ALL,
				Security.AUTO_ROLE_QUERY + Security.AUTO_ALL,
				Security.AUTO_GROUP_QUERY + Security.AUTO_ALL,
				Security.AUTO_USER_ROLE_QUERY + Security.AUTO_ALL,
				Security.AUTO_ACCOUNT_QUERY + Security.AUTO_ALL,
				Security.AUTO_APPLICATION_QUERY + Security.AUTO_ALL});
		try {
			for (String user: getUsers())
			{
				
				Usuari usuari = ServiceLocator.instance().getUsuariService().findUsuariByCodiUsuari(user);
	
				if (usuari.getUsuariSEU() != null && usuari.getUsuariSEU().getIdioma() != null)
					MessageFactory.setThreadLocale(new Locale (usuari.getUsuariSEU().getIdioma()));
				
				String subject = Messages.getString(header); //$NON-NLS-1$
				
				InputStream in = getMailContent();
				InputStreamReader reader = new InputStreamReader(in);
				StringBuffer buffer = new StringBuffer ();
				int ch = reader.read();
				while ( ch >= 0)
				{
					buffer.append((char) ch);
					ch = reader.read ();
				}
				
				text = buffer.toString();
				InternetAddress recipient = getUserAddress(usuari);
				if (recipient != null)
				{
					String fromDescription = null;
					String sender = Security.getCurrentUser();
					if (sender != null)
					{
						Usuari u = ServiceLocator.instance().getUsuariService().findUsuariByCodiUsuari(sender);
						if (u != null)
							fromDescription = u.getFullName();
					}
					send(from, 
							Collections.singleton(recipient), 
							evaluate(subject, recipient.getPersonal(), fromDescription), 
							evaluate (text, recipient.getPersonal(), fromDescription));
				}
			}
		} finally {
			Security.nestedLogoff();
		}
		MessageFactory.setThreadLocale(previousLocale);
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
		if (in == null)
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(template+"_"+locale.getLanguage()+".html"); //$NON-NLS-1$ //$NON-NLS-2$
		if (in == null)
			in = Thread.currentThread().getContextClassLoader().getResourceAsStream(template+".html"); //$NON-NLS-1$ //$NON-NLS-2$
		if ( in == null)
			throw new RuntimeException("Cannot find mail template "+template);

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
		if (text != null && ! text.isEmpty())
		{
			while (subject != null && 
					(subject.startsWith(" ") || subject.startsWith("\t")))
					subject = subject.substring(1);
			MailUtils.sendHtmlMail(null, targetAddresses, fromAddress, subject, text);
		}
	}



	String evaluate(String expression, String from, String to) {
		if (expression == null) {
			return null;
		}
		VariableResolver variableResolver = JbpmExpressionEvaluator
				.getUsedVariableResolver();
		if (variableResolver != null) {
			variableResolver = new MailVariableResolver(
					from, to,
					System.getProperties(),
					variableResolver);
		}
		return (String) JbpmExpressionEvaluator.evaluate(expression,
				executionContext, variableResolver,
				JbpmExpressionEvaluator.getUsedFunctionMapper());
	}

	private void sendCustomMail() throws IOException,
	InternalErrorException, UnsupportedEncodingException, AddressException {
		Security.nestedLogin("mail-server", new String[] { //$NON-NLS-1$
				Security.AUTO_USER_QUERY + Security.AUTO_ALL,
				Security.AUTO_ROLE_QUERY + Security.AUTO_ALL,
				Security.AUTO_GROUP_QUERY + Security.AUTO_ALL,
				Security.AUTO_USER_ROLE_QUERY + Security.AUTO_ALL,
				Security.AUTO_ACCOUNT_QUERY + Security.AUTO_ALL,
				Security.AUTO_APPLICATION_QUERY + Security.AUTO_ALL});
		try {
			String realTo = evaluate(to, null, null);
			if (realTo != null)
			{
				Set<InternetAddress> users = new HashSet<InternetAddress>();
				for (String t: realTo.split("[, ]+"))
				{
					if ( ! t.isEmpty())
						users.add(new InternetAddress(t));
				}

				String content;
				if (text != null && !text.trim().isEmpty())
					content = text;
				else if (template != null && !template.trim().isEmpty())
				{
					InputStream in = getMailContent();
					InputStreamReader reader = new InputStreamReader(in);
					StringBuffer buffer = new StringBuffer ();
					int ch = reader.read();
					while ( ch >= 0)
					{
						buffer.append((char) ch);
						ch = reader.read ();
					}
					content = buffer.toString();
				} else {
					content = subject;
				}

				for (InternetAddress user: users)
				{
					String fromDescription = null;
					String sender = Security.getCurrentUser();
					if (sender != null)
					{
						Usuari u = ServiceLocator.instance().getUsuariService().findUsuariByCodiUsuari(sender);
						if (u != null)
							fromDescription = u.getFullName();
					}
					send(from, 
							Collections.singleton(user), 
							evaluate(subject, user.getPersonal(), fromDescription), 
							evaluate (text, user.getPersonal(), fromDescription));
				}
			}
			if (actors != null)
			{
				Set<String> users = new HashSet<String>();
				String actors2 = evaluate(actors, null, null);
				if (actors2 == null)
					return ;
				
				for (String t: actors2.split("[, ]+"))
				{
					if ( ! t.isEmpty())
						users.addAll( getNameUsers(t));
				}
				for (String user: users)
				{
					
					Usuari usuari = ServiceLocator.instance().getUsuariService().findUsuariByCodiUsuari(user);
			
					InternetAddress recipient = getUserAddress(usuari);
					if (recipient != null)
					{
						String content;
						if (text != null && !text.trim().isEmpty())
							content = text;
						else if (template != null && !template.trim().isEmpty())
						{
							InputStream in = getMailContent();
							InputStreamReader reader = new InputStreamReader(in);
							StringBuffer buffer = new StringBuffer ();
							int ch = reader.read();
							while ( ch >= 0)
							{
								buffer.append((char) ch);
								ch = reader.read ();
							}
							content = buffer.toString();
						} else {
							content = subject;
						}

						String fromDescription = null;
						String sender = Security.getCurrentUser();
						if (sender != null)
						{
							Usuari u = ServiceLocator.instance().getUsuariService().findUsuariByCodiUsuari(sender);
							if (u != null)
								fromDescription = u.getFullName();
						}
						send(from, 
								Collections.singleton(recipient), 
								evaluate(subject, recipient.getPersonal(), fromDescription), 
								evaluate (content, recipient.getPersonal(), fromDescription));
					}
				}
			}
		} finally {
			Security.nestedLogoff();
		}
	}
		

	class MailVariableResolver implements VariableResolver, Serializable {
		private static final long serialVersionUID = 1L;
		Map templateVariables = null;
		VariableResolver variableResolver = null;
		private String to;
		private String from;

		public MailVariableResolver(
				String from,
				String to,
				Map templateVariables,
				VariableResolver variableResolver) {
			this.templateVariables = templateVariables;
			this.variableResolver = variableResolver;
			this.from = from;
			this.to = to;
		}

		public Object resolveVariable(String pName) throws ELException {
			if ((templateVariables != null)
					&& (templateVariables.containsKey(pName))) {
				return templateVariables.get(pName);
			}
			if (pName.equals("systemProperties"))
				return System.getProperties();
			else if (pName.equals("from"))
				return from;
			else if (pName.equals("to"))
				return to;
			else
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

	
	public Set<String> getUsers () throws InternalErrorException, UnsupportedEncodingException
	{
		TaskInstance taskInstance = executionContext.getTaskInstance();
		if (taskInstance != null)
		{
			if (taskInstance.getActorId() != null)
			{
				return getNameUsers (taskInstance.getActorId());
			}
			else if (taskInstance.getSwimlaneInstance() != null)
			{
				SwimlaneInstance swimlane = taskInstance.getSwimlaneInstance();
				return getSwimlaneUsers(swimlane);
			}
			else if (taskInstance.getPooledActors() != null)
				return getNameUsers(taskInstance.getPooledActors());
		}
		return null;
	}

	private Set<String> getSwimlaneUsers (SwimlaneInstance swimlane) throws InternalErrorException, UnsupportedEncodingException
	{
		if (swimlane.getActorId() != null)
			return getNameUsers(swimlane.getActorId());
		else if (swimlane.getPooledActors() != null)
			return getNameUsers (swimlane.getPooledActors());
		else
			return null;
	}

	/**
	 * @param actorId
	 * @return
	 * @throws InternalErrorException 
	 * @throws UnsupportedEncodingException 
	 */
	private Set<String> getNameUsers (String actorId) throws InternalErrorException, UnsupportedEncodingException
	{
		HashSet<String> result = new HashSet<String>();
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
				result.addAll(getNameUsers(rol));
			}
			return result;
			
		}
		Security.nestedLogin("mail-server", new String[] { //$NON-NLS-1$
						Security.AUTO_USER_QUERY + Security.AUTO_ALL,
						Security.AUTO_ROLE_QUERY + Security.AUTO_ALL,
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
    				result.add(usuari.getCodi());
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
    					result.add( ug.getCodiUsuari()) ;
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
        			debug ("Resolving role "+roleName+"@"+dispatcher);
    				AplicacioService aplicacioService = ServiceLocator.instance().getAplicacioService();
					for (Rol role: aplicacioService.findRolsByFiltre(roleName, "%", "%", dispatcher, "%", "%")) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
    				{
	        			debug ("Resolving role grantees: "+role.getNom()+"@"+role.getBaseDeDades());
    					for (RolGrant grant: aplicacioService.findEffectiveRolGrantsByRolId(role.getId()))
    					{
    						if (grant.getUser() != null && (scope == null || scope.equals (grant.getDomainValue())))
    						{
    							result.add(grant.getUser());
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

	
	private InternetAddress getUserAddress (Usuari usuari) throws UnsupportedEncodingException, InternalErrorException
	{
		if (usuari.getNomCurt() != null && usuari.getDominiCorreu() != null)
		{
			return new InternetAddress( 
						usuari.getNomCurt()+"@"+usuari.getDominiCorreu(),
						usuari.getFullName());
		}
		else
		{
			DadaUsuari dada = ServiceLocator.instance().getUsuariService().findDadaByCodiTipusDada(usuari.getCodi(), "EMAIL"); //$NON-NLS-1$
			if (dada != null && dada.getValorDada() != null)
			{
				return new InternetAddress(dada.getValorDada(),
						usuari.getFullName());
			}
			else
				return null;
		}

	}
	/**
	 * @param pooledActors
	 * @return
	 * @throws InternalErrorException 
	 * @throws UnsupportedEncodingException 
	 */
	private Set<String> getNameUsers (Set<PooledActor> pooledActors) throws InternalErrorException, UnsupportedEncodingException
	{
		HashSet<String> result = new HashSet<String>();
		debug ("Resolving address for actor pool");
		for (PooledActor actor: pooledActors)
		{
			if (actor.getActorId() != null)
				result.addAll(getNameUsers(actor.getActorId()));
			else if (actor.getSwimlaneInstance() != null)
			{
				debug ("Resolving addres for swimlane "+actor.getSwimlaneInstance().getName());
				result.addAll(getSwimlaneUsers(actor.getSwimlaneInstance()));
			}
		}
		return result;
	}
}
