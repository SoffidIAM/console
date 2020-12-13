//
// (C) 2013 Soffid
//
//

package com.soffid.iam.model;

import java.util.Collections;
import java.util.Comparator;

import com.soffid.iam.api.ScheduledTask;
import com.soffid.iam.api.ScheduledTaskLog;

/**
 * DAO ScheduledTaskEntity implementation
 */
public class ScheduledTaskEntityDaoImpl extends ScheduledTaskEntityDaoBase
{

	@Override
	public void toScheduledTask (ScheduledTaskEntity source, ScheduledTask target)
	{
		super.toScheduledTask(source, target);
		String split [] = source.getSchedulePattern().split(" "); //$NON-NLS-1$
		if (split.length > 0)
			target.setMinutesPattern(split[0]);
		
		if (split.length > 1)
			target.setHoursPattern(split[1]);
		
		if (split.length > 2)
			target.setDayPattern(split[2]);
		
		if (split.length > 3)
			target.setMonthsPattern(split[3]);
		
		if (split.length > 4)
			target.setDayOfWeekPattern(split[4]);
		
		if (source.getServer() == null)
			target.setServerName("*"); //$NON-NLS-1$
		else
			target.setServerName(source.getServer().getName());
		target.setHandlerName(source.getHandler().getName());
		target.setTenant(source.getTenant().getName());
		target.setLogs( getScheduledTaskLogEntityDao().toScheduledTaskLogList( source.getLogs() ) );
		Collections.sort(target.getLogs(), new Comparator<ScheduledTaskLog>() {
			@Override
			public int compare(ScheduledTaskLog o1, ScheduledTaskLog o2) {
				return -o1.getTime().compareTo(o2.getTime());
			}
		});
	}

	@Override
	public void scheduledTaskToEntity (ScheduledTask source, ScheduledTaskEntity target,
					boolean copyIfNull)
	{
		super.scheduledTaskToEntity(source, target, copyIfNull);
		StringBuffer pattern = new StringBuffer();
		pattern.append(source.getMinutesPattern().replace(' ', ','))
			.append (" ") //$NON-NLS-1$
			.append(source.getHoursPattern().replace(' ', ','))
			.append (" ") //$NON-NLS-1$
			.append(source.getDayPattern().replace(' ', ','))
			.append (" ") //$NON-NLS-1$
			.append(source.getMonthsPattern().replace(' ', ','))
			.append (" ") //$NON-NLS-1$
			.append(source.getDayOfWeekPattern().replace(' ', ','));
		target.setSchedulePattern(pattern.toString());
		
		target.setHandler(getScheduledTaskHandlerEntityDao().findByName(source.getHandlerName()));
		
		if (source.getServerName() == null || "*".equals(source.getServerName())) //$NON-NLS-1$
			target.setServer(null);
		else
			target.setServer(getServerEntityDao().findByName(source.getServerName()));
	}
}
