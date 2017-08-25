package com.soffid.iam.api;

import java.util.Map;

import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class SamlRequest {
	String url;
	String method;
	Map<String,String> parameters;
}
