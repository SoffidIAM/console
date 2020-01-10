package com.soffid.iam.api;

import java.util.HashMap;
import java.util.List;

import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class Stats {
	String name;
	List<String> tags;
	HashMap<String,List<StatsSample>> series;
}
