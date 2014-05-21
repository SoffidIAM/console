//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="PUE_APL_1",	unique=false,
	entity=es.caib.seycon.ng.model.PuntEntradaEntity.class,
	columns={"PUE_IDAPL"})
public abstract class PuntEntradaIndex {
}

