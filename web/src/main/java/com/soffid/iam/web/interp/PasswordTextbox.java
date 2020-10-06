package com.soffid.iam.web.interp;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Textbox;

import es.caib.seycon.ng.comu.Password;
import es.caib.zkib.binder.SingletonBinder;
import es.caib.zkib.datasource.DataSource;
import es.caib.zkib.events.XPathEvent;
import es.caib.zkib.events.XPathSubscriber;

public class PasswordTextbox extends Textbox implements XPathSubscriber {
	private SingletonBinder binder = new SingletonBinder (this);
	private boolean duringOnUpdate = false;
	final String dummyPassword = "!$%&/()=_:;"; //$NON-NLS-1$
	
	
	public void undo(){
		binder.setOldValue();
	}
	
	/* (non-Javadoc)
	 * @see com.centillex.zk.Bindable#getBind()
	 */
	public String getBind() {		
		return binder.getDataPath();
		
	}

	/* (non-Javadoc)
	 * @see com.centillex.zk.Bindable#setBind(java.lang.String)
	 */

	public void setText(String value) throws WrongValueException {
		super.setText(value);
		if (! duringOnUpdate )
		{
			if (! dummyPassword.equals(value))
				binder.setValue(new Password(value));
		}
	}

	/* (non-Javadoc)
	 * @see com.centillex.zk.Bindable#setBind(java.lang.String)9
	 */

	public void setValue(String value) throws WrongValueException {
		super.setValue(value);
		if (! duringOnUpdate )
		{
			if (! dummyPassword.equals(value))
				binder.setValue(new Password(value));
		}
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.net.web.zul.BinderImplementation#getDataSource()
	 */
	public DataSource getDataSource() {
		return binder.getDataSource();
	}

	/* (non-Javadoc)
	 * @see es.caib.seycon.net.web.zul.BinderImplementation#setBind(java.lang.String)
	 */
	public void setBind(String bind) {
		binder.setDataPath(bind);
		if (bind != null)
		{
			refreshValue ();
		}
	}

	public void onUpdate (XPathEvent event) {
		refreshValue ();
	}

	private void refreshValue ()
	{
		Object newVal = binder.getValue();
		try {
			duringOnUpdate = true;
			if (newVal == null)
				setValue (""); //$NON-NLS-1$
			else
				setValue( dummyPassword );
		} finally {
			duringOnUpdate = false;
		}
		
		boolean disable ;
		if (!binder.isVoid () && ! binder.isValid())
			disable = true;
		else
			disable = effectiveDisabled;
		if (disable != isDisabled())
		{
			super.setDisabled(disable);
			invalidate();
		}
	}
	
	public boolean effectiveDisabled = false;
	/* (non-Javadoc)
	 * @see org.zkoss.zul.impl.InputElement#setDisabled(boolean)
	 */
	public void setDisabled(boolean disabled) {
		effectiveDisabled = disabled;
		super.setDisabled(disabled);
	}

	public void setPage(Page page) {
		super.setPage(page);
		binder.setPage(page);
	}
	
	public void setOldValue() {
		binder.setOldValue();
	}

	public void setParent(Component parent) {
		super.setParent(parent);
		binder.setParent(parent);
	}

	public Object clone() {
		PasswordTextbox clone = (PasswordTextbox) super.clone();
		clone.binder = new SingletonBinder (clone);
		clone.binder.setDataPath(binder.getDataPath());
		clone.effectiveDisabled = effectiveDisabled;
		return clone;
	}

}
