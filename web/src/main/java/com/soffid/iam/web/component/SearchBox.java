package com.soffid.iam.web.component;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.CreateException;
import javax.json.JsonArray;
import javax.naming.NamingException;

import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.ComponentCommand;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.ext.AfterCompose;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Menuitem;
import org.zkoss.zul.Menupopup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Timer;
import org.zkoss.zul.Tree;
import org.zkoss.zul.Treeitem;
import org.zkoss.zul.Window;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.DataType;
import com.soffid.iam.api.User;
import com.soffid.iam.api.exception.ListFullException;
import com.soffid.iam.utils.ConfigurationCache;
import com.soffid.iam.web.SearchAttributeDefinition;
import com.soffid.iam.web.SearchDictionary;
import com.soffid.scimquery.parser.ParseException;

import es.caib.seycon.ng.comu.Usuari;
import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.component.DataTextbox;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.RowsLimitExceededException;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.zkiblaf.Esquema;
import es.caib.zkib.zkiblaf.EsquemaVertical;
import es.caib.zkib.zkiblaf.Missatgebox;

public class SearchBox extends HtmlBasedComponent implements AfterCompose {
	public static final String CHANGE_MODE_EVENT = "onChangeMode";
	public static final String SEARCH_EVENT = "onSearch";
	SearchDictionary dictionary;
	String jsonObject;
	private Button addAttributeButton;
	private Textbox advancedSearch;
	private Textbox textSearchBox;
	private Menupopup menu;
	String variableName;
	String variableNameText;
	String dataPath;
	String enforcedFilter;
	private String defaultAttributes;
	boolean auto=false;
	private String lastQuery = "";
	private boolean initialized = false;
	private Timer timer;
	private Image progressImage;
	private DataModelCollection modelCollection;
	private boolean textSearch = false;
	private String preference = null;
	static int TEXT = 0;
	static int BASIC = 1;
	static int ADVANCED = 2;
	int mode = BASIC;
	boolean enableQuick = true;
	boolean enableBasic = true;
	boolean enableAdvanced = true;
	
	public String getJsonObject() {
		return jsonObject;
	}

	public void setJsonObject(String jsonObject) throws ClassNotFoundException, InternalErrorException, NamingException, CreateException {
		this.jsonObject = jsonObject;
		if (initialized)
		{
			dictionary = null;
			initialize();
		}
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
		addEventListener(CHANGE_MODE_EVENT, new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				String i = (String)event.getData();
				if (i == null || i.equals("1"))
					setBasicMode();
				else if (i.equals("2"))
					setAdvancedMode();
				else if (i.equals("0"))
					setTextMode();
				else 
					setBasicMode();
			}

		});
		addEventListener(SEARCH_EVENT, new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				search();
			}
		});
	}
	
	public void search(boolean force) {
		if (dataPath != null)
		{
			SingletonBinder binder = new SingletonBinder(this);
			binder.setDataPath(dataPath);
			DataSource ds = binder.getDataSource();
			if (! force && ds.isCommitPending()) {
				Missatgebox.avis(org.zkoss.util.resource.Labels
							.getLabel("usuaris.Confirmar"),
						org.zkoss.util.resource.Labels
							.getLabel("usuaris.CanvisPendents"));
				return;
			}
			
			lastQuery  = getQueryString();

			String q = lastQuery;
			
			if (enforcedFilter != null && ! enforcedFilter.trim().isEmpty())
			{
				if (lastQuery != null && ! lastQuery.isEmpty())
					q = "("+enforcedFilter+") and ("+lastQuery+")";
				else
					q = enforcedFilter;
			}
			
			if (variableName != null)
				ds.getJXPathContext().getVariables().declareVariable(variableName, mode == TEXT? enforcedFilter: q);
			
			if (variableNameText != null)
				ds.getJXPathContext().getVariables().declareVariable(variableNameText, mode == TEXT ? lastQuery: null);

			Object v = binder.getValue();
			if (v instanceof DataModelCollection)
			{
				modelCollection = (DataModelCollection) v;
				try {
					String maxRows = ConfigurationCache.getProperty("soffid.query.maxrows");
					if (maxRows != null && ! maxRows.trim().isEmpty()) {
						try {
							modelCollection.setMaxSize(Integer.parseInt(maxRows));
						} catch (NumberFormatException e) {
							LogFactory.getLog(getClass()).warn("Error parsing soffid.query.maxrows parameter: "+maxRows);
						}
					}
					modelCollection.refresh();
				} catch (UiException e) {
					if (e.getCause() instanceof ParseException)
						throw new UiException(new InternalErrorException(Labels.getLabel("searchbox.badSyntax"), e.getCause()));
					else
						throw (UiException) e;
				} catch (RowsLimitExceededException | ListFullException e) {
					throw new UiException(String.format( Labels.getLabel("searchBox.maxRows"), modelCollection.getSize()));
				} catch (Exception e) {
					throw new UiException(e);
				}
				updateProgress();
			}
			binder.setDataPath(null);
			try {
				savePreferences();
			} catch (Exception e) {
			}
		}
	}
	
	public void updateProgress() {
		if (modelCollection == null)
			return;
		else if (modelCollection.isInProgress())
		{
			timer.setDelay(300);
			timer.start();
			progressImage.setVisible(true);
		}
		else
		{
			try {
				modelCollection.updateProgressStatus();
			} catch (UiException e) {
				if (e.getCause() instanceof ParseException)
					throw new UiException(new InternalErrorException(Labels.getLabel("searchbox.badSyntax"), e.getCause()));
				else
					throw (UiException) e;
			} catch (RowsLimitExceededException | ListFullException e) {
				throw new UiException(String.format( Labels.getLabel("searchBox.maxRows"), modelCollection.getSize()));
			} catch (Exception e) {
				throw new UiException(e);
			}
			if (modelCollection.getSize() == 1)
				Events.postEvent("onSingleRecord",  this, null);
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
				if (show)
					((Esquema) parent).showFormulari();
				else 
					((Esquema) parent).hideFormulari();
				selectFirstRow(parent);
			}
			if (parent instanceof EsquemaVertical)
			{
				if (show)
					((EsquemaVertical) parent).showFormulari();
				else 
					((EsquemaVertical) parent).hideFormulari();
				selectFirstRow(parent);
			}
		}
	}

	private void selectFirstRow(Component parent) {
		if (parent.getChildren().size() >= 2) 
		{
			Component listHolder = (Component) parent.getChildren().get(1);
			if (listHolder != null)
			{
				for (Component navigator: (Collection<Component>)listHolder.getChildren())
				{
					Component c = navigator.getFellowIfAny("listbox");
					if (c instanceof Listbox)
					{
						Listbox lb = (Listbox) c;
						if (lb.getItemCount() > 0 && lb.getSelectedItem() == null)
						{
							lb.setSelectedIndex(0);
						}
					}
					if (c instanceof Tree)
					{
						Tree tree = (Tree) c;
						if (tree.getItemCount() > 0 && tree.getSelectedItem() == null)
						{
							tree.setSelectedItem((Treeitem) tree.getItems().iterator().next());
						}
					}
				}
			}
		}
	}

	private void setTextMode() {
		mode = TEXT;
		setChildrenVisibility();
		invalidate();
		textSearchBox.setFocus(true);
	}

	public void setAdvancedMode() {
		mode = ADVANCED;
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
		setChildrenVisibility();
		invalidate();
		advancedSearch.setFocus(true);
	}

	public void setAdvancedSearchFilter(String q) {
		advancedSearch.setValue(q);
	}

	public void setBasicMode() {
		mode = BASIC;
		setChildrenVisibility();
		invalidate();
	}


	private void setChildrenVisibility ()
	{
		for (Component c: getAttributeSearchBoxes())
		{
			c.setVisible(mode == BASIC);
		}
		addAttributeButton.setVisible(mode == BASIC);
		advancedSearch.setVisible(mode == ADVANCED);
		textSearchBox.setVisible(mode == TEXT);
	}
	
	public void afterCompose () 
	{
		try {
			initialize();
			initialized  = true;
			try {
				loadPreferences ();
			} catch (JSONException e) {
			}
		} catch (Exception e) {
			throw new UiException(e);
		}
	}

	private void loadPreferences() throws JSONException, InternalErrorException, NamingException, CreateException {
		if (preference != null)
		{
			User u = EJBLocator.getUserService().getCurrentUser();
			if (u != null)
			{
				String p = EJBLocator.getPreferencesService().findMyPreference("sb-"+preference);
				if (p != null)
				{
					JSONObject j = new JSONObject(p);
					int option = j.getInt("type");
					if (option == TEXT)
						setTextMode();
					else if (option == ADVANCED)
						setAdvancedMode();
					else
						setBasicMode();
					JSONArray l = j.optJSONArray("criteria");
					if (l != null)
					{
						for (Object child: new LinkedList(getChildren()))
						{
							if (child instanceof AttributeSearchBox)
								((AttributeSearchBox) child).detach();
						}
						
						HashSet<String> atts = new HashSet<>();
						for (String att: defaultAttributes.split("[ ,]+"))
						{
							atts.add(att);
							addAttribute(att);
						}
						
						for ( int i = 0; i < l.length(); i++)
						{
							String s = l.getString(i);
							if (!atts.contains(s)) {
								addAttribute(s);
								atts.add(s);
							}
						}
					}
				}
			}
		}
	}

	private void savePreferences() throws JSONException, InternalErrorException, NamingException, CreateException {
		if (preference != null)
		{
			User u = EJBLocator.getUserService().getCurrentUser();
			if (u != null)
			{
				JSONObject j = new JSONObject();
				j.put("type", mode);
				LinkedList l = new LinkedList<String>();
				for (Object child: getChildren())
				{
					if (child instanceof AttributeSearchBox)
						l.add( ((AttributeSearchBox) child).getAttributeDef().getName() );
				}
				j.put("criteria", l);
				String p = j.toString();
				EJBLocator.getPreferencesService().updateMyPreference("sb-"+preference, p);
			}
		}
	}

	
	public void reload() throws ClassNotFoundException, InternalErrorException, NamingException, CreateException {
		dictionary = null;
		initialize();
	}
	
	protected void initialize() throws ClassNotFoundException, InternalErrorException, NamingException, CreateException {
		
		getChildren().clear();
		
		if ( mode == TEXT && ! enableQuick) mode = BASIC;
		if ( mode == BASIC &&  ! enableBasic) mode = TEXT;
		if ( mode == ADVANCED && ! enableAdvanced) mode = enableBasic ? BASIC: TEXT;
		
		if (progressImage != null)
			progressImage.detach();
		progressImage = new Image("~./img/soffid-progress.gif");
		progressImage.setVisible(false);
		progressImage.setSclass("progress");
		progressImage.setParent(this);
		if (timer != null)
			timer.detach();

		timer = new Timer();
		timer.setParent(this);
		timer.setDelay(300);
		timer.setRepeats(true);
		timer.setRunning(false);
		timer.addEventListener("onTimer", new SerializableEventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				if (modelCollection != null) {
					boolean end = !modelCollection.isInProgress();
					timer.setDelay(1000);
					try {
						modelCollection.updateProgressStatus();
					} catch (RowsLimitExceededException | ListFullException e) {
						modelCollection.cancel();
						throw new UiException(String.format( Labels.getLabel("searchBox.maxRows"), modelCollection.getSize()));
					} finally {
						if (end)
						{
							timer.stop();
							progressImage.setVisible(false);
							if (modelCollection.getSize() == 1)
								Events.postEvent("onSingleRecord",  SearchBox.this, null);
						}
					}
				}
			}
		});

		
		if (jsonObject != null && dictionary == null)
			dictionary = SearchDictionaryBuilder.build(jsonObject);
	
		if (defaultAttributes != null) {
			for (String att: defaultAttributes.split("[ ,]+"))
			{
				addAttribute(att);
			}
		}
		
		if (advancedSearch != null)
			advancedSearch.detach();
		advancedSearch = new DataTextbox();
		advancedSearch.setMultiline(true);
		advancedSearch.setRows(2);
		appendChild(advancedSearch);

		if (textSearchBox != null)
			textSearchBox.detach();
		textSearchBox = new Textbox();
		appendChild(textSearchBox);
		textSearchBox.setClass("textsearch textbox");
		textSearchBox.addEventListener("onOK", new SerializableEventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				search();
			}
		});
		

		if (addAttributeButton != null)
			addAttributeButton.detach();
		addAttributeButton = new Button(Labels.getLabel("searchBox.addAttribute"));
		addAttributeButton.addEventListener("onClick", new SerializableEventListener() {
			
			@Override
			public void onEvent(Event event) throws Exception {
				selectAttributeToAdd();
			}
		});
		addAttributeButton.setClass("searchbox-addcriteria");
		appendChild(addAttributeButton);
		
		if (menu != null)
			menu.detach();
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
		return mode == ADVANCED;
	}

	public boolean isBasicMode() {
		return mode == BASIC;
	}

	public boolean isTextMode() {
		return mode == TEXT;
	}

	public void setDefaultAttributes (String s)
	{
		if (s.equals("-auto-")) {
			s = "";
			try {
				for (DataType datatype: EJBLocator.getAdditionalDataService().findDataTypesByObjectTypeAndName2(getJsonObject(), null)) {
					if (Boolean.TRUE.equals(datatype.getSearchCriteria()))
					{
						if (! s.isEmpty()) s += ", ";
						s += datatype.getName();
					}
				}
			} catch (Exception e) {
			}
		}
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
		if (mode == TEXT)
			return textSearchBox.getText();
		else if (mode == ADVANCED)
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
		HashSet<String> atts = new HashSet();
		for (final SearchAttributeDefinition def: dictionary.getAttributes())
		{
			if (! atts.contains(def.getName())) {
				atts.add(def.getName());
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
							Labels.getLabel(def.getLabelName())+" ("+def.getName()+")");
					item.setValue(def.getName());
					item.setAutocheck(true);
					item.addEventListener("onClick", new SerializableEventListener() {
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
		if (dictionary == null)
			return false;
		
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
					c != textSearchBox &&
					!(c instanceof AttributeSearchBox) &&
					!(c instanceof Window))
				l.add(c);
		}
		return l;
	}


	public Textbox getAdvancedSearch() {
		return advancedSearch;
	}

	public Textbox getTextSearchBox() {
		return textSearchBox;
	}

	private static Command _onChangeMode  = new ComponentCommand (CHANGE_MODE_EVENT, 0) {
		protected void process(AuRequest request) {
			try {
				Events.postEvent(new Event (CHANGE_MODE_EVENT, request.getComponent(), request.getData()[0])); //$NON-NLS-1$
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
		return Executions.encodeURL("/img/search2.svg");
	}

	public boolean isAuto() {
		return auto;
	}

	public void setAuto(boolean auto) {
		this.auto = auto;
	}

	public boolean isTextSearch() {
		return textSearch;
	}

	public void setTextSearch(boolean textSearch) {
		this.textSearch = textSearch;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public int getMode() {
		return mode;
	}

	public String getVariableNameText() {
		return variableNameText;
	}

	public void setVariableNameText(String variableNameText) {
		this.variableNameText = variableNameText;
		textSearch = variableNameText != null;
	}
	
	public String getTextStyle () {
		if ( !enableQuick || 
				!enableBasic && !enableAdvanced)
			return "change-mode-label-hidden";
		else if (variableNameText == null)
			return "change-mode-label-hidden";
		else if (mode == TEXT)
			return "change-mode-label-selected";
		else
			return "change-mode-label";
	}
	public String getBasicStyle () {
		if ( !enableBasic || 
				!enableAdvanced && !enableQuick)
			return "change-mode-label-hidden";
		else if (mode == BASIC)
			return "change-mode-label-selected";
		else
			return "change-mode-label";
	}
	public String getAdvancedStyle () {
		if ( !enableAdvanced || 
				!enableBasic && !enableQuick)
			return "change-mode-label-hidden";
		else if (mode == ADVANCED)
			return "change-mode-label-selected";
		else
			return "change-mode-label";
	}

	public String getEnforcedFilter() {
		return enforcedFilter;
	}

	public void setEnforcedFilter(String enforcedFilter) {
		this.enforcedFilter = enforcedFilter;
	}

	
	public boolean isEnableQuick() {
		return enableQuick;
	}

	
	public void setEnableQuick(boolean enableQuick) {
		this.enableQuick = enableQuick;
	}

	
	public boolean isEnableBasic() {
		return enableBasic;
	}

	
	public void setEnableBasic(boolean enableBasic) {
		this.enableBasic = enableBasic;
	}

	
	public boolean isEnableAdvanced() {
		return enableAdvanced;
	}

	
	public void setEnableAdvanced(boolean enableAdvanced) {
		this.enableAdvanced = enableAdvanced;
	}

	public void search() {
		search (false);
	}
}
