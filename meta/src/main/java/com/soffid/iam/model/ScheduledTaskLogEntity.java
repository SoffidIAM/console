package com.soffid.iam.model;

import com.soffid.iam.api.ScheduledTaskLog;
import com.soffid.mda.annotation.Column;
import com.soffid.mda.annotation.Depends;
import com.soffid.mda.annotation.Entity;
import com.soffid.mda.annotation.Identifier;
import com.soffid.mda.annotation.Nullable;

@Entity(table = "SC_SCTALO")
@Depends({ScheduledTaskLog.class})
public class ScheduledTaskLogEntity {
	@Column (name="STL_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="STL_SCT_ID", reverseAttribute = "logs")
	public ScheduledTaskEntity task;

	@Column (name="SCT_LASEXE")
	@Nullable
	public java.util.Date time;

	@Column (name="SCT_LASEND")
	@Nullable
	public java.util.Date end;

	@Column (name="SCT_LOGDOC")
	@Nullable
	public java.lang.String logReferenceID;

	@Column (name="SCT_ERROR",
		defaultValue="false")
	public boolean error;

}
