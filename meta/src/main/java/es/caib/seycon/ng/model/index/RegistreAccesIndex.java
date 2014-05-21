//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="RAC_AGE_I",	unique=false,
	entity=es.caib.seycon.ng.model.RegistreAccesEntity.class,
	columns={"RAC_IDSES", "RAC_CODAGE"})
public abstract class RegistreAccesIndex {
}

