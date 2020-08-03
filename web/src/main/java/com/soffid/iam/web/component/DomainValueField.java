package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.logging.LogFactory;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.metainfo.EventHandler;
import org.zkoss.zk.ui.metainfo.ZScript;

import com.soffid.iam.api.AsyncList;
import com.soffid.iam.api.DataType;
import com.soffid.iam.web.component.inputField.ApplicationDataHandler;
import com.soffid.iam.web.component.inputField.DomainValueDataHandler;
import com.soffid.iam.web.component.inputField.GroupDataHandler;
import com.soffid.iam.web.component.inputField.InputFieldDataHandler;
import com.soffid.iam.web.users.additionalData.SearchFilter;

import es.caib.seycon.ng.comu.TipusDomini;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.Databox;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;

public class DomainValueField extends Databox
{
	org.apache.commons.logging.Log log = LogFactory.getLog(getClass());
	
	private static final long serialVersionUID = 1L;
	boolean raisePrivileges = false;
	boolean updating = false; 
	boolean disableRecursive = false;
	InputFieldDataHandler<?> dataHandler = null;
	String domain = null;
	String application = null;
	
	private SearchFilter filter;

	private AsyncList<?> currentList;

	private int currentPosition;

	private EventListener listener;
	
	public DomainValueField(){
		super();
		setWarningIcon("/img/warning.svg");
	}
	
	public void onCreate () throws NamingException, CreateException, InternalErrorException, IOException
	{
		createField();
	}
	
	protected void onItemChange(Object value, Integer pos) {
		super.onItemChange(value, pos);
		attributeValidate( pos , value);
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
			addEventHandler("onChange", new EventHandler(ZScript.parseContent(""), null));
		disableRecursive = true;
		
		try
		{
			setType(Databox.Type.NAME_DESCRIPTION);
			DataType dataType = null;
			if ( TipusDomini.APPLICATIONS.equals(domain))
			{
				dataHandler = new ApplicationDataHandler(dataType);
				setSelectIcon("/img/application.svg");
			}
			else if ( TipusDomini.GROUPS.equals(domain))
			{
				dataHandler = new GroupDataHandler(dataType);
				setSelectIcon("/img/group.svg");
			}
			else if ( TipusDomini.MEMBERSHIPS.equals(domain))
			{
				dataHandler = new GroupDataHandler(dataType);
				setSelectIcon("/img/group.svg");
			}
			else if (domain != null)
			{
				dataHandler = new DomainValueDataHandler(dataType, application, domain);
			}
		} catch (Throwable e) {
			log.warn(e);
		} finally {
			disableRecursive = false;
		}
		
	}


	public Object clone() {
		DomainValueField clone = (DomainValueField) super.clone();
		clone.application = this.application;
		clone.domain = this.domain;
		return clone;
	}
		
	
	public boolean isRaisePrivileges() {
		return raisePrivileges;
	}

	public void setRaisePrivileges(boolean raisePrivileges) {
		this.raisePrivileges = raisePrivileges;
	}


	public void onFocus(Event ev) 
	{
	}
	
	

	@Override
	public String getDescription(Object name) throws Exception {
		if (dataHandler == null || name == null || name.toString().trim().isEmpty())
			return null;
		else {
			String d = dataHandler.getDescription((String)name, null);
			return d;
		}
	}

	@Override
	public List<String[]> findObjects(String text) throws Throwable {
		if (currentList != null)
			currentList.cancel();
		if ( dataHandler != null) {
			try {
				currentList = dataHandler.search(text, null);
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
		return result;
	}
	
	
	public void openSelectWindow(Integer position) throws UiException {
		try {
			if (listener == null)
				listener = new FinderListener();
			dataHandler.openFinder(null, isMultiValue(), this, listener);
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	class FinderListener implements EventListener {
		@Override
		public void onEvent(Event event) throws Exception {
			if (isMultiValue()) {
				
			} else {
				onItemChange(event.getData(), 0);
				refreshValue();
			}
		}
	}
	
	
	public String getDomain() {
		return domain;
	}

	
	public void setDomain(String domain) {
		this.domain = domain;
	}

	
	public String getApplication() {
		return application;
	}

	
	public void setApplication(String application) {
		this.application = application;
	}
	
	public boolean attributeValidateAll() {
		boolean ok = true;
		if (isReadonly()) {
			Object value = getValue();
			if (isMultiValue())
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
				if (isRequired() ) {
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
		if (isRequired() && (currentValue == null || currentValue.toString().trim().isEmpty())) {
			if (isMultiValue() ) {
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
			

		if ( currentValue != null && 
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

		setWarning(position, "" );
		return true;
	}
}