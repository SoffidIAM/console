package com.soffid.iam.web.component;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.logging.LogFactory;
import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.xel.Function;
import org.zkoss.xel.FunctionMapper;
import org.zkoss.xel.VariableResolver;
import org.zkoss.xel.XelContext;
import org.zkoss.xel.XelException;
import org.zkoss.xel.el.ELFactory;
import org.zkoss.xel.el.ELXelExpression;
import org.zkoss.xel.util.SimpleXelContext;
import org.zkoss.xml.HTMLs;
import org.zkoss.xml.XMLs;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.BinaryData;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.OsType;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.Task;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserType;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.api.TaskInstance;
import com.soffid.iam.interp.Evaluator;
import com.soffid.iam.service.impl.bshjail.SecureInterpreter;
import com.soffid.iam.utils.Security;
import com.soffid.iam.web.component.inputField.AccountDataHandler;
import com.soffid.iam.web.component.inputField.ApplicationDataHandler;
import com.soffid.iam.web.component.inputField.CustomObjectDataHandler;
import com.soffid.iam.web.component.inputField.GroupDataHandler;
import com.soffid.iam.web.component.inputField.HostDataHandler;
import com.soffid.iam.web.component.inputField.InputFieldDataHandler;
import com.soffid.iam.web.component.inputField.MailDomainDataHandler;
import com.soffid.iam.web.component.inputField.MailListDataHandler;
import com.soffid.iam.web.component.inputField.NetworkDataHandler;
import com.soffid.iam.web.component.inputField.OUTypeDataHandler;
import com.soffid.iam.web.component.inputField.PrinterDataHandler;
import com.soffid.iam.web.component.inputField.RoleDataHandler;
import com.soffid.iam.web.component.inputField.UserDataHandler;
import com.soffid.iam.web.popup.Editor;
import com.soffid.iam.web.popup.FileUpload2;
import com.soffid.iam.web.popup.HtmlEditor;
import com.soffid.iam.web.users.additionalData.AttributesDiv;
import com.soffid.iam.web.users.additionalData.SearchFilter;

import bsh.EvalError;
import bsh.TargetError;
import es.caib.seycon.ng.comu.Aplicacio;
import es.caib.seycon.ng.comu.Grup;
import es.caib.seycon.ng.comu.Rol;
import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.web.Messages;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.Databox;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathException;

public class InputField3 extends Databox
{
	org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
	
	private static final long serialVersionUID = 1L;
	private DataType dataType;
	private Object ownerObject;
	boolean hideUserName = false;
	boolean raisePrivileges = false;
	boolean updating = false; 
	boolean disableRecursive = false;
	private String ownerContext;
	InputFieldUIHandler uiHandler = null;
	InputFieldDataHandler<?> dataHandler = null;
	boolean readonly = false;
	boolean visible = true;
	String keysPath = null;
	String valuesPath = null;
	String javascript = null;
	String javascripthelp = null;
	String descriptionExpression = null;
	SimpleXelContext xelContext = new SimpleXelContext();
	
	private SearchFilter filter;

	private AsyncList<?> currentList;

	private int currentPosition;

	private EventListener listener;

	private boolean noPermissions;

	private org.zkoss.xel.Expression descriptionExpressionCompiled;
	
	public DataType getDataType() {
		return dataType;
	}

	public void setDataType(DataType dataType) {
		this.dataType = dataType;
	}

	public InputField3(){
		super();
		setWarningIcon("/img/warning.svg");
	}
	
	public void onCreate () throws NamingException, CreateException, InternalErrorException, IOException
	{
		createField();
	}

	public InputFieldContainer getObjectContainer () {
		Component c = this;
		do
		{
			if (c instanceof InputFieldContainer)
			{
				return ((InputFieldContainer) c);
			}
			else
				c = c.getParent();
		} while (c != null);
		return null;
	}
	
	protected void onItemChange(Object value, Integer pos) {
		super.onItemChange(value, pos);
		attributeValidate( pos , value);
		Component c = this;
		do
		{
			if (c instanceof AttributesDiv)
			{
				((AttributesDiv) c).adjustVisibility();
				break;
			}
			else if (c instanceof InputFieldContainer)
			{
				((InputFieldContainer) c).adjustVisibility();
				break;
			}
			else
				c = c.getParent();
		} while (c != null);
		
		// Now, run the onChange trigger
		runOnChangeTrigger();
	}

	private void commit() throws CommitException {
		XPathUtils.getComponentContext(this).getDataSource().commit();
	}

		
	public synchronized void createField() throws NamingException, CreateException, InternalErrorException, IOException{
		if (getPage() == null)
			return;	
		
		if (disableRecursive)
			return;
		
		
		if (getEventHandler("onChange") == null)
			addEventHandler("onChange", new EventHandler(ZScript.parseContent("ref:"), null));
		disableRecursive = true;
		
		try
		{
			if (dataType.getBuiltinHandler() != null && ! dataType.getBuiltinHandler().trim().isEmpty()) {
				uiHandler = (InputFieldUIHandler) Class.forName(dataType.getBuiltinHandler()).newInstance();
			} else {
				uiHandler = null;
			}
			calculateVisibility();
			super.setMultiValue(dataType.isMultiValued());
			super.setMaxrows(dataType.getMultiValuedRows());
			if ( dataType.getType().equals(TypeEnumeration.APPLICATION_TYPE) ||
								dataType.getType().equals(TypeEnumeration.CUSTOM_OBJECT_TYPE) ||
								dataType.getType().equals(TypeEnumeration.GROUP_TYPE) ||
								dataType.getType().equals(TypeEnumeration.GROUP_TYPE_TYPE) ||
								dataType.getType().equals(TypeEnumeration.USER_TYPE_TYPE) ||
								dataType.getType().equals(TypeEnumeration.ACCOUNT_TYPE) ||
								dataType.getType().equals(TypeEnumeration.USER_TYPE) ||
								dataType.getType().equals(TypeEnumeration.ROLE_TYPE) ||
								dataType.getType().equals(TypeEnumeration.NETWORK_TYPE) ||
								dataType.getType().equals(TypeEnumeration.HOST_TYPE) ||
								dataType.getType().equals(TypeEnumeration.MAIL_DOMAIN_TYPE) ||
								dataType.getType().equals(TypeEnumeration.MAIL_LIST_TYPE) ||
								dataType.getType().equals(TypeEnumeration.OS_TYPE) ||
								dataType.getType().equals(TypeEnumeration.PRINTER_TYPE)  )
				
			{
				if (hideUserName)
					setType(Databox.Type.DESCRIPTION);
				else
					setType(Databox.Type.NAME_DESCRIPTION);
				setSelectIcon("/img/search2.svg");
				if ( dataType.getType().equals(TypeEnumeration.USER_TYPE))
				{
					dataHandler = new UserDataHandler(dataType);
					setSelectIcon("/img/user.svg");
					setHyperlink(true);
					noPermissions = ! Security.isUserInRole(Security.AUTO_USER_QUERY);
				}
				else if ( dataType.getType().equals(TypeEnumeration.PRINTER_TYPE))
				{
					dataHandler = new PrinterDataHandler(dataType);
					setSelectIcon("/img/printer.svg");
					setHyperlink(true);
					noPermissions = ! Security.isUserInRole(Security.AUTO_PRINTER_QUERY);
				}
				else if ( dataType.getType().equals(TypeEnumeration.APPLICATION_TYPE))
				{
					dataHandler = new ApplicationDataHandler(dataType);
					setHyperlink(true);
					noPermissions = ! Security.isUserInRole(Security.AUTO_APPLICATION_QUERY);
				}
				else if ( dataType.getType().equals(TypeEnumeration.CUSTOM_OBJECT_TYPE)) {
					dataHandler = new CustomObjectDataHandler(dataType);
					setHyperlink(true);
					noPermissions = ! Security.isUserInRole("customObject:query");
				} else  if ( dataType.getType().equals(TypeEnumeration.GROUP_TYPE_TYPE)) {
					setSelectIcon("/img/group.svg");
					dataHandler = new OUTypeDataHandler(dataType);
					setHyperlink(true);
					noPermissions = ! Security.isUserInRole("organizationalUnit:query");
				} else  if ( dataType.getType().equals(TypeEnumeration.GROUP_TYPE)) {
					setSelectIcon("/img/group.svg");
					dataHandler = new GroupDataHandler(dataType);
					setHyperlink(true);
					noPermissions = ! Security.isUserInRole(Security.AUTO_GROUP_QUERY);
				} else if ( dataType.getType().equals(TypeEnumeration.HOST_TYPE)) {
					setSelectIcon("/img/host.svg");
					dataHandler = new HostDataHandler(dataType);
					setHyperlink(true);
					noPermissions = ! Security.isUserInRole(Security.AUTO_HOST_ALL_QUERY) &&
							! Security.isUserInRole(Security.AUTO_HOST_ALL_SUPPORT_VNC);
				} else if ( dataType.getType().equals(TypeEnumeration.ROLE_TYPE)) {
					dataHandler = new RoleDataHandler(dataType);
					setSelectIcon("/img/role.svg");
					setHyperlink(true);
					noPermissions = ! Security.isUserInRole(Security.AUTO_ROLE_QUERY);
				} else if ( dataType.getType().equals(TypeEnumeration.ACCOUNT_TYPE)) {
					dataHandler = new AccountDataHandler(dataType);
					setSelectIcon("/img/account.svg");
					setHyperlink(true);
					noPermissions = ! Security.isUserInRole(Security.AUTO_ACCOUNT_QUERY);
				}
				else if ( dataType.getType().equals(TypeEnumeration.MAIL_DOMAIN_TYPE))
				{
					dataHandler = new MailDomainDataHandler(dataType);
					setSelectIcon("/img/maildomain.svg");
					setHyperlink(true);
					noPermissions = ! Security.isUserInRole(Security.AUTO_MAIL_QUERY);
				}
				else if ( dataType.getType().equals(TypeEnumeration.MAIL_LIST_TYPE))
				{
					dataHandler = new MailListDataHandler(dataType);
					setHyperlink(true);
					setSelectIcon("/img/maillist.svg");
					noPermissions = ! Security.isUserInRole(Security.AUTO_MAIL_QUERY);
				}
				else if ( dataType.getType().equals(TypeEnumeration.NETWORK_TYPE))
				{
					dataHandler = new NetworkDataHandler(dataType);
					setSelectIcon("/img/network.svg");
					setHyperlink(true);
				}
				else if (dataType.getType().equals(TypeEnumeration.USER_TYPE_TYPE)) {
					List<String> values = new LinkedList<String>();
					Security.nestedLogin(Security.ALL_PERMISSIONS);
					try {
						List<UserType> allUserType = new LinkedList<UserType> 
							(EJBLocator.getUserDomainService().findAllUserType());
						Collections.sort(allUserType, new Comparator<UserType>() {
							@Override
							public int compare(UserType o1, UserType o2) {
								return o1.getName().compareTo(o2.getName());
							}
						});
						for (UserType ut: allUserType ) {
							values.add( URLEncoder.encode( ut.getCode(), "UTF-8" )+":"+ut.getDescription());
						}
						setType(Databox.Type.LIST);
						setValues(values);
					} finally {
						Security.nestedLogoff();
					}
				}
				else if (dataType.getType().equals(TypeEnumeration.OS_TYPE)) {
					List<String> values = new LinkedList<String>();
					Security.nestedLogin(Security.ALL_PERMISSIONS);
					try {
						List<OsType> allOperatingSystems = new LinkedList<OsType> 
							(EJBLocator.getNetworkService().findAllOSTypes());
						Collections.sort(allOperatingSystems, new Comparator<OsType>() {
							@Override
							public int compare(OsType o1, OsType o2) {
								return o1.getName().compareTo(o2.getName());
							}
						});
						for (OsType ut: allOperatingSystems ) {
							values.add(URLEncoder.encode( ut.getName(), "UTF-8" )+":"+ut.getDescription());
						}
						setType(Databox.Type.LIST);
						setValues(values);
					} finally {
						Security.nestedLogoff();
					}
				}
				setSelectIcon2(getSelectIcon().substring(0, getSelectIcon().length()-4)+"-white.svg");
			}
			else if (dataType.getType() == TypeEnumeration.BINARY_TYPE ||
					dataType.getType() == TypeEnumeration.ATTACHMENT_TYPE) {
				setType(Databox.Type.BINARY);
				setSelectIcon("/img/clip.svg");
				setUploadIcon("/img/import.svg");
				setDownloadIcon("/img/download.svg");
				setClearIcon("/img/remove.svg");
				setUploadMessage(Labels.getLabel("altaDocumento.btnBrowse"));
				setDownloadMessage(Labels.getLabel("contenidoTarea.btnDescargar"));
				setClearMessage(Labels.getLabel("dadesAddicionals.zul.Esborra"));
			}
			else if (dataType.getType() == TypeEnumeration.BOOLEAN_TYPE) {
				setType(Databox.Type.BOOLEAN);
				setSclass("databox databox_switch");
			}
			else if (dataType.getType() == TypeEnumeration.DATE_TIME_TYPE)
			{
				setType(Databox.Type.DATE);
				setFormat(  DateFormats.getDateTimeFormatString());
				setCalendarIcon("/img/calendar.svg");
			}
			else if (dataType.getType() == TypeEnumeration.DATE_TYPE)
			{
				setType(Databox.Type.DATE);
				setFormat( DateFormats.getDateFormatString());
				setCalendarIcon("/img/calendar.svg");
			}
			else if (dataType.getType() == TypeEnumeration.EMAIL_TYPE)
			{
				setType(Databox.Type.STRING);
			}
			else if (dataType.getType() == TypeEnumeration.HTML)
			{
				setType(Databox.Type.HTML);
			}
			else if (dataType.getType() == TypeEnumeration.PASSWORD_TYPE)
			{
				setType(Databox.Type.PASSWORD);
			}
			else if (dataType.getType() == TypeEnumeration.PHOTO_TYPE)
			{
				setType(Databox.Type.IMAGE);
				setIcon("/img/upload.svg");
				setSelectIcon("/img/upload.svg");
				setSelectIcon2("/img/upload-white.svg");
			}
			else if (dataType.getType() == TypeEnumeration.SEPARATOR)
			{
				setType(Databox.Type.SEPARATOR);
				setSclass("databox separator");
			} else if (dataType.getValues() != null && !dataType.getValues().isEmpty()) {
				setType(Databox.Type.LIST);
				setValues(dataType.getValues());
			}
			else if (dataType.getType() == TypeEnumeration.NUMBER_TYPE)
			{
				setType(Databox.Type.NUMBER);
			} else {
				setType(Databox.Type.STRING);
				configureEnumeration();
			}
			if (dataType.getLabel() != null) {
				if ( dataType.getType() == TypeEnumeration.SEPARATOR)
					super.setLabel(dataType.getLabel());
				else {
					super.setLabel(dataType.getLabel()+" :");
					if (getPlaceholder() == null)
						setPlaceholder(dataType.getLabel());
				}
			}
			if (dataType.getHint() != null && ! dataType.getHint().isEmpty())
				setPlaceholder(dataType.getHint());
			if (dataType.getSize() != null)
				setMaxlength(dataType.getSize());
			setReadonly(this.readonly || dataType.isReadOnly());
			setMultiline(dataType.isMultiLine());
			setRequired(dataType.isRequired());

			if ( uiHandler != null) {
				try {
					uiHandler.beforeCreate(this);
				} catch (Exception e) {
					log.info("Error loading field "+dataType.getName(), e);
					setVisible(false);
				}			
			}

			invalidate();
		} catch (Throwable e) {
			log.warn(e);
		} finally {
			disableRecursive = false;
		}
		
	}


	private void calculateVisibility() throws Exception {
		if (XPathUtils.getComponentContext(this) != null)
		{
			if (uiHandler != null && !uiHandler.isVisible(this)) {
				super.setVisible(false);
			} 
			else if (dataType != null && 
					dataType.getVisibilityExpression() != null &&
					!dataType.getVisibilityExpression().trim().isEmpty())
			{
				Object obj = Evaluator.instance().evaluate(dataType.getVisibilityExpression(), 
						createVars(), 
						"Visibility expression for "+dataType.getName());
				if ( Boolean.FALSE.equals( obj ))
					super.setVisible(false);
				else
					super.setVisible(visible);
			}
			else
			{
				super.setVisible(visible);
			}
		} else {
			super.setVisible(visible);
		}
	}

	private String[] getFormValues() throws UnsupportedEncodingException {
		String result[] = new String[] {"", ""}; //$NON-NLS-1$ //$NON-NLS-2$
		String v = (String) getValue();
		if (v != null)
		{
			String split [] = v.split("="); //$NON-NLS-1$
			if (split.length > 0)
				result[0] = URLDecoder.decode(split[0], "UTF-8"); //$NON-NLS-1$
			if (split.length > 1)
				result[1] = URLDecoder.decode(split[1], "UTF-8"); //$NON-NLS-1$
		}
		return result;
	}

	private boolean fileAlreadySaved(){
		if(getValue() != null )
			return true;
		else
			return false;
	}
	
	
	public Object clone() {
		InputField3 clone = (InputField3) super.clone();
		clone.dataType = this.dataType;
		return clone;
	}
		
	
	public Object getOwnerObject() {
		return ownerObject;
	}

	public void setOwnerObject(Object ownerObject) {
		this.ownerObject = ownerObject;
	}
	

	public boolean attributeVisible()
	{
		try {
			if (uiHandler != null && !  uiHandler.isVisible(this))
				return false;
		} catch (Exception e) {
			throw new UiException(String.format("Visibility expression for attribute %s has generated an error condition", dataType.getCode()),
					e); //$NON-NLS-1$
		}

		if (dataType.getVisibilityExpression() != null &&
				! dataType.getVisibilityExpression().trim().isEmpty()) {
			try {
				Object o = Evaluator.instance().evaluate(dataType.getVisibilityExpression(), 
						createVars(), 
						"Visibility expression for "+dataType.getName());
				if (o == null)
					throw new UiException(String.format("Visibility expression for attribute %s has returned a null value: %s", dataType.getCode(), dataType.getVisibilityExpression())); //$NON-NLS-1$
				if (o != null && o instanceof Boolean)
					return ((Boolean) o).booleanValue();
				else
					throw new UiException(String.format("Visibility expression for attribute %s has not returned a boolean value", dataType.getCode())); //$NON-NLS-1$
			} catch (JXPathException e) {
				return false;
			} catch ( Exception e) {
				throw new UiException("Error evaluating visibility expression for attribute "+dataType.getName(), e);
			}
		}


		return true;
	}

	private Map<String,Object> createVars() throws EvalError {
		BindContext ctx = XPathUtils.getComponentContext(this);
		Object value = null;
		try {
			value = parseUiValue(getValue());
		} catch (Exception e) {
			// Ignore parse error
		}
		InputFieldContainer grandpa = getObjectContainer();
		Map attributes = null;
		try {
			attributes = grandpa != null ? ((InputFieldContainer) grandpa).getAttributesMap():
				(Map) XPathUtils.getValue(ctx, "/."); //$NON-NLS-1$
		} catch (Exception e) {
			attributes = new HashMap<>();
		}
		Map<String,Object> vars = new HashMap<>();

		vars.put("inputFields", new HashMap()); //$NON-NLS-1$
		// Identify attributes div
		Component c = this;
		do
		{
			if (c instanceof AttributesDiv)
			{
				vars.put("inputFields", ((AttributesDiv) c).getInputFieldsMap()); //$NON-NLS-1$
				break;
			}
			else if (c instanceof ObjectAttributesDiv)
			{
				vars.put("inputFields", ((ObjectAttributesDiv) c).getInputFieldsMap()); //$NON-NLS-1$
				break;
			}
			else if (c instanceof InputFieldContainer)
			{
				vars.put("inputFields", ((InputFieldContainer) c).getInputFieldsMap()); //$NON-NLS-1$
				break;
			}
			else
				c = c.getParent();
		} while (c != null);

		vars.put("value", value); //$NON-NLS-1$
		vars.put("attributes", attributes); //$NON-NLS-1$
		vars.put("serviceLocator", new com.soffid.iam.EJBLocator()); //$NON-NLS-1$
		vars.put("inputField", this); //$NON-NLS-1$
		if (ownerObject != null)
		{
			vars.put("object", ownerObject); //$NON-NLS-1$
			if (ownerObject instanceof User)
				vars.put("user", ownerObject); //$NON-NLS-1$
			if (ownerObject instanceof Usuari)
			{
				vars.put("user", User.toUser((Usuari) ownerObject)); //$NON-NLS-1$
				vars.put("object", User.toUser((Usuari) ownerObject)); //$NON-NLS-1$
			}
			if (ownerObject instanceof Group)
				vars.put("group", ownerObject); //$NON-NLS-1$
			if (ownerObject instanceof Grup)
			{
				vars.put("group", Group.toGroup((Grup) ownerObject) ); //$NON-NLS-1$
				vars.put("object", Group.toGroup((Grup) ownerObject) ); //$NON-NLS-1$
			}
			if (ownerObject instanceof Role)
				vars.put("role", ownerObject); //$NON-NLS-1$
			if (ownerObject instanceof Rol)
			{
				vars.put("role", Role.toRole((Rol) ownerObject)); //$NON-NLS-1$
				vars.put("object", Role.toRole((Rol) ownerObject)); //$NON-NLS-1$
			}
			if (ownerObject instanceof Application)
				vars.put("application", ownerObject); //$NON-NLS-1$
			if (ownerObject instanceof Aplicacio)
			{
				vars.put("application", Application.toApplication((Aplicacio) ownerObject)); //$NON-NLS-1$
				vars.put("object", Application.toApplication((Aplicacio) ownerObject)); //$NON-NLS-1$
			}
			if (ownerObject instanceof TaskInstance || ownerObject instanceof es.caib.bpm.vo.TaskInstance )
			{
				vars.put("task",  ownerObject); //$NON-NLS-1$
			}
			if (ownerObject instanceof ProcessInstance || ownerObject instanceof es.caib.bpm.vo.ProcessInstance)
			{
				vars.put("process", ownerObject); //$NON-NLS-1$
			}
		}
		vars.put("context", ownerContext); //$NON-NLS-1$
		vars.put("requestContext", ownerContext); //$NON-NLS-1$
		return vars;
	}

	public void setOwnerContext(String ownerContext) {
		this.ownerContext = ownerContext;
	}



	public boolean attributeValidateAll() {
		boolean ok = true;
		if(dataType != null && ! super.isReadonly()) {
			Object value = getValue();
			if (dataType.isMultiValued())
			{
				if (value != null && value instanceof List)
				{
					List l = (List) value;
					int i;
					for ( i = 0; i < l.size(); i++)
					{
						if (!attributeValidate(new Integer(i), l.get(0)))
							ok = false;
					}
				}
				if (dataType.isRequired() ) {
					if (value == null || collectionValue.isEmpty()) {
						setWarning(0, "Please, enter a value" );
						ok = false;
					}
				}
			}
			else
				ok = attributeValidate(new Integer(0), value);
		}
		return ok;
	}

	public boolean attributeValidate(Integer position, Object currentValue)
	{
		BindContext ctx = XPathUtils.getComponentContext(this);
		if (dataType.isRequired() && (currentValue == null || currentValue.toString().trim().isEmpty())) {
			if (dataType.isMultiValued() ) {
				if (collectionValue.isEmpty()) {
					setWarning(position, "Please, enter a value" );
					return false;
				}
			}
			else
			{
				setWarning(position, "Please, enter a value" );
				return false;
			}
		}
			

		if (dataType.getType() == TypeEnumeration.EMAIL_TYPE)
		{
			if (currentValue != null && ! currentValue.toString().trim().isEmpty())
			{
				String t = currentValue.toString();
				int p = t.indexOf('@');
				if ( p < 0) {
					setWarning(position, "Enter a valid email address. @ symbol is missing");
					return false;
				}
				int q = t.indexOf('@', p+1);
				if ( q >= 0) {
					setWarning(position, "Enter a valid email address. Only one @ is allowed");
					return false;
				}
				q = t.indexOf('.',p+1);
				if ( q < 0) {
					setWarning(position, "Enter a valid email address. The domain name after the @ symbol must have at least one dot (.)");
					return false;
				}
			}
		}
		
		if ( currentValue != null &&
				! currentValue.toString().trim().isEmpty() &&
				(getType().equals(Databox.Type.NAME_DESCRIPTION.toString()) ||
				 getType().equals(Databox.Type.DESCRIPTION.toString()))) {
			String d;
			try {
				d = getDescription(currentValue);
				if (d == null) {
					setWarning(position, "Invalid value");
					return false;
				}
			} catch (Exception e) {
				setWarning(position, e.getMessage());
				return false;
			}
		}

		if (dataType.getValidationExpression() != null &&
				! dataType.getValidationExpression().isEmpty())
		{
			try {
				Object o = Evaluator.instance().evaluate(dataType.getValidationExpression(), 
						createVars(), 
						"Validation expression for "+dataType.getName());
				if (o == null)
					throw new UiException(String.format("Validation expression for attribute %s has returned a null value", dataType.getCode())); //$NON-NLS-1$

				if (o != null && ! Boolean.TRUE.equals(o))
				{
					setWarning(position, o instanceof String ? o.toString(): "Wrong value"); //$NON-NLS-1$
					return false;
				}
			} catch ( Exception e) {
				setWarning(position, "Internal error" );
				throw new UiException(e);
			}
		}
		
		try {
			if (uiHandler != null && ! uiHandler.validate(this)) {
				return false;
			}
		} catch (Exception e) {
			setWarning(position, "Internal error" );
			throw new UiException (e.toString());
		}
		setWarning(position, "" );
		return true;
	}


	
	public void runOnChangeTrigger() {
		if (dataType != null && dataType.getOnChangeTrigger() != null && ! dataType.getOnChangeTrigger().trim().isEmpty())
		{
			try {
				Evaluator.instance().evaluate(dataType.getOnChangeTrigger(), 
						createVars(), 
						"On-change trigger for "+dataType.getName());
			} catch ( Exception e) {
				throw new UiException("Error evaluating on-change trigger for attribute "+dataType.getName(), e);
			}

		}
		if ( uiHandler != null) {
			try {
				uiHandler.onChange(this);
			} catch (Exception e) {
				throw new UiException("Error loading field "+dataType.getName(), e);
			}			
		}
	}

	
	public void setSearchFilter(SearchFilter filter) {
		this.filter = filter;
	}

	public boolean isHideUserName() {
		return hideUserName;
	}

	public void setHideUserName(boolean hideUserName) {
		this.hideUserName = hideUserName;
	}

	public boolean isRaisePrivileges() {
		return raisePrivileges;
	}

	public void setRaisePrivileges(boolean raisePrivileges) {
		this.raisePrivileges = raisePrivileges;
	}

	public void runOnLoadTrigger() {
		if (dataType != null && dataType.getOnLoadTrigger() != null && ! dataType.getOnLoadTrigger().trim().isEmpty() &&
				binder.isValid())
		{
			try {
				Evaluator.instance().evaluate(dataType.getOnLoadTrigger(), 
						createVars(), 
						"On-load trigger for "+dataType.getName());
			} catch ( Exception e) {
				throw new UiException("Error evaluating on-load trigger for attribute "+dataType.getName(), e);
			}

		}
		if ( uiHandler != null) {
			try {
				uiHandler.afterCreate(this);
			} catch (Exception e) {
				throw new UiException("Error loading field "+dataType.getName(), e);
			}			
		}
	}

	public void onFocus(Event ev) 
	{
	}
	
	
	public void runOnFocusTrigger() {
		if (dataType != null && dataType.getOnFocusTrigger() != null && ! dataType.getOnFocusTrigger().trim().isEmpty())
		{
			try {
				Evaluator.instance().evaluate(dataType.getOnFocusTrigger(), 
						createVars(), 
						"On-focus trigger for "+dataType.getName());
			} catch ( Exception e) {
				throw new UiException("Error evaluating on-focus trigger for attribute "+dataType.getName(), e);
			}

		}
	}

	@Override
	public String getDescription(Object name) throws Exception {
		if (dataHandler == null || name == null || name.toString().trim().isEmpty())
			return null;
		else {
			if (raisePrivileges)
				Security.nestedLogin(Security.ALL_PERMISSIONS);
			try {
				String d = null;
				if (descriptionExpression == null)
					d = dataHandler.getDescription(name.toString(), dataType.getFilterExpression());
				else {
					Object o = dataHandler.getObject(name.toString(), dataType.getFilterExpression());
					if ( o != null ) {
						d = evaluateDescriptionExpression(o);
					}
				}
				String link = dataHandler.followLink(name.toString());
				if (link != null) {
					d = "<a href='"+  XMLs.encodeAttribute(link)+"' target='_blank' class='shylink'>"+XMLs.escapeXML(d)+"</a>";
				} else if (d == null) {
					return "";
				} else {
					d = XMLs.encodeAttribute(d);
				}
				return d;
			} catch (Exception e) {
				noPermissions = true;
				Security.nestedLogin(Security.ALL_PERMISSIONS);
				try {
					String d = null;
					if (descriptionExpression == null)
						d = dataHandler.getDescription(name.toString(), dataType.getFilterExpression());
					else {
						Object o = dataHandler.getObject(name.toString(), dataType.getFilterExpression());
						if ( o != null ) {
							d = evaluateDescriptionExpression(o);
						}
					}
					return  d == null ? "" : XMLs.encodeAttribute(d);
				} finally {
					Security.nestedLogoff();
				}
			} finally {
				if (raisePrivileges)
					Security.nestedLogoff();
			}
		}
	}

	
	private String evaluateDescriptionExpression(final Object value) {
		xelContext.setVariableResolver(new VariableResolver() {
			@Override
			public Object resolveVariable(String name) throws XelException {
				if ("this".equals(name))
					return value;
				Object p;
				try {
					p = PropertyUtils.getProperty(value, name);
					if (p == null)
						return null;
					if (p instanceof Date)
						return DateFormats.getDateFormat().format((Date)p);
					if (p instanceof Calendar)
						return DateFormats.getDateFormat().format(((Calendar)p).getTime());
				} catch (Exception e) {
					throw new XelException ( e );
				}
				return p;
			}
		});
		xelContext.setFunctionMapper( getPage().getFunctionMapper());
		return (String) descriptionExpressionCompiled.evaluate(xelContext);
	}

	@Override
	public List<String[]> findObjects(String text) throws Throwable {
		if (currentList != null)
			currentList.cancel();
		if ( dataHandler != null && ! noPermissions) {
			try {
				currentList = dataHandler.search(text, dataType.getFilterExpression());
			} catch (Exception e) {
				log.info("Error searchinf for "+text, e);
			}
			currentPosition = 0;
			return fetchObjects();
		} else
			return null;
	}

	@Override
	public List<String[]> findNextObjects() throws Throwable {
		if ( currentList != null)
			return fetchObjects();
		else
			return null;
	}
	
	private List<String[]> fetchObjects() throws Throwable {
		List<String[]> result = null;
		if (currentList != null) {
			Iterator<?> it = currentList.iterator();
			if ( (currentList.isDone() &&  currentPosition == currentList.size()) || currentList.isCancelled())
			{
				Throwable th = currentList.getExceptionToThrow();
				currentList.clearExceptionToThrow();
				if (th != null)
				{
					throw th; 
				}
			} else if (currentList.size() > currentPosition) {
				int i = 0;
				result = new LinkedList();
				while (it.hasNext() && currentPosition < 100)
				{
				    Object o = it.next();
					if (i++ >= currentPosition)
					{
						String[] row = ((InputFieldDataHandler<Object>)dataHandler).toNameDescription(o);
						if (descriptionExpression != null) {
							row[1] = evaluateDescriptionExpression(o);
						}
						result.add(row);
						currentPosition ++;
					}
				}
				if (currentPosition >= 100) 
					currentList.cancel();
			} else {
				result = new LinkedList<>();
			}
		}
		return result;
	}
	
	
	public void openSelectWindow(final Integer position) throws UiException {
		if (noPermissions)
			return;
		if (customOpenDialog())
			return;
		
		if (dataHandler != null)
		{
			try {
				if (listener == null)
					listener = new FinderListener();
				dataHandler.openFinder(dataType.getFilterExpression(), dataType.isMultiValued(), this, listener);
			} catch (Exception e) {
				throw new UiException(e);
			}
		}
		else if (dataType.getType() == TypeEnumeration.PHOTO_TYPE) {
			FileUpload2.get((event) -> {
	        	Media dataSubida = ((UploadEvent)event).getMedia();
	        	if (dataSubida == null) return; //Per si l'usuari pitja en Cancelar
	        	if (!dataSubida.isBinary()) {
	        		throw new UiException(Messages.getString("PluginsUI.NotBinaryFileError")); //$NON-NLS-1$
	        	}
	        	byte data[];
	        	if (dataSubida.inMemory()) {
	        		data = dataSubida.getByteData();
	        	} else {
	        		ByteArrayOutputStream os = new ByteArrayOutputStream();
	        		InputStream is = dataSubida.getStreamData();
	        		byte b[] = new byte[2048];
	        		int read = is.read(b);
	        		while (read > 0) {
	        			os.write(b, 0, read);
	        			read = is.read(b);
	        		}
	        		is.close();
	        		os.close();
	        		data = os.toByteArray();
	        	}
	        	binder.setValue(data);
	        	invalidate();
			});
		}
		else if (dataType.getType() == TypeEnumeration.HTML) {
			HtmlEditor.edit(this);
		}
		else if (isMultiline()) {
			if (javascripthelp == null)
				javascripthelp = (String) getAttribute("javascripthelp");
			Editor.edit(this, javascript == null ? "{}" : javascript, javascripthelp);
		}
	}

	private boolean customOpenDialog() {
		try {
			return uiHandler != null && uiHandler.openSelectWindow(this);
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	@Override
	public void onUpload(final Integer position) {
		FileUpload2.get(dataType.isMultiValued(), (event2) -> {
			UploadEvent ue = (UploadEvent) event2;
			int pos = position.intValue();
			for (Media m: ue.getMedias()) {
				BinaryData bd;
				if (m.isBinary() && m.inMemory())
					bd = new BinaryData(m.getName(), m.getByteData());
				else if (m.isBinary()) 
					bd = new BinaryData(m.getName(), m.getStreamData());
				else if (m.inMemory()) 
					bd = new BinaryData(m.getName(), m.getStringData().getBytes(StandardCharsets.UTF_8));
				else 
					bd = new BinaryData(m.getName(), m.getReaderData());
				onItemChange(bd, pos);
				pos++;
			}
			invalidate();
		});
	}

	@Override
	public void onClear(final Integer position) {
		onItemChange(null, position);
		invalidate();
	}

	@Override
	public void onDownload(final Integer position) {
		Object data = binder.getValue();
		Object value = null;
		if (dataType.isMultiValued()) {
			List values = (List) data;
			if (values != null && values.size() > position.intValue())
				value = values.get(position.intValue());
		} else {
			value = data;
		}

		if (value instanceof BinaryData) {
			BinaryData bd = (BinaryData) value;
			AMedia m;
			try {
				m = new AMedia(bd.getName(), null, "binary/octet-stream", bd.getInputStream());
			} catch (FileNotFoundException e) {
				throw new UiException(e);
			}
			Filedownload.save(m);
		} else {
			AMedia m = new AMedia("noname", null, "binary/octet-stream", (byte[]) value);
			Filedownload.save(m);
		}
		invalidate();
	}


	class FinderListener implements EventListener {
		@Override
		public void onEvent(Event event) throws Exception {
			if (dataType.isMultiValued()) {
				List values = (List) event.getData();
				for (Object value: values) {
					onItemChange(value, collectionValue.size());
				}
				refreshValue();
			} else {
				onItemChange(event.getData(), 0);
				refreshValue();
			}
		}
	}
	
	protected Object parseUiValue(Object value) {
		if (dataType != null && dataType.getEnumeration() != null && ! dataType.getEnumeration().trim().isEmpty()) {
			List<String> r = new LinkedList<String>();
			if (value == null || value.toString().isEmpty())
				value = null;
			else {
				try {
					Class<?> cl = Class.forName(dataType.getEnumeration());
					value = cl.getMethod("fromString", String.class).invoke(null, (String)value);
				} catch (NoSuchMethodException e) {
					try {
						Class<?> cl = Class.forName(dataType.getEnumeration());
						value = cl.getMethod("fromLong", Long.class).invoke(null, Long.decode(value.toString()));						
					} catch (Exception e2) {
						throw new UiException(e);
					}
				} catch (Exception e) {
					throw new UiException(e);
				}
			}
		} else if ( dataType != null && dataType.getType() == TypeEnumeration.PASSWORD_TYPE){
			value = new Password((String)value);
		} else {
			value = super.parseUiValue(value);
		}
		return value;
	}

	protected void configureEnumeration() {
		if (dataType != null && dataType.getEnumeration() != null && !dataType.getEnumeration().trim().isEmpty()) {
			List<String> r = new LinkedList<String>();
			try {
				Class<?> cl = Class.forName(dataType.getEnumeration());
				for (Field field: cl.getFields()) {
					if ( (field.getModifiers() & Modifier.STATIC) != 0) {
						if (field.getType() == cl) {
							String name = field.getName();
							Object value = field.get(null);
							String v = value.toString();
							String label = Labels.getLabel(cl.getName()+"."+name);
							if (label == null || label.trim().isEmpty())
								label = name;
							r.add(URLEncoder.encode( v, "UTF-8" )+":"+ label);
						}
					}
				}
				setValues(r);
				setType(Type.LIST);
			} catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException | UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		} else if (keysPath != null) {
			List<String> keys = calcListValues(keysPath);
			List<String> labels = calcListValues(valuesPath);
			List<String> values = new LinkedList<>();
			for (int i = 0; i < keys.size(); i++) {
				String key;
				try {
					key = keys.get(i);
					if (labels.size() > i)
						key = URLEncoder.encode( key, "UTF-8") + ":"+labels.get(i);
					else
						key = URLEncoder.encode( key, "UTF-8") + ":"+key;
				} catch (UnsupportedEncodingException e) {
					throw new RuntimeException(e);
				}
				values.add(key);
			}
			setValues(values);
			setType(Type.LIST);
		}
	}

	public List<String> calcListValues(String path) {
		List<String> v = new LinkedList<>();
		if (path == null) return v;
		Component c = this;
		if (path.contains(":")) {
			c = XPathUtils.getPath(this, keysPath.substring(0,path.indexOf(":")));
			path = path.substring(path.indexOf(":")+1);
		}
		if (c instanceof DataSource) {
			DataSource ds = (DataSource) c;
			for (Iterator it = ds.getJXPathContext().iterate( path );
					it.hasNext();)
			{
				Object o = it.next();
				v.add(o == null ? null: o.toString());
			}
		} else {
			BindContext b = XPathUtils.getComponentContext(c);
			for (Iterator it = b.getDataSource().getJXPathContext().iterate( XPathUtils.concat(b.getXPath(), path));
					it.hasNext();)
			{
				Object o = it.next();
				v.add(o == null ? null: o.toString());
			}
		}
		return v;
	}
	
	public Object getValueObject() throws Exception {
		if (isMultiValue())
			throw new RuntimeException("Input field is multivalued");
		else if (dataHandler == null)
			return null;
		else {
			Object value = getValue();
			if (value == null) return null;
			else return dataHandler.getObject(value.toString(), dataType.getFilterExpression());
		}
	}

	public List<Object> getValueObjects() throws Exception {
		if (! isMultiValue())
			throw new RuntimeException("Input field is singlevalued");
		else if (dataHandler == null)
			return null;
		else {
			List<Object> values = (List<Object>) getValue();
			if (values == null) return null;
			else {
				List<Object> r = new LinkedList<>();
				for (Object value: values)
					r.add ( dataHandler.getObject(value.toString(), dataType.getFilterExpression()) );
				return r;
			}
		}
	}

	
	public boolean isReadonly() {
		return readonly || noPermissions;
	}

	
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
		super.setReadonly(readonly);
	}

	
	public InputFieldDataHandler<?> getDataHandler() {
		return dataHandler;
	}

	
	public void setDataHandler(InputFieldDataHandler<?> dataHandler) {
		this.dataHandler = dataHandler;
	}

	
	public String getKeysPath() {
		return keysPath;
	}

	
	public void setKeysPath(String keysPath) {
		this.keysPath = keysPath;
	}

	
	public String getValuesPath() {
		return valuesPath;
	}

	
	public void setValuesPath(String valuesPath) {
		this.valuesPath = valuesPath;
	}

	
	public boolean isVisible() {
		return super.isVisible();
	}

	
	public boolean setVisible(boolean visible) {
		this.visible = visible;
		if (visible) {
			try {
				calculateVisibility();
			} catch (Exception e) {
			}
			return super.isVisible();
		}
		else
			return super.setVisible(visible);
	}

	
	public String getJavascript() {
		return javascript;
	}

	
	public void setJavascript(String javascript) {
		this.javascript = javascript;
	}
	
	protected void refreshValue () {
		raisePrivileges = true;
		try {
			super.refreshValue();
		} finally {
			raisePrivileges = false;
		}
	}
	
	public Object translateToUserInterface(Object o) {
		if (dataType != null && dataType.getType() == TypeEnumeration.ATTACHMENT_TYPE && o != null) {
			String name = null;
			if (o instanceof BinaryData) 
				name = ((BinaryData)o).getName();
			if (name == null) name = Labels.getLabel("inbox.lblAttachment");
			return name;
		}
		else if (dataType != null  && dataType.getType() == TypeEnumeration.BINARY_TYPE && o != null) {
			return Labels.getLabel("inbox.lblAttachment");
		}
		else
			return super.translateToUserInterface(o);
	}

	
	public String getDescriptionExpression() {
		return descriptionExpression;
	}

	
	public void setDescriptionExpression(String descriptionExpression) {
		this.descriptionExpression = descriptionExpression;
		if (descriptionExpression == null)
			descriptionExpressionCompiled = null;
		else {
			xelContext.setFunctionMapper(getPage().getFunctionMapper());
			descriptionExpressionCompiled = new ELFactory()
				.parseExpression(xelContext, descriptionExpression.replace("#{", "${"), String.class);
		}
	}

	
	public String getJavascripthelp() {
		return javascripthelp;
	}

	
	public void setJavascripthelp(String javascripthelp) {
		this.javascripthelp = javascripthelp;
	}

}