package com.soffid.iam.sync.engine.intf;

import com.soffid.mda.annotation.ValueObject;

@ValueObject
public class DebugTaskResults {
	String status;
	String log;
	Exception exception;
}
