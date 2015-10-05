//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="GRU_PUE_1",	unique=false,
	entity=es.caib.seycon.ng.model.AutoritzacioPUEGrupEntity.class,
	columns={"GPE_IDGRU"})
public abstract class AutoritzacioPUEGrupIndex {
}

