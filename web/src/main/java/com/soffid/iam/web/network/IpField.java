package com.soffid.iam.web.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.soffid.iam.web.component.CustomField3;


public class IpField extends CustomField3 {

	@Override
	public boolean attributeValidate(Integer position, Object currentValue) {
		if (super.attributeValidate(position, currentValue)) {
			try {
				InetAddress address = InetAddress.getByName((String) currentValue);
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
