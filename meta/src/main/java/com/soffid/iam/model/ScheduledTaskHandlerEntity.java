//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;
import com.soffid.mda.annotation.*;

@Entity (table="SC_SCTAHA" )
@Depends ({com.soffid.iam.api.ScheduledTaskHandler.class,
	com.soffid.iam.model.ScheduledTaskEntity.class})
public abstract class ScheduledTaskHandlerEntity {

	@Column (name="STH_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="STH_NAME", length=100)
	public java.lang.String name;

	@Column (name="STH_CLANAM", length=250)
	public java.lang.String className;

	@ForeignKey (foreignColumn="SCT_HANDLE")
	public java.util.Collection<com.soffid.iam.model.ScheduledTaskEntity> tasks;

	@DaoFinder
	public com.soffid.iam.model.ScheduledTaskHandlerEntity findByName(
		java.lang.String name) {
	 return null;
	}
}

@Index (name="SC_SCTAHA_NAME_UK",	unique=true,
entity=com.soffid.iam.model.ScheduledTaskHandlerEntity.class,
columns={"STH_NAME"})
abstract class SheculedTaskHandlerNameIndex {
}

