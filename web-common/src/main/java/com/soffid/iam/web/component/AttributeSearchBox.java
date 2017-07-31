package com.soffid.iam.web.component;

import java.util.HashMap;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.ComponentCommand;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.XulElement;

import com.soffid.iam.web.SearchAttributeDefinition;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.zkib.zkiblaf.SignApplet;

public class AttributeSearchBox extends XulElement {
	private static final String REMOVE_EVENT = "onRemove";
	private static final String CLICK_EVENT = "onClick";
	SearchAttributeDefinition attributeDef;
	Set<String> selectedValues;
	String queryExpression;
	String humanExpression;
	int textOperation = 0;
	String textValue = "";
	
	public SearchAttributeDefinition getAttributeDef() {
		return attributeDef;
	}

	public void setAttributeDef(SearchAttributeDefinition attributeDef) {
		this.attributeDef = attributeDef;
	}

	public AttributeSearchBox() {
		setSclass("attribute-search-box");
		queryExpression = "";
		humanExpression = Labels.getLabel("attributeQuery.all");
		addEventListener(REMOVE_EVENT, new EventListener() {
			public void onEvent(Event event) throws Exception {
				onRemove();
			}
		});
//		addEventListener(CLICK_EVENT, new EventListener() {
//			public void onEvent(Event event) throws Exception {
//				onClick();
//			}
//		});
	}

	public Set<String> getSelectedValues() {
		return selectedValues;
	}

	public void setSelectedValues(Set<String> selectedValues) {
		this.selectedValues = selectedValues;
	}

	public String getQueryExpression() {
		return queryExpression;
	}

	public void setQueryExpression(String queryExpression) {
		this.queryExpression = queryExpression;
	}

	public String getHumanExpression() {
		return humanExpression;
	}

	public void setHumanExpression(String humanExpression) {
		this.humanExpression = humanExpression;
	}
	
	public String getFieldLabel ()
	{
		if (attributeDef == null)
			return "";
		if (attributeDef.getLocalizedName() != null)
			return attributeDef.getLocalizedName();
		else
			return Labels.getLabel(attributeDef.getLabelName());
	}

	private static Command _onRemove  = new ComponentCommand (REMOVE_EVENT, 0) {

		protected void process(AuRequest request) {
			try {
				Events.postEvent(new Event (REMOVE_EVENT, request.getComponent(),"")); //$NON-NLS-1$
			} catch (Exception e) {
				throw new UiException(e);
			}
		}
		
	};
	
	public void onRemove ()
	{
		setParent(null);
		setPage(null);
	}

	
	public void onClick()
	{
		final Window w = new Window();
		final Div bg = new Div();

		getParent().insertBefore(w, this);
		w.setSclass("search-popup");
		w.setPosition("parent,parent");

		if (attributeDef.getType().equals(TypeEnumeration.STRING_TYPE))
		{
			createTextSearch(w, bg);
		}
			
		w.doEmbedded();

		getParent().insertBefore(bg, this);
		bg.setSclass("search-background");
		bg.addEventListener("onClick", new EventListener() {
			public void onEvent(Event event) throws Exception {
				w.detach();
				bg.detach();
			}
		});
	}
	
	private void enableTextBoxes(Radiogroup target) {
		int i = target.getSelectedIndex();
		((Textbox)target.getFellow("tb1")).setDisabled(i != 0);
		((Textbox)target.getFellow("tb2")).setDisabled(i != 1);
		((Textbox)target.getFellow("tb3")).setDisabled(i != 2);
		((Textbox)target.getFellow("tb4")).setDisabled(i != 3);
	}

	private void doTextSearch(Window w, Div bg, Radiogroup rg) {
		int i = rg.getSelectedIndex();
		String t = null;
		if ( i == 0)
			t = ((Textbox)w.getFellow("tb1")).getText();
		else if ( i == 1)
			t = ((Textbox)w.getFellow("tb2")).getText();
		else if ( i == 2)
			t = ((Textbox)w.getFellow("tb3")).getText();
		else
			t = ((Textbox)w.getFellow("tb4")).getText();
		if (t == null || t.isEmpty())
		{
			queryExpression = null;
			Labels.getLabel("attributeQuery.all");		
		}
		else
		{
			queryExpression = attributeDef.getName()+" "+
					rg.getSelectedItem().getValue()+" \""+
					t+"\"";
			humanExpression = rg.getSelectedItem().getLabel()+" \""+
					t+"\"";
		}
		invalidate();
	}

	private void createTextSearch(final Window w, final Div bg) {
		Executions.createComponents("~./com/soffid/iam/web/search/text-search.zul",
				w, new HashMap<String, String>());
		((Button)w.getFellow("okbutton")).setLabel(Labels.getLabel("agentsllista.zul.Accepta"));
		((Button)w.getFellow("cancelbutton")).setLabel(Labels.getLabel("agentsllista.zul.Cancel·la"));

		final Radiogroup rg = ((Radiogroup) w.getFellow("rg"));
		rg.setSelectedIndex(textOperation);
		enableTextBoxes(rg);
		if ( textOperation == 0)
			((Textbox)w.getFellow("tb1")).setText(textValue);
		else if ( textOperation == 1)
			((Textbox)w.getFellow("tb2")).setText(textValue);
		else if ( textOperation == 2)
			((Textbox)w.getFellow("tb3")).setText(textValue);
		else
			((Textbox)w.getFellow("tb4")).setText(textValue);
		
		rg.addEventListener("onCheck", new EventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				Component target = event.getTarget();
				if (target instanceof Radiogroup)
					enableTextBoxes((Radiogroup) target);
				else if (target instanceof Radio)
					enableTextBoxes(((Radio) target).getRadiogroup());
			}

		});
		for (String id: new String[] {"tb1", "tb2", "tb3", "tb4"})
		{
			w.getFellow(id).addEventListener("onOK", new EventListener() {
				@Override
				public void onEvent(Event event) throws Exception {
					doTextSearch(w, bg, rg);
				}
		
			});
		}
		w.getFellow("okbutton").addEventListener("onClick", new EventListener() {
			public void onEvent(Event event) throws Exception {
				doTextSearch(w, bg, rg);
			}
		});
		w.getFellow("cancelbutton").addEventListener("onClick", new EventListener() {
			public void onEvent(Event event) throws Exception {
				w.detach();
				bg.detach();
			}
		});
	}

	public Command getCommand(String cmdId) {
		if (REMOVE_EVENT.equals(cmdId))
			return _onRemove;
		
		return super.getCommand(cmdId);
	}

}
