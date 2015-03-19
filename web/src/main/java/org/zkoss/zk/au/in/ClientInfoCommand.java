/* ClientInfoCommand.java

{{IS_NOTE
	Purpose:
		
	Description:
		
	History:
		Fri Jul 28 14:13:09     2006, Created by tomyeh
}}IS_NOTE

Copyright (C) 2006 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under GPL Version 2.0 in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.zk.au.in;

import org.zkoss.lang.Objects;

import org.zkoss.zk.mesg.MZk;
import org.zkoss.zk.ui.UiException;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ClientInfoEvent;
import org.zkoss.zk.au.AuRequest;
import org.zkoss.zk.au.Command;

/**
 * Used by {@link AuRequest} to implement a command to broadcast
 * an {@link ClientInfoEvent} event to all root components.
 * 
 * @author tomyeh
 * @since 3.0.0
 */
public class ClientInfoCommand extends Command {
	public ClientInfoCommand(String evtnm, int flags) {
		super(evtnm, flags);
	}

	private Integer parseInt(String s)
	{
		int i = s.indexOf('.');
		if (i >= 0)
			return Integer.parseInt(s.substring(0, i));
		else
			return Integer.parseInt(s);
	}
	//-- super --//
	protected void process(AuRequest request) {
		final String[] data = request.getData();
		if (data == null || data.length != 8)
			throw new UiException(MZk.ILLEGAL_REQUEST_WRONG_DATA,
				new Object[] {Objects.toString(data), this});
		//Note: ClientInfoEvent is a broadcast event
		Events.postEvent(new ClientInfoEvent(getId(),
			parseInt(data[0]), parseInt(data[1]),
			parseInt(data[2]), parseInt(data[3]),
			parseInt(data[4]), parseInt(data[5]),
			parseInt(data[6]), parseInt(data[7])));
	}
}
