package com.soffid.iam.web.component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URLDecoder;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.LogFactory;
import org.zkoss.util.media.Media;
import org.zkoss.util.resource.Labels;
import org.zkoss.xml.HTMLs;
import org.zkoss.xml.XMLs;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Application;
import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Group;
import com.soffid.iam.api.OsType;
import com.soffid.iam.api.Password;
import com.soffid.iam.api.Role;
import com.soffid.iam.api.Task;
import com.soffid.iam.api.User;
import com.soffid.iam.api.UserType;
import com.soffid.iam.bpm.api.ProcessInstance;
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
import com.soffid.iam.web.component.inputField.RoleDataHandler;
import com.soffid.iam.web.component.inputField.UserDataHandler;
import com.soffid.iam.web.popup.Editor;
import com.soffid.iam.web.popup.FileUpload2;
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
	
	private SearchFilter filter;

	private AsyncList<?> currentList;

	private int currentPosition;

	private EventListener listener;
	
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

	public ObjectAttributesDiv getObjectContainer () {
		Component c = this;
		do
		{
			if (c instanceof ObjectAttributesDiv)
			{
				return ((ObjectAttributesDiv) c);
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
			else if (c instanceof ObjectAttributesDiv)
			{
				((ObjectAttributesDiv) c).adjustVisibility();
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
								dataType.getType().equals(TypeEnumeration.OS_TYPE) )
				
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
				}
				else if ( dataType.getType().equals(TypeEnumeration.APPLICATION_TYPE))
				{
					dataHandler = new ApplicationDataHandler(dataType);
					setHyperlink(true);
				}
				else if ( dataType.getType().equals(TypeEnumeration.CUSTOM_OBJECT_TYPE)) {
					dataHandler = new CustomObjectDataHandler(dataType);
					setHyperlink(true);
				} else  if ( dataType.getType().equals(TypeEnumeration.GROUP_TYPE_TYPE)) {
					setSelectIcon("/img/group.svg");
					dataHandler = new OUTypeDataHandler(dataType);
					setHyperlink(true);
				} else  if ( dataType.getType().equals(TypeEnumeration.GROUP_TYPE)) {
					setSelectIcon("/img/group.svg");
					dataHandler = new GroupDataHandler(dataType);
					setHyperlink(true);
				} else if ( dataType.getType().equals(TypeEnumeration.HOST_TYPE)) {
					setSelectIcon("/img/host.svg");
					dataHandler = new HostDataHandler(dataType);
					setHyperlink(true);
				} else if ( dataType.getType().equals(TypeEnumeration.ROLE_TYPE)) {
					dataHandler = new RoleDataHandler(dataType);
					setSelectIcon("/img/role.svg");
					setHyperlink(true);
				} else if ( dataType.getType().equals(TypeEnumeration.ACCOUNT_TYPE)) {
					dataHandler = new AccountDataHandler(dataType);
					setSelectIcon("/img/account.svg");
					setHyperlink(true);
				}
				else if ( dataType.getType().equals(TypeEnumeration.MAIL_DOMAIN_TYPE))
				{
					dataHandler = new MailDomainDataHandler(dataType);
					setSelectIcon("/img/maildomain.svg");
					setHyperlink(true);
				}
				else if ( dataType.getType().equals(TypeEnumeration.MAIL_LIST_TYPE))
				{
					dataHandler = new MailListDataHandler(dataType);
					setHyperlink(true);
					setSelectIcon("/img/maillist.svg");
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
							values.add(ut.getCode()+":"+ut.getDescription());
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
							values.add(ut.getName()+":"+ut.getDescription());
						}
						setType(Databox.Type.LIST);
						setValues(values);
					} finally {
						Security.nestedLogoff();
					}
				}
				setSelectIcon2(getSelectIcon().substring(0, getSelectIcon().length()-4)+"-white.svg");
			}
			else if (dataType.getType() == TypeEnumeration.BINARY_TYPE)
				setVisible(false);
			else if (dataType.getType() == TypeEnumeration.BOOLEAN_TYPE)
				setType(Databox.Type.BOOLEAN);
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
					setPlaceholder(dataType.getLabel());
				}
			}
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
		if (uiHandler != null && !uiHandler.isVisible(this)) {
			setVisible(false);
		} 
		else if (dataType.getVisibilityExpression() != null &&
				!dataType.getVisibilityExpression().trim().isEmpty())
		{
			SecureInterpreter interp = createInterpreter();
			if ( Boolean.FALSE.equals(interp.eval(dataType.getVisibilityExpression())))
				this.setVisible(false);
			else
				this.setVisible(true);
		}
		else
		{
			this.setVisible(true);
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
				SecureInterpreter i = createInterpreter();
				Object o = i.eval(dataType.getVisibilityExpression());
				if (o == null)
					throw new UiException(String.format("Visibility expression for attribute %s has returned a null value: %s", dataType.getCode(), dataType.getVisibilityExpression())); //$NON-NLS-1$
				if (o != null && o instanceof Boolean)
					return ((Boolean) o).booleanValue();
				else
					throw new UiException(String.format("Visibility expression for attribute %s has not returned a boolean value", dataType.getCode())); //$NON-NLS-1$
			} catch ( TargetError e) {
				throw new UiException(e.getTarget());
			} catch ( EvalError e) {
				throw new UiException(e.toString(), e);
			} catch (MalformedURLException e) {
				throw new UiException (e.toString());
			} catch (JXPathException e) {
				return false;
			}
		}


		return true;
	}

	private SecureInterpreter createInterpreter() throws EvalError {
		BindContext ctx = XPathUtils.getComponentContext(this);
		Object value = null;
		value = getValue();
		ObjectAttributesDiv grandpa = getObjectContainer();
		Map attributes = grandpa != null ? ((ObjectAttributesDiv) grandpa).getAttributesMap():
			(Map) XPathUtils.getValue(ctx, "/."); //$NON-NLS-1$
		SecureInterpreter i = new SecureInterpreter();

		// Identify attributes div
		Component c = this;
		do
		{
			if (c instanceof AttributesDiv)
			{
				i.set("inputFields", ((AttributesDiv) c).getInputFieldsMap()); //$NON-NLS-1$
				break;
			}
			else if (c instanceof ObjectAttributesDiv)
			{
				i.set("inputFields", ((ObjectAttributesDiv) c).getInputFieldsMap()); //$NON-NLS-1$
				break;
			}
			else
				c = c.getParent();
		} while (c != null);

		i.set("value", value); //$NON-NLS-1$
		i.set("attributes", attributes); //$NON-NLS-1$
		i.set("serviceLocator", new com.soffid.iam.EJBLocator()); //$NON-NLS-1$
		i.set("inputField", this); //$NON-NLS-1$
		if (ownerObject != null)
		{
			i.set("object", ownerObject); //$NON-NLS-1$
			if (ownerObject instanceof User)
				i.set("user", ownerObject); //$NON-NLS-1$
			if (ownerObject instanceof Usuari)
			{
				i.set("user", User.toUser((Usuari) ownerObject)); //$NON-NLS-1$
				i.set("object", User.toUser((Usuari) ownerObject)); //$NON-NLS-1$
			}
			if (ownerObject instanceof Group)
				i.set("group", ownerObject); //$NON-NLS-1$
			if (ownerObject instanceof Grup)
			{
				i.set("group", Group.toGroup((Grup) ownerObject) ); //$NON-NLS-1$
				i.set("object", Group.toGroup((Grup) ownerObject) ); //$NON-NLS-1$
			}
			if (ownerObject instanceof Role)
				i.set("role", ownerObject); //$NON-NLS-1$
			if (ownerObject instanceof Rol)
			{
				i.set("role", Role.toRole((Rol) ownerObject)); //$NON-NLS-1$
				i.set("object", Role.toRole((Rol) ownerObject)); //$NON-NLS-1$
			}
			if (ownerObject instanceof Application)
				i.set("application", ownerObject); //$NON-NLS-1$
			if (ownerObject instanceof Aplicacio)
			{
				i.set("application", Application.toApplication((Aplicacio) ownerObject)); //$NON-NLS-1$
				i.set("object", Application.toApplication((Aplicacio) ownerObject)); //$NON-NLS-1$
			}
			if (ownerObject instanceof Task)
			{
				i.set("task",  ownerObject); //$NON-NLS-1$
			}
			if (ownerObject instanceof ProcessInstance)
			{
				i.set("process", ownerObject); //$NON-NLS-1$
			}
		}
		i.set("context", ownerContext); //$NON-NLS-1$
		i.set("requestContext", ownerContext); //$NON-NLS-1$
		return i;
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
				SecureInterpreter i = createInterpreter();
				Object o = i.eval(dataType.getValidationExpression());
				if (o == null)
					throw new UiException(String.format("Validation expression for attribute %s has returned a null value", dataType.getCode())); //$NON-NLS-1$
				if (o != null && ! Boolean.TRUE.equals(o))
				{
					if  (!((Boolean) o).booleanValue()) {
						setWarning(position, o instanceof String ? (String) o: "Wrong value"); //$NON-NLS-1$
						return false;
					}
				}
			} catch ( TargetError e) {
				setWarning(position, "Internal error" );
				if (e.getTarget() instanceof UiException)
					throw new UiException(e);
				else
					throw new RuntimeException(e.getTarget());
			} catch ( EvalError e) {
				setWarning(position, "Internal error" );
				throw new UiException(e.toString());
			} catch (MalformedURLException e) {
				setWarning(position, "Internal error" );
				throw new UiException (e.toString());
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
				SecureInterpreter i = createInterpreter();
				i.eval(dataType.getOnChangeTrigger());
			} catch ( TargetError e) {
				if (e.getTarget() instanceof UiException)
					throw new UiException(e);
				else
					throw new RuntimeException(e.getTarget());
			} catch ( EvalError e) {
				throw new UiException(e.toString());
			} catch (MalformedURLException e) {
				throw new UiException (e.toString());
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
				SecureInterpreter i = createInterpreter();
				i.eval(dataType.getOnLoadTrigger());
			} catch ( TargetError e) {
				if (e.getTarget() instanceof UiException)
					throw new UiException(e);
				else
					throw new RuntimeException(e.getTarget());
			} catch ( EvalError e) {
				throw new UiException(e.toString());
			} catch (MalformedURLException e) {
				throw new UiException (e.toString());
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
				SecureInterpreter i = createInterpreter();
				i.eval(dataType.getOnFocusTrigger());
			} catch ( TargetError e) {
				if (e.getTarget() instanceof UiException)
					throw new UiException(e);
				else
					throw new RuntimeException(e.getTarget());
			} catch ( EvalError e) {
				throw new UiException(e.toString());
			} catch (MalformedURLException e) {
				throw new UiException (e.toString());
			}

		}
	}

	@Override
	public String getDescription(Object name) throws Exception {
		if (dataHandler == null || name == null || name.toString().trim().isEmpty())
			return null;
		else {
			String d = dataHandler.getDescription(name.toString(), dataType.getFilterExpression());
			String link = dataHandler.followLink(name.toString());
			if (link != null) {
				d = "<a href='"+  XMLs.encodeAttribute(link)+"' target='_blank' class='shylink'>"+XMLs.escapeXML(d)+"</a>";
			} else {
				d = XMLs.encodeAttribute(d);
			}
			return d;
		}
	}

	@Override
	public List<String[]> findObjects(String text) throws Throwable {
		if (currentList != null)
			currentList.cancel();
		if ( dataHandler != null) {
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
				while (it.hasNext())
				{
				    Object o = it.next();
					if (i++ >= currentPosition)
					{
						String[] row = ((InputFieldDataHandler<Object>)dataHandler).toNameDescription(o);
						result.add(row);
						currentPosition ++;
					}
				}
			} 
		}
		return result;
	}
	
	
	public void openSelectWindow(Integer position) throws UiException {
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
		else if (isMultiline()) {
			Editor.edit(this, "{}");
		}
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
			try {
				Class<?> cl = Class.forName(dataType.getEnumeration());
				value = cl.getMethod("fromString", String.class).invoke(null, (String)value);
			} catch (Exception e) {
				throw new UiException(e);
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
							r.add(v+":"+ label);
						}
					}
				}
				setValues(r);
				setType(Type.LIST);
			} catch (ClassNotFoundException | IllegalArgumentException | IllegalAccessException e) {
				throw new RuntimeException(e);
			}
		}
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
		return readonly;
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

}