//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="ROL_UK_NOM_IDDISPAT_IDAPL",	unique=true,
	entity=es.caib.seycon.ng.model.RolEntity.class,
	columns={"ROL_NOM", "ROL_IDDISPAT", "ROL_IDAPL"})
public abstract class RolIndex {
}

