package com.soffid.iam.sync.engine.intf;

import java.util.Map;

import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class GetObjectResults {
	String status;
	String log;
	Map<String,Object> object;
}
