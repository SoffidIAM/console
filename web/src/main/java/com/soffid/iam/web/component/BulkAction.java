package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Progressmeter;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.MetadataScope;
import com.soffid.iam.common.TransactionalTask;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.popup.BulkActionAttribute;
import com.soffid.iam.web.popup.BulkActionAttributeAction;
import com.soffid.iam.web.popup.BulkActionHandler;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.ng.exception.SoffidStackTrace;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.jxpath.JXPathException;

public class BulkAction {
	private String objectClass;
	int count = 0;
	int total = 0;
	private DataTable dataTable;
	private SearchBox searchBox;
	boolean cancel = false;
	
	public BulkAction() {
		
	}
	
	public BulkAction(String objectClass) {
		this.objectClass = objectClass;
	}
	
	public Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException {
		return EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(objectClass, null);
	}

	public void start (DataTable dt) throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		this.dataTable = dt;
		dt.getDataSource().commit();
		List<String[]> def = new LinkedList<>();
		List<BulkActionAttribute> atts = new LinkedList<>();
		for ( DataType data: getMetadata())
		{
			if (data.getType() != TypeEnumeration.SEPARATOR && !data.isReadOnly()) {
				WebDataType wdt = new WebDataType(data);
				def.add(new String[] { wdt.getName(), wdt.getLabel()});
				BulkActionAttributeAction[] actions;
				if (data.isMultiValued()) {
					actions = new BulkActionAttributeAction[] {
							new BulkActionAttributeAction ("addValue", wdt.getType()),
							new BulkActionAttributeAction ("removeValue", wdt.getType()),
							new BulkActionAttributeAction ("clearValue", null),
					};
				}
				else if (data.isRequired() ) {
					actions = new BulkActionAttributeAction[] {
							new BulkActionAttributeAction ("setValue", wdt.getType()),
					};
				} else {
					actions = new BulkActionAttributeAction[] {
							new BulkActionAttributeAction ("setValue", wdt.getType()),
							new BulkActionAttributeAction ("clearValue", null),
					};
				}
				BulkActionAttribute att = new BulkActionAttribute(wdt.getName(), wdt, actions);
				atts.add(att);
			}
		}
		
		Collections.sort(atts, new Comparator<BulkActionAttribute>() {
			@Override
			public int compare(BulkActionAttribute o1, BulkActionAttribute o2) {
				return o1.getDataType().getLabel().compareTo(o2.getDataType().getLabel());
			}
		});
		BulkActionHandler.startWizard(atts.toArray(new BulkActionAttribute[atts.size()]), dt, this); 
	}

	public void apply (List<BulkActionAttribute> attributes, List<BulkActionAttributeAction> actions, List<Object> values, boolean singleTransaction, 
			final Desktop desktop, final Progressmeter progressmeter) 
		throws Exception {
		total = dataTable.getSelectedIndexes().length;
		count = 0;
		cancel = false;
		final StringBuffer errors = new StringBuffer();
		EJBLocator.getAsyncRunnerService().runTransaction(new TransactionalTask() {
			@Override
			public Object run() throws Exception {
				Executions.activate(desktop);
				long last = System.currentTimeMillis();
				try {
					for (int position: dataTable.getSelectedIndexes()) {
						try {
							if (cancel) throw new InternalErrorException(Labels.getLabel("bulk.cancelled"));
							if (System.currentTimeMillis() - last > 500) {
								Executions.deactivate(desktop);
								Executions.activate(desktop);
								last = System.currentTimeMillis();
							}
							apply (position, attributes, actions, values);
							dataTable.commit();
							count ++;
							progressmeter.setValue(getProgress());
						} catch (Exception e) {
							String msg = "Error updating object "+fetchObjectName(position)+": "+SoffidStackTrace.generateShortDescription(e);
							if (searchBox != null) {
								searchBox.search(true);
							} 
							else
								dataTable.refresh();
							throw new InternalErrorException(msg);
						}
					}
				} finally {
					Executions.deactivate(desktop);
				}
				return null;
			}
		});
	}
	
	public String fetchObjectName(int position) {
		String xpath = dataTable.getItemXPath(position);
		DataSource ds = dataTable.getDataSource();
		JXPathContext ctx = ds.getJXPathContext();
		String name =  null;
		try {
			name = (String) ctx.getValue(xpath+"/@name");
		} catch (JXPathException e) {}
		if (name == null) {
			try {
				name = (String) ctx.getValue(xpath+"/@userName");			
			} catch (JXPathException e) {}
		}
		if (name == null) {
			try {
				name = (String) ctx.getValue(xpath+"/@code");			
			} catch (JXPathException e) {}
		}
		if (name == null) {
			try {
				name = (String) ctx.getValue(xpath+"/@description");			
			} catch (JXPathException e) {}
		}
		if (name == null) {
			try {
				Long id = (Long) ctx.getValue(xpath+"/@id");
			if (id != null) name = id.toString();
			} catch (JXPathException e) {}
		}
		if (name == null) {
			try {
				Object obj = ctx.getValue(xpath+"/.").toString();
				if (obj != null)
					name = obj.toString();
			} catch (JXPathException e) {}
		}
		return name;
	}

	public void apply (int position, List<BulkActionAttribute> attributes, List<BulkActionAttributeAction> actions, List<Object> values) {
		String xpath = dataTable.getItemXPath(position);
		DataSource ds = dataTable.getDataSource();
		JXPathContext ctx = ds.getJXPathContext();
		for ( int i = 0; i < attributes.size(); i++) {
			BulkActionAttribute attribute = attributes.get(i);
			BulkActionAttributeAction action = actions.get(i);
			Object value = values.get(i);
			apply (ctx, xpath, attribute, action, value);
		}
		dataTable.updateClientRow(position);
	}

	public void apply(JXPathContext ctx, String xpath, BulkActionAttribute attribute, BulkActionAttributeAction action, Object value) {
		String path;
		if (Boolean.TRUE.equals( attribute.getDataType().getBuiltin())) 
			path = attribute.getDataType().getName();
		else
			path = "/attributes[@name='"+attribute.getDataType().getName()+"']";
		path = XPathUtils.concat(xpath, path);
		
		if (action.getName().equals("setValue"))
			ctx.setValue(path, value);
		else if (action.getName().equals("clearValue"))
			ctx.setValue(path, null);
		else if (action.getName().equals("addValue")) {
			Collection coll = (Collection) ctx.getValue(path);
			if (coll == null) coll = new LinkedList<>();
			coll.addAll((Collection)value);
		}
		else if (action.getName().equals("removeValue")) {
			Collection coll = (Collection) ctx.getValue(path);
			if (coll != null) coll.removeAll((Collection)value);
		}
	}

	public int getProgress () {
		return total == 0 ? 100 : count * 100 / total;
	}

	public void resetCounter() {
		total = dataTable.getSelectedIndexes().length;
		count = 0;
	}

	public void setSearchBox(SearchBox searchBox) {
		this.searchBox = searchBox;
	}

	public void cancel() {
		cancel = true;
	}
}
