//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="SC_AUTROL_UK1",	unique=true,
	entity=es.caib.seycon.ng.model.AutoritzacioRolEntity.class,
	columns={"AUR_AUTCOD", "AUR_ROL"})
public abstract class AutoritzacioRolIndex {
}

