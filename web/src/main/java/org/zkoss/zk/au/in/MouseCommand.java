package org.zkoss.zk.au.in;

/* MouseCommand.java
*/

import org.zkoss.lang.Objects;

import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;
import org.zkoss.zk.au.in.Commands;

/**
 * Used only by {@link AuRequest} to implement the {@link MouseEvent}
 * relevant command.
 *
 * @author tomyeh
 * @since 3.0.0
 */
public class MouseCommand extends Command {
	public MouseCommand(String evtnm, int flags) {
		super(evtnm, flags);
	}

	//-- super --//
	protected void process(AuRequest request) {
		final Component comp = request.getComponent();
		if (comp == null)
			throw new UiException(MZk.ILLEGAL_REQUEST_COMPONENT_REQUIRED, this);
		final String[] data = request.getData();
		if (data != null && data.length != 1 && data.length != 2 && data.length != 3)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA,
				new Object[] {Objects.toString(data), this});

		if (data != null && data.length > 1)
		{
			int x = data[0].indexOf('.');
			if (x >= 0) data[0] = data[0].substring(0,  x);
			x = data[1].indexOf('.');
			if (x >= 0) data[1] = data[1].substring(0,  x);
		}
		final MouseEvent event =
		data == null || data.length == 0 ?
			new MouseEvent(getId(), comp):			//no area, no coord
		data.length == 1 ?
			new MouseEvent(getId(), comp, data[0]):	//by area
			new MouseEvent(getId(), comp,			//by coord
				Integer.parseInt(data[0]), Integer.parseInt(data[1]),
				data.length == 2 ? 0: Commands.parseKeys(data[2]));
		Events.postEvent(event);
	}
}
