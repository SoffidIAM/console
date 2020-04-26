package com.soffid.iam.web.component;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Timer;

import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.datamodel.DataModelCollection;
import es.caib.zkib.events.SerializableEventListener;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathRerunEvent;
import es.caib.zkib.events.XPathSubscriber;

public class ProgressImage extends Div implements XPathSubscriber {
	String path;
	SingletonBinder binder = new SingletonBinder(this);
	private Image progressImage;
	private Timer timer;
	private DataModelCollection modelCollection;

	public ProgressImage() {
		init ();
	}
	
	@Override
	public void onUpdate(XPathEvent event) {
		if (event instanceof XPathRerunEvent)
			startTimer();
	}

	private void init() {
		progressImage = new Image("~./img/soffid-progress.gif");
		progressImage.setVisible(false);
		progressImage.setSclass("progress");
		progressImage.setParent(this);
		timer = new Timer();
		timer.setParent(this);
		timer.setDelay(300);
		timer.setRepeats(true);
		timer.setRunning(false);
		timer.addEventListener("onTimer", new SerializableEventListener() {
			@Override
			public void onEvent(Event event) throws Exception {
				boolean end = modelCollection == null || !modelCollection.isInProgress();
				timer.setDelay(1000);
				try {
					modelCollection.updateProgressStatus();
				} finally {
					if (end)
					{
						stopTimer();
					}
				}
			}
		});
	}
	
	public ProgressImage clone ()
	{
		ProgressImage n = new ProgressImage();
		n.init();
		n.startTimer();
		return n;
	}

	private void startTimer() {
		Object o = binder.getValue();
		if (o instanceof DataModelCollection)
		{
			modelCollection = (DataModelCollection) o;
			if (modelCollection.isInProgress())
			{
				progressImage.setVisible(true);
				timer.start();
			}
			else
				stopTimer();
		}
		else
		{
			modelCollection = null;
			stopTimer();
		}
	}

	private void stopTimer() {
		timer.stop();
		progressImage.setVisible(false);
	}

	public void setBind(String s)
	{
		binder.setDataPath(s);
		startTimer();
	}
	
	public String getBind ()
	{
		return binder.getDataPath();
	}

	public void setPage(Page page) {
		super.setPage(page);
		binder.setPage(page);
		startTimer();
	}
	

	public void setParent(Component parent) {
		super.setParent(parent);
		binder.setParent(parent);
		startTimer();
	}
}
