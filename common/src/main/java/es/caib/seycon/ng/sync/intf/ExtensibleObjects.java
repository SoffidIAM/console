package es.caib.seycon.ng.sync.intf;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.soffid.iam.api.Account;
import com.soffid.iam.api.User;
import es.caib.seycon.ng.sync.intf.ExtensibleObject;

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

	public static ExtensibleObjects toExtensibleObjects (com.soffid.iam.sync.intf.ExtensibleObjects eos)
	{
		ExtensibleObjects target = new ExtensibleObjects();
		for (com.soffid.iam.sync.intf.ExtensibleObject eo: eos.getObjects())
		{
			target.objects.add( ExtensibleObject.toExtensibleObject(eo));
		}
		return target;
	}

	
}
