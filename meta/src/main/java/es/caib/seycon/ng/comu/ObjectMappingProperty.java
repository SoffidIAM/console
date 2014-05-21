//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject 
public abstract class ObjectMappingProperty {

	@Nullable
	public java.lang.Long id;

	public java.lang.String property;

	public java.lang.String value;

	public java.lang.Long objectId;

}
