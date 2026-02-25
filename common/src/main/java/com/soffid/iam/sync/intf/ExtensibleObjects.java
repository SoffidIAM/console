package com.soffid.iam.sync.intf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.soffid.iam.base.api.User;

public class ExtensibleObjects implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	List<ExtensibleObject> objects = new LinkedList<ExtensibleObject>();
	
	public List<ExtensibleObject> getObjects()
	{
		return objects;
	}

	public void setObjects(List<ExtensibleObject> objects)
	{
		this.objects = objects;
	}

}
