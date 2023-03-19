package com.soffid.iam.web.component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.json.JSONException;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.out.AuScript;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.ComponentNotFoundException;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zul.Div;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.impl.InputElement;

import com.soffid.iam.web.menu.MenuOption;
import com.soffid.iam.web.menu.MenuParser;
import com.soffid.iam.web.popup.SelectColumnsHandler;
import com.soffid.scimquery.expr.AbstractExpression;
import com.soffid.scimquery.expr.AndExpression;
import com.soffid.scimquery.expr.ComparisonExpression;
import com.soffid.scimquery.expr.NotExpression;
import com.soffid.scimquery.expr.OrExpression;
import com.soffid.scimquery.expr.OrderByExpression;
import com.soffid.scimquery.parser.ExpressionParser;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.binder.BindContext;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.DataTable;
import es.caib.zkib.component.DataTree2;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datasource.CommitException;
import es.caib.zkib.datasource.XPathUtils;
import es.caib.zkib.zkiblaf.Application;
import es.caib.zkib.zkiblaf.Missatgebox;

public class FrameHandler extends Frame {
	String menu = "console.yaml";
	boolean nomenu = false;
	boolean registerUrl = true;
	String pageTitle = "";
	private InputField3 errorField;
	
	public FrameHandler() throws InternalErrorException {
		setStyle("position:relative");
		setSaveContent(true);
		setId("frame");
	}

	public void setUrl(String url) {
		if (!Executions.getCurrent().isExplorer() && registerUrl)
		{
			String ctx = getDesktop().getExecution().getContextPath();
			response("setUrl", 
					new AuScript(this, 
							String.format("try {"
									+ "if (! window.location.pathname.startsWith(\"%s\")) "
									+ "    window.history.pushState(\"%s\", \"%s\", window.location.protocol+\"//\"+window.location.host+\"%s\");"
									+ "document.title=\"%s\";"
									+ "} catch (e) {console.log(e);}",
									ctx + url, 
									ctx + url, 
									pageTitle, 
									ctx + url,
									pageTitle)));
		}
	}
	@Override
	public void afterCompose() {
		if (getPage() != null) {
			MenuParser menuParser = new MenuParser();
			List<MenuOption> options;
			MenuOption option = null;
			try {
				options = menuParser.getMenus(menu);
				option = menuParser.findMenuOption(options, getPage());
			} catch (Exception e) {
			}
			if (option != null)
			{
				if (option.getLiteral() != null)
					pageTitle = option.getLiteral();
				else
					pageTitle = Labels.getLabel(option.getLabel());
			} else {
				if (! nomenu) {
					setVisible(false);
					throw new SecurityException("This URL is forbidden");
				}
			}
			
			setUrl(getPage().getRequestPath());
			
			addEventListener("onReturn", (ev) -> { 
				setUrl(getPage().getRequestPath());
			});
			
			Application.registerPage(this);
			
		}
		super.afterCompose();
		
		SearchBox sb = (SearchBox) getFellowIfAny("searchBox");
		if (sb != null) {
			sb.addEventListener("onSingleRecord", event -> {
				Component lb = getListbox();
				if (lb instanceof DataTable) {
					if (((DataTable) lb).getSelectedIndex() < 0) {
						((DataTable) lb).setSelectedIndex(0);
						showDetails();
					}
				}
			});
			
			javax.servlet.http.HttpServletRequest req = (javax.servlet.http.HttpServletRequest) org.zkoss.zk.ui.Executions.getCurrent().getNativeRequest();
			String filter = req.getParameter("filter");
			if (filter != null) {
				applyFilter(sb, filter);
			}
		}
		// Set table height
		try {
			Component lb = getListbox();
			int substract = 215;
			if (sb != null)
				substract += 80; 
			if (lb instanceof DataTable) {
				DataTable dt = (DataTable) lb;
				if (dt.isEnablefilter() || true) substract += 30;
				if (dt.getMaxheight() == null || dt.getMaxheight().isEmpty())
					dt.setMaxheight("calc( 100vh - "+substract+"px )");
			}
			if (lb instanceof DataTree2) {
				DataTree2 dt = (DataTree2) lb;
				if (dt.isEnablefilter()) substract += 30;
				if (dt.getMaxheight() == null || dt.getMaxheight().isEmpty())
					dt.setMaxheight("calc( 100vh - "+substract+"px )");
			}
		} catch (ComponentNotFoundException e) {}
	}

	public void applyFilter(SearchBox sb, String filter) {
		try {
			AbstractExpression e = ExpressionParser.parse(filter);
			sb.setBasicMode();
			applyFilter (sb, e);
			sb.search();
		} catch (Exception e) {
			sb.setAdvancedMode();
			sb.setAdvancedSearchFilter(filter);
			sb.search();
		}
	}


	private void applyFilter(SearchBox sb, AbstractExpression e) throws Exception {
		if ( e instanceof AndExpression) {
			for ( AbstractExpression se: ((AndExpression) e).getMembers()) {
				applyFilter (sb, se);
			}
		}
		else if (e instanceof OrExpression) {
			List<AbstractExpression> members = ((OrExpression) e).getMembers();
			if (members.size() == 1)
				applyFilter(sb, members.get(0));
			else
				throw new Exception("Or is not supported");
		}
		else if (e instanceof OrderByExpression) {
			AbstractExpression ee = ((OrderByExpression)e).getExpression();
			applyFilter(sb, ee);
		}
		else if (e instanceof NotExpression)
			throw new Exception("Not is not supported");
		else if (e instanceof ComparisonExpression) {
			ComparisonExpression ce = (ComparisonExpression) e;
			AttributeSearchBox att = sb.addAttribute(ce.getAttribute());
			if (att.getQueryExpression() == null)
				throw new Exception("Attribute "+ce.getAttribute()+" is defined twice");
			att.setSearchFilter(ce.getOperator(), ce.getValue().toString());
		}
	}

	@Override
	public void setPage(Page p) {
		super.setPage(p);
	}
		
	public void showDetails() {
		if (isSingleFaceCard()) return;
		getCard().setSclass ( "card is-flipped" );
		focusElement (getCard().getLastChild());
		try {
			displayRemoveButton(getListbox(), false);
		} catch (ComponentNotFoundException e) {}
	}

	private boolean focusElement(Component c) {
		if (c instanceof Tabpanel && ! ((Tabpanel) c).isSelected())
			return false;
		if (c instanceof InputElement) {
			((InputElement) c).focus();
			return true;
		}
		for (Component cc = c.getFirstChild(); cc != null; cc = cc.getNextSibling())
			if (focusElement(cc))
				return true;
		return false;
	}

	public boolean isSingleFaceCard() {
		final HtmlBasedComponent card = getCard();
		return card == null || card.getSclass().equals("single-face-card");
	}
	
	protected HtmlBasedComponent getCard() {
		final HtmlBasedComponent card = (HtmlBasedComponent) getPage().getFellowIfAny("card");
		return card;
	}

	public void hideDetails() throws CommitException {
 		if (getModel() != null)
			getModel().commit();
		
		try {
			Component lb = getListbox();
			if (lb != null) {
				if (lb instanceof DataTable)
					((DataTable) lb).setSelectedIndex(-1);
				if (lb instanceof DataTree2)
					((DataTree2) lb).setSelectedIndex(new int[0]);
				
			} 
		} catch (ComponentNotFoundException e) {
			
		}

		try {
			displayRemoveButton(getListbox(), false);
		} catch (ComponentNotFoundException e) {} // Ignore
		
		if (!isSingleFaceCard()) 
			getCard().setSclass ( "card" );
	}
	
	protected Component getListbox() {
		return getFellow("listbox");
	}

	protected DataModel getModel() {
		return (DataModel) getFellowIfAny("model");
	}

	protected BindContext getForm() {
		return (BindContext) getFellow("form");
	}

	public void addNew() throws Exception {
		Component lb = getListbox();
		if (lb instanceof DataTable)
		{
			((DataTable) lb).addNew();
			showDetails();
		}
	}
	
	public void delete() throws CommitException {
		Missatgebox.confirmaOK_CANCEL(Labels.getLabel("common.delete"), 
				(event) -> {
					((Component)event.getTarget().getSpaceOwner()).setVisible(false);
					if (event.getName().equals("onOK")) {
						Component lb = getListbox();
						if (lb instanceof DataTable)
							((DataTable) lb).delete();
						if (lb instanceof DataTree2)
							((DataTree2) lb).delete();
						hideDetails();
						
					}
				});
	}

	public void onChangeForm(Event ev) throws Exception {
	}
	
	public void apply(Event ev) throws CommitException {
		if (applyNoClose(ev))
			hideDetails();
	}
	
	public void undo(Event ev) throws CommitException {
		try {
			Component lb = getListbox();
			Long id = null;
			try {
				id = (Long) XPathUtils.getValue( getForm(), "/@id");
			} catch (Exception e) {
			}
			int pos[] = null;
			if (lb instanceof DataTree2)
				pos = ((DataTree2) lb).getSelectedItem();
			getModel().refresh();
			SearchBox sb = (SearchBox) getFellowIfAny("searchBox");
			if (sb != null)
				sb.updateProgress();
			if (id != null && lb instanceof DataTable)
			{
				try {
					ListModel model = ((DataTable) lb).getModel();
					for (int i = 0; i < model.getSize(); i++)
					{
						Object o = ((DataNode)model.getElementAt(i)).getInstance();
						if (id.equals( PropertyUtils.getProperty(o, "id")))
						{
							((DataTable) lb).setSelectedIndex(i);
							return;
						}
					}
				} catch (Exception e) {
				}
			}
			if (pos != null && lb instanceof DataTree2)
			{
				((DataTree2)lb).setSelectedIndex(pos);
			}
		} catch (ComponentNotFoundException e) {
			if (getModel() != null)
				getModel().refresh();
			SearchBox sb = (SearchBox) getFellowIfAny("searchBox");
			if (sb != null)
				sb.updateProgress();
		}
		hideDetails();
	}

	public void menu(Event ev) {
		String option = (String) ev.getTarget().getAttribute("target");
		if (ev instanceof MouseEvent &&
				((MouseEvent) ev).getKeys() == MouseEvent.CTRL_KEY )
			Executions.getCurrent().sendRedirect("/main/menu.zul?option="+option, "_blank");
		else
			Application.setPage("/main/menu.zul?option="+option);
	}
	
	public boolean canClose (EventListener action)
	{
		if (getModel() != null && getModel().isCommitPending())
		{
			try
			{
				Missatgebox.confirmaYES_NO(Labels.getLabel("task.msgDeseaSalir"), //$NON-NLS-1$
						Labels.getLabel("task.titleDeseaSalir"), //$NON-NLS-1$
						(event2) -> {
							if (event2.getName().equals("onYes"))
							{
								Component c = getModel();
								if (c != null && c instanceof DataModel)
								{
									DataModel dm  =(DataModel) c;
									dm.refresh();
								}
								if (action != null)	
									action.onEvent(new Event("onClose", this));
							}
							
						},
						Messagebox.QUESTION);
				return false;
			}
			catch (Exception ex)
			{
				return true;
			}
		}
		else
		{
			return true;
		}
	}

	
	public void closeFrame() throws IOException, InstantiationException, IllegalAccessException, ClassNotFoundException, JSONException {
		Application.goBack();

	}
	
	public boolean applyNoClose(Event event) throws CommitException {
		Component form = null;
		try {
			form = (Component) getForm();
		} catch (ComponentNotFoundException e) {
		}
		errorField = null;
		if (validateAttributes (form)) {
			getModel().commit();
			return true;
		} else {
			if (errorField != null) errorField.focus();
			return false;
		}
	}

	
	public boolean validateAttributes(Component form) {
		boolean ok = true;
		if (form == null || !form.isVisible()) return true;
		if (form instanceof ObjectAttributesDiv) {
			return ((ObjectAttributesDiv) form).validate();
		}
		if (form instanceof InputField3) {
			InputField3 inputField = (InputField3)form;
			if (inputField.isReadonly() || inputField.isDisabled())
				return true;
			else {
				ok = inputField.attributeValidateAll();
				if (!ok && errorField == null)
					errorField = inputField;
				return ok;
			}
		}
		for (Component child = form.getFirstChild(); child != null; child = child.getNextSibling())
			if (! validateAttributes(child)) 
				ok = false;
		return ok;
	}

	public void downloadCsv(Event event) {
		Component lb = getListbox();
		if (lb instanceof DataTable)
			((DataTable) lb).download();
		if (lb instanceof DataTree2)
			((DataTree2) lb).download();
	}

	public void changeColumns(Event event) throws IOException {
		SelectColumnsHandler.startWizard((DynamicColumnsDatatable) getListbox());
	}

	
	public boolean isNomenu() {
		return nomenu;
	}

	
	public void setNomenu(boolean nomenu) {
		this.nomenu = nomenu;
	}

	
	public boolean isRegisterUrl() {
		return registerUrl;
	}

	
	public void setRegisterUrl(boolean registerUrl) {
		this.registerUrl = registerUrl;
	}
	
	public void displayRemoveButton(Component lb, boolean display) {
		HtmlBasedComponent d = (HtmlBasedComponent) lb.getNextSibling();
		if (d != null && d instanceof Div) {
			d =  (HtmlBasedComponent) d.getFirstChild();
			if (d != null && "deleteButton".equals(d.getSclass())) {
				d.setVisible(display);
			}
		}
	}
	
	public void multiSelect(Event event) {
		DataTable lb = (DataTable) event.getTarget();
		displayRemoveButton( lb, lb.getSelectedIndexes() != null && lb.getSelectedIndexes().length > 0);
		if (!isSingleFaceCard() && lb == getListbox()) 
			getCard().setSclass ( "card" );
	}

	public void deleteSelected(Event event0) {
		Component b = event0.getTarget();
		final Component lb = b.getParent().getPreviousSibling();
		if (lb instanceof DataTable) {
			final DataTable dt = (DataTable) lb;
			if (dt.getSelectedIndexes() == null || dt.getSelectedIndexes().length == 0) return;
			String msg = dt.getSelectedIndexes().length == 1 ? 
					Labels.getLabel("common.delete") :
					String.format(Labels.getLabel("common.deleteMulti"), dt.getSelectedIndexes().length);
				
			Missatgebox.confirmaOK_CANCEL(msg, 
					(event) -> {
						if (event.getName().equals("onOK")) {
							dt.delete();
							displayRemoveButton(lb, false);
						}
					});
		}
	}
	
	public void confirmApply (Event e) throws CommitException {
		if (getModel() == null || ! getModel().isCommitPending()) {
			hideDetails();
		} else {
			Missatgebox.confirmaYES_NO(Labels.getLabel("aplica_usuarisRolllista.zul.Confirm"), (event) -> {
				if (event.getName().equals("onYes")) {
					apply(e);
				}
			});
		}
	}
}
