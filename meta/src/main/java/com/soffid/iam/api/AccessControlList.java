package com.soffid.iam.api;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Set;

import com.soffid.mda.annotation.Attribute;
import com.soffid.mda.annotation.Nullable;
import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class AccessControlList {
	@Nullable @Attribute (defaultValue="new java.util.HashSet<Long>()")
	Set<Long> users;
	@Nullable @Attribute (defaultValue="new java.util.HashSet<Long>()")
	Set<Long> roles; 
	@Nullable @Attribute (defaultValue="new java.util.HashSet<Long>()")
	Set<Long> groups;	
}
 