package es.caib.seycon.ng.sync.intf;

public class ExtensibleObject extends com.soffid.iam.sync.intf.ExtensibleObject
{
	public static ExtensibleObject toExtensibleObject ( com.soffid.iam.sync.intf.ExtensibleObject source)
	{
		return new WrappedExtensibleObject(source);
	}
	
	
	public String toString ()
	{
		return super.toString();
	}
}


