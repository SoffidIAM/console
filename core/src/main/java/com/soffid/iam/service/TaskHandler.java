/**
 * 
 */
package com.soffid.iam.service;

import com.soffid.iam.api.ScheduledTask;

/**
 * @author bubu
 *
 */
public interface TaskHandler
{
	void run () throws Exception;
	
	void setTask (ScheduledTask task);
	ScheduledTask getTask ();
	
}
