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


/**
 * defines a task and how the actor must be calculated at runtime.
 */
public class TaskDefinition implements Serializable {
  
  private static final long serialVersionUID = 1L;
  
  public static final int PRIORITY_HIGHEST = 1;
  public static final int PRIORITY_HIGH = 2;
  public static final int PRIORITY_NORMAL = 3;
  public static final int PRIORITY_LOW = 4;
  public static final int PRIORITY_LOWEST = 5;

  protected long id;
  protected String name;
  protected String description = null;
  protected boolean isBlocking = false;
  protected boolean isSignalling = true;
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public boolean isBlocking() {
	return isBlocking;
}
public void setBlocking(boolean isBlocking) {
	this.isBlocking = isBlocking;
}
public boolean isSignalling() {
	return isSignalling;
}
public void setSignalling(boolean isSignalling) {
	this.isSignalling = isSignalling;
}

  
}
