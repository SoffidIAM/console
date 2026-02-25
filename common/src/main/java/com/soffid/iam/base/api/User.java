package com.soffid.iam.base.api;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import com.soffid.iam.base.api.AbstractUser;

public class User extends AbstractUser {
	private static final long serialVersionUID = 1;

	public User() {
	}

	public User(String userName, String firstName, String lastName, String userType, String primaryGroup) {
		super(userName, firstName, lastName, userType, primaryGroup);
	}

	public User(AbstractUser usuari) {
		super (usuari);
	}

	@Override
	public void setShortName(String shortName) {
		super.setShortName(shortName);
		if (shortName == null || shortName.trim().isEmpty() ||
				getMailDomain() == null || getMailDomain().trim().isEmpty()) {
			super.setEmailAddress(null);
		} else {
			super.setEmailAddress(getShortName()+"@"+getMailDomain());
		}
	}

	@Override
	public void setEmailAddress(String emailAddress) {
		super.setEmailAddress(emailAddress);
		if (emailAddress == null) {
			super.setShortName(null);
			super.setMailDomain(null);
		} else {
			int i = emailAddress.indexOf('@');
			if (i >= 0) {
				super.setShortName(emailAddress.substring(0,i));
				super.setMailDomain(emailAddress.substring(i+1));
			} else {
				super.setShortName(emailAddress);
				super.setMailDomain(null);
			}
		}
	}

	@Override
	public void setMailDomain(String mailDomain) {
		super.setMailDomain(mailDomain);
		if (getShortName() == null || getShortName().trim().isEmpty() ||
				getMailDomain() == null || getMailDomain().trim().isEmpty()) {
			super.setEmailAddress(null);
		} else {
			super.setEmailAddress(getShortName()+"@"+getMailDomain());
		}
	}

}
