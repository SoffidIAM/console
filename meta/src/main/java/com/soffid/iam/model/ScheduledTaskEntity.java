//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package com.soffid.iam.model;
import java.util.Collection;

import com.soffid.mda.annotation.*;

@Entity (table="SC_SCHTAS" )
@Depends ({com.soffid.iam.api.ScheduledTask.class,
	com.soffid.iam.model.ScheduledTaskHandlerEntity.class,
	es.caib.seycon.ng.model.ServerEntity.class})
public abstract class ScheduledTaskEntity {

	@Column (name="SCT_HANDLE")
	public com.soffid.iam.model.ScheduledTaskHandlerEntity handler;

	@Column (name="SCT_ID")
	@Nullable
	@Identifier
	public java.lang.Long id;

	@Column (name="SCT_NAME", length=100)
	public java.lang.String name;

	@Column (name="SCT_PARAM", length=250)
	@Nullable
	public java.lang.String params;

	@Column (name="SCT_SCHEDU", length=100)
	@Nullable
	public java.lang.String schedulePattern;

	@Column (name="SCT_LASEXE")
	@Nullable
	public java.util.Date lastExecution;

	@Column (name="SCT_LASEND")
	@Nullable
	public java.util.Date lastEnd;

	@Column (name="SCT_LASLOG", length=128000)
	@Nullable
	public java.lang.String lastLog;

	@Column (name="SCT_ERROR",
		defaultValue="false")
	public boolean error;

	@Column (name="SCT_SERVER")
	@Nullable
	public es.caib.seycon.ng.model.ServerEntity server;

	@Column (name="SCT_ACTIVE", defaultValue="false")
	public boolean active;

	@Column (name="SCT_ENABLE", defaultValue="false")
	public boolean enabled;

	@Column (name="SCT_TEN_ID")
	public TenantEntity tenant;

	@DaoFinder
	public com.soffid.iam.model.ScheduledTaskEntity findByName(
		java.lang.String name) {
	 return null;
	}

	@DaoFinder("select ste "
			+ "from  com.soffid.iam.model.ScheduledTaskEntity as ste "
			+ "join ste.tenant as tenant "
			+ "join tenant.servers as servers "
			+ "join servers.tenantServer as server "
			+ "where server.name=:server")
	public Collection<com.soffid.iam.model.ScheduledTaskEntity> findAllByServer(
		java.lang.String server) {
	 return null;
	}

	@DaoFinder("select ste\n"
			+ "from  com.soffid.iam.model.ScheduledTaskEntity as ste\n"
			+ "join ste.handler as handler\n"
			+ "where handler.name=:handlerName and ste.params=:params\n"
			+ "and ste.tenant.id=:tenantId")
	public com.soffid.iam.model.ScheduledTaskEntity findByHandlerParams(
		java.lang.String handlerName, 
		java.lang.String params) {
	 return null;
	}
}



@Index (name="SC_SCHTAS_NAME_UK",	unique=true,
	entity=com.soffid.iam.model.ScheduledTaskEntity.class,
	columns={"SCT_TEN_ID", "SCT_NAME"})
abstract class ScheduledTaskEntityNameIndex {
}


@Index (name="SC_SCHTAS_HANDLER_UK",	unique=true,
entity=com.soffid.iam.model.ScheduledTaskEntity.class,
columns={"SCT_HANDLE", "SCT_TEN_ID", "SCT_PARAM"})
abstract class ScheduledTaskEntityHandlerIndex {
}


