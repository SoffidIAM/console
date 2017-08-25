/**
 * Modificación de la clase org.jbpm.Mail para que se pueda extender sus funcionalidades.
 */
/**
 * Modificación de la clase org.jbpm.Mail para que se pueda extender sus funcionalidades.
 */
/**
 * Modificación de la clase org.jbpm.Mail para que se pueda extender sus funcionalidades.
 */
package com.soffid.iam.bpm.mail;

import com.soffid.iam.api.AuthorizationRole;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.GroupUser;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.RoleGrant;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserData;
import com.soffid.iam.lang.MessageFactory;
import com.soffid.iam.model.SystemEntity;
import com.soffid.iam.model.SystemEntityDao;
import com.soffid.iam.service.ApplicationService;
import com.soffid.iam.service.AuthorizationService;
import com.soffid.iam.service.ConfigurationService;
import com.soffid.iam.service.GroupService;
import com.soffid.iam.utils.MailUtils;
import com.soffid.iam.utils.Security;
import com.soffid.iam.ServiceLocator;

import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;

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
				
				User usuari = ServiceLocator.instance().getUserService().findUserByUserName(user);
	
				if (usuari.getConsoleProperties() != null && usuari.getConsoleProperties().getLanguage() != null)
					MessageFactory.setThreadLocale(new Locale (usuari.getConsoleProperties().getLanguage()));
				
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
				
				InternetAddress recipient = getUserAddress(usuari);
				if (recipient != null)
				{
					send(from, Collections.singleton(recipient), evaluate(subject), evaluate (buffer.toString()));
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

		MailUtils.sendHtmlMail(null, targetAddresses, fromAddress, subject, text);
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
			String realTo = evaluate(to);
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

				send(from, users, evaluate(subject), evaluate (text));
			}
			if (actors != null)
			{
				Set<String> users = new HashSet<String>();
				for (String t: evaluate(actors).split("[, ]+"))
				{
					if ( ! t.isEmpty())
						users.addAll( getNameUsers(t));
				}
				for (String user: users)
				{
					
					User usuari = ServiceLocator.instance().getUserService().findUserByUserName(user);
			
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

						send(from, Collections.singleton(recipient), evaluate(subject), evaluate (content));
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
			if (pName.equals("systemProperties"))
				return System.getProperties();
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
			AuthorizationService autService = ServiceLocator.instance().getAuthorizationService();
			debug ("Resolving address for AUTHORIZATION "+autorization);
			for (AuthorizationRole ar : autService.getAuthorizationRoles(autorization)) {
                String rol = ar.getRole().getName();
                if (domain != null) rol = rol + "/" + domain;
                rol = rol + "@" + ar.getRole().getSystem();
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
    		User usuari = ServiceLocator.instance().getUserService().findUserByUserName(actorId);
    		if (usuari != null)
    		{
    			debug("Resolving address for user " + usuari.getUserName());
    			if (usuari.getActive().booleanValue())
    			{
    				result.add(usuari.getUserName());
    			}
    		}
    		else
    		{
    			GroupService gs = ServiceLocator.instance().getGroupService();
    			Group grup = gs.findGroupByGroupName(actorId);
    			if (grup != null)
    			{
    				StringBuffer sb = new StringBuffer();
        			debug("Resolving group members: " + grup.getName());
    				for (GroupUser ug : gs.findUsersBelongtoGroupByGroupName(actorId)) 
    				{
    					result.add( ug.getUser()) ;
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
    					SystemEntityDao dao = (SystemEntityDao) ServiceLocator.instance().getService("systemEntityDao");
						SystemEntity defaultDispatcher = dao.findSoffidSystem();
    					dispatcher = defaultDispatcher.getName();
    				}
    				i = roleName.lastIndexOf('/');
    				if (i >= 0)
    				{
    					scope = roleName.substring(i+1);
    					roleName = roleName.substring(0, i);
    				}
        			debug ("Resolving role "+roleName+"@"+dispatcher);
    				ApplicationService aplicacioService = ServiceLocator.instance().getApplicationService();
					for (Role role : aplicacioService.findRolesByFilter(roleName, "%", "%", dispatcher, "%", "%")) {
                        debug("Resolving role grantees: " + role.getName() + "@" + role.getSystem());
                        for (RoleGrant grant : aplicacioService.findEffectiveRoleGrantsByRoleId(role.getId())) {
                            if (scope == null || scope.equals(grant.getDomainValue())) {
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

	
	private InternetAddress getUserAddress (User usuari) throws UnsupportedEncodingException, InternalErrorException
	{
		if (usuari.getShortName() != null && usuari.getMailDomain() != null)
		{
			return new InternetAddress( 
						usuari.getShortName()+"@"+usuari.getMailDomain(),
						usuari.getFullName());
		}
		else
		{
			UserData dada = ServiceLocator.instance().getUserService().findDataByUserAndCode(usuari.getUserName(), "EMAIL"); //$NON-NLS-1$
			if (dada != null && dada.getValue() != null)
			{
				return new InternetAddress(dada.getValue(),
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
		debug ("Resolving addres for actor pool");
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
