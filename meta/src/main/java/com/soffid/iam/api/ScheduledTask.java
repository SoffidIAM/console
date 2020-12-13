//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.api;
import java.util.List;

import com.soffid.mda.annotation.*;

@ValueObject 
public class ScheduledTask {

	@Nullable
	public java.lang.Long id;

	@Nullable
	public String tenant;
	
	public java.lang.String name;

	@Nullable
	public java.lang.String params;

	public java.lang.String handlerName;

	@Nullable
	public java.util.Calendar nextExecution;

	@Nullable
	public java.util.Calendar lastExecution;

	@Nullable
	public java.util.Calendar lastEnd;

	@Nullable
	public java.lang.String logReferenceID;

	@Attribute(defaultValue="\"*\"")
	public java.lang.String dayPattern;

	@Attribute(defaultValue="\"0\"")
	public java.lang.String hoursPattern;

	@Attribute(defaultValue="\"*\"")
	public java.lang.String monthsPattern;

	@Attribute(defaultValue="\"*\"")
	public java.lang.String dayOfWeekPattern;

	@Attribute(defaultValue="\"0\"")
	public java.lang.String minutesPattern;

	public boolean error;

	public boolean active;
	
	public boolean enabled;

	@Nullable
	public java.lang.String serverName;

	@Nullable
	@Attribute(defaultValue = "new java.util.LinkedList<>()")
	public List<ScheduledTaskLog> logs;
}
