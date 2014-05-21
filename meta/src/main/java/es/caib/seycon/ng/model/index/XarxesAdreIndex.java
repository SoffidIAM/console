//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="XAR_UK_ADRIP",	unique=true,
	entity=es.caib.seycon.ng.model.XarxaEntity.class,
	columns={"XAR_ADRIP"})
public abstract class XarxesAdreIndex {
}

