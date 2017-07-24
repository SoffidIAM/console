package com.soffid.iam.web.component;

import java.util.HashMap;
import java.util.Set;

import org.apache.commons.codec.binary.Base64;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.ComponentCommand;
import org.zkoss.zk.ui.AbstractComponent;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.HtmlBasedComponent;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Popup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.XulElement;

import com.soffid.iam.web.SearchAttributeDefinition;

import es.caib.zkib.zkiblaf.SignApplet;

public class AttributeSearchBox extends XulElement {
	private static final String REMOVE_EVENT = "onRemove";
	private static final String CLICK_EVENT = "onClick";
	SearchAttributeDefinition attributeDef;
	Set<String> selectedValues;
	String textValue;
	String queryExpression;
	String humanExpression;
	
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
		final String previousValue = textValue;
		getParent().insertBefore(w, this);
		w.setSclass("search-popup");
		w.setPosition("parent,parent");
		Executions.createComponentsDirectly("<div><div><label value='Filter:'/><textbox id='tb'/></div></div>", "zul",
				w, new HashMap<String, String>());
		Executions.createComponentsDirectly(
				"<div class='button-container'><div class='buttons'>"
				+ "<button id='okbutton'/><button id='cancelbutton'/>"
				+ "</div></div>", "zul", w, new HashMap<String, String>());
		((Button)w.getFellow("okbutton")).setLabel(Labels.getLabel("agentsllista.zul.Accepta"));
		((Button)w.getFellow("cancelbutton")).setLabel(Labels.getLabel("agentsllista.zul.Cancel·la"));
		final Textbox tb = ((Textbox)w.getFellow("tb"));
		tb.setText(textValue);
		
		w.getFellow("tb").addEventListener("onOK", new EventListener() {
			public void onEvent(Event event) throws Exception {
				setHumanExpression(tb.getText());
				setTextValue(tb.getText());
				invalidate();
				w.detach();
				bg.detach();
			}
		});
		w.getFellow("okbutton").addEventListener("onClick", new EventListener() {
			public void onEvent(Event event) throws Exception {
				setHumanExpression(tb.getText());
				setTextValue(tb.getText());
				invalidate();
				w.detach();
				bg.detach();
			}
		});
		w.getFellow("cancelbutton").addEventListener("onClick", new EventListener() {
			public void onEvent(Event event) throws Exception {
				((Textbox)w.getFellow("tb")).setText(previousValue);
				w.detach();
				bg.detach();
			}
		});
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
	
	private void setTextValue(String text) {
		textValue = text;
		if (!textValue.isEmpty())
		{
			queryExpression = attributeDef.getName()+" co \""+textValue+"\"";
		}
	}

	public Command getCommand(String cmdId) {
		if (REMOVE_EVENT.equals(cmdId))
			return _onRemove;
		
		return super.getCommand(cmdId);
	}

}
