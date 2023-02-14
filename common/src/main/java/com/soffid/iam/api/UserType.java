package com.soffid.iam.api;

public class UserType extends AbstractUserType {

	@Override
	public void setUnmanaged(boolean unmanaged) {
		super.setUnmanaged(unmanaged);
		super.setManaged(!unmanaged);
	}

	@Override
	public void setManaged(boolean managed) {
		super.setManaged(managed);
		super.setUnmanaged(!managed);
	}

}
