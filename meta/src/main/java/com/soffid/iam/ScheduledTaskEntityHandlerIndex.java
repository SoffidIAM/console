//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam;
import com.soffid.mda.annotation.*;

@Index (name="SC_SCHTAS_HANDLER_UK",	unique=true,
	entity=com.soffid.iam.model.ScheduledTaskEntity.class,
	columns={"SCT_HANDLE", "SCT_PARAM"})
public abstract class ScheduledTaskEntityHandlerIndex {
}

