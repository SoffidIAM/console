/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005, JBoss Inc., and individual contributors as indicated
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package es.caib.bpm.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * is one execution of a {@link org.jbpm.graph.def.ProcessDefinition}. To create
 * a new process execution of a process definition, just use the
 * {@link #ProcessInstance(ProcessDefinition)}.
 * 
 */
public class ProcessInstance implements Serializable {

	private static final long serialVersionUID = 1L;

	long id = 0;
	protected Date start = null;
	protected Date end = null;
	protected String description = null;
	protected Map variables = null;
	protected String currentTask = null;
	protected Vector comments = null;
	protected ClassLoader processClassLoader = null;
	
	// constructors
	// /////////////////////////////////////////////////////////////

	public ClassLoader getProcessClassLoader()
	{
		return processClassLoader;
	}

	public void setProcessClassLoader(ClassLoader processClassLoader)
	{
		this.processClassLoader = processClassLoader;
	}

	public String getCurrentTask() {
		return currentTask;
	}

	public void setCurrentTask(String currentTask) {
		this.currentTask = currentTask;
	}

	public ProcessInstance() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public Map getVariables() {
		return variables;
	}

	public void setVariables(Map variables) {
		this.variables = variables;
	}

	public Collection getComments() {
		return comments;
	}

	public void setComments(Vector comments) {
		this.comments = comments;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

}
