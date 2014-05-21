//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="DOM_UK_NOM_APP",	unique=true,
	entity=es.caib.seycon.ng.model.DominiAplicacioEntity.class,
	columns={"DOM_APP", "DOM_NOM"})
public abstract class DominiAplicacioIndex {
}

