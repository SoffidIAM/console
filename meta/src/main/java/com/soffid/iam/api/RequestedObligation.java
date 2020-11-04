package com.soffid.iam.api;

import java.util.Map;

import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
class RequestedObligation {
	String  obligation;
	@Nullable
	Map<String,String> attributes;
}