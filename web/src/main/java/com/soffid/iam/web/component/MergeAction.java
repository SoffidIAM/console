package com.soffid.iam.web.component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.Issue;
import com.soffid.iam.web.popup.MergeActionHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.jxpath.JXPathException;

public abstract class MergeAction {
	private String objectClass;
	protected DataTable dataTable;
	protected Component window;
	boolean cancel = false;
	private DataNodeCollection collection;
	protected DataSource dataSource;
	protected List<String> xPaths;
	protected Issue currentIssue;
	protected LinkedList<String> names;
	protected EventListener onApply;
	
	public MergeAction(String objectClass) {
		this.objectClass = objectClass;
	}
	
	public Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException {
		return EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(objectClass, null);
	}

	public void start (DataTable dt, boolean newIssue, Issue currentIssue) throws IOException, CommitException, InternalErrorException, NamingException, CreateException, WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		this.dataTable = dt;
		dataSource = dt.getDataSource();
		
		int[] p0 = dt.getSelectedIndexes();
		int[] p1 = new int[p0.length];
		
		List<String> xpaths = new LinkedList<>();
		for (int i = 0; i < p0.length; i++) {
			xpaths.add( dt.getItemXPath(p0[i]) );
		}
		
		start (dt, dataSource, xpaths, newIssue, currentIssue);
		
	}


	public void start(Component w, DataSource src, String xpath, int[] positions,
			boolean newIssue, Issue currentIssue) throws WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, NamingException, CreateException, InternalErrorException, CommitException 
	{
		List<String> xpaths = new LinkedList<>();
		for (int i = 0; i < positions.length; i++) {
			xpaths.add( xpath+"["+Integer.toString(positions[i])+"]" );
		}
		
		start (w, src, xpaths, newIssue, currentIssue);
	}
	
	public void start(Component w, DataSource src, List<String> xpaths, boolean newIssue, Issue currentIssue) throws WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, NamingException, CreateException, InternalErrorException, CommitException {
		src.commit();
		this.window = w;
		this.dataSource = src;
		this.xPaths = xpaths;
		this.currentIssue = currentIssue;
		
		LinkedList<DataType> data = new LinkedList<DataType>(getMetadata());
		Collections.sort(data, new Comparator<DataType>() {
			@Override
			public int compare(DataType o1, DataType o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});
		
		names = new LinkedList<>();
		LinkedList<Object> l2 = new LinkedList<>();
		for ( int i = 0; i < xpaths.size(); i++) {
			l2.add(fetchObject(i));
			names.add(fetchObjectName(i));
		}

		MergeActionHandler.startWizard(w, xpaths, l2, names, data, this, newIssue, currentIssue); 
	}

	private Object fetchObject(int position) {
		return XPathUtils.eval(dataSource, xPaths.get(position)+"/instance");
	}

	public String fetchObjectName(int position) {
		String name =  null;
		String xpath = xPaths.get(position);
		try {
			name = (String) XPathUtils.eval(dataSource, xpath+"/@name");
		} catch (JXPathException e) {}
		if (name == null) {
			try {
				name = (String) XPathUtils.eval(dataSource, xpath+"/@userName");			
			} catch (JXPathException e) {}
		}
		if (name == null) {
			try {
				name = (String) XPathUtils.eval(dataSource, xpath+"/@code");			
			} catch (JXPathException e) {}
		}
		if (name == null) {
			try {
				name = (String) XPathUtils.eval(dataSource, xpath+"/@description");			
			} catch (JXPathException e) {}
		}
		if (name == null) {
			try {
				Long id = (Long) XPathUtils.eval(dataSource, xpath+"/@id");
			if (id != null) name = id.toString();
			} catch (JXPathException e) {}
		}
		if (name == null) {
			try {
				Object obj = XPathUtils.eval(dataSource, xpath+"/.").toString();
				if (obj != null)
					name = obj.toString();
			} catch (JXPathException e) {}
		}
		return name;
	}

	public abstract void apply(Map<String, int[]> actions, int srcPosition[]) throws Exception;

	public void apply(Map<String, int[]> actions, int srcPosition[], int targetPosition) throws Exception {
		String targetPath = xPaths.get(targetPosition);
		for ( DataType dt: getMetadata()) {
			String attPath = "/"+ ( Boolean.TRUE.equals(dt.getBuiltin()) ? dt.getName(): "attributes[@name='"+dt.getName()+"']" );
			int[] src = actions.get(dt.getName());
			if (src != null) {
				if (dt.isMultiValued()) {
					LinkedList<Object> values = new LinkedList<>();
					for (int srcid: src) {
						Object v = XPathUtils.eval(dataSource, xPaths.get(srcid)+attPath);
						if (v != null) {
							values.addAll((Collection) v);
						}
					}
					XPathUtils.setValue(dataSource, targetPath+attPath, values);
				} else {
					for (int srcid: src) {
						Object v = XPathUtils.eval(dataSource, xPaths.get(srcid)+attPath);
						XPathUtils.setValue(dataSource, targetPath+attPath, v);
					}
				}
			}
		}
	}


	public void setOnApply(EventListener onApply) {
		this.onApply = onApply;
	}

	
	public EventListener getOnApply() {
		return onApply;
	}
}
