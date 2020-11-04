package com.soffid.iam.api;

import java.util.List;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class PamSecurityCheck {
	boolean allowed;
	
	@Nullable
	List<RequestedObligation> obligations;
}
