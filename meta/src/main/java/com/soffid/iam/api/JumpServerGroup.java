package com.soffid.iam.api;

import java.util.List;

import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class JumpServerGroup {
	@Nullable
	Long id;
	
	String name;
	
	@Nullable
	String description;
	
	String storeUrl;
	
	String storeUserName;
	
	String password;

	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<String>()")
	List<String> jumpServers;
}
