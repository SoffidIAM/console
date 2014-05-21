//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject 
public abstract class AttributeMapping {

	@Nullable
	public java.lang.Long id;

	public java.lang.String soffidAttribute;

	public java.lang.String systemAttribute;

	public es.caib.seycon.ng.comu.AttributeDirection direction;

	public java.lang.Long objectId;

}
