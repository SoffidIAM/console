/**
 * 
 */
package com.soffid.iam.service;

import java.io.BufferedWriter;
import java.io.PrintWriter;

import com.soffid.iam.api.ScheduledTask;

/**
 * @author bubu
 *
 */
public interface TaskHandler
{
	void run (PrintWriter out) throws Exception;
	
	void setTask (ScheduledTask task);
	ScheduledTask getTask ();
	
}
