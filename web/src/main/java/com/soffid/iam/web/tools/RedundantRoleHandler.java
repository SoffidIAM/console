package com.soffid.iam.web.tools;

import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.Progressmeter;
import org.zkoss.zul.Timer;

import com.soffid.iam.EJBLocator;
import com.soffid.iam.api.AsyncProcessTracker;
import com.soffid.iam.api.exception.ListFullException;
import com.soffid.iam.web.component.FrameHandler;
import com.soffid.iam.web.component.SearchBox;
import com.soffid.scimquery.parser.ParseException;

import es.caib.seycon.ng.exception.InternalErrorException;
import es.caib.zkib.component.DataModel;
import es.caib.zkib.component.Div;
import es.caib.zkib.component.Wizard;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.datamodel.DataNodeCollection;
import es.caib.zkib.datamodel.RowsLimitExceededException;


public class RedundantRoleHandler extends FrameHandler {

	private Wizard wizard;
	private Timer timer;
	private Div progressImage;
	private Button step2next;
	private AsyncProcessTracker progress;
	private Div progressImage2;
	private Div progressImage3;
	private Progressmeter progressPct;

	public RedundantRoleHandler() throws InternalErrorException {
		super();
	}

	@Override
	public void afterCompose() {
		super.afterCompose();
		wizard = (Wizard) getFellow("wizard");
		timer = (Timer) getFellow("timer");
		progressImage = (Div) getFellow("progressImage");
		progressImage2 = (Div) getFellow("progressImage2");
		progressImage3 = (Div) getFellow("progressImage3");
		progressPct = (Progressmeter) getFellow("progressPct");
		step2next = (Button) getFellow("step2next");
	}

	public void back(Event event) {
		wizard.previous();
		if (wizard.getSelected() == 1)
		{
			timer.stop();
		}
	}
	
	public void next(Event event) throws Exception {
		wizard.next();
		if (wizard.getSelected() == 2) {
			timer.setDelay(300);
			timer.setRepeats(true);
			timer.start();
			DataModel m = getModel();
			m.getJXPathContext().getVariables().declareVariable("searchRedundant", true);
			DataNodeCollection coll = (DataNodeCollection) m.getValue("/grant");
			coll.refresh();
			progressImage.setVisible(true);
			step2next.setVisible(false);
		}
		if (wizard.getSelected() == 3) {
			SearchBox sb = (SearchBox) getFellow("searchBox");
			progress = EJBLocator.getApplicationService().removeRedundantRoles( sb.getQueryString() );
			timer.setDelay(300);
			timer.setRepeats(true);
			timer.start();
			progressImage2.setVisible(true);
			progressImage3.setVisible(false);
			progressPct.setValue(0);
		}
	}

	public void onTimer(Event event) throws Exception {
		if (wizard.getSelected() == 2) {
			DataModel m = getModel();
			DataNodeCollection modelCollection = (DataNodeCollection) m.getValue("/grant");
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
					step2next.setVisible(true);
				}
			}
		}
		if (wizard.getSelected() == 3) {
			if (progress.isFinished()) {
				progressImage2.setVisible(false);
				progressImage3.setVisible(true);
				timer.stop();
				if (progress.getErrorMessage() != null) {
					((Label) progressImage3.getFirstChild()).setValue(progress.getErrorMessage());
				}
			} else {
				progressPct.setValue((int)(100.0 * progress.getProgress()));
			}
		}
	}
}
