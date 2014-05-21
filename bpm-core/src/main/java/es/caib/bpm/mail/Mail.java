/**
 * Modificación de la clase org.jbpm.Mail para que se pueda extender sus funcionalidades.
 */
package es.caib.bpm.mail;

import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.jbpm.JbpmConfiguration;
import org.jbpm.JbpmException;
import org.jbpm.graph.def.ActionHandler;
import org.jbpm.graph.exe.ExecutionContext;
import org.jbpm.jpdl.el.ELException;
import org.jbpm.jpdl.el.VariableResolver;
import org.jbpm.jpdl.el.impl.JbpmExpressionEvaluator;
import org.jbpm.mail.AddressResolver;
import org.jbpm.util.ClassLoaderUtil;
import org.jbpm.util.XmlUtil;

import es.caib.bpm.servei.BPMConfigService;
import es.caib.bpm.toolkit.EJBContainer;
import es.caib.bpm.vo.ConfigParameterVO;
import es.caib.seycon.ng.servei.SeyconServiceLocator;

public class Mail implements ActionHandler
{
  private static final long serialVersionUID = 1L;

  public boolean signalProcessInstance=true;
  
  protected String template = null;
  protected String actors = null;
  protected String to = null;
  protected String bcc = null;
  protected String bccActors = null;
  protected String subject = null;
  protected String text = null;
  protected String sender = null;
  
  protected String mailServiceJNDI=null;
  protected String sendMailEnabled=null;
  protected String redirectSendMail=null;
  
  protected ExecutionContext executionContext = null;

  public void initialize(){
		ConfigParameterVO param1 = null;
		ConfigParameterVO param2 = null;
		ConfigParameterVO param3 = null;
		
		try {
			BPMConfigService configService = SeyconServiceLocator.instance().getBPMConfigService();
			param1 = configService.findFirstByAppKey("BPM","es.caib.bpm.mailServiceJNDI");
			param2 = configService.findFirstByAppKey("BPM","es.caib.bpm.sendMailEnabled");
			param3 = configService.findFirstByAppKey("BPM","es.caib.bpm.redirectSendMail");
		} catch (Exception e) {}
		if(param1!=null) mailServiceJNDI=param1.getValue();
		if(param2!=null) sendMailEnabled=param2.getValue();
		if(param3!=null) redirectSendMail=param3.getValue();
  }
  public Mail()
  {
	  initialize();
  
  }

  public Mail(String template, String actors, String to, String subject, String text)
  {
	  initialize();

	  this.template = template;
    this.actors = actors;
    this.to = to;
    this.subject = subject;
    this.text = text;
  }

  public Mail(String template, String actors, String to, String bccActors, String bcc, String subject, String text)
  {
	  initialize();

	this.template = template;
    this.actors = actors;
    this.to = to;
    this.bccActors = bccActors;
    this.bcc = bcc;
    this.subject = subject;
    this.text = text;
  }

  public void execute(ExecutionContext executionContext)
  {
	debug("execute begin");
    this.executionContext = executionContext;
    send();
    debug("execute end");
    
    if(getSignalProcessInstance())
    	executionContext.getProcessInstance().signal();
  }

  public List getRecipients()
  {
    List recipients = new ArrayList();
    if (actors != null)
    {
      String evaluatedActors = evaluate(actors);
      List tokenizedActors = tokenize(evaluatedActors);
      if (tokenizedActors != null)
      {
        recipients.addAll(resolveAddresses(tokenizedActors));
      }
    }
    if (to != null)
    {
      String resolvedTo = evaluate(to);
      recipients.addAll(tokenize(resolvedTo));
    }
    return recipients;
  }

  public List getBccRecipients()
  {
    List recipients = new ArrayList();
    if (bccActors != null)
    {
      String evaluatedActors = evaluate(bccActors);
      List tokenizedActors = tokenize(evaluatedActors);
      if (tokenizedActors != null)
      {
        recipients.addAll(resolveAddresses(tokenizedActors));
      }
    }
    if (bcc != null)
    {
      String resolvedTo = evaluate(bcc);
      recipients.addAll(tokenize(resolvedTo));
    }
    if (JbpmConfiguration.Configs.hasObject("jbpm.mail.bcc.address"))
    {
      recipients.addAll(tokenize(JbpmConfiguration.Configs.getString("jbpm.mail.bcc.address")));
    }
    return recipients;
  }

  public String getSubject()
  {
    if (subject == null)
      return null;
    return evaluate(subject);
  }

  public String getText()
  {
    if (text == null)
      return null;
    return evaluate(text);
  }

  public String getFromAddress()
  {
    if(sender==null){
		if (JbpmConfiguration.Configs.hasObject("jbpm.mail.from.address"))
	    {
	      return JbpmConfiguration.Configs.getString("jbpm.mail.from.address");
	    }
    }else{
    	return sender;
    }
    return "jbpm@caib.es";
  }

  public void send()
  {
    if (template != null)
    {
      Properties properties = getMailTemplateProperties(template);
      if (actors == null)
      {
        actors = properties.getProperty("actors");
      }
      if (to == null)
      {
        to = properties.getProperty("to");
      }
      if (subject == null)
      {
        subject = properties.getProperty("subject");
      }
      if (text == null)
      {
        text = properties.getProperty("text");
      }
      if (bcc == null)
      {
        bcc = properties.getProperty("bcc");
      }
      if (bccActors == null)
      {
        bccActors = properties.getProperty("bccActors");
      }
    }

    send(getMailServerProperties(), getFromAddress(), getRecipients(), getBccRecipients(), getSubject(), getText());
  }

  public void send(Properties mailServerProperties, String fromAddress, List recipients, String subject, String text)
  {
    send(mailServerProperties, fromAddress, recipients, null, subject, text);
  }

  public void send(Properties mailServerProperties, String fromAddress, List recipients, List bccRecipients, String subject, String text)
  {
    if (((recipients == null) || (recipients.isEmpty())) && ((bccRecipients == null) || (bccRecipients.isEmpty())))
    {
      debug("skipping mail because there are no recipients");
      return;
    }

    try
    {
      int retries = 5;
      while (0 < retries)
      {
        retries--;
        try
        {
          sendMailInternal(mailServerProperties, fromAddress, recipients, bccRecipients, subject, text);
          break;
        }
        catch (MessagingException msgex)
        {
          if (retries == 0)
            throw msgex;

          //System.out.println("Cannot send mail, now retrying: " + msgex);
          error("Cannot send mail, now retrying: " + msgex);
          Thread.sleep(1000);
        }
      }
    }
    catch (Exception e)
    {
      throw new JbpmException("Cannot send email", e);
    }
  }

  protected void sendMailInternal(Properties mailServerProperties, String fromAddress, List recipients, List bccRecipients, String subject, String text)
      throws Exception
  {
    
    debug("sending email to '" + recipients + "' " + (bccRecipients != null ? "and in bcc to '" + bccRecipients + "' " : "") + "about '" + subject + "'");
    
    Session session =(Session) new InitialContext().lookup(mailServiceJNDI);
    MimeMessage message = new MimeMessage(session);
    if (fromAddress != null)
    {
      message.setFrom(new InternetAddress(fromAddress));
    }
	if(sendMailEnabled!=null){
		Iterator iter = recipients.iterator();
		while (iter.hasNext()) {
			InternetAddress recipient = new InternetAddress((String) iter.next());
			message.addRecipient(Message.RecipientType.TO, recipient);
		}
		if (bccRecipients != null) {
			iter = bccRecipients.iterator();
			while (iter.hasNext()) {
				InternetAddress recipient = new InternetAddress((String) iter.next());
				message.addRecipient(Message.RecipientType.BCC, recipient);
			}
		}
	}else{
		if(redirectSendMail!=null)
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(redirectSendMail));
	}
    if (subject != null)
    {
      message.setSubject(subject);
    }
    if (text != null)
    {
      message.setText(text);
    }
    message.setSentDate(new Date());
    
   	Transport.send(message);
    
  }

  protected List tokenize(String text)
  {
    if (text == null)
    {
      return null;
    }
    List list = new ArrayList();
    StringTokenizer tokenizer = new StringTokenizer(text, ";:");
    while (tokenizer.hasMoreTokens())
    {
      list.add(tokenizer.nextToken());
    }
    return list;
  }

  protected Collection resolveAddresses(List actorIds)
  {
    List emailAddresses = new ArrayList();
    Iterator iter = actorIds.iterator();
    while (iter.hasNext())
    {
      String actorId = (String)iter.next();
      AddressResolver addressResolver = (AddressResolver)JbpmConfiguration.Configs.getObject("jbpm.mail.address.resolver");
      Object resolvedAddresses = addressResolver.resolveAddress(actorId);
      if (resolvedAddresses != null)
      {
        if (resolvedAddresses instanceof String)
        {
          emailAddresses.add((String)resolvedAddresses);
        }
        else if (resolvedAddresses instanceof Collection)
        {
          emailAddresses.addAll((Collection)resolvedAddresses);
        }
        else if (resolvedAddresses instanceof String[])
        {
          emailAddresses.addAll(Arrays.asList((String[])resolvedAddresses));
        }
        else
        {
          throw new JbpmException("Address resolver '" + addressResolver + "' returned '" + resolvedAddresses.getClass().getName()
              + "' instead of a String, Collection or String-array: " + resolvedAddresses);
        }
      }
    }
    return emailAddresses;
  }

  Properties getMailServerProperties()
  {
    Properties mailServerProperties = new Properties();

    if (JbpmConfiguration.Configs.hasObject("resource.mail.properties"))
    {
      String mailServerPropertiesResource = JbpmConfiguration.Configs.getString("resource.mail.properties");
      InputStream mailServerStream;
      try
      {
        mailServerStream = ClassLoaderUtil.getStream(mailServerPropertiesResource);
        try{
        	mailServerProperties.load(mailServerStream);  	
        }catch(Exception e){
        	throw e;
        }finally{
        	mailServerStream.close();
        }
      }
      catch (Exception e)
      {
        throw new JbpmException("couldn't get configuration properties for jbpm mail server from resource '" + mailServerPropertiesResource + "'", e);
      }

    }
    else if (JbpmConfiguration.Configs.hasObject("jbpm.mail.smtp.host"))
    {
      String smtpServer = JbpmConfiguration.Configs.getString("jbpm.mail.smtp.host");
      mailServerProperties.put("mail.smtp.host", smtpServer);

    }
    else
    {

      error("couldn't get mail properties");
    }

    return mailServerProperties;
  }

  static Map templates = null;
  static Map templateVariables = null;

  synchronized Properties getMailTemplateProperties(String templateName)
  {
    if (templates == null)
    {
      templates = new HashMap();
      String mailTemplatesResource = JbpmConfiguration.Configs.getString("resource.mail.templates");
      org.w3c.dom.Element mailTemplatesElement = XmlUtil.parseXmlResource(mailTemplatesResource, false).getDocumentElement();
      List mailTemplateElements = XmlUtil.elements(mailTemplatesElement, "mail-template");
      Iterator iter = mailTemplateElements.iterator();
      while (iter.hasNext())
      {
        org.w3c.dom.Element mailTemplateElement = (org.w3c.dom.Element)iter.next();

        Properties templateProperties = new Properties();
        addTemplateProperty(mailTemplateElement, "actors", templateProperties);
        addTemplateProperty(mailTemplateElement, "to", templateProperties);
        addTemplateProperty(mailTemplateElement, "subject", templateProperties);
        addTemplateProperty(mailTemplateElement, "text", templateProperties);
        addTemplateProperty(mailTemplateElement, "bcc", templateProperties);
        addTemplateProperty(mailTemplateElement, "bccActors", templateProperties);

        templates.put(mailTemplateElement.getAttribute("name"), templateProperties);
      }

      templateVariables = new HashMap();
      List variableElements = XmlUtil.elements(mailTemplatesElement, "variable");
      iter = variableElements.iterator();
      while (iter.hasNext())
      {
        org.w3c.dom.Element variableElement = (org.w3c.dom.Element)iter.next();
        templateVariables.put(variableElement.getAttribute("name"), variableElement.getAttribute("value"));
      }
    }
    return (Properties)templates.get(templateName);
  }

  void addTemplateProperty(org.w3c.dom.Element mailTemplateElement, String property, Properties templateProperties)
  {
    org.w3c.dom.Element element = XmlUtil.element(mailTemplateElement, property);
    if (element != null)
    {
      templateProperties.put(property, XmlUtil.getContentText(element));
    }
  }

  String evaluate(String expression)
  {
    if (expression == null)
    {
      return null;
    }
    VariableResolver variableResolver = JbpmExpressionEvaluator.getUsedVariableResolver();
    if (variableResolver != null)
    {
      variableResolver = new MailVariableResolver(templateVariables, variableResolver);
    }
    return (String)JbpmExpressionEvaluator.evaluate(expression, executionContext, variableResolver, JbpmExpressionEvaluator.getUsedFunctionMapper());
  }

  class MailVariableResolver implements VariableResolver, Serializable
  {
    private static final long serialVersionUID = 1L;
    Map templateVariables = null;
    VariableResolver variableResolver = null;

    public MailVariableResolver(Map templateVariables, VariableResolver variableResolver)
    {
      this.templateVariables = templateVariables;
      this.variableResolver = variableResolver;
    }

    public Object resolveVariable(String pName) throws ELException
    {
      if ((templateVariables != null) && (templateVariables.containsKey(pName)))
      {
        return templateVariables.get(pName);
      }
      return variableResolver.resolveVariable(pName);
    }
  }

  public void setSignalProcessInstance(boolean signalProcessInstance) {
	  this.signalProcessInstance = signalProcessInstance;
  }
  
  public boolean getSignalProcessInstance(){
	  return this.signalProcessInstance; 
  }
  
  private long __processInstanceId=0;

  
  

	public void debug(String message){
		Logger.getLogger(this.getClass()).debug("["+__processInstanceId+"] "+message);
	}

	public void error(String message,Throwable t){
		Logger.getLogger(this.getClass()).error("["+__processInstanceId+"] "+message,t);
	}

	public void error(String message){
		Logger.getLogger(this.getClass()).error("["+__processInstanceId+"] "+message);
	}

}
