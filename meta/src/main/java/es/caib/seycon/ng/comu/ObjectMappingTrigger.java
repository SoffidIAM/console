//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject 
public abstract class ObjectMappingTrigger {

	@Nullable
	public java.lang.Long id;

	public SoffidObjectTrigger trigger;

	@Nullable
	public java.lang.String script;

	public java.lang.Long objectId;

}
