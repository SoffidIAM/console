package com.soffid.iam.web.component;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.naming.NamingException;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.ComponentCommand;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.soffid.iam.web.SearchAttributeDefinition;
import com.soffid.iam.web.SearchDictionary;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.zkiblaf.Esquema;
import es.caib.zkib.zkiblaf.EsquemaVertical;
import es.caib.zkib.zkiblaf.Missatgebox;

public class SearchBox extends HtmlBasedComponent {
	public static final String CHANGE_MODE_EVENT = "onChangeMode";
	public static final String SEARCH_EVENT = "onSearch";
	SearchDictionary dictionary;
	String jsonObject;
	private Button addAttributeButton;
	private Textbox advancedSearch;
	private Menupopup menu;
	boolean advancedMode = false;
	String variableName;
	String dataPath;
	private String defaultAttributes;
	boolean auto=false;
	private String lastQuery = "";
	
	public String getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonObject) {
		this.jsonObject = jsonObject;
	}

	public String getDataPath() {
		return dataPath;
	}

	public void setDataPath(String dataPath) {
		this.dataPath = dataPath;
	}

	public String getVariableName() {
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public SearchBox() {
		setSclass("search-box");
		addEventListener(CHANGE_MODE_EVENT, new EventListener() {
			public void onEvent(Event event) throws Exception {
				changeMode();
			}

		});
		addEventListener(SEARCH_EVENT, new EventListener() {
			public void onEvent(Event event) throws Exception {
				search();
			}
		});
	}
	
	public void search() {
		if (dataPath != null)
		{
			SingletonBinder binder = new SingletonBinder(this);
			binder.setDataPath(dataPath);
			DataSource ds = binder.getDataSource();
			if (ds.isCommitPending()) {
				Missatgebox.avis(org.zkoss.util.resource.Labels
							.getLabel("usuaris.Confirmar"),
						org.zkoss.util.resource.Labels
							.getLabel("usuaris.CanvisPendents"));
				return;
			}
			
			lastQuery  = getQueryString();

			ds.getJXPathContext().getVariables().declareVariable(variableName, lastQuery);
			Object v = binder.getValue();
			if (v instanceof DataModelCollection)
			{
				try {
					((DataModelCollection) v).refresh();
				} catch (Exception e) {
					throw new UiException(e);
				}
				showForm(((DataModelCollection) v).getSize() == 1);
					
			}
			binder.setDataPath(null);
		}
	}


	private void showForm(boolean show) {
		Component parent = this;
		for (int i = 0; parent != null && i < 4; i++)
			parent = parent.getParent();
		if (parent != null)
		{
			if (parent instanceof Esquema)
			{
				if (show) ((Esquema) parent).showFormulari();
				else ((Esquema) parent).hideFormulari();
			}
			if (parent instanceof EsquemaVertical)
			{
				if (show) ((EsquemaVertical) parent).showFormulari();
				else ((EsquemaVertical) parent).hideFormulari();
			}
		}
	}

	private void changeMode() {
		advancedMode = ! advancedMode;
		if (advancedMode)
		{
			StringBuffer sb = new StringBuffer();
			for (AttributeSearchBox asb: getAttributeSearchBoxes())
			{
				String f = asb.getQueryExpression();
				if (f != null && !f.isEmpty())
				{
					if (sb.length() > 0)
						sb.append(" AND ");
					sb.append(f);
				}
			}
			advancedSearch.setValue(sb.toString());
		}
		setChildrenVisibility();
		invalidate();
	}

	private void setChildrenVisibility ()
	{
		for (Component c: getAttributeSearchBoxes())
		{
			c.setVisible(!advancedMode);
		}
		addAttributeButton.setVisible(!advancedMode);
		advancedSearch.setVisible(advancedMode);
	}
	
	public void onCreate () throws ClassNotFoundException, InternalErrorException, NamingException, CreateException
	{
		if (jsonObject != null && dictionary == null)
			dictionary = SearchDictionaryBuilder.build(jsonObject);
		
		for (String att: defaultAttributes.split("[ ,]+"))
		{
			addAttribute(att);
		}

		advancedSearch = new Textbox();
		advancedSearch.setMultiline(true);
		advancedSearch.setRows(2);
		appendChild(advancedSearch);

		addAttributeButton = new Button(Labels.getLabel("searchBox.addAttribute"));
		addAttributeButton.addEventListener("onClick", new EventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				selectAttributeToAdd();
			}
		});
		addAttributeButton.setClass("searchbox-addcriteria");
		appendChild(addAttributeButton);
		menu = new Menupopup();
		appendChild(menu);
		setChildrenVisibility();

		// Button to add attributes. Only we show it if there are attributes to select
		if (!areAttributesToAdd())
			addAttributeButton.setVisible(false);

		invalidate();
	}

	public void onChangeFilter () 
	{
		String newQuery = getQueryString();
		if (auto && !lastQuery.equals(newQuery))
			search();
	}
	
	public boolean isAdvancedMode() {
		return advancedMode;
	}

	public void setAdvancedMode(boolean advancedMode) {
		this.advancedMode = advancedMode;
	}

	public String getChangeModeLabel ()
	{
		if (advancedMode)
			return Labels.getLabel("searchBox.basicMode");
		else
			return Labels.getLabel("searchBox.advancedMode");
	}
	
	public void setDefaultAttributes (String s)
	{
		this.defaultAttributes = s;
	}

	public AttributeSearchBox addAttribute(String att) {
		for (SearchAttributeDefinition def: dictionary.getAttributes())
		{
			if (def.getName().equals(att))
				return addAttribute (def);
		}
		return null;
	}

	public SearchDictionary getDictionary() {
		return dictionary;
	}

	public void setDictionary(SearchDictionary dictionary) {
		this.dictionary = dictionary;
	}

	public AttributeSearchBox getAttributeSearchBox (String tag, boolean create)
	{
		for (AttributeSearchBox asb: getAttributeSearchBoxes())
		{
			if (asb.getAttributeDef() != null &&
					asb.getAttributeDef().getName().equals(tag))
				return asb;
		}
		return addAttribute(tag);
	}
	
	public AttributeSearchBox getAttributeSearchBox (String tag)
	{
		return getAttributeSearchBox(tag, true);
	}

	protected AttributeSearchBox addAttribute(SearchAttributeDefinition def) {
		for (AttributeSearchBox asb: getAttributeSearchBoxes())
		{
			if (asb.getAttributeDef() == def)
				return asb;
		}

		AttributeSearchBox asb = new AttributeSearchBox();
		asb.setAttributeDef(def);
		insertBefore(asb, addAttributeButton);

		if (dictionary != null && addAttributeButton != null)
		{
			boolean anyMissing = false;
			for (final SearchAttributeDefinition def2: dictionary.getAttributes())
			{
				boolean found = false;
				for ( Component component: (List<Component>)getChildren())
				{
					if (component instanceof AttributeSearchBox)
					{
						AttributeSearchBox asb2 = (AttributeSearchBox) component;
						if (asb2.attributeDef == def2)
						{
							found = true;
							break;
						}
					}
				}
				if (! found)
				{
					anyMissing = true;
					break;
				}
			}
			if (!anyMissing)
				addAttributeButton.setVisible(false);
		}
		invalidate();
		return asb;
	}

	
	public void onRemoveFilter (AttributeSearchBox asb)
	{
		addAttributeButton.setVisible(true);
		onChangeFilter();
	}

	public String getQueryString ()
	{
		if (advancedMode)
			return advancedSearch.getText();
		else
		{
			StringBuffer qs = new StringBuffer();
			for (Object child: getChildren())
			{
				if (child instanceof AttributeSearchBox)
				{
					String q = ((AttributeSearchBox) child).getQueryExpression();
					if ( q != null && ! q.isEmpty())
					{
						if (qs.length() > 0)
							qs.append(" and ");
						qs.append("(")
							.append(q)
							.append(")");
					}
				}
			}
			return qs.toString();
		}
	}


	private void selectAttributeToAdd() {
		menu.getChildren().clear();
		LinkedList<Menuitem> items = new LinkedList<Menuitem>();
		for (final SearchAttributeDefinition def: dictionary.getAttributes())
		{
			boolean found = false;
			for ( Component component: (List<Component>)getChildren())
			{
				if (component instanceof AttributeSearchBox)
				{
					AttributeSearchBox asb = (AttributeSearchBox) component;
					if (asb.attributeDef == def)
					{
						found = true;
						break;
					}
				}
			}
			if (! found)
			{
				Menuitem item = new Menuitem(def.getLocalizedName() != null ?
						def.getLocalizedName() :
						Labels.getLabel(def.getLabelName()));
				item.setValue(def.getName());
				item.setAutocheck(true);
				item.addEventListener("onClick", new EventListener() {
					@Override
					public void onEvent(Event event) throws Exception {
						AttributeSearchBox att = addAttribute(def.getName());
						if (att != null)
							att.onClick();
					}
				});
				items.add(item);
			}
		}
		Collections.sort( items, new Comparator<Menuitem>()
				{
					public int compare(Menuitem o1, Menuitem o2) {
						return o1.getLabel().compareTo(o2.getLabel());
					}
			
				});
		menu.getChildren().addAll(items);
		appendChild(menu);
		menu.open(addAttributeButton);
	}

	private boolean areAttributesToAdd() {
		for (final SearchAttributeDefinition def : dictionary.getAttributes()) {
			boolean found = false;
			for (Component component : (List<Component>) getChildren()) {
				if (component instanceof AttributeSearchBox) {
					AttributeSearchBox asb = (AttributeSearchBox) component;
					if (asb.attributeDef == def) {
						found = true;
						break;
					}
				}
			}
			if (!found) return true;
		}
		return false;
	}

	public List<AttributeSearchBox> getAttributeSearchBoxes ()
	{
		List<AttributeSearchBox> l = new LinkedList<AttributeSearchBox>();
		for (Component c:(List<Component>)getChildren())
		{
			if (c instanceof AttributeSearchBox )
				l.add((AttributeSearchBox) c);
		}
		return l;
	}

	public List<Component> getAttributeSearchBoxesAndPopups ()
	{
		List<Component> l = new LinkedList<Component>();
		for (Component c:(List<Component>)getChildren())
		{
			if (c instanceof AttributeSearchBox ||
					c instanceof Window)
				l.add((Component) c);
		}
		return l;
	}

	public List<Component> getOtherChildren ()
	{
		List<Component> l = new LinkedList<Component>();
		for (Component c:(List<Component>)getChildren())
		{
			if (c != addAttributeButton && 
					c != advancedSearch &&
					!(c instanceof AttributeSearchBox) &&
					!(c instanceof Window))
				l.add(c);
		}
		return l;
	}


	public Textbox getAdvancedSearch() {
		return advancedSearch;
	}

	public void setAdvancedSearch(Textbox advancedSearch) {
		this.advancedSearch = advancedSearch;
	}

	private static Command _onChangeMode  = new ComponentCommand (CHANGE_MODE_EVENT, 0) {
		protected void process(AuRequest request) {
			try {
				Events.postEvent(new Event (CHANGE_MODE_EVENT, request.getComponent(),"")); //$NON-NLS-1$
			} catch (Exception e) {
				throw new UiException(e);
			}
		}
		
	};
	
	private static Command _onSearch  = new ComponentCommand (SEARCH_EVENT, 0) {
		protected void process(AuRequest request) {
			try {
				Events.postEvent(new Event (SEARCH_EVENT, request.getComponent(),"")); //$NON-NLS-1$
			} catch (Exception e) {
				throw new UiException(e);
			}
		}
		
	};
	
	public Command getCommand(String cmdId) {
		if (CHANGE_MODE_EVENT.equals(cmdId))
			return _onChangeMode;
		
		if (SEARCH_EVENT.equals(cmdId))
			return _onSearch;

		return super.getCommand(cmdId);
	}

	public Button getAddAttributeButton() {
		return addAttributeButton;
	}

	public void setAddAttributeButton(Button addAttributeButton) {
		this.addAttributeButton = addAttributeButton;
	}

	public Menupopup getMenu() {
		return menu;
	}

	public void setMenu(Menupopup menu) {
		this.menu = menu;
	}

	public String getSearchIconUrl()
	{
		return Executions.encodeURL("~./img/search.png");
	}

	public boolean isAuto() {
		return auto;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}
}
