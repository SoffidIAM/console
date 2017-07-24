package com.soffid.iam.web;

import java.util.List;

import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class SearchDictionary {
	List<SearchAttributeDefinition> attributes;
}
