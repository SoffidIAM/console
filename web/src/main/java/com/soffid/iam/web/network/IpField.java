package com.soffid.iam.web.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.zkoss.zk.ui.Component;

import com.soffid.iam.web.component.CustomField3;

import es.caib.zkib.component.Databox;


public class IpField extends CustomField3 {

	final int byteMask[] = {0, 0x80, 0xc0, 0xe0, 0xf0, 0xf8, 0xfc, 0xfe, 0xff};
	@Override
	public boolean attributeValidate(Integer position, Object currentValue) {
		if (super.attributeValidate(position, currentValue)) {
			try {
				final String v = (String) currentValue;
				if (v.contains("/")) {
					int bits = 0;
					final String bitsString = v.substring(v.indexOf("/")+1);
					try {
						bits = Integer.parseInt(bitsString);
					} catch (NumberFormatException e) {
						setWarning (0, bitsString+" is not a valid network size");
					}
					InetAddress address = InetAddress.getByName(v.substring(0, v.indexOf("/")));
					byte[] a = address.getAddress();
					byte[] m = new byte[a.length];
					for (int i = 0; i < m.length; i++) {
						m[i] = -1;
						if (bits >= 8) {
							bits -= 8;
						} else {
							a[i] = (byte) (a[i] & byteMask[bits]);
							m[i] = (byte) (m[i] & byteMask[bits]);
							bits = 0;
						}
					}
					InetAddress a2 = InetAddress.getByAddress(a);
					setValue(a2.getHostAddress());
					Component next = getNextSibling();
					if (next != null && next instanceof Databox) {
						((Databox) next).setValue(InetAddress.getByAddress(m).getHostAddress());
					}
				} else {
					InetAddress.getByName(v);
				}
				return true;
			} catch (UnknownHostException e) {
				setWarning(position, "Wrong IP address");
				return false;
			}
		} else {
			return false;
		}
	}

}
