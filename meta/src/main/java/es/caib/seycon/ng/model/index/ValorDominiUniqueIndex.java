//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="UNIQUE_VALOR_DOMINI",	unique=true,
	entity=es.caib.seycon.ng.model.ValorDominiAplicacioEntity.class,
	columns={"VDO_VALOR", "VDO_DESC", "VDO_DOM"})
public abstract class ValorDominiUniqueIndex {
}

