package com.soffid.iam.web.popup;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Button;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Identity;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.api.User;
import com.soffid.iam.web.component.DynamicColumnsDatatable;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.iam.web.component.SearchDictionaryBuilder;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.list.ModelProxy;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.zkiblaf.Missatgebox;


public class FinderHandler extends Window implements AfterCompose {
	private EventListener listener;
	private Boolean multi;
	private String className;
	private String filter;

	public FinderHandler() {
		Map args = Executions.getCurrent().getAttributes();
		if (args != null) {
			listener = (EventListener) args.get("listener");
			multi = (Boolean) args.get("multi");
			className = (String) args.get("className");
			filter = (String) args.get("filter");
			setTitle( (String) args.get("title"));
		}
	}

	
	@Override
	public void setPage(Page page) {
		super.setPage(page);
	}
	
		
	public void onClose(Event event) {
		event.stopPropagation();		                                 
		close (event);
	}
	
	public void close(Event event) {
		setVisible(false);
	}
	
	@Override
	public void afterCompose() {
		
		DataModel model = (DataModel) getFellow("model");
		model.getVariables().declareVariable("className", className);
		doHighlighted();
	}
	
	protected static String defaultColumns(String className) throws InternalErrorException, NamingException, CreateException {
		String objectType = className.startsWith(SearchDictionaryBuilder.COM_SOFFID_IAM_API_CUSTOM_OBJECT) ?
				className.substring(SearchDictionaryBuilder.COM_SOFFID_IAM_API_CUSTOM_OBJECT.length()) :
				className;
		StringBuffer sb = new StringBuffer();
		LinkedList<DataType> l = new LinkedList<DataType>( 
				EJBLocator
					.getAdditionalDataService()
					.findDataTypesByObjectTypeAndName2(objectType, null) );
		Collections.sort(l, new Comparator<DataType>() {
			@Override
			public int compare(DataType o1, DataType o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
			
		});
		for (Iterator<DataType> it = l.iterator(); it.hasNext();) {
			DataType dt = it.next();
			if (dt.getSearchCriteria() != null && dt.getSearchCriteria().booleanValue())
			{
				if (sb.length() > 0)
					sb.append(", ");
				sb.append(dt.getName());
			}
		}
		if (sb.length() == 0) {
			for (int i = 0; i < 2 && i < l.size(); i++) {
				if (i > 0)
					sb.append(", ");
				sb.append(l.get(i).getCode());
			}
		}
		return sb.toString();
	
	}
	
	public static void startWizard (String title, String className,
			Component invoker, 
			boolean multi,
			String filter,
			EventListener listener) throws Exception {
		Page p = invoker.getDesktop().getPageIfAny("finderWizard");
		if ( p == null) {
			Include i = new Include("/popup/finder.zul");
			i.setDynamicProperty("listener", listener);
			i.setDynamicProperty("title", title);
			i.setDynamicProperty("multi", multi);
			i.setDynamicProperty("filter", filter);
			i.setDynamicProperty("defaultColumns", defaultColumns(className));
			i.setDynamicProperty("className", className);
			i.setDynamicProperty("preferenceName", className);
			i.setDynamicProperty("visible", true);
			i.setPage(invoker.getPage());
		} else {
			FinderHandler h = (FinderHandler) p.getFellow("window");
			
			h.setTitle(title);

			boolean sameFilter = filter == null && h.filter == null ||
					filter != null && filter.equals(h.filter);
			if (!h.className.equals(className) || ! sameFilter) {
				SearchBox searchBox = (SearchBox) h.getFellow("searchBox");
				searchBox.setDefaultAttributes( defaultColumns(className));
				searchBox.setPreference(className+"-query");
				searchBox.setEnforcedFilter(filter);
				searchBox.setJsonObject(className);
				searchBox.reload();
				
				FinderDatatable table = (FinderDatatable) h.getFellow("listbox");
				table.setClassName(className);
				table.setPreference(className+"-data");
				table.setMultiselect(multi);
				table.invalidate();
				
				DataModel model = (DataModel) h.getFellow("model");
				model.getVariables().declareVariable("className", null);
				model.getVariables().declareVariable("query", null);
				model.getVariables().declareVariable("textQuery", null);
				model.refresh();
				model.getVariables().declareVariable("className", className);
			}
			

			Button finishButton = (Button) h.getFellow("finishButton");
			finishButton.setVisible(multi);
			
			h.setTitle(title);
			h.filter = filter;
			h.listener = listener;
			h.multi = multi;
			h.className = className;
			h.doHighlighted();
		}
	}
	
	public void changeColumns(Event event) throws IOException {
		SelectColumnsHandler.startWizard(getListbox());
	}


	private DynamicColumnsDatatable getListbox() {
		return (DynamicColumnsDatatable) getFellow("listbox");
	}

	public void onSelect(Event ev) {
		if (!multi)
		{
			DynamicColumnsDatatable lb = getListbox();
			String path = lb.getSelectedItemXPath();
			if (path != null) {
				String name = getNameForPath(lb, path);
				if (name != null) {
					try {
						listener.onEvent(new Event("onSelect", lb, name));
					} catch (Exception e) {
						throw new UiException(e);
					}
					setVisible(false);
				} else {
					Missatgebox.avis("Internal error: Cannot get object name");
				}
			}
		}
	}


	public String getNameForPath(DynamicColumnsDatatable lb, String path) {
		JXPathContext ds = lb.getDataSource().getJXPathContext();
		path = lb.getXPath()+path;
		String name = null;
		if (className.equals("com.soffid.iam.api.Role")) 
		{
			name = (String) ds.getValue(path+"/name");
			String system = (String) ds.getValue(path+"/system");
			name = name +"@"+system;
		} else if (className.equals("com.soffid.iam.api.MailList")) 
		{
			name = (String) ds.getValue(path+"/name");
			String domain = (String) ds.getValue(path+"/domainName");
			name = name +"@"+domain;
		} else if (className.equals("com.soffid.iam.api.DomainValue")) 
		{
			Object o = ds.getValue(path);
			return (String) ds.getValue(path+"/value");
		} else if (className.equals("com.soffid.iam.api.VaultFolder")) 
		{
			Long id = (Long) ds.getValue(path+"/id");
			return id.toString();
		} else {
			try {
				name = (String) ds.getValue(path+"/userName");
			} catch (Exception e) {}
			if (name == null) {
				try {
					name = (String) ds.getValue(path+"/name");
				} catch (Exception e) {}
			}
			if (name == null) {
				try {
					name = (String) ds.getValue(path+"/code");
				} catch (Exception e) {}
			}
		}
		return name;
	}

	public void accept(Event ev) throws Exception {
		if (!multi)
			onSelect(ev);
		else
		{
			DynamicColumnsDatatable lb = getListbox();
			int[] items = lb.getSelectedIndexes();
			if (items != null && items.length > 0) {
				List<String> values = new LinkedList<>();
				JXPathContext ds = lb.getDataSource().getJXPathContext();
				for (int item: items) {
					String path = ((ModelProxy) lb.getModel()).getBind(item);
					String name = getNameForPath(lb, path);
					if (name != null) {
						try {
							values.add(name);
						} catch (Exception e) {
							throw new UiException(e);
						}
						setVisible(false);
					} else {
						Missatgebox.avis("Internal error: Cannot get object name");
					}
				}
				listener.onEvent(new Event("onSelect", lb, values));
			}
		}
	}
}

