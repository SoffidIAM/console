//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam;
import com.soffid.mda.annotation.*;

@Index (name="SC_SCHTAS_NAME_UK",	unique=true,
	entity=com.soffid.iam.model.ScheduledTaskEntity.class,
	columns={"SCT_NAME"})
public abstract class ScheduledTaskEntityNameIndex {
}

