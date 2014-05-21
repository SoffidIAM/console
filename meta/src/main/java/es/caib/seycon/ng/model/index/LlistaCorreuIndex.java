//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="LCO_UK_NOM_IDDCO",	unique=true,
	entity=es.caib.seycon.ng.model.LlistaCorreuEntity.class,
	columns={"LCO_NOM", "LCO_IDDCO"})
public abstract class LlistaCorreuIndex {
}

