package com.soffid.iam.web.component;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.apache.commons.beanutils.PropertyUtils;
import org.zkoss.mesg.Messages;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zul.mesg.MZul;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.Configuration;
import com.soffid.iam.api.CrudHandler;
import com.soffid.iam.api.CustomObject;
import com.soffid.iam.api.DataType;
import com.soffid.iam.service.ejb.CustomObjectService;
import com.soffid.iam.web.WebDataType;
import com.soffid.iam.web.application.ApplicationRoleHandler;
import com.soffid.iam.web.popup.CsvParser;
import com.soffid.iam.web.popup.ImportCsvHandler;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.seycon.util.Base64;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.zkiblaf.Missatgebox;

public abstract class CsvImporter<E> {
	protected abstract Collection<DataType> getMetadata() throws InternalErrorException, NamingException, CreateException ;
	protected abstract E newObject() throws InternalErrorException, NamingException, CreateException ;
	protected abstract CrudHandler<E> getCrudHandler() throws InternalErrorException, NamingException, CreateException ;
	protected abstract E load(E object) throws InternalErrorException;

	private DataModel model;
	private DynamicColumnsDatatable dataTable;

	public void importCsv (FrameHandler frame) throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		model = frame.getModel();
		model.commit();
		List<String[]> def = new LinkedList<>();
		for ( DataType data: getMetadata())
		{
			WebDataType wdt = new WebDataType(data);
			def.add(new String[] { wdt.getName(), wdt.getLabel()});
		}
		
		String title = Labels.getLabel("tenant.zul.import");
		ImportCsvHandler.startWizard(title, def.toArray(new String[def.size()][]), frame, 
				parser -> importCsv(parser));
	}

	public void importCsv (ApplicationRoleHandler frame) throws IOException, CommitException, InternalErrorException, NamingException, CreateException {
		dataTable = frame.getListbox();
		dataTable.commit();
		model = null;
		List<String[]> def = new LinkedList<>();
		for ( DataType data: getMetadata())
		{
			WebDataType wdt = new WebDataType(data);
			def.add(new String[] { wdt.getName(), wdt.getLabel()});
		}
		
		String title = Labels.getLabel("tenant.zul.import");
		ImportCsvHandler.startWizard(title, def.toArray(new String[def.size()][]), frame, 
				parser -> importCsv(parser));
	}
	
	private void importCsv(CsvParser parser) {
		Map<String,String> m = null;
		int updates = 0;
		int inserts = 0;
		int unchanged = 0;
		int removed = 0;
		try {
			Collection<DataType> metadata = getMetadata();
			CrudHandler<E> handler = getCrudHandler();
			CustomObjectService svc = EJBLocator.getCustomObjectService();
			for ( Iterator<Map<String, String>> iterator = parser.iterator(); iterator.hasNext(); )
			{
				m = iterator.next();
				E object = newObject(); 
				
				populate(object, metadata, m);

				E existing = load(object);
				if (existing != null)
				{
					if (m.keySet().size() == 1) {
						handler.delete(existing);
						removed ++;
					}
					else 
					{
						boolean anyChange = populate (existing, metadata, m);
						if (anyChange) {
							handler.update(existing);
							updates ++;
						} else {
							unchanged ++;
						}
					}
				} else {
					handler.create(object);
					inserts++;
				}
			}
		} catch (UiException e) {
			throw e;
		} catch (Exception e) {
			if (m == null)
				throw new UiException(e);
			else
				throw new UiException("Error loading object "+m, e);
		}
		
		if (model != null)
			model.refresh();
		if (dataTable != null) {
			try {
				dataTable.refresh();
			} catch (Exception e) {
			}
		}
		Missatgebox.avis(Labels.getLabel("parametres.zul.import", new Object[] { updates, inserts, removed, unchanged }));
	}


	protected boolean populate(E object, Collection<DataType> metadata, Map<String, String> m) throws InternalErrorException, NamingException, CreateException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		boolean anyChange = false;
		for (DataType dt: metadata) {
			String t = m.get(dt.getName());
			if (t != null) {
				Object v = parse (t, dt);
				if (Boolean.TRUE.equals(dt.getBuiltin())) {
					Object vv = PropertyUtils.getProperty(object, dt.getName());
					if (! v.equals(vv)) {
						PropertyUtils.setProperty(object, dt.getName(), v);
						anyChange = true;
					}
				} else {
					Map<String,Object> attributes = (Map<String, Object>) PropertyUtils.getProperty(object, "attributes");
					if (attributes == null)
					{
						attributes = new HashMap<>();
						PropertyUtils.setProperty(object, "attributes", attributes);
					}
					Object vv = attributes.get(dt.getName());
					if ( ! v.equals(vv)) {
						attributes.put(dt.getName(), v);
						anyChange = true;
					}
				}
			}
		}
		return anyChange;
	}

	private Object parse(String v, DataType dt) {
		if (dt.getType() == TypeEnumeration.BOOLEAN_TYPE)
			return Messages.get(MZul.YES).equals(v) ||
					"true".equals(v);
		if (dt.getType() == TypeEnumeration.DATE_TYPE)
			try {
				return DateFormats.getDateFormat().parse(v);
			} catch (ParseException e) {
				throw new UiException("Error parsing date "+v);
			}
		if (dt.getType() == TypeEnumeration.DATE_TIME_TYPE)
			try {
				return DateFormats.getDateTimeFormat().parse(v);
			} catch (ParseException e) {
				throw new UiException("Error parsing date and time "+v);
			}
		if (dt.getType() == TypeEnumeration.BINARY_TYPE)
			return Base64.decode(v);
		if (dt.getType() == TypeEnumeration.NUMBER_TYPE)
			return Long.parseLong(v);
		return v;

	}

}
