package es.caib.seycon.ng.sync.intf;

public class ExtensibleObject extends com.soffid.iam.sync.intf.ExtensibleObject
{
	public static ExtensibleObject toExtensibleObject ( com.soffid.iam.sync.intf.ExtensibleObject source)
	{
		ExtensibleObject target = new ExtensibleObject();
		target.putAll(source);
		target.setObjectType(source.getObjectType());
		return target;
	}
	
	
	public String toString ()
	{
		return super.toString();
	}
}


