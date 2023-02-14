package com.soffid.iam.web.wheel;

import com.soffid.iam.utils.ConfigurationCache;

public class Pam04Sector extends Sector {

	public Pam04Sector(String tag) {
		super(tag);
	}

	@Override
	public boolean isDone() {
		return ConfigurationCache.getProperty("soffid.otp.required") != null;
	}

}
