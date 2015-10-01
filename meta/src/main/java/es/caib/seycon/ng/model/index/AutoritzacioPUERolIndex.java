//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="PUE_ROL_2",	unique=false,
	entity=es.caib.seycon.ng.model.AutoritzacioPUERolEntity.class,
	columns={"RPE_IDROL"})
public abstract class AutoritzacioPUERolIndex {
}

