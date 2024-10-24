package com.soffid.iam.web.bpm;

import java.text.SimpleDateFormat;
import java.util.Iterator;

import org.zkoss.util.resource.Labels;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import com.soffid.iam.bpm.api.Job;
import com.soffid.iam.bpm.api.ProcessDefinition;
import com.soffid.iam.bpm.api.ProcessInstance;
import com.soffid.iam.bpm.api.TaskInstance;
import com.soffid.iam.bpm.service.ejb.BpmEngine;

public class ListitemCreator {
	BpmEngine engine;
	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy"); //$NON-NLS-1$
	SimpleDateFormat formatConHora = new SimpleDateFormat("dd/MM/yyyy HH:mm"); //$NON-NLS-1$


	public ListitemCreator(BpmEngine engine) {
		super();
		this.engine = engine;
	}
	
	public Listitem createListitem( TaskInstance task)
	{
		Listitem item = new Listitem();

		// tomamos la definici�n del proceso

		String style = null;
		item.setValue(task);
		if (task.getDueDate() != null
				&& task.getDueDate().getTime() < System
						.currentTimeMillis()) {
			style = "color: red"; //$NON-NLS-1$
		} else if (task.getStart() == null) {
			style = "font-weight: bold"; //$NON-NLS-1$
		}

		item.setTooltiptext(task.getDescription());
		Label l = new Label(task.getProcessName() + "\n  " + task.getName()); //$NON-NLS-1$
		l.setMultiline(true);
		l.setStyle(style);
		Listcell lc = new Listcell();
		l.setParent(lc);
		item.getChildren().add(lc);
		lc.setClass("classOne");

		if (task.getCreate() != null) {
			lc = new Listcell(formatConHora
					.format(task.getCreate()));
			lc.setStyle(style);
			lc.setClass("classTwo");
			item.getChildren().add(lc);
		} else {
			lc = new Listcell("");
			lc.setClass("classTwo");
			item.getChildren().add(lc); //$NON-NLS-1$
		}

		if (task.getDueDate() != null) {
			lc = new Listcell(format.format(task.getDueDate()));
			lc.setStyle(style);
			lc.setClass("classThree");
			item.getChildren().add(lc);
		} else {
			lc = new Listcell(Labels
					.getLabel("task.lblVencimiento")); //$NON-NLS-1$
			lc.setClass("classThree");
			lc.setStyle(style);
			item.getChildren().add(lc);
		}
		if (task.getActorId() != null) {
			lc = new Listcell(task.getActorId());
			lc.setClass("classFour");
			lc.setStyle(style);
			item.getChildren().add(lc);
		} else {
			String actors = null;
			for (Iterator it = task.getPooledActors().iterator(); it
					.hasNext();) {
				String actor = (String) it.next();
				if (actors == null)
					actors = actor;
				else
					actors = actors + "; " + actor; //$NON-NLS-1$
			}
			if (actors == null)
				actors = "-"; //$NON-NLS-1$
			lc = new Listcell(actors);
			lc.setStyle(style);
			lc.setClass("classFour");
			item.getChildren().add(lc);
		}
		String identifier;
		//PJR:optimitzacio rendiment		if (task.getVariables().get("_identifier") == null) {
					identifier = Long.toString(task.getProcessId());
		//PJR:optimitzacio rendiment		} else {
		//PJR:optimitzacio rendiment			identifier = task.getVariables().get("_identifier").toString();
		//PJR:optimitzacio rendiment		}
				lc = new Listcell(identifier);
				lc.setStyle(style);
				lc.setClass("classFive");
				item.getChildren().add(lc);

		return item;
		
	}
	
	public Listitem createListitem( Job job)
	{
		Listitem item = new Listitem();

		String style = null;
		item.setValue(job);
		if (job.getFailures() > 0 ) {
			style = "color: red"; //$NON-NLS-1$
		} else if (job.getDueDate() != null
				&& job.getDueDate().getTime() < System
						.currentTimeMillis()) {
			style = "font-weight: bold"; //$NON-NLS-1$
		}

		// Id de procés
		Label l = new Label(Long.toString(job.getId()));
		l.setStyle(style);
		Listcell lc = new Listcell();
		l.setParent(lc);
		item.getChildren().add(lc);

		// Nom del procés
		l = new Label(job.getName());
		l.setMultiline(true);
		l.setStyle(style);
		lc = new Listcell();
		l.setParent(lc);
		item.getChildren().add(lc);


		if (job.getDueDate() != null) {
			lc = new Listcell(formatConHora
					.format(job.getDueDate()));
			lc.setStyle(style);
			item.getChildren().add(lc);
		} else {
			item.getChildren().add(new Listcell("")); //$NON-NLS-1$
		}
		
		if (job.isPaused())
		{
			lc = new Listcell(Labels
					.getLabel("job.status.pause")); //$NON-NLS-1$
			lc.setStyle(style);
			item.getChildren().add(lc);
		} else if (job.isError())
		{
			lc = new Listcell(Labels
					.getLabel("job.status.error")); //$NON-NLS-1$
			lc.setStyle(style);
			item.getChildren().add(lc);
		} else if (job.getFailures() > 0 && job.getErrorMessage() != null) {
			lc = new Listcell(Labels
					.getLabel("job.status.warning")); //$NON-NLS-1$
			lc.setStyle(style);
			item.getChildren().add(lc);
		} else {
			lc = new Listcell(Labels
					.getLabel("job.status.pending")); //$NON-NLS-1$
			lc.setStyle(style);
			item.getChildren().add(lc);
		}
		return item;
		
	}
	
	public Listitem createListitem( ProcessInstance proc)
	{
		Listitem item = new Listitem();
		String style= proc.getEnd() == null ? "" : "font-weight: bold";
		item.setValue(proc);

		// Id de procés
		Label l = new Label(Long.toString(proc.getId()));
		
		Listcell lc = new Listcell();
		l.setParent(lc);
		item.getChildren().add(lc);

		// Nom del procés
		l = new Label(proc.getDescription());
		l.setMultiline(true);
		lc = new Listcell();
		l.setParent(lc);
		item.getChildren().add(lc);


		String s = formatConHora.format(proc.getStart());
		if (proc.getEnd() != null)
			s = s + " - "+formatConHora.format(proc.getEnd());
		lc = new Listcell(s);
		lc.setStyle(style);
		item.getChildren().add(lc);
		
		lc = new Listcell(proc.getCurrentTask());
		lc.setStyle(style);
		item.getChildren().add(lc);

		return item;
		
	}
	
}
