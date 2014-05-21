//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="SC_TAS_HASH",	unique=false,
	entity=es.caib.seycon.ng.model.TasqueEntity.class,
	columns={"TAS_HASH"})
public abstract class TasqueHashIndex {
}

