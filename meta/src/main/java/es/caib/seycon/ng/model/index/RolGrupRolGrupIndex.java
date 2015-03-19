//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="RLG_ROLGRUP_ROLGRUP",	unique=false,
	entity=es.caib.seycon.ng.model.RolsGrupEntity.class,
	columns={"RLG_ROL", "RLG_GRUP"})
public abstract class RolGrupRolGrupIndex {
}

