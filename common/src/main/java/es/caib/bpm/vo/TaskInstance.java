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
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 * is one task instance that can be assigned to an actor (read: put in someones
 * task list) and that can trigger the coninuation of execution of the token
 * upon completion.
 */
public class TaskInstance implements Serializable {

	private static final long serialVersionUID = 1L;

	long id = 0;
	protected String processName = null;
	protected long processId = -1L;
	protected String name = null;
	protected String description = null;
	protected String actorId = null;
	protected Date create = null;
	protected Date start = null;
	protected Date end = null;
	protected Date dueDate = null;
	protected int priority = 0;
	protected boolean isCancelled = false;
	protected boolean isOpen = true;
	protected boolean isSignalling = true;
	protected boolean isBlocking = false;
	protected String swimlane = null;
	protected Set pooledActors = null;
	protected Map variables = null;
	protected String[] transitions = null;
	protected ClassLoader processClassLoader = null;

	public ClassLoader getProcessClassLoader()
	{
		return processClassLoader;
	}

	public void setProcessClassLoader(ClassLoader processClassLoader)
	{
		this.processClassLoader = processClassLoader;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String[] getTransitions() {
		return transitions;
	}

	public TaskInstance() {
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getActorId() {
		return actorId;
	}

	public Date getCreate() {
		return create;
	}

	public Date getStart() {
		return start;
	}

	public Date getEnd() {
		return end;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public int getPriority() {
		return priority;
	}

	public boolean isCancelled() {
		return isCancelled;
	}

	public boolean isOpen() {
		return isOpen;
	}

	public boolean isSignalling() {
		return isSignalling;
	}

	public boolean isBlocking() {
		return isBlocking;
	}

	public String getSwimlane() {
		return swimlane;
	}

	public Set getPooledActors() {
		return pooledActors;
	}

	public Map getVariables() {
		return variables;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setActorId(String actorId) {
		this.actorId = actorId;
	}

	public void setCreate(Date create) {
		this.create = create;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public void setCancelled(boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
	}

	public void setSignalling(boolean isSignalling) {
		this.isSignalling = isSignalling;
	}

	public void setBlocking(boolean isBlocking) {
		this.isBlocking = isBlocking;
	}

	public void setSwimlane(String swimlane) {
		this.swimlane = swimlane;
	}

	public void setPooledActors(Set pooledActors) {
		this.pooledActors = pooledActors;
	}

    public void setVariables(Map variables) {
		this.variables = variables;
	}

	public void setTransitions(String[] transitions) {
		this.transitions = transitions;
	}

	
	public String getProcessName(){
		return this.processName;
	}
	
	public void setProcessName(String processName){
		this.processName=processName;
	}

	
	
	/**
	 * @return the processId
	 */
	public long getProcessId() {
		return processId;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(long processId) {
		this.processId = processId;
	}

	public String getCurrentAssignation( )
	{
		if (getActorId () != null)
			return getActorId();
		else
		{
			String actor = ""; //$NON-NLS-1$
			for (Iterator it2 = getPooledActors().iterator(); it2.hasNext(); )
			{
				if (actor.length() == 0)
					actor = (String) it2.next();
				else
					actor = actor + "; "+(String) it2.next(); //$NON-NLS-1$
			}
			return actor;
		}
	}
}
