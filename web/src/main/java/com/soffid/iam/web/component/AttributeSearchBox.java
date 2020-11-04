package com.soffid.iam.web.component;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.joda.time.format.ISODateTimeFormat;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.ComponentCommand;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.impl.XulElement;

import com.soffid.iam.web.SearchAttributeDefinition;

import es.caib.seycon.ng.comu.TypeEnumeration;
import es.caib.zkib.component.Databox;
import es.caib.zkib.component.DateFormats;
import es.caib.zkib.events.SerializableEventListener;

public class AttributeSearchBox extends XulElement {

	private static final long serialVersionUID = -1434804713564643330L;
	private static final String[] INT_TEXTBOXES = new String[] {"ib1", "ib2", "ib3"};
	private static final String[] STRING_TEXTBOXES = new String[] {"tb1", "tb2", "tb3", "tb4"};
	private static final String[] DATE_DATEBOXES = new String[] {"db1", "db2"};
	private static final String REMOVE_EVENT = "onRemove";
	private static final String CLICK_EVENT = "onClick";
	SearchAttributeDefinition attributeDef;
	Set<String> selectedValues = new HashSet<String>();
	String queryExpression;
	String humanExpression;
	int textOperation = 0;
	String textValue = "";
	private Date since;
	private Date until;
	
	private String escape(String s)
	{
		return s.replaceAll("\\\\", "\\\\\\\\")
				  .replaceAll("\"", "\\\\\"");
	}
	
	public void setSearchFilter(String search)
	{
		if (search == null || search.isEmpty()) {
			queryExpression = "";
			humanExpression = Labels.getLabel("attributeQuery.all");
			textValue = "";
			textOperation = 0;
		} else {
			queryExpression = attributeDef.getName()+" eq \""+escape(search)+"\"";
			humanExpression = Labels.getLabel("attributeQuery.Equals")+": \""+search +"\"";
			textValue = search;
			textOperation = 0;
		}
		invalidate();
	}
	
	public void setSearchFilter(String operator, String search)
	{
		if (search == null || search.isEmpty()) {
			queryExpression = "";
			humanExpression = Labels.getLabel("attributeQuery.all");
			textValue = "";
			textOperation = 0;
		} else {
			queryExpression = attributeDef.getName()+" "+operator+" \""+escape(search)+"\"";
			humanExpression = 
				operator.equals("eq") ? Labels.getLabel("attributeQuery.Equals") :
				operator.equals("sw") ? Labels.getLabel("attributeQuery.StartsWith") :
				operator.equals("ew") ? Labels.getLabel("attributeQuery.EndsWith") :
				operator.equals("co") ? Labels.getLabel("attributeQuery.Contains") :
				operator.equals("gt") ? Labels.getLabel("attributeQuery.GreaterThan") :
				operator.equals("lt") ? Labels.getLabel("attributeQuery.LessThan") :
				operator;
			humanExpression = humanExpression + ": \""+search +"\"";
			textValue = search;
			textOperation = 0;
		}
		invalidate();
	}
	
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
		addEventListener(REMOVE_EVENT, new SerializableEventListener() {
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
		if (selectedValues.isEmpty())
		{
			queryExpression = null;
			humanExpression = Labels.getLabel("attributeQuery.all");		
		}
		else
		{
			StringBuffer sb = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			for (String value: selectedValues)
			{
				if (sb.length() == 0)
					sb.append("(");
				else {
					sb.append(" or ");
					sb2.append(", ");
				}
				if (value == null)
				{
					sb.append("(not ")
						.append(attributeDef.getName())
						.append(" pr)");
				} else if ( attributeDef.getJavaType() == Boolean.class ){
					sb.append(attributeDef.getName())
					.append(" eq ")
					.append(escape(value));
				} else {
					sb.append(attributeDef.getName())
					.append(" eq ")
					.append('"')
					.append(escape(value))
					.append('"');
				}
				int i = attributeDef.getValues().indexOf(value);
				if (i >= 0)
					sb2.append( attributeDef.getLabels().get(i) );
				else
					sb2.append( value );
			}
			sb.append(")");
			queryExpression = sb.toString();
			humanExpression = sb2.toString();
		}
		invalidate();
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
		queryExpression = "";
		if (getParent() != null && getParent() instanceof SearchBox)
		{
			((SearchBox)getParent()).onRemoveFilter(this);
		}
		setParent(null);
		setPage(null);
	}

	
	public void onClick()
	{
		final Window w = new Window();
		final Div bg = new Div();

		insertBefore(w, this);
		getParent().invalidate();
		w.setSclass("search-popup");
		w.setPosition("parent,parent");

		if (attributeDef.getType().equals(TypeEnumeration.DATE_TYPE) )
		{
			createDateSearch(w, bg);
		}
		else if (attributeDef.getValues() != null)
		{
			createSelectSearch(w, bg);
		}
		else if (attributeDef.getJavaType() != null && 
				(Integer.class.isAssignableFrom(attributeDef.getJavaType()) ||
				 Long.class.isAssignableFrom(attributeDef.getJavaType()) ||
				 Float.class.isAssignableFrom(attributeDef.getJavaType()) ||
			  	 Double.class.isAssignableFrom(attributeDef.getJavaType())))
		{
			createIntSearch(w, bg);
		}
		else
		{
			createTextSearch(w, bg);
		}
			
		w.doEmbedded();

		insertBefore(bg, this);
		bg.setSclass("search-background");
		bg.addEventListener(CLICK_EVENT, new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				w.detach();
				bg.detach();
			}
		});
	}
	
	private void enableTextBoxes(Radiogroup target) {
		int i = target.getSelectedIndex();
		for (int j = 0; j < STRING_TEXTBOXES.length; j++)
		{
			((Textbox)target.getFellow(STRING_TEXTBOXES[j])).setDisabled(i != j);
		}
		Textbox textBox = (Textbox)target.getFellow(STRING_TEXTBOXES[i]);
		textBox.focus();
		textBox.setSelectionRange(0, textBox.getText().length());
	}

	private void doTextSearch(Window w, Div bg, Radiogroup rg) {
		textOperation = rg.getSelectedIndex();
		textValue = null;
		textValue = ((Textbox)w.getFellow(STRING_TEXTBOXES[textOperation])).getText();
		if (textValue == null || textValue.isEmpty())
		{
			queryExpression = null;
			humanExpression = Labels.getLabel("attributeQuery.all");		
		}
		else
		{
			queryExpression = attributeDef.getName()+" "+
					rg.getSelectedItem().getValue()+" \""+
					escape(textValue)+"\"";
			humanExpression = rg.getSelectedItem().getLabel()+" \""+
					textValue+"\"";
		}
		w.detach();
		bg.detach();
		invalidate();
		notifyParent();
	}

	private void notifyParent() {
		if (getParent() != null && getParent() instanceof SearchBox)
		{
			((SearchBox)getParent()).onChangeFilter();
		}
	}

	private void createTextSearch(final Window w, final Div bg) {
		Executions.createComponents("~./com/soffid/iam/web/search/text-search.zul",
				w, new HashMap<String, String>());
		final Radiogroup rg = ((Radiogroup) w.getFellow("rg"));
		rg.setSelectedIndex(textOperation);
		((Textbox)w.getFellow(STRING_TEXTBOXES[textOperation])).setText(textValue);
		enableTextBoxes(rg);
		
		rg.addEventListener("onCheck", new SerializableEventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				Component target = event.getTarget();
				if (target instanceof Radiogroup)
					enableTextBoxes((Radiogroup) target);
				else if (target instanceof Radio)
					enableTextBoxes(((Radio) target).getRadiogroup());
			}

		});
		for (String id: STRING_TEXTBOXES)
		{
			w.getFellow(id).addEventListener("onOK", new SerializableEventListener() {
				@Override
				public void onEvent(Event event) throws Exception {
					doTextSearch(w, bg, rg);
				}
		
			});
			w.getFellow(id).addEventListener("onCancel", new SerializableEventListener() {
				@Override
				public void onEvent(Event event) throws Exception {
					textOperation = 0;
					textValue = "";
					queryExpression = null;
					humanExpression = Labels.getLabel("attributeQuery.all");		
					w.detach();
					bg.detach();
					invalidate();
					notifyParent();
				}
		
			});
		}
		w.getFellow("okbutton").addEventListener(CLICK_EVENT, new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				doTextSearch(w, bg, rg);
			}
		});
		w.getFellow("cancelbutton").addEventListener(CLICK_EVENT, new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				textOperation = 0;
				textValue = "";
				queryExpression = null;
				humanExpression = Labels.getLabel("attributeQuery.all");		
				w.detach();
				bg.detach();
				invalidate();
				notifyParent();
			}
		});
	}

	private void enableIntBoxes(Radiogroup target) {
		int i = target.getSelectedIndex();
		for (int j = 0; j < INT_TEXTBOXES.length; j++)
		{
			((Intbox)target.getFellow(INT_TEXTBOXES[j])).setDisabled(i != j);
		}
		((Intbox)target.getFellow(INT_TEXTBOXES[i])).focus();
	}

	private void doIntSearch(Window w, Div bg, Radiogroup rg) {
		textOperation = rg.getSelectedIndex();
		textValue = null;
		textValue = ((Intbox)w.getFellow(INT_TEXTBOXES[textOperation])).getText();
		if (textValue == null || textValue.isEmpty())
		{
			queryExpression = null;
			humanExpression = Labels.getLabel("attributeQuery.all");		
		}
		else
		{
			queryExpression = attributeDef.getName()+" "+
					rg.getSelectedItem().getValue()+" \""+
					escape(textValue)+"\"";
			humanExpression = rg.getSelectedItem().getLabel()+" \""+
					textValue+"\"";
		}
		w.detach();
		bg.detach();
		invalidate();
		notifyParent();
	}

	private void createIntSearch(final Window w, final Div bg) {
		Executions.createComponents("~./com/soffid/iam/web/search/int-search.zul",
				w, new HashMap<String, String>());
		final Radiogroup rg = ((Radiogroup) w.getFellow("rg"));
		rg.setSelectedIndex(textOperation);
		enableIntBoxes(rg);
		((Intbox)w.getFellow(INT_TEXTBOXES[textOperation])).setText(textValue);
		
		rg.addEventListener("onCheck", new SerializableEventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				Component target = event.getTarget();
				if (target instanceof Radiogroup)
					enableIntBoxes((Radiogroup) target);
				else if (target instanceof Radio)
					enableIntBoxes(((Radio) target).getRadiogroup());
			}

		});
		for (String id: INT_TEXTBOXES)
		{
			w.getFellow(id).addEventListener("onOK", new SerializableEventListener() {
				@Override
				public void onEvent(Event event) throws Exception {
					doIntSearch(w, bg, rg);
				}
		
			});
			w.getFellow(id).addEventListener("onCancel", new SerializableEventListener() {
				@Override
				public void onEvent(Event event) throws Exception {
					textOperation = 0;
					textValue = "";
					queryExpression = null;
					humanExpression = Labels.getLabel("attributeQuery.all");		
					w.detach();
					bg.detach();
					invalidate();
					notifyParent();
				}
		
			});
		}
		w.getFellow("okbutton").addEventListener(CLICK_EVENT, new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				doIntSearch(w, bg, rg);
			}
		});
		w.getFellow("cancelbutton").addEventListener(CLICK_EVENT, new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				textOperation = 0;
				textValue = "";
				queryExpression = null;
				humanExpression = Labels.getLabel("attributeQuery.all");		
				w.detach();
				bg.detach();
				invalidate();
				notifyParent();
			}
		});
	}

	private void doDateSearch(Window w, Div bg) {
		Databox db1 = (Databox)w.getFellow("db1");
		since = (Date) db1.getValue();
		Databox db2 = (Databox)w.getFellow("db2");
		until = (Date) db2.getValue();
		setDateSearchInterval(since, until);
		w.detach();
		bg.detach();
		notifyParent();
	}

	public void setDateSearchInterval(Date since, Date until) {
		this.since = since;
		this.until = until;
		DateFormat df = DateFormats.getDateTimeFormat(); 
		
		if (since == null && until == null)
		{
			queryExpression = null;
			humanExpression = Labels.getLabel("attributeQuery.all");		
		}
		else if (since == null)
		{
			queryExpression = attributeDef.getName()+" le \""+
					ISODateTimeFormat.dateTime().print(until.getTime())+
					"\"";
			humanExpression = Labels.getLabel("attributeQuery.Until")+" \""+
					df.format(until)+"\"";
		}
		else if (until == null)
		{
			queryExpression = attributeDef.getName()+" ge \""+
					ISODateTimeFormat.dateTime().print(since.getTime())+
					"\"";
			humanExpression = Labels.getLabel("attributeQuery.Since")+" \""+
					df.format(since)+"\"";
		}
		else
		{
			String s = Labels.getLabel("attributeQuery.Between");
			queryExpression = attributeDef.getName()+" ge \""+
					ISODateTimeFormat.dateTime().print(since.getTime())+
					"\""+
					" and "+
					attributeDef.getName()+" le \""+
					ISODateTimeFormat.dateTime().print(until.getTime())+
					"\"";
			humanExpression = String.format(s,"\""+df.format(since)+"\"", "\""+df.format(until)+"\"");
		}
		invalidate();
	}

	private void createDateSearch(final Window w, final Div bg) {
		Executions.createComponents("~./com/soffid/iam/web/search/date-search.zul",
				w, new HashMap<String, String>());
		((Databox)w.getFellow("db1")).setValue( since );
		((Databox)w.getFellow("db2")).setValue( until );
		
		for (String id: DATE_DATEBOXES)
		{
			w.getFellow(id).addEventListener("onOK", new SerializableEventListener() {
				@Override
				public void onEvent(Event event) throws Exception {
					doDateSearch(w, bg);
				}
		
			});
			w.getFellow(id).addEventListener("onCancel", new SerializableEventListener() {
				@Override
				public void onEvent(Event event) throws Exception {
					textOperation = 0;
					since = null;
					until = null;
					queryExpression = null;
					humanExpression = Labels.getLabel("attributeQuery.all");		
					w.detach();
					bg.detach();
					invalidate();
					notifyParent();
				}
		
			});
		}
		w.getFellow("okbutton").addEventListener(CLICK_EVENT, new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				doDateSearch(w, bg);
			}
		});
		w.getFellow("cancelbutton").addEventListener(CLICK_EVENT, new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				textOperation = 0;
				since = null;
				until = null;
				queryExpression = null;
				humanExpression = Labels.getLabel("attributeQuery.all");		
				w.detach();
				bg.detach();
				invalidate();
				notifyParent();
			}
		});
	}

	private void doSelectSearch(Window w, Div bg, Div div) {
		selectedValues = new HashSet<String>();
		for (Component child: (List<Component>)div.getChildren())
		{
			Checkbox cb = (Checkbox) child.getFirstChild();
			if (cb.isChecked())
				selectedValues.add((String) cb.getAttribute("value"));
		}
		
		setSelectedValues(selectedValues);

		w.detach();
		bg.detach();
//		invalidate();
		notifyParent();
	}

	private void createSelectSearch(final Window w, final Div bg) {
		Executions.createComponents("~./com/soffid/iam/web/search/select-search.zul",
				w, new HashMap<String, String>());
		final Div div = ((Div) w.getFellow("searchBoxParent"));
		div.getChildren().clear();
		Iterator<String> itl = attributeDef.getLabels().iterator();
		Iterator<String> itv = attributeDef.getValues().iterator();
		while (itv.hasNext() && itl.hasNext())
		{
			String value = itv.next();
			String label = itl.next();
			Div d2 = new Div();
			d2.setStyle("margin-top: 10px");
			div.appendChild(d2);
			Checkbox cb = new Checkbox(label);
			cb.setAttribute("value", value);
			d2.appendChild(cb);
			if (selectedValues.contains(value))
				cb.setChecked(true);
		}
		
		w.getFellow("okbutton").addEventListener(CLICK_EVENT, new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				doSelectSearch(w, bg, div);
			}
		});
		w.getFellow("cancelbutton").addEventListener(CLICK_EVENT, new SerializableEventListener() {
			public void onEvent(Event event) throws Exception {
				selectedValues.clear();
				queryExpression = null;
				humanExpression = Labels.getLabel("attributeQuery.all");		
				w.detach();
				bg.detach();
				invalidate();
				notifyParent();
			}
		});
	}

	public Command getCommand(String cmdId) {
		if (REMOVE_EVENT.equals(cmdId))
			return _onRemove;
		
		return super.getCommand(cmdId);
	}

	public String getImageUrl()
	{
		return Executions.encodeURL("~./img/remove.png");
	}
	
}
