package com.soffid.selfservice.utils;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;

import com.soffid.selfservice.utils.Inputlabel;

import es.caib.seycon.ng.comu.EstatContrasenya;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.component.DataLabel;
import es.caib.zkib.datamodel.DataNode;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathSubscriber;


import java.util.Calendar;

public class Inputlabel extends DataLabel implements XPathSubscriber{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SingletonBinder binderColor = new SingletonBinder(this);
	
	
	public Inputlabel(){
		super();
	}
	
	public void onUpdate(XPathEvent event) {
		super.onUpdate(event);
		calculateStyle();
	}

	public void setPasswordDataPath (String s)
	{
		binderColor.setDataPath(s);
		calculateStyle();
	}

	public String getPasswordDataPath ()
	{
		return binderColor.getDataPath();
	}
	
	public void setPage(Page page) {
		super.setPage(page);
		binderColor.setPage(page);
	}
	
	public void setParent(Component parent) {
		super.setParent(parent);
		binderColor.setParent(parent);
	}
	
	public Object clone() {
		Inputlabel clone = (Inputlabel) super.clone();
		clone.binderColor = new SingletonBinder(clone);
		clone.binderColor.setDataPath(binderColor.getDataPath());
		return clone;
	}
	
	private void calculateStyle(){
		Object obj = binderColor.getValue();
		if (obj == null)
		{
			return;
		}
		else if (obj instanceof DataNodeCollection)
		{
			DataNodeCollection dnc = (DataNodeCollection) obj;
			if (dnc.size() > 0)
			{
				EstatContrasenya ec = (EstatContrasenya) ((DataNode) dnc.getDataModel(0)).getInstance();
				boolean caducada = ec.getCaducada();
				
				Calendar caducitat = (Calendar) ec.getCaducitat().clone();
				Calendar avui = Calendar.getInstance();
				caducitat.add(Calendar.DAY_OF_MONTH, -5);
				if(caducada)
				{
					setStyle("color: red;");
					setTooltip("toolCaducat");
				}
				else if(caducitat.compareTo(avui)<0)
				{
					setStyle("color: #a4d100;");
					setTooltip("toolPropCaducar");
				}
				else
				{
					setStyle("color: black;");
					setTooltip("");
				}
			}
		}
	}

}
