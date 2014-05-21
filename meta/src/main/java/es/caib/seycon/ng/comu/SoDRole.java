//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.comu;
import com.soffid.mda.annotation.*;

@ValueObject 
public abstract class SoDRole {

	@Nullable
	public java.lang.Long id;

	public es.caib.seycon.ng.comu.Rol role;

	public java.lang.Long ruleId;

}
