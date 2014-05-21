//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam;
import com.soffid.mda.annotation.*;

@Index (name="SC_SCTAHA_NAME_UK",	unique=true,
	entity=com.soffid.iam.model.ScheduledTaskHandlerEntity.class,
	columns={"STH_NAME"})
public abstract class SheculedTaskHandlerNameIndex {
}

