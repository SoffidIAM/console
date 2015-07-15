package com.soffid.iam.model.security;

import java.util.Collection;

public interface SecurityScopeEntity {
	public boolean isAllowed (String permission);
}
