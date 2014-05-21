//
// (C) 2013 Soffid
// 
// This file is licensed by Soffid under GPL v3 license
//

package es.caib.seycon.ng.model.index;
import com.soffid.mda.annotation.*;

@Index (name="TAD_UK_CODE",	unique=true,
	entity=es.caib.seycon.ng.model.TipusDadaEntity.class,
	columns={"TDA_CODI"})
public abstract class TipusDadaCodiIndex {
}

