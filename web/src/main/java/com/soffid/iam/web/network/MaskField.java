package com.soffid.iam.web.network;

import java.net.InetAddress;
import java.net.UnknownHostException;

import com.soffid.iam.web.component.CustomField3;


public class MaskField extends CustomField3 {

	@Override
	public boolean attributeValidate(Integer position, Object currentValue) {
		if (super.attributeValidate(position, currentValue)) {
			try {
				InetAddress address = InetAddress.getByName((String) currentValue);
				byte[] addr = address.getAddress();
				boolean end = false;
				for (byte ab: addr) {
					int b = ab;
					if (b < 0) b += 256;
					if (end) {
						if (b != 0) {
							setWarning(position, "Wrong IP mask address. Value "+b+" should be 0");
							return false;
						}
					} else {
						if (b != 0xff) {
							end = true;
							if (b != 0 && b != 0x80 && b != 0xc0 && b != 0xe0 && b != 0xf0 &&
									b != 0xf8 && b != 0xfc && b != 0xfe) {
								setWarning(position, "Wrong IP mask address. Value "+b+" is not valid");
								return false;
							}
						}
					}
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
