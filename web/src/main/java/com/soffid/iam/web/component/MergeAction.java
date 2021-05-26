package com.soffid.iam.web.component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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

import org.zkoss.zk.ui.WrongValueException;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.web.popup.MergeActionHandler;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.jxpath.JXPathContext;
import es.caib.zkib.jxpath.JXPathException;

public abstract class MergeAction {
	private String objectClass;
	protected DataTable dataTable;
	boolean cancel = false;
	protected int[] positions;
	
	public MergeAction(String objectClass) {
		this.objectClass = objectClass;
	}
	
	public Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException {
		return EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(objectClass, null);
	}

	public void start (DataTable dt) throws IOException, CommitException, InternalErrorException, NamingException, CreateException, WrongValueException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		this.dataTable = dt;
		dt.getDataSource().commit();
		
		positions = dt.getSelectedIndexes();

		LinkedList<DataType> data = new LinkedList<DataType>(getMetadata());
		Collections.sort(data, new Comparator<DataType>() {
			@Override
			public int compare(DataType o1, DataType o2) {
				return o1.getOrder().compareTo(o2.getOrder());
			}
		});

		MergeActionHandler.startWizard(dt, fetchObject(positions[0]), fetchObject(positions[1]),
				fetchObjectName(positions[0]), fetchObjectName(positions[1]),
				data,
				this); 
	}

	private Object fetchObject(int position) {
		String xpath = dataTable.getItemXPath(position);
		DataSource ds = dataTable.getDataSource();
		JXPathContext ctx = ds.getJXPathContext();
		return ctx.getValue(xpath+"/instance");
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

	public abstract void apply(Map<String,String> actions) throws Exception ;

	public void apply(Map<String, String> actions, int position) throws Exception {
		String xpath1 = dataTable.getItemXPath(positions[0]);
		String xpath2 = dataTable.getItemXPath(positions[1]);
		DataSource ds = dataTable.getDataSource();
		JXPathContext ctx = ds.getJXPathContext();
		String target = actions.get("userName");
		String targetPath = position == positions[0] ? xpath1: xpath2;
		for ( DataType dt: getMetadata()) {
			String action = actions.get(dt.getName());
			if (action != null) {
				String attPath = "/"+ ( Boolean.TRUE.equals(dt.getBuiltin()) ? dt.getName(): "attributes[@name='"+dt.getName()+"']" );
				if (action.equals("1") && target.equals("2")) 
					ctx.setValue(targetPath+attPath, ctx.getValue(xpath1+attPath));
				if (action.equals("2") && target.equals("1")) 
					ctx.setValue(targetPath+attPath, ctx.getValue(xpath2+attPath));
				if (action.equals("3")) {
					Set<Object> o = new HashSet<>();
					List<Object> l1 = (List<Object>) ctx.getValue(xpath1+attPath);
					if (l1 != null) o.addAll(l1);
					List<Object> l2 = (List<Object>) ctx.getValue(xpath2+attPath);
					if (l2 != null) o.addAll(l2);
					ctx.setValue(targetPath+attPath, new LinkedList<Object>(o));
				}
			}
		}
	}
}
